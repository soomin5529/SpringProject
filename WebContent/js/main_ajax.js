// main화면에서 업종분류 선택시, 다음 하위 옵션의 카테고리 동적으로 받아옴
function sendToControllerSelectCategoryValue(select) {
	var category = select.getAttribute('id') == 'main' ? 'main' : 'middle';

	$.ajax({
		type : "post",
		url : "/SpringTeamProject/request/categoryOption",
		contentType : "application/x-www-form-urlencoded; charset=UTF-8",
		data : {
			'category' : category,
			'code' : select.value
		},
		success : function(textStatus) {
			if (category == 'main') {
				$("#middle").empty().append(textStatus);
			} else {
				$("#small").empty().append(textStatus);
			}
		}
	});
}

function sendToControllerguCode(select) {
	var area = select.getAttribute('id') == 'sigungu' ? 'sigungu' : 'dong';
	var code = document.getElementById("sigungu");
	code = code.options[code.selectedIndex].value;
	$.ajax({
		type : "post",
		url : "/SpringTeamProject/request/selectCode",
		contentType : "application/x-www-form-urlencoded; charset=UTF-8",
		data : {
			'area' : area,
			'code' : code
		},
		success : function(textStatus) {
			var gu_name = textStatus;
			// $('#result').empty().append(textStatus);
			document.getElementById("sigunguName").innerHTML = gu_name;
		}
	});
}

function sendToControllerdongCode(select) {
	var area = select.getAttribute('id') == 'sigungu' ? 'sigungu' : 'dong';
	var gucode = document.getElementById("sigungu");
	var dongcode = document.getElementById("dong");

	gucode = gucode.options[gucode.selectedIndex].value;
	dongcode = dongcode.options[dongcode.selectedIndex].value;
	$.ajax({
		type : "post",
		url : "/SpringTeamProject/request/selectCode",
		contentType : "application/x-www-form-urlencoded; charset=UTF-8",
		data : {
			'area' : area,
			'code' : dongcode
		},
		success : function(textStatus) {
			var dong_name = textStatus;
			// $('#result').append(textStatus);
			document.getElementById("dongName").innerHTML = dong_name;
		}
	});
}

function sendLike(board) {
	var boardid = board.parentNode.parentNode.id;
	var status = "insert";
	var postLikeBtn = document.getElementById(boardid);
	var userid = document.getElementById("userid").value;
	if (postLikeBtn.classList.contains('on')) {
		status = "delete";
	}

	$.ajax({
		type : "post",
		url : "/SpringTeamProject/request/insertLike",
		contentType : "application/x-www-form-urlencoded; charset=UTF-8",
		data : {
			'userid' : userid,
			'boardid' : boardid,
			'status' : status
		},
		success : function(textStatus) {
			postLike(boardid + "u");
		}
	});
}
/* 동 선택하면 (셀렉트 박스 or 동 핀 클릭) 대쉬보드 나오게 한다. */
function openDashBoard(dongCode) {
	var code = dongCode
	$.ajax({
		type : "post",
		url : "/SpringTeamProject/dashBoard/dong/" + code,
		contentType : "application/x-www-form-urlencoded; charset=UTF-8",
		success : function(text) {
			document.getElementById('dash-board').innerHTML = text;
			drawDouhnutChart(code);
			drawBarChart(code);
			importantFrenchise(code);
		}
	});
}
/* DashBoard에 도넛 차트 */
function drawDouhnutChart(dongCode) {
	$.ajax({
		type : "post",
		url : "/SpringTeamProject/dashBoard/douhnutchart",
		contentType : "application/json; charset=UTF-8",
		dataType : "json",
		data : JSON.stringify({
			"code" : dongCode
		}),
		success : function(data) {
			var ctx1 = document.getElementById('chart01').getContext('2d');
			var data1 = data[0];
			var data2 = data[1];
			var data3 = data[2];
			/* doughnutChart */
			var doughnutData = {
				label : '# of Votes',
				data : [ data1.sum, data2.sum, data3.sum ],
				backgroundColor : [ 'rgba(88, 77, 228, 1.0)',
						'rgba(88, 77, 228, 0.6)', 'rgba(88, 77, 228, 0.2)' ],
				borderWidth : 2
			}
			var doughnutOption = {
				animation : { /* 등장 애니메이션 */
					animateRotate : true,
					animateScale : true
				},
				legend : { /* 범례 옵션 */
					display : true,
					position : 'right',
					align : 'end',
					maintainAspectRatio : false, /* false = 차트 비율 부모 div 따름 */
					labels : {
						boxWidth : 10
					}
				}
			}
			var doughnutChart = new Chart(ctx1, {
				type : 'doughnut',
				data : {
					labels : [ data1.name, data2.name, data3.name ],
					datasets : [ doughnutData ]
				},
				options : doughnutOption
			});
		}
	});
}
/* DashBoard에 바 차트 */
function drawBarChart(dongCode) {
	$
			.ajax({
				type : "post",
				url : "/SpringTeamProject/dashBoard/barchart",
				contentType : "application/json; charset=UTF-8",
				dataType : "json",
				data : JSON.stringify({
					"code" : dongCode
				}),
				success : function(data) {
					var ctx2 = document.getElementById('chart02').getContext(
							'2d');
					var data1 = data[0];
					var data2 = data[1];
					var data3 = data[2];
					var data4 = data[3];
					var data5 = data[4];

					var max = Math.round(data1.sum / (data1.sum + "").length)
							* (data1.sum + "").length;
					/* barChart */
					var barData = {
						data : [ data1.sum, data2.sum, data3.sum, data4.sum,
								data5.sum ],
						backgroundColor : [ 'rgba(88, 77, 228, 1.0)',
								'rgba(0, 0, 0, 0.1)', 'rgba(0, 0, 0, 0.1)',
								'rgba(0, 0, 0, 0.1)', 'rgba(0, 0, 0, 0.1)' ]
					};

					var barOption = {
						legend : {
							display : false
						},
						scales : {
							yAxes : [ {
								barPercentage : 0.5,
								gridLines : {
									display : false
								}
							} ],
							xAxes : [ {
								gridLines : {
									display : false
								},
								ticks : { /* x축 범위 */
									min : 0,
									max : max,
									stepSize : Math.ceil(max / 5)
								}
							} ]
						},
						elements : {
							rectangle : {
								borderSkipped : 'left',
							}
						}
					};

					var barChart = new Chart(ctx2, {
						type : 'horizontalBar',
						data : {
							labels : [ data1.name, data2.name, data3.name,
									data4.name, data5.name ],
							datasets : [ barData ],
						},
						options : barOption
					});
				}
			});
}
/* DashBoard에 주요 생활 프렌차이즈 */
function importantFrenchise(dongCode) {
	$.ajax({
				type : "post",
				url : "/SpringTeamProject/dashBoard/frenchise",
				contentType : "application/json; charset=UTF-8",
				dataType : "json",
				data : JSON.stringify({
					"code" : dongCode
				}),
				success : function(data) {
					$("#importantFranchise").empty();
					for ( var i in data) {
						if (data[i].name == '스타벅스') {
							$("#importantFranchise")
									.append(
											'<li> <svg viewBox="0 0 40 40" class="shop-icon float-l">'
													+ '<path d="M4 15h25v15a6 6 0 01-6 6H10a6 6 0 01-6-6V15zm25 0h2a6 6 0 110 12h-2V15zM14 3c0 2 1 3.5 3 4.4 2 1.1 3 2.6 3 4.6"></path>'
													+ '</svg> <span>스타벅스</span><span class="float-r"><b>'
													+ data[i].sum
													+ '</b>개</span> </li>');
						}
						if (data[i].name == '맥도날드') {
							$("#importantFranchise")
									.append(
											'<li> <svg viewBox="0 0 40 40" class="shop-icon float-l"><path d="M16.5 20.5h17v14h-17zM7 5.5h26l3.5 8.5v6.5h-33V14zm4.5 9.5v6m-5 0v15m22-21v6m-8-6v6"></path></svg>'
													+ '<span>맥도날드</span> <span class="float-r"><b>'
													+ data[i].sum
													+ '</b>개</span></li>');
						}
						if (data[i].name == '올리브영') {
							$("#importantFranchise")
									.append(
											'<li> <svg viewBox="0 0 40 40" class="shop-icon float-l"><path d="M16.5 20.5h17v14h-17zM7 5.5h26l3.5 8.5v6.5h-33V14zm4.5 9.5v6m-5 0v15m22-21v6m-8-6v6"></path></svg>'
													+ '<span>올리브영</span> <span class="float-r"><b>'
													+ data[i].sum
													+ '</b>개</span></li>');
						}
						if (data[i].name == '파리바게트') {
							$("#importantFranchise")
									.append(
											'<li> <svg viewBox="0 0 40 40" class="shop-icon float-l">'
													+ '<path class="st0" d="M31.5,11.4c-1.8,0-3.5,0.9-4.5,2.2c-0.9-3-3.7-5.2-6.9-5.2s-6.1,2.2-6.9,5.2c-1.1-1.3-2.7-2.2-4.5-2.2c-3.2,0-5.7,2.6-5.7,5.7v16.4h10h1.5h11.5h1.5h10V17.2C37.2,14,34.6,11.4,31.5,11.4z" />'
													+ '<line class="st0" x1="13.1" y1="13.7" x2="13.1" y2="19.3" /><line class="st0" x1="26.9" y1="13.7" x2="26.9" y2="19.3" /> </svg>'
													+ '<span>파리바게트</span> <span class="float-r"><b>'
													+ data[i].sum
													+ '</b>개</span></li>');
						}
					}
				}
			});
}
/* 대분류 선택 시 동에 있는 대분류 하위 중분류를 가져온다. */
function dashBoardMiddleCategory(industryCode, dongCode) {
	$.ajax({
		type : "post",
		url : "/SpringTeamProject/dashBoard/middlecategory",
		contentType : "application/json; charset=UTF-8",
		dataType : "json",
		data : JSON.stringify({
			"dongCode" : dongCode,
			"mainCode" : industryCode
		}),
		success : function(data) {
			$('#middle').empty().append('<option value="no" disabled selected>중분류</option>');
			for(var i in data){
				$('#middle').append('<option value='+ data[i].code +'>' + data[i].name + '</option>');
			}
		}
	});
}
/* 중분류 선택 시 동에 있는 중분류 하위 소분류를 가져온다. */
function dashBoardSmallCategory(industryCode, dongCode) {
	$.ajax({
		type : "post",
		url : "/SpringTeamProject/dashBoard/smallcategory",
		contentType : "application/json; charset=UTF-8",
		dataType : "json",
		data : JSON.stringify({
			"dongCode" : dongCode,
			"middleCode" : industryCode
		}),
		success : function(data) {
			$('#small').empty().append('<option value="no" disabled selected>소분류</option>');
			for(var i in data){
				$('#small').append('<option value='+ data[i].code +'>' + data[i].name + '</option>');
			}
		}
	});
}
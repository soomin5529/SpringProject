<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String display = request.getParameter("display");
%>

<div class="map-popup dashboard" id="dashboard"
	style="display:none; display:<%=display%>">
	<div class="title-box cf">
		<div class="back-btn" onclick="closePopDashboard();">
			<svg viewBox="0 0 40 40" class="back-icon">
				<path d="M18 35L3 20 18 5" />
			</svg>
		</div>
		<div class="tit">
<<<<<<< HEAD:WebContent/jsp/view/board/dashBoard.jsp
			  <%-- ${sigungu.name} ${dong.name} --%>
			<span id="sigunguName"></span>&nbsp;<span id="dongName"></span>
=======
			<!--<c:forEach var="sigungu" items="${sigungu}">
			  ${sigungu.name}
			  
			  
			</c:forEach>
			<c:forEach var="dong" items="${dong }">
				 ${dong.name}
			</c:forEach>-->
			<span id="sigunguName" ></span>&nbsp;<span id="dongName" ></span>
>>>>>>> origin/mina:WebContent/jsp/board/dashBoard.jsp
		</div>
		<!-- bookmark on/off -->
		<div class="bookmark off" id="bookmark" onclick="bookmark()">
			<svg viewBox="0 0 55 55" class="bookmark-icon">
					<path
					d="M9.9,30.8l-9.1-8.9c-1.6-1.6-0.7-4.3,1.5-4.6l12.6-1.8c0.9-0.1,1.6-0.7,2-1.5l5.7-11.4c1-2,3.8-2,4.8,0L33.1,14c0.4,0.8,1.1,1.3,2,1.5l12.6,1.8c2.2,0.3,3.1,3,1.5,4.6L40,30.8c-0.6,0.6-0.9,1.5-0.8,2.4l2.1,12.6c0.4,2.2-1.9,3.9-3.9,2.8l-11.3-5.9c-0.8-0.4-1.7-0.4-2.5,0l-11.3,5.9c-2,1-4.3-0.6-3.9-2.8l2.2-12.6C10.9,32.3,10.6,31.4,9.9,30.8z" />
				</svg>
		</div>
	</div>

	<!-- 검색항목 묶음 - 업종선택 -->
	<div class="search-box">
		<div class="label-box">업종 선택</div>
		<div class="input-box third">
			<select id="main" name="main_category"
				onchange="javascript:sendToControllerSelectCategoryValue(this);">
				<option value="no" disabled selected>대분류</option>
				<c:forEach var="main" items="${main }">
					<option value="${main.code}">${main.name}</option>
				</c:forEach>
			</select> <select id="middle" name="middle_category"
				onchange="javascript:sendToControllerSelectCategoryValue(this);">
				<option value="no" disabled selected>중분류</option>
			</select> <select id="small" name="small_category">
				<option value="no" disabled selected>소분류</option>
			</select>
		</div>
	</div>

	<!-- 통계자료 묶음 -->
	<div class="chart-box">
		<div class="chart">
			<div class="tit">대분류 업종 Top3</div>
			<div id="chartContainer01"></div>
		</div>
		<div class="chart">
			<div class="tit">중분류 업종 Top5</div>
			<div id="chartContainer02"></div>
		</div>
		<div class="chart">
			<div class="tit">주요 생활시설 개수</div>
			<ul>
				<li>
					<svg viewBox="0 0 40 40" class="shop-icon float-l"><path d="M4 15h25v15a6 6 0 01-6 6H10a6 6 0 01-6-6V15zm25 0h2a6 6 0 110 12h-2V15zM14 3c0 2 1 3.5 3 4.4 2 1.1 3 2.6 3 4.6"></path></svg>
					<span>스타벅스</span>
					<span class="float-r"><b id="facility01"></b>개</span>
				</li>
				<li>
					<svg viewBox="0 0 40 40" class="shop-icon float-l"><path class="st0" d="M31.5,11.4c-1.8,0-3.5,0.9-4.5,2.2c-0.9-3-3.7-5.2-6.9-5.2s-6.1,2.2-6.9,5.2c-1.1-1.3-2.7-2.2-4.5-2.2c-3.2,0-5.7,2.6-5.7,5.7v16.4h10h1.5h11.5h1.5h10V17.2C37.2,14,34.6,11.4,31.5,11.4z"/><line class="st0" x1="13.1" y1="13.7" x2="13.1" y2="19.3"/><line class="st0" x1="26.9" y1="13.7" x2="26.9" y2="19.3"/></svg>
					<span>파리바게트</span>
					<span class="float-r"><b id="facility02"></b>개</span>
				</li>
				<li>
					<svg viewBox="0 0 40 40" class="shop-icon float-l"><path d="M16.5 20.5h17v14h-17zM7 5.5h26l3.5 8.5v6.5h-33V14zm4.5 9.5v6m-5 0v15m22-21v6m-8-6v6"></path></svg>
					<span>올리브영</span>
					<span class="float-r"><b id="facility03"></b>개</span>
				</li>
			</ul>
			
		</div>
	</div>
	<!-- 떠들썩 커뮤니티 버튼 -->
	<button type="button" class="btn-full btn01-reverse"
		onclick="openPopCommunity()">떠들썩</button>
</div>

<script>
function drawChart(data) {
	var chartContainer01 = document.getElementById('chartContainer01');
	var chartContainer02 = document.getElementById('chartContainer02');
	chartContainer01.innerHTML = '&nbsp;';
	chartContainer01.innerHTML = '<canvas id="chart01"></canvas>';
	chartContainer02.innerHTML = '&nbsp;';
	chartContainer02.innerHTML = '<canvas id="chart02"></canvas>';
	var ctx1 = document.getElementById('chart01').getContext('2d');
	var ctx2 = document.getElementById('chart02').getContext('2d');
	
	/* chart data 배열에 넣기 */
	var industryCount = new Array();
	var industryName = new Array();
	for (var i = 0; i < data.length; i++) {
		industryCount[i] = data[i].industryCount;
		industryName[i] = data[i].industryName;
	}
	
	/* doughnutChart */
	var doughnutData = {
		label : '# of Votes',
		data : [industryCount[0], industryCount[1], industryCount[2]],
		backgroundColor : [ 'rgba(88, 77, 228, 1.0)', 'rgba(88, 77, 228, 0.5)', 'rgba(0, 0, 0, 0.1)' ],
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
	var config1 = {
		type : 'doughnut',
		data : {
			labels : [ industryName[0], industryName[1], industryName[2]],
			datasets : [ doughnutData ]
		},
		options : doughnutOption
	}
	/* end of doughnutChart */
	
	/* barChart */
	var barData = {
		data : [ industryCount[3], industryCount[4], industryCount[5], industryCount[6], industryCount[7]],
		backgroundColor : [ 
			'rgba(88, 77, 228, 1.0)', 
			'rgba(0, 0, 0, 0.1)',
			'rgba(0, 0, 0, 0.1)',
			'rgba(0, 0, 0, 0.1)',
			'rgba(0, 0, 0, 0.1)' ]
	};
	var barOption = {
		legend: {
	        display: false
	    },
		scales : {
			yAxes : [{
				barPercentage : 0.5,
				gridLines : {
					display:false
				}
			}],
			xAxes : [{
				 gridLines: {
			        display:false
				},
				ticks : { /* x축 범위 */
					min : 0
				}
			}]
		},
		elements : {
			rectangle : {
				borderSkipped : 'left',
			}
		}
	};
	var config2 = {
			type : 'horizontalBar',
		data : {
			labels : [ industryName[3], industryName[4], industryName[5], industryName[6], industryName[7] ],
			datasets : [ barData ],
		},
		options : barOption
	};
	/* end of barChart */
	
   	doughnutChart = new Chart(ctx1, config1);
	barChart = new Chart(ctx2, config2);
	
	/* 주요시설 count */
	document.getElementById("facility01").innerHTML = industryCount[10];
	document.getElementById("facility02").innerHTML = industryCount[9];
	document.getElementById("facility03").innerHTML = industryCount[8];
	
}

</script>

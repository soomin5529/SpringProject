/* jQuery onload */
$(document).ready(function(){
	/* svgMap hover 이벤트 */
	$("#svgMap g").mouseover(function(event) {
		var cls = $(this).attr('class');
		var _path = event.target;
		var city_name = _path.id;
		var _over_g = $(this).find('path, polygon');

		if (cls == 'city') {
			_path.setAttribute("fill", "#584de4");

		} else {
			$.each(_over_g, function(index, item) {
				item.setAttribute("fill", "#584de4")
			});
		}
	}).mouseout(function(event) {
		var cls = $(this).attr('class');
		var _path = event.target;
		if (cls == 'city') {
			_path.setAttribute('fill', '#fff');
		} else {
			var _path = event.target;
			var _over_g = $(this).find('path, polygon');
			$.each(_over_g, function(index, item) {
				item.setAttribute('fill', '#fff')
			});
		}
	});
	
	$(window).scroll( function(){
        $('.fadeinslide').each( function(i){
            var delayPosition = 200;
            var bottomOfEl = $(this).offset().top + $(this).outerHeight() - delayPosition;
            var bottomOfWin = $('.page').scrollTop() + $('.page').height();
            
            if( bottomOfWin > bottomOfEl ){
                $(this).animate({'opacity':'1','margin-top':'0px'},1000);
            } 
        }); 
    });
	
	$(window).scroll( function(){
        $('.fadein').each( function(i){
            var bottomOfEl = $(this).offset().top + $(this).outerHeight();
            var bottomOfWin = $('.page').scrollTop() + $('.page').height();
            
            if( bottomOfWin > bottomOfEl ){
                $(this).animate({'opacity':'1'},1000);
            } 
        }); 
    });
});


/* slider */
var slider = document.getElementById("myRange");
var output = document.getElementById("funds");
output.innerHTML = slider.value; // Display the default slider value
// slider - Update the current slider value
slider.oninput = function() {
	output.innerHTML = this.value;
}


/* svg Map */
var svgSeoul = document.getElementById("svgMapSeoul");
var svgIndex = document.getElementById("svgMapIndex");
/* svgMap 서울 지도 뿌리기 */
function goToSeoul(){
	svgIndex.style.display = "none";
	svgSeoul.style.display = "block";
}
/* svgMap 전국 지도 뿌리기 */
function goToFullmap(){
	svgIndex.style.display = "none";
	svgSeoul.style.display = "block";
}


function returnAreaArray(districtType, districtCode) {
   $.ajax({
      type : "post",
      url : "/SpringTeamProject/request/areaOption",
      dataType : 'json',
      contentType : "application/json; charset=UTF-8",
      data : JSON.stringify({
         'type' : districtType,
         'code' : districtCode
      }),
      success : function(areaList) {
         var options = '<option value=\"no\" disabled selected>선택</option>';
         for ( var i in areaList) {
            if (districtType.includes('sido')) {
               sigunguArray.push(areaList[i]);
            }
            if (districtType.includes('sigungu')) {
               dongArray.push(areaList[i]);
            }
            options += '<option value=' + areaList[i].code + '>'
                  + areaList[i].name + '</option>\n'
         }
         var areaId = districtType == 'sido' ? 'sigungu' : 'dong';
         $("#" + areaId).empty().append(options);
      }
   });
}

// main화면에서 행정구역을 선택하면 동적으로 하위의 행정구역을 받아옵니다.
// 전역변수 Array
var sigunguArray = new Array();
var dongArray = new Array();
function choiceAdministrativeDistrict2(select) {

   // 시도 선택 -> 시군구리스트 출력 및 담기
   if (select.getAttribute('id') == 'sido') {
      sigunguArray = [];
      returnAreaArray('sido', select.value);
   }
   if (select.getAttribute('id') == 'sigungu') {
      dongArray = [];
      returnAreaArray('sigungu', select.value);
   }
}

// main화면에서 업종분류 선택시, 다음 하위 옵션의 카테고리 동적으로 받아옴
function sendToControllerSelectCategoryValue2(select) {
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

/* 창업기상도 검색 */
var container = document.getElementById("svgMapBox");
function startupWeatherSearch(dong, small_category, funds){
	if(dong == "no" || small_category == "no"){
		alert("지역 및 업종을 선택하세요");
	}else{
		$.ajax({
		    type : "post",
		    url : "/SpringTeamProject/view/startupWeatherSearch",
		    contentType : "application/x-www-form-urlencoded; charset=UTF-8",
		    data : {
		       'dongCode' : dong,
		       'smallCode' : small_category,
		       'funds' : funds
		    },
		    success : function(textStatus) {
		    	container.style.display="none";
		    	document.getElementById("SearchResultBox").innerHTML=textStatus;
		    	drawRadarChart(dong, small_category);
		    }
		});
	}
}


/* 창업기상도 차트  */
function drawRadarChart(dong, small_category){
// 하드코딩 데이터로 할 경우 여기서부터---------------------------------------------
//	var chartContainer03 = document.getElementById('chartContainer03');
//	chartContainer03.innerHTML = '<canvas id="radarChart"></canvas>';
//	var ctx3 = document.getElementById('radarChart').getContext('2d');
//
//	var dataTotal = 60;//합계
//	
//	var radarData = {
//		data : [20, 15, 5, 10, 10],
//		borderColor : ['rgba(255, 205, 86, 1.0)'],
//		backgroundColor : ['rgba(255, 205, 86, 0.3)'],
//		point : {
//			hitRadius:[10]
//		}
//	}
//
//	var radarOption = {
//		legend: {
//	        display: false
//	    },
//		scale: {
//	        angleLines: {
//	            display: false
//	        },
//	        ticks: {
//	            suggestedMin: 0,
//	            suggestedMax: 20
//	        }
//	    }
//	}
//
//	var config1 = {
//		type : 'radar',
//		data : {
//			labels : ['희소성','영업력','안정성','지속성','집객력'],
//			datasets : [ radarData ],
//		},
//		options : radarOption
//	};
//
//	var myRadarChart = new Chart(ctx3, config1);
//	
//	insertMsg(dataTotal);
//	insertGrade(dataTotal);
// 하드코딩 데이터로 할 경우 여기까지---------------------------------------------
	
//	AJAX로 할경우 여기서부터---------------------------------------------
	$.ajax({
		type : "post",
		url : "/SpringTeamProject/view/sendRadarChart",
		contentType : "application/json; charset=UTF-8",
		dataType : "json",
		data : JSON.stringify({
			'dongCode' : dong,
			'smallCode' : small_category
		}),
		success : function(data) {
			var chartContainer03 = document.getElementById('chartContainer03');
			chartContainer03.innerHTML = '<canvas id="radarChart"></canvas>';
			var ctx3 = document.getElementById('radarChart').getContext('2d');
			
			var data1 = data.rare;//희소성
			var data2 = data.sales;//영업력
			var data3 = data.safety;//안전성
			var data4 = data.persistence;//지속성
			var data5 = data.population;//집객력
			var dataTotal = data.total;//합계
			
			var radarData = {
				data : [data1, data2, data3, data4, data5],
				borderColor : ['rgba(255, 205, 86, 1.0)'],
				backgroundColor : ['rgba(255, 205, 86, 0.3)']
			}
		
			var radarOption = {
				legend: {
			        display: false
			    },
				scale: {
			        angleLines: {
			            display: false
			        },
			        ticks: {
			            suggestedMin: 0,
			            suggestedMax: 20
			        }
			    }
			}
		
			var config1 = {
				type : 'radar',
				data : {
					labels : ['희소성','영업력','안정성','지속성','집객력'],
					datasets : [ radarData ],
				},
				options : radarOption
			};
		
			var myRadarChart = new Chart(ctx3, config1);
			
			insertMsg(dataTotal);
			insertGrade(dataTotal);
		},
		error : function(){
			alert("왜 에러나ㅏㅏㅏ");
		}
	});
//	AJAX로 할경우 여기까지---------------------------------------------
}

//등급에 따라 메세지 출력
function insertMsg(dataTotal){
	var iconBox = document.getElementById("resultIconBox");
	var txtBox = document.getElementById("resultTxt");
	var resultMsg;
	
	if(67 < dataTotal){
		resultMsg = "맑아요!";
		txtBox.innerHTML = resultMsg;
		iconBox.innerHTML =
			'<svg class="score01" width="80" height="80" viewBox="-1 -1 82 82">'
			+' <circle class="bg" cx="40" cy="40" r="40"/>'
			+' <path class="mouth" d="M40,50.7c-2.2,0-4.1-0.4-5.8-1.1c0,0.4-0.1,0.7-0.1,1.1c0,4.1,2.7,7.4,5.9,7.4s5.9-3.3,5.9-7.4'
			+' 	c0-0.4,0-0.8-0.1-1.1C44.1,50.3,42.2,50.7,40,50.7z"/>'
			+' <ellipse class="cheek" cx="13.4" cy="44.9" rx="3.7" ry="1.5"/>'
			+' <ellipse class="cheek" cx="66.6" cy="44.9" rx="3.7" ry="1.5"/>'
			+' <path class="eye" d="M61.6,33.7c0,0-6,0.8-8.6,5.6c0,0,3.7-0.5,8.6,0.8"/>'
			+' <path class="eye" d="M18.4,33.7c0,0,6,0.8,8.6,5.6c0,0-3.7-0.5-8.6,0.8"/>'
			+' </svg>';
	}else if(34 <= dataTotal && dataTotal <= 66){
		resultMsg = "보통이에요";
		txtBox.innerHTML = resultMsg;
		iconBox.innerHTML =
			'<svg class="score03" width="80" height="80" viewBox="-1 -1 82 82">'
			+'	<circle class="bg" cx="40" cy="40" r="40"/>'
			+'	<path class="eye" d="M27.4,40.7H16.7c-0.4,0-0.7-0.3-0.7-0.7V37c0-0.4,0.3-0.7,0.7-0.7h10.7c0.4,0,0.7,0.3,0.7,0.7v2.9'
			+'		C28.2,40.3,27.8,40.7,27.4,40.7z"/>'
			+'	<path class="eye" d="M63.3,40.7H52.6c-0.4,0-0.7-0.3-0.7-0.7V37c0-0.4,0.3-0.7,0.7-0.7h10.7c0.4,0,0.7,0.3,0.7,0.7v2.9'
			+'		C64,40.3,63.7,40.7,63.3,40.7z"/>'
			+'	<path class="mouth" d="M33.1,52.9l2.6-1.3c2.7-1.4,5.9-1.4,8.7,0l2.6,1.3"/>'
			+'	<ellipse class="cheek" cx="13.4" cy="48.2" rx="3.7" ry="1.5"/>'
			+'	<ellipse class="cheek" cx="66.6" cy="48.2" rx="3.7" ry="1.5"/>'
			+'</svg>';
	}else{
		resultMsg = "흐려요";
		txtBox.innerHTML = resultMsg;
		iconBox.innerHTML =
			'<svg class="score04" width="80" height="80" viewBox="-1 -1 82 82">'
			+' 	<circle class="bg" cx="40" cy="40" r="40"/>'
			+' 	<path class="eye" d="M53.1,38.8c-0.3,0.2-0.4,0.6-0.2,0.9c1.5,2.3,4.5,3.3,7.1,2.1s3.8-4.1,2.9-6.7c-0.1-0.3-0.5-0.5-0.8-0.4L53.1,38.8z"/>'
			+' 	<path class="eye" d="M26.9,38.8c0.3,0.2,0.4,0.6,0.2,0.9c-1.5,2.3-4.5,3.3-7.1,2.1s-3.8-4.1-2.9-6.7c0.1-0.3,0.5-0.5,0.8-0.4L26.9,38.8z"/>'
			+' 	<polygon class="mouth" points="30.1,48.8 33.4,50.7 36.7,48.8 40,50.7 43.3,48.8 46.6,50.7 49.9,48.8 49.9,57.2 46.6,59.1 43.3,57.2 40,59.1 36.7,57.2 33.4,59.1 30.1,57.2 	"/>'
			+' </svg>';
	}
	
}


//점수 출력
function insertGrade(dataTotal){
	var pointBox = document.getElementById("point");
	var gradeBox = document.getElementById("grade");
	var grade;
	
	switch(true) {
		case (80 < dataTotal) : grade = 1; break;
		case (60 < dataTotal) : grade = 2; break;
		case (40 < dataTotal) : grade = 3; break;
		case (20 < dataTotal) : grade = 4; break;
		defalt : grade = 5; break;
	}
	pointBox.innerHTML = dataTotal;
	gradeBox.innerHTML = grade;
}
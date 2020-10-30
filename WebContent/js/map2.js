/* 최초 지도 생성 */
var mapOptions = {
	center : new naver.maps.LatLng(37.5642135, 127.0016985),
	zoom : 10,
	zoomControl : true,
	zoomControlOptions : {
		style : naver.maps.ZoomControlStyle.SMALL,
		position : naver.maps.Position.TOP_RIGHT
	}
};

var map = new naver.maps.Map('map', mapOptions);
/* 구별 좌표 */
var Gangnamgu = new naver.maps.LatLng(37.4959854, 127.0664091);
var Dobonggu = new naver.maps.LatLng(37.6658609, 127.0317674);
var Eunpyeonggu = new naver.maps.LatLng(37.6176125, 126.9227004);
var Dongdaemungu = new naver.maps.LatLng(37.5838012, 127.0507003);
var Dongjakgu = new naver.maps.LatLng(37.4965037, 126.9443073);
var Geumcheongu = new naver.maps.LatLng(37.4600969, 126.9001546);
var Gurogu = new naver.maps.LatLng(37.4954856, 126.858121);
var Jongnogu = new naver.maps.LatLng(37.5990998, 126.9861493);
var Gangbukgu = new naver.maps.LatLng(37.6469954, 127.0147158);
var Jungnanggu = new naver.maps.LatLng(37.5953795, 127.0939669);
var Gangnamgu = new naver.maps.LatLng(37.4959854, 127.0664091);
var Gangseogu = new naver.maps.LatLng(37.5657617, 126.8226561);
var Junggu = new naver.maps.LatLng(37.5579452, 126.9941904);
var Gangdonggu = new naver.maps.LatLng(37.5492077, 127.1464824);
var Gwangjingu = new naver.maps.LatLng(37.5481445, 127.0857528);
var Mapogu = new naver.maps.LatLng(37.5622906, 126.9087803);
var Seochogu = new naver.maps.LatLng(37.4769528, 127.0378103);
var Seongbukgu = new naver.maps.LatLng(37.606991, 127.0232185);
var Nowongu = new naver.maps.LatLng(37.655264, 127.0771201);
var Songpagu = new naver.maps.LatLng(37.5048534, 127.1144822);
var Seodaemungu = new naver.maps.LatLng(37.5820369, 126.9356665);
var Yangcheongu = new naver.maps.LatLng(37.5270616, 126.8561534);
var Yeongdeungpogu = new naver.maps.LatLng(37.520641, 126.9139242);
var Gwanakgu = new naver.maps.LatLng(37.4653993, 126.9438071);
var Seongdonggu = new naver.maps.LatLng(37.5506753, 127.0409622);
var Yongsangu = new naver.maps.LatLng(37.5311008, 126.9810742);
/* 구 선택시 함수 */
function selectCity() {
	polygon.removeLayer;

	var city = document.getElementById("sigungu");
	var selected_city = city.options[city.selectedIndex].value;

	var selected_city_JSON;
	if (selected_city == "11680")
		selected_city = Gangnamgu;
	if (selected_city == "11320")
		selected_city = Dobonggu;
	if (selected_city == "11380")
		selected_city = Eunpyeonggu;
	if (selected_city == "11230")
		selected_city = Dongdaemungu;
	if (selected_city == "11590")
		selected_city = Dongjakgu;
	if (selected_city == "11545")
		selected_city = Geumcheongu;
	if (selected_city == "11530")
		selected_city = Gurogu;
	if (selected_city == "11110")
		selected_city = Jongnogu;
	if (selected_city == "11305")
		selected_city = Gangbukgu;
	if (selected_city == "11260")
		selected_city = Jungnanggu;
	if (selected_city == "11500")
		selected_city = Gangseogu;
	if (selected_city == "11140")
		selected_city = Junggu;
	if (selected_city == "11740")
		selected_city = Gangdonggu;
	if (selected_city == "11215")
		selected_city = Gwangjingu;
	if (selected_city == "11440")
		selected_city = Mapogu;
	if (selected_city == "11650")
		selected_city = Seochogu;
	if (selected_city == "11290")
		selected_city = Seongbukgu;
	if (selected_city == "11350")
		selected_city = Nowongu;
	if (selected_city == "11710")
		selected_city = Songpagu;
	if (selected_city == "11410")
		selected_city = Seodaemungu;
	if (selected_city == "11470")
		selected_city = Yangcheongu;
	if (selected_city == "11560")
		selected_city = Yeongdeungpogu;
	if (selected_city == "11620")
		selected_city = Gwanakgu;
	if (selected_city == "11200")
		selected_city = Seongdonggu;
	if (selected_city == "11170")
		selected_city = Yongsangu;

	map.setCenter(selected_city);
	map.setZoom(13); // 줌 레벨 변경
}
//
// /* 동선택 시 함수 */
// function selectStreet() {
// var yuksamdong = new naver.maps.LatLng(37.500457, 127.038218);
// var street = document.getElementById("dong");
// var selected_street = street.options[street.selectedIndex].value;
// var selected_street_JSON;
//		
// if (selected_street == "1168010100"){
// selected_street = yuksamdong;
// selected_street_JSON = '/SpringTeamProject/json/umd/yeoksam.json';
// }
//	
// map.setCenter(selected_street);
// map.setZoom(15); // 줌 레벨 변경
//	
// $.ajax({
// url: selected_street_JSON,
// dataType: 'json',
// success: function(data) {
// map.data.removeGeoJson(prev);
// startDataLayer(data);
// }
//        
// });
//	
// document.getElementById("dashboard").style.display = "block";
// }
//
//
// /* 영역 그리는 함수 */
// var prev = {};
// function startDataLayer(geojson) {
// map.data.setStyle(function(feature) {
// var styleOptions = {
// fillColor: '#001e91',
// fillOpacity: 0.4,
// strokeColor: '#001e91',
// strokeWeight: 2,
// strokeOpacity: 0.6
// };
// prev=geojson;
// return styleOptions;
// });
//
// map.data.addGeoJson(geojson);
//    
// map.data.addListener('click', function(e) {
// var feature = e.feature;
// var dashboard = document.getElementById("dashboard");
// var community = document.getElementById("community");
//    	
// dashboard.style.display = "block";
// if(community.style.display == "block"){
// community.style.left = "350px";
// }
// });
//
// map.data.addListener('mouseover', function(e) {
// var feature = e.feature,
// regionName = feature.getProperty('area1');
//        
// map.data.overrideStyle(feature, {
// fillOpacity: 0.6,
// strokeWeight: 4,
// strokeOpacity: 1
// });
// });
//
// map.data.addListener('mouseout', function(e) {
// map.data.revertStyle();
// });
// }

// 동 선택시 폴리곤을 그릴꺼야
var polygon = new naver.maps.Polygon({
	paths : [],
	strokeColor : 'black',
	strokeOpacity : 1,
	strokeWeight : 2,
	fillColor : 'red',
	fillOpacity : 0.1,
	zIndex : 1,
	clickable : true,
	map : map
});
function drawPolygonDong(coordinates) {

	var paths = new Array();
	var coordinatesArr = coordinates.split('/');
	for ( var i in coordinatesArr) {
		paths[i] = (new naver.maps.LatLng(coordinatesArr[i].split(',')[0],
				coordinatesArr[i].split(',')[1]));
	}
	polygon.setOptions({
		paths : paths
	});
}
//naver.maps.Event.addListener(polygon, 'click', function() {
//	polygon.setOptions({
//		strokeColor : '#E51D1A',
//		strokeOpacity : 1
//	});
//});

// 동 polygon 선택하면 dashboard 오픈
// naver.maps.Event.addListener(polygon, 'click', function() {
// openPopDashboard();
// });

function pinSigungu(array) {
	for ( var i in array) {
		var name = array[i].name;
		var code = array[i].code;
		var lat = array[i].lat;
		var lng = array[i].lng;

		var sigMarker = new naver.maps.Marker(
				{
					position : new naver.maps.LatLng(lat, lng),
					map : map,
					icon : {
						content : [
								'<div style="text-align:center; background-color:#4d55b2; padding:2px;">',
								'<div style="color:white;"> ',
								'<p>' + name + '</p>',
								'<span>' + code + '</span>', '</div>', '</div>' ]
								.join(''),
						size : new naver.maps.Size(38, 58),
						anchor : new naver.maps.Point(19, 58),
					},
				});
	}
}

var storeMarkers = [];

naver.maps.Event.addListener(map, 'mousewheel dragend', function(e) {
	console.log(storeMarkers.length);
	
	deleteStroeMarkers();

	if (map.getZoom() > 16) {
		var bound = mapBound();
		var bounts = {
			'lng2' : bound.getNE().x,
			'lng1' : bound.getSW().x,
			'lat2' : bound.getNE().y,
			'lat1' : bound.getSW().y
		}
		findStoreInMapBound(bounts);
	} else {
		deleteStroeMarkers();
	}
});

function deleteStroeMarkers(){
	// 모든 마커 지우기
	for(var i in storeMarkers){
		storeMarkers[i].setMap(null);
	}
	storeMarkers = [];
}

function mapBound() {
	var bounds = map.getBounds();
	var sw = bounds.getSW(), ne = bounds.getNE(), offsetSW = map
			.getProjection().fromCoordToOffset(sw), offsetNE = map
			.getProjection().fromCoordToOffset(ne);
	return bounds;
}

function findStoreInMapBound(bounts) {
	$.ajax({
		type : "post",
		url : "/SpringTeamProject/request/currentPageStore",
		contentType : "application/json; charset=UTF-8",
		Accept : "application/json+custom",
		dataType : 'json',
		data : JSON.stringify(bounts),
		success : function(stores) {
			for ( var i in stores) {
				markStorePin(stores[i]);
			}
		}
	});
}

function markStorePin(store) {
	var name = store.storeName;
	var code = store.code;
	var lat = store.latitude;
	var lng = store.longitude;

	var storePin = new naver.maps.Marker(
			{
				position : new naver.maps.LatLng(lat, lng),
				map : map,
				icon : {
					content : [
							'<div style="text-align:center; background-color:#4d55b2; padding:2px;">',
							'<div style="color:white;"> ',
							'<p>' + name + '</p>',
							'<span>' + code + '</span>',
							'</div>',
							'</div>' ].join(''),
					size : new naver.maps.Size(38, 58),
					anchor : new naver.maps.Point(19, 58),
				},
			});
	
	storeMarkers.push(storePin);
}
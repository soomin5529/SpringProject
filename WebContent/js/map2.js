/* 최초 지도 생성 */
var mapOptions = {
	center : new naver.maps.LatLng(37.5642135, 127.0016985),
	zoom : 12,
	zoomControl : true,
	zoomControlOptions : {
		style : naver.maps.ZoomControlStyle.SMALL,
		position : naver.maps.Position.TOP_RIGHT
	}
};

var map = new naver.maps.Map('map', mapOptions);

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
	
	deleteStoreMarkers();

	if (map.getZoom() >= 18) {
		var bound = mapBound();
		var bounts = {
			'lng2' : bound.getNE().x,
			'lng1' : bound.getSW().x,
			'lat2' : bound.getNE().y,
			'lat1' : bound.getSW().y
		}
		findStoreInMapBound(bounts);
	} else {
		deleteStoreMarkers();
	}
});

function deleteStoreMarkers(){
	// 모든 마커 지우기
	for(var i in storeMarkers){
		storeMarkers[i].setMap(null);
	}
	storeMarkers = [];
}

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
// 행정구역 옵션에서 지역을 택하면, 해당 좌표로 가게된다.
function choiceArea(area){
	console.log(area.latitude + "--->아니왜안나와!!")
	var areaCode = area.code,
	lat = area.latitude,
	lng = area.longitude;
	var selected_city = new naver.maps.LatLng(lat, lng); 
	map.setCenter(selected_city);
	if(areaCode.length <= 5){
		map.setOptions({
			zoom : 14
		})
	}else{
		map.setOptions({
			zoom : 16
		})
	}
}
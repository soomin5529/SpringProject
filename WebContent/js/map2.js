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

/* 동 선택시 폴리곤으로 영역을 그려준다. */
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

var storeMarkers = [];
var sigunguMarkers = [];
var dongMarkers = [];

naver.maps.Event.addListener(map, 'mousewheel dragend', function(e) {
	deleteSigunguMarkers();
	deleteDongMarkers();
	deleteStoreMarkers();

	/* 현재 보여지는 화면에서 지도의 사이즈를 측정한다. */
	var bound = mapBound();
	var bounds = {
		'lng2' : bound.getNE().x,
		'lng1' : bound.getSW().x,
		'lat2' : bound.getNE().y,
		'lat1' : bound.getSW().y
	}

	/* 시군구가 보여지는 줌 레벨 */
	if (map.getZoom() >= 11 && map.getZoom() <= 14) {
		findDistrictInMapBound(bounds, 'sigungu');
		// pinSigungu(sigungu);
	}

	/* 동이 보여지는 줌 레벨 */
	if (map.getZoom() > 14 && map.getZoom() <= 16) {
		// deleteSigunguMarkers();
		findDistrictInMapBound(bounds, 'dong');
	}

	/* 상권영역이 보여지는 줌 레벨 */
	if (map.getZoom() > 16 && map.getZoom() <= 18) {
		deleteSigunguMarkers();
	}

	/* 상점들이 보여지는 줌 레벨 */
	if (map.getZoom() > 18) {
		deleteStoreMarkers();
		findStoreInMapBound(bounds, 'currentPageStore');
	}
});
// 현재 보여지는 지도의 좌표를 나타내 준다.
function mapBound() {
	var bounds = map.getBounds();
	var sw = bounds.getSW(), ne = bounds.getNE(), offsetSW = map
			.getProjection().fromCoordToOffset(sw), offsetNE = map
			.getProjection().fromCoordToOffset(ne);
	return bounds;
}
// 화면에 보여지는 지도안에 들어올 상점들을 가져와서 pin 생성
function findStoreInMapBound(bounds) {
	$.ajax({
		type : "post",
		url : "/SpringTeamProject/request/currentPageStore",
		contentType : "application/json; charset=UTF-8",
		Accept : "application/json+custom",
		dataType : 'json',
		data : JSON.stringify(bounds),
		success : function(stores) {
			for ( var i in stores) {
				markStorePin(stores[i]);
			}
		}
	});
}
function findDistrictInMapBound(bounds, districtType) {
	$.ajax({
		type : "post",
		url : "/SpringTeamProject/request/currentPageDistrict/" + districtType,
		contentType : "application/json; charset=UTF-8",
		Accept : "application/json+custom",
		dataType : 'json',
		data : JSON.stringify(bounds),
		success : function(districts) {
			console.log(districts);
			for ( var i in districts) {
				pinDistrict(districts[i]);
			}
		}
	});
}

function pinDistrict(district) {
	var name = district.name;
	var code = district.code;
	var lat = district.latitude;
	var lng = district.longitude;
	console.log(name + ", " + code + ", " + lat + ", " + lng);
	var districtMarker = new naver.maps.Marker(
			{
				position : new naver.maps.LatLng(lat, lng),
				map : map,
				icon : {
					content : [
							'<div style="text-align:center; background-color:#4d55b2; padding:2px;">',
							'<div style="color:white;"> ',
							'<p>' + name + '</p>', '<span>' + code + '</span>',
							'</div>', '</div>' ].join(''),
					size : new naver.maps.Size(38, 58),
					anchor : new naver.maps.Point(19, 58),
				},
			});
	sigunguMarkers.push(districtMarker);
}
// 
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
							'<p>' + name + '</p>', '<span>' + code + '</span>',
							'</div>', '</div>' ].join(''),
					size : new naver.maps.Size(38, 58),
					anchor : new naver.maps.Point(19, 58),
				},
			});

	storeMarkers.push(storePin);
}

/* 마커 지우기 */
// 상점 마커 지우기
function deleteStoreMarkers() {
	for ( var i in storeMarkers) {
		storeMarkers[i].setMap(null);
	}
	storeMarkers = [];
}
// 행정구역(시군구) 마커 지우기
function deleteSigunguMarkers() {
	for ( var i in sigunguMarkers) {
		sigunguMarkers[i].setMap(null);
	}
	sigunguMarkers = [];
}
// 행정구역(읍면동) 마커 지우기
function deleteDongMarkers() {
	for ( var i in dongMarkers) {
		dongMarkers[i].setMap(null);
	}
	dongMarkers = [];
}

// 행정구역 옵션에서 지역을 택하면, 해당 좌표로 가게된다.
function choiceArea(area) {
	var areaCode = area.code, lat = area.latitude, lng = area.longitude;
	var selected_city = new naver.maps.LatLng(lat, lng);
	map.setCenter(selected_city);
	if (areaCode.length <= 5) {
		map.setOptions({
			zoom : 14
		})
	} else {
		map.setOptions({
			zoom : 16
		})
	}
}
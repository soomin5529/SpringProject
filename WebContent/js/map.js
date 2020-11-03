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
	strokeColor : '#ff2d2d',
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

/* 지도에 찍히는 행정구역(시군구, 읍면동), 상점들의 핀 배열이다 */
var districtMarkers = [];
var storeMarkers = [];

/* 마우스 휠과 드래그 이벤트를 감지하여 줌에 따라 핀을 표시한다 */
naver.maps.Event.addListener(map, 'mousewheel dragend', function(e) {

	/* 그려진 마커를 삭제한다. */
	deleteDistrictMarkers();
	deleteStoreMarkers();

	/* 현재 보여지는 화면에서 지도의 사이즈를 측정한다. */
	var bound = mapBound();

	/* DashBoard가 있으면 동 구역 안에서만, 없으면 전체 구역에서만 작동한다. */
	if (document.getElementById('dash-board').hasChildNodes()) {
		findStoreInDongBound(bound, sessionStorage.getItem('code'), sessionStorage.getItem('categry'), sessionStorage.getItem('categryCode'))
	} else {
		/* 시군구가 보여지는 줌 레벨 */
		if (map.getZoom() >= 11 && map.getZoom() <= 14) {
			findDistrictInMapBound('sigungu');
		}

		/* 동이 보여지는 줌 레벨 */
		if (map.getZoom() > 14 && map.getZoom() <= 16) {
			findDistrictInMapBound('dong');
		}

		/* 상권영역이 보여지는 줌 레벨 */
		if (map.getZoom() > 16 && map.getZoom() <= 18) {
			deleteDistrictMarkers();
		}

		/* 상점들이 보여지는 줌 레벨 */
		if (map.getZoom() > 18) {
			deleteStoreMarkers();
			findStoreInMapBound(bound, 'currentPageStore');
		}
	}
});
/* 현재 보여지는 지도의 좌표를 나타내 준다 */
function mapBound() {
	var bound = map.getBounds();
	var bounds = {
		'lng2' : bound.getNE().x,
		'lng1' : bound.getSW().x,
		'lat2' : bound.getNE().y,
		'lat1' : bound.getSW().y
	}
	console.log(bounds);
	return bounds;
}

/* 화면 안에 행정구역(시군구, 읍면동)을 출력하고 핀으로 표시한다 */
function findDistrictInMapBound(districtType) {
	var bounds = mapBound();
	$.ajax({
		type : "post",
		url : "/SpringTeamProject/request/currentPageDistrict/" + districtType,
		contentType : "application/json; charset=UTF-8",
		Accept : "application/json+custom",
		dataType : 'json',
		data : JSON.stringify(bounds),
		success : function(districts) {
			console.log("지도 내 행정구역 수");
			console.log(districts);
			for ( var i in districts) {
				pinDistrict(districts[i]);
			}
		}
	});
}
/* 행정구역(시군구, 읍면동) 핀을 그리는 함수 */
function pinDistrict(district) {
	var name = district.name;
	var code = district.code;
	var lat = district.latitude;
	var lng = district.longitude;
	var storeNum = district.storeNum;
	var districtMarker = new naver.maps.Marker(
			{
				position : new naver.maps.LatLng(lat, lng),
				map : map,
				icon : {
					content : [
							'<button onclick="findAreaToJson(' + code + '); clickPinDistrict(' + code + ')">',
							'<div class="district-marker">',
							'<div style="color:white;"> ',
							'<p>' + name + '</p>',
							'<span>' + storeNum + '</span>', '</div>',
							'</div>', '</button>' ].join(''),
					size : new naver.maps.Size(38, 58),
					anchor : new naver.maps.Point(19, 58),
				},
			});
	districtMarkers.push(districtMarker);
}

/* 화면에 보여지는 지도안에 들어올 상점들을 가져와서 핀 생성 */
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
				pinStore(stores[i]);
			}
		}
	});
}
/* 보고싶은 DONG에 들어올 상점들을 가져와서 핀 생성 */
function findStoreInDongBound(bounds, dongCode, category, categoryCode) {
	// pin을 그리기전에 지워준다.
	deleteStoreMarkers();
	
	var data = {
			'lat1' : bounds.lat1,
			'lat2' : bounds.lat2,
			'lng1' : bounds.lng1,
			'lng2' : bounds.lng2,
			'dongCode' : dongCode,
			'category' : category == 'main' ? 'MAINCATEGORY_CODE' : category == 'middle' ? 'MIDDLECATEGORY_CODE' : 'SMALLCATEGORY_CODE',
			'categoryCode' : categoryCode
	}
	$.ajax({
		type : "post",
		url : "/SpringTeamProject/dashBoard/currentDongStore",
		contentType : "application/json; charset=UTF-8",
		Accept : "application/json+custom",
		dataType : 'json',
		data : JSON.stringify(data),
		success : function(stores) {
			for ( var i in stores) {
				pinStore(stores[i]);
			}
		}
	});
}
/* 상점 핀 그리는 함수 */
function pinStore(store) {
	var name = store.storeName;
	var category = store.smallCategoryCode;
	var code = store.code;
	var lat = store.latitude;
	var lng = store.longitude;

	var storePin = new naver.maps.Marker(
			{
				position : new naver.maps.LatLng(lat, lng),
				map : map,
				icon : {
					content : [
							'<div class="store-marker">',
							'<div style="color:white;"> ',
							'<p>' + name + '</p>', '<span>' + category + '</span>',
							'</div>', '</div>' ].join(''),
					size : new naver.maps.Size(38, 58),
					anchor : new naver.maps.Point(19, 58),
				},
			});
	
	storeMarkers.push(storePin);
}

/* 마커 지우기 */
// 행정구역(시군구, 읍면동) 핀 지우기
function deleteDistrictMarkers() {
	for ( var i in districtMarkers) {
		districtMarkers[i].setMap(null);
	}
	districtMarkers = [];
}
// 상점 핀 지우기
function deleteStoreMarkers() {
	for ( var i in storeMarkers) {
		storeMarkers[i].setMap(null);
	}
	storeMarkers = [];
}

/* 행정구역(시구, 시군구, 읍면동)을 택하면 해당 좌표로 이동 */
function choiceArea(area) {
	var areaCode = area.code, lat = area.latitude, lng = area.longitude;
	var selected_city = new naver.maps.LatLng(lat, lng);
	map.setCenter(selected_city);
	if (areaCode.length <= 5) {
		map.setOptions({
			zoom : 15
		})
	} else {
		map.setOptions({
			zoom : 17
		})
	}
}
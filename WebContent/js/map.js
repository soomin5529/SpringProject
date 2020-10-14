window.onload = function() {

	/**** map setting *****/
	/* basic option */
	var position = new naver.maps.LatLng(37.431467, 127.099761);
	var mapOptions = {
		center : position,
		zoom : 17,
		zoomControl : true,
		zoomControlOptions : {
			style : naver.maps.ZoomControlStyle.SMALL,
			position : naver.maps.Position.TOP_RIGHT
		}
	};
	var map = new naver.maps.Map('map', mapOptions);

	/* custom marker */
	var markerOptions = {
		position : position.destinationPoint(90, 15),
		map : map,
		icon : {
			url : '/SpringTeamProject/images/ic_marker.png',
			size : new naver.maps.Size(30, 40),
			origin : new naver.maps.Point(0, 0),
			anchor : new naver.maps.Point(15, 20)
		}
	};
	var marker = new naver.maps.Marker(markerOptions);

	/* polygon marker */
	var polygon = new naver.maps.Polygon({
		paths : [ new naver.maps.LatLng(37.37544345085402, 127.11224555969238),
				new naver.maps.LatLng(37.37230584065902, 127.10791110992432),
				new naver.maps.LatLng(37.35975408751081, 127.10795402526855),
				new naver.maps.LatLng(37.359924641705476, 127.11576461791992),
				new naver.maps.LatLng(37.35931064479073, 127.12211608886719),
				new naver.maps.LatLng(37.36043630196386, 127.12293148040771),
				new naver.maps.LatLng(37.36354029942161, 127.12310314178465),
				new naver.maps.LatLng(37.365211629488016, 127.12456226348876),
				new naver.maps.LatLng(37.37544345085402, 127.11224555969238) ],
		strokeColor : '#001e91',
		strokeOpacity : 0.8,
		strokeWeight : 2,
		fillColor : '#001e91',
		fillOpacity : 0.35,
		zIndex : 1,
		clickable : true,
		map : map
	});

	/* rectangle marker*/
	var rectangle = new naver.maps.Rectangle({
		strokeColor : '#ff00ff',
		strokeOpacity : 0.8,
		strokeWeight : 2,
		fillColor : '#ff00ff',
		fillOpacity : 0.35,
		bounds : new naver.maps.LatLngBounds(new naver.maps.LatLng(37.5924988,
				127.1291527), new naver.maps.LatLng(37.5315893, 127.2149296)),
		clickable : true,
		map : map
	});

	/* basic marker onclick*/
	naver.maps.Event.addListener(marker, 'click', function() {
		var delta = 0, zoom = map.getZoom();

		if (zoom < 12) {
			delta = 12 - zoom;
		} else {
			delta = 5 - zoom;
		}

		map.zoomBy(delta, marker.getPosition(), true);
		document.getElementById("")
	});

	/* polygon marker mouseover*/
	naver.maps.Event.addListener(polygon, "mouseover", function(e) {
		map.setCursor("pointer");

		polygon.setOptions({
			fillOpacity : 0.8
		});
	});

	naver.maps.Event.addListener(polygon, "mouseout", function(e) {
		map.setCursor("auto");

		polygon.setOptions({
			fillOpacity : 0.35
		});
	});
	
	/* polygon marker onclick*/
	naver.maps.Event.addListener(polygon, 'click', function() {
		document.getElementById("dashboard").style.display = "block";
	});
}
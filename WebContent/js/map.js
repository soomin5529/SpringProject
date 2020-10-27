/** ** map setting **** */
/* basic option */
var position = new naver.maps.LatLng(37.5642135, 127.0016985);
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

/* marker */

var map = new naver.maps.Map(document.getElementById('map'), {
	zoom : 17,
	center : new naver.maps.LatLng(37.5642135, 127.0016985)

});

var latlngs = [ new naver.maps.LatLng(37.6658609, 127.0317674),
		new naver.maps.LatLng(37.6176125, 126.9227004),
		new naver.maps.LatLng(37.5838012, 127.0507003),
		new naver.maps.LatLng(37.4965037, 126.9443073),
		new naver.maps.LatLng(37.4600969, 126.9001546),
		new naver.maps.LatLng(37.4954856, 126.858121),
		new naver.maps.LatLng(37.5990998, 126.9861493),
		new naver.maps.LatLng(37.6469954, 127.0147158),
		new naver.maps.LatLng(37.5953795, 127.0939669),
		new naver.maps.LatLng(37.4959854, 127.0664091),
		new naver.maps.LatLng(37.5657617, 126.8226561),
		new naver.maps.LatLng(37.5579452, 126.9941904),
		new naver.maps.LatLng(37.5492077, 127.1464824),
		new naver.maps.LatLng(37.5481445, 127.0857528),
		new naver.maps.LatLng(37.5622906, 126.9087803),
		new naver.maps.LatLng(37.4769528, 127.0378103),
		new naver.maps.LatLng(37.606991, 127.0232185),
		new naver.maps.LatLng(37.655264, 127.0771201),
		new naver.maps.LatLng(37.5048534, 127.1144822),
		new naver.maps.LatLng(37.5820369, 126.9356665),
		new naver.maps.LatLng(37.5270616, 126.8561534),
		new naver.maps.LatLng(37.520641, 126.9139242),
		new naver.maps.LatLng(37.4653993, 126.9438071),
		new naver.maps.LatLng(37.5506753, 127.0409622),
		new naver.maps.LatLng(37.5311008, 126.9810742), ];

var markerList = [];

for (var i = 0, ii = latlngs.length; i < ii; i++) {
	var icon = {
		url : '/SpringTeamProject/images/ic_marker.png',
		size : new naver.maps.Size(30, 40),
		origin : new naver.maps.Point(0, 0),
		anchor : new naver.maps.Point(15, 20)
	}, marker = new naver.maps.Marker({
		position : latlngs[i],
		map : map,
		icon : icon
	});

	markerList.push(marker);
	map.setZoom(13);

}

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

// 압구정동
var polygon1 = new naver.maps.Polygon({
	paths : [ new naver.maps.LatLng(37.528926, 127.033433),
			new naver.maps.LatLng(37.522610, 127.019646),
			new naver.maps.LatLng(37.520556, 127.018683),
			new naver.maps.LatLng(37.516575, 127.019514),
			new naver.maps.LatLng(37.519835, 127.027908),
			new naver.maps.LatLng(37.523740, 127.028218) ],
	strokeColor : '#001e91',
	strokeOpacity : 0.8,
	strokeWeight : 2,
	fillColor : '#001e91',
	fillOpacity : 0.35,
	zIndex : 1,
	clickable : true,
	map : map
});
// 논현
var polygon2 = new naver.maps.Polygon({
	paths : [ new naver.maps.LatLng(37.519069, 127.017798),
			new naver.maps.LatLng(37.515466, 127.015910),
			new naver.maps.LatLng(37.509901, 127.016274),
			new naver.maps.LatLng(37.509818, 127.018086),
			new naver.maps.LatLng(37.511233, 127.021092),
			new naver.maps.LatLng(37.513847, 127.019581) ],
	strokeColor : '#33FFCC',
	strokeOpacity : 0.8,
	strokeWeight : 2,
	fillColor : '#33FFCC',
	fillOpacity : 0.35,
	zIndex : 1,
	clickable : true,
	map : map
});
// 학동역
var polygon3 = new naver.maps.Polygon({
	paths : [ new naver.maps.LatLng(37.517813, 127.025831),
			new naver.maps.LatLng(37.516954, 127.023846),
			new naver.maps.LatLng(37.515638, 127.024351),
			new naver.maps.LatLng(37.516554, 127.025723),
			new naver.maps.LatLng(37.515786, 127.026722),
			new naver.maps.LatLng(37.516516, 127.028564),
			new naver.maps.LatLng(37.517380, 127.028223),
			new naver.maps.LatLng(37.517495, 127.026830),
			new naver.maps.LatLng(37.517776, 127.026620) ],
	strokeColor : '#FF5733',
	strokeOpacity : 0.8,
	strokeWeight : 2,
	fillColor : '#FF5733',
	fillOpacity : 0.35,
	zIndex : 1,
	clickable : true,
	map : map
});
// 언주역
var polygon4 = new naver.maps.Polygon({
	paths : [ new naver.maps.LatLng(37.512111, 127.031591),
			new naver.maps.LatLng(37.510095, 127.029104),
			new naver.maps.LatLng(37.508337, 127.027103),
			new naver.maps.LatLng(37.505248, 127.027049),
			new naver.maps.LatLng(37.502717, 127.027428),
			new naver.maps.LatLng(37.502689, 127.036103),
			new naver.maps.LatLng(37.505157, 127.038155),
			new naver.maps.LatLng(37.507467, 127.037360),
			new naver.maps.LatLng(37.508675, 127.038552),
			new naver.maps.LatLng(37.509357, 127.033654),
			new naver.maps.LatLng(37.511090, 127.033058) ],
	strokeColor : '#F3FF33',
	strokeOpacity : 0.8,
	strokeWeight : 2,
	fillColor : '#F3FF33',
	fillOpacity : 0.35,
	zIndex : 1,
	clickable : true,
	map : map
});
// 강남역
var polygon5 = new naver.maps.Polygon({
	paths : [ new naver.maps.LatLng(37.497806, 127.027697),
			new naver.maps.LatLng(37.487829, 127.032330),
			new naver.maps.LatLng(37.488406, 127.034978),
			new naver.maps.LatLng(37.490665, 127.035110),
			new naver.maps.LatLng(37.492187, 127.040868),
			new naver.maps.LatLng(37.493343, 127.040471),
			new naver.maps.LatLng(37.495849, 127.039167),
			new naver.maps.LatLng(37.500659, 127.036822) ],
	strokeColor : '#FF5733',
	strokeOpacity : 0.8,
	strokeWeight : 2,
	fillColor : '#FF5733',
	fillOpacity : 0.35,
	zIndex : 1,
	clickable : true,
	map : map
});
// 압구정로데오
var polygon6 = new naver.maps.Polygon({
	paths : [ new naver.maps.LatLng(37.527851, 127.040072),
			new naver.maps.LatLng(37.528365, 127.044204),
			new naver.maps.LatLng(37.524381, 127.048984),
			new naver.maps.LatLng(37.524381, 127.054493),
			new naver.maps.LatLng(37.519177, 127.057247),
			new naver.maps.LatLng(37.516028, 127.051657),
			new naver.maps.LatLng(37.513650, 127.042746),
			new naver.maps.LatLng(37.515578, 127.038371),
			new naver.maps.LatLng(37.515707, 127.035374),
			new naver.maps.LatLng(37.521426, 127.033753),
			new naver.maps.LatLng(37.523032, 127.031566),
			new naver.maps.LatLng(37.527979, 127.033348) ],
	strokeColor : '#33FFCC',
	strokeOpacity : 0.8,
	strokeWeight : 2,
	fillColor : '#33FFCC',
	fillOpacity : 0.35,
	zIndex : 1,
	clickable : true,
	map : map
});
// 신논현역
var polygon8 = new naver.maps.Polygon({
	paths : [ new naver.maps.LatLng(37.512398, 127.025969),
			new naver.maps.LatLng(37.509538, 127.016914),
			new naver.maps.LatLng(37.496941, 127.021291),
			new naver.maps.LatLng(37.497963, 127.021634),
			new naver.maps.LatLng(37.499495, 127.027084),
			new naver.maps.LatLng(37.504398, 127.024853),
			new naver.maps.LatLng(37.501504, 127.027557),
			new naver.maps.LatLng(37.506883, 127.025025), ],
	strokeColor : '#D633FF',
	strokeOpacity : 0.8,
	strokeWeight : 2,
	fillColor : '#D633FF',
	fillOpacity : 0.35,
	zIndex : 1,
	clickable : true,
	map : map
});
// 선릉역
var polygon7 = new naver.maps.Polygon({
	paths : [ new naver.maps.LatLng(37.503882, 127.054736),
			new naver.maps.LatLng(37.501633, 127.053521),
			new naver.maps.LatLng(37.500283, 127.054007),
			new naver.maps.LatLng(37.500733, 127.057328),
			new naver.maps.LatLng(37.500604, 127.059597),
			new naver.maps.LatLng(37.499255, 127.058543),
			new naver.maps.LatLng(37.495527, 127.059516),
			new naver.maps.LatLng(37.496041, 127.057247),
			new naver.maps.LatLng(37.494177, 127.046634),
			new naver.maps.LatLng(37.496877, 127.045176),
			new naver.maps.LatLng(37.497905, 127.048255),
			new naver.maps.LatLng(37.501376, 127.043556),
			new naver.maps.LatLng(37.504193, 127.047212) ],
	strokeColor : '#D633FF',
	strokeOpacity : 0.8,
	strokeWeight : 2,
	fillColor : '#D633FF',
	fillOpacity : 0.35,
	zIndex : 1,
	clickable : true,
	map : map
});

/* rectangle marker */
/*
 * var rectangle = new naver.maps.Rectangle({ strokeColor : '#ff00ff',
 * strokeOpacity : 0.8, strokeWeight : 2, fillColor : '#ff00ff', fillOpacity :
 * 0.35, bounds : new naver.maps.LatLngBounds(new naver.maps.LatLng(37.5924988,
 * 127.1291527), new naver.maps.LatLng(37.5315893, 127.2149296)), clickable :
 * true, map : map });
 */

/* basic marker onclick */
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

/* polygon marker mouseover */
naver.maps.Event.addListener(polygon5, "mouseover", function(e) {
	map.setCursor("pointer");

	polygon.setOptions({
		fillOpacity : 0.8
	});
});

naver.maps.Event.addListener(polygon5, "mouseout", function(e) {
	map.setCursor("auto");

	polygon.setOptions({
		fillOpacity : 0.35
	});
});

/* polygon marker onclick */
naver.maps.Event.addListener(polygon5, 'click', function() {
	var dashboard = document.getElementById("dashboard");
	var community = document.getElementById("community");

	dashboard.style.display = "block";
	if (community.style.display == "block") {
		community.style.left = "350px";
	}
});

function selectCity() {
	var city = document.getElementById("sigungu");
	var selected_city = city.options[city.selectedIndex].value;
	var cityPosition;

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
	console.log(selected_city);
	map.setCenter(selected_city);
	map.setZoom(13); // 줌 레벨 변경
	console.log(selected_city);
}

function selectStreet() {

	document.getElementById("dashboard").style.display = "block";

	var yuksamdong = new naver.maps.LatLng(37.500457, 127.038218);
	var street = document.getElementById("dong");
	var selected_street = street.options[street.selectedIndex].value;
	if (selected_street == "1168010100")
		selected_street = yuksamdong;
	console.log(selected_street);
	map.setCenter(selected_street);
	map.setZoom(15); // 줌 레벨 변경

}

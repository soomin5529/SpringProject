/* javascript onload */
window.onload = function() {
	/* header - current menu active */
	var menu01 = document.getElementById("menu01");
	var menu02 = document.getElementById("menu02");
	var menu03 = document.getElementById("menu03");
	var url = document.location.href.split("/");
	if (url[url.length - 1] == "main") {
		menu01.className += "on";
	}
	if (url[url.length - 1] == "startupKeyword") {
		menu02.className += "on";
	}
	if (url[url.length - 1] == "startupWeather") {
		menu03.className += "on";
	}
}

/* jQuery onload */
$(document).ready(function() {
	
	/* 화면이 로드되면 시군구를 보여준다 */
	var bound = mapBound();
	/* 시군구가 보여지는 줌 레벨 */
	if (map.getZoom() >= 11 && map.getZoom() <= 14) {
		findDistrictInMapBound('sigungu');
	}
	
	$("#svgMap01 g").mouseover(function(event) {
		var cls = $(this).attr('class');
		var _path = event.target;
		var city_name = _path.id;
		var _over_g = $(this).find('path, polygon');

		if (cls == 'city') {
			_path.setAttribute("fill", "#584de4");

		} else {
			$.each(_over_g, function(index, item) {
				item.setAttribute("fill", "#584de4")
			});da
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
});

/* popup open/close */
var dashboard = document.getElementById("dashboard");
var community = document.getElementById("community");
var communityReg = document.getElementById("communityReg");
var login = document.getElementById("login");
var myPage = document.getElementById("myPage");
var join = document.getElementById("join");
var push = document.getElementById("push");
var myPageModify = document.getElementById("myPageModify");
var pwdModify = document.getElementById("pwdModify");
var userDelete = document.getElementById("userDelete");
var myArea = document.getElementById("myArea");
var myCommunity = document.getElementById("myCommunity");

/* dashboard */
function openPopDashboard() {
	dashboard.style.display = "block";
	if (community.style.display == "block") {
		community.style.left = "350px";
	}
	drawChart();
}


function closePopDashboard() {
	dashboard.style.display = "none";
	if (community.style.display == "block") {
		community.style.left = 0;
	}
	
}

/* community */
function openPopCommunity() {
	community.style.display = "block";
	if (dashboard.style.display == "block") {
		community.style.left = "350px";
	}
}
function closePopCommunity() {
	community.style.display = "none";
}

/* communityRegist */
function openPopCommunityReg() {
	
	var code = document.getElementById("dong").value;
	sendToboardWriteForm(code);
	communityReg.style.display = "block";
}
function closePopCommunityReg() {
	communityReg.style.display = "none";
}

/* mypage */
function openPopMyPage() {
	if (login == null) {
		if (!myPage.firstChild) {
			myPage.innerHTML = '	<div class="title-box cf">'
					+ '		<div class="close-btn" onclick="closePopMyPage();">'
					+ '			<svg viewBox="0 0 40 40" class="close-icon">'
					+ '				<line x1="4.9" y1="4.9" x2="35.1" y2="35.1"/>'
					+ '				<line x1="35.1" y1="4.9" x2="4.9" y2="35.1"/>'
					+ '			</svg>'
					+ '		</div>'
					+ '		<div class="tit">마이페이지</div>'
					+ '	</div>'
					+ '	<ul>'
					+ '		<li onclick="location.href=\'/SpringTeamProject/member/myPageModify\'; closePopMypage();">내 정보 변경</li>'
					+ '		<li onclick="location.href=\'/SpringTeamProject/member/logout\'">로그아웃</li>'
					+ '	</ul>' + '	<ul>'
					+ '		<li onclick="openPopMyArea()">관심지역</li>'
					+ '		<li onclick="openPopMyCommunity()">떠들썩</li>'
					+ '	</ul>';
			myPage.style.display = "block";
			pwdModify.style.display = "block";
			userDelete.style.display = "block";
			myArea.style.display = "block";
			myCommunity.style.display = "block";
		}
	} else {
		if (!login.firstChild) {
			login.innerHTML = '<div class="deemed" onclick="closePopLogin()"></div>'
					+ '<span class="close-btn" onclick="closePopLogin()">x</span>'
					+ '<div class="pop-box" style="width:400px; height:360px;">'
					+ '	<div class="title-box">'
					+ '		<div class="tit">로그인</div>'
					+ '	</div>'
					+ '<div class="content-box">'
					+ '		<form name="loginFrm" action="/SpringTeamProject/member/login">'
					+ '			<div class="input-box">'
					+ '				<div class="label-box">아이디</div>'
					+ '				<input type="text" id="loginId" name="userid" placeholder="아이디를 입력하세요" onkeyup="enterLogin(event);"/>'
					+ '			</div>'
					+ '			<div class="input-box">'
					+ '				<div class="label-box">비밀번호</div>'
					+ '				<input type="password" id="loginPwd" name="pwd" placeholder="비밀번호를 입력하세요" onkeyup="enterLogin(event);"/>'
					+ '			</div>'
					+ '			<span id="idPresenceCheck" style="color:red;"></span>'
					+ '			<button type="button" onclick="loginCheck()" class="btn-full btn01-reverse">로그인</button>'
					+ '			<div class="join-btn">'
					+ '				<span class="gray">아직 회원이 아니신가요?</span>'
					+ '				<span class="highlight01" onclick="openPopJoin()">회원가입</span>'
					+ '			</div>' + '		</form>' + '	</div>' + '</div>';
			login.style.display = "block";
		}
	}
}
function closePopMyPage() {
	while (myPage.firstChild) {
		myPage.removeChild(myPage.firstChild);
	}
	myPage.style.display = "none";
	pwdModify.style.display = "none";
	userDelete.style.display = "none";
	myArea.style.display = "none";
	myCommunity.style.display = "none";
}

/* login */
function closePopLogin() {
	while (login.firstChild) {
		login.removeChild(login.firstChild);
	}
	login.style.display = "none";
}

/* join */
function openPopJoin() {
	if (!join.firstChild) {
		join.innerHTML = '<div class="deemed" onclick="closePopJoin()"></div>'
				+ '<span class="close-btn" onclick="closePopJoin()">x</span>'
				+ '<div class="pop-box" style="width:400px; height:716px;">'
				+ '	<div class="title-box">'
				+ '		<div class="tit">회원가입</div>'
				+ '	</div>'
				+ '	<div class="content-box">'
				+ '		<form name = "regForm">'
				+ '			<div class="input-box">'
				+ '				<div class="label-box">아이디</div>'
				+ '				<input type="text" id="userid" name="userid" placeholder="아이디를 입력하세요" onkeyup="idCheck(this.form.userid.value)"/>'
				+ '             <span id="idOverlapCheck"></span>'
				+ '			</div>'
				+ '			<div class="input-box">'
				+ '				<div class="label-box">비밀번호</div>'
				+ '				<input type="password" id="pwd" name="pwd" placeholder="비밀번호를 입력하세요"/>'
				+ '			</div>'
				+ '			<div class="input-box">'
				+ '				<div class="label-box">비밀번호 확인</div>'
				+ '				<input type="password" name="repwd" placeholder="비밀번호를 다시 입력하세요"/>'
				+ '			</div>'
				+ '			<div class="input-box">'
				+ '				<div class="label-box">이름</div>'
				+ '				<input type="text" id="name" name="name" placeholder="이름(닉네임)을 입력하세요"/>'
				+ '			</div>'
				+ '			<div class="input-box">'
				+ '				<div class="label-box">이메일</div>'
				+ '				<input type="email" id="email" name="email" placeholder="이메일을 입력하세요"/>'
				+ '			</div>'
				+ '			<div class="input-box">'
				+ '				<div class="label-box">생일</div>'
				+ '				<input type="date" id="birthdate" name="birthdate" placeholder="생일을 입력하세요"/>'
				+ '			</div>'
				+ '			<div class="input-box">'
				+ '				<div class="label-box">성별</div>'
				+ '				<input type="radio" name="gender" id="male" value="male" checked/><label for="male">남성</label>'
				+ '				<input type="radio" name="gender" id="female" value="female"/><label for="female">여성</label>'
				+ '			</div>'
				+ '			<button type="button" class="btn-full btn01-reverse" onclick="inputCheck()">회원가입</button>'
				+ '		</form>' + '	</div>' + '</div>';
		join.style.display = "block";
	}
}
function closePopJoin() {
	while (join.firstChild) {
		join.removeChild(join.firstChild);
	};
	join.style.display = "none";
}

/* push */
function openPopPush() {
	if (!push.firstChild) {
		push.innerHTML = '<div class="title-box cf">'
				+ '	<div class="close-btn" onclick="closePopPush();">'
				+ '		<svg viewBox="0 0 40 40" class="close-icon">'
				+ '			<line x1="4.9" y1="4.9" x2="35.1" y2="35.1"/>'
				+ '			<line x1="35.1" y1="4.9" x2="4.9" y2="35.1"/>'
				+ '		</svg>'
				+ '	</div>'
				+ '	<div class="tit">알림</div>'
				+ '</div>'
				+ '<ul>'
				+ '	<li>'
				+ '		<div class="msg"><span class="highlight01">강남구 역삼동</span>에 새글이 등록되었습니다.</div>'
				+ '		<div class="regdate">10분 전</div>'
				+ '	</li>'
				+ '	<li class="readed">'
				+ '		<div class="msg"><span class="highlight01">강남구 역삼동</span>에 새글이 등록되었습니다.</div>'
				+ '		<div class="regdate">10분 전</div>'
				+ '	</li>'
				+ '	<li>'
				+ '		<div class="msg"><span class="highlight01">강남구 역삼동</span>에 새글이 등록되었습니다.</div>'
				+ '		<div class="regdate">10분 전</div>' + '	</li>' + '</ul>'
		push.style.display = "block";
	}
}
function closePopPush() {
	while (push.firstChild) {
		push.removeChild(push.firstChild);
	}
	;
	push.style.display = "none";
}

/* myPageModify */
function openPopMyPageModify() {
	if (!myPageModify.firstChild) {
		myPageModify.innerHTML = '<div class="deemed" onclick="closePopMyPageModify()"></div>'
				+ '<span class="close-btn" onclick="closePopMyPageModify()">x</span>'
				+ '<div class="pop-box" style="width:400px; height:645px;">'
				+ '	<div class="title-box">'
				+ '		<div class="tit">내 정보 수정</div>'
				+ '	</div>'
				+ '	<div class="content-box">'
				+ '		<form name = "regForm">'
				+ '			<div class="input-box">'
				+ '				<div class="label-box">아이디</div>'
				+ '				<input type="text" id="userid" name="userid" placeholder="아이디를 입력하세요" value="김윤민" readonly/>'
				+ '             <span id="idOverlapCheck"></span>'
				+ '			</div>'
				+ '			<div class="input-box">'
				+ '				<div class="label-box">비밀번호</div>'
				+ '				<input type="password" id="pwd" name="pwd" placeholder="비밀번호를 입력하세요"/>'
				+ '			</div>'
				+ '			<div class="input-box">'
				+ '				<div class="label-box">이름</div>'
				+ '				<input type="text" id="name" name="name" placeholder="이름(닉네임)을 입력하세요" value="값넣어쥬세요"/>'
				+ '			</div>'
				+ '			<div class="input-box">'
				+ '				<div class="label-box">이메일</div>'
				+ '				<input type="email" id="email" name="email" placeholder="이메일을 입력하세요" value="값넣어쥬세요"/>'
				+ '			</div>'
				+ '			<div class="input-box">'
				+ '				<div class="label-box">생일</div>'
				+ '				<input type="date" id="birthdate" name="birthdate" placeholder="생일을 입력하세요" value="값넣어쥬세요"/>'
				+ '			</div>'
				+ '			<div class="input-box">'
				+ '				<div class="label-box">성별</div>'
				+ '				<input type="radio" name="gender" id="male" value="male" checked/><label for="male">남성</label>'
				+ '				<input type="radio" name="gender" id="female" value="female"/><label for="female">여성</label>'
				+ '			</div>'
				+ '			<button type="button" class="btn-full btn01-reverse" onclick="inputCheck()">수정하기</button>'
				+ '		</form>' + '	</div>' + '</div>';
		myPageModify.style.display = "block";
	}
}
function closePopMyPageModify() {
	while (myPageModify.firstChild) {
		myPageModify.removeChild(myPageModify.firstChild);
	};
	myPageModify.style.display = "none";
}

/* pwdModify */
function openPopPwd() {
	if (!pwdModify.firstChild) {
		pwdModify.innerHTML = '	<div class="title-box cf">'
				+ '		<div class="close-btn" onclick="closePopPwd();">'
				+ '			<svg viewBox="0 0 40 40" class="close-icon">'
				+ '				<line x1="4.9" y1="4.9" x2="35.1" y2="35.1"/>'
				+ '				<line x1="35.1" y1="4.9" x2="4.9" y2="35.1"/>'
				+ '			</svg>'
				+ '		</div>'
				+ '		<div class="tit">비밀번호 변경</div>'
				+ '	</div>'
				+ '	<form action="">'
				+ '	<input type="password" name="oldpwd" id="oldpwd" placeholder="기존 비밀번호를 입력하세요"/>'
				+ '	<input type="password" name="pwd" id="pwd" placeholder="새 비밀번호를 입력하세요"/>'
				+ '	<input type="password" name="repwd" id="repwd" placeholder="새 비밀번호를 다시 입력하세요"/>'
				+ '	<button type="submit" class="btn-full btn01">비밀번호 변경</button>'
				+ '	</form>';
	}
	pwdModify.style.right = 0;
}
function closePopPwd() {
	pwdModify.style.right = "-350px";
}

/* userDelete */
function openPopUserDelete() {
	if (!userDelete.firstChild) {
		userDelete.innerHTML = '	<div class="title-box cf">'
				+ '		<div class="close-btn" onclick="closePopUserDelete();">'
				+ '			<svg viewBox="0 0 40 40" class="close-icon">'
				+ '				<line x1="4.9" y1="4.9" x2="35.1" y2="35.1"/>'
				+ '				<line x1="35.1" y1="4.9" x2="4.9" y2="35.1"/>'
				+ '			</svg>'
				+ '		</div>'
				+ '		<div class="tit">회원탈퇴</div>'
				+ '	</div>'
				+ '	<form action="">'
				+ '	<input type="password" name="pwd" id="pwd" placeholder="비밀번호를 입력하세요"/>'
				+ '	<button type="submit" class="btn-full btn01">탈퇴하기</button>'
				+ '<span>정말 가실거에요오?( Ĭ ^ Ĭ )</span>' + '	</form>';
	}
	userDelete.style.right = 0;
}
function closePopUserDelete() {
	userDelete.style.right = "-350px";
}

/* myArea */
function openPopMyArea() {
	var userid = document.getElementById("push").value;
	alert(userid);
	if (!myArea.firstChild) {
	$.ajax({
		type : "post",
		url : "<%=request.getContextPath()%>/dashboard/myArea/" + userid,
		contentType : "application/json; charset=UTF-8",
		success : function(data) {
			   $("#myArea").html(data);
		}
	});
		
	}
	myArea.style.right = 0;
}
function closePopMyArea() {
	myArea.style.right = "-350px";
}
/* end of popup open/close */

/* login enter submit */
function enterLogin(event) {
	if (window.event.keyCode == 13) {
		loginCheck()
	}
}

/* SVG toggle button */


/* bookmark icon */
function bookmark() {

	var bookmark = document.getElementById("bookmark");
	if (bookmark.classList.contains('on')) {
		bookmark.className = bookmark.className.replace("on", "off");
	} else if (bookmark.classList.contains('off')) {
		bookmark.className = bookmark.className.replace("off", "on");
	}
}

/* like icon */
function postLike(bdid) {
	var postLike = document.getElementById(bdid);
	if (postLike.classList.contains('on')) {
		postLike.className = postLike.className.replace("on", "off");
		postLike.innerHTML = '<svg viewBox="0 0 40 40" class="like-icon">'
				+ '<path d="M20 4.5h.8c.9.1 1.6.5 2.1 1.2.5.6 	.8 1.4.6 2.2v8.6H34c.7 0 1.4.3 1.8.9.5.6.8 1.5.7 2.4v7.8c0 2.3-.5 4.9-2.5 6.7-1.5 1.3-4 2.2-7.6 2.2H3.5v-15h7.6l5.2-8 1.3-8.6.1-.3zm-9.5 17v15"></path>'
				+ '</svg>' + '<span class="like-txt">좋아요</span>';
				
	} else if (postLike.classList.contains('off')) {
		postLike.className = postLike.className.replace("off", "on");
		postLike.innerHTML = '<svg viewBox="0 0 40 40" class="like-icon">'
				+ '<path d="M25 15V8c.4-2.4-1.5-4.7-4-5h-2a2 2 0 00-2 1.5v.2l-1.3 8.2-3 7.1H2v18h24.4C36 38 38 32.4 38 27.6V20c.2-2.6-1.6-5-4-4.9-.5-.4-.8-.4-1 0h-8zM12 38H9V20h3v18z"></path>'
				+ '</svg>' + '<span class="like-txt">좋아요</span>';
	}

}
// 행정구역(시군군, 읍면동) 선택 시, 행정구역 면적을 json에서 찾아와 표시한다.
function findAreaToJson(districtCode) {
	var code = districtCode+"";
	var url;
	if (code.length==5) {
		url = "/SpringTeamProject/request/findSigunguToJson";
	} else {
		url = "/SpringTeamProject/request/findDongToJson";
	}
	$.ajax({
		type : "post",
		url : url,
		contentType : "application/x-www-form-urlencoded; charset=UTF-8",
		data : {
			'code' : code
		},
		success : function(paths) {
			var centerPath = paths.split(':')[0];
			var area = { code : code, latitude : centerPath.split(',')[0], longitude : centerPath.split(',')[1]}
			choiceArea(area);
			var borderPaths = paths.split(':')[1];
			drawPolygonDong(borderPaths);
		}
	});
}

// main화면에서 행정구역을 선택하면 동적으로 하위의 행정구역을 받아옵니다.
// 전역변수 Array
var sigunguArray = new Array();
var dongArray = new Array();
function choiceAdministrativeDistrict(select) {

	deleteDistrictMarkers();
	// 시도 선택 -> 시군구리스트 출력 및 담기
	if (select.getAttribute('id') == 'sido') {
		sigunguArray = [];
		returnAreaArray('sido', select.value);
		if (select.value == '11') {
			map.setOptions({
				zoom : 12,
				center : new naver.maps.LatLng(37.5642135, 127.0016985)
			})
			findDistrictInMapBound('sigungu');
		}
	}
	// 시군구 선택 -> 동리스트 출력 및 담기
	if (select.getAttribute('id') == 'sigungu') {
		closePopDashboard();
		console.log("-----------------시군구선택")
		dongArray = [];
		returnAreaArray('sigungu', select.value);
		console.log(dongArray);
		for ( var i in sigunguArray) {
			if (sigunguArray[i].code == select.value) {
				console.log(sigunguArray[i]);
				choiceArea(sigunguArray[i]);
			}
		}
		//selectOption(sigunguArray, select);
	}
	if (select.getAttribute('id') == 'dong') {
		console.log("-----------------동선택")
		for ( var i in dongArray) {
			if (dongArray[i].code == select.value) {
				console.log(dongArray[i]);
				choiceArea(dongArray[i]);
			}
		}
		//selectOption(dongArray, select);
	}
}
function returnAreaArray(districtType, districtCode){
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
				if(districtType.includes('sigungu')){
					dongArray.push(areaList[i]);
				}
				options += '<option value=' + areaList[i].code + '>' + areaList[i].name + '</option>\n'
			}
			var areaId = districtType == 'sido' ? 'sigungu' : 'dong';
			$("#" + areaId).empty().append(options);
		}
	});
}
// 행정구역(시군구, 동)을 선택하면 areaArray[]에서 선택된 구역을 찾아 choiceArea()에 보내어 실행시킨다.
function selectOption(array, option) {
	console.log("selectOption() 실행-->" + array[i] +", " +option)
	for ( var i in array) {
		if (array[i].code == option.value) {
			console.log(array[i]);
			choiceArea(array[i]);
		}
	}
}
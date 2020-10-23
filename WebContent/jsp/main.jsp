<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script type="text/javascript" src="https://openapi.map.naver.com/openapi/v3/maps.js?ncpClientId=1daef4c0ea"></script>
<script>
function sendToControllerSelectValue(select){
	var area = select.getAttribute('id') == 'sido' ? 'sido' : 'sigungu';
	
	$.ajax({
        type: "post", 
        url: "<%=request.getContextPath()%>/view/mainOption",
			contentType : "application/x-www-form-urlencoded; charset=UTF-8",
			data : {
				'area' : area,
				'code' : select.value
			},
			success : function(textStatus) {
				if(area == 'sido'){
					$("#sigungu").empty().append(textStatus);	
				}else{
					$("#dong").empty().append(textStatus);
				}
			}
		});
	}
</script>
<div class="page main" id="page">
	<!-- map -->
	<div id="map" class="map"></div>
	
	<!-- search -->
	<form action="" method="post" class="map-popup search-group">
		<!-- 검색항목 묶음 - 구역선택 -->
				<div class="search-box">
			<div class="input-box">
				<!-- <div class="label-box">구역 선택</div> -->
				<select id="sido" name="state"
					onchange="javascript:sendToControllerSelectValue(this);">
					<option value="no" disabled selected>선택</option>
					<c:forEach var="sido" items="${sido }">
						<option value="${sido.code}">${sido.name}</option>
					</c:forEach>
				</select> <select id="sigungu" name="city"
					onchange="javascript:sendToControllerSelectValue(this); selectCity();">
					<option value="no" disabled selected>선택</option>
				</select>
				<select id="dong" name="street" onchange="selectStreet()">
					<option value="no" disabled selected>선택</option>
				</select>
			</div>
		</div>
		<!-- 검색버튼 -->
		<!-- <button type="submit" class="btn-full btn01-reverse">검색</button> -->
	</form>
	<!-- end of search -->
	
	<!-- dashboard -->
	<div class="map-popup dashboard" id="dashboard" style="display:none">
		<div class="title-box cf">
			<div class="back-btn" onclick="closePopDashboard()">
				<svg viewBox="0 0 40 40" class="back-icon">
					<path d="M18 35L3 20 18 5"/>
				</svg>
			</div>
			<div class="tit">강남구 역삼동</div>
			<!-- bookmark on/off -->
			<div class="bookmark off" id="bookmark" onclick="bookmark()">
				<svg viewBox="0 0 55 55" class="bookmark-icon" >
					<path d="M9.9,30.8l-9.1-8.9c-1.6-1.6-0.7-4.3,1.5-4.6l12.6-1.8c0.9-0.1,1.6-0.7,2-1.5l5.7-11.4c1-2,3.8-2,4.8,0L33.1,14c0.4,0.8,1.1,1.3,2,1.5l12.6,1.8c2.2,0.3,3.1,3,1.5,4.6L40,30.8c-0.6,0.6-0.9,1.5-0.8,2.4l2.1,12.6c0.4,2.2-1.9,3.9-3.9,2.8l-11.3-5.9c-0.8-0.4-1.7-0.4-2.5,0l-11.3,5.9c-2,1-4.3-0.6-3.9-2.8l2.2-12.6C10.9,32.3,10.6,31.4,9.9,30.8z"/>
				</svg>
			</div>
		</div>
		
		<!-- 검색항목 묶음 - 업종선택 -->
		<div class="search-box">
			<div class="label-box">업종 선택</div>
			<div class="input-box half">
				<select id="categoryMain" name="categoryMain">
					<option value="대분류">대분류</option>
					<option value="음식점">음식점</option>
				</select>
				<select id="categoryMiddle" name="categoryMiddle">
					<option value="중분류">중분류</option>
					<option value="한식">한식</option>
					<option value="한식">중식</option>
					<option value="한식">일식</option>
					<option value="한식">양식</option>
				</select>
			</div>
		</div>
		
		<!-- 통계자료 묶음 -->
		<div class="chart-box">
			<img src="<%=request.getContextPath()%>/images/temp.png" alt="temp" />
			훼이크 이미지에요  차트 넣어주세용
		</div>
		<!-- 떠들썩 커뮤니티 버튼 -->
		<button type="button" class="btn-full btn01-reverse" onclick="openPopCommunity()">떠들썩</button>
	</div>
	<!-- end of dashboard -->
	
	<!-- 떠들썩 커뮤니티 -->
	<div class="map-popup community" id="community" style="display:none">
		<div class="title-box cf">
			<div class="back-btn" onclick="closePopCommunity()">
				<svg viewBox="0 0 40 40" class="back-icon">
					<path d="M18 35L3 20 18 5"/>
				</svg>
			</div>
			<div class="tit">
				<span>떠들썩</span>
				<span class="highlight01" id="postCount">10</span>
			</div>
			<div class="reg-btn" onclick="openPopCommunityReg()">
				<svg viewBox="0 0 40 40" class="reg-icon">
					<path d="M28.6 4.5c.4 0 .8.2 1 .4l5.5 5.4a1.5 1.5 0 010 2.1L13 34.5H5.5V27l22-22c.3-.3.7-.5 1.1-.5zm-6.1 6l7 7"/>
				</svg>
			</div>
		</div>
		<!-- list-box -->
		<div class="list-box">
			<!-- post-box -->
			<div class="post-box">
				<div class="content-box cf">
					<div class="name">
					강운기삼겹살 사장이다</div>
					<div class="content">
						<div class="text">
						코로나 여파가 있어도 장사는 잘되더군요,, 직장인분들이 많다보니 매출손해가 크지 않아요
						체인점 내실 분 쪽지 주십쇼.
						</div>
						<div class="photo" style="background-image:url('../images/gogi.jpg');"></div>
					</div>
					<div class="regdate">2020.10.13 14:00</div>
					<div class="like-btn on" id="likeBtn" onclick="postLike()">
						<!-- 좋아요 아이콘 -->
						<svg viewBox="0 0 40 40" class="like-icon">
							<path d="M25 15V8c.4-2.4-1.5-4.7-4-5h-2a2 2 0 00-2 1.5v.2l-1.3 8.2-3 7.1H2v18h24.4C36 38 38 32.4 38 27.6V20c.2-2.6-1.6-5-4-4.9-.5-.4-.8-.4-1 0h-8zM12 38H9V20h3v18z"></path>
						</svg>
						
						<span class="like-txt">좋아요</span>
					</div>
				</div>
				
				<div class="reply-list-box">
					<div class="reply-box">
						<div class="name">역삼 노비</div>
						<div class="content">댓글 : 거기 먹어보니까 맛은 별로던데 상권이 좋아서 매출이 괜찮나보네요 쩝;;</div>
						<div class="regdate">두달 전</div>
					</div>
				</div>
				<div class="reply-reg-box">
					<input type="text" placeholder="댓글을 입력하세요"/>
					<div class="reg-btn"><svg viewBox="0 0 40 40" class="reg-icon"><path d="M28.6 4.5c.4 0 .8.2 1 .4l5.5 5.4a1.5 1.5 0 010 2.1L13 34.5H5.5V27l22-22c.3-.3.7-.5 1.1-.5zm-6.1 6l7 7"></path></svg></div>
				</div>
			</div>
			<!-- end of post-box -->
		</div>
		<!-- end of list-box -->
	</div>
	<!-- end of 떠들썩커뮤니티 -->
	
	<!-- communityRegPopup -->
	<div class="pop-container" id="communityReg">
		<div class="deemed" onclick="closePopCommunityReg()"></div>
		<span class="close-btn" onclick="closePopCommunityReg()">x</span>
		<div class="pop-box" style="width:400px; height:570px;">
			<div class="title-box">
				<div class="tit">강남구 역삼동</div>
			</div>
			<div class="content-box">
				<!-- 이름 -->
				<input type="text" value="역삼 뽀시래기" placeholder="닉네임을 입력하세요" style="margin-bottom:10px;">
				<!-- 내용입력 -->
				<textarea name="communityContent" rows=14 placeholder="이 상권에 창업을 준비하는 사람들에게 알려주고 싶은 이야기, 꿀팁을 적어주세요! 궁금하신 것을 물어보셔도 됩니다 ʕ￫ᴥ￩ʔ"></textarea>
				<!-- 사진등록 -->
				<div class="photo-box cf">
					<div class="thumb" id="photo" style="background-image:url('../images/temp01.jpg')"></div>
					
					<div class="btn-photo" onclick="onclick=document.all.file.click()">
					<input type="file" id="file" name="file"  style="display: none;"/></div>
				</div>
				<button type="submit" class="btn-full btn01-reverse">등록하기</button>
			</div>
		</div>
	</div>
	
</div>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/map.js"></script>
<!-- end of main -->

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<script type="text/javascript" src="https://openapi.map.naver.com/openapi/v3/maps.js?ncpClientId=1daef4c0ea"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/map.js"></script>
<script type="text/javascript">
	var menu01 = document.getElementById("menu01");
	menu01.className += "on";
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
				<select id="state" name="state">
					<option value="시 전체">시 전체</option>
					<option value="서울특별시">서울특별시</option>
				</select> 
				<select id="city" onchange="selectCity()" name="city">
					<option value="전체">전체</option>
					<option value="Gangnamgu">강남구</option>
					<option value="Dobonggue">도봉구</option>
					<option value="Eunpyeonggu">은평구</option>
					<option value="Dongdaemungu">동대문구</option>
					<option value="Dongjakgu">동작구</option>
					<option value="Geumcheongu">금천구</option>
					<option value="Gurogu">구로구</option>
					<option value="Jongnogu">종로구</option>
					<option value="Gangbukgu">강북구</option>
					<option value="Jungnanggu">중랑구</option>
					<option value="Gangseogu">강서구</option>
					<option value="Junggu">중구</option>
					<option value="Gangdonggu">강동구</option>
					<option value="Gwangjingu">광진구</option>
					<option value="Mapogu">마포구</option>
					<option value="Seochogu">서초구</option>
					<option value="Seongbukgu">성북구</option>
					<option value="Nowongu">노원구</option>
					<option value="Songpagu">송파구</option>
					<option value="Seodaemungu">서대문구</option>
					<option value="Yangcheongu">양천구</option>
					<option value="Yeongdeungpogu">영등포구</option>
					<option value="Gwanakgu">관악구</option>
					<option value="Seongdonggu">성동구</option>
					<option value="Yongsangu">용산구</option>
				</select> 
				<select id= "street" onchange="selectStreet()" name="street">
					<option value="동 전체">동 전체</option>
					<option value="전체">압구정동</option>
					<option value="전체">신사동</option>
					<option value="전체">청담동</option>
					<option value="전체">논현동</option>
					<option value="전체">삼성동</option>
					<option value="yuksamdong" >역삼동</option>
					<option value="전체">도곡동</option>
					<option value="전체">개포동</option>
					<option value="전체">대치동</option>
					<option value="전체">일원동</option>
					<option value="전체">수서동</option>
					<option value="전체">자곡동</option>
					<option value="전체">세곡동</option>
					<option value="전체">율현동</option>
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
				<select name="categoryMain">
					<option value="대분류">대분류</option>
					<option value="음식점">음식점</option>
				</select>
				<select name="categoryMiddle">
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
<!-- end of main -->

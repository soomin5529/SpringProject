<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<script type="text/javascript" src="https://openapi.map.naver.com/openapi/v3/maps.js?ncpClientId=1daef4c0ea"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/map.js"></script>
<div class="page main">
	<!-- map -->
	<div id="map" class="map"></div>
	
	<!-- search -->
	<form action="" class="map-popup search-group" style="top:80px; left:20px;">
		<!-- tab menu-->
		<ul class="search-tab cf">
			<li class="on">창업 성공률</li>
			<li>어디에 창업할까?</li>
		</ul>
		
		<div class="search-body">
			<!-- tab menu - 창업 성공률 -->
			<div class="tab">
				<!-- 검색항목 묶음 - 구역선택 -->
				<div class="search-box">
					<div class="label-box">구역 선택</div>
					<div class="input-box">
						<select name="state" style="width:calc(50% - 5px); margin-right:10px; float:left;">
							<option value="전체">전체</option>
							<option value="서울특별시">서울특별시</option>
						</select>
						<select name="city" style="width:calc(50% - 5px);">
							<option value="전체">전체</option>
							<option value="강남구">강남구</option>
						</select>
					</div>
				</div>
				
				<!-- 검색항목 묶음 - 업종선택 -->
				<div class="search-box">
					<div class="label-box">업종 선택</div>
					<div class="input-box">
						<select name="state" style="width:calc(50% - 5px); margin-right:10px; float:left;">
							<option value="대분류">대분류</option>
						</select>
						<select name="city" style="width:calc(50% - 5px);">
							<option value="중분류">중분류</option>
						</select>
					</div>
				</div>
			</div>
			
			<!-- tab menu - 어디에 창업할까? -->
			<div class="tab hidden"></div>
		</div>
		
		<!-- 검색버튼 -->
		<button type="submit" class="btn-full btn01-reverse">검색</button>
	</form>
	<!-- end of search -->
	
	<!-- dashboard -->
	<div class="map-popup dashboard">
		<div class="title-box cf">
			<div class="back-btn">
				<svg viewBox="0 0 40 40" class="back-icon">
					<path d="M18 35L3 20 18 5"/>
				</svg>
			</div>
			<div class="tit">강남구 어딘가</div>
			<div class="bookmark off">
				<svg viewBox="0 0 55 55" class="bookmark-icon" >
					<path d="M9.9,30.8l-9.1-8.9c-1.6-1.6-0.7-4.3,1.5-4.6l12.6-1.8c0.9-0.1,1.6-0.7,2-1.5l5.7-11.4c1-2,3.8-2,4.8,0L33.1,14c0.4,0.8,1.1,1.3,2,1.5l12.6,1.8c2.2,0.3,3.1,3,1.5,4.6L40,30.8c-0.6,0.6-0.9,1.5-0.8,2.4l2.1,12.6c0.4,2.2-1.9,3.9-3.9,2.8l-11.3-5.9c-0.8-0.4-1.7-0.4-2.5,0l-11.3,5.9c-2,1-4.3-0.6-3.9-2.8l2.2-12.6C10.9,32.3,10.6,31.4,9.9,30.8z"/>
				</svg>
			</div>
		</div>
		<div class="chart-box">
			<img src="<%=request.getContextPath()%>/images/temp.png" alt="temp" />
			훼이크 이미지에요  차트 넣어주세용
		</div>
		<!-- 떠들썩 커뮤니티 버튼 -->
		<button type="button" class="btn-full btn01-reverse">떠들썩</button>
	</div>
	
	<!-- 떠들썩 커뮤니티 -->
	<div class="map-popup community">
		<div class="title-box cf">
			<div class="back-btn">
				<svg viewBox="0 0 40 40" class="back-icon">
					<path d="M18 35L3 20 18 5"/>
				</svg>
			</div>
			<div class="tit">
				<span>떠들썩</span>
				<span class="highlight01" id="postCount">10</span>
			</div>
			<div class="reg-btn">
				<svg viewBox="0 0 40 40" class="reg-icon">
					<path d="M28.6 4.5c.4 0 .8.2 1 .4l5.5 5.4a1.5 1.5 0 010 2.1L13 34.5H5.5V27l22-22c.3-.3.7-.5 1.1-.5zm-6.1 6l7 7"/>
				</svg>
			</div>
		</div>
		<div class="list-box">
			<div class="post-box">
				<div class="content-box cf">
					<div class="name">이름이름이름이름</div>
					<div class="content">내용 Lorem Ipsum is simply dummy text of the printing and typesetting industry. </div>
					<div class="regdate">2020.10.13 14:00</div>
					<div class="like-btn on">
						<!-- 좋아요 아이콘 off-->
						<!-- <svg viewBox="0 0 40 40" class="like-icon">
							<path d="M20 4.5h.8c.9.1 1.6.5 2.1 1.2.5.6.8 1.4.6 2.2v8.6H34c.7 0 1.4.3 1.8.9.5.6.8 1.5.7 2.4v7.8c0 2.3-.5 4.9-2.5 6.7-1.5 1.3-4 2.2-7.6 2.2H3.5v-15h7.6l5.2-8 1.3-8.6.1-.3zm-9.5 17v15"></path>
						</svg>
						 -->
						<!-- 좋아요 아이콘 on -->
						<svg viewBox="0 0 40 40" class="like-icon">
							<path d="M25 15V8c.4-2.4-1.5-4.7-4-5h-2a2 2 0 00-2 1.5v.2l-1.3 8.2-3 7.1H2v18h24.4C36 38 38 32.4 38 27.6V20c.2-2.6-1.6-5-4-4.9-.5-.4-.8-.4-1 0h-8zM12 38H9V20h3v18z"></path>
						</svg>
						
						<span class="like-txt">좋아요</span>
					</div>
				</div>
				
				<div class="reply-list-box">
					<div class="reply-box">
						<div class="name">이름이름이름이름</div>
						<div class="content">댓글 내용내용내용내용내용내용내용내용내용내용내용내용내용내용</div>
						<div class="regdate">두달전</div>
					</div>
				</div>
				<div class="reply-reg-box">
					<input type="text" placeholder="댓글을 입력하세요"/>
					<div class="reg-btn"><svg viewBox="0 0 40 40" class="reg-icon"><path d="M28.6 4.5c.4 0 .8.2 1 .4l5.5 5.4a1.5 1.5 0 010 2.1L13 34.5H5.5V27l22-22c.3-.3.7-.5 1.1-.5zm-6.1 6l7 7"></path></svg></div>
				</div>
			</div>
		</div>
	</div>
</div>


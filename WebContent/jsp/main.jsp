<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<script type="text/javascript" src="https://openapi.map.naver.com/openapi/v3/maps.js?ncpClientId=1daef4c0ea"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/map.js"></script>
<div class="page main">
	<!-- map -->
	<div id="map" class="map"></div>
	
	<!-- search -->
	<form action="" class="search-group">
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
					<div class="title-box">구역 선택</div>
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
					<div class="title-box">업종 선택</div>
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
</div>


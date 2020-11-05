<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<script src="https://cdn.jsdelivr.net/npm/chart.js@2.8.0"></script>
<div class="page startup-weather" id="page">
	<!-- page title section -->
	<div class="page-title-box">
		<div class="page-name">창업기상도</div>
		<div class="page-desc">
			어디에 창업할까?<br />
			창업기상도는 예비창업자님이 원하는 창업 조건을 선택하시면 지역별 상권을 추천해드립니다. 
		</div>
	</div>

	<div class="content-body cf">
		<!-- search -->
		<form action="<%=request.getContextPath()%>/view/startupWeather/search" method="post" class="search-group">
			<!-- 검색항목 묶음 - 구역선택 -->
			<div class="search-box">
				<div class="input-box">
					<div class="label-box">선호 지역</div>
					<div class="third">
						<!-- Select-Sido (시도) -->
						<select id="sido" name="sido"
							onchange="javascript:choiceAdministrativeDistrict2(this);">
							<option value="no" disabled selected>시도</option>
							<c:forEach var="sido" items="${sido}">
								<option value="${sido.code}">${sido.name}</option>
							</c:forEach>
						</select>

						<!-- Select-Sigungu (시군구) -->
						<select id="sigungu" name="sigungu"
							onchange="javascript:choiceAdministrativeDistrict2(this); ">
							<option value="no" disabled selected>시군구</option>
						</select>

						<!-- Select-Dong (읍면동) -->
						<select id="dong" name="dong">
							<option value="no" disabled selected>동</option>
						</select>
					</div>
				</div>
				<div class="input-box">
					<div class="label-box">선호 업종</div>
					<div class="third">
						<select id="main" name="main_category"
							onchange="javascript:sendToControllerSelectCategoryValue2(this);">
							<option value="no" disabled selected>대분류</option>
							<c:forEach var="main" items="${main }">
								<option value="${main.code}">${main.name}</option>
							</c:forEach>
						</select> <select id="middle" name="middle_category"
							onchange="javascript:sendToControllerSelectCategoryValue2(this);">
							<option value="no" disabled selected>중분류</option>
						</select> <select id="small" name="small_category">
							<option value="no" disabled selected>소분류</option>
						</select>
					</div>
				</div>
				<div class="input-box">
					<div class="label-box">
						<span>창업자금</span>
						<span class="value">만원</span>
						<span class="value" id="funds">1000</span>
					</div>
					<div class="slide-box">
						<input type="range" min="1000" max="50000" value="1" class="slider" name="funds" id="myRange">
					</div>
				</div>
			</div>

			<!-- 검색버튼 -->
			<button type="button" class="btn-full btn01-reverse" onclick="startupWeatherSearch(dong.value, small_category.value, funds.value)">검색</button>
		</form>
		<!-- search of end -->


		<!-- content -->
		<div class="content">
			<div class="svg-map-box" id="svgMapBox" style="display: block">
				<jsp:include page="/jsp/jsp_nohead/svgMapIndex.jsp" flush="false" />
				<jsp:include page="/jsp/jsp_nohead/svgMapSeoul.jsp" flush="false" />
			</div>
			<div class="search-result-box" id="SearchResultBox">
			</div>
		</div>
	</div>
</div>

<script type="text/javascript" src="<%=request.getContextPath()%>/js/startupWeather.js"></script>
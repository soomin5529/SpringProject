<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- <div class="map-popup dashboard" id="dashboard" style="display:none; display:display"> -->
<div class="map-popup dashboard" id="dashboard">
	<div class="title-box cf">
		<div class="back-btn" onclick="javascript:$('#dash-board').empty()">
			<svg viewBox="0 0 40 40" class="back-icon">
				<path d="M18 35L3 20 18 5" /></svg>
		</div>
		<div class="tit"> ${sigungu.name} ${dong.name} </div>
		<div id="dongCode" style="display: none;">${dong.code }</div>
		<!-- bookmark on/off -->
		<div class="bookmark off" id="bookmark" onclick="bookmark()">
			<svg viewBox="0 0 55 55" class="bookmark-icon">
				<path d="M9.9,30.8l-9.1-8.9c-1.6-1.6-0.7-4.3,1.5-4.6l12.6-1.8c0.9-0.1,1.6-0.7,2-1.5l5.7-11.4c1-2,3.8-2,4.8,0L33.1,14c0.4,0.8,1.1,1.3,2,1.5l12.6,1.8c2.2,0.3,3.1,3,1.5,4.6L40,30.8c-0.6,0.6-0.9,1.5-0.8,2.4l2.1,12.6c0.4,2.2-1.9,3.9-3.9,2.8l-11.3-5.9c-0.8-0.4-1.7-0.4-2.5,0l-11.3,5.9c-2,1-4.3-0.6-3.9-2.8l2.2-12.6C10.9,32.3,10.6,31.4,9.9,30.8z" />
			</svg>
		</div>
	</div>

	<!-- 검색항목 묶음 - 업종선택 -->
	<div class="search-box">
		<div class="label-box">업종 선택</div>
		<div class="input-box third">
			<!-- 산업-대분류 -->
			<select id="main" name="main_category"
				onchange="javascript:dashBoardMiddleCategory(this.value, ${dong.code }); dashBoardSetSession(this, ${dong.code }); findStoreInDongBound(mapBound(), ${dong.code }, this.id, this.value);">
				<option value="no" disabled selected>대분류</option>
				<c:forEach var="main" items ="${maincategory }">
					<option value="${main.code}">${main.name}</option>
				</c:forEach>
			</select>
			<!-- 산업-중분류 -->
			<select id="middle" name="middle_category"
				onchange="javascript:dashBoardSmallCategory(this.value, ${dong.code }); dashBoardSetSession(this, ${dong.code }); findStoreInDongBound(mapBound(), ${dong.code }, this.id, this.value);">
				<option value="no" disabled selected>중분류</option>
			</select>
			<!-- 산업-소분류 -->
			<select id="small" name="small_category" onchange="javascript:dashBoardSetSession(this, ${dong.code }); findStoreInDongBound(mapBound(), ${dong.code }, this.id, this.value);">
				<option value="no" disabled selected>소분류</option>
			</select>
		</div>
	</div>

	<!-- 통계자료 묶음 -->
	<div class="chart-box">
		<div class="chart">
			<div class="tit">대분류 업종 Top3</div>
			<canvas id="chart01"></canvas>
		</div>
		<div class="chart">
			<div class="tit">중분류 업종 Top5</div>
			<canvas id="chart02"></canvas>
		</div>
		<div class="chart">
			<div class="tit">주요 생활상점 개수</div>
			<ul id = "importantFranchise"></ul>
		</div>
	</div>
	<!-- 떠들썩 커뮤니티 버튼 -->
	<button type="button" class="btn-full btn01-reverse"
		onclick="openPopCommunity()">떠들썩</button>
</div>
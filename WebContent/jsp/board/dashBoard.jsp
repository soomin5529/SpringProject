<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
			<%		String display = request.getParameter("display");%>
			
<div class="map-popup dashboard" id="dashboard" style="display:none; display:<%=display %>">
	<div class="title-box cf">
		<div class="back-btn" onclick="closePopDashboard();">
			<svg viewBox="0 0 40 40" class="back-icon">
					<path d="M18 35L3 20 18 5" />
				</svg>
		</div>
		<div class="tit">
			<!--<c:forEach var="sigungu" items="${sigungu}">
			  ${sigungu.name}
			</c:forEach>
			<c:forEach var="dong" items="${dong }">
				 ${dong.name}
			</c:forEach>-->
			<span id="sigunguName" ></span>&nbsp;<span id="dongName" ></span>
		</div>
		<!-- bookmark on/off -->
		<div class="bookmark off" id="bookmark" onclick="bookmark()">
			<svg viewBox="0 0 55 55" class="bookmark-icon">
					<path
					d="M9.9,30.8l-9.1-8.9c-1.6-1.6-0.7-4.3,1.5-4.6l12.6-1.8c0.9-0.1,1.6-0.7,2-1.5l5.7-11.4c1-2,3.8-2,4.8,0L33.1,14c0.4,0.8,1.1,1.3,2,1.5l12.6,1.8c2.2,0.3,3.1,3,1.5,4.6L40,30.8c-0.6,0.6-0.9,1.5-0.8,2.4l2.1,12.6c0.4,2.2-1.9,3.9-3.9,2.8l-11.3-5.9c-0.8-0.4-1.7-0.4-2.5,0l-11.3,5.9c-2,1-4.3-0.6-3.9-2.8l2.2-12.6C10.9,32.3,10.6,31.4,9.9,30.8z" />
				</svg>
		</div>
	</div>

	<!-- 검색항목 묶음 - 업종선택 -->
	<div class="search-box">
		<div class="label-box">업종 선택</div>
		<div class="input-box third">
			<select id="main" name="main_category"
				onchange="javascript:sendToControllerSelectCategoryValue(this);">
				<option value="no" disabled selected>대분류</option>
				<c:forEach var="main" items="${main }">
					<option value="${main.code}">${main.name}</option>
				</c:forEach>
			</select> <select id="middle" name="middle_category"
				onchange="javascript:sendToControllerSelectCategoryValue(this);">
				<option value="no" disabled selected>중분류</option>
			</select> <select id="small" name="small_category">
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
				<div class="tit">주요 생활시설 개수</div>
				<ul>
					<li>
						<svg viewBox="0 0 40 40" class="shop-icon float-l"><path d="M16.5 20.5h17v14h-17zM7 5.5h26l3.5 8.5v6.5h-33V14zm4.5 9.5v6m-5 0v15m22-21v6m-8-6v6"></path></svg>
						<span>맘스터치</span>
						<span class="float-r"><b>40</b>개</span>
					</li>
					<li>
						<svg viewBox="0 0 40 40" class="shop-icon float-l"><path class="st0" d="M31.5,11.4c-1.8,0-3.5,0.9-4.5,2.2c-0.9-3-3.7-5.2-6.9-5.2s-6.1,2.2-6.9,5.2c-1.1-1.3-2.7-2.2-4.5-2.2c-3.2,0-5.7,2.6-5.7,5.7v16.4h10h1.5h11.5h1.5h10V17.2C37.2,14,34.6,11.4,31.5,11.4z"/><line class="st0" x1="13.1" y1="13.7" x2="13.1" y2="19.3"/><line class="st0" x1="26.9" y1="13.7" x2="26.9" y2="19.3"/></svg>
						<span>파리바게트</span>
						<span class="float-r"><b>40</b>개</span>
					</li>
					<li>
						<svg viewBox="0 0 40 40" class="shop-icon float-l"><path d="M4 15h25v15a6 6 0 01-6 6H10a6 6 0 01-6-6V15zm25 0h2a6 6 0 110 12h-2V15zM14 3c0 2 1 3.5 3 4.4 2 1.1 3 2.6 3 4.6"></path></svg>
						<span>스타벅스</span>
						<span class="float-r"><b>40</b>개</span>
					</li>
				</ul>
				
			</div>
		</div>
	<!-- 떠들썩 커뮤니티 버튼 -->
	<button type="button" class="btn-full btn01-reverse"
		onclick="openPopCommunity()">떠들썩</button>
</div>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script type="text/javascript"
	src="https://openapi.map.naver.com/openapi/v3/maps.js?ncpClientId=dhok2btnos"></script>
<script src="https://cdn.jsdelivr.net/npm/chart.js@2.8.0"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/js/main_ajax.js"></script>
<!-- Main 화면 -->
<div class="page main" id="page">

	<!-- Map -->
	<div id="map" class="map"></div>

	<!-- Search Administrative District (행정 구역 검색) -->
	<div id="search-form">
		<form action="" method="post" class="map-popup search-group">
			<!-- Search-Box -->
			<div class="search-box">
				<div class="input-box">

					<!-- Select-Sido (시도) -->
					<select id="sido" name="state"
						onchange="javascript:choiceAdministrativeDistrict(this); $('#dash-board').empty(); $('#dong').empty()">
						<option value="no" disabled selected>선택</option>
						<c:forEach var="sido" items="${sido }">
							<option value="${sido.code}">${sido.name}</option>
						</c:forEach>
					</select>

					<!-- Select-Sigungu (시군구) -->
					<select id="sigungu" name="city"
						onchange="javascript:choiceAdministrativeDistrict(this); findAreaToJson(this.value); $('#dash-board').empty()">
						<option value="no" disabled selected>선택</option>
					</select>

					<!-- Select-Dong (읍면동) -->
					<select id="dong" name="street"
						onchange="javascript:choiceAdministrativeDistrict(this); findAreaToJson(this.value); openDashBoard(this.value);">
						<option value="no" disabled selected>선택</option>
					</select>
				</div>
			</div>
			<!-- End of Search-Box -->
		</form>
	</div>
	<!-- End of Search Administrative District (행정 구역 검색 끝) -->

	<!-- dashboard -->
	<div id="dash-board"></div>
	<%-- <jsp:include page="board/dashBoard.jsp" flush="false" /> --%>
	<!-- end of dashboard -->

	<!-- 떠들썩 커뮤니티 -->
	<jsp:include page="board/boardList.jsp" flush="false" />
	<!-- end of 떠들썩커뮤니티 -->

	<!-- communityRegPopup -->
	<jsp:include page="board/boardWriteForm.jsp" flush="false" />

</div>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/js/map.js"></script>
<!-- end of main -->

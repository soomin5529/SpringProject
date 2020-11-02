<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script type="text/javascript"
	src="https://openapi.map.naver.com/openapi/v3/maps.js?ncpClientId=1daef4c0ea"></script>
<script src="https://cdn.jsdelivr.net/npm/chart.js@2.8.0"></script>
<script>
var sigungu = new Array();
<c:forEach var="sigungu" items="${sigunguList}">
	sigungu.push(
			{code:"${sigungu.code}",
			name: "${sigungu.name}",
			lat: "${sigungu.latitude}",
			lng: "${sigungu.longitude}"}
	)
</c:forEach>


 

    function openDashBoardOfDong(select) {
    	
		var dongcode = select.value;
		$.ajax({
			type : "post",
			url : "<%=request.getContextPath()%>/dashboard/" + dongcode,
			contentType : "application/x-www-form-urlencoded; charset=UTF-8",
			success : function(textStatus) {
				 $("#dashboard").html(textStatus);
				 sendChart();
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
					onchange="javascript:choiceAdministrativeDistrict(this);">
					<option value="no" disabled selected>선택</option>
					<c:forEach var="sido" items="${sido }">
						<option value="${sido.code}">${sido.name}</option>
					</c:forEach>
				</select> <select id="sigungu" name="city"
					onchange="javascript:choiceAdministrativeDistrict(this); ">
					<option value="no" disabled selected>선택</option>
				</select> <select id="dong" name="street"
					onchange="javascript:openDashBoardOfDong(this); choiceAdministrativeDistrict(this); findAreaToJson(this);">
					<option value="no" disabled selected>선택</option>
				</select>
			</div>
		</div>
		<!-- 검색버튼 -->
		<!-- <button type="submit" class="btn-full btn01-reverse">검색</button> -->
	</form>
	<!-- end of search -->

	<!-- dashboard -->
	<div class="map-popup dashboard" id="dashboard" style="display:none;" ></div>
	<!-- end of dashboard -->

	<!-- 떠들썩 커뮤니티 -->
	<!-- end of 떠들썩커뮤니티 -->

	<!-- communityRegPopup -->
	

</div>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/map2.js"></script>
<!-- end of main -->

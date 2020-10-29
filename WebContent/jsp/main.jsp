<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script type="text/javascript" src="https://openapi.map.naver.com/openapi/v3/maps.js?ncpClientId=1daef4c0ea"></script>
<script src="https://cdn.jsdelivr.net/npm/chart.js@2.8.0"></script>
<script>
function findAreaToJson(select){
	var id = select.getAttribute('id');
	var text = $("#"+id + " option:checked").text();
	var paths;
	$.ajax({
		type : "post",
		url : "<%=request.getContextPath()%>/request/findAreaToJson",
		contentType : "application/x-www-form-urlencoded; charset=UTF-8",
		data : {
			'dong' : text,
			'code' : select.value
		},
		success : function(textStatus) {
			drawPolygonDong(textStatus);
			openPopDashboard();
		}
	});
}
/* main화면에서 지역선택 시, 동적으로 다음 옵션 받아옴 */
function sendToControllerSelectValue(select){
	var area = select.getAttribute('id') == 'sido' ? 'sido' : 'sigungu';
	
	$.ajax({
        type: "post", 
        url: "<%=request.getContextPath()%>/request/areaOption",
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

// main화면에서 업종분류 선택시, 다음 하위 옵션의 카테고리 동적으로 받아옴
function sendToControllerSelectCategoryValue(select){
	var category = select.getAttribute('id') == 'main' ? 'main' : 'middle';
	
	$.ajax({
        type: "post", 
        url: "<%=request.getContextPath()%>/request/categoryOption",
			contentType : "application/x-www-form-urlencoded; charset=UTF-8",
			data : {
				'category' : category,
				'code' : select.value
			},
			success : function(textStatus) {
				if(category == 'main'){
					$("#middle").empty().append(textStatus);	
				}else{
					$("#small").empty().append(textStatus);
				}
			}
		});
	}
	
   function sendToControlerguCode(select) {
	   var area = select.getAttribute('id') == 'sigungu' ? 'sigungu' : 'dong';
	   var code =  document.getElementById("sigungu");
	   code = code.options[code.selectedIndex].value;
	   $.ajax({
	        type: "post", 
	        url: "<%=request.getContextPath()%>/request/selectCode",
				contentType : "application/x-www-form-urlencoded; charset=UTF-8",
				data : {
					'area' : area,
					'code' : code
					
				},
				success : function(textStatus) {
					var gu_name = textStatus;
					 $('#result').empty().append(textStatus);
				}
			});
   }
   
   function sendToControlerdongCode(select) {
	   var area = select.getAttribute('id') == 'sigungu' ? 'sigungu' : 'dong';
	   var code =  document.getElementById("dong");
	   code = code.options[code.selectedIndex].value;
	   $.ajax({
	        type: "post", 
	        url: "<%=request.getContextPath()%>/request/selectCode",
			contentType : "application/x-www-form-urlencoded; charset=UTF-8",
			data : {
				'area' : area,
				'code' : code
			},
			success : function(textStatus) {
				var dong_name = textStatus;
				$('#result').append(textStatus);
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
				</select> <select id="sigungu" name="city" onchange="javascript:sendToControllerSelectValue(this); selectCity();  sendToControlerguCode(this)">
					<option value="no" disabled selected>선택</option>
				</select> <select id="dong" name="street" onchange="javascript:sendToControlerdongCode(this); findAreaToJson(this);">
					<option value="no" disabled selected>선택</option>
				</select>
			</div>
		</div>
		<!-- 검색버튼 -->
		<!-- <button type="submit" class="btn-full btn01-reverse">검색</button> -->
	</form>
	<!-- end of search -->

	<!-- dashboard -->
	<jsp:include page="board/dashBoard.jsp" flush="false" />
	<!-- end of dashboard -->

	<!-- 떠들썩 커뮤니티 -->
	<jsp:include page="board/boardList.jsp" flush="false" />
	<!-- end of 떠들썩커뮤니티 -->

	<!-- communityRegPopup -->
	<jsp:include page="board/boardWriteForm.jsp" flush="false" />

</div>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/map2.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/chart.js"></script>
<!-- end of main -->

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
	
   function sendToControllerguCode(select) {
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
					 //$('#result').empty().append(textStatus);
					  document.getElementById("sigunguName").innerHTML = gu_name;
				}
			});
   }

    function sendToControlerdongCode(select) {
		var area = select.getAttribute('id') == 'sigungu' ? 'sigungu' : 'dong';
		var gucode = document.getElementById("sigungu");
		var dongcode = document.getElementById("dong");

		gucode = gucode.options[gucode.selectedIndex].value;
		dongcode = dongcode.options[dongcode.selectedIndex].value;
		//document.street.submit();
		$.ajax({
			type : "post",
			url : "<%=request.getContextPath()%>/request/selectCode",
			contentType : "application/x-www-form-urlencoded; charset=UTF-8",
			data : {
				'area' : area,
				'code' : dongcode
			},
			success : function(textStatus) {
				var dong_name = textStatus;
				//$('#result').append(textStatus);
				document.getElementById("dongName").innerHTML = dong_name;
			}
		});
	}
   
function sendLike(board){ 
	var boardid = board.parentNode.parentNode.id;
	var status = "insert";
	var postLikeBtn = document.getElementById(boardid);
	var userid = document.getElementById("userid").value; 
	if (postLikeBtn.classList.contains('on')) {
   		status = "delete";}
   
   	$.ajax({
		type : "post",
		url : "<%=request.getContextPath()%>/request/insertLike",
		contentType : "application/x-www-form-urlencoded; charset=UTF-8",
		data : {
			'userid' : userid,
			'boardid': boardid,
			'status'  : status
		},
		success : function(textStatus) {
			postLike(boardid+"u");
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
					onchange="javascript:choiceAdministrativeDistrict(this); sendToControllerguCode(this);">
					<option value="no" disabled selected>선택</option>
				</select> <select id="dong" name="street"
					onchange="javascript:sendToControlerdongCode(this); choiceAdministrativeDistrict(this); findAreaToJson(this);">
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
<script type="text/javascript"
	src="<%=request.getContextPath()%>/js/map2.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/js/chart.js"></script>
<!-- end of main -->

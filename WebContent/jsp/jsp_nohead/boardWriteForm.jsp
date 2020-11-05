<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="deemed" onclick="closePopCommunityReg()"></div>
<span class="close-btn" onclick="javascript:closePopCommunityReg();">x</span>
<div class="pop-box" style="width: 400px; height: 570px;">
	<form method="post" name="writeform" enctype="multipart/form-data">
		<div class="title-box">
			<input type="hidden" name="dongCode" value="${dongCode}"> <input
				type="hidden" name="userid" value="${userid }" />
			<div class="tit">${sigunguName}  ${dongName}</div>
		</div>
		<div class="content-box">
			<!-- member 테이블에 있는 name 값 : 이름 -->
			<input type="text" name="writer" onfocus="this.value='${name}';"
				placeholder="닉네임을 입력하세요" style="margin-bottom: 10px;">
			<!-- 내용입력 -->
			<textarea name="content" rows=14
				placeholder="이 상권에 창업을 준비하는 사람들에게 알려주고 싶은 이야기, 꿀팁을 적어주세요! 궁금하신 것을 물어보셔도 됩니다 ʕ￫ᴥ￩ʔ"></textarea>
			<!-- 사진등록 -->
			<div class="photo-box cf">
				<div class="thumb" id="photo"
					style="background-image: url('../images/temp01.jpg')"></div>

				<div class="btn-photo" onclick="onclick=document.all.file.click()">
					<input type="file" id="file" name="uploadfile"
						style="display: none;" />
				</div>
			</div>
			<input type="button" onclick="writeBoard()"
				class="btn-full btn01-reverse" value="등록하기" />
		</div>
	</form>
</div>
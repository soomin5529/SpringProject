<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div class="page mypage-Delete" id="page">
	<!-- page title section -->
	<div class="page-title-box">
		<div class="page-name">탈퇴하기</div>
		<div class="page-desc">
			안녕하세요  <span id="name" name="name">${name} </span>회원님<br />
			정말 탈퇴하실 거에요?( Ĭ ^ Ĭ )
		</div>
	</div>
	
	<div class="content-body cf">
		<div class="search-group">
			<ul class="lnb-box">
				<li onclick="location.href='<%=request.getContextPath()%>/member/myPageModify'">내 정보 변경</li>
				<li onclick="location.href='<%=request.getContextPath()%>/member/mypageDelete'" class="on">탈퇴하기</li>
			</ul>
		</div>
		<div class="content">
			<form name="deleteForm" action="/SpringTeamProject/member/deletemember" method="post">
				<table class="tbl tbl-reg">
					<tr>
						<th>비밀번호</th>
						<td><input type="password" id="pwd" name="pwd" placeholder="비밀번호를 입력하세요"/></td>
					</tr>
					<tr>
						<td colspan="2"><button type="submit" class="btn-full btn01" style="width:200px;">탈퇴하기</button></td>
					</tr>
				</table>
			</form>
		</div>
	</div>
</div>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div class="page mypage-Pwd" id="page">
	<!-- page title section -->
	<div class="page-title-box">
		<div class="page-name">비밀번호 변경</div>
		<div class="page-desc">
			안녕하세요 <span>홍길동</span>회원님<br />
			비밀번호를 변경하시겠습니까?
		</div>
	</div>
	<div class="content-body cf">
		<div class="search-group">
			<ul class="lnb-box">
				<li onclick="location.href='<%=request.getContextPath()%>/mypage/mypageModify'">내 정보 변경</li>
				<li onclick="location.href='<%=request.getContextPath()%>/mypage/mypagePwd'" class="on">비밀번호 변경</li>
				<li onclick="location.href='<%=request.getContextPath()%>/mypage/mypageDelete'" >탈퇴하기</li>
			</ul>
		</div>
		<div class="content">
			<form action="">
				<table class="tbl tbl-reg">
					<tr>
						<th>비밀번호</th>
						<td><input type="password" name="oldpwd" id="oldpwd" placeholder="기존 비밀번호를 입력하세요"/></td>
					</tr>
					<tr>
						<th>비밀번호</th>
						<td><input type="password" name="pwd" id="pwd" placeholder="새 비밀번호를 입력하세요"/></td>
					</tr>
					<tr>
						<th>비밀번호</th>
						<td><input type="password" name="repwd" id="repwd" placeholder="새 비밀번호를 다시 입력하세요"/></td>
					</tr>
					<tr>
						<td colspan="2"><button type="submit" class="btn-full btn01" style="width:200px;">수정하기</button></td>
					</tr>
				</table>
			</form>
		</div>
	</div>
</div>
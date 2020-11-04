<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div class="page mypage-modify" id="page">
	<!-- page title section -->
	<div class="page-title-box">
		<div class="page-name">내 정보 변경</div>
		<div class="page-desc">
			안녕하세요 <span id="name" name="name">${dto.name} </span>회원님<br />
			회원님 정보를 변경하시겠습니까?
		</div>
	</div>
	
	<div class="content-body cf">
		<div class="search-group">
			<ul class="lnb-box">
	
				<li onclick="location.href='<%=request.getContextPath()%>/member/myPageModify'" class="on">내 정보 변경</li>
				<li onclick="location.href='<%=request.getContextPath()%>/member/mypageDelete'">탈퇴하기</li>
			</ul>
		</div>
		<div class="content">
			<form method="post" name="updateForm" enctype="multipart/form-data"
         action="/SpringTeamProject/member/updateMember">

				<table class="tbl tbl-reg">
					<tr>
						<th>아이디</th>
						<td><input type="text" id="userid" name="userid" value="${dto.userid}" disabled/></td>
					</tr>
					<tr>
						<th>비밀번호</th>
						<td><input type="password" id="pwd" name="pwd" placeholder="비밀번호를 입력하세요" /></td>
					</tr>
					<tr>
						<th>비밀번호 확인</th>
						<td><input type="password" id="repwd" name="repwd" placeholder="비밀번호를 입력하세요" /></td>
					</tr>
					<tr>
						<th>이름</th>
						<td><input type="text" id="name" name="name" placeholder="이름을 입력하세요" value="${dto.name}"/></td>
					</tr>
					<tr>
						<th>이메일</th>
						<td><input type="email" id="email" name="email" placeholder="이메일을 입력하세요" value="${dto.email }"/></td>
					</tr>
					<tr>
						<th>생일</th>
						<td><%-- <input type="date" id="birthdate" name="birthdate" placeholder="생일을 입력하세요" value="${dto.birthdate}"/> --%>
							<input type="text" id="birthdate" name="birthdate" placeholder="생일을 입력하세요" value="${dto.birthdate}"/>						
						</td>
					</tr>
					<tr>
						<th>성별</th>
						<td>
							<input type="radio" name="gender" id="male" value="male" ${dto.gender.equals("male") ? "checked":"" }/><label for="male">남성</label>
							<input type="radio" name="gender" id="female" value="female" ${dto.gender.equals("female")?"checked":""}/><label for="female">여성</label>
						</td>
					</tr>
					<tr>
						<td colspan="2"><button type="submit" class="btn-full btn01" style="width:200px;" onclick="pwdCheck()">수정하기</button></td>

					</tr>
				</table>
			</form>
		</div>
	</div>
</div>
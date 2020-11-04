<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="title-box cf">
	<div class="close-btn" onclick="closePopPush();">
		<svg viewBox="0 0 40 40" class="close-icon">
						<line x1="4.9" y1="4.9" x2="35.1" y2="35.1" />
							<line x1="35.1" y1="4.9" x2="4.9" y2="35.1" />
						</svg>
	</div>
	<div class="tit">알림</div>
</div>
<ul>
	<c:forEach var="notice" items="${noticeList}">
		<li>
			<div class="msg">
				<span class="highlight01">${AreaNamemap.value}</span>에 새글이 등록되었습니다.
			</div>
			<c:forEach var="regDate" items="${regDatemap}">
							<c:if test="${regDate.key eq noticeList.boardid}">
			<div class="regdate">10분 전</div></c:if></c:forEach>
		</li>
	</c:forEach>
	<li class="readed">
		<div class="msg">
			<span class="highlight01">강남구 역삼동</span>에 새글이 등록되었습니다.
		</div>
		<div class="regdate">10분 전</div>
	</li>
</ul>


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
	<c:if test="${notice.readed ==0 }">
		<li id="openDash" onclick="javascript:openDashBoard(${notice.dongCode}); readed(${notice.boardid});">
	</c:if>
	<c:if test="${notice.readed ==1 }">
		<li class = "readed" id="openDash" onclick="openDashBoard(${notice.dongCode})">
	</c:if>
			<div class="msg">
			<c:forEach var="AreaName" items="${AreaNamemap}">
				<c:if test="${AreaName.key eq notice.boardid}">
				<span class="highlight01">${AreaName.value}</span>에 새글이 등록되었습니다.
			</c:if></c:forEach></div>
			<c:forEach var="regDate" items="${regDatemap}">
							<c:if test="${regDate.key eq notice.boardid}">
			<div class="regdate">${regDate.value }</div></c:if></c:forEach>
		</li>
	</c:forEach>
</ul>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="map-popup community" id="community" style="display: none">
	<div class="title-box cf">
		<div class="back-btn" onclick="closePopCommunity()">
			<svg viewBox="0 0 40 40" class="back-icon">
					<path d="M18 35L3 20 18 5" />
				</svg>
		</div>
		<div class="tit">
			<span>떠들썩</span> <span class="highlight01" id="postCount">${count }</span>
		</div>
		<div class="reg-btn" onclick="openPopCommunityReg()">
			<svg viewBox="0 0 40 40" class="reg-icon">
					<path
					d="M28.6 4.5c.4 0 .8.2 1 .4l5.5 5.4a1.5 1.5 0 010 2.1L13 34.5H5.5V27l22-22c.3-.3.7-.5 1.1-.5zm-6.1 6l7 7" />
				</svg>
		</div>
	</div>
	<!-- list-box -->
	<div class="list-box">
		<!-- post-box -->

		<c:if test="${count == 0 }">
			현재 저장된 게시글이 없습니다
			</c:if>
		<c:if test="${count != 0 }">
			<c:forEach var="article" items="${articleList}">
				<div class="post-box">
					<div class="content-box cf">
						<div class="name">${article.writer}</div>
						<div class="content">
							<div class="text">${article.content }</div>
							<div class="photo"
								style="background-image:url('<%=request.getContextPath() %>/uploadFile/${article.filename}');"></div>
						</div>
						<div class="regdate">${article.regDate }</div>
						<div class="like-btn on" id="likeBtn" onclick="postLike()">
							<!-- 좋아요 아이콘 -->
							<svg viewBox="0 0 40 40" class="like-icon">
							<path
									d="M25 15V8c.4-2.4-1.5-4.7-4-5h-2a2 2 0 00-2 1.5v.2l-1.3 8.2-3 7.1H2v18h24.4C36 38 38 32.4 38 27.6V20c.2-2.6-1.6-5-4-4.9-.5-.4-.8-.4-1 0h-8zM12 38H9V20h3v18z"></path>
						</svg>

							<span class="like-txt">좋아요</span>
						</div>
					</div>
						<div class="reply-list-box">
							<div class="reply-box">
								<div class="name">역삼 노비</div>
								<div class="content">댓글 : 거기 먹어보니까 맛은 별로던데 상권이 좋아서 매출이
									괜찮나보네요 쩝;;</div>
								<div class="regdate">두달 전</div>
							</div>
						</div>
						<div class="reply-reg-box">
							<input type="text" placeholder="댓글을 입력하세요" />
							<div class="reg-btn">
								<svg viewBox="0 0 40 40" class="reg-icon">
						<path
										d="M28.6 4.5c.4 0 .8.2 1 .4l5.5 5.4a1.5 1.5 0 010 2.1L13 34.5H5.5V27l22-22c.3-.3.7-.5 1.1-.5zm-6.1 6l7 7"></path></svg>
							</div>
						</div>
				</div>
			</c:forEach>
		</c:if>
	</div>


</div>
<!-- end of post-box -->

<!-- end of list-box -->


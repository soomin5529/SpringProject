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
			<script type="text/javascript">
			
				var regDate = new Date("${article.regDate}");
				var today = new Date();
				var day = "일 전";

				regDate = new Date(regDate.getFullYear(),
						regDate.getMonth() + 1, regDate.getDate());
				today = new Date(today.getFullYear(), today.getMonth() + 1,
						today.getDate());

				var diff = Math.abs(today.getTime() - regDate.getTime());
				diff = Math.ceil(diff/(1000 * 3600 * 24 ));
				
			//	document.getElementById("boardregDate").innerHTML = diff + day;
			</script>
				<div class="post-box">
					<div class="content-box cf">
						<div class="name">${article.writer}</div>
						<div class="content">
							<div class="text">${article.content }</div>
							<div class="photo"
								style="background-image:url('<%=request.getContextPath() %>/uploadFile/${article.filename}');"></div>
						</div>
						<div class="regdate" id="boardregDate">${article.regDate }</div>
						<div class="like-btn off" id="likeBtn"
							onclick="javascript:postLike(); sendBoardLike();">
							<!-- 좋아요 아이콘 -->
							<svg viewBox="0 0 40 40" class="like-icon">
								<path d="M20 4.5h.8c.9.1 1.6.5 2.1 1.2.5.6.8 1.4.6 2.2v8.6H34c.7 0 1.4.3 1.8.9.5.6.8 1.5.7 2.4v7.8c0 2.3-.5 4.9-2.5 6.7-1.5 1.3-4 2.2-7.6 2.2H3.5v-15h7.6l5.2-8 1.3-8.6.1-.3zm-9.5 17v15"></path>
							</svg>
							<span class="like-txt">좋아요</span>
						</div>
					</div>
					<c:if test="${cnt != 0 }">
						<div class="reply-list-box">
							<c:forEach var="comment" items="${commentList}">
							<div class="reply-box">
									<div class="name">${comment.name }</div>
									<div class="content"> ${comment.content }</div>
									<div class="regdate" id="comment.regDate">${comment.regDate }</div>
							</div>
							</c:forEach>
						</div>
					</c:if>
					<div class="reply-reg-box">
						<form method="post" id="commentform"
							action="<%=request.getContextPath()%>/board/commentUploadPro">
							<input type="hidden" name="userid" value="<%=userid%>" /> <input
								type="hidden" name="name" value="<%=name%>" /> <input
								type="hidden" name="boardid" value="${article.boardid }" /> <input
								type="text" name="content" placeholder="댓글을 입력하세요" />

							<div class="reg-btn"
								onclick="javascript:document.getElementById('commentform').submit();">
								<svg viewBox="0 0 40 40" class="reg-icon">
						<path
										d="M28.6 4.5c.4 0 .8.2 1 .4l5.5 5.4a1.5 1.5 0 010 2.1L13 34.5H5.5V27l22-22c.3-.3.7-.5 1.1-.5zm-6.1 6l7 7"></path></svg>
							</div>
						</form>
					</div>
				</div>
			</c:forEach>
		</c:if>
	</div>

</div>
<!-- end of post-box -->

<!-- end of list-box -->



<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%   String display = request.getParameter("display"); %>
<div class="map-popup community" id="community" style="display: none; display:<%=display %>">
   <div class="title-box cf">
      <div class="back-btn" onclick="closePopCommunity()">
         <svg viewBox="0 0 40 40" class="back-icon">
            <path d="M18 35L3 20 18 5"/>
         </svg>
      </div>
      <div class="tit">
         <span>떠들썩</span>
         <span class="highlight01" id="postCount">${count }</span>
      </div>
      <div class="reg-btn" onclick="openPopCommunityReg()">
         <svg viewBox="0 0 40 40" class="reg-icon">
            <path d="M28.6 4.5c.4 0 .8.2 1 .4l5.5 5.4a1.5 1.5 0 010 2.1L13 34.5H5.5V27l22-22c.3-.3.7-.5 1.1-.5zm-6.1 6l7 7"/>
         </svg>
      </div>
   </div>
   <!-- list-box -->
   <div class="list-box">
      <c:if test="${count == 0 }">
            <div class="empty-txt">현재 저장된 게시글이 없습니다</div>
         </c:if>
         <c:if test="${count != 0 }">
            <c:forEach var="article" items="${articleList}">
            <!-- post-box -->
            <div class="post-box">
               <div class="content-box cf">
                  <div class="name">${article.writer}</div>
                  <div class="content">
                     <div class="text">${article.content }</div>
                     <div class="photo" style="background-image:url('<%=request.getContextPath() %>/uploadFile/${article.filename}');"></div>
                  </div>
                  <c:forEach var="regDate" items="${regDate}">
                     <c:if test="${regDate.key eq article.boardid}">
                        <div class="regdate" id="boardregDate">${regDate.value }<span> · </span>
                     </c:if>
                  </c:forEach>
                  <c:forEach var="boardLike" items="${boardLike}">
                     <c:if test="${boardLike.key eq article.boardid}">
                                 ${boardLike.value}명에게 도움됐어요
                        </div>
                     </c:if>
                  </c:forEach>
                  <div class="like-btn off" id="likeBtn" onclick="postLike()">
                     <!-- 좋아요 아이콘 -->
                     <svg viewBox="0 0 40 40" class="like-icon">
                        <path d="M25 15V8c.4-2.4-1.5-4.7-4-5h-2a2 2 0 00-2 1.5v.2l-1.3 8.2-3 7.1H2v18h24.4C36 38 38 32.4 38 27.6V20c.2-2.6-1.6-5-4-4.9-.5-.4-.8-.4-1 0h-8zM12 38H9V20h3v18z"></path>
                     </svg>
                     <span class="like-txt">좋아요</span>
                  </div>
               </div>
               
               <div class="reply-list-box">
                  <c:forEach var="map" items="${map}">
                     <c:if test="${map.key eq article.boardid}">
                        <c:forEach var="comment" items="${map.value}">
                           <div class="reply-box">
                              <div class="name">${comment.name }</div>
                              <div class="content">${comment.content }</div>
                              <div class="regdate" id="comment.regDate">${comment.regDate}</div>
                           </div>
                        </c:forEach>
                     </c:if>
                  </c:forEach>
               </div>
               <div class="reply-reg-box">
                  <form method="post" id="commentform" action="<%=request.getContextPath()%>/board/commentUploadPro">
                     <input type="hidden" name="userid" value="<%=userid%>" />
                     <input type="hidden" name="name" value="<%=name%>" />
                     <input type="hidden" name="boardid" value="${article.boardid }" />
                     <input type="text" name="content" placeholder="댓글을 입력하세요" />
            
                     <div class="reg-btn" onclick="javascript:document.getElementById('commentform').submit();">
                        <svg viewBox="0 0 40 40" class="reg-icon"><path d="M28.6 4.5c.4 0 .8.2 1 .4l5.5 5.4a1.5 1.5 0 010 2.1L13 34.5H5.5V27l22-22c.3-.3.7-.5 1.1-.5zm-6.1 6l7 7"></path></svg>
                     </div>
                  </form>
               </div>
            </div>
         </c:forEach>
      </c:if>
      <!-- end of post-box -->
   </div>
   <!-- end of list-box -->
</div>
<!-- end of community -->
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- main에서 대분류 중 소분류 선택할때 -->
<option value="no" disabled selected>선택</option>
<c:forEach var="option" items="${option}">
	<option value="${option.code}">${option.name}</option>
</c:forEach>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String userId = request.getParameter("userId");
	session.setAttribute("userId", userId);
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=no">
   	<link rel="icon" type="image/png" sizes="32x32" href="<%=request.getContextPath()%>/images/favicon-32x32.png">
   	<link rel="icon" type="image/png" sizes="16x16" href="<%=request.getContextPath()%>/images/favicon-16x16.png">
    <title>잘나가게:창업은치킨만있는게아니다</title>
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/202010_reset.css">
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/common.css">
    <script src = "http://code.jquery.com/jquery-1.7.js"></script>
</head>
<body>
	<div class="header">
		<!-- logo -->
		<div class="logo" onclick="location.href='<%=request.getContextPath()%>/view/main'">
			<img src="<%=request.getContextPath()%>/images/logo_w.png" alt="logo" />
		</div>
		
		<!-- main menu -->
		<ul class="main-menu cf">
			<li id="menu01" onclick="location.href='<%=request.getContextPath()%>/view/main';">창업지도</li>
			<li id="menu02" onclick="location.href='<%=request.getContextPath()%>/view/startupKeyword';">창업키워드</li>
			<li id="menu03" onclick="location.href='<%=request.getContextPath()%>/view/startupWeather';">창업기상도</li>
		</ul>
		
		<!-- user menu -->
		<ul class="user-menu">
			<li><img src="<%=request.getContextPath()%>/images/ic_bell.png" alt="push" /></li>
			<li onclick="openPopMyPage()"><img src="<%=request.getContextPath()%>/images/ic_user.png" alt="user" /></li>
		</ul>
	</div>
	
	<div class="pop-container login" id="login">
	</div>
	<div class="pop-container login" id="join">
	</div>
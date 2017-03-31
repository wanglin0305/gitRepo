<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>信息板</title>
</head>
<body>
<c:choose>
	<c:when test="${code eq 'success' }">
		<c:set var="img" value="/images/duihao.jpg"></c:set>
		<c:set var="title" value="成功"/>
	</c:when>
</c:choose>
<div class="divBody">
	<div class="divTitle">
		<span class="spanTitle">${title }</span>
	</div>
	<div class="divContent">
	  <div style="margin: 20px;">
		<img style="float: left; margin-right: 30px;" src="<c:url value='${img }'/>" width="150"/>
		<span style="font-size: 30px; color: #c30; font-weight: 900;">${msg }</span>
		<br/>
		<br/>
		<br/>
		<br/>
		<span style="margin-left: 50px;"><a target="_top" href="<c:url value='/jsp/user/login.jsp'/>">登录</a></span>
		<span style="margin-left: 50px;"><a target="_top" href="<c:url value='/index.jsp'/>">主页</a></span>
	  </div>
	</div>
</div>
</body>
</html>
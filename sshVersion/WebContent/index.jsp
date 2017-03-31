<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>主页</title>
</head>
<body>
<c:choose>

	<c:when test="${empty sessionScope.user }">
		  <a href="<c:url value='/jsp/user/login.jsp'/>" target="_parent">登录</a> |&nbsp; 
		  <a href="<c:url value='/regist.jsp'/>" target="_parent">注册</a>	
	</c:when>
	<c:otherwise>
		      会员：${sessionScope.user.userName }&nbsp;&nbsp;|&nbsp;&nbsp; 
		  <a href="<c:url value='/jsp/user/pwd.jsp'/>" target="body">修改密码</a>&nbsp;&nbsp;|&nbsp;&nbsp;
		  <a href="<c:url value='/userAction_logout'/>" target="_parent">退出</a>&nbsp;&nbsp;|&nbsp;&nbsp;
	</c:otherwise>
</c:choose>
	<a href="${pageContext.request.contextPath}/foodMaterialAction_listCategory">查看食物原料信息</a><br/>
	<a href="${pageContext.request.contextPath}/recipeAction_listCategory">查看食谱信息</a><br/>
	<a href="${pageContext.request.contextPath}/recommendAction_validateInfo">创建食谱</a><br/>
</body>
</html>
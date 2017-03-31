<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" 
           uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="js/jquery-3.1.1.js"></script>
<script type="text/javascript" src="js/myjquery2.js"></script>
<title>食谱信息展示页面</title>
</head>
<body>
<h1>食谱信息展示页面</h1>
	<c:forEach items="${rclist }" var="rc">
		${rc.rcname }:<a id="${rc.rcId }" href="#">${rc.rcname }</a>	
	</c:forEach>

	<div id="fm"></div>
</body>
</html>
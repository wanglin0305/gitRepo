<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="<c:url value='/jsp/css/user/regist.css'/>">
<script type="text/javascript" src="<c:url value='/js/jquery-3.1.1.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/user/regist.js'/>"></script>
<title>registPage</title>


</head>
<body>
	<div id="divMain">
		<div id="divTitle">
			<span id="spanTitle">新用户注册</span>
		</div>

		<div id="divBody">
		<form action="${pageContext.request.contextPath}/userAction_regist"  method="post" id="registForm">
			<table id="tableForm" >
				<tr>
					<td class="tdText">用户名：</td>
					<td class="tdInput"><input class="inputClass"  type="text" name="loginname"  id="loginname"/></td>
					<td class="tdError"><label class="errorClass" id="loginnameError">${errors.loginname }</label></td>
				</tr>
				<tr>
					<td class="tdText">登录密码：</td>
					<td><input class="inputClass"  type="password" name="loginpass"  id="loginpass"/></td>
					<td><label class="errorClass" id="loginpassError">${errors.loginpass }</label></td>
				</tr>
				<tr>
					<td class="tdText">确认密码：</td>
					<td><input class="inputClass"  type="password" name="reloginpass"  id="reloginpass"/></td>
					<td><label class="errorClass" id="reloginpassError">${errors.reloginpass }</label></td>
				</tr>
				<tr>
					<td class="tdText">Email：</td>
					<td><input class="inputClass"  type="text" name="email"  id="email"/></td>
					<td><label class="errorClass" id="emailError">${errors.email }</label></td>
				</tr>
				<tr>
					<td class="tdText">验证码：</td>
					<td><input class="inputClass"  type="text" name="verifycode"  id="verifycode"/></td>
					<td><label class="errorClass" id="verifycodeError">${errors.verifyCode }</label></td>
				</tr>
				<tr>
					<td></td>
					<td>
						<div id="divVerifyCode">
							<img id="imgVerifyCode"  src="<c:url value='/verifyCodeAction'/>" />
						</div>
					</td>
					<td><label><a href="javascript:_hyz()">换一张</a></label></td>
				</tr>
				<tr>
					<td></td>
					<td><input type="image"
						src="<c:url value='/images/regist1.jpg'/>"  id="submitBtn" /></td>
					<td><label></label></td>
				</tr>
			</table>
			</form>
		</div>
	</div>
</body>
</html>
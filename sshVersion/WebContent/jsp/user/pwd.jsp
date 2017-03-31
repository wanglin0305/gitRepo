<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="<c:url value='/jsp/css/css.css'/>">
<link rel="stylesheet" type="text/css" href="<c:url value='/jsp/css/user/pwd.css'/>">
<script type="text/javascript" src="<c:url value='/js/jquery-3.1.1.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/user/pwd.js'/>"></script>
<script src="<c:url value='/js/common.js'/>"></script>
<title>pwd</title>
</head>
<body>
	<div class="div0">
		<span>修改密码</span>
	</div>

	<div class="div1">
		<form action="<c:url value='/userAction_updatePassword'/>"
			method="post" target="_top">
			<table>
				<tr>
					<td><label class="error">${msg }</label></td>
					<td colspan="2">&nbsp;</td>
				</tr>
				<tr>
					<td align="right">原密码:</td>
					<td><input class="input" type="password" name="loginpass"
						id="loginpass" value="${userBean.loginpass }" /></td>
					<td><label id="loginpassError" class="error"></label>${errors.loginpass }</td>
				</tr>
				<tr>
					<td align="right">新密码:</td>
					<td><input class="input" type="password" name="newpass"
						id="newpass" value="${userBean.newpass }" "/></td>
					<td><label id="newpassError" class="error">${errors.newpass }</label></td>
				</tr>
				<tr>
					<td align="right">确认密码:</td>
					<td><input class="input" type="password" name="reloginpass"
						id="reloginpass" value="${userBean.reloginpass }" "/></td>
					<td><label id="reloginpassError" class="error">${errors.reloginpass }</label></td>
				</tr>
				<tr>
					<td align="right"></td>
					<td><img id="vCode" src="<c:url value='/verifyCodeAction'/>"
						border="1" /> <a href="javascript:_change();">看不清，换一张</a></td>
				</tr>
				<tr>
					<td align="right">验证码:</td>
					<td><input class="input" type="text" name="verifycode"
						id="verifyCode" value="${userBean.verifycode }" "/></td>
					<td><label id="verifyCodeError" class="error">${errors.verifyCode }</label></td>
				</tr>
				<tr>
					<td align="right"></td>
					<td><input id="submit" type="submit" value="修改密码" /></td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>
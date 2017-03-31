<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="<c:url value='/jsp/css/recommend/userInfo.css'/>">
<script type="text/javascript" src="<c:url value='/js/jquery-3.1.1.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/recommend/userInfo.js'/>"></script>
<title>填写信息页面</title>
</head>
<body>
<body>
	<div id="divMain">
		<div id="divTitle">
			<span id="spanTitle">填写用户信息</span>
		</div>

		<div id="divBody">
		<form action="${pageContext.request.contextPath}/recommendAction_userInfo"  method="post" id="registForm">
			<table id="tableForm" >
				<tr>
					<td class="tdText">用户名：</td>
					<td class="tdInput"><label>${user.userName }</label></td>
				</tr>
				<tr>
					<td class="tdText">Email：</td>
					<td><label>${user.email }</label></td>
				</tr>
					<tr>
					<td class="tdText">年龄：</td>
					<td class="tdInput"><input class="inputClass"  type="text" name="age"  id="age" /></td>
					<td class="tdError"><label class="errorClass" id="ageError">${errors.age }</label></td>
				</tr>
					<tr>
					<td class="tdText">体重：</td>
					<td class="tdInput"><input class="inputClass"  type="text" name="weight"  id="weight"/></td>
					<td class="tdError"><label class="errorClass" id="weightError">${errors.weight}</label></td>
				</tr>
				<tr>
					<td class="tdText">性别：</td>
					<td class="tdInput">
					<label><input class="inputClass"  type="radio"  value="male" name="sex" />男</label>
					<label><input class="inputClass"  type="radio"  value="female" name="sex" />女</label>
					</td>
					<td class="tdError"><label class="errorClass" id="sexError">${errors.sex }</label></td>
				</tr>
					<tr>
					<td class="tdText">工作性质：</td>
					<td class="tdInput">
					<label><input class="inputClass"  type="radio"  value="1" name="work" />轻</label>
					<label><input class="inputClass"  type="radio"  value="2" name="work" />中</label>
					<label><input class="inputClass"  type="radio"  value="3" name="work" />重</label>
					</td>
					<td class="tdError"><label class="errorClass" id="workError">${errors.work }</label></td>
				</tr>
				<tr>
					<td class="tdText">怀孕：</td>
					<td class="tdInput">
					<label><input class="inputClass"  type="radio"  value="1" name="pregnant" />yes</label>
					<label><input class="inputClass"  type="radio"  value="0" name="pregnant" />no</label>
					</td>
					<td class="tdError"><label class="errorClass" id="pregnantError">${errors.pregnant }</label></td>
				</tr>
				<tr>
					<td class="tdText">哺乳期：</td>
					<td class="tdInput">
					<label><input class="inputClass"  type="radio"  value="1" name="lactation" />yes</label>
					<label><input class="inputClass"  type="radio"  value="0" name="lactation" />no</label>
					</td>
					<td class="tdError"><label class="errorClass" id="lactationError">${errors.lactation }</label></td>
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
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>食物原料展示页面</title>
<script type="text/javascript" src="js/jquery-3.1.1.js"></script>
<script type="text/javascript" src="js/myjquery.js"></script>
</head>
<body>
	<h1>食物原料展示页面</h1>
	<!-- a的onclick=一个jquery代码，代码中有一个ajax过程，使用xhr跳转到action，在href中不需要跳转，
	使用“#”就可以了 -->
	<!-- 或者给a一个id，直接在js文件中，查找这个a，再赋予它一个onclick属性？ -->
	<!-- 页面左边，用来展示食谱一级分类数据 -->
	<div id="leftSide">
		<ul>
			<c:forEach items="${fmclist }" var="fmc">
				<li><a  class="firstCategory" id="${fmc.fmCategoryId }" href="#">${fmc.fmCategoryName }</a>
				</li>
			</c:forEach>
		</ul>
	</div>

	<!-- 页面右边上半部分，用来展示食谱二级分类数据，通过ajax返回的数据填充-->
	<div class="rightUpSide">
		<table>
			<tr  id="rightUpSide">
			</tr>
		</table>
	</div>

	<!-- 页面右边中间部分，是查找模块 -->
	<div id="rightMiddleFind">
		根据食材名称查询：<input type="text" id="fmName"  name="findByName" ><br/>
		根据卡路里查询：<input type="text"  id="calorieMin" name="min">至<input type="text"  id="calorieMax" name="max">
		<input id="findFM" type="submit" value="提交">
	</div>

	<!-- 页面右边中间下，用来展示详细食材信息，通过ajax返回来的数据填充 -->
	<div id="rightMiddleContent"></div>

	<!-- 页面右边下半部分，用来展示详细食材名称，通过ajax返回来的数据填充 -->
	<div id="rightDownSide"></div>




</body>
</html>
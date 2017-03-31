<%@ page language="java" contentType="text/xml; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" 
           uri="http://java.sun.com/jsp/jstl/core" %>
<foodMaterial>
	<name>${fm.fName}</name>
	<othername>${fm.otherName}</othername>
	<calorie>${fm.calorie}</calorie>
	<carbohydrate>${fm. cabohydrate}</carbohydrate>
	<protein>${fm.protein}</protein>
	<fat>${fm.fat}</fat>
	<c:forEach items="${fm.fMaterialCategorieSet}"  var="category">
	<category>${category.fmCategoryName }</category>
	</c:forEach>
</foodMaterial>
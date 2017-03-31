<%@ page language="java" contentType="text/xml; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" 
           uri="http://java.sun.com/jsp/jstl/core" %>
<!-- xml文件，用于ajax返回二季分类名称、Id等信息 -->
<SecondFoodMaterialCategory>
	<c:forEach items="${secondFmcList }" var="Sfmc">
	<foodMaterialCategory>
		<name>${Sfmc.fmCategoryName }</name>
		<id>${Sfmc.fmCategoryId }</id>
	</foodMaterialCategory>
	</c:forEach>
</SecondFoodMaterialCategory>
<%@ page language="java" contentType="text/xml; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" 
           uri="http://java.sun.com/jsp/jstl/core" %>
<foodMaterials>

	<c:forEach items="${pageBean.list }" var="fm">
	<foodMaterial>
		<name>${fm.fName }</name>
		<calorie>${fm.calorie }</calorie>
		<id>${fm.fId }</id>
	</foodMaterial>
	</c:forEach>

	<page>
		<currentPage>${pageBean.currentPage }</currentPage>
		<nextPage>${pageBean.currentPage + 1}</nextPage>
		<totalCount>${pageBean.totalCount }</totalCount>
	</page>
	<categoryId>${id }</categoryId>


</foodMaterials>    
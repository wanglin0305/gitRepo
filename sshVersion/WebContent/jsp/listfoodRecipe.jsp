<%@ page language="java" contentType="text/xml; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" 
           uri="http://java.sun.com/jsp/jstl/core" %>
<recipes>
	<c:forEach items="${rlist }" var="r">
	<recipe>
		<name>${r.rName }</name>
		<c:forEach items="${r.fmCountMap}" var="item">
		<foodMaterial>
			<foodMaterialName>"${item.key}"</foodMaterialName>
			<foodMaterialValue>"${item.value}"</foodMaterialValue>
		</foodMaterial>
		</c:forEach> 
		<steps>${r.rSteps }</steps>
	</recipe>
	</c:forEach>
</recipes>   
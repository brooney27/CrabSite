<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<jsp:include page="bootstrap.jsp"/>
<title>Results</title>
</head>
<body>
<table class="table table-bordered table-striped table-hover">
<tr>
<c:forEach var="h" items="${sqlheader}">
<td><c:out value="${h}"/></td>
</c:forEach>
</tr>
<c:forEach begin="0" end="${rows-1}" var="i">
<tr>
<c:forEach begin="${i*columns}" end="${(i+1)*columns-1}" var="j">
<td>
<c:out value="${data[j]}"/>
</td>
</c:forEach>
</tr>
</c:forEach>
</table>
</body>
</html>
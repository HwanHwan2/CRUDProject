<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file = "/WEB-INF/setting.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인</title>
</head>
<body>
	<form:form modelAttribute = "user" method = "post" name = "login" action = "login.do">
		<form:input path = "id" name = "id"/>
		<form:input path = "pw" name = "pw" type = "password"/>
		<button type = "submit" class = "btn btn-primary">로그인</button>
	</form:form>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file = "/WEB-INF/setting.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
	.search_form{
		margin-left:600px;
		margin-right:600px;
		height:700px;
		text-align:center;
		position:relative;
		top:250px;
	}
	.search_header{
		text-align:center;
	}
	.id_form, .name_form, .email_form{
  		border-bottom:2px solid #adadad;
  		margin:30px;
  		padding:10px 10px;
	}
	.id, .name, .email{
  		width:100%;
  		border:none;
  		outline:none;
  		color:#636e72;
  		font-size:16px;
  		height:25px;
	}
</style>
</head>
<body>
	<div class = "search_form">
		<div class = "search_header">
			<h3>
				<font style = "font-size:35px;">비</font>밀번호 찾기
			</h3>
		</div>
		
		<form:form modelAttribute = "user" method = "post" action = "pwsearch.do">
			<spring:hasBindErrors name = "user">
				<font color = "red">
					<c:forEach items = "${errors.globalErrors}" var = "error">
						<spring:message code = "${error.code}"/>
					</c:forEach>
				</font>
			</spring:hasBindErrors>
			<div class = "id_form">
				<form:input path = "id" class = "id" placeholder = "아이디"/><br>
			</div>
			<div class = "name_form">
				<form:input path = "name" class = "name" placeholder = "이름"/><br>
			</div>
			<div class = "email_form">
				<form:input path = "email" class = "email" placeholder = "aaa@bbb.com"/> 
			</div>
			
			<button type = "submit" class = "btn btn-success" style = "width:42%">비밀번호찾기</button>
			<button type = "button" class = "btn btn-primary" style = "width:42%" onclick = "location.href = 'login.do'">돌아가기</button>
		</form:form>
		
		
	</div>
</body>
</html>
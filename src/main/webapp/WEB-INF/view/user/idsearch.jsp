<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file = "/WEB-INF/setting.jsp" %>
<!DOCTYPE html>
<html>
<head>
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
	.name_form{
  		border-bottom:2px solid #adadad;
  		margin:30px;
  		padding:10px 10px;
	}
	.birth_form{
  		border-bottom:2px solid #adadad;
  		margin:30px;
  		padding:10px 10px;
	}
	.name {
  		width:100%;
  		border:none;
  		outline:none;
  		color:#636e72;
  		font-size:16px;
  		height:25px;
  		background-color:#f1f1f1;
	}
	.birth{
		width:100%;
  		border:none;
  		outline:none;
  		color: #636e72;
  		font-size:16px;
  		height:25px;
  		background-color:#f1f1f1;
	}
</style>
<meta charset="UTF-8">
<title>아이디/비밀번호 찾기</title>
</head>


<body>
	<div class = "search_form">
		<div class = "search_header">
			<h3>
				<font style = "font-size:35px;">아</font>이디 찾기
			</h3>
		</div>
		
		<form:form modelAttribute = "user" method = "post" action = "idsearch.do">
			<spring:hasBindErrors name = "user">
				<font color = "red">
					<c:forEach items = "${errors.globalErrors}" var = "error">
						<spring:message code = "${error.code}"/>
					</c:forEach>
				</font>
			</spring:hasBindErrors>
			<div class = "name_form">
				<form:input path = "name" class = "name" placeholder = "이름"/><br>
			</div>
			<div class = "birth_form">
				<form:input path = "birth" class = "birth" type = "date"/> 
			</div>
	
			<button type = "submit" class = "btn btn-success" style = "width:42%">아이디찾기</button>
			<button class = "btn btn-primary" style = "width:42%" onclick = "location.href = 'pwsearch.do'">비밀번호찾기</button>
		</form:form>
		
		
		
	</div>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file = "/WEB-INF/setting.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>아이디 찾기</title>
<style>
	
	.login_form{
		margin-left:600px;
		margin-right:600px;
		height:700px;
		text-align:center;
		position:relative;
		top:250px;
	}
	.login_header{
		text-align:center;
	}
	.id_form{
  		border-bottom:2px solid #adadad;
  		margin:30px;
  		padding:10px 10px;
	}
	.pw_form{
  		border-bottom:2px solid #adadad;
  		margin:30px;
  		padding:10px 10px;
	}
	.id {
  		width:100%;
  		border:none;
  		outline:none;
  		color:#636e72;
  		font-size:16px;
  		height:25px;
  		background-color:#f1f1f1;
	}
	.pw{
		width:100%;
  		border:none;
  		outline:none;
  		color: #636e72;
  		font-size:16px;
  		height:25px;
  		background-color:#f1f1f1;
	}

</style>
</head>
<body>
	<div class = "login_form">
		<div class = "login_header">
			아이디는 ${id} 입니다.
		</div>
		<a href = "login.do">로그인하기</a>
	</div>
</body>
</html>
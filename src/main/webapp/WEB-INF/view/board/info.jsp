<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file = "/WEB-INF/setting.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시물 보기</title>
<style>
	.info_form{
		margin-left:200px;
		margin-right:200px;
		height:700px;
		position:relative;
		top:100px;
		border:1px solid #ebecef;
	}
	.info_title{
		height:10%;
		border:1px solid #ebecef;
		
		font-size:25px;
		text-align:left;
	}
	.info_content{
		height:80%;
		
		text-align:left;
		font-size:25px;
		
		border:1px solid #ebecef;
	}
</style>
</head>
<body>

	<div class = "info_form">
		<div class = "info_header">
			<h5 style = "text-align:left; width:30%;">
				<font style = "font-size:35px;">자</font>유 게시판
			</h5>
		</div>
		<div class = "info_main">
			<div class = "info_title">
				${info.title}
			</div>
			<hr>
			<div class = "info_content">
				${info.content}
			</div>
		</div>
	</div>
</body>
</html>
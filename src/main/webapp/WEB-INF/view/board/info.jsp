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
		padding:20px 20px 0;
		border:1px solid #ebecef;
		border:radius:6px; <!-- 둥글게 -->
	}
	.info_title{
		margin-bottom:20px;
		border-bottom : 1px solid #eee;
		height:13%;
		font-size:30px;
		text-align:left;
	}
	#userinfo{
		font-size:13px;
	}
	.info_content{
		height:80%;
		text-align:left;
		font-size:25px;
	}
</style>
</head>
<body>
	
	<div class = "info_form">
	<a href = "list.do" style = "font-color:green; font-size:12px;">-자유게시판-</a>
		<div class = "info_title">
			<span class = "title1">
				<b>${info.title}</b>
			</span>
			<div class = "title2">
				<table id = "userinfo">
					<tr>
						<td rowspan="2" style = "width:30px; float:center;"><span class = "glyphicon glyphicon-user"></span></td>
						<td><b>${info.nickname}</b></td>
					</tr>
					<tr>
						<td><fmt:formatDate value = "${info.b_date}" pattern = "yyyy-MM-dd HH:mm:ss"/></td>
					</tr>
				</table>
					<button class = "btn btn-success" style = "float:right; position:relative; top:-33px;" onclick = "location.href='list.do'">목록</button>
					<c:if test = "${sessionScope.login.nickname == info.nickname}">
						<button class = "btn btn-primary" style = "float:right; margin-right:10px; position:relative; top:-33px;">수정</button>
						<button class = "btn btn-danger" style = "float:right; margin-right:10px; position:relative; top:-33px;">삭제</button>
					</c:if>
			</div>
			
		</div>
		<div class = "info_content">
			<div class = "info_content">
				${info.content}
			</div>
		</div>
	</div>
</body>
</html>
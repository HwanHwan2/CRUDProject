<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file = "/WEB-INF/setting.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판 리스트</title>
<style>
	.list_form{
		margin-left:200px;
		margin-right:200px;
		height:700px;
		position:relative;
		top:100px;
	}
</style>
</head>
<body>
<body>
	<div class = "list_form">
		<div class = "list_header">
			<span class = "header1" style = "font-size:25px;">
				<font style = "font-size:35px;">자</font>유 게시판
			</span>
			<span>
			<c:if test="${!empty sessionScope.login.id}">
				<button class = "btn btn-primary" onclick = "location.href = 'write.do'" style = "float:right;">글쓰기</button>
			</c:if>
			</span>
		</div>
		<table class = "table table-hover" id = "main_table">
			<tr><th style = "width:10%;">번호</th><th style = "width:50%;">제목</th><th style = "width:20%;">작성자</th><th style = "width:20%;">작성일</th></tr>	
			<c:forEach var = "board" items = "${list}">
				<tr>
					<td>${board.no}</td>
					<td><a href = "info.do?type=1&no=${board.no}">${board.title}</a></td>
					<td>${board.nickname}</td>
					<td>
						<fmt:formatDate value = "${board.b_date}" pattern = "yyyyMMdd" var = "date"/>
						<c:if test = "${today == date}">
							<fmt:formatDate value = "${board.b_date}" pattern = "HH:ss"/>
						</c:if>
						<c:if test = "${today != date}">
							<fmt:formatDate value = "${board.b_date}" pattern = "yy-MM-dd" />
						</c:if>
					</td>
				</tr>
			</c:forEach>
		</table>
		
	</div>


</body>
</html>
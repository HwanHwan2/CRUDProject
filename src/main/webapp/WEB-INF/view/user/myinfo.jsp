<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file = "/WEB-INF/setting.jsp" %>
<c:set var="path" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>내 정보</title>
<style>
	.info_form{
		margin-left:200px;
		margin-right:200px;
		position:relative;
		top:100px;
	}
	.info_header1 .info_header2{
		border-bottom:1px solid #efefef;
		margin-bottom:10px;
	}
	.info1{
		margin-bottom:100px;
	}
	.info2{
		margin-bottom:200px;
	}
</style>
</head>
<body>
	<div class = "info_form">
		<div class = "info_header1">
			<span class = "header1" style = "font-size:25px;">
				<font style = "font-size:40px;">내</font> 정보
			</span>
		</div>
		<div class = "info1"> <!-- 내 정보 -->
			<table class = "table">
				<tr>
					<td style = "width:20%;height:15%;">아이디</td><td>user1</td>	
				</tr>
				<tr>
					<td>비밀번호</td><td><a href=  "#"><u>비밀번호변경</u></a>
				</tr>
				<tr>
					<td>닉네임</td><td>유저1</td>
				</tr>
				<tr>
					<td>이름</td><td>유저일</td>
				</tr>
				<tr>
					<td>생년월일</td><td>1991-01-01</td>
				</tr>
				<tr>
					<td>이메일</td><td>user1@naver.com</td>
				</tr>
			</table>
			<button class = "btn btn-danger">회원탈퇴</button>
		</div>
		<div class = "info_header2">
			<span class = "header2" style = "font-size:25px;">
				<font style = "font-size:40px;">내</font> 게시물
			</span>
		</div>
		<div class = "info2"> <!-- 내 게시물 -->
			<table class = "table table-hover" id = "main_table">
			<tr>
				<th style = "width:14%;">종류</th>
				<th style = "width:46%;">제목</th>
				<th style = "width:20%;">작성일</th>
				<th style = "width:10%;">조회수</th>
				<th style = "width:10%;">댓글수</th>
			</tr>	
			<c:forEach var = "board" items = "${myBoardList}">
				<tr>
					<td>
						<c:if test = "${board.type == 1}">
							자유게시판
						</c:if>
					</td>
					<td><a href = "${path}/board/info.do?type=${board.type}&no=${board.no}">${board.title}</a></td>
					<td>
						<fmt:formatDate value = "${board.b_date}" pattern = "yyyyMMdd" var = "date"/>
						<c:if test = "${today == date}">
							<fmt:formatDate value = "${board.b_date}" pattern = "HH:ss"/>
						</c:if>
						<c:if test = "${today != date}">
							<fmt:formatDate value = "${board.b_date}" pattern = "yy-MM-dd" />
						</c:if>
					</td>
					<td>${board.hits}</td>
					<td>${board.nickname}</td>
				</tr>
			</c:forEach>
			</table>
		</div>
	</div>
</body>

</html>
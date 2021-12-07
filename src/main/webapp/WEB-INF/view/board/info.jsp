<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file = "/WEB-INF/setting.jsp" %>
<c:set var="path" value="${pageContext.request.contextPath}"/>
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
	.comment_count{
		position:relative;
		left:210px;
		top:150px;
		font-size:20px;
	}
	.comment_form{
		margin-left:200px;
		margin-right:200px;
		border:1px solid #ebecef;
		position:relative;
		top:150px;
		height:500px;
	}
	#input_comment{
		width:
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
						<button type = "button" class = "btn btn-primary" style = "float:right; margin-right:10px; position:relative; top:-33px;" onclick = "location.href='update.do?type=${info.type}&no=${info.no}'">
							수정
						</button>
						<form:form modelAttribute="board" action="delete.do" method="post">
							<form:hidden path = "type" value = "${info.type}"/>
							<form:hidden path = "no" value = "${info.no}" />
							<button type = "submit" class = "btn btn-danger"
								style = "float:right; margin-right:10px; position:relative; top:-33px;" onclick = "return deleteCheck();">
								삭제
							</button>
						</form:form>
					</c:if>
			</div>
			
		</div>
		<div class = "info_content">
			<div class = "info_content">
				${info.content}
			</div>
		</div>
	</div>
		
	<span class = "comment_count"><b>댓글 ${commentCount}</b></span>
	<div class = "comment_form">
		<textarea class = "form-control" id = "input_comment" placeholder = "내용을 입력해주세요."></textarea>
		<c:forEach var = "comment" items = "${comment}">
			<div class = "comment_content">
				작성자 : ${comment.nickname}
				내용 : ${comment.content}
			</div>
		</c:forEach>
	</div>
	
	<script>
		function deleteCheck(){
			if(confirm("삭제하시겠습니까?")){
				return true;
			} else{
				return false;
			}
		}
	</script>
</body>
</html>
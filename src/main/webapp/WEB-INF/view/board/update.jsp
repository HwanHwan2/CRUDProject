<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file = "/WEB-INF/setting.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시물 수정하기</title>
<style>
	.write_form{
		margin-left:200px;
		margin-right:200px;
		position:relative;
		top:100px;
		padding-bottom: 10px;
	}
	.write_header{
		margin-bottom:20px;
		border-bottom : 1px solid #eee; <!-- 밑줄 -->
	}
	#input_title{
		width:100%;
		height:50px;
		font-size:23px;
	}
	.ck.ck-editor {
    	max-width: 100%;
	}
	.ck-editor__editable {
		font-size:17px;
	    min-height: 500px;
	}
</style>
</head>
<body>
	<div class = "write_form">
		<form:form modelAttribute = "board" action = "update.do" method = "post">
			<form:hidden path = "nickname" value = "${sessionScope.login.nickname}"/>
			<form:hidden path = "type" value = "1"/>
			<form:hidden path = "no" value = "${info.no}"/>
			<div class = "write_header">
				<span class = "header1" style = "font-size:25px;">
					<font style = "font-size:35px;">게</font>시물 수정
				</span>
				<spring:hasBindErrors name = "board">
					<font color = "red" style = "float:middle;">
						<c:forEach items = "${errors.globalErrors}" var = "error">
							<spring:message code = "${error.code}"/>
						</c:forEach>
					</font>
				</spring:hasBindErrors>
				<button type = "submit" class = "btn btn-success" style = "float:right; ">수정</button>
				<button type = "button" class = "btn btn-primary" onclick = "location.href = 'info.do?type=${info.type}&no=${info.no}'" style = "float:right; margin-right:10px;">돌아가기</button>
			</div>
			<div class = "title_form">
				<form:input path = "title" class="form-control" id = "input_title" placeholder = "제목을 입력해주세요." value = "${info.title}"/>
			</div>
			<br>
			<div class = "content_form">
				<form:textarea path="content" class = "form-control" id = "input_content" placeholder = "내용을 입력해주세요." row="15" cols="80"/>
			</div>
			첨부파일 <input type = "file" name = "files" id = "files" onchange = "file();">
		</form:form>
	</div>
	
<script src="https://cdn.ckeditor.com/ckeditor5/31.0.0/classic/ckeditor.js"></script>
<script>
	ClassicEditor
		.create(document.querySelector('#input_content'))
		.then( editor => {
			editor.setData('${info.content}');
		})
		.catch(error =>{
			console.error(error);
		});
</script>
<script>
	function file(){
		var realFileName = ${info.realFileName};
		document.getElementById('files').value = realFileName;
	}
</script>
</body>
</html>
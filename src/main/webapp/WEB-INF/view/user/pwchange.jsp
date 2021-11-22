<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file = "/WEB-INF/setting.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>비밀번호 변경</title>
<style>
	.pwchange_form{
		margin-left:600px;
		margin-right:600px;
		height:700px;
		text-align:center;
		position:relative;
		top:250px;
	}
	.pwchange_header{
		text-align:center;
	}
	.pw1_form, .pw2_form{
  		border-bottom:2px solid #adadad;
  		margin:30px;
  		padding:10px 10px;
	}
	#pw, #pw2{
  		width:100%;
  		border:none;
  		outline:none;
  		color:#636e72;
  		font-size:16px;
  		height:25px;
  		background-color:#f1f1f1;
	}
</style>
</head>
<body>

	<div class = "pwchange_form">
		<div class = "pwchange_header">
			<h3>
				<font style = "font-size:35px;">비</font>밀번호 변경
			</h3>
		</div>
		
		<form:form modelAttribute = "user" method = "post" action = "pwchange.do" onsubmit = "return pwCheck();">
			<form:hidden path = "id" value = "${id}"/>
			<spring:hasBindErrors name = "user">
				<font color = "red">
					<c:forEach items = "${errors.globalErrors}" var = "error">
						<spring:message code = "${error.code}"/>
					</c:forEach>
				</font>
			</spring:hasBindErrors>
			<div class = "pw1_form">
				<form:input path = "pw" id = "pw" placeholder = "비밀번호입력"/><br>
			</div>
			<div class = "pw2_form">
				<form:input path = "pw" id = "pw2" placeholder = "비밀번호재입력"/><br>
			</div>
			
			<button type = "submit" class = "btn btn-success" style = "width:42%">비밀번호변경</button>
			<button class = "btn btn-primary" style = "width:42%" onclick = "location.herf = 'login.do'">돌아가기</button>
		</form:form>
	</div>
<script>
	function pwCheck(){
		var pw1 = document.getElementById("pw").value;
		var pw2 = document.getElementById("pw2").value;

		if(pw1 != pw2) {
			alert("비밀번호가 일치하지 않습니다.");
			return false;
		} else {
			return true;
		}
	}
</script>
</body>
</html>
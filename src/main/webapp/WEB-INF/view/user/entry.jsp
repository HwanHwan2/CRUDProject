<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file = "/WEB-INF/setting.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입</title>
</head>
<body>
	<!-- 회원가입 Header -->
	<div class="page-header w3-center" style = "padding-top:100px;">
		<div class="center-block">
			<h3>
				<i class="fa fa-user"></i>
				<font style="font-size: 35px;">회</font>원가입
			</h3>
			<hr>
			<div class="check_font" id="AllCheck"></div>
		</div>
	</div>
	
	<form:form modelAttribute = "user" method = "post" name = "entry" action = "entry.do" onsubmit = "return Check();">
		<div class = "container w3-center" style = "width:600px; height:600px;">
		
			 <!-- 에러 체크 -->
			<div class="check_font" id="AllCheck"></div>
			
			<!-- id, pw, name, img -->
			<div class = "content" style = "height:50%; border: 1px solid gold;"> <!-- border 제거하기 -->
				<div class = "content" style = "float:left; width:50%; text-align:left;">
					<div class = "form-group">
						<label class="label_st" for="id">아이디</label> <form:input path = "id" name = "id"/><br>
					</div>
					<div class = "form-group">
						<label class="label_st" for="pw">비밀번호</label> <form:input path = "pw" name = "pw" type = "password"/><br>
					</div>
					<div class = "form-group">
						<label class="label_st" for="name">이름</label> <form:input path = "name" name = "name"/><br>
					</div>
					<div class = "form-group">
						<label class="label_st" for="nickname">닉네임</label> <form:input path = "nickname" name = "nickname"/><br>
					</div>
					<div class = "form-group">
						<label class="label_st" for="birth">생년월일</label> <form:input path = "birth" name = "birth" placeholder = "0000-00-00"/><br>
					</div>
					<div class = "form-group">
						<label class="label_st" for="email">이메일</label> <form:input path = "email" name = "email"/><br>
					</div>
				</div>
				<button type = "submit" class = "btn btn-primary">회원가입</button>
			</div>
			
			 <!-- nickname, birth, email -->
<!--		<div id = "content">
				<label class="label_st" for="nickname">닉네임</label> <form:input path = "nickname" name = "nickname"/>
				<label class="label_st" for="birth">생년월일</label> <form:input path = "birth" name = "birth" placeholder = "0000-00-00"/>
				<label class="label_st" for="email">이메일</label> <form:input path = "email" name = "email"/>
				<input type = "submit" value = "전송">
			</div> -->
		</div>
	</form:form>
<script type = "text/javascript">
	function Check(){
		const id = document.getElementById("id");
		const pw = f.pw.value();
		const name = f.name.value();
		const nickname = f.nickname.value();
		const birth = f.birth.value();
		const email = f.email.value();
	
		if(id == "" || pw == "" || name == "" || nickname == "" || birth == "" || email == "" ){
			$("#AllCheck").text("오류있음");
			return false;
		}
	}
</script>
</body>
</html>
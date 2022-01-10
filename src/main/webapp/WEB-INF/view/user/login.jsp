<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file = "/WEB-INF/setting.jsp" %>
<c:set var="path" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인</title>
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
  		background-color:white;
	}
	.pw{
		width:100%;
  		border:none;
  		outline:none;
  		color: #636e72;
  		font-size:16px;
  		height:25px;
  		background-color:#white;
	}
	input[type=password] {
		font-family:'Malgun gothic', dotum, sans-serif;
	}
	::placeholder{
		font-family: 'OTWelcomeRA';
	}

</style>
</head>
<body>
	<div class = "login_form">
		<div class = "login_header">
			<h3>
				<font style = "font-size:35px;">로</font>그인
			</h3>
		</div>
		<form:form modelAttribute = "user" method = "post" name = "login" action = "login.do" onsubmit = "return LoginCheck();">
			<spring:hasBindErrors name = "user">
				<font color = "red">
					<c:forEach items = "${errors.globalErrors}" var = "error">
						<spring:message code = "${error.code}"/>
					</c:forEach>
				</font>
			</spring:hasBindErrors>
			<div class = "id_form">
				<form:input path = "id" class = "id" placeholder = "ID"/><br>
			</div>
			<div class = "pw_form">
				<form:input path = "pw" class = "pw" type = "password" placeholder = "PW"/> 
			</div>
			
			<button type = "submit" class = "btn btn-primary" style = "width:85%">로그인</button>
		</form:form>
		<a href = "idsearch.do">아이디찾기</a> | 
		<a href = "pwsearch.do">비밀번호찾기</a> | 
		<a data-toggle = "modal" href = "#entryModal">회원가입</a>
	</div>
	
	<!-- Modal 회원가입 -->
	<div class="modal fade" id="entryModal" role="dialog">
    	<div class="modal-dialog">
    
      	<!-- Modal content-->
      	<form:form modelAttribute = "user" action = "entry.do" method = "post" name = "entry" onsubmit = "return entryCheck();">
      	<div class="modal-content">
        	<div class="modal-header">
        		<h3>
					<i class="fa fa-user"></i>
					<font style="font-size: 35px;">회</font>원가입
				</h3>
        	</div>
        	<div class="modal-body">
          		<div class = "form-group">
          			<label for = "id">아이디</label><label id = "errorId">&nbsp;&nbsp;</label>
          			<form:input path = "id" id = "entryId" class = "form-control" placeholder = "아이디"/>
          		</div>
          		<div class = "form-group">
          			<label for = "pw">비밀번호</label><label id = "errorPw">&nbsp;&nbsp;</label>
          			<form:input path = "pw" id = "entryPw" class = "form-control" type = "password" placeholder = "****"/>
          		</div>
          		<div class = "form-group">
          			<label for = "name">이름</label><label id = "errorName">&nbsp;&nbsp;</label>
          			<form:input path = "name" id = "entryName" class = "form-control"/>
          		</div>
          		<div class = "form-group">
          			<label for = "nickname">닉네임</label><label id = "errorNickname">&nbsp;&nbsp;</label>
          			<form:input path = "nickname" id = "entryNickname" class = "form-control" placeholder = "미입력 시 이름으로 대체됩니다."/>
          		</div>
          		<div class = "form-group">
          			<label for = "birth">생년월일</label><label id = "errorBirth">&nbsp;&nbsp;</label>
          			<form:input path = "birth" id = "entryBirth" class = "form-control" type = "date"/>
          		</div>
          		<div class = "form-group">
          			<label for = "email">이메일</label><label id = "errorEmail">&nbsp;&nbsp;</label>
          			<form:input path = "email" id = "entryEmail" class = "form-control"/>
          		</div>
        	</div>
       		<div class="modal-footer">
       			<input type="submit" class="btn btn-success" id = "entryButton" value = "회원가입">
          		<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
        	</div>
      	</div>
      	</form:form>
    	</div>
  	</div>
  	
<script>

function entryCheck(){
	var font1 = '<font color = "red">&nbsp;&nbsp;';
	var font2 = '</font>';
	
	var pw = entry.entryPw.value;
	var name = entry.entryName.value;
	var birth = entry.entryBirth.value;
	var email = entry.entryEmail.value;
	
	if(id == "" || pw == "" || name == "" || birth == "" || email == ""){
		alert("입력 정보에 문제가 있습니다.");
	}
	
	entryIdNicknameCheck();
	if(pw == ""){
		$("#errorPw").html(font1 + "비밀번호를 입력해주세요." + font2);
	} else $("#errorPw").html("");
	if(name == ""){
		$("#errorName").html(font1 + "이름을 입력해주세요." + font2);
	} else $("#errorName").html("");
	if(birth == ""){
		$("#errorBirth").html(font1 + "생년월일을 입력해주세요." + font2);
	} else $("#errorBirth").html("");
	if(email == ""){
		$("#errorEmail").html(font1 + "이메일을 입력해주세요." + font2);
		return false;
	} else $("#errorEmail").html("");
	
	
}

function entryIdNicknameCheck(){
	var font1 = '<font color = "red">&nbsp;&nbsp;';
	var font2 = '</font>';
	var id = entry.entryId.value;
	var nickname = entry.entryNickname.value;
	
	<!-- 아이디체크 ajax -->
	$.ajax({
		url : '${path}/user/entryIdCheck.do?id='+id,
		type : "post",
		success : function(data) {
			if(data == 1) {
				$("#errorId").html(font1 + "중복된 아이디입니다." + font2);
				return false;
			} else if(id == ""){
				$("#errorId").html(font1 + "아이디를 입력해주세요." + font2);
				return false;
			} else $("#errorId").html("");
		}, error : function(){}
	});
	
	<!-- 닉네임체크 ajax -->
	$.ajax({
		url : '${path}/user/entryNicknameCheck.do?nickname='+nickname,
		type : "post",
		success : function(data){
			if(data == 1) {
				$("#errorNickname").html(font1 + "중복된 닉네임입니다." + font2);
				return false;
			} else if(nickname == ""){
				$("#errorNickname").html(font1 + "닉네임을 입력해주세요." + font2);
				return false;
			} else $("#errorNickname").html("");
		}, error : function(){}
	});
}

</script>
</body>
</html>
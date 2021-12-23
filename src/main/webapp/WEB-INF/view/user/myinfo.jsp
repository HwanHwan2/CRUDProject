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
	
	input[type=password] {
		font-family:'Malgun gothic', dotum, sans-serif;
	}
	::placeholder{
		font-family: 'OTWelcomeRA';
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
					<td style = "width:20%;height:15%;">아이디</td><td>${sessionScope.login.id}</td>	
				</tr>
				<tr>
					<td>비밀번호</td><td><a data-toggle = "modal" href = "#changePwModal"><u>비밀번호변경</u></a>
				</tr>
				<tr>
					<td>닉네임</td><td>${sessionScope.login.nickname}</td>
				</tr>
				<tr>
					<td>이름</td><td>${sessionScope.login.name}</td>
				</tr>
				<tr>
					<td>생년월일</td><td>${sessionScope.login.birth}</td>
				</tr>
				<tr>
					<td>이메일</td><td>${sessionScope.login.email}</td>
				</tr>
			</table>
			<button type="button" class="btn btn-danger" data-toggle="modal" data-target="#dropModal">회원탈퇴</button>
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
					<td><a href = "${path}/board/info.do?type=${board.type}&no=${board.no}">${board.title}</a>
						<c:if test = "${board.realFileName != null}">
							<span class="glyphicon glyphicon-paperclip" aria-hidden="true" style = "width:15px; height:15px;"></span>
						</c:if></td>
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
					<td>${board.commentCnt}</td>
				</tr>
			</c:forEach>
			</table>
			<c:if test = "${empty myBoardList}">
				<b style = "position:relative; left:42%; font-size:20px;">게시물이 없습니다.</b>
			</c:if>
		</div>
	</div>
	
	<!-- 회원탈퇴 Modal -->
	<div class="modal fade" id="dropModal" role="dialog">
    	<div class="modal-dialog">
    
      	<!-- Modal content-->
      	<form:form modelAttribute = "user" id = "drop" action = "drop.do" method = "post" name = "drop" onsubmit = "return dropCheck();">
      	<input type = "hidden" name = "id" value = "${sessionScope.login.id}">
      	<div class="modal-content">
        	<div class="modal-header">
        		<h3>
					<i class="fa fa-user"></i>
					<font style="font-size: 35px;">회</font>원탈퇴
				</h3>
        	</div>
        	<div class="modal-body">
          		<div class = "form-group">
          			<label for = "id">비밀번호</label><label id = "errorPw">&nbsp;&nbsp;</label>
          			<input type = "password" class = "form-control" id = "pw" name = "pw" placeholder = "****">
          		</div>
          		<div class = "form-group">
          			<label for = "pw">비밀번호 재입력</label><label id = "errorPw2">&nbsp;&nbsp;</label>
          			<input type = "password" class = "form-control" name = "pw2" placeholder = "****">
          		</div>
          		<div class = "form-group">
          			<label for = "name">회원탈퇴를 원하실 경우 "회원탈퇴"를 입력해주세요</label><label id = "errorName">&nbsp;&nbsp;</label>
					<input type = "text" class = "form-control" name = "drop">
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
  	
  	<!-- 회원탈퇴 Modal -->
	<div class="modal fade" id="changePwModal" role="dialog">
    	<div class="modal-dialog">
    
      	<!-- Modal content-->
      	<form:form modelAttribute = "user" id = "changePw" action = "changePw.do" method = "post" name = "changePw" onsubmit = "return changePwCheck();">
      	<input type = "hidden" name = "id" value = "${sessionScope.login.id}">
      	<div class="modal-content">
        	<div class="modal-header">
        		<h3>
					<i class="fa fa-user"></i>
					<font style="font-size: 35px;">비</font>밀번호변경
				</h3>
        	</div>
        	<div class="modal-body">
          		<div class = "form-group">
          			<label for = "oldPw">현재 비밀번호</label><label id = "pw1">&nbsp;&nbsp;</label>
          			<input type = "password" class = "form-control" id = "oldPw" name = "oldPw" placeholder = "현재 비밀번호">
          		</div>
          		<div class = "form-group">
          			<label for = "newPw1">새 비밀번호</label><label id = "newPw1">&nbsp;&nbsp;</label>
          			<input type = "password" class = "form-control" name = "newPw1" placeholder = "새 비밀번호">
          		</div>
          		<div class = "form-group">
          			<label for = "newPw2">새 비밀번호 재입력</label><label id = "newPw2">&nbsp;&nbsp;</label>
          			<input type = "password" class = "form-control" name = "newPw2" placeholder = "새 비밀번호 재입력">
          		</div>		
        	</div>
       		<div class="modal-footer">
       			<input type="submit" class="btn btn-success" id = "changPwButton" value = "변경하기">
          		<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
        	</div>
      	</div>
      	</form:form>
    	</div>
  	</div>
	
<script>
	function dropCheck(){
		var pw1 = drop.pw.value;
		var pw2 = drop.pw2.value;
		var text = drop.drop.value;
		var id = '${sessionScope.login.id}';
		
		if(pw1 == pw2){
			if(text == "회원탈퇴"){
				$.ajax({
					url : '${path}/user/PwCheck.do?id='+id+'&pw='+pw1,
					type : "post",
					success : function(data){
						if(data == 1) {
							return ture;
						} else{
							alert("회원 비밀번호가 일치하지 않습니다.");
							return false;
						}
					}, error : function(){
						alert("ajax 에러 발생");
					}
				});
			} else {
				alert("회원탈퇴를 정확히 입력해주세요.");
				return false;
			}
		} else {
			alert("비밀번호가 일치하지 않습니다.");
		}
	}
	
	function changePwCheck(){
		var oldPw = changePw.oldPw.value;
		var newPw1 = changePw.newPw1.value;
		var newPw2 = changePw.newPw2.value;
		var id = "${sessionScope.login.id}";
		let resultFlag = false;
		
		if(confirm("변경하시겠습니까?")){
			if(newPw1 == newPw2) {
				$.ajax({
					url : '${path}/user/PwCheck.do?id='+id+'&pw='+oldPw,
					type : 'post',
					async : false,
					success : function(data){
						alert(data);
						if(data == 1) {
							resultFlag = true;
						} else if(data == 0){
							alert("비밀번호가 일치하지 않습니다.");
							resultFlag = false;
						}
					}, error : function(){alert("ajax 에러");}
				});
			} else {
				alert("사용하실 비밀번호를 정확히 입력해주세요.");
				resultFlag = false;
			}
		} else {
			resultFlag = false;
		}
		return resultFlag;
	}
</script>
</body>
</html>
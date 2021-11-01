<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/setting.jsp"%>
<!DOCTYPE html>
<html>
<title>W3.CSS Template</title>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<link rel="stylesheet"
	href="https://fonts.googleapis.com/css?family=Raleway">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<style>
html, body, h1, h2, h3, h4, h5 {
	font-family: "Raleway", sans-serif
}

</style>
<body class="w3-light-grey">

	<!-- Top container -->
	<div class="w3-bar w3-top w3-black w3-large" style="z-index: 4">
		<button
			class="w3-bar-item w3-button w3-hide-large w3-hover-none w3-hover-text-light-grey"
			onclick="w3_open();">
			<i class="fa fa-bars"></i>Menu
		</button>
		<a href = "#"><span class="w3-bar-item w3-right">Login</span></a>
	</div>

	<!-- Sidebar/menu -->
	<nav class="w3-sidebar w3-collapse w3-white w3-animate-left" style="z-index: 3; width: 300px;" id="mySidebar">
		<br>
		<div class="w3-container w3-row">
			<c:choose>
				<c:when test = "${login eq null}">
					<form:form modelAttribute = "user" name = "login" method = "post" action = "login.do">
					<div class="input-box">
						<table>
							<tr>
								<th colspan = "2"><form:input path = "id" name = "id"/></th>
								<th colspan = "2" rowspan = "2"><input type = "submit" class = "btn btn-default" value = "로그인"></th>
							</tr>
							<tr>
								<th colspan = "2"><form:input path = "pw" name = "pw" placeholder = "pw"/></th>
							</tr>
						</table>
						<br>
						<a href = "#">아이디찾기</a>
						<a href = "#">비밀번호찾기</a>
						<a data-toggle = "modal" href = "#entryModal">회원가입</a>
					</div>
					</form:form>
				</c:when>
				<c:otherwise>
					<div class="w3-col s8 w3-bar">
						 <strong>${login.nickname}&nbsp;</strong><span>님, 환영합니다!</span><br> <a href="#" class="w3-bar-item w3-button"><i class="fa fa-envelope"></i></a>
						<a href="logout.do">로그아웃</a>
						<a href="#" class="w3-bar-item w3-button"><i class="fa fa-cog"></i></a>
					</div>
				</c:otherwise>
			</c:choose>
<!--		<div class="w3-col s8 w3-bar">
				<span>Welcome, <strong>Mike</strong></span><br> <a href="#"
					class="w3-bar-item w3-button"><i class="fa fa-envelope"></i></a> <a
					href="#" class="w3-bar-item w3-button"><i class="fa fa-user"></i></a>
				<a href="#" class="w3-bar-item w3-button"><i class="fa fa-cog"></i></a>
			</div> -->    <!-- 로그인 바 -->
		</div>
		<hr>
		<div class="w3-container">
			<h5>Dashboard</h5>
		</div>
		<div class="w3-bar-block">
			<a href="#"
				class="w3-bar-item w3-button w3-padding-16 w3-hide-large w3-dark-grey w3-hover-black"
				onclick="w3_close()" title="close menu"><i
				class="fa fa-remove fa-fw"></i>  Close Menu</a> <a href="#"
				class="w3-bar-item w3-button w3-padding w3-blue"><i
				class="fa fa-users fa-fw"></i>  Overview</a> <a href="#"
				class="w3-bar-item w3-button w3-padding"><i
				class="fa fa-eye fa-fw"></i>  Views</a> <a href="#"
				class="w3-bar-item w3-button w3-padding"><i
				class="fa fa-users fa-fw"></i>  Traffic</a> <a href="#"
				class="w3-bar-item w3-button w3-padding"><i
				class="fa fa-bullseye fa-fw"></i>  Geo</a> <a href="#"
				class="w3-bar-item w3-button w3-padding"><i
				class="fa fa-diamond fa-fw"></i>  Orders</a> <a href="#"
				class="w3-bar-item w3-button w3-padding"><i
				class="fa fa-bell fa-fw"></i>  News</a> <a href="#"
				class="w3-bar-item w3-button w3-padding"><i
				class="fa fa-bank fa-fw"></i>  General</a> <a href="#"
				class="w3-bar-item w3-button w3-padding"><i
				class="fa fa-history fa-fw"></i>  History</a> <a href="#"
				class="w3-bar-item w3-button w3-padding"><i
				class="fa fa-cog fa-fw"></i>  Settings</a><br> <br>
		</div>
	</nav>


	<!-- Overlay effect when opening sidebar on small screens -->
	<div class="w3-overlay w3-hide-large w3-animate-opacity"
		onclick="w3_close()" style="cursor: pointer" title="close side menu"
		id="myOverlay"></div>

	<!-- !PAGE CONTENT! -->
	<div class="w3-main" style="margin-left: 300px; margin-top: 43px;">
		<decorator:body/>
		
		<!-- Footer -->
		<footer class="w3-container w3-padding-16 w3-light-grey">
			<h4>FOOTER</h4>
			<p>
				Powered by <a href="https://www.w3schools.com/w3css/default.asp"
					target="_blank">w3.css</a>
			</p>
		</footer>
	</div>

		

		<!-- End page content -->
		
		
	<!-- Modal 회원가입 -->
	<div class="modal fade" id="entryModal" role="dialog">
    	<div class="modal-dialog">
    
      	<!-- Modal content-->
      	<form:form modelAttribute = "user" action = "entry.do" method = "post" onsubmit = "return AllChk();">
      	<div class="modal-content">
        	<div class="modal-header">
        		<h3>
					<i class="fa fa-user"></i>
					<font style="font-size: 35px;">회</font>원가입
				</h3>
        	</div>
        	<div class="modal-body">
          		<div class = "form-group">
          			<label for = "id">아이디</label>
          			<form:input path = "id" class = "form-control" name = "id" placeholder = "아이디"/>
          		</div>
          		<div class = "form-group">
          			<label for = "pw">비밀번호</label>
          			<form:input path = "pw" class = "form-control" name = "pw" type = "password" placeholder = "****" value = ""/>
          		</div>
          		<div class = "form-group">
          			<label for = "name">이름</label>
          			<form:input path = "name" class = "form-control" name = "name"/>
          		</div>
          		<div class = "form-group">
          			<label for = "nickname">닉네임</label>
          			<form:input path = "nickname" class = "form-control" name = "nickname"/>
          		</div>
          		<div class = "form-group">
          			<label for = "birth">생년월일</label>
          			<form:input path = "birth" class = "form-control" name = "birth" type = "date"/>
          		</div>
          		<div class = "form-group">
          			<label for = "email">이메일</label>
          			<form:input path = "email" class = "form-control" name = "email"/>
          		</div>
        	</div>
       		<div class="modal-footer">
       			<button type="submit" class="btn btn-success" id = "entry">회원가입</button>
          		<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
        	</div>
      	</div>
      	</form:form>
      	
    	</div>
  	</div>
	<script>
		// Get the Sidebar
		var mySidebar = document.getElementById("mySidebar");

		// Get the DIV with overlay effect
		var overlayBg = document.getElementById("myOverlay");

		// Toggle between showing and hiding the sidebar, and add overlay effect
		function w3_open() {
			if (mySidebar.style.display === 'block') {
				mySidebar.style.display = 'none';
				overlayBg.style.display = "none";
			} else {
				mySidebar.style.display = 'block';
				overlayBg.style.display = "block";
			}
		}

		// Close the sidebar with the close button
		function w3_close() {
			mySidebar.style.display = "none";
			overlayBg.style.display = "none";
		}
		
		//회원가입 에러
		function AllChk(){
			const id = document.getElementsByName("id")
			console.log(id);
			return false;
		}
	</script>

</body>
</html>

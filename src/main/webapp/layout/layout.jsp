<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/setting.jsp"%>
<c:set var="path" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<link rel="stylesheet"
	href="https://fonts.googleapis.com/css?family=Raleway">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<style>
@font-face {
    font-family: 'OTWelcomeRA';
    src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_2110@1.0/OTWelcomeRA.woff2') format('woff2');
    font-weight: normal;
    font-style: normal;
}
html, body, h1, h2, h3, h4, h5 {
	font-family: 'OTWelcomeRA';
}


</style>
<script src='//cdnjs.cloudflare.com/ajax/libs/toastr.js/latest/js/toastr.min.js'></script>
<link rel='stylesheet' href='//cdnjs.cloudflare.com/ajax/libs/toastr.js/latest/css/toastr.min.css'/>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css">
<script src="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/js/all.js"></script>

<head>
	<decorator:head/>
</head>
<body class="w3-light-white">

	<!-- Top container -->
	<div class="w3-bar w3-top w3-black w3-large" style="z-index: 4">
		<c:choose>
			<c:when test = "${login eq null}">
				<a href = "${path}/user/login.do"><span class="w3-bar-item w3-right">Login</span></a>
			</c:when>
			<c:otherwise>
				<a href = "${path}/user/logout.do"><span class = "w3-bar-item w3-right">Logout</span></a>
				<span class = "w3-bar-item w3-right">${login.nickname},&nbsp;Welcome!</span>
				<a href = "${path}/user/myinfo.do"><span class = "w3-bar-item w3-right">내 정보</span></a>
				<div class = "dropdown" style = "float:right;">
					 <a href="#" id="imageDropdown" data-toggle="dropdown">
					 	<img id = "alarmImg" src = "../img/alarm.png" style = "width:30px; height:30px; float:right; margin-top:3px;">
     				</a>
     				<ul class="dropdown-menu dropdown-menu-left" role="menu" aria-labelledby="imageDropdown" style = "border:1px solid black;">
        					<li role="presentation"><a role="menuitem" tabindex="-1" href="#">Menu item 1</a></li>
        					<li role="presentation"><a role="menuitem" tabindex="-1" href="#">Menu item 2</a></li>
        					<li role="presentation"><a role="menuitem" tabindex="-1" href="#">Menu item 3</a></li>
        					<li role="presentation" class="divider"></li>
        					<li role="presentation"><a role="menuitem" tabindex="-1" href="#">Menu item 4</a></li>
      				</ul>
				</div>
			</c:otherwise>
		</c:choose>
	</div>
<script> 
var tmpDate = new Date(); 
$("#alarmImg").attr("src", "../img/alarm.png?"+tmpDate.getTime()); 
</script>

	<!-- Sidebar/menu -->
	<nav class="w3-sidebar w3-collapse w3-white w3-animate-left w3-light-grey" style="z-index: 3; width: 300px;" id="mySidebar">
		<br>
		<!-- sidebar/login -->
		<hr>
		
		<div class="w3-container">
			<h5>Dashboard</h5>
		</div>
		<div class="w3-bar-block">
			
			<a href="#" class="w3-bar-item w3-button w3-padding-16 w3-hide-large w3-dark-grey w3-hover-black" onclick="w3_close()" title="close menu">
				<i class="fa fa-remove fa-fw"></i>  Close Menu</a>
			<a href="${path}/board/list.do" class="w3-bar-item w3-button w3-padding"><span class="glyphicon glyphicon-align-justify" aria-hidden="true"></span>     게시판</a>
			<c:if test = "${sessionScope.login != null}">
				<a href="${path}/user/myinfo.do" class="w3-bar-item w3-button w3-padding"><span class="glyphicon glyphicon-user" aria-hidden="true"></span>  내 정보</a>
			</c:if>
			<br><br>
		</div>
	</nav>
	<!-- Overlay effect when opening sidebar on small screens -->
	<div class="w3-overlay w3-hide-large w3-animate-opacity"
		onclick="w3_close()" style="cursor: pointer" title="close side menu"
		id="myOverlay"></div>

	<!-- !PAGE CONTENT! -->
	<div class="w3-main" style="margin-left: 300px; margin-top: 43px; height:auto;">
		<decorator:body/>
	</div>

	
	<div class="footer" style = "background-color : #d9edf7; margin-left: 300px; margin-top:100px; text-align:center; padding:5px;">
			<h5>CRUD</h5>
			<p>Powered by <a href="https://www.w3schools.com/w3css/default.asp" target="_blank">w3.css</a></p>
	</div>

		

		<!-- End page content -->
		
		

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
	</script>

<!-- WebSocket Login -->
<c:if test = "${login ne null }">
	<script>
		$(document).ready(function(){
			connect();
		})
		function connect(){
			ws = new WebSocket("ws://localhost:8080/CRUD/EchoHandler.do");
			ws.onopen = function(){
				console.log("${login.nickname} 접속 완료");
			}
			ws.onclose = function(){
				console.log("${login.nickname} 끊김.");
			}
			ws.onerror = function(event) {
				console.log(event);
			}
			ws.onmessage = function(event) {
				//알림이 왔을 때 알림 갯수 갱신하기.
				/*$.ajax({
					type : "get",
					url : "${path}/user/AlarmCnt.do?id=${login.id}",
					success : function(data) {
						if(data != null) {
							$(".badge").text(data);
						}
						else {
							$(".badge").text("0");
						}
					}
				}) */
				//토스트창 설정 후 보이기
				toastr.options = {
						"escapeHtml" : true,
						"closeButton" : true, // 닫기X버튼 활성화 여부
						"newestOnTop" : true, //새로운 토스트창이 나올 곳. 해보면 알듯
						"closeDuration" : 5000, //사라지는 시간. 밀리초
						"progressBar" : true, //모래시계처럼
				}
				let strs = event.data;
				let arr = strs.split(",");
				toastr.info(arr[1],arr[0],{timeOut : 5000});
			}
		}
	</script>
</c:if>
</body>
</html>

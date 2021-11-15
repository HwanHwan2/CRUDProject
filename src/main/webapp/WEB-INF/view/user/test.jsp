<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file = "/WEB-INF/setting.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>test</title>
</head>
<body>
	<input type = "text" id = "id" placeholder = "하이">
	<Button class = "btn btn-primary" id = "button">버튼</Button>
	
<script>
		
		$("#button").click(function(){
			var id = document.getElementById('id').value;
			console.log(id);
			if(id == "") {
				alert("아이디 안적음");
			} else {
				alert("아이디 적음 아이디 : " + id);
			}
		})
		
		
</script>
</body>

</html>
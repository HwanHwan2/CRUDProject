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
		position:relative;
		height:auto;
		top:100px;
		padding:20px 20px 0;
		border:1px solid #ebecef;
		border:radius:6px; <!-- 둥글게 -->
	}
	.info_title{
		margin-bottom:20px;
		border-bottom : 1px solid #eee;
		height:10%;
		font-size:30px;
		text-align:left;
	}
	#userinfo{
		font-size:13px;
	}
	.info_content{
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
		height:120px;
	}
	.comment_form{
		padding:10px;
		height:auto;
		margin-bottom:200px;
	}
	.comment_content{
		width:100%;
		height:auto;
		border-top : 1px solid #eee;
		padding:15px;
	}
	#comment_submit{
		width:9%;
		height:120px;
		float:right;
		position:relative;
		bottom:120px;
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
				${info.content}
			<c:if test = "${info.realFileName != null}">
			<div class = "content_files">
				<font style = "font-size:15px;">첨부파일 [<a href = "download.do?type=${info.type}&no=${info.no}">${info.realFileName}</a>]</font>
			</div>
			</c:if>
		</div>
	</div>
	
	<span class = "comment_count"><b>댓글 ${commentCount}</b></span>
	
	<div class = "comment_form">
		<div class = "input_comment">
			<span class = "input1">
				<form:form modelAttribute = "comment" action = "commentWrite.do" method = "post" name = "comment">
					<label id = "errorInput"></label>
					<input type = "hidden" name = "no" value = "${info.no}">
					<input type = "hidden" name = "type" value = "${info.type}">
					<input type = "hidden" name = "nickname" value = "${sessionScope.login.nickname}">
					<c:choose>
					<c:when test = "${not empty sessionScope.login}">
						<textarea class="form-control" name="content" id = "input_comment" placeholder="내용을 입력해주세요." style = "resize:none; width:90%;"></textarea>
						<button type = "submit" class = "btn btn-success" id = "comment_submit" onclick = "return commentCheck();">등록</button>
					</c:when>
					<c:otherwise>
						<textarea class="form-control" name="content" id = "input_comment" placeholder="로그인 후 댓글 작성 가능합니다." style = "resize:none; width:100%;" readonly></textarea>
					</c:otherwise>
					</c:choose>
				</form:form>
			</span>
		</div>
		
		<c:forEach var = "comment" items = "${comment}">
			<c:set var = "c_no" value = "${comment.c_no }"/> <!-- ajax에 사용될 commentNo -->
			<div class = "comment_content" id = "comment${c_no}">
				<div class = "commentInfo">
					<span class = "commentNickname" style = "font-size:18px;">
						<b>${comment.nickname}</b>
					</span>
				</div>
				
				<div class = "commentContent" style = "font-size:18px;">
					${comment.content}
				</div>
				<div class = "commentDate">
					<span class = "commentDate2">
						<fmt:formatDate value = "${comment.c_date}" pattern = "yyyy-MM-dd HH:mm"/>
					</span>
					<c:if test = "${sessionScope.login.nickname == comment.nickname}">
						&nbsp;<a href = "#" onclick = "commentDelete(${c_no});"><u>삭제</u></a>
						&nbsp;<a href = "#" onclick = "commentUpdate();"><u>수정</u></a>
					</c:if>
				</div>
				
			</div>
		</c:forEach>
	</div>
	
	<script>
		function commentCount(){
			var infoType = ${info.type};
			var infoNo = ${info.no};
			var comment = {
					type : infoType,
					no : infoNo
			};
			$.ajax({
				url:'${path}/board/commentCount.do?type='+infoType+"&no="+infoNo,
				type:'get',
				success : function(data) {
					$('.comment_count').html("<b>댓글 " + data + "</b>");
				}, error : function(){
					alert("댓글 수 불러오기 실패");
				}
			});
		}
		function deleteCheck(){
			if(confirm("삭제하시겠습니까?")){
				return true;
			} else{
				return false;
			}
		}
		function commentDelete(no){
			if(confirm("삭제하시겠습니까?")){
				var infoType = ${info.type};
				var infoNo = ${info.no};
				var no = no;
				var comment = {
						type : infoType,
						no : infoNo,
						c_no : no
				};
				$.ajax({
					url : '${path}/board/commentDelete.do',
					type : "post",
					dataType : "json",
					data : JSON.stringify(comment),
					contentType : 'application/json; charset = UTF-8',
					success : function(data) {
						if(data == 1) {
							commentCount();
							const div = document.getElementById('comment'+no); <!-- 댓글 실시간 삭제 -->
							div.remove();
						} else {
							alert("삭제 실패");
						}
					}, error : function(){
						alert("알 수 없는 오류");
					}
				});
			} else {
				
			}
		}
		function commentCheck(){
			var content = comment.input_comment.value;
			var sentNickname = "${sessionScope.login.nickname}";
			var recvNickname = "${info.nickname}";
			var title = "님이 게시물에 댓글을 남겼습니다.";
			var content = $("#input_comment").val();
			var href = "${path}/board/info.do?type=${info.type}&no=${info.no}";
			var today = new Date();
			
			var CommentParam = {
					"sentNickname" : sentNickname,
					"recvNickname" : recvNickname,
					"title" : title,
					"content" : content,
					"href" : href,
					"date" : today
			}
			
			if(content == "") {
				$("#errorInput").html("<font color = 'red'>내용을 입력해주세요.</font>");
				return false;
			} else {
				$.ajax({
					type : "POST",
					url : "${path}/alarm/saveAlarm.do",
					data : JSON.stringify(CommentParam),
					contentType : 'application/json; charset = UTF-8',
					async : false,
					success : function(data) {
						let Message = sentNickname + "," + recvNickname + "," + content + "," + today;
						ws.send(Message);
					} , error:function(error){
						console.log(error);
					}
				})
				return true;
			}
		}
	</script>

</body>
</html>
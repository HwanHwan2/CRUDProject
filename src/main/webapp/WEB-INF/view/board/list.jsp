<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file = "/WEB-INF/setting.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판 리스트</title>
<style>
	.list_form{
		margin-left:200px;
		margin-right:200px;
		position:relative;
		top:100px;
	}
	.pageSearch{
		margin-top:150px;
	}
	#search{
		margin-left:33%;
		z-index:30;
	}
</style>
<script>
   function listdo(page){
      document.searchform.pageNum.value=page;
      document.searchform.submit();
   }
</script>
</head>
<body>
<body>
	<div class = "list_form">
		<div class = "list_header">
			<span class = "header1" style = "font-size:25px;">
				<font style = "font-size:35px;">자</font>유 게시판
			</span>
			<span>
			<c:if test="${!empty sessionScope.login.id}">
				<button class = "btn btn-primary" onclick = "location.href = 'write.do'" style = "float:right;">글쓰기</button>
			</c:if>
			</span>
		</div>
		<table class = "table table-hover" id = "main_table">
			<tr><th style = "width:10%;">번호</th><th style = "width:50%;">제목</th><th style = "width:20%;">작성자</th><th style = "width:20%;">작성일</th></tr>	
			<c:forEach var = "board" items = "${list}">
				<tr>
					<td>${board.no}</td>
					<td><a href = "info.do?type=1&no=${board.no}">${board.title}</a></td>
					<td>${board.nickname}</td>
					<td>
						<fmt:formatDate value = "${board.b_date}" pattern = "yyyyMMdd" var = "date"/>
						<c:if test = "${today == date}">
							<fmt:formatDate value = "${board.b_date}" pattern = "HH:ss"/>
						</c:if>
						<c:if test = "${today != date}">
							<fmt:formatDate value = "${board.b_date}" pattern = "yy-MM-dd" />
						</c:if>
					</td>
				</tr>
			</c:forEach>
		</table>
		
	</div>
	
	<div class = "pageSearch">
		<ul class="pagination justify-content-center" style="margin-left:40%;">
          <li><a href="javascript:listdo(${startpage})"><<</a></li>
        <c:if test="${pageNum <=1}">
           <li><a href="javascript:listdo(${pageNum+1})"><</a></li>
        </c:if>
        <c:if test="${pageNum > 1}">
           <li><a href="javascript:listdo(${pageNum})"><</a></li>
         </c:if>
      
        <c:forEach var="a" begin="${startpage}" end="${endpage}">
            <li><a href="javascript:listdo(${a})">${a}</a></li>
         </c:forEach>
           
        <c:if test="${pageNum >= maxpage}">   
           <li><a href="javascript:listdo(${pageNum+1})">></a></li>
        </c:if>
        <c:if test="${pageNum < maxpage}">
            <li><a href="javascript:listdo(${pageNum+2})">></a></li>
        </c:if>
        <li><a href="javascript:listdo(${endpage+1})">>></a></li>
    	</ul><hr style="margin-top: 5px;margin-bottom: 15px">
	</div>
	
	<form action="list.do" method="post" name="searchform" class="form-inline" id = "search">
      	<select class="form-control" aria-label = "Default select example" name="searchtype" style="width:100px; font-family:emoji;">
      		<option value = "">선택</option>
      		<option value="title">제목</option>
      		<option value="nickname">닉네임</option>
      		<option value="content">글내용</option>
      	</select>
      	<script>
         	searchform.searchtype.value="${param.searchtype}";
      	</script>
       	<input type="text" class="form-control" placeholder="검색어를 입력해주세요." name="searchcontent" value="${param.searchcontent}" style="width:300px; font-size:13px; font-family:emoji;">
       	<input type="submit" class="btn btn-info" value="검색">
   	</form>
   	
</body>
</html>
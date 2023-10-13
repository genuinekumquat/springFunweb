<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>recenter/rewrite.jsp</title>
<link href="${pageContext.request.contextPath}/resources/css/default.css" rel="stylesheet" type="text/css">
<link href="${pageContext.request.contextPath}/resources/css/subpage.css" rel="stylesheet" type="text/css">
<!--[if lt IE 9]>
<script src="http://ie7-js.googlecode.com/svn/version/2.1(beta4)/IE9.js" type="text/javascript"></script>
<script src="http://ie7-js.googlecode.com/svn/version/2.1(beta4)/ie7-squish.js" type="text/javascript"></script>
<script src="http://html5shim.googlecode.com/svn/trunk/html5.js" type="text/javascript"></script>
<![endif]-->
<!--[if IE 6]>
 <script src="../script/DD_belatedPNG_0.0.8a.js"></script>
 <script>
   /* EXAMPLE */
   DD_belatedPNG.fix('#wrap');
   DD_belatedPNG.fix('#main_img');   

 </script>
 <![endif]-->
</head>
<body>
<div id="wrap">
<!-- 헤더들어가는 곳 -->
<!-- 액션태그 : jsp문법 , 태그처럼 사용, 웹의 경로 지정(문법으로 정해놓음)  
include 액션태그 : 반복되는 화면을 파일로 만들고 파일을 가져다가 사용-->
<jsp:include page="../inc/top.jsp"></jsp:include>
<!-- 헤더들어가는 곳 -->

<!-- 본문들어가는 곳 -->
<!-- 메인이미지 -->
<div id="sub_img_center"></div>
<!-- 메인이미지 -->

<!-- 왼쪽메뉴 -->
<nav id="sub_menu">
<ul>
<li><a href="${pageContext.request.contextPath}/board/list">Notice</a></li>
<li><a href="${pageContext.request.contextPath}/reboard/list">답글 게시판</a></li>
<li><a href="${pageContext.request.contextPath}/reboard/write">답글 게시판 글쓰기</a></li>
<li><a href="#">Driver Download</a></li>
<li><a href="#">Service Policy</a></li>
</ul>
</nav>
<!-- 왼쪽메뉴 -->

<!-- 게시판 -->
<%
// String id = (String)session.getAttribute("id");
%>
<article>
<h1>Re Write Notice (답글쓰기)</h1>
<form action="${pageContext.request.contextPath}/reboard/rewritePro" method="post">
<input type="hidden" name="num" value="${param.num }">
<input type="hidden" name="re_ref" value="${param.re_ref }">
<input type="hidden" name="re_lev" value="${param.re_lev }">
<input type="hidden" name="re_seq" value="${param.re_seq }">
<table id="notice">
<tr><td>글쓴이</td>
<td><input type="text" name="name" value="${sessionScope.id }" readonly="readonly"></td></tr>
<tr><td>제목</td>
    <td><input type="text" name="subject" value="[답글]"></td></tr>
<tr><td>내용</td>
    <td><textarea rows="10" cols="20" name="content"></textarea></td></tr>    
</table>
<div id="table_search">
<input type="submit" value="답글쓰기" class="btn">
</div>
</form>

<div class="clear"></div>
<div id="page_control">

</div>
</article>
<!-- 게시판 -->
<!-- 본문들어가는 곳 -->
<div class="clear"></div>
<!-- 푸터들어가는 곳 -->
<jsp:include page="../inc/bottom.jsp"></jsp:include>
<!-- 푸터들어가는 곳 -->
</div>
</body>
</html>
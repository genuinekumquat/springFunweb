<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
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
// List<BoardDTO> boardList 
//     = (List<BoardDTO>)request.getAttribute("boardList");

// PageDTO pageDTO=(PageDTO)request.getAttribute("pageDTO");
%>
<article>
<h1>Re Notice</h1>
<table id="notice">
<tr><th class="tno">No.</th>
    <th class="ttitle">Title</th>
    <th class="twrite">Writer</th>
    <th class="tdate">Date</th>
    <th class="tread">Read</th></tr>
    <%
// SimpleDateFormat format =new SimpleDateFormat("yyyy.MM.dd");
//     for(int i=0;i<boardList.size();i++){
//     	BoardDTO boardDTO=boardList.get(i);
    	%>
    	
    <c:forEach var="boardDTO" items="${boardList }">	
<tr onclick="location.href='${pageContext.request.contextPath}/reboard/content?num=${boardDTO.num}'">
    <td>${boardDTO.num}</td>
    <td class="left">
    <c:if test="${boardDTO.re_lev > 0}">
    	<c:set var="wid" value="${boardDTO.re_lev * 10}"></c:set>   <%--  레벨값을 변수를지정  --%>
    	<img src="${pageContext.request.contextPath}/resources/images/center/level.gif" width="${wid}" height="10px">
    	<img src="${pageContext.request.contextPath}/resources/images/center/re.gif">
    </c:if>
    ${boardDTO.subject}</td>
    <td>${boardDTO.name}</td>
    <td><fmt:formatDate value="${boardDTO.date}" pattern="yyyy.MM.dd"/> </td>
    <td>${boardDTO.readcount}</td></tr>  
    </c:forEach>
      	
    	<%
//     }
    %>
</table>
<%
// String id = (String)session.getAttribute("id");
// if(id != null){
	%>
	<c:if test="${!( empty sessionScope.id )}">
	
<div id="table_search">
<input type="button" value="글쓰기" class="btn" 
       onclick="location.href='${pageContext.request.contextPath}/reboard/write'">
<input type="button" value="파일글쓰기" class="btn" 
       onclick="location.href='${pageContext.request.contextPath}/reboard/fwrite'">       
</div>	

</c:if>
	<%
// }
%>


<div id="table_search">
<form action="${pageContext.request.contextPath}/reboard/list" method="get">
<input type="text" name="search" class="input_box">
<input type="submit" value="search" class="btn">
</form>
</div>

<div class="clear"></div>
<div id="page_control">
<%
// 시작페이지 1페이지 Prev 없음
// 시작페이지 11,21,31 Prev 보임
// if(pageDTO.getStartPage() > pageDTO.getPageBlock()){
	%>
<%-- 	<a href="list.bo?pageNum=<%=pageDTO.getStartPage()-pageDTO.getPageBlock()%>">Prev</a> --%>
	<%
// }
%> 
<c:if test="${pageDTO.startPage > pageDTO.pageBlock}">
	<a href="${pageContext.request.contextPath}/reboard/list?pageNum=${pageDTO.startPage - pageDTO.pageBlock}&search=${pageDTO.search}">Prev</a>
</c:if>


<%
// for(int i=pageDTO.getStartPage();i<=pageDTO.getEndPage();i++){
	%>
<%-- 	<a href="list.bo?pageNum=<%=i%>"><%=i %></a>  --%>
	<%
// }
%>

<c:forEach var="i" begin="${pageDTO.startPage}" 
                   end="${pageDTO.endPage}" step="1">
<a href="${pageContext.request.contextPath}/reboard/list?pageNum=${i}&search=${pageDTO.search}">${i}</a> 
</c:forEach>

<%
//끝페이지번호  전체페이지수 비교 => 전체페이지수 크면 => Next보임
// if(pageDTO.getEndPage() < pageDTO.getPageCount()){
	%>
<%-- 	<a href="list.bo?pageNum=<%=pageDTO.getStartPage()+pageDTO.getPageBlock()%>">Next</a> --%>
	<%
// }
%>

<c:if test="${pageDTO.endPage < pageDTO.pageCount}">
	<a href="${pageContext.request.contextPath}/reboard/list?pageNum=${pageDTO.startPage + pageDTO.pageBlock}&search=${pageDTO.search}">Next</a>
</c:if>

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
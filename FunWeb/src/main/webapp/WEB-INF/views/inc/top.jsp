<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<header>
<%
// inc/top.jsp
// JSTL(JSP Standard Tag Library) : JSP 표준 태그 라이브러리 
// 외부 프로그램 설차(API)
// tomcat.apache.org
// https://tomcat.apache.org/download-taglibs.cgi
// 다운로드 
// Impl: taglibs-standard-impl-1.2.5.jar (pgp, sha512)
// Spec: taglibs-standard-spec-1.2.5.jar (pgp, sha512)
// EL: taglibs-standard-jstlel-1.2.5.jar (pgp, sha512)
// Compat: taglibs-standard-compat-1.2.5.jar (pgp, sha512)
// -> WEB-INF - lib에 넣기 

// 사용하기 위해서 불러오기 
//  taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" 
// 변수선언, if, choose when otherwise, foreach. forToken
// redirect, url

// taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"
// setLocale, timezone, formatNumber, formatDate, parseDate




//로그인 하면 => 로그인한 정보를 세션 저장
// 세션에 로그인 정보 가져오기
// String id=(String)session.getAttribute("id");
// // 세션에 로그인 정보가 없으면(null) => 로그인login, 회원가입join
// // 세션에 로그인 정보가 있으면 => ..님 로그아웃logout 회원수정update
// if(id == null){
	%> 
<!-- <div id="login"><a href="login.me">login</a> | <a href="insert.me">join</a></div>	 -->
<%
// }else{
 %> 
<%-- <div id="login">${sessionScope.id} <%=id %>님 | <a href="logout.me">logout</a> --%>
<!--                                            | <a href="update.me">update</a> -->
<!--                                             | <a href="delete.me">delete</a> -->
<!--                                              | <a href="list.me">list</a></div> -->
<%	
//}
 %> 
<c:if test="${empty sessionScope.id}">
<div id="login"><a href="login.me">login</a> | <a href="insert.me">join</a></div>	
</c:if>

<c:if test="${!(empty sessionScope.id)}">
<div id="login">${sessionScope.id}님 | <a href="${pageContext.request.contextPath}/member/logout">logout</a>
                                           | <a href="${pageContext.request.contextPath}/member/update">update</a>
                                            | <a href="${pageContext.request.contextPath}/member/delete">delete</a>
                                             | <a href="${pageContext.request.contextPath}/member/list">list</a></div>
                                             
</c:if>

<div class="clear"></div>
<!-- 로고들어가는 곳 -->
<div id="logo"><img src="${pageContext.request.contextPath}/resources/images/logo.gif" width="265" height="62" alt="Fun Web"></div>
<!-- 로고들어가는 곳 -->
<nav id="top_menu">
<ul>
	<li><a href="${pageContext.request.contextPath}/member/main">HOME</a></li>
	<li><a href="welcome.co">COMPANY</a></li>
	<li><a href="#">SOLUTIONS</a></li>
	<li><a href="${pageContext.request.contextPath}/board/list">CUSTOMER CENTER</a></li>
	<li><a href="#">CONTACT US</a></li>
</ul>
</nav>
</header>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
<jsp:include page="../inc/top.jsp"></jsp:include>
<!-- 헤더들어가는 곳 -->

<!-- 본문들어가는 곳 -->
<!-- 본문메인이미지 -->
<div id="sub_img_member"></div>
<!-- 본문메인이미지 -->
<!-- 왼쪽메뉴 -->
<nav id="sub_menu">
<ul>
<li><a href="#">Join us</a></li>
<li><a href="#">Privacy policy</a></li>
</ul>
</nav>
<!-- 왼쪽메뉴 -->
<!-- 본문내용 -->
<article>
<h1>Join Us</h1>
<form action="${pageContext.request.contextPath}/member/insertPro" id="join" method="post">
<fieldset>
<legend>Basic Info</legend>
<label>User ID</label>
<input type="text" name="id" class="id">
<input type="button" value="dup. check" class="dup"><br>
<label></label>
<div class="divdup"></div>
<br>

<label>Password</label>
<input type="password" name="pass" class="pass"><br>
<label>Retype Password</label>
<input type="password" name="pass2"><br>
<label>Name</label>
<input type="text" name="name" class="name"><br>
<label>E-Mail</label>
<input type="text" name="email" class="email"><br>
<label>Retype E-Mail</label>
<input type="email" name="email2"><br>
</fieldset>

<fieldset>
<legend>Optional</legend>
<label>Address</label>
<input type="text" name="address"><br>
<label>Phone Number</label>
<input type="text" name="phone"><br>
<label>Mobile Phone Number</label>
<input type="text" name="mobile"><br>
</fieldset>
<div class="clear"></div>
<div id="buttons">
<input type="submit" value="Submit" class="submit">
<input type="reset" value="Cancel" class="cancel">
</div>
</form>
</article>
<!-- 본문내용 -->
<!-- 본문들어가는 곳 -->

<div class="clear"></div>
<!-- 푸터들어가는 곳 -->
<jsp:include page="../inc/bottom.jsp"></jsp:include>
<!-- 푸터들어가는 곳 -->
</div>

<script type="text/javascript" 
        src="${pageContext.request.contextPath}/resources/script/jquery-3.7.0.js"></script>
        
<script type="text/javascript">
	$(document).ready(function(){
// 		id="join" 폼을 전송했을때
		$('#join').submit(function(){
// 			alert("동작");

//         정규표현식 : 문자열을 처리하기 위한 패턴 기반의 문자열
//                 : 정규표현식을 통해 처리할 문자열의 패턴 지정
//                 : 패스워크 유효성 검사, 전화번호, 이메일 양식 검사
//                 : 자바, 자바스크립트 모든 언어 사용가능
//                 : 네트워크, 서버 환경 설정 등 공용으로 사용하는 표준 표현식

//         정규표현식 -> 변수 정의
//                -> 영어만 입력 가능 [a-zA-Z]  
//                -> 숫자만 입력 가능 [0-9]
//                -> 한글만 입력 가능 [가-힣]
//                -> [] 하나 이상 포함, ()안에 문자열 그대로 포함
//                   {} 문자열 반복 
//                -> ^ 시작하는 문자열, $ 끝나는 문자열
//                   . 1개 문자, + 반복, * 0번이상 반복
//                   ? 나올 수도 있고 나오지 않을 수도 있는 문자열
//                   | 또는 포함되는 문자열
       

//          class="id"  value 비어있으면
			if($('.id').val()==""){
				// 아이디 입력하세요
				alert("아이디 입력하세요");
				$('.id').focus();
				//submit버튼이 동작하지 못하게 막음
				return false;
			}
			
			// 영문 대소문자, 숫자, 특수문자 _ - 입력가능 
			// 5 ~ 20 자리 입력 체크
			var idCheck = RegExp(/^[a-zA-Z0-9_\-]{5,20}$/);
			if(!idCheck.test($('.id').val())){
				alert("아이디 형식 아님");
				$('.id').focus();
				return false;
			}
			
			if($('.pass').val()==""){
				// 비밀번호 입력하세요
				alert("비밀번호 입력하세요");
				$('.pass').focus();
				//submit버튼이 동작하지 못하게 막음
				return false;
			}
			//비밀번호 영문 대소문자,숫자, 특수문자 하나 이상 포함 8~16  
			var passCheck = RegExp(/^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#$%^*]).{8,16}$/);
			if(!passCheck.test($('.pass').val())){
				alert("비밀번호 형식 아님");
				$('.pass').focus();
				return false;
			}
			
			if($('.name').val()==""){
				// 이름 입력하세요
				alert("이름 입력하세요");
				$('.name').focus();
				//submit버튼이 동작하지 못하게 막음
				return false;
			}
			//이름 한글 2~6
			var nameCheck = RegExp(/^[가-힣]{2,6}$/);
			if(!nameCheck.test($('.name').val())){
				alert("이름 형식 아님");
				$('.name').focus();
				return false;
			}
			//이메일 아이디@naver.com
			var emailCheck = RegExp(/^[A-Za-z0-9_\.\-]+@[A-Za-z0-9\-]+\.[A-Za-z0-9\-]/);
			if(!emailCheck.test($('.email').val())){
				alert("이메일 형식 아님");
				$('.email').focus();
				return false;
			}
			
		});//submit이벤트
		
// 		class="dup" 버튼을 클릭했을때
		$('.dup').click(function(){
// 			alert("클릭");
			$.ajax({
				url:'${pageContext.request.contextPath}/member/idCheck',
				// 파라미터이름 : val() 값
				data:{'id':$('.id').val()},
				success:function(result){
// result 아이디 중복, 아이디 사용가능 결과 출력값 받아오는 변수
					if(result=='iddup'){
						result = "아이디 중복";
					}else{
						result = "아이디 사용가능";
					}
//		          class="divdup" 안에 아이디 중복 값을 넣기
					$('.divdup').html(result);
				}
			});//ajax()
		});//click()
		// 화면이 안바뀌면서 아이디 중복,아이디 사용가능 출력
		// join.me에서 idCheck.me 페이지로 갔다가 출력결과를 가지고
		// 다시와서 join.me 에서 결과 값을 출력
// 		ajax : 비동기식 자바스크립트 XML
//            (Asynchronous Javascript And XML)의 약자.

		
	});
</script>


</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>과제관리시스템_8조</title>
<script type="text/javascript">
function logincheck(){
	if ( document.userLogin.userId.value=="" ) {
		alert("아이디를 입력해 주세요.");
		document.userLogin.userId.focus();
		return;
	}
	if ( document.userLogin.userPw.value=="" ) {
		alert("비밀번호를 입력해 주세요.");
		document.userLogin.userPw.focus();
		return;
	}
	document.userLogin.submit();	
}

</script>
</head>
<body>
<%@include file="/jsp/common/Main.jsp"%>
<div id="contents">
	<p align="center">로그인 먼저 해주세요</p>
	<form method="post" action="loginCheck.jsp" name=userLogin>
		<table style="margin: 0 auto;" >
			<tr><td><input type="text" name="userId"placeholder="아이디"></td></tr>
			<tr><td><input type="password" name="userPw"placeholder="비밀번호"></td></tr>
			<tr><td><input type=button value="로그인" onClick="logincheck()"></td></tr>
		</table>
	</form>
</div>

</body>
</html>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>과제관리시스템_8조</title>
<script type="text/javascript">
function logincheck(){
	if ( document.userLogin.userId.value=="" ) {
		alert("아이디를 입력해 주세요.dydydyd");
		document.userLogin.userId.focus();
		return;
	}
	if ( document.userLogin.userPw.value=="" ) {
		alert("비밀번호를 입력해 주세요.");
		document.userLogin.userPw.focus();
		return;
	}
	document.userLoing.submit();	
}
</script>
</head>
<body>
<%@include file="/jsp/common/Main.jsp"%>
<div id="contents">
	안녕하세요 <%=loginId %>
	<%if(loginId == null || loginId == "") 
	{
		response.sendRedirect("/ResearchMasterProject/jsp/common/login.jsp");
	}
	%>
</div>

</body>
</html>

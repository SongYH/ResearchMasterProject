<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="Control.UserManage" %>
<!-- jsp코드부분 -->
<%	request.setCharacterEncoding("UTF-8"); %>
<%
	String id = request.getParameter("userid");
	UserManage um = new UserManage();
	boolean check = um.reqcheckId(id);
%>
<!-- jsp 끝 -->
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<!-- 자바스크립트 부분 -->
<script language="javascript">
function setId()
{
	opener.document.userInput.id.value="<%=id%>";
	self.close();
}
</script>
<!-- 스크립트 끝 -->

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>아이디 중복확인</title>
</head>
<body>
<%	if(check)
	{
%>
 		<p>입력하신 <%=id%> 는 사용할 수 있는 ID입니다.</p>
 		<input type="button" value="ID사용" onclick="setId()">
<%	}
	else
	{
%>		<p>입력하신 <%=id%> 는 이미 사용중인 ID입니다.</p>
		<input type="button" value="닫기" onclick="self.close()">
<%	}
%>
</body>
</html>


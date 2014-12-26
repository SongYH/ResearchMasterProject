<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="Entity.UserList" %>

<!-- jsp코드부분 -->
<%	request.setCharacterEncoding("UTF-8");
	String num = request.getParameter("num");
	String userName = request.getParameter("name");
	String socialNumber = request.getParameter("socialnum");
	UserList manager = UserList.getInstance();
	String userID = manager.getUserId(userName, socialNumber);
	
%>
 
<!-- jsp 끝 -->
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<!-- 자바스크립트 부분 -->
<script language="javascript">
function setId()
{
	if(<%=num%> == 1)
	{
		opener.document.projectInput.workerId1.value="<%=userID%>";
		self.close();
	}
	if(<%=num%> == 2)
	{
		opener.document.projectInput.workerId2.value="<%=userID%>";
		self.close();
	}
	if(<%=num%> == 3)
	{
		opener.document.projectInput.workerId3.value="<%=userID%>";
		self.close();
	}
	if(<%=num%> == 4)
	{
		opener.document.projectInput.workerId4.value="<%=userID%>";
		self.close();
	}
	if(<%=num%> == 5)
	{
		opener.document.projectInput.workerId5.value="<%=userID%>";
		self.close();
	}
	self.close();
}
</script>
<!-- 스크립트 끝 -->

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>연구원 확인</title>
</head>
<body>
<%	if(userID != null)
	{
%>
 		<p> 존재하는 사용자 입니다. 등록하시겠습니까? </p>
 		<input type="button" value="등록" onclick="setId()">
 		<input type="button" value="닫기" onclick="self.close()">
<%	}
	else
	{
%>		
		<p> 존재하지 않는 사용자 입니다. 확인해보세요 </p>
 		<input type="button" value="닫기" onclick="self.close()">
<%	}
%>
</body>
</html>


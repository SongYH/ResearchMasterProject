<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="Control.PermissionManager" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>권한관리</title>
</head>
<body>
<%@include file="/jsp/common/Main.jsp"%>
<%
	request.setCharacterEncoding("UTF-8");

	String login_id = (String)session.getAttribute("loginId");
	PermissionManager manager = new PermissionManager();
	
	if(login_id == null || login_id == "") 
	{
		response.sendRedirect("/ResearchMasterProject/jsp/common/login.jsp");
	}
	else
	{
		String permission = manager.confirmUserPermission(login_id);// 권한을 아이디를 통해 가져옴 
		
		if(permission.equals("권한담당자"))
		{
%>


<div id="contents">
	<h1>권한관리 사용자 검색</h1>
	<form action="/ResearchMasterProject/jsp/permng/PageManagePerm.jsp" method="post" name="searchInputs">
		<table style="width:30%; margin-left:40%; margin-right:30%;">
			<tr>
				<td>검색방법
				<select name="searchType">
						<option value="id" selected>아이디</option>
						<option value="permissionName">권한이름</option>
				</select>
				<input type="text" name="searchInput" placeholder="검색어입력" />
				<input type="submit" name="searchButton" value="검색"/>
				</td>
			</tr>
		</table>
	</form>
</div>
<%		}
		else
		{
			out.println("<script>alert('권한담당자가 아닙니다'); location.href = '/ResearchMasterProject/jsp/common/Home.jsp'; </script>"); 
		}
	}
%>
</body>
</html>
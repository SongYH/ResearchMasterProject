<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="Control.PermissionManager" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>선정평가</title>
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
%>


<div id="contents">
	<h1>선정평가 검색</h1>
	<form action="/ResearchMasterProject/jsp/selecteval/PageManageSelect.jsp" method="post" name="searchInputs">
		<table style="width:30%; margin-left:40%; margin-right:30%;">
			<tr>
				<td>검색방법
				<select name="searchType">
						<option value="state">상태</option>
				</select>
				<input type="text" name="searchInput" value="신청" />
				<input type="submit" name="searchButton" value="검색"/>
				</td>
			</tr>
		</table>
	</form>
</div>
<%
}
%>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList" %>
<%@ page import="Control.PermissionManager" %>
<%@ page import="Common.User" %>
<%@ page import="Common.Permission" %>
<% request.setCharacterEncoding("UTF-8"); %>

<%
	String searchType = request.getParameter("searchType");
	String searchInput = request.getParameter("searchInput");

	PermissionManager manager = new PermissionManager();
	ArrayList<PermissionManager.ManageInfo> viewElements = manager.reqPermissionInfo(searchType, searchInput);
	ArrayList<Permission> pList = manager.getPermissionList();
%>

<!-- 여기서부터는 사용자에게 보여질 페이지입니다. -->
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
<%@include file="/jsp/common/Main.jsp"%>
<div id="contents">
	<a href="/ResearchMasterProject/jsp/permng/PageSearchUser.jsp">사용자검색으로 이동</a>
	<h1>권한관리</h1>
<% 	if(viewElements == null)
	{
%>
		<h2>해당하는 사용자가 없습니다.</h2>
<%	}
	else
	{
%>
	<form action="/ResearchMasterProject/jsp/permng/ActionManagePerm.jsp" name="newPermission" method="post">
	<table>
		<tr>
			<th>아이디</th>
			<th>이름</th>
			<th>전화번호</th>
			<th width="20%">이메일</th>
			<th width="20%">주소</th>
			<th>권한</th>
		</tr>
		
<%
		for(PermissionManager.ManageInfo x : viewElements)
		{
			User u = x.getUserInfo();
			String pName = x.getPermissionName();
			int count = 1;
%>
			<tr>
				<td><%= u.getId() %></td>
				<td><%= u.getName() %></td>
				<td><%= u.getTel()%></td>
				<td><%= u.getEmail() %></td>
				<td><%= u.getAddress() %></td>
				<td>
					<select name="newPermissionName">
						<option selected value="<%=pName%>"><%=pName%></option>
						<% for(Permission p : pList) {%>
						<option value="<%=p.getName()%>"><%=p.getName()%></option>
						<%} %>
					</select>
				</td>
				<td><input type="hidden" value="<%=u.getId()%>" name="userId"/></td>
			</tr>
<%		}
%>
	<tr><td colspan="6"><input type="submit" value="전체수정"/></td></tr>
	</table>
	</form>
<%	}
%>
</div>
</body>
</html>
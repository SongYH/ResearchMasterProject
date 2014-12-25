<%@page import="Common.PreProject"%>
<%@page import="Control.SubjectManager"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList" %>
<%@ page import="Control.PermissionManager" %>
<%@ page import="Common.User" %>
<%@ page import="Common.Permission" %>
<% request.setCharacterEncoding("UTF-8"); %>

<%
	String state = request.getParameter("searchType");
	String searchInput = request.getParameter("searchInput");

	SubjectManager subM = new SubjectManager();
	ArrayList<PreProject> preproList = subM.request_PreProjectList_state(searchInput);
%>

<!-- 여기서부터는 사용자에게 보여질 페이지입니다. -->
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
<%@include file="/jsp/common/Main.jsp"%>
<div id="contents">
	<h1>선정평가</h1>
<% 	if(preproList.size() == 0)
	{
%>
		<h2>신청된 과제가 없습니다.</h2>
<%	}
	else
	{
%>
	<form action="/ResearchMasterProject/jsp/selecteval/ActionManageSelect.jsp" name="newPermission" method="post">
	<table>
		<tr>
			<th>사업명</th>
			<th>과제유형</th>
			<th>과제기간</th>
			<th>id</th>
			<th>상태</th>
		</tr>
		
<%
		for(PreProject x : preproList)
		{
			int count = 1;
%>
			<tr>
				<td><%= x.getSubjectName() %></td>
				<td><%= x.getType()%></td>
				<td><%= x.getArea() %></td>
				<td><%= x.getId() %></td>
				<td>
					<select name="newPermissionName">
						<option selected value="신청">신청</option>
						<option selected value="선정평가통과">선정평가통과</option>
					</select>
				</td>
				<td><input type="hidden" value="<%=x.getProjectNumber()%>" name="projectNum"/></td>
			</tr>
<%		}
%>
	<tr><td colspan="6"><input type="submit" value="변경"/></td></tr>
	</table>
	</form>
<%	}
%>
</div>
</body>
</html>
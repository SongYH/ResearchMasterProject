<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.*, java.text.*"  %>
<%@ page import="Common.*, Control.*, Entity.*"  %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>과제조회</title>
<%
	request.setCharacterEncoding("UTF-8");
	String projectNum = request.getParameter("projectNum");
	ProjectManager projectM = new ProjectManager();
	Project project = projectM.reqProjectSearchNumber(Integer.parseInt(projectNum));
	Budget budget = projectM.reqBudgetSearchNumber(Integer.parseInt(projectNum));
	ArrayList<String> userIdList = projectM.reqResearcherIDList(Integer.parseInt(projectNum));
	ArrayList<User> userList = new ArrayList<User>();
	for(String id : userIdList)
		userList.add(projectM.reqUserInfo(id));
%>
</head>
<body>
<%@include file="/jsp/common/Main.jsp"%>
	<%if(loginId == null || loginId == "") 
	{
		response.sendRedirect("/ResearchMasterProject/jsp/common/login.jsp");
	}else { // 로그인 되있는 상태면
	%>
		<hr /> <p align="center">과제조회</p> <hr />
		<table align="center">
				<tr>
					<td align="right" >과제번호 : </td>
					<td align="left"><%=project.getProjectNumber() %></td>
				</tr>
				<tr>
					<td align="right" >과제명 : </td>
					<td align="left"><%=project.getProjectName() %>	</td>
				</tr>
				<tr>
					<td align="right" >과제상태 : </td>
					<td align="left"><%=project.getStatus()%></td>
				</tr>
				<tr>
					<td align="right" >과제종류 : </td>
					<td align="left"><%=project.getType() %></td>
				</tr>
				<tr>
					<td align="right" >과제기간 : </td>
					<td align="left"><%=project.getArea() %></td>
				</tr>
				<tr>
					<td align="right" >과제협약 : </td>
					<td align="left"><%=project.getAgreeYear() %> 년</td>
				</tr>
				<tr>
					<td align="right" >과제책임자 : </td>
					<td align="left"><%=project.getLeaderName() %></td>
				</tr>
				<tr>
					<td align="right" >기관명 : </td>
					<td align="left"><%=project.getOrganName()%></td>
				</tr>
				<tr>
					<td align="right" >과제등록일 : </td>
					<td align="left"><%=project.getRegisterDate()%></td>
				</tr>
				<tr>
					<td align="right" >첨부파일 : </td>
					<td align="left"><%=project.getFileName()%></td>
				</tr>
			</table>
			<hr /> 
			<table align="center">
				<%if(0 < userList.size()) 
				{%>
				<tr>
					<td align="right" >참여연구원1 : </td>
					<td align="left"><%=userList.get(0).getName() %></td>
				</tr>
				<%} %>
					<%if(1 < userList.size()) 
				{%>
				<tr>
					<td align="right" >참여연구원2 : </td>
					<td align="left"><%=userList.get(1).getName() %></td>
				</tr>
				<%} %>
					<%if(2 < userList.size()) 
				{%>
				<tr>
					<td align="right" >참여연구원3 : </td>
					<td align="left"><%=userList.get(2).getName() %></td>
				</tr>
				<%} %>
					<%if(3 < userList.size()) 
				{%>
				<tr>
					<td align="right" >참여연구원4 : </td>
					<td align="left"><%=userList.get(3).getName() %></td>
				</tr>
				<%} %>
					<%if(4 < userList.size()) 
				{%>
				<tr>
					<td align="right" >참여연구원5 : </td>
					<td align="left"><%=userList.get(4).getName() %></td>
				</tr>
				<%} %>
			</table>
			<hr />
			<table align="center">
				<tr>
					<td align="right" >총예산 : </td>
					<td align="left"><%=budget.getAmount() %></td>
				</tr>
			</table>
			<hr />
	<%	
	} 
	%>
	
</body>
</html>
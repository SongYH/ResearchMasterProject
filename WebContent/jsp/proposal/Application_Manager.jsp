<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ page import="java.util.ArrayList"%>
<%@ page import="Common.Subject" %>
<%@ page import="Control.SubjectManager"%>
<%@ page import="java.util.Date" %>

<!-- jsp 코드부분 -->
<%	
	response.setContentType("text/html; charset=UTF-8");
	request.setCharacterEncoding("UTF-8"); 
%>
<%
	String type = request.getParameter("type");
	SubjectManager sm = new SubjectManager();
	
	ArrayList<Subject> sl = sm.request_Type_SubjectList(type);
%>
<!-- jsp 끝 -->

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="/ResearchMasterProject/css/style.css" />
<title>해당신청대상과제목록</title>
</head>
<body>
<%@include file="/jsp/common/Main.jsp"%>
	<table style=margin:auto;>
	<tr>
	<td><center><font size="5"><b>산업과제표준분류</b></font></center></td>
	</tr>
	<td>
		<form method="post" name="selectType" action="/ResearchMasterProject/jsp/proposal/Application_Manager.jsp">
			<input type = "radio" name = "type" value = "전체" checked/>전체 &nbsp;
			<input type = "radio" name = "type" value = "선택집중형"/>선택집중형 &nbsp;
			<input type = "radio" name = "type" value = "저변확대형"/>저변확대형 &nbsp;
			<input type = "radio" name = "type" value = "인프라조성형"/>인프라조성형 &nbsp;
			<input type = "submit" name = "select" value = "선택">
		</form>
	</td>
	</table>
	
		<table style=margin:auto; border="1">
			<tr>
				<th>번 호</th>
				<th>분 야</th>
				<th>사업유형</th>
				<th>사업명</th>
				<th>세부사항</th>
				<th>공고일</th>
				<th>마감일</th>
				<th>신청하기</th>
			</tr>
	<% 	for(int i = 0; i < sl.size(); i++){ %>
			<tr>
				<td><%= sl.get(i).getNumber() %></td>
				<td><%= sl.get(i).getType() %></td>
				<td><%= sl.get(i).getArea() %></td>
				<td><%= sl.get(i).getName() %></td>
				<td><%= sl.get(i).getDescription() %></td>
				<td><%= sl.get(i).getOpenDate() %></td>
				<td><%= sl.get(i).getCloseDate() %></td>
				
			<%
				type = sl.get(i).getType();
				String area = sl.get(i).getArea();
				String name = sl.get(i).getName();
			%>
				
				<td><a href = "/ResearchMasterProject/jsp/proposal/Application_Manager2.jsp?
				&type=<%= type %>
				&area=<%= area %>
				&name=<%= name %>
				">신청하기</a>
				</td>
			</tr>
	<% 	}	%>		
		</table>
</body>
</html>
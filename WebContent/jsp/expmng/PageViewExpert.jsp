<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList" %>
<%@ page import="Common.Expert" %>
<%@ page import="Control.ExpertManager" %>
<% request.setCharacterEncoding("UTF-8"); %>

<%
	String searchType = request.getParameter("searchType");
	String searchInput = request.getParameter("searchInput");
	
	ArrayList<Expert> expertList = new ArrayList<Expert>();
	ExpertManager manager = new ExpertManager();
	
	if(searchType.equals("expertNumber"))
		expertList = manager.requestSearchExpert(Integer.parseInt(searchInput));
	else if(searchType.equals("expertName"))
		expertList = manager.requestSearchExpertsByName(searchInput);
	else if(searchType.equals("organName"))
		expertList = manager.requestSearchExpertsByOrgan(searchInput);
	
	boolean nullFlag = expertList.size() == 0? true : false;
%>

<!-- 여기서부터는 사용자에게 보여질 페이지입니다. -->
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
<%@include file="/jsp/common/Main.jsp"%>
<div id="contents">
	<a href="/ResearchMasterProject/jsp/expmng/PageSearchExpert.jsp">전문가 검색</a>
	<h1>전문가관리</h1>
<% 	if(nullFlag)
	{
%>
		<h2>해당하는 전문가는 없습니다.</h2>
<%	}
	else
	{
%>
	<table style=margin:auto;>
		<tr>
			<th>아이디</th>
			<th>이름</th>
			<th>전화번호</th>
			<th>이메일</th>
			<th>주소</th>
			<th>분야</th>
		</tr>
<%
		for(Expert x : expertList)
		{
%>
			<tr>
				<td><%= x.getId() %></td>
				<td><%= x.getName() %></td>
				<td><%= x.getTel()%></td>
				<td><%= x.getEmail() %></td>
				<td><%= x.getHomeAddress() %></td>
				<td><%= x.getField() %></td>
			</tr>
<%		}
%>
	</table>
<%	}
%>
</div>
</body>
</html>
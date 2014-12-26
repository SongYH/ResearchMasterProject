<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList" %>
<%@ page import="Common.User" %>
<%@ page import="Control.UserManage" %>
<% request.setCharacterEncoding("UTF-8"); %>

<%
    int num=0;
	String searchType = request.getParameter("searchType");
	String searchInput = request.getParameter("searchInput");
	boolean nullFlag = false;
	ArrayList<User> UserList = new ArrayList<User>();
	
	UserManage user = new UserManage();
	
    if(searchType.equals("expertName"))
    {
    	UserList = user.reqUserListFromName(searchInput);
    	nullFlag = UserList.size() == 0? true : false;
    }
	else if(searchType.equals("organName"))
	{
		num = user.reqgetorgannum(searchInput); // 기관명을 입력해서 먼저  기관번호를 얻음
		UserList = user.requserorid(num);
		if(num==0) nullFlag = true;
	}

	
%>

<!-- 여기서부터는 사용자에게 보여질 페이지입니다. -->
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
<%@include file="/jsp/common/Main.jsp"%>
<div id="contents">
	<a href="/ResearchMasterProject/jsp/expmng/PageSearchExpert_User.jsp">전문가 추가</a>
	<h1>전문가 추가</h1>
<% 	if(nullFlag)
	{
%>
		<h2>해당하는 사용자가 없습니다.</h2>
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

		</tr>
<%
		for(User x : UserList)
		{
			 System.out.println(num);
%>
		  	<tr>
				<td><%= x.getId() %></td>
				<td><%= x.getName() %></td>
				<td><%= x.getTel()%></td>
				<td><%= x.getEmail() %></td>

			</tr>
<%		}
%>
	</table>
	
		<form action="/ResearchMasterProject/jsp/expmng/PageInputExpert.jsp" method="post">
		<table style="width:30%; margin-left:40%; margin-right:30%;">
			<tr>
				<td>추가할 전문가 ID입력 :
				<input type="text" name="searchID" placeholder="검색어입력" />
				<input type="submit" name="searchButton" value="검색"/>
				</td>
			</tr>
		</table>
	</form>
	
<%	}
%>

</div>
</body>
</html>
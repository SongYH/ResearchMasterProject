<%@page import="Control.UserManage"%>
<%@page import="Common.User"%>
<%@page import="Control.CareerManager.ProjectCareerUnityInfo"%>
<%@page import="java.text.DateFormat"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.Date" %>
<%@ page import="Control.CareerManager" %>
<%@ page import="Common.ProjectCareer" %>
<%@ page import="Common.Project" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>이력관리-참여기관정보조회</title>

<style>
  table {
    width: 100%;
  }
  th, td {
    border: 1px solid #bcbcbc;
  }
</style>

</head>
<body>
<%@include file="/jsp/common/Main.jsp"%>
<h2 style="text-align:center;"> 과제 참여 기관정보 조회</h2>
<%
	String recordDate = request.getParameter("recordDate");
	String projectNumber = request.getParameter("projectNumber");

	SimpleDateFormat transFormat = new SimpleDateFormat("yyyy-MM-dd");
	
	Date RecordDate = (Date)transFormat.parse(recordDate);

	int ProjectNumber=0;
		ProjectNumber = Integer.parseInt(projectNumber);

	CareerManager manager = new CareerManager();
	
	ProjectCareerUnityInfo viewElementsInfo = manager.requestProjectCareerUnityInfo(RecordDate, ProjectNumber);
	ArrayList<String> projectUserIDList =viewElementsInfo.getProjectCareer().getProjectInfo().getUserIdList();
		
	if(viewElementsInfo == null)
	{
%>
		<h2 style="text-align:center;">참여 구성원의 아이디목록이 없습니다.</h2>
<%	}
	else
	{

%>
<% 
	//UserManager userManaser= new UserManager();
	UserManage userManaser= new UserManage();
	ArrayList<User> viewUserInfoList =  userManaser.requserImformation(projectUserIDList);
	
%>

<!-- 여기서부터는 사용자에게 보여질 유저 정보 목록입니다. -->

<% 	if(viewUserInfoList == null)
	{
%>
		<h2 style="text-align:center;">참여 구성원의 정보목록이 없습니다.</h2>
<%	}
	else
	{

%>
	<table style=margin:auto;>
		<tr>
			<th>이름</th>
			<th>ID</th>
			<th>주민번호(앞)</th>
			<th>주소</th>
			<th>전화번호</th>
			<th>email</th>
			<th>기관ID</th>
		</tr>
		
<%
		for(User x : viewUserInfoList)
		{
			
		
%>
			<tr>
				<td><%= x.getName() %></td>
				<td><%= x.getId() %></td>
				<td><%= x.getSocialNumber() %></td>
				<td><%= x.getAddress() %></td>
				<!-- 직책 넣어야 함..x.get직책() -->
				<td><%= x.getTel() %></td>
				<td><%= x.getEmail() %></td>
				<td><%= x.getOrganId() %></td>
				<td>
				
  </td>
			</tr>
<%		}
%>

	</table>
	
<%	
		}
	}
%>
</div>

</body>
</html>
<%@page import="Common.Organ"%>
<%@page import="Control.UserManage"%>
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
	ArrayList<Organ> viewOrganInfoList =  userManaser.requserOrganlist(projectUserIDList);
	
%>

<!-- 여기서부터는 사용자에게 보여질 유저 정보 목록입니다. -->

<% 	if(viewOrganInfoList == null)
	{
%>
		<h2 style="text-align:center;">참여 기관의 정보목록이 없습니다.</h2>
<%	}
	else
	{

%>
	<table style=margin:auto;>
		<tr>
			<th>참여 기관명</th>
			<th>참여 기관ID</th>
			<th>대표자</th>
			<th>지역코드</th>
			<th>FAX</th>
		</tr>
		
<%
		for(Organ x : viewOrganInfoList)
		{
			
		
%>
			<tr>
				<td><%= x.getOrganName() %></td>
				<td><%= x.getOrganId() %></td>
				<td><%= x.getPresident() %></td>
				<td><%= x.getRegionCode() %></td>
				<td><%= x.getFax() %></td>
			</tr>
<%		}
%>

	</table>
	
<%	
		}
	}
%>

</body>
</html>
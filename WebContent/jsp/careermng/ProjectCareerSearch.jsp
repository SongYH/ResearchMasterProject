
<%@page import="Control.ProjectManager"%>
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
<title>이력관리-검색</title>
</head>
<body>
<%@include file="/jsp/common/Main.jsp"%>
<%
	ProjectManager projectManager = new ProjectManager();
	if(loginId == null || loginId == "") 
	{
		 out.println("<script>alert('로그인이 필요합니다.')</script>");
		response.sendRedirect("/ResearchMasterProject/jsp/common/login.jsp");
	}else { // 로그인 되있는 상태면
		String permission = projectManager.reqUserPermission(loginId);		// 권한을 아이디를 통해 가져옴 
		if(permission.equals("과제담당자") || permission.equals("과제책임자") || permission.equals("참여연구원"))
		{
		} // 과제책임자 or 참여연구원 or 과제책임자

		else // 과제담당자  책임자 둘다 아닐떄
	 	{ // 팝업창 띄우고 Home 화면으로 가기
			 out.println("<script>alert('권한이 없습니다.'); location.href = '/ResearchMasterProject/jsp/common/Home.jsp'; </script>"); 
	 	}
	} 
	
%>
<div id="contents">
	<h1 style="text-align:center;">과제 이력 검색</h1>
	<form action="/ResearchMasterProject/jsp/careermng/ProjectCareerSearch.jsp" method="post" name="searchInputs">
		<table style="width:30%; margin-left:40%; margin-right:30%;">
			<tr>
			
				검색 시작, 종료일 : <input type="text" name="startDate" placeholder="ex)yyyy-mm-dd 부터" /> ~ <input type="text" name="endDate" placeholder="ex)yyyy-mm-dd 까지" />
				<br>	
				과제 상태   :
				<select name="status">
<!--
 과제상태
-등록승인
-중간평가계획
-중간평가완료
-증빙자료입력
-정산완료
-최종평가계획
-최종평가완료 
-->
						<option value="모두" selected>모두</option>
						<option value="등록승인">등록승인</option>
						<option value="중간평가계획">중간평가계획</option>
						<option value="중간평가완료">중간평가완료</option>
						<option value="증빙자료입력">증빙자료입력</option>
						<option value="정산완료">정산완료</option>
						<option value="최종평가완료">최종평가계획</option>
						<option value="최종평가완료">최종평가완료</option>
				</select>
				<br>	
				과제 번호 : <input type="text" name="projectNumber" placeholder="8자리 숫자" />
				<input type="submit" name="searchButton" value="이력검색"/>
				</td>
			</tr>
		</table>
	</form>
</div>

<%
	String startDate = request.getParameter("startDate");
	String endDate = request.getParameter("endDate");
	String Status = request.getParameter("status");
	String projectNumber = request.getParameter("projectNumber");

	if(startDate==null || endDate==null ||Status==null ||projectNumber==null )
	{
		startDate="0000-00-00";
		endDate="0000-00-00";
		Status="모두";
		projectNumber="-1";
	}
	
	if((startDate.equals("")))
	{
		startDate="0000-00-00";
	}
	
	if((endDate.equals("")))
	{
		endDate="9999-99-99";
	}

	

	SimpleDateFormat transFormat = new SimpleDateFormat("yyyy-MM-dd");
	
	Date StartDate = (Date)transFormat.parse(startDate);
	Date EndDate = (Date)transFormat.parse(endDate);
	int ProjectNumber=0;
	
	
	if((projectNumber.equals("")))
	{
		ProjectNumber=-1;
	}
	else
	{
		ProjectNumber = Integer.parseInt(projectNumber);
	}
	
	CareerManager manager = new CareerManager();
	

	ArrayList<ProjectCareer> viewElements = manager.requestProjectCareerList(StartDate, EndDate, Status, ProjectNumber);

%>

<!-- 여기서부터는 사용자에게 보여질 과제 목록입니다. -->

<div id="contents">

	<h1 style="text-align:center;">=과제 이력 검색 목록=</h1>
<% 	if(viewElements == null)
	{
%>
		<h2 style="text-align:center;">해당하는 과제이력이 없습니다.</h2>
<%	}
	else
	{

%>
	<table style=margin:auto;>
		<tr>
			<th>이력일시</th>
			<th>과제상태</th>
			<th>과제종류</th>
			<th>과제번호</th>
			<th>과제명</th>
			<th>과제책임자ID</th>
			<th>기관명</th>
			<th>정보통합조회</th>
		</tr>
		
<%
		for(ProjectCareer x : viewElements)
		{
			Project p = x.getProjectInfo();
			
	
			   Date DateRecordDate = x.getRecordDate();
			    DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			   String StringRecordDate =formatter.format(DateRecordDate);

		
%>
			<tr>
				<td><%= StringRecordDate %></td>
				<td><%= p.getStatus() %></td>
				<td><%= p.getType() %></td>
				<td><%= p.getProjectNumber() %></td>
				<td><%= p.getProjectName() %></td>
				<td><%= p.getLeaderId() %></td>
				<td><%= p.getOrganName() %></td>
				<td>
				
				<form method=post action=ProjectUnityInfoView.jsp>
  <input type=hidden name=recordDate value=<%=StringRecordDate %>>
  <input type=hidden name=projectNumber value=<%=p.getProjectNumber() %>>
  <input type=submit value=이력정보통합조회>
  </form>
  </td>
			</tr>
<%		}
%>

	</table>
	
<%	}
%>
</div>
			
</body>
</html>
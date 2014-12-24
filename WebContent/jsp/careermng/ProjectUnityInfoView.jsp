
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
<title>이력관리-통합조회</title>

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

<%
	String recordDate = request.getParameter("recordDate");
	String projectNumber = request.getParameter("projectNumber");

	SimpleDateFormat transFormat = new SimpleDateFormat("yyyy-MM-dd");
	
	Date RecordDate = (Date)transFormat.parse(recordDate);

	int ProjectNumber=0;
		ProjectNumber = Integer.parseInt(projectNumber);

	CareerManager manager = new CareerManager();
	

	ProjectCareerUnityInfo viewElementsInfo = manager.requestProjectCareerUnityInfo(RecordDate, ProjectNumber);

%>

<!-- 여기서부터는 사용자에게 보여질 과제 이력입니다. -->

<div id="contents">
<h1 style="text-align:center;"><%= recordDate %>이력 통합 정보 조회</h1>

<% 	if(viewElementsInfo == null)
	{
%>
		<h2 style="text-align:center;">해당하는 과제이력 정보가 없습니다.</h2>
<%	}
	else
	{

%>
<table>
<tr>
<td>
<h1 style="text-align:left;">과제정보</h1>
-과제-<br>
과제명 :<%= viewElementsInfo.getProjectCareer().getProjectInfo().getProjectName() %><br>
과제 번호 :<%= viewElementsInfo.getProjectCareer().getProjectInfo().getProjectNumber() %><br>
과제 상태 :<%= viewElementsInfo.getProjectCareer().getProjectInfo().getStatus() %><br>
과제 등록일 :<%= viewElementsInfo.getProjectCareer().getProjectInfo().getRegisterDate() %><br>
과제 기간 :<%= viewElementsInfo.getProjectCareer().getProjectInfo().getArea() %><br>
과제 종류 :<%= viewElementsInfo.getProjectCareer().getProjectInfo().getType() %><br>
협약 기간 :<%= viewElementsInfo.getProjectCareer().getProjectInfo().getAgreeYear() %><br>
관련 파일 :<%= viewElementsInfo.getProjectCareer().getProjectInfo().getFileName() %><br>
<br>
사업비 총액:
<%
if(viewElementsInfo.getBudgetCareer()==null)
	%>정보 없음.<br><%
	else
	{
	%><%=viewElementsInfo.getBudgetCareer().getBudgetInfo().getAmount() %> <%
	}
%>
<br>
-주관기관-<br>
기관명 :<%= viewElementsInfo.getProjectCareer().getProjectInfo().getProjectName() %><br>
사업자번호 :<%= viewElementsInfo.getProjectCareer().getProjectInfo().getOrganId() %><br>
<form method=post action=ProjectOrganInfoView.jsp>
  <input type=hidden name=recordDate value=<%=recordDate%>>
  <input type=hidden name=projectNumber value=<%=projectNumber%>>
  <input type=submit value=참여기관정보조회>
  </form>
  <br>
  
-과제 책임자-<br>
책임자명 :<%= viewElementsInfo.getProjectCareer().getProjectInfo().getLeaderName() %><br>
책임자ID :<%= viewElementsInfo.getProjectCareer().getProjectInfo().getLeaderId() %><br>
<form method=post action=ProjectUserInfoView.jsp>
  <input type=hidden name=recordDate value=<%=recordDate%>>
  <input type=hidden name=projectNumber value=<%=projectNumber%>>
  <input type=submit value=참여구성원정보조회>
  </form>
<br>
</td>
</tr>
<%
}
%>

<tr>
<td>
<h1 style="text-align:left;">평가 정보</h1>
-중간 평가 결과-<br>
<%
if(viewElementsInfo.getFinEvalResult()==null)
	%>정보 없음.<br><%
	else
	{
	%>
	중간 평가 결과 :<%=viewElementsInfo.getFinEvalResult().getResult() %> <br>
	중간 평가 파일:<%=viewElementsInfo.getFinEvalResult().getFileName() %> <br>
	<%
	}
%>
<br>
-최종 평가 결과-<br>
<%
if(viewElementsInfo.getFinEvalResult()==null)
	%>정보 없음.<br><%
	else
	{
	%>
	최종 평가 결과 :<%=viewElementsInfo.getFinEvalResult().getResult() %> <br>
	최종 평가 파일:<%=viewElementsInfo.getFinEvalResult().getFileName() %> <br>
	<%
	}
%>
<br>
</td>
</tr>

<tr>
<td>
<h1 style="text-align:left;">정산 정보</h1>
-정산 결과-<br>
<%
if(viewElementsInfo.getCalculate()==null)
	%>정보 없음.<br><%
	else
	{
	%>
	실인정 총액 :<%=viewElementsInfo.getCalculate().getAmount() %> <br>
	정산일:<%=viewElementsInfo.getCalculate().getDate() %> <br>
	<%
	}
%>

<br>

-환급 상태-<br>
<%
if(viewElementsInfo.getClawback()==null)
	%>정보 없음.<br><%
	else
	{
	%>
	환급 총액 :<%=viewElementsInfo.getClawback().getAmount() %> <br>
	환급 비율:<%=viewElementsInfo.getClawback().getRate() %> <br>
	환급일:<%=viewElementsInfo.getClawback().getDate() %> <br>
	<%
	}
%>
<br>
</td>
</tr>

</table>

</div>
			
</body>
</html>
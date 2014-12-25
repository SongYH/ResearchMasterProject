<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.Date" %>
<%@ page import="Common.Project" %>
<%@ page import="Common.Expert" %>
<%@ page import="Control.MidEvalManager" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title></title>
</head>
<body>
<%@include file="/jsp/common/Main.jsp"%>
<%
	request.setCharacterEncoding("UTF-8");
	String sel = request.getParameter("rd");
	String expertLeaderId = request.getParameter("expertId"+sel);
	String expertLeaderEmail = request.getParameter("expertEmail"+sel);
	int selProjectNum = Integer.parseInt(request.getParameter("projectNum"));
	String selLeaderId = request.getParameter("leaderId");
	
	String fileName = (String)request.getParameter("inputFile");
	int year = Integer.parseInt((String)request.getParameter("inputYear"));
	int month = Integer.parseInt((String)request.getParameter("inputMonth"));
	int date = Integer.parseInt((String)request.getParameter("inputDate"));
	
	Date noticeDate = new Date();
	noticeDate.setYear(year-1900); noticeDate.setMonth(month-1); noticeDate.setDate(date);
	
	
	
	
	
	ArrayList<String> expertsId = new ArrayList<String>();
	ArrayList<String> expertsEmail = new ArrayList<String>();
	
	
	for(int i=0; i<6; i++)
	{
		if(i==Integer.parseInt(sel))
		{
			expertLeaderId = request.getParameter("expertId"+sel);
			expertLeaderEmail = request.getParameter("expertEmail"+sel);
		}
		else
		{
			expertsId.add(request.getParameter("expertId"+String.valueOf(i)));
			expertsEmail.add(request.getParameter("expertEmail"+String.valueOf(i)));
		}
	}
	
	MidEvalManager midManager = new MidEvalManager();
	
	boolean result = midManager.requestAddMidEvalPlan(selProjectNum, expertLeaderId, expertLeaderEmail, expertsEmail, fileName, noticeDate);
	
%>
<% if(result){%>
	<h2>평가계획추가 완료</h2>
  <%}else{ %>
  	<h2>평가계획추가 실패</h2>
  	<%} %>
</body>
</html>
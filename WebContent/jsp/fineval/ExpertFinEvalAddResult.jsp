<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList" %>
<%@ page import="Control.FinEvalManager" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
</head>
<body>
<%@include file="/jsp/common/Main.jsp"%>
<%
	request.setCharacterEncoding("UTF-8");
	int projectNum = Integer.parseInt(request.getParameter("selProjectNum"));
	String fileName = request.getParameter("inputFile");
	String result = request.getParameter("rd");
	
	FinEvalManager finManager = new FinEvalManager();
	
	boolean inputResult = finManager.requestAddFinEvalResult(projectNum, result, fileName);
	
%>
<% if(inputResult){%>
	<h2>평가결과추가 완료</h2>
  <%}else{ %>
  	<h2>평가결과추가 실패</h2>
  	<%} %>
</body>
</html>
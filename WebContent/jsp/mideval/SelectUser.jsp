<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="Control.MidEvalManager" %>
<%@ page import="Common.Project" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.Date" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>
</head>
<body>
<%@include file="/jsp/common/Main.jsp"%>
<% 
	request.setCharacterEncoding("UTF-8");
	
	if(loginId == null || loginId == "")
	{
		response.sendRedirect("/ResearchMasterProject/jsp/common/login.jsp");
	}
	else
	{
		MidEvalManager midManager = new MidEvalManager();
		String permission = midManager.reqUserPermission(loginId);
	
		if(permission.equals("평가담당자"))
		{
			response.sendRedirect("/ResearchMasterProject/jsp/mideval/ChargerMidEvalSelectProject.jsp");
		} // 과제책임자 일때 if 문 끝
		else if(permission.equals("평가위원장"))
		{
			response.sendRedirect("/ResearchMasterProject/jsp/mideval/ExpertMidEvalSelectProject.jsp");
		}
		else // 과제담당자  책임자 둘다 아닐떄
	 	{ // 팝업창 띄우고 Home 화면으로 가기
			 out.println("<script>alert('권한이 없습니다.'); location.href = '/ResearchMasterProject/jsp/common/Home.jsp'; </script>"); 
	 	}
	}
	
%>
</body>
</html>
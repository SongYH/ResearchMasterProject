<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html><html>
<%@ page import="Control.SubjectManager"%>
<!-- jsp 코드부분 -->
<%	
	request.setCharacterEncoding("UTF-8"); 
%>
<%
	int organId = Integer.parseInt(request.getParameter("organId"));
	String type = request.getParameter("type");
	String area = request.getParameter("area");
	String name = request.getParameter("name");
	String id = request.getParameter("id");
	String status = request.getParameter("status");
	String fileName = request.getParameter("fileName");
	
	SubjectManager sm = new SubjectManager();
	sm.request_AddpreProject(organId, fileName, id, type, area, name);
%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="/ResearchMasterProject/css/style.css" />
<title>과제 신청</title>
</head>
<body>
<%@include file="/jsp/common/Main.jsp"%>
<br><br>
<font size="4"><center>과제 등록이 완료되었습니다.</center></font><br>
<font size="4"><a href = "/ResearchMasterProject/jsp/proposal/Application.jsp">확인</a></font>
</body>
</html>
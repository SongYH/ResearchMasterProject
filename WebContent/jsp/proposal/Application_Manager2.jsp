<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ page import="java.util.ArrayList"%>
<%@ page import="Common.Subject" %>
<%@ page import="Control.SubjectManager"%>
<%@ page import="java.util.Date" %>

<!-- jsp 코드부분 -->
<%	
	request.setCharacterEncoding("UTF-8"); 
%>
<%
	String type = request.getParameter("type");
	String area = request.getParameter("area");
	String name = request.getParameter("name");
	
	SubjectManager sm = new SubjectManager();
	String id = "id1";
	int availability = sm.request_Application_Availability(id, name, type, area);
	int organId = sm.request_organId(id);
%>
<!-- jsp 끝 -->

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="/ResearchMasterProject/css/style.css" />
<title>해당신청대상과제목록</title>
</head>
<body>
<%@include file="/jsp/common/Main.jsp"%>
	<% if(availability == 1){ %><br><br>
		<font size="4"><center>
		기관이 등록되어 있지 않은 사용자입니다.
		<br>
		과제 신청이 불가합니다.
		<br><br>
		<font size="4"><a href = "/ResearchMasterProject/jsp/proposal/Application.jsp">확인</a></font>
		</center></font>
	<%}else if(availability == 2){ %><br><br>
		<font size="4"><center>
		이미 사용자의 기관에서 지원한 사업입니다. 
		<br>
		과제 신청이 불가합니다.
		<br><br>
		<font size="4"><a href = "/ResearchMasterProject/jsp/proposal/Application.jsp">확인</a></font>
		</center></font>
	<%}else if(availability == 0){ %>
	<form method = "post" action="/ResearchMasterProject/jsp/proposal/Application_Manager3.jsp">
		<b>과제 신청</b>
		<table>	
		<tr>
		<td>분 야</td>
		<td>: <%=type %></td>
		</tr>
	
		<tr>
		<td>유 형</td>
		<td>: <%=area %></td>
		</tr>
	
		<tr>
		<td>사업명</td>
		<td>: <%=name %></td>
		</tr>	
	
		<tr>
		<td>신청자 ID</td>
		<td>: <%=id %></td>
		</tr>
		
		<tr>
		<td>사업자 번호</td>
		<td>: <%=organId %></td>
		</tr>
	
		<% String status = "선정평가통과"; %>	
	
		<tr>
		<td>업로드 파일</td>
		<td>: <INPUT type="text" name="fileName"></td>
		</tr>
		</table>
		<input type="hidden" value="<%=organId %>" name = "organId">
		<input type="hidden" value="<%=type %>" name = "type">
		<input type="hidden" value="<%=area %>" name = "area">
		<input type="hidden" value="<%=name %>" name = "name">
		<input type="hidden" value="<%=id %>" name = "id">
		<input type="hidden" value="<%=status %>" name = "status">
		<INPUT type="submit" value="등록">
		</form>
		<%} %>
</body>
</html>

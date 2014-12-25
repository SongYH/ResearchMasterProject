<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ page import="Control.SubjectManager"%>
<%@ page import="Common.PreProject" %>
<%@ page import="java.util.ArrayList" %>
<%	
	response.setContentType("text/html; charset=UTF-8");
	request.setCharacterEncoding("UTF-8"); 
	String id = (String)session.getAttribute("loginId");    // 아이디 가져오기
	SubjectManager um = new SubjectManager();
	ArrayList<PreProject> pl = um.request_PreProjectList(id);
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="/ResearchMasterProject/css/style.css" />
<title>신청과제 조회</title>
</head>
<body>
<%@include file="/jsp/common/Main.jsp"%>

	<% if(id == null){ %>
		<font size="4">
		<br>
		<center>로그인이 필요한 서비스입니다.</center>
		<br>
		<font size="4"><a href = "/ResearchMasterProject/jsp/common/login.jsp"><center>확인</center></a></font>
		</font>
	<% }else{ %>
	<br>
	<table style=margin:auto; border = "1">
	<caption><b>신청과제 조회내역</b></caption>
	<tr>
	<th>번호</th>
	<th>사업자번호</th>
	<th>사업명</th>
	<th>상태</th>
	<th>분야</th>
	<th>유형</th>
	<th>아이디</th>
	<th>파일명</th>
	<th>정보수정</th>
	</tr>
	
	<% for(int i = 0; i < pl.size(); i++){ %>
		<tr>
		<td><center><%=i+1%></center></td>
		<td><center><%=pl.get(i).getOrganId() %></center></td>
		<td><%=pl.get(i).getSubjectName() %></td>
		<td><%=pl.get(i).getStatus() %></td>
		<td><%=pl.get(i).getType() %></td>
		<td><%=pl.get(i).getArea() %></td>
		<td><%=pl.get(i).getId() %></td>
		<td><%=pl.get(i).getFileName() %></td>
		<td><center><a href ="#">수정</a></center></td>
		</tr>
	<%}
	}
	%>
	</table>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<%	
	response.setContentType("text/html; charset=UTF-8");
	request.setCharacterEncoding("UTF-8"); 
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="/ResearchMasterProject/css/style.css" />
<title>신청</title>
</head>
<body>
<%@include file="/jsp/common/Main.jsp"%>
	<table style=margin:auto;>
	<tr>
	<td><center><font size="5"><b>산업과제표준분류</b></font></center></td>
	</tr>
	<td>
		<form method="post" name="selectType" action="/ResearchMasterProject/jsp/proposal/Application_Manager.jsp">
			<input type = "radio" name = "type" value = "전체" checked/>전체 &nbsp;
			<input type = "radio" name = "type" value = "선택집중형"/>선택집중형 &nbsp;
			<input type = "radio" name = "type" value = "저변확대형"/>저변확대형 &nbsp;
			<input type = "radio" name = "type" value = "인프라조성형"/>인프라조성형 &nbsp;
			<input type = "submit" name = "select" value = "선택">
		</form>
	</td>
	</table>
</body>
</html>
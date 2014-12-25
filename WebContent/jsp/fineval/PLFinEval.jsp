<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="Control.FinEvalManager" %>
<%@ page import="Common.FinEvalResult" %>
<%@ page import="Common.Project" %>
<%@ page import="java.util.ArrayList" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>평가결과 조회</title>

</head>
<body>
<%@include file="/jsp/common/Main.jsp"%>
<% 
	request.setCharacterEncoding("UTF-8");
	
	FinEvalManager finManager = new FinEvalManager();
	ArrayList<FinEvalResult> finResult = new ArrayList<FinEvalResult>();
	finResult = finManager.requestFinEvalResultForPl(loginId);
	
%>
<div id="contents">
	<form method="post" name="chargerInput" action="/ResearchMasterProject/jsp/common/Main.jsp">
		<%if(finResult.size()==0){ %>
			<h2>평가 결과가 없습니다.</h2>
			<%}else{ %>
		<table>
			<tr>
				<td>번호</td><td>PL아이디</td><td>결과문서</td><td>평가결과</td>
			</tr>
			<%for(int i=0; i<finResult.size(); i++){ %>
			<tr>
				<td><%= finResult.get(i).getProjectNumber() %></td>
				<td><%= finResult.get(i).getProjectLeaderId() %></td>
				<td><%= finResult.get(i).getFileName() %></td>
				<td><%= finResult.get(i).getResult() %></td>
			</tr>
			<%} %>
			<tr>
				<td align="center"><input type="submit" name="submit" value="확인" /></td>
			</tr>
		</table>
		<%} %>
	</form>
</div>
</body>
</html>
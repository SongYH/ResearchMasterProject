<%@page import="Control.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="Common.*"
	import="task_registration.*"%>

<HTML>
<HEAD>
<TITLE>사업비정산입력 beans</TITLE>
</HEAD>
<script language=JavaScript src="../../js/calculation/calculation.js"></script>

<BODY>
<%@include file="/jsp/common/Main.jsp"%>
	<%
		request.setCharacterEncoding("UTF-8");
			String strProjectNumber = request
			.getParameter("select_project_number"); // 선택된 과제번호
			int selectProjectNumber = Integer.parseInt(strProjectNumber);	// 과제번호를 integer형태로 변형
			CalculationManager cm = new CalculationManager();	//  사업비 정산관리 객체 생성
			ProjectManager pm = new ProjectManager();			// 	과제관리 객체 생성
			Budget bg = pm.reqBudgetSearchNumber(selectProjectNumber);	// 과제번호에 해당하는 사업비예산정보 요청
			Evidence ev = cm.requestEvidence(selectProjectNumber);			// 과제번호에 해당하는 사업비증빙자료정보 요청 
	%>
	<form name=calculationInput method=post action="calculation.js">
		<h3>사업비 정산 결과 beans</h3>
		선택된 과제번호 :
		<%=selectProjectNumber%>
		<br> 사업비예산 신청액 :
		<%=bg.getAmount()%>
		<br> 사업비 총 집행금액 :
		<%=ev.getAmount()%>
		<br> 실인정 여부 <input type=checkbox name="verification" value=실인정> <%//실인정 여부 check box %>
		<br> 영수증 파일 : <%=ev.getFile() %>
		
		<input type=button value="정산" onClick="calculationInputCheck()"><br>
		<input type=hidden name="select_project_number"	
			value=<%=selectProjectNumber%>>
	</form>
</BODY>
</HTML>
<%@page import="Control.CalculationManager"%>
<%@page import="Common.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="Control.*" import="task_registration.*"%>
<%@ page import="java.util.Enumeration"%>

<HTML>
<HEAD>
<TITLE>사업비정산 완료 beans</TITLE>
</HEAD>
<script language=JavaScript src="../../js/calculation/calculation.js"></script>

<BODY>
	<%@include file="/jsp/common/Main.jsp"%>
	<%
		request.setCharacterEncoding("UTF-8");
	%>

	<h3>사업비 정산완료</h3>
	<%
		String strProjectNumber = request
				.getParameter("select_project_number"); // 선택된 과제번호 
		int selectProjectNumber = Integer.parseInt(strProjectNumber);	// 선택된 과제번호 integer형으로 변환
		CalculationManager cm = new CalculationManager();			// 사업비정산관리 객체 생성
		ProjectManager pm = new ProjectManager();					//	과제관리 객체 생성
		Budget bg = pm.reqBudgetSearchNumber(selectProjectNumber);	// 과제번호로 사업비 예산정보 요청
		Evidence ev = cm.requestEvidence(selectProjectNumber);				// 과제번호로 사업비 예산증빙자료 요청
		Clawback ca = cm.requestClawback(selectProjectNumber);			// 과제번호로 사업비 환수금정보 요청
		Calculate cu = cm.requestCalculate(selectProjectNumber);			// 과제번호로 정산정보 요청 
	%>


	<form name=calculateResult method=post action="calculation.js">
		<h3>사업비 증거자료 입력 beans</h3>
		선택된 과제번호 :
		<%=selectProjectNumber%>
		<br> 사업비예산 신청액 :
		<%=bg.getAmount()%>
		<br> 사업비 총 집행금액 :
		<%=ev.getAmount()%>
		<%
			if (cu.isVerification() == true) {	// 사업비 인정 일때 
		// 인정된 결과 %>
		인정
		<%
			} else { // 사업비 불인정일때 
		%>
		불인정
		<%
			}
		%><br> 환수금액 :
		<%=ca.getAmount()%>
		<br> 이자율 :
		<%=ca.getRate()%>
		% <br>
	</form>
</BODY>
</HTML>
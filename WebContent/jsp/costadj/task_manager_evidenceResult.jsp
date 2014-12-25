<%@page import="Control.ProjectManager"%>
<%@page import="Control.CalculationManager"%>
<%@page import="Common.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="Entity.*" import="task_registration.*"%>
<%@ page import="java.util.Enumeration"%>

<HTML>
<HEAD>
<TITLE>사업비증거입력 완료 beans</TITLE>
</HEAD>
<script language=JavaScript src="../../js/calculation/evidence.js"></script>

<BODY>
	<%@include file="/jsp/common/Main.jsp"%>
	<%
		request.setCharacterEncoding("UTF-8");
	%>

	<%
		String strProjectNumber = request
				.getParameter("select_project_number"); // 선택된 과제번호
		int selectProjectNumber = Integer.parseInt(strProjectNumber); // 선택된 과제번호 integer형태로 변환
		CalculationManager cm = new CalculationManager(); // 정산 관리 객체 생성
		ProjectManager pm = new ProjectManager(); // 과제관리 객체 생성
		Budget bg = pm.reqBudgetSearchNumber(selectProjectNumber); // 사업비예산정보를 과제번호를  통해 받아옴
		Evidence ev = cm.requestEvidence(selectProjectNumber); // 사업비 증빙자료를 과제번호를 통해 받아옴
	%>
	<div>
		<form name=evidenceInput method=post action="evidence.js">
			<!-- 사업비 증거정보를 출력  -->
			<h3>사업비 증거입력 완료 beans</h3>
			선택된 과제번호 :
			<%=selectProjectNumber%>
			<br> 사업비예산 신청액 :
			<%=bg.getAmount()%>
			<br> 증거파일 :
			<%=ev.getFile()%>
			<br> 사업비 총 집행금액 :
			<%=ev.getAmount()%>

		</form>
	</div>
</BODY>
</HTML>
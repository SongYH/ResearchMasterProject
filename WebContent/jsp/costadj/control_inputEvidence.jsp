<%@page import="Common.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="Control.*"
	import="task_registration.*"%>
<%@ page import="java.util.Enumeration"%>

<HTML>
<HEAD>
<TITLE>사업비증거입력 완료 beans</TITLE>
</HEAD>
<script language=JavaScript src="../../js/calculation/calculation.js"></script>
<BODY>
	<%
		request.setCharacterEncoding("UTF-8");
		String strProjectNumber = request
			.getParameter("select_project_number");		// 선택된 과제번호 
			String strEvidenceAmount = request.getParameter("evidence_amount");	// 증빙금액 요청
			String strEvidenceFile = "";	// 증빙자료 파일 
			strEvidenceFile = request.getParameter("upfile");	// 증빙자료 파일 받기
			int selectProjectNumber = Integer.parseInt(strProjectNumber);	// 과제번호 integer로 변환
			int evidenceAmount = Integer.parseInt(strEvidenceAmount);		// 증빙금액 integer로 변환
			CalculationManager cm = new CalculationManager();			// 사업비 정산 관리 객체 생성 
			ProjectManager pm = new ProjectManager();						// 과제관리 객체 생성
			Budget bg = pm.reqBudgetSearchNumber(selectProjectNumber);	// 사업비 예산정보를 과제번호를 통해 요청
			Evidence ev = new Evidence(selectProjectNumber, evidenceAmount,strEvidenceFile);	// 받아온 자료를 통해 사업비 증빙자료 정보를 생성 
			cm.addEvidence(ev);		// 사업비 증빙자료 정보를 증빙자료목록에 추가
			pm.setChangeProjectStatus(selectProjectNumber, "증빙자료입력");	// 선택된 과제의 상태를 증빙자료 입력상태로 변경 요청
			// 과제등록컨트롤 -> 과제 상태 증빙자료제출상태로 변경
			response.sendRedirect("task_manager_evidenceResult.jsp?select_project_number="+selectProjectNumber);	// 사업비 정산담당자가 아니면 에러화면으로 갱신 
	%>
	
</BODY>
</HTML>
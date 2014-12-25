<%@page import="Common.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="Control.*"%>

<HTML>
<HEAD>
<TITLE>사업비증거자료 beans</TITLE>
</HEAD>
<script language=JavaScript src="../../js/calculation/evidence.js"
	charset="UTF-8"></script>

<BODY>
	<%@include file="/jsp/common/Main.jsp"%>
	<%
		request.setCharacterEncoding("UTF-8");
		String strProjectNumber = request
				.getParameter("select_project_number"); // 선택된 과제번호
		int selectProjectNumber = Integer.parseInt(strProjectNumber); // 과제번호 integer형태로 변환
		CalculationManager cm = new CalculationManager(); // 정산관리 객체 생성
		ProjectManager pm = new ProjectManager(); // 과제관리 객체 생성
		Budget bg = pm.reqBudgetSearchNumber(selectProjectNumber); // 사업비 예산정보를 과제번호를 통해 요청
	%>
	<div>
		<form name=evidenceInput method=post action="evidence.js">
		<input type="text" onkeydown="return showKeyCode(event)">
		   <script>
        function showKeyCode(event) {
            event = event || window.event;
            var keyID = (event.which) ? event.which : event.keyCode;
            if( ( keyID >=48 && keyID <= 57 ) || ( keyID >=96 && keyID <= 105 ) )
            {
                return;
            }
            else
            {
                return false;
            }
        }
    </script>
			<input type=hidden name="select_project_number"
				value=<%=selectProjectNumber%>>

			<h3>사업비 증거자료 입력 beans</h3>
			선택된 과제번호 :
			<%=selectProjectNumber%>
			<br> 사업비예산 신청액 :
			<%=bg.getAmount()%>
			<br> <input type="file" name="upfile" size="20">(파일의 용량
			제한 : 20M) <br> 사업비 총 집행금액 : <input type=text
				name="evidence_amount" value="" size=20 
				OnKeypress="onlyNumber()"> <input type=button
				value="사업비 집행금액 입력" onClick="evidenceInputCheck()"><br>

		</form>
	</div>
</BODY>
</HTML>
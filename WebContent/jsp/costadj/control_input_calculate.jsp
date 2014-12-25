<%@page import="com.sun.org.apache.bcel.internal.classfile.PMGClass"%>
<%@page import="Common.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="Control.*" import="task_notice.*"
	import="task_registration.*" import="java.util.Date"%>
<%@ page import="java.util.Enumeration"%>

<HTML>
<HEAD>
<TITLE>정산 control</TITLE>
</HEAD>
<BODY onload="calculationControlCheck()">
	<%
		request.setCharacterEncoding("UTF-8");
	%>

	<%
		String strProjectNumber = request
			.getParameter("select_project_number"); // 선택한과제 
			String strVerification = request.getParameter("verification"); // 실 인정정보 
			boolean verification = false;	// 실인정 정보 false로 초기화
			if (strVerification == null)		// 실인정 정보의값이 null일때 불인정
				verification = false;	
			else
				verification = true;			// 실인정 정보의 값이 있을대 인정
				
			int selectProjectNumber = Integer.parseInt(strProjectNumber);	 // 선택한 과제의 값을 str-> integer형으로 변환
				
			CalculationManager cm = new CalculationManager();	// 정산을 진행하는 컨트롤객체 선언
			Evidence e = cm.requestEvidence(selectProjectNumber);	// 과제에 해당하는 증빙자료 요청
			Calculate c = new Calculate(e.getProjectNumber() , e.getAmount(), verification);	// 과제에 해당하는 정산정보 생성
		
			if(verification==false)	// 불인정일때 정산금액 0 원
				c.setAmount(0);
			
			cm.addCalculate(c); // 정산목록에 정산한내용을 추가함
		
			int amount = cm.requestProjectVerificationAmount(selectProjectNumber);	//환수금을 계산을 요청
			Clawback cb = new Clawback(c.getProjectNumber(),amount);					//환수금정보 생성
			cm.addClawback(cb);																			// 환수금 목록에 추가
			ProjectManager pm = new ProjectManager();											// 과제등록을 관리하는 객체 생성
			UserManage um = new UserManage();													// 사용자들을 관리하는 객체 생성
			String email = um.reqProjectLeaderEmail(pm.reqProjectSearchNumber(selectProjectNumber).getLeaderId()); // 과제번호로부터 과제책임자의 id를 요청하고,
																																								  // 요청한 id로 부터  email을 추출함
			cm.addNotice(new Notice(new Date(),"정산",strProjectNumber+"번호 사업비정산 완료", 				// 공지에 현재시간과 정산정보와 이메일 내용을 입력하고 목록에 추가.
			email,"사업비 정산 완료File.hwp"));
			pm.setChangeProjectStatus(selectProjectNumber, "정산완료");												// 현재과제번호를 정산완료상태로 변경
	%>
	<form name=calculationControl method=post action="evidence.js">
		<!-- 출력 바운더리에 넘겨주기위한 hidden type의 과제선택번호 -->
		<input type=hidden name="select_project_number"
			value=<%=selectProjectNumber%>>
	</form>
</BODY>
<script type="text/javascript">
function calculationControlCheck() {
	document.calculationControl.action = "resultCalculation.jsp";
	document.calculationControl.method = "post";
	document.calculationControl.submit();
}
</script>
</HTML>

function evidenceInputCheck() {
	if ( document.evidenceInput.evidence_amount.value=="" ) {
		alert("집행금액을 입력하세요.");
		document.evidenceInput.evidence_amount.focus();
		return;
	}
		
	document.evidenceInput.action="control_inputEvidence.jsp";
	document.evidenceInput.method="post";
	document.evidenceInput.submit();
}


function evidenceControlCheck() {
	
	document.evidenceControl.action="task_manager_evidenceResult.jsp";
	document.evidenceControl.method="post";
	document.evidenceControl.submit();
}

function calculationInputCheck() {
	document.calculationInput.action="control_input_calculate.jsp";
	document.calculationInput.method="post";
	document.calculationInput.submit();
}

function calculationControlCheck() {
	document.calculationControl.action="resultCalculation.jsp";
	document.calculationControl.method="post";
	document.calculationControl.submit();
}


function searchCalculation() {
	if ( document.searchCalculate.select_project_number.value=="" ) {
		alert("과제번호를 입력하세요");
		document.searchCalculate.select_project_number.focus();
		return;
	}
		
	document.searchCalculate.action="resultCalculation.jsp";
	document.searchCalculate.method="post";
	document.searchCalculate.submit();
}
function selectProjectNumber() {
	document.selectProject.action="calculation_admin_inputCalculation.jsp";
	document.selectProject.method="post";
	document.selectProject.value = document.selectProject.
	document.selectProject.submit();
}
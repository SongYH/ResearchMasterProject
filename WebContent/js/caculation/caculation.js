
function caculationInputCheck() {
	document.caculationInput.action="control_input_caculate.jsp";
	document.caculationInput.method="post";
	document.caculationInput.submit();
}

function caculationControlCheck() {
	document.caculationControl.action="resultCaculation.jsp";
	document.caculationControl.method="post";
	document.caculationControl.submit();
}


function searchCaculation() {
	if ( document.searchCaculate.select_project_number.value=="" ) {
		alert("과제번호를 입력하세요");
		document.searchCaculate.select_project_number.focus();
		return;
	}
		
	document.searchCaculate.action="resultCaculation.jsp";
	document.searchCaculate.method="post";
	document.searchCaculate.submit();
}
function selectProjectNumber() {
	document.selectProject.action="caculation_admin_inputCaculation.jsp";
	document.selectProject.method="post";
	document.selectProject.value = document.selectProject.
	document.selectProject.submit();
}
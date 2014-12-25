<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList" %>
<%@ page import="Common.Project" %>
<%@ page import="Common.Expert" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>평가결과 입력</title>
<script language="javascript">
function checkInput(midEvalResultInput)
{
	var result=false;
	var radioBtn = midEvalResultInput.rd;
	for(var i=0; i<radioBtn.length; i++)
	{
		if(radioBtn[i].checked==true)
		{	result = true;	break; }
	}
	
	if(midEvalResultInput.inputFile.value=="")
	{ alert("평가결과파일을 입력하세요!"); }
	else if(!result)
	{ 
		alert("평가결과를 선택하세요!");
	}
	else
	{
		document.midEvalResultInput.action="/ResearchMasterProject/jsp/mideval/ExpertMidEvalAddResult.jsp";
		document.midEvalResultInput.method="post";
		document.midEvalResultInput.submit();
	}
}
</script>
</head>
<body>
<%@include file="/jsp/common/Main.jsp"%>
<% 
	request.setCharacterEncoding("UTF-8");
	String sel = request.getParameter("rd");
	int selProjectNum = Integer.parseInt(request.getParameter("projectNum"+sel));
	
	
	
	
%>
<div id="contents">
	<form method="post" name="midEvalResultInput" action="/ResearchMasterProject/jsp/mideval/ExpertMidEvalAddResult.jsp">
		<table>
			<tr>
				<td>첨부파일</td><td colspan="3"><input type = "text" name = "inputFile"></input></td>
				<td><input type = "text" name = "selProjectNum" value ="<%=selProjectNum%>" style="display: none" ></input></td>
			</tr>
			<tr>
				<td>평가결과</td><td><input type = "radio" name = "rd" value ="계속">계속</td><td><input type = "radio" name = "rd" value ="중지">중지</td>
			</tr>
			<tr>
				<td colspan="2"><input type="button" name="button" value="완료" onclick="checkInput(this.form)"/></td>
				<td colspan="2"><input type="reset" name="reset" value="다시작성" /></td>
			</tr>
		</table>
	</form>
</div>
</body>
</html>
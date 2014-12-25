<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList" %>
<%@ page import="Control.FinEvalManager" %>
<%@ page import="Common.Project" %>
<%@ page import="Common.Expert" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>평가계획 수립</title>
<script language="javascript">
function showKeyCode(event) {
    event = event || window.event;
    var keyID = (event.which) ? event.which : event.keyCode;
    if( ( keyID >=48 && keyID <= 57 ) || ( keyID >=96 && keyID <= 105 ) || keyID == 8 || keyID == 46
          || keyID == 37 || keyID == 39)
    {
        return;
    }
    else
    {
        return false;
    }
}
function checkInput(chargerExpertList)
{
	var result = false;
	var radioBtn = chargerExpertList.rd;
	for(var i=0; i<radioBtn.length; i++)
	{
		if(radioBtn[i].checked==true)
		{	result = true;	break; }
	}
	
	if(!result)
	{ alert("평가위원장을 선택하세요!"); }
	else if(chargerExpertList.inputFile.value=="")
	{ alert("평가계획파일을 입력하세요!"); }
	else if(chargerExpertList.inputYear.value=="")
	{ alert("통보 연도를 입력하세요!"); }
	else if(chargerExpertList.inputMonth.value<1 || chargerExpertList.inputMonth.value>12 || chargerExpertList.inputMonth.value=="")
	{ alert("통보 월을 정확히 입력하세요!"); }
	else if(chargerExpertList.inputDate.value<1 || chargerExpertList.inputDate.value>31 || chargerExpertList.inputDate.value=="")
	{ alert("통보 일을 정확히 입력하세요!"); }
	else
	{
		document.chargerExpertList.action="/ResearchMasterProject/jsp/fineval/ChargerFinEvalAddPlan.jsp";
		document.chargerExpertList.method="post";
		document.chargerExpertList.submit();
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
	String selOrganName = request.getParameter("organName"+sel);
	String selStatus = request.getParameter("status"+sel);
	String selType = request.getParameter("type"+sel);
	String selArea = request.getParameter("area"+sel);
	String selLeaderName = request.getParameter("leaderName"+sel);
	String selLeaderId = request.getParameter("leaderId"+sel);

	ArrayList<Expert> expertList = new ArrayList<Expert>();
	FinEvalManager finManager = new FinEvalManager();
	expertList = finManager.requestExpertsForCharger(selProjectNum);
	
%>
<div id="contents">
	<form method="post" name="chargerExpertList" action="/ResearchMasterProject/jsp/fineval/ChargerFinEvalAddPlan.jsp">
		<table>
			<tr>
				<td></td><td>이름</td><td>기관번호</td><td>직급</td><td>분야</td>
			</tr>
			<%for(int i=0; i<expertList.size(); i++){ %>
			<tr>
				<td><input type = "radio" name = "rd" value ="<%=i%>"></td>
				<td><%=expertList.get(i).getName() %></td>
				<td><%=expertList.get(i).getOrganId() %></td>
				<td><%=expertList.get(i).getPosition() %></td>
				<td><%=expertList.get(i).getField() %></td>
				<td><input type = "text" name = "expertId<%=i%>" value ="<%=expertList.get(i).getId()%>" style="display: none" ></input></td>
				<td><input type = "text" name = "expertEmail<%=i%>" value ="<%=expertList.get(i).getEmail()%>" style="display: none" ></input></td>
			</tr>
			<%} %>
			<tr>
				<td>첨부파일</td><td colspan="3"><input type = "text" name = "inputFile"></input></td>
			</tr>
			<tr>
				<td>통보날짜</td><td><input type = "text" name = "inputYear" OnKeydown="return showKeyCode(event)" size=3></input></td><td><input type = "text" name = "inputMonth" OnKeydown="return showKeyCode(event)" size=3></input></td><td><input type = "text" name = "inputDate" OnKeydown="return showKeyCode(event)" size=3></input></td>
			</tr>
			<tr>
				<td><input type = "text" name = "projectNum" value ="<%=selProjectNum%>" style="display: none" ></input></td>
				<td><input type = "text" name = "leaderId" value = "<%=selLeaderId%>" style="display: none" ></input></td>
			</tr>
			<tr>
				<td><input type="button" name="button" value="완료" onclick="checkInput(this.form)" /></td>
				<td><input type="reset" name="reset" value="다시작성" /></td>
			</tr>
		</table>
	</form>
</div>
</body>
</html>
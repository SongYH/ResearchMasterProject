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

	/*
	Expert a1 = new Expert(1, "expert1", "abc", "전문가1", "123123", "0100404", "경기도", "서울", "금오공대", "대리", 
			"552-5252", "sunmin204@naver.com", "모바일");
	Expert a2 = new Expert(2, "expert2", "abc", "전문가2", "123123", "0100404", "경기도", "서울", "금오공대", "대리", 
			"552-5252", "sunmin204@naver.com", "모바일");
	Expert a3 = new Expert(3, "expert3", "abc", "전문가3", "123123", "0100404", "경기도", "서울", "금오공대", "대리", 
			"552-5252", "sunmin204@naver.com", "모바일");
	Expert a4 = new Expert(4, "expert4", "abc", "전문가4", "123123", "0100404", "경기도", "서울", "금오공대", "대리", 
			"552-5252", "sunmin204@naver.com", "모바일");
	Expert a5 = new Expert(5, "expert5", "abc", "전문가5", "123123", "0100404", "경기도", "서울", "금오공대", "대리", 
			"552-5252", "sunmin204@naver.com", "모바일");
	Expert a6 = new Expert(6, "expert6", "abc", "전문가6", "123123", "0100404", "경기도", "서울", "금오공대", "대리", 
			"552-5252", "sunmin204@naver.com", "모바일");

	expertList.add(a1); expertList.add(a2); expertList.add(a3); 
	expertList.add(a4); expertList.add(a5); expertList.add(a6); 
	
	*/
	
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
				<td>통보날짜</td><td><input type = "text" name = "inputYear" size=3></input></td><td><input type = "text" name = "inputMonth" size=3></input></td><td><input type = "text" name = "inputDate" size=3></input></td>
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
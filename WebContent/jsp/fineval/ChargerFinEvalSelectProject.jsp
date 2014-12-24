<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="Control.FinEvalManager" %>
<%@ page import="Common.Project" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.Date" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>평가과제 선택</title>

<script language="javascript">
function checkInput(chargerProjectList)
{
	var result=false;
	var radioBtn = chargerProjectList.rd;
	for(var i=0; i<radioBtn.length; i++)
	{
		if(radioBtn[i].checked==true)
		{	result = true;	break; }
	}
	
	if(!result)
	{ 
		alert("과제를 선택하세요!");
	}
	else
	{
		document.chargerProjectList.action="/ResearchMasterProject/jsp/fineval/ChargerFinEvalSelectExpert.jsp";
		document.chargerProjectList.method="post";
		document.chargerProjectList.submit();
	}
}
</script>

</head>
<body>
<%@include file="/jsp/common/Main.jsp"%>
<% 
	request.setCharacterEncoding("UTF-8");
	ArrayList<Project> proList = new ArrayList<Project>();
	FinEvalManager finManager = new FinEvalManager();
	proList = finManager.requestProjectsForCharger();
	
	/*
	ArrayList<String> test = new ArrayList<String>();

	Project a1 = new Project("정산", "장기", "모바일", 1, "sunmin1"
			, "최선민", "abc", "금오공대", new Date(), 2014, "fileName1", test);
	Project a2 = new Project("중간평가", "단기", "임베", 2, "sunmin2"
			, "최선민", "abc", "금오공대", new Date(), 2014, "fileName2", test);
	Project a3 = new Project("중간평가", "장기", "모바일", 3, "sunmin3"
			, "최선민", "abc", "금오공대", new Date(), 2014, "fileName3", test);
	Project a4 = new Project("정산", "장기", "임베", 4, "sunmin4"
			, "최선민", "abc", "금오공대", new Date(), 2014, "fileName4", test);
	Project a5 = new Project("신청", "장기", "모바일", 5, "sunmin5"
			, "최선민", "abc", "금오공대", new Date(), 2014, "fileName5", test);
	Project a6 = new Project("등록", "장기", "모바일", 6, "sunmin6"
			, "최선민", "abc", "금오공대", new Date(), 2014, "fileName6", test);
	
	proList.add(a1); proList.add(a2); proList.add(a3); 
	proList.add(a4); proList.add(a5); proList.add(a6);
	*/
%>
<div id="contents">
	<form method="post" name="chargerProjectList" action="/ResearchMasterProject/jsp/fineval/ChargerFinEvalSelectExpert.jsp">
		<table>
			<tr>
				<td></td><td>번호</td><td>기관명</td><td>상태</td><td>종류</td><td>분야</td><td>책임자</td>
			</tr>
			<%for(int i=0; i<proList.size(); i++){ %>
			<tr>
				<td><input type = "radio" name = "rd" value ="<%=i%>"></td>
				<td><input type = "text" name = "projectNum<%=i%>" value = "<%=proList.get(i).getProjectNumber()%>" style="display: none" ><%=proList.get(i).getProjectNumber() %></input></td>
				<td><input type = "text" name = "organName<%=i%>" value = "<%=proList.get(i).getOrganName()%>" style="display: none" ><%=proList.get(i).getOrganName() %></input></td>
				<td><input type = "text" name = "status<%=i%>" value = "<%=proList.get(i).getStatus()%>" style="display: none" ><%=proList.get(i).getStatus() %></input></td>
				<td><input type = "text" name = "type<%=i%>" value = "<%=proList.get(i).getType()%>" style="display: none" ><%=proList.get(i).getType() %></input></td>
				<td><input type = "text" name = "area<%=i%>" value = "<%=proList.get(i).getArea()%>" style="display: none" ><%=proList.get(i).getArea() %></input></td>
				<td><input type = "text" name = "leaderName<%=i%>" value = "<%=proList.get(i).getLeaderName()%>" style="display: none" ><%=proList.get(i).getLeaderName() %></input></td>
				<td><input type = "text" name = "leaderId<%=i%>" value = "<%=proList.get(i).getLeaderId()%>" style="display: none" ></input></td>
			</tr>
			<%} %>
			<tr>
				<td align="center"><input type="button" name="button" value="선택" onclick="checkInput(this.form)"/></td>
				<td align="center"><input type="reset" name="reset" value="다시작성" /></td>
			</tr>
		</table>
	</form>
</div>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="Control.MidEvalManager" %>
<%@ page import="Control.ProjectManager" %>
<%@ page import="Common.Project" %>
<%@ page import="Common.MidEvalPlan" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.Date" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>평가과제 선택</title>

<script language="javascript">
function checkInput(expertProjectList)
{
		document.expertInput.action="/ResearchMasterProject/jsp/mideval/ExpertMidEvalInput.jsp";
		document.expertInput.method="post";
		document.expertInput.submit();
	
}
</script>

</head>
<body>
<%@include file="/jsp/common/Main.jsp"%>
<% 
	request.setCharacterEncoding("UTF-8");
	ArrayList<Project> proList = new ArrayList<Project>();
	
	
	MidEvalManager midEvalManager = new MidEvalManager();
	ArrayList<MidEvalPlan> midEvalPlan = new ArrayList<MidEvalPlan>();
	midEvalPlan = midEvalManager.requestMidEvalPlanForExpert(loginId);
	ArrayList<Integer> proNums = new ArrayList<Integer>();
	
	for(int i=0; i<midEvalPlan.size(); i++)
	{
		proNums.add(midEvalPlan.get(i).getProjectNumber());
	}
	
	ProjectManager proManager = new ProjectManager();
	
	proList = proManager.reqProjectList(proNums);
	
%>
<div id="contents">
	<form method="post" name="expertInput" action="/ResearchMasterProject/jsp/mideval/ExpertMidEvalInput.jsp">
		<%if(proList.size()==0){ %>
			<h2>평가할 프로젝트가 없습니다.</h2>
			<%}else{ %>
		<table>
			<tr>
				<td></td><td>번호</td><td>기관명</td><td>상태</td><td>종류</td><td>분야</td><td>책임자</td>
			</tr>
			<%for(int i=0; i<proList.size(); i++){ %>
			<tr>
				<td><input type = "radio" name = "rd" value ="<%=i%>"></td>
				<td><input type = "text" name = "projectNum<%=i%>" value ="<%=proList.get(i).getProjectNumber()%>" style="display: none" ><%=proList.get(i).getProjectNumber() %></input></td>
				<td><input type = "text" name = "organName<%=i%>" value = "<%=proList.get(i).getOrganName()%>" style="display: none" ><%=proList.get(i).getOrganName() %></input></td>
				<td><input type = "text" name = "status<%=i%>" value = "<%=proList.get(i).getStatus()%>" style="display: none" ><%=proList.get(i).getStatus() %></input></td>
				<td><input type = "text" name = "type<%=i%>" value = "<%=proList.get(i).getType()%>" style="display: none" ><%=proList.get(i).getType() %></input></td>
				<td><input type = "text" name = "area<%=i%>" value = "<%=proList.get(i).getArea()%>" style="display: none" ><%=proList.get(i).getArea() %></input></td>
				<td><input type = "text" name = "leaderName<%=i%>" value = "<%=proList.get(i).getLeaderName()%>" style="display: none" ><%=proList.get(i).getLeaderName() %></input></td>
				<td><input type = "text" name = "leaderId<%=i%>" value = "<%=proList.get(i).getLeaderId()%>" style="display: none" ></input></td>
			</tr>
			<%} %>
			<tr>
				<td><input type="button" name="button" value="선택" onclick="checkInput(this.form)" /></td>
				<td><input type="reset" name="reset" value="다시작성" /></td>
			</tr>
		</table>
		<%} %>
	</form>
</div>
</body>
</html>
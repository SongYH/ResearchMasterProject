		<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.*, java.text.*"  %>
<%@ page import="Common.*, Control.*, Entity.*"  %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>과제조회</title>
<%
	ProjectManager projectM = new ProjectManager();
	ArrayList<Project> projectList = new ArrayList<Project>();
%>
</head>
<body>
<%@include file="/jsp/common/Main.jsp"%>
	<%if(loginId == null || loginId == "") 
	{
		response.sendRedirect("/ResearchMasterProject/jsp/common/login.jsp");
	}else { // 로그인 되있는 상태면
		String permission = projectM.reqUserPermission(loginId);		// 권한을 아이디를 통해 가져옴 
		if(permission.equals("과제책임자"))		// 정산담당자 일때
		{
			projectList.add(projectM.reqProjectSearchID(loginId));
		} // 과제책임자 일때 if 문 끝
		else if(permission.equals("과제담당자"))
		{
			projectList = projectM.reqAllProjectList();
		}
		else // 과제담당자  책임자 둘다 아닐떄
	 	{ // 팝업창 띄우고 Home 화면으로 가기
			 out.println("<script>alert('과제책임자 또는 과제담당자가 아닙니다'); location.href = '/ResearchMasterProject/jsp/common/Home.jsp'; </script>"); 
	 	}
	} %>
	<hr /> <p align="center">과제조회</p> <hr />
	<form name=selectProject method=post action="calculation.js">
		<table>
			<tr>
				<td>과제번호</td>
				<td>과제명</td>
				<td>과제종류</td>
				<td>과제기간</td>
				<td>과제책임자</td>
				<td>과제상태</td>
				<td>비고</td>
			</tr>
			<%
				for (int i = 0; i < projectList.size(); i++) {	// 각 index에 해당하는 과제 정보를 출력 
			%>
			<tr>
				<td><%=projectList.get(i).getProjectName()%></td>
				<td><%=projectList.get(i).getOrganName()%></td>
				<td><%=projectList.get(i).getType()%></td>
				<td><%=projectList.get(i).getArea()%></td>
				<td><%=projectList.get(i).getLeaderName()%></td>
				<td><%=projectList.get(i).getStatus()%></td>
				<td><a
					href="SearchRegistrationView.jsp?projectNum=<%=projectList.get(i).getProjectNumber()%>">
						선택</a><%//선택된 과제정보해당 하는 결과화면을 요청한다 %> </td>
			</tr>
			<%
				}
			%>
		</table>
	</form>
</body>
</html>
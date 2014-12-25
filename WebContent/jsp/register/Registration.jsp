<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.*, java.text.*"  %>
<%@ page import="Common.*, Control.*"  %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>과제등록</title>

<%
	request.setCharacterEncoding("UTF-8");
	String login_id = (String)session.getAttribute("loginId"); // 세션 아이디 가져오기
	
	ProjectManager projectM = new ProjectManager();
	PreProject preProject = new PreProject();
	PreProject preProject2 = new PreProject();
	if(login_id != null)
	{
		preProject = projectM.reqSelectionEvaluationProject(login_id, "선정평가통과");
		preProject2 = projectM.reqSelectionEvaluationProject(login_id, "등록완료");
		//아이디로 User객체 가져오기
	}
	//로그인후 세션
%>

</head>
<body>
	<%@include file="/jsp/common/Main.jsp"%>
	<%if(login_id == null || login_id == "") 
	{
		response.sendRedirect("/ResearchMasterProject/jsp/common/login.jsp");
	}else {
		String permission = projectM.reqUserPermission(login_id);		// 권한을 아이디를 통해 가져옴 
		if(permission.equals("과제책임자"))		// 정산담당자 일때
		{
			if(preProject == null)
			{
				if(preProject2 == null)
				{
				%>
					<p align="center"> 선정평가를 통과한 과제가 없습니다.</p>
				<%
				}
				else 
				{
					 out.println("<script>alert('등록된 과제가 있습니다.'); location.href = '/ResearchMasterProject/jsp/register/SearchRegistration.jsp'; </script>"); 
				}
			}
			else // 과제 있을때
			{
			%>
				<div id="contents">
				<form method="post" name="projectInput" action="/ResearchMasterProject/jsp/register/RegistrationView.jsp">
				<hr /> <p align="center">선정평가 통과 과제</p> <hr />
				<table align="center">
					<tr>
						<td align="center">과제명</td>
						<td align="center">과제종류</td>
						<td align="center">과제기간</td>
						<td align="center">비고</td>
					</tr>
					<tr>
						<td align="center"><%=preProject.getSubjectName() %></td>
						<td align="center"><%=preProject.getType() %></td>
						<td align="center"><%=preProject.getArea() %></td>
						<td align="center"><input type="submit" name="submit" value="등록하기" ></td>
					</tr>
				</table>
				</form>
				</div>
			<%
			} // 과제 있을때 else 문 종료
		} // 과제책임자 일때 if 문 끝
		else
	 	{ // 팝업창 띄우고 Home 화면으로 가기
			 out.println("<script>alert('과제책임자가 아닙니다'); location.href = '/ResearchMasterProject/jsp/common/Home.jsp'; </script>"); 
	 	}
	} %>
</body>

</html>
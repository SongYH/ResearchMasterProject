
<%@page import="Control.ProjectManager"%>
<%@page import="Common.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="Entity.*" import="java.util.ArrayList"%>

<HTML>
<HEAD>
<TITLE>사업비 정산 조회 beans</TITLE>
</HEAD>
<script language=JavaScript src="../../js/calculation/calculation.js"></script>
<BODY>

	<%
		ProjectList pl = ProjectList.getInstance();			// 과제관리 객체를 생성한다 
		ProjectManager pm = new ProjectManager();
		ArrayList<Project> prList = pm.reqProjectList("상태","증빙자료입력");	// 증빙자료가 입력된 과제 목록을 요청한다.
	%>

	<form name=selectProject method=post action="calculation.js">
		<table>
			<tr>
				<td>번호</td>
				<td>기관명</td>
				<td>상태</td>
				<td>종류</td>
				<td>분야</td>
				<td>과제책임자</td>
				<td>과제선택</td>
			</tr>
			<%
				for (int i = 0; i < prList.size(); i++) {	// 각 index에 해당하는 과제 정보를 출력 
			%>
			<tr>
				<td><%=prList.get(i).getProjectNumber()%></td>
				<td><%=prList.get(i).getOrganName()%></td>
				<td><%=prList.get(i).getStatus()%></td>
				<td><%=prList.get(i).getArea()%></td>
				<td><%=prList.get(i).getLeaderId()%></td>
				<td><a
					href="resultCalculation.jsp?select_project_number=<%=prList.get(i).getProjectNumber()%>">
						선택</a><%//선택된 과제정보해당 하는 결과화면을 요청한다 %> </td>
			</tr>
			<%
				}
			%>
		</table>
	</form>
</BODY>
</HTML>
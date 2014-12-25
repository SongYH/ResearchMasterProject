<%@page import="Control.ProjectManager"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="Common.*" %>
<%@ page import="java.util.*" %>
<%@ page import="java.text.*" %>
<% request.setCharacterEncoding("UTF-8");
	ProjectManager projectM = new ProjectManager();
	int projectNum = projectM.createProjectNumber();
	String projectName = request.getParameter("projectName");
	String projectType = request.getParameter("projectType");
	String projectArea = request.getParameter("projectArea");
	String leaderName = request.getParameter("leaderName");
	String leaderId = (String)session.getAttribute("loginId"); // 세션 아이디 가져오기
	String organName = request.getParameter("organName");
	String organId = request.getParameter("organId"); // Int로 변경해야함
	String agreeYear = request.getParameter("agreeYear");
	String fileName = request.getParameter("fileName");
	String userSelect = request.getParameter("userSelect"); // Int 변환 몇명 선택인지
	String projectStatus = request.getParameter("status");

	String registerday = request.getParameter("registerday"); // 20141212
	
	String workerId1 = request.getParameter("workerId1");
	String workerId2 = request.getParameter("workerId2");
	String workerId3 = request.getParameter("workerId3");
	String workerId4 = request.getParameter("workerId4");
	String workerId5 = request.getParameter("workerId5");
	
	String amount = request.getParameter("amount");	
	ArrayList<String> userList = new ArrayList<String>();
	
	switch(Integer.parseInt(userSelect))
	{
	case 1:
		userList.add(workerId1);
		break;
	case 2:
		userList.add(workerId1);	userList.add(workerId2);
		break;
	case 3:
		userList.add(workerId1);	userList.add(workerId2);	userList.add(workerId3);
		break;
	case 4:
		userList.add(workerId1);	userList.add(workerId2);	userList.add(workerId3);
		userList.add(workerId4);
		break;
	case 5:
		userList.add(workerId1);	userList.add(workerId2);	userList.add(workerId3);
		userList.add(workerId4);	userList.add(workerId5);
		break;
		
	}
	SimpleDateFormat transFormat = new SimpleDateFormat("yyyyMMdd");
	Date Dregisterday = transFormat.parse(registerday);

	
	//상태, 분야, 기간, 번호(int), 과제명, 과책아이디, 과책이름, 기관번호(int), 기관명, 등록일(date), 협약연한(int), 파일명, userList
	Project project = new Project(projectStatus, projectType, projectArea, projectNum, projectName,
			leaderId, leaderName, Integer.parseInt(organId), organName, Dregisterday,
			Integer.parseInt(agreeYear), fileName, userList ); // 프로젝트 만듬
	Budget budget = new Budget(projectNum, Integer.parseInt(amount)); //예산 만듬
	projectM.addProject(project, budget);
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>연구원 확인</title>
</head>
<body>
	과제번호 <%=projectNum %> <br>
	과제명 <%=projectName %> <br>
	과제타입 <%=projectType %> <br>
	과제분야 <%=projectArea %> <br>
	책임자이름 <%=leaderName %> <br>
	기관명 <%=organName %> <br>
	사업자번호 <%=organId %> <br>
	연한 <%=agreeYear %> <br>
	상태 <%=projectStatus %> <br>
	연구원 1  <%=workerId1 %> <br>
	연구원 2 <%=workerId2 %> <br>
	연구원 3  <%=workerId3 %> <br>
	연구원 4 <%=workerId4%> <br>
	연구원 5 <%=workerId5 %> <br>
	총액  <%=amount %> <br>
</body>
</html>
	
<%@page import="Common.Project"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="Control.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%
		request.setCharacterEncoding("UTF-8");

		String id = null;
		id = (String) session.getAttribute("loginId"); // 아이디 가져오기
		if (id != null){
			id = new String(id.getBytes("8859_1"), "UTF-8"); // 로그인 되어있는 id
			
		PermissionManager perm = new PermissionManager(); //권한확인을 위한 권한관리 객체
		String actor = perm.confirmUserPermission(id); // 아이디를 통해 권한 확인
		if (actor.equals("과제책임자")) // 권한이 과제책임자일때	
		{
			ProjectManager pm = new ProjectManager(); // 과제정보를 얻어오기위한 과제 관리객체 생성 
			Project pr = pm.reqProjectSearchID(id); // 과제책임자 id로 과제 정보 요청 
			if (pr == null)
				response.sendRedirect("error_calculation.jsp?error_msg=CA005"); // 해당되는 프로젝트가 없을때 에러처리페이지로 화면변경
			else
				response.sendRedirect("resultCalculation.jsp?select_project_number="
						+ pr.getProjectNumber()); // 과제 책임자가 해당되어있는 과제번호를 통해 과제 정산 정보 확인 바운더리로 화면변경
		} else if (actor.equals("정산담당자")) {
			response.sendRedirect("admin_check_selectProject.jsp"); // 정산담당자일때, 정산담당자의 과제 정보확인 바운더리로 화면변경 
		} else
			response.sendRedirect("error_calculation.jsp?error_msg=CA001"); // 해당되는 권한을 가지지않았을때 에러처리페이지로 화면변경
		}
		else
			response.sendRedirect("error_calculation.jsp?error_msg=CA007"); // 로그인을 요청하는 에러페이지
		
	%>

</body>
</html>
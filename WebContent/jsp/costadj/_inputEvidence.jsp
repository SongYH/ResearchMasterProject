<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="Control.*" import="Common.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<body>
	<%
		request.setCharacterEncoding("UTF-8");
		String id = null;
		id = (String) session.getAttribute("loginId"); // 아이디 가져오기
		if (id != null) {
			id = new String(id.getBytes("8859_1"), "UTF-8"); // 로그인 되어있는 id
			PermissionManager perm = new PermissionManager();
			String actor = perm.confirmUserPermission(id); // id로부터 권한획득
			if (actor.equals("과제책임자")) // 과제책임자일때	
			{
				ProjectManager pm = new ProjectManager();
				Project pr = pm.reqProjectSearchID(id);
				if (pr == null)
					response.sendRedirect("error_calculation.jsp?error_msg=CA005"); // 해당되는 프로젝트가 없을때 에러처리페이지로 화면변경
				else if (pr.getStatus() == "정산완료"
						|| pr.getStatus() == "증빙자료입력")
					response.sendRedirect("task_manager_evidenceResult.jsp?select_project_number="
							+ pr.getProjectNumber()); // 사업비증빙자료 추가 바운더리로 이동
				else
					response.sendRedirect("task_manager_inputEvidence.jsp?select_project_number="
							+ pr.getProjectNumber()); // 사업비증빙자료 추가 바운더리로 이동
			} else {
				response.sendRedirect("error_calculation.jsp?error_msg=CA003");
			}
		} else
			response.sendRedirect("error_calculation.jsp?error_msg=CA007"); // 로그인을 요청하는 에러페이지
	%>

</body>
</html>
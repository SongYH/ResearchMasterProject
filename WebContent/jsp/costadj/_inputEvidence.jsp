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
	<% 	request.setCharacterEncoding("UTF-8");
			//String id = request
			//.getParameter("id");	// id 요청
			String id = "song91";
			PermissionManager perm = new PermissionManager();
			String actor = perm.confirmUserPermission(id);	// id로부터 권한획득
			if(actor.equals("과제책임자"))		// 과제책임자일때	
			{
				ProjectManager pm = new ProjectManager();	
				Project pr = pm.reqProjectSearchID(id);
				response.sendRedirect("task_manager_inputEvidence.jsp?select_project_number="+pr.getProjectNumber());	// 사업비증빙자료 추가 바운더리로 이동
			}
			else
			{
				response.sendRedirect("error_calculation.jsp?error_msg=CA003");
			}
			
	%>

</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="Common.*" import="Control.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<% 	request.setCharacterEncoding("UTF-8");
		String id = (String)session.getAttribute("loginId");
	//String id = request
		//.getParameter("id");			// 로그인되어있는 id를 가져옴
		PermissionManager perm = new PermissionManager();		// 권한을 확인하기위한 권한관리객체 생성
		String actor = perm.confirmUserPermission(id);			// 권한을 아이디를 통해 가져옴 
	 if(actor.equals("정산담당자"))		// 정산담당자 일때
		{
			response.sendRedirect("input_calculation_selectProject.jsp");	// 사업비 정산입력 화면으로 갱신
		}
	 else
	 {
			response.sendRedirect("error_calculation.jsp?error_msg=CA002");	// 사업비 정산담당자가 아니면 에러화면으로 갱신 
	 }
		
%>

</body>
</html>
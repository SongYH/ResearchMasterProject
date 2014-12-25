<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="Control.*"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>게시판 기본 예제 : 게시 등록 수정 삭제 처리</title>
</head>
<body>
<% 
	//한글 처리를 위해 문자인코딩 지정
	request.setCharacterEncoding("euc-kr");
	
	UserManage userM = new UserManage();
	String userId = request.getParameter("userId");
 	String userPw = request.getParameter("userPw");
 	
 	if(userM.reqlogin(userId, userPw))
 	{
		session.setAttribute("loginId", userId); 
		response.sendRedirect("/ResearchMasterProject/jsp/common/Home.jsp");
 	}
 	else 
 	{
%>
	<script>alert("로그인 정보를 확인하세요."); history.go(-1);</script>
<%} 
%>		
	 
</body>
</html>
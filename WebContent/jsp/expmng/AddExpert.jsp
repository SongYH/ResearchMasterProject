<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList" %>
<%@ page import="Control.ExpertManager" %>
<%@ page import="Common.Expert" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
</head>
<body>
<%@include file="/jsp/common/Main.jsp"%>
<%
	request.setCharacterEncoding("UTF-8");
	String id = request.getParameter("user_id"); 
	String password = request.getParameter("user_password");
//	int expertnumber = Integer.parseInt(request.getParameter("new_expertnumber"));
	String name = request.getParameter("user_name");
	String socialNumber = request.getParameter("user_socialnumber");
	String tel = request.getParameter("user_tel");
	String officeAddress = request.getParameter("new_officeaddress");
	String homeAddress = request.getParameter("user_address");
	int organid = Integer.parseInt(request.getParameter("user_organid"));
	String position = request.getParameter("new_position");
	String accountInfo = request.getParameter("new_accountinfo");
	String email = request.getParameter("user_email");
	String field = request.getParameter("new_field");

	ExpertManager expertmanager = new ExpertManager();
	Expert expert = new Expert();
	
	expert.setId(id);
	expert.setPassword(password);
//	expert.setExpertNumber(expertnumber);
	expert.setName(name);
	expert.setSocialNumber(socialNumber);
	expert.setTel(tel);
	expert.setOfficeAddress(officeAddress);
	expert.setHomeAddress(homeAddress);
	expert.setOrganId(organid);
	expert.setPosition(position);
	expert.setAccountInfo(accountInfo);
	expert.setEmail(email);
	expert.setField(field);
	
	expertmanager.requestAddExpert(expert);
	out.println("<script>alert('전문가 등록이 완료되었습니다'); location.href = '/ResearchMasterProject/jsp/common/Home.jsp'; </script>");
//	response.sendRedirect("/ResearchMasterProject/jsp/common/Home.jsp");
%>
</body>
</html>
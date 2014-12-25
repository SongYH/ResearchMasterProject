<%@page import="Control.SubjectManager"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="Control.PermissionManager" %>
<%@ page import="Common.User" %>
<% request.setCharacterEncoding("UTF-8"); %>
<%
	String projectNum = request.getParameter("projectNum");
	
	SubjectManager subM = new SubjectManager();
	subM.setChangePreProjectStatus(Integer.parseInt(projectNum), "선정평가통과");
	response.sendRedirect("/ResearchMasterProject/jsp/common/Home.jsp");
%>
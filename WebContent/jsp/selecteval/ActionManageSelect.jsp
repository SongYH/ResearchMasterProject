<%@page import="Control.SubjectManager"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="Control.PermissionManager" %>
<%@ page import="Common.User" %>
<% request.setCharacterEncoding("UTF-8"); %>
<%
	String[] projectNum = request.getParameterValues("projectNum");
	String[] newPermissionName = request.getParameterValues("newPermissionName");

	SubjectManager subM = new SubjectManager();
	subM.setChangePreProjectStatus(projectNum, newPermissionName);
	response.sendRedirect("/ResearchMasterProject/jsp/common/Home.jsp");
%>
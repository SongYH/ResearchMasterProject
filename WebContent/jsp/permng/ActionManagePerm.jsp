<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="Control.PermissionManager" %>
<%@ page import="Common.User" %>
<% request.setCharacterEncoding("UTF-8"); %>
<%
	String[] userId = request.getParameterValues("userId");
	String[] newPermissionName = request.getParameterValues("newPermissionName");
	
	PermissionManager manager = new PermissionManager();
	manager.updateUserPermission(userId, newPermissionName);
	
	response.sendRedirect("/ResearchMasterProject/jsp/permng/PageSearchUser.jsp");
%>
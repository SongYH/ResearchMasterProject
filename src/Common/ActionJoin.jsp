<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="Control.UserManage" %>
<%@ page import="Common.User" %> 
<% request.setCharacterEncoding("UTF-8"); %>

<jsp:useBean id="user" class="Common.User">
	<jsp:setProperty name="user" property="id"/>
	<jsp:setProperty name="user" property="password"/>
	<jsp:setProperty name="user" property="name"/>
	<jsp:setProperty name="user" property="socialNumber"/>
	<jsp:setProperty name="user" property="tel"/>
	<jsp:setProperty name="user" property="email"/>
	<jsp:setProperty name="user" property="address"/>
</jsp:useBean>
<jsp:useBean id="organ" class="Common.Organ">
	<jsp:setProperty name="organ" property="organName"/>	
	<jsp:setProperty name="organ" property="regionCode"/>
	<jsp:setProperty name="organ" property="fax"/>
	<jsp:setProperty name="organ" property="president"/>
</jsp:useBean>

<% 
	String sn = request.getParameter("socialNumber");
	int val = Integer.parseInt(request.getParameter("val"));
	String rc = request.getParameter("regionCode");
	String orid = request.getParameter("orid");
	String orname = request.getParameter("organName1");
	
	UserManage um = new UserManage();
	boolean check = um.reqchecksocialNumber(sn);
	System.out.println("기관이름"+orname);
	int oridid = um.reqgetorgannum(orname); //디비에 넣을 orid
	
	if(rc.equals("seoul"))
	{
		organ.setRegionCode("S00");
	}
	else if(rc.equals("kido"))
	{
		organ.setRegionCode("G001");
	}
	else if(rc.equals("buk"))
	{
		organ.setRegionCode("D001");
	}
	else if(rc.equals("etc"))
	{
		organ.setRegionCode("J001");
	}
%>	 
<!-- jsp 끝 -->
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>회원가입</title>
</head>
<body>
<%	if(check)
	{
%>
 		<p>회원가입에 성공하였습니다.</p>
		<input type="button" value="닫기" onclick="self.close()">
		<%
		if(val==0)
			{
			 	System.out.println("일반 사용자");
			 	System.out.println(oridid);
			 	
				user.setOrganId(oridid);
				um.join(user);
			}
		else if(val==1)
			{
				organ.setOrganId(Integer.parseInt(orid));
				um.join(user);
				um.Organjoin(organ);
		 	}
		%>
<%	}
	else
	{
%>		<p>회원가입에 실패하였습니다..</p>
		<input type="button" value="닫기" onclick="self.close()">
<%	}
response.sendRedirect("/ResearchMasterProject/jsp/common/Home.jsp");
%>
</body>
</html>

	
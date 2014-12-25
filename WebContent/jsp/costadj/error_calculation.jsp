<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>calculation error</title>
</head>
<body>
<%@include file="/jsp/common/Main.jsp"%>
<%
		request.setCharacterEncoding("UTF-8");
		String error_msg = request
				.getParameter("error_msg");
		if(error_msg.equals("CA001"))	// 
		{
			%>
				올바른 Actor가 아닙니다.	
			<%
		}
		else if(error_msg.equals("CA002"))	// 정산담당자가 아닌경우 정산담당자가
		{
			%>
					정산담당자가 아닙니다.
			<%
		}
		else if(error_msg.equals("CA003"))
		{
			%>
					과제 책임자가 아닙니다.
			<%
		}
		else if(error_msg.equals("CA004"))
		{
			%>
					조회된 데이터가 없습니다.
			<%
		}
		else if(error_msg.equals("CA005"))
		{
			%>
				 진행중인 과제가 없습니다.
			<%
		}
		else if(error_msg.equals("CA006"))
		{
			%>
				정산결과가 입력되지않았습니다.
			<%
		}
		else if(error_msg.equals("CA007"))
		{
			%>
				로그인후 이용해주세요.
			<%
		}
%>
</body>
</html>
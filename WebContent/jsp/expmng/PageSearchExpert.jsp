<%@page import="Control.ExpertManager"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>전문가관리</title>
<%
	ExpertManager expM = new ExpertManager();
%>
</head>
<body>
<%@include file="/jsp/common/Main.jsp"%>
<%if(loginId == null || loginId == "") 
   {
      response.sendRedirect("/ResearchMasterProject/jsp/common/login.jsp");
   }else {
      String permission = expM.reqUserPermission(loginId);      // 권한을 아이디를 통해 가져옴 
      if(permission.equals("전문가담당자"))      // 정산담당자 일때
    	{
	%>
		<div id="contents">
		<a href="/ResearchMasterProject/jsp/expmng/PageSearchExpert_User.jsp">전문가 추가</a>
		<h1>전문가 검색</h1>
		<form action="/ResearchMasterProject/jsp/expmng/PageViewExpert.jsp" method="post">
			<table style="width:30%; margin-left:40%; margin-right:30%;">
				<tr>
					<td>검색방법
					<select name="searchType">
							<option value="expertNumber" selected>전문가번호</option>
							<option value="expertName">이름</option>
							<option value="organName">기관명</option>
					</select>
					<input type="text" name="searchInput" value="" placeholder="검색어입력"/>
					<input type="submit" name="searchButton" value="검색"/>
					</td>
				</tr>
			</table>
		</form>
		</div>
	
	<%    	  
      } // 과제책임자 일때 if 문 끝
      else
       { // 팝업창 띄우고 Home 화면으로 가기
          out.println("<script>alert('전문가담당자가 아닙니다'); location.href = '/ResearchMasterProject/jsp/common/Home.jsp'; </script>"); 
       }
   } %>
</body>

<script>
	function showKeyCode(event) {
    	event = event || window.event;
    	var keyID = (event.which) ? event.which : event.keyCode;
    	if( ( keyID >=48 && keyID <= 57 ) || ( keyID >=96 && keyID <= 105 ) || keyID == 8 || keyID == 46
    			|| keyID == 37 || keyID == 39)
    	{
        	return;
    	}
    	else
    	{
        	return false;
    	}
	}
</script>

</html>
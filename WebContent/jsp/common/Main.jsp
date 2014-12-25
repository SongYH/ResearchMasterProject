<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" type="text/css" href="/ResearchMasterProject/css/style.css" />
<%
//세션 받아오기
request.setCharacterEncoding("UTF-8");
String loginId = null;
loginId = (String)session.getAttribute("loginId");    // 아이디 가져오기
if (loginId != null)
	loginId = new String(loginId.getBytes("8859_1"), "UTF-8");

%>
</head>
<body>
	<div id="top"><br/></div>
	<div id="menu">
		<ul class="menu">
			<li><a href="/ResearchMasterProject/jsp/common/Home.jsp">과제관리시스템_8조</a>
			<li><a href="#">사업공고</a>
			<li><a href="#">과제신청</a>
				<ul>
					<li><a href="/ResearchMasterProject/jsp/proposal/Application.jsp" >과제신청</a></li>
					<li><a href="/ResearchMasterProject/jsp/proposal/ApplicationSearch.jsp" >신청조회</a></li>
				</ul>
			</li>
			<li><a href="#">과제등록</a>
				<ul>
					<li><a href="/ResearchMasterProject/jsp/register/Registration.jsp" >과제등록</a></li>
					<li><a href="/ResearchMasterProject/jsp/register/SearchRegistration.jsp" >등록조회</a></li>
				</ul>
			</li>
			<li><a href="#">과제평가</a>
				<ul>
					<li><a href="#" >중간평가</a></li>
					<li><a href="/ResearchMasterProject/jsp/fineval/ChargerFinEvalSelectProject.jsp" >최종평가</a></li>
					<li><a href="#" >중간평가결과조회</a></li>
					<li><a href="#" >최종평가결과조회</a></li>
				</ul>
			</li>
			<li><a href="#">사업비정산</a>
				<ul>
					<li><a href="/ResearchMasterProject/jsp/costadj/_inputEvidence.jsp">증거 자료 입력</a>
					<li><a href="/ResearchMasterProject/jsp/costadj/_input_calculate.jsp">정산 입력</a>
					<li><a href="/ResearchMasterProject/jsp/costadj/_checkCalculation.jsp">정산 결과 조회</a>
				</ul>
			<li><a href="#">이력관리</a>
				<ul>
					<li><a href="/ResearchMasterProject/jsp/careermng/ProjectCareerSearch.jsp">이력통합조회</a>
				</ul>
			<li><a href="#">관리자메뉴</a>
				<ul>
					<li><a href="#" >사용자관리</a></li>
					<li><a href="/ResearchMasterProject/jsp/expmng/PageSearchExpert.jsp" >전문가관리</a></li>
					<li><a href="/ResearchMasterProject/jsp/permng/PageSearchUser.jsp" >권한관리</a></li>
				</ul>
			</li>
			<%if(loginId == null){ %>
			<li style=float:right;><a href="/ResearchMasterProject/jsp/common/login.jsp">로그인</a></li>
			<%}
			else{%>
			<li style=float:right;><a href="/ResearchMasterProject/jsp/common/logout.jsp">로그아웃</a></li>
			<%} %>
			<li style=float:right;><a href="/ResearchMasterProject/jsp/common/Join.jsp">회원가입</a></li>
		</ul> <!-- end .menu -->
	</div>
	
	<body>
</html>
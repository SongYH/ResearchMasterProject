<%@page import="Common.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="Entity.*" import="task_registration.*"%>

<HTML>
<HEAD>
<TITLE>사업비 정산 조회 beans</TITLE>
</HEAD>
<script language=JavaScript src="../../js/calculation/calculation.js"></script>

<BODY>
	<%@include file="/jsp/common/Main.jsp"%>
	<!-- 과제번호를 통해 정산결과를 조회  -->
	<div>
		<form name=searchCalculate method=post action="calculation.js">
			<h3>사업비 정산 조회 입력</h3>
			과제번호 : <input type=text name="select_project_number" value="" size=20>
			<input type=button value="정산 조회" onClick="searchCalculation()"><br>
		</form>
	</div>
</BODY>
</HTML>
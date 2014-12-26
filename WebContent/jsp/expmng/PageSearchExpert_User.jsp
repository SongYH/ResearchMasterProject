<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>전문가관리</title>
</head>
<body>
<%@include file="/jsp/common/Main.jsp"%>
<div id="contents">
	<a href="/ResearchMasterProject/jsp/expmng/PageSearchExpert.jsp">전문가 검색</a>
	<h1>전문가 추가</h1>
	<form action="/ResearchMasterProject/jsp/expmng/PageViewExpert_User.jsp" method="post">
		<table style="width:30%; margin-left:40%; margin-right:30%;">
			<tr>
				<td>검색방법
				<select name="searchType">
						<option value="expertName">이름</option>
						<option value="organName">기관명</option>
				</select>
				<input type="text" name="searchInput" placeholder="검색어입력" />
				<input type="submit" name="searchButton" value="검색"/>
				</td>
			</tr>
		</table>
	</form>
</div>
</body>
</html>
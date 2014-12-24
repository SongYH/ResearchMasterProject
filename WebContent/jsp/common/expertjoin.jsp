<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="/ResearchMasterProject/css/style.css" />
<title>회원가입</title>
<script language="javascript">
function checkId(userInput)
{
	if(userInput.id.value == "")
	{
		alert("아이디를 입력하세요");
		return;
	}
	
	url = "/ResearchMasterProject/jsp/common/checkId.jsp?id=" + userInput.id.value;
	
	open(url, "confirm", "toolbar=no, location=no, status=no, menubar=no, width=500, height=230");
} 
function checkso(userInput)
{
	url = "/ResearchMasterProject/jsp/common/ActionJoin.jsp.jsp?socialNumber=" + userInput.socialNumber.value;
	
	open(url, "confirm", "toolbar=no, location=no, status=no, menubar=no, width=500, height=230");
}
</script>

</head>
<body>
<%@include file="/jsp/common/Main.jsp"%>
<div id="contents">
	<form method="post" name="userInput" action="/ResearchMasterProject/jsp/common/ActionJoin.jsp">
		<p align="center">기본정보</p>
		<hr />
		<table align="center">
		<tr><td>사용아이디</td><td><input type="text" name="id" /></td>
		<td><input type="button" name="idButton" value="중복확인" onclick="checkId(this.form)"/></td></tr>
		<tr><td>비밀번호</td><td><input type="text" name="password" /></td></tr>
		<tr><td>사용자이름</td><td><input type="text" name="name" /></td></tr>
		<tr><td>주민등록번호</td><td><input type="text" name="socialNumber" /></td></tr>
		<tr><td>전화번호</td><td><input type="text" name="tel" /></td></tr>
		<tr><td>이메일</td><td><input type="text" name="email" /></td></tr>
		<tr><td>근무지주소</td><td><input type="text" name="officeAddress" /></td></tr>
		<tr><td>거주지주소</td><td><input type="text" name="homeAddress"/></td></tr>
		<tr><td>기관명</td><td><input type="text" name="organName"/></td></tr>
		<tr><td>직급</td><td><input type="text" name="position"/></td></tr>
		<tr><td>계좌정보</td><td><input type="text" name="accountInfo"/></td></tr>
		<tr><td>분야</td><td><input type="text" name="field"/></td></tr>
		<tr><td><input type="hidden" name="val" value="0" id="val">
		</table>
		<hr />
		<p align="center">
		<input type="submit" name="submit" value="등록"/>
		<input type="reset" name="reset" value="다시작성"/></p>
	</form>
</div>
</body>
</html>
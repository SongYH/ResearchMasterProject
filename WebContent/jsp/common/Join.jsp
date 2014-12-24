<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="Control.UserManage" %>
<%@ page import="Common.Organ" %>
<%@ page import="java.util.ArrayList;" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="/ResearchMasterProject/css/style.css" />
<title>회원가입</title>
<%
	request.setCharacterEncoding("UTF-8");
	UserManage um = new UserManage();
	ArrayList<Organ> orarr = new ArrayList<Organ>();
	orarr = um.getOrgans();
%>
<script language="javascript">
function checkId(userInput)
{
	if(userInput.userid.value == "")
	{
		alert("아이디를 입력하세요");
		return;
	}
	
	url = "/ResearchMasterProject/jsp/common/checkId.jsp?userid=" + userInput.userid.value;
	
	open(url, "confirm", "toolbar=no, location=no, status=no, menubar=no, width=500, height=230");
}
function select(value)
{
	if(value == 0) //일반
	{ 
		document.getElementById('nor').style.display = ''; 
		document.getElementById('lea').style.display = 'none';
		document.getElementById('val').value=0;
	}
	else if(value == 1)//기관대표
	{ 
		document.getElementById('nor').style.display = 'none';
		document.getElementById('lea').style.display = '';
		document.getElementById('val').value=1;
	} 
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
		<tr><td>주소</td><td><input type="text" name="address" /></td></tr>
		<tr><td><input type="hidden" name="val" value="0" id="val"></td></tr>
		</table>
		 <hr />
		 <!-- 일반, 기관 대표, 전문가-->
		 <p align="center">기관정보</p>
		<hr />
		<p align="center">
		<select name="selectTest" onchange="select(this.value)" id="selectTest" >
                     <option value="0">일반 사용자</option>
                     <option value="1">기관 대표</option>
         </select></p>

		<!-- 일반 -->
		<table id="nor" align="center">
		<tr><td>기관명</td><td><select name="organName">
		<%
			for(int i=0;i<orarr.size();i++)
			{
				%>
				<option value=<%=i%>><%=orarr.get(i).getOrganName()%></option>
				<%
			}
		%> 
		</select></td></tr>
		<tr><td>기관 대표</td><td><input type="text" name="president"/></td></tr>
		<tr><td>팩스번호</td><td><input type="text" name="fax"/></td></tr>
		<tr><td>기관 주소</td><td><select name="regionCode1">
					 <option value="seoul">서울</option>
                     <option value="kido">경기도</option>
                     <option value="buk">경북</option> 
                     <option value="etc">그 외</option>
		</select></td></tr>		
		</table>

		<!-- 기관대표 -->
		<table  style="display: none" id="lea" align="center">
		<tr><td>기관 대표는 기관을 등록합니다.</td></tr>
		<tr><td>사업자번호</td><td><input type="text" name="orid"/></td></tr>
		<tr><td>기관명</td><td><input type="text" name="organName" /></td></tr>
		<tr><td>기관 대표</td><td><input type="text" name="president"/></td></tr>
		<tr><td>팩스번호</td><td><input type="text" name="fax"/></td></tr>
		<tr><td>기관 주소</td><td><select name="regionCode">
					 <option value="seoul">서울</option>
                     <option value="kido">경기도</option>
                     <option value="buk">경북</option> 
                     <option value="etc">그 외</option>
		</select></td></tr>		
		</table>
	 
		<hr />
		<p align="center">
		<input type="submit" name="submit" value="등록"/>
		<input type="reset" name="reset" value="다시작성"/></p>

	</form>
</div>
</body>
</html>
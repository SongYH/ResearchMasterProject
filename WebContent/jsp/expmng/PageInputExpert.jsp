<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList" %>
<%@ page import="Common.User" %>
<%@ page import="Common.Expert" %>
<%@ page import="Control.UserManage" %>
<% request.setCharacterEncoding("UTF-8"); %>

<%
	String SearchId = request.getParameter("searchID");

	UserManage user = new UserManage();
	User temp = new User();
	Expert expert = new Expert();
	boolean nullFlag = true;
	
	temp = user.reqUser(SearchId);// 유저의 아이디를 이용해서 유저 정보를 가져옴
    
	if(temp != null){
	    expert.setName(temp.getName());
	    expert.setId(temp.getId());
	    expert.setPassword(temp.getPassword());
	    expert.setSocialNumber(temp.getSocialNumber());
	    expert.setTel(temp.getTel());
	    expert.setEmail(temp.getEmail());
	    expert.setHomeAddress(temp.getAddress());
	    expert.setOrganId(temp.getOrganId());
	    nullFlag = false;
	}

%>

<!-- 여기서부터는 사용자에게 보여질 페이지입니다. -->
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script language="javascript">
function checkInput(List)
{

	if(List.new_officeaddress.value=="") alert("근무지 주소를 입력하세요!");
	else if(List.new_position.value=="") alert("직급을 입력하세요!");
	else if(List.new_accountinfo.value=="") alert("계좌정보를 입력하세요!");
	else if(List.new_field.value=="") alert("분야를 입력하세요!");
		
	else
	{
		
		document.chargerExpertList.action="/ResearchMasterProject/jsp/expmng/AddExpert.jsp";
		document.chargerExpertList.method="post";
		document.chargerExpertList.submit();
	}
}
</script>
</head>
<body>
<%@include file="/jsp/common/Main.jsp"%>

<div id="contents">
	<a href="/ResearchMasterProject/jsp/expmng/PageSearchExpert_User.jsp">전문가 추가</a>

	
<% 	if(nullFlag)
	{
%>
		<h2>ID에 해당하는 사용자가 없습니다.</h2>
<%	}
	else
	{
%>
<form method="post" name="ExpertList" action="/ResearchMasterProject/jsp/expmng/AddExpert.jsp">
<table style=margin:auto;>
		<tr>
			<th> 추가 정보 입력 </th>
		</tr>

			<tr>
				<td>아이디 </td><td><input type="text" name="user_id"  value ="<%= expert.getId() %>" /></td>
			</tr>
			<tr>
				<td>이름</td><td><input type="text" name="user_name"  value ="<%= expert.getName() %>" /></td>
			</tr>
			<tr>
				<td>전화번호</td><td><input type="text" name="user_tel"  value ="<%= expert.getTel() %>" /></td>
			</tr>
			<tr>
				<td>이메일</td><td><input type="text" name="user_email"  value ="<%= expert.getEmail() %>" /></td>
			</tr>
			<tr>
				<td><input type="text" name="user_password"  value ="<%= expert.getPassword() %>"style="display: none" /></td>
			</tr>
			<tr>
				<td><input type="text" name="user_address" value ="<%= expert.getHomeAddress() %>"style="display: none" /></td>
			</tr>
			<tr>
				<td><input type="text" name="user_organid" value ="<%= expert.getOrganId() %>"style="display: none" /></td>
			</tr>
			<tr>
				<td><input type="text" name="user_socialnumber" value ="<%= expert.getSocialNumber() %>"style="display: none" /></td>
			</tr>
			<tr><td>근무지주소</td>   <td>	<input type="text" name="new_officeaddress"  /> </td></tr>
			<tr><td>직급</td>       <td>	<input type="text" name="new_position"  /> </td></tr>
			<tr><td>계좌정보</td>     <td>	<input type="text" name="new_accountinfo"  /> </td></tr>
			<tr><td>분야</td>        
			<td>
				<select name="new_field" id="new_field">
				<option value="저변확대형">저변확대형</option>
				<option value="선택집중형">선택집중형</option>
				<option value="인프라조성형">인프라조성형</option>
				</select>	
			 </td>
			 </tr>
			<tr>
				<td><input type="submit" name="button" value="완료" onclick="checkInput(this.form)" /></td>
				<td><input type="reset" name="reset" value="다시작성" /></td>
			</tr>
			

	</table>
<%	}
%>
</form>

</div>

</body>
</html>
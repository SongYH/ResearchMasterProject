<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="Common.*, Control.*, Entity.*"  %>
<%@ page import="java.util.ArrayList;" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="/ResearchMasterProject/css/style.css" />
<title>사용자조회</title>
 <%
 	PermissionManager pm = new PermissionManager(); 
	UserManage um = new UserManage();
	ArrayList<User> arr = new ArrayList<User>();
	User user = new User();
 %>
<%@include file="/jsp/common/Main.jsp"%>
<%if(loginId == null || loginId == "") 
	{
		response.sendRedirect("/ResearchMasterProject/jsp/common/login.jsp");
	}else { // 로그인 되있는 상태면
		String permission = pm.confirmUserPermission(loginId);		// 권한을 아이디를 통해 가져옴 
		if(permission.equals("권한담당자")||permission.equals("과제담당자")||permission.equals("전문가담당자")
		   ||permission.equals("정산담당자")||permission.equals("평가담당자"))	// 담당자 일때
		{
			request.setCharacterEncoding("utf-8");
			String text1 = request.getParameter("textbox");
			String text2 = request.getParameter("textbox1");
			
			if(text1 != null)
			{
				int val = Integer.parseInt(request.getParameter("val"));
				   
				if(val == 0)
				{
					user = um.reqUser(text1); 
					if(user ==null)
					{
						System.out.println("사용자 없음");
					}
					else
					{
						arr.add(user);
					}  
				}
				else if(val == 1)
				{
				 	System.out.println(text1);

					arr = um.reqUserListFromName(text1);
				} 
				else if(val == 3)
				{
					arr = um.requserorid(Integer.parseInt(text1));
				}
			}
			else {
			}
		}
		else
	 	{ // 팝업창 띄우고 Home 화면으로 가기
			 out.println("<script>alert('권한이 없습니다'); location.href = '/ResearchMasterProject/jsp/common/Home.jsp'; </script>"); 
	 	}
	} 
%> 
<script language="javascript">
function select(value)
{
	if(value == 0) //아이디만
	{ 
		document.getElementById('textbox1').style.display = 'none';
		document.getElementById('val').value=0;
	}
	else if(value == 1) //이름만
	{
		document.getElementById('textbox1').style.display = 'none';
		document.getElementById('val').value=1;
	}
	else if(value == 2)  //아이디+이름
	{
		document.getElementById('textbox1').style.display = '';
		document.getElementById('val').value=2;
	}
	else if(value == 3)  //사업자번호
	{
		document.getElementById('textbox1').style.display = 'none';
		document.getElementById('val').value=3;
	}
}
</script>
</head>
<body> 
<div id="contents">
	<form method="post" name="userInput" action="/ResearchMasterProject/jsp/usermng/PageSearchUser.jsp">
		<p align="center">기본정보</p> 
		<hr/>
		<table  width=800 border=0 cellpadding=1 cellspacing=3 align="center">
		<tr>
		<td></td>
		<td>조회</td>   
		<td><select name="selectTest" id="selectTest" onclick="select(this.value)">
                     <option value="0">아이디</option>
                     <option value="1">이름</option>
                     <option value="3">사업자번호</option>
         </select>
         </td>
        <td><input type="text" id="textbox" width="70" name ="textbox"  value="" style="ime-mode:active;"></td>
        <td><input type="text" id="textbox1" width="70" name ="textbox1" style="display: none"  value="" style="ime-mode:active;"></td>
        <td><input type="hidden" id="val" name="val" value="0">
        <td><input type="submit" value="조회"> <input type="submit" value="초기화"></tr>
		<tr>
		<td> 사용아이디 </td> 
		<td> 이름 </td> 
		<td> 주민번호 </td> 
		<td> 전화번호 </td> 
		<td> 이메일  </td> 
		<td> 주소 </td>
		</tr> 
		<%
		if(arr.size()>0)
		for(int i=0;i<arr.size();i++)
		{
		%>
		<tr>
		<td><%=arr.get(i).getId()%></td>
		<td><%=arr.get(i).getName()%></td>
		<td><%=arr.get(i).getSocialNumber()%></td>
		<td><%=arr.get(i).getTel()%></td>
		<td><%=arr.get(i).getEmail()%></td>
		<td><%=arr.get(i).getAddress()%></td>
		</tr>
		<%} %>
		</table> 
	</form>
</div>
</body>
</html>
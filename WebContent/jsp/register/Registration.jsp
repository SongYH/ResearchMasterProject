<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.*, java.text.*"  %>
<%@ page import="Common.*, Control.*"  %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>과제등록</title>

<%
	request.setCharacterEncoding("UTF-8");
	String login_id = (String)session.getAttribute("loginId");
	
	SubjectManager subjectM = new SubjectManager();
	PreProject preProject = new PreProject();
	User leader = new User();
	String organName=null;
	String printToday=null;
	String today=null;
	if(login_id != null)
	{
		preProject = subjectM.request_PreProject(login_id, "선정평가통과");
		//아이디로 User객체 가져오기
		UserManage userM = new UserManage();
		leader = userM.reqUser(login_id);
		organName = userM.reqOrganImformation(leader.getOrganId());
		java.text.SimpleDateFormat formatter = new java.text.SimpleDateFormat("yyyy-MM-dd");
		printToday = formatter.format(new java.util.Date());
		formatter = new java.text.SimpleDateFormat("yyyyMMdd");
		today = formatter.format(new java.util.Date());
	}
	
	//로그인후 세션
%>

</head>
<body>
	<%@include file="/jsp/common/Main.jsp"%>
	<%if(loginId == null || loginId == "") 
	{
		response.sendRedirect("/ResearchMasterProject/jsp/common/login.jsp");
	}else {
		PermissionManager perm = new PermissionManager();		// 권한을 확인하기위한 권한관리객체 생성
		String actor = perm.confirmUserPermission(loginId);			// 권한을 아이디를 통해 가져옴 
		if(actor.equals("과제책임자"))		// 정산담당자 일때
	{ %>
		{ 
		<div id="contents">
		<!-- 넘길 데이터
			프로젝트명 / 종류 / 분야 / 		
		
		 -->
		<!-- 과제 등록 폼 -->
		<form method="post" name="projectInput" action="/ResearchMasterProject/jsp/register/R_ActionRegistration.jsp">
			<hr /> <p align="center">과제등록</p> <hr />
			<table align="center">
				<tr>
					<td align="right" >과제명 : </td>
					<td align="left"><%=preProject.getSubjectName() %>
						<input type="hidden" name="projectName" value="<%=preProject.getSubjectName() %>" />
					</td>
				</tr>
				<tr>
					<td align="right">과제종류 : </td> 
					<td align="left"><%=preProject.getArea()%>
					<input type="hidden" name="projectArea" value="<%=preProject.getArea() %>" />
					</td>
				</tr>
				<tr>
					<td align="right">과제기간 : </td>
					<td align="left"><%=preProject.getType()%>
					<input type="hidden" name="projectType" value="<%=preProject.getType() %>" />
					</td>
				</tr>
				<tr>
					<td align="right">과제책임자 : </td>
					<td align="left"><%=leader.getName()%>
					<input type="hidden" name="leaderName" value="<%=leader.getName() %>" />
					</td>
				</tr>
				<tr>
					<td align="right">기관명 : </td>
					<td align="left"><%=organName%>
					<input type="hidden" name="organName" value="<%=organName %>" />
					</td>
				</tr>
				<tr>
					<td align="right">사업자번호 : </td>
					<td align="left"><%=leader.getOrganId()%>
					<input type="hidden" name="organId" value="<%=leader.getOrganId() %>" />
					</td>
				</tr>
			 	<tr>
					<td align="right">등록일 : </td>
					<td align="left"><%=printToday%>
					<input type="hidden" name="registerDate" value="<%=printToday%>" />
					</td>
				</tr>
				<tr>
					<td align="right">계약연한 : </td>
					<td align="left">
					<select name="agreeYear" style="width:40px;">
						<option>1</option><option>2</option><option>3</option><option>4</option><option>5</option>
					</select>년
					</td>
					
				</tr>
			
			</table>
			
			<hr />
			
			<table align="center">
				<tr>
					<td align="right">파일첨부</td>
					<td align="left"><input type="text" name="fileName" /></td>
				</tr>
			</table>
			<hr />
			
			<table align="center" border="0" width="400">
				<tr>
					<td align="right" width="150">참여연구원 </td>
					<td align="left" class=text_bold	style="padding-left: 13px">
					
					<select name="selectTest" onchange="select(this.value)" id="selectTest">
							<option value="0">없음</option>
							<option value="1">1명</option>
							<option value="2">2명</option>
							<option value="3">3명</option>
							<option value="4">4명</option>
							<option value="5">5명</option>
					</select></td>
				</tr>
				<tr>
					<td colspan="2" align=left class=text_bold	style="padding-left: 13px">
					<input height="10"  placeholder="이름" size="10" name="workerName1" id="workerName1" style="display: none;">
					<input height="10" placeholder="주민번호(-없이)" name="workerSN1" id="workerSN1" style="display: none;"> 
					<input type="button" value="연구원확인" onclick="checkUser(this.form, 1)"id="workerBtn1" style="display: none;" />
					
					<input height="10"  placeholder="이름" size="10" name="workerName2" id="workerName2" style="display: none;">
					<input height="10" placeholder="주민번호(-없이)" name="workerSN2" id="workerSN2" style="display: none;"> 
					<input type="button" value="연구원확인" onclick="checkUser(this.form, 2)"id="workerBtn2" style="display: none;" />
					
					<input height="10"  placeholder="이름" size="10" name="workerName3" id="workerName3" style="display: none;">
					<input height="10" placeholder="주민번호(-없이)" name="workerSN3" id="workerSN3" style="display: none;"> 
					<input type="button" value="연구원확인" onclick="checkUser(this.form, 3)"id="workerBtn3" style="display: none;" />
					
					<input height="10"  placeholder="이름" size="10" name="workerName4" id="workerName4" style="display: none;">
					<input height="10" placeholder="주민번호(-없이)" name="workerSN4" id="workerSN4" style="display: none;"> 
					<input type="button" value="연구원확인" onclick="checkUser(this.form, 4)"id="workerBtn4" style="display: none;" />
					
					<input height="10"  placeholder="이름" size="10" name="workerName5" id="workerName5" style="display: none;">
					<input height="10" placeholder="주민번호(-없이)" name="workerSN5" id="workerSN5" style="display: none;"> 
					<input type="button" value="연구원확인" onclick="checkUser(this.form, 5)"id="workerBtn5" style="display: none;" />
				</td>
				</tr>
			</table>
			<hr />
			<p align="center">사업비 등록</p> 
			<hr />
			<table align="center" border=0>
				<tr>
					<td>총사업비</td>
					<td><input type="text" name="amount" /></td>
				</tr>
				<tr>
					<td colspan="2">
					<!-- 히든으로 넘어갈 데이터 -->
					<input type="hidden" name="status" value="등록">
					<input type="hidden" name="registerday"value=<%=today%>>
					<input type="hidden" name="workerId1"  id="workerId1" value="">
					<input type="hidden" name="workerId2" id="workerId2" value="">
					<input type="hidden" name="workerId3" id="workerId3" value="">
					<input type="hidden" name="workerId4" id="workerId4" value="">
					<input type="hidden" name="workerId5" id="workerId5" value="">
					<input type="submit" name="submit" value="등록" />
					<input type="reset" name="reset" value="다시작성" />
					</td>
				</tr>
			</table>
		</form>
	</div>
	<%
		}
		else
	 	{ // 팝업창 띄우고 Home 화면으로 가기
			 out.println("<script>alert('과제책임자가 아닙니다'); location.href = '/ResearchMasterProject/jsp/common/Home.jsp'; </script>"); 
	 	}
	} %>
</body>

<script language="javascript">
	function select(value) {
		if (value == 0) {
			document.getElementById('workerName1').style.display = 'none';
			document.getElementById('workerSN1').style.display = 'none';
			document.getElementById('workerName1').value ="";
			document.getElementById('workerSN1').value ="";
			document.getElementById('workerBtn1').style.display = 'none';
			
			document.getElementById('workerName2').style.display = 'none';
			document.getElementById('workerSN2').style.display = 'none';
			document.getElementById('workerName2').value ="";
			document.getElementById('workerSN2').value ="";
			document.getElementById('workerBtn2').style.display = 'none';
			
			document.getElementById('workerName3').style.display = 'none';
			document.getElementById('workerSN3').style.display = 'none';
			document.getElementById('workerName3').value ="";
			document.getElementById('workerSN3').value ="";
			document.getElementById('workerBtn3').style.display = 'none';
			
			document.getElementById('workerName4').style.display = 'none';
			document.getElementById('workerSN4').style.display = 'none';
			document.getElementById('workerName4').value ="";
			document.getElementById('workerSN4').value ="";
			document.getElementById('workerBtn4').style.display = 'none';
			
			document.getElementById('workerName5').style.display = 'none';
			document.getElementById('workerSN5').style.display = 'none';
			document.getElementById('workerName5').value ="";
			document.getElementById('workerSN5').value ="";
			document.getElementById('workerBtn5').style.display = 'none';
			
			document.getElementById('workerId1').value ="";
			document.getElementById('workerId2').value ="";
			document.getElementById('workerId3').value ="";
			document.getElementById('workerId4').value ="";
			document.getElementById('workerId5').value ="";
		} 
		else if(value == 1) {
			document.getElementById('workerName1').style.display = '';
			document.getElementById('workerSN1').style.display = '';
			document.getElementById('workerName1').value ="";
			document.getElementById('workerSN1').value ="";
			document.getElementById('workerBtn1').style.display = '';
			
			document.getElementById('workerName2').style.display = 'none';
			document.getElementById('workerSN2').style.display = 'none';
			document.getElementById('workerName2').value ="";
			document.getElementById('workerSN2').value ="";
			document.getElementById('workerBtn2').style.display = 'none';
			
			document.getElementById('workerName3').style.display = 'none';
			document.getElementById('workerSN3').style.display = 'none';
			document.getElementById('workerName3').value ="";
			document.getElementById('workerSN3').value ="";
			document.getElementById('workerBtn3').style.display = 'none';
			
			document.getElementById('workerName4').style.display = 'none';
			document.getElementById('workerSN4').style.display = 'none';
			document.getElementById('workerName4').value ="";
			document.getElementById('workerSN4').value ="";
			document.getElementById('workerBtn4').style.display = 'none';
			
			document.getElementById('workerName5').style.display = 'none';
			document.getElementById('workerSN5').style.display = 'none';
			document.getElementById('workerName5').value ="";
			document.getElementById('workerSN5').value ="";
			document.getElementById('workerBtn5').style.display = 'none';
			
			document.getElementById('workerId1').value ="";
			document.getElementById('workerId2').value ="";
			document.getElementById('workerId3').value ="";
			document.getElementById('workerId4').value ="";
			document.getElementById('workerId5').value ="";
				
		}
		else if(value == 2) {
			document.getElementById('workerName1').style.display = '';
			document.getElementById('workerSN1').style.display = '';
			document.getElementById('workerName1').value ="";
			document.getElementById('workerSN1').value ="";
			document.getElementById('workerBtn1').style.display = '';
			
			document.getElementById('workerName2').style.display = '';
			document.getElementById('workerSN2').style.display = '';
			document.getElementById('workerName2').value ="";
			document.getElementById('workerSN2').value ="";
			document.getElementById('workerBtn2').style.display = '';
			
			document.getElementById('workerName3').style.display = 'none';
			document.getElementById('workerSN3').style.display = 'none';
			document.getElementById('workerName3').value ="";
			document.getElementById('workerSN3').value ="";
			document.getElementById('workerBtn3').style.display = 'none';
			
			document.getElementById('workerName4').style.display = 'none';
			document.getElementById('workerSN4').style.display = 'none';
			document.getElementById('workerName4').value ="";
			document.getElementById('workerSN4').value ="";
			document.getElementById('workerBtn4').style.display = 'none';
			
			document.getElementById('workerName5').style.display = 'none';
			document.getElementById('workerSN5').style.display = 'none';
			document.getElementById('workerName5').value ="";
			document.getElementById('workerSN5').value ="";
			document.getElementById('workerBtn5').style.display = 'none';
			
			document.getElementById('workerId1').value ="";
			document.getElementById('workerId2').value ="";
			document.getElementById('workerId3').value ="";
			document.getElementById('workerId4').value ="";
			document.getElementById('workerId5').value ="";
		}
		else if(value == 3) {
			document.getElementById('workerName1').style.display = '';
			document.getElementById('workerSN1').style.display = '';
			document.getElementById('workerName1').value ="";
			document.getElementById('workerSN1').value ="";
			document.getElementById('workerBtn1').style.display = '';
			
			document.getElementById('workerName2').style.display = '';
			document.getElementById('workerSN2').style.display = '';
			document.getElementById('workerName2').value ="";
			document.getElementById('workerSN2').value ="";
			document.getElementById('workerBtn2').style.display = '';
			
			document.getElementById('workerName3').style.display = '';
			document.getElementById('workerSN3').style.display = '';
			document.getElementById('workerName3').value ="";
			document.getElementById('workerSN3').value ="";
			document.getElementById('workerBtn3').style.display = '';
			
			document.getElementById('workerName4').style.display = 'none';
			document.getElementById('workerSN4').style.display = 'none';
			document.getElementById('workerName4').value ="";
			document.getElementById('workerSN4').value ="";
			document.getElementById('workerBtn4').style.display = 'none';
			
			document.getElementById('workerName5').style.display = 'none';
			document.getElementById('workerSN5').style.display = 'none';
			document.getElementById('workerName5').value ="";
			document.getElementById('workerSN5').value ="";
			document.getElementById('workerBtn5').style.display = 'none';
			
			document.getElementById('workerId1').value ="";
			document.getElementById('workerId2').value ="";
			document.getElementById('workerId3').value ="";
			document.getElementById('workerId4').value ="";
			document.getElementById('workerId5').value ="";
		}
		else if(value == 4) {
			document.getElementById('workerName1').style.display = '';
			document.getElementById('workerSN1').style.display = '';
			document.getElementById('workerName1').value ="";
			document.getElementById('workerSN1').value ="";
			document.getElementById('workerBtn1').style.display = '';
			
			document.getElementById('workerName2').style.display = '';
			document.getElementById('workerSN2').style.display = '';
			document.getElementById('workerName2').value ="";
			document.getElementById('workerSN2').value ="";
			document.getElementById('workerBtn2').style.display = '';
			
			document.getElementById('workerName3').style.display = '';
			document.getElementById('workerSN3').style.display = '';
			document.getElementById('workerName3').value ="";
			document.getElementById('workerSN3').value ="";
			document.getElementById('workerBtn3').style.display = '';
			
			document.getElementById('workerName4').style.display = '';
			document.getElementById('workerSN4').style.display = '';
			document.getElementById('workerName4').value ="";
			document.getElementById('workerSN4').value ="";
			document.getElementById('workerBtn4').style.display = '';
			
			document.getElementById('workerName5').style.display = 'none';
			document.getElementById('workerSN5').style.display = 'none';
			document.getElementById('workerName5').value ="";
			document.getElementById('workerSN5').value ="";
			document.getElementById('workerBtn5').style.display = 'none';
			
			document.getElementById('workerId1').value ="";
			document.getElementById('workerId2').value ="";
			document.getElementById('workerId3').value ="";
			document.getElementById('workerId4').value ="";
			document.getElementById('workerId5').value ="";
		}
		else if(value == 5) {
			document.getElementById('workerName1').style.display = '';
			document.getElementById('workerSN1').style.display = '';
			document.getElementById('workerName1').value ="";
			document.getElementById('workerSN1').value ="";
			document.getElementById('workerBtn1').style.display = '';
			
			document.getElementById('workerName2').style.display = '';
			document.getElementById('workerSN2').style.display = '';
			document.getElementById('workerName2').value ="";
			document.getElementById('workerSN2').value ="";
			document.getElementById('workerBtn2').style.display = '';
			
			document.getElementById('workerName3').style.display = '';
			document.getElementById('workerSN3').style.display = '';
			document.getElementById('workerName3').value ="";
			document.getElementById('workerSN3').value ="";
			document.getElementById('workerBtn3').style.display = '';
			
			document.getElementById('workerName4').style.display = '';
			document.getElementById('workerSN4').style.display = '';
			document.getElementById('workerName4').value ="";
			document.getElementById('workerSN4').value ="";
			document.getElementById('workerBtn4').style.display = '';
			
			document.getElementById('workerName5').style.display = '';
			document.getElementById('workerSN5').style.display = '';
			document.getElementById('workerName5').value ="";
			document.getElementById('workerSN5').value ="";
			document.getElementById('workerBtn5').style.display = '';
			
			document.getElementById('workerId1').value ="";
			document.getElementById('workerId2').value ="";
			document.getElementById('workerId3').value ="";
			document.getElementById('workerId4').value ="";
			document.getElementById('workerId5').value ="";
		}
	} // end of select 

	
	function checkUser(projectInput, select) {
		if(select == 1)
		{
			if (projectInput.workerName1.value == "") {
				alert("이름을 입력하세요1");
				return;
			}
			if (projectInput.workerSN1.value == "") {
				alert("주민번호를 입력하세요1");
				return;
			}
		
			url = "/ResearchMasterProject/jsp/register/R_CheckUser.jsp?num=1&name=" + projectInput.workerName1.value
			+"&socialnum=" + projectInput.workerSN1.value ;
			open(url, "confirm", "toolbar=no, location=no, status=no, menubar=no, width=500, height=230");
		}
		if(select == 2)
		{
			if (projectInput.workerName2.value == "") {
				alert("이름을 입력하세요2");
				return;
			}
			if (projectInput.workerSN2.value == "") {
				alert("주민번호를 입력하세요2");
				return;
			}
		
			url = "/ResearchMasterProject/jsp/register/R_CheckUser.jsp?num=2&name=" + projectInput.workerName2.value
			+"&socialnum=" + projectInput.workerSN2.value ;
			open(url, "confirm", "toolbar=no, location=no, status=no, menubar=no, width=500, height=230");
		}
		
		if(select == 3)
		{
			if (projectInput.workerName3.value == "") {
				alert("이름을 입력하세요3");
				return;
			}
			if (projectInput.workerSN3.value == "") {
				alert("주민번호를 입력하세요3");
				return;
			}
		
			url = "/ResearchMasterProject/jsp/register/R_CheckUser.jsp?num=3&name=" + projectInput.workerName3.value
			+"&socialnum=" + projectInput.workerSN3.value ;
			open(url, "confirm", "toolbar=no, location=no, status=no, menubar=no, width=500, height=230");
		}
		
		if(select == 4)
		{
			if (projectInput.workerName4.value == "") {
				alert("이름을 입력하세요4");
				return;
			}
			if (projectInput.workerSN4.value == "") {
				alert("주민번호를 입력하세요4");
				return;
			}
		
			url = "/ResearchMasterProject/jsp/register/R_CheckUser.jsp?num=4&name=" + projectInput.workerName4.value
			+"&socialnum=" + projectInput.workerSN4.value ;
			open(url, "confirm", "toolbar=no, location=no, status=no, menubar=no, width=500, height=230");
		}
		
		if(select == 5)
		{
			if (projectInput.workerName5.value == "") {
				alert("주민번호를 입력하세요5");
				return;
			}
			if (projectInput.workerSN5.value == "") {
				alert("주민번호를 입력하세요5");
				return;
			}
		
			url = "/ResearchMasterProject/jsp/register/R_CheckUser.jsp?num=5&name=" + projectInput.workerName5.value
			+"&socialnum=" + projectInput.workerSN5.value ;
			open(url, "confirm", "toolbar=no, location=no, status=no, menubar=no, width=500, height=230");
		}

	} //end of checkUser
	</script>

</html>
package Control;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import Common.Budget;
import Common.BudgetCareer;
import Common.PreProject;
import Common.Project;
import Common.ProjectCareer;
import Common.User;
import Entity.BudgetCareerList;
import Entity.BudgetList;
import Entity.ProjectCareerList;
import Entity.ProjectList;

public class ProjectManager
{
	public ProjectManager()
	{
	}

	//1
	// 선정평가 통과(아이디 / 상태) 된 신청과제 정보 가져오기
	public PreProject reqSelectionEvaluationProject(String id, String status)
	{
		SubjectManager subjectManager = new SubjectManager();
		return subjectManager.request_PreProjectList(id, status);
	}

	//1
	// 과제번호목록을 받고 해당 과제를 List로 만들어서 리턴
	public ArrayList<Project> reqProjectList(ArrayList<Integer> pNumList)
	{
		ProjectList projectList = ProjectList.getInstance();
		ArrayList<Project> tempList = new ArrayList<Project>();

		for (int x : pNumList)
		{
			Project temp = new Project();
			temp = projectList.getProjectSearchNumber(x);
			if (temp != null)
			{
				tempList.add(temp);
			}
		}
		return tempList;
	}
	//1
	public int createProjectNumber()
	{
		ProjectList projectList = ProjectList.getInstance();
		return projectList.createProjectNumber();
	}
	//1
	// 과제정보목록
	// 요청종류 : 상태별/기간별/과제종류 별
	// 요청내용 : 중간평가완료 등등/ 단기 중장기 / 모바일 등등
	public ArrayList<Project> reqProjectList(String type, String content)
	{
		ProjectList projectList = ProjectList.getInstance();

		switch (type)
		{
		case "상태":
			return projectList.getStatusProjectList(content);

		case "분야":
			return projectList.getTypeProjectList(content);

		case "기간":
			return projectList.getAreaProjectList(content);

		}
		return null;
	}

	//1
	// 전체과제 정보
	public ArrayList<Project> reqAllProjectList()
	{
		ProjectList projectList = ProjectList.getInstance();
		return projectList.getProjectList();
	}

	//1
	// 과제번호로 과제정보
	// 있으면 과제정보 없으면 null
	public Project reqProjectSearchNumber(int num)
	{
		ProjectList projectList = ProjectList.getInstance();
		return projectList.getProjectSearchNumber(num);
	}
	//1
	// 책임자 아이디로 과제정보
	// 있으면 과제정보 없으면 null
	public Project reqProjectSearchID(String id)
	{
		ProjectList projectList = ProjectList.getInstance();
		return projectList.getProjectSearchID(id);
	}
	
	//1
	// 과제 상태 변경 (과제번호 / 상태)
	public boolean setChangeProjectStatus(int num, String state)
	{
		ProjectList projectList = ProjectList.getInstance();
		if (projectList.setChangeProjectStatus(num, state))
			return true;
		else
			return false;
	}

	//1
	// 사용자아이디 가져오기
	public String reqUserId(String userName, String socialNumber)
	{
		UserManage um = new UserManage();
		return um.reqUserId(userName, socialNumber);
	}

	//1
	public ArrayList<User> reqUserListInfo(ArrayList<String> userid)
	{
		UserManage userM = new UserManage();
		return userM.requserImformation(userid);
	}
	
	public User reqUserInfo(String id)
	{
		UserManage userM = new UserManage();
		return userM.reqUser(id);
	}
	//1
	public String reqOrganName(int organId)
	{
		UserManage userM = new UserManage();
		return userM.reqOrganImformation(organId);
	}
	
	// 사업비 관련///
	public void addBudget(Budget b)
	{
		BudgetList bl = BudgetList.getInstance();
		bl.addBudget(b);
	}

	//1
	public Budget reqBudgetSearchNumber(int projectNumber)
	{
		BudgetList budgetList = BudgetList.getInstance();
		return budgetList.getBudgetSearchNumber(projectNumber);
	}

	//1
	// 사업비 관련 끝//
	public void addProject(Project p, Budget b) throws ParseException
	{
		ProjectList projectList = ProjectList.getInstance();
		projectList.regAddProject(p); // 프로젝트 등록
		BudgetCareerList budgetCareerList = BudgetCareerList.getInstance(); 
		ProjectCareerList projectCareerList = ProjectCareerList.getInstance(); 
		
		addBudget(b); // 사업비 등록

		Date from = new Date();
		SimpleDateFormat transFormat = new SimpleDateFormat("yyyyMMdd");
		String stringToday = transFormat.format(from);

		SimpleDateFormat transFormat1 = new SimpleDateFormat("yyyyMMdd");
		Date today = transFormat.parse(stringToday);

		CareerManager careerManager = new CareerManager();
		careerManager.addBudgetCareer(new BudgetCareer(budgetCareerList.getBudgetCareerList().size()+1,today, b));
		careerManager.addProjectCareer(new ProjectCareer(projectCareerList.getProjectCareerList().size()+1, today, p));
	}
	//1
	public String reqApproval()
	{
		return "등록승인";
	}
	//1
	public ArrayList<String> reqResearcherIDList(int num)
	{
		ProjectList projectList = ProjectList.getInstance();
		return projectList.getResearcherIDList(num);
	}
	
	//1
	public String reqUserPermission(String loginId)
	{
		PermissionManager perm = new PermissionManager();		// 권한을 확인하기위한 권한관리객체 생성
		String permission = perm.confirmUserPermission(loginId);			// 권한을 아이디를 통해 가져옴 
		
		return permission;
	}
	
	//1
	public boolean reqSetPreProjectState(int num, String state)
	{
		SubjectManager subm = new SubjectManager();
		return subm.setChangePreProjectStatus(num, state);
	}
	
}

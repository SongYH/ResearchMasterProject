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
import Entity.BudgetList;
import Entity.ProjectList;

public class ProjectManager
{
	public ProjectManager()
	{
	}

	// 선정평가 통과(아이디 / 상태) 된 신청과제 정보 가져오기
	public PreProject reqSelectionEvaluationProject(String id, String status)
	{
		SubjectManager subjectManager = new SubjectManager();
		return subjectManager.request_PreProjectList(id, status);
	}

	// 파라미터 - 아이디 / 상태 / 리턴 신청과제정보

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
	public int createProjectNumber()
	{
		ProjectList projectList = ProjectList.getInstance();
		return projectList.createProjectNumber();
	}
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

	// 전체과제 정보
	public ArrayList<Project> reqAllProjectList()
	{
		ProjectList projectList = ProjectList.getInstance();
		return projectList.getAllProjectList();
	}

	// 과제번호로 과제정보
	// 있으면 과제정보 없으면 null
	public Project reqProjectSearchNumber(int num)
	{
		ProjectList projectList = ProjectList.getInstance();
		return projectList.getProjectSearchNumber(num);
	}

	// 책임자 아이디로 과제정보
	// 있으면 과제정보 없으면 null
	public Project reqProjectSearchID(String id)
	{
		ProjectList projectList = ProjectList.getInstance();
		return projectList.getProjectSearchID(id);
	}

	// 과제 상태 변경 (과제번호 / 상태)
	public boolean setChangeProjectStatus(int num, String state)
	{
		ProjectList projectList = ProjectList.getInstance();
		if (projectList.setChangeProjectStatus(num, state))
			return true;
		else
			return false;
	}

	// 사용자아이디 가져오기
	public String reqUserId(String userName, String socialNumber)
	{
		UserManage um = new UserManage();
		return um.reqUserId(userName, socialNumber);
	}

	// 사업비 관련///
	public void addBudget(Budget b)
	{
		BudgetList bl = BudgetList.getInstance();
		bl.addBudget(b);
	}

	public Budget reqBudgetSearchNumber(int projectNumber)
	{
		BudgetList budgetList = BudgetList.getInstance();
		return budgetList.getBudgetSearchNumber(projectNumber);
	}

	// 사업비 관련 끝//
	public void addProject(Project p, Budget b) throws ParseException
	{
		ProjectList projectList = ProjectList.getInstance();
		projectList.regAddProject(p); // 프로젝트 등록
		addBudget(b); // 사업비 등록

		Date from = new Date();
		SimpleDateFormat transFormat = new SimpleDateFormat("yyyyMMdd");
		String stringToday = transFormat.format(from);

		SimpleDateFormat transFormat1 = new SimpleDateFormat("yyyyMMdd");
		Date today = transFormat.parse(stringToday);

		CareerManager careerManager = new CareerManager();
		careerManager.addBudgetCareer(new BudgetCareer(today, b));
		careerManager.addProjectCareer(new ProjectCareer(today, p));
	}

	public ArrayList<String> reqResearcherIDList(int num)
	{
		ProjectList projectList = ProjectList.getInstance();
		return projectList.getResearcherIDList(num);
	}

}

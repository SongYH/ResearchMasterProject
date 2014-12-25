package Entity;

import java.util.ArrayList;
import java.util.GregorianCalendar;

import Common.Project;
import DAO.DAOpreproject;
import DAO.DAOproject;

/**
 * 우리가 그린 Class 다이어그램에서 목록 Entity에 해당하는 클래스 UML에서 나타낸 함수들을 여기서 만들면된다.
 * 
 * @author JM
 * 
 */
public class ProjectList
{
	private ArrayList<Project> projectList;
	private static class ProjectListHolder
	{
		static final ProjectList uniqueInstance = new ProjectList();
	}

	public static ProjectList getInstance()
	{
		return ProjectListHolder.uniqueInstance;
	}

	// //////////////////////////////
	//1
	public boolean regAddProject(Project project)
	{
		projectList.add(project);
		return true;
	}

	//1
	public ArrayList<Project> getProjectList()
	{
		return projectList;
	}

	//1
	public int createProjectNumber()
	{
		int projectNum =0;
		int projectCnt =1; // 
		GregorianCalendar today = new GregorianCalendar ( );  // 오늘 연도 가져오기
		String year = Integer.toString(today.get(today.YEAR));
		
		for (Project p : projectList)
		{
			//int로 저장되있는 과제번호 String으로 변경
			String temp = Integer.toString(p.getProjectNumber());
			//과제번호 - 20140001 이런식으로 되있음 앞에 연도를 따오기 위해
			//년도별로 갯수 파악하기
			if(temp.substring(0, 4).equals(year))
				projectCnt++;
		}
		
		//이번년도 프로젝트 갯수 파악 끝
		projectNum = Integer.parseInt(year); // 이번연도 int로 변환
		projectNum *= 1000; // 기본 폼 20140000 뒤에 4자리
		projectNum += projectCnt;
		return projectNum;
	}
	
	//1
	// 상태별 과제
	public ArrayList<Project> getStatusProjectList(String state)
	{
		ArrayList<Project> statusProjectList = new ArrayList<Project>();

		for (Project p : projectList)
		{
			// 입력한 상태와 같은 리스트
			if (p.getStatus().equals(state))
			{
				statusProjectList.add(p);
			}
		}
		return statusProjectList;
	}
	//1
	// 분야별 과제
	public ArrayList<Project> getTypeProjectList(String type)
	{
		ArrayList<Project> typeProjectList = new ArrayList<Project>();

		for (Project p : projectList)
		{
			// 입력한 분야 같은 리스트
			if (p.getType().equals(type))
			{
				typeProjectList.add(p);
			}
		}
		return typeProjectList;
	}
	//1
	// 기간별 과제
	public ArrayList<Project> getAreaProjectList(String area)
	{
		ArrayList<Project> areaProjectList = new ArrayList<Project>();

		for (Project p : projectList)
		{
			// 입력한 기간(단기/중장기)와 같은 리스트
			if (p.getArea().equals(area))
			{
				areaProjectList.add(p);
			}
		}
		return areaProjectList;
	}
	//1
	// 과제담당자 아이디로 과제 찾기
	public Project getProjectSearchID(String id)
	{
		for (Project p : projectList)
		{
			if (p.getLeaderId().equals(id))
				return p;
		}
		return null;
	}
	//1
	// 해당 과제 번호의 과제정보
	public Project getProjectSearchNumber(int num)
	{
		for (Project p : projectList)
		{
			if (p.getProjectNumber() == num)
				return p;
		}
		return null;
	}

	public boolean setChangeProjectStatus(int num, String state)
	{
		for (Project p : projectList)
		{
			if (p.getProjectNumber() == num)
			{
				p.setStatus(state);
				return true;
			}
		}
		return false;
	}

	public ArrayList<String> getResearcherIDList(int num)
	{
		for (Project p : projectList)
		{
			if (p.getProjectNumber() == num)
			{
				return p.getUserIdList();
			}
		}
		return null;
	}

	public void setProjectList(ArrayList<Project> projectList)
	{
		this.projectList = projectList;
	}
	
	
}
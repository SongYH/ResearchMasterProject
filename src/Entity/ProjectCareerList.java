package Entity;

import java.util.ArrayList;
import java.util.Date;
import Common.ProjectCareer;
import DAO.DAOprojectCareer;


public class ProjectCareerList {
	
	private ArrayList<ProjectCareer> ProjectCareerList;

	private static class ProjectCareerListHolder {
		static final ProjectCareerList uniqueInstance = new ProjectCareerList();
	}

	private ProjectCareerList() {
	}

	public static ProjectCareerList getInstance() {
		return ProjectCareerListHolder.uniqueInstance;
	}
	////////////////////////////////

	public void addProjectCareer(ProjectCareer b) {
		ProjectCareerList.add(b);
	}

	//일시와 과제번호로 과제 이력 가져오기
	public ProjectCareer getProjectCareer(Date date,int projectNumber) {
		for (ProjectCareer b : ProjectCareerList) {
			if ( (b.getRecordDate()).compareTo(date)==0 && (b.getProjectInfo()).getProjectNumber()==projectNumber )
				return b;
		}
		return null;
	}
	
	//일시, 과제상태, 과제번호에 해당하는 과제 이력 목록 가져오기
	public ArrayList<ProjectCareer> getProjectCareerList(Date startDate, Date endDate,String status, int projectNumber ) {
		
		ArrayList<ProjectCareer> tempProjectCareerList=new ArrayList<ProjectCareer>();
		
		for (ProjectCareer b : ProjectCareerList) {
			boolean statusCheck=false;
			boolean projectNumberCheck=false;
			
			if( (b.getProjectInfo()).getStatus().equals(status) )
				statusCheck=true;
			if( (b.getProjectInfo()).getProjectNumber() ==projectNumber )
				projectNumberCheck=true;
			
			if(status.equals("모두"))
				statusCheck=true;
			if(projectNumber < 0)
				projectNumberCheck=true;
	
				if ( (b.getRecordDate()).compareTo(startDate)>=0 && (b.getRecordDate()).compareTo(endDate)<=0 && statusCheck && projectNumberCheck )
					tempProjectCareerList.add(b);			

		}
		return tempProjectCareerList;
	}
	
	public void setProjectCareerList(ArrayList<ProjectCareer> projectCareerList) {
		ProjectCareerList = projectCareerList;
	}

	public ArrayList<ProjectCareer> getProjectCareerList() {
		return ProjectCareerList;
	}
}

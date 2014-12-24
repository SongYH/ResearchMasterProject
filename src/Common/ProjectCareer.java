package Common;

import java.util.ArrayList;
import java.util.Date;


public class ProjectCareer {
	
	private Date recordDate;//이력 기록일
	private Project projectInfo;//과제 정보
	
	public ProjectCareer(Date recordDate,Project projectInfo)
	{
		this.recordDate=recordDate;
		this.projectInfo=projectInfo;
	}
	
	public Date getRecordDate() {
		return recordDate;
	}
	public void setRecordDate(Date recordDate) {
		this.recordDate = recordDate;
	}
	public Project getProjectInfo() {
		return projectInfo;
	}
	public void setProjectInfo(Project projectInfo) {
		this.projectInfo = projectInfo;
	}

}

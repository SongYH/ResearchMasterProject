package Common;

import java.util.ArrayList;
import java.util.Date;


public class ProjectCareer {
	private int careerCnt;
	private Date recordDate;//이력 기록일
	private Project projectInfo;//과제 정보
	
	public ProjectCareer(int careerCnt, Date recordDate,Project projectInfo)
	{
		this.careerCnt = careerCnt;
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

	public int getCareerCnt() {
		return careerCnt;
	}

	public void setCareerCnt(int careerCnt) {
		this.careerCnt = careerCnt;
	}

}

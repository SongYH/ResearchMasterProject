package Common;

import java.util.ArrayList;
import java.util.Date;

public class FinEvalPlan
{
	private int projectNumber;
	private String expertLeaderId;
	private String expertLeaderEmail;
	private ArrayList<String> expertsEmail;
	private String projectLeaderEmail;
	private Date noticeDate;
	private String fileName;
	
	public FinEvalPlan(){}
	
	public FinEvalPlan(int projectNumber, String expertLeaderId, String expertLeaderEmail, ArrayList<String> expertsEmail, 
			String projectLeaderEmail, Date noticeDate, String fileName)
	{
		super();
		this.projectNumber = projectNumber; this.expertLeaderId = expertLeaderId; this.expertLeaderEmail = expertLeaderEmail;
		this.expertsEmail = expertsEmail; this.projectLeaderEmail = projectLeaderEmail; this.noticeDate = noticeDate;
		this.fileName = fileName;
	}

	public int getProjectNumber() {
		return projectNumber;
	}

	public void setProjectNumber(int projectNumber) {
		this.projectNumber = projectNumber;
	}

	public String getExpertLeaderId() {
		return expertLeaderId;
	}

	public void setExpertLeaderId(String expertLeaderId) {
		this.expertLeaderId = expertLeaderId;
	}

	public String getExpertLeaderEmail() {
		return expertLeaderEmail;
	}

	public void setExpertLeaderEmail(String expertLeaderEmail) {
		this.expertLeaderEmail = expertLeaderEmail;
	}

	public ArrayList<String> getExpertsEmail() {
		return expertsEmail;
	}

	public void setExpertsEmail(ArrayList<String> expertsEmail) {
		this.expertsEmail = expertsEmail;
	}

	public String getProjectLeaderEmail() {
		return projectLeaderEmail;
	}

	public void setProjectLeaderEmail(String projectLeaderEmail) {
		this.projectLeaderEmail = projectLeaderEmail;
	}

	public Date getNoticeDate() {
		return noticeDate;
	}

	public void setNoticeDate(Date noticeDate) {
		this.noticeDate = noticeDate;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	
}

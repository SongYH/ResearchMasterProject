package Common;

import java.util.ArrayList;
import java.util.Date;

public class Project
{
	private String status; // 과제 상태 - 등록 / 중간평가완료 / 정산완료 / 최종평가완료
	private String type; // 과제분야 / 모바일 / EM 등등
	private String area; // 단기 / 중장기
	private int projectNumber; // 과제 번호
	private String projectName; // 과제명
	private String leaderId; // 과제 책임자 아이디
	private String leaderName; // 과제 책임자 이름
	private int organId; // 사업자번호
	private String organName; // 기관명
	private Date registerDate; // 과제 등록일
	private int agreeYear; // 과제 협약연한 (몇년 협약했는지)
	private String fileName; // 첨부파일 이름
	private ArrayList<String> userIdList;

	// 생성자
	public Project(String status, String type, String area, int projectNumber, String projectName, String leaderId,
			String leaderName, int organId, String organName, Date registerDate, int agreeYear, String fileName,
			ArrayList<String> userIdList)
	{
		super();
		this.status = status;
		this.type = type;
		this.area = area;
		this.projectNumber = projectNumber;
		this.projectName = projectName;
		this.leaderId = leaderId;
		this.leaderName = leaderName;
		this.organId = organId;
		this.organName = organName;
		this.registerDate = registerDate;
		this.agreeYear = agreeYear;
		this.fileName = fileName;
		this.userIdList = userIdList;
	}

	public Project()
	{
	};

	// get set
	public String getStatus()
	{
		return status;
	}

	public void setStatus(String status)
	{
		this.status = status;
	}

	public String getType()
	{
		return type;
	}

	public void setType(String type)
	{
		this.type = type;
	}

	public String getArea()
	{
		return area;
	}

	public void setArea(String area)
	{
		this.area = area;
	}

	public int getProjectNumber()
	{
		return projectNumber;
	}

	public void setProjectNumber(int projectNumber)
	{
		this.projectNumber = projectNumber;
	}

	public String getLeaderId()
	{
		return leaderId;
	}

	public void setLeaderId(String leaderId)
	{
		this.leaderId = leaderId;
	}

	public String getLeaderName()
	{
		return leaderName;
	}

	public void setLeaderName(String leaderName)
	{
		this.leaderName = leaderName;
	}

	public int getOrganId()
	{
		return organId;
	}

	public void setOrganId(int organId)
	{
		this.organId = organId;
	}

	public String getOrganName()
	{
		return organName;
	}

	public void setOrganName(String organName)
	{
		this.organName = organName;
	}

	public Date getRegisterDate()
	{
		return registerDate;
	}

	public void setRegisterDate(Date registerDate)
	{
		this.registerDate = registerDate;
	}

	public int getAgreeYear()
	{
		return agreeYear;
	}

	public void setAgreeYear(int agreeYear)
	{
		this.agreeYear = agreeYear;
	}

	public String getFileName()
	{
		return fileName;
	}

	public void setFileName(String fileName)
	{
		this.fileName = fileName;
	}

	public ArrayList<String> getUserIdList()
	{
		return userIdList;
	}

	public void setUserIdList(ArrayList<String> userIdList)
	{
		this.userIdList = userIdList;
	}

	public String getProjectName()
	{
		return projectName;
	}

	public void setProjectName(String projectName)
	{
		this.projectName = projectName;
	}

}
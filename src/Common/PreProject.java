package Common;

public class PreProject {
	int projectNumber; // 프로젝트 번호
	int organId;	   // 사업자번호
	String subjectName;// 사업명
	String status;	   // 상태
	String type;       // 분류
	String area;       // 유형
	String id;         // 아이디
	String fileName;   // 파일명
	
	public PreProject(){}
	public PreProject(int projectNumber, int organId, String subjectName,
			String status, String type, String area, String id, String fileName) {
		super();
		this.projectNumber = projectNumber;
		this.organId = organId;
		this.subjectName = subjectName;
		this.status = status;
		this.type = type;
		this.area = area;
		this.id = id;
		this.fileName = fileName;
	}
	public int getProjectNumber() {
		return projectNumber;
	}
	public void setProjectNumber(int projectNumber) {
		this.projectNumber = projectNumber;
	}
	public int getOrganId() {
		return organId;
	}
	public void setOrganId(int organId) {
		this.organId = organId;
	}
	public String getSubjectName() {
		return subjectName;
	}
	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
}

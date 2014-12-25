package Common;

public class MidEvalResult
{
	private int projectNumber;
	private String projectLeaderId;
	private String result;
	private String fileName;
	
	public MidEvalResult(){}
	
	public MidEvalResult(int projectNumber, String projectLeaderId, String result, String fileName)
	{
		super();
		this.projectNumber = projectNumber; this.projectLeaderId = projectLeaderId;
		this.result = result; this.fileName = fileName;
	}
	
	public int getProjectNumber() {
		return projectNumber;
	}

	public void setProjectNumber(int projectNumber) {
		this.projectNumber = projectNumber;
	}

	public String getProjectLeaderId() {
		return projectLeaderId;
	}

	public void setProjectLeaderId(String projectLeaderId) {
		this.projectLeaderId = projectLeaderId;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	
}

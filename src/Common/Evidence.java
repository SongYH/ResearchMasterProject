package Common;

import java.util.Date;

public class Evidence {
	private int projectNumber;
	private int amount;
	private Date date;
	private String file;
	public int getProjectNumber() {
		return projectNumber;
	}
	public void setProjectNumber(int projectNumber) {
		this.projectNumber = projectNumber;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public Evidence(int projectNumber,int amount,String file)
	{
		this.projectNumber = projectNumber;
		this.amount = amount;
		this.file = file;
		date = new Date();
	}
	public Evidence(int projectNumber,int amount,String file, Date date)
	{
		this.projectNumber = projectNumber;
		this.amount = amount;
		this.date = date;
		this.file = file;
	}
	public Evidence()
	{
		projectNumber=0;
		amount=0;
	}

	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getFile() {
		return file;
	}
	public void setFile(String file) {
		this.file = file;
	}
}
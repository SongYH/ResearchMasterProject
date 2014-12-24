package Common;
import java.util.Date;

public class Clawback {
	private int projectNumber;
	private int rate = 15; // 환수금 이자율 
	private int amount;
	private Date date;
	public int getProjectNumber() {
		return projectNumber;
	}
	public void setProjectNumber(int projectNumber) {
		this.projectNumber = projectNumber;
	}
	public int getRate() {
		return rate;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	
	public Clawback(int projectNumber,int amount)
	{
		this.projectNumber = projectNumber;
		this.amount = amount;
		date = new Date();
	}
	public Clawback(int projectNumber,int amount, Date date)
	{
		this.projectNumber = projectNumber;
		this.amount = amount;
		this.date = date;
	}
	public Clawback(int projectNumber,int amount, int rate, Date date)
	{
		this.projectNumber = projectNumber;
		this.amount = amount;
		this.date = date;
		this.rate = rate;
	}
	public Clawback()
	{
		projectNumber=0;
		amount =0;
	}

	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	
}
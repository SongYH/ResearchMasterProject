package Common;

import java.util.Date;

public class Calculate
{
	private int projectNumber;
	private boolean verification; // 실인정
	private int amount;
	private Date date;

	public int getProjectNumber()
	{
		return projectNumber;
	}

	public void setProjectNumber(int projectNumber)
	{
		this.projectNumber = projectNumber;
	}

	public boolean isVerification()
	{
		return verification;
	}

	public void setVerification(boolean verification)
	{
		this.verification = verification;
	}

	public int getAmount()
	{
		return amount;
	}

	public void setAmount(int amount)
	{
		this.amount = amount;
	}

	public Calculate(int projectNumber, int amount, boolean verification)
	{
		this.projectNumber = projectNumber;
		this.amount = amount;
		this.verification = verification;
		setDate(new Date());
	}

	public Calculate(int projectNumber, int amount, boolean verification, Date date)
	{
		this.projectNumber = projectNumber;
		this.amount = amount;
		this.verification = verification;
		this.date = date;
	}

	public Calculate()
	{
		projectNumber = 0;
		amount = 0;
		verification = false;
	}

	public Date getDate()
	{
		return date;
	}

	public void setDate(Date date)
	{
		this.date = date;
	}
}
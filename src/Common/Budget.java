package Common;

public class Budget {
	private int projectNumber;
	private int amount;
	
	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public int getProjectNumber() {
		return projectNumber;
	}

	public void setProjectNumber(int projectNumber) {
		this.projectNumber = projectNumber;
	}

	public Budget(int projectNumber, int amount) {
		this.projectNumber = projectNumber;
		this.amount = amount;
	}
}

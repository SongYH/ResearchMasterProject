package Common;

import java.util.Date;

public class BudgetCareer {
	
	private Date recordDate;//이력 기록일
	private Budget budgetInfo;//사업비 예산 정보
	
	public BudgetCareer(Date recordDate, Budget budgetInfo) {
		this.recordDate = recordDate;
		this.budgetInfo = budgetInfo;
	}

	public Date getRecordDate() {
		return recordDate;
	}

	public void setRecordDate(Date recordDate) {
		this.recordDate = recordDate;
	}

	public Budget getBudgetInfo() {
		return budgetInfo;
	}

	public void setBudgetInfo(Budget budgetInfo) {
		this.budgetInfo = budgetInfo;
	}
	

}

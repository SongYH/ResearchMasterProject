package Common;

import java.util.Date;

public class BudgetCareer {
	private int careerCnt;
	private Date recordDate;//이력 기록일
	private Budget budgetInfo;//사업비 예산 정보
	
	public BudgetCareer(int careerCnt, Date recordDate, Budget budgetInfo) {
		this.careerCnt = careerCnt;
		this.recordDate = recordDate;
		this.budgetInfo = budgetInfo;
	}

	public Date getRecordDate() {
		return recordDate;
	}

	public int getCareerCnt() {
		return careerCnt;
	}

	public void setCareerCnt(int careerCnt) {
		this.careerCnt = careerCnt;
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

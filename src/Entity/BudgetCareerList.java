package Entity;

import java.util.ArrayList;
import java.util.Date;
import Common.BudgetCareer;
import DAO.DAObudgetCareer;

public class BudgetCareerList {
	
	private ArrayList<BudgetCareer> budgetCareerList;
	
	public void setBudgetCareerList(ArrayList<BudgetCareer> budgetCareerList) {
		this.budgetCareerList = budgetCareerList;
	}

	private static class BudgetCareerHolder {
		static final BudgetCareerList uniqueInstance = new BudgetCareerList();
	}	
	
	private BudgetCareerList() {
	}
	

	public static BudgetCareerList getInstance() {
		return BudgetCareerHolder.uniqueInstance;
	}
	
	// //////////////////////////////////

	public void addBudgetCareer(BudgetCareer b) {
		budgetCareerList.add(b);
	}

	//일시와 과제번호로 일시에 가까운 사업비 이력 가져오기
	public BudgetCareer getBudgetCareer(Date date,int projectNumber) {
		
		BudgetCareer CloseBudgetCareer = null;
		
		for (BudgetCareer b : budgetCareerList) {
			
			if( (b.getRecordDate()).compareTo(date) >= 0)
				break;
			
			if ( (b.getRecordDate()).compareTo(date)<=0 && (b.getBudgetInfo()).getProjectNumber()==projectNumber )
				CloseBudgetCareer=b;
		}
		return CloseBudgetCareer;
	}
	//일시, 과제번호에 해당하는 사업비 이력 목록 가져오기
	public ArrayList<BudgetCareer> getBudgetCareerList(Date startDate, Date endDate, int projectNumber ) {
		
		ArrayList<BudgetCareer> tempBudgetCareerList=new ArrayList<BudgetCareer>();
		
		for (BudgetCareer b : budgetCareerList) {
			if ( (b.getRecordDate()).compareTo(startDate)>=0 && (b.getRecordDate()).compareTo(endDate)<=0 && (b.getBudgetInfo()).getProjectNumber()==projectNumber )
				tempBudgetCareerList.add(b);
		}
		return tempBudgetCareerList;
	}
	
	public ArrayList<BudgetCareer> getBudgetCareerList() {
		return budgetCareerList;
	}

}

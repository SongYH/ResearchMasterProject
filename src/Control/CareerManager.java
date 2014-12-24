package Control;

import java.util.ArrayList;
import java.util.Date;

import Common.BudgetCareer;
import Common.Calculate;
import Common.Clawback;
import Common.FinEvalResult;
import Common.Organ;
import Common.ProjectCareer;
import Common.User;
import Entity.BudgetCareerList;
import Entity.CalculateList;
import Entity.FinEvalResultList;
import Entity.ProjectCareerList;

public class CareerManager {
	
	public static class ProjectCareerUnityInfo
	{
		ProjectCareer projectCareer;
		BudgetCareer budgetCareer;
		//MidEvalResult는 없음...
		FinEvalResult finEvalResult;
		Calculate calculate;
		Clawback clawback;
		
		public ProjectCareerUnityInfo(ProjectCareer projectCareer, BudgetCareer budgetCareer, FinEvalResult finEvalResult, Calculate calculate,Clawback clawback)
		{
			this.projectCareer = projectCareer;
			this.budgetCareer = budgetCareer;
			//this.midEvalResult = midEvalResult;
			this.finEvalResult = finEvalResult;
			this.calculate = calculate;
			this.clawback = clawback;
		}
		public ProjectCareer getProjectCareer(){return projectCareer;}
		public BudgetCareer getBudgetCareer(){return budgetCareer;}
		//public User getMidEvalResult(){return midEvalResult;}
		public FinEvalResult getFinEvalResult(){return finEvalResult;}
		public Calculate getCalculate(){return calculate;}
		public Clawback getClawback(){return clawback;}
	}
	
	public ArrayList<ProjectCareer> requestProjectCareerList(Date startDate, Date endDate,String status, int projectNumber )
	{
		ProjectCareerList projectCareerList = ProjectCareerList.getInstance();
		
		return projectCareerList.getProjectCareerList(startDate,endDate,status,projectNumber);
	}
	public ArrayList<BudgetCareer> requestBudgetCareerList(Date startDate, Date endDate,int projectNumber )
	{
		BudgetCareerList budgetCareerList = BudgetCareerList.getInstance();
		
		return budgetCareerList.getBudgetCareerList(startDate,endDate,projectNumber);
	}
	
	public ProjectCareerUnityInfo requestProjectCareerUnityInfo(Date recordDate, int projectNumber)
	{
		/////////////////////////////////////////정보 요청할 클래스 식별////////////////////////////
		ProjectCareerList projectCareerList = ProjectCareerList.getInstance();	
		BudgetCareerList budgetCareerList = BudgetCareerList.getInstance();
		
		//MidEvalManager midEvalManager = new MidEvalManager();	
		FinEvalManager finEvalManager = new FinEvalManager();	
		CalculationManager calculationManager=new CalculationManager();

		
		///////////////////////////////////////////정보 요청////////////////////////////////
		ProjectCareer projectCareerInfo=projectCareerList.getProjectCareer(recordDate, projectNumber);
		BudgetCareer budgetCareerInfo=budgetCareerList.getBudgetCareer(recordDate, projectNumber);
		
		//MidEvalResult midEvalResultInfo=midEvalManager.requestMidEvalResult(projectNumber);
		FinEvalResult finEvalResultInfo=finEvalManager.requestFinEvalResult(projectNumber);
		Calculate calculateInfo=calculationManager.requestCalculate(projectNumber);
		Clawback clawbackInfo=calculationManager.requestClawback(projectNumber);
		
		ProjectCareerUnityInfo projectCareerUnityInfo= new ProjectCareerUnityInfo(projectCareerInfo,budgetCareerInfo,finEvalResultInfo,calculateInfo,clawbackInfo);
		return projectCareerUnityInfo;
		
	}
	
	
	public ArrayList<Organ> requestProjectOrganInfo(ArrayList<String> workerid)
	{	
		/*
		UserManage userManage = new UserManage();
		ArrayList<Organ> organImformationList=userManage.reqorganImformation(workerid);
		return organImformationList;
		*/
		return null;
	
	}
	
	public ArrayList<User> requestProjectUsersInfo(ArrayList<String> workerid)
	{
		UserManage userManage = new UserManage();
		ArrayList<User> userImformationList=userManage.requserImformation(workerid);
		return userImformationList;
	
	}
	
	public void addProjectCareer(ProjectCareer projectCareer)
	{
		ProjectCareerList projectCareerList= ProjectCareerList.getInstance();
		projectCareerList.addProjectCareer(projectCareer);
		
	}
	
	public void addBudgetCareer(BudgetCareer budgetCareer)
	{
		BudgetCareerList budgetCareerList= BudgetCareerList.getInstance();
		budgetCareerList.addBudgetCareer(budgetCareer);
	}

}

package Entity;
import java.util.ArrayList;
import java.util.Date;

import Common.FinEvalPlan;
import DAO.DAOfinEval;
import DAO.DAOfinEval;

/**
 * 우리가 그린 Class 다이어그램에서
 * 목록 Entity에 해당하는 클래스
 * UML에서 나타낸 함수들을 여기서 만들면된다.
 * @author JM
 *
 */
public class FinEvalPlanList
{
	private ArrayList<FinEvalPlan> planList;
	
	private static class FinEvalPlanListHolder
	{
		static final FinEvalPlanList uniqueInstance = new FinEvalPlanList();
	}

	public static FinEvalPlanList getInstance()
	{
		return FinEvalPlanListHolder.uniqueInstance;
	}
///////////////////////////////////////////////////////////////////////
	
	public ArrayList<FinEvalPlan> getAllList()
	{
		return planList;
	}
	
	public boolean addFinEvalPlan(FinEvalPlan finEvalPlan)
	{
		planList.add(finEvalPlan);
		//System.out.println(finEvalPlan.getExpertsEmail().get(0));
		//System.out.println(finEvalPlan.getExpertsEmail().get(1));
		//System.out.println(finEvalPlan.getExpertsEmail().get(2));
		return true;
	}
	public ArrayList<FinEvalPlan> getFinEvalPlanListForEval(String id)
	{
		ArrayList<FinEvalPlan> result = new ArrayList<FinEvalPlan>();
		
		for(int i=0; i<planList.size(); i++)
		{
			if(planList.get(i).getExpertLeaderId().equals(id))
			{ result.add(planList.get(i)); }
		}
		
		return result;
	}
	
	public ArrayList<FinEvalPlan> getFinEvalPlanListForNotice(Date curDate)
	{
		ArrayList<FinEvalPlan> result = new ArrayList<FinEvalPlan>();
		
		for(int i=0; i<planList.size(); i++)
		{
			Date temp = planList.get(i).getNoticeDate();
			if(temp.getYear() == curDate.getYear() && temp.getMonth() == curDate.getMonth()
					&& temp.getDate() == temp.getDate())
			{ result.add(planList.get(i)); }
		}
		
		return result;
	}
	public void setPlanList(ArrayList<FinEvalPlan> planList)
	{
		this.planList = planList;
	}
	

}
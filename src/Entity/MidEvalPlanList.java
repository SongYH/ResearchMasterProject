package Entity;
import java.util.ArrayList;
import java.util.Date;

import Common.MidEvalPlan;
import DAO.DAOmidEval;

/**
 * 우리가 그린 Class 다이어그램에서
 * 목록 Entity에 해당하는 클래스
 * UML에서 나타낸 함수들을 여기서 만들면된다.
 * @author JM
 *
 */
public class MidEvalPlanList
{
	private ArrayList<MidEvalPlan> planList;
	
	private static class MidEvalPlanListHolder
	{
		static final MidEvalPlanList uniqueInstance = new MidEvalPlanList();
	}

	private MidEvalPlanList()
	{
	}
	public static MidEvalPlanList getInstance()
	{
		return MidEvalPlanListHolder.uniqueInstance;
	}
///////////////////////////////////////////////////////////////////////
	
	public ArrayList<MidEvalPlan> getAllList()
	{
		return planList;
	}
	
	public boolean addMidEvalPlan(MidEvalPlan midEvalPlan)
	{
		planList.add(midEvalPlan);
		return true;
	}
	public boolean deleteMidEvalPlan(int projectNum)
	{
		for(int i=0; i<planList.size(); i++)
		{
			if(planList.get(i).getProjectNumber() == projectNum)
			{
				planList.remove(i);
				return true;
			}
		}
		return false;
	}
	
	public ArrayList<MidEvalPlan> getMidEvalPlanListForEval(String id)
	{
		ArrayList<MidEvalPlan> result = new ArrayList<MidEvalPlan>();
		
		for(int i=0; i<planList.size(); i++)
		{
			if(planList.get(i).getExpertLeaderId().equals(id))
			{ result.add(planList.get(i)); }
		}
		
		return result;
	}
	
	public ArrayList<MidEvalPlan> getMidEvalPlanListForNotice(Date curDate)
	{
		ArrayList<MidEvalPlan> result = new ArrayList<MidEvalPlan>();
		
		for(int i=0; i<planList.size(); i++)
		{
			Date temp = planList.get(i).getNoticeDate();
			if(temp.getYear() == curDate.getYear() && temp.getMonth() == curDate.getMonth()
					&& temp.getDate() == temp.getDate())
			{ result.add(planList.get(i)); }
		}
		
		return result;
	}
	public void setPlanList(ArrayList<MidEvalPlan> planList)
	{
		this.planList = planList;
	}
	

}
package Entity;
import java.util.ArrayList;

import Common.MidEvalResult;
import DAO.DAOmidEval;

/**
 * 우리가 그린 Class 다이어그램에서
 * 목록 Entity에 해당하는 클래스
 * UML에서 나타낸 함수들을 여기서 만들면된다.
 * @author JM
 *
 */
public class MidEvalResultList
{
	private ArrayList<MidEvalResult> resultList;
	
	private static class MidEvalResultListHolder
	{
		static final MidEvalResultList uniqueInstance = new MidEvalResultList();
	}

	private MidEvalResultList()
	{
	}
	public static MidEvalResultList getInstance()
	{
		return MidEvalResultListHolder.uniqueInstance;
	}
///////////////////////////////////////////////////////////////////////
	
	public MidEvalResult getMidEvalResult(int projectNumber)
	{
		MidEvalResult result=null;
		for(int i=0; i<resultList.size(); i++)
		{
			if(resultList.get(i).getProjectNumber() == projectNumber)
			{ result = resultList.get(i); }
		}
		return result;
	}
	
	public boolean addMidEvalResult(MidEvalResult midEvalResult)
	{
		resultList.add(midEvalResult);
		return true;
	}
	
	public ArrayList<MidEvalResult> getMidEvalResultForPl(String id)
	{
		ArrayList<MidEvalResult> result = new ArrayList<MidEvalResult>();
		for(int i=0; i<resultList.size(); i++)
		{
			if(resultList.get(i).getProjectLeaderId().equals(id))
			{ result.add(resultList.get(i)); }
		}
		return result;
	}
	public ArrayList<MidEvalResult> getAllList()
	{
		return resultList;
	}

	public void setResultList(ArrayList<MidEvalResult> resultList)
	{
		this.resultList = resultList;
	}
	
	
}
package Entity;
import java.util.ArrayList;

import Common.FinEvalResult;
import DAO.DAOfinEval;

/**
 * 우리가 그린 Class 다이어그램에서
 * 목록 Entity에 해당하는 클래스
 * UML에서 나타낸 함수들을 여기서 만들면된다.
 * @author JM
 *
 */
public class FinEvalResultList
{
	private ArrayList<FinEvalResult> resultList;
	
	private static class FinEvalResultListHolder
	{
		static final FinEvalResultList uniqueInstance = new FinEvalResultList();
	}

	public static FinEvalResultList getInstance()
	{
		return FinEvalResultListHolder.uniqueInstance;
	}
///////////////////////////////////////////////////////////////////////
	
	public FinEvalResult getFinEvalResult(int projectNumber)
	{
		FinEvalResult result=null;
		for(int i=0; i<resultList.size(); i++)
		{
			if(resultList.get(i).getProjectNumber() == projectNumber)
			{ result = resultList.get(i); }
		}
		return result;
	}
	
	public boolean addFinEvalResult(FinEvalResult finEvalResult)
	{
		resultList.add(finEvalResult);
		return true;
	}
	
	public ArrayList<FinEvalResult> getFinEvalResultForPl(String id)
	{
		ArrayList<FinEvalResult> result = new ArrayList<FinEvalResult>();
		for(int i=0; i<resultList.size(); i++)
		{
			if(resultList.get(i).getProjectLeaderId().equals(id))
			{ result.add(resultList.get(i)); }
		}
		return result;
	}
	public ArrayList<FinEvalResult> getAllList()
	{
		return resultList;
	}

	public void setResultList(ArrayList<FinEvalResult> resultList)
	{
		this.resultList = resultList;
	}
	
	
}
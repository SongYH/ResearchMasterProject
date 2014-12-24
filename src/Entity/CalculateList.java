package Entity;

import java.util.ArrayList;

import Common.Calculate;

public class CalculateList
{
	private ArrayList<Calculate> calculateList;
	private static class CalculateListHolder
	{
		static final CalculateList uniqueInstance = new CalculateList();
	}

	private CalculateList()
	{
		calculateList = new ArrayList<Calculate>();
	}

	public static CalculateList getInstance()
	{
		return CalculateListHolder.uniqueInstance;
	}

	public void add(Calculate b)
	{
		calculateList.add(b);
	}

	public Calculate getI(int i)
	{
		if (calculateList.size() > i && i >= 0)
			return calculateList.get(i);
		else
			return null;
	}

	public ArrayList<Calculate> getCalculateList()
	{
		return calculateList;
	}
	

	public void setCalculateList(ArrayList<Calculate> calculateList)
	{
		this.calculateList = calculateList;
	}
	public ArrayList<Calculate> getAllList()
	{
		return calculateList;
	}

	
}
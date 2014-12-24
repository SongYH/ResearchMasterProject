package Entity;

import java.util.ArrayList;

import Common.Clawback;

public class ClawbackList
{

	private ArrayList<Clawback> clawbackList;

	private static class ClawbackListHolder
	{
		static final ClawbackList uniqueInstance = new ClawbackList();
	}

	public ArrayList<Clawback> getClawList()
	{
		return clawbackList;
	}

	private ClawbackList()
	{
		clawbackList = new ArrayList<Clawback>();
	}

	public static ClawbackList getInstance()
	{
		return ClawbackListHolder.uniqueInstance;
	}

	public void add(Clawback b)
	{
		clawbackList.add(b);
	}

	public Clawback getI(int i)
	{
		if (clawbackList.size() > i && i >= 0)
			return clawbackList.get(i);
		else
			return null;
	}

	public ArrayList<Clawback> getAllList()
	{
		return clawbackList;
	}

	public void setClawbackList(ArrayList<Clawback> clawbackList)
	{
		this.clawbackList = clawbackList;
	}
	
	
}
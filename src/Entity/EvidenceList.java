package Entity;

import java.util.ArrayList;

import Common.Evidence;

public class EvidenceList
{

	private ArrayList<Evidence> evidenceList;

	private static class EvidenceListHolder
	{
		static final EvidenceList uniqueInstance = new EvidenceList();

	}

	private EvidenceList()
	{
		evidenceList = new ArrayList<Evidence>();
	}

	public static EvidenceList getInstance()
	{
		return EvidenceListHolder.uniqueInstance;
	}

	public void add(Evidence e)
	{
		evidenceList.add(e);
	}

	public Evidence getI(int i)
	{
		if (evidenceList.size() > i && i >= 0)
			return evidenceList.get(i);
		else
			return null;
	}

	public ArrayList<Evidence> getAllList()
	{
		return evidenceList;
	}

	public void setEvidenceList(ArrayList<Evidence> evidenceList)
	{
		this.evidenceList = evidenceList;
	}
	
}

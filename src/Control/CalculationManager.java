package Control;

import java.util.ArrayList;

import Common.*;
import Entity.*;

public class CalculationManager
{
	public Calculate requestCalculate(int projectNumber)
	{
		CalculateList CalculateListEntity = CalculateList.getInstance();
		ArrayList<Calculate> CalculateList = CalculateListEntity.getCalculateList();

		for (Calculate c : CalculateList)
		{
			if (c.getProjectNumber() == projectNumber)
			{
				return c;
			}
		}
		return null;
	}

	public Clawback requestClawback(int projectNumber)
	{
		ClawbackList clawbackListEntity = ClawbackList.getInstance();
		ArrayList<Clawback> clawbackList = clawbackListEntity.getClawList();

		for (Clawback c : clawbackList)
		{
			if (c.getProjectNumber() == projectNumber)
			{
				return c;
			}
		}
		return null;
	}

	public Evidence requestEvidence(int projectNumber)
	{
		EvidenceList evidenceListEntity = EvidenceList.getInstance();
		ArrayList<Evidence> evidenceList = evidenceListEntity.getAllList();

		for (Evidence e : evidenceList)
		{
			if (e.getProjectNumber() == projectNumber)
			{
				return e;
			}
		}
		return null;
	}

	public int requestProjectVerificationAmount(int projectNumber)
	{
		ProjectManager pm = new ProjectManager();
		int budgetAmount = pm.reqBudgetSearchNumber(projectNumber).getAmount();
		int CalculateAmount = requestCalculate(projectNumber).getAmount();
		return budgetAmount - CalculateAmount;
	}

	public Budget requestBudget(int projectNumber)
	{
		ProjectManager pm = new ProjectManager();
		return pm.reqBudgetSearchNumber(projectNumber);
	}

	public void addEvidence(Evidence e)
	{
		EvidenceList el = EvidenceList.getInstance();
		ArrayList<Evidence> evList = el.getAllList();

		for (Evidence ev : evList)
			if (e.getProjectNumber() == ev.getProjectNumber())
				return;
		el.add(e);
	}

	public void addCalculate(Calculate c)
	{
		CalculateList cl = CalculateList.getInstance();
		ArrayList<Calculate> clList = cl.getCalculateList();
		for (Calculate ca : clList)
			if (ca.getProjectNumber() == c.getProjectNumber())
				return;
		cl.add(c);
	}

	public void addClawback(Clawback c)
	{
		ClawbackList cl = ClawbackList.getInstance();
		ArrayList<Clawback> clawbackList = cl.getAllList();
		for (Clawback ca : clawbackList)
			if (ca.getProjectNumber() == c.getProjectNumber())
				return;
		cl.add(c);
	}

	public void addNotice(Notice n)
	{
		NoticeActive na = NoticeActive.getInstance();
		na.add(n);
	}
}
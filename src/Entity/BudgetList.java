package Entity;

import java.util.ArrayList;
import Common.Budget;
import Common.Project;
import DAO.DAObudget;

public class BudgetList
{
	private ArrayList<Budget> budgetList;

	private static class BudgetHolder
	{
		static final BudgetList uniqueInstance = new BudgetList();
	}

	private BudgetList()
	{
	}

	public static BudgetList getInstance()
	{
		return BudgetHolder.uniqueInstance;
	}

	// //////////////////////////////////

	//1
	public void addBudget(Budget b)
	{
		budgetList.add(b);
	}

	//1
	// 과제번호로 예산정보 가져오기
	public Budget getBudgetSearchNumber(int num)
	{
		for (Budget b : budgetList)
		{
			if (b.getProjectNumber() == num)
				return b;
		}
		return null;
	}
	
	public ArrayList<Budget> getBudgetList()
	{
		return budgetList;
	}

	public void setBudgetList(ArrayList<Budget> budgetList)
	{
		this.budgetList = budgetList;
	}
}

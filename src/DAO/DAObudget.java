package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Common.Budget;
import Common.BudgetCareer;
import Common.DBconnection;
import Entity.BudgetList;

public class DAObudget
{
	private Connection connection;

	// DBconnection에서 DB 주소 가져옴
	public DAObudget()
	{
		connection = DBconnection.getConnection();
	}

	// /////////////////////////////////////////////////////////////

	/**
	 * 시스템이 시작될 때 DB에 있는 모든 예산 목록을 싱글턴클래스 BudgetListBean에 모두 적재할것이다. 그 때 호출되는
	 * 메소드.
	 * 
	 * @return budget테이블에 있는 모든 사용자 목록
	 */

	public void loadList()
	{
		ArrayList<Budget> retBudgetList = new ArrayList<Budget>();
		try
		{
			String sqlQuery = "SELECT * FROM budget;";
			PreparedStatement pstmt = connection.prepareStatement(sqlQuery);
			ResultSet result = pstmt.executeQuery();
			while (result.next())
			{
				int projectNumber = result.getInt("projectNumber");
				int amount = result.getInt("amount");
				retBudgetList.add(new Budget(projectNumber, amount));
			}
			result.close();
			pstmt.close();
		} catch (SQLException e)
		{
			e.printStackTrace();
		}
		BudgetList.getInstance().setBudgetList(retBudgetList);
		System.out.println("예산 로드 완료");
	}

	/**
	 * -수정필요- DB에 통으로 다 save한다 사실 이렇게 되면 mysql이 중복잡아내서 오류를 엄청 뱉어낼것이다. saveList가
	 * 아니라 updateList로 수정해야할 필요가 있다.
	 * 
	 * @param saveList
	 *            -> DB에 저장할 사용자 목록전체이다.
	 * @return 저장성공여부
	 */
	public boolean saveList()
	{
		ArrayList<Budget> saveList = BudgetList.getInstance().getAllList();

		StringBuilder queryBuilder = new StringBuilder();
		StringBuilder queryProjectUser = new StringBuilder();
		try
		{
			for(Budget x : saveList)
			{
				queryBuilder.delete(0, queryBuilder.length());
				queryBuilder.append("INSERT INTO budget (projectNumber, amount) ");
				queryBuilder.append("VALUES (?, ?) ");
				queryBuilder.append("ON DUPLICATE KEY UPDATE " );
				queryBuilder.append("projectNumber = ?, amount = ?;");
				
				String query = queryBuilder.toString();
				
				PreparedStatement pstmt = connection.prepareStatement(query);

				pstmt.setInt(1, x.getProjectNumber());
				pstmt.setInt(2, x.getAmount());

				pstmt.setInt(3, x.getProjectNumber());
				pstmt.setInt(4, x.getAmount());
				
				pstmt.executeUpdate();
				pstmt.close();	
			}
		}

		catch (SQLException e)
		{
			System.out.println("예산목록 DB저장 실패...");
			e.printStackTrace();
		}

		System.out.println("예산목록 DB저장 성공!");
		return true;
	}

}

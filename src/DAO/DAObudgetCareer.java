package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import Common.Budget;
import Common.BudgetCareer;
import Common.DBconnection;
import Common.ProjectCareer;
import Entity.BudgetCareerList;
import Entity.ProjectCareerList;

public class DAObudgetCareer {
	private Connection connection;
	
	//DBconnection에서 DB 주소 가져옴
	public DAObudgetCareer()
	{
		DBconnection dbcon = new DBconnection();
		connection = dbcon.getConnection();
	}
///////////////////////////////////////////////////////////////

	/**
	 * 시스템이 시작될 때 DB에 있는 모든 예산 이력 목록을
	 * 싱글턴클래스 BudgetCarrerListBean에 모두 적재할것이다.
	 * 그 때 호출되는 메소드.
	 * @return budgetCareer테이블에 있는 모든 사용자 목록
	 */
	public void loadList()
	{
		ArrayList<BudgetCareer> retBudgetCareerList = new ArrayList<BudgetCareer>();
		try
		{
			String sqlQuery = "SELECT * FROM budgetcareer;";
			PreparedStatement pstmt = connection.prepareStatement(sqlQuery);
			ResultSet result = pstmt.executeQuery();
			while(result.next())
			{
				Date recordDate = new Date(result.getDate("recordDate").getTime());
				int projectNumber = result.getInt("projectNumber");
				int amount = result.getInt("amount");
				Budget budgetInfo=new Budget(projectNumber,amount);
				BudgetCareer budgetCareer=new BudgetCareer(recordDate,budgetInfo);
				retBudgetCareerList.add(budgetCareer);
			}
			result.close();
			pstmt.close();
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		
		BudgetCareerList.getInstance().setBudgetCareerList(retBudgetCareerList);
		System.out.println("예산이력 로드 완료");
		return ;
	}
	
	/**
	 * -수정필요-
	 * DB에 통으로 다 save한다 사실 이렇게 되면
	 * mysql이 중복잡아내서 오류를 엄청 뱉어낼것이다.
	 * saveList가 아니라 updateList로 수정해야할 필요가 있다.
	 * @param saveList -> DB에 저장할 사용자 목록전체이다.
	 * @return 저장성공여부
	 */
	public boolean saveList()
	{
		ArrayList<BudgetCareer> saveList = BudgetCareerList.getInstance().getBudgetCareerList();
		StringBuilder queryBuilder = new StringBuilder();
		StringBuilder queryProjectUser = new StringBuilder();
		try
		{
			for(BudgetCareer x : saveList)
			{
				queryBuilder.delete(0, queryBuilder.length());
				queryBuilder.append("INSERT INTO budgetcareer (recordDate, projectNumber, amount) ");
				queryBuilder.append("VALUES (?, ?, ?) ");
				queryBuilder.append("ON DUPLICATE KEY UPDATE " );
				queryBuilder.append("recordDate=?, projectNumber = ?, amount = ?;");
				
				String query = queryBuilder.toString();
				
				PreparedStatement pstmt = connection.prepareStatement(query);
				
				pstmt.setDate(1, new java.sql.Date(x.getRecordDate().getTime()));
				pstmt.setInt(2, (x.getBudgetInfo()).getProjectNumber());
				pstmt.setInt(3, (x.getBudgetInfo()).getAmount());
				
				//수정
				pstmt.setDate(4, new java.sql.Date(x.getRecordDate().getTime()));
				pstmt.setInt(5, (x.getBudgetInfo()).getProjectNumber());
				pstmt.setInt(6, (x.getBudgetInfo()).getAmount());
				
				pstmt.executeUpdate();
				pstmt.close();	
			}
		}
		
		catch(SQLException e)
		{
			System.out.println("예산이력 DB저장 실패...");
			e.printStackTrace();
		}
		System.out.println("예산이력 DB저장 성공");
		return true;
	}
	

}

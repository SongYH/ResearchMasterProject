package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Common.DBconnection;
import Common.Expert;
import Common.FinEvalPlan;
import Common.FinEvalResult;
import Entity.ExpertList;
import Entity.FinEvalPlanList;
import Entity.FinEvalResultList;

public class DAOfinEval
{
	private Connection connection;
	
	public DAOfinEval()
	{
		connection = DBconnection.getConnection();
	}
	
	/**
	 * 시스템이 시작될 때 DB에 있는 모든 사용자 목록을
	 * 싱글턴클래스 UserListBean에 모두 적재할것이다.
	 * 그 때 호출되는 메소드.
	 * @return user테이블에 있는 모든 사용자 목록
	 */
	
	public void loadPlanList()
	{
		ArrayList<FinEvalPlan> retFinPlanList = new ArrayList<FinEvalPlan>();

		try
		{
			String sqlQuery = "SELECT * FROM final_eval_plan;";
			PreparedStatement pstmt = connection.prepareStatement(sqlQuery);
			ResultSet result = pstmt.executeQuery();
			while(result.next())
			{
				int projectNumber = result.getInt("projectNumber");
				String expertLeaderId = result.getString("expertLeaderId");
				String expertLeaderEmail = result.getString("expertLeaderEmail");
				
				ArrayList<String> expertsEmail = new ArrayList<String>();
				String expertEmail1 = result.getString("expertEmail1");
				String expertEmail2 = result.getString("expertEmail2");
				String expertEmail3 = result.getString("expertEmail3");
				String expertEmail4 = result.getString("expertEmail4");
				String expertEmail5 = result.getString("expertEmail5");

				expertsEmail.add(expertEmail1); expertsEmail.add(expertEmail2);
				expertsEmail.add(expertEmail3); expertsEmail.add(expertEmail4);
				expertsEmail.add(expertEmail5);
				
				String projectLeaderEmail = result.getString("projectLeaderEmail");
				
				java.util.Date noticeDate = new java.util.Date(result.getDate("noticeDate").getTime());
				
				String fileName = result.getString("fileName");
				
				retFinPlanList.add(new FinEvalPlan(projectNumber, expertLeaderId, expertLeaderEmail, 
						expertsEmail, projectLeaderEmail, noticeDate, fileName));
			}
			result.close();
			pstmt.close();
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}

		FinEvalPlanList.getInstance().setPlanList(retFinPlanList);
	}
	
	public void loadResultList()
	{
		ArrayList<FinEvalResult> retFinResultList = new ArrayList<FinEvalResult>();

		try
		{
			String sqlQuery = "SELECT * FROM final_eval_result;";
			PreparedStatement pstmt = connection.prepareStatement(sqlQuery);
			ResultSet result = pstmt.executeQuery();
			while(result.next())
			{
				int projectNumber = result.getInt("projectNumber");
				String projectLeaderId = result.getString("projectLeaderId");
				String projectResult = result.getString("result");
				String fileName = result.getString("fileName");
				
				retFinResultList.add(new FinEvalResult(projectNumber, projectLeaderId, projectResult, fileName));
			}
			result.close();
			pstmt.close();
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}

		FinEvalResultList.getInstance().setResultList(retFinResultList);
	}
	
	
	/**
	 * -수정필요-
	 * DB에 통으로 다 save한다 사실 이렇게 되면
	 * mysql이 중복잡아내서 오류를 엄청 뱉어낼것이다.
	 * saveList가 아니라 updateList로 수정해야할 필요가 있다.
	 * @return 저장성공여부
	 */
	public boolean savePlanList()
	{
		ArrayList<FinEvalPlan> saveList = FinEvalPlanList.getInstance().getAllList();
		
		StringBuilder queryBuilder = new StringBuilder();
		try
		{
			for(FinEvalPlan x : saveList)
			{
				queryBuilder.delete(0, queryBuilder.length());
				queryBuilder.append("INSERT INTO final_eval_plan (projectNumber, expertLeaderId, expertLeaderEmail, expertEmail1, expertEmail2, expertEmail3, expertEmail4, expertEmail5, projectLeaderEmail, noticeDate, fileName) ");
				queryBuilder.append("VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) ");
				queryBuilder.append("ON DUPLICATE KEY UPDATE " );
				queryBuilder.append("projectNumber = ?, expertLeaderId = ?, expertLeaderEmail = ?, expertEmail1 = ?, expertEmail2 = ?, ");
				queryBuilder.append("expertEmail3 = ?, expertEmail4 = ?, expertEmail5 = ?, projectLeaderEmail = ?, noticeDate = ?, fileName = ?;");
				
				String query = queryBuilder.toString();
				
				PreparedStatement pstmt = connection.prepareStatement(query);
				
				System.out.println(x.getProjectNumber() + "  " + x.getProjectLeaderEmail());
				
				pstmt.setInt(1, x.getProjectNumber());
				pstmt.setString(2, x.getExpertLeaderId());
				pstmt.setString(3, x.getExpertLeaderEmail());
				pstmt.setString(4, x.getExpertsEmail().get(0));
				pstmt.setString(5, x.getExpertsEmail().get(1));
				pstmt.setString(6, x.getExpertsEmail().get(2));
				pstmt.setString(7, x.getExpertsEmail().get(3));
				pstmt.setString(8, x.getExpertsEmail().get(4));
				pstmt.setString(9, x.getProjectLeaderEmail());
				pstmt.setDate(10, new java.sql.Date(x.getNoticeDate().getTime()));
				pstmt.setString(11, x.getFileName());
				/* 수정 */
				pstmt.setInt(12, x.getProjectNumber());
				pstmt.setString(13, x.getExpertLeaderId());
				pstmt.setString(14, x.getExpertLeaderEmail());
				pstmt.setString(15, x.getExpertsEmail().get(0));
				pstmt.setString(16, x.getExpertsEmail().get(1));
				pstmt.setString(17, x.getExpertsEmail().get(2));
				pstmt.setString(18, x.getExpertsEmail().get(3));
				pstmt.setString(19, x.getExpertsEmail().get(4));
				pstmt.setString(20, x.getProjectLeaderEmail());
				pstmt.setDate(21, new java.sql.Date(x.getNoticeDate().getTime()));
				pstmt.setString(22, x.getFileName());
				
				pstmt.executeUpdate();
				pstmt.close();
			}
		}
		
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		
		return true;
	}
	
	public boolean saveResultList()
	{
		ArrayList<FinEvalResult> saveList = FinEvalResultList.getInstance().getAllList();
		
		StringBuilder queryBuilder = new StringBuilder();
		try
		{
			for(FinEvalResult x : saveList)
			{
				queryBuilder.delete(0, queryBuilder.length());
				queryBuilder.append("INSERT INTO final_eval_result (projectNumber, projectLeaderId, result, fileName) ");
				queryBuilder.append("VALUES (?, ?, ?, ?) ");
				queryBuilder.append("ON DUPLICATE KEY UPDATE " );
				queryBuilder.append("projectNumber = ?, projectLeaderId = ?, result = ?, fileName = ?;");
				
				String query = queryBuilder.toString();
				PreparedStatement pstmt = connection.prepareStatement(query);
				
				pstmt.setInt(1, x.getProjectNumber());
				pstmt.setString(2, x.getProjectLeaderId());
				pstmt.setString(3, x.getResult());
				pstmt.setString(4, x.getFileName());
			
				/* 수정 */
				pstmt.setInt(5, x.getProjectNumber());
				pstmt.setString(6, x.getProjectLeaderId());
				pstmt.setString(7, x.getResult());
				pstmt.setString(8, x.getFileName());
				
				pstmt.executeUpdate();
				pstmt.close();
			}
		}
		
		catch(SQLException e)
		{
			System.out.println("최종평가목록 DB저장 실패...");
			e.printStackTrace();
		}
		
		System.out.println("최종평가목록 DB저장 성공!");
		return true;
	}
}

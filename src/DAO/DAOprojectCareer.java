package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import Common.DBconnection;
import Common.Project;
import Common.ProjectCareer;
import Entity.BudgetList;
import Entity.ProjectCareerList;
import Entity.ProjectList;

public class DAOprojectCareer {
	
private Connection connection;
	
	//DBconnection에서 DB 주소 가져옴
	public DAOprojectCareer()
	{
		DBconnection dbcon = new DBconnection();
		connection = dbcon.getConnection();
	}
	
	/**
	 * 시스템이 시작될 때 DB에 있는 모든 과제 목록을
	 * 싱글턴클래스 ProjectCareerListBean에 모두 적재할것이다.
	 * 그 때 호출되는 메소드.
	 * @return projectCareer테이블에 있는 모든 사용자 목록
	 */
	
	public void loadList()
	{
		ArrayList<ProjectCareer> retProjectCareerList = new ArrayList<ProjectCareer>();
		try
		{
			String sqlQuery = "SELECT * FROM projectcareer;";
			PreparedStatement pstmt = connection.prepareStatement(sqlQuery);
			ResultSet result = pstmt.executeQuery();
			while(result.next())
			{
				///////////////////////Date/////////////////////////
				Date recordDate = new Date(result.getDate("recordDate").getTime());
				
				//////////////////////Project Info/////////////////
				String status = result.getString("status");
				String type = result.getString("type");
				String area = result.getString("area");
				int projectNumber = result.getInt("projectNumber");
				String projectName = result.getString("projectName");
				String leaderId = result.getString("leaderId");
				String leaderName = result.getString("leaderName");
				int organId = result.getInt("organId");
				String organName = result.getString("organName");
				Date registerDate = new Date(result.getDate("registerDate").getTime());
				int agreeYear = result.getInt("agreeYear");
				String fileName = result.getString("fileName");
				
				//User 정보에서 해당 과제에 대한 참여연구원 아이디 가져오기
				ArrayList<String> userIdList = new ArrayList<String>();
				//프로젝트 번호로 ID 가져오기
				String sqlQuery2 = "SELECT userId FROM project_user where projectNumber ='"+projectNumber+"';";
				PreparedStatement pstmt2 = connection.prepareStatement(sqlQuery2);
				System.out.println(sqlQuery2);
				ResultSet result2 = pstmt2.executeQuery();
				while(result2.next())
				{
					String id = result2.getString("userId");
					userIdList.add(id);
				}
				result2.close();
				pstmt2.close();
				
				//////////////////////////////////////////////
				Project projectInfo=new Project(status, type, area, projectNumber, projectName, leaderId, leaderName, organId, organName, registerDate, agreeYear, fileName, userIdList );
				
				ProjectCareer projectcareer=new ProjectCareer(recordDate,projectInfo);
				retProjectCareerList.add(projectcareer);
			}
			result.close();
			pstmt.close();
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		ProjectCareerList.getInstance().setProjectCareerList(retProjectCareerList);
		System.out.println("과제 이력 load 완료");
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
		ArrayList<ProjectCareer> saveList = ProjectCareerList.getInstance().getProjectCareerList();
		StringBuilder queryBuilder = new StringBuilder();
		StringBuilder queryProjectUser = new StringBuilder();
		try
		{
			for(ProjectCareer x : saveList)
			{
				queryBuilder.delete(0, queryBuilder.length());
				queryBuilder.append("INSERT INTO projectcareer (recordDate, status, type, area, projectNumber, projectNumber, leaderId, leaderName, organId, organName, registerDate, agreeYear, fileName) ");
				queryBuilder.append("VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) ");
				queryBuilder.append("ON DUPLICATE KEY UPDATE " );
				queryBuilder.append("recordDate = ?, status = ?, type = ?, area = ?, projectNumber = ?, projectName = ?, leaderId = ?, leaderName = ?, ");
				queryBuilder.append("organId = ?, organName = ?, registerDate = ?, agreeYear = ?, fileName = ?;");
				
				String query = queryBuilder.toString();
				
				PreparedStatement pstmt = connection.prepareStatement(query);
				
				/* 수정 */
				pstmt.setDate(1, new java.sql.Date(x.getRecordDate().getTime()));
				pstmt.setString(2, (x.getProjectInfo()).getStatus());
				pstmt.setString(3, (x.getProjectInfo()).getType());
				pstmt.setString(4, (x.getProjectInfo()).getArea());
				pstmt.setInt(5, (x.getProjectInfo()).getProjectNumber());
				pstmt.setString(6, (x.getProjectInfo()).getProjectName());
				pstmt.setString(7, (x.getProjectInfo()).getLeaderId());
				pstmt.setString(8, (x.getProjectInfo()).getLeaderName());
				pstmt.setInt(9, (x.getProjectInfo()).getOrganId());
				pstmt.setString(10, (x.getProjectInfo()).getOrganName());
				pstmt.setDate(11, new java.sql.Date((x.getProjectInfo()).getRegisterDate().getTime()));
				pstmt.setInt(12, (x.getProjectInfo()).getAgreeYear());
				pstmt.setString(13, (x.getProjectInfo()).getFileName());
				
				
				/*
				//사용자와 과제 묶기
				for(String t : (x.getProjectInfo()).getUserIdList())
				{
					queryProjectUser.delete(0, queryProjectUser.length());
					queryBuilder.append("INSERT INTO project_user (projectNumber, id) ");
					queryBuilder.append("VALUES (?, ?) ");
					queryBuilder.append("ON DUPLICATE KEY UPDATE " );
					queryBuilder.append("projectNumber = ?, id = ?;");
					
					String queryP_U = queryProjectUser.toString();
					
					PreparedStatement pstmtP_U = connection.prepareStatement(queryP_U);
					
					pstmtP_U.setInt(1, (x.getProjectInfo()).getProjectNumber());
					pstmtP_U.setString(2, t);
					
					//수정 
					pstmtP_U.setInt(3, (x.getProjectInfo()).getProjectNumber());
					pstmtP_U.setString(4, t);
					
					pstmtP_U.executeUpdate();
					pstmtP_U.close();
				}
				*/
				
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
	

}

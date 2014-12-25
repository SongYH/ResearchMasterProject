package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import Common.DBconnection;
import Common.Project;
import Entity.ProjectList;

public class DAOproject
{
	private Connection connection;

	// DBconnection에서 DB 주소 가져옴
	public DAOproject()
	{
		connection = DBconnection.getConnection();
	}

	/**
	 * 시스템이 시작될 때 DB에 있는 모든 과제 목록을 싱글턴클래스 ProjectListBean에 모두 적재할것이다. 그 때 호출되는
	 * 메소드.
	 * 
	 * @return project테이블에 있는 모든 사용자 목록
	 */

	public void loadList()
	{
		ArrayList<Project> retProjectList = new ArrayList<Project>();
		try
		{
			String sqlQuery = "SELECT * FROM project;";
			PreparedStatement pstmt = connection.prepareStatement(sqlQuery);
			ResultSet result = pstmt.executeQuery();
			while (result.next())
			{
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

				
				// User 정보에서 해당 과제에 대한 참여연구원 아이디 가져오기
				ArrayList<String> userIdList = new ArrayList<String>();
				// 프로젝트 번호로 ID 가져오기
				String sqlQuery2 = "SELECT userId FROM project_user where projectNumber ='" + projectNumber + "';";
				PreparedStatement pstmt2 = connection.prepareStatement(sqlQuery2);
				ResultSet result2 = pstmt2.executeQuery();
				while (result2.next())
				{
					String id = result2.getString("userId");
					userIdList.add(id);
				}
				result2.close();
				pstmt2.close();

				retProjectList.add(new Project(status, type, area, projectNumber, projectName, leaderId, leaderName,
						organId, organName, registerDate, agreeYear, fileName, userIdList));
			}
			result.close();
			pstmt.close();
		} catch (SQLException e)
		{
			e.printStackTrace();
		}

		ProjectList.getInstance().setProjectList(retProjectList);
		System.out.println("과제목록 로드 완료");
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
		ArrayList<Project> saveList = ProjectList.getInstance().getProjectList();
		
		StringBuilder queryBuilder = new StringBuilder();
		StringBuilder queryProjectUser = new StringBuilder();
		try
		{
			for (Project x : saveList)
			{
				queryBuilder.delete(0, queryBuilder.length());
				queryBuilder.append("INSERT INTO project (status, type, area, projectNumber, projectName, leaderName, leaderId, organName, organId, registerDate, agreeYear, fileName) ");
				queryBuilder.append("VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) ");
				queryBuilder.append("ON DUPLICATE KEY UPDATE ");
				queryBuilder.append("status = ?, type = ?, area = ?, projectNumber = ?, projectName = ?, leaderName = ?, leaderId = ?, ");
				queryBuilder.append("organName = ?, organId = ?, registerDate = ?, agreeYear = ?, fileName = ?;");

				String query = queryBuilder.toString();

				PreparedStatement pstmt = connection.prepareStatement(query);

				pstmt.setString(1, x.getStatus());
				pstmt.setString(2, x.getType());
				pstmt.setString(3, x.getArea());
				pstmt.setInt(4, x.getProjectNumber());
				pstmt.setString(5, x.getProjectName());
				pstmt.setString(6, x.getLeaderName());
				pstmt.setString(7, x.getLeaderId());
				pstmt.setString(8, x.getOrganName());
				pstmt.setInt(9, x.getOrganId());
				pstmt.setDate(10, new java.sql.Date(x.getRegisterDate().getTime()));
				pstmt.setInt(11, x.getAgreeYear());
				pstmt.setString(12, x.getFileName());

				/* 수정 */
				pstmt.setString(13, x.getStatus());
				pstmt.setString(14, x.getType());
				pstmt.setString(15, x.getArea());
				pstmt.setInt(16, x.getProjectNumber());
				pstmt.setString(17, x.getProjectName());
				pstmt.setString(18, x.getLeaderName());
				pstmt.setString(19, x.getLeaderId());
				pstmt.setString(20, x.getOrganName());
				pstmt.setInt(21, x.getOrganId());
				pstmt.setDate(22, new java.sql.Date(x.getRegisterDate().getTime()));
				pstmt.setInt(23, x.getAgreeYear());
				pstmt.setString(24, x.getFileName());
				pstmt.executeUpdate();
				// 사용자와 과제 묶기
				if (x.getUserIdList().size() > 0) 
				{
					int projectNum = x.getProjectNumber();
					for (String t : x.getUserIdList()) 
					{
						queryProjectUser.delete(0, queryProjectUser.length());
						queryProjectUser.append("INSERT INTO project_user (projectNumber, userId) ");
						queryProjectUser.append("VALUES (?, ?) ");
						queryProjectUser.append("ON DUPLICATE KEY UPDATE ");
						queryProjectUser.append("projectNumber = ?, userId = ?;");

						String queryP_U = queryProjectUser.toString();

						PreparedStatement pstmtP_U = connection.prepareStatement(queryP_U);

						pstmtP_U.setInt(1, projectNum);
						pstmtP_U.setString(2, t); // 아이디 저장

						/* 수정 */
						pstmtP_U.setInt(3, x.getProjectNumber());
						pstmtP_U.setString(4, t);

						pstmtP_U.executeUpdate();
						pstmtP_U.close();
					}
				}
				
				pstmt.close();
			}
		}

		catch (SQLException e)
		{
			System.out.println("과제목록 DB저장 실패...");
			e.printStackTrace();
		}

		System.out.println("과제목록 DB저장 성공!");
		return true;
	}

	// 전체 과제 갯수 파악 // 과제 번호 등록시 이건 조금 있다가 함

}
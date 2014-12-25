package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Common.DBconnection;
import Common.PreProject;
import Entity.PreProjectList;

public class DAOpreproject {
	
	private Connection connection;
	
	public DAOpreproject()
	{
		connection = DBconnection.getConnection();
	}
	
	/**
	 * 생성자에서만 사용하는 메소드
	 * 사용자가 쓸일은 없으므로 건드리지 말것.
	 * @return Connection객체
	 */
	
	public void loadList()
	{
		ArrayList<PreProject> retUserList = new ArrayList<PreProject>();
		try
		{
			String sqlQuery = "SELECT * FROM preproject;";
			PreparedStatement pstmt = connection.prepareStatement(sqlQuery);
			ResultSet result = pstmt.executeQuery();
			while(result.next())
			{
				int projectNumber = result.getInt("projectNumber");
				int organId = result.getInt("organId");
				String subjectName = result.getString("subjectName");
				String status = result.getString("status");
				String type = result.getString("type");
				String area = result.getString("area");
				String id = result.getString("id");
				String fileName = result.getString("fileName");
				
				retUserList.add(new PreProject(projectNumber, organId, subjectName, status, type, area, id, fileName));
			}
			result.close();
			pstmt.close();
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}

		PreProjectList.getInstance().setPreProjectList(retUserList);
		System.out.println("신청과제목록 로드 완료");
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
		ArrayList<PreProject> saveList = PreProjectList.getInstance().getPreProjectList();
		StringBuilder queryBuilder = new StringBuilder();
		try{
			for(PreProject x : saveList){
				queryBuilder.delete(0, queryBuilder.length());
				queryBuilder.append("INSERT INTO preproject (projectNumber, organId, subjectName, status, type, area, id, fileName) ");
				queryBuilder.append("VALUES (?, ?, ?, ?, ?, ?, ?, ?) ");
				queryBuilder.append("ON DUPLICATE KEY UPDATE " );
				queryBuilder.append("projectNumber = ?, organId = ?,  subjectName = ?, status = ?, type = ?, ");
				queryBuilder.append("area = ?, id = ?, fileName = ?;");
				
				String query = queryBuilder.toString();
				PreparedStatement pstmt = connection.prepareStatement(query);
				
				pstmt.setInt(1, x.getProjectNumber());
				pstmt.setInt(2, x.getOrganId());
				pstmt.setString(3, x.getSubjectName());
				pstmt.setString(4, x.getStatus());
				pstmt.setString(5, x.getType());
				pstmt.setString(6, x.getArea());
				pstmt.setString(7, x.getId());
				pstmt.setString(8, x.getFileName());
				/* 수정 */
				pstmt.setInt(9, x.getProjectNumber());
				pstmt.setInt(10, x.getOrganId());
				pstmt.setString(11, x.getSubjectName());
				pstmt.setString(12, x.getStatus());
				pstmt.setString(13, x.getType());
				pstmt.setString(14, x.getArea());
				pstmt.setString(15, x.getId());
				pstmt.setString(16, x.getFileName());
			
				pstmt.executeUpdate();
				pstmt.close();
			}
		}
		
		catch(SQLException e)
		{
			System.out.println("신청과제목록 DB저장 실패...");
			e.printStackTrace();
		}
		
		System.out.println("신청과제목록 DB저장 성공!");
		return true;
	}

}

package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Common.DBconnection;
import Common.Expert;
import Entity.ExpertList;

public class DAOexpert
{
	private Connection connection;
	
	public DAOexpert()
	{
		connection = DBconnection.getConnection();
	}
	
	/**
	 * 시스템이 시작될 때 DB에 있는 모든 사용자 목록을
	 * 싱글턴클래스 UserListBean에 모두 적재할것이다.
	 * 그 때 호출되는 메소드.
	 * @return user테이블에 있는 모든 사용자 목록
	 */
	
	public void loadList()
	{
		ArrayList<Expert> retExpertList = new ArrayList<Expert>();
		try
		{
			String sqlQuery = "SELECT * FROM expert;";
			PreparedStatement pstmt = connection.prepareStatement(sqlQuery);
			ResultSet result = pstmt.executeQuery();
			while(result.next())
			{   //
				int expertNumber = result.getInt("expertNumber");
				String id = result.getString("id");
				String password = result.getString("password");
				String name = result.getString("name");
				String sn = result.getString("socialNumber");
				String tel = result.getString("tel");
				String officeAddress = result.getString("officeAddress");
				String homeAddress = result.getString("homeAddress");
				int organId = result.getInt("organId");
				String position = result.getString("position");
				String accInfo = result.getString("accountInfo");
				String email = result.getString("email");
				String field = result.getString("field");
				
				retExpertList.add(new Expert(id, password, expertNumber, name, sn, tel, officeAddress, 
						homeAddress, organId, position, accInfo, email, field));
			}
			result.close();
			pstmt.close();
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		ExpertList.getInstance().setExpertList(retExpertList);
	}
	
	/**
	 * -수정필요-
	 * DB에 통으로 다 save한다 사실 이렇게 되면
	 * mysql이 중복잡아내서 오류를 엄청 뱉어낼것이다.
	 * saveList가 아니라 updateList로 수정해야할 필요가 있다.
	 * @return 저장성공여부
	 */
	public boolean saveList()
	{
		ArrayList<Expert> saveList = ExpertList.getInstance().getExpertList();
		
		StringBuilder queryBuilder = new StringBuilder();
		try
		{
			for(Expert x : saveList)
			{
				queryBuilder.delete(0, queryBuilder.length());
				queryBuilder.append("INSERT INTO expert (expertNumber, id, password, name, socialNumber, tel, officeAddress, homeAddress, organId, position, accountInfo, email, field) ");
				queryBuilder.append("VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) ");
				queryBuilder.append("ON DUPLICATE KEY UPDATE " );
				queryBuilder.append("expertNumber = ?, id = ?, password = ?, name = ?, socialNumber = ?, ");
				queryBuilder.append("tel = ?, officeAddress = ?, homeAddress = ?, organId = ?, position = ?, accountInfo = ?, email = ?, field = ?;");
				
				String query = queryBuilder.toString();
				
				PreparedStatement pstmt = connection.prepareStatement(query);
				
				pstmt.setInt(1, x.getExpertNumber());
				pstmt.setString(2, x.getId());
				pstmt.setString(3, x.getPassword());
				pstmt.setString(4, x.getName());
				pstmt.setString(5, x.getSocialNumber());
				pstmt.setString(6, x.getTel());
				pstmt.setString(7, x.getOfficeAddress());
				pstmt.setString(8, x.getHomeAddress());
				pstmt.setInt(9, x.getOrganId());
				pstmt.setString(10, x.getPosition());
				pstmt.setString(11, x.getAccountInfo());
				pstmt.setString(12, x.getEmail());
				pstmt.setString(13, x.getField());
				
				/* 수정 */
				pstmt.setInt(14, x.getExpertNumber());
				pstmt.setString(15, x.getId());
				pstmt.setString(16, x.getPassword());
				pstmt.setString(17, x.getName());
				pstmt.setString(18, x.getSocialNumber());
				pstmt.setString(19, x.getTel());
				pstmt.setString(20, x.getOfficeAddress());
				pstmt.setString(21, x.getHomeAddress());
				pstmt.setInt(22, x.getOrganId());
				pstmt.setString(23, x.getPosition());
				pstmt.setString(24, x.getAccountInfo());
				pstmt.setString(25, x.getEmail());
				pstmt.setString(26, x.getField());
				
				pstmt.executeUpdate();
				pstmt.close();
			}
		}
		
		catch(SQLException e)
		{
			System.out.println("전문가목록 DB저장 실패...");
			e.printStackTrace();
		}
		
		System.out.println("전문가목록 DB저장 성공!");
		return true;
	}
}

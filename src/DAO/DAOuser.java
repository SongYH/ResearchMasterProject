package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Common.DBconnection;
import Common.User;
import Entity.UserList;

public class DAOuser
{
	private Connection connection;
	
	public DAOuser()
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
		ArrayList<User> retUserList = new ArrayList<User>();
		try
		{
			String sqlQuery = "SELECT * FROM user;";
			PreparedStatement pstmt = connection.prepareStatement(sqlQuery);
			ResultSet result = pstmt.executeQuery();
			while(result.next())
			{
				String id = result.getString("id");
				String password = result.getString("password");
				String name = result.getString("name");
				String sn = result.getString("socialNumber");
				String tel = result.getString("tel");
				String email = result.getString("email");
				String address = result.getString("address");
				int organId = result.getInt("organId");
				retUserList.add(new User(id, password, name, sn, tel, email, address, organId));
			}
			result.close();
			pstmt.close();
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}

		UserList.getInstance().setUserList(retUserList);
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
		ArrayList<User> saveList = UserList.getInstance().getUserList();
		
		StringBuilder queryBuilder = new StringBuilder();
		try
		{
			for(User x : saveList)
			{
				queryBuilder.delete(0, queryBuilder.length());
				queryBuilder.append("INSERT INTO user (id, password, name, socialNumber, tel, email, address, organId) ");
				queryBuilder.append("VALUES (?, ?, ?, ?, ?, ?, ?, ?) ");
				queryBuilder.append("ON DUPLICATE KEY UPDATE " );
				queryBuilder.append("id = ?, password = ?, name = ?, socialNumber = ?, ");
				queryBuilder.append("tel = ?, email = ?, address = ?, organId = ?;");
				
				String query = queryBuilder.toString();
				
				PreparedStatement pstmt = connection.prepareStatement(query);
				
				pstmt.setString(1, x.getId());
				pstmt.setString(2, x.getPassword());
				pstmt.setString(3, x.getName());
				pstmt.setString(4, x.getSocialNumber());
				pstmt.setString(5, x.getTel());
				pstmt.setString(6, x.getEmail());
				pstmt.setString(7, x.getAddress());
				pstmt.setInt(8, x.getOrganId());
				/* 수정 */
				pstmt.setString(9, x.getId());
				pstmt.setString(10, x.getPassword());
				pstmt.setString(11, x.getName());
				pstmt.setString(12, x.getSocialNumber());
				pstmt.setString(13, x.getTel());
				pstmt.setString(14, x.getEmail());
				pstmt.setString(15, x.getAddress());
				pstmt.setInt(16, x.getOrganId());
				
				pstmt.executeUpdate();
				pstmt.close();
			}
		}
		
		catch(SQLException e)
		{
			System.out.println("사용자목록 DB저장 실패...");
			e.printStackTrace();
		}
		
		System.out.println("사용자목록 DB저장 성공!");
		return true;
	}
}

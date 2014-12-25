package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Common.DBconnection;
import Common.UserPermission;
import Common.User;
import Entity.UserPermissionList;

public class DAOuserPermission
{	
	private Connection connection;
	
	public DAOuserPermission()
	{
		connection = DBconnection.getConnection();
	}
	
	/**
	 * 시스템이 시작될 때 DB에 있는 모든 사용자 목록을
	 * 싱글턴클래스 UserListBean에 모두 적재할것이다.
	 * 그 때 호출되는 메소드.
	 * @return user테이블에 있는 모든 사용자_권한 목록
	 */
	public void loadList()
	{
		ArrayList<UserPermission> retUserList = new ArrayList<UserPermission>();
		try
		{	
			String sqlQuery = "SELECT * FROM user_permission;";
			PreparedStatement pstmt = connection.prepareStatement(sqlQuery);
			ResultSet result = pstmt.executeQuery();
			while(result.next())
			{
				String userId = result.getString("userId");
				String permissionName = result.getString("permissionName");
				retUserList.add(new UserPermission(userId, permissionName));
			}
			result.close();
			pstmt.close();
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		
		UserPermissionList.getInstance().setList(retUserList);
		System.out.println("유저권한 로드 완료");
	}
	
	/**
	 * -수정필요-
	 * DB에 통으로 다 save한다 사실 이렇게 되면
	 * mysql이 중복잡아내서 오류를 엄청 뱉어낼것이다.
	 * saveList가 아니라 updateList로 수정해야할 필요가 있다.
	 * @param saveList -> DB에 저장할 사용자_권한 목록전체이다.
	 * @return 저장성공여부
	 */
	public boolean saveList()
	{	
		ArrayList<UserPermission> saveList = UserPermissionList.getInstance().getEntireUserPermissionList();
		StringBuilder queryBuilder = new StringBuilder();
		try
		{	
			for(UserPermission x : saveList)
			{	
				queryBuilder.delete(0, queryBuilder.length());
				queryBuilder.append("INSERT INTO user_permission (userId, permissionName) ");
				queryBuilder.append("VALUES (?, ?) ");
				queryBuilder.append("ON DUPLICATE KEY UPDATE ");
				queryBuilder.append("userId = ?, permissionName = ?;");
				
				String query = queryBuilder.toString();
				
				PreparedStatement pstmt = connection.prepareStatement(query);
				
				pstmt.setString(1, x.getUserId());
				pstmt.setString(2, x.getPermissionName());
				/* 수정 */
				pstmt.setString(3, x.getUserId());
				pstmt.setString(4, x.getPermissionName());
				
				pstmt.executeUpdate();
				pstmt.close();
			}
		}
		
		catch(SQLException e)
		{
			e.printStackTrace();
			System.out.println("사용자_권한목록 DB저장 실패...");
		}
		
		System.out.println("사용자_권한목록 DB저장 성공!");
		return true;
	}
}

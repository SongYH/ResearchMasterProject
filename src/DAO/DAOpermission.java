package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Common.DBconnection;
import Common.Permission;
import Common.User;
import Entity.PermissionList;

public class DAOpermission
{
	private Connection connection;
	
	public DAOpermission()
	{
		connection = DBconnection.getConnection();
	}

	/**
	 * 시스템이 시작될 때 DB에 있는 모든 사용자 목록을
	 * 싱글톤클래스 UserListBean에 모두 적재할것이다.
	 * 그 때 호출되는 메소드.
	 * @return user테이블에 있는 모든 권한목록
	 */
	
	public void loadList()
	{
		ArrayList<Permission> retPermissionList = new ArrayList<Permission>();
		try
		{
			String sqlQuery = "SELECT * FROM permission;";
			PreparedStatement pstmt = connection.prepareStatement(sqlQuery);
			ResultSet result = pstmt.executeQuery();
			while(result.next())
			{
				String name = result.getString("name");
				String description = result.getString("description");
				retPermissionList.add(new Permission(name, description));
			}
			result.close();
			pstmt.close();
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		
		PermissionList.getInstance().setList(retPermissionList);
		System.out.println("권한 로드 완료");
	}
	
	/**
	 * -수정필요-
	 * DB에 통으로 다 save한다 사실 이렇게 되면
	 * mysql이 중복잡아내서 오류를 엄청 뱉어낼것이다.
	 * saveList가 아니라 updateList로 수정해야할 필요가 있다.
	 * @param saveList -> DB에 저장할 권한목록전체이다.
	 * @return 저장성공여부
	 */
	public boolean saveList()
	{
		ArrayList<Permission> saveList = PermissionList.getInstance().getEntirePermissionList();
		
		StringBuilder queryBuilder = new StringBuilder();
		try
		{
			for(Permission x : saveList)
			{
				queryBuilder.delete(0, queryBuilder.length());
				queryBuilder.append("INSERT INTO permission (name, description) ");
				queryBuilder.append("VALUES (?, ?) ");
				queryBuilder.append("ON DUPLICATE KEY UPDATE " );
				queryBuilder.append("name = ?, description = ?;");
				
				String query = queryBuilder.toString();
				
				PreparedStatement pstmt = connection.prepareStatement(query);
				
				pstmt.setString(1, x.getName());
				pstmt.setString(2, x.getDescription());
				/* 수정 */
				pstmt.setString(3, x.getName());
				pstmt.setString(4, x.getDescription());

				pstmt.executeUpdate();
				pstmt.close();
			}
		}
		
		catch(SQLException e)
		{
			System.out.println("권한목록 DB저장 실패...");
			e.printStackTrace();
		}
		
		System.out.println("권한목록 DB저장 성공!");
		return true;
	}
}

package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Common.DBconnection;
import Common.Subject;
import Entity.SubjectList;

public class DAOsubject {
	
	private Connection connection;
	
	public DAOsubject()
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
		ArrayList<Subject> retUserList = new ArrayList<Subject>();
		try
		{
			String sqlQuery = "SELECT * FROM subject;";
			PreparedStatement pstmt = connection.prepareStatement(sqlQuery);
			ResultSet result = pstmt.executeQuery();
			while(result.next())
			{
				int number = result.getInt("number");
				String type = result.getString("type");
				String area = result.getString("area");
				String name = result.getString("name");
				String description = result.getString("description");
				java.util.Date openDate = new java.util.Date(result.getDate("openDate").getTime());
				java.util.Date closeDate = new java.util.Date(result.getDate("closeDate").getTime());
				retUserList.add(new Subject(number, type, area, name, description, openDate, closeDate));
			}
			result.close();
			pstmt.close();
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		
		SubjectList.getInstance().setSubjectList(retUserList);
		System.out.println("공고과제 subject 로드 완료");
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
		ArrayList<Subject> saveList = SubjectList.getInstance().getSubjectList();
		
		StringBuilder queryBuilder = new StringBuilder();
		try
		{
			for(Subject x : saveList)
			{
				queryBuilder.delete(0, queryBuilder.length());
				queryBuilder.append("INSERT INTO subject (number, type, area, name, description, openDate, closeDate) ");
				queryBuilder.append("VALUES (?, ?, ?, ?, ?, ?, ?) ");
				queryBuilder.append("ON DUPLICATE KEY UPDATE " );
				queryBuilder.append("number = ?, type = ?, area = ?, name = ?, description = ?, openDate = ?, closeDate =?;");
				
				String query = queryBuilder.toString();
				
				PreparedStatement pstmt = connection.prepareStatement(query);
				
				pstmt.setInt(1, x.getNumber());
				pstmt.setString(2, x.getType());
				pstmt.setString(3, x.getArea());
				pstmt.setString(4, x.getName());
				pstmt.setString(5, x.getDescription());
				pstmt.setDate(6, new java.sql.Date(x.getOpenDate().getTime()));
				pstmt.setDate(7, new java.sql.Date(x.getCloseDate().getTime()));
				/* 수정 */
				pstmt.setInt(8, x.getNumber());
				pstmt.setString(9, x.getType());
				pstmt.setString(10, x.getArea());
				pstmt.setString(11, x.getName());
				pstmt.setString(12, x.getDescription());
				pstmt.setDate(13, new java.sql.Date(x.getOpenDate().getTime()));
				pstmt.setDate(14, new java.sql.Date(x.getCloseDate().getTime()));
				
				pstmt.executeUpdate();
				pstmt.close();
			}
		}
		
		catch(SQLException e)
		{
			System.out.println("신청대상과제목록 DB저장 실패...");
			e.printStackTrace();
		}
		
		System.out.println("신청대상과제목록 DB저장 성공!");
		return true;
	}
}

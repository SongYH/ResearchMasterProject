package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Common.DBconnection;
import Common.Organ;
import Entity.OrganList;

public class DAOorgan
{
	private Connection connection;
	
	public DAOorgan()
	{
		connection = DBconnection.getConnection();
	}
	
	/**
	 * 시스템이 시작될 때 DB에 있는 모든 기관목록을
	 * 싱글턴클래스 OrganListBean에 모두 적재할것이다.
	 * 그 때 호출되는 메소드.
	 * @return organ테이블에 있는 모든 기관목록
	 */
	public void loadList()
	{
		ArrayList<Organ> retOrganList = new ArrayList<Organ>();
		try
		{
			String sqlQuery = "SELECT * FROM organ;";
			PreparedStatement pstmt = connection.prepareStatement(sqlQuery);
			ResultSet result = pstmt.executeQuery();
			while(result.next())
			{
				int organId = result.getInt("organId");
				String organName = result.getString("organName");
				String regionCode = result.getString("regionCode");
				String fax = result.getString("fax");
				String president = result.getString("president");
				retOrganList.add(new Organ(organId, organName, regionCode, fax, president));
			}
			result.close();
			pstmt.close();
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		
		OrganList.getInstance().setOrganList(retOrganList);
		System.out.println("기관 로드 완료");
	}
	
	/**
	 * -수정필요-
	 * DB에 통으로 다 save한다 사실 이렇게 되면
	 * mysql이 중복잡아내서 오류를 엄청 뱉어낼것이다.
	 * saveList가 아니라 updateList로 수정해야할 필요가 있다.
	 * @param saveList -> DB에 저장할 기관목록전체이다.
	 * @return 저장성공여부
	 */
	public boolean saveList()
	{
		ArrayList<Organ> saveList = OrganList.getInstance().getOrganList();
		
		StringBuilder queryBuilder = new StringBuilder();
		try
		{
			for(Organ x : saveList)
			{
				queryBuilder.delete(0, queryBuilder.length());
				queryBuilder.append("INSERT INTO organ (organId, organName, regionCode, fax, president) ");
				queryBuilder.append("VALUES (?, ?, ?, ?, ?) ");
				queryBuilder.append("ON DUPLICATE KEY UPDATE " );
				queryBuilder.append("organId = ?, organName = ?, regionCode = ?, fax = ?, president = ?;");
				
				String query = queryBuilder.toString();
				
				PreparedStatement pstmt = connection.prepareStatement(query);
				
				pstmt.setInt(1, x.getOrganId());
				pstmt.setString(2, x.getOrganName());
				pstmt.setString(3, x.getRegionCode());
				pstmt.setString(4, x.getFax());
				pstmt.setString(5, x.getPresident());
				
				pstmt.setInt(6, x.getOrganId());
				pstmt.setString(7, x.getOrganName());
				pstmt.setString(8, x.getRegionCode());
				pstmt.setString(9, x.getFax());
				pstmt.setString(10, x.getPresident());
				
				pstmt.executeUpdate();
				pstmt.close();
			}
		}
		
		catch(SQLException e)
		{
			System.out.println("기관목록 DB저장 실패...");
			e.printStackTrace();
			return false;
		}
		
		System.out.println("기관목록 DB저장 성공!");
		return true;
	}
}

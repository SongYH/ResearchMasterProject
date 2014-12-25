package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import Common.Evidence;
import Common.DBconnection;
import Entity.EvidenceList;

public class DAOevidence {
   private Connection connection;
   
   //DBconnection에서 DB 주소 가져옴
   public DAOevidence()
   {
      connection = DBconnection.getConnection();
   }
///////////////////////////////////////////////////////////////

   /**
    * 시스템이 시작될 때 DB에 있는 모든 예산 목록을
    * 싱글턴클래스 EvidenceListBean에 모두 적재할것이다.
    * 그 때 호출되는 메소드.
    * @return Evidence테이블에 있는 모든 사용자 목록
    */
   
   public void loadList()
   {
      ArrayList<Evidence> evidenceList = new ArrayList<Evidence>();
      try
      {
         String sqlQuery = "SELECT * FROM evidence;";
         PreparedStatement pstmt = connection.prepareStatement(sqlQuery);
         ResultSet result = pstmt.executeQuery();
         while(result.next())
         {
            int projectNumber = result.getInt("projectNumber");
            int amount = result.getInt("amount");
            Date date = new Date(result.getDate("date").getTime());
            String file = result.getString("file");
     
            evidenceList.add(new Evidence(projectNumber, amount,file,date));
         }
         result.close();
         pstmt.close();
      }
      catch(SQLException e)
      {
         e.printStackTrace();
      }
      EvidenceList.getInstance().setEvidenceList(evidenceList);
      System.out.println("사업증빙자료 로드 완료");
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
      ArrayList<Evidence> saveList = EvidenceList.getInstance().getAllList();
      
      StringBuilder queryBuilder = new StringBuilder();
      StringBuilder queryProjectUser = new StringBuilder();
      try
      {
         for(Evidence x : saveList)
         {
            queryBuilder.delete(0, queryBuilder.length());
            queryBuilder.append("INSERT INTO evidence (projectNumber, amount, file, date) ");
            queryBuilder.append("VALUES (?, ?, ?, ?) ");
            queryBuilder.append("ON DUPLICATE KEY UPDATE " );
            queryBuilder.append("projectNumber = ?, amount=?, file = ?, date = ?;");
            
            String query = queryBuilder.toString();
            
            PreparedStatement pstmt = connection.prepareStatement(query);
            
            pstmt.setInt(1, x.getProjectNumber());
            pstmt.setInt(2, x.getAmount());
            pstmt.setString(3, x.getFile());
            pstmt.setDate(4, new java.sql.Date(x.getDate().getTime()));
            
            /* 수정 */
            pstmt.setInt(5, x.getProjectNumber());
            pstmt.setInt(6, x.getAmount());
            pstmt.setString(7, x.getFile());
            pstmt.setDate(8, new java.sql.Date(x.getDate().getTime()));
            
            pstmt.executeUpdate();
            pstmt.close();   
         }

         
         System.out.println("사업비 증빙자료 목록 저장 성공");
         }
      
      catch(SQLException e)
      {
    	  System.out.println("사업비 증빙자료 목록 저장 실패");
         e.printStackTrace();
      }
      return true;
   }
   
}

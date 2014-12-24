package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import Common.Clawback;
import Common.DBconnection;
import Entity.ClawbackList;

public class DAOclawback {
   private Connection connection;
   
   //DBconnection에서 DB 주소 가져옴
   public DAOclawback()
   {
      connection = DBconnection.getConnection();
   }
///////////////////////////////////////////////////////////////

   /**
    * 시스템이 시작될 때 DB에 있는 모든 예산 목록을
    * 싱글턴클래스 ClawbackListBean에 모두 적재할것이다.
    * 그 때 호출되는 메소드.
    * @return Clawback테이블에 있는 모든 사용자 목록
    */
   
   public void loadList()
   {
      ArrayList<Clawback> clawbackList = new ArrayList<Clawback>();
      try
      {
         String sqlQuery = "SELECT * FROM clawback;";
         PreparedStatement pstmt = connection.prepareStatement(sqlQuery);
         ResultSet result = pstmt.executeQuery();
         while(result.next())
         {
            int projectNumber = result.getInt("projectNumber");
            int amount = result.getInt("amount");
            int rate = result.getInt("rate");
            Date date = new Date(result.getDate("date").getTime());
            clawbackList.add(new Clawback(projectNumber, amount,rate,date));
         }
         result.close();
         pstmt.close();
      }
      catch(SQLException e)
      {
         e.printStackTrace();
      }
      ClawbackList.getInstance().setClawbackList(clawbackList);
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
      ArrayList<Clawback> saveList = ClawbackList.getInstance().getAllList();
      
      StringBuilder queryBuilder = new StringBuilder();
      StringBuilder queryProjectUser = new StringBuilder();
      try
      {
         for(Clawback x : saveList)
         {
            queryBuilder.delete(0, queryBuilder.length());
            queryBuilder.append("INSERT INTO clawback (projectNumber, amount, rate, date) ");
            queryBuilder.append("VALUES (?, ?, ?, ?) ");
            queryBuilder.append("ON DUPLICATE KEY UPDATE " );
            queryBuilder.append("projectNumber = ?, amount=?, rate = ?, date = ?;");
            
            String query = queryBuilder.toString();
            
            PreparedStatement pstmt = connection.prepareStatement(query);
            
            pstmt.setInt(1, x.getProjectNumber());
            pstmt.setInt(2, x.getAmount());
            pstmt.setInt(3, x.getRate());
            pstmt.setDate(4, new java.sql.Date(x.getDate().getTime()));
            
            /* 수정 */
            pstmt.setInt(5, x.getProjectNumber());
            pstmt.setInt(6, x.getAmount());
            pstmt.setInt(7, x.getRate());
            pstmt.setDate(8, new java.sql.Date(x.getDate().getTime()));
            pstmt.executeUpdate();
            pstmt.close();   
         }

         
         System.out.println("환수금 목록 저장 성공");
         }
      
      catch(SQLException e)
      {
    	  System.out.println("환수금 목록 저장 실패");
         e.printStackTrace();
      }
      return true;
   }
   
}

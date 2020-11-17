package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBUtil {

   public static ResultSet findUser(Connection con, String mid) {

      String sqlSt = "SELECT user_pwd FROM User WHERE user_email=";
      Statement st;
      try {

         st = con.createStatement();

         if (st.execute(sqlSt + "'" + mid + "'")) {
            return st.getResultSet();
         }

      } catch (SQLException e) {

         // TODO Auto-generated catch block
         e.printStackTrace();

      }
      return null;
   }

   public static void addMember(Connection con, String new_name, String new_id, 
         String new_pwd) throws SQLException {
      
      PreparedStatement pstmt = null;
      try {
         con.setAutoCommit(false);
         pstmt = con.prepareStatement("INSERT INTO User (user_name, user_email, user_pwd) VALUES(?, ? ,?)");
         pstmt.setString(1, new_name);
         pstmt.setString(2, new_id);
         pstmt.setString(3, new_pwd);
         pstmt.executeUpdate();

         con.commit();
         con.setAutoCommit(true);

      } catch (SQLException e) {
         e.printStackTrace();

      } finally {
         if (pstmt != null) {pstmt.close();}

      }

   }
   
   public static ResultSet findCompany(Connection con, String field) { // 선택한 분야의 회사 찾는 함수 

	      String sqlinter = "SELECT stock_company FROM Stockinsight.Stock WHERE stock_field =";
	      Statement st;
	      try {

	         st = con.createStatement();

	         if (st.execute(sqlinter + "'" + field + "'")) {
	            return st.getResultSet();
	         }

	      } catch (SQLException e) {

	         // TODO Auto-generated catch block
	         e.printStackTrace();

	      }
	      return null;
	   }
   
   public static ResultSet findSearchCompany(Connection con, String search) { // 검색문자가 들어간 회사 종류 찾는 함수 

	      String sqlinter = "SELECT stock_company FROM Stockinsight.Stock WHERE stock_company LIKE '%";
	      Statement st;
	      try {

	         st = con.createStatement();
	         
	         if (st.execute(sqlinter +  search + "%'")) { // 해당 문자열이 들어간 결과 반환 
	        	 return st.getResultSet();
	         }
	         

	      } catch (SQLException e) {

	         // TODO Auto-generated catch block
	         e.printStackTrace();
	         return null;

	      }
	      return null;
	}
   
   public static ResultSet findFieldSelectCompany(Connection con, String selectCompany) { // 회사명에 일치하는 분야 리턴 --> 마지막에 검색 결과 뿌려줄 때 사용 

	      String sqlinter = "SELECT stock_field FROM Stockinsight.Stock WHERE stock_company ="; // 회사명에 일치하는 분야 리턴 
	      Statement st;
	      try {

	         st = con.createStatement();
	         System.out.println("findFieldSelectCompany:" + selectCompany);
	         if (st.execute(sqlinter + "'" + selectCompany + "'")) {
	            return st.getResultSet(); // field 넘김 
	           
	         }

	      } catch (SQLException e) {

	         // TODO Auto-generated catch block
	         e.printStackTrace();

	      }
	      return null;
	}
   
   public static ResultSet findFieldSet(Connection con) {

	      String sqlinter = "SELECT DISTINCT stock_field FROM Stockinsight.Stock"; // 모든 분야 리턴 
	      Statement st;
	      try {

	         st = con.createStatement();
	         if (st.execute(sqlinter)) {
	            return st.getResultSet(); // field 넘김 
	           
	         }

	      } catch (SQLException e) {

	         // TODO Auto-generated catch block
	         e.printStackTrace();

	      }
	      return null;
	}
   
}
   
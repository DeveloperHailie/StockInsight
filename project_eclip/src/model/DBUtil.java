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
}
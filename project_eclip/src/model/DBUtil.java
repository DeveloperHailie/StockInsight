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

   public static void addMember(Connection con, String newId, String newpasswd 
         ,String newName, String address, String phone) throws SQLException {
      
      PreparedStatement pstmt = null;
      try {
         con.setAutoCommit(false);
         pstmt = con.prepareStatement("INSERT INTO User VALUES( ?, ?, ? , ?, ?)");
         pstmt.setString(1, newId);
         pstmt.setString(2, newpasswd);
         pstmt.setString(3, newName);
         pstmt.setString(4, address);
         pstmt.setString(5, phone);
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
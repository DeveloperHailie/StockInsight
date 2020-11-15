package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class DBUtil {

	public static ResultSet findIndex(Connection con, String mid) {
	      String sql = "SELECT user_index FROM User WHERE user_email=";
	       Statement st;

	      try {
	          st = con.createStatement();

	            if (st.execute(sql + "'" + mid + "'")) {
	               return st.getResultSet();
	            }
	      }catch(SQLException e) {
	         e.printStackTrace();
	      }
	      return null;
	   }
	   

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

   public static int addQuestion(Connection conn, int uidx, String title, String content, String date) {
		// index 받아오기
		String selectSql = "SELECT MAX(ques_index) FROM Question";
		Statement st;
		int number = -1;
		try {
			st = conn.createStatement();
			ResultSet rs = st.executeQuery(selectSql);

			System.out.println(rs.toString());
			while (rs.next()) {
				number = rs.getInt(1) + 1;
				System.out.println(number);
			}
			if (number > -1) {
				// 받아온 index+1로 insert
				PreparedStatement pstmt = null;
				try {
					conn.setAutoCommit(false);
					// INSERT INTO Stockinsight.Question VALUES('','문의드립니다.','카카오 너무 높게 예측하는거
					// 아닌가요?','20201115',null,'1');
					String sqlSt = "INSERT INTO Question VALUES(?,?,?,?,?,?)";
					pstmt = conn.prepareStatement(sqlSt);
					pstmt.setString(1, Integer.toString(number));
					pstmt.setString(2, title);
					pstmt.setString(3, content);
					pstmt.setString(4, date);
					pstmt.setString(5, null);
					pstmt.setString(6, Integer.toString(uidx));

					pstmt.executeUpdate();
					conn.commit();
					conn.setAutoCommit(true);

					return number;

				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		// 실패하면 -1
		return -1;
		
	}

	public static Boolean checkAdmin(Connection conn, int uidx) {
		// admin이면 true, admin아니면 false
		String selectSql = "SELECT user_admin FROM User WHERE user_index="+Integer.toBinaryString(uidx);

		Statement st;
		int number = -1;
		try {
			st = conn.createStatement();
			ResultSet rs = st.executeQuery(selectSql);

			System.out.println(rs.toString());
			while (rs.next()) {
				number = rs.getInt(1);
				if(number==1) {
					return true;
				}
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}

		return false;

	}
   
}
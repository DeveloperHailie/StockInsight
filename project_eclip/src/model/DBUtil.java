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
        String sql = "SELECT user_index FROM User WHERE user_id=";
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

     String sqlSt = "SELECT user_pwd FROM User WHERE user_id=";
     Statement st;
     try {

        st = con.createStatement();

        if (st.execute(sqlSt + "'" + mid + "'")) {
           return st.getResultSet();
        }

     } catch (SQLException e) {

        // TODO Auto-generated catch block
        e.printStackTrace();

<<<<<<< HEAD
      }
      return null;
   }
   public static ResultSet checkMypage(Connection con, String memail) {

	      String sqlSt = "SELECT user_pwd FROM User WHERE user_id=";
	      Statement st;
	      try {

	         st = con.createStatement();

	         if (st.execute(sqlSt + "'" + memail + "'")) {
	        	 System.out.println("1");
	            return st.getResultSet();
	         }

	      } catch (SQLException e) {
	    	  System.out.println("2");
	         // TODO Auto-generated catch block
	         e.printStackTrace();

	      }
	      return null;
	   }
=======
     }
     return null;
  }
  
  public static Boolean checkID(Connection conn, String input_id) {
		
		String idfind_Sql = "SELECT user_id FROM User WHERE user_id=";

		Statement st;
		try {
			st = conn.createStatement();
			ResultSet rs = st.executeQuery(idfind_Sql+ "'" + input_id + "'");

			while (rs.next()) {
				String id = rs.getString(1);
				if(id.equals(input_id)) {
					return true;
				}
				else {
					return false;
				}
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}

		return false;
>>>>>>> 7dac78cb4717abb89c70055754cd1df43be250fa

	}
  public static void addMember(Connection con, String new_name, String new_id,
		  String new_email, String new_pwd) throws SQLException {
     
     PreparedStatement pstmt = null;
     try {
        con.setAutoCommit(false);
        pstmt = con.prepareStatement("INSERT INTO User (user_name, user_id, user_email, user_pwd) VALUES(?, ?,? ,?)");
        pstmt.setString(1, new_name);
        pstmt.setString(2, new_id);
        pstmt.setString(3, new_email);
        pstmt.setString(4, new_pwd);
        pstmt.executeUpdate();

        con.commit();
        con.setAutoCommit(true);

     } catch (SQLException e) {
        e.printStackTrace();

     } finally {
        if (pstmt != null) {pstmt.close();}

     }

  }


<<<<<<< HEAD
=======
   public static int addQuestion(Connection conn, int uidx, String title, String content, String date) {
>>>>>>> 7dac78cb4717abb89c70055754cd1df43be250fa
		// index 諛쏆븘�삤湲�
		String selectSql = "SELECT MAX(ques_index) FROM Question";
		Statement st;
		int number = -1;
		try {
			st = conn.createStatement();
			ResultSet rs = st.executeQuery(selectSql);
<<<<<<< HEAD
			System.out.println(rs.getInt(1));
			number = rs.getInt(1)+1;
			
			// 諛쏆븘�삩 index+1濡� insert
			PreparedStatement pstmt = null;
			try {
				conn.setAutoCommit(false);
				// INSERT INTO Stockinsight.Question VALUES('','臾몄쓽�뱶由쎈땲�떎.','移댁뭅�삤 �꼫臾� �넂寃� �삁痢≫븯�뒗嫄� �븘�땶媛��슂?','20201115',null,'1');
				String sqlSt = "INSERT INTO Question VALUES(?,?,?,?,?,?)";
				pstmt = conn.prepareStatement(sqlSt);
				pstmt.setString(1,Integer.toString(number));
				pstmt.setString(2,title);
				pstmt.setString(3,content);
				pstmt.setString(4,date);
				pstmt.setString(5,null);
				pstmt.setString(6, Integer.toString(uidx));
				
				pstmt.executeUpdate();
				conn.commit();
				conn.setAutoCommit(true);
				
				return number;
				
			}catch(SQLException e) {
				e.printStackTrace();
=======

			System.out.println(rs.toString());
			while (rs.next()) {
				number = rs.getInt(1) + 1;
				System.out.println(number);
			}
			if (number > -1) {
				// 諛쏆븘�삩 index+1濡� insert
				PreparedStatement pstmt = null;
				try {
					conn.setAutoCommit(false);
					// INSERT INTO Stockinsight.Question VALUES('','臾몄쓽�뱶由쎈땲�떎.','移댁뭅�삤 �꼫臾� �넂寃� �삁痢≫븯�뒗嫄�
					// �븘�땶媛��슂?','20201115',null,'1');
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
>>>>>>> 7dac78cb4717abb89c70055754cd1df43be250fa
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
<<<<<<< HEAD
	   
=======
>>>>>>> 7dac78cb4717abb89c70055754cd1df43be250fa
		// �떎�뙣�븯硫� -1
		return -1;
		
	}

	public static Boolean checkAdmin(Connection conn, int uidx) {
		// admin�씠硫� true, admin�븘�땲硫� false
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
	
	public static int addAnswer(Connection conn, String title, String content, String date) {
		// answer table에 넣기
		// answer_index 반환
		String selectSql = "SELECT MAX(answer_index) FROM Answer";
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
					// INSERT INTO Stockinsight.Answer VALUES('','답변드립니다.','답변내용','2020.11.15');
					String sqlSt = "INSERT INTO Answer VALUES(?,?,?,?)";
					pstmt = conn.prepareStatement(sqlSt);
					pstmt.setString(1, Integer.toString(number));
					pstmt.setString(2, title);
					pstmt.setString(3, content);
					pstmt.setString(4, date);

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
	
	public static Boolean updateQuestion(Connection conn, int question_index, int answer_index) {
		// Question DB에 question_index row의 Answer_answer_index 수정
		// update Stockinsight.Question SET Answer_answer_index=2 where ques_index=1;
		String selectSql = "SELECT * FROM Question WHERE ques_index="+ question_index;
		Statement stmt = null;
		try {
			stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
			ResultSet uprs = stmt.executeQuery(selectSql);
			while(uprs.next()) {
				uprs.updateString("Answer_answer_index",Integer.toString(answer_index));
				uprs.updateRow();
			}
			return true;
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
}
package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import com.mysql.cj.protocol.Resultset;

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

		}
		return null;
	}

	public static ResultSet checkMypage(Connection con, String mid) {

		String sqlSt = "SELECT user_pwd FROM User WHERE user_id=";
		Statement st;
		try {

			st = con.createStatement();

			if (st.execute(sqlSt + "'" + mid + "'")) {
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

	public static ResultSet checkMypageinner(Connection con, String mid) {

		String sqlSt = "SELECT * FROM User WHERE user_id=";
		Statement st;
		try {

			st = con.createStatement();
			String str = (sqlSt + "'" + mid + "'");

			if (st.execute(str)) {
				//System.out.println("1");
				System.out.println("여기왔어?");
				return st.executeQuery(str);
			}

		} catch (SQLException e) {
			System.out.println("2");
			// TODO Auto-generated catch block
			e.printStackTrace();

		}
		return null;
	}

	public static ResultSet popupplus(Connection con, String mid) {

		String sqlSt = 
				"select stock_company, stock_future-stock_before as Gap, stock_code\r\n" + 
						"from Stock\r\n" + 
						"where stock_future-stock_before>0 and stock_index=any(select Stock_stock_index\r\n" + 
						"from User inner join Interest on User.user_index=Interest.User_user_index \r\n" + 
						"where user_id = '"+mid+"') order by gap desc limit 1;";
		Statement st;
		try {

			st = con.createStatement();
			// String str = (sqlSt + "'" + mid + "'");

			if (st.execute(sqlSt)) {
				//System.out.println("1");
				// System.out.println("여기왔어?");
				// System.out.println("here");
				return st.executeQuery(sqlSt);
			}

		} catch (SQLException e) {
			//System.out.println("2");
			// TODO Auto-generated catch block
			e.printStackTrace();

		}
		return null;
	}

	public static ResultSet popupminus(Connection con, String mid) {

		String sqlSt = 
				"select stock_company, stock_future-stock_before as Gap, stock_code\r\n" + 
						"from Stock\r\n" + 
						"where stock_future-stock_before<0 and stock_index=any(select Stock_stock_index\r\n" + 
						"from User inner join Interest on User.user_index=Interest.User_user_index \r\n" + 
						"where user_id = '"+mid+"') order by gap limit 1;";
		Statement st;
		try {

			st = con.createStatement();
			// String str = (sqlSt + "'" + mid + "'");

			if (st.execute(sqlSt)) {
				//System.out.println("1");
				// System.out.println("여기왔어?");
				// System.out.println("here");
				return st.executeQuery(sqlSt);
			}

		} catch (SQLException e) {
			//System.out.println("2");
			// TODO Auto-generated catch block
			e.printStackTrace();

		}
		return null;
	}

	public static ResultSet checkMypageedit(Connection con, String mid, String new_email, String new_passwd) {

		String sqlSt = "UPDATE User SET user_email ='" + new_email+"', user_pwd='" +new_passwd+"' WHERE user_id='"+mid+"'";
		Statement st;
		try {

			st = con.createStatement();

			if (st.execute(sqlSt)) {
				//System.out.println("1");
				System.out.println("수정하러왔어요");
				return st.executeQuery(sqlSt);
			}

		} catch (SQLException e) {
			System.out.println("수정실패");
			// TODO Auto-generated catch block
			e.printStackTrace();

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
	}


	public static ResultSet findID(Connection conn, String input_name, String input_email) {

		String sqlSt = "SELECT user_id FROM User WHERE user_name=";
		Statement st;
		try {

			st = conn.createStatement();

			if (st.execute(sqlSt + "'" + input_name + "'and user_email='" + input_email + "'")) {
				return st.getResultSet();
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static ResultSet findPWD(Connection conn, String input_name, String input_id, String input_email) {

		String sqlSt = "SELECT user_pwd FROM User WHERE user_name=";
		Statement st;
		try {

			st = conn.createStatement();

			if (st.execute(sqlSt + "'" + input_name + "'and user_id = '" + input_id + "'and user_email='" + input_email + "'")) {
				return st.getResultSet();
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
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



	public static int addQuestion(Connection conn, int uidx, String title, String content, String date) {
		// index 諛쏆븘�삤湲�
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
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		// �떎�뙣�븯硫� -1
		return -1;

	}


	public static Boolean checkAdmin(Connection conn, int uidx) {
		// admin�씠硫� true, admin�븘�땲硫� false
		String selectSql = "SELECT user_admin FROM User WHERE user_index="+Integer.toString(uidx);

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
		// answer table�뿉 �꽔湲�
		// answer_index 諛섑솚
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
				// 諛쏆븘�삩 index+1濡� insert
				PreparedStatement pstmt = null;
				try {

					conn.setAutoCommit(false);
					// INSERT INTO Stockinsight.Answer  VALUES('','�떟蹂��뱶由쎈땲�떎.','�떟蹂��궡�슜','2020.11.15');
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
		// �떎�뙣�븯硫� -1
		return -1;
	}

	public static Boolean updateQuestion(Connection conn, int question_index, int answer_index) {
		// Question DB�뿉 question_index row�쓽 Answer_answer_index �닔�젙
		// update Stockinsight.Question SET Answer_answer_index=2 where ques_index=1;
		System.out.println("updateQuestion:"+question_index+" "+answer_index);
		String selectSql = "SELECT * FROM Question WHERE ques_index="+ question_index;
		Statement stmt = null;
		try {
			stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
			ResultSet uprs = stmt.executeQuery(selectSql);
			while(uprs.next()) {
				conn.setAutoCommit(false);

				uprs.updateString("Answer_answer_index",Integer.toString(answer_index));
				uprs.updateRow();

				conn.commit();
				conn.setAutoCommit(true);
			}
			return true;
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public static ArrayList<QnAList> getPostList(Connection conn){
		ArrayList<QnAList> qnaList = new ArrayList<QnAList>();

		String qu_title="";
		String qu_content="";
		String qu_date="";
		String qu_writer="";
		String qu_index="";
		String qu_reply="";

		Statement stmt = null;
		String questionQuery = "SELECT * FROM Question order by ques_index DESC";

		try {
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(questionQuery);
			while(rs.next()) {
				QnAList post = new QnAList();
				QnAList post_answer = new QnAList();
				// index, title, content, date, answer_index, user_index
				qu_index = rs.getString(1);
				qu_title= rs.getString(2);
				qu_content= rs.getString(3);
				qu_date= rs.getString(4);
				qu_reply= rs.getString(5);
				qu_writer= rs.getString(6);
				// writer (index)로 사용자 id 받아오기
				qu_writer = getUserId(conn, qu_writer);

				if(qu_writer!=null) {
					post.setQnAList(true, qu_index, qu_writer, qu_title, qu_content, qu_date);
					// question_post를 list에 넣기
					qnaList.add(post);
					// reply가 null이 아니면  reply_post 받아오기
					if(qu_reply!=null) {
						post_answer = getAnswerPost(conn, qu_reply);
						post_answer.setIndex(qu_index);
						// reply_post를 list에 넣기
						qnaList.add(post_answer);
					}
				}
			}
			return qnaList;
		}catch(SQLException e) {
			e.printStackTrace();
		}

		return qnaList;
	}

	public static QnAList getAnswerPost(Connection conn, String idx) {
		QnAList answer = new QnAList();
		String an_title="";
		String an_content="";
		String an_date="";

		Statement stmt = null;
		String questionQuery = "SELECT * FROM Answer WHERE answer_index="+idx;

		try {
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(questionQuery);
			while(rs.next()) {
				an_title = rs.getString(2);
				an_content = rs.getString(3);
				an_date = rs.getString(4);
			}
			answer.setQnAList(false, idx, "admin", an_title, an_content, an_date);
			return answer;
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
	}



	public static String getUserId(Connection conn, String idx) {
		Statement stmt = null;
		String questionQuery = "SELECT user_id FROM User WHERE user_index="+idx;
		String user_id = "";
		try {
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(questionQuery);
			while(rs.next()) {
				user_id = rs.getString(1);
			}
			return user_id;
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static QnAList getQuestionPost(Connection conn, String idx) {
		Statement stmt = null;
		String questionQuery = "SELECT * FROM Question WHERE ques_index="+idx;
		String qu_title="";
		String qu_content="";
		String qu_date="";
		String qu_writer="";
		String qu_index="";
		String qu_reply="";

		try {
			QnAList post = new QnAList();
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(questionQuery);
			while(rs.next()) {
				// index, title, content, date, answer_index, user_index
				qu_index = rs.getString(1);
				qu_title= rs.getString(2);
				qu_content= rs.getString(3);
				qu_date= rs.getString(4);
				qu_reply= rs.getString(5);
				qu_writer= rs.getString(6);
				// writer (index)로 사용자 id 받아오기
				qu_writer = getUserId(conn, qu_writer);

				if(qu_writer!=null) {
					// question_post를 list에 넣기
					post.setQnAList(true, qu_index, qu_writer, qu_title, qu_content, qu_date);
				}
			}
			return post;
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static QnAList getAnswerPostUseQuesIdx(Connection conn, String ques_idx) {
		Statement stmt = null;
		String questionQuery = "SELECT Answer_answer_index FROM Question WHERE ques_index="+ques_idx;

		String ans_idx="";
		try {
			QnAList post = new QnAList();
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(questionQuery);
			while(rs.next()) {
				ans_idx = rs.getString(1);
			}
			post = getAnswerPost(conn, ans_idx);
			post.setIndex(ques_idx);
			System.out.println("getAPUQI: "+post);
			return post;			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
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

	public static ArrayList<QnAList> getmyPostList(Connection conn, String param_user_index){
		ArrayList<QnAList> qnaList = new ArrayList<QnAList>();

		String qu_title="";
		String qu_content="";
		String qu_date="";
		String qu_writer_index = "";
		String qu_writer="";
		String qu_index="";
		String qu_reply="";

		Statement stmt = null;
		String questionQuery = "SELECT * FROM Question order by ques_index DESC";

		try {
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(questionQuery);
			while(rs.next()) {
				QnAList post = new QnAList();
				QnAList post_answer = new QnAList();
				// index, title, content, date, answer_index, user_index
				qu_index = rs.getString(1);
				qu_title= rs.getString(2);
				qu_content= rs.getString(3);
				qu_date= rs.getString(4);
				qu_reply= rs.getString(5);
				qu_writer_index= rs.getString(6);
				// writer (index)로 사용자 id 받아오기
				qu_writer = getUserId(conn, qu_writer_index);

				if(qu_writer!=null && qu_writer_index.contentEquals(param_user_index)) {
					post.setQnAList(true, qu_index, qu_writer, qu_title, qu_content, qu_date);
					// question_post를 list에 넣기
					qnaList.add(post);
					// reply가 null이 아니면  reply_post 받아오기
					if(qu_reply!=null) {
						post_answer = getAnswerPost(conn, qu_reply);
						post_answer.setIndex(qu_index);
						// reply_post를 list에 넣기
						qnaList.add(post_answer);
					}
				}
			}
			return qnaList;
		}catch(SQLException e) {
			e.printStackTrace();
		}

		return qnaList;
	}

	public static ResultSet findUserIndex(Connection con, String user_id) {

		String sqlinter = "SELECT user_index From Stockinsight.User WHERE user_id ="; // 회사명에 일치하는 분야 리턴 
		Statement st;
		try {

			st = con.createStatement();
			System.out.println("user_id:" + user_id);
			if (st.execute(sqlinter + "'" + user_id + "'")) {
				return st.getResultSet(); // field 넘김 

			}

		} catch (SQLException e) {

			// TODO Auto-generated catch block
			e.printStackTrace();

		}
		return null;
	}

	public static ResultSet findStockIndex(Connection con, String selectCompany) {

		String sqlinter = "SELECT stock_index FROM Stockinsight.Stock WHERE stock_company ="; // Stock 테이블에서 stock_index 가져오기 
		Statement st;
		try {

			st = con.createStatement();
			//System.out.println("DBUtil_findStockIndex_selectCompany:" + selectCompany);
			if (st.execute(sqlinter + "'" + selectCompany + "'")) {
				return st.getResultSet(); // field 넘김 

			}

		} catch (SQLException e) {

			// TODO Auto-generated catch block
			e.printStackTrace();

		}
		return null;
	}

	static int interest_index = 0;
	public static void insertInterest(Connection con, String user_index, String stock_index ) {

		PreparedStatement pstmt = null;
		try {  
			//System.out.print("  interest_index: " + interest_index);
			con.setAutoCommit(false);
			pstmt = con.prepareStatement("INSERT INTO Stockinsight.Interest (interest_index, User_user_index, Stock_stock_index) VALUES(?, ?, ?)"); // 관심 종목 삽입 
			pstmt.setInt(1, interest_index);
			pstmt.setString(2, user_index);
			pstmt.setString(3, stock_index);
			pstmt.executeUpdate();

			con.commit();
			con.setAutoCommit(true);

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (pstmt != null) {try {
				pstmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}}
		}
	}


	public static ResultSet deleteInterest(Connection con, String user_index, String stock_index ) {

		String sqlinter = "DELETE FROM Stockinsight.Interest WHERE User_user_index="; // 관심 종목에 들어가있는 항목 삭제 
		String sqlinter2 = "and Stock_stock_index=";
		Statement st;
		try {

			st = con.createStatement();

			if (st.execute(sqlinter + "'" + user_index + "'" + sqlinter2 + "'" + stock_index + "'" )) {
				//interest_index = - 1;
				return st.getResultSet(); // field 넘김 
			}



		} catch (SQLException e) {

			// TODO Auto-generated catch block
			e.printStackTrace();

		}
		return null;
	}

	public static ResultSet findStockIndexFromUser(Connection con, String user_index) {

		String sqlinter = "SELECT Stock_stock_index FROM Stockinsight.Interest WHERE User_user_index ="; // 유저로부터 stock_index 얻어내기 
		String sqlfield = "";
		Statement st;
		try {

			st = con.createStatement();

			if (st.execute(sqlinter + "'" + user_index + "'" )) {
				//interest_index = - 1;
				return st.getResultSet(); // field 넘김 
			}

		} catch (SQLException e) {

			// TODO Auto-generated catch block
			e.printStackTrace();

		}
		return null;
	}

	public static ResultSet findStockFieldFromStockIndex(Connection con, String stock_index) {

		String sqlinter = "SELECT stock_field FROM Stockinsight.Stock WHERE stock_index = "; 
		Statement st;
		try {

			st = con.createStatement();

			if (st.execute(sqlinter + "'" + stock_index + "'" )) {

				return st.getResultSet(); // field 넘김 
			}

		} catch (SQLException e) {

			// TODO Auto-generated catch block
			e.printStackTrace();

		}
		return null;
	}

	public static ResultSet findStockCompanyFromStockIndex(Connection con, String stock_company) { //stock_index로 company 이름 구하기 

		String sqlinter = "SELECT stock_company FROM Stockinsight.Stock WHERE stock_index ="; 
		Statement st;
		try {

			st = con.createStatement();

			if (st.execute(sqlinter + "'" + stock_company + "'" )) {
				//interest_index = - 1;
				return st.getResultSet(); // field 넘김 
			}

		} catch (SQLException e) {

			// TODO Auto-generated catch block
			e.printStackTrace();

		}
		return null;
	}

	public static ResultSet findStockBeforeFromStockIndex(Connection con, String stock_index) { //stock_index로 company 이름 구하기 

		String sqlinter = "SELECT stock_before FROM Stockinsight.Stock WHERE stock_index ="; 
		Statement st;
		try {

			st = con.createStatement();

			if (st.execute(sqlinter + "'" + stock_index + "'" )) {
				//interest_index = - 1;
				return st.getResultSet(); // field 넘김 
			}

		} catch (SQLException e) {

			// TODO Auto-generated catch block
			e.printStackTrace();

		}
		return null;
	}


	public static ResultSet findStockFutureFromStockIndex(Connection con, String stock_index) { //stock_index로 company 이름 구하기 

		String sqlinter = "SELECT stock_future FROM Stockinsight.Stock WHERE stock_index ="; 
		Statement st;
		try {

			st = con.createStatement();

			if (st.execute(sqlinter + "'" + stock_index + "'" )) {
				//interest_index = - 1;
				return st.getResultSet(); // field 넘김 
			}

		} catch (SQLException e) {

			// TODO Auto-generated catch block
			e.printStackTrace();

		}
		return null;
	}


	public static ResultSet divStockIndexByField(Connection con, String stock_field) { // interest table 안의 stock_index를 분야별로 나누기 

		String sqlstock = "SELECT Stock_stock_index FROM Stockinsight.Interest WHERE Stock_stock_index IN (SELECT stock_index FROM Stockinsight.Stock WHERE Stockinsight.Stock.stock_field ="; //분야에 해당하는 stock_index 가져오기 

		Statement st;

		try {

			st = con.createStatement();

			if (st.execute(sqlstock + "'" + stock_field + "')" )) {

				return st.getResultSet(); 
			}

		} catch (SQLException e) {

			// TODO Auto-generated catch block
			e.printStackTrace();

		}
		return null;
	}

	public static Boolean interestCheck(Connection con, String user_index ,String stock_index) { // interest table 안의 stock_index를 분야별로 나누기 

		String sql = "SELECT interest_index FROM Stockinsight.Interest WHERE User_user_index ="; 
		String sqltwo = "AND Stock_stock_index =";

		Statement st;

		try {

			st = con.createStatement(); 

			if (st.execute(sql + "'" + user_index + "'" + sqltwo + "'" + stock_index + "'" )) {
				if(st.getResultSet().next()) {
					return true;
				}
				else {
					return false;
				}

			}

		} catch (SQLException e) {

			// TODO Auto-generated catch block
			e.printStackTrace();

		}
		return null;
	}
	public static ResultSet find_stockcode(Connection con, String stock_index) { //stock_index로 company 이름 구하기 
		System.out.println("DBU: " + stock_index);
		String sql = "SELECT stock_code FROM Stockinsight.Stock WHERE stock_index ="; 
		Statement st;
		try {

			st = con.createStatement();

			if (st.execute(sql + "'" + stock_index + "'" )) {
				//interest_index = - 1;
				return st.getResultSet(); // field 넘김 
			}

		} catch (SQLException e) {

			// TODO Auto-generated catch block
			e.printStackTrace();

		}
		return null;
	} 
	public static String[] getRanking(Connection con, String standard) {
		// standard 기준으로 상위 10개 반환

		String[] companyName = new String[10];

		//SELECT stock_company FROM Stock ORDER BY stock_volume DESC, stock_before
		String sql = "SELECT stock_company FROM Stock ORDER BY "+ standard +" DESC, stock_before"; 
		Statement st;
		try {
			st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);
			int count = 0;
			while (rs.next()) {
				// 상위 10개만 companyName에 추가
				if(count<=9) {
					companyName[count] = rs.getString(1);
					count++;
				}
				else {
					break;
				}
			}
			// 상위 10개 반환
			return companyName;
		} catch (SQLException e) {

			// TODO Auto-generated catch block
			e.printStackTrace();

		}
		// 실패 시
		return null;
	}
	public static ResultSet getStockCode(Connection con, String companyName) {
		String sql = "SELECT stock_code FROM Stockinsight.Stock WHERE stock_company ="; 
		Statement st;
		try {

			st = con.createStatement();

			if (st.execute(sql + "'" + companyName + "'" )) {
				//interest_index = - 1;
				return st.getResultSet(); // field 넘김 
			}

		} catch (SQLException e) {

			// TODO Auto-generated catch block
			e.printStackTrace();

		}
		return null;
	}

	public static boolean removeInfo(Connection con, String user_index) {
		String sql = "DELETE u, ui, uq from "
				+ "Stockinsight.User as u left join Stockinsight.Interest as ui on u.user_index = ui.User_user_index "
				+ "left join Stockinsight.Question as uq on u.user_index = uq.User_user_index "
				+ "where u.user_index="; 
		Statement st;
		try {

			st = con.createStatement();

			if (st.execute(sql + "'" + user_index + "'" )) {
				//interest_index = - 1;
				return true; // field 넘김 
			}
		} catch (SQLException e) {

			// TODO Auto-generated catch block
			e.printStackTrace();	
		}
		return true;
	}


	public static ResultSet sortVolumeLow(Connection con, String search) {
		String sqlinter = "SELECT stock_company FROM Stockinsight.Stock WHERE stock_company LIKE '%";
		String sql = "ORDER BY stock_volume ASC";
		Statement st;
		try {

			st = con.createStatement();

			if (st.execute(sqlinter +  search + "%'" + sql)) { // 해당 문자열이 들어간 결과 반환 
				return st.getResultSet();
			}


		} catch (SQLException e) {

			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;

		}
		return null;
	}

	public static ResultSet sortVolumeHigh(Connection con, String search) {
		String sqlinter = "SELECT stock_company FROM Stockinsight.Stock WHERE stock_company LIKE '%";
		String sql = "ORDER BY stock_volume DESC";
		Statement st;
		try {

			st = con.createStatement();

			if (st.execute(sqlinter +  search + "%'" + sql)) { // 해당 문자열이 들어간 결과 반환 
				return st.getResultSet();
			}


		} catch (SQLException e) {

			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;

		}
		return null;
	}

	public static ResultSet sortBeforeLow(Connection con, String search) {
		String sqlinter = "SELECT stock_company FROM Stockinsight.Stock WHERE stock_company LIKE '%";
		String sql = "ORDER BY stock_before ASC";
		Statement st;
		try {

			st = con.createStatement();

			if (st.execute(sqlinter +  search + "%'" + sql)) { // 해당 문자열이 들어간 결과 반환 
				return st.getResultSet();
			}
		} catch (SQLException e) {

			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;

		}
		return null;
	}

	public static ResultSet sortBeforeHigh(Connection con, String search) {
		String sqlinter = "SELECT stock_company FROM Stockinsight.Stock WHERE stock_company LIKE '%";
		String sql = "ORDER BY stock_before DESC";
		Statement st;
		try {

			st = con.createStatement();

			if (st.execute(sqlinter +  search + "%'" + sql)) { // 해당 문자열이 들어간 결과 반환 
				return st.getResultSet();
			}


		} catch (SQLException e) {

			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;

		}
		return null;
	}

	public static ResultSet sortName(Connection con, String search) {
		String sqlinter = "SELECT stock_company FROM Stockinsight.Stock WHERE stock_company LIKE '%";
		String sql = "ORDER BY stock_company ASC";
		Statement st;
		try {

			st = con.createStatement();

			if (st.execute(sqlinter +  search + "%'" + sql)) { // 해당 문자열이 들어간 결과 반환 
				return st.getResultSet();
			}

		} catch (SQLException e) {

			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;

		}
		return null;
	}


	// 질문글 인덱스로 답변글 인덱스 받아오기
	public static String find_answerIndex(Connection con, String que_index) {
		String sql = "SELECT Answer_answer_index from Question where ques_index=" + que_index;

		Statement st;
		try {
			st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);
			
			while (rs.next()) {
				String ans_index = rs.getString(1);				
				return ans_index;

			}
		}catch(SQLException e) {
			e.printStackTrace();
		}

		return null;		
	}

	// 답변글 인덱스로 답변글 삭제하기
	public static void removeAnswerIndex(Connection con, String answer_index) throws SQLException {
		PreparedStatement pstmt = null;
		try {
			con.setAutoCommit(false);
			pstmt = con.prepareStatement("delete from Answer where answer_index=?");
			pstmt.setString(1, answer_index);
			pstmt.executeUpdate();
			
			con.commit();
			con.setAutoCommit(true);

		} catch (SQLException e) {
			e.printStackTrace();

		} finally {
			if (pstmt != null) {pstmt.close();}

		}
	}

	// 질문글 인덱스로 질문글 삭제하기
	public static void removeQuestionIndex(Connection con, String que_index) throws SQLException {
		PreparedStatement pstmt = null;
		try {
			con.setAutoCommit(false);
			pstmt = con.prepareStatement("delete from Question where ques_index=?");
			pstmt.setString(1, que_index);
			pstmt.executeUpdate();

			con.commit();
			con.setAutoCommit(true);

		} catch (SQLException e) {
			e.printStackTrace();

		} finally {
			if (pstmt != null) {pstmt.close();}

		}
	}
	
	// 질문글 index로 질문글 table의 답변 null로 수정
	public static void updateAnswerIndex(Connection con, String que_index) {
		PreparedStatement pstmt = null;
		try {
			con.setAutoCommit(false);
			pstmt = con.prepareStatement("UPDATE Question SET Answer_answer_index=null WHERE ques_index=?");
			pstmt.setString(1, que_index);
			pstmt.executeUpdate();

			con.commit();
			con.setAutoCommit(true);

		} catch (SQLException e) {
			e.printStackTrace();

		} finally {
			if (pstmt != null) {try {
				pstmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}}

		}

	// 상위권 5개 받아오는 코드
	public static String[][] getTopFive(Connection conn) {
		String[][] result = new String[5][3];
		String sql = "select stock_code, stock_company, ((stock_future)-(stock_before))/stock_before*100 from Stock order by ((stock_future)-(stock_before))/stock_before*100 DESC";
		Statement st;
		try {
			st = conn.createStatement();
			ResultSet rs = st.executeQuery(sql);
			int count = 0;
			while (rs.next()) {
				// 상위 5개만 companyName에 추가
				if(count<=4) {
					result[count][0] = rs.getString(1);
					result[count][1] = rs.getString(2);
					result[count][2] = rs.getString(3);
					count++;
				}
				else {
					break;
				}
			}
			// 상위 10개 반환
			return result;
		} catch (SQLException e) {

			// TODO Auto-generated catch block
			e.printStackTrace();

		}
		// 실패 시
		return null;
	}
	
	// 하위권 5개 받아오는 코드
	public static String[][] getDownFive(Connection conn) {
		String[][] result = new String[5][3];
		String sql = "select stock_code, stock_company, ((stock_future)-(stock_before))/stock_before*100 from Stock order by ((stock_future)-(stock_before))/stock_before*100";
		Statement st;
		try {
			st = conn.createStatement();
			ResultSet rs = st.executeQuery(sql);
			int count = 0;
			while (rs.next()) {
				// 하위 5개만 companyName에 추가
				if(rs.getString(3).equals("-100.0000"))
					continue;
				if (count <= 4) {
					result[count][0] = rs.getString(1);
					result[count][1] = rs.getString(2);
					result[count][2] = rs.getString(3);
					count++;
				} else {
					break;
				}
			}
			// 상위 10개 반환
			return result;
		} catch (SQLException e) {

			// TODO Auto-generated catch block
			e.printStackTrace();

		}
		// 실패 시
		return null;

	}
}

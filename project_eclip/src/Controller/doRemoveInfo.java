package Controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mysql.cj.protocol.Resultset;

import model.DBUtil;

/**
 * Servlet implementation class doRemoveInfo
 */
@WebServlet("/removeId")
public class doRemoveInfo extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public doRemoveInfo() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out= response.getWriter(); 

		ServletContext sc = getServletContext();
		Connection conn = (Connection) sc.getAttribute("DBconnection");

		HttpSession session = request.getSession(false);
		String user_index = (String)session.getAttribute("INDEX"); //index를 얻는다 세션을 통해

		//Interest에서 user_index 행 삭제
		Boolean check = false;
		try {
			check = DBUtil.rmInterest(conn, user_index);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		Boolean check4 = false;
		//Question에서 user_index를 통해 answer_index얻기
		if(check == true) {
			ResultSet rs = DBUtil.infoAnswerIndex(conn, user_index); 
			if(rs!=null) {
				try
				{
					if(rs.next()) {
						String answer_index = rs.getString(1);
						System.out.println("ansIdx : " + answer_index);
						//Answer의 answer_index 행 삭제
						check4 = DBUtil.rmAnswer(conn, answer_index);
					}
				}catch (Exception e) {
						e.printStackTrace();
					}
				}
			}

			//Question에서 user_index 행 삭제
			Boolean check2 = false;
			try {
				check2 = DBUtil.rmQuestion(conn, user_index);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			//User에서 user_index 행 삭제
			Boolean check3 = false;
			try {
				check3 = DBUtil.rmUser(conn, user_index);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("check값들 : " + check + " " + check2 + " " + check3 + " " + check4);
			
			if(check == true && check2 == true && check3 == true && check4==true) {
				session.invalidate();
				out.println("<script>alert('정말로 탈퇴하실 생각이신가요? 😭'); location.href='/Stock_Insigh/';</script>");
			}
		}

		/**
		 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
		 */
		protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			// TODO Auto-generated method stub
			doGet(request, response);
		}

	}

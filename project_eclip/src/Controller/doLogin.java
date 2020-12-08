package Controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.DBUtil;

/**
 * Servlet implementation class doLogin
 */
@WebServlet("/doLogin")
public class doLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public doLogin() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");

		HttpSession session = request.getSession();          

		String user_id = request.getParameter("user_id");
		String user_pwd = request.getParameter("user_pwd");

		ServletContext sc = getServletContext();
		Connection conn = (Connection) sc.getAttribute("DBconnection");

		//index 찾아서 스트링 만들고 세션할당
		ResultSet index_set = DBUtil.findIndex(conn, user_id);

		String user_index = "null";
		if(index_set != null) {
			try
			{
				if(index_set.next()) { // existing user
					user_index = index_set.getString(1);
				}
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}   
		}
		session.setAttribute("ID",user_id);   //id ���� �Ҵ� 
		session.setAttribute("INDEX",user_index); //index ���� �Ҵ� 
		

		//      String str = (String) session.getAttribute("ID");
		//      String str2 = (String) session.getAttribute("INDEX");

		//IDã�� ����
		ResultSet rs = DBUtil.findUser(conn, user_id);  //id가 있는지
		ResultSet okay = DBUtil.checkMypageinner(conn, user_id); //name 받아오기
		
		String check = null; // check 변수는 비밀번호가 틀리든가 가입자가 아닐때의 jsp를 위함
		boolean go_okay = false; //go_okay 변수는 로그인 성공과 이름받아서 환영합니다를 뿌려주기 위한 jsp 체크 변수
		
		if(rs != null) {
			try
			{
				if(rs.next()) { // existing user
					String checkpw = rs.getString(1);
					if(checkpw.equals(user_pwd)){
						// valid user and passwd
						request.setAttribute("id",user_id);	
						go_okay= true;
					}
					else
					{
						check = "0";
					}
				}
				else { 
					check = "1";
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} 
		}
		
		if(okay != null){		
     		try
			{
				if(okay.next()) { // existing user
					String name = okay.getString(2);  
					request.setAttribute("name",name);	
					session.setAttribute("NAME",name);
					go_okay= true;
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} 
     	}
		if(check != null) {
			request.setAttribute("check",check);
			RequestDispatcher view = request.getRequestDispatcher("noLogin.jsp");
			view.forward(request, response);
		}
		if(go_okay == true) {
			RequestDispatcher view = request.getRequestDispatcher("okayLogin.jsp");
			view.forward(request, response);
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
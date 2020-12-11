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
import javax.servlet.jsp.PageContext;

import com.mysql.cj.protocol.Resultset;

import model.DBUtil;

/**
 * Servlet implementation class doJoin
 */
@WebServlet("/doJoin")
public class doJoin extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public doJoin() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		String name = request.getParameter("user_name");
		String id = request.getParameter("user_id");	
		String email = request.getParameter("user_email");	
		String pwd = request.getParameter("user_pwd");

		ServletContext sc = getServletContext();
		Connection conn = (Connection) sc.getAttribute("DBconnection");

		boolean overlap = DBUtil.checkID(conn, id);
		
		if (overlap == true) {
			request.setAttribute("name", name);
			request.setAttribute("id",id);
			request.setAttribute("pwd",pwd);
			request.setAttribute("email",email);
			RequestDispatcher view = request.getRequestDispatcher("checkid.jsp");
			view.forward(request, response);
		}
		else { //가입이 된 유저
			try {
				DBUtil.addMember(conn, name, id, email, pwd);
				response.sendRedirect("login.jsp"); //로그인으로
			} catch (SQLException e) {
				e.printStackTrace();
			} //가입
           
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
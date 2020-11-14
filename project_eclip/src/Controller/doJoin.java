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

		String name = request.getParameter("user_name");
		String id = request.getParameter("user_email");	
		String pwd = request.getParameter("user_pwd");

		ServletContext sc = getServletContext();
		Connection conn = (Connection) sc.getAttribute("DBconnection");

		ResultSet rs = DBUtil.findUser(conn, id);

		PrintWriter out = response.getWriter();

		if (rs != null) {
			try{
				if(rs.next()){ // existing user
						response.sendRedirect("main.html");
				}
				else { 				
					DBUtil.addMember(conn, name, id, pwd); //db
					response.sendRedirect("login.html"); //�씠�룞 濡쒓렇�씤�쑝濡�
				}
			}catch (SQLException e) {e.printStackTrace();}
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

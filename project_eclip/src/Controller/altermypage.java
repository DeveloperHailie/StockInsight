package Controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.DBUtil;

/**
 * Servlet implementation class altermypage
 */
@WebServlet("/altermypage")
public class altermypage extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public altermypage() {
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
	    
	    HttpSession session = request.getSession();
	    String user_id = (String)session.getAttribute("ID");//�뜝�룞�삕�뜝�떎�슱�삕�뜝�룞�삕 �뜝�룞�삕�뜝�떛�벝�삕 �뜝�뙣�븘�슱�삕
	    String new_email = (String)request.getParameter("user_email");
		String new_passwd = (String)request.getParameter("user_pwd");
		

	    ServletContext sc = getServletContext();
	    Connection conn = (Connection) sc.getAttribute("DBconnection");
	    ResultSet rs = DBUtil.checkMypageedit(conn, user_id, new_email, new_passwd); //id �뜮袁㏉꺍
		response.sendRedirect("mypage_inner.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

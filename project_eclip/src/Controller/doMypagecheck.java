package Controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.DBUtil;

/**
 * Servlet implementation class doMypagecheck
 */
@WebServlet("/doMypagecheck")
public class doMypagecheck extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public doMypagecheck() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	      request.setCharacterEncoding("UTF-8");
	      
	      HttpSession session = request.getSession();
	      String user_id = (String)session.getAttribute("ID");//세션에서 아이디 받아옴
	      
	      //String user_id = request.getParameter("user_email");
	      String user_pwd = request.getParameter("user_pwd");// 비밀번호 받아옴

	      ServletContext sc = getServletContext();
	      Connection conn = (Connection) sc.getAttribute("DBconnection");

	      ResultSet rs = DBUtil.findUser(conn, user_id); //id 鍮꾧탳

	      PrintWriter out = response.getWriter();
	   
	      if(rs != null) {
	         try
	         {
	            if(rs.next()) { // existing user
	               String checkpw = rs.getString(4);
	               System.out.println(checkpw);
	               if(checkpw.equals(user_pwd)){
	            	   out.println("회원체크 완료 : 성공");
	                  // valid user and passwd
	                  //response.sendRedirect("main.html");
	               }
	               else
	               {
	                  // wrong passwd
	            	   out.println("회원체크 완료 : 실패");
	               }
	            }
	            else { // invalid user
	               out.println("세션실패");
	            }
	         } catch (SQLException e) {
	        	 out.println("예외 실패");
	            e.printStackTrace();
	         } 
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

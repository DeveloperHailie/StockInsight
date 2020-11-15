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
	      String user_id = (String)session.getAttribute("ID");//���ǿ��� ���̵� �޾ƿ�
	      
	      //String user_id = request.getParameter("user_email");
	      String user_pwd = request.getParameter("user_pwd");// ��й�ȣ �޾ƿ�

	      ServletContext sc = getServletContext();
	      Connection conn = (Connection) sc.getAttribute("DBconnection");

	      ResultSet rs = DBUtil.findUser(conn, user_id); //id 비교

	      PrintWriter out = response.getWriter();
	   
	      if(rs != null) {
	         try
	         {
	            if(rs.next()) { // existing user
	               String checkpw = rs.getString(4);
	               System.out.println(checkpw);
	               if(checkpw.equals(user_pwd)){
	            	   out.println("ȸ��üũ �Ϸ� : ����");
	                  // valid user and passwd
	                  //response.sendRedirect("main.html");
	               }
	               else
	               {
	                  // wrong passwd
	            	   out.println("ȸ��üũ �Ϸ� : ����");
	               }
	            }
	            else { // invalid user
	               out.println("���ǽ���");
	            }
	         } catch (SQLException e) {
	        	 out.println("���� ����");
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

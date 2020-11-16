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

import model.DBUtil;

/**
 * Servlet implementation class getmypage
 */
@WebServlet("/getmypage")
public class getmypage extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public getmypage() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		 PrintWriter out = response.getWriter();
	     
		HttpSession session = request.getSession();
	    String user_id = (String)session.getAttribute("ID");
		

	      ServletContext sc = getServletContext();
	      Connection conn = (Connection) sc.getAttribute("DBconnection");

	      ResultSet rs = DBUtil.checkMypageinner(conn, user_id); //id �뜮袁㏉꺍
	      
		//RequestDispatcher view = request.getRequestDispatcher("answer.jsp");
		//view.forward(request, response);if(rs != null) {
        try
        {
       	 //System.out.println(rs);
           if(rs.next()) { // existing user
              String checkpw = rs.getString(1);
              System.out.println(checkpw);
             
                 // valid user and passwd
                 //response.sendRedirect("main.html");
              } 
              
          
           else { // invalid user
              out.println("not invalid");
           }
        } catch (SQLException e) { 
       	 out.println("just Fail Fail Fail....");
           e.printStackTrace();
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

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
 * Servlet implementation class WriteQuestion
 */
@WebServlet("/writeQuestion")
public class WriteQuestion extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public WriteQuestion() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
			request.setCharacterEncoding("UTF-8");
	      
	      String title = request.getParameter("title");
	      String content = request.getParameter("content");

	      ServletContext sc = getServletContext();
	      Connection conn = (Connection) sc.getAttribute("DBconnection");
	      
	      // 로그인 되어 있는 상태인지 체크
	      HttpSession session = request.getSession(false);
	      if(session==null) {
	    	// 로그인 안 되어있으면 login.html 반환
	    	  response.sendRedirect("login.html");
	    	  
	      }else {
	    	  // 로그인 되어 있으면 (1)question db update, (2)작성된 글 보는 페이지 반환
	    	  // 사용자 idx 가져오기
	    	  //int uidx = session.getAttribute(idx);
	    	  int uidx=1;
	    	  if(DBUtil.addQuestion(conn, uidx, title, content)) {
	    		  
	    	  }
	      }
	      
	      
	     
	      
	      
	      
	      //ResultSet rs = DBUtil.findUser(conn, user_id); //id 비교

	      PrintWriter out = response.getWriter();
	   
	      /*if(true) {
	         try
	         {
	           
	         } catch (SQLException e) {
	            e.printStackTrace();
	         } 
	      } */
		
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

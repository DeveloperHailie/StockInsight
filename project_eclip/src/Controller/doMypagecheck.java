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
        response.setContentType("text/html; charset=UTF-8");
        PrintWriter out = response.getWriter();
        
        
        HttpSession session = request.getSession();
        String user_id = (String)session.getAttribute("ID");
        String user_pwd = request.getParameter("user_pwd");//  뜝 룞 삕艅섇뜝 떕占   뜝 뙣 븘 슱 삕
        
        
        ServletContext sc = getServletContext();
        Connection conn = (Connection) sc.getAttribute("DBconnection");

        ResultSet rs = DBUtil.checkMypageinner(conn, user_id); //id  뜮袁㏉꺍
     
        if(rs != null) {
           try
           {
              if(rs.next()) { // existing user

            	  String name = rs.getString(2);
                  String user_mid = rs.getString(3);
                  String email = rs.getString(4);
                  String user_passwd = rs.getString(5);
                  request.setAttribute("name", name);
                  request.setAttribute("user_mid", user_mid);
                  request.setAttribute("email", email);
                 request.setAttribute("user_passwd", user_passwd); //사용자의 pw
                 
                 //System.out.println(checkpw);
                 if(!user_passwd.equals(user_pwd)){
                	 out.println("<script>alert(\"올바른 비밀번호가 아닙니다. 다시 입력해주세요.\"); location.href='mypage.jsp';</script>");
                     out.flush();
                 }
                 
                 RequestDispatcher view = request.getRequestDispatcher("mypage_inner.jsp");
				  view.forward(request, response);
                 
                 
              }
           } catch (SQLException e) { 
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

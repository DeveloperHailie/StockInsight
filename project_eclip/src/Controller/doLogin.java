package Controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
      
      String user_id = request.getParameter("user_email");
      String user_pwd = request.getParameter("user_pwd");

      ServletContext sc = getServletContext();
      Connection conn = (Connection) sc.getAttribute("DBconnection");

      ResultSet rs = DBUtil.findUser(conn, user_id); //id 비교

      PrintWriter out = response.getWriter();
   
      if(rs != null) {
         try
         {
            if(rs.next()) { // existing user
               String checkpw = rs.getString(1);
               System.out.println(checkpw);
               if(checkpw.equals(user_pwd)){
                  // valid user and passwd
                  response.sendRedirect("main.html");
               }
               else
               {
                  // wrong passwd
                  out.println("Wrong Passwd!!");
               }
            }
            else { // invalid user
               out.println("Invalid User Name!!");
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
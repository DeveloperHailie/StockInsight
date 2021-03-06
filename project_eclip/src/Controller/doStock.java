package Controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.DBUtil;

/**
 * Servlet implementation class doInterest
 */
@WebServlet("/doStock")
public class doStock extends HttpServlet {
   private static final long serialVersionUID = 1L;

   /**
    * @see HttpServlet#HttpServlet()
    */
   public doStock() {
      super();
      // TODO Auto-generated constructor stub
   }

   /**
    * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
    *      response)
    */
   protected void doGet(HttpServletRequest request, HttpServletResponse response)
         throws ServletException, IOException {
      // TODO Auto-generated method stub
      request.setCharacterEncoding("UTF-8");
      response.setContentType("text/html; charset=UTF-8");

      ServletContext sc = getServletContext();
      Connection conn = (Connection) sc.getAttribute("DBconnection");
      PrintWriter out = response.getWriter();

      try {
         
         Statement st = conn.createStatement();
         ResultSet rs = DBUtil.findFieldSet(conn);
         ArrayList<String> searchFieldList = new ArrayList<String>();

         if (rs != null) {
            while (rs.next()) {
               String searchField = rs.getString(1);
               searchFieldList.add(searchField);
               request.setAttribute("searchFieldList", searchFieldList);
            }
            RequestDispatcher view = sc.getRequestDispatcher("/stock.jsp");
            view.forward(request, response);
         }
         
         
      } catch (SQLException e1) {
         // TODO Auto-generated catch block
         e1.printStackTrace();
      }
   }

   /**
    * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
    *      response)
    */
   protected void doPost(HttpServletRequest request, HttpServletResponse response)
         throws ServletException, IOException {
      // TODO Auto-generated method stub
      request.setCharacterEncoding("UTF-8");
      doGet(request, response);
   }

}
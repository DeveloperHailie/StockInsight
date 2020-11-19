package Controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.DBUtil;
/**
 * Servlet implementation class doFindIndex
 */
@WebServlet("/doFindIndex")
public class doFindIndex extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public doFindIndex() {
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

	         ServletContext sc = getServletContext();
	         Connection conn = (Connection) sc.getAttribute("DBconnection");
	         PrintWriter out = response.getWriter();
	         
	         try {
	             
	             Statement st = conn.createStatement();
	             String user_id = request.getParameter("user_id");
	             request.setAttribute("user_id", user_id);
	             ResultSet rs = DBUtil.findUserIndex(conn, user_id); // user_id로 user_index 찾기 
	             
	             String selectCompany = request.getParameter("selectCompany");
	             request.setAttribute("selectCompany", selectCompany);
	             ResultSet rsc = DBUtil.findStockIndex(conn, selectCompany); // stock_company로 stock_index 찾기 
	             
	             String selectField = request.getParameter("selectField");
	             request.setAttribute("selectField", selectField);
	             
	       
	             if (rs != null) {
	                while (rs.next()) {
	                   String findUserIndex = rs.getString(1);
	                   request.setAttribute("user_index", findUserIndex);
	                }
	             }
	             
	             if(rsc != null) {
	               while(rsc.next()) {
	                  String findStockIndex = rsc.getString(1);
	                  request.setAttribute("stock_index", findStockIndex);
	               }
	            }
	               
	             //RequestDispatcher view = sc.getRequestDispatcher("/search_final.jsp");
	             RequestDispatcher view = sc.getRequestDispatcher("/doInsertInterest");
	             view.forward(request, response);
	             
	          } catch (SQLException e1) {
	             // TODO Auto-generated catch block
	             e1.printStackTrace();
	          }

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		 request.setCharacterEncoding("UTF-8");
	     doGet(request, response);

	}

}

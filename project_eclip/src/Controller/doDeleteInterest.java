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
 * Servlet implementation class doDeleteInterest
 */
@WebServlet("/doDeleteInterest")
public class doDeleteInterest extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public doDeleteInterest() {
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
   
            String del_user_index = request.getParameter("user_index");
            request.setAttribute("user_index", del_user_index);
            
            String del_stock_index = request.getParameter("stock_index"); 
            request.setAttribute("stock_index", del_stock_index);   
            
            String selectField = request.getParameter("selectField");
            request.setAttribute("selectField", selectField);
            
            String selectCompany = request.getParameter("selectCompany");
            request.setAttribute("selectCompany", selectCompany);
            
            
            ResultSet rs_stock_future = null;
            rs_stock_future = DBUtil.findStockFutureFromStockIndex(conn, del_stock_index);
            
            if (rs_stock_future != null) {
 					while (rs_stock_future.next()) {
 						String findStockFuture_FromStockIndex = rs_stock_future.getString(1); // 분야 얻어오기
 						request.setAttribute("selectFuture", findStockFuture_FromStockIndex); // stock_index 리스트에 해당하는 예측가격 가져오기
 					}

            }
            
            
            ResultSet rs = DBUtil.deleteInterest(conn, del_user_index, del_stock_index);
        
            if (rs != null) {
               while (rs.next()) {
            	   String deleteInterestRow = rs.getString(1);
                  request.setAttribute("deleteInterestRow", deleteInterestRow);
               }
            }
         
            Boolean interCheck = DBUtil.interestCheck(conn, del_user_index, del_stock_index);
            request.setAttribute("interCheck", interCheck); 
            System.out.print("----두딜리트의----------interCheck : " + interCheck);
            
            RequestDispatcher view = sc.getRequestDispatcher("/search_final.jsp");
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

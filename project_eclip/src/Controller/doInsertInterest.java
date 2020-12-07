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

import com.mysql.cj.protocol.Resultset;

import model.DBUtil;

/**
 * Servlet implementation class doInsertInterest
 */
@WebServlet("/doInsertInterest")
public class doInsertInterest extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public doInsertInterest() {
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
        Boolean isExist = false;
        
        try {
            
            Statement st = conn.createStatement();            
            
            String user_index = request.getParameter("user_index");
            request.setAttribute("user_index", user_index);
            
            String stock_index = request.getParameter("stock_index"); 
            request.setAttribute("stock_index", stock_index);   
            
            String selectField = request.getParameter("selectField");
            request.setAttribute("selectField", selectField);
            
            String selectCompany = request.getParameter("selectCompany");
            request.setAttribute("selectCompany", selectCompany);            
              
            //DBUtil.insertInterest(conn, user_index, stock_index);// user_id로 user_index 찾기 
          
            Boolean interCheck = DBUtil.interestCheck(conn, user_index, stock_index); //관심 종목에 있는지 없는지 체크 
                        
            if(interCheck == true) { //관심종목에 있으면 
            	interCheck = true;
            }else { // 관심종목에 없으면 
            	DBUtil.insertInterest(conn, user_index, stock_index);// user_id로 user_index 찾기 
            	interCheck = true;
            }
            ResultSet rs_stock_index = DBUtil.getStockCode(conn, selectCompany);
            if(rs_stock_index !=null) {
    			try
    			{
    				if(rs_stock_index.next()) { //결과가 1개인 경우 
    					String st_cd = rs_stock_index.getString(1);
    					System.out.println("야2" + st_cd);
    					request.setAttribute("stock_code", st_cd);
    				}
    			} catch (SQLException e) {
    				e.printStackTrace();
    			} 
    		}       
            String st_stock_index = null;
    		//stock_index 얻기   
    		ResultSet rs_st_index = DBUtil.findStockIndex(conn,selectCompany);
    		 if (rs_st_index != null) {
    	        	try {
    					while (rs_st_index.next()) {
    						st_stock_index = rs_st_index.getString(1); // 분야 얻어오기
    					}
    				} catch (SQLException e) {
    					// TODO Auto-generated catch block
    					e.printStackTrace();
    				}
    	         }
    		//future price 가격   
            ResultSet rs_stock_future = DBUtil.findStockFutureFromStockIndex(conn, st_stock_index);
            if (rs_stock_future != null) {
            	try {
    				while (rs_stock_future.next()) {
    					String findStockFuture_FromStockIndex = rs_stock_future.getString(1); // 분야 얻어오기
    					request.setAttribute("selectFuture", findStockFuture_FromStockIndex); // stock_index 리스트에 해당하는 예측가격 가져오기
    				}
    			} catch (SQLException e) {
    				// TODO Auto-generated catch block
    				e.printStackTrace();
    			}
             }
            request.setAttribute("interCheck", interCheck); 
            
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

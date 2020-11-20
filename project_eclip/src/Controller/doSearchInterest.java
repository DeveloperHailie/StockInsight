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
import javax.servlet.http.HttpSession;

import model.DBUtil;

/**
 * Servlet implementation class doSerarchInterest
 */
@WebServlet("/doSearchInterest") // user가 설정한 관심종목 가져오기 
public class doSearchInterest extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public doSearchInterest() {
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
	     
	      HttpSession session = request.getSession();
	      String user_id = (String) session.getAttribute("ID");
	      String user_index = null;
	      
	      try {
	         
	         Statement st = conn.createStatement();
	         
	         ResultSet rs_user_id = DBUtil.findUserIndex(conn, user_id);
	         
	         // user_index 정보 추출  
	         if (rs_user_id != null) {
		            while (rs_user_id.next()) {
		               user_index = rs_user_id.getString(1);
		               request.setAttribute("user_index", user_index); // user_id에 해당하는 user_index 가져오기 
		            }
		         }

	         //user_index가 고른 stock_index들의 리스트 
	         ResultSet rs_stock_index = DBUtil.findStockIndexFromUser(conn, user_index);
	         ArrayList<String> findStockIndexFromUser = new ArrayList<String>(); //stock_index list 저장할 리스트 

	         if (rs_stock_index != null) {
	            while (rs_stock_index.next()) {
	               String findStockIndex_FromUser = rs_stock_index.getString(1);
	               findStockIndexFromUser.add(findStockIndex_FromUser);
	               request.setAttribute("findStockIndexFromUser", findStockIndexFromUser); // user_index에 해당하는 stock_index list
	            }
	            
	         }
	         
	         
	         //stock_index를 field 이름으로 매핑 --> 관심종목에 넣은 분야 출력 
	         ResultSet  rs_stock_field = null; // 중복 제거한 stock_field들의 모음 
	         ArrayList<String> findStockIndex_FromUser = (ArrayList<String>) request.getAttribute("findStockIndexFromUser"); // user가 넣은 종목 index 리스트 
	         ArrayList<String> findStockFieldFromStockIndex = new ArrayList<String>(); //인덱스로부터 분야 추출 
	         
	         
	         if (findStockIndex_FromUser != null) {
                 for (int i = 0; i < findStockIndex_FromUser.size(); i++) { //stock_index list에서 stock_index 하나씩 가져와서 field 구하기  
                	 rs_stock_field = DBUtil.findStockFieldFromStockIndex(conn, findStockIndex_FromUser.get(i)); // stock_index --> stock_field
                	 
                	 if (rs_stock_field != null) {
        		            while (rs_stock_field.next()) {
        		               String findStockField_FromStockIndex = rs_stock_field.getString(1); //분야 얻어오기 
        		               System.out.print(" \n findStockField: "  + findStockField_FromStockIndex );
        		                
        		               if(findStockFieldFromStockIndex.contains(findStockField_FromStockIndex)) {
        		            	   System.out.print(" 배열에 넣지않을 분야:" + findStockField_FromStockIndex);
        		               }
        		               else {
        		            	    findStockFieldFromStockIndex.add(findStockField_FromStockIndex);
           		            		System.out.print(" 배열에 넣을 분야:" + findStockField_FromStockIndex);
        		               }         
                		        request.setAttribute("findStockFieldFromStockIndex", findStockFieldFromStockIndex); // stock_index 리스트에 해당하는 분야 가져오기 
        		            }
        		            
        		        }
                 }
	         }

	        
	         RequestDispatcher view = sc.getRequestDispatcher("/interest.jsp");
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

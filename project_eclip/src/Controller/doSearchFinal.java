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
 * Servlet implementation class doSearchFinal
 */
@WebServlet("/doSearchFinal")
public class doSearchFinal extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public doSearchFinal() {
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
		String stock_index = null;

		int value = 0;
		try {	
			Statement st = conn.createStatement();
			
			
			 // user_index 정보 추출 
			ResultSet rs_user_id = DBUtil.findUserIndex(conn, user_id);
	         
	         
	         if (rs_user_id != null) {
		            while (rs_user_id.next()) {
		               user_index = rs_user_id.getString(1);
		               request.setAttribute("user_index", user_index); // user_id에 해당하는 user_index 가져오기 
		            }
		         }
	         
	         System.out.print("doSearchFinal_user_index : " + user_index + "\n");	         

			
			String selectCompany = request.getParameter("selectCompany");
			request.setAttribute("selectCompany", selectCompany); //선택한 회사 넘기기 
			
			//selectCompany 으로부터 stock_index 정보추출 
			ResultSet rs_stock_index = DBUtil.findStockIndex(conn, selectCompany);
			
			if (rs_stock_index != null) {
	            while (rs_stock_index.next()) {
	               stock_index = rs_stock_index.getString(1);
	               request.setAttribute("stock_index", stock_index); // user_id에 해당하는 user_index 가져오기 
	            }
	         }           
           //future price 가격 
           
           ResultSet rs_stock_future = null;
           rs_stock_future = DBUtil.findStockFutureFromStockIndex(conn, stock_index);
           
           if (rs_stock_future != null) {
					while (rs_stock_future.next()) {
						String findStockFuture_FromStockIndex = rs_stock_future.getString(1); // 분야 얻어오기
						request.setAttribute("selectFuture", findStockFuture_FromStockIndex); // stock_index 리스트에 해당하는 예측가격 가져오기
					}

           }           
           
           //interest_index 있는지 없는지 확인
           Boolean interCheck = DBUtil.interestCheck(conn, user_index, stock_index);
           request.setAttribute("interCheck", interCheck); 
           //System.out.print("doSearchFinal_interCheck : " + interCheck + "\n");
			
			ResultSet rs = DBUtil.findFieldSelectCompany(conn, selectCompany); // 검색결과 비교 
			
			if(rs != null) {
				if(rs.next()) { //결과가 1개인 경우 
					String selectField = rs.getString(1); // 분야 
					request.setAttribute("selectField", selectField); // 분야 넘기기 
					value = 2;
				}
			}
			
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		ResultSet code = DBUtil.find_stockcode(conn, stock_index);
		String st_cd = null;
		if(code !=null) {
			try
			{
				if(code.next()) { //결과가 1개인 경우 
					st_cd = code.getString(1);
					request.setAttribute("stock_code", st_cd);
					value = 2;
				}
			} catch (SQLException e) {
				e.printStackTrace(); } 
		}
		if(value !=0) {
			RequestDispatcher view =  request.getRequestDispatcher("/search_final.jsp");
			view.forward(request, response);
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

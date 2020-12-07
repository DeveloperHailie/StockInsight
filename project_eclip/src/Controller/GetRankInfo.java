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
 * Servlet implementation class GetRankInfo
 */
@WebServlet("/getRankInfo")
public class GetRankInfo extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetRankInfo() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		ServletContext sc = getServletContext();
		Connection conn = (Connection) sc.getAttribute("DBconnection");

		String companyName = request.getParameter("companyName");
		// companyName으로 다음 세값 알아내기
		// selectField
		// selectCampany
		// stock_code
		ResultSet fieldRs = DBUtil.findFieldSelectCompany(conn, companyName);
		String selectField = null;
		try {
			if(fieldRs.next()) {
				selectField = fieldRs.getString(1);
				request.setAttribute("selectField", selectField);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		request.setAttribute("selectCompany", companyName);
		// 회사이름으로 회사 종목 코드 찾아내기
		ResultSet comCode = DBUtil.getStockCode(conn, companyName);
		String stock_code = null;
		if(comCode != null) {
		try {
			if(comCode.next()) {
				stock_code = comCode.getString(1);
				request.setAttribute("stock_code", stock_code);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}}
		
		HttpSession session = request.getSession(false);
		String check_index = (String)session.getAttribute("INDEX");
		System.out.println(session);
		if (session == null || check_index==null) {
			System.out.println("세션없음");
			// 혹시 모르니까 한번 더 무효화
			session.invalidate();
		} else {
			// 세션 있으면
			request.setAttribute("user_index",check_index); // 받아온 user_index 
			
			// 회사명으로 회사인덱스 받아오는 함수 사용
			//selectCompany 으로부터 stock_index 정보추출 
			ResultSet rs_stock_index = DBUtil.findStockIndex(conn, companyName);
			String stock_index = null;
			if (rs_stock_index != null) {
	            try {
					while (rs_stock_index.next()) {
					   stock_index = rs_stock_index.getString(1);
					   request.setAttribute("stock_index", stock_index); // user_id에 해당하는 user_index 가져오기 
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	         }
			
			// 사용자아이디/인덱스 , 회사명/회사인덱스로 들어가있는지 아닌지 확인하는 함수 사용
			// interest_index 있는지 없는지 확인
			Boolean interCheck = DBUtil.interestCheck(conn, check_index, stock_index);
			request.setAttribute("interCheck", interCheck);
		}
		String st_stock_index = null;
		//stock_index 얻기   
		ResultSet rs_stock_index = DBUtil.findStockIndex(conn,companyName);
		 if (rs_stock_index != null) {
	        	try {
					while (rs_stock_index.next()) {
						st_stock_index = rs_stock_index.getString(1); // 분야 얻어오기
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
		RequestDispatcher view = sc.getRequestDispatcher("/search_final.jsp");
		view.forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

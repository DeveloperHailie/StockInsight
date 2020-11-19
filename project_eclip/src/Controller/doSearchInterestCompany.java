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
 * Servlet implementation class doSearchInterestCompany
 */
@WebServlet("/doSearchInterestCompany")
public class doSearchInterestCompany extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public doSearchInterestCompany() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		/// TODO Auto-generated method stub
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

			if (rs_user_id != null) {
				while (rs_user_id.next()) {
					user_index = rs_user_id.getString(1);
					request.setAttribute("user_index", user_index); // user_id에 해당하는 user_index 가져오기
				}
			}

			ResultSet rs = DBUtil.findStockIndexFromUser(conn, user_index);
			ArrayList<String> findStockIndexFromUser = new ArrayList<String>(); // stock_index list 저장할 리스트

			if (rs != null) {
				while (rs.next()) {
					String findStockIndex_FromUser = rs.getString(1);
					findStockIndexFromUser.add(findStockIndex_FromUser);
					request.setAttribute("findStockIndexFromUser", findStockIndexFromUser); // user_index에 해당하는
																							// stock_index list
				}

			}

			String stock_index = request.getParameter("stock_index");
			System.out.print("dodododod : " + stock_index);
			request.setAttribute("stock_index", stock_index);

			ResultSet rsc = DBUtil.findStockCompanyFromStockIndex(conn, stock_index); // 선택 분야에 따른 회사들 찾기

			ArrayList<String> searchCompanyList = new ArrayList<String>();

			if (rsc != null) {
				while (rsc.next()) {
					String searchCompany = rsc.getString(1);
					searchCompanyList.add(searchCompany);
					request.setAttribute("searchCompanyList", searchCompanyList);
				}
			}

			RequestDispatcher view = sc.getRequestDispatcher("/interest_company.jsp");
			view.forward(request, response);

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

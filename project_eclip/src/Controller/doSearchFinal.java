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
		
		try {	
			Statement st = conn.createStatement();
			String selectCompany = request.getParameter("selectCompany");
			request.setAttribute("selectCompany", selectCompany); //선택한 회사 넘기기 
			
			ResultSet rs = DBUtil.findFieldSelectCompany(conn, selectCompany); // 검색결과 비교 
			
			if(rs != null) {
				if(rs.next()) { //결과가 1개인 경우 
					String selectField = rs.getString(1); // 분야 
					request.setAttribute("selectField", selectField); // 분야 넘기기 
				}
				RequestDispatcher view =  sc.getRequestDispatcher("/search_final.jsp");
				view.forward(request, response);
			}
			
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

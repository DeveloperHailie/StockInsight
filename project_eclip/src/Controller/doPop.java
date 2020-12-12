package Controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

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
 * Servlet implementation class doPop
 */
@WebServlet("/doPop")
public class doPop extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public doPop() {
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
	      PrintWriter out = response.getWriter();
	      
	      HttpSession session = request.getSession();
	      String session_user_id = (String) session.getAttribute("ID");
	      
	      ServletContext sc = getServletContext();
	      Connection conn = (Connection) sc.getAttribute("DBconnection");
	      ResultSet rs = DBUtil.popupplus(conn, session_user_id);
	      ResultSet rs2 = DBUtil.popupminus(conn, session_user_id);

      try {
			if(rs.next()) {
				try {
					request.setAttribute("increase", rs.getString(1));
					request.setAttribute("increase_gap", rs.getString(2));
				} catch (SQLException e) {
					// TODO Auto-generated catch block
						e.printStackTrace();
				}
			}
			else {
				request.setAttribute("increase", "적합한 종목이 없습니다");
			}
			if(rs2.next()) {
				try {
					request.setAttribute("decrease", rs2.getString(1));
					request.setAttribute("decrease_gap", rs2.getString(2));
				} catch (SQLException e) {
					// TODO Auto-generated catch block
						e.printStackTrace();
				}
			}
			else {
				request.setAttribute("decrease", "적합한 종목이 없습니다");
			}
			RequestDispatcher view = request.getRequestDispatcher("popup.jsp");
			view.forward(request, response);
			
			}
      
      catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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

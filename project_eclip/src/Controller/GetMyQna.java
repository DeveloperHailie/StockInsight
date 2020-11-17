package Controller;

import java.io.IOException;
import java.sql.Connection;
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
import model.QnAList;

/**
 * Servlet implementation class GetMyQna
 */
@WebServlet("/getMyQna")
public class GetMyQna extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetMyQna() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		
		HttpSession session = request.getSession(false);
		String check_index = (String) session.getAttribute("INDEX");
		
		if (session == null || check_index == null) {
			// 세션 없음
			// 혹시 모르니 무효화 한번 더
			session.invalidate();
			response.sendRedirect("login.jsp");
		} else {
			// 세션있음
			ServletContext sc = getServletContext();
			Connection conn = (Connection) sc.getAttribute("DBconnection");
			ArrayList<QnAList> postList = DBUtil.getmyPostList(conn, check_index);
			
			request.setAttribute("postList",postList);
			
			RequestDispatcher view = request.getRequestDispatcher("my_qna_list.jsp");
			view.forward(request, response);
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

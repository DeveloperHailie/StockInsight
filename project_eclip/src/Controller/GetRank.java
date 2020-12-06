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

import model.DBUtil;

/**
 * Servlet implementation class GetRank
 */
@WebServlet("/getRank")
public class GetRank extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetRank() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		ServletContext sc = getServletContext();
		Connection conn = (Connection) sc.getAttribute("DBconnection");
		
		// 뭐를 기준으로 ranking 매기는지
		String ranking = request.getParameter("table"); // volume
		
		// DBUtil에서 stock table에서 volume 상위 10개 회사이름 가져오기
		String[] rank = DBUtil.getRanking(conn, ranking);
		// attribute로 설정
		request.setAttribute("rank", rank);
		// rankData.jsp로 forward
		RequestDispatcher view = request.getRequestDispatcher("rankData.jsp");
		
		
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

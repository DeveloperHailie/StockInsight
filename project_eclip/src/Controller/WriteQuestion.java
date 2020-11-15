package Controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.Calendar;

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
 * Servlet implementation class WriteQuestion
 */
@WebServlet("/writeQuestion")
public class WriteQuestion extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public WriteQuestion() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
		String date = sdf.format(cal.getTime());

		ServletContext sc = getServletContext();
		Connection conn = (Connection) sc.getAttribute("DBconnection");

		// 로그인 되어 있는 상태인지 체크
		HttpSession session = request.getSession(false);
		if (session == null) {
			// 로그인 안 되어있으면 login.html 반환
			response.sendRedirect("login.html");

		} else {
			// 로그인 되어 있으면 (1)question db update, (2)작성된 글 보는 페이지 반환
			// 사용자 idx와 name 가져오기
			int uidx =  Integer.parseInt((String)session.getAttribute("INDEX"));
			String name = (String)session.getAttribute("ID");
			
			int number = DBUtil.addQuestion(conn, uidx, title, content, date);
			if (number!=-1) {
				// name, title, content, date 를 question_content.jsp로 전송
				request.setAttribute("name", name);
				request.setAttribute("title",title);
				request.setAttribute("content",content);
				request.setAttribute("date",date);
				request.setAttribute("number",Integer.toString(number));
				RequestDispatcher view = request.getRequestDispatcher("question_content.jsp");
				view.forward(request, response);
			}else {
				// 다시 문의하기 페이지로
				response.sendRedirect("question.html");
			}
		}

		// ResultSet rs = DBUtil.findUser(conn, user_id); //id 비교

		PrintWriter out = response.getWriter();

		/*
		 * if(true) { try {
		 * 
		 * } catch (SQLException e) { e.printStackTrace(); } }
		 */
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

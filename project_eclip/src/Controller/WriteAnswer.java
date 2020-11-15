package Controller;

import java.io.IOException;
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
 * Servlet implementation class WriteAnswer
 */
@WebServlet("/writeAnswer")
public class WriteAnswer extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public WriteAnswer() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		String question_number = request.getParameter("number");
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
			//로그인 되어 있고 관계자이면 
			//(1)answer db insert
			//(2)question db update (answer 인덱스)
			//(3)작성된 글 보는 페이지 반환 _ 얘는 답변하기 버튼 아예없음 _ jsp
			
			//(1)
			int answer_number = DBUtil.addAnswer(conn, title, content, date);
			
			if (answer_number!=-1) {
				// 제대로 삽입되었으면 (2)
				Boolean checkUpdateQuestion = DBUtil.updateQuestion(conn, Integer.parseInt(question_number), answer_number);
				if(checkUpdateQuestion) {
					// 제대로 update되었으면
					//(3)작성된 글 보는 페이지 반환 _ 얘는 답변하기 버튼 아예없음 _ jsp
					// name, title, content, date 를 question_content.jsp로 전송
					request.setAttribute("name", "관리자");
					request.setAttribute("title",title);
					request.setAttribute("content",content);
					request.setAttribute("date",date);
					request.setAttribute("admin",false);
					request.setAttribute("number", "[re]"+question_number);
					RequestDispatcher view = request.getRequestDispatcher("question_content.jsp");
					view.forward(request, response);
				}
			}else {
				// 다시 문의하기 페이지로
				response.sendRedirect("question.html");
			}
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

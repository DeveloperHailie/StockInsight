package Controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.DBUtil;

/**
 * Servlet implementation class DeleteAnswer
 */
@WebServlet("/deleteAnswer")
public class DeleteAnswer extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteAnswer() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		// [re]숫자 => 숫자
		String number = request.getParameter("number");
		String[] numberAnswer = number.split("]");
		number = numberAnswer[1];
		
		ServletContext sc = getServletContext();
		Connection conn = (Connection) sc.getAttribute("DBconnection");
		
		// number(=question index)로 answer_index 받아오기 (없으면 null) find_answerIndex
		String answer_index = DBUtil.find_answerIndex(conn, number);
		System.out.println("delete index? :" + answer_index);
		
		// question table에서 답변글 null로 수정
		DBUtil.updateAnswerIndex(conn, number);
		
		// answer table에서 row 삭제 
		if(answer_index != null) {
			try {
				DBUtil.removeAnswerIndex(conn, answer_index);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		// question_list.jsp로 
		response.sendRedirect("/Stock_Insigh/postList?pageIndex=1");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

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
 * Servlet implementation class ReadPost
 */
@WebServlet("/readPost")
public class ReadPost extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReadPost() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// type, index
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		ServletContext sc = getServletContext();
		Connection conn = (Connection) sc.getAttribute("DBconnection");
		
		// question, answer
		String type = request.getParameter("type");
		// 질문글의 index
		String index = request.getParameter("index");
		System.out.println("ReadPost:"+type+"/"+index);
		QnAList post;
		if(type.equals("question")) {
			post = DBUtil.getQuestionPost(conn, index);
			System.out.println("final:"+post);
		}else {
			post = DBUtil.getAnswerPostUseQuesIdx(conn,index);
			System.out.println("final:"+post);
		}
		
		
		
		String name = post.getWriter();
		String title = post.getTitle();
		String content = post.getContent();
		String date = post.getDate();
		String number = post.getIndex();
		
		// 세션 확인
		// 로그인 되어 있는 상태인지 체크
		Boolean admin;
		HttpSession session = request.getSession(false);
		if (session == null) {
			admin = false;
		}else {
			if(type.equals("answer")) {
				admin=false;
			}else {
				int uidx =  Integer.parseInt((String)session.getAttribute("INDEX"));
				admin = DBUtil.checkAdmin(conn,uidx);
			}	
		}
		
		request.setAttribute("name", name);
		request.setAttribute("title", title);
		request.setAttribute("content", content);
		request.setAttribute("date", date);
		request.setAttribute("number", number);
		request.setAttribute("admin", admin);
		RequestDispatcher view = request.getRequestDispatcher("question_content.jsp");
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

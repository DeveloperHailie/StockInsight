package Controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

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
	      String user_id = (String)session.getAttribute("ID");//�뜝�룞�삕�뜝�떎�슱�삕�뜝�룞�삕 �뜝�룞�삕�뜝�떛�벝�삕 �뜝�뙣�븘�슱�삕
	      
	      ServletContext sc = getServletContext();
	      Connection conn = (Connection) sc.getAttribute("DBconnection");

	      ResultSet rs = DBUtil.popupplus(conn, user_id); //id �뜮袁㏉꺍
	      System.out.println(rs);
	      
	      try {
			while(rs.next()) { //rs.next()를 통해 다음행을 내려갈 수 있으면 true를 반환하고, 커서를 한칸 내린다. 다음행이 없으면 false를 반환한다. 
				  try {
					System.out.println(rs.getString(1) + "\t" + rs.getString(2));
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} 
			  }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
	      
	      out.println("<html><body>");
	      out.println("<script type=\"text/javascript\">");
	      out.println("var popUrl=\"popup.jsp\"");
	      out.println("var popOption = \"width=400, height=400, resizable=no, scrollbars=no, status=no;\"");
	      out.println("window.open(popUrl,\"\", popOption)");
	      //out.println("var popwin = window.open(\"popup.jsp\")");
	      //out.println("setTimeout(function(){ popwin.close(); window.location.href='pageB.jsp';},5000)");
	      out.println("</script>");
	      out.println("</body></html>");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

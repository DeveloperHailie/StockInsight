package Controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.DBUtil;

/**
 * Servlet implementation class doInsertInterest
 */
@WebServlet("/doInsertInterest")
public class doInsertInterest extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public doInsertInterest() {
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
        Boolean isExist = false;
        
        try {
            
            Statement st = conn.createStatement();
            
            
            String user_index = request.getParameter("user_index");
            request.setAttribute("user_index", user_index);
            
            String stock_index = request.getParameter("stock_index"); 
            request.setAttribute("stock_index", stock_index);   
            
            String selectField = request.getParameter("selectField");
            request.setAttribute("selectField", selectField);
            
            String selectCompany = request.getParameter("selectCompany");
            request.setAttribute("selectCompany", selectCompany);
            
              
            //DBUtil.insertInterest(conn, user_index, stock_index);// user_id로 user_index 찾기 
          
            Boolean interCheck = DBUtil.interestCheck(conn, user_index, stock_index); //관심 종목에 있는지 없는지 체크 
            
            
            if(interCheck == true) { //관심종목에 있으면 
            	interCheck = true;
            }else { // 관심종목에 없으면 
            	DBUtil.insertInterest(conn, user_index, stock_index);// user_id로 user_index 찾기 
            	interCheck = true;
            }
            
            request.setAttribute("interCheck", interCheck); 
         
            RequestDispatcher view = sc.getRequestDispatcher("/search_final.jsp");
            view.forward(request, response);
            
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

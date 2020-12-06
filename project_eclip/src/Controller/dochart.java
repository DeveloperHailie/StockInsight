package Controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class dochart
 */
@WebServlet("/csv")
public class dochart extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public dochart() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		 // TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
	      String name = request.getParameter("code"); 
	      System.out.println(name);
	      // name db filename
	      
	      // code.csv파일 열어서 그 안의 내용 저장
	      ArrayList<String[]> file = csv.chart_csv.addToList("C:\\Users\\User\\Desktop\\Git\\StockInsight\\project_eclip\\"+name+".csv");
	      for(int i=0;i<file.size();i++) {
	         System.out.println(file.get(i)[0]+file.get(i)[1]+file.get(i)[2]+file.get(i)[3]+file.get(i)[4]);
	      }
	      request.setAttribute("file", file); 
	      RequestDispatcher view = request.getRequestDispatcher("data.jsp");
	      view.forward(request, response);
	   }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		doGet(request, response);
	}

}

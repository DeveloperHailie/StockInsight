package Controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.DBUtil;

/**
 * Servlet implementation class doSearch
 */
@WebServlet("/doSearch")
public class doSearch extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public doSearch() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");

		int count = 0;

		ServletContext sc = getServletContext();
		Connection conn = (Connection) sc.getAttribute("DBconnection");
		PrintWriter out = response.getWriter();

		try {
			Statement st = conn.createStatement();
			// String search = request.getParameter("search");
			String search;
			if (request.getParameter("search") == "") {
				search = null;
			} else {
				search = request.getParameter("search");
				
			}
			
			
			String btnUpDown = null; // 내림순 오름순 버튼 받아오기 
			
			if (request.getParameter("btnUpDown") == "") {
				btnUpDown = null;
			} else {
				btnUpDown = request.getParameter("btnUpDown");
				
			}
			

			if (search != null) {// 비어있지 않은 입력일 경우
				request.setAttribute("search", search);

			  if(btnUpDown == null) { //검색결과에서 오름순 버튼 누르지 않고 검색해서 들어간 경우 
				
				ResultSet rs = DBUtil.sortVolumeHigh(conn, search); // 검색결과 비교
				ArrayList<String> searchList = new ArrayList<String>();

				if (rs != null) {// 받아온 DB리스트가 null이 아닐 경우
					System.out.print("not null");
					if (rs.next()) {
						rs = DBUtil.sortVolumeHigh(conn, search);
						while (rs.next()) {
							count++;
							String searchResult = rs.getString(1);
							btnUpDown = "btnVolumeHigh";
							searchList.add(searchResult);
							request.setAttribute("click", btnUpDown);
							request.setAttribute("searchResult", searchList);
							request.setAttribute("count", count);
						}
						//RequestDispatcher view = sc.getRequestDispatcher("/search_after.jsp");
						//view.forward(request, response);
					}
					else {
						
						 String searchResult = "검색 결과가 없습니다."; searchList.add(searchResult);
						 request.setAttribute("searchResult", searchList);
						 request.setAttribute("count", count); System.out.println("searchResult: " +searchList); 
						
						 
						System.out.print("empty");
					}
					
					//RequestDispatcher view = sc.getRequestDispatcher("/search_after.jsp");
					//view.forward(request, response);
				} 
			  } else { // 검색 결과에서 오름순 버튼 눌러서 나오는 결과 
				//String btnVolumeLow, btnVolumeHigh ,btnBeforeLow , btnBeforeHigh, btnName;
				  //System.out.print("버튼을 눌렀어요!!!");
				 // btnUpDown = request.getParameter("btnUpDown");
				  //System.out.print("랄랄라" + btnUpDown);
				  
				  if(btnUpDown.equals("btnVolumeLow")) {
				
					  ResultSet rs = DBUtil.findSearchCompany(conn, search);	
		               ArrayList<String> searchList = new ArrayList<String>();
		               
		               if (rs != null) {// 받아온 DB리스트가 null이 아닐 경우
		            	   System.out.print("not null");
		            	   
		                     while (rs.next()) {
		                    	count++;
		                        String searchResult = rs.getString(1);
		                        
		                        searchList.add(searchResult);
		                        request.setAttribute("click", btnUpDown);
		                        request.setAttribute("searchResult", searchList);
		                        request.setAttribute("count", count);
		                     }
		               }               

					  
				  }else if (btnUpDown.equals("btnVolumeHigh")) {
					  ResultSet rs = DBUtil.sortVolumeHigh(conn, search);
		               ArrayList<String> searchList = new ArrayList<String>();
		               
		               if (rs != null) {// 받아온 DB리스트가 null이 아닐 경우
		                     while (rs.next()) {
		                        count++;
		                        String searchResult = rs.getString(1);
		                        
		                        searchList.add(searchResult);
		                        request.setAttribute("click", btnUpDown);
		                        request.setAttribute("searchResult", searchList);
		                        request.setAttribute("count", count);
		                     }
		               }      

					  
				  }else if (btnUpDown.equals("btnBeforeLow")) {
					  ResultSet rs = DBUtil.sortBeforeLow(conn, search);
		               ArrayList<String> searchList = new ArrayList<String>();
		               
		               if (rs != null) {// 받아온 DB리스트가 null이 아닐 경우
		                     while (rs.next()) {
		                        count++;
		                        String searchResult = rs.getString(1);
		                       
		                        searchList.add(searchResult);
		                        request.setAttribute("click", btnUpDown);
		                        request.setAttribute("searchResult", searchList);
		                        request.setAttribute("count", count);
		                     }
		               }      

					  
				  }else if (btnUpDown.equals("btnBeforeHigh")) {
					  
					  ResultSet rs = DBUtil.sortBeforeHigh(conn, search);
		               ArrayList<String> searchList = new ArrayList<String>();
		               
		               if (rs != null) {// 받아온 DB리스트가 null이 아닐 경우
		                     while (rs.next()) {
		                        count++;
		                        String searchResult = rs.getString(1);
		                       
		                        searchList.add(searchResult);
		                        request.setAttribute("click", btnUpDown);
		                        request.setAttribute("searchResult", searchList);
		                        request.setAttribute("count", count);
		                     }
		               }      

				  }else if (btnUpDown.equals("btnName")) { //btnName
					  
					  ResultSet rs = DBUtil.sortName(conn, search);
		               ArrayList<String> searchList = new ArrayList<String>();
		               
		               if (rs != null) {// 받아온 DB리스트가 null이 아닐 경우
		                     while (rs.next()) {
		                        count++;
		                        String searchResult = rs.getString(1);
		                       
		                        searchList.add(searchResult);
		                        request.setAttribute("click", btnUpDown);
		                        request.setAttribute("searchResult", searchList);
		                        request.setAttribute("count", count);
		                     }
		               }      

				  } 
			  }
			  
			  RequestDispatcher view = sc.getRequestDispatcher("/search_after.jsp");
			  view.forward(request, response);
			  
			  
			} else { // 빈입력일 경우 경고창
				out.println("<script>alert('검색어를 입력해주세요.'); history.go(-1);</script>");
				out.close();
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");

		doGet(request, response);

	}
}

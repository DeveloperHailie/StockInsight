<%@ page import="model.DBUtil"%>
<%@ page import="java.sql.Connection"%>
<%@ page import="java.sql.ResultSet"%>
<%@ page import="java.sql.SQLException"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html" charset="UTF-8">
<title>Stock Insight</title>
<link rel="stylesheet" type="text/css" href="style.css" />
<script type="text/javascript">
	var n = 0;
	var imgs = new Array("title_ver3_1.png", "title_ver3_2.png");
	function rotate() {
		document.images.slide.src = imgs[n];
		(n == (imgs.length - 1)) ? n = 0 : n++;
		setTimeout("rotate()", 800);
	}
</script>
</head>

<body onload='rotate()'>

	<div class="front">
		<div class="logo">
			<a href="main.jsp"><img src="logo.png"
				style="width: 336px; height: 148px; float: left;"></a>
		</div>

		<%
			if (session.getAttribute("ID") != null) {
			// 세션 존재
		%>
		<ul id="okaylogin_ul">
			<li id="okaylogin_li">
				<%
					String name = (String) session.getAttribute("NAME");
				out.println("<b>" + name + "</b> 님 환영합니다. </br>");
				%>
			</li>
			</br>
			<li id="okaylogin_li"><a href="javascript:popupOpen();" id="red"><b>알림확인</b></a></li>&nbsp;&nbsp;&nbsp;
			<li id="okaylogin_li"><a href="/Stock_Insigh/doLogout"> 로그아웃
			</a></li> &nbsp; &nbsp;
			<li id="okaylogin_li"><a href="main.jsp">메인화면</a></li> &nbsp;
			&nbsp;
			<li id="okaylogin_li"><a href="stock.jsp">종목조회</a></li> &nbsp; &nbsp;
			<li id="okaylogin_li"><a href="interest.jsp">관심종목</a></li> &nbsp;
			&nbsp;
			<li id="okaylogin_li"><a id="yellow" href="mypage.jsp">마이페이지</a></li>
			&nbsp; &nbsp;
			<li id="okaylogin_li"><a
				href="/Stock_Insigh/postList?pageIndex=1">문의하기</a></li>

			</br>
		</ul>
		<%
			} else {
		// 세션존재하지 않음
		%>
		<ul>
			<li><a href="login.jsp">로그인</a></li> &nbsp; &nbsp;
			<li><a href="main.jsp">메인화면</a></li> &nbsp; &nbsp;
			<li><a href="stock.jsp">종목조회</a></li> &nbsp; &nbsp;
			<li><a href="interest.jsp">관심종목</a></li> &nbsp; &nbsp;
			<li><a id="yellow" href="login.jsp">마이페이지</a></li> &nbsp; &nbsp;
			<li><a href="/Stock_Insigh/postList?pageIndex=1">문의하기</a></li>
		</ul>

		<%
			}
		%>
	</div>
	<div>
		<header>
			<center>
				<img src="title_ver3_1.png" id="slide"
					style="width: 1200; height: auto"; >
			</center>
		</header>
	</div>
	<section id="content">

		<nav>
			마이페이지<br /> <img class="bar" src="bar.jpg"
				style="padding-top: 20px; width: 121px; height: 10px; float: center;">
		</nav>

		<div class="menu_content">

			<br> <br> <br> 
			<center>
            <form id="mypage_inner" method="POST" action="getMyQna">
               <button type=\"button\" class=\"btn_collection\" style="position: relative;left: 20%;top: 15px;padding: 7px 17px;border-radius: 3px;font-family:nanum;font-weight: bold;font-size: 13px;background-color:#fff;color:#E8CE48;outline-color:#E8CE48;outline-style: default;">내가 쓴 문의글</button>
            </form>
               <form id="mypage_inner" method="POST" action="getmypage">
               <br> <br> <br><table class="mypage_inner">


                  <%
                     String session_user_id = (String) session.getAttribute("ID");
                  //out.println(session.getAttribute("ID"));

                  ServletContext sc = getServletContext();
                  Connection conn = (Connection) sc.getAttribute("DBconnection");

                  ResultSet rs = DBUtil.checkMypageinner(conn, session_user_id); //id  뜮袁㏉꺍


                  try {
                     if (rs.next()) { // existing user
                        String name = rs.getString(2);
                        String user_id = rs.getString(3);
                        String email = rs.getString(4);
                        String user_pwd = rs.getString(5);

                        out.println(
                        "<tr><td align=right><font size=\"5\"><b>이름</b></font></td><td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td><td><font size=\"4\">"
                              + name + "</font></td></tr>");
                        out.println(
                        "<tr><td align=right><font size=\"5\"><b>아이디</b></font></td><td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td><td><font size=\"4\">"
                              + user_id + "</font></td></tr>");
                        out.println(
                        "<tr><td align=right><font size=\"5\"><b>이메일</b></font></td><td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td><td><font size=\"4\">"
                              + email + "</font></td></tr>");
                        out.println(
                        "<tr><td align=right><font size=\"5\"><b>패스워드</b></font></td><td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td><td><font size=\"4\">"
                              + user_pwd + "</font></td></tr>");
                        
                  %>
               </table>
               <br>
               <br>
               <br>
               <%
                        
                  }

               else { // invalid user
               out.println("not invalid");
               }
               } catch (SQLException e) {
               out.println("just Fail Fail Fail....");
               e.printStackTrace();
               }
               %>
               </table>
               <br>
               <br>
               <br>



               <button type="button" class="btn_question_submit"
                  onClick="location.href='mypage_edit.jsp' ">수정하기</button>
            </form>
      </div>

   </section>

   <footer>
      <p> © 2020 본 홈페이지의 모든 권리는 베짱이찬가에 귀속됩니다.</p>
   </footer>

</body>
</html>
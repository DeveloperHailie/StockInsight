<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html" charset="UTF-8">
<title>Stock Insight</title>
<link rel="stylesheet" type="text/css" href="style.css" />
<script type="text/javascript">
			var n = 0;
			var imgs = new Array("title_ver3_1.png","title_ver3_2.png");
			function rotate() {
				document.images.slide.src = imgs[n];
				(n == (imgs.length - 1)) ? n=0 : n++; setTimeout("rotate()",800);
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
         if(session.getAttribute("ID")!=null){
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
			<li id="okaylogin_li"><a href="main.jsp">메인화면</a></li>
			&nbsp; &nbsp;
			<li id="okaylogin_li"><a href="stock.jsp">종목조회</a></li> &nbsp;
			&nbsp;
			<li id="okaylogin_li"><a href="/Stock_Insigh/doSearchInterest" id="yellow">관심종목</a></li> &nbsp;
			&nbsp;
			<li id="okaylogin_li"><a href="mypage.jsp">마이페이지</a></li> &nbsp;
			&nbsp;
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
			<li><a id="yellow" href="/Stock_Insigh/doSearchInterest">관심종목</a></li> &nbsp; &nbsp;
			<li><a href="login.jsp">마이페이지</a></li> &nbsp; &nbsp;
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
         <%
            ArrayList<String> findStockFieldList = (ArrayList<String>) request.getAttribute("findStockFieldFromStockIndex");
          %>
         관심종목<br /> <img class="bar" src="bar.jpg"
            style="padding-top: 20px; width: 121px; height: 10px; float: center;">
      </nav>
<div class="menu_content">
      <%
       if(session.getAttribute("ID")!=null){ //세션 존재 
        
    	%>
      <h1 style="float: left; margin-left: 660px;">분야</h1>
      <h1 style="float: right; margin-right: 670px;">회사</h1>
      <div class="interest">
         <ul
            style="border: 5px solid #4568DC; width: 350px; height: 500px; float: left; margin-left: 500px;">
            <form method="POST" action="doSearchInterestCompany">
               <%
                  if (findStockFieldList != null) {
                  for (int i = 0; i < findStockFieldList.size(); i++) {
               %>
               <%
               out.print("<button type = \"submit\" class=\"interbtn\" name= \"stock_field\" style=\"height: 40px; width: 340px;\" value = \"");
               out.print(findStockFieldList.get(i));
               out.print("\">");
               out.print("<li>");
               out.print("<a>");
               out.print(findStockFieldList.get(i));
               out.print("</a>");
               out.print("</li>");
               out.print("</button>");

               %>
               <%
                  }
               }
               %>
            </form>
         </ul>
      </div>
      <div class="interest">
         <ul
            style="border: 5px solid #B06AB3; width: 350px; height: 500px; margin-right: 500px;">

         </ul>
      </div>
      
      <% } 
       else { // 세션존재하지 않음
       %>
                  <h1> 로그인 후 이용해주세요. </h1>
    <% } %>
</div>

   </section>

   <footer>
      <p>© 2020 본 홈페이지의 모든 권리는 베짱이찬가에 귀속됩니다.</p>
   </footer>

</body>
</html>
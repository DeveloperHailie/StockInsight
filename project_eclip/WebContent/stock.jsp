<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html" charset="UTF-8">
        <title>Stock Insight</title>
        <link rel="stylesheet" type="text/css" href="style.css"/>
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
            <div class="logo"><a href="main.html"><img src="logo.png" style="width:336px; height:148px; float:left;"></a></div>
            <ul>
                <li><a href="login.jsp">로그인</a></li>
                &nbsp; &nbsp; 
                <li><a href="main.jsp">메인화면</a></li>
                &nbsp; &nbsp; 
                <li><a id="yellow" href="/Stock_Insigh/doStock">종목조회</a></li>
                &nbsp; &nbsp;  
                <li><a href="interest.html">관심종목</a></li>
                &nbsp;  &nbsp; 
                <li><a href="discuss.html">토론하기</a></li>
                &nbsp;   &nbsp; 
                <li><a href="mypage.jsp">마이페이지</a></li>
                &nbsp;  &nbsp; 
                <li><a  href="qna.jsp">문의하기</a></li>
            </ul>
          </div>
          <div>
          <header>
          <center>
            <img src="title_ver3_1.png" id="slide" style="width: 1200; height: auto"; >
            </center>
          </header>
           </div>
          <section id="content" >

		<nav>
			<%
				ArrayList<String> fieldList = (ArrayList<String>) request.getAttribute("searchFieldList");
			%>
			종목조회<br /> <img class="bar" src="bar.jpg" style="padding-top: 20px; width: 121px; height: 10px; float: center;">
		</nav>


		<div class="menu_content style=">
			<div class="search" style="float: center;">
				<form method="POST" action="doSearch">
					<input type="text" name="search" placeholder="검색어 입력">
					<button type="submit" onclick="location.href='search_after.jsp'">검색</button>
				</form>
			</div>
			<h1 style= "float:left; margin-left: 660px; "> 분야 </h1>
			<h1 style= "float:right; margin-right: 670px;"> 회사 </h1>
			<div class= "interest" >
				<ul style="border: 5px solid #4568DC; width: 350px; height: 500px; float: left; margin-left: 500px;">
						<%
							if (fieldList != null) {
							for (int i = 0; i < fieldList.size(); i++) {
						%>
						<form method="POST" action="doStockCompany">
						<%
						out.print("<button type = \"submit\" class=\"interbtn\" name= \"field\" style=\"height: 40px; width: 340px;\" value = \"");
						out.print(fieldList.get(i));
						out.print("\">");
						out.print("<li>");
						out.print("<a>");
						out.print(fieldList.get(i));
						out.print("</a>");
						out.print("</li>");
						out.print("</button>");	
						%>
						</form>
						<%
							}
						}
						%>
				</ul>
			</div>
			<div class="interest">
				<ul style="border: 5px solid #B06AB3; width: 350px; height: 500px; margin-right: 500px;">

				</ul>
			</div>
		</div>

	</section>

	<footer>
		<p>© 2020 본 홈페이지의 모든 권리는 베짱이찬가에 귀속됩니다.</p>
	</footer>

</body>
</html>

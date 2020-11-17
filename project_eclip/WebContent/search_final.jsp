<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
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
			<a href="main.html"><img src="logo.png"
				style="width: 336px; height: 148px; float: left;"></a>
		</div>
		<ul>
			<li><a href="login.html">로그인</a></li> &nbsp; &nbsp;
			<li><a href="main.html">메인화면</a></li> &nbsp; &nbsp;
			<li><a id="yellow" href="stock.jsp">종목조회</a></li> &nbsp; &nbsp;
			<li><a href="interest.html">관심종목</a></li> &nbsp; &nbsp;
			<li><a href="discuss.html">토론하기</a></li> &nbsp; &nbsp;
			<li><a href="mypage.html">마이페이지</a></li> &nbsp; &nbsp;
			<li><a href="qna.html">문의하기</a></li>
		</ul>
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
				String selectField = (String) request.getAttribute("selectField"); //분야
				String selectCompany = (String) request.getAttribute("selectCompany"); //회사
			%>
			종목조회<br /> <img class="bar" src="bar.jpg"
				style="padding-top: 20px; width: 121px; height: 10px; float: center;">
		</nav>
		<div class="menu_content">
			<div class="resultKind">
				<p>${selectField}</p>
				<p>:${selectCompany}</p>
				
				<div class="resultBtn">
				<form method="POST" action="doInsertInterest">
					<%
					out.print("<button type=\"submit\" class=\"interbtn\" name=\"insertInterest\" value1=\"");
					out.print(selectField);
					out.print("\" value2=\"");
					out.print(selectCompany);
					out.print("\">");
					out.print("<img src=\" heart.png\" height=\"50px\" width=\"50px\"");
					out.print("</button>");
					 %>
				</form>
				</div>
			</div>
			
			<div class ="resultPrice">
			
			</div>
			
			<div class ="resultChart">
			
			</div>
		</div>

	</section>

	<footer>
		<p>© 2020 본 홈페이지의 모든 권리는 베짱이찬가에 귀속됩니다.</p>
	</footer>

</body>
</html>
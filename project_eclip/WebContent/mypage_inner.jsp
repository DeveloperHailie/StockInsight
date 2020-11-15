<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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
			<a href="main.html"><img src="logo.png"
				style="width: 336px; height: 148px; float: left;"></a>
		</div>
		<ul>
			<li><a href="login.html">로그인</a></li> &nbsp; &nbsp;
			<li><a href="main.html">메인화면</a></li> &nbsp; &nbsp;
			<li><a href="stock.html">종목조회</a></li> &nbsp; &nbsp;
			<li><a href="interest.html">관심종목</a></li> &nbsp; &nbsp;
			<li><a href="discuss.html">토론하기</a></li> &nbsp; &nbsp;
			<li><a href="mypage.html">마이페이지</a></li> &nbsp; &nbsp;
			<li><a id="yellow" href="qna.html">문의하기</a></li>
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
			문의 게시판<br /> <img class="bar" src="bar.jpg"
				style="padding-top: 20px; width: 121px; height: 10px; float: center;">
		</nav>

		<div class="menu_content">



			<center>
				<form form method="POST" action="getAnswerPage">
					<table class="question_content_table">
						<%
                    	// name, title, content, date, number
                    	String name = (String)request.getAttribute("name");
                    	String title = (String)request.getAttribute("title");
                    	String content = (String)request.getAttribute("content");
                    	String date = (String)request.getAttribute("date");
                    	String number = (String)request.getAttribute("number");
                    	
                    	out.println("<tr class=\"cont\"><th>제목</th><td colspan=\"5\">"+title+"</td></tr>");
                    	out.println("<tr class=\"cont\"><th>작성자</th><td colspan=\"5\">"+name+"</td></tr>");
                    	out.println("<tr class=\"cont\"><th>작성일</th><td colspan=\"5\">"+date+"</td></tr>");
                    	out.println("<tr class=\"cont\"><th>글번호</th><td colspan=\"5\">"+number+"</td></tr>");
                    	out.println("<tr class=\"cont\"><th>내용</th><td colspan=\"5\" height=\"500\">"+content+"</td></tr>");
                    	
                    	%>

						<tr class="button_table_content">
							<th></th>
							<td colspan="5" height="50">
								<%
                            	if((Boolean)request.getAttribute("admin")){
                            		out.println("<button type=\"submit\" class=\"btn_answer\">답변하기</button>");
                            	}
                            %> <a href="qna.html" class="btn_list">목록가기</a>
							</td>
						</tr>
					</table>
					<%
                    out.println("<input type=\"hidden\" name=\"title\" value=\""+title+"\">");
                    out.println("<input type=\"hidden\" name=\"name\" value=\""+name+"\">");
                    out.println("<input type=\"hidden\" name=\"date\" value=\""+date+"\">");
                    out.println("<input type=\"hidden\" name=\"content\" value=\""+content+"\">");
                    out.println("<input type=\"hidden\" name=\"number\" value=\""+number+"\">");
                    %>
				</form>
			</center>
		</div>

	</section>

	<footer>
		<p>​© 2020 본 홈페이지의 모든 권리는 베짱이찬가에 귀속됩니다.</p>
	</footer>

</body>
</html>
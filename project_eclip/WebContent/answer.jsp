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
			답변 하기<br /> <img class="bar" src="bar.jpg"
				style="padding-top: 20px; width: 121px; height: 10px; float: center;">
		</nav>

		<div class="menu_content">
			<center>
				<table class="question_content_table">
					<%
					String title = (String)request.getParameter("title");
					String content = (String)request.getParameter("content");
					String name = (String)request.getParameter("name");
					String number = (String)request.getParameter("number");
					String date = (String)request.getParameter("date");
					
					out.println("<tr class=\"cont\"><th>제목</th><td colspan=\"5\">"+title+"</td></tr>");
                	out.println("<tr class=\"cont\"><th>작성자</th><td colspan=\"5\">"+name+"</td></tr>");
                	out.println("<tr class=\"cont\"><th>작성일</th><td colspan=\"5\">"+date+"</td></tr>");
                	out.println("<tr class=\"cont\"><th>글번호</th><td colspan=\"5\">"+number+"</td></tr>");
                	out.println("<tr class=\"cont\"><th>내용</th><td colspan=\"5\" height=\"500\">"+content+"</td></tr>");
                	
					%>
				</table>
				<form method="POST" action="writeAnswer">
					<table style="padding-bottom: 50px" align=center border=0
						cellpadding=2>
						<tr>
							<td bgcolor=white>
								<table class="questionTable" id="answer">
									<tr>
										<td>제목</td>
										<td><input type="text" class="title" name="title"
											style="width: 100%" required></td>
									</tr>

									<tr>
										<td>내용</td>
										<td><textarea name="content" class="content" cols=150
												rows=30 style="width: 100%" required></textarea></td>
									</tr>


									<tr>
										<td colspan="2" class="button_table"><a href="qna.html"
											class="btn_question_list">목록가기
												</button>
										</a> <input type="submit" class="btn_question_submit" value="등록하기">
										</td>
									</tr>

								</table> <%
								out.println("<input type=\"hidden\" name=\"number\" value=\""+number+"\">");
								%>
							</td>
						</tr>
					</table>
				</form>
			</center>
		</div>

	</section>

	<footer>
		<p>​© 2020 본 홈페이지의 모든 권리는 베짱이찬가에 귀속됩니다.</p>
	</footer>

</body>
</html>
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
            <div class="logo"><a href="main.jsp"><img src="logo.png" style="width:336px; height:148px; float:left;"></a></div>

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
         <li id="okaylogin_li"><a  href="okayLogin.jsp">메인화면</a></li>
         &nbsp; &nbsp;
         <li id="okaylogin_li"><a id="yellow" href="/Stock_Insigh/doStock">종목조회</a></li> &nbsp;
         &nbsp;
         <li id="okaylogin_li"><a href="interest.jsp">관심종목</a></li> &nbsp;
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
                <li><a href="login.jsp">로그인</a></li>
                &nbsp; &nbsp; 
                <li><a  href="main.jsp">메인화면</a></li>
                &nbsp; &nbsp; 
                <li><a id="yellow" href="/Stock_Insigh/doStock">종목조회</a></li>
                &nbsp; &nbsp;  
                <li><a href="interest.jsp">관심종목</a></li>
                &nbsp;  &nbsp; 
                <li><a href="mypage.jsp">마이페이지</a></li>
                &nbsp;  &nbsp; 
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
			검색 결과<br /> <img class="bar" src="bar.jpg"
				style="padding-top: 20px; width: 121px; height: 10px; float: center;">

		</nav>

		<div class="menu_content">
			<center>
				<%
					String search = (String) request.getAttribute("search");
					int count = (int) request.getAttribute("count");
					ArrayList<String> result = (ArrayList<String>) request.getAttribute("searchResult");
				%>

				<div class="result_count">
					<strong style="font-size: 20px;">"${search}"</strong>에 대한 검색결과 <strong
						style="font-size: 20px;">총 ${count} 건</strong> 입니다.
				</div>

				<form method="POST" action="doSearchFinal">
					<table class="srch" width="50%"
						style="border-top: 1px solid #ccc; border-right: 1px solid #ccc;">
						<%
							if (result != null) {
								for (int i = 0; i < result.size(); i++) {
						%>
						<tr>
							<th width="20%">
								<%
									String index = Integer.toString(i + 1);
								out.print(index);
								%>
							</th>
							<td width="80%"><input type="submit" name="selectCompany"
								value="<%out.print(result.get(i));%>" /></td>
						</tr>


						<%
							}
						}
						%>
					</table>

				</form>
				</tr>
			</center>
		</div>


	</section>

	<footer>
		<p>© 2020 본 홈페이지의 모든 권리는 베짱이찬가에 귀속됩니다.</p>
	</footer>

</body>
</html>
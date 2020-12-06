<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<title>Stock Insight</title>
<link rel="stylesheet" type="text/css" href="style.css?ver=1.2">
<!--design 폴더 내에 있는 css-->
<script type="text/javascript">
        var n = 0;
        var imgs = new Array("title_ver3_1.png","title_ver3_2.png");

        function rotate() {
            document.images.slide.src = imgs[n];
            (n == (imgs.length - 1)) ? n = 0: n++;
            setTimeout("rotate()", 800);
        }
    </script>
<script>
   function validate() {

      if (login.id.value == "") {
         alert("아이디를 입력해 주세요");
         login.id.focus();
         return false;
      }

      if (login.pw.value == "") { // 비밀번호 확인 입력하지 않을때,
         alert("비밀번호를 입력해주세요.");
         login.pw.value = "";
         login.pw.focus();
         return false;
      }

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
			<li id="okaylogin_li"><a href="okayLogin.jsp">메인화면</a></li> &nbsp;
			&nbsp;
			<li id="okaylogin_li"><a id="yellow"
				href="/Stock_Insigh/doStock">종목조회</a></li> &nbsp; &nbsp;
			<li id="okaylogin_li"><a href="/Stock_Insigh/doSearchInterest">관심종목</a></li>
			&nbsp; &nbsp;
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
			<li><a id="yellow" href="/Stock_Insigh/doStock">종목조회</a></li> &nbsp;
			&nbsp;
			<li><a href="interest.jsp">관심종목</a></li> &nbsp; &nbsp;
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
            ArrayList<String> fieldList = (ArrayList<String>) request.getAttribute("searchFieldList");
            ArrayList<String> companyList = (ArrayList<String>) request.getAttribute("searchCompanyList");
         %>

			종목조회<br /> <img class="bar" src="bar.jpg"
				style="padding-top: 20px; width: 121px; height: 10px; float: center;">

			<br />
		</nav>

		<div class="inner_search">
			<fieldset>
				<center>
					<form id="stock" method="POST" action="doSearch">
						<input type="text" name="search" class="search_inner"
							placeholder="검색어 입력">
						<button type="submit" style="width: auto; height: 27pt;"
							class="search_button" onclick="location.href='search_after.jsp'">&nbsp;검색&nbsp;</button>
					</form>
					<br />
					<br /> <br />
				</center>
				<br /> <br />
				<div class="inn">
					<h1 class="inn h1" style="margin-left: 17%">분야</h1>
					<h1 class="inn h1" style="margin-left: 50%">회사</h1>
				</div>
				<br />
				<br />
				<br />

				<div class="interest">

					<ul class="interest ul"
						style="border: 5px solid #4568DC; width: 360px; height: 510px; float: left">
						<form method="POST" action="doStockCompany">
							<%
                                    if (fieldList != null) {
                                    for (int i = 0; i < fieldList.size(); i++) {
                                 %>
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
							<%
                                    }
                                 }
                                 %>
						</form>
					</ul>
					<!--	</center> -->
					<!--</div> -->

					<!--  <div class="interest"> -->
					<!--  <center> -->
					<ul class="interest ul"
						style="border: 5px solid #B06AB3; width: 360px; height: 510px;">
						<form method="POST" action="doSearchFinal">
							<%
                                       if (companyList != null) {
                                       for (int i = 0; i < companyList.size(); i++) {
                                    %>
							<%
                                    out.print("<button type = \"submit\" class=\"interbtn\" name= \"selectCompany\" style=\"height: 40px; width: 340px;\" value = \"");
                                 out.print(companyList.get(i));
                                 out.print("\">");
                                 out.print("<li>");
                                 out.print("<a>");
                                 out.print(companyList.get(i));
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
		</div>
		<br />
		<br />
		<br />
		<br />
		<br />
		<br />
		</fieldset>



		</div>
		</div>
	</section>
	<footer>
		<p>© 2020 본 홈페이지의 모든 권리는 베짱이찬가에 귀속됩니다.</p>
	</footer>

</body>

</html>
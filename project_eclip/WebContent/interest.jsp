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
        <script type="text/javascript">
   function popupOpen() {

      var popUrl = "popup.jsp"; //팝업창에 출력될 페이지 URL

      var popOption = "width=400, height=400, resizable=no, scrollbars=no, status=no;"; //팝업창 옵션(optoin)

      window.open(popUrl, "", popOption);
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
			<li id="okaylogin_li"><a href="/Stock_Insigh/doStock">종목조회</a></li> &nbsp;
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
			<li><a href="/Stock_Insigh/doStock">종목조회</a></li> &nbsp; &nbsp;
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
            ArrayList<String> companyList = (ArrayList<String>) request.getAttribute("searchCompanyList");
            ArrayList<String> stockindexList = (ArrayList<String>) request.getAttribute("findStockIndexFromUser");
            ArrayList<String> beforeList = (ArrayList<String>) request.getAttribute("findBeforeList");
            ArrayList<String> futureList = (ArrayList<String>) request.getAttribute("findFutureList");
          %>
         관심종목<br /> <img class="bar" src="bar.jpg"
            style="padding-top: 20px; width: 121px; height: 10px; float: center;">
      </nav>
<div class="interst_stock">
   <div >                   <fieldset>
      <%
       if(session.getAttribute("ID")!=null){ //세션 존재 
        
    	%>
       
       <br/><br/><br/>
       
         
           <form method="POST" action="doSearchFinal">                       
               <div class = "alllike">
               <%
                  if (stockindexList != null) {
                  for (int i = 0; i < stockindexList.size(); i++) {
               %>
               <%
               out.print("<div class = \"like\">");
               out.print("<button type = \"submit\" class=\"likebtn\" name= \"selectCompany\"  value = \"");
               out.print(companyList.get(i));
               out.print("\">"); 
               %>
               <h1>[<%= findStockFieldList.get(i)%>]</h1>  
               <br/>
               <h1><%= companyList.get(i)%></h1>
               <br/>
               <p>실시간 가격 : <%= beforeList.get(i)%>원</p>  
               <p>내일 예측 가격 : <%= futureList.get(i)%>원</p>
               </button>
               </div>
               <%
                  }
               }
               %>
              </div>
            </form>
      <% } 
       else { // 세션존재하지 않음
       %><center>
                 <% 
                  out.print("<h1> 로그인 후 이용해주세요. </h1>");
                  out.print("<script>");
                  out.print("alert(\"로그인 후 이용해주세요\"); location.href = \"login.jsp\"; ");
                  out.print("</script>");
                  %>
         </center>
    <% } %></div>
   </fieldset>
                
                


</div></div>
<br /><br/><br /><br /><br/><br />

   </section>

   <footer>
      <p>© 2020 본 홈페이지의 모든 권리는 베짱이찬가에 귀속됩니다.</p>
   </footer>

</body>
</html>
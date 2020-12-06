<%@page import="model.DBUtil"%><%@page import="java.sql.*" %>
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
   var imgs = new Array("title_ver2.png", "title_ver2_2.png");
   function rotate() {
      document.images.slide.src = imgs[n];
      (n == (imgs.length - 1)) ? n = 0 : n++;
      setTimeout("rotate()", 800);
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
   <script type="text/javascript">
      var popUrl = "popup.jsp"; //팝업창에 출력될 페이지 URL

      var popOption = "width=400, height=400, resizable=no, scrollbars=no, status=no;"; //팝업창 옵션(optoin)

      window.open(popUrl, "", popOption);
   </script>

   <div class="front">
      <div class="logo">
         <a href="main.jsp"><img src="logo.png"
            style="width: 336px; height: 160px; float: left;"></a>
      </div>
      <ul id="okaylogin_ul">
         <li id="okaylogin_li">
         <%
         session.getAttribute("ID");
         String name = (String)request.getAttribute("name");
         out.println("<b>" + name + "</b> 님 환영합니다. </br>" ); 		
         %>       
       </li>
       </br>
         <li id="okaylogin_li"><a href="javascript:popupOpen();" id="red"><b>알림확인</b></a></li>&nbsp;&nbsp;&nbsp;
         <li id="okaylogin_li"><a href="/Stock_Insigh/doLogout"> 로그아웃 </a></li> &nbsp; &nbsp;
         <li id="okaylogin_li"><a id="yellow" href="main.jsp">메인화면</a></li> &nbsp; &nbsp;
         <li id="okaylogin_li"><a href="/Stock_Insigh/doStock">종목조회</a></li> &nbsp; &nbsp;
         <li id="okaylogin_li"><a href="/Stock_Insigh/doSearchInterest">관심종목</a></li> &nbsp; &nbsp;
         <li id="okaylogin_li"><a href="mypage.jsp">마이페이지</a></li> &nbsp; &nbsp;
         <li id="okaylogin_li"><a href="/Stock_Insigh/postList?pageIndex=1">문의하기</a></li>

         </br>
      </ul>
   </div>
   <div>
      <header>
         <center>
            <img src="title_ver2.png" id="slide"
               style="width: 1200px; height: auto"; >
         </center>
      </header>
   </div>
   <section id="content">

      <nav>
         실시간 차트<br /> <img class="bar" src="bar.jpg"
            style="padding-top: 20px; width: 121px; height: 10px; float: center;">
      </nav>

      <div class="menu_content">여기에 내용이 들어가면 됩니다.</div>

   </section>

   <footer>
      <p> © 2020 본 홈페이지의 모든 권리는 베짱이찬가에 귀속됩니다.</p>
   </footer>

</body>
</html>
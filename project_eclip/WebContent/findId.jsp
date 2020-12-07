<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="UTF-8">
<title>Stock Insight</title>
<link rel="stylesheet" type="text/css" href="style.css?ver=1.1">
<!--design 폴더 내에 있는 css-->
<script type="text/javascript">
   var n = 0;
   var imgs = new Array("title_ver3_1.png","title_ver3_2.png");

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
         <li id="okaylogin_li"><a id="yellow" href="main.jsp">메인화면</a></li>
         &nbsp; &nbsp;
         <li id="okaylogin_li"><a href="/Stock_Insigh/doStock">종목조회</a></li> &nbsp;
         &nbsp;
         <li id="okaylogin_li"><a href="/Stock_Insigh/doSearchInterest">관심종목</a></li> &nbsp;
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
                <li><a id="yellow" href="login.jsp">로그인</a></li>
                &nbsp; &nbsp; 
                <li><a href="main.jsp">메인화면</a></li>
                &nbsp; &nbsp; 
                <li><a href="/Stock_Insigh/doStock">종목조회</a></li>
                &nbsp; &nbsp;  
                <li><a href="/Stock_Insigh/doSearchInterest">관심종목</a></li>
                &nbsp;  &nbsp; 
                <li><a href="login.jsp">마이페이지</a></li>
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
            <img src="title.PNG" id="slide" style="width: 1200px; height: auto";>
         </center>
      </header>
   </div>
   <section id="content">
      <nav>
         ID 찾기 <br /> <img class="bar" src="bar.jpg"
            style="padding-top: 20px; width: 121px; height: 10px; float: center;">

         <br />
         <div style="font-size: 20px; margin-top: 10px;">
            <class="welcome">가입할 때 작성한 <b>이름과 이메일</b>을 입력해주세요.
         </div>
      </nav>

      <form  method="POST" action="dofindId" >
         <center>
            <table class="find_idtable">
               <br>
               <tr>
                  <td>이름 :</td>
                  <td><input required type="text" name="user_name" id="name"
                     maxlength="12" /></td>
               </tr>
               <tr>
                  <td>email :</td>
                  <td><input required type="email" name="user_email" id="email"
                     maxlength="50" /> </td>
               </tr>
            </table>
            <input type="submit" class="findid" value=" ID 찾기 ">            
         </center>
      </form>
   </section>

   <footer>
      <p> © 2020 본 홈페이지의 모든 권리는 베짱이찬가에 귀속됩니다.</p>
   </footer>

</body>

</html>
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
</script><script>
   function validate() {
      var re = /^[a-zA-Z0-9]{4,12}$;/ // 아이디와 패스워드가 적합한지 검사할 정규식
      var re2 = /^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$/i;
      // 이메일이 적합한지 검사할 정규식
;
      var email = document.getElementById("email");


      if (!check(re2, email, "적합하지 않은 이메일 형식입니다.")) {
         return false;
      }

      if (!check(re, pw, "패스워드는 4~12자 사이의 영문 대소문자와 숫자를 포함해야 합니다.")) {
         return false;
      }

      if (join.checkpw.value == "") { // 비밀번호 확인 입력하지 않을때,
         alert("비밀번호를 확인을 입력해주세요.");
         join.checkpw.value = "";
         join.checkpw.focus();
         return false;
      }

      if (join.pw.value != join.checkpw.value) {
         alert("비밀번호가 다릅니다. 다시 확인해 주세요.");
         join.checkpw.value = "";
         join.checkpw.focus();
         return false;
      }

   }
   function check(re, what, message) {
      if (re.test(what.value)) {
         return true;
      }
      alert(message);
      what.value = "";
      what.focus();
      //return false;
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
         <li id="okaylogin_li"><a id="yellow" href="okayLogin.jsp">메인화면</a></li>
         &nbsp; &nbsp;
         <li id="okaylogin_li"><a href="stock.jsp">종목조회</a></li> &nbsp;
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
                <li><a id="yellow" href="main.jsp">메인화면</a></li>
                &nbsp; &nbsp; 
                <li><a href="stock.jsp">종목조회</a></li>
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
            <img src="title.PNG" id="slide" style="width: 1200px; height: auto";>
         </center>
      </header>
   </div>
   <section id="content">
      <nav>
         회원가입<br /> <img class="bar" src="bar.jpg"
            style="padding-top: 20px; width: 121px; height: 10px; float: center;">

         <br />
         <div style="font-size: 20px; margin-top: 10px;">
            <b class="welcome">아래의 정보를 빠짐없이 입력해주시기 바랍니다. </b>
         </div>
      </nav>

      <form name="join" onsubmit="return validate();" action="doJoin"
         method="POST">
         <center>
            <table class="table">
               <br>
               <tr>
                  <td>이름 :</td>
                  <td><input required type="text" name="user_name" id="name"
                     maxlength="12" /></td>
               </tr>
               <tr>
                  <td>ID :</td>
                  <td><input required type="text" name="user_id" id="id"
                     maxlength="20" /> </td>

               </tr>
               <tr>
                  <td>email :</td>
                  <td><input required type="text" name="user_email" id="email"
                     maxlength="50" /> </td>

               </tr>
               <tr>
                  <td></td>
                  <td colspan="2" style="color: red;">ex)
                     stockinsight@naver.com</td>
               </tr>
               <tr>
                  <td>비밀번호 :</td>
                  <td><input required type="password" name="user_pwd" id="pw"
                     maxlength="12"></td>
               </tr>
               <tr>
                  <td></td>
                  <td colspan="2" style="color: red;">※ 4-12자의 영문 대소문자와 숫자로만 입력</td>
               </tr>
               <tr>
                  <td>비밀번호 확인 :</td>
                  <td><input required type="password" id="checkpw" maxlength="12"></td>
               </tr>
            </table>
            <br /> <br /> <input type="submit" class="okayjoin" value=" 회원가입 ">
            </td>
         </center>
      </form>
   </section>

   <footer>
      <p> © 2020 본 홈페이지의 모든 권리는 베짱이찬가에 귀속됩니다.</p>
   </footer>

</body>

</html>
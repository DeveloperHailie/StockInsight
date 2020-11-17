<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
                <li><a id="yellow" href="login.jsp">로그인</a></li>
                &nbsp; &nbsp; 
                <li><a href="main.jsp">메인화면</a></li>
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
                <img src="title_ver3_1.png" id="slide" style="width: 1200; height: auto"; >
            </center>
        </header>
    </div>
    <section id="content">
        <nav>
            로그인<br /> <img class="bar" src="bar.jpg" style="padding-top: 20px; width: 121px; height: 10px; float: center;">

            <br /> <div style = "font-size: 20px; margin-top: 10px;">
                <b class="welcome">스톡인사이트 </b>에 오신 것을 환영합니다!                
            </div>
        </nav>

        <div class="inner_login">
            <div class="login_start">
                <form id = "login" onsubmit="return validate();" method="POST" action="doLogin">
                    <fieldset>
                        <div class="box_login">
                            <div class="inp_text">
                                <label for="loginId" class="login_show">아이디</label> 
                                <input type="text" id="id" name="user_id" placeholder="ID">
                            </div>
                            <div class="inp_text">
                                <label for="loginPw" class="login_show">비밀번호</label> 
                                <input type="password" id="pw" name="user_pwd" placeholder="Password">
                            </div>
                        </div>
                        <input type="submit" class="btn_login" value="로그인"  onclick="showPopup();">
                    </fieldset>
                </form>
                <fieldset class= "login_under">
                    <a href="findId.jsp">ID 찾기 </a> / <a href="findPwd.jsp">PASSWORD 찾기</a>
                <a href="join.jsp" class = "join">회원가입</a>               
                
                </fieldset>

            </div>
        </div>
    </section>
    <footer>
        <p> © 2020 본 홈페이지의 모든 권리는 베짱이찬가에 귀속됩니다.</p>
    </footer>

</body>

</html>
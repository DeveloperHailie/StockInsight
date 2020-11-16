<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html" charset="UTF-8">
        <title>Stock Insight</title>
        <link rel="stylesheet" type="text/css" href="style.css"/>
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
            <div class="logo"><a href="main.html"><img src="logo.png" style="width:336px; height:148px; float:left;"></a></div>
            <ul>
                <li><a href="login.html">로그인</a></li>
                &nbsp; &nbsp; 
                <li><a href="main.html">메인화면</a></li>
                &nbsp; &nbsp; 
                <li><a href="stock.html">종목조회</a></li>
                &nbsp; &nbsp;  
                <li><a href="interest.html">관심종목</a></li>
                &nbsp;  &nbsp; 
                <li><a href="discuss.html">토론하기</a></li>
                &nbsp;   &nbsp; 
                <li><a id="yellow" href="mypage.html">마이페이지</a></li>
                &nbsp;  &nbsp; 
                <li><a href="/Stock_Insigh/postList?pageIndex=1">문의하기</a></li>
            </ul>
          </div>
          <div>
          <header>
          <center>
            <img src="title_ver3_1.png" id="slide" style="width: 1200; height: auto"; >
          </center>
          </header>
           </div>
          <section id="content" >
           
            <nav>
                마이페이지<br/>
                <img class="bar" src="bar.jpg" style="padding-top:20px; width:121px; height : 10px; float:center;">
            </nav>

            <div class="menu_content">
            마이페이지 정보를 확인하기 전<br>
            안전한 열람을 위해 비밀번호를 한번 더 입력해주세요<br><p>

             
      <form method = "POST" action="doMypagecheck">
       <br>
            <br>
            <br>
            <br>
               <center>
                 <font size="3" > 패스워드 </font>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                  <input requied type="password" name="user_pwd" id="user_pwd"
                      placeholder="Enter your Password"/> 
                     </center>
            <br>
            <br>
            <br>
            <br>
            <br>
            <br>
            <br>
            <br>
            <br /> <br />
            <input type = "submit" class="btn_question_submit" value=" 확 인 "></form>
</center>
            </div>
            
          </section>
         
          <footer>
            <p>​© 2020 본 홈페이지의 모든 권리는 베짱이찬가에 귀속됩니다.</p>
          </footer>
        
    </body>
</html>
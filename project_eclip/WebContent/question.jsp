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
                <li><a href="main.jsp">메인화면</a></li>
                &nbsp; &nbsp; 
                <li><a href="stock.jsp">종목조회</a></li>
                &nbsp; &nbsp;  
                <li><a href="interest.jsp">관심종목</a></li>
                &nbsp;  &nbsp; 
                <li><a href="mypage.jsp">마이페이지</a></li>
                &nbsp;  &nbsp; 
                <li><a href="/Stock_Insigh/postList?pageIndex=1" id="yellow">문의하기</a></li>
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
          <section id="content" >
           
            <nav>
                문의하기<br/>
                <img class="bar" src="bar.jpg" style="padding-top:20px; width:121px; height : 10px; float:center;">
            </nav>

            <div class="question_content">
              <center>
              <form method = "POST" action="writeQuestion">
                <table  style="padding-bottom:50px" align = center width=700 border=0 cellpadding=2 >
                        <tr>
                          <td bgcolor=white>
                          <table class = "questionTable">
                                  <tr>
                                  <td>제목</td>
                                  <td><input type = "text" class="title"  name ="title" size=86.5 required></td>
                                  </tr>
          
                                  <tr>
                                  <td>내용</td>
                                  <td><textarea name ="content" class="content" cols=85 rows=30 required></textarea></td>
                                  </tr>

                                  <tr>
                                    <td colspan="2" class="button_table">
                                      <a href="/Stock_Insigh/postList?pageIndex=1" class="btn_question_list">목록보기</button></a>
                                      <input type = "submit" class="btn_question_submit" value="등록하기">
                                    </td>
                                  </tr>

                          </table>                      
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
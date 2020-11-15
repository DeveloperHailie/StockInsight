<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="model.QnAList,java.util.ArrayList"%>

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
                <li><a href="mypage.html">마이페이지</a></li>
                &nbsp;  &nbsp; 
                <li><a id="yellow" href="/Stock_Insigh/postList">문의하기</a></li>
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
                문의 게시판<br/>
                <img class="bar" src="bar.jpg" style="padding-top:20px; width:121px; height : 10px; float:center;">
            </nav>

            <div class="menu_content">

              <a href="question.html" class="btn_question">글 작성</a>

              <center>
                <table class = "qna_table">
                  <thead>
                    <tr>
                      <th>번호</th>
                      <th>제목</th>
                      <th>작성자</th>
                      <th>작성일</th>
                    </tr>
                  </thead>
                  <tbody>
                  
                  <%
                  // 출력할 post list
                  	ArrayList<QnAList> postList = (ArrayList<QnAList>)request.getAttribute("postList");
                  // 현재 page
                  	String pageIndexStr = (String)request.getParameter("pageIndex");
                  	int pageindex = Integer.parseInt(pageIndexStr);
                  	
                  	for(int i=(15*(pageindex-1));i<postList.size()&&i<(15*(pageindex-1)+15);i++){
                  		if(!postList.get(i).getIsQuestion()){
                  			out.println("<tr class=\"reply\">");
                  		}
                  		else{
                  			out.println("<tr>");
                  		}
                  		out.print("<td>");
                  		out.print(postList.get(i).getIndex());
                  		out.print("</td>");
                  		out.print("<td>");
                  		out.print(postList.get(i).getTitle());
                  		out.print("</td>");
                  		out.print("<td>");
                  		out.print(postList.get(i).getWriter());
                  		out.print("</td>");
                  		out.print("<td>");
                  		out.print(postList.get(i).getDate());
                  		out.print("</td>");
                  	}
                  %>
                  
                  </tbody>
                </table>

				<table class="page">
                  <tr>
                  <%
                  	if (pageindex > 1) {
                  	out.println("<td><a href=\"/Stock_Insigh/postList?pageIndex=" + (pageindex - 1) + "\"> &lt; </a></td>");
                  }
                  if ((postList.size() / 15 + 1) > pageindex) {
                  	out.println("<td><a href=\"/Stock_Insigh/postList?pageIndex=" + (pageindex + 1) + "\"> &gt; </a></td>");
                  }
                  %>
                  </tr>
                </table>

              </center>
            </div>
            
          </section>
         
          <footer>
            <p>​© 2020 본 홈페이지의 모든 권리는 베짱이찬가에 귀속됩니다.</p>
          </footer>
        
    </body>
</html>
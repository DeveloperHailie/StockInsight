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
			<%
				String selectField = (String) request.getAttribute("selectField"); //분야
				String selectCompany = (String) request.getAttribute("selectCompany"); //회사
				
			%>
			예측결과<br /> <img class="bar" src="bar.jpg"
				style="padding-top: 20px; width: 121px; height: 10px; float: center;">
		</nav>
		<div class="final_content">
			<div class= "left_final">
				<!--<p style="font-size: 30px; color: black;">${selectField}분야 :  </p> <!-- yh 사용 
				<p style="font-size: 38px; color: black;"><b>&nbsp;&emsp;&emsp;&emsp;&emsp;${selectCompany}</b></p> <!-- yh 사용 
				<div class ="price">
					${selectField}분야 <b>${selectCompany}</b>의 실시간 가격 <br>
					<b style="font-size: 20px">330,600원</b>입니다.
				</div> 연희 수정 후에 주석 풀고 밑에꺼 삭제하기-->
				
				<p style="font-size: 35px; color: black;"><b>${selectField}</b>분야 :  </p> <!-- yh 사용 -->
				<p style="font-size: 40px; color: black; padding-left: 30px;"><b>${selectCompany}</b>
				
				
				<%
               if(session.getAttribute("ID")!=null){ //세션 존재 
               	String user_id = (String) session.getAttribute("ID"); // 세션에 저장된 user_id 
               	String user_index = (String)request.getAttribute("user_index"); // 받아온 user_index 
               	
               	String stock_index = (String)request.getAttribute("stock_index"); // 받아온 stock_index 
               	Boolean interestCheck = (Boolean)request.getAttribute("interCheck"); //관심 종목에 들어가있는지 유무 
               	System.out.print("있는지 없는지 :   " + interestCheck);
               %> 
				
               	<%
               	if(interestCheck == false){//선택한 회사가 관심종목에 없을 때, x
               		out.print("<form method = \"POST\" action=\"doFindIndex\">"); //관심종목에 현재 로그인한 user_index에 선택한 분야가 관심종목에 있을때 
               		out.print("<button type = \"submit\" name= \"user_id\" style=\" border : none; margin-left:20px;\" onClick=\"alert('관심종목에 담겼습니다.')\" value = \"");
               		out.print(user_id);
               		out.print("\">");
               		out.print("<input type = \"hidden\" name = \"selectCompany\" value = \"");
               		out.print(selectCompany);
               		out.print("\"/>");
               		out.print("<img src=\"empty_heart.png\" style=\"width: 30px; height: auto; background: white;\">");
              		out.print("</button>");
               		out.print("</p>");
               		
               	 	out.print("<input type = \"hidden\" name = \"user_index\" value = \"");
                 	out.print(user_index);
                 	out.print("\"/>");
  
                 	out.print("<input type = \"hidden\" name = \"stock_index\" value = \"");
                 	out.print(stock_index);
                 	out.print("\"/>");
                 	
                 	out.print("<input type = \"hidden\" name = \"selectField\" value = \"");
                 	out.print(selectField);
                 	out.print("\"/>");
                 	out.print("</form>");
               	}else { //선택한 회사가 관심종목에 있을 때, o 
               		out.print("<form method = \"POST\" action=\"doDeleteInterest\">");
               		out.print("<button type = \"submit\" name= \"user_id\" style=\" border : none; margin-left:20px;\" onClick=\"alert('관심종목이 취소되었습니다.')\" value = \"");
               		out.print(user_id);
               		out.print("\">");
               		out.print("<input type = \"hidden\" name = \"selectCompany\" value = \"");
               		out.print(selectCompany);
               		out.print("\"/>");
               		out.print("<img src=\"heart.png\" style=\"width: 30px; height: auto; background: white;\">");
              		out.print("</button>");
               		out.print("</p>");
               		
               	 	out.print("<input type = \"hidden\" name = \"user_index\" value = \"");
                 	out.print(user_index);
                 	out.print("\"/>");
  
                 	out.print("<input type = \"hidden\" name = \"stock_index\" value = \"");
                 	out.print(stock_index);
                 	out.print("\"/>");
                 	
                 	out.print("<input type = \"hidden\" name = \"selectField\" value = \"");
                 	out.print(selectField);
                 	out.print("\"/>");
                 	out.print("</form>");
               	}
               	%>
              	
                
               
               <% }else {
            	   // 세션존재하지 않음
                   %>
                   <button style="border : none; margin-left:20px;" onClick="alert('로그인이 필요한 서비스입니다.')"><img src="empty_heart.png" style="width: 30px; height: auto; background: white;"></button></p>
                   <% } %>

				<div class ="realtime_price">
					<b>${selectCompany}</b>의 실시간 가격 <br>
					<b style="font-size: 20px;">330,600원</b>입니다.
				</div>
				<div class = "price">
					내일 예측 가격은<br>
					<b style="font-size: 30px; background: linear-gradient(to right, #B06AB3, #4568DC); -webkit-background-clip: text;
                     -webkit-text-fill-color: transparent;">330,000원</b> 입니다.
				</div>
				
			</div>
			<div class="right_final"> <!-- chat -->
				실시간 차트
			</div>
		</div>
		</section>

	<footer>
		<p>© 2020 본 홈페이지의 모든 권리는 베짱이찬가에 귀속됩니다.</p>
	</footer>

</body>
</html>
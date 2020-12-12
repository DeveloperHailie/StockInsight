<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>

<!DOCTYPE html>
<html>
<head>
<style>
#rank-list a {
   color: #FFF;
   text-decoration: none;
}

#rank-list a:hover {
   text-decoration: underline;
}

#rank-list {
   overflow: hidden;
   width: 160px;
   height: 20px;
   margin: 0;
}

#rank-list dt {
   display: none;
}

#rank-list dd {
   position: relative;
   margin: 0;
}

#rank-list ol {
   position: absolute;
   top: 0;
   left: 0;
   margin: 0;
   padding: 0;
   list-style-type: none;
}

#rank-list li {
   height: 20px;
   line-height: 20px;
}
</style>
<script src="./js/myAjax.js"></script>
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
<script type="text/javascript">
   function popupOpen() {

	   var popUrl = "/Stock_Insigh/doPop";//팝업창에 출력될 페이지 URL

      var popOption = "width=400, height=400, resizable=no, scrollbars=no, status=no;"; //팝업창 옵션(optoin)

      window.open(popUrl, "", popOption);
   }
</script>
<script
   src="https://ajax.googleapis.com/ajax/libs/jquery/1.6.4/jquery.min.js"
   type="text/javascript"></script>
<script>
   $(function() {
      var count = $('#rank-list li').length;
      var height = $('#rank-list li').height();
      function step(index) {
         $('#rank-list ol').delay(2000).animate({
            top : -height * index,
         }, 500, function() {
            step((index + 1) % count);
         });
      }
      step(1);
   });
</script>
<script>
   function showplay() {
      var flag = $('#hidTempSynopsis');
      var btn = document.getElementById("D");
      var SynopsisDiv = $('#content');
      var real = $('#D');
      var flagValue = flag.val();
      if (flag != null) {
         if (flagValue == "0") {
            real.css("display", "block");
            var tag;
            //tag = "<ul>";
            tag  = "<a href=\"#\" style='color: cornflowerblue;font-size: 16px;'><b>실시간 거래량 순위</b></a></br>";
tag += "<section id='hiddenRank' style='padding:3px 10px 10px 10px;'>";
            
            tag += "</section>";
            //tag += "</ul>";
            btn.innerHTML = tag;
            $("#synopMore").text("▲");
            flag.val("1");
         } else {
            //SynopsisDiv.css("height", "77px");
            real.css("display", "none");
            $("#synopMore").text("▼");
            flag.val("0");
         }
      }
   }
</script>
</head>

<body onload='rotate()'>

   <!-- 차트 그리기 -->
   <script type="text/javascript"
      src="https://www.gstatic.com/charts/loader.js"></script>

   <script type="text/javascript">
      //Google Stuff
      google.charts.load('current', {
         packages : [ 'corechart' ]
      });
      google.charts.setOnLoadCallback(function() {
         repeatChart();
         loadPresentPrice();
      });
   </script>
   <script>
   <%String stock_code = (String) request.getAttribute("stock_code"); 
   System.out.println(stock_code);
   %>

   // 차트 부분만 reload

   var repeatChart = function() {
      myAjax("/Stock_Insigh/csv", "code=" + "${stock_code}", function() {
         ajaxMakeChart(right_final, this.responseText.trim()); //데이터, 그리기 함수가 들어간 함수
      });
   }
   // 현재 가격 부분만 reload
   var loadPresentPrice = function() {
      myAjax("/Stock_Insigh/csv", "code=" + "${stock_code}", function() {
         stock_pre_data(stock_pre, this.responseText.trim()); //현재가격 영역
      });
   };
// 보여지는 순위만 reload
	var loadShowRank = function() {
		var btn = document.getElementById('showRank');
		var table = "stock_volume"
		myAjax("/Stock_Insigh/getRank", "table="+table, function() {
			updateShowRank(btn, this.responseText.trim()); //현재가격 영역
		});
	}
	// 숨겨진 순위만 reload
	var loadHiddenRank = function() {
		var btn = document.getElementById('hiddenRank');
		var table = "stock_volume"
		myAjax("/Stock_Insigh/getRank", "table="+table, function() {
			updateHiddenRank(btn, this.responseText.trim()); //현재가격 영역
		});
	}
	
	// 보여지는 순위 그리는 코드
	function updateShowRank(element, txtData) {
		var strTag = "";
		row = txtData.split("@"); // 회사명@회사명@...@회사명        
		for (var rowIndex = 1; rowIndex <= row.length; rowIndex++) {
			var companyName = row[rowIndex-1];
			strTag += "<li><a href='javascript:showplay();'>"+rowIndex + "위. "+companyName+"</a></li>";
		};
		element.innerHTML = strTag;
	};
	
	// 숨긴 순위 그리는 코드
	function updateHiddenRank(element, txtData) {
		var strTag = "";
		row = txtData.split("@"); // 회사명@회사명@...         
		for (var rowIndex = 1; rowIndex <= row.length; rowIndex++) {
			var companyName = row[rowIndex-1];
			strTag += "<a href='"+"/Stock_Insigh/getRankInfo?companyName="+companyName+"'>"+rowIndex + "위. "+companyName+"</a></br>";
		};
		element.innerHTML = strTag;
	};
   // 차트 그리기
   function ajaxMakeChart(element, txtData) {
      row = txtData.split("@"); // row1@row2@...         
      var one_row;
      var all_row = [];
      // 한 tr이 한 row
      for (var rowIndex = 0; rowIndex < row.length - 1; rowIndex++) {
         col = row[rowIndex].split("|"); // row의 각 col들 (name=value|name=value)            
         //one_row 초기화
         one_row = [];
         // date
         dateNameValue = col[0].split("=");
         one_row.push(dateNameValue[1]);
         // price
         priceNameValue = col[1].split("=");
         one_row.push(parseInt(priceNameValue[1]));
         // (date, price)
         all_row.push(one_row);
      };
      
      var data = new google.visualization.DataTable();
      data.addColumn('string', 'date');
      data.addColumn('number', 'price');
      data.addRows(all_row);
      var options = null;
      var options = {
         title : '실시간 차트',
         textAlign : 'center',
         titleTextStyle: {
            display : true,
             fontSize: 20, // 12, 18 whatever you want (don't specify px)
             bold: true,  // true of false
             titleAlign:'center',
          },
         hAxis : {
            title : 'time',
            textPosition : 'none'
         },
         vAxis : {
            title : 'price'
         }

      };
      var chart = new google.visualization.LineChart(document
            .getElementById('right_final'));

      chart.draw(data, options);
   };
   var stock_pre_data = function(element, txtData) {
      if(txtData=="파일없음"){
         var strTag = "<b>실시간 데이터를 갱신하지 못했습니다.</br>조금만 기다려 주세요.</b>";
         element.innerHTML = strTag;
      }
      else{
         row = txtData.split("@"); // row1@row2@... 
         var strTag = "<b>";

         // 내가 필요한 것은 가장 최근이므로, 가장 마지막 row(에서 2번째. 가장 마지막 row는 비어있다.)
         rowIndex = row.length - 2;
         col = row[rowIndex].split("|"); // row의 각 col들 (name=value|name=value)

         // code , date, presentPrice, sign, difference, prevEndPrice, volume
         if (col.length > 4) {
            presentPrice = col[1].split("=");    
            volume = col[4].split("=");
            sign = col[2].split("=");
            difference = col[3].split("=");
            strTag += "현재 가격: " + presentPrice[1] + "&nbsp원";           
            if (sign[1] == "상승") {
               strTag += "<b style='color:red;'>&emsp;&emsp;&emsp; ▲ ";

            } else if (sign[1] == "하락") {
               strTag += "<b style='color:blue;'>&emsp;&emsp;&emsp; ▼ ";
            } else { // 보합,      
               strTag += "<b style='color:black;'>&emsp;&emsp;&emsp; 〓 ";
            }

            strTag += difference[1] + "</b>";
            strTag += "</br>실시간 거래량: " + volume[1] + "&nbsp";
         }
         strTag += "</b>";
         element.innerHTML = strTag;
      }      
   };
   setInterval(function() { 
      repeatChart();
      loadPresentPrice();
   }, 30000);
   setInterval(function() { 
      loadShowRank();
      loadHiddenRank();
   }, 1000);
   window.onload = function(){
      loadShowRank();
      loadHiddenRank();
   }
</script>
	<div class="front">
		<div class="logo">
			<a href="main.jsp"><img src="logo.png"
				style="width: 336px; height: 148px; float: left;"></a>
		</div>
		<div id="content-rank"
			style="position: absolute; margin-left: 380px; margin-top: 65px;">
			<dl id="rank-list">
				<dd>
					<ol id="showRank" style="font-family: 'nanum';">
						<li><a href='javascript:showplay();'> </a></li>
						<li><a href='javascript:showplay();'> </a></li>
						<li><a href='javascript:showplay();'> </a></li>
						<li><a href='javascript:showplay();'> </a></li>
						<li><a href='javascript:showplay();'> </a></li>
						<li><a href='javascript:showplay();'> </a></li>
						<li><a href='javascript:showplay();'> </a></li>
						<li><a href='javascript:showplay();'> </a></li>
						<li><a href='javascript:showplay();'> </a></li>
						<li><a href='javascript:showplay();'> </a></li>
					</ol>
				</dd>
			</dl>
		</div>
		<div id="D" style="display:none; border-radius:20px; position: absolute;  margin-left: 380px; margin-top: 85px; padding-top: 10px; padding-left: 10px; background-color: #ffffffdd; font-size:14px; font-family: 'nanum';"></div>
		<input name="hidTempSynopsis" type="hidden" id="hidTempSynopsis" value="0">
		<!-- value 체크값을 위함 -->
		<%
         if (session.getAttribute("ID") != null) {
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
			<li id="okaylogin_li"><a href="/Stock_Insigh/sortPredict">🥇예측순위</a></li>&nbsp;&nbsp;&nbsp;
			<li id="okaylogin_li"><a href="/Stock_Insigh/doLogout"> 로그아웃
			</a></li> &nbsp; &nbsp;
			<li id="okaylogin_li"><a href="main.jsp">메인화면</a></li>
			&nbsp; &nbsp;
			<li id="okaylogin_li"><a id="yellow" href="/Stock_Insigh/doStock">종목조회</a></li>
			&nbsp; &nbsp;
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
         <li><a href="mypage.jsp">마이페이지</a></li> &nbsp; &nbsp;
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
            String selectFuture = (String) request.getAttribute("selectFuture"); // 예측가격
         %>
         예측결과<br /> <img class="bar" src="bar.jpg"
            style="padding-top: 20px; width: 121px; height: 10px; float: center;">
      </nav>
      <div class="final_content">
         <div class="left_final">

            <p style="font-size: 30px; color: black;">
               <b>${selectField}</b>분야 :
            </p>
            <!-- yh 사용 -->
            <p style="font-size: 40px; color: black; padding-left: 30px;">
               <b>${selectCompany}</b>
               <%
                  if (session.getAttribute("ID") != null) { //세션 존재 
                  String user_id = (String) session.getAttribute("ID"); // 세션에 저장된 user_id 
                  String user_index = (String) request.getAttribute("user_index"); // 받아온 user_index 

                  String stock_index = (String) request.getAttribute("stock_index"); // 받아온 stock_index 
                  Boolean interestCheck = (Boolean) request.getAttribute("interCheck"); //관심 종목에 들어가있는지 유무 
               %>
               <%
                  if (interestCheck == false) {//선택한 회사가 관심종목에 없을 때, x
                  out.print("<form method = \"POST\" action=\"doInsertInterest\">"); //관심종목에 현재 로그인한 user_index에 선택한 분야가 관심종목에 있을때 
                  out.print(
                  "<button type = \"submit\" name= \"user_id\" style=\" border : none; margin-left:20px;\" onClick=\"alert('관심종목에 담겼습니다.')\" value = \"");
                  out.print(user_id);
                  out.print("\">");
                  out.print("<input type = \"hidden\" name = \"selectCompany\" value = \"");
                  out.print(selectCompany);
                  out.print("\"/>");
                  out.print("<img src=\"empty_heart.png\" style=\"width: 30px; height: auto; background: white;  \">");
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
               } else { //선택한 회사가 관심종목에 있을 때, o 
                  out.print("<form method = \"POST\" action=\"doDeleteInterest\">");
                  out.print(
                  "<button type = \"submit\" name= \"user_id\" style=\" border : none; margin-left:20px;\" onClick=\"alert('관심종목이 취소되었습니다.')\" value = \"");
                  out.print(user_id);
                  out.print("\">");
                  out.print("<input type = \"hidden\" name = \"selectCompany\" style=\" float : right;\" value = \"");
                  out.print(selectCompany);
                  out.print("\"/>");
                  out.print("<img src=\"heart.png\" style=\"width: 30px; height: auto; background: white; float : right;\">");
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
               <%
                  } else {
               // 세션존재하지 않음
               %>
               <button style="border: none; margin-left: 20px;"
                  onClick="alert('로그인이 필요한 서비스입니다.');">
                  <img src="empty_heart.png"
                     style="width: 30px; height: auto; background: white;">
               </button>
            </p>
            <%
               }
            %>
            <div class="realtime_price" id="stock_pre"></div>
            <div class="price">
               내일 예측 가격은<br> <b
                  style="font-size: 30px; background: linear-gradient(to right, #B06AB3, #4568DC); -webkit-background-clip: text; -webkit-text-fill-color: transparent;">${selectFuture}원</b>
               입니다.
            </div>
            <% %>
            <div style="margin-top:10%">
            	<a href="https://search.naver.com/search.naver?where=news&sm=tab_jum&query=<%=selectCompany%>" 
            	target="_blank" style="text-decoration: underline; color: gray; ">
            	<b style="font-size: 20px;"> 📢 관련 이슈가 궁금하신가요?</b></a>
            </div>
         </div>

         <div id="right_final"
            style="border: 1px solid black; float: left; margin-left: 5%; width: 35%; height: 450px;">
            <!-- chat -->
            실시간 차트
         </div>

      </div>
   </section>

   <footer>
      <p>© 2020 본 홈페이지의 모든 권리는 베짱이찬가에 귀속됩니다.</p>
   </footer>

</body>
</html>
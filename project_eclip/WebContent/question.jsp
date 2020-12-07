<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
<<<<<<< HEAD
        <script type="text/javascript">
   function popupOpen() {

      var popUrl = "popup.jsp"; //팝업창에 출력될 페이지 URL

      var popOption = "width=400, height=400, resizable=no, scrollbars=no, status=no;"; //팝업창 옵션(optoin)

      window.open(popUrl, "", popOption);
   }
</script>
=======
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
				tag  = "<a href=\"#\"><b>실시간 거래량 순위</b></a></br>";
				tag += "<section id='hiddenRank'>";
				
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
        
>>>>>>> 97a5790ac4d49c14ba6c02d3a69b053dba496868
    </head>
	
    <body onload='rotate()'>
        <script>
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
setInterval(function() { 
			loadShowRank();
			loadHiddenRank();
		}, 1000);
		window.onload = function(){
			loadShowRank();
			loadHiddenRank();
		} </script>
        
         <div class="front">
            <div class="logo"><a href="main.jsp"><img src="logo.png" style="width:336px; height:148px; float:left;"></a></div>
		<div id="content-rank"
			style="position: absolute; margin-left: 380px; margin-top: 65px;">
			<dl id="rank-list">
				<dd>
					<ol id="showRank" style="font-family: 'nanum';" >
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
		<div id="D"
			style="position: absolute;  margin-left: 380px; margin-top: 85px; background-color: #ffffffcc; font-size:14px; font-family: 'nanum';"></div>
		<input name="hidTempSynopsis" type="hidden" id="hidTempSynopsis"
			value="0">
		<!-- value 체크값을 위함 -->
		
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
                <li><a href="login.jsp">로그인</a></li>
                &nbsp; &nbsp; 
                <li><a href="main.jsp">메인화면</a></li>
                &nbsp; &nbsp; 
                <li><a href="/Stock_Insigh/doStock">종목조회</a></li>
                &nbsp; &nbsp;  
                <li><a href="/Stock_Insigh/doSearchInterest">관심종목</a></li>
                &nbsp;  &nbsp; 
                <li><a href="login.jsp">마이페이지</a></li>
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
            <img src="title_ver3_1.png" id="slide" style="height: auto"; >
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
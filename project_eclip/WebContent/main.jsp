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
<meta http-equiv="Content-Type" content="text/html" charset="UTF-8">
<title>Stock Insight</title>
<link rel="stylesheet" type="text/css" href="style.css" />
<script type="text/javascript">
			var n = 0;
			var imgs = new Array("title_ver2.png","title_ver2_2.png");
			function rotate() {
				document.images.slide.src = imgs[n];
				(n == (imgs.length - 1)) ? n=0 : n++; setTimeout("rotate()",800);
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
	            top: -height * index,
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
     		tag = "<a href=\"#\" id=\"current\">1 순위</a></br>";
     		tag += "<a href=\"#\">2 순위</a></li>";
     		//tag += "</ul>";
     		btn.innerHTML = tag;
             $("#synopMore").text("▲");
             flag.val("1");
         }
         else {
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

	<div class="front">
		<div class="logo">
			<a href="main.jsp"><img src="logo.png"
				style="width: 336px; height: 148px; float: left;"></a>
		</div>

		<div id="content-rank"
			style="position: absolute; margin-left: 380px; margin-top: 65px;">
			<dl id="rank-list">
				<dd>
					<ol>
						<li><a href="javascript:showplay();">1 순위</a></li>
						<li><a href="javascript:showplay();">2 순위</a></li>
						<li><a href="javascript:showplay();">3 순위</a></li>
						<li><a href="javascript:showplay();">4 순위</a></li>
						<li><a href="javascript:showplay();">5 순위</a></li>
						<li><a href="javascript:showplay();">6 순위</a></li>
						<li><a href="javascript:showplay();">7 순위</a></li>
						<li><a href="javascript:showplay();">8 순위</a></li>
						<li><a href="javascript:showplay();">9 순위</a></li>
						<li><a href="javascript:showplay();">10 순위</a></li>
					</ol>
				</dd>
			</dl>
		</div>
		<div id="D" style="position: absolute; margin-left:380px; margin-top : 85px; background: white;"></div>
		<input name="hidTempSynopsis" type="hidden" id="hidTempSynopsis" value="0"> <!-- value 체크값을 위함 -->

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
			<li id="okaylogin_li"><a href="/Stock_Insigh/doStock">종목조회</a></li>
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
			<li><a id="yellow" href="main.jsp">메인화면</a></li> &nbsp; &nbsp;
			<li><a href="/Stock_Insigh/doStock">종목조회</a></li> &nbsp; &nbsp;
			<li><a href="/Stock_Insigh/doSearchInterest">관심종목</a></li> &nbsp;
			&nbsp;
			<li><a href="login.jsp">마이페이지</a></li> &nbsp; &nbsp;
			<li><a href="/Stock_Insigh/postList?pageIndex=1">문의하기</a></li>
		</ul>

		<% 
		}
		%>
	</div>

	<div>
		<header>
			<center>
				<img src="title_ver2.png" id="slide"
					style="width: 1200; height: auto";">
			</center>
		</header>
	</div>
	<section id="content">

		<nav>
			실시간 차트<br /> <img class="bar" src="bar.jpg"
				style="padding-top: 20px; width: 121px; height: 10px; float: center;">
		</nav>

		<div class="main">
			<div class="kos_char">KOSPI 차트</div>
			<div class="kod_char">KOSDAQ 차트</div>
		</div>

	</section>

	<footer>
		<p>​© 2020 본 홈페이지의 모든 권리는 베짱이찬가에 귀속됩니다.</p>
	</footer>

</body>
</html>
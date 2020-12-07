<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Stock Insight</title>
<link rel="stylesheet" type="text/css" href="style.css?ver=1.1">
<!--design 폴더 내에 있는 css-->
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

<script type="text/javascript">
   var n = 0;
   var imgs = new Array("title_ver3_1.png","title_ver3_2.png");

   function rotate() {
      document.images.slide.src = imgs[n];
      (n == (imgs.length - 1)) ? n = 0 : n++;
      setTimeout("rotate()", 800);
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
>>>>>>> 97a5790ac4d49c14ba6c02d3a69b053dba496868
<script>
   function validate() {
      var re = /^[a-zA-Z0-9]{4,12}$/ // 아이디와 패스워드가 적합한지 검사할 정규식
      var re2 = /^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$/i;
      // 이메일이 적합한지 검사할 정규식

      var id = document.getElementById("id");
      var pw = document.getElementById("pw");
      var email = document.getElementById("email");

      if (join.name.value == "") {
         alert("이름을 입력해 주세요");
         join.name.focus();
         return false;
      }

      if (email.value == "") {
         alert("이메일을 입력해 주세요");
         email.focus();
         return false;
      }

      if (!check(re2, email, "적합하지 않은 이메일 형식입니다.")) {
         return false;
      }

      if (!check(re, pw, "패스워드는 4~12자의 영문 대소문자와 숫자로만 입력")) {
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

      alert("회원가입이 완료되었습니다.");
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

	<script type="text/javascript">
alert("아이디가 중복됩니다.\n다시입력해주세요");
</script>

	<div class="front">
		<div class="logo">
			<a href="home.jsp"><img src="logo.png"
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
		<div id="D"
			style="position: absolute; margin-left: 380px; margin-top: 85px; background-color: #ffffffcc; font-size: 14px; font-family: 'nanum';"></div>
		<input name="hidTempSynopsis" type="hidden" id="hidTempSynopsis"
			value="0">
		<!-- value 체크값을 위함 -->

		<ul>
			<li><a id="yellow" href="login.jsp">로그인</a></li> &nbsp; &nbsp;
			<li><a href="main.jsp">메인화면</a></li> &nbsp; &nbsp;
			<li><a href="/Stock_Insigh/doStock">종목조회</a></li> &nbsp; &nbsp;
			<li><a href="/Stock_Insigh/doSearchInterest">관심종목</a></li> &nbsp;
			&nbsp;
			<li><a href="discuss.jsp">토론하기</a></li> &nbsp; &nbsp;
			<li><a href="login.jsp">마이페이지</a></li> &nbsp; &nbsp;
			<li><a href="/Stock_Insigh/postList?pageIndex=1">문의하기</a></li>
		</ul>
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
					<%
                    	// name, title, content, date, number
                    	String name = (String)request.getAttribute("name");
                    	String title = (String)request.getAttribute("title");
                    	String content = (String)request.getAttribute("content");
                    	String date = (String)request.getAttribute("date");
                    	String number = (String)request.getAttribute("number");
   
                    	out.println("<tr><td>이름 :</td><td><input type=\"text\" name=\"user_name\" id=\"name\"maxlength=\"12\"></td></tr>");
                    	out.println("<tr><td>ID :</td><td><input type=\"text\" name=\"user_id\" id=\"id\"maxlength=\"20\"></td></tr>");
                    	out.println("<tr><td>email :</td><td><input type=\"text\" name=\"user_email\" id=\"email\"maxlength=\"50\"></td></tr>");
                    	out.println("<tr><td></td><td colspan=\"2\" style=\"color: red;\">ex) stockinsight@naver.com</td>");
                    	out.println("<tr><td>비밀번호 :</td><td><input type=\"password\" name=\"user_pwd\" id=\"pw\"maxlength=\"12\"></td></tr>");
                    	out.println("<tr><td></td><td colspan=\"2\" style=\"color: red;\">※ 4-12자의 영문 대소문자와 숫자로만 입력</td>");
                      	out.println("<tr><td>비밀번호 확인 :</td><td><input type=\"password\" id=\"checkpw\"maxlength=\"12\"></td></tr>");
                    	%>
				</table>
				<br /> <br /> <input type="submit" class="okayjoin" value=" 회원가입 ">
				</td>
			</center>
		</form>
	</section>

	<footer>
		<p>© 2020 본 홈페이지의 모든 권리는 베짱이찬가에 귀속됩니다.</p>
	</footer>

</body>

</html>
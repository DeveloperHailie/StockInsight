<%@ page import="model.DBUtil"%>
<%@ page import="java.sql.Connection"%>
<%@ page import="java.sql.ResultSet"%>
<%@ page import="java.sql.SQLException"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html" charset="UTF-8">
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
   function popupOpen() {

	   var popUrl = "/Stock_Insigh/doPop"; //팝업창에 출력될 페이지 URL

      var popOption = "width=400, height=400, resizable=no, scrollbars=no, status=no;"; //팝업창 옵션(optoin)

      window.open(popUrl, "", popOption);
   }
</script>
<script>
	function input_passwd_check() {
		//var new_passwd = document.getElementById('user_pwd').value;
		var re = /^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9]).{4,12}$/;
    	// 아이디와 패스워드가 적합한지 검사할 정규식
		///^[a-zA-Z0-9]{10,15}$/
		///^[A-Za-z0-9+]{4,12}$/; 
		var re2 = /^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$/i;

		if (mypage_edit.email.value == "") {
			alert("이메일을 입력해 주세요");
			email.focus();
			return false;
		}

		if (!check(re2, email, "적합하지 않은 이메일 형식입니다.")) {
			return false;
		}
		if (mypage_edit.user_pwd.value == "") { // 비밀번호 확인 입력하지 않을때,
			alert("변경할 비밀번호를 입력해주세요.");
			mypage_edit.user_pwd.value = "";
			mypage_edit.user_pwd.focus();
			return false;
		}
		if (!check(re, user_pwd, "패스워드는 4~12자 사이의 영문 대소문자와 숫자를 포함해야 합니다.")) {
			return false;
		}
		if (mypage_edit.user_pwd_check.value == "") { // 비밀번호 확인 입력하지 않을때,
			alert("비밀번호 확인란을 입력해주세요.");
			mypage_edit.user_pwd_check.value = "";
			mypage_edit.user_pwd_check.focus();
			return false;
		}

		if (mypage_edit.user_pwd_check.value != mypage_edit.user_pwd.value) {
			alert("비밀번호가 다릅니다. 다시 확인해 주세요.");
			mypage_edit.user_pwd_check.value = "";
			mypage_edit.user_pwd_check.focus();
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
		<div class="logo">
			<a href="main.jsp"><img src="logo.png"
				style="width: 336px; height: 148px; float: left;"></a>
		</div>
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
		<div id="D" style="display:none; border-radius:20px; position: absolute;  margin-left: 380px; margin-top: 85px; padding-top: 10px; padding-left: 10px; background-color: #ffffffdd; font-size:14px; font-family: 'nanum';"></div>
		<input name="hidTempSynopsis" type="hidden" id="hidTempSynopsis"
			value="0">
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
			<li id="okaylogin_li"><a href="/Stock_Insigh/doStock">종목조회</a></li>
			&nbsp; &nbsp;
			<li id="okaylogin_li"><a href="/Stock_Insigh/doSearchInterest">관심종목</a></li>
			&nbsp; &nbsp;
			<li id="okaylogin_li"><a id="yellow" href="mypage.jsp">마이페이지</a></li> &nbsp;
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
			<li><a href="/Stock_Insigh/doStock">종목조회</a></li> &nbsp; &nbsp;
			<li><a href="/Stock_Insigh/doSearchInterest">관심종목</a></li> &nbsp; &nbsp;
			<li><a id="yellow" href="mypage.jsp">마이페이지</a></li> &nbsp; &nbsp;
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
			마이페이지<br /> <img class="bar" src="bar.jpg"
				style="padding-top: 20px; width: 121px; height: 10px; float: center;">
		</nav>

		<div class="menu_content">

			변경하고 싶은 정보를 수정한 후<br> 수정완료 버튼을 클릭하세요<br> 아이디와 이름은 변경이
			불가합니다.<br>
			<p>
				<br> <br> <br> <br> <br> <br>
			<center>
				<form name="mypage_edit" onsubmit="return input_passwd_check();"
					method="POST" action="altermypage">
					<table class="mypage_edit">


						<%
						
								String name = (String)request.getAttribute("name");
								String user_mid = (String)request.getAttribute("user_mid");
								String email = (String)request.getAttribute("email");
								String user_passwd = (String)request.getAttribute("user_passwd");

								out.println(
								"<tr><td align=right><font size=\"5\"><b>이름</b></font></td><td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td><td><font size=\"4\">"
										+ name + "</font></td></tr>");
								out.println(
								"<tr><td align=right><font size=\"5\"><b>아이디</b></font></td><td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td><td><font size=\"4\">"
										+ user_mid + "</font></td></tr>");

								// 변경가능
								out.println(
								"<tr><td align=right><font size=\"5\"><b>이메일</b></font></td><td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td><td><input type=\"text\" name = \"user_email\" id = \"email\" size=23 font size=\"4\" value="
										+ email + "></font></td></tr>");
								out.println(
								"<tr><td align=right><font size=\"5\"><b>비밀번호</b></font></td><td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td><td><input type=\"password\" name = \"user_pwd\" id = \"user_pwd\" size=23 font size=\"4\" placeholder=\"변경할 비밀번호를 입력하세요\">"
										+ "</font></td></tr>");
								out.println(
								"<tr><td align=right><font size=\"5\"><b>비밀번호 확인</b></font></td><td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td><td><input type=\"password\" name = \"user_pwd_check\" id = \"user_pwd_check\" size=23 font size=\"4\" placeholder=\"비밀번호를 한번 더 입력하세요\">"
										+ "</font></td></tr>");
						%>
					</table>
					<br> <br> <br>


					</table>
					<br> <br> <br> <input type="submit"
						class="btn_question_submit" value="수정완료" />
				</form>
		</div>
	</section>
	<footer>
		<p>​© 2020 본 홈페이지의 모든 권리는 베짱이찬가에 귀속됩니다.</p>
	</footer>

</body>
</html>
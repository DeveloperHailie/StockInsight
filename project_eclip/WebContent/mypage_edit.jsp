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
<script>
	function input_passwd_check() {
		//var new_passwd = document.getElementById('user_pwd').value;
		var re = /^[A-Za-z0-9+]{4,12}$/; 
		/^(?=.*[a-zA-Z])(?=.*[!@#$%^*+=-])(?=.*[0-9]).{8,25}$/
		"^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)[a-zA-Z\d]{8,}$"
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
		if  (mypage_edit.user_pwd.value == "") { // 비밀번호 확인 입력하지 않을때,
	         alert("변경할 비밀번호를 입력해주세요.");
	         mypage_edit.user_pwd.value = "";
	         mypage_edit.user_pwd.focus();
	         return false;
	    }
		if (!check(re, user_pwd, "패스워드는 4~12자 사이의 영문 대소문자와 숫자를 포함해야 합니다.")) {
	         return false;
	    }
		if  (mypage_edit.user_pwd_check.value == "") { // 비밀번호 확인 입력하지 않을때,
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
</head>

<body onload='rotate()'><div class="front">
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
                <li><a href="login.jsp">로그인</a></li>
                &nbsp; &nbsp; 
                <li><a id="yellow" href="main.jsp">메인화면</a></li>
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
							String session_user_id = (String) session.getAttribute("ID");
						//out.println(session.getAttribute("ID"));

						ServletContext sc = getServletContext();
						Connection conn = (Connection) sc.getAttribute("DBconnection");

						ResultSet rs = DBUtil.checkMypageinner(conn, session_user_id); //id �뜮袁㏉꺍


						try {
							if (rs.next()) { // existing user
								String name = rs.getString(2);
								String user_id = rs.getString(3);
								String email = rs.getString(4);
								String user_pwd = rs.getString(5);

								out.println(
								"<tr><td align=right><font size=\"5\"><b>이름</b></font></td><td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td><td><font size=\"4\">"
										+ name + "</font></td></tr>");
								out.println(
								"<tr><td align=right><font size=\"5\"><b>아이디</b></font></td><td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td><td><font size=\"4\">"
										+ user_id + "</font></td></tr>");

								// 변경가능
								out.println(
								"<tr><td align=right><font size=\"5\"><b>이메일</b></font></td><td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td><td><input type=\"text\" name = \"user_email\" id = \"email\" size=23 font size=\"4\" value="
										+ email + "></font></td></tr>");
								out.println(
								"<tr><td align=right><font size=\"5\"><b>비밀번호</b></font></td><td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td><td><input type=\"text\" name = \"user_pwd\" id = \"user_pwd\" size=23 font size=\"4\" placeholder=\"변경할 비밀번호를 입력하세요\">"
										+ "</font></td></tr>");
								out.println(
								"<tr><td align=right><font size=\"5\"><b>비밀번호 확인</b></font></td><td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td><td><input type=\"text\" name = \"user_pwd_check\" id = \"user_pwd_check\" size=23 font size=\"4\" placeholder=\"비밀번호를 한번 더 입력하세요\">"
										+ "</font></td></tr>");

								
						%>
					</table>
					<br> <br> <br>

					<%
						} else { // invalid user
					out.println("not invalid");
					}
					} catch (SQLException e) {
					out.println("just Fail Fail Fail....");
					e.printStackTrace();
					}
					%>

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
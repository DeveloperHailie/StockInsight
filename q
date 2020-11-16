[1mdiff --git a/project_eclip/WebContent/login.html b/project_eclip/WebContent/login.html[m
[1mindex 64b425f..07fc7f6 100644[m
[1m--- a/project_eclip/WebContent/login.html[m
[1m+++ b/project_eclip/WebContent/login.html[m
[36m@@ -38,65 +38,71 @@[m
 [m
 <body onload='rotate()'>[m
 [m
[31m-    <div class="front">[m
[31m-        <div class="logo">[m
[31m-            <a href="main.html"><img src="logo.png" style="width: 336px; height: 148px; float: left;"></a>[m
[31m-        </div>[m
[31m-        <ul>[m
[31m-            <li><a id="yellow" href="login.html">로그인</a></li> &nbsp; &nbsp;[m
[31m-            <li><a href="main.html">메인화면</a></li> &nbsp; &nbsp;[m
[31m-            <li><a href="stock.html">종목조회</a></li> &nbsp; &nbsp;[m
[31m-            <li><a href="interest.html">관심종목</a></li> &nbsp; &nbsp;[m
[31m-            <li><a href="discuss.html">토론하기</a></li> &nbsp; &nbsp;[m
[31m-            <li><a href="mypage.html">마이페이지</a></li> &nbsp; &nbsp;[m
[31m-            <li><a href="qna.html">문의하기</a></li>[m
[31m-        </ul>[m
[31m-    </div>[m
[31m-    <div>[m
[31m-        <header>[m
[31m-            <center>[m
[31m-                <img src="title_ver3_1.png" id="slide" style="width: 1200; height: auto"; >[m
[31m-            </center>[m
[31m-        </header>[m
[31m-    </div>[m
[31m-    <section id="content">[m
[31m-        <nav>[m
[31m-            로그인<br /> <img class="bar" src="bar.jpg" style="padding-top: 20px; width: 121px; height: 10px; float: center;">[m
[32m+[m	[32m<div class="front">[m
[32m+[m		[32m<div class="logo">[m
[32m+[m			[32m<a href="main.html"><img src="logo.png"[m
[32m+[m				[32mstyle="width: 336px; height: 148px; float: left;"></a>[m
[32m+[m		[32m</div>[m
[32m+[m		[32m<ul>[m
[32m+[m			[32m<li><a id="yellow" href="login.html">로그인</a></li> &nbsp; &nbsp;[m
[32m+[m			[32m<li><a href="main.html">메인화면</a></li> &nbsp; &nbsp;[m
[32m+[m			[32m<li><a href="stock.html">종목조회</a></li> &nbsp; &nbsp;[m
[32m+[m			[32m<li><a href="interest.html">관심종목</a></li> &nbsp; &nbsp;[m
[32m+[m			[32m<li><a href="discuss.html">토론하기</a></li> &nbsp; &nbsp;[m
[32m+[m			[32m<li><a href="mypage.html">마이페이지</a></li> &nbsp; &nbsp;[m
[32m+[m			[32m<li><a href="qna.html">문의하기</a></li>[m
[32m+[m		[32m</ul>[m
[32m+[m	[32m</div>[m
[32m+[m	[32m<div>[m
[32m+[m		[32m<header>[m
[32m+[m			[32m<center>[m
[32m+[m				[32m<img src="title_ver3_1.png" id="slide"[m
[32m+[m					[32mstyle="width: 1200px; height: auto"; >[m
[32m+[m			[32m</center>[m
[32m+[m		[32m</header>[m
[32m+[m	[32m</div>[m
[32m+[m	[32m<section id="content">[m
[32m+[m		[32m<nav>[m
[32m+[m			[32m로그인<br /> <img class="bar" src="bar.jpg"[m
[32m+[m				[32mstyle="padding-top: 20px; width: 121px; height: 10px; float: center;">[m
 [m
[31m-            <br /> <div style = "font-size: 20px; margin-top: 10px;">[m
[31m-                <b class="welcome">스톡인사이트 </b>에 오신 것을 환영합니다!                [m
[31m-            </div>[m
[31m-        </nav>[m
[32m+[m			[32m<br />[m
[32m+[m			[32m<div style="font-size: 20px; margin-top: 10px;">[m
[32m+[m				[32m<b class="welcome">스톡인사이트 </b>에 오신 것을 환영합니다![m
[32m+[m			[32m</div>[m
[32m+[m		[32m</nav>[m
 [m
[31m-        <div class="inner_login">[m
[31m-            <div class="login_start">[m
[31m-                <form id = "login" onsubmit="return validate();" method="POST" action="doLogin">[m
[31m-                    <fieldset>[m
[31m-                        <div class="box_login">[m
[31m-                            <div class="inp_text">[m
[31m-                                <label for="loginId" class="login_show">아이디</label> [m
[31m-                                <input type="text" id="id" name="user_id" placeholder="ID">[m
[31m-                            </div>[m
[31m-                            <div class="inp_text">[m
[31m-                                <label for="loginPw" class="login_show">비밀번호</label> [m
[31m-                                <input type="password" id="pw" name="user_pwd" placeholder="Password">[m
[31m-                            </div>[m
[31m-                        </div>[m
[31m-                        <input type="submit" class="btn_login" value="로그인"  onclick="showPopup();">[m
[31m-                    </fieldset>[m
[31m-                </form>[m
[31m-                <fieldset class= "login_under">[m
[31m-                    <a href="findId.html">ID 찾기 </a> / <a href="findPwd.html">PASSWORD 찾기</a>[m
[31m-                <a href="join.html" class = "join">회원가입</a>               [m
[31m-                [m
[31m-                </fieldset>[m
[32m+[m		[32m<div class="inner_login">[m
[32m+[m			[32m<div class="login_start">[m
[32m+[m				[32m<form id="login" onsubmit="return validate();" method="POST"[m
[32m+[m					[32maction="doLogin">[m
[32m+[m					[32m<fieldset>[m
[32m+[m						[32m<div class="box_login">[m
[32m+[m							[32m<div class="inp_text">[m
[32m+[m								[32m<label for="loginId" class="login_show">아이디</label> <input[m
[32m+[m									[32mtype="text" id="id" name="user_id" placeholder="ID">[m
[32m+[m							[32m</div>[m
[32m+[m							[32m<div class="inp_text">[m
[32m+[m								[32m<label for="loginPw" class="login_show">비밀번호</label> <input[m
[32m+[m									[32mtype="password" id="pw" name="user_pwd" placeholder="Password">[m
[32m+[m							[32m</div>[m
[32m+[m						[32m</div>[m
[32m+[m						[32m<input type="submit" class="btn_login" value="로그인"[m
[32m+[m							[32monclick="showPopup();">[m
[32m+[m					[32m</fieldset>[m
[32m+[m				[32m</form>[m
[32m+[m				[32m<fieldset class="login_under">[m
[32m+[m					[32m<a href="findId.html">ID 찾기 </a> / <a href="findPwd.html">PASSWORD[m
[32m+[m						[32m찾기</a> <a href="join.html" class="join">회원가입</a>[m
 [m
[31m-            </div>[m
[31m-        </div>[m
[31m-    </section>[m
[31m-    <footer>[m
[31m-        <p> © 2020 본 홈페이지의 모든 권리는 베짱이찬가에 귀속됩니다.</p>[m
[31m-    </footer>[m
[32m+[m				[32m</fieldset>[m
[32m+[m
[32m+[m			[32m</div>[m
[32m+[m		[32m</div>[m
[32m+[m	[32m</section>[m
[32m+[m	[32m<footer>[m
[32m+[m		[32m<p>© 2020 본 홈페이지의 모든 권리는 베짱이찬가에 귀속됩니다.</p>[m
[32m+[m	[32m</footer>[m
 [m
 </body>[m
 [m
[1mdiff --git a/project_eclip/WebContent/okayLogin.jsp b/project_eclip/WebContent/okayLogin.jsp[m
[1mindex e487a3d..ad525a7 100644[m
[1m--- a/project_eclip/WebContent/okayLogin.jsp[m
[1m+++ b/project_eclip/WebContent/okayLogin.jsp[m
[36m@@ -40,15 +40,17 @@[m
 			<a href="main.html"><img src="logo.png"[m
 				style="width: 336px; height: 148px; float: left;"></a>[m
 		</div>[m
[31m-		<ul>[m
[31m-			<li><a href="javascript:popupOpen();" id="red"><b>알림확인</b></a></li>&nbsp;&nbsp;&nbsp;[m
[31m-			<li><a href="/Stock_Insigh/doLogout"> 로그아웃 </a></li> &nbsp; &nbsp;[m
[31m-			<li><a id="yellow" href="okayLogin.jsp">메인화면</a></li> &nbsp; &nbsp;[m
[31m-			<li><a href="stock.html">종목조회</a></li> &nbsp; &nbsp;[m
[31m-			<li><a href="interest.html">관심종목</a></li> &nbsp; &nbsp;[m
[31m-			<li><a href="discuss.html">토론하기</a></li> &nbsp; &nbsp;[m
[31m-			<li><a href="mypage.html">마이페이지</a></li> &nbsp; &nbsp;[m
[31m-			<li><a href="qna.html">문의하기</a></li>[m
[32m+[m		[32m<ul class="okaylogin_ul">[m
[32m+[m			[32m<li class="okaylogin_li"><b>알림확인</b></a></li></br>[m
[32m+[m			[32m<li class="okaylogin_li"><a href="javascript:popupOpen();" id="red"><b>알림확인</b></a></li>&nbsp;&nbsp;&nbsp;[m
[32m+[m			[32m<li class="okaylogin_li"><a href="/Stock_Insigh/doLogout"> 로그아웃 </a></li> &nbsp; &nbsp;[m
[32m+[m			[32m<li class="okaylogin_li"><a id="yellow" href="okayLogin.jsp">메인화면</a></li> &nbsp; &nbsp;[m
[32m+[m			[32m<li class="okaylogin_li"><a href="stock.html">종목조회</a></li> &nbsp; &nbsp;[m
[32m+[m			[32m<li class="okaylogin_li"><a href="interest.html">관심종목</a></li> &nbsp; &nbsp;[m
[32m+[m			[32m<li class="okaylogin_li"><a href="discuss.html">토론하기</a></li> &nbsp; &nbsp;[m
[32m+[m			[32m<li class="okaylogin_li"><a href="mypage.html">마이페이지</a></li> &nbsp; &nbsp;[m
[32m+[m			[32m<li class="okaylogin_li"><a href="qna.html">문의하기</a></li>[m
[32m+[m			[32m</br>[m
 		</ul>[m
 	</div>[m
 	<div>[m
[1mdiff --git a/project_eclip/WebContent/style.css b/project_eclip/WebContent/style.css[m
[1mindex 8d9f01d..71dced5 100644[m
[1m--- a/project_eclip/WebContent/style.css[m
[1m+++ b/project_eclip/WebContent/style.css[m
[36m@@ -50,7 +50,17 @@[m [mbody {[m
 .front ul li a:hover {[m
 	color: #AAAAAA;[m
 }[m
[31m-[m
[32m+[m[32m/**jh okaylogin ul, li **/[m
[32m+[m[32m.okaylogin_ul {[m
[32m+[m	[32mmargin: 40px 40px 0px 0px;[m
[32m+[m	[32mlist-style: none;[m
[32m+[m	[32mtext-align: right;[m
[32m+[m	[32mletter-spacing: 2px;[m
[32m+[m[32m}[m
[32m+[m[32m.okaylogin_li{[m
[32m+[m	[32mtext-decoration: none;[m
[32m+[m	[32mcolor: white;[m
[32m+[m[32m}[m
 #yellow {[m
 	/**-11.10 yellow plus**/[m
 	color: #E8CE48;[m
[1mdiff --git a/project_eclip/bin/model/DBUtil.class b/project_eclip/bin/model/DBUtil.class[m
[1mindex abcba6b..373c4c9 100644[m
Binary files a/project_eclip/bin/model/DBUtil.class and b/project_eclip/bin/model/DBUtil.class differ
[1mdiff --git a/project_eclip/src/Controller/doLogout.java b/project_eclip/src/Controller/doLogout.java[m
[1mindex ef2a8cf..cdfa927 100644[m
[1m--- a/project_eclip/src/Controller/doLogout.java[m
[1m+++ b/project_eclip/src/Controller/doLogout.java[m
[36m@@ -14,14 +14,14 @@[m [mimport javax.servlet.http.HttpSession;[m
 @WebServlet("/doLogout")[m
 public class doLogout extends HttpServlet {[m
 	private static final long serialVersionUID = 1L;[m
[31m-       [m
[31m-    /**[m
[31m-     * @see HttpServlet#HttpServlet()[m
[31m-     */[m
[31m-    public doLogout() {[m
[31m-        super();[m
[31m-        // TODO Auto-generated constructor stub[m
[31m-    }[m
[32m+[m
[32m+[m	[32m/**[m
[32m+[m	[32m * @see HttpServlet#HttpServlet()[m
[32m+[m	[32m */[m
[32m+[m	[32mpublic doLogout() {[m
[32m+[m		[32msuper();[m
[32m+[m		[32m// TODO Auto-generated constructor stub[m
[32m+[m	[32m}[m
 [m
 	/**[m
 	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)[m
[36m@@ -29,14 +29,13 @@[m [mpublic class doLogout extends HttpServlet {[m
 	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {[m
 		request.setCharacterEncoding("UTF-8");[m
 		response.setContentType("text/html; charset=UTF-8");[m
[31m-		[m
[31m-		HttpSession session = request.getSession();[m
 [m
[31m-        // 해당 session을 날려버림[m
[31m-        session.invalidate();[m
[32m+[m		[32mHttpSession session = request.getSession();[m
 [m
[31m-        //다시 login.jsp 페이지로 응답[m
[31m-        response.sendRedirect("login.html");[m
[32m+[m		[32m// 해당 session을 날려버림[m
[32m+[m		[32msession.invalidate();[m
[32m+[m		[32m//다시 login.jsp 페이지로 응답[m
[32m+[m		[32mresponse.sendRedirect("login.html");[m
 	}[m
 [m
 	/**[m

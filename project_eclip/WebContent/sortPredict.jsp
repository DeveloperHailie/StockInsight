<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<!DOCTYPE html>
<html>

<head>
<meta http-equiv="Content-Type" content="text/html" charset="UTF-8">
<title>Stock Insight</title>
<link rel="stylesheet" type="text/css" href="style.css" />

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
				(n == (imgs.length - 1)) ? n=0 : n++; setTimeout("rotate()",800);
			}
        </script>
<script type="text/javascript">
   
	function popupOpen() {

		var popUrl = "/Stock_Insigh/doPop"; //팝업창에 출력될 페이지 URL

		var popOption = "width=400, height=400, resizable=no, scrollbars=no, status=no;"; //팝업창 옵션(optoin)

		window.open(popUrl, "", popOption);
	}
</script>
<%

if (session.getAttribute("ID") == null) {
	out.print("<h1> 로그인 후 이용해주세요. </h1>");
	out.print("<script>");
	out.print("alert(\"로그인 후 이용해주세요\"); location.href = \"login.jsp\"; ");
	out.print("</script>");
}
%>
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

	<!-- 차트 그리기 -->
	<script type="text/javascript"
		src="https://www.gstatic.com/charts/loader.js"></script>

	<script type="text/javascript">
		//Google Stuff
		google.charts.load('current', {
			packages : [ 'corechart' ]
		});
		google.charts.setOnLoadCallback(function() {
			gap_draw();
		});
	</script>
	<script type="text/javascript">
	function gap_draw() {
		<%
		String[][] top = (String[][])request.getAttribute("top");
		String[][] down = (String[][])request.getAttribute("down");
		
		String topStr = "";
		String downStr = "";
		String top_perStr ="";
		String down_perStr ="";
		
		for(int k = 1; k <= 5; k++){
			if(k <=4){
				topStr += top[k-1][1] + "@";
				downStr += down[k-1][1] + "@";
				top_perStr += top[k-1][2] + "@";
				down_perStr += down[k-1][2] + "@";
			}
			else{
				topStr += top[k-1][1];
				downStr += down[k-1][1];
				top_perStr += top[k-1][2];
				down_perStr += down[k-1][2];
			}
		}%>
		var topStr = "<%=topStr%>";
		var downStr = "<%=downStr%>";
		var top_perStr = "<%=top_perStr%>";
		var down_perStr = "<%=down_perStr%>";
		
		//var plus_row = [];
		var all_row = [];
		var j = 0;
		
		var top_name = topStr.split("@");
		var top_per = top_perStr.split("@");
		
		for(var i = 1; i<=5; i++){
			var plus_row = [];
			
			console.log("야" + top_name[i-1] + " "+ top_per[i-1]);
			plus_row.push(top_name[i-1]);
			// float 바꿔주기
			plus_row.push(parseFloat(top_per[i-1]));
			plus_row.push('red');
			all_row.push(plus_row);
		}
		
		var down_name = downStr.split("@");
		var down_per = down_perStr.split("@");
		
		for(var i = 4; i>=0; i--){
			var plus_row = [];
			
			plus_row.push(down_name[i]);
			// float 바꿔주기
			plus_row.push(parseFloat(down_per[i]));	
			plus_row.push('blue');
			all_row.push(plus_row);
		}

		// [[,],[,]]
		var data = new google.visualization.DataTable();
		data.addColumn('string', 'companyName');
		data.addColumn('number', 'percent');
		data.addColumn({type:'string', role:'style'});
		data.addRows(all_row);

		var view = new google.visualization.DataView(data);
	     view.setColumns([0, 1,
	                      { calc: "stringify",
	                        sourceColumn: 1,
	                        type: "string",
	                        role: "annotation" },
	                      2]);
	     
		var options = null;
		var options = {
			hAxis : {
				title : 'Percent(%)',
				textPosition: 'none'
			},
			vAxis : {
				title : 'Company'
			}
		};
		var chart = new google.visualization.BarChart(document.getElementById("gap_chart"));
		chart.draw(data, options);
	};
	</script>
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
	

	<div class="front">
		<div class="logo">
			<a href="main.jsp"><img src="logo.png"
				style="width: 336px; height: 148px; float: left;"></a>
		</div>

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
			<li id="okaylogin_li"><a  id="yellow" href="/Stock_Insigh/sortPredict">🥇예측순위</a></li>&nbsp;&nbsp;&nbsp;
			<li id="okaylogin_li"><a href="/Stock_Insigh/doLogout"> 로그아웃
			</a></li> &nbsp; &nbsp;
			<li id="okaylogin_li"><a href="main.jsp">메인화면</a></li>
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
			<li><a href="login.jsp">로그인</a></li> &nbsp; &nbsp;
			<li><a href="main.jsp">메인화면</a></li> &nbsp; &nbsp;
			<li><a href="/Stock_Insigh/doStock">종목조회</a></li> &nbsp; &nbsp;
			<li><a id="yellow" href="/Stock_Insigh/doSearchInterest">관심종목</a></li> &nbsp; &nbsp;
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
			예상 급등/급락 항목<br /> <img class="bar" src="bar.jpg"
				style="padding-top: 20px; width: 121px; height: 10px; float: center;">
		</nav>

	
		<div class="main">
			<table style="width: 90%; margin-left: auto; margin-right: auto; ">
				
				<tr >
					<td >
					<div id="gap_chart"
							style="border: 1px solid black; width:700px; height: 600px;margin-left: auto; margin-right: auto; margin-top:20px; margin-bottom:10px;"></div>
					</td>
					<td >
						<div id="gap_list">
							<div id="up_list" style="padding:50px; font-size:25px;">
								<div style="margin-bottom:10px; font-size:30px;"><b>👍 예상 <span style="color:red;">급상승</span> 종목</b></div>
								<%
								//String[][] top = (String[][])request.getAttribute("top");
								//String[][] down = (String[][])request.getAttribute("down");
								for(int j=1;j<=5;j++){
									String path =  "/Stock_Insigh/getRankInfo?companyName="+top[j-1][1];
									out.print("<a href='"+path+"'> "+j+". "+top[j-1][1]+"<a/><br/> ");
								}
								%>
							</div>
							<div id="down_list" style="padding:50px; font-size:25px;">
								<div style="font-size:30px;"><b>👎 예상 <span style="color:blue;">급하강</span> 종목</b></div>
								<%
								for(int j=1;j<=5;j++){
									String path =  "/Stock_Insigh/getRankInfo?companyName="+down[j-1][1];
									out.print("<a href='"+path+"'> "+j+". "+down[j-1][1]+"<a/><br/> ");
								}
								%>
							</div>
							
						</div>
					</td>					
				</tr>
				
				
			</table>
		</div>

	</section>

	<footer>
		<p>​© 2020 본 홈페이지의 모든 권리는 베짱이찬가에 귀속됩니다.</p>
	</footer>

</body>
</html>
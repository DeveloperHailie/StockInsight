<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.sql.*"%>
<%@page import="model.DBUtil"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>팝업 알림 안내</title>
</head>
<style type="text/css">
body {
	text-align: center;
	display: block;
	margin: 0 auto;
	font-size: 16px;
	color: #999;
}

h1 {
	font-family: 'Oswald', sans-serif;
	font-size: 30px;
	color: #216182;
}

label {
	display: block;
	margin-top: 20px;
	letter-spacing: 2px;
}

form {
	margin: 0 auto;
	width: 459px;
}

input, textarea {
	width: 250px;
	height: 27px;
	background-color: #efefef;
	border-radius: 6px;
	border: 1px solid #dedede;
	padding: 10px;
	margin: 3px;
	font-size: 0.9em;
	color: #3a3a3a;
}

#info {
	width: 250px;
	height: 27px;
	background-color: #efefef;
	border-radius: 6px;
	border: 1px solid #dedede;
	padding: 10px;
	margin: 3px;
	font-size: 0.9em;
	color: #3a3a3a;
	text-align: center;
	font-weight: bold;
}

input:focus, textarea:focus {
	border: 1px solid #97d6eb;
}

textarea {
	height: 60px;
	background-color: #efefef;
}

#submit {
	width: 127px;
	height: 48px;
	text-align: center;
	border: none;
	margin-top: 20px;
	cursor: pointer;
}

#submit:hover {
	color: #fff;
	background-color: #216282;
	opacity: 0.9;
}

#okay {
	width: 80px;
	height: 30px;
	text-align: center;
	border: none;
	border-radius: 3px;
}

#okay:hover {
	border-radius: 3px;
	font-family: "nanum";
	font-size: 14px;
	color: #fff;
	background: linear-gradient(to right, #B06AB3, #4568DC);
	background: -webkit-linear-gradient(to right, #B06AB3, #4568DC);
	border: none;
	opacity: 0.9;
}

.modal {
	position: fixed;
	left: 0;
	top: 0;
	width: 100%;
	height: 100%;
	background-color: rgba(0, 0, 0, 0.5);
	opacity: 0;
	visibility: hidden;
	transform: scale(1.1);
	transition: visibility 0s linear 0.25s, opacity 0.25s 0s, transform
		0.25s;
}

.modal-content {
	position: absolute;
	top: 45%;
	left: 50%;
	transform: translate(-50%, -50%);
	background-color: white;
	padding: 1rem 1.5rem;
	width: 300px;
	height: 350px;
	border-radius: 0.5rem;
}

.close-button {
	float: right;
	width: 1.5rem;
	line-height: 1.5rem;
	text-align: center;
	cursor: pointer;
	border-radius: 0.25rem;
	background-color: lightgray;
}

.close-button:hover {
	background-color: darkgray;
}

.show-modal {
	opacity: 1;
	visibility: visible;
	transform: scale(1.0);
	transition: visibility 0s linear 0s, opacity 0.25s 0s, transform 0.25s;
}
</style>

<body>        
	
	<% 
	String increase = (String)request.getAttribute("increase");
	String decrease = (String)request.getAttribute("decrease");
	String increase_gap = (String)request.getAttribute("increase_gap");
	String decrease_gap = (String)request.getAttribute("decrease_gap");
   	System.out.println(increase);
   	System.out.println(decrease);
   	%>
    

	<div class="modal-content"><center>
		<h1 class="title"><span style="color:#E8CE48">급등·락</span> 예상 종목</h1>
		<span style="color:black; font-size: small;">당신의 관심종목 중 가격이 가장</span> 
		<br>
		<span style="color:black; font-size: small;">급변할 것으로 예상되는 종목을 확인하세요! </span>
		<label>급등 종목</label>
		<%if(increase_gap != null){out.println("<div id=\"info\" name=\"increase\">"+increase+" <sapn style=\"color:red;\"> ▲ </span>"+increase_gap+" 예상</div>");} else {out.println(increase);}; %> <label></label>
		<label>급락 종목</label> 
		<%if(decrease_gap != null){out.println("<div id=\"info\" name=\"decrease\">"+decrease+" <sapn style=\"color:blue;\"> ▼ </span>"+decrease_gap+" 예상</div>");} else {out.println(decrease);}; %> <label></label>
		
		<%if((increase_gap== null)&&(decrease_gap== null)){out.println(" <sapn style=\"color:black; font-size:small;\"> 관심종목이 없습니다.</br> 관심종목을 먼저 추가해주세요 <br/><br/></span>");}%>
		<button type="button" id="okay" onClick="window.close()">확인</button><br><br><label></label></center>
	</div>
</body>
</html>


<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" type="text/css" href="css/StyleCss.css">
<meta charset="UTF-8">
<title>予約完了画面</title>
</head>
<body>

<div class="test1">
<br><br><br>
<p class="lead-form">予約が完了しました。</p>
<br><br>ご利用ありがとうございます。
<br><br><br>

<% String yoyakuId = (String)request.getAttribute("yoyakuId"); %>

<h1>予約ID：<%= yoyakuId %></h1>

<br>
<br>

	<% 
  
		int pri = (int)session.getAttribute("userPrivilege");
  
   if(pri == 0){
  %>


 <input type="button" value="メニューへ" class="listbutton" onclick="location.href='AdminMypage.jsp'">
<% }else{ %>
<input type="button" value="メニューへ" class="listbutton" onclick="location.href='UserMypage.jsp'">

<% } %>
<br><br><br><br></div>
</body>
</html>
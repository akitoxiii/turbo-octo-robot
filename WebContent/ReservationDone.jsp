<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>予約完了画面</title>
</head>
<body>

予約が完了しました。
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
<br>
</body>
</html>
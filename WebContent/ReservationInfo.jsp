<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
     <%@page import="model.ReservationBean"%>
     
<!DOCTYPE html>
<html>
<head>

<link rel="stylesheet" type="text/css" href="css/StyleCss.css">
<meta charset="UTF-8">


<title>予約詳細画面</title>
</head>
<body>

<% 
 ReservationBean contentBean = (ReservationBean)request.getAttribute("contentBean");

	%>
	<br>
	<br>
	<div class="item">
	
	<p class="label">予約ID</p>
	
	<p class="inputs"><%=contentBean.getReservationId()%></p>
	
	
		<p class="label">会員ID</p>

		<p class="inputs"><%=contentBean.getUserId()%></p>

		<p class="label">予約日</p>

		<p class="inputs"><%=contentBean.getReservationDate()%></p>

		<p class="label">予約時間</p>

		<p class="inputs"><%=contentBean.getReservationTime() + ":00"%></p>
		
		
		
		
		<a href="Reservation?action=ok">予約</a>
		<br>
	<br>
	<a href="Reservation">やりなおす</a>
	

	</div>




</body>
</html>
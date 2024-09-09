<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@page import="model.ReservationBean"%>
<%@page import="model.UserIdBean"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<link rel="stylesheet" type="text/css" href="css/StyleCss.css">

<title>予約確認画面</title>
</head>
<body>
<div class="test1">
	<%
	ReservationBean reseBean = (ReservationBean) session.getAttribute("reseBean");
	%>

	<%

	%>
	<br>
	<br>
	<p class="lead-form">予約情報をご確認ください</p>

	<br>
	<br>
	<div class="item">
		<p class="label">会員ID</p>

		<p class="inputs"><%=reseBean.getUserId()%></p><br>

		<p class="label">予約日</p>

		<p class="inputs"><%=reseBean.getReservationDate()%></p><br>

		<p class="label">予約時間</p>

		<p class="inputs"><%=reseBean.getReservationTime() + ":00"%></p><br>
		
	
		
		<br><br>
		<a href="Reservation?action=ok">予約</a>
		<br>
	<br><br>
	<a href="Reservation">やりなおす</a>
	<br>
	<br><br>
	<br><br>
	<br><br>
	

	</div></div>







</body>
</html>
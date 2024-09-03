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
	<%
	ReservationBean reseBean = (ReservationBean) session.getAttribute("reseBean");
	%>

	<%

	%>

	<br>
	<br>
	<div class="item">
		<p class="label">会員ID</p>

		<p class="inputs"><%=reseBean.getUserId()%></p>

		<p class="label">予約日</p>

		<p class="inputs"><%=reseBean.getReservationDate()%></p>

		<p class="label">予約時間</p>

		<p class="inputs"><%=reseBean.getReservationTime() + ":00"%></p>
		
		<p class="inputs"><%=reseBean.getReservationId()%></p>
		
		
		<a href="Reservation?action=ok">予約</a>
		<br>
	<br>
	<a href="Reservation">やりなおす</a>
	

	</div>







</body>
</html>
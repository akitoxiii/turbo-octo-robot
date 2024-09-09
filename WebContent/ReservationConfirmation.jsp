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
		<br> <br> 予約情報をご確認ください <br> <br>
		<div class="item">
			<p class="label">会員ID</p>

			<p class="inputs"><%=reseBean.getUserId()%></p>
			<br>

			<p class="label">予約日</p>

			<p class="inputs"><%=reseBean.getReservationDate()%></p>
			<br>

			<p class="label">予約時間</p>

			<p class="inputs"><%=reseBean.getReservationTime() + ":00"%></p>
			<br> <br> <br>
			<form action="Reservation" method="get">
				<input type="hidden" name="action" value="ok">
				<button type="submit" class="btn">予約</button>
			</form>
			<br> <br>
			<form action="Reservation" method="get">
				<button type="submit" class="btn">やりなおす</button>
			</form>
			<br> <br> <br> <br> <br>


		</div>
	</div>







</body>
</html>
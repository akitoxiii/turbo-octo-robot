<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Forest 予約システム - マイページ</title>
<link rel="stylesheet" type="text/css" href="css/StyleCss.css">
</head>
<body>
	<div class="mypage-container">
		<h1>Forest 予約システム</h1>
		<div class="user-info">
			<p>
				ID:
				<%=session.getAttribute("userId")%></p>
			<p><%=session.getAttribute("userName")%></p>
		</div>
		<form action="reservationServlet" method="post">
			<input type="button" value="予約する"
				onclick="location.href='Reservation.jsp'"> <input
				type="button" value="予約確認"
				onclick="location.href='myReservationList'">
		</form>
		<!-- ログアウトリンクをLogoutServletに設定 -->
		<form action="LogoutServlet" method="post">
			<input type="submit" value="ログアウト">
		</form>
	</div>
</body>
</html>
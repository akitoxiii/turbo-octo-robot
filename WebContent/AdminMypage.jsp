<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Forest 予約システム - 管理者マイページ</title>
<link rel="stylesheet" type="text/css" href="css/StyleCss.css">
</head>
<body>
	<div class="admin-mypage-container">
		<h1>Forest 予約システム</h1>
		<div class="user-info">
			<p>
				ID:
			<%=session.getAttribute("userId")%></p>
			<p><%=session.getAttribute("userName")%></p>
		</div>
		<form action="ReservationListCon" method="get">
			<input type="submit" value="全予約一覧">
		</form>
		<form action="reservationServlet" method="post">
			<input type="button" value="予約する"
				onclick="location.href='Reservation.jsp'">
		</form>
		<form action="CustomerListServlet" method="get">
			<input type="submit" value="会員一覧 (CSV出力)">
		</form>
		<form action="AdminMypageServlet" method="post">
			<input type="hidden" name="action" value="register"> <input
				type="submit" value="会員登録">
		</form>
		<!-- ログアウトリンクをLogoutServletに設定 -->
		<form action="LogoutServlet" method="post">
			<input type="submit" value="ログアウト">
		</form>
	</div>
</body>
</html>
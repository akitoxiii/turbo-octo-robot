<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Forest 予約システム - 登録完了</title>
<style>
body {
	font-family: Arial, sans-serif;
}

.complete-container {
	width: 400px;
	margin: 0 auto;
	padding: 30px;
	border: 1px solid #ccc;
	border-radius: 10px;
	background-color: #f9f9f9;
	text-align: center;
}

.complete-container h1 {
	font-size: 24px;
	margin-bottom: 20px;
}

.complete-container p {
	font-size: 18px;
	margin: 10px 0;
}

.complete-container button {
	width: 100%;
	padding: 10px;
	margin: 10px 0;
	background-color: #4CAF50;
	border: none;
	color: white;
	border-radius: 5px;
	cursor: pointer;
}

.complete-container button:hover {
	background-color: #45a049;
}
</style>
</head>
<body>
	<div class="complete-container">
		<h1>登録が完了しました。</h1>
		<p>
			会員ID:
			<%=session.getAttribute("userId")%></p>
		<p>ご利用ありがとうございます。</p>

		<button onclick="location.href='login.jsp'">ログイン画面へ</button>
	</div>
</body>
</html>
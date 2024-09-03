<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Forest 予約システム - ログイン</title>
<link rel="stylesheet" type="text/css" href="css/StyleCss.css">
<style>
body {
	font-family: Arial, sans-serif;
}

.login-container {
	width: 300px;
	margin: 0 auto;
	padding: 30px;
	border: 1px solid #ccc;
	border-radius: 10px;
	background-color: #f9f9f9;
	text-align: center;
}

.login-container h1 {
	font-size: 24px;
	margin-bottom: 20px;
}

.login-container input[type="text"], .login-container input[type="password"]
	{
	width: 100%;
	padding: 10px;
	margin: 10px 0;
	border: 1px solid #ccc;
	border-radius: 5px;
}

.login-container input[type="submit"], .login-container input[type="button"]
	{
	width: 100%;
	padding: 10px;
	margin: 10px 0;
	background-color: #4CAF50;
	border: none;
	color: white;
	border-radius: 5px;
	cursor: pointer;
}

.login-container input[type="button"] {
	background-color: #008CBA;
}

.error-message {
	color: red;
	margin-bottom: 10px;
}
</style>
</head>
<body>
	<div class="login-container">
		<h1>Forest 予約システム</h1>
		<%-- エラーメッセージ表示 --%>
		<div class="error-message">
			<%=request.getAttribute("loginError") != null ? request.getAttribute("loginError") : ""%>
		</div>
		<form action="LoginCon" method="post">
			<input type="text" name="loginId" placeholder="ログインID"
				value="<%=request.getParameter("loginId") != null ? request.getParameter("loginId") : ""%>"
				required> <input type="password" name="loginPassword"
				placeholder="ログインパスワード" required> <input type="submit"
				value="ログイン">
		</form>
		<form action="registerServlet" method="get">
			<input type="button" value="新規登録"
				onclick="location.href='UserRegister.jsp'">
		</form>
	</div>
</body>
</html>
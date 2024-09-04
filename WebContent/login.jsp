<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Forest 予約システム - ログイン</title>
<link rel="stylesheet" type="text/css" href="css/StyleCss.css">
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
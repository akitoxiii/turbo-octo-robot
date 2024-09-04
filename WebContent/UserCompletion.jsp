<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Forest 予約システム - 登録完了</title>
<link rel="stylesheet" type="text/css" href="css/StyleCss.css">
</head>
<body>
	<div class="complete-container">
		<h1>登録が完了しました。</h1>
		<p>
			会員ID:
			<%=request.getAttribute("userId")%></p>
		<p>ご利用ありがとうございます。</p>

		<button onclick="location.href='login.jsp'">ログイン画面へ</button>
	</div>
</body>
</html>
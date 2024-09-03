<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Forest 予約システム - 新規会員情報確認画面</title>
<link rel="stylesheet" type="text/css" href="css/StyleCss.css">
<style>
body {
	font-family: Arial, sans-serif;
}

.confirm-container {
	width: 400px;
	margin: 0 auto;
	padding: 30px;
	border: 1px solid #ccc;
	border-radius: 10px;
	background-color: #f9f9f9;
}

.confirm-container h1 {
	text-align: center;
	font-size: 24px;
	margin-bottom: 20px;
}

.confirm-container label {
	display: block;
	margin: 10px 0 5px;
	font-weight: bold;
}

.confirm-container p {
	margin: 5px 0 15px;
	padding: 8px;
	background-color: #f1f1f1;
	border-radius: 5px;
	border: 1px solid #ccc;
}

.confirm-container input[type="button"], .confirm-container input[type="submit"]
	{
	width: 48%;
	padding: 10px;
	margin: 10px 1%;
	background-color: #4CAF50;
	border: none;
	color: white;
	border-radius: 5px;
	cursor: pointer;
}

.confirm-container input[type="button"]:hover, .confirm-container input[type="submit"]:hover
	{
	background-color: #45a049;
}
</style>
</head>
<body>
	<div class="confirm-container">
		<h1>新規会員情報確認画面</h1>

		<label>メールアドレス</label>
		<p><%=request.getAttribute("userMailAddress")%></p>

		<label>パスワード</label>
		<p><%=request.getAttribute("password")%></p>

		<label>名前</label>
		<p><%=request.getAttribute("userName")%></p>

		<label>住所</label>
		<p><%=request.getAttribute("userAddress")%></p>

		<label>電話番号</label>
		<p><%=request.getAttribute("userPhoneNumber")%></p>

		<div style="text-align: center;">
			<input type="button" value="戻る" onclick="history.back();">
			<%-- サーブレット UserCompletionServlet へデータを送信する --%>
			<form action="UserCompletionServlet" method="post">
				<%-- 隠しフィールドを使ってデータを渡す --%>
				<input type="hidden" name="userMailAddress"
					value="<%=request.getAttribute("userMailAddress")%>"> <input
					type="hidden" name="password"
					value="<%=request.getAttribute("password")%>"> <input
					type="hidden" name="userName"
					value="<%=request.getAttribute("userName")%>"> <input
					type="hidden" name="userAddress"
					value="<%=request.getAttribute("userAddress")%>"> <input
					type="hidden" name="userPhoneNumber"
					value="<%=request.getAttribute("userPhoneNumber")%>"> <input
					type="submit" value="登録">
			</form>
		</div>
	</div>
</body>
</html>
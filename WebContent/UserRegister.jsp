<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Forest 予約システム - 新規会員登録</title>
<link rel="stylesheet" type="text/css" href="css/StyleCss.css">
</head>
<body>
	<div class="register-container">
		<h1>新規会員登録画面</h1>
		<p>会員情報を入力してください</p>

		<%-- エラーメッセージ表示 --%>
		<div class="error-message">
			<%=request.getAttribute("userError") != null ? request.getAttribute("userError") : ""%>
		</div>

		<%-- サーブレット UserRegisterServlet へデータを送信する --%>
		<form action="UserRegisterServlet" method="post">
			<label for="email">メールアドレス</label> <input type="email" id="email"
				name="userMailAddress" placeholder="メールアドレス" required> <label
				for="password">パスワード</label> <input type="password" id="password"
				name="password" placeholder="パスワード" required> <label
				for="confirm-password">パスワード確認</label> <input type="password"
				id="confirm-password" name="confirmPassword" placeholder="パスワード確認"
				required> <label for="name">名前</label> <input type="text"
				id="name" name="userName" placeholder="名前" required> <label
				for="address">住所</label> <input type="text" id="address"
				name="userAddress" placeholder="住所" required> <label
				for="phone">電話番号</label> <input type="text" id="phone"
				name="userPhoneNumber" placeholder="電話番号" required>

			<div style="text-align: center;">
				<input type="button" value="戻る" onclick="history.back();"> <input
					type="submit" value="次へ">
			</div>
		</form>
	</div>
</body>
</html>
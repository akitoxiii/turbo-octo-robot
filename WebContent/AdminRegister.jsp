<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Forest 予約システム - 新規管理者登録</title>
<link rel="stylesheet" type="text/css" href="css/StyleCss.css">
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script>
	$(document)
			.ready(
					function() {
						$("form")
								.submit(
										function(event) {
											// メールアドレス形式のチェック
											var email = $("#email").val();
											var emailPattern = /^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,6}$/;
											if (!emailPattern.test(email)) {
												alert("正しいメールアドレスを入力してください。");
												event.preventDefault();
											}

											// パスワードの一致確認
											var password = $("#password").val();
											var confirmPassword = $(
													"#confirm-password").val();
											if (password !== confirmPassword) {
												alert("パスワードが一致しません。");
												event.preventDefault();
											}

											// 電話番号の形式チェック（数字のみ許可）
											var phone = $("#phone").val();
											var phonePattern = /^[0-9]+$/;
											if (!phonePattern.test(phone)) {
												alert("正しい電話番号を入力してください。");
												event.preventDefault();
											}
										});
					});
</script>
</head>
<body>
	<div class="register-container">
		<h1>新規管理者登録画面</h1>
		<p>会員情報を入力してください</p>

		<div class="error-message">
			<%=request.getAttribute("userError") != null ? request.getAttribute("userError") : ""%>
		</div>

		<form action="AdminRegisterServlet" method="post">
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
				name="userPhoneNumber" placeholder="電話番号" required> <label
				for="privilege">管理権限</label> <select id="privilege" name="privilege"
				required>
				<option value="0">顧客</option>
				<option value="1">管理者</option>
			</select>

			<div style="text-align: center;">
				<input type="button" value="戻る" onclick="history.back();"> <input
					type="submit" value="次へ">
			</div>
		</form>
	</div>
</body>
</html>
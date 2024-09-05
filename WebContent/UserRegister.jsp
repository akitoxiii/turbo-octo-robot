<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Forest 予約システム - 新規会員登録</title>
<link rel="stylesheet" type="text/css" href="css/StyleCss.css">
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script>
	$(document)
			.ready(
					function() {
						$("form")
								.submit(
										function(event) {
											var isValid = true;

											// メールアドレス形式のチェック
											var email = $("#email").val();
											var emailPattern = /^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,6}$/;
											if (!emailPattern.test(email)) {
												$("#email-error").text(
														"正しいメールアドレスを入力してください。");
												isValid = false;
											} else {
												$("#email-error").text("");
											}

											// パスワードの一致確認
											var password = $("#password").val();
											var confirmPassword = $(
													"#confirm-password").val();
											if (password !== confirmPassword) {
												$("#confirm-password-error")
														.text("パスワードが一致しません。");
												isValid = false;
											} else {
												$("#confirm-password-error")
														.text("");
											}

											// 電話番号の形式チェック（ハイフン許可）
											var phone = $("#phone").val();
											var phonePattern = /^[0-9-]+$/;
											if (!phonePattern.test(phone)) {
												$("#phone-error").text(
														"正しい電話番号を入力してください。");
												isValid = false;
											} else {
												$("#phone-error").text("");
											}

											// 最終的なフォーム送信時のバリデーション
											var emailError = $("#email-error")
													.text();
											var passwordError = $(
													"#confirm-password-error")
													.text();
											var phoneError = $("#phone-error")
													.text();

											if (emailError || passwordError
													|| phoneError) {
												alert("入力にエラーがあります。修正してください。");
												event.preventDefault();
											}

											if (!isValid) {
												event.preventDefault();
											}
										});

						// パスワード確認フィールドでリアルタイムチェック
						$("#confirm-password")
								.on(
										"input",
										function() {
											var password = $("#password").val();
											var confirmPassword = $(
													"#confirm-password").val();
											if (password !== confirmPassword) {
												$("#confirm-password-error")
														.text("パスワードが一致しません。");
											} else {
												$("#confirm-password-error")
														.text("");
											}
										});

						// パスワード確認フィールドからフォーカスが外れたときにチェック
						$("#confirm-password")
								.on(
										"blur",
										function() {
											var password = $("#password").val();
											var confirmPassword = $(
													"#confirm-password").val();
											if (password !== confirmPassword) {
												$("#confirm-password-error")
														.text("パスワードが一致しません。");
											} else {
												$("#confirm-password-error")
														.text("");
											}
										});
					});
</script>
</head>
<body>
	<div class="register-container">
		<h1>新規会員登録画面</h1>
		<p>会員情報を入力してください</p>

		<div class="error-message">
			<%=request.getAttribute("userError") != null ? request.getAttribute("userError") : ""%>
		</div>

		<form action="UserRegisterServlet" method="post">
			<label for="email">メールアドレス</label> <input type="email" id="email"
				name="userMailAddress" placeholder="メールアドレス" required> <span
				id="email-error" style="color: red;"></span><br> <label
				for="password">パスワード</label> <input type="password" id="password"
				name="password" placeholder="パスワード" required> <label
				for="confirm-password">パスワード確認</label> <input type="password"
				id="confirm-password" name="confirmPassword" placeholder="パスワード確認"
				required> <span id="confirm-password-error"
				style="color: red;"></span><br> <label for="name">名前</label> <input
				type="text" id="name" name="userName" placeholder="名前" required>

			<label for="address">住所</label> <input type="text" id="address"
				name="userAddress" placeholder="住所" required> <label
				for="phone">電話番号</label> <input type="text" id="phone"
				name="userPhoneNumber" placeholder="電話番号" required> <span
				id="phone-error" style="color: red;"></span><br>

			<div style="text-align: center;">
				<input type="button" value="戻る" onclick="history.back();"> <input
					type="submit" value="次へ">
			</div>
		</form>
	</div>
</body>
</html>
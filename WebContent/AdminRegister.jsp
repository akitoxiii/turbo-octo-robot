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
						// メールアドレスフィールドがフォーカスを外れたときにバリデーションを実行
						$("#email")
								.on(
										"blur",
										function() {
											var email = $(this).val();
											var emailPattern = /^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,6}$/;
											if (!emailPattern.test(email)) {
												$("#email-error").text(
														"正しいメールアドレスを入力してください。");
											} else {
												$("#email-error").text("");
											}
										});

						// 電話番号フィールドがフォーカスを外れたときにバリデーションを実行
						$("#phone").on("blur", function() {
							var phone = $(this).val();
							var phonePattern = /^[0-9-]+$/;
							if (!phonePattern.test(phone)) {
								$("#phone-error").text("正しい電話番号を入力してください。");
							} else {
								$("#phone-error").text("");
							}
						});

						// パスワード確認フィールドのフォーカスが外れた時にチェック
						$("#confirm-password")
								.on(
										"blur",
										function() {
											var password = $("#password").val();
											var confirmPassword = $(
													"#confirm-password").val();

											// パスワードが空ではないかチェックしてからバリデーションを行う
											if (password !== ""
													&& confirmPassword !== "") {
												if (password !== confirmPassword) {
													$("#confirm-password-error")
															.text(
																	"パスワードが一致しません。");
												} else {
													$("#confirm-password-error")
															.text("");
												}
											}
										});

						// フォーム送信時の最終バリデーション
						$("form")
								.submit(
										function(event) {
											var isValid = true;

											// メールアドレスの最終チェック
											var email = $("#email").val();
											var emailPattern = /^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,6}$/;
											if (!emailPattern.test(email)) {
												$("#email-error").text(
														"正しいメールアドレスを入力してください。");
												isValid = false;
											} else {
												$("#email-error").text("");
											}

											// 電話番号の最終チェック
											var phone = $("#phone").val();
											var phonePattern = /^[0-9-]+$/;
											if (!phonePattern.test(phone)) {
												$("#phone-error").text(
														"正しい電話番号を入力してください。");
												isValid = false;
											} else {
												$("#phone-error").text("");
											}

											if (!isValid) {
												event.preventDefault(); // フォーム送信を防止
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
			<p>
				ID:
				<%=request.getAttribute("userId")%></p>
			<p><%=request.getAttribute("userName")%></p>
		</div>

		<form action="AdminRegisterServlet" method="post">
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
				id="phone-error" style="color: red;"></span><br> <label
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
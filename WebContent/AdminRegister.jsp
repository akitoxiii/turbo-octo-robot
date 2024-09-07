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
											if (!emailPattern.test(email)
													&& email !== "") {
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

						// パスワードフィールドがフォーカスを外れたときにバリデーションを実行
						$("#password").on(
								"blur",
								function() {
									var password = $(this).val();
									if (password.length < 7) {
										$("#password-error").text(
												"パスワードは7文字以上で入力してください。");
									} else {
										$("#password-error").text("");
									}
								});

						// パスワード確認フィールドがフォーカスを外れた時にバリデーションを実行
						$("#confirm-password")
								.on(
										"blur",
										function() {
											var password = $("#password").val();
											var confirmPassword = $(
													"#confirm-password").val();

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

											// メールアドレスの最終チェック（空欄ならスキップ）
											var email = $("#email").val();
											var emailPattern = /^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,6}$/;
											if (email !== ""
													&& !emailPattern
															.test(email)) {
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

											// パスワードの最終チェック
											var password = $("#password").val();
											if (password.length < 7) {
												$("#password-error")
														.text(
																"パスワードは7文字以上で入力してください。");
												isValid = false;
											} else {
												$("#password-error").text("");
											}

											// パスワード確認の最終チェック
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

		<!-- ユーザーID重複エラーメッセージ -->
		<div class="error-message" style="color: red;">
			<%=request.getAttribute("userError") != null ? request.getAttribute("userError") : ""%>
		</div>
		
		<%-- ログインユーザー情報表示 --%>
		<div class="user-info">
			<p>
				ID:
				<%=request.getAttribute("userId")%>
			</p>
			<p><%=request.getAttribute("userName")%></p>
		</div>

		<form action="AdminRegisterServlet" method="post">
			<div>
				<label for="email">メールアドレス</label><br> <input type="email"
					id="email" name="userMailAddress" placeholder="メールアドレス"
					value="<%=request.getAttribute("userMailAddress") != null ? request.getAttribute("userMailAddress") : ""%>"><br>
				<span id="email-error" style="color: red;"></span><br>
			</div>

			<div>
				<label for="password">パスワード <span style="color: red;">*</span></label><br>
				<input type="password" id="password" name="password"
					placeholder="パスワード" required><br> <span
					id="password-error" style="color: red;"></span><br>
			</div>

			<div>
				<label for="confirm-password">パスワード確認 <span
					style="color: red;">*</span></label><br> <input type="password"
					id="confirm-password" name="confirmPassword" placeholder="パスワード確認"
					required><br> <span id="confirm-password-error"
					style="color: red;"></span><br>
			</div>

			<div>
				<label for="NewUserName">名前 <span style="color: red;">*</span></label><br>
				<input type="text" id="NewUserName" name="NewUserName"
					placeholder="名前" required><br>
			</div>

			<div>
				<label for="address">住所 <span style="color: red;">*</span></label><br>
				<input type="text" id="address" name="userAddress" placeholder="住所"
					required><br>
			</div>

			<div>
				<label for="phone">電話番号 <span style="color: red;">*</span></label><br>
				<input type="text" id="phone" name="userPhoneNumber"
					placeholder="電話番号" required><br> <span
					id="phone-error" style="color: red;"></span><br>
			</div>

			<div>
				<label for="privilege">管理権限 <span style="color: red;">*</span></label><br>
				<select id="privilege" name="privilege" required>
					<option value="1"
						<%=request.getAttribute("privilege") != null && request.getAttribute("privilege").equals("1") ? "selected" : ""%>>顧客</option>
					<option value="0"
						<%=request.getAttribute("privilege") != null && request.getAttribute("privilege").equals("0") ? "selected" : ""%>>管理者</option>
				</select><br>
			</div>

			<div style="text-align: center;">
				<input type="button" value="戻る" onclick="history.back();"><br>
				<br> <input type="submit" value="次へ"><br>
			</div>
		</form>
	</div>
</body>
</html>
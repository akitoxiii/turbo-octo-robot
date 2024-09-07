<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<title>Forest 予約システム - ログイン</title>
<link rel="stylesheet" type="text/css" href="css/StyleCss.css">
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
</head>
<body>

	<div class="login-container">
		<h1>Forest 予約システム</h1>
		<%-- エラーメッセージ表示 --%>
		<div class="error-message">
			<%=request.getAttribute("loginError") != null ? request.getAttribute("loginError") : ""%>
		</div>
		<form id="loginForm" action="LoginCon" method="post">
			<label for="loginId">ログインID</label><br> <input type="text"
				id="loginId" name="loginId" placeholder="ログインID"
				value="<%=request.getParameter("loginId") != null ? request.getParameter("loginId") : ""%>"
				required> <span id="loginId-error" style="color: red;"></span><br>
			<br> <label for="loginPassword">ログインパスワード</label><br> <input
				type="password" id="loginPassword" name="loginPassword"
				placeholder="ログインパスワード" required> <span
				id="loginPassword-error" style="color: red;"></span><br> <br>
			<input type="submit" value="ログイン">
		</form>
		<form action="registerServlet" method="get">
			<input type="button" value="新規登録"
				onclick="location.href='UserRegister.jsp'">
		</form>
	</div>

	<script>
		$(document).ready(function() {
			// フィールドから離れた時にバリデーションを表示
			$("#loginId").on("blur", function() {
				if ($(this).val().trim() === "") {
					$("#loginId-error").text("ログインIDを入力してください。");
				} else {
					$("#loginId-error").text("");
				}
			});

			$("#loginPassword").on("blur", function() {
				if ($(this).val().trim() === "") {
					$("#loginPassword-error").text("パスワードを入力してください。");
				} else {
					$("#loginPassword-error").text("");
				}
			});

			// フォーム送信時の最終チェック
			$('#loginForm').on('submit', function(event) {
				var isValid = true;

				// ログインIDのチェック
				if ($('#loginId').val().trim() === "") {
					isValid = false;
					$("#loginId-error").text("ログインIDを入力してください。");
				}

				// パスワードのチェック
				if ($('#loginPassword').val().trim() === "") {
					isValid = false;
					$("#loginPassword-error").text("パスワードを入力してください。");
				}

				// バリデーションにエラーがあれば送信を防ぐ
				if (!isValid) {
					event.preventDefault();
				}
			});
		});
	</script>
</body>
</html>
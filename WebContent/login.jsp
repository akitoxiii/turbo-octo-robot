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
			<input type="text" id="loginId" name="loginId" placeholder="ログインID"
				value="<%=request.getParameter("loginId") != null ? request.getParameter("loginId") : ""%>"
				required> <input type="password" id="loginPassword"
				name="loginPassword" placeholder="ログインパスワード" required> <input
				type="submit" value="ログイン">
		</form>
		<form action="registerServlet" method="get">
			<input type="button" value="新規登録"
				onclick="location.href='UserRegister.jsp'">
		</form>
	</div>

	<script>
		$(document).ready(function() {
			// フォームのバリデーション
			$('#loginForm').on('submit', function(event) {
				var isValid = true;
				var errorMessage = "";

				// ログインIDのチェック
				if ($('#loginId').val().trim() === "") {
					isValid = false;
					errorMessage += "ログインIDを入力してください。\n";
				}

				// パスワードのチェック
				if ($('#loginPassword').val().trim() === "") {
					isValid = false;
					errorMessage += "パスワードを入力してください。\n";
				}

				if (!isValid) {
					event.preventDefault(); // フォーム送信を防止
					alert(errorMessage); // エラーメッセージを表示
				}
			});
		});
	</script>
</body>
</html>
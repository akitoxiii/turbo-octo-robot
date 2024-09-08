<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Forest 予約システム - 新規会員情報確認画面</title>
<link rel="stylesheet" type="text/css" href="css/StyleCss.css">
</head>
<body>
	<div class="confirm-container">
		<h1>新規会員情報確認画面</h1>

		<label>メールアドレス</label>
		<p><%=request.getAttribute("userMailAddress")%></p>

		<label>パスワード <span style="color: red;">*</span></label>
		<p><%=request.getAttribute("password")%></p>

		<label>名前 <span style="color: red;">*</span></label>
		<p><%=request.getAttribute("userName")%></p>

		<label>住所 <span style="color: red;">*</span></label>
		<p><%=request.getAttribute("userAddress")%></p>

		<label>電話番号 <span style="color: red;">*</span></label>
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
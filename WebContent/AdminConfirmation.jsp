<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Forest 予約システム - 新規管理者情報確認画面</title>
<link rel="stylesheet" type="text/css" href="css/StyleCss.css">
</head>
<body>
	<div class="confirm-container">
		<h1>新規管理者情報確認画面</h1>

		<%-- ログインユーザー情報表示 --%>
		<div class="user-info">
			<p>
				ID:
				<%=request.getAttribute("userId")%></p>
			<p><%=request.getAttribute("AdminUserName")%></p>
		</div>

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

		<label>管理権限</label>
		<p>
			<%
			String privilege = (String) request.getAttribute("privilege");
			if (privilege != null) {
				out.print(privilege.equals("1") ? "管理者" : "顧客");
			} else {
				out.print("権限情報がありません");
			}
			%>
		</p>

		<div style="text-align: center;">
			<input type="button" value="戻る" onclick="history.back();">
			<form action="AdminCompletionServlet" method="post">
				<%-- 隠しフィールドを使ってデータを渡す --%>
				<input type="hidden" name="userMailAddress"
					value="<%=request.getAttribute("userMailAddress")%>"> <input
					type="hidden" name="password"
					value="<%=request.getAttribute("password")%>"> <input
					type="hidden" name="userName"
					value="<%=request.getAttribute("NewUserName")%>"> <input
					type="hidden" name="userAddress"
					value="<%=request.getAttribute("userAddress")%>"> <input
					type="hidden" name="userPhoneNumber"
					value="<%=request.getAttribute("userPhoneNumber")%>"> <input
					type="hidden" name="privilege"
					value="<%=request.getAttribute("privilege")%>"> <input
					type="submit" value="登録">
			</form>
		</div>
	</div>
</body>
</html>
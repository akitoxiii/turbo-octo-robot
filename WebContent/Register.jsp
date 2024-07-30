<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<title>商品登録画面</title>
<link rel="stylesheet" type="text/css" href="css/Style.css">
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script src="js/jquery.validationEngine.js"></script>
<script src="js/languages/jquery.validationEngine-ja.js"></script>
<script src="js/click.js"></script>
<script>
	$(document)
			.ready(
					function() {
						$("#product-form").validationEngine({
							promptPosition : "inline",
							scroll : false
						});

						$.validationEngineLanguage.allRules["custom[number]"].alertText = "* 数字のみの入力が可能です";
						$.validationEngineLanguage.allRules["maxSize[100]"].alertText = "* 最大100文字までの入力が可能です";
						$.validationEngineLanguage.allRules["max[100000]"].alertText = "* 数値は最大100000以下で入力してください";
						$.validationEngineLanguage.allRules["max[1000]"].alertText = "* 数値は最大1000以下で入力してください";
					});
</script>
</head>
<body>
	<h1>商品登録画面</h1>
	<div class="navigation">
		<a href="Logout.jsp">ログアウト</a> <a href="ListCon">一覧画面へ</a>
	</div>
	<div class="message">
		<%
		String result = (String) request.getAttribute("result");
		if (result != null) {
			if (result.equals("0")) {
				out.println("<p class='message'>登録が失敗しました</p>");
			} else if (result.equals("1")) {
				out.println("<p class='message success'>登録が完了しました</p>");
			} else if (result.equals("既に同じ商品名が存在します")) {
				out.println("<p class='message'>既に同じ商品名が存在します</p>");
			}
		}
		String error = (String) request.getAttribute("error");
		if (error != null) {
			out.println("<p class='message'>" + error + "</p>");
		}
		%>
	</div>
	<form id="product-form" action="Register" method="post"
		enctype="multipart/form-data">
		<table>
			<caption>◆登録する商品を入力してください◆</caption>
			<tr>
				<th>項目</th>
				<th>入力欄</th>
			</tr>
			<tr>
				<td>商品NO</td>
				<td><input type="text" name="itemNo" readonly
					value="<%=request.getAttribute("itemNo") != null ? request.getAttribute("itemNo") : ""%>"></td>
			</tr>
			<tr>
				<td>商品名</td>
				<td><input type="text" name="itemName" size="30"
					class="validate[required,maxSize[100]]"></td>
			</tr>
			<tr>
				<td>値段</td>
				<td><input type="text" name="itemPrice" size="30"
					class="validate[required,custom[number],max[100000]]"></td>
			</tr>
			<tr>
				<td>在庫数</td>
				<td><input type="text" name="stockCount" size="30"
					class="validate[required,custom[integer],max[1000]]"></td>
			</tr>
			<tr>
				<td>商品画像</td>
				<td><input type="file" name="itemImage"></td>
			</tr>
			<tr>
				<td>目玉商品</td>
				<td><label><input type="radio" name="specialItem"
						value="0" class="validate[required]">目玉商品</label> <label><input
						type="radio" name="specialItem" value="1"
						class="validate[required]">一般商品</label></td>
			</tr>
			<tr>
				<td>CSVファイル選択</td>
				<td><input type="file" name="csvFile"></td>
			</tr>
		</table>
		<div class="buttonall">
			<button type="submit" class="button" id="register-button">登録</button>
			<input type="reset" value="リセット" class="button">
		</div>
	</form>
	<form id="bulk-form" action="CsvCon" method="post"
		enctype="multipart/form-data">
		<div class="buttonall">
			<input type="submit" value="CSVの一括登録" class="button">
		</div>
	</form>
</body>
</html>
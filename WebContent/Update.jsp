<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<title>商品更新画面</title>
<link rel="stylesheet" type="text/css" href="css/Style.css">
<style>
.container {
	max-width: 800px;
	margin: 0 auto;
	padding: 20px;
	background-color: #f9f9f9;
	border-radius: 10px;
}

table {
	width: 100%;
	border-collapse: collapse;
	margin-bottom: 20px;
}

th, td {
	padding: 10px;
	border: 1px solid #ddd;
}

th {
	background-color: #f2f2f2;
}

.btn {
	padding: 10px 20px;
	color: #fff;
	background-color: #007bff;
	border: none;
	border-radius: 5px;
	cursor: pointer;
}

.btn:hover {
	background-color: #0056b3;
}
</style>
<script type="text/javascript">
	function validateForm() {
		var itemName = document.getElementById("itemName").value;
		var itemPrice = document.getElementById("itemPrice").value;
		var stockCount = document.getElementById("stockCount").value;

		if (itemName.trim() === "") {
			alert("商品名を入力してください");
			return false;
		}

		if (itemPrice.trim() === "" || isNaN(itemPrice)
				|| parseInt(itemPrice) <= 0) {
			alert("有効な値段を入力してください");
			return false;
		}

		if (stockCount.trim() === "" || isNaN(stockCount)
				|| parseInt(stockCount) < 0) {
			alert("有効な在庫数を入力してください");
			return false;
		}

		return true;
	}
</script>
</head>
<body>
	<div class="container">
		<h1>商品更新画面</h1>
		<form id="update-form" action="UpdateCon" method="post"
			enctype="multipart/form-data" onsubmit="return validateForm();">
			<input type="hidden" name="itemNo" id="itemNo" value="${item.itemNo}">
			<input type="hidden" name="datetime" id="lastUpdateTime"
				value="${new SimpleDateFormat(" yyyy-MM-ddHH:mm:ss.SSS").format(lastUpdateTime)}">

			<table>
				<caption>◆更新する商品情報を入力してください◆</caption>
				<tr>
					<th>項目</th>
					<th>入力欄</th>
				</tr>
				<tr>
					<td>商品NO</td>
					<td><input type="text" name="itemNoDisplay" size="30" readonly
						value="${item.itemNo}"></td>
				</tr>
				<tr>
					<td>商品名</td>
					<td><input type="text" id="itemName" name="itemName" size="30"
						value="${item.itemName}"></td>
				</tr>
				<tr>
					<td>値段</td>
					<td><input type="text" id="itemPrice" name="itemPrice"
						size="30" value="${item.itemPrice}"></td>
				</tr>
				<tr>
					<td>在庫数</td>
					<td><input type="text" id="stockCount" name="stockCount"
						size="30" value="${item.stockCount}"></td>
				</tr>
				<tr>
					<td>商品画像</td>
					<td><input type="file" name="itemImagePath"></td>
				</tr>
				<tr>
					<td>目玉商品</td>
					<td><label><input type="radio" name="specialItem"
							value="0" ${item.specialItem == 0 ? 'checked' : ''}>目玉商品</label>
						<label><input type="radio" name="specialItem" value="1"
							${item.specialItem == 1 ? 'checked' : ''}>一般商品</label></td>
				</tr>
				<tr>
					<td colspan="2"><button type="submit" class="btn"
							id="update-button">更新</button></td>
				</tr>
			</table>
		</form>
		<button class="btn" onclick="window.location.href='ListCon'">一覧画面へ戻る</button>
	</div>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<title>商品閲覧画面</title>
<link rel="stylesheet" type="text/css" href="css/Style.css">
<style>
.container {
	max-width: 600px;
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

.image-container {
	text-align: center;
	margin-bottom: 20px;
}

.image-container img {
	max-width: 100%;
	height: auto;
}
</style>
</head>
<body>
	<div class="container">
		<h1>商品閲覧画面</h1>
		<div class="image-container">
			<img src="<c:url value='${item.itemImagePath}' />" alt="商品画像">
		</div>
		<table>
			<caption>◆商品の詳細情報です◆</caption>
			<tr>
				<th>項目</th>
				<th>詳細内容</th>
			</tr>
			<tr>
				<td>商品NO</td>
				<td>${item.itemNo}</td>
			</tr>
			<tr>
				<td>商品名</td>
				<td>${item.itemName}</td>
			</tr>
			<tr>
				<td>値段</td>
				<td>${item.itemPrice}</td>
			</tr>
			<tr>
				<td>在庫数</td>
				<td>${item.stockCount}</td>
			</tr>
			<tr>
				<td>目玉商品</td>
				<td><c:choose>
						<c:when test="${item.specialItem == 0}">目玉商品</c:when>
						<c:otherwise>一般商品</c:otherwise>
					</c:choose></td>
			</tr>
		</table>
		<a href="ListCon"><button class="btn">一覧画面へ</button></a>
	</div>
</body>
</html>
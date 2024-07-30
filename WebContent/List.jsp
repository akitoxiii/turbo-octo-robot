<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<title>商品一覧画面</title>
<link rel="stylesheet" type="text/css" href="css/Style.css">
<style>
.table-wrapper {
	max-height: 336px;
	overflow-y: auto;
}

table {
	width: 100%;
	border-collapse: separate;
	border-spacing: 0 15px;
	margin-bottom: 20px;
}

th, td {
	border: 1px solid #e0e0e5;
	padding: 12px;
	text-align: left;
	font-size: 1em;
}

th {
	background-color: #f9f9f9;
	font-weight: 600;
	color: #1d1d1f;
	border-bottom: 2px solid #e0e0e5;
}

td {
	background-color: #fff;
	border-radius: 8px;
	box-shadow: 0 1px 3px rgba(0, 0, 0, 0.1);
}

.search-form {
	width: 60%;
	margin: 20px auto;
	padding: 10px;
	border: 1px solid #e0e0e5;
	border-radius: 8px;
	background-color: #fff;
	box-shadow: 0 1px 3px rgba(0, 0, 0, 0.1);
	display: flex;
	flex-wrap: wrap;
	justify-content: center;
	align-items: center;
}

.search-form label {
	margin-right: 10px;
}

.search-form input[type="text"], .search-form input[type="radio"],
	.search-form input[type="submit"], .search-form input[type="reset"],
	.search-form input[type="button"] {
	margin: 5px;
}

.search-form .radio-group {
	display: flex;
	justify-content: center;
	align-items: center;
}

.button-group {
	display: flex;
	justify-content: center;
	gap: 10px;
}

.errormessage {
	color: red;
}

.message {
	color: green;
}
</style>
<script type="text/javascript">
	function updateLink(itemNo) {
		if (window.confirm("この商品を更新しますか？")) {
			var form = document.createElement('form');
			form.method = 'post';
			form.action = 'UpdateCon';

			var input = document.createElement('input');
			input.type = 'hidden';
			input.name = 'itemNo';
			input.value = itemNo;
			form.appendChild(input);

			document.body.appendChild(form);
			form.submit();
		}
	}

	function confirmDelete(itemNo) {
		if (confirm("この商品を削除しますか？")) {
			document.getElementById("deleteForm" + itemNo).submit();
		}
	}

	function confirmView(itemNo) {
		if (confirm("この商品を照会しますか？")) {
			document.getElementById("viewForm" + itemNo).submit();
		}
	}

	function resetSearchForm() {
		document.getElementById("searchForm").reset();
		document.getElementById("searchName").value = "";
		document.getElementById("itemType").value = "";
	}
</script>
</head>
<body>
	<h1>商品一覧画面</h1>
	<div class="navigation">
		<a href="Register.jsp">登録画面へ</a> | <a href="Purchase.jsp">購入画面へ</a>
	</div>
	<div class="message">
		<c:if test="${not empty result}">
			<c:choose>
				<c:when test="${result == '既に登録されている商品です'}">
					<p class="errormessage">${result}</p>
				</c:when>
				<c:when test="${result == '更新が完了しました'}">
					<p class="message">${result}</p>
				</c:when>
				<c:when test="${result == '更新済みエラーです'}">
					<p class="errormessage">${result}</p>
				</c:when>
				<c:otherwise>
					<p>${result}</p>
				</c:otherwise>
			</c:choose>
		</c:if>
	</div>
	<div class="table-wrapper">
		<table>
			<caption>◆登録されている商品情報です◆</caption>
			<tr>
				<th>商品名</th>
				<th>値段</th>
				<th>在庫数</th>
				<th>項目選択</th>
			</tr>
			<c:if test="${not empty itemList}">
				<c:forEach var="item" items="${itemList}">
					<tr>
						<td>${item.itemName}</td>
						<td>${item.itemPrice}</td>
						<td>${item.stockCount}</td>
						<td><a href="javascript:void(0)"
							onclick="updateLink('${item.itemNo}')">更新</a> |
							<form id="deleteForm${item.itemNo}" action="DeleteCon"
								method="post" style="display: inline;">
								<input type="hidden" name="itemNo" value="${item.itemNo}">
								<a href="javascript:void(0)"
									onclick="confirmDelete('${item.itemNo}')">削除</a>
							</form> |
							<form id="viewForm${item.itemNo}" action="ViewCon" method="get"
								style="display: inline;">
								<input type="hidden" name="itemNo" value="${item.itemNo}">
								<a href="javascript:void(0)"
									onclick="confirmView('${item.itemNo}')">詳細</a>
							</form></td>
					</tr>
				</c:forEach>
			</c:if>
			<c:if test="${empty itemList}">
				<tr>
					<td colspan="4">登録されている商品がありません。</td>
				</tr>
			</c:if>
		</table>
	</div>
	<form id="searchForm" class="search-form" action="ListCon"
		method="post">
		<label for="searchName">名前検索</label> <input type="text"
			id="searchName" name="searchName" size="20" maxlength="100"
			value="${searchName}">
		<div class="radio-group">
			<label> <input type="radio" name="itemType" value="0"
				<c:if test="${itemType == '0'}">checked</c:if>>目玉商品
			</label> <label> <input type="radio" name="itemType" value="1"
				<c:if test="${itemType == '1'}">checked</c:if>>一般商品
			</label>
		</div>
		<div class="button-group">
			<input type="submit" value="検索"> <input type="button"
				value="出力" onclick="location.href='CsvInputCon'"> <input
				type="reset" value="リセット" onclick="resetSearchForm()">
		</div>
	</form>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <%@page import="java.util.ArrayList"%>
    <%@page import="java.text.NumberFormat"%>
    <%@page import="model.ReservationBean"%>
    
    
<!DOCTYPE html>
<html>
<head>

<link rel="stylesheet" type="text/css" href="css/Style.css">

<meta charset="UTF-8">
<title>全予約一覧</title>
</head>
<body>
<div class="container">

// 削除用フォーム
<form method="post" action="DeleteCon"></form>


<%-- 削除機能の結果表示 --%>
	<%
	Integer count = (Integer)request.getAttribute("count");
 if(count == null){
	 System.out.print("");
 }
 else if(count == 0){
%>
	<p style="text-align: center; color: red;">削除に失敗しました</p>

	<%
}
 else if(count <= 1){
		%>
	<p style="text-align: center; color: blue">削除が完了しました</p>
	<%	
	} 
 %>


<br>

	<!-- for文でテーブル内容を繰り返す -->
	<% 
	NumberFormat comFormat = NumberFormat.getNumberInstance();
	
	 ArrayList<ReservatiionBean> allList = (ArrayList<ReservationBean>)request.getAttribute("allList");
	 
	 %>

<div class="section">
	<table class="table_sticky" id="listTable">
		<thead>
			<tr>
				<th width="100">商品名</th>
				<th width="10">値段</th>
				<th width="10">在庫数</th>
				<th width="40">項目選択</th>
			</tr>
		</thead>

		<tbody>
		<% 
		
			 for(ReservationBean listAll : allList){
			 %>
			 <tr data-href="javascript:void(0)"
					onclick="ContentLink('<%= listAll.getItemNo() %>');"> 
				
				
				<td class="itemName"><a href="javascript:void(0)"
					onclick="ContentLink('<%= listAll.getUserId() %>');" class="listLink"><%= listAll.getItemName() %></a></td>
				<td><%= comFormat.format(listAll.getItemPrice()) %></td>
				<td><%= comFormat.format(listAll.getStockCount()) %></td>
				<td class="itemContent">
				<input type="hidden" name="itemNo"
					value="<%= listAll.getItemNo() %>" id="<%= listAll.getItemNo() %>">
					
					
				</td>
			</tr>
			<%
			 }
				%>


		</tbody>


	</table>





</div>

	
			<%-- 詳細リンクのfunction --%>
	<script type="text/javascript">
	function ContentLink(itemNo){
			var form = document.forms[0];
			var input = document.getElementById(itemNo);
			form.appendChild(input);
			document.body.appendChild(form);
			form.submit();	<%-- フォーム送信 --%>

		}
	</script>



</body>
</html>
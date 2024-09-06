<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <%@page import="java.util.ArrayList"%>
    <%@page import="model.ReservationBean"%>
    
    
    
<!DOCTYPE html>
<html>
<head>

<link rel="stylesheet" type="text/css" href="css/StyleCss.css">


<meta charset="UTF-8">
<title>全予約一覧</title>
</head>
<body>

<%-- 詳細へのフォームリンク --%>
<form method="post" action="ReservationViewCon"></form>



<div class="container">

<%-- 削除機能の結果表示 
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
 --%>


<br>

	<!-- for文でテーブル内容を繰り返す -->
	<% 
	
	 ArrayList<ReservationBean> allList = (ArrayList<ReservationBean>)request.getAttribute("allList");
	 
	 %>
<div class="test1">
<div class="section">
<br><br>
<div class="logo">全予約一覧</div>

<br><br><br><div style="text-align: left;">
<a href="AdminMypage.jsp">メニューへ戻る</div><br><br>


	<table class="table_sticky" id="listTable">
		<thead>
			<tr>
				<th width="100">予約ID</th>
				<th width="10">ユーザーID</th>
				<th width="100">名前</th>
			</tr>
		</thead>

		<tbody>
		<% 
		
			 for(ReservationBean listAll : allList){
			 %>
			 <tr data-href="javascript:void(0)"
					onclick="ContentLink('<%= listAll.getReservationId() %>');">  
					
					<input type="hidden" name="ReservationId"
					value="<%= listAll.getReservationId() %>" id="<%= listAll.getReservationId() %>">
					<a href="javascript:void(0)"
					onclick="ContentLink('<%= listAll.getReservationId() %>');">
				<td><%= listAll.getReservationId() %></td>
				<td><%= listAll.getUserId() %></td></a>
				<td><%= listAll.getUserName() %></td></a>
			</tr>
			<%
			 }
				%>


		</tbody>


	</table>



<br><br><br><br><br><br><br><br><br>

</div>

</div>
<br><br><br>

	<script type="text/javascript">
	$('tr[data-href]').click(function (e) {
  if (!$(e.target).is('a')) {
    window.location = $(e.target).data('href');
  };
});
	</script>
	
			<%-- 詳細リンクのfunction --%>
	<script type="text/javascript">
	function ContentLink(ReservationId){
			var form = document.forms[0];
			var input = document.getElementById(ReservationId);
			form.appendChild(input);
			document.body.appendChild(form);
			form.submit();	<%-- フォーム送信 --%>

		}
	</script>
	

	

</body>
</html>
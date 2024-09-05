<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
     <%@page import="model.ReservationBean"%>
     
<!DOCTYPE html>
<html>
<head>

<link rel="stylesheet" type="text/css" href="css/StyleCss.css">
<meta charset="UTF-8">


<title>予約詳細画面</title>
</head>
<body>
<br>

<% if(request.getAttribute("contentBean") ==null){ %>
	
	<div>
	<p class="inputs">予約情報はありません</p>
	
	</div>
	
	<% 
}else{
	int pri = (int)session.getAttribute("userPrivilege");
	if(pri==0){ %>
	<a href="ReservationList">戻る</a>
	
	<% 	
	}else{ %>
	
	<a href="UserMypage">メニューへ戻る</a>
	<%	
	}

 ReservationBean contentBean = (ReservationBean)request.getAttribute("contentBean");

	%>
	<br>
	<br>
	<div class="item">
	
	<p class="label">予約ID</p>
	
	<p class="inputs"><%=contentBean.getReservationId()%></p>
	
	
		<p class="label">会員ID</p>

		<p class="inputs"><%=contentBean.getUserId()%></p>

		<p class="label">予約日</p>

		<p class="inputs"><%=contentBean.getReservationDate()%></p>

		<p class="label">予約時間</p>

		<p class="inputs"><%=contentBean.getReservationTime() + ":00"%></p>
		
		
		
		<%-- ここで削除をする --%>
		<form action="ReservationDelete" method="post">
		<input type="hidden" name="ReservationId"
					value="<%= contentBean.getReservationId() %>" id="<%= contentBean.getReservationId() %>">
					<input type="submit" value="削除" class="deletebutton" id="test">
		</form>
		<br>
	<br>
	

	</div>

<% } %>


</body>
</html>
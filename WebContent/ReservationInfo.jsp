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
<div class="test1">
<br>

<% 
ReservationBean contentBean = (ReservationBean)request.getAttribute("contentBean");

int pri = (int)session.getAttribute("userPrivilege");

if(contentBean.getReservationId() ==null){ 
	
	if(pri==0){ %>
	<a href="AdminMypage.jsp">戻る</a>
		
	<div>
	<p class="inputs">予約情報はありません</p>
	
	</div>
	
	<% 	
	}else{ %>
	<div style="text-align: left;">
	<a href="UserMypage.jsp">メニューへ戻る</a></div>
	<%	


%>
	
	<div>
	<p class="inputs">予約情報はありません</p>
	
	</div>
	
	<% 
	}
}else{
	
	if(pri==0){ %>
	<a href="ReservationListCon">戻る</a>
	
	<% 	
	}else{ %>
	<div style="text-align: left;">
	<a href="UserMypage.jsp">メニューへ戻る</a></div>
	<%	
	}



	%>
	<br>
	<br>
	<div class="item">
	
	<p class="label">予約ID</p>
	
	<p class="inputs"><%=contentBean.getReservationId()%></p>
	<br><br>
	
		<p class="label">会員ID</p>

		<p class="inputs"><%=contentBean.getUserId()%></p><br><br><br>
		
		<p class="label">名前</p>
		
		<p class="inputs"><%=contentBean.getUserName()%></p><br><br><br>

		<p class="label">予約日</p>

		<p class="inputs"><%=contentBean.getReservationDate()%></p><br><br><br>

		<p class="label">予約時間</p>

		<p class="inputs"><%=contentBean.getReservationTime() + ":00"%></p><br><br><br>
		
		
		
		<%-- ここで削除をする --%>
		<form action="ReservationDelete" method="post">
		<input type="hidden" name="ReservationId"
					value="<%= contentBean.getReservationId() %>" id="<%= contentBean.getReservationId() %>">
					<input type="submit" value="削除" class="deletebutton" id="test">
		</form>
		<br>
	<br>
	
</div>
	</div>

<% } %>


</body>
</html>
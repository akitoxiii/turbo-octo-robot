<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@page import="model.UserIdBean"%>
<%@page import="model.ReservationBean"%>
<%@page import="model.ReservationDao"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="javax.servlet.http.HttpSession"%>
<%@page import="java.util.Date"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<%-- ============ Googleフォント・noto san ============ --%>
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link
	href="https://fonts.googleapis.com/css2?family=Noto+Sans+JP:wght@100..900&display=swap"
	rel="stylesheet">
<%-- ============ Googleフォント・noto san ここまで ============ --%>

<link rel="stylesheet" type="text/css" href="css/StyleCss.css">


<title>Insert title here</title>
</head>

<%-- ============ bodyここから ============ --%>
<body>
<div class="test1">
<br>
	<br>


	<h1>時間選択画面</h1>
	<br>
	<br>

	<p class="lead-form">時間を選択してください</p>
	<br>
	<form method="post" action="Reservation">

		<% 
  
		int pri = (int)session.getAttribute("userPrivilege");
  
   if(pri == 0){
  %>


		<div class="item">
			<p class="label">会員ID</p> <input class="inputs" type="text"
				name="userId">
		</div>

		<%  } %>



		<div class="item">
			<p class="label">予約時間</p>
			<div class="inputs">


				
				<% 
				// セッションスコープから取得
				 ReservationBean reseBean = (ReservationBean)session.getAttribute("reseBean");
     
  // 予約時間を20240801のような形式で保存(reservationBeanでStrimgで保存しているので、もうそうなっている？)
  		// ①日にち
  		//  SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
  		// Date date = sdf.parse(reseBean.getReservationDate());
  		 // String date = sdf.format(reseBean.getReservationDate());
  		 String date = reseBean.getReservationDate();
  		 
  		
  		// ②時間
  		
     
     // DBで予約日時でセレクトするDaoを作り、それで帰ってきた件数をintで受ける。２なら表示しない。
     ReservationDao rs = new ReservationDao();
     
     int reservationTime = rs.saerchTime(date,"9");
     if(reservationTime <2){
      %>

				<input id="9" type="radio" name="time" value="9"><label
					for="9">9:00</label><br>
				<%  } %>

				<input id="10" type="radio" name="time" value="10"><label
					for="10">10:00</label><br> <input id="11" type="radio"
					name="time" value="11"><label for="11">11:00</label><br>
				<input id="12" type="radio" name="time" value="12"><label
					for="12">12:00</label><br> <input id="13" type="radio"
					name="time" value="13"><label for="13">13:00</label><br>
				<input id="14" type="radio" name="time" value="14"><label
					for="14">14:00</label><br> <input id="15" type="radio"
					name="time" value="15"><label for="15">15:00</label><br>
				<input id="16" type="radio" name="time" value="16"><label
					for="16">16:00</label><br>
			</div>
		</div>
		<br>
		<br>

		<div class="btn-area">
			<input type="submit" value="送信"><br><br><br>
		</div>

	</form>



</div>
</body>
</html>
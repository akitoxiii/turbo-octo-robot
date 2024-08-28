<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@page import="model.UserIdBean"%>
<%@page import="model.ReservationBean"%>
<%@page import="model.ReservationDao"%>
<%@page import=""%>
<%@page import="javax.servlet.http.HttpSession"%>

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


	<h1>時間選択画面</h1>
	<br>
	<br>

	<p class="lead-form">時間を選択してください</p>
	<br>
	<form>

		<% 
  
  
  
  // if(ログインセッションスコープの権限が０なら){
  %>


		<div class="item">
			<label class="label">会員ID</label> <input class="inputs" type="text"
				name="userId">
		</div>

		<% // } %>



		<div class="item">
			<p class="label">予約時間</p>
			<div class="inputs">


				
				<% 
     
  // 予約時間を20240801のような形式で保存
  		// ①日にち
  		SimpleDateFormat sdf = new SimpleDateFormat("yyyymmdd");
  		String date = sdf.format(reseBean.getReservationDate());
  		
  		// ②時間
  		int reservationTime = reseBean.getReservationTime();
  		
     
     // DBで予約日時でセレクトするDaoを作り、それで帰ってきた件数をintで受ける。２なら表示しない。
     ReservationDao rs = new ReservationDao();
     
    // int reservationTime = rs.saerchTime(session.getAttribute("rDay"),9);
    // if(!(reservationTime ==2)){
      %>

				<input id="9am" type="radio" name="time" value="9am"><label
					for="9am">9:00</label><br>
				<% // } %>

				<input id="10am" type="radio" name="time" value="10am"><label
					for="10am">10:00</label><br> <input id="11am" type="radio"
					name="time" value="11am"><label for="11am">11:00</label><br>
				<input id="12pm" type="radio" name="time" value="12pm"><label
					for="12pm">12:00</label><br> <input id="13pm" type="radio"
					name="time" value="13pm"><label for="13pm">13:00</label><br>
				<input id="14pm" type="radio" name="time" value="14pm"><label
					for="14pm">14:00</label><br> <input id="15pm" type="radio"
					name="time" value="15pm"><label for="15pm">15:00</label><br>
				<input id="16pm" type="radio" name="time" value="16pm"><label
					for="16pm">16:00</label><br>
			</div>
		</div>
		<br>
		<br>

		<div class="btn-area">
			<input type="submit" value="送信">
		</div>

	</form>




</body>
</html>
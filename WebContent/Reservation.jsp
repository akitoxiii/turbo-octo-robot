<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <%-- カレンダー、コレクション等のインポート --%>
    <%@page import="java.util.Calendar"%>
    <%@page import="java.util.Collections"%>
    <%@page import="java.util.Date"%>
    <%@page import="java.text.SimpleDateFormat"%>
     <%@page import="model.ReservationDao"%>
    
    <%-- ーーーーーーーーーーーーーーーーーー --%>
    
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


<title>予約画面</title>
</head>

<%-- ============ bodyここから ============ --%>
<body>

<% Calendar cl = Calendar.getInstance(); 

String[] month = {"1月","2月","3月","4月","5月","6月","7月","8月","9月","10月","11月","12月"};


int firstDay = 1; //月の初日

// カレンダーに当日の年、月、１日をセットする
cl.set(cl.get(Calendar.YEAR),cl.get(Calendar.MONTH),firstDay);


int lastDay = cl.getActualMaximum(Calendar.DAY_OF_MONTH); // 月の最終日

int firstDayWeek = cl.get(Calendar.DAY_OF_WEEK) - 1;  // 月の最初の曜日をintで求める。DAY_OF_WEEKは日曜日なら１を取得する？

// Daoのインスタンス化
ReservationDao dao = new ReservationDao();

%>

<%  %>



<div class="test1">
<div class="container">
<br><br>
<div class="logo">予約画面</div>
<br><br>


<%-- ============ フォーム送信 ============ --%>
<form method="post" action="Reservation"></form>
<%-- ============ フォーム送信 ============ --%>
<%-- 今月（配列もカレンダーも０からのカウントなので＋１をする必要がない？） --%>
<%= month[(cl.get(Calendar.MONTH))] %>
<br><br>

<%-- カレンダーテーブル --%>
<table>
	<thead>
	<tr>
	<th>日</th>
	<th>月</th>
	<th>火</th>
	<th>水</th>
	<th>木</th>
	<th>金</th>
	<th>土</th>
	</tr>
	</thead>
	
	<tbody>
	<tr>
	
	<% 
	Date date = new Date(); // 今日の日付
	SimpleDateFormat dateFormat = new SimpleDateFormat("dd");
	String strDate = dateFormat.format(date);
	int intDate = Integer.parseInt(strDate);
	
	// 月に合わせて１日の前の空白を表示
	for(int i =1; i <= firstDayWeek; i++){ %>
	<td> </td>
	<% }
	
	// 後は１日から月末までの日まで繰り返す
	for(int i = firstDay; i <= lastDay; i++){ 
		if(i <= intDate || (firstDayWeek + i) % 7 == 2){
	%> 
	
	<td><%= i %></td>
	<% // 空白＋日付の数が７になったら列を変える
	if ((firstDayWeek + i) % 7 == 0) { %>
	</tr><tr>
	<% }
	} 
		// 当日行以降はリンクにする
	
	else if(i > intDate){
		
		
		// ここで予約のある日を弾く（？年？月？日で送る）
		// ＝＝＝めちゃ重になるので割愛します＝＝＝
		// int calenderMonth = cl.get(Calendar.MONTH) +1;
		//  if(dao.SeachDay((cl.get(Calendar.YEAR)) + "年" + calenderMonth + "月" + i + "日") >= 16){ %>
			   <%-- <td><%= i %></td> 
		
			<%     
		 }else{
		  --%>
		<td>
		<input type="hidden" name="yearId" value="<%= cl.get(Calendar.YEAR) %>" id="yearId">
		<input type="hidden" name="monthId" value="<%= cl.get(Calendar.MONTH)+1 %>" id="monthId">
		<input type="hidden" name="dayId" value="<%= i %>" id="<%= i %>">

		<a href="javascript:void(0)" onclick="DayLink('<%= i %>','monthId','yearId');"><%= i %>
		</td></a> 
<% // 空白＋日付の数が７になったら列を変える
if ((firstDayWeek + i) % 7 == 0) { %>
</tr><tr>
	
	
	
	<% 
}
	} 
	} %>
	

	
	</tr>
	
	</tbody>
	</table>
<br><br>

<%-- 次月カレンダーテーブル --%>

<% 

// カレンダーに当日の年、月、１日をセットする
cl.set(cl.get(Calendar.YEAR),cl.get(Calendar.MONTH)+1,firstDay);



lastDay = cl.getActualMaximum(Calendar.DAY_OF_MONTH); // 月の最終日

firstDayWeek = cl.get(Calendar.DAY_OF_WEEK) - 1;  // 月の最初の曜日をintで求める。DAY_OF_WEEKは日曜日なら１を取得する？


%>

<%= month[(cl.get(Calendar.MONTH))] %>
<br><br>


<table>
	<thead>
	<tr>
	<th>日</th>
	<th>月</th>
	<th>火</th>
	<th>水</th>
	<th>木</th>
	<th>金</th>
	<th>土</th>
	</tr>
	</thead>
	
	<tbody>
	<tr>
	
	<% 
	// 月に合わせて１日の前の空白を表示
	for(int i =1; i <= firstDayWeek; i++){ %>
	<td> </td>
	<% } %>
	
	<% // 後は１日から月末までの日まで繰り返す
	for(int i = firstDay; i <= lastDay; i++){ %> 
	
	<td>
	<input type="hidden" name="yearId" value="<%= cl.get(Calendar.YEAR) %>" id="yearId">
	<input type="hidden" name="monthId" value="<%= cl.get(Calendar.MONTH) %>" id="monthId">
	<input type="hidden" name="dayId" value="<%= i %>" id="<%= i %>">	
		<% //月曜日は表示しない
		if((firstDayWeek + i) % 7 == 2) {
			 %>
			<%= i %></td>
			<%
		}else{
		 
		
		
		// ここで予約のある日を弾く（？年？月？日）で送る）
		int calenderMonth = cl.get(Calendar.MONTH) +1;
		 if(dao.SeachDay((cl.get(Calendar.YEAR)) + "年" + calenderMonth + "月" + i + "日") >= 16){ %>
		<%= i %></td> 
		
			<%     
		 }else{
			 %>
		<a href="javascript:void(0)" onclick="DayLink('<%= i %>','monthId','yearId');"><%= i %></a> </td>
		<%
} %>
	<% // 空白＋日付の数が７になったら列を変える
	if ((firstDayWeek + i) % 7 == 0) { %>
	</tr><tr>
	<% }
		}
	} %>
	
	</tr>
	
	</tbody>
	</table><br><br><br><br>




<%-- ============ フォーム送信 ============ --%>
	<script type="text/javascript">
	function DayLink(dayId,monthId,yearId){
			var form = document.forms[0];
			var input = document.getElementById(dayId);
			var input2 = document.getElementById(monthId);
			var input3 = document.getElementById(yearId);
			form.appendChild(input);
			form.appendChild(input2);
			form.appendChild(input3);
			document.body.appendChild(form);
			form.submit();	<%-- ここで送信 --%>
		}
	</script>
<%-- ============ フォーム送信 ============ --%>

</div>
</div>



</body>
</html>
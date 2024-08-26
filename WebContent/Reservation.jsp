<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <%-- カレンダー、コレクションのインポート --%>
    <%@page import="java.util.Calendar"%>
    <%@page import="java.util.Collections"%>
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


%>

<%  %>





<h1>予約画面</h1>
<br><br>
<%-- 今月（配列もカレンダーも０からのカウントなので＋１をする必要がない？） --%>
<%= month[(cl.get(Calendar.MONTH))] %>
<br>

<%-- ============ フォーム送信 ============ --%>
<form method="post" action="ReservationCon"></form>
<%-- ============ フォーム送信 ============ --%>


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
	
	<% // 月に合わせて１日の前の空白を表示
	for(int i =1; i <= firstDayWeek; i++){ %>
	<td> </td>
	<% } %>
	
	<% // 後は１日から月末までの日まで繰り返す
	for(int i = firstDay; i <= lastDay; i++){ %> 
	
	<td><input type="hidden" name="dayId"
					value="<%= i %>" id="<%= i %>">
					
		<a href="javascript:void(0)" onclick="DayLink('<%= i %>');"><%= i %></td></a> 
	<% // 空白＋日付の数が７になったら列を変える
	if ((firstDayWeek + i) % 7 == 0) { %>
	</tr><tr>
	<% }
	} %>
	
	</tr>
	
	</tbody>
	</table>



<%-- ============ フォーム送信 ============ --%>
	<script type="text/javascript">
	function DayLink(dayId){
			var form = document.forms[0];
			var input = document.getElementById(dayId);
			form.appendChild(input);
			document.body.appendChild(form);
			form.submit();	<%-- ここで送信 --%>
		}
	</script>
<%-- ============ フォーム送信 ============ --%>

</body>
</html>
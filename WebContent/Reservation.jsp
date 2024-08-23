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

int firstDayWeek = cl.get(Calendar.DAY_OF_WEEK) - 1; 
int lastDay = cl.getActualMaximum(Calendar.DAY_OF_MONTH); // 月の最終日

int firstDayWeek = cl.get(Calendar.DAY_OF_WEEK) - 1;  // 月の最初の曜日をintで求める。DAY_OF_WEEKは日曜日なら１を取得する？


%>

<%  %>





<h1>予約画面</h1>
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
	<% // firstDayWeekで出した数分、空の項目をコピーして出力する
	
	Collections.nCopies(firstDayWeek, " %> <td> </td> <% ") %>
	<% // 後は１日から月末までの日まで繰り返す
	for(int i = firstDay; i <= lastDay; i++){ %>
	<td><% System.out.print(i) %></td>>
	<% // 空白＋日付の数が７になったら列を変える
	if ((firstDayWeek + i) % 7 == 0) { %>
	</tr>
	
	
	</tbody>
	</table>





</body>
</html>
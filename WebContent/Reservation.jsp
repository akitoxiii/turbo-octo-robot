<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%-- カレンダーのインポート --%>
    <%@page import="java.util.Calendar"%>
    
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

<%-- ============ 本文ここから ============ --%>
<body>

<% Calendar cl = Calendar.getInstance(); 

String[] month = {"1月","2月","3月","4月","5月","6月","7月","8月","9月","10月","11月","12月"};
String[] week = {"日","月","火","水","木","金","土"};

int firstDay = 1; //月の初日

// カレンダーに当日の年、月、１日をセットする
cl.set(cl.get(Calendar.YEAR),cl.get(Calendar.MONTH),firstDay);

int firstDayWeek = cl.get(Calendar.DAY_OF_WEEK) - 1; 
int lastDay = cl.getActualMaximum(Calendar.DAY_OF_MONTH); //月の最終日


%>

<%  %>





<h1>予約画面</h1>
<br><br>





</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Forest 予約システム - 管理者マイページ</title>
    <style>
        body {
            font-family: Arial, sans-serif;
        }
        .admin-mypage-container {
            width: 400px;
            margin: 0 auto;
            padding: 30px;
            border: 1px solid #ccc;
            border-radius: 10px;
            background-color: #f9f9f9;
            text-align: center;
        }
        .admin-mypage-container h1 {
            font-size: 24px;
            margin-bottom: 20px;
        }
        .admin-mypage-container .user-info {
            margin-bottom: 20px;
        }
        .admin-mypage-container .user-info p {
            margin: 5px 0;
            font-size: 18px;
        }
        .admin-mypage-container input[type="button"] {
            width: 100%;
            padding: 10px;
            margin: 10px 0;
            background-color: #4CAF50;
            border: none;
            color: white;
            border-radius: 5px;
            cursor: pointer;
        }
        .admin-mypage-container a {
            display: block;
            margin: 10px 0;
            color: #008CBA;
            text-decoration: none;
        }
        .admin-mypage-container a:hover {
            text-decoration: underline;
        }
    </style>
</head>
<body>
    <div class="admin-mypage-container">
        <h1>Forest 予約システム</h1>
        <div class="user-info">
            <p>ID: <%= session.getAttribute("loginUserId") %></p>
            <p><%= session.getAttribute("loginUserName") %>さん</p>
        </div>
        <form action="reservationListServlet" method="get">
            <input type="button" value="全予約一覧" onclick="location.href='reservationList.jsp'">
        </form>
        <form action="reservationServlet" method="get">
            <input type="button" value="予約する" onclick="location.href='reservation.jsp'">
        </form>
        <form action="customerListServlet" method="get">
            <input type="button" value="会員一覧 (CSV出力)" onclick="location.href='customerList.csv'">
        </form>
        <form action="registerServlet" method="get">
            <input type="button" value="会員登録" onclick="location.href='register.jsp'">
        </form>
        <a href="logoutServlet">ログアウト</a>
    </div>
</body>
</html>
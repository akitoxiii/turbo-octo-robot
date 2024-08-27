<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Forest 予約システム - マイページ</title>
    <style>
        body {
            font-family: Arial, sans-serif;
        }
        .mypage-container {
            width: 400px;
            margin: 0 auto;
            padding: 30px;
            border: 1px solid #ccc;
            border-radius: 10px;
            background-color: #f9f9f9;
            text-align: center;
        }
        .mypage-container h1 {
            font-size: 24px;
            margin-bottom: 20px;
        }
        .mypage-container .user-info {
            margin-bottom: 20px;
        }
        .mypage-container .user-info p {
            margin: 5px 0;
            font-size: 18px;
        }
        .mypage-container input[type="button"] {
            width: 100%;
            padding: 10px;
            margin: 10px 0;
            background-color: #4CAF50;
            border: none;
            color: white;
            border-radius: 5px;
            cursor: pointer;
        }
        .mypage-container a {
            display: block;
            margin: 10px 0;
            color: #008CBA;
            text-decoration: none;
        }
        .mypage-container a:hover {
            text-decoration: underline;
        }
    </style>
</head>
<body>
    <div class="mypage-container">
        <h1>Forest 予約システム</h1>
        <div class="user-info">
            <p>ID: <%= session.getAttribute("loginUserId") %></p>
            <p><%= session.getAttribute("loginUserName") %>さん</p>
        </div>
        <form action="reservationServlet" method="post">
            <input type="button" value="予約する" onclick="location.href='reservation.jsp'">
            <input type="button" value="予約確認" onclick="location.href='reservationInfo.jsp'">
        </form>
        <a href="logoutServlet">ログアウト</a>
    </div>
</body>
</html>
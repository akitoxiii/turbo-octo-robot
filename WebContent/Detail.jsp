<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ja">
<head>
    <meta charset="UTF-8">
    <title>商品詳細画面</title>
    <link rel="stylesheet" type="text/css" href="css/Style.css">
    <style>
        body {
            font-family: -apple-system, BlinkMacSystemFont, "Segoe UI", Roboto, "Helvetica Neue", Arial, sans-serif;
            margin: 0;
            padding: 0;
            background-color: #f5f5f7;
            color: #1d1d1f;
        }
        .container {
            width: 80%;
            margin: 0 auto;
            padding: 20px;
            background-color: white;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }
        h1, h2, h3 {
            font-weight: 600;
        }
        .item-image {
            max-width: 100%;
        }
        .review, .submit-review {
            margin-top: 20px;
        }
        .star-rating {
            display: flex;
            flex-direction: row-reverse;
            justify-content: center;
        }
        .star-rating input[type=radio] {
            display: none;
        }
        .star-rating label {
            font-size: 2em;
            color: #ccc;
            cursor: pointer;
        }
        .star-rating input[type=radio]:checked ~ label {
            color: #f5b301;
        }
        .star-rating input[type=radio]:hover ~ label {
            color: #f5b301;
        }
        .button {
            background-color: #0071e3;
            color: white;
            border: none;
            padding: 10px 20px;
            cursor: pointer;
        }
        .button:hover {
            background-color: #005bb5;
        }
    </style>
</head>
<body>
    <div class="container">
        <h1>商品詳細画面</h1>
        <div>
            <h2>${item.itemName}</h2>
            <p>価格: ¥${item.itemPrice}</p>
            <p>在庫: ${item.stockCount}</p>
            <p>平均評価: ${item.averageRating}</p>
            <img class="item-image" src="${item.itemImagePath}" alt="${item.itemName}">
        </div>

        <div class="review">
            <h3>レビュー</h3>
            <c:forEach var="review" items="${item.reviews}">
                <div>
                    <p>${review.reviewText}</p>
                    <p>投稿者: ${review.userId} - 日付: ${review.reviewDate}</p>
                    <p>評価: ${review.rating} / 5</p>
                </div>
            </c:forEach>
        </div>

        <div class="submit-review">
            <h3>レビューを投稿する</h3>
            <form action="ReviewCon" method="post">
                <input type="hidden" name="itemNo" value="${item.itemNo}">
                <div>
                    <label for="reviewText">レビュー:</label>
                    <textarea name="reviewText" rows="4" cols="50"></textarea>
                </div>
                <div class="star-rating">
                    <input id="star5" name="rating" type="radio" value="5"><label for="star5">★</label>
                    <input id="star4" name="rating" type="radio" value="4"><label for="star4">★</label>
                    <input id="star3" name="rating" type="radio" value="3"><label for="star3">★</label>
                    <input id="star2" name="rating" type="radio" value="2"><label for="star2">★</label>
                    <input id="star1" name="rating" type="radio" value="1"><label for="star1">★</label>
                </div>
                <div>
                    <input class="button" type="submit" value="投稿">
                </div>
            </form>
        </div>

        <a href="ListCon" class="button">商品一覧に戻る</a>
    </div>
</body>
</html>
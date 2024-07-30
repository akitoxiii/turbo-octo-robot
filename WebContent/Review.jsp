<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ja">
<head>
    <meta charset="UTF-8">
    <title>商品レビュー画面</title>
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
        .review-item {
            padding: 10px;
            border-bottom: 1px solid #e0e0e0;
        }
        .review-item:last-child {
            border-bottom: none;
        }
        .no-reviews {
            text-align: center;
            color: #999;
            font-style: italic;
        }
    </style>
</head>
<body>
    <div class="container">
        <h1>商品レビュー画面</h1>
        <div>
            <h2>平均評価: <c:out value="${averageRating != null ? averageRating : '未評価'}" /> / 5</h2>
            <form action="ReviewCon" method="post">
                <input type="hidden" name="itemNo" value="${itemNo}">
                <div>
                    <label for="reviewText">レビュー:</label>
                    <textarea id="reviewText" name="reviewText" rows="4" cols="50" required></textarea>
                </div>
                <div>
                    <label for="rating">評価:</label>
                    <select id="rating" name="rating" required>
                        <option value="1">★☆☆☆☆</option>
                        <option value="2">★★☆☆☆</option>
                        <option value="3">★★★☆☆</option>
                        <option value="4">★★★★☆</option>
                        <option value="5">★★★★★</option>
                    </select>
                </div>
                <div>
                    <button class="button" type="submit">レビューを送信</button>
                </div>
            </form>
        </div>
        <div>
            <h2>レビュー一覧</h2>
            <c:choose>
                <c:when test="${not empty reviews}">
                    <c:forEach var="review" items="${reviews}">
                        <div class="review-item">
                            <p>評価: ${review.rating} / 5</p>
                            <p>レビュー: ${review.reviewText}</p>
                            <p>日時: ${review.reviewDate}</p>
                        </div>
                    </c:forEach>
                </c:when>
                <c:otherwise>
                    <p class="no-reviews">レビューがまだありません。</p>
                </c:otherwise>
            </c:choose>
        </div>
        <a href="ListCon" class="button">商品一覧に戻る</a>
    </div>
</body>
</html>
package model;

import java.io.Serializable;
import java.sql.Timestamp;

public class RatingBean implements Serializable {
    private static final long serialVersionUID = 1L;

    private int ratingId;
    private int itemNo;
    private int userId;
    private int rating;
    private Timestamp ratingDate;

    // ゲッターとセッター
    public int getRatingId() {
        return ratingId;
    }

    public void setRatingId(int ratingId) {
        this.ratingId = ratingId;
    }

    public int getItemNo() {
        return itemNo;
    }

    public void setItemNo(int itemNo) {
        this.itemNo = itemNo;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public Timestamp getRatingDate() {
        return ratingDate;
    }

    public void setRatingDate(Timestamp ratingDate) {
        this.ratingDate = ratingDate;
    }
}
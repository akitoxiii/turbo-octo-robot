package model;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

public class ItemBean implements Serializable {
	private static final long serialVersionUID = 1L;

	private int itemNo;
	private String itemName;
	private int itemPrice;
	private String itemImage;
	private String itemImagePath;
	private int specialItem;
	private int stockCount;
	private Timestamp updateTime;
	private List<ReviewBean> reviews; // レビューのリストを追加
	private double averageRating; // 平均評価を追加
	private String csvFilePath; // CSVファイルのパスを追加

	public ItemBean() {
	}

	public ItemBean(int itemNo, String itemName) {
		this.itemNo = itemNo;
		this.itemName = itemName;
	}

	// ゲッターとセッター
	public int getItemNo() {
		return itemNo;
	}

	public void setItemNo(int itemNo) {
		this.itemNo = itemNo;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public int getItemPrice() {
		return itemPrice;
	}

	public void setItemPrice(int itemPrice) {
		this.itemPrice = itemPrice;
	}

	public String getItemImage() {
		return itemImage;
	}

	public void setItemImage(String itemImage) {
		this.itemImage = itemImage;
	}

	public String getItemImagePath() {
		return itemImagePath;
	}

	public void setItemImagePath(String itemImagePath) {
		this.itemImagePath = itemImagePath;
	}

	public int getSpecialItem() {
		return specialItem;
	}

	public void setSpecialItem(int specialItem) {
		this.specialItem = specialItem;
	}

	public int getStockCount() {
		return stockCount;
	}

	public void setStockCount(int stockCount) {
		this.stockCount = stockCount;
	}

	public Timestamp getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Timestamp updateTime) {
		this.updateTime = updateTime;
	}

	public List<ReviewBean> getReviews() {
		return reviews;
	}

	public void setReviews(List<ReviewBean> reviews) {
		this.reviews = reviews;
	}

	public double getAverageRating() {
		return averageRating;
	}

	public void setAverageRating(double averageRating) {
		this.averageRating = averageRating;
	}

	public String getCsvFilePath() {
		return csvFilePath;
	}

	public void setCsvFilePath(String csvFilePath) {
		this.csvFilePath = csvFilePath;
	}
}
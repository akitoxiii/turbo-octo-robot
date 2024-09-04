package model;

import java.io.Serializable;

public class ReservationBean implements Serializable {
	
	private String reservationId;
	private String userId;
	private String userName;
	private String reservationDate;
	private String reservationTime;
	
	
	public String getUserName() {
		return userName;
	}
	
	
	public void setUserName(String UserName) {
		this.userName = UserName;
	}
	
	
	
	
	
	
	
	public String getReservationId() {
		return reservationId;
	}
	
	
	public void setReservationId(String reservationId) {
		this.reservationId = reservationId;
	}
	
	
	
	
	public String getUserId() {
		return userId;
	}
	
	
	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	
	
	
	
	public String getReservationDate() {
		return reservationDate;
	}
	
	
	public void setReservationDate(String reservationDate) {
		this.reservationDate = reservationDate;
	}
	
	
	
	

	
	
	
	public String getReservationTime() {
		return reservationTime;
	}
	
	public void setReservationTime(String reservationTime) {
		this.reservationTime = reservationTime;
	}
	
	
	
}

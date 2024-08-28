package model;

import java.io.Serializable;
import java.sql.Timestamp;

public class ReservationBean implements Serializable {
	
	private int reservationId;
	private String userId;
	private Timestamp reservationDate;
	private Timestamp updateDateTime;
	private int reservationTime;
	
	
	public int getReservationId() {
		return reservationId;
	}
	
	
	public void setReservationId(int reservationId) {
		this.reservationId = reservationId;
	}
	
	
	
	
	public String getUserId() {
		return userId;
	}
	
	
	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	
	
	
	
	public Timestamp getReservationDate() {
		return reservationDate;
	}
	
	
	public void setReservationDate(Timestamp reservationDate) {
		this.reservationDate = reservationDate;
	}
	
	
	
	
	
	public Timestamp getUpdateDateTime() {
		return updateDateTime;
	}
	
	public void setUpdateDateTime(Timestamp updateDateTime) {
		this.updateDateTime = updateDateTime;
	}
	
	
	
	
	public int getReservationTime() {
		return reservationTime;
	}
	
	public void setReservationTime(int reservationTime) {
		this.reservationTime = reservationTime;
	}
	
	
	
}

package model;

import java.io.Serializable;

public class UserIdBean implements Serializable{
	
	private String userId;
	private String userName;
	private String userEmail;
	private String userPassword;
	private String userAdress;
	private String userPhone;
	private int userPrivilege;
	
	
	public String getUserId() {
		return userId;
	}
	
	
	public void setUserId(String string) {
		this.userId = string;
	}
	
	
	
	
	
	public String getUserName() {
		return userName;
	}
	
	
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	
	
	
	
	public String getUserEmail() {
		return userEmail;
	}
	
	
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	
	
	
	
	
	public String getUserPassword() {
		return userPassword;
	}
	
	
	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}
	
	
	
	
	
	public String getUserAdress() {
		return userAdress;
	}
	
	
	public void setUserAdress(String userAdress) {
		this.userAdress = userAdress;
	}
	
	
	
	
	
	public String getUserPhone() {
		return userPhone;
	}
	
	
	public void setUserPhone(String userPhone) {
		this.userPhone = userPhone;
	}
	
	
	
	
	
	public int getUserPrivilege() {
		return userPrivilege;
	}
	
	
	public void setUserPrivilege(int userPrivilege) {
		this.userPrivilege = userPrivilege;
	}
	
	
	
	

}

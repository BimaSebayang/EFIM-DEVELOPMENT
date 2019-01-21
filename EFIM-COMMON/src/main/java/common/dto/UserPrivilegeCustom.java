package common.dto;

import java.io.Serializable;

public class UserPrivilegeCustom implements Serializable{
	private static final long serialVersionUID = -1807201580494528457L;
	
	private String userId;
	private String userName;
	private String userSessionCode;
	private byte[] userPhoto;
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserSessionCode() {
		return userSessionCode;
	}
	public void setUserSessionCode(String userSessionCode) {
		this.userSessionCode = userSessionCode;
	}
	public byte[] getUserPhoto() {
		return userPhoto;
	}
	public void setUserPhoto(byte[] userPhoto) {
		this.userPhoto = userPhoto;
	}

}

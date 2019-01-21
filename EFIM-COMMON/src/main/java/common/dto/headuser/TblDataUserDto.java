package common.dto.headuser;

import java.io.Serializable;
import java.util.Date;

public class TblDataUserDto implements Serializable{
	private static final long serialVersionUID = 4180449126938812144L;
    private String userId;
    private String userName;
    private String userPassword;
    private String userSessionCode;
    private Double userMaxDbstorage;
    private String userMail;
	private String userPhone;
    private String userPhoto;
    private String createdDate;
    private String userStatus;
    private String projectCode;
    private TblSessionUserDto tblSessionUserDto;
    private String messageEmail;
    private String subjecEmail;
    
	public String getMessageEmail() {
		return messageEmail;
	}
	public void setMessageEmail(String messageEmail) {
		this.messageEmail = messageEmail;
	}
	public String getSubjecEmail() {
		return subjecEmail;
	}
	public void setSubjecEmail(String subjecEmail) {
		this.subjecEmail = subjecEmail;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserPassword() {
		return userPassword;
	}
	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}
	public String getUserSessionCode() {
		return userSessionCode;
	}
	public void setUserSessionCode(String userSessionCode) {
		this.userSessionCode = userSessionCode;
	}
	public Double getUserMaxDbstorage() {
		return userMaxDbstorage;
	}
	public void setUserMaxDbstorage(Double userMaxDbstorage) {
		this.userMaxDbstorage = userMaxDbstorage;
	}
	public String getUserPhoto() {
		return userPhoto;
	}
	public void setUserPhoto(String userPhoto) {
		this.userPhoto = userPhoto;
	}
	public String getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}
	public String getUserStatus() {
		return userStatus;
	}
	public void setUserStatus(String userStatus) {
		this.userStatus = userStatus;
	}
	public String getProjectCode() {
		return projectCode;
	}
	public void setProjectCode(String projectCode) {
		this.projectCode = projectCode;
	}
	public TblSessionUserDto getTblSessionUserDto() {
		return tblSessionUserDto;
	}
	public void setTblSessionUserDto(TblSessionUserDto tblSessionUserDto) {
		this.tblSessionUserDto = tblSessionUserDto;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserMail() {
		return userMail;
	}
	public void setUserMail(String userMail) {
		this.userMail = userMail;
	}
	public String getUserPhone() {
		return userPhone;
	}
	public void setUserPhone(String userPhone) {
		this.userPhone = userPhone;
	}
    
    
}

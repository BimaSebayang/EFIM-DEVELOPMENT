package entity.headuser;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

import entity.headuser.pk.TblSessionUserPk;
import entity.headuser.pk.TempTblRegistrationPk;

@Entity
@Table(name="HEADUSER.TEMP_TBL_REGISTRATION")
@IdClass(TempTblRegistrationPk.class)
public class TempTblRegistration {
	
	private String regNo;
	private String userName;
	private String userId;
	private String userPassword;
	private String userMail;
	private String userPhone;
	private String createdDate;
	private String userStatus;
	private String projectCode;
	
	 @Id
	 @Column(name="REG_NO") 
	public String getRegNo() {
		return regNo;
	}
	public void setRegNo(String regNo) {
		this.regNo = regNo;
	}
	
	 @Column(name="USER_NAME") 
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	 @Column(name="USER_ID") 
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	 @Column(name="USER_PASSWORD") 
	public String getUserPassword() {
		return userPassword;
	}
	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}
	
	 @Column(name="USER_MAIL") 
	public String getUserMail() {
		return userMail;
	}
	public void setUserMail(String userMail) {
		this.userMail = userMail;
	}
	
	 @Column(name="USER_PHONE") 
	public String getUserPhone() {
		return userPhone;
	}
	public void setUserPhone(String userPhone) {
		this.userPhone = userPhone;
	}
	
	 @Column(name="CREATED_DATE") 
	public String getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}
	
	 @Column(name="USER_STATUS") 
	public String getUserStatus() {
		return userStatus;
	}
	public void setUserStatus(String userStatus) {
		this.userStatus = userStatus;
	}
	
	 @Column(name="PROJECT_CODE") 
	public String getProjectCode() {
		return projectCode;
	}
	public void setProjectCode(String projectCode) {
		this.projectCode = projectCode;
	}
	
	
	

}

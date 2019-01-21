package entity.headuser;

import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import entity.headuser.pk.TblDataUserPk;

@Entity
@Table(name = "HEADUSER.TBL_DATA_USER")
@IdClass(TblDataUserPk.class)
// @Access(AccessType.FIELD)
public class TblDataUser {
	private String userId;
	private String userName;
	private String userPassword;
	private String userMail;
	private String userPhone;
	private String userSessionCode;
	private Double userMaxDbstorage;
	private String userPhoto;
	private String createdDate;
	private String userStatus;
	private String projectCode;

	@Id
	@Column(name = "USER_ID")
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	@Column(name = "USER_MAIL")
	public String getUserMail() {
		return userMail;
	}

	public void setUserMail(String userMail) {
		this.userMail = userMail;
	}

	@Column(name = "USER_PHONE")
	public String getUserPhone() {
		return userPhone;
	}

	public void setUserPhone(String userPhone) {
		this.userPhone = userPhone;
	}

	@Column(name = "USER_PASSWORD")
	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	@Column(name = "USER_SESSION_CODE")
	public String getUserSessionCode() {
		return userSessionCode;
	}

	public void setUserSessionCode(String userSessionCode) {
		this.userSessionCode = userSessionCode;
	}

	@Column(name = "USER_MAX_DBSTORAGE")
	public Double getUserMaxDbstorage() {
		return userMaxDbstorage;
	}

	public void setUserMaxDbstorage(Double userMaxDbstorage) {
		this.userMaxDbstorage = userMaxDbstorage;
	}

	@Column(name = "USER_PHOTO")
	public String getUserPhoto() {
		return userPhoto;
	}

	public void setUserPhoto(String userPhoto) {
		this.userPhoto = userPhoto;
	}

	@Column(name = "CREATED_DATE")
	public String getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}

	@Column(name = "USER_STATUS")
	public String getUserStatus() {
		return userStatus;
	}

	public void setUserStatus(String userStatus) {
		this.userStatus = userStatus;
	}

	@Column(name = "PROJECT_CODE")
	public String getProjectCode() {
		return projectCode;
	}

	public void setProjectCode(String projectCode) {
		this.projectCode = projectCode;
	}

	@Column(name = "USER_NAME")
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	// Join column part --------------------------------- /////

	// @OneToOne(cascade = CascadeType.ALL)
	// @JoinColumn(name="USER_SESSION_CODE")
	// private TblSessionUser tblSessionUser;
	//
	// public TblSessionUser getTblSessionUser() {
	// return tblSessionUser;
	// }
	// public void setTblSessionUser(TblSessionUser tblSessionUser) {
	// this.tblSessionUser = tblSessionUser;
	// }
}

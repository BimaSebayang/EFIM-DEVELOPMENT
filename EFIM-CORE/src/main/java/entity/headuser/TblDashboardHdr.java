package entity.headuser;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

import entity.headuser.pk.TblDashboardHdrPk;

@Entity
@Table(name="HEADUSER.TBL_DASHBOARD_HDR")
@IdClass(TblDashboardHdrPk.class)
public class TblDashboardHdr {
	
	private String dashboardId;
	private String userSessionCode;
	private String dashboardFriendRoleCode;
	private String dashboardCommentDtlId;
	private String dashboardLoveDtlId;
	private String dashboardItemDtlId;
	private String dashboardNotification;
	private String dashboardStatus;
	private String dashboardShareStatus;
	private String createdDate;
	private String createdBy;
	
	@Id
	@Column(name="DASHBOARD_ID")
	public String getDashboardId() {
		return dashboardId;
	}
	public void setDashboardId(String dashboardId) {
		this.dashboardId = dashboardId;
	}
	
	@Column(name="USER_SESSION_CODE")
	public String getUserSessionCode() {
		return userSessionCode;
	}
	public void setUserSessionCode(String userSessionCode) {
		this.userSessionCode = userSessionCode;
	}
	
	@Column(name="DASHBOARD_FRIEND_ROLE_CODE")
	public String getDashboardFriendRoleCode() {
		return dashboardFriendRoleCode;
	}
	public void setDashboardFriendRoleCode(String dashboardFriendRoleCode) {
		this.dashboardFriendRoleCode = dashboardFriendRoleCode;
	}
	
	@Column(name="DASHBOARD_COMMENT_DTL_ID")
	public String getDashboardCommentDtlId() {
		return dashboardCommentDtlId;
	}
	public void setDashboardCommentDtlId(String dashboardCommentDtlId) {
		this.dashboardCommentDtlId = dashboardCommentDtlId;
	}
	
	@Column(name="DASHBOARD_LOVE_DTL_ID")
	public String getDashboardLoveDtlId() {
		return dashboardLoveDtlId;
	}
	public void setDashboardLoveDtlId(String dashboardLoveDtlId) {
		this.dashboardLoveDtlId = dashboardLoveDtlId;
	}
	
	@Column(name="DASHBOARD_ITEM_DTL_ID")
	public String getDashboardItemDtlId() {
		return dashboardItemDtlId;
	}
	public void setDashboardItemDtlId(String dashboardItemDtlId) {
		this.dashboardItemDtlId = dashboardItemDtlId;
	}
	
	@Column(name="DASHBOARD_NOTIFICATION")
	public String getDashboardNotification() {
		return dashboardNotification;
	}
	public void setDashboardNotification(String dashboardNotification) {
		this.dashboardNotification = dashboardNotification;
	}
	
	@Column(name="DASHBOARD_STATUS")
	public String getDashboardStatus() {
		return dashboardStatus;
	}
	public void setDashboardStatus(String dashboardStatus) {
		this.dashboardStatus = dashboardStatus;
	}
	
	
	@Column(name="DASHBOARD_SHARE_STATUS")
	public String getDashboardShareStatus() {
		return dashboardShareStatus;
	}
	public void setDashboardShareStatus(String dashboardShareStatus) {
		this.dashboardShareStatus = dashboardShareStatus;
	}
	
	@Column(name="CREATED_DATE")
	public String getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}
	
	@Column(name="CREATED_BY")
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	
    

}

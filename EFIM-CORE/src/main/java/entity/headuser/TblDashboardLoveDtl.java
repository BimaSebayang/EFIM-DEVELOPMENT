package entity.headuser;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

import entity.headuser.pk.TblDashboardLoveDtlPk;

@Entity
@Table(name="HEADUSER.TBL_DASHBOARD_LOVE_DTL")
@IdClass(TblDashboardLoveDtlPk.class)
public class TblDashboardLoveDtl {

	private String loveDtlId;
	private String dashboardLoveDtlId;
	private String userSessionCode;
	private String createdDate;
	private String createdBy;
	
	@Id
	@Column(name="LOVE_DTL_ID")
	public String getLoveDtlId() {
		return loveDtlId;
	}
	public void setLoveDtlId(String loveDtlId) {
		this.loveDtlId = loveDtlId;
	}
	
	@Column(name="DASHBOARD_LOVE_DTL_ID")
	public String getDashboardLoveDtlId() {
		return dashboardLoveDtlId;
	}
	public void setDashboardLoveDtlId(String dashboardLoveDtlId) {
		this.dashboardLoveDtlId = dashboardLoveDtlId;
	}
	
	@Column(name="USER_SESSION_CODE")
	public String getUserSessionCode() {
		return userSessionCode;
	}
	public void setUserSessionCode(String userSessionCode) {
		this.userSessionCode = userSessionCode;
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

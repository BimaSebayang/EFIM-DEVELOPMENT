package entity.headuser;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

import entity.headuser.pk.TblDashboardCommentDtlPk;

@Entity
@Table(name="HEADUSER.TBL_DASHBOARD_COMMENT_DTL")
@IdClass(TblDashboardCommentDtlPk.class)
public class TblDashboardCommentDtl {
	private String commentDtlId;
	private String dashboardCommentDtlId;
	private String commentChart;
	private String createdDate;
	private String createdBy;
	
	
	@Id
	@Column(name="COMMENT_DTL_ID")
	public String getCommentDtlId() {
		return commentDtlId;
	}
	public void setCommentDtlId(String commentDtlId) {
		this.commentDtlId = commentDtlId;
	}
	
	@Column(name="DASHBOARD_COMMENT_DTL_ID")
	public String getDashboardCommentDtlId() {
		return dashboardCommentDtlId;
	}
	public void setDashboardCommentDtlId(String dashboardCommentDtlId) {
		this.dashboardCommentDtlId = dashboardCommentDtlId;
	}
	
	@Column(name="COMMENT_CHART")
	public String getCommentChart() {
		return commentChart;
	}
	public void setCommentChart(String commentChart) {
		this.commentChart = commentChart;
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

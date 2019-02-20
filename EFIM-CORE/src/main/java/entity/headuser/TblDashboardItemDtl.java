package entity.headuser;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

import entity.headuser.pk.TblDashboardItemDtlPk;

@Entity
@Table(name="HEADUSER.TBL_DASHBOARD_ITEM_DTL")
@IdClass(TblDashboardItemDtlPk.class)
public class TblDashboardItemDtl {
	
	 private String itemDtlId;
	 private String dashboardItemDtlId;
	 private String fileIdRef;
	 private String createdDate;
	 private String createdBy;
	 
	@Id
	@Column(name="ITEM_DTL_ID")
	public String getItemDtlId() {
		return itemDtlId;
	}
	public void setItemDtlId(String itemDtlId) {
		this.itemDtlId = itemDtlId;
	}
	
	@Column(name="DASHBOARD_ITEM_DTL_ID")
	public String getDashboardItemDtlId() {
		return dashboardItemDtlId;
	}
	public void setDashboardItemDtlId(String dashboardItemDtlId) {
		this.dashboardItemDtlId = dashboardItemDtlId;
	}
	
	@Column(name="FILE_ID_REF")
	public String getFileIdRef() {
		return fileIdRef;
	}
	public void setFileIdRef(String fileIdRef) {
		this.fileIdRef = fileIdRef;
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

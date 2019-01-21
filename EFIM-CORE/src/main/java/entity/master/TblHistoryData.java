package entity.master;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import entity.master.pk.TblCodePk;
import entity.master.pk.TblHistoryDataPk;

@Entity
@Table(name="MASTER.TBL_HISTORY_DATA")
@IdClass(TblHistoryDataPk.class)
public class TblHistoryData {
	
	private String updateHistoryCode;
	private String trxId;
	private String formatId;
	private String historyLogBfr;
	private Integer sequenceEdited;
	private Integer flagDelete;
	private String reasonEdited;
	private String createdDate;
	private String projectCode;
	
	 @Id
	 @Column(name="UPDATE_HISTORY_CODE") 
	public String getUpdateHistoryCode() {
		return updateHistoryCode;
	}
	public void setUpdateHistoryCode(String updateHistoryCode) {
		this.updateHistoryCode = updateHistoryCode;
	}
	
	 @Column(name="TRX_ID") 
	public String getTrxId() {
		return trxId;
	}
	public void setTrxId(String trxId) {
		this.trxId = trxId;
	}
	
	 @Column(name="FORMAT_ID") 
	public String getFormatId() {
		return formatId;
	}
	public void setFormatId(String formatId) {
		this.formatId = formatId;
	}
	
	 @Column(name="HISTORY_LOG_BFR") 
	public String getHistoryLogBfr() {
		return historyLogBfr;
	}
	public void setHistoryLogBfr(String historyLogBfr) {
		this.historyLogBfr = historyLogBfr;
	}
	
	 @Column(name="SEQUENCE_EDITED") 
	public Integer getSequenceEdited() {
		return sequenceEdited;
	}
	public void setSequenceEdited(Integer sequenceEdited) {
		this.sequenceEdited = sequenceEdited;
	}
	
	@Column(name="FLAG_DELETE") 
	public Integer getFlagDelete() {
		return flagDelete;
	}
	public void setFlagDelete(Integer flagDelete) {
		this.flagDelete = flagDelete;
	}
	
	@Column(name="REASON_EDITED") 
	public String getReasonEdited() {
		return reasonEdited;
	}
	public void setReasonEdited(String reasonEdited) {
		this.reasonEdited = reasonEdited;
	}
	
	@Column(name="CREATED_DATE") 
	public String getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}
	
	@Column(name="PROJECT_CODE") 
	public String getProjectCode() {
		return projectCode;
	}
	public void setProjectCode(String projectCode) {
		this.projectCode = projectCode;
	}
	
	
}

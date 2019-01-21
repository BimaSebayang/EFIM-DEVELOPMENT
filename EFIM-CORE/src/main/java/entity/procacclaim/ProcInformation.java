package entity.procacclaim;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

import entity.procacclaim.pk.ProcInformationPk;

@Entity
@Table(name="PROC_ACCLAIM.PROC_INFORMATION")
@IdClass(ProcInformationPk.class)
public class ProcInformation implements Serializable{
	private static final long serialVersionUID = 7380590150901411708L;
	private String procId;
    private String procName;
    private Integer procSatatus;
    private String procNotes;
    private String createdDate;
    
    @Id
	@Column(name="PROC_ID") 
	public String getProcId() {
		return procId;
	}
	public void setProcId(String procId) {
		this.procId = procId;
	}
	
	@Column(name="PROC_NAME") 
	public String getProcName() {
		return procName;
	}
	public void setProcName(String procName) {
		this.procName = procName;
	}
	
	@Column(name="PROC_STATUS") 
	public Integer getProcSatatus() {
		return procSatatus;
	}
	public void setProcSatatus(Integer procSatatus) {
		this.procSatatus = procSatatus;
	}
	
	@Column(name="PROC_NOTES") 
	public String getProcNotes() {
		return procNotes;
	}
	public void setProcNotes(String procNotes) {
		this.procNotes = procNotes;
	}
	
	@Column(name="CREATED_DATE") 
	public String getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}
	
	
    
    
}

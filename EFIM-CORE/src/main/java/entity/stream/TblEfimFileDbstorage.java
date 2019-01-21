
package entity.stream;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

import entity.stream.pk.TblEfimFileDbstoragePk;

@Entity
@Table(name = "STREAM.TBL_EFIM_FILE_DBSTORAGE")
@IdClass(TblEfimFileDbstoragePk.class)
public class TblEfimFileDbstorage {

	private UUID fileStrId;
	private String fileStrIdReff;
	private byte[] fileStr;
	private String fileIdRef;
	
	@Column(name = "FILE_STR_ID")
	public UUID getFileStrId() {
		return fileStrId;
	}
	public void setFileStrId(UUID fileStrId) {
		this.fileStrId = fileStrId;
	}
	
	
	@Column(name = "FILE_STR_ID_REFF")
	public String getFileStrIdReff() {
		return fileStrIdReff;
	}
	public void setFileStrIdReff(String fileStrIdReff) {
		this.fileStrIdReff = fileStrIdReff;
	}
	
	@Column(name = "FILE_STR")
	public byte[] getFileStr() {
		return fileStr;
	}
	public void setFileStr(byte[] fileStr) {
		this.fileStr = fileStr;
	}
	
	@Id
	@Column(name = "FILE_ID_REF")
	public String getFileIdRef() {
		return fileIdRef;
	}
	public void setFileIdRef(String fileIdRef) {
		this.fileIdRef = fileIdRef;
	}
	
}

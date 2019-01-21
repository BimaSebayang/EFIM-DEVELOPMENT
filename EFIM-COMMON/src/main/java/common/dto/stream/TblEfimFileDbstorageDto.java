package common.dto.stream;

import java.io.Serializable;
import java.util.UUID;

public class TblEfimFileDbstorageDto implements Serializable{

	private static final long serialVersionUID = -2799671296297131110L;

	
	private UUID fileStrId;
	private String fileStrIdReff;
	private byte[] fileStr;
	private String fileIdRef;
	public UUID getFileStrId() {
		return fileStrId;
	}
	public void setFileStrId(UUID fileStrId) {
		this.fileStrId = fileStrId;
	}
	public String getFileStrIdReff() {
		return fileStrIdReff;
	}
	public void setFileStrIdReff(String fileStrIdReff) {
		this.fileStrIdReff = fileStrIdReff;
	}
	public byte[] getFileStr() {
		return fileStr;
	}
	public void setFileStr(byte[] fileStr) {
		this.fileStr = fileStr;
	}
	public String getFileIdRef() {
		return fileIdRef;
	}
	public void setFileIdRef(String fileIdRef) {
		this.fileIdRef = fileIdRef;
	}
	
}

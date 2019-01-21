package common.dto.headuser;

import java.io.Serializable;

import common.dto.stream.TblEfimFileDbstorageDto;

public class TblEfimDbDto implements Serializable{

	private static final long serialVersionUID = -8131168631090955073L;
	private String fileIdReff;
	private String fileName;
	private Double fileSize;
	private String fileStrIdReff;
	private String fileOwner;
	private String hiddenFileCode;
	private String createdDate;
	private String createdBy;
	private String updatedDate;
	private String updatedBy;
	private String fileStatus;
	private String projectCode;
	private String fileType;
	private TblEfimFileDbstorageDto tblEfimFileDbstorageDto;
	
	public String getFileIdReff() {
		return fileIdReff;
	}
	public void setFileIdReff(String fileIdReff) {
		this.fileIdReff = fileIdReff;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public Double getFileSize() {
		return fileSize;
	}
	public void setFileSize(Double fileSize) {
		this.fileSize = fileSize;
	}
	public String getFileStrIdReff() {
		return fileStrIdReff;
	}
	public void setFileStrIdReff(String fileStrIdReff) {
		this.fileStrIdReff = fileStrIdReff;
	}
	public String getFileOwner() {
		return fileOwner;
	}
	public void setFileOwner(String fileOwner) {
		this.fileOwner = fileOwner;
	}
	public String getHiddenFileCode() {
		return hiddenFileCode;
	}
	public void setHiddenFileCode(String hiddenFileCode) {
		this.hiddenFileCode = hiddenFileCode;
	}
	public String getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	public String getUpdatedDate() {
		return updatedDate;
	}
	public void setUpdatedDate(String updatedDate) {
		this.updatedDate = updatedDate;
	}
	public String getUpdatedBy() {
		return updatedBy;
	}
	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}
	public String getFileStatus() {
		return fileStatus;
	}
	public void setFileStatus(String fileStatus) {
		this.fileStatus = fileStatus;
	}
	public String getProjectCode() {
		return projectCode;
	}
	public void setProjectCode(String projectCode) {
		this.projectCode = projectCode;
	}
	public String getFileType() {
		return fileType;
	}
	public void setFileType(String fileType) {
		this.fileType = fileType;
	}
	public TblEfimFileDbstorageDto getTblEfimFileDbstorageDto() {
		return tblEfimFileDbstorageDto;
	}
	public void setTblEfimFileDbstorageDto(TblEfimFileDbstorageDto tblEfimFileDbstorageDto) {
		this.tblEfimFileDbstorageDto = tblEfimFileDbstorageDto;
	}
	
	
}

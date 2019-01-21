package entity.headuser;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

import entity.headuser.pk.TblDataUserPk;
import entity.headuser.pk.TblEfimDbPk;

@Entity
@Table(name = "HEADUSER.TBL_EFIM_DB")
@IdClass(TblEfimDbPk.class)
public class TblEfimDb implements Serializable{
	private static final long serialVersionUID = 6824449861746780827L;

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
	

	@Id
	@Column(name = "FILE_ID_REF")
	public String getFileIdReff() {
		return fileIdReff;
	}
	public void setFileIdReff(String fileIdReff) {
		this.fileIdReff = fileIdReff;
	}
	
	@Column(name = "FILE_NAME")
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	
	@Column(name = "FILE_SIZE")
	public Double getFileSize() {
		return fileSize;
	}
	public void setFileSize(Double fileSize) {
		this.fileSize = fileSize;
	}
	
	@Column(name = "FILE_STR_ID_REFF")
	public String getFileStrIdReff() {
		return fileStrIdReff;
	}
	public void setFileStrIdReff(String fileStrIdReff) {
		this.fileStrIdReff = fileStrIdReff;
	}
	
	@Column(name = "FILE_OWNER")
	public String getFileOwner() {
		return fileOwner;
	}
	public void setFileOwner(String fileOwner) {
		this.fileOwner = fileOwner;
	}
	
	@Column(name = "HIDDEN_FILE_CODE")
	public String getHiddenFileCode() {
		return hiddenFileCode;
	}
	public void setHiddenFileCode(String hiddenFileCode) {
		this.hiddenFileCode = hiddenFileCode;
	}
	
	@Column(name = "CREATED_DATE")
	public String getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}
	
	@Column(name = "CREATED_BY")
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	
	@Column(name = "UPDATED_DATE")
	public String getUpdatedDate() {
		return updatedDate;
	}
	public void setUpdatedDate(String updatedDate) {
		this.updatedDate = updatedDate;
	}
	
	@Column(name = "UPDATED_BY")
	public String getUpdatedBy() {
		return updatedBy;
	}
	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}
	
	@Column(name = "FILE_STATUS")
	public String getFileStatus() {
		return fileStatus;
	}
	public void setFileStatus(String fileStatus) {
		this.fileStatus = fileStatus;
	}
	
	@Column(name = "PROJECT_CODE")
	public String getProjectCode() {
		return projectCode;
	}
	public void setProjectCode(String projectCode) {
		this.projectCode = projectCode;
	}
	
	@Column(name = "FILE_TYPE")
	public String getFileType() {
		return fileType;
	}
	public void setFileType(String fileType) {
		this.fileType = fileType;
	}
	
	
	
	
}

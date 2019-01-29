package id.co.roxas.efim.solr.model;

import org.apache.solr.client.solrj.beans.Field;
import org.springframework.data.annotation.Id;
import org.springframework.data.solr.core.mapping.Indexed;
import org.springframework.data.solr.core.mapping.SolrDocument;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SolrDocument(collection="TBL_EFIM_DB_REPO")
public class TblEfimDb {
	
	
	@Id
	@Field
	private String id;
	@Field
	private String fileIdReff;
	@Field
	private String fileName;
	@Field
	private Double fileSize;
	@Field
	private String fileStrIdReff;
	@Field
	private String fileOwner;
	@Field
	private String hiddenFileCode;
	@Field
	private String createdDate;
	@Field
	private String createdBy;
	@Field
	private String updatedDate;
	@Field
	private String updatedBy;
	@Field
	private String fileStatus;
	@Field
	private String projectCode;
	@Field
	private String fileType;
	
	
	
	public TblEfimDb(String id,String fileIdReff, String fileName, Double fileSize, String fileStrIdReff, String fileOwner,
			String hiddenFileCode, String createdDate, String createdBy, String updatedDate, String updatedBy,
			String fileStatus, String projectCode, String fileType) {
		this.id = id;
		this.fileIdReff = fileIdReff;
		this.fileName = fileName;
		this.fileSize = fileSize;
		this.fileStrIdReff = fileStrIdReff;
		this.fileOwner = fileOwner;
		this.hiddenFileCode = hiddenFileCode;
		this.createdDate = createdDate;
		this.createdBy = createdBy;
		this.updatedDate = updatedDate;
		this.updatedBy = updatedBy;
		this.fileStatus = fileStatus;
		this.projectCode = projectCode;
		this.fileType = fileType;
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

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
	
	
	
}


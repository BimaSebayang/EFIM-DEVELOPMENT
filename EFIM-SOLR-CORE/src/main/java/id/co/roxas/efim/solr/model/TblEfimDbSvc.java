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
@SolrDocument(collection="TBL_EFIM_DB_DAO")
public class TblEfimDbSvc {
	
	
	@Id
	@Field
	private int id;
	@Field
	private String fileIdReff;
	@Field
	private String fileName;
	@Field
	private Double fileSize;
	
	public TblEfimDbSvc() {
	}

	public TblEfimDbSvc(int id, String fileIdReff, String fileName, Double fileSize) {
		this.id = id;
		this.fileIdReff = fileIdReff;
		this.fileName = fileName;
		this.fileSize = fileSize;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
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
	
	
	
}


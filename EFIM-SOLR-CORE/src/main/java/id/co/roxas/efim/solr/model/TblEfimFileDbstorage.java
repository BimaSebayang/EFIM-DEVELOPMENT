package id.co.roxas.efim.solr.model;

import java.io.Serializable;
import java.util.UUID;

import org.apache.solr.client.solrj.beans.Field;
import org.springframework.data.annotation.Id;
import org.springframework.data.solr.core.mapping.SolrDocument;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SolrDocument(collection="TBL_EFIM_DBSTORAGE_REPO")
public class TblEfimFileDbstorage implements Serializable{

	private static final long serialVersionUID = -2799671296297131110L;

	@Id
	@Field
	private String id;
	@Field
	private UUID fileStrId;
	@Field
	private String fileStrIdReff;
	@Field
	private byte[] fileStr;
	@Field
	private String fileIdRef;
	
	
	
	
	public TblEfimFileDbstorage(String id, UUID fileStrId, String fileStrIdReff, byte[] fileStr, String fileIdRef) {
		super();
		this.id = id;
		this.fileStrId = fileStrId;
		this.fileStrIdReff = fileStrIdReff;
		this.fileStr = fileStr;
		this.fileIdRef = fileIdRef;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
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

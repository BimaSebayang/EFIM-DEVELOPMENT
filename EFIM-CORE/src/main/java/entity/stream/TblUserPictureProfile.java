package entity.stream;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

import entity.stream.pk.TblPictureFrontEndPk;
import entity.stream.pk.TblUserPictureProfilePk;

@Entity
@Table(name = "STREAM.TBL_USER_PICTURE_PROFILE")
@IdClass(TblUserPictureProfilePk.class)
public class TblUserPictureProfile {

	private UUID picProfileId;
	private String picProfileNo;
	private byte[] picProfilePath;
	private String projectCode;
	
	
	@Column(name = "PIC_PROFILE_ID")
	public UUID getPicProfileId() {
		return picProfileId;
	}
	
	
	public void setPicProfileId(UUID picProfileId) {
		this.picProfileId = picProfileId;
	}
	
	@Id
	@Column(name = "PIC_PROFILE_NO")
	public String getPicProfileNo() {
		return picProfileNo;
	}
	
	public void setPicProfileNo(String picProfileNo) {
		this.picProfileNo = picProfileNo;
	}
	
	
	@Column(name = "PIC_PROFILE_PATH")
	public byte[] getPicProfilePath() {
		return picProfilePath;
	}
	public void setPicProfilePath(byte[] picProfilePath) {
		this.picProfilePath = picProfilePath;
	}
	
	
	@Column(name = "PROJECT_CODE")
	public String getProjectCode() {
		return projectCode;
	}
	public void setProjectCode(String projectCode) {
		this.projectCode = projectCode;
	}
	
}

package entity.stream;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

import entity.stream.pk.TblPictureFrontEndPk;

@Entity
@Table(name = "STREAM.TBL_PIC_FRONT_END")
@IdClass(TblPictureFrontEndPk.class)
public class TblPictureFrontEnd {
	private UUID pictureId;
	private String pictureName;
	private byte[] picturePath;
	private String projectCode;

	@Column(name = "PICTURE_ID")
	public UUID getPictureId() {
		return pictureId;
	}
	public void setPictureId(UUID pictureId) {
		this.pictureId = pictureId;
	}
	
	@Id
	@Column(name = "PICTURE_NAME")
	public String getPictureName() {
		return pictureName;
	}
	public void setPictureName(String pictureName) {
		this.pictureName = pictureName;
	}
	@Column(name = "PICTURE_PATH")
	public byte[] getPicturePath() {
		return picturePath;
	}
	public void setPicturePath(byte[] picturePath) {
		this.picturePath = picturePath;
	}
	
	@Column(name = "PROJECT_CODE")
	public String getProjectCode() {
		return projectCode;
	}
	public void setProjectCode(String projectCode) {
		this.projectCode = projectCode;
	}

	

}

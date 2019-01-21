package common.dto.stream;

import java.io.Serializable;
import java.util.UUID;

public class TblPictureFrontEndDto implements Serializable{

	private static final long serialVersionUID = 5747978263962766419L;
	
	private UUID pictureId;
	private String pictureName;
	private byte[] picturePath;
	private String projectCode;
	public UUID getPictureId() {
		return pictureId;
	}
	public void setPictureId(UUID pictureId) {
		this.pictureId = pictureId;
	}
	public String getPictureName() {
		return pictureName;
	}
	public void setPictureName(String pictureName) {
		this.pictureName = pictureName;
	}
	public byte[] getPicturePath() {
		return picturePath;
	}
	public void setPicturePath(byte[] picturePath) {
		this.picturePath = picturePath;
	}
	public String getProjectCode() {
		return projectCode;
	}
	public void setProjectCode(String projectCode) {
		this.projectCode = projectCode;
	}

}

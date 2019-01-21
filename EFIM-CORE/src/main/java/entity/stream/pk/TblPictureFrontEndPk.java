package entity.stream.pk;

import java.io.Serializable;
import java.util.UUID;

public class TblPictureFrontEndPk implements Serializable{
	private static final long serialVersionUID = 4553326939183578470L;
	private String pictureName;
	
	
	
	public String getPictureName() {
		return pictureName;
	}

	public void setPictureName(String pictureName) {
		this.pictureName = pictureName;
	}

	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return super.hashCode();
	}
	
	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		return super.equals(obj);
	}
	
}

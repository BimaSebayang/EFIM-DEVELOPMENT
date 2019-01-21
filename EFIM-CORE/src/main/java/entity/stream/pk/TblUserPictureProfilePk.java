package entity.stream.pk;

import java.io.Serializable;

public class TblUserPictureProfilePk implements Serializable{
	private static final long serialVersionUID = 6948745466283927539L;
	private String picProfileNo;

	public String getPicProfileNo() {
		return picProfileNo;
	}

	public void setPicProfileNo(String picProfileNo) {
		this.picProfileNo = picProfileNo;
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

package entity.headuser.pk;

import java.io.Serializable;

public class TblDataUserPk implements Serializable {

	private static final long serialVersionUID = -8384460176219708490L;

	private String userId;
	
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
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

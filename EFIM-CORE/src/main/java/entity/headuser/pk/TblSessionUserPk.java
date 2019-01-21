package entity.headuser.pk;

import java.io.Serializable;

public class TblSessionUserPk implements Serializable{

	private static final long serialVersionUID = -943074528539825796L;

	private String userSessionCode;

	public String getUserSessionCode() {
		return userSessionCode;
	}

	public void setUserSessionCode(String userSessionCode) {
		this.userSessionCode = userSessionCode;
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

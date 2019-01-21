package entity.headuser.pk;

import java.io.Serializable;

public class TempTblRegistrationPk implements Serializable{

	private static final long serialVersionUID = -943074528539825796L;

	private String regNo;
	
	public String getRegNo() {
		return regNo;
	}

	public void setRegNo(String regNo) {
		this.regNo = regNo;
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

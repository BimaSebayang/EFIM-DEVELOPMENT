package entity.headuser.pk;

import java.io.Serializable;

public class TblDashboardLoveDtlPk implements Serializable{

	private static final long serialVersionUID = 8042625808279629075L;
	private String loveDtlId;
	public String getLoveDtlId() {
		return loveDtlId;
	}
	public void setLoveDtlId(String loveDtlId) {
		this.loveDtlId = loveDtlId;
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

package entity.procacclaim.pk;

import java.io.Serializable;

public class ProcInformationPk implements Serializable{
	private static final long serialVersionUID = -4922032407999349054L;
	private String procId;
	
	public String getProcId() {
		return procId;
	}

	public void setProcId(String procId) {
		this.procId = procId;
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

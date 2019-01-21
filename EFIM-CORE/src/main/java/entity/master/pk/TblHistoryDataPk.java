package entity.master.pk;

import java.io.Serializable;

public class TblHistoryDataPk implements Serializable{

	private static final long serialVersionUID = -1711824364820982491L;

	private String updateHistoryCode;

	public String getUpdateHistoryCode() {
		return updateHistoryCode;
	}

	public void setUpdateHistoryCode(String updateHistoryCode) {
		this.updateHistoryCode = updateHistoryCode;
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

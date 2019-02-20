package entity.headuser.pk;

import java.io.Serializable;

public class TblDashboardItemDtlPk implements Serializable{

	private static final long serialVersionUID = 1312908825379230072L;

	private String itemDtlId;

	public String getItemDtlId() {
		return itemDtlId;
	}

	public void setItemDtlId(String itemDtlId) {
		this.itemDtlId = itemDtlId;
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

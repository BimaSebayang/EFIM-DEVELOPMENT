package entity.headuser.pk;

import java.io.Serializable;

public class TblDashboardHdrPk implements Serializable{

	private static final long serialVersionUID = 8683049825840404147L;

	private String dashboardId;

	public String getDashboardId() {
		return dashboardId;
	}

	public void setDashboardId(String dashboardId) {
		this.dashboardId = dashboardId;
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


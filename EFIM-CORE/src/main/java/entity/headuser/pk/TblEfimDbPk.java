package entity.headuser.pk;

import java.io.Serializable;

public class TblEfimDbPk implements Serializable{
	private static final long serialVersionUID = -5056657650826870510L;

	private String fileIdReff;

	public String getFileIdReff() {
		return fileIdReff;
	}

	public void setFileIdReff(String fileIdReff) {
		this.fileIdReff = fileIdReff;
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

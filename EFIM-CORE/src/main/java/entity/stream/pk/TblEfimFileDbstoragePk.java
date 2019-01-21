package entity.stream.pk;

import java.io.Serializable;

public class TblEfimFileDbstoragePk implements Serializable{
	
	private static final long serialVersionUID = 5853896828140473515L;

	private String fileIdRef;
	
	public String getFileIdRef() {
		return fileIdRef;
	}

	public void setFileIdRef(String fileIdRef) {
		this.fileIdRef = fileIdRef;
	}

	@Override
	public int hashCode() {
		return super.hashCode();
	}
	
	@Override
	public boolean equals(Object obj) {
		return super.equals(obj);
	}

}

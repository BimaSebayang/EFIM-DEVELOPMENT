package entity.headuser.pk;

import java.io.Serializable;

public class TblDashboardCommentDtlPk implements Serializable{
	private static final long serialVersionUID = 6938459338970560744L;
	
	private String commentDtlId;
	
	public String getCommentDtlId() {
		return commentDtlId;
	}

	public void setCommentDtlId(String commentDtlId) {
		this.commentDtlId = commentDtlId;
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

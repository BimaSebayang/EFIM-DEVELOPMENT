package common.dto.master;

import java.io.Serializable;

public class TblCodeDto implements Serializable{
	private static final long serialVersionUID = 1004162385261553660L;
    private String mstCode;
    private String mstColumnName;
    private String mstConditionName;
    private String notes;
    private String mstCodeType;
    private String mstCodeTypeName;
    private String typeDate;
    
    
	public String getMstCode() {
		return mstCode;
	}
	public void setMstCode(String mstCode) {
		this.mstCode = mstCode;
	}
	public String getMstColumnName() {
		return mstColumnName;
	}
	public void setMstColumnName(String mstColumnName) {
		this.mstColumnName = mstColumnName;
	}
	public String getMstConditionName() {
		return mstConditionName;
	}
	public void setMstConditionName(String mstConditionName) {
		this.mstConditionName = mstConditionName;
	}
	public String getNotes() {
		return notes;
	}
	public void setNotes(String notes) {
		this.notes = notes;
	}
	public String getMstCodeType() {
		return mstCodeType;
	}
	public void setMstCodeType(String mstCodeType) {
		this.mstCodeType = mstCodeType;
	}
	public String getMstCodeTypeName() {
		return mstCodeTypeName;
	}
	public void setMstCodeTypeName(String mstCodeTypeName) {
		this.mstCodeTypeName = mstCodeTypeName;
	}
	public String getTypeDate() {
		return typeDate;
	}
	public void setTypeDate(String typeDate) {
		this.typeDate = typeDate;
	}
}

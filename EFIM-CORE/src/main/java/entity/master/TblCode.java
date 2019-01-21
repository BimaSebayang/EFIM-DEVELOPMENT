package entity.master;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import entity.master.pk.TblCodePk;

@Entity
@Table(name="MASTER.TBL_CODE")
@IdClass(TblCodePk.class)
public class TblCode {
	    private String mstCode;
	    private String mstColumnName;
	    private String mstConditionName;
	    private String notes;
	    private String mstCodeType;
	    private String mstCodeTypeName;
	    private String typeDate;
	    
	    @Id
		@Column(name="MST_CODE") 
		public String getMstCode() {
			return mstCode;
		}
		
		public void setMstCode(String mstCode) {
			this.mstCode = mstCode;
		}
		
		@Column(name="MST_COLUMN_NAME") 
		public String getMstColumnName() {
			return mstColumnName;
		}
		
		public void setMstColumnName(String mstColumnName) {
			this.mstColumnName = mstColumnName;
		}
		
		@Column(name="MST_CONDITION_NAME") 
		public String getMstConditionName() {
			return mstConditionName;
		}
		
		public void setMstConditionName(String mstConditionName) {
			this.mstConditionName = mstConditionName;
		}
		
		@Column(name="NOTES") 
		public String getNotes() {
			return notes;
		}
		
		public void setNotes(String notes) {
			this.notes = notes;
		}
		
		@Column(name="MST_CODE_TYPE") 
		public String getMstCodeType() {
			return mstCodeType;
		}
		
		public void setMstCodeType(String mstCodeType) {
			this.mstCodeType = mstCodeType;
		}
		
		@Column(name="MST_CODE_TYPE_NAME") 
		public String getMstCodeTypeName() {
			return mstCodeTypeName;
		}
		
		public void setMstCodeTypeName(String mstCodeTypeName) {
			this.mstCodeTypeName = mstCodeTypeName;
		}
		
		@Column(name="TYPE_DATA") 
		public String getTypeDate() {
			return typeDate;
			}
		
		public void setTypeDate(String typeDate) {
			this.typeDate = typeDate;
		}
}

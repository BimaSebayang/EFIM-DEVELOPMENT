package common.dto.master;

import java.io.Serializable;
import java.util.Date;

public class TblHistoryDataDto implements Serializable{

	private static final long serialVersionUID = -4815950420737286194L;

	private String updateHistoryCode;
	private String historyLog;
	private String createdDate;
	private String projectCode;
	public String getUpdateHistoryCode() {
		return updateHistoryCode;
	}
	public void setUpdateHistoryCode(String updateHistoryCode) {
		this.updateHistoryCode = updateHistoryCode;
	}
	public String getHistoryLog() {
		return historyLog;
	}
	public void setHistoryLog(String historyLog) {
		this.historyLog = historyLog;
	}
	public String getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}
	public String getProjectCode() {
		return projectCode;
	}
	public void setProjectCode(String projectCode) {
		this.projectCode = projectCode;
	}
	
	
	
	
}

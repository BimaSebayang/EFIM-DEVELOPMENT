package service.master;

import java.util.Map;

public interface TblCodeSvc {
	Map<String, Object> getCurrentInformationCodeFromMstCodeType(String mstCodeType);
	
	Map<String, Object> getCurrentInformationCodeFromMstCode(String mstCode);
   
}

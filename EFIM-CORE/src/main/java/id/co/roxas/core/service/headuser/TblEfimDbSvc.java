package id.co.roxas.core.service.headuser;

import java.util.List;
import java.util.Map;

import id.co.roxas.efim.common.common.dto.headuser.TblEfimDbDto;

public interface TblEfimDbSvc {
   public Map<String,Object> getUserInformativeMotive(Map<String, Object> mapResult);
   
   public Map<String,Object> getAllDataAndFileOwner(Map<String, Object> mapResult, int page);
   
   public Map<String,Object> searchFileDataWithSensitive(String search, String fileStrIdReff,String projectCode,String caseSensitive, String fileType ,int page);
}

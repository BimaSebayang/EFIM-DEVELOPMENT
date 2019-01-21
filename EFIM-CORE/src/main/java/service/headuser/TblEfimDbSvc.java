package service.headuser;

import java.util.Map;

public interface TblEfimDbSvc {
   public Map<String,Object> getUserInformativeMotive(Map<String, Object> mapResult);
   
   public Map<String,Object> getAllDataAndFileOwner(Map<String, Object> mapResult);
}

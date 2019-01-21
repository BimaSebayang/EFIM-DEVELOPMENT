package service.headuser;

import java.util.Map;

public interface TblDataUserSvc {
  
	public Map<String,Object> getTheResultEmailAddress(String userId, String projectId);
	
	public Map<String, Object> getTheResultAllExistingUser(String projectCode);
	
}

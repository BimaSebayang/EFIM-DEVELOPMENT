package controller.forgotpass;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import dao.ProcedureDao;
import service.headuser.TblDataUserSvc;
import webservice.global.WsResponse;

@RestController
@RequestMapping("/ForgotPassCtl")
public class ForgotPassCtl {
	@Autowired
	private TblDataUserSvc tblDataUserSvc;
	
	@Autowired
	private ProcedureDao procedureDao;
	
	@RequestMapping(value = "/GetEmail",  method = RequestMethod.POST,params = {"projectCode"})
    public WsResponse checkIfValidUser(@RequestBody String userId, 
    		@RequestHeader Map<String, Object> rh,
    		@RequestParam String projectCode) {
    String TS = new SimpleDateFormat("dd.MM.yyyy.HH.mm.ss").format(new Date());
    	
        procedureDao.spCreatedHistoryAccess((String)rh.get("ip"),(String)rh.get("mac") , (String)rh.get("sn"), TS, "NONE",
	    		  "http://localhost:8080/EFIM-CORE/ForgotPassCtl/GetEmail", projectCode);
    
        Map<String, Object> mapResult = tblDataUserSvc.getTheResultEmailAddress(userId, projectCode);
    	WsResponse wsResponse = new WsResponse(mapResult.get("content"),(int)mapResult.get("count"),false,null,null);
    	return wsResponse;
    }
	
	@RequestMapping(value = "/GetUserId",  method = RequestMethod.POST,params = {"projectCode"})
    public WsResponse GetUserIdToChange(@RequestBody String emailNo, 
    		@RequestHeader Map<String, Object> rh,
    		@RequestParam String projectCode) {
    String TS = new SimpleDateFormat("dd.MM.yyyy.HH.mm.ss").format(new Date());
    	
        procedureDao.spCreatedHistoryAccess((String)rh.get("ip"),(String)rh.get("mac") , (String)rh.get("sn"), TS, "NONE",
	    		  "http://localhost:8080/EFIM-CORE/ForgotPassCtl/GetUserId", projectCode);
    
        String userId = procedureDao.spCheckEmailIsValid(emailNo, projectCode);
    	WsResponse wsResponse = new WsResponse(userId,1);	
    	return wsResponse;
    }
	
	@RequestMapping(value = "/ResetPassword",  method = RequestMethod.POST,params = {"projectCode"})
    public WsResponse checkIfValidUser(@RequestBody Map<String, String> mapper, 
    		@RequestHeader Map<String, Object> rh,
    		@RequestParam String projectCode) {
        String TS = new SimpleDateFormat("dd.MM.yyyy.HH.mm.ss").format(new Date());
    	
        procedureDao.spCreatedHistoryAccess((String)rh.get("ip"),(String)rh.get("mac") , (String)rh.get("sn"), TS, "NONE",
	    		  "http://localhost:8080/EFIM-CORE/ForgotPassCtl/ResetPassword", projectCode);
        procedureDao.spResetPassword(mapper.get("user"),mapper.get("pass"), TS, projectCode);
    	WsResponse wsResponse = new WsResponse("true",1);
    	return wsResponse;
    }

}

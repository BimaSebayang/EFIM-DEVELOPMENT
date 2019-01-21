package controller.login;

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

import com.google.gson.Gson;

import dao.ProcedureDao;
import service.headuser.TblSessionUserSvc;
import webservice.global.WsResponse;

@RestController
@RequestMapping("/LoginTesterCtl")
public class LoginTesterCtl {


	@Autowired
	private TblSessionUserSvc tblSessionUserSvc;
	
	@Autowired
	private ProcedureDao procedureDao;

	    @RequestMapping(value = "/ResultUser", method = RequestMethod.GET, params = {"projectCode"})
		public WsResponse getAllAktifList(@RequestHeader Map<String, Object> rh,
				@RequestParam String projectCode){
	    
	      String TS = new SimpleDateFormat("dd.MM.yyyy.HH.mm.ss").format(new Date());
	      procedureDao.spCreatedHistoryAccess((String)rh.get("ip"),(String)rh.get("mac") , (String)rh.get("sn"), TS, "NONE",
	    		  "http://localhost:8080/EFIM-CORE/LoginTesterCtl/ResultUser", projectCode);
	      
	  	  Map<String, Object> result = tblSessionUserSvc.getResultUser();
	      WsResponse wsResponse = new WsResponse(result.get("content"), (Integer) result.get("size"), false, null, null);
		  return wsResponse;
	    }
	    
	    @RequestMapping(value = "/CallProc",  method = RequestMethod.POST,params = {"projectCode"})
	    public WsResponse checkIfValidUser(@RequestBody Map<String, Object> inputedUser, 
	    		@RequestHeader Map<String, Object> rh,
	    		@RequestParam String projectCode) {
	    String TS = new SimpleDateFormat("dd.MM.yyyy.HH.mm.ss").format(new Date());
	    	
	    procedureDao.spCreatedHistoryAccess((String)rh.get("ip"),(String)rh.get("mac") , (String)rh.get("sn"), TS, "NONE",
		    		  "http://localhost:8080/EFIM-CORE/LoginTesterCtl/CallProc", projectCode);
	    
	    String result = procedureDao.spCheckValidUser((String)inputedUser.get("user"), 
	    			(String)inputedUser.get("pass"), projectCode, TS);
	    	WsResponse wsResponse = new WsResponse(result, 1);
	    	return wsResponse;
	    }

	
}

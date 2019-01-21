package controller.register;

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

import common.dto.headuser.TblDataUserDto;
import dao.ProcedureDao;
import webservice.global.WsResponse;

@RestController
@RequestMapping("/RegisterCtl")
public class RegisterCtl {

	@Autowired
	private ProcedureDao procedureDao;
	
	@RequestMapping(value = "/CreateUser",  method = RequestMethod.POST,params = {"projectCode"})
    public WsResponse checkIfValidUser(@RequestBody TblDataUserDto tblDataUserDto, 
    		@RequestHeader Map<String, Object> rh,
    		@RequestParam String projectCode) {
    String TS = new SimpleDateFormat("dd.MM.yyyy.HH.mm.ss").format(new Date());
    	
        procedureDao.spCreatedHistoryAccess((String)rh.get("ip"),(String)rh.get("mac") , (String)rh.get("sn"), TS, "NONE",
	    		  "http://localhost:8080/EFIM-CORE/RegisterCtl/CreateUser", projectCode);
    
        String result = procedureDao.spCreatedTemporaryUser
             		(tblDataUserDto.getUserName(), tblDataUserDto.getUserId(), 
    				tblDataUserDto.getUserPassword(), tblDataUserDto.getUserMail(), tblDataUserDto.getUserPhone(), 
    				TS, projectCode);
        
        WsResponse wsResponse = new WsResponse(result, 1) ;
    	return wsResponse;
    }
	
	@RequestMapping(value = "/CreateAndSessionUser",  method = RequestMethod.POST,params = {"projectCode"})
    public WsResponse createAndSessionUser(@RequestBody String regNo, 
    		@RequestHeader Map<String, Object> rh,
    		@RequestParam String projectCode) {
        String TS = new SimpleDateFormat("dd.MM.yyyy.HH.mm.ss").format(new Date());
    	
        procedureDao.spCreatedHistoryAccess((String)rh.get("ip"),(String)rh.get("mac") , (String)rh.get("sn"), TS, "NONE",
	    		  "http://localhost:8080/EFIM-CORE/RegisterCtl/CreateAndSessionUser", projectCode);
    
        System.err.println("reg no : " + regNo);
        String result = procedureDao.spCreatedUserAndSession(regNo, TS, projectCode);
        
        WsResponse wsResponse = new WsResponse(result, 1) ;
    	return wsResponse;
    }
	
	
}

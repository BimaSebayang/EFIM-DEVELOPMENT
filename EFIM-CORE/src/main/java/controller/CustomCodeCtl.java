package controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.zkoss.lang.Strings;

import Constant.CommonConstant;
import service.master.TblCodeSvc;
import webservice.global.WsResponse;

@RestController
@RequestMapping("/CustomCodeCtl")
public class CustomCodeCtl  extends CommonConstant{

	@Autowired
	private TblCodeSvc tblCodeSvc;
	
	@RequestMapping(value = "/Request/MstCodeType", method = RequestMethod.POST)
	public WsResponse RequestCodeUsingMstCodeType(@RequestBody String mstCodeType) {
		    WsResponse wsResponse = new WsResponse();
	        if(mstCodeType==null || Strings.isEmpty(mstCodeType)|| Strings.isBlank(mstCodeType)) {
	        	wsResponse.setErrorCmd("Request Body mstCodeType cannot be blank/null");
	        	wsResponse.setIsErrorSvc(true);
	        	return wsResponse;
	        }	
		    Map<String, Object> mapperResult = tblCodeSvc.getCurrentInformationCodeFromMstCodeType(mstCodeType);
		    try {
			if(((String)mapperResult.get("error")).equalsIgnoreCase(COMMONNOERROR)) {
				wsResponse = new WsResponse(mapperResult.get("content"), (int)mapperResult.get("count"));
				wsResponse.setIsErrorSvc(false);
			}
			else if(((String)mapperResult.get("error")).equalsIgnoreCase(COMMONNODATA)) {
				wsResponse.setIsErrorSvc(true);
				wsResponse.setErrorCmd(NOEXIST);
			}
			else {
				wsResponse.setIsErrorSvc(true);
				wsResponse.setErrorCmd((String)mapperResult.get("error"));
			}
		    }catch (Exception e) {
		    	e.printStackTrace();
		    	wsResponse.setIsErrorSvc(true);
				wsResponse.setErrorCmd(e.getMessage());
			}
		    return wsResponse;
	}
	
	@RequestMapping(value = "/Request/MstCode", method = RequestMethod.POST)
	public WsResponse RequestCodeUsingMstCode(@RequestBody String mstCode) {
		    WsResponse wsResponse = new WsResponse();
	        if(mstCode!=null || Strings.isEmpty(mstCode)|| Strings.isBlank(mstCode)) {
	        	wsResponse.setErrorCmd("Request Body mstCode cannot be blank/null");
	        	wsResponse.setIsErrorSvc(true);
	        	return wsResponse;
	        }	
		    Map<String, Object> mapperResult = tblCodeSvc.getCurrentInformationCodeFromMstCode(mstCode);
		    try {
			if(((String)mapperResult.get("error")).equalsIgnoreCase(COMMONNOERROR)) {
				wsResponse = new WsResponse(mapperResult.get("content"), (int)mapperResult.get("count"));
				wsResponse.setIsErrorSvc(false);
			}
			else if(((String)mapperResult.get("error")).equalsIgnoreCase(COMMONNODATA)) {
				wsResponse.setIsErrorSvc(true);
				wsResponse.setErrorCmd(NOEXIST);
			}
			else {
				wsResponse.setIsErrorSvc(true);
				wsResponse.setErrorCmd((String)mapperResult.get("error"));
			}
		    }catch (Exception e) {
		    	e.printStackTrace();
		    	wsResponse.setIsErrorSvc(true);
				wsResponse.setErrorCmd(e.getMessage());
			}
		    return wsResponse;
	}
	
}

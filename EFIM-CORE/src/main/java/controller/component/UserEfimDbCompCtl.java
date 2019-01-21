package controller.component;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.zkoss.lang.Strings;

import Constant.CommonConstant;
import common.dto.stream.TblEfimFileDbstorageDto;
import service.headuser.TblEfimDbSvc;
import service.stream.TblEfimFileDbstorageSvc;
import webservice.global.WsResponse;

@RestController
@RequestMapping("/UserEfimDbCompCtl")
public class UserEfimDbCompCtl extends CommonConstant{

	@Autowired
	private TblEfimDbSvc tblEfimDbSvc;
	
	@Autowired
	private TblEfimFileDbstorageSvc tblEfimFileDbstorageSvc;
	
	@RequestMapping(value = "/EfimStr", method = RequestMethod.POST)
	public ResponseEntity<byte[]> getAllEfimStreamData(@RequestBody String fileStrIdReff) {
	        if(fileStrIdReff!=null||Strings.isEmpty(fileStrIdReff)||Strings.isBlank(fileStrIdReff)) {
	        	fileStrIdReff = "******";
	        }	
	        
	        System.err.println("file " + fileStrIdReff);
	        byte[] media = tblEfimFileDbstorageSvc.getStreamFile(fileStrIdReff);
	        HttpHeaders headers = new HttpHeaders();
	        headers.setCacheControl(CacheControl.noCache().getHeaderValue());
	        ResponseEntity<byte[]> responseEntity = new ResponseEntity<>(media, headers, HttpStatus.OK);
		    return responseEntity;
		
	}
	
	@RequestMapping(value = "/FileStream", method = RequestMethod.GET, params = {"projectCode","fileStrIdRef","fileIdRef"})
	public WsResponse checkAllUserEfimPhoto(@RequestParam String projectCode,
			@RequestParam String fileStrIdRef,
			@RequestParam String fileIdRef) {
		   TblEfimFileDbstorageDto tblEfimFileDbstorageDto = tblEfimFileDbstorageSvc.getEfimFile
				   (fileStrIdRef, fileIdRef);
		   WsResponse wsResponse = new WsResponse(tblEfimFileDbstorageDto,1);
		   return wsResponse;
	}
	
	@RequestMapping(value = "/UserEfim", method = RequestMethod.POST, params = { "projectCode"})
	public WsResponse checkAllUserEfim(@RequestBody Map<String, Object> mapperRequest, 
			@RequestParam String projectCode) {
		
	        if(mapperRequest!=null) {
	        	mapperRequest.put("projectCode", projectCode);
	        }	
	        
	        WsResponse wsResponse = new WsResponse();
	        
		    Map<String, Object> mapperResult = tblEfimDbSvc.getAllDataAndFileOwner(mapperRequest);
		    try {
			if(((String)mapperResult.get("error")).equalsIgnoreCase(COMMONNOERROR)) {
				wsResponse = new WsResponse(mapperResult.get("content"), 
						(int)mapperResult.get("count"), false, null,(int) mapperResult.get("size"));
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


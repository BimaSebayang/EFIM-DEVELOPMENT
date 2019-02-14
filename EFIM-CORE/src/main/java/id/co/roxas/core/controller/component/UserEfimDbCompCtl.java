package id.co.roxas.core.controller.component;

import java.util.List;
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

import id.co.roxas.core.service.headuser.TblEfimDbSvc;
import id.co.roxas.core.service.stream.TblEfimFileDbstorageSvc;
import id.co.roxas.efim.common.common.dto.headuser.TblEfimDbDto;
import id.co.roxas.efim.common.common.dto.stream.TblEfimFileDbstorageDto;
import id.co.roxas.efim.common.constant.CommonConstant;
import id.co.roxas.efim.common.webservice.global.WsResponse;

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
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/SensitiveSearch", method = RequestMethod.POST, params = {"projectCode","fileStrIdReff","page"})
	public WsResponse searchFileDataWithSensitiveCtl(@RequestParam String projectCode,
			@RequestParam String fileStrIdReff,
			@RequestParam int page,
			@RequestBody Map<String, Object> requestBody) {
		String search = (String) requestBody.get("search");
		Map<String, Object> mapper = tblEfimDbSvc.searchFileDataWithSensitive(search, fileStrIdReff, projectCode, page);
		int total = (int) mapper.get("count");
		List<TblEfimDbDto> tblEfimDbDtos = (List<TblEfimDbDto>) mapper.get("content");
		WsResponse wsResponse = new WsResponse(tblEfimDbDtos, total, true, page, total);
		return wsResponse;
	}
	
	@RequestMapping(value = "/UserEfim", method = RequestMethod.POST, params = { "projectCode","page"})
	public WsResponse checkAllUserEfim(@RequestBody Map<String, Object> mapperRequest, 
			@RequestParam String projectCode, @RequestParam int page) {
		
	        if(mapperRequest!=null) {
	        	mapperRequest.put("projectCode", projectCode);
	        }	
	        
	        WsResponse wsResponse = new WsResponse();
	        
		    Map<String, Object> mapperResult = tblEfimDbSvc.getAllDataAndFileOwner(mapperRequest,page);
		    try {
			if(((String)mapperResult.get("error")).equalsIgnoreCase(COMMONNOERROR)) {
				wsResponse = new WsResponse(mapperResult.get("content"), 
						(int)mapperResult.get("count"), true, page,(int) mapperResult.get("size"));
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


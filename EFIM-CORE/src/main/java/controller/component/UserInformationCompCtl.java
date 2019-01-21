package controller.component;

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

import Constant.CommonConstant;
import service.headuser.TblDataUserSvc;
import service.headuser.TblEfimDbSvc;
import webservice.global.WsResponse;

/*
 * Hanya dibagian controller component yang tidak dibutuhkan procedure hak akses
 */

@RestController
@RequestMapping("/UserInformationCompCtl")
public class UserInformationCompCtl extends CommonConstant{
	@Autowired
	private TblEfimDbSvc tblEfimDbSvc;

	@RequestMapping(value = "/UserInf", method = RequestMethod.POST, params = { "projectCode" })
	public WsResponse checkAllUserInformation(@RequestBody Map<String, Object> mapperRequest, 
			@RequestParam String projectCode) {
		//String TS = new SimpleDateFormat("dd.MM.yyyy.HH.mm.ss").format(new Date());

		if (mapperRequest != null) {
			mapperRequest.put("projectCode", projectCode);
		}
		Map<String, Object> mapResult = tblEfimDbSvc.getUserInformativeMotive(mapperRequest);
		WsResponse wsResponse = new WsResponse();
		String error = (String) mapResult.get("error");
		
		try {
		if(error.equalsIgnoreCase(COMMONNOERROR)) {
		wsResponse = new WsResponse(mapResult.get("content"), (int) mapResult.get("count"), false, null,
				null);
		wsResponse.setIsErrorSvc(false);
		}
		else if(error.equalsIgnoreCase(COMMONNODATA)){
			wsResponse.setIsErrorSvc(true);
			wsResponse.setErrorCmd(NOEXIST);
		}
		else {
			wsResponse.setIsErrorSvc(true);
			wsResponse.setErrorCmd(GENERROR);
		}
		}catch(Exception exp) {
			exp.printStackTrace();
			wsResponse.setIsErrorSvc(true);
			wsResponse.setErrorCmd(exp.getMessage());
		}
		return wsResponse;
	}
}

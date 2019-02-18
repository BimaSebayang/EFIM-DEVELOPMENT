package id.co.roxas.core.controller.component.shared;

import java.util.Date;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;

import id.co.roxas.core.dao.ProcedureDao;
import id.co.roxas.efim.common.common.lib.CommonDateLibPr;
import id.co.roxas.efim.common.constant.CommonConstant;
import id.co.roxas.efim.common.webservice.global.WsResponse;
import id.co.roxas.efim.common.webservice.lib.MapperWs;

@RestController
@RequestMapping("/HapusCompCtl")
public class HapusCompCtl extends CommonConstant {

	@Autowired
	private ProcedureDao procedureDao;
	private Map<String, Object> mapper;

	@RequestMapping(value = "/Delete/{TypeDeleted}", params = {"projectCode"}, method = RequestMethod.POST)
	public WsResponse saveEfimDb(@PathVariable String TypeDeleted,
			@RequestBody Map<String, Object> bodyRequest, @RequestParam String projectCode) {

		if (bodyRequest == null) {
			WsResponse response = new WsResponse();
			response.setErrorCmd(NULLBODY);
			response.setIsErrorSvc(true);
			return response;
		}

		String createdDate = CommonDateLibPr.formattingDateToString(new Date());
		String fileIdReff = "";
		String fileType = "";
		String fileStrIdReff = "";

		try {
			fileIdReff = (String) bodyRequest.get("file_id_reff");
			fileType = (String) bodyRequest.get("file_type");
			fileStrIdReff = (String) bodyRequest.get("file_str_id_reff");
		} catch (Exception exp) {
			exp.printStackTrace();
			WsResponse response = new WsResponse();
			response.setIsErrorSvc(true);
			response.setErrorCmd(exp.toString());
			return response;
		}

		String result = "";
		mapper = null;
		try {
			if(TypeDeleted.equalsIgnoreCase("temporary")) {
			result = procedureDao.spDeleteEfimDbTemporary(fileIdReff, fileType, projectCode, fileStrIdReff,
					createdDate);
			}
			else if(TypeDeleted.equalsIgnoreCase("permanent")) {
				result = procedureDao.spDeleteEfimDbPermanent(fileIdReff, fileType, projectCode, fileStrIdReff,
						createdDate);
			}
			else {
				WsResponse response = new WsResponse();
				response.setIsErrorSvc(true);
				response.setErrorCmd("Header TypeDeleted doesn't find right way to delete");
				return response;	
			}
			mapper = new MapperWs().mapperJsonToHashMap(result);
		} catch (Exception exp) {
			exp.printStackTrace();
			WsResponse response = new WsResponse();
			response.setIsErrorSvc(true);
			response.setErrorCmd(exp.toString() + " with parameter : " + new Gson().toJson(bodyRequest));
			return response;
		}

		if (mapper != null) {
			if (mapper.get("error_method") == null) {
				if ((boolean) mapper.get("result")) {
					WsResponse response = new WsResponse();
					response.setIsErrorSvc(false);
					response.setWsContent(result);
					return response;
				} else {
					WsResponse response = new WsResponse();
					response.setErrorCmd((String) mapper.get("error"));
					response.setIsErrorSvc(true);
					return response;
				}
			} else {
				WsResponse response = new WsResponse();
				response.setErrorCmd(CANNOTRETRIEVE);
				response.setIsErrorSvc(true);
				return response;
			}
		} else {
			WsResponse response = new WsResponse();
			response.setErrorCmd((String) mapper.get("error_method"));
			response.setIsErrorSvc(true);
			return response;
		}

	}
}

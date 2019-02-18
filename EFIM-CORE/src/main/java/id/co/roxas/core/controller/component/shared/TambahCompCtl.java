package id.co.roxas.core.controller.component.shared;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;

import id.co.roxas.core.dao.ProcedureDao;
import id.co.roxas.efim.common.common.lib.dto.headuser.TblEfimDbDto;
import id.co.roxas.efim.common.common.lib.lib.CommonDateLibPr;
import id.co.roxas.efim.common.constant.CommonConstant;
import id.co.roxas.efim.common.webservice.global.WsResponse;
import id.co.roxas.efim.common.webservice.lib.MapperWs;

@RestController
@RequestMapping("/TambahCompCtl")
public class TambahCompCtl extends CommonConstant {
	@Autowired
	private ProcedureDao procedureDao;

	@RequestMapping(value = "/Save", method = RequestMethod.POST)
	public WsResponse saveEfimDb(@RequestBody TblEfimDbDto saveBody) {

		if (saveBody == null) {
			WsResponse response = new WsResponse();
			response.setErrorCmd(NULLBODY);
			response.setIsErrorSvc(true);
			return response;
		}

		String fileName = "";
		double fileSize = 0.0;
		String fileStrIdReff = "";
		String owner = "";
		String projectCode = "";
		String fileType = "";
		String actualFileName = "";
		byte[] fileStr = null;
		try {
			fileName = saveBody.getFileName();
			try {
				fileSize = (double) saveBody.getFileSize();
			} catch (ClassCastException cce) {
				int fileSizeInInteger = (int) saveBody.getFileSize().doubleValue();
				fileSize = fileSizeInInteger;
			}
			fileStrIdReff = saveBody.getFileStrIdReff();
			owner = saveBody.getFileOwner();
			projectCode = saveBody.getProjectCode();
			fileType = saveBody.getFileType();
			actualFileName = saveBody.getFileName();
			fileStr = saveBody.getTblEfimFileDbstorageDto().getFileStr();
		} catch (Exception exp) {
			exp.printStackTrace();
			WsResponse response = new WsResponse();
			response.setIsErrorSvc(true);
			response.setErrorCmd(exp.toString());
			return response;
		}
		String createdDate = CommonDateLibPr.formattingDateToString(new Date());
		System.err.println("cd : " + createdDate);
		String result = "";
		try {
			result = procedureDao.spCreateEfimDb(fileName, new Double(fileSize), fileStrIdReff, owner, projectCode,
					fileType, createdDate, actualFileName, fileStr);
		} catch (Exception exp) {
			exp.printStackTrace();
			WsResponse response = new WsResponse();
			response.setIsErrorSvc(true);
			response.setErrorCmd(exp.toString() + " with parameter : " + new Gson().toJson(saveBody));
			return response;
		}

		Map<String, Object> mapper = new MapperWs().mapperJsonToHashMap(result);
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
				response.setErrorCmd((String) mapper.get("error_method"));
				response.setIsErrorSvc(true);
				return response;
			}
		} else {
			WsResponse response = new WsResponse();
			response.setErrorCmd(CANNOTRETRIEVE);
			response.setIsErrorSvc(true);
			return response;
		}
	}
	
	@RequestMapping(value = "/Restore", method = RequestMethod.POST)
	public WsResponse restoreEfimDb(@RequestBody TblEfimDbDto saveBody) {
       // System.err.println("restore kepanggil nggak sih");
		if (saveBody == null) {
			WsResponse response = new WsResponse();
			response.setErrorCmd(NULLBODY);
			response.setIsErrorSvc(true);
			return response;
		}

		System.err.println("body : " + new Gson().toJson(saveBody));
		
		String fileIdReff = "";
		String fileStrIdReff = "";
		String projectCode = "";
		try {
			fileIdReff = saveBody.getFileIdReff();
			fileStrIdReff = saveBody.getFileStrIdReff();
			projectCode = saveBody.getProjectCode();
		} catch (Exception exp) {
			exp.printStackTrace();
			WsResponse response = new WsResponse();
			response.setIsErrorSvc(true);
			response.setErrorCmd(exp.toString());
			return response;
		}
		String createdDate = CommonDateLibPr.formattingDateToString(new Date());
		System.err.println("cd : " + createdDate);
		String result = "";
		try {
			result = procedureDao.spRestoreDataBin(fileIdReff, projectCode, fileStrIdReff, createdDate);
		} catch (Exception exp) {
			exp.printStackTrace();
			WsResponse response = new WsResponse();
			response.setIsErrorSvc(true);
			response.setErrorCmd(exp.toString() + " with parameter : " + new Gson().toJson(saveBody));
			return response;
		}

		Map<String, Object> mapper = new MapperWs().mapperJsonToHashMap(result);
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
				response.setErrorCmd((String) mapper.get("error_method"));
				response.setIsErrorSvc(true);
				return response;
			}
		} else {
			WsResponse response = new WsResponse();
			response.setErrorCmd(CANNOTRETRIEVE);
			response.setIsErrorSvc(true);
			return response;
		}
	}
}

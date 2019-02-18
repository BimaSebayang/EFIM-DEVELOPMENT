package id.co.roxas.core.controller.extention;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.zkoss.lang.Strings;

import com.google.gson.Gson;
import com.thoughtworks.xstream.core.util.Base64Encoder;

import entity.stream.TblPictureFrontEnd;
import id.co.roxas.core.dao.ProcedureDao;
import id.co.roxas.core.dao.headuser.TblPictureFrontEndDao;
import id.co.roxas.core.service.headuser.TblPictureFrontEndSvc;
import id.co.roxas.efim.common.common.lib.lib.CommonDateLibPr;
import id.co.roxas.efim.common.constant.CommonConstant;
import id.co.roxas.efim.common.webservice.global.WsResponse;
import id.co.roxas.efim.common.webservice.lib.MapperWs;

@RestController
@RequestMapping("/PictureCtl")
public class PictureCtl extends CommonConstant{

	private final Path URITEMPORARY = Paths.get("D:\\Kumpulan Projek Bima\\EFIM_DB");
	private final Path URIPICTURE = Paths.get(URITEMPORARY.toString(),"Picture");
	private Map<String, Object> mapper;
	
	@Autowired
	TblPictureFrontEndSvc tblPictureFrontEndSvc;
	
	@Autowired
	private ProcedureDao procedureDao;
	
	@RequestMapping(value = "/GetTheBackgroundPicture", method = RequestMethod.POST,params = {"projectCode"})
	public ResponseEntity<byte[]> getImageFromDatabase(@RequestBody String pictureName, 
			@RequestParam String projectCode) {
		HttpHeaders headers = new HttpHeaders();
		System.err.println("picture id " + pictureName + " project code " + projectCode);
		byte[] media = tblPictureFrontEndSvc.getTheImage(pictureName, projectCode);
		headers.setCacheControl(CacheControl.noCache().getHeaderValue());
		ResponseEntity<byte[]> responseEntity = new ResponseEntity<>(media, headers, HttpStatus.OK);
	    return responseEntity;
	}
	
	@RequestMapping(value="/SaveTitleChange", method = RequestMethod.POST, params = {"projectCode"})
	public WsResponse SaveTitleChange(@RequestBody Map<String, Object> bodyRequest, 
			@RequestParam String projectCode) {
		if (bodyRequest == null) {
			WsResponse response = new WsResponse();
			response.setErrorCmd(NULLBODY);
			response.setIsErrorSvc(true);
			return response;
		}
		String createdDate = CommonDateLibPr.formattingDateToString(new Date());
		String fileIdReff = "";
		String newFileName = "";
		String fileStrIdReff = "";
		
		try {
			fileIdReff = (String) bodyRequest.get("file_id_reff");
			newFileName = (String) bodyRequest.get("new_file_name");
			if(Strings.isEmpty(newFileName)) {
				newFileName = "Undefined Name";
			}
			fileStrIdReff = (String) bodyRequest.get("file_str_id_reff");
		}catch(Exception exp) {
			exp.printStackTrace();
			WsResponse response = new WsResponse();
			response.setIsErrorSvc(true);
			response.setErrorCmd(exp.toString());
			return response;
		}
		
		String result = "";
		mapper = null;
		try {
			result = procedureDao.spEditTitleEfimDb(fileIdReff, newFileName, projectCode, fileStrIdReff, createdDate);
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
	
	@RequestMapping(value="/SaveFlagChange", method = RequestMethod.POST, params = {"projectCode"})
	public WsResponse SaveFlagChange(@RequestBody Map<String, Object> bodyRequest, 
			@RequestParam String projectCode) {
		if (bodyRequest == null) {
			WsResponse response = new WsResponse();
			response.setErrorCmd(NULLBODY);
			response.setIsErrorSvc(true);
			return response;
		}
		String createdDate = CommonDateLibPr.formattingDateToString(new Date());
		String fileIdReff = "";
		String fileStrIdReff = "";
		
		try {
			fileIdReff = (String) bodyRequest.get("file_id_reff");
			fileStrIdReff = (String) bodyRequest.get("file_str_id_reff");
		}catch(Exception exp) {
			exp.printStackTrace();
			WsResponse response = new WsResponse();
			response.setIsErrorSvc(true);
			response.setErrorCmd(exp.toString());
			return response;
		}
		
		String result = "";
		mapper = null;
		try {
			result = procedureDao.spChangeFlagEfimDb(fileIdReff, projectCode, fileStrIdReff, createdDate);
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
	
	@RequestMapping(value = "/GetSpecificImage", method = RequestMethod.POST)
	public ResponseEntity<byte[]> getImageAsResponseEntity(
			@RequestBody Map<String,Object> mapper) {
		HttpHeaders headers = new HttpHeaders();
		
		String uriTambahan = (String) mapper.get("uri");
		System.err.println("uri yang dipanggil : " + URIPICTURE.toString()+uriTambahan);
		if(uriTambahan == null){	
			uriTambahan = "";
		}
        if((String) mapper.get("title")!=null){
		Path getTheFile = Paths.get(URIPICTURE.toString()+uriTambahan, (String) mapper.get("title"));
		File file = new File(getTheFile.toString());
		System.err.println(file);
        InputStream inputStream = null;
        byte[] media = null;
		try {
			inputStream = new FileInputStream(file);
			media = IOUtils.toByteArray(inputStream);
		} catch (Exception e) {
			e.printStackTrace();
		}
		headers.setCacheControl(CacheControl.noCache().getHeaderValue());
		ResponseEntity<byte[]> responseEntity = new ResponseEntity<>(media, headers, HttpStatus.OK);
	    return responseEntity;
	    }
        return new ResponseEntity<byte[]>(null, null, HttpStatus.OK);
	}

	@RequestMapping(value = "/SaveImage", method = RequestMethod.POST)
	public String saveImageEntity(@RequestBody Map<String,Object> mapper){
        String uriTambahan = (String) mapper.get("uri");
		
		if(uriTambahan == null){	
			uriTambahan = "";
		}
		Path getTheFile = Paths.get(URIPICTURE.toString()+uriTambahan, (String) mapper.get("title"));
		File newFile = new File(getTheFile.toString());
		Base64Encoder decoder = new Base64Encoder();
		BufferedImage image = null;
	        byte[] imageByte = null;
	        try {
	            imageByte = decoder.decode((String) mapper.get("encoder"));
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
		
		try {
			FileOutputStream fos = new FileOutputStream(newFile);
			fos.write(imageByte);
			fos.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "berhasil";
	}
	
	@RequestMapping(value = "/DeleteImage", method = RequestMethod.POST)
	public String deletePhotoEntity(@RequestBody Map<String,Object> mapper){
        String uriTambahan = (String) mapper.get("uri");
		
		if(uriTambahan == null){	
			uriTambahan = "";
		}
		Path getTheFile = Paths.get(URIPICTURE.toString()+uriTambahan, (String) mapper.get("title"));
		try{
			Files.delete(getTheFile);
			System.err.println("file deleted");
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return "berhasil";
	}
	
	@RequestMapping(value = "/ReplaceImage", method = RequestMethod.POST)
	public String replacePhotoEntity(@RequestBody Map<String,Object> mapper){
        String uriTambahan = (String) mapper.get("uri");
		if(uriTambahan == null){	
			uriTambahan = "";
		}
		if( (String) mapper.get("oldTitle") !=null){
		Path getTheOldFile = Paths.get(URIPICTURE.toString()+uriTambahan, (String) mapper.get("oldTitle"));
		try{
			Files.delete(getTheOldFile);
			System.err.println("file deleted");
		}catch(Exception e){
			e.printStackTrace();
		}
		}
		Path getTheNewFile = Paths.get(URIPICTURE.toString()+uriTambahan, (String) mapper.get("newTitle"));
		File newFile = new File(getTheNewFile.toString());
		Base64Encoder decoder = new Base64Encoder();
		BufferedImage pdf = null;
	        byte[] pdfByte = null;
	        try {
	            pdfByte = decoder.decode((String) mapper.get("encoder"));
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
		
		try {
			FileOutputStream fos = new FileOutputStream(newFile);
			fos.write(pdfByte);
			fos.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "berhasil";
	}

}

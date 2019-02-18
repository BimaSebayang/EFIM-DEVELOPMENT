package id.co.roxas.core.service.headuser.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.zkoss.lang.Strings;

import entity.headuser.TblDataUser;
import entity.headuser.TblEfimDb;
import id.co.roxas.core.dao.SearchProcedureDao;
import id.co.roxas.core.dao.headuser.TblDataUserDao;
import id.co.roxas.core.dao.headuser.TblEfimDbDao;
import id.co.roxas.core.dao.stream.TblUserPictureProfileDao;
import id.co.roxas.core.service.headuser.TblEfimDbSvc;
import id.co.roxas.efim.common.common.dto.UserPrivilegeCustom;
import id.co.roxas.efim.common.common.dto.headuser.TblEfimDbDto;
import id.co.roxas.efim.common.common.lib.CommonDateLibPr;
import id.co.roxas.efim.common.constant.CommonConstant;
import id.co.roxas.efim.common.paging.request.RequestPaging;
import id.co.roxas.efim.common.tester.AlgoritmaLevenshtein;
import id.co.roxas.efim.common.webservice.lib.RestTemplateLib;
import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.impl.DefaultMapperFactory;

@Service("tblEfimDbSvc")
@Transactional
public class TblEfimDbSvcImpl extends CommonConstant implements TblEfimDbSvc{

	@Autowired
	private TblDataUserDao tblDataUserDao;
	
	@Autowired
	private TblUserPictureProfileDao tblUserPictureProfileDao;
	
	@Autowired
	private TblEfimDbDao tblEfimDbDao;
	
	@Autowired
	private SearchProcedureDao searchProcedureDao;
	
	private MapperFacade mapperFacade = new DefaultMapperFactory.Builder().build().getMapperFacade();
	
	@Override
	public Map<String, Object> getUserInformativeMotive(Map<String, Object> mapResult) {
		String userId = (String) mapResult.get("userId");
		String projectCode = (String) mapResult.get("projectCode");
		UserPrivilegeCustom userPrivilegeCustom = new UserPrivilegeCustom();
		TblDataUser tblDataUser = tblDataUserDao.getUserInformation(userId, projectCode);
		
		Map<String, Object> mapper = new HashMap<>();
		
		if(tblDataUser!=null) {
		userPrivilegeCustom.setUserId(tblDataUser.getUserId());
		userPrivilegeCustom.setUserName(tblDataUser.getUserName());
		userPrivilegeCustom.setUserSessionCode(tblDataUser.getUserSessionCode());
		byte[] pic = tblUserPictureProfileDao.getUserPicture(tblDataUser.getUserPhoto(), projectCode);
		userPrivilegeCustom.setUserPhoto(pic);
		mapper.put("content", userPrivilegeCustom);
		mapper.put("count", 1);
		mapper.put("error", COMMONNOERROR);
		}
		else if(tblDataUser ==null) {
			mapper.put("content", userPrivilegeCustom);
			mapper.put("count", 0);
			mapper.put("error", COMMONNODATA);
		}
		return mapper;
	}

	@Override
	public Map<String, Object> getAllDataAndFileOwner(Map<String, Object> mapResult,int page) {

		Map<String, Object> mapper = new HashMap<>();
		List<String> listReff = (List<String>) mapResult.get("idReffs");
		
		if(listReff==null) {
			listReff = new ArrayList<>();
			listReff.add("");
		}
		
		try {
		String fileOwner = (String) mapResult.get("userId");
		String projectCode = (String) mapResult.get("projectCode");
		String fileType = (String) mapResult.get("fileType");
		RequestPaging paging = new RequestPaging();
		Page<TblEfimDb> pager= tblEfimDbDao.getAllDataAndFileOwner(fileOwner, fileType,projectCode, paging.createRequestPage(page, "DESC", "createdDate"));
		List<TblEfimDb> pageTed = pager.getContent();
		if(pageTed.size()!=0) {
			List<TblEfimDbDto> tblEfimDbDtos = new ArrayList<>();
			for (TblEfimDb tblEfimDb : pageTed) {
				TblEfimDbDto efimDbDto = new TblEfimDbDto();
				efimDbDto = mapperFacade.map(tblEfimDb, TblEfimDbDto.class);
				tblEfimDbDtos.add(efimDbDto);
			}
			int totalCon = (int) pageTed.size();
			int totSize = pageTed.size();
			mapper.put("content", tblEfimDbDtos);
			mapper.put("count", totalCon);
			mapper.put("error", COMMONNOERROR);
			mapper.put("size", totSize);
			
		}
		else {
			mapper.put("content", null);
			mapper.put("count", 0);
			mapper.put("error", COMMONNODATA);
		}
		}
		catch(Exception exp) {
			exp.printStackTrace();
			mapper.put("content", null);
			mapper.put("count", 0);
			mapper.put("error", exp.getMessage());
		}
		
		return mapper;
	}

	@Override
	public Map<String, Object> searchFileDataWithSensitive(String search, String fileStrIdReff,String projectCode,String caseSensitive, String fileType,int page) {
		String createdDate = CommonDateLibPr.formattingDateToString(new Date());
		List<TblEfimDb> tblEfimDbs = new ArrayList<>();
		System.err.println("page : " + page + ", fileType : " + fileType + ", caseSensitive : "+ caseSensitive + ", projectCode : " +
		projectCode + ", fileStrIdReff : " + fileStrIdReff + ", search : " + search);
		
	
		
		try {
			
			String result = searchProcedureDao.getAllSearch(fileStrIdReff, search, projectCode, createdDate, caseSensitive, fileType, page); 
			tblEfimDbs = new RestTemplateLib().mapperJsonToListDto
					(result,
					TblEfimDb.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		List<TblEfimDbDto> tblEfimDbDtos = new ArrayList<>();
		
	    if(caseSensitive.equalsIgnoreCase("false")) {
			List<String> strings = new ArrayList<>();
	    	for (TblEfimDb tblEfimDb : tblEfimDbs) {
				strings.add(tblEfimDb.getFileName());
			}
	    	List<String> stringNews = new ArrayList<>();
			stringNews = AlgoritmaLevenshtein.getAllClosestWordWithLevenshtein(strings, search);	
			for (String sn : stringNews) {
			for (TblEfimDb tblEfimDb : tblEfimDbs) {
					if(tblEfimDb.getFileName().equalsIgnoreCase(sn)) {
						TblEfimDbDto tblEfimDbDto = new TblEfimDbDto();
						tblEfimDbDto = mapperFacade.map(tblEfimDb, TblEfimDbDto.class);
						tblEfimDbDtos.add(tblEfimDbDto);
					}
				}	
			}	
		}
	    else {
	    	tblEfimDbDtos = mapperFacade.mapAsList(tblEfimDbs, TblEfimDbDto.class);
	    }
	
	    for (TblEfimDbDto tblEfimDbDto : tblEfimDbDtos) {
			System.err.println("file name : " + tblEfimDbDto.getFileName());
		}
	    
		Map<String, Object> mapper = new HashMap<>();
		mapper.put("count",tblEfimDbDtos.size());
		mapper.put("content", tblEfimDbDtos);	
		return mapper;
	}

}

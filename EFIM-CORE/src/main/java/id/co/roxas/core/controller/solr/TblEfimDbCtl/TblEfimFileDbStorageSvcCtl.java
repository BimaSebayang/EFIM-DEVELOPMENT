package id.co.roxas.core.controller.solr.TblEfimDbCtl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import entity.headuser.TblEfimDb;
import entity.stream.TblEfimFileDbstorage;
import id.co.roxas.core.dao.headuser.TblEfimDbDao;
import id.co.roxas.core.dao.stream.TblEfimFileDbStorageDao;
import id.co.roxas.efim.common.common.lib.dto.stream.TblEfimFileDbstorageDto;
import id.co.roxas.efim.common.constant.CommonConstant;
import id.co.roxas.efim.common.webservice.global.WsResponse;

@RestController
@RequestMapping("/TblEfimFileDbStorageSvcCtl")
public class TblEfimFileDbStorageSvcCtl extends CommonConstant{
	@Autowired
	private TblEfimFileDbStorageDao tblEfimFileDbStorageDao;
	
	@RequestMapping(value = "/Solr/Request", method = RequestMethod.GET)
	public WsResponse RequestCodeUsingMstCodeType() {
	
		List<TblEfimFileDbstorage> tblEfimFileDbstorages = tblEfimFileDbStorageDao.findAll();
		try {
		if(tblEfimFileDbstorages.size()>0) {
	    WsResponse wsResponse = new WsResponse(tblEfimFileDbstorages, tblEfimFileDbstorages.size());
	    wsResponse.setIsErrorSvc(false);
	    return wsResponse;
		}else {
			 WsResponse wsResponse = new WsResponse();
			 wsResponse.setIsErrorSvc(true);
		     wsResponse.setErrorCmd(NOEXIST);
		     return wsResponse;
		}
		}catch(Exception exp) {
			 WsResponse wsResponse = new WsResponse();
			 wsResponse.setIsErrorSvc(true);
		     wsResponse.setErrorCmd(exp.getMessage());
		     return wsResponse;
		}
	}
}

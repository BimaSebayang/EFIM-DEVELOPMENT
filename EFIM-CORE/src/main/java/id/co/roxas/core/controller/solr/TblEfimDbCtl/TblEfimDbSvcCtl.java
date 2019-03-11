package id.co.roxas.core.controller.solr.TblEfimDbCtl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import Share.WsResponse;
import entity.headuser.TblEfimDb;
import id.co.roxas.core.dao.headuser.TblDataUserDao;
import id.co.roxas.core.dao.headuser.TblEfimDbDao;
import id.co.roxas.core.service.master.TblCodeSvc;
import id.co.roxas.efim.common.constant.CommonConstant;

@RestController
@RequestMapping("/TblEfimDbSvcCtl")
public class TblEfimDbSvcCtl  extends CommonConstant{
	@Autowired
	private TblEfimDbDao tblEfimDbDao;
	
	@RequestMapping(value = "/Solr/Request", method = RequestMethod.POST, params = { "projectCode" })
	public WsResponse RequestCodeUsingMstCodeType(
			@RequestBody List<String> listIdReffs,
			@RequestParam String projectCode) {
		if(listIdReffs==null||listIdReffs.size()==0) {
			listIdReffs = new ArrayList<>();
			listIdReffs.add("");
		}
		
		List<TblEfimDb> tblEfimDbs = tblEfimDbDao.getAllDataBySolr(listIdReffs, projectCode);
	
		try {
		if(tblEfimDbs.size()>0) {
	    WsResponse wsResponse = new WsResponse(tblEfimDbs, tblEfimDbs.size());
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

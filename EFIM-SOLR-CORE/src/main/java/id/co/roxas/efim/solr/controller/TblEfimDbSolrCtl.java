package id.co.roxas.efim.solr.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;

import id.co.roxas.efim.common.common.dto.headuser.TblEfimDbDto;
import id.co.roxas.efim.common.common.dto.stream.TblEfimFileDbstorageDto;
import id.co.roxas.efim.common.webservice.global.WsResponse;
import id.co.roxas.efim.common.webservice.lib.RestTemplateLib;
import id.co.roxas.efim.solr.common.lib.BaseController;
import id.co.roxas.efim.solr.model.TblEfimDb;
import id.co.roxas.efim.solr.model.TblEfimFileDbstorage;
import id.co.roxas.efim.solr.repository.TblEfimDbRepo;
import id.co.roxas.efim.solr.repository.TblEfimFileDbStorageRepo;

@RestController
public class TblEfimDbSolrCtl extends BaseController {

	@Autowired
	private TblEfimDbRepo tblEfimDbSvcRepo;

	@Autowired
	private TblEfimFileDbStorageRepo tblEfimFileDbStorageRepo;
	
	
	
	@PostConstruct
	public void addTblEfimDbController() {
		List<String> strId = new ArrayList<>();
		WsResponse response = restTemplateLib.getResultWs("/TblEfimDbSvcCtl/Solr/Request", strId, "post",
				"projectCode=" + PROJECT);

		boolean isServiceError = response.getIsErrorSvc();
		if (!isServiceError) {
			List<TblEfimDbDto> tblEfimDbDtos = new ArrayList<>();
			try {
				tblEfimDbDtos = restTemplateLib.mapperJsonToListDto(response.getWsContent(), TblEfimDbDto.class);
				 List<TblEfimDb> tblEfimDbs = new ArrayList<>();
				for (TblEfimDbDto dto : tblEfimDbDtos) {
                  tblEfimDbs.add(new TblEfimDb
                		  (dto.getFileIdReff(),dto.getFileIdReff(), dto.getFileName(), dto.getFileSize(), 
                				  dto.getFileStrIdReff(), dto.getFileOwner(), 
                				  dto.getHiddenFileCode(), dto.getCreatedDate(), 
                				  dto.getCreatedBy(), dto.getUpdatedBy(), 
                				  dto.getUpdatedBy(), dto.getFileStatus(), dto.getProjectCode(), 
                				  dto.getFileType()));
				}
				tblEfimDbSvcRepo.saveAll(tblEfimDbs);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			System.err.println("data tidak ada");
			System.err.println("data adalah " + new Gson().toJson(response));
		}
	}
}

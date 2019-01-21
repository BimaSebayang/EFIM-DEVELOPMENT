package id.co.roxas.core.service.stream.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import entity.stream.TblEfimFileDbstorage;
import id.co.roxas.core.dao.stream.TblEfimFileDbStorageDao;
import id.co.roxas.core.service.headuser.TblPictureFrontEndSvc;
import id.co.roxas.core.service.stream.TblEfimFileDbstorageSvc;
import id.co.roxas.efim.common.common.dto.stream.TblEfimFileDbstorageDto;
import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.impl.DefaultMapperFactory;

@Service("tblEfimFileDbstorageSvc")
@Transactional
public class TblEfimFileDbstorageSvcImpl implements TblEfimFileDbstorageSvc{

	@Autowired
	private TblEfimFileDbStorageDao tblEfimFileDbStorageDao;
	
	private MapperFacade mapperFacade = new DefaultMapperFactory.Builder().build().getMapperFacade();
	
	@Override
	public byte[] getStreamFile(String fileStrIdReff) {
		byte[] strFile = tblEfimFileDbStorageDao.getFileStream(fileStrIdReff);
		return strFile;
	}

	@Override
	public TblEfimFileDbstorageDto getEfimFile(String fileStrIdRef, String fileIdRef) {
		TblEfimFileDbstorage tblEfimFileDbStorage = 
				tblEfimFileDbStorageDao.getFileStreamInCurrentReff(fileStrIdRef, fileIdRef);
		TblEfimFileDbstorageDto tblEfimFileDbstorageDto = mapperFacade.map(tblEfimFileDbStorage, TblEfimFileDbstorageDto.class);
		return tblEfimFileDbstorageDto;
	}

}

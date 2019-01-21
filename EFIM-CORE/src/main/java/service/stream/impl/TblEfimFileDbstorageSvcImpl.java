package service.stream.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import common.dto.stream.TblEfimFileDbstorageDto;
import dao.stream.TblEfimFileDbStorageDao;
import entity.stream.TblEfimFileDbstorage;
import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import service.headuser.TblPictureFrontEndSvc;
import service.stream.TblEfimFileDbstorageSvc;

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

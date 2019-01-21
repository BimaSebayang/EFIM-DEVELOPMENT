package service.stream;

import java.util.List;

import org.springframework.data.repository.query.Param;

import common.dto.stream.TblEfimFileDbstorageDto;
import entity.stream.TblEfimFileDbstorage;

public interface TblEfimFileDbstorageSvc {
	 public byte[] getStreamFile(String fileStrIdReff);
	 
	 public TblEfimFileDbstorageDto getEfimFile(String fileStrIdRef, String fileIdRef);
}

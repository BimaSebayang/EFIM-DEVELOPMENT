package id.co.roxas.core.service.stream;

import java.util.List;

import org.springframework.data.repository.query.Param;

import Share.Dto.HeadUser.TblEfimDbDto;
import Share.Dto.Stream.TblEfimFileDbstorageDto;
import entity.stream.TblEfimFileDbstorage;

public interface TblEfimFileDbstorageSvc {
	 public byte[] getStreamFile(String fileStrIdReff);
	 
	 public TblEfimFileDbstorageDto getEfimFile(String fileStrIdRef, String fileIdRef);
	 
}

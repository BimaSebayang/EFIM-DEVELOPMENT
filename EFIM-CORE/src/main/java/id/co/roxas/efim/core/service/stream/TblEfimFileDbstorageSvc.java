package id.co.roxas.efim.core.service.stream;

import java.util.List;

import org.springframework.data.repository.query.Param;

import id.co.roxas.efim.common.common.dto.stream.TblEfimFileDbstorageDto;
import id.co.roxas.efim.core.entity.stream.TblEfimFileDbstorage;

public interface TblEfimFileDbstorageSvc {
	 public byte[] getStreamFile(String fileStrIdReff);
	 
	 public TblEfimFileDbstorageDto getEfimFile(String fileStrIdRef, String fileIdRef);
}

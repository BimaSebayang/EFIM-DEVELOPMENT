package id.co.roxas.core.service.stream;

import java.util.List;

import org.springframework.data.repository.query.Param;

import id.co.roxas.core.entity.stream.TblEfimFileDbstorage;
import id.co.roxas.efim.common.common.dto.stream.TblEfimFileDbstorageDto;

public interface TblEfimFileDbstorageSvc {
	 public byte[] getStreamFile(String fileStrIdReff);
	 
	 public TblEfimFileDbstorageDto getEfimFile(String fileStrIdRef, String fileIdRef);
}

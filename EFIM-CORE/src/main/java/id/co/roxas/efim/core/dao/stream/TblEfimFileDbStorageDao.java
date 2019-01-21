package id.co.roxas.efim.core.dao.stream;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import id.co.roxas.efim.core.entity.master.TblHistoryData;
import id.co.roxas.efim.core.entity.master.pk.TblHistoryDataPk;
import id.co.roxas.efim.core.entity.stream.TblEfimFileDbstorage;
import id.co.roxas.efim.core.entity.stream.TblUserPictureProfile;
import id.co.roxas.efim.core.entity.stream.pk.TblEfimFileDbstoragePk;

public interface TblEfimFileDbStorageDao extends JpaRepository<TblEfimFileDbstorage,TblEfimFileDbstoragePk>{
	     
	    @Query(" select a.fileStr from TblEfimFileDbstorage a where a.fileStrIdReff =:fileStrIdReff")
	    public byte[] getFileStream(@Param("fileStrIdReff") String fileStrIdReff);
	    
	    @Query(" select a from TblEfimFileDbstorage a where a.fileStrIdReff =:fileStrIdReff "
	    		+ " and a.fileIdRef =:fileIdRef")
	    public TblEfimFileDbstorage getFileStreamInCurrentReff(@Param("fileStrIdReff") String fileStrIdReff,
	    		@Param("fileIdRef") String fileIdRef);
}

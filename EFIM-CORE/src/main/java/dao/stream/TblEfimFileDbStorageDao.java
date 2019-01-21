package dao.stream;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import entity.master.TblHistoryData;
import entity.master.pk.TblHistoryDataPk;
import entity.stream.TblEfimFileDbstorage;
import entity.stream.TblUserPictureProfile;
import entity.stream.pk.TblEfimFileDbstoragePk;

public interface TblEfimFileDbStorageDao extends JpaRepository<TblEfimFileDbstorage,TblEfimFileDbstoragePk>{
	     
	    @Query(" select a.fileStr from TblEfimFileDbstorage a where a.fileStrIdReff =:fileStrIdReff")
	    public byte[] getFileStream(@Param("fileStrIdReff") String fileStrIdReff);
	    
	    @Query(" select a from TblEfimFileDbstorage a where a.fileStrIdReff =:fileStrIdReff "
	    		+ " and a.fileIdRef =:fileIdRef")
	    public TblEfimFileDbstorage getFileStreamInCurrentReff(@Param("fileStrIdReff") String fileStrIdReff,
	    		@Param("fileIdRef") String fileIdRef);
}

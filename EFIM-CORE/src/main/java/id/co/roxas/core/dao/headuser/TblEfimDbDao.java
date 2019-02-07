package id.co.roxas.core.dao.headuser;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import entity.headuser.TblDataUser;
import entity.headuser.TblEfimDb;
import entity.headuser.pk.TblDataUserPk;
import entity.headuser.pk.TblEfimDbPk;
import entity.stream.TblPictureFrontEnd;

public interface TblEfimDbDao extends JpaRepository<TblEfimDb, TblEfimDbPk>{

	@Query("select a from TblEfimDb a where a.fileOwner = :fileOwner and a.projectCode = :projectCode and a.fileType = :fileType")
	public Page<TblEfimDb> getAllDataAndFileOwner
	(@Param("fileOwner") String fileOwner, @Param("fileType") String fileType,@Param("projectCode") String projectCode, Pageable pageable );
	
	//Semua untuk keperluan Solr
	
	@Query("select a from TblEfimDb a where a.fileIdReff not in :listIdReff and a.projectCode = :projectCode ")
	public List<TblEfimDb> getAllDataBySolr
	(@Param("listIdReff") List<String> listIdReff,@Param("projectCode") String projectCode );
}

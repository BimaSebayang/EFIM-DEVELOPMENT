package id.co.roxas.core.dao.headuser;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import id.co.roxas.core.entity.headuser.TblDataUser;
import id.co.roxas.core.entity.headuser.TblEfimDb;
import id.co.roxas.core.entity.headuser.pk.TblDataUserPk;
import id.co.roxas.core.entity.headuser.pk.TblEfimDbPk;
import id.co.roxas.core.entity.stream.TblPictureFrontEnd;

public interface TblEfimDbDao extends JpaRepository<TblEfimDb, TblEfimDbPk>{

	@Query("select a from TblEfimDb a where a.fileOwner = :fileOwner and a.projectCode = :projectCode "
			+ " and a.fileIdReff not in :listIdReff")
	public List<TblEfimDb> getAllDataAndFileOwner
	(@Param("fileOwner") String fileOwner, @Param("projectCode") String projectCode,
			@Param("listIdReff") List<String> listIdReff );
	
}

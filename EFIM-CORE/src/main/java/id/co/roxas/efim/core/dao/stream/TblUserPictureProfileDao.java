package id.co.roxas.efim.core.dao.stream;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import id.co.roxas.efim.core.entity.headuser.TblDataUser;
import id.co.roxas.efim.core.entity.stream.TblEfimFileDbstorage;
import id.co.roxas.efim.core.entity.stream.TblUserPictureProfile;
import id.co.roxas.efim.core.entity.stream.pk.TblEfimFileDbstoragePk;
import id.co.roxas.efim.core.entity.stream.pk.TblUserPictureProfilePk;

public interface TblUserPictureProfileDao extends JpaRepository<TblUserPictureProfile,TblUserPictureProfilePk>{
	    
	     @Query(" select a.picProfilePath from TblUserPictureProfile a where a.picProfileNo =:picProfileNo and a.projectCode=:projectCode")
	    public byte[] getUserPicture(@Param("picProfileNo") String picProfileNo,
	    		@Param("projectCode") String projectCode);
}

package dao.headuser;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;

import entity.headuser.TblDataUser;
import entity.headuser.TblSessionUser;
import entity.headuser.pk.TblDataUserPk;
import entity.headuser.pk.TblSessionUserPk;

public interface TblSessionUserDao extends JpaRepository<TblSessionUser, TblSessionUserPk>{
	
}

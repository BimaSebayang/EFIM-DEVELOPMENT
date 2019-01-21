package id.co.roxas.core.dao.headuser;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;

import id.co.roxas.core.entity.headuser.TblDataUser;
import id.co.roxas.core.entity.headuser.TblSessionUser;
import id.co.roxas.core.entity.headuser.pk.TblDataUserPk;
import id.co.roxas.core.entity.headuser.pk.TblSessionUserPk;

public interface TblSessionUserDao extends JpaRepository<TblSessionUser, TblSessionUserPk>{
	
}

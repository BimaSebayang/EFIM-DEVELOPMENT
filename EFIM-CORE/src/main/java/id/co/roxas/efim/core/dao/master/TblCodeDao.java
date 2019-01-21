package id.co.roxas.efim.core.dao.master;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import id.co.roxas.efim.core.entity.headuser.TblDataUser;
import id.co.roxas.efim.core.entity.headuser.pk.TblDataUserPk;
import id.co.roxas.efim.core.entity.master.TblCode;
import id.co.roxas.efim.core.entity.master.pk.TblCodePk;

public interface TblCodeDao extends JpaRepository<TblCode, TblCodePk> {
	@Query("select a from TblCode a where a.mstCodeType = :mstCodeType")
	public TblCode getAllCodeInfoByMstCodeType(@Param("mstCodeType") String mstCodeType);

	@Query("select a from TblCode a where a.mstCode = :mstCode")
	public TblCode getAllCodeInfoByMstCode(@Param("mstCode") String mstCode);
}

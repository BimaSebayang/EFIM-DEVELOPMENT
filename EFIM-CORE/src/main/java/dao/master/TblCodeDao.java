package dao.master;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import entity.headuser.TblDataUser;
import entity.headuser.pk.TblDataUserPk;
import entity.master.TblCode;
import entity.master.pk.TblCodePk;

public interface TblCodeDao extends JpaRepository<TblCode, TblCodePk> {
	@Query("select a from TblCode a where a.mstCodeType = :mstCodeType")
	public TblCode getAllCodeInfoByMstCodeType(@Param("mstCodeType") String mstCodeType);

	@Query("select a from TblCode a where a.mstCode = :mstCode")
	public TblCode getAllCodeInfoByMstCode(@Param("mstCode") String mstCode);
}

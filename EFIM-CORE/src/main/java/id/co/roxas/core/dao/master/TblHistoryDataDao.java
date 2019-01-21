package id.co.roxas.core.dao.master;

import java.io.Serializable;
import java.util.Date;

import org.springframework.data.jpa.repository.JpaRepository;

import id.co.roxas.core.entity.headuser.TblDataUser;
import id.co.roxas.core.entity.headuser.pk.TblDataUserPk;
import id.co.roxas.core.entity.master.TblHistoryData;
import id.co.roxas.core.entity.master.pk.TblHistoryDataPk;

public interface TblHistoryDataDao extends JpaRepository<TblHistoryData, TblHistoryDataPk>{

	
}

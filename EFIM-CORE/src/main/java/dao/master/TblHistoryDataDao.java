package dao.master;

import java.io.Serializable;
import java.util.Date;

import org.springframework.data.jpa.repository.JpaRepository;

import entity.headuser.TblDataUser;
import entity.headuser.pk.TblDataUserPk;
import entity.master.TblHistoryData;
import entity.master.pk.TblHistoryDataPk;

public interface TblHistoryDataDao extends JpaRepository<TblHistoryData, TblHistoryDataPk>{

	
}

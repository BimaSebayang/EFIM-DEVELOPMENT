package id.co.roxas.core.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;

import entity.headuser.TblEfimDb;
import entity.procacclaim.ProcInformation;
import entity.procacclaim.pk.ProcInformationPk;

public interface SearchProcedureDao extends JpaRepository<ProcInformation, ProcInformationPk>{
    
	@Procedure(procedureName="MASTER.SP_SEARCH_DATA_IN_SENSITIVE")
	public String getAllSearch(String fileStrIdReff, String search, String projectCode, String createdDate, String caseSensitive, int page);
}

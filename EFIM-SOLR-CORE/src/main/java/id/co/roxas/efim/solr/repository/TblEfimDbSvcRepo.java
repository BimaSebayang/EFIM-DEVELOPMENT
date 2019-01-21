package id.co.roxas.efim.solr.repository;

import java.util.List;

import org.springframework.data.solr.repository.Query;
import org.springframework.data.solr.repository.SolrCrudRepository;

import id.co.roxas.efim.solr.model.Employee;
import id.co.roxas.efim.solr.model.TblEfimDbSvc;

public interface TblEfimDbSvcRepo extends SolrCrudRepository
<TblEfimDbSvc, Integer>{

	TblEfimDbSvc findByFileName(String fileName);
	
	@Query("id:*?0* OR fileName:*?0*")
	List<TblEfimDbSvc> findByCustomQuery(String searchTerm);
	
	@Query("*:*")
	List<TblEfimDbSvc> findALl();
	
	@Query("id:*?0* AND fileName:*?1*")
	List<TblEfimDbSvc> findCustom1(String searchTerm0, String searchTerm1);
	
	
}

package id.co.roxas.efim.solr.repository;

import java.util.List;

import org.springframework.data.solr.repository.Query;
import org.springframework.data.solr.repository.SolrCrudRepository;

import id.co.roxas.efim.solr.model.Employee;
import id.co.roxas.efim.solr.model.TblEfimDb;

public interface TblEfimDbRepo extends SolrCrudRepository
<TblEfimDb, Integer>{

	TblEfimDb findByFileName(String fileName);
	
	@Query("id:*?0* OR fileName:*?0*")
	List<TblEfimDb> findByCustomQuery(String searchTerm);
	
	@Query("*:*")
	List<TblEfimDb> findALl();
	
	@Query("id:*?0* AND fileName:*?1*")
	List<TblEfimDb> findCustom1(String searchTerm0, String searchTerm1);
	
	
}

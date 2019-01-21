package id.co.roxas.efim.solr.repository;

import org.springframework.data.solr.repository.SolrCrudRepository;

import id.co.roxas.efim.solr.model.Employee;

public interface EmployeeRepository extends SolrCrudRepository
<Employee, Integer>{

	Employee findByName(String name);
	
}

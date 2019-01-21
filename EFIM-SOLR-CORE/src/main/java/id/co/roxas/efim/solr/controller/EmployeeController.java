package id.co.roxas.efim.solr.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.solr.core.query.SimpleQuery;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import id.co.roxas.efim.solr.model.Employee;
import id.co.roxas.efim.solr.model.TblEfimDbSvc;
import id.co.roxas.efim.solr.repository.EmployeeRepository;
import id.co.roxas.efim.solr.repository.TblEfimDbSvcRepo;

@RestController
public class EmployeeController {

	@Autowired
	private EmployeeRepository repository;
	
	@Autowired
	private TblEfimDbSvcRepo tblEfimDbSvcRepo;

	@PostConstruct
    public void addEmployee() {
//    	List<Employee> employees = new ArrayList<>();
//    	employees.add(new Employee(373544,"Silver Awesome",new String[] {"Royal Palace I"}));
//    	employees.add(new Employee(374343,"Rogwe Lopa II",new String[] {"Royal Palace II"}));
//    	employees.add(new Employee(379424,"Sebayang Kari Polandia",new String[] {"Royal Palace III"}));
//    	repository.saveAll(employees);
		
		List<TblEfimDbSvc> tblEfimDbSvcs = new ArrayList<>();
		
		tblEfimDbSvcs.add(new TblEfimDbSvc(930,"T1", "Virus", 0.1));
		tblEfimDbSvcs.add(new TblEfimDbSvc(931,"T2", "Viros", 0.1));
		tblEfimDbSvcs.add(new TblEfimDbSvc(933,"T3", "Virous", 0.1));
		
        System.err.println("banyak svc " + tblEfimDbSvcs.size());
        tblEfimDbSvcRepo.saveAll(tblEfimDbSvcs);
        
    }
	
	@GetMapping("/getAll")
	public String getAllData(){
	List<TblEfimDbSvc> dbSvcs = tblEfimDbSvcRepo.findALl();
	String fi = "";    
	for (TblEfimDbSvc emp : dbSvcs) {
		   fi += emp.getFileName()+" : " + emp.getFileIdReff();
	    }
	return fi;
	}
	
	@GetMapping("/getAll/{fileName}")
	public String getEmployeeByName(@PathVariable String fileName) {
		TblEfimDbSvc dbSvc = tblEfimDbSvcRepo.findByFileName(fileName);
	  
		return dbSvc.getFileName()+" : " + dbSvc.getFileIdReff() ;
	}
	
	@GetMapping("/getAllCustom/{customSearch1}/{customSearch2}")
	public String getCustom(@PathVariable String customSearch1,@PathVariable String customSearch2) {
		List<TblEfimDbSvc> dbSvc = tblEfimDbSvcRepo.findCustom1(customSearch1,customSearch2);
		String searh = "";
		for (TblEfimDbSvc tblEfimDbSvc : dbSvc) {
			searh += tblEfimDbSvc.getFileName()+" " ;
		}
		return searh ;
	}
	
}

package id.co.roxas.efim.solr.repository;

import org.springframework.data.solr.repository.SolrCrudRepository;

import id.co.roxas.efim.solr.model.TblEfimFileDbstorage;

public interface TblEfimFileDbStorageRepo extends SolrCrudRepository
<TblEfimFileDbstorage, Integer> {

}

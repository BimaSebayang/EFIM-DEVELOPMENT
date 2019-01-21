package id.co.roxas.efim.solr.model;

import org.apache.solr.client.solrj.beans.Field;
import org.springframework.data.annotation.Id;
import org.springframework.data.solr.core.mapping.SolrDocument;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SolrDocument(collection="TBL_EFIM_DB_DAO")
public class Employee {

	   @Id
	   @Field
	   private int id;
	   @Field
	   private String name;
	   @Field
	   private String[] address;
	
	   public Employee(int id, String name,String[] address) {
		   this.id = id;
		   this.name = name;
		   this.address = address;
	   }
	   
	   public int getId() {
			return id;
		}
		public void setId(int id) {
			this.id = id;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String[] getAddress() {
			return address;
		}
		public void setAddress(String[] address) {
			this.address = address;
		}
	   
}

package tester;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.impl.XMLResponseParser;
import org.apache.solr.common.SolrInputDocument;

public class solrTester {
   public static void main(String[] args) {
	   String urlSolr = "http://localhost:29000/solr/EFIM";
	   HttpSolrClient solr = new HttpSolrClient.Builder(urlSolr).build();
	   //solr.setParser(new XMLResponseParser());
	   System.err.println(solr.toString());
	   SolrInputDocument document = new SolrInputDocument();
	   document.addField("id", "1001");
	   document.addField("name", "kenmo");
	   document.addField("price", "1203");
	   try {
		   List<String> add = new ArrayList<>();
		   add.add("1000");
		   add.add("1001");
		   solr.deleteById(add);
		   solr.commit();
		 //solr.add(document);
		 //solr.commit();
	} catch (SolrServerException | IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	  
   }
}

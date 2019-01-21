package webservice.lib;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;

public class MapperWs implements Serializable{
	private static final long serialVersionUID = -335682060113253806L;

	 public Map<String, Object> mapResultApi(String result){
		 ObjectMapper mapper = new ObjectMapper();
		 mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,
					false);
		 Map<String, Object> finalMap = new HashMap<>();
		 try {
			finalMap =  mapper.readValue(result, new TypeReference<HashMap<String, Object>>(){});
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	     return finalMap;
	 }
	
	 public Map<String, Object> mapperJsonToHashMap(String result){
		 ObjectMapper mapper = new ObjectMapper();
		 mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,
					false);
		 Map<String, Object> finalMap = new HashMap<>();
		 try {
			finalMap =  mapper.readValue(result, new TypeReference<HashMap<String, Object>>(){});
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	     return finalMap;
	 }
	 
	 public <T> T mapperJsonToSingleDto(String json, Class<T> clazz)
				throws Exception {
			ObjectMapper om = new ObjectMapper();
			om.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
			return om.readValue(json, clazz);
	}
	 
	 public <T> List<T> mapperJsonToListDto(
				String json,Class<T> clazz) throws Exception {
		    ObjectMapper om= new ObjectMapper();
			om.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
			om.configure(DeserializationFeature.FAIL_ON_MISSING_CREATOR_PROPERTIES, false);
			om.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
			TypeFactory t = TypeFactory.defaultInstance();
			List<T> list = om.readValue(json, t.constructCollectionType(ArrayList.class, clazz));
			return  list;
		}
	
	
}

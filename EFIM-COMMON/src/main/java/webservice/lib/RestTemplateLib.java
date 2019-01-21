package webservice.lib;

import java.io.IOException;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.modelmapper.internal.util.Strings;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ser.impl.StringArraySerializer;
import com.google.gson.Gson;

import common.lib.SecurityPr;
import webservice.global.WsResponse;

public class RestTemplateLib extends MapperWs implements Serializable{

	private static final long serialVersionUID = 1564074110921000829L;
	private static final String headUrl = "http://localhost:8080/EFIM-CORE";

	
	
	public WsResponse getResultWs(String url, Object body, String method, String... paramQuery) {
		WsResponse wsResponse = new WsResponse();
		
		MultiValueMap<String, Object> header = new LinkedMultiValueMap();
		SecurityPr spr = new SecurityPr();
		header.add("ip", spr.getIpAddress());
		header.add("mac", spr.getMacAddress());
		header.add("sn", spr.getSerialNumber());
		
		String finalparamQuery = "";
		if(paramQuery!=null) {
		if(paramQuery.length!=0) {
			   if(paramQuery.length != 0){
			   finalparamQuery = "?";
			   for (int i = 0; i < paramQuery.length; i++) {
				 finalparamQuery += paramQuery[i];
				 if(i<paramQuery.length){
					 finalparamQuery += "&"; 
				 }
			   }
			   }
		}
		}
		
		String resultApi = "";
		if(method.equalsIgnoreCase("POST")) {
			RestTemplate restTemplate = new RestTemplate();
			HttpEntity httpEntity = new HttpEntity(body,header);
			resultApi = restTemplate.exchange(headUrl+url+finalparamQuery, HttpMethod.POST,httpEntity,String.class).getBody();
			System.err.println("hasil : " + resultApi);
		}
		else if(method.equalsIgnoreCase("GET")) {
			RestTemplate restTemplate = new RestTemplate();
			HttpEntity httpEntity = new HttpEntity(null,header);
			resultApi = restTemplate.exchange(headUrl+url+finalparamQuery, HttpMethod.GET,httpEntity,String.class).getBody();
		}
	
		System.err.println("hasilnya sesungguhnya adalah " + resultApi);
		
		ObjectMapper om = new ObjectMapper();
		om.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		try {
			wsResponse = om.readValue(resultApi, WsResponse.class);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return wsResponse;
	}

}

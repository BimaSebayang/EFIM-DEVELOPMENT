package tester;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.springframework.web.client.RestTemplate;

import common.dto.headuser.TblDataUserDto;
import common.lib.SecurityPr;
import webservice.global.WsResponse;
import webservice.lib.RestTemplateLib;

public class testerCtl {

	public static void main(String[] args) {
	String format1 = "dd.MM.yyyy.HH.mm.ss";
	String format2 = "HH:mm:ss";
	String tesPass = "21.11.2018.09.30.40";
	
	DateTimeFormatter dtf = DateTimeFormat.forPattern(format1);
	DateTime jodaTime = dtf.parseDateTime(tesPass);
	DateTimeFormatter dtfOut = DateTimeFormat.forPattern(format2);
	
	System.err.println(dtfOut.print(jodaTime));
	
	}

}

package id.co.roxas.efim.common.common.lib;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

public class CommonDateLibPr {
	
        public static String formattingDateToString(Date date) {
        	DateFormat df = new SimpleDateFormat("dd.MM.yyyy.HH.mm.ss");
        	String formatDate = df.format(date);
        	return formatDate;
        }
        
        public static Date formattingStringToDate(String wholeText) {
        	DateTimeFormatter dtf = DateTimeFormat.forPattern("dd.MM.yyyy.HH.mm.ss");
        	DateTime jodaTime = dtf.parseDateTime(wholeText);
        	Date date = null;
        	try {
        		date = jodaTime.toDate();
        	}catch(Exception exp ) {
        		exp.getMessage();
        	}
            return date;
        }
 
        
        public static void main(String[] args) {
        	System.err.println(formattingStringToDate(formattingDateToString(new Date())));
        }
}

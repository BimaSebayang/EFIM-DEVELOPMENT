package tester;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

public class jodaTime {

	public static void main(String[] args) {
		DateTime dater = getJodaDate("mar, 01" + "2018");
		System.err.println(changeToMyFormat(dater.toDate(), "ddMMyy"));
	}
	
	private static String changeToMyFormat(Date date, String pattern) {
		return new SimpleDateFormat(pattern).format(date);
	}
	
	private static List<String> getAllPossibleFormat() {
		List<String> args = new ArrayList<>();		
		String[] formDate = new String[] {"dd","MMMM","yyyy"}; 
		for(int i = 0 ; i<=2; i++) {
			for(int j = 0; j<=2;j++) {
				for(int k = 0; k<=2;k++) {
					if(i!=j&&i!=k&&j!=k) {
					args.add(formDate[i]+" " +formDate[j]+" " + formDate[k]);
					args.add(formDate[i]+" " +formDate[j]+"" + formDate[k]);
					args.add(formDate[i]+"" +formDate[j]+" " + formDate[k]);
					args.add(formDate[i]+"" +formDate[j]+"" + formDate[k]);
					args.add(formDate[i]+" " +formDate[j]);
					args.add(formDate[i]+"" +formDate[j]);
					}
				}
			}
		}
		return args;
	}
	
	public static DateTime getJodaDate(String wholeText) {
		
		String formPat = "!@#$%^&*()_+-= {}|[]\\//:;',.";
		char[] charPat = formPat.toCharArray();
		for (char c : charPat) {
			 String cha = Character.toString(c);
			 wholeText = wholeText.replace(cha, "");
		}
		
		
		for (String str: getAllPossibleFormat()) {
			try {
				DateTimeFormatter df = DateTimeFormat
				        .forPattern(str);
				DateTime dateTime = df.withOffsetParsed().parseDateTime(wholeText);
				return dateTime;
			}catch(java.lang.IllegalArgumentException exc) {
			}
		}
		return null;
		
	}

}

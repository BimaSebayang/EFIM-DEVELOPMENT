package id.co.roxas.efim.common.tester;

import id.co.roxas.efim.common.common.dto.MapperLovInformation;

public class tester {

	public static void main(String[] args) {
		MapperLovInformation map1 = new MapperLovInformation("test", 0);
		MapperLovInformation map2 = new MapperLovInformation("test1", 0);
		MapperLovInformation map3 = new MapperLovInformation("test2", 0);
		MapperLovInformation map4 = new MapperLovInformation("test3", 0);
		MapperLovInformation map5 = new MapperLovInformation("test3", 4);

		MapperLovInformation[] lov = new MapperLovInformation[] { map1, map2, map3, map4, map5 };

		String temp = "";
		int whole = lov.length;
		boolean continu = true;
		while (continu) {
			temp = lov[whole-1].getKey();
			
			for(int i = 0; i<= whole-1;i++) {
				if (temp.equals(lov[i].getKey())&&i!=whole-1) {
					System.err.println("Pemetaan ["+lov[whole-1].getKey()+","+lov[whole-1].getValue()+"]" 
				    + " memiliki key yang sama dengan pemetaan ["+lov[i].getKey()+","+lov[i].getValue()+"]" );
					continu = false;
				}
			}

			whole--;
			if (whole == 0) {
				System.err.println("tidak ada yang sama");
				continu = false;
			}
		}
		
	

	}

}

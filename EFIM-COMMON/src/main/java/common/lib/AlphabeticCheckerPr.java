package common.lib;

public class AlphabeticCheckerPr {
	private boolean isArithmatic(String s){
		if(s.equals("1")||s.equals("2")||s.equals("3")||s.equals("4")||s.equals("5")||s.equals("6")||s.equals("7")||s.equals("8")||s.equals("9")||s.equals("0")) {
			return true;
		}
		return false;
	}
	
	
	public boolean isNumber(String strCheck) {
		char[] charStr = strCheck.toCharArray();
		for(int i = 0; i<charStr.length; i++){
			String pC = Character.toString(charStr[i]);
			if(!isArithmatic(pC)){
			return false;
			}
		}
		return true;
	}

	
	public boolean thereIsNumber(String strCheck) {
		// TODO Auto-generated method stub
		return false;
	}

	
	public boolean thereIsSpecialCharacter(String strCheck) {
		// TODO Auto-generated method stub
		return false;
	}
	
	public boolean convertToNumeric(String strCheck) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean isNumeric(String strCheck) {
		// TODO Auto-generated method stub
		return false;
	}

}

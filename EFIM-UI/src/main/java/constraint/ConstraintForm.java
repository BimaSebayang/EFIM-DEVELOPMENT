package constraint;

import java.io.Serializable;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.WrongValueException;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zul.Constraint;
import org.zkoss.zul.Intbox;
import org.zkoss.zul.Textbox;

import common.lib.AlphabeticCheckerPr;

public class ConstraintForm implements Serializable{

	private static final long serialVersionUID = -1487563044142747756L;
   
	private AlphabeticCheckerPr alphabeticCheckerPr = new AlphabeticCheckerPr();
	private Constraint consNullValue = forNullValue();
	private Constraint consNullNumber = forNullNumber();
	private Constraint consNumberOnlyInTextBox = numberOnlyInTextBox();
	
	private void InValidFormClass(String idName) {
		String si = "valid_FormClass('"+ idName +"',"+"'Panel"+idName+"',"+"'Label"+idName+"','textboxFormInputInvalid','panelFormInvalid','labelFormInputInvalid')";
		Clients.evalJavaScript(si);
	}
	
    private void ValidFormClass(String idName) {
    	String si = "invalid_FormClass('"+ idName +"',"+"'Panel"+idName+"',"+"'Label"+idName+"','textboxFormInputInvalid','panelFormInvalid','labelFormInputInvalid')";
		Clients.evalJavaScript(si);
		String s = "valid_FormClass('"+ idName +"',"+"'Panel"+idName+"',"+"'Label"+idName+"','textboxFormInput','panelForm','labelFormInput')";
		Clients.evalJavaScript(s);
	}
    
 
	
	protected Constraint forNullValue(){
		return new Constraint() {
			
			@Override
			public void validate(Component comp, Object value)
					throws WrongValueException {
				String idName = comp.getId();
				String parentForm = "";
				try {
				Textbox tb = (Textbox) comp;
				parentForm = tb.getName();
				}
				catch(Exception e) {
					Intbox ib = (Intbox) comp;
					parentForm = ib.getName();
				}
				
				if( ((String) value).trim().equalsIgnoreCase("")){
					InValidFormClass(idName);
					
				    throw new WrongValueException(comp,"Bagian Ini Tidak Boleh Kosong");
					}
				else {
					ValidFormClass(idName);
			       }
				}
			 };
    	}

	
	protected Constraint forNullNumber(){
		return new Constraint() {
			
			@Override
			public void validate(Component comp, Object value)
					throws WrongValueException {
				String idName = comp.getId();		        
				if(value == null){
					InValidFormClass(idName);
				    throw new WrongValueException(comp,"Bagian Ini Tidak Boleh Kosong");
					}
				else {
					ValidFormClass(idName);
			       }
				}
			 };
    	}
	
	
	protected Constraint numberOnlyInTextBox(){
		return new Constraint() {
			
			@Override
			public void validate(Component comp, Object value)
					throws WrongValueException {
				String idName = comp.getId();		        
				if(((String) value).trim().equalsIgnoreCase("")){
					InValidFormClass(idName);
				    throw new WrongValueException(comp,"Bagian Ini Tidak Boleh Kosong");
					}
				else {	
					
					if(!alphabeticCheckerPr.isNumber((String)value)) {
						InValidFormClass(idName);
					    throw new WrongValueException(comp,"Hanya Bisa Isi Angka");
					}
					else {
					ValidFormClass(idName);
					}
			       }
				}
			 };
    	}
	
	
	public Constraint getConsNullNumber() {
		return consNullNumber;
	}

	
	public Constraint getConsNullValue() {
		return consNullValue;
	}

	public Constraint getConsNumberOnlyInTextBox() {
		return consNumberOnlyInTextBox;
	}

}

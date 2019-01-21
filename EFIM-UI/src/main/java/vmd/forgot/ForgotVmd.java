package vmd.forgot;

import java.util.HashMap;
import java.util.Map;

import org.zkoss.bind.BindUtils;
import org.zkoss.bind.ValidationContext;
import org.zkoss.bind.Validator;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.validator.AbstractValidator;
import org.zkoss.lang.Strings;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.WrongValueException;
import org.zkoss.zul.Constraint;
import org.zkoss.zul.Messagebox;

import vmd.BaseVmd;
import webservice.global.WsResponse;

@Init(superclass = true)
public class ForgotVmd extends BaseVmd{
	private static final long serialVersionUID = -7835629868714512176L;
    private String newPassword = "";
    private String konfirmasiPass = "";
    private String getCurrentId = "";
    private boolean validEmail = true;
    private String label = "";
    
    @Override
	public void loadList() {
		super.loadList();
		String emailId = Executions.getCurrent().getParameter("id");
		WsResponse wsResponse = restTemplateLib.getResultWs("/ForgotPassCtl/GetUserId", emailId, "post", "projectCode="+PROJECT);
		Map<String, Object> mapp = restTemplateLib.mapperJsonToHashMap(wsResponse.getWsContent());
		boolean boolNext = (boolean) mapp.get("result");
		if(boolNext) {
		setGetCurrentId((String)mapp.get("sessionCode"));
		BindUtils.postNotifyChange(null, null, this, "getCurrentId");
		}
		else {
			setValidEmail(false);
			setLabel((String)mapp.get("sessionCode"));
			BindUtils.postNotifyChange(null, null, this, "validEmail");
			BindUtils.postNotifyChange(null, null, this, "label");
		}
	}
    
    @Command("reset")
    public void reset() {
    	
    	if(!newPassword.equalsIgnoreCase(konfirmasiPass)) {
    		showErrorMessageBox("Mohon Maaf password dan konfirmasi password yang anda berikan masih belum sama.");
			setNewPassword("");
			setKonfirmasiPass("");
			BindUtils.postNotifyChange(null, null, this, "newPassword");
			BindUtils.postNotifyChange(null, null, this, "konfirmasiPass");
			return;
    	}
    	
    	Map<String, String> mapper = new HashMap<>();
    	mapper.put("user",getCurrentId);
    	mapper.put("pass", getNewPassword());
    	WsResponse wsResponse = restTemplateLib.getResultWs("/ForgotPassCtl/ResetPassword", mapper, "post", "projectCode="+PROJECT);
    	showInformationMessageBox("Password Anda Berhasil Diganti");
    }
    
    public Constraint getConstrainForgotPassword() {
		return new Constraint() {

			@Override
			public void validate(Component comp, Object value) throws WrongValueException {
				String val = (String) value;
				String nuVal = "!" + val.trim() + "!";
				if (nuVal.equalsIgnoreCase("!!")) {
					InValidFormClass("newPassword", "text-boxWrongValue");
					throw new WrongValueException(comp, "Password Tidak Boleh Kosong");
				} 
				else if(!isPasswordValid(val)) {
					InValidFormClass("newPassword", "text-boxWrongValue");
					throw new WrongValueException(comp, "Format Password Tidak Sesuai");
				}
				else {
					ValidFormClass("newPassword", "text-boxWrongValue", "text-box");
				}
			}
		};
	}

   
	public String getNewPassword() {
		return newPassword;
	}
	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}
	public String getKonfirmasiPass() {
		return konfirmasiPass;
	}
	public void setKonfirmasiPass(String konfirmasiPass) {
		this.konfirmasiPass = konfirmasiPass;
	}

	public String getGetCurrentId() {
		return getCurrentId;
	}

	public boolean isValidEmail() {
		return validEmail;
	}

	public void setValidEmail(boolean validEmail) {
		this.validEmail = validEmail;
	}

	public void setGetCurrentId(String getCurrentId) {
		this.getCurrentId = getCurrentId;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}
    
    
}

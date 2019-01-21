package vmd.registeration;

import java.util.HashMap;
import java.util.Map;

import org.zkoss.bind.BindUtils;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.impl.BinderUtil;
import org.zkoss.zk.ui.Executions;

import vmd.BaseVmd;
import webservice.global.WsResponse;

@Init(superclass=true)
public class SuccessPrVmd extends BaseVmd{

	private String label ;
	private boolean buttonActive = true;
	
	private static final long serialVersionUID = -9070173358369223370L;
    
	@Override
	public void loadList() {
		super.loadList();
		String hp = Executions.getCurrent().getParameter("id");
		//
		
		WsResponse wsResponse = restTemplateLib.getResultWs("/RegisterCtl/CreateAndSessionUser", hp, "Post","projectCode="+PROJECT);
		Map<String, Object> resultMap = new HashMap<>();
		resultMap = restTemplateLib.mapperJsonToHashMap(wsResponse.getWsContent());
		System.err.println("Hasilnya adalah " + resultMap.get("result"));
		
		boolean next = (boolean) resultMap.get("result");
		if(next) {
			setLabel("Terima kasih menggunakan layanan penyimpanan data EFIM. Akun anda berhasil aktif. " + 
					"Anda dapat langsung menggunakan akun anda dengan menekan tombol dibawah ini.");
		}
		else {
			String sessionCode = (String) resultMap.get("sessionCode");
			if(sessionCode.equalsIgnoreCase("ZERO")) {
				setLabel("ERROR -2");
				setButtonActive(next);
			}
			else if(sessionCode.equalsIgnoreCase("INFINITY")) {
				setLabel("USER ALREADY ACTIVE");
				setButtonActive(next);
			}
			else {
				setLabel(sessionCode);
				setButtonActive(next);
			}
		} 
		
		BindUtils.postNotifyChange(null, null, this, "buttonActive");
		BindUtils.postNotifyChange(null, null, this, "label");
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public boolean isButtonActive() {
		return buttonActive;
	}

	public void setButtonActive(boolean buttonActive) {
		this.buttonActive = buttonActive;
	}
}

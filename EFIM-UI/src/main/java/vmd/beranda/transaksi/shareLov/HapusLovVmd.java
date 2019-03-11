package vmd.beranda.transaksi.shareLov;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import org.zkoss.bind.BindUtils;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.Init;
import org.zkoss.zul.Window;

import Share.WsResponse;
import id.co.roxas.efim.common.webservice.lib.RestTemplateLib;
import vmd.BaseVmd;

@Init(superclass = true)
public class HapusLovVmd extends BaseVmd implements Serializable {

	private static final long serialVersionUID = -6900270578466645436L;
	private boolean yesClick = false;
	private boolean timerStart = true;

	@Override
	public void loadList() {
		super.loadList();
	}

	@Command("timerStartup")
	public void timerStartup(@BindingParam("destroy") Window lov) {
		try {
			new Thread().sleep(400);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		if(yesClick) {
		lov.detach();
		timerStart = false;
		BindUtils.postNotifyChange(null, null, this,"timerStart");
		}
	}
	
	@Command("yesDelete")
	public void yesDelete(@BindingParam("destroy") Window lov) {
		yesClick = true;
		BindUtils.postNotifyChange(null, null, this,"yesClick");
		Map<String, Object> bodyRequest = new HashMap<>(); 
		bodyRequest.put("file_id_reff", getInformationForLov("file_id_reff").getValue());
		bodyRequest.put("file_type", getInformationForLov("file_type").getValue());
		bodyRequest.put("file_str_id_reff", getInformationForLov("file_str_id_reff").getValue());
		
		String deleteType = "";
		if(((String)getInformationForLov("file_type").getValue()).equalsIgnoreCase(BIN)) {
			deleteType = "permanent";
		}
		else {
			deleteType = "temporary";
		}
		
		WsResponse wsResponse = new RestTemplateLib().getResultWs("/HapusCompCtl/Delete/"+deleteType, bodyRequest, "post", "projectCode="+getComponentUser().getProjectCode());
		if(!wsResponse.getIsErrorSvc().booleanValue()) {
			Map<String, Object> mapper = new RestTemplateLib().mapperJsonToHashMap(wsResponse.getWsContent());
			boolean result;
			if (mapper != null) {
				result = (boolean) mapper.get("result");
				if (result) {
					Map<String, Object> args = new HashMap<>();
					args.put("success", true);
					BindUtils.postGlobalCommand(null, null, "HapusLov", args);
				} else {
					showErrorMessageBox("message : " + mapper.get("error"));
				}
			} else {
				showErrorMessageBox("cannot retrieve wsContent");
			}
		}
	    
	}

	@Command("noDelete")
	public void noDelete(@BindingParam("destroy") Window lov) {
		lov.detach();
	}

	public boolean isYesClick() {
		return yesClick;
	}

	public void setYesClick(boolean yesClick) {
		this.yesClick = yesClick;
	}

	public boolean isTimerStart() {
		return timerStart;
	}

	public void setTimerStart(boolean timerStart) {
		this.timerStart = timerStart;
	}

}

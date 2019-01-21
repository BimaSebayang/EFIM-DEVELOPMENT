package vmd.error;

import java.io.Serializable;

import org.zkoss.bind.BindUtils;
import org.zkoss.bind.annotation.Init;

import vmd.BaseVmd;

@Init(superclass = true)
public class error404Vmd extends BaseVmd implements Serializable{

	private String label ;
	private static final long serialVersionUID = -4963398499062058408L;

	@Override
	public void loadList() {
		super.loadList();
		setLabel("Mohon maaf. Page Yang anda Tuju Tidak Ada.");
		BindUtils.postNotifyChange(null, null, this, "label");
		
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}
	
}

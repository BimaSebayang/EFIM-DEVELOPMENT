package vmd.error;

import java.io.Serializable;

import org.zkoss.bind.BindUtils;
import org.zkoss.bind.annotation.Init;

import vmd.BaseVmd;

@Init(superclass = true)
public class error500Vmd extends BaseVmd implements Serializable{

	private static final long serialVersionUID = 5754725518729081832L;
	private String label ;

	@Override
	public void loadList() {
		super.loadList();
		setLabel("500 Internal Server Error");
		BindUtils.postNotifyChange(null, null, this, "label");
		
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}
	
}


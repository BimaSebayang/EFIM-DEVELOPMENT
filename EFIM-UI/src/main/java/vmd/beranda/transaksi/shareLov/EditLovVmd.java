package vmd.beranda.transaksi.shareLov;

import java.io.Serializable;

import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.Init;
import org.zkoss.zul.Window;

import vmd.BaseVmd;

@Init(superclass = true)
public class EditLovVmd extends BaseVmd implements Serializable{

	private static final long serialVersionUID = 1075698674175979833L;
	private boolean yesClick = false;
	private boolean timerStart = true;
	
	@Override
	public void loadList() {
		super.loadList();
	}

	@Command("noEdit")
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

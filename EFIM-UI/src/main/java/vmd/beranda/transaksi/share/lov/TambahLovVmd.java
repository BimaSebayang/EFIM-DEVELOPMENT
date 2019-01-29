package vmd.beranda.transaksi.share.lov;

import java.io.Serializable;

import org.zkoss.bind.annotation.Init;

import vmd.BaseVmd;

@Init(superclass=true)
public class TambahLovVmd extends BaseVmd implements Serializable{
	
	private static final long serialVersionUID = 3276365232009484683L;
	
	    @Override
	 	public void loadList() {
	 		super.loadList();
	 		showErrorMessageBox("nilai : " + getInformationForLov().get("file_type"));
	 	}

}

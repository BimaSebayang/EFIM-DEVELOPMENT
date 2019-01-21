package vmd.beranda;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.zkoss.bind.BindUtils;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.Init;

import common.dto.headuser.TblEfimDbDto;
import vmd.BaseVmd;
import webservice.global.WsResponse;

@Init(superclass = true)
public class FirstPageVmd extends BaseVmd implements Serializable {
	private static final long serialVersionUID = -8179266191439272476L;
	private TblEfimDbDto tblEfimDbDto = new TblEfimDbDto();
	private List<TblEfimDbDto> tblEfimDbDtos = new ArrayList<>();
	private String menu = "";
	private boolean moveTimer = true;

	@Command("onRecruitTime")
	public void onRecruitTime() {
		Map<String, Object> mapp = new HashMap<>();
		mapp.put("userId", getComponentUser().getUserId());
		if (getRegisterDbUserInCurrentAttributeFile(getComponentUser().getUserId()) != null) {
			List<String> arraysStr = new ArrayList<>();
			for (TblEfimDbDto dto : getRegisterDbUserInCurrentAttributeFile(getComponentUser().getUserId())) {
				arraysStr.add(dto.getFileIdReff());
			}
			mapp.put("idReffs", arraysStr);
		}
		WsResponse response = restTemplateLib.getResultWs("/UserEfimDbCompCtl/UserEfim",mapp,"post",
				"projectCode=" + PROJECT);

		boolean isErrorSvc = response.getIsErrorSvc();
		if (!isErrorSvc) {
			try {
				tblEfimDbDtos = restTemplateLib.mapperJsonToListDto(response.getWsContent(), TblEfimDbDto.class);
				registerDbUserToDesktop(getComponentUser().getUserId(), tblEfimDbDtos);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		else {
			moveTimer = false;
			BindUtils.postNotifyChange(null, null, this, "moveTimer");
		}
		BindUtils.postNotifyChange(null, null, this, "tblEfimDbDtos");
	}

	@Override
	public void loadList() {
		super.loadList();
	}

	@Command("dashboard")
	public void dashboard() {
		menu = "/beranda/transaksi/dashboard.zul";
		BindUtils.postNotifyChange(null, null, this, "menu");
	}

	@Command("file")
	public void file() {
		menu = "/beranda/transaksi/file.zul";
		BindUtils.postNotifyChange(null, null, this, "menu");
	}

	@Command("notifikasi")
	public void notifikasi() {
		menu = "/beranda/transaksi/notifikasi.zul";
		BindUtils.postNotifyChange(null, null, this, "menu");
	}

	@Command("inbox")
	public void inbox() {
		menu = "/beranda/transaksi/inbox.zul";
		BindUtils.postNotifyChange(null, null, this, "menu");
	}

	@Command("keluar")
	public void keluar() {

	}

	public TblEfimDbDto getTblEfimDbDto() {
		return tblEfimDbDto;
	}

	public void setTblEfimDbDto(TblEfimDbDto tblEfimDbDto) {
		this.tblEfimDbDto = tblEfimDbDto;
	}

	public List<TblEfimDbDto> getTblEfimDbDtos() {
		return tblEfimDbDtos;
	}

	public void setTblEfimDbDtos(List<TblEfimDbDto> tblEfimDbDtos) {
		this.tblEfimDbDtos = tblEfimDbDtos;
	}

	public String getMenu() {
		return menu;
	}

	public void setMenu(String menu) {
		this.menu = menu;
	}

	public boolean isMoveTimer() {
		return moveTimer;
	}

	public void setMoveTimer(boolean moveTimer) {
		this.moveTimer = moveTimer;
	}

}

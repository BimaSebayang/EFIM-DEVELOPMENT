package vmd.beranda.transaksi.fileDetail;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.zkoss.bind.BindUtils;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.Init;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.ForwardEvent;
import org.zkoss.zk.ui.event.MouseEvent;
import org.zkoss.zul.Listcell;

import common.dto.headuser.TblEfimDbDto;
import common.dto.stream.TblEfimFileDbstorageDto;
import vmd.BaseVmd;
import webservice.global.WsResponse;

@Init(superclass = true)
public class pictureVmd extends BaseVmd implements Serializable {

	private static final long serialVersionUID = -2144646169306928976L;
	private List<TblEfimDbDto> tblEfimDbDtos1 = new ArrayList<>(); // untuk menampung data di bagian atas
	private List<TblEfimDbDto> tblEfimDbDtos2 = new ArrayList<>(); // untuk menampung data di bagian bawah
	private Map<Integer, Map<Integer, TblEfimDbDto>> mapForColumns = new HashMap<>();
	private TblEfimDbDto tblEfimDbDto = new TblEfimDbDto();
	private boolean enoughTop = false;
	private boolean enoughBottom = false;
	private boolean moveTimer = true;
	private Integer timeCounter = 1;

	private List<TblEfimDbDto> getTempEfimDbDtos(List<TblEfimDbDto> tblEfimDbDtos) {
		List<TblEfimDbDto> tempTblEfimDbDtos = new ArrayList<>();
		for (TblEfimDbDto tblEfimDbDto : tblEfimDbDtos) {
			if (tblEfimDbDto.getTblEfimFileDbstorageDto() == null) {
				tempTblEfimDbDtos.add(tblEfimDbDto);
			}

		}
		return tempTblEfimDbDtos;
	}

	@Command("onRecruitPictureTime")
	public void onRecruitPictureTime() {
		// http://localhost:8080/EFIM-CORE/UserEfimDbCompCtl/FileStream?projectCode=EFIM&fileStrIdRef=GBOLAAHQWCCDAHKLCCLV&fileIdRef=TED000000001

		if (!mapForColumns.isEmpty()) {
			if (timeCounter <= mapForColumns.size()) {
				Map<Integer, TblEfimDbDto> tempTblEfim = mapForColumns.get(timeCounter);
				for (Entry<Integer, TblEfimDbDto> b : mapForColumns.get(timeCounter).entrySet()) {
					WsResponse response = restTemplateLib.getResultWs("/UserEfimDbCompCtl/FileStream", null, "get",
							"projectCode=" + PROJECT, "fileStrIdRef=" + b.getValue().getFileStrIdReff(),
							"fileIdRef=" + b.getValue().getFileIdReff());
					
					TblEfimDbDto efimDbDto = b.getValue();
					try {
						efimDbDto.setTblEfimFileDbstorageDto(restTemplateLib
								.mapperJsonToSingleDto(response.getWsContent(), TblEfimFileDbstorageDto.class));
					} catch (Exception e) {
						e.printStackTrace();
					}
					tempTblEfim.put(b.getKey(), efimDbDto);
				}
				mapForColumns.put(timeCounter, tempTblEfim);
				BindUtils.postNotifyChange(null, null, this, "mapForColumns");
				timeCounter++;
			} else {
				moveTimer = false;
				BindUtils.postNotifyChange(null, null, this, "moveTimer");
			}

		}
	}

	public boolean isMoveTimer() {
		return moveTimer;
	}

	public void setMoveTimer(boolean moveTimer) {
		this.moveTimer = moveTimer;
	}

	@Command("tester")
	public void tester() {
		showInformationMessageBox("tester doang");
	}
	
	@Override
	public void loadList() {
		super.loadList();
		List<TblEfimDbDto> tblEfimDbDtos = getRegisterDbUserInCurrentFile(getComponentUser().getUserId(), "PICT");
		try {
			int counterpart = 0;
			int counterColumn = 0;
			int counterService = 0;
			Map<Integer, TblEfimDbDto> mapTempTbl = new HashMap<>();
			for (TblEfimDbDto tblEfimDbDto : tblEfimDbDtos) {
				counterpart++;
				counterService++;
				mapTempTbl.put(counterService, tblEfimDbDto);
				if (counterpart % DIV == 0) {
					counterColumn++;
					mapForColumns.put(counterColumn, mapTempTbl);
					mapTempTbl = new HashMap<>();
					counterService = 0;
				} else if (counterpart == tblEfimDbDtos.size()) {
					counterColumn++;
					mapForColumns.put(counterColumn, mapTempTbl);
				}

			}
		} catch (NullPointerException npe) {
			npe.printStackTrace();
		}
		BindUtils.postNotifyChange(null, null, this, "mapForColumns");
	}

	public List<TblEfimDbDto> getTblEfimDbDtos1() {
		return tblEfimDbDtos1;
	}

	public void setTblEfimDbDtos1(List<TblEfimDbDto> tblEfimDbDtos1) {
		this.tblEfimDbDtos1 = tblEfimDbDtos1;
	}

	public List<TblEfimDbDto> getTblEfimDbDtos2() {
		return tblEfimDbDtos2;
	}

	public void setTblEfimDbDtos2(List<TblEfimDbDto> tblEfimDbDtos2) {
		this.tblEfimDbDtos2 = tblEfimDbDtos2;
	}

	public TblEfimDbDto getTblEfimDbDto() {
		return tblEfimDbDto;
	}

	public void setTblEfimDbDto(TblEfimDbDto tblEfimDbDto) {
		this.tblEfimDbDto = tblEfimDbDto;
	}

	public Map<Integer, Map<Integer, TblEfimDbDto>> getMapForColumns() {
		return mapForColumns;
	}

	public void setMapForColumns(Map<Integer, Map<Integer, TblEfimDbDto>> mapForColumns) {
		this.mapForColumns = mapForColumns;
	}

	public boolean isEnoughTop() {
		return enoughTop;
	}

	public void setEnoughTop(boolean enoughTop) {
		this.enoughTop = enoughTop;
	}

	public boolean isEnoughBottom() {
		return enoughBottom;
	}

	public void setEnoughBottom(boolean enoughBottom) {
		this.enoughBottom = enoughBottom;
	}

}

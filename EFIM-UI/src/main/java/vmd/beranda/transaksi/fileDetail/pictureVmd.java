package vmd.beranda.transaksi.fileDetail;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.zkoss.bind.BindContext;
import org.zkoss.bind.BindUtils;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.bind.annotation.GlobalCommand;
import org.zkoss.bind.annotation.Init;

import id.co.roxas.efim.common.common.dto.headuser.TblEfimDbDto;
import id.co.roxas.efim.common.common.dto.stream.TblEfimFileDbstorageDto;
import id.co.roxas.efim.common.webservice.global.WsResponse;
import id.co.roxas.efim.common.webservice.lib.RestTemplateLib;
import vmd.BaseVmd;

@Init(superclass = true)
public class pictureVmd extends BaseVmd implements Serializable {

	private static final long serialVersionUID = -2144646169306928976L;
	private List<TblEfimDbDto> tblEfimDbDtos1 = new ArrayList<>(); // untuk menampung data di bagian atas
	private List<TblEfimDbDto> tblEfimDbDtos2 = new ArrayList<>(); // untuk menampung data di bagian bawah
	private Map<Integer, Map<Integer, TblEfimDbDto>> mapForColumns = new HashMap<>();
	private Map<String, byte[]> mapPictures = new HashMap<>();
	private TblEfimDbDto tblEfimDbDto = new TblEfimDbDto();
	private boolean enoughTop = false;
	private boolean enoughBottom = false;
	private boolean moveTimer = true;
	private Integer timeCounter = 1;
	private byte[] fileStr = null;
	private String selectedPict = new String();

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
		BindUtils.postNotifyChange(null, null, this, "mapPictures");
	}

	public boolean isMoveTimer() {
		return moveTimer;
	}

	public void setMoveTimer(boolean moveTimer) {
		this.moveTimer = moveTimer;
	}

	private boolean activator = false;

	public boolean isActivator() {
		return activator;
	}

	public void setActivator(boolean activator) {
		this.activator = activator;
	}

	@Command("mouseOverPict")
	public void mouseOverPict(@BindingParam("imagePart") String imagePart,
			@ContextParam(ContextType.BIND_CONTEXT) BindContext ctx) {
		InValidFormClass(imagePart, "pictPanelMouse");
		selectedPict = imagePart;
		BindUtils.postNotifyChange(null, null, this, "selectedPict");
	}

	@Command("mouseOutPict")
	public void mouseOutPict(@BindingParam("imagePart") String imagePart,
			@ContextParam(ContextType.BIND_CONTEXT) BindContext ctx) {
		ValidFormClass(imagePart, "pictPanelMouse", "pictPanel");
		selectedPict = "";
		BindUtils.postNotifyChange(null, null, this, "selectedPict");
	}

	@Command("mouseOverButtonFlag")
	public void mouseOverButtonFlag(@BindingParam("comp") String comp,
			@ContextParam(ContextType.BIND_CONTEXT) BindContext ctx) {
		InValidFormClass(comp, "pictPanelMouse");
		selectedPict = comp;
		BindUtils.postNotifyChange(null, null, this, "selectedPict");
	}

	@Command("mouseOutButtonFlag")
	public void mouseOutButtonFlag(@BindingParam("comp") String comp,
			@ContextParam(ContextType.BIND_CONTEXT) BindContext ctx) {
		ValidFormClass(comp, "pictPanelMouse", "pictPanel");
		selectedPict = "";
		BindUtils.postNotifyChange(null, null, this, "selectedPict");
	}

	@Command("mouseOverButtonComment")
	public void mouseOverButtonComment(@BindingParam("comp") String comp,
			@ContextParam(ContextType.BIND_CONTEXT) BindContext ctx) {
		InValidFormClass(comp, "pictPanelMouse");
		selectedPict = comp;
		BindUtils.postNotifyChange(null, null, this, "selectedPict");
	}

	@Command("mouseOutButtonComment")
	public void mouseOutButtonComment(@BindingParam("comp") String comp,
			@ContextParam(ContextType.BIND_CONTEXT) BindContext ctx) {
		ValidFormClass(comp, "pictPanelMouse", "pictPanel");
		selectedPict = "";
		BindUtils.postNotifyChange(null, null, this, "selectedPict");
	}

	@Command("mouseOverButtonEdit")
	public void mouseOverButtonEdit(@BindingParam("comp") String comp,
			@ContextParam(ContextType.BIND_CONTEXT) BindContext ctx) {
		InValidFormClass(comp, "pictPanelMouse");
		selectedPict = comp;
		BindUtils.postNotifyChange(null, null, this, "selectedPict");
	}

	@Command("mouseOutButtonEdit")
	public void mouseOutButtonEdit(@BindingParam("comp") String comp,
			@ContextParam(ContextType.BIND_CONTEXT) BindContext ctx) {
		ValidFormClass(comp, "pictPanelMouse", "pictPanel");
		selectedPict = "";
		BindUtils.postNotifyChange(null, null, this, "selectedPict");
	}

	@Command("mouseOverButtonHeart")
	public void mouseOverButtonHeart(@BindingParam("comp") String comp,
			@ContextParam(ContextType.BIND_CONTEXT) BindContext ctx) {
		InValidFormClass(comp, "pictPanelMouse");
		selectedPict = comp;
		BindUtils.postNotifyChange(null, null, this, "selectedPict");
	}

	@Command("mouseOutButtonHeart")
	public void mouseOutButtonHeart(@BindingParam("comp") String comp,
			@ContextParam(ContextType.BIND_CONTEXT) BindContext ctx) {
		ValidFormClass(comp, "pictPanelMouse", "pictPanel");
		selectedPict = "";
		BindUtils.postNotifyChange(null, null, this, "selectedPict");
	}

	@GlobalCommand("TambahLov")
	public void tambahLov(@BindingParam("success") boolean success) {
		if (success) {
			loadList();
			moveTimer = true;
			BindUtils.postNotifyChange(null, null, this, "moveTimer");
		}
	}

	@Override
	public void loadList() {
		super.loadList();
		Map<String, Object> tempTblEfim = new HashMap<>();
		tempTblEfim.put("userId", getComponentUser().getUserId());
		tempTblEfim.put("projectCode", getComponentUser().getProjectCode());
		tempTblEfim.put("fileType", PICT);
		WsResponse response = restTemplateLib.getResultWs("/UserEfimDbCompCtl/UserEfim", tempTblEfim, "post",
				"projectCode=" + PROJECT, "page=1");
		List<TblEfimDbDto> tblEfimDbDtos = new ArrayList<>();
		try {
			tblEfimDbDtos = new RestTemplateLib().mapperJsonToListDto(response.getWsContent(), TblEfimDbDto.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			int counterpart = 0;
			int counterColumn = 0;
			int counterService = 0;
			Map<Integer, TblEfimDbDto> mapTempTbl = new HashMap<>();
			for (TblEfimDbDto tblEfimDbDto : tblEfimDbDtos) {
				mapPictures.put(tblEfimDbDto.getFileIdReff(), null);
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
		// BindUtils.postNotifyChange(null, null, this, "mapPictures");

		new Thread(new Runnable() {
			public void run() {
				boolean cont = true;
				while (cont) {
					if (!mapForColumns.isEmpty()) {
						if (timeCounter <= mapForColumns.size()) {
							// Map<Integer, TblEfimDbDto> tempTblEfim = mapForColumns.get(timeCounter);
							for (Entry<Integer, TblEfimDbDto> b : mapForColumns.get(timeCounter).entrySet()) {
								WsResponse response = restTemplateLib.getResultWs("/UserEfimDbCompCtl/FileStream", null,
										"get", "projectCode=" + PROJECT,
										"fileStrIdRef=" + b.getValue().getFileStrIdReff(),
										"fileIdRef=" + b.getValue().getFileIdReff());
								TblEfimDbDto efimDbDto = b.getValue();
								TblEfimFileDbstorageDto tblEfimFileDbstorageDto = new TblEfimFileDbstorageDto();
								try {
									tblEfimFileDbstorageDto = restTemplateLib.mapperJsonToSingleDto(
											response.getWsContent(), TblEfimFileDbstorageDto.class);
									efimDbDto.setTblEfimFileDbstorageDto(restTemplateLib.mapperJsonToSingleDto(
											response.getWsContent(), TblEfimFileDbstorageDto.class));
									mapPictures.put(tblEfimFileDbstorageDto.getFileIdRef(),
											tblEfimFileDbstorageDto.getFileStr());
								} catch (Exception e) {
									e.printStackTrace();
								}
								// tempTblEfim.put(b.getKey(), efimDbDto);
							}
							// mapForColumns.put(timeCounter, tempTblEfim);
							timeCounter++;
						}
					} else {
						cont = false;
					}
				}
			}
		}).start();

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

	public byte[] getFileStr() {
		return fileStr;
	}

	public void setFileStr(byte[] fileStr) {
		this.fileStr = fileStr;
	}

	public String getSelectedPict() {
		return selectedPict;
	}

	public void setSelectedPict(String selectedPict) {
		this.selectedPict = selectedPict;
	}

	public Map<String, byte[]> getMapPictures() {
		return mapPictures;
	}

	public void setMapPictures(Map<String, byte[]> mapPictures) {
		this.mapPictures = mapPictures;
	}

}

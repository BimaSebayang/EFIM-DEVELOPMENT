package vmd.beranda.transaksi.fileDetail;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.zkoss.bind.BindContext;
import org.zkoss.bind.BindUtils;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.bind.annotation.GlobalCommand;
import org.zkoss.bind.annotation.Init;

import id.co.roxas.efim.common.common.lib.dto.MapperLovInformation;
import id.co.roxas.efim.common.common.lib.dto.headuser.TblEfimDbDto;
import id.co.roxas.efim.common.webservice.global.WsResponse;
import id.co.roxas.efim.common.webservice.lib.RestTemplateLib;
import vmd.BaseVmd;

@Init(superclass = true)
public class documentVmd extends BaseVmd implements Serializable {
	private static final long serialVersionUID = -4626408736848812202L;
	private List<TblEfimDbDto> tblEfimDbDtos1 = new ArrayList<>(); // untuk menampung data di bagian atas
	private List<TblEfimDbDto> tblEfimDbDtos2 = new ArrayList<>(); // untuk menampung data di bagian bawah
	private Map<Integer, Map<Integer, TblEfimDbDto>> mapForColumns = new HashMap<>();
	private Map<String, TblEfimDbDto> mapTblEfimDbDto = new HashMap<>();
	private Map<String, byte[]> mapPictures = new HashMap<>();
	private TblEfimDbDto tblEfimDbDto = new TblEfimDbDto();
	private boolean enoughTop = false;
	private boolean enoughBottom = false;
	private boolean moveTimer = true;
	private String changeName = new String();
	private Integer timeCounter = 1;
	private byte[] fileStr = null;
	private String selectedPict = new String();
	private String selectedFileIdReff = new String();
    private boolean visibleEdit = false;
    
    @Override
	public void loadList() {
		super.loadList();
		Map<String, Object> tempTblEfim = new HashMap<>();
		tempTblEfim.put("userId", getComponentUser().getUserId());
		tempTblEfim.put("projectCode", getComponentUser().getProjectCode());
		tempTblEfim.put("fileType", DOCU);
		WsResponse response = restTemplateLib.getResultWs("/UserEfimDbCompCtl/UserEfim", tempTblEfim, "post",
				"projectCode=" + getComponentUser().getProjectCode(), "page=1");
		getAllResultOfTableContent(response);
	}
    
    @Command("caseChange")
	public void caseChange(@BindingParam("caseMethod") String caseMethod) {
		Map<String, Object> bodyInfo = new HashMap<>();
		setCaseSearch(caseMethod);
		bodyInfo.put("search", getSearch());
		bodyInfo.put("case_sensitive", getCaseSearch());
		WsResponse response = restTemplateLib.getResultWs("/UserEfimDbCompCtl/SensitiveSearch/DOCU", bodyInfo, "post",
				"projectCode=" + getComponentUser().getProjectCode(),"fileStrIdReff="+getComponentUser().getUserSessionCode(),
				"page=1");
		BindUtils.postNotifyChange(null, null, this, "caseSearch");
		getAllResultOfTableContent(response);
	}
	
	@Command("searchOrClick")
	public void searchOrClick() {
		Map<String, Object> bodyInfo = new HashMap<>();
		bodyInfo.put("search", getSearch());
		bodyInfo.put("case_sensitive", getCaseSearch());
		WsResponse response = restTemplateLib.getResultWs("/UserEfimDbCompCtl/SensitiveSearch/DOCU", bodyInfo, "post",
				"projectCode=" + getComponentUser().getProjectCode(),"fileStrIdReff="+getComponentUser().getUserSessionCode(),
				"page=1");
		getAllResultOfTableContent(response);
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

	@Command("mouseOverButtonTrash")
	public void mouseOverButtonTrash(@BindingParam("comp") String comp,
			@ContextParam(ContextType.BIND_CONTEXT) BindContext ctx) {
		InValidFormClass(comp, "pictPanelMouse");
		selectedPict = comp;
		BindUtils.postNotifyChange(null, null, this, "selectedPict");
	}

	@Command("mouseOutButtonTrash")
	public void mouseOutButtonTrash(@BindingParam("comp") String comp,
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

	@Command("mouseOverButtoneye")
	public void mouseOverButtoneye(@BindingParam("comp") String comp,
			@ContextParam(ContextType.BIND_CONTEXT) BindContext ctx) {
		InValidFormClass(comp, "pictPanelMouse");
		selectedPict = comp;
		BindUtils.postNotifyChange(null, null, this, "selectedPict");
	}

	@Command("mouseOutButtoneye")
	public void mouseOutButtoneye(@BindingParam("comp") String comp,
			@ContextParam(ContextType.BIND_CONTEXT) BindContext ctx) {
		ValidFormClass(comp, "pictPanelMouse", "pictPanel");
		selectedPict = "";
		BindUtils.postNotifyChange(null, null, this, "selectedPict");
	}

	@Command("hapus")
	public void hapus(@BindingParam("comp") String comp) {
		forLov(comp,"/lov/HapusLov.zul");
	}
	
	@Command("changeFlag")
	public void changeFlag(@BindingParam("comp") String comp) {
		Map<String, Object> bodyRequest = new HashMap<>(); 
		bodyRequest.put("file_id_reff", comp);
		bodyRequest.put("file_str_id_reff", getComponentUser().getUserSessionCode());
		WsResponse wsResponse = new RestTemplateLib().getResultWs("/PictureCtl/SaveFlagChange", bodyRequest, "Post", "projectCode="+getComponentUser().getProjectCode());
		if(!wsResponse.getIsErrorSvc().booleanValue()) {
			Map<String, Object> mapper = new RestTemplateLib().mapperJsonToHashMap(wsResponse.getWsContent());
			boolean result;
			if (mapper != null) {
				result = (boolean) mapper.get("result");
				if (result) {
					loadList();
				} else {
					showErrorMessageBox("message : " + mapper.get("error"));
				}
			} else {
				showErrorMessageBox("cannot retrieve wsContent");
			}
		}
	}
	
	@Command("saveTitleChange")
	public void saveTitleChange(@BindingParam("comp") String comp) {
		Map<String, Object> bodyRequest = new HashMap<>(); 
		bodyRequest.put("file_id_reff", comp);
		bodyRequest.put("new_file_name", changeName);
		bodyRequest.put("file_str_id_reff", getComponentUser().getUserSessionCode());
		WsResponse wsResponse = new RestTemplateLib().getResultWs("/PictureCtl/SaveTitleChange", bodyRequest, "Post", "projectCode="+getComponentUser().getProjectCode());
		if(!wsResponse.getIsErrorSvc().booleanValue()) {
			Map<String, Object> mapper = new RestTemplateLib().mapperJsonToHashMap(wsResponse.getWsContent());
			boolean result;
			if (mapper != null) {
				result = (boolean) mapper.get("result");
				if (result) {
					loadList();
					changeName = "";
					visibleEdit = false;
					BindUtils.postNotifyChange(null, null, this, "changeName");
					BindUtils.postNotifyChange(null, null, this, "visibleEdit");
				} else {
					showErrorMessageBox("message : " + mapper.get("error"));
				}
			} else {
				showErrorMessageBox("cannot retrieve wsContent");
			}
		}
	}
	
	@Command("edit")
	public void edit(@BindingParam("comp") String comp) {
		visibleEdit = true;
		selectedFileIdReff = comp;
		BindUtils.postNotifyChange(null, null, this, "visibleEdit");
		BindUtils.postNotifyChange(null, null, this, "selectedFileIdReff");
	}
	
	public void forLov(String comp, String url) {
		callLovVmd(url , new MapperLovInformation("file_id_reff", comp), new MapperLovInformation("file_type", DOCU)
				, new MapperLovInformation("file_str_id_reff", getComponentUser().getUserSessionCode()));
	}
	
	@GlobalCommand("TambahLov")
	public void tambahLov(@BindingParam("success") boolean success) {
		if (success) {
			loadList();
		}
	}
	
	@GlobalCommand("HapusLov")
	public void hapusLov(@BindingParam("success") boolean success) {
			loadList();
	}
    
    protected void getAllResultOfTableContent(WsResponse response) {
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
				mapTblEfimDbDto.put(tblEfimDbDto.getFileIdReff(), tblEfimDbDto);
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
		BindUtils.postNotifyChange(null, null, this, "mapTblEfimDbDto");
		// BindUtils.postNotifyChange(null, null, this, "mapPictures");

		// new Thread(new Runnable() {
		// public void run() {
		// boolean cont = true;
		// while (cont) {
		// if (!mapForColumns.isEmpty()) {
		// if (timeCounter <= mapForColumns.size()) {
		// // Map<Integer, TblEfimDbDto> tempTblEfim = mapForColumns.get(timeCounter);
		// for (Entry<Integer, TblEfimDbDto> b :
		// mapForColumns.get(timeCounter).entrySet()) {
		// WsResponse response =
		// restTemplateLib.getResultWs("/UserEfimDbCompCtl/FileStream", null,
		// "get", "projectCode=" + PROJECT,
		// "fileStrIdRef=" + b.getValue().getFileStrIdReff(),
		// "fileIdRef=" + b.getValue().getFileIdReff());
		// TblEfimDbDto efimDbDto = b.getValue();
		// TblEfimFileDbstorageDto tblEfimFileDbstorageDto = new
		// TblEfimFileDbstorageDto();
		// try {
		// tblEfimFileDbstorageDto = restTemplateLib.mapperJsonToSingleDto(
		// response.getWsContent(), TblEfimFileDbstorageDto.class);
		// efimDbDto.setTblEfimFileDbstorageDto(restTemplateLib.mapperJsonToSingleDto(
		// response.getWsContent(), TblEfimFileDbstorageDto.class));
		// mapPictures.put(tblEfimFileDbstorageDto.getFileIdRef(),
		// tblEfimFileDbstorageDto.getFileStr());
		// } catch (Exception e) {
		// e.printStackTrace();
		// }
		// // tempTblEfim.put(b.getKey(), efimDbDto);
		// }
		// // mapForColumns.put(timeCounter, tempTblEfim);
		// timeCounter++;
		// }
		// } else {
		// cont = false;
		// }
		// }
		// }
		// }).start();
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
	public Map<Integer, Map<Integer, TblEfimDbDto>> getMapForColumns() {
		return mapForColumns;
	}
	public void setMapForColumns(Map<Integer, Map<Integer, TblEfimDbDto>> mapForColumns) {
		this.mapForColumns = mapForColumns;
	}
	public Map<String, TblEfimDbDto> getMapTblEfimDbDto() {
		return mapTblEfimDbDto;
	}
	public void setMapTblEfimDbDto(Map<String, TblEfimDbDto> mapTblEfimDbDto) {
		this.mapTblEfimDbDto = mapTblEfimDbDto;
	}
	public Map<String, byte[]> getMapPictures() {
		return mapPictures;
	}
	public void setMapPictures(Map<String, byte[]> mapPictures) {
		this.mapPictures = mapPictures;
	}
	public TblEfimDbDto getTblEfimDbDto() {
		return tblEfimDbDto;
	}
	public void setTblEfimDbDto(TblEfimDbDto tblEfimDbDto) {
		this.tblEfimDbDto = tblEfimDbDto;
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
	public boolean isMoveTimer() {
		return moveTimer;
	}
	public void setMoveTimer(boolean moveTimer) {
		this.moveTimer = moveTimer;
	}
	public String getChangeName() {
		return changeName;
	}
	public void setChangeName(String changeName) {
		this.changeName = changeName;
	}
	public Integer getTimeCounter() {
		return timeCounter;
	}
	public void setTimeCounter(Integer timeCounter) {
		this.timeCounter = timeCounter;
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
	public String getSelectedFileIdReff() {
		return selectedFileIdReff;
	}
	public void setSelectedFileIdReff(String selectedFileIdReff) {
		this.selectedFileIdReff = selectedFileIdReff;
	}
	public boolean isVisibleEdit() {
		return visibleEdit;
	}
	public void setVisibleEdit(boolean visibleEdit) {
		this.visibleEdit = visibleEdit;
	}   
}

package vmd.beranda.transaksi;

import java.io.Serializable;
import java.util.HashMap;

import org.zkoss.bind.BindUtils;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.Init;

import vmd.BaseVmd;

@Init(superclass = true)
public class FileVmd extends BaseVmd implements Serializable {

	private static final long serialVersionUID = -8504551667404664315L;
	private byte[] coolBin;
	private byte[] coolCamera;
	private byte[] coolDocument;
	private byte[] coolSize;
	private byte[] coolVideo;
	private String lblBin;
	private String lblCamera;
	private String lblDocument;
	private String lblVideo;
	private boolean visDocument = true;
	private boolean visVideo = true;
	private boolean visCamera = true;
	private boolean visBin = true;
	private String fileDetail = "";

	@Override
	public void loadList() {
		super.loadList();
		coolBin = getTheFileFileStream("/PictureCtl/GetTheBackgroundPicture", new HashMap<>(), "bin.png",
				"projectCode=" + PROJECT);
		coolCamera = getTheFileFileStream("/PictureCtl/GetTheBackgroundPicture", new HashMap<>(), "camera.jpg",
				"projectCode=" + PROJECT);
		coolDocument = getTheFileFileStream("/PictureCtl/GetTheBackgroundPicture", new HashMap<>(), "document.jpg",
				"projectCode=" + PROJECT);
		coolSize = getTheFileFileStream("/PictureCtl/GetTheBackgroundPicture", new HashMap<>(), "size.jpg",
				"projectCode=" + PROJECT);
		coolVideo = getTheFileFileStream("/PictureCtl/GetTheBackgroundPicture", new HashMap<>(), "video.jpg",
				"projectCode=" + PROJECT);
		lblBin = getCodeInformationInRequest("/Request/MstCodeType", "BIN").getMstCodeTypeName();
		lblCamera = getCodeInformationInRequest("/Request/MstCodeType", "PICT").getMstCodeTypeName();
		lblDocument = getCodeInformationInRequest("/Request/MstCodeType", "DOCU").getMstCodeTypeName();
		lblVideo = getCodeInformationInRequest("/Request/MstCodeType", "VIDEO").getMstCodeTypeName();
	}

	@Command("showDocument")
	public void showDocument() {
		visVideo = false;
		visCamera = false;
		visBin = false;
		fileDetail = "/beranda/transaksi/fileDetail/document.zul";
		BindUtils.postNotifyChange(null, null, this, "visVideo");
		BindUtils.postNotifyChange(null, null, this, "visCamera");
		BindUtils.postNotifyChange(null, null, this, "visBin");
		BindUtils.postNotifyChange(null, null, this, "fileDetail");
	}

	@Command("showGambar")
	public void showGambar() {
		visVideo = false;
		visDocument = false;
		visBin = false;
		fileDetail = "/beranda/transaksi/fileDetail/picture.zul";
		BindUtils.postNotifyChange(null, null, this, "fileDetail");
		BindUtils.postNotifyChange(null, null, this, "visVideo");
		BindUtils.postNotifyChange(null, null, this, "visDocument");
		BindUtils.postNotifyChange(null, null, this, "visBin");
	}

	@Command("showVideo")
	public void showVideo() {
		visCamera = false;
		visDocument = false;
		visBin = false;
		fileDetail = "/beranda/transaksi/fileDetail/video.zul";
		BindUtils.postNotifyChange(null, null, this, "fileDetail");
		BindUtils.postNotifyChange(null, null, this, "visCamera");
		BindUtils.postNotifyChange(null, null, this, "visDocument");
		BindUtils.postNotifyChange(null, null, this, "visBin");
	}

	@Command("showBin")
	public void showBin() {
		visCamera = false;
		visDocument = false;
		visVideo = false;
		fileDetail = "/beranda/transaksi/fileDetail/bin.zul";
		BindUtils.postNotifyChange(null, null, this, "fileDetail");
		BindUtils.postNotifyChange(null, null, this, "visCamera");
		BindUtils.postNotifyChange(null, null, this, "visDocument");
		BindUtils.postNotifyChange(null, null, this, "visVideo");
	}

	public boolean isVisDocument() {
		return visDocument;
	}

	public void setVisDocument(boolean visDocument) {
		this.visDocument = visDocument;
	}

	public boolean isVisVideo() {
		return visVideo;
	}

	public void setVisVideo(boolean visVideo) {
		this.visVideo = visVideo;
	}

	public boolean isVisCamera() {
		return visCamera;
	}

	public void setVisCamera(boolean visCamera) {
		this.visCamera = visCamera;
	}

	public boolean isVisBin() {
		return visBin;
	}

	public void setVisBin(boolean visBin) {
		this.visBin = visBin;
	}

	public byte[] getCoolBin() {
		return coolBin;
	}

	public void setCoolBin(byte[] coolBin) {
		this.coolBin = coolBin;
	}

	public byte[] getCoolCamera() {
		return coolCamera;
	}

	public void setCoolCamera(byte[] coolCamera) {
		this.coolCamera = coolCamera;
	}

	public byte[] getCoolDocument() {
		return coolDocument;
	}

	public void setCoolDocument(byte[] coolDocument) {
		this.coolDocument = coolDocument;
	}

	public byte[] getCoolSize() {
		return coolSize;
	}

	public void setCoolSize(byte[] coolSize) {
		this.coolSize = coolSize;
	}

	public byte[] getCoolVideo() {
		return coolVideo;
	}

	public void setCoolVideo(byte[] coolVideo) {
		this.coolVideo = coolVideo;
	}

	public String getLblBin() {
		return lblBin;
	}

	public void setLblBin(String lblBin) {
		this.lblBin = lblBin;
	}

	public String getLblCamera() {
		return lblCamera;
	}

	public void setLblCamera(String lblCamera) {
		this.lblCamera = lblCamera;
	}

	public String getLblDocument() {
		return lblDocument;
	}

	public void setLblDocument(String lblDocument) {
		this.lblDocument = lblDocument;
	}

	public String getLblVideo() {
		return lblVideo;
	}

	public void setLblVideo(String lblVideo) {
		this.lblVideo = lblVideo;
	}

	public String getFileDetail() {
		return fileDetail;
	}

	public void setFileDetail(String fileDetail) {
		this.fileDetail = fileDetail;
	}

}

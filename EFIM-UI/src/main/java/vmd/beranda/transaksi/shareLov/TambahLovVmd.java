package vmd.beranda.transaksi.shareLov;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.apache.sanselan.ImageReadException;
import org.zkoss.bind.BindContext;
import org.zkoss.bind.BindUtils;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.bind.annotation.Init;
import org.zkoss.util.media.Media;
import org.zkoss.zhtml.Messagebox;
import org.zkoss.zk.ui.event.UploadEvent;
import org.zkoss.zul.Image;
import org.zkoss.zul.Window;

import com.google.protobuf.Message;

import id.co.roxas.efim.common.common.dto.ImageCaptureDto;
import id.co.roxas.efim.common.common.dto.headuser.TblEfimDbDto;
import id.co.roxas.efim.common.common.dto.stream.TblEfimFileDbstorageDto;
import id.co.roxas.efim.common.common.lib.GraphicOverlay2D;
import id.co.roxas.efim.common.webservice.global.WsResponse;
import id.co.roxas.efim.common.webservice.lib.RestTemplateLib;
import vmd.BaseVmd;

@Init(superclass = true)
public class TambahLovVmd extends BaseVmd implements Serializable {

	private static final long serialVersionUID = 3276365232009484683L;
	private byte[] initPic = null;
	private ImageCaptureDto getImage = new ImageCaptureDto();
	private boolean onUpload = true;

	@Command("onPhotoUpload")
	public void onPhotoUpload(@ContextParam(ContextType.BIND_CONTEXT) BindContext ctx) {

		UploadEvent upEvent = null;
		Object objUploadEvent = ctx.getTriggerEvent();
		if (objUploadEvent != null && (objUploadEvent instanceof UploadEvent)) {
			upEvent = (UploadEvent) objUploadEvent;
			Media media = upEvent.getMedia();
			String typeMedia = media.getContentType();
			String[] typeMedias = typeMedia.split("/");
			if (typeMedias[0].equalsIgnoreCase("image")) {
				GraphicOverlay2D overlay2d = new GraphicOverlay2D();
				Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
				getImage = new ImageCaptureDto(media.getByteData(), media.getName());
				int height = (int) (getImage.getHeight());
				int width = (int) (getImage.getWidth());
				int szHeight = (int) (screenSize.height * 0.7);
				int szWidth = (int) (screenSize.width * 0.7);
				int proccessSizeHeight = 0;
				int processSizeWidth = 0;
				if (szHeight >= height) {
					if (szWidth >= width) {
						proccessSizeHeight = height;
						processSizeWidth = width;
					} else {
						proccessSizeHeight = height;
						processSizeWidth = szWidth;
					}
				} else {
					if (szWidth >= width) {
						proccessSizeHeight = szHeight;
						processSizeWidth = width;
					} else {
						proccessSizeHeight = szHeight;
						processSizeWidth = szWidth;
					}
				}

				try {
					initPic = overlay2d.bufferedImageToByte(
							overlay2d.resizingImage(getImage.getByte(), processSizeWidth, proccessSizeHeight));
				} catch (ImageReadException | IOException e) {
					e.printStackTrace();
				}
				onUpload = false;
				InValidFormClass("uploadLov", "upload-button-after-upload");
				InValidFormClass("backLov", "return-button-after-upload");
				InValidFormClass("saveLov", "upload-button-after-upload");
				BindUtils.postNotifyChange(null, null, this, "onUpload");
				BindUtils.postNotifyChange(null, null, this, "initPic");
				BindUtils.postNotifyChange(null, null, this, "getImage");
			} else {
				// showWarningMessageBox(WARNING.W003.getMessage());
			}
		}
	}

	@Command("mouseOverPicture")
	public void mouseOverPicture(@BindingParam("imfo") Image image,
			@ContextParam(ContextType.BIND_CONTEXT) BindContext ctx) {
		// InValidFormClass("uploadLov", "pictPanelMouse");
		ValidFormClass("backLov", "return-button-after-upload", "return-button");
		ValidFormClass("uploadLov", "upload-button-after-upload", "upload-button");
		ValidFormClass("saveLov", "upload-button-after-upload", "upload-button");
		// InValidFormClass("saveLov", "pictPanelMouse");
	}

	@Command("mouseOutPicture")
	public void mouseOutPicture(@BindingParam("imfo") Image image,
			@ContextParam(ContextType.BIND_CONTEXT) BindContext ctx) {
		InValidFormClass("backLov", "return-button-after-upload");
		InValidFormClass("uploadLov", "upload-button-after-upload");
	}

	@Command("kembali")
	public void kembali(@BindingParam("destroy") Window lov) {
		removeCurrentLovInformation("file_type");
		// showErrorMessageBox("nilai : " +
		// getInformationForLov("file_type").getValue());
		lov.detach();
	}

	@Command("savePict")
	public void savePict(@BindingParam("destroy") Window lov) {
		TblEfimDbDto tblEfimDbDto = new TblEfimDbDto();
		TblEfimFileDbstorageDto tblEfimFileDbstorageDto = new TblEfimFileDbstorageDto();
		tblEfimFileDbstorageDto.setFileStr(getImage.getByte());
		tblEfimDbDto.setFileName(getImage.getImageTitle());
		tblEfimDbDto.setFileSize(getImage.getFileSize("kilobyte"));
		tblEfimDbDto.setFileStrIdReff(getComponentUser().getUserSessionCode());
		tblEfimDbDto.setFileOwner(getComponentUser().getUserId());
		tblEfimDbDto.setProjectCode(getComponentUser().getProjectCode());
		tblEfimDbDto.setFileType((String) getInformationForLov("file_type").getValue());
		tblEfimDbDto.setTblEfimFileDbstorageDto(tblEfimFileDbstorageDto);
		WsResponse wsResponse = new RestTemplateLib().getResultWs("/TambahCompCtl/Save", tblEfimDbDto, "post", null);
		if (!wsResponse.getIsErrorSvc().booleanValue()) {
			Map<String, Object> mapper = new RestTemplateLib().mapperJsonToHashMap(wsResponse.getWsContent());
			boolean result;
			if (mapper != null) {
				result = (boolean) mapper.get("result");

				if (result) {
					Map<String, Object> args = new HashMap<>();
					args.put("success", true);
					BindUtils.postGlobalCommand(null, null, "TambahLov", args);
				} else {
					showErrorMessageBox("message : " + mapper.get("error"));
				}
			} else {
				showErrorMessageBox("cannot retrieve wsContent");
			}
		}
		else {
			showErrorMessageBox("error : " + wsResponse.getErrorCmd());
		}
		
	
		
        lov.detach();
	}

	@Override
	public void loadList() {
		super.loadList();
		BindUtils.postNotifyChange(null, null, this, "getImage");
	}

	public byte[] getInitPic() {
		return initPic;
	}

	public void setInitPic(byte[] initPic) {
		this.initPic = initPic;
	}

	public ImageCaptureDto getGetImage() {
		return getImage;
	}

	public void setGetImage(ImageCaptureDto getImage) {
		this.getImage = getImage;
	}

	public boolean isOnUpload() {
		return onUpload;
	}

	public void setOnUpload(boolean onUpload) {
		this.onUpload = onUpload;
	}

}

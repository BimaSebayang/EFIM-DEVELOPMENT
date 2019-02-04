package vmd.beranda.transaksi.share.lov;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.IOException;
import java.io.Serializable;
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
import id.co.roxas.efim.common.common.lib.GraphicOverlay2D;
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
					//Messagebox.show("panjang x lebar : " + processSizeWidth + " " + proccessSizeHeight );
					initPic = overlay2d.bufferedImageToByte(
							overlay2d.resizingImage(getImage.getByte(), processSizeWidth, proccessSizeHeight));
				} catch (ImageReadException | IOException e) {
					e.printStackTrace();
				}
				//changeDisplayCss("imageT", Integer.toString(processSizeWidth), Integer.toString(proccessSizeHeight));
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
	public void mouseOverPicture(@BindingParam("imfo") Image image,@ContextParam(ContextType.BIND_CONTEXT) BindContext ctx){
		//InValidFormClass("uploadLov", "pictPanelMouse");
		ValidFormClass("backLov", "return-button-after-upload","return-button");
		ValidFormClass("uploadLov", "upload-button-after-upload","upload-button");
		ValidFormClass("saveLov", "upload-button-after-upload","upload-button");
		//InValidFormClass("saveLov", "pictPanelMouse");
	}
	
	@Command("mouseOutPicture")
	public void mouseOutPicture(@BindingParam("imfo") Image image,@ContextParam(ContextType.BIND_CONTEXT) BindContext ctx){
		InValidFormClass("backLov", "return-button-after-upload");
		InValidFormClass("uploadLov", "upload-button-after-upload");
//		InValidFormClass("backLov", "pictPanelMouse");
//		InValidFormClass("saveLov", "pictPanelMouse");
	}
	
	
	@Command("kembali")
	public void pilih(@BindingParam("destroy")  Window lov){
		removeCurrentLovInformation("file_type");
		//showErrorMessageBox("nilai : " + getInformationForLov("file_type").getValue());
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

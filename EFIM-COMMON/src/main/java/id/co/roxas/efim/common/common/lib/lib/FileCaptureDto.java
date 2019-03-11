package id.co.roxas.efim.common.common.lib.lib;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;

public class FileCaptureDto {
	private byte[] imageByte;
	private String imageTitle;

	public FileCaptureDto() {
	}

	public FileCaptureDto(byte[] imageByte, String imageTitle) {
		this.imageByte = imageByte;
		this.imageTitle = imageTitle;
	}

	public byte[] getByte() {
		return imageByte;
	}

	public double getFileSize(String powerConversion) {
		try {
			FileUtils.writeByteArrayToFile(new File("pathname"), imageByte);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		File file = new File("pathname");
		Long specificSize = file.length();
		double bytes = specificSize;
		double kilobytes = (bytes / 1024);
		double megabytes = (kilobytes / 1024);
		double gigabytes = (megabytes / 1024);
		double terabytes = (gigabytes / 1024);
		if (powerConversion.equalsIgnoreCase("byte")) {
			return bytes;
		} else if (powerConversion.equalsIgnoreCase("kilobyte")) {
			return kilobytes;
		} else if (powerConversion.equalsIgnoreCase("megabyte")) {
			return megabytes;
		} else if (powerConversion.equalsIgnoreCase("gigabyte")) {
			return gigabytes;
		} else if (powerConversion.equalsIgnoreCase("terabyte")) {
			return terabytes;
		} else {
			return 0.0;
		}
	}

	public int getHeight() {
		try {
			ByteArrayInputStream bisImage = new ByteArrayInputStream(imageByte);
			Image image = null;
			try {
				image = ImageIO.read(bisImage);
			} catch (IOException e) {
				System.err.println("File Ini Mengandung CMYK " + bisImage);
				e.printStackTrace();
			}
			return image.getHeight(null);
		} catch (NullPointerException nep) {
			nep.printStackTrace();
			return 0;
		}
	}

	public int getWidth() {
		try {
			ByteArrayInputStream bisImage = new ByteArrayInputStream(imageByte);
			Image image = null;
			try {
				image = ImageIO.read(bisImage);
			} catch (IOException e) {
				System.err.println("File Ini Mengandung CMYK " + bisImage);
				e.printStackTrace();
			}
			return image.getWidth(null);
		} catch (NullPointerException nep) {
			nep.printStackTrace();
			return 0;
		}
	}

	public BufferedImage getBuffImage() {
		try {
			return ImageIO.read(new ByteArrayInputStream(imageByte));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public byte[] getImageByte() {
		return imageByte;
	}

	public String getImageTitle() {
		return imageTitle;
	}

}

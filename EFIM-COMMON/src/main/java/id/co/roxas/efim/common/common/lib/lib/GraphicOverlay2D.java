package id.co.roxas.efim.common.common.lib.lib;

import java.awt.BasicStroke;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLConnection;
import java.nio.file.Files;
import java.util.List;

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.extractor.WordExtractor;
import org.apache.poi.hwpf.usermodel.Range;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.sanselan.ImageReadException;

import com.lowagie.text.Document;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfWriter;

import id.co.roxas.efim.common.common.lib.dto.FileCaptureDto;

public class GraphicOverlay2D {
	// public BufferedImage overLayImages

	public static String detectMimeType(byte[] fileStream) {
		InputStream is = new ByteArrayInputStream(fileStream);
		String mimeType = null;
		try {
			mimeType = URLConnection.guessContentTypeFromStream(is);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return mimeType;
	}
	
	public byte[] changePdfFileToPicture(byte[] pdfFile) throws IOException {
		byte[] finalImage = null;
		File outputfile = null;
		FileUtils.writeByteArrayToFile(new File("pathname"), pdfFile);
		try {
			BufferedImage image = null;
			File sourceFile = new File("pathname");
			if (sourceFile.exists()) {
				PDDocument document = PDDocument.load(sourceFile);
				List<PDPage> page = document.getDocumentCatalog().getAllPages();
				image = page.get(0).convertToImage();
				outputfile = new File("dokumenpath");
				ImageIO.write(image, "png", outputfile);
				document.close();
			} else {
				System.err.println(sourceFile.getName() + " File not exists");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(outputfile!=null) {
		finalImage = Files.readAllBytes(outputfile.toPath());
		}
		else {
			finalImage = null;
		}
		return finalImage;
	}

	public byte[] bufferedImageToByte(BufferedImage image) {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		byte[] finalImage = null;
		try {
			ImageIO.write(image, "jpg", baos);
			baos.flush();
			finalImage = baos.toByteArray();
			baos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return finalImage;
	}

	/**
	 * Melakukan overlay dengan background yang sama
	 * 
	 * @param bg
	 * @param fg
	 * @param coorX
	 * @param coorY
	 * @return
	 */
	public BufferedImage overLayImagesWithSpecificCoordinate(BufferedImage bg, BufferedImage fg, float coorX,
			float coorY) {
		if (fg.getHeight() > bg.getHeight()) {
			// Messagebox.show("picture foreground lebih besar dari background");
			return null;
		}

		BufferedImage biFinal = new BufferedImage(450, 400, BufferedImage.TYPE_INT_RGB);
		Graphics2D g = biFinal.createGraphics();
		g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
		g.drawImage(bg, 0, 0, null);
		g.setStroke(new BasicStroke(100));
		g.drawImage(fg, ((int) coorX) + 6 - ((int) (fg.getWidth() / 2)), ((int) coorY - fg.getHeight() + 8), null);
		g.dispose();
		return biFinal;
	}

	/**
	 * Melakukan overlay dengan background yang sama
	 * 
	 * @param bg
	 * @param fg
	 * @param coorX
	 * @param coorY
	 * @return
	 */
	public BufferedImage overLayImagesWithSpecificCoordinateNew(FileCaptureDto bg, FileCaptureDto fg, float coorX,
			float coorY) {
		if (fg.getHeight() > bg.getHeight()) {
			// Messagebox.show("picture foreground lebih besar dari background");
			return null;
		}

		BufferedImage biFinal = new BufferedImage(bg.getWidth(), bg.getHeight(), BufferedImage.TYPE_INT_RGB);
		Graphics2D g = biFinal.createGraphics();
		g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
		g.drawImage(bg.getBuffImage(), 0, 0, null);
		g.setStroke(new BasicStroke(100));
		g.drawImage(fg.getBuffImage(), ((int) coorX) - ((int) (fg.getWidth() / 2)), ((int) coorY - fg.getHeight()),
				null);
		g.dispose();
		return biFinal;
	}

	public BufferedImage resizingImage(byte[] bitConvert, int newWidth, int newHeight)
			throws ImageReadException, IOException {
		ByteArrayInputStream bisImage = new ByteArrayInputStream(bitConvert);
		Image image = null;
		try {
			image = ImageIO.read(bisImage);
		} catch (IOException e) {
			// Messagebox.show("File Ini Mengandung CMYK " + bisImage);
			e.printStackTrace();
		}

		Image scaledImage = image.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH);

		if (scaledImage instanceof BufferedImage) {
			return (BufferedImage) scaledImage;
		}
		BufferedImage bufferedImage = new BufferedImage(scaledImage.getWidth(null), scaledImage.getHeight(null),
				BufferedImage.TYPE_INT_RGB);
		Graphics2D bgr = bufferedImage.createGraphics();
		bgr.drawImage(scaledImage, 0, 0, null);
		bgr.dispose();
		return bufferedImage;
	}
}

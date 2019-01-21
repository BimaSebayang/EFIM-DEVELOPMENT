package tester;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ByteReference {
   
	public static void main(String[] args) throws IOException {
	  
		BufferedImage imm = ImageIO.read(new File("d:/acea7151be6fe74084457cfd04b85e78.jpg"));
		byte[] immAsByte = null;
	    ByteArrayOutputStream baos = new ByteArrayOutputStream();
	    ImageIO.write(imm, "jpg", baos);
	    baos.flush();
	    immAsByte = baos.toByteArray();
	    baos.close();
	    System.err.println("bytenya " + immAsByte);
	}
	
}

package tester.dao;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

import javax.imageio.ImageIO;

import org.apache.commons.codec.binary.Base64;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import dao.ProcedureDao;
import dao.headuser.TblPictureFrontEndDao;
import entity.stream.TblPictureFrontEnd;

public class ByteReference {
   
	public static void main(String[] args) throws IOException {
	  
		BufferedImage imm = ImageIO.read(new File("d:/astronomy-night_sky-fish_eye-moon-457213795.jpg"));
		byte[] immAsByte = null;
	    ByteArrayOutputStream baos = new ByteArrayOutputStream();
	    ImageIO.write(imm, "jpg", baos);
	    baos.flush();
	    immAsByte = baos.toByteArray();
	    baos.close();
	    System.err.println("bytenya " + immAsByte);
		
		ApplicationContext ctx = new ClassPathXmlApplicationContext("/META-INF/spring/app-config.xml");
        //TblDataUserDao dao = ctx.getBean(TblDataUserDao.class);
        TblPictureFrontEndDao pd = ctx.getBean(TblPictureFrontEndDao.class);
//        ImageUserPhoto imageUserPhoto = pd.getImage();
//        String h = Base64.encodeBase64String(imageUserPhoto.getImageFile());
//        System.err.println(h);
        
        
        
//        TblPictureFrontEnd iup = new TblPictureFrontEnd();
//        UUID uid = UUID.randomUUID();
//        iup.setPhotoId(uid);
//        iup.setImageFile(immAsByte);
//        pd.save(iup);
//       
	}
	
}

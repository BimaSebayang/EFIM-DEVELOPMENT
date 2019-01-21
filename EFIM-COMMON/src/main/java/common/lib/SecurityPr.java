package common.lib;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;

public class SecurityPr {
   private String macAddress;
   private String serialNumber;
   private String ipAddress;

   
   public String getMacAddress() {
	  
//	   InetAddress ip = null ;
//	      try {
//		  ip = InetAddress.getLocalHost();
//	      } catch (UnknownHostException e) {
//		  // TODO Auto-generated catch block
//		  e.printStackTrace();
//	      }
//	   
//	   NetworkInterface networkInterface = null;
//	      byte[] mac = null;
//	      try {
//			networkInterface = NetworkInterface.getByInetAddress(ip);
//			mac = networkInterface.getHardwareAddress();
//		  } catch (SocketException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		  }
//	      
//	      StringBuilder sb = new StringBuilder();
//	      for (int i = 0; i < mac.length; i++) {
//			  sb.append(mac[i]);
//		  }
//	      
	      
	    return macAddress = "Testing Mac"; 
   }

   public String getSerialNumber() {
//	   String result = null;
//	      Process p  = null;
//	      try {
//			p = Runtime.getRuntime().exec("wmic baseboard get serialnumber");
//		  } catch (IOException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		  }
//	      
//	      BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream()));
//	      String line;
//	      try {
//			while ((line = br.readLine()) != null) {
//			    if(!line.equalsIgnoreCase("nullSerialNumber".trim())) {
//				result += line + "@";
//			    }
//			  }
//		   } catch (IOException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		   }
//	        
//	      String[] splitter = result.split("@");
	    return serialNumber = "Testing Serial Number";
   }
  
   public String getIpAddress() {
	   InetAddress ip = null ;
	      try {
		  ip = InetAddress.getLocalHost();
	      } catch (UnknownHostException e) {
		  // TODO Auto-generated catch block
		  e.printStackTrace();
	      }   
	   
	   return ipAddress=ip.getHostAddress();
   }

 
   
}

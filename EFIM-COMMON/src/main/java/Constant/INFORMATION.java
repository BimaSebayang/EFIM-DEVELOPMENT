package Constant;

import java.util.HashMap;
import java.util.Map;

public class INFORMATION {

	private String message = "";
	
	
	public INFORMATION(String message) {
		Map<String, String> map = new HashMap<>();
		map.put("I001", "Mohon Maaf User Tidak Terdaftar");
		map.put("I002", "Mohon Maaf Password Masih Salah");
		map.put("I003", "Mohon Maaf User Ini Telah Diblokir");
		map.put("I004", "Login Berhasil");
		map.put("I005", "Mohon Maaf User Ini Sedang Digunakan");
		
		this.message = map.get(message);
	}
	
	public String getMessage() {
		return message;
	}
}

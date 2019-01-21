package vmd;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.ws.rs.core.MultivaluedMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.zkoss.bind.annotation.Init;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Messagebox.Button;

import com.thoughtworks.xstream.core.util.Base64Encoder;

import Constant.INFORMATION;
import common.dto.master.TblCodeDto;
import webservice.global.WsResponse;
import webservice.lib.RestTemplateLib;

@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class BaseVmd extends BaseComponent implements Serializable {
	protected static final String PROJECT = "EFIM";
	public final Integer DIV = 4;
	protected String search = "";
	protected static int PAGE = 1;
	protected static String SORT = "ASC";
	protected static String ORDERBY = "";
	private static final long serialVersionUID = -8547420080756422847L;
	private final String VARIABLE = "http://localhost:8080/EFIM-CORE/";
	protected byte[] coolBackground;
	protected byte[] coolLogo;
	protected RestTemplateLib restTemplateLib = new RestTemplateLib();

	public boolean toWelcomePage() {
		if (getComponentUser() == null) {
			sendMeToOtherPage("/");
			return true;
		}
		return false;
	}

	public String[] paramPaging(String projectCode, Integer page, String sort, String orderBy) {
		String[] paging = new String[] {};
		try {
		if (projectCode == null) {
			projectCode = PROJECT;
		}
		if (page == null) {
			page = new Integer(1);
		}
		if (sort == null) {
			sort = SORT;
		}
		if (orderBy == null) {
			orderBy = ORDERBY;
		}
		paging = new String[] { "projectCode=" + projectCode, "page=" + page.intValue(), "direction=" + sort,
				"orderBy=" + orderBy };
		}catch(NullPointerException nep) {
			nep.printStackTrace();
		}
		return paging;
	}

	public TblCodeDto getCodeInformationInRequest(String url, String... requests) {
		WsResponse wsResponse = new WsResponse();
		if (requests.length == 1) {
			wsResponse = restTemplateLib.getResultWs("/CustomCodeCtl" + url, requests[0], "Post");
		}

		TblCodeDto tblCodeDto = null;

		try {
			tblCodeDto = restTemplateLib.mapperJsonToSingleDto(wsResponse.getWsContent(), TblCodeDto.class);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return tblCodeDto;

	}

	@Init(superclass = true)
	public void loadList() {

		coolBackground = getTheFileFileStream("/PictureCtl/GetTheBackgroundPicture", new HashMap<>(), "EFIMBG.png",
				"projectCode=" + PROJECT);
		coolLogo = getTheFileFileStream("/PictureCtl/GetTheBackgroundPicture", new HashMap<>(), "EFIMLOGO.png",
				"projectCode=" + PROJECT);
		// coolLogo = getTheFile("\\BG", "EFIMLOGO.png", "jpg");
		// coolBackground = getTheFile("\\BG", "EFIMBG.png", "jpg");
	}

	protected String getCommonConstant(String message) {
		String finalMessage = "";
		String subMess = message.substring(0, 1);
		if (subMess.equalsIgnoreCase("I")) {
			INFORMATION info = new INFORMATION(message);
			finalMessage = info.getMessage();
		}

		return finalMessage;
	}

	// For Call ShowMessageBox --start
	protected void showErrorMessageBox(String argument) {
		Map<String, String> params = new HashMap<>();
		params.put("sclass", "myMessagebox");
		Messagebox.show(argument, "Kesalahan", new Button[] { Button.OK }, null, Messagebox.ERROR, null, null, params);
	}

	protected void showWarningMessageBox(String argument) {
		Map<String, String> params = new HashMap();
		params.put("sclass", "myMessagebox");
		Messagebox.show(argument, "Peringatan", new Button[] { Button.OK }, null, Messagebox.EXCLAMATION, null, null,
				params);

	}

	protected void showInformationMessageBox(String argument) {
		Map<String, String> params = new HashMap();
		params.put("sclass", "myMessagebox");
		Messagebox.show(argument, "Informasi", new Button[] { Button.OK }, null, Messagebox.INFORMATION, null, null,
				params);
	}
	// For Call ShowMessageBox --end

	public String saveFile(byte[] media, String uri, String titleFile, String typeFile) {

		RestTemplate restTemplate = new RestTemplate();
		Base64Encoder encoder = new Base64Encoder();
		String encoderMedia = encoder.encode(media);

		Map<String, Object> mapRe = new HashMap<>();
		mapRe.put("title", titleFile);
		mapRe.put("encoder", encoderMedia);
		mapRe.put("uri", uri);
		HttpEntity httpEntity = new HttpEntity(mapRe);
		if (typeFile.equalsIgnoreCase("jpg")) {
			ResponseEntity<String> entity = restTemplate.exchange(VARIABLE + "PictureCtl/SaveImage", HttpMethod.POST,
					httpEntity, String.class);
			return entity.getBody();
		} else if (typeFile.equalsIgnoreCase("pdf")) {
			ResponseEntity<String> entity = restTemplate.exchange(VARIABLE + "filingSystemPdf/savePdf", HttpMethod.POST,
					httpEntity, String.class);
			return entity.getBody();
		} else if (typeFile.equalsIgnoreCase("excel")) {

		} else if (typeFile.equalsIgnoreCase("txt")) {

		} else if (typeFile.equalsIgnoreCase("word")) {

		}

		return "ERROR WHILE PARSING";
	}

	public String deleteFile(String uri, String titleFile, String typeFile) {
		RestTemplate restTemplate = new RestTemplate();
		Map<String, Object> mapRe = new HashMap<>();
		mapRe.put("title", titleFile);
		mapRe.put("uri", uri);
		HttpEntity httpEntity = new HttpEntity(mapRe);
		if (typeFile.equalsIgnoreCase("jpg")) {
			ResponseEntity<String> entity = restTemplate.exchange(VARIABLE + "PictureCtl/DeleteImage", HttpMethod.POST,
					httpEntity, String.class);
			return entity.getBody();
		} else if (typeFile.equalsIgnoreCase("pdf")) {
			ResponseEntity<String> entity = restTemplate.exchange(VARIABLE + "filingSystemPdf/deletePdf",
					HttpMethod.POST, httpEntity, String.class);
			return entity.getBody();
		} else if (typeFile.equalsIgnoreCase("excel")) {

		} else if (typeFile.equalsIgnoreCase("txt")) {

		} else if (typeFile.equalsIgnoreCase("word")) {

		}
		return "ERROR WHILE PARSING";
	}

	public String replace(byte[] media, String uri, String oldTitleFile, String newTitleFile, String typeFile) {
		RestTemplate restTemplate = new RestTemplate();
		Base64Encoder encoder = new Base64Encoder();
		String encoderMedia = encoder.encode(media);
		Map<String, Object> mapRe = new HashMap<>();
		mapRe.put("oldTitle", oldTitleFile);
		mapRe.put("newTitle", newTitleFile);
		mapRe.put("encoder", encoderMedia);
		mapRe.put("uri", uri);
		HttpEntity httpEntity = new HttpEntity(mapRe);
		if (typeFile.equalsIgnoreCase("jpg")) {
			ResponseEntity<String> entity = restTemplate.exchange(VARIABLE + "PictureCtl/ReplaceImage", HttpMethod.POST,
					httpEntity, String.class);
			return entity.getBody();
		} else if (typeFile.equalsIgnoreCase("pdf")) {
			ResponseEntity<String> entity = restTemplate.exchange(VARIABLE + "filingSystemPdf/replacePdf",
					HttpMethod.POST, httpEntity, String.class);
			return entity.getBody();
		} else if (typeFile.equalsIgnoreCase("excel")) {

		} else if (typeFile.equalsIgnoreCase("txt")) {

		} else if (typeFile.equalsIgnoreCase("word")) {

		}
		return "ERROR WHILE PARSING";
	}

	public byte[] getTheFileFileStream(String url, Map<String, Object> header, Object body, String... paramQuery) {
		String finalparamQuery = "";
		if (paramQuery != null) {
			if (paramQuery.length != 0) {
				if (paramQuery.length != 0) {
					finalparamQuery = "?";
					for (int i = 0; i < paramQuery.length; i++) {
						finalparamQuery += paramQuery[i];
						if (i < paramQuery.length) {
							finalparamQuery += "&";
						}
					}
				}
			}
		}

		MultiValueMap<String, Object> mapp = new LinkedMultiValueMap();

		for (Entry<String, Object> head : header.entrySet()) {
			mapp.add(head.getKey(), head.getValue());
		}
		RestTemplate restTemplate = new RestTemplate();
		HttpEntity httpEntity = new HttpEntity(body, mapp);
		ResponseEntity<byte[]> entity = restTemplate.exchange(VARIABLE + url + finalparamQuery, HttpMethod.POST,
				httpEntity, byte[].class);
		return entity.getBody();
	}

	public byte[] getTheFile(String uri, String titleFile, String typeFile) {
		Map<String, Object> mapRe = new HashMap<>();
		mapRe.put("title", titleFile);
		mapRe.put("uri", uri);
		RestTemplate restTemplate = new RestTemplate();
		HttpEntity httpEntity = new HttpEntity(mapRe);
		if (typeFile.equalsIgnoreCase("jpg")) {
			ResponseEntity<byte[]> entity = restTemplate.exchange(VARIABLE + "PictureCtl/GetSpecificImage",
					HttpMethod.POST, httpEntity, byte[].class);
			return entity.getBody();
		} else if (typeFile.equalsIgnoreCase("pdf")) {
			ResponseEntity<byte[]> entity = restTemplate.exchange(VARIABLE + "filingSystemPdf/getSpecificPdf",
					HttpMethod.POST, httpEntity, byte[].class);
			return entity.getBody();
		} else if (typeFile.equalsIgnoreCase("excel")) {

		} else if (typeFile.equalsIgnoreCase("txt")) {

		} else if (typeFile.equalsIgnoreCase("word")) {

		}

		byte[] a = null;
		return a;
	}

	public boolean isEmailValid(String wholeText) {
		String regex = "^[A-Za-z0-9+_.-]+@(.+)$";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(wholeText);
		if (!matcher.matches()) {
			return true;
		}
		return false;
	}

	public boolean isPasswordValid(String wholeText) {
		boolean isValid = false;

		char[] wtInChar = wholeText.toCharArray();

		int countUpper = 0;
		int countLower = 0;
		int countNumber = 0;
		int countSpecialCharacter = 0;

		for (char c : wtInChar) {
			char compChar = c;
			int asciiChar = (int) compChar;
			if (asciiChar >= 65 && asciiChar <= 89) {
				countUpper++;
			}
			if (asciiChar >= 97 && asciiChar <= 122) {
				countLower++;
			}
			if (asciiChar >= 48 && asciiChar <= 57) {
				countNumber++;
			}
			if ((asciiChar >= 33 && asciiChar <= 47) || (asciiChar >= 58 && asciiChar <= 64)
					|| (asciiChar >= 91 && asciiChar <= 96) || (asciiChar >= 123 && asciiChar <= 126)) {
				countSpecialCharacter++;
			}
		}

		if (countUpper >= 1 && countLower >= 1 && countNumber >= 1 && countSpecialCharacter >= 1) {
			isValid = true;
		}

		return isValid;
	}

	public boolean isPhoneValid(String wholeText) {
		String phoneValidation = wholeText;
		System.err.println("phone validasinya : " + phoneValidation);
		int j = 0;

		String[] finalPhone = phoneValidation.split(" ");

		String kalPertama = phoneValidation.substring(0, 1);
		System.err.println("kata pertama : " + kalPertama);
		if (!kalPertama.equalsIgnoreCase("0")) {
			return isPhoneValidFor64(phoneValidation);
		}

		int qrCount = phoneValidation.toCharArray().length - 1;
		System.err.println("banyak angka : " + qrCount);
		if (qrCount < 10 || qrCount > 14) {
			return false;
		}

		String lfp = "";
		for (String fp : finalPhone) {
			lfp += fp;
		}
		wholeText = lfp;
		while (j < wholeText.toCharArray().length) {
			String charVal = wholeText.substring(j, j + 1);
			System.err.print(charVal + " ");
			if (charVal.equals("1") || charVal.equals("2") || charVal.equals("3") || charVal.equals("4")
					|| charVal.equals("5") || charVal.equals("6") || charVal.equals("7") || charVal.equals("8")
					|| charVal.equals("9") || charVal.equals("0")) {
			} else {
				System.out.println("huruf " + charVal + " bukan angka!");
				return false;
			}
			j++;
		}
		return true;
	}

	public static boolean isPhoneValidFor64(String wholeText) {
		String phoneValidation = wholeText;
		System.err.println("phone validasinya : " + phoneValidation);
		int j = 3;

		String[] finalPhone = phoneValidation.split(" ");

		String kalPertama = wholeText.substring(0, 3);
		System.err.println("kata pertama : " + kalPertama);
		if (!kalPertama.equalsIgnoreCase("+62")) {
			return false;
		}

		int qrCount = wholeText.toCharArray().length - 1;
		if (qrCount < 12 || qrCount > 16) {
			return false;
		}

		String lfp = "";
		for (String fp : finalPhone) {
			lfp += fp;
		}
		wholeText = lfp;
		while (j < wholeText.toCharArray().length) {
			String charVal = wholeText.substring(j, j + 1);
			System.err.print(charVal + " ");
			if (charVal.equals("1") || charVal.equals("2") || charVal.equals("3") || charVal.equals("4")
					|| charVal.equals("5") || charVal.equals("6") || charVal.equals("7") || charVal.equals("8")
					|| charVal.equals("9") || charVal.equals("0")) {
			} else {
				System.out.println("huruf " + charVal + " bukan angka!");
				return false;
			}
			j++;
		}
		return true;
	}

	public void InValidFormClass(String idName, String cssName) {
		String si = "valid_FormClass('" + idName + "','" + cssName + "')";
		Clients.evalJavaScript(si);
	}

	public void ValidFormClass(String idName, String cssName, String lastCss) {
		String s = "invalid_FormClass('" + idName + "','" + cssName + "')";
		Clients.evalJavaScript(s);
		String si = "valid_FormClass('" + idName + "','" + lastCss + "')";
		Clients.evalJavaScript(s);
	}

	public void callConstraint(String idName) {
		String s = "call_constraint('" + idName + "')";
		Clients.evalJavaScript(s);
	}

	public byte[] getCoolBackground() {
		return coolBackground;
	}

	public void setCoolBackground(byte[] coolBackground) {
		this.coolBackground = coolBackground;
	}

	public byte[] getCoolLogo() {
		return coolLogo;
	}

	public void setCoolLogo(byte[] coolLogo) {
		this.coolLogo = coolLogo;
	}

	public String getSearch() {
		return search;
	}

	public void setSearch(String search) {
		this.search = search;
	}

}

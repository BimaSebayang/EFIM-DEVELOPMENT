package vmd.portal;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.zkoss.bind.BindUtils;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.impl.BinderUtil;
import org.zkoss.lang.Strings;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.WrongValueException;
import org.zkoss.zul.Constraint;
import org.zkoss.zul.Messagebox;

import Constant.INFORMATION;
import common.dto.UserPrivilegeCustom;
import common.dto.headuser.TblDataUserDto;
import common.lib.EmailPr;
import vmd.BaseVmd;
import webservice.global.WsResponse;

@Init(superclass = true)
public class LoginVmd extends BaseVmd {
	private static final long serialVersionUID = 9132739999799555344L;
	private String userId;
	private String inputUserId;
	private String inputPassword;

	private String registerUserId;
	private String registerUserName;
	private String registerUserPassword;
	private String confirmRegisterUserPassword;
	private String registerUserMail;
	private String registerUserPhone;
	private String forgetUserId;

	private boolean onLogin = true;
	private boolean onRegister = false;
	private boolean onForgot = false;

	@Command("forgot")
	public void forgot() {
		WsResponse wsResponse = restTemplateLib.getResultWs("/ForgotPassCtl/GetEmail", getForgetUserId(), "post",
				"projectCode=" + PROJECT);
		TblDataUserDto information = new TblDataUserDto();
		try {
			information = restTemplateLib.mapperJsonToSingleDto(wsResponse.getWsContent(), TblDataUserDto.class);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (information.getUserMail().equalsIgnoreCase("ERROR")) {
			Messagebox.show(new INFORMATION("I001").getMessage());
			return;
		} else {

			TblDataUserDto dto = new TblDataUserDto();
			dto.setUserName(information.getUserName());
			dto.setUserId(information.getUserId());
			dto.setUserMail(information.getUserMail());
			Map<String, Object> resultMap = new HashMap<>();
			resultMap = restTemplateLib.mapperJsonToHashMap(wsResponse.getWsContent());
			String idNo = (String) resultMap.get("idNo");

			String html = " <p> Dear " + information.getUserName() + ", <br>"
					+ " Kami mendengar bahwa anda baru saja kehilangan password anda. Kami kirimkan link untuk "
					+ " dapat mereset password yang anda punya sekarang </p>"
					+ " <p>Anda dapat mengklik button dibawah ini untuk dapat langsung mereset password anda </p>"
					+ " <a href='http://localhost:28080/EFIM-UI/forgot/forgotPr.zul?id=" 
					+ "'><button>Reset Your Password</button></a>"
					+ " <p>Anda dapat mengabaikan pesan ini jika tidak ingin melanjutkan <br>" + "    Best Regard,</p>"
					+ " <br>" + " <p> <b>Notes : Pesan ini akan kadaluarsa dalam 3 hari</b> </p>";
			
			String subject = "Reset Password Akun EFIM";
			
			dto.setSubjecEmail(subject);
			dto.setMessageEmail(html);
			
			WsResponse wsResponse2 = restTemplateLib.getResultWs("/LoginCtl/CreateUserEmail", dto, "post", "projectCode=" + PROJECT);
			String responseFinal = null;
			try {
				responseFinal = restTemplateLib.mapperJsonToSingleDto(wsResponse2.getWsContent(), String.class);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			String html2 = " <p> Dear " + information.getUserName() + ", <br>"
					+ " Kami mendengar bahwa anda baru saja kehilangan password anda. Kami kirimkan link untuk "
					+ " dapat mereset password yang anda punya sekarang </p>"
					+ " <p>Anda dapat mengklik button dibawah ini untuk dapat langsung mereset password anda </p>"
					+ " <a href='http://localhost:28080/EFIM-UI/forgot/forgotPr.zul?id=" + responseFinal
					+ "'><button>Reset Your Password</button></a>"
					+ " <p>Anda dapat mengabaikan pesan ini jika tidak ingin melanjutkan <br>" + "    Best Regard,</p>"
					+ " <br>" + " <p> <b>Notes : Pesan ini akan kadaluarsa dalam 3 hari</b> </p>";
			       showInformationMessageBox(new EmailPr().sendMail(information.getUserMail(), html2,
					"Reset Password Akun EFIM", "Mohon Buka email anda untuk dapat mereset password anda."));
		}

	}

	@Command("headLogin")
	public void headLogin() {
		onLogin = true;
		onRegister = false;
		onForgot = false;
		
		registerUserId = "";
		registerUserName = "";
		registerUserPassword = "";
		confirmRegisterUserPassword = "";
		registerUserMail = "";
		registerUserPhone = "";
		forgetUserId = "";
		
		ValidFormClass("inputUser", "text-boxWrongValue", "text-box");
		ValidFormClass("forgetUserId", "text-boxWrongValue", "text-box");
		ValidFormClass("inputPassword", "text-boxWrongValue", "text-box");
		ValidFormClass("registerUserName", "text-boxWrongValue", "text-box");
			ValidFormClass("userMail", "text-boxWrongValue", "text-box");
		ValidFormClass("userPassword", "text-boxWrongValue", "text-box");
		ValidFormClass("userPhone", "text-boxWrongValue", "text-box");
			ValidFormClass("registerUserId", "text-boxWrongValue", "text-box");
		
		BindUtils.postNotifyChange(null, null, this, "registerUserId");
		BindUtils.postNotifyChange(null, null, this, "registerUserName");
		BindUtils.postNotifyChange(null, null, this, "registerUserPassword");
		BindUtils.postNotifyChange(null, null, this, "confirmRegisterUserPassword");
		BindUtils.postNotifyChange(null, null, this, "registerUserMail");
		BindUtils.postNotifyChange(null, null, this, "registerUserPhone");
		BindUtils.postNotifyChange(null, null, this, "forgetUserId");
		BindUtils.postNotifyChange(null, null, this, "onLogin");
		BindUtils.postNotifyChange(null, null, this, "onRegister");
		BindUtils.postNotifyChange(null, null, this, "onForgot");
	}

	@Command("headRegister")
	public void headRegister() {
		onLogin = false;
		onRegister = true;
		onForgot = false;
		userId = "";
		inputUserId = "";
	    inputPassword = "";
		forgetUserId = "";
		
		ValidFormClass("inputUser", "text-boxWrongValue", "text-box");
		ValidFormClass("forgetUserId", "text-boxWrongValue", "text-box");
		ValidFormClass("inputPassword", "text-boxWrongValue", "text-box");
		ValidFormClass("registerUserName", "text-boxWrongValue", "text-box");
			ValidFormClass("userMail", "text-boxWrongValue", "text-box");
		ValidFormClass("userPassword", "text-boxWrongValue", "text-box");
		ValidFormClass("userPhone", "text-boxWrongValue", "text-box");
			ValidFormClass("registerUserId", "text-boxWrongValue", "text-box");
		BindUtils.postNotifyChange(null, null, this, "userId");
		BindUtils.postNotifyChange(null, null, this, "inputUserId");
		BindUtils.postNotifyChange(null, null, this, "inputPassword");
		BindUtils.postNotifyChange(null, null, this, "forgetUserId");
		BindUtils.postNotifyChange(null, null, this, "onLogin");
		BindUtils.postNotifyChange(null, null, this, "onRegister");
		BindUtils.postNotifyChange(null, null, this, "onForgot");
	}

	@Command("headForgot")
	public void headForgot() {
		onLogin = false;
		onRegister = false;
		onForgot = true;
		userId = "";
		inputUserId = "";
		inputPassword = "";

		registerUserId = "";
		registerUserName = "";
		registerUserPassword = "";
		confirmRegisterUserPassword = "";
		registerUserMail = "";
		registerUserPhone = "";
		ValidFormClass("inputUser", "text-boxWrongValue", "text-box");
		ValidFormClass("forgetUserId", "text-boxWrongValue", "text-box");
		ValidFormClass("inputPassword", "text-boxWrongValue", "text-box");
		ValidFormClass("registerUserName", "text-boxWrongValue", "text-box");
			ValidFormClass("userMail", "text-boxWrongValue", "text-box");
		ValidFormClass("userPassword", "text-boxWrongValue", "text-box");
		ValidFormClass("userPhone", "text-boxWrongValue", "text-box");
			ValidFormClass("registerUserId", "text-boxWrongValue", "text-box");
		BindUtils.postNotifyChange(null, null, this, "registerUserId");
		BindUtils.postNotifyChange(null, null, this, "registerUserName");
		BindUtils.postNotifyChange(null, null, this, "registerUserPassword");
		BindUtils.postNotifyChange(null, null, this, "confirmRegisterUserPassword");
		BindUtils.postNotifyChange(null, null, this, "registerUserMail");
		BindUtils.postNotifyChange(null, null, this, "registerUserPhone");
		BindUtils.postNotifyChange(null, null, this, "userId");
		BindUtils.postNotifyChange(null, null, this, "inputUserId");
		BindUtils.postNotifyChange(null, null, this, "inputPassword");
		
		
		BindUtils.postNotifyChange(null, null, this, "onLogin");
		BindUtils.postNotifyChange(null, null, this, "onRegister");
		BindUtils.postNotifyChange(null, null, this, "onForgot");
	}

	@Command("login")
	public void login() {
		if (getInputUserId() == null || getInputUserId() == "" || getInputPassword() == null
				|| getInputPassword() == "") {
			if ((getInputUserId() == null || getInputUserId() == "")
					&& (getInputPassword() == null || getInputPassword() == "")) {
				InValidFormClass("inputPassword", "text-boxWrongValue");
				InValidFormClass("inputUser", "text-boxWrongValue");
				return;
			}
			if (getInputUserId() == null || getInputUserId() == "") {
				InValidFormClass("inputUser", "text-boxWrongValue");
				return;
			}
			if (getInputPassword() == null || getInputPassword() == "") {
				InValidFormClass("inputPassword", "text-boxWrongValue");
				return;
			}
		}

		Map<String, Object> body = new HashMap<>();
		body.put("user", getInputUserId());
		body.put("pass", getInputPassword());
		WsResponse response = restTemplateLib.getResultWs("/LoginCtl/ValidationUser", body, "post",
				"projectCode=" + PROJECT);
		Map<String, Object> resultMap = new HashMap<>();
		resultMap = restTemplateLib.mapperJsonToHashMap(response.getWsContent());
		showInformationMessageBox(getCommonConstant((String) resultMap.get("information")));
		boolean pass = (boolean) resultMap.get("pass");
		if(pass) {
			Map<String, Object> mapBody = new HashMap<>();
			mapBody.put("userId", getInputUserId());
			WsResponse resp01 = restTemplateLib.getResultWs("/UserInformationCompCtl/UserInf", mapBody, "post",
					"projectCode=" + PROJECT);
			UserPrivilegeCustom upc = null;
			try {
				upc = restTemplateLib.mapperJsonToSingleDto(resp01.getWsContent(), UserPrivilegeCustom.class);
				System.err.println("apakah upc ada " + upc.getUserName());
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			if(upc!=null) {
				registerUserToDesktop(getInputUserId(), upc);
				sendMeToOtherPage("/beranda/FirstPage.zul");
			}	
		}
	}

	public Constraint getConstrainDataInputUserId() {
		return new Constraint() {

			@Override
			public void validate(Component comp, Object value) throws WrongValueException {
				String val = (String) value;
				String nuVal = "!" + val.trim() + "!";
				if (nuVal.equalsIgnoreCase("!!")) {
					InValidFormClass("inputUser", "text-boxWrongValue");
					throw new WrongValueException(comp, "Nama User Tidak Boleh Kosong");
				} else {
					ValidFormClass("inputUser", "text-boxWrongValue", "text-box");
				}
			}
		};
	}

	public Constraint getConstrainDataForgotInputUserId() {
		return new Constraint() {

			@Override
			public void validate(Component comp, Object value) throws WrongValueException {
				String val = (String) value;
				String nuVal = "!" + val.trim() + "!";
				if (nuVal.equalsIgnoreCase("!!")) {
					InValidFormClass("forgetUserId", "text-boxWrongValue");
					throw new WrongValueException(comp, "Nama User Tidak Boleh Kosong");
				} else {
					ValidFormClass("forgetUserId", "text-boxWrongValue", "text-box");
				}
			}
		};
	}

	public Constraint getConstrainDataInputUserPassword() {
		return new Constraint() {

			@Override
			public void validate(Component comp, Object value) throws WrongValueException {
				String val = (String) value;
				String nuVal = "!" + val.trim() + "!";
				if (nuVal.equalsIgnoreCase("!!")) {
					InValidFormClass("inputPassword", "text-boxWrongValue");
					throw new WrongValueException(comp, "Password Tidak Boleh Kosong");
				} else {
					ValidFormClass("inputPassword", "text-boxWrongValue", "text-box");
				}
			}
		};
	}

	public Constraint getConstraintDataRegisterUserName() {
		return new Constraint() {
			@Override
			public void validate(Component comp, Object value) throws WrongValueException {
				String val = (String) value;
				String nuVal = "!" + val.trim() + "!";
				if (nuVal.equalsIgnoreCase("!!")) {
					InValidFormClass("registerUserName", "text-boxWrongValue");
					throw new WrongValueException(comp, "Nama User Tidak Boleh Kosong");
				} else {
					ValidFormClass("registerUserName", "text-boxWrongValue", "text-box");
				}
			}
		};
	}

	public Constraint getConstraintDataEmail() {
		return new Constraint() {
			@Override
			public void validate(Component comp, Object value) throws WrongValueException {
				String val = (String) value;
				String nuVal = "!" + val.trim() + "!";
				if (nuVal.equalsIgnoreCase("!!")) {
					InValidFormClass("userMail", "text-boxWrongValue");
					throw new WrongValueException(comp, "Email Tidak Boleh Kosong");
				} else if (isEmailValid(val)) {
					InValidFormClass("userMail", "text-boxWrongValue");
					throw new WrongValueException(comp, "Email Tidak sesuai Dari Format Yang Diminta");
				} else {
					ValidFormClass("userMail", "text-boxWrongValue", "text-box");
				}
			}
		};
	}

	public Constraint getConstraintPassword() {
		return new Constraint() {

			@Override
			public void validate(Component comp, Object value) throws WrongValueException {
				String val = (String) value;
				String nuVal = "!" + val.trim() + "!";
				if (nuVal.equalsIgnoreCase("!!")) {
					InValidFormClass("userPassword", "text-boxWrongValue");
					throw new WrongValueException(comp, "Password Tidak Boleh Kosong");
				} else if (!isPasswordValid(val)) {
					InValidFormClass("userPassword", "text-boxWrongValue");
					throw new WrongValueException(comp, "Format Pasword Tidak Memenuhi Yang Diminta");
				} else {
					ValidFormClass("userPassword", "text-boxWrongValue", "text-box");
				}
			}
		};
	}

	public Constraint getConstraintDataPhone() {
		return new Constraint() {
			@Override
			public void validate(Component comp, Object value) throws WrongValueException {
				String val = (String) value;
				String nuVal = "!" + val.trim() + "!";
				if (nuVal.equalsIgnoreCase("!!")) {
					InValidFormClass("userPhone", "text-boxWrongValue");
					throw new WrongValueException(comp, "No Telp Tidak Boleh Kosong");
				} else if (!isPhoneValid(val)) {
					InValidFormClass("userPhone", "text-boxWrongValue");
					throw new WrongValueException(comp, "Format No Telp Masih Salah");
				} else {
					ValidFormClass("userPhone", "text-boxWrongValue", "text-box");
				}
			}
		};
	}

	public Constraint getConstraintDataRegisterUserId() {
		return new Constraint() {
			@Override
			public void validate(Component comp, Object value) throws WrongValueException {
				String val = (String) value;
				String nuVal = "!" + val.trim() + "!";
				if (nuVal.equalsIgnoreCase("!!")) {
					InValidFormClass("registerUserId", "text-boxWrongValue");
					throw new WrongValueException(comp, "User Id Tidak Boleh Kosong");
				} else if (!nuVal.equalsIgnoreCase("!!")) {
					WsResponse wsResponse = restTemplateLib.getResultWs("/LoginCtl/GetAllUser", null, "get",
							"projectCode=" + PROJECT);
					List<String> users = null;
					try {
						users = restTemplateLib.mapperJsonToListDto(wsResponse.getWsContent(), String.class);
					} catch (Exception e) {
						e.printStackTrace();
					}

					if (users != null) {
						if (users.contains(val)) {
							InValidFormClass("registerUserId", "text-boxWrongValue");
							throw new WrongValueException(comp, "User Id Sudah Terdaftar");
						}
						else {
							ValidFormClass("registerUserId", "text-boxWrongValue", "text-box");
						}
					}
				} else {
					ValidFormClass("registerUserId", "text-boxWrongValue", "text-box");
				}
			}
		};
	}

	@Command("register")
	public void register() {

		if (registerUserPassword != null) {
			if (!registerUserPassword.equals(confirmRegisterUserPassword)) {
				showErrorMessageBox("Mohon Maaf password dan konfirmasi password yang anda berikan masih belum sama.");
				setRegisterUserPassword("");
				setConfirmRegisterUserPassword("");
				BindUtils.postNotifyChange(null, null, this, "registerUserPassword");
				BindUtils.postNotifyChange(null, null, this, "confirmRegisterUserPassword");
				return;
			}
		}

		TblDataUserDto dto = new TblDataUserDto();
		dto.setUserName(getRegisterUserName());
		dto.setUserId(getRegisterUserId());
		dto.setUserPassword(getRegisterUserPassword());
		dto.setUserMail(getRegisterUserMail());
		dto.setUserPhone(getRegisterUserPhone());

		WsResponse wsResponse = restTemplateLib.getResultWs("/RegisterCtl/CreateUser", dto, "post",
				"projectCode=" + PROJECT);
		Map<String, Object> resultMap = new HashMap<>();
		resultMap = restTemplateLib.mapperJsonToHashMap(wsResponse.getWsContent());
		String idNo = (String) resultMap.get("idNo");

		String html = " <p> Dear " + getRegisterUserName() + ", <br>"
				+ " Terima kasih telah menggunakan EFIM sebagai layanan penyimpanan data anda."
				+ " Kami Konfirmasi kembali data yang telah anda input . </p>" + " <p>Nama    : "
				+ getRegisterUserName() + "<br>" + "    User Id : " + getRegisterUserId() + "<br>" + "    No Telp : "
				+ getRegisterUserPhone() + "<br></p>"
				+ " <p>Anda dapat mengklik button dibawah ini untuk dapat langsung mengaktifkan akun anda </p>"
				+ " <a href='http://localhost:28080/EFIM-UI/registeration/successPr.zul?id=" 
				+ "'><button>Activate your Account</button></a>"
				+ " <p>Anda dapat mengabaikan pesan ini jika tidak ingin melanjutkan <br>" + "    Best Regard,</p>"
				+ "<br>" + "<p> <b>Notes : Pesan ini akan kadaluarsa dalam 3 hari</b> </p>";
		
		String subject = "New Register Efim";
		
		dto.setSubjecEmail(subject);
		dto.setMessageEmail(html);
		
		WsResponse wsResponse2 = restTemplateLib.getResultWs("/LoginCtl/CreateUserEmail", dto, "post", "projectCode=" + PROJECT);
		String responseFinal = null;
		try {
			responseFinal = restTemplateLib.mapperJsonToSingleDto(wsResponse2.getWsContent(), String.class);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String html2 = " <p> Dear " + getRegisterUserName() + ", <br>"
				+ " Terima kasih telah menggunakan EFIM sebagai layanan penyimpanan data anda."
				+ " Kami Konfirmasi kembali data yang telah anda input . </p>" + " <p>Nama    : "
				+ getRegisterUserName() + "<br>" + "    User Id : " + getRegisterUserId() + "<br>" + "    No Telp : "
				+ getRegisterUserPhone() + "<br></p>"
				+ " <p>Anda dapat mengklik button dibawah ini untuk dapat langsung mengaktifkan akun anda </p>"
				+ " <a href='http://localhost:28080/EFIM-UI/registeration/successPr.zul?id=" + responseFinal
				+ "'><button>Activate your Account</button></a>"
				+ " <p>Anda dapat mengabaikan pesan ini jika tidak ingin melanjutkan <br>" + "    Best Regard,</p>"
				+ "<br>" + "<p> <b>Notes : Pesan ini akan kadaluarsa dalam 3 hari</b> </p>";
	
		showInformationMessageBox(new EmailPr().sendMail(registerUserMail.trim(), html2, subject,
				"Mohon Buka email anda untuk aktivasi akun anda."));
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getInputUserId() {
		return inputUserId;
	}

	public void setInputUserId(String inputUserId) {
		this.inputUserId = inputUserId;
	}

	public String getInputPassword() {
		return inputPassword;
	}

	public void setInputPassword(String inputPassword) {
		this.inputPassword = inputPassword;
	}

	public String getRegisterUserId() {
		return registerUserId;
	}

	public void setRegisterUserId(String registerUserId) {
		this.registerUserId = registerUserId;
	}

	public String getRegisterUserName() {
		return registerUserName;
	}

	public void setRegisterUserName(String registerUserName) {
		this.registerUserName = registerUserName;
	}

	public String getRegisterUserPassword() {
		return registerUserPassword;
	}

	public void setRegisterUserPassword(String registerUserPassword) {
		this.registerUserPassword = registerUserPassword;
	}

	public String getConfirmRegisterUserPassword() {
		return confirmRegisterUserPassword;
	}

	public void setConfirmRegisterUserPassword(String confirmRegisterUserPassword) {
		this.confirmRegisterUserPassword = confirmRegisterUserPassword;
	}

	public String getRegisterUserMail() {
		return registerUserMail;
	}

	public void setRegisterUserMail(String registerUserMail) {
		this.registerUserMail = registerUserMail;
	}

	public String getRegisterUserPhone() {
		return registerUserPhone;
	}

	public void setRegisterUserPhone(String registerUserPhone) {
		this.registerUserPhone = registerUserPhone;
	}

	public boolean isOnLogin() {
		return onLogin;
	}

	public void setOnLogin(boolean onLogin) {
		this.onLogin = onLogin;
	}

	public boolean isOnRegister() {
		return onRegister;
	}

	public void setOnRegister(boolean onRegister) {
		this.onRegister = onRegister;
	}

	public boolean isOnForgot() {
		return onForgot;
	}

	public void setOnForgot(boolean onForgot) {
		this.onForgot = onForgot;
	}

	public String getForgetUserId() {
		return forgetUserId;
	}

	public void setForgetUserId(String forgetUserId) {
		this.forgetUserId = forgetUserId;
	}
}

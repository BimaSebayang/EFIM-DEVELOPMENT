package vmd;

import java.util.ArrayList;
import java.util.List;

import org.zkoss.bind.annotation.Init;
import org.zkoss.zk.ui.Desktop;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.util.GenericForwardComposer;

import id.co.roxas.efim.common.common.dto.GlobalDesktopInitUser;
import id.co.roxas.efim.common.common.dto.UserPrivilegeCustom;
import id.co.roxas.efim.common.common.dto.headuser.TblEfimDbDto;

@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class BaseComponent extends GenericForwardComposer {
	private static final long serialVersionUID = -116133037662167662L;
	protected Desktop chatDesktop;
	private GlobalDesktopInitUser globalDesktopInitUser = new GlobalDesktopInitUser();

	// tester
	@Init
	public Desktop initDesktop() {
		return Executions.getCurrent().getDesktop();
	}

	private GlobalDesktopInitUser getPrivateDesktopInitUser(String userId) {
		GlobalDesktopInitUser gdiu = new GlobalDesktopInitUser();
		gdiu = (GlobalDesktopInitUser) initDesktop().getWebApp().getAttribute(userId);
		if (gdiu == null) {
			return new GlobalDesktopInitUser();
		} else {
			return gdiu;
		}
	}

	public UserPrivilegeCustom getComponentUser() {
		// ini hanya digunakan jika langsung directed ke firstpage.zul
		UserPrivilegeCustom upc = new UserPrivilegeCustom();
		upc.setUserId("Roxas0309");
		upc.setUserName("Bima Satrya Sebayang");
		upc.setUserPhoto(null);
		upc.setUserSessionCode("GBOLAAHQWCCDAHKLCCLV");
		upc.setProjectCode("EFIM");
		registerUserToDesktop("Roxas0309", upc);
		// ini hanya digunakan jika langsung directed ke firstpage.zul
		String user = (String) Sessions.getCurrent().getAttribute("user");
		System.err.println("usernya adalah " + user);
		if (user == null) {
			return null;
		}
		return ((GlobalDesktopInitUser) initDesktop().getWebApp().getAttribute(user)).getUserPrivilegeCustom();
	}

	public void registerDbUserToDesktop(String userId, List<TblEfimDbDto> tblEfimDbDtos) {
		globalDesktopInitUser = getPrivateDesktopInitUser(userId);
		globalDesktopInitUser.setTblEfimDbDtos(tblEfimDbDtos);
		initDesktop().getWebApp().setAttribute(userId, globalDesktopInitUser);
	}

	@SuppressWarnings("unchecked")
	public List<TblEfimDbDto> getRegisterDbUserInCurrentFile(String userId, String fileType) {
		List<TblEfimDbDto> tblEfimDbDtos = new ArrayList<>();
		try {
			List<TblEfimDbDto> tblEfimDbDtosTemp = ((GlobalDesktopInitUser) initDesktop().getWebApp()
					.getAttribute(userId)).getTblEfimDbDtos();
			for (TblEfimDbDto tblEfimDbDto : tblEfimDbDtosTemp) {
				if (tblEfimDbDto.getFileType().equalsIgnoreCase(fileType)) {
					tblEfimDbDtos.add(tblEfimDbDto);
				}
			}
		} catch (NullPointerException npe) {
           npe.printStackTrace();
		}
		return tblEfimDbDtos;
	}

	@SuppressWarnings("unchecked")
	public List<TblEfimDbDto> getRegisterDbUserInCurrentAttributeFile(String userId) {
		return ((GlobalDesktopInitUser) initDesktop().getWebApp().getAttribute(userId)).getTblEfimDbDtos();
	}

	public void sendMeToOtherPage(String url) {
		Executions.sendRedirect(url);
	}

	public void registerUserToDesktop(String userId, UserPrivilegeCustom upc) {
		globalDesktopInitUser = getPrivateDesktopInitUser(userId);
		globalDesktopInitUser.setUserPrivilegeCustom(upc);
		Sessions.getCurrent().setAttribute("user", userId);
		initDesktop().getWebApp().setAttribute(userId, globalDesktopInitUser);
	}

	public Desktop getChatDesktop() {
		return chatDesktop;
	}

	public void setChatDesktop(Desktop chatDesktop) {
		this.chatDesktop = chatDesktop;
	}

	public GlobalDesktopInitUser getGlobalDesktopInitUser() {
		return globalDesktopInitUser;
	}

	public void setGlobalDesktopInitUser(GlobalDesktopInitUser globalDesktopInitUser) {
		this.globalDesktopInitUser = globalDesktopInitUser;
	}
}

package controller.login;

import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import common.dto.headuser.TblDataUserDto;
import dao.ProcedureDao;
import service.headuser.TblDataUserSvc;
import webservice.global.WsResponse;
import webservice.lib.RestTemplateLib;

@RestController
@RequestMapping("/LoginCtl")
public class LoginCtl {

	private RestTemplateLib restTemplateLib;
	
	@Autowired
	private ProcedureDao procedureDao;

	@Autowired
	private TblDataUserSvc tblDataUserSvc;

	@RequestMapping(value = "/ValidationUser", method = RequestMethod.POST, params = { "projectCode" })
	public WsResponse checkIfValidUser(@RequestBody Map<String, Object> inputedUser,
			@RequestHeader Map<String, Object> rh, @RequestParam String projectCode) {
		String TS = new SimpleDateFormat("dd.MM.yyyy.HH.mm.ss").format(new Date());

		procedureDao.spCreatedHistoryAccess((String) rh.get("ip"), (String) rh.get("mac"), (String) rh.get("sn"), TS,
				"NONE", "http://localhost:8080/EFIM-CORE/LoginCtl/ValidationUser", projectCode);

		String result = procedureDao.spCheckValidUser((String) inputedUser.get("user"),
				(String) inputedUser.get("pass"), projectCode, TS);
		WsResponse wsResponse = new WsResponse(result, 1);
		return wsResponse;
	}

	@RequestMapping(value = "/GetAllUser", method = RequestMethod.GET, params = { "projectCode" })
	public WsResponse checkIfValidUser(@RequestHeader Map<String, Object> rh, @RequestParam String projectCode) {
		String TS = new SimpleDateFormat("dd.MM.yyyy.HH.mm.ss").format(new Date());

		procedureDao.spCreatedHistoryAccess((String) rh.get("ip"), (String) rh.get("mac"), (String) rh.get("sn"), TS,
				"NONE", "http://localhost:8080/EFIM-CORE/LoginCtl/GetAllUser", projectCode);

		Map<String, Object> mapp = tblDataUserSvc.getTheResultAllExistingUser(projectCode);
		WsResponse wsResponse = new WsResponse(mapp.get("content"), (int) mapp.get("count"), false, null, null);
		return wsResponse;
	}

	@RequestMapping(value = "/CreateUserEmail", method = RequestMethod.POST, params = { "projectCode" })
	public WsResponse createdUserEmail(@RequestBody TblDataUserDto tblDataUserDto, @RequestHeader Map<String, Object> rh,
			@RequestParam String projectCode) {
		String TS = new SimpleDateFormat("dd.MM.yyyy.HH.mm.ss").format(new Date());
		procedureDao.spCreatedHistoryAccess((String) rh.get("ip"), (String) rh.get("mac"), (String) rh.get("sn"), TS,
				"NONE", "http://localhost:8080/EFIM-CORE/LoginCtl/CreateUserEmail", projectCode);
		
		//System.err.println("user " + tblDataUserDto.getUserName());
		
		String emailId = procedureDao.spCreateEmailExecute(tblDataUserDto.getUserName(), tblDataUserDto.getUserMail(),tblDataUserDto.getUserId(),
				tblDataUserDto.getMessageEmail(), tblDataUserDto.getSubjecEmail(), "", "", TS, new BigInteger("3"), projectCode);

		WsResponse wsResponse = new WsResponse(emailId, 1, false, null, null);
		return wsResponse;
	}

}

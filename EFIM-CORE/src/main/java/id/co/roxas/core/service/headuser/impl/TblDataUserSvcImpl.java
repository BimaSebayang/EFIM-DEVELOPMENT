package id.co.roxas.core.service.headuser.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zkoss.lang.Strings;

import id.co.roxas.core.dao.headuser.TblDataUserDao;
import id.co.roxas.core.entity.headuser.TblDataUser;
import id.co.roxas.core.service.headuser.TblDataUserSvc;
import id.co.roxas.efim.common.common.dto.headuser.TblDataUserDto;
import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.impl.DefaultMapperFactory;

@Service("tblDataUserSvc")
@Transactional
public class TblDataUserSvcImpl implements TblDataUserSvc{

	@Autowired
	private TblDataUserDao tblDataUserDao;
	
	private MapperFacade mapperFacade = new DefaultMapperFactory.Builder().build().getMapperFacade();
	
	@Override
	public Map<String, Object> getTheResultEmailAddress(String userId, String projectId) {
		TblDataUser tblDataUser = tblDataUserDao.getUserInformation(userId, projectId);
		TblDataUserDto tblDataUserDto = mapperFacade.map(tblDataUser, TblDataUserDto.class);
		Map<String, Object> mapResult = new HashMap<>();
		if(tblDataUser==null) {
			tblDataUserDto = new TblDataUserDto();
			tblDataUserDto.setUserMail("ERROR");
			mapResult.put("content", tblDataUserDto);
			mapResult.put("count", 0);
		}
		else {
		mapResult.put("content", tblDataUserDto);
		mapResult.put("count", 1);
		}
		return mapResult;
	}

	@Override
	public Map<String, Object> getTheResultAllExistingUser(String projectCode) {
		List<String> users = tblDataUserDao.getAllUser(projectCode);
		List<String> usersTemp = tblDataUserDao.getAllUSerFromTemp(projectCode);
		List<String> finUsers = new ArrayList<>();
		finUsers.addAll(usersTemp);
		finUsers.addAll(users);
		Map<String, Object> mapResult = new HashMap<>();
		mapResult.put("content", finUsers);
		mapResult.put("count", finUsers.size());
		return mapResult;
	}

}

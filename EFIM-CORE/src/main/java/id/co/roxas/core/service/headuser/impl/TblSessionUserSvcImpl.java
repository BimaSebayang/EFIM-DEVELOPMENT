package id.co.roxas.core.service.headuser.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Share.Dto.HeadUser.TblDataUserDto;
import Share.Dto.HeadUser.TblSessionUserDto;
import entity.headuser.TblDataUser;
import entity.headuser.TblSessionUser;
import id.co.roxas.core.dao.headuser.TblDataUserDao;
import id.co.roxas.core.service.headuser.TblSessionUserSvc;
import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.impl.DefaultMapperFactory;

@Service("tblSessionUserSvc")
@Transactional
public class TblSessionUserSvcImpl implements TblSessionUserSvc{

	private MapperFacade mapperFacade = new DefaultMapperFactory.Builder().build().getMapperFacade();
	
	@Autowired
	private TblDataUserDao tableDataUserDao;
	
	@Override
	public Map<String, Object> getResultUser() {
		List<Object[]> users = tableDataUserDao.selectAll();
		Map<String, Object> mapper = new HashMap<>();
		
		List<TblDataUserDto> dataUserDtos = new ArrayList<>();
		for (Object[] user : users) {
			TblDataUser dataUser = (TblDataUser) user[0];
			TblSessionUser session = (TblSessionUser) user[1];
			TblDataUserDto tblDataUserDto = mapperFacade.map(dataUser, TblDataUserDto.class);
			tblDataUserDto.setTblSessionUserDto(mapperFacade.map(session, TblSessionUserDto.class));
		    dataUserDtos.add(tblDataUserDto);
		}
		
		mapper.put("content", dataUserDtos);
		mapper.put("size", dataUserDtos.size());
		return mapper;
	}

}

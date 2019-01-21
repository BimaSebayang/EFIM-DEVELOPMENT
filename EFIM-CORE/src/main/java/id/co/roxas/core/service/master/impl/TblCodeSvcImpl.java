package id.co.roxas.core.service.master.impl;

import java.util.HashMap;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import id.co.roxas.core.dao.master.TblCodeDao;
import id.co.roxas.core.entity.master.TblCode;
import id.co.roxas.core.service.master.TblCodeSvc;
import id.co.roxas.efim.common.common.dto.master.TblCodeDto;
import id.co.roxas.efim.common.constant.CommonConstant;
import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.impl.DefaultMapperFactory;

@Service("tblCodeSvc")
@Transactional
public class TblCodeSvcImpl extends CommonConstant implements TblCodeSvc {

	@Autowired
	private TblCodeDao tblCodeDao;

	private MapperFacade mapperFacade = new DefaultMapperFactory.Builder().build().getMapperFacade();

	@Override
	public Map<String, Object> getCurrentInformationCodeFromMstCodeType(String mstCodeType) {
		TblCode tblCode = tblCodeDao.getAllCodeInfoByMstCodeType(mstCodeType);

		Map<String, Object> mapper = new HashMap<>();

		try {
			if (tblCode != null) {
				TblCodeDto tblCodeDto = mapperFacade.map(tblCode, TblCodeDto.class);
				mapper.put("content", tblCodeDto);
				mapper.put("count", 1);
				mapper.put("error", COMMONNOERROR);
			
			} else {
				mapper.put("content", null);
				mapper.put("count", 0);
				mapper.put("error", COMMONNODATA);
			}
		} catch (Exception exp) {
			exp.printStackTrace();
			mapper.put("content", null);
			mapper.put("count", 0);
			mapper.put("error", exp.getMessage());
		}
		return mapper;
	}

	@Override
	public Map<String, Object> getCurrentInformationCodeFromMstCode(String mstCode) {
		TblCode tblCode = tblCodeDao.getAllCodeInfoByMstCode(mstCode);

		Map<String, Object> mapper = new HashMap<>();

		try {
			if (tblCode != null) {
				TblCodeDto tblCodeDto = mapperFacade.map(tblCode, TblCodeDto.class);
				mapper.put("content", tblCodeDto);
				mapper.put("count", 1);
				mapper.put("error", COMMONNOERROR);
			} else {
				mapper.put("content", null);
				mapper.put("count", 0);
				mapper.put("error", COMMONNODATA);
			}
		} catch (Exception exp) {
			exp.printStackTrace();
			mapper.put("content", null);
			mapper.put("count", 0);
			mapper.put("error", exp.getMessage());
		}
		return mapper;
	}

}

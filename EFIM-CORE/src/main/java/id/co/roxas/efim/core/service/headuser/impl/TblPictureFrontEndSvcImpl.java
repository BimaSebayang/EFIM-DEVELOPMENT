package id.co.roxas.efim.core.service.headuser.impl;

import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import id.co.roxas.efim.core.dao.headuser.TblPictureFrontEndDao;
import id.co.roxas.efim.core.entity.stream.TblPictureFrontEnd;
import id.co.roxas.efim.core.service.headuser.TblPictureFrontEndSvc;

@Service("tblPictureFrontEndSvc")
@Transactional
public class TblPictureFrontEndSvcImpl implements TblPictureFrontEndSvc{

	@Autowired
	private TblPictureFrontEndDao tblPictureFrontEndDao;
	
	@Override
	public byte[] getTheImage(String pictureName, String projectCode) {
		TblPictureFrontEnd tblPictureFrontEnd = tblPictureFrontEndDao.getImage(pictureName, projectCode);
		return tblPictureFrontEnd.getPicturePath();
	}

}

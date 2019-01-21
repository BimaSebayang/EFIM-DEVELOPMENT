package tester.dao;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.domain.Page;

import com.google.gson.Gson;

import dao.ProcedureDao;
import dao.headuser.TblDataUserDao;
import entity.headuser.TblDataUser;
import entity.headuser.TblSessionUser;
import paging.request.RequestPaging;

public class daoTester1 {

	public static void main(String[] args) {
		
		ApplicationContext ctx = new ClassPathXmlApplicationContext("/META-INF/spring/app-config.xml");
        //TblDataUserDao dao = ctx.getBean(TblDataUserDao.class);
        ProcedureDao pd = ctx.getBean(ProcedureDao.class);
        String ts = new SimpleDateFormat("dd.MM.yyyy.HH.mm.ss").format(new Date());
        String result = pd.spCheckValidUser("BimaSS", "Roxas0309.", "EFIM", ts);
        System.err.println(result);
//        RequestPaging rp = new RequestPaging();
//       Page<Object[]> dataUser = dao.selectAllWithPaging(rp.createRequestPage(1, "DESC", "createdDate"));
//        List<Object[]> content = dataUser.getContent();
//        
//       // List<Object[]> dataUser = dao.selectAllWithLeft();
//        List<TblDataUser> users = new ArrayList<>();
//        for (Object[] obj: dataUser.getContent()) {
//        	TblDataUser user = (TblDataUser) obj[0];
//        	TblSessionUser session = (TblSessionUser) obj[1];
//        	users.add(user);
//			//System.err.println("user  " + user.getUserId() + " dengan session " + session.getSessionTime() + " tanggal " + user.getCreatedDate() );
//		}
        
//       String ts = new SimpleDateFormat("dd.MM.yyyy.HH.mm.ss").format(new Date());
//        System.err.println(ts);
//        String str = new Gson().toJson(users);
//        System.err.println(str);
        
	}

}

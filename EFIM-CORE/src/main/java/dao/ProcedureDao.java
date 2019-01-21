package dao;

import java.math.BigInteger;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;

import entity.procacclaim.ProcInformation;
import entity.procacclaim.pk.ProcInformationPk;

public interface ProcedureDao extends JpaRepository<ProcInformation, ProcInformationPk>{

    @Procedure(procedureName="MASTER.SP_CHECK_VALID_USER")
    public String spCheckValidUser(String inputedId, String inputedPass, String projekCode, String accessDate);
    
    @Procedure(procedureName="SECURITY.SP_CREATE_HISTORY_ACCESS")
    public void spCreatedHistoryAccess(String ipAddress, String macAddress, String sn
    		, String accessDate, String userId, String controller, String projectCode);
    
    @Procedure(procedureName="MASTER.SP_CREATE_TEMPORARY_USER")
    public String spCreatedTemporaryUser(String userName, String userId, String userPassword
    		, String userMail, String userPhone, String createdDate, String projectCode);
    
    @Procedure(procedureName="MASTER.SP_CREATE_USER_AND_SESSION")
    public String spCreatedUserAndSession(String regNo, String createdDate, String projectCode);
    
    @Procedure(procedureName="MASTER.SP_RESET_PASSWORD")
    public void spResetPassword(String userId, String password, String accessDate, String projectCode);
    
    @Procedure(procedureName="MASTER.CREATE_EMAIL_EXECUTE")
    public String spCreateEmailExecute(String recepientName,String recepientEmail,String recepientId,String message, String subject ,
    		String ccPerson, String ccPersonEmail,
    		String createdDate ,BigInteger expiredTime, String projectCode);
    
    @Procedure(procedureName="SECURITY.SP_CHECK_EMAIL_IS_VALID")
    public String  spCheckEmailIsValid(String emailNo, String projectCode);
    

}

package service.headuser;

import java.util.UUID;

public interface TblPictureFrontEndSvc {
    public byte[] getTheImage(String pictureName, String projectCode);
}

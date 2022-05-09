package QLTV.BUS;

import java.util.ArrayList;

import QLTV.DAO.QLHDTPDAO;
import QLTV.DTO.HDTIENPHAT;

public class QLHDTPBUS {
    public static ArrayList<HDTIENPHAT> dshdtp;

    public QLHDTPBUS() {

    }

    public void docDS() throws Exception {
        QLHDTPDAO data = new QLHDTPDAO();
        if (dshdtp == null)
            dshdtp = new ArrayList<HDTIENPHAT>();
        dshdtp = data.docDS();
    }
}

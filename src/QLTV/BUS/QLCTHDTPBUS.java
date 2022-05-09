package QLTV.BUS;

import java.util.ArrayList;

import QLTV.DAO.QLCTHDTPDAO;
import QLTV.DTO.CHITIETHDTIENPHAT;

public class QLCTHDTPBUS {
    public static ArrayList<CHITIETHDTIENPHAT> dscthdtp;

    public QLCTHDTPBUS() {

    }

    public void docDS() throws Exception {
        QLCTHDTPDAO data = new QLCTHDTPDAO();
        if (dscthdtp == null)
            dscthdtp = new ArrayList<CHITIETHDTIENPHAT>();
        dscthdtp = data.docDS();
    }
}

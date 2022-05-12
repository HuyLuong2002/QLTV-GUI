package QLTV.BUS;

import java.util.ArrayList;

import QLTV.DAO.QLPNDAO;
import QLTV.DTO.PHIEUNHAP;

public class QLPNBUS {
    public static ArrayList<PHIEUNHAP> dspn;

    public QLPNBUS() {

    }

    public void docDS() throws Exception {
        QLPNDAO data = new QLPNDAO();
        if (dspn == null)
            dspn = new ArrayList<PHIEUNHAP>();
        dspn = data.docDS();
    }
}

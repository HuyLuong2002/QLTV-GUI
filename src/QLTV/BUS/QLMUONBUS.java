package QLTV.BUS;

import java.util.ArrayList;

import QLTV.DAO.QLMUONDAO;
import QLTV.DTO.PHIEUMUON;

public class QLMUONBUS {
    public static ArrayList<PHIEUMUON> dspm;
    public static ArrayList<PHIEUMUON> htXoa = new ArrayList<PHIEUMUON>();

    public QLMUONBUS() {

    }

    public void docDSPM() throws Exception {
        QLMUONDAO data = new QLMUONDAO();
        if (dspm == null)
            dspm = new ArrayList<PHIEUMUON>();
        dspm = data.docDSPM();
    }
}

package QLTV.BUS;

import java.util.ArrayList;

import QLTV.DAO.QLCTMUONDAO;
import QLTV.DTO.CHITIETPHIEUMUON;

public class QLCTMUONBUS {
    public static ArrayList<CHITIETPHIEUMUON> dsctpm;
    public static ArrayList<CHITIETPHIEUMUON> htXoa = new ArrayList<CHITIETPHIEUMUON>();

    public QLCTMUONBUS() {

    }

    public void docDSCTPM() throws Exception {
        QLCTMUONDAO data = new QLCTMUONDAO();
        if (dsctpm == null)
            dsctpm = new ArrayList<CHITIETPHIEUMUON>();
        dsctpm = data.docDSCTPM();
    }
}

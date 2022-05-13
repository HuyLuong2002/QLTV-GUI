package QLTV.BUS;

import java.util.ArrayList;
import QLTV.DAO.QLCTTRADAO;
import QLTV.DTO.CHITIETPHIEUTRA;

public class QLCTTRABUS {
    public static ArrayList<CHITIETPHIEUTRA> dsctpt;
    public static ArrayList<CHITIETPHIEUTRA> htXoa = new ArrayList<CHITIETPHIEUTRA>();

    public QLCTTRABUS(){}

    public void docDSCTPT() throws Exception {
        QLCTTRADAO data = new QLCTTRADAO();
        if (dsctpt == null)
            dsctpt = new ArrayList<CHITIETPHIEUTRA>();
        dsctpt = data.docDSCTPT();
    }

    public int them(CHITIETPHIEUTRA ctphieutra) throws Exception {
        QLCTTRADAO data = new QLCTTRADAO();
        data.them(ctphieutra);
        dsctpt.add(ctphieutra);
        return 1;
    }

    public int sua(CHITIETPHIEUTRA phieutramoi, CHITIETPHIEUTRA phieutracu, int i) throws Exception {
        // Truy cập vào database
        int kt = 0;
        QLCTTRADAO data = new QLCTTRADAO();
        kt = data.sua(phieutramoi, phieutracu);
        dsctpt.set(i, phieutramoi);
        return kt;
    }
}

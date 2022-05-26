package QLTV.BUS;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import QLTV.DAO.QLCTTRADAO;
import QLTV.DTO.CHITIETPHIEUTRA;

public class QLCTTRABUS {
    public static ArrayList<CHITIETPHIEUTRA> dsctpt;
    public static Set<CHITIETPHIEUTRA> htXoa = new HashSet<CHITIETPHIEUTRA>();

    public QLCTTRABUS(){}

    public void docDSCTPT() throws Exception {
        QLCTTRADAO data = new QLCTTRADAO();
        if (dsctpt == null)
            dsctpt = new ArrayList<CHITIETPHIEUTRA>();
        dsctpt = data.docDSCTPT();
    }

    public int them(CHITIETPHIEUTRA ctphieutra) throws Exception {
        int kt = 0;
        QLCTTRADAO data = new QLCTTRADAO();
        kt = data.them(ctphieutra);
        if(kt == 0){
            dsctpt.add(ctphieutra);
        }
        return kt;
    }

    public int sua(CHITIETPHIEUTRA phieutramoi, String MaPTCTPTCu, String MasachCTPTCu, int i) throws Exception {
        // Truy cập vào database
        int kt = 0;
        QLCTTRADAO data = new QLCTTRADAO();
        kt = data.sua(phieutramoi, MaPTCTPTCu, MasachCTPTCu);
        if(kt == 0)
            dsctpt.set(i, phieutramoi);
        return kt;
    }

    public int xoa(String MaSV, int i) throws Exception {
        int kt = -1;
        QLCTTRADAO data = new QLCTTRADAO();
        kt = data.xoa(MaSV);
        if(kt == 0)
            dsctpt.remove(i);
        return kt;
    }
}

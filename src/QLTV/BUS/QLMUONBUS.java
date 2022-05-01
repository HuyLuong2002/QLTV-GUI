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

    public PHIEUMUON timTheoMa(String MaPM) {
        for (PHIEUMUON pm : dspm)
            if (pm.getMaPM().trim().equals(MaPM))
                return pm;
        return null;
    }

    public ArrayList<PHIEUMUON> timTheoSLtong(String SLtong) {
        ArrayList<PHIEUMUON> kq = new ArrayList<PHIEUMUON>();
        for (PHIEUMUON pm : dspm)
            if (String.valueOf(pm.getSLtong()).indexOf(SLtong) >= 0)
                kq.add(pm);
        return kq;
    }

    public ArrayList<PHIEUMUON> timTheoTinhTrangMuon(String TinhTrangMuon) {
        ArrayList<PHIEUMUON> kq = new ArrayList<PHIEUMUON>();
        for (PHIEUMUON pm : dspm)
            if (String.valueOf(pm.getTinhTrangMuon().trim()).indexOf(TinhTrangMuon) >= 0)
                kq.add(pm);
        return kq;
    }

    public ArrayList<PHIEUMUON> timTheoMaDG(String MaDG) {
        ArrayList<PHIEUMUON> kq = new ArrayList<PHIEUMUON>();
        for (PHIEUMUON pm : dspm)
            if (String.valueOf(pm.getMaDG().trim()).indexOf(MaDG) >= 0)
                kq.add(pm);
        return kq;
    }
}

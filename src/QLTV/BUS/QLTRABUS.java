package QLTV.BUS;

import java.util.ArrayList;

import QLTV.DAO.QLTRADAO;
import QLTV.DTO.PHIEUTRASACH;

public class QLTRABUS {
    public static ArrayList<PHIEUTRASACH> dspt;
    public static ArrayList<PHIEUTRASACH> htXoa = new ArrayList<PHIEUTRASACH>();

    public QLTRABUS() {

    }

    public void docDSPT() throws Exception {
        QLTRADAO data = new QLTRADAO();
        if (dspt == null)
            dspt = new ArrayList<PHIEUTRASACH>();
        dspt = data.docDSPT();
    }

    public PHIEUTRASACH timTheoMaPT(String MaPT) {
        for (PHIEUTRASACH pt : dspt)
            if (pt.getMaPT().trim().equals(MaPT))
                return pt;
        return null;
    }

    public PHIEUTRASACH timTheoMaPM(String MaPM) {
        for (PHIEUTRASACH pt : dspt)
            if (pt.getMaPM().trim().equals(MaPM))
                return pt;
        return null;
    }

    public ArrayList<PHIEUTRASACH> timTheoNgayTra(String Ngaytra) {
        ArrayList<PHIEUTRASACH> kq = new ArrayList<PHIEUTRASACH>();
        for (PHIEUTRASACH pt : dspt)
            if (String.valueOf(pt.getNgaytra()).indexOf(Ngaytra) >= 0)
                kq.add(pt);
        return kq;
    }

    public ArrayList<PHIEUTRASACH> timTheoTinhTrangSach(String TinhTrangSach) {
        ArrayList<PHIEUTRASACH> kq = new ArrayList<PHIEUTRASACH>();
        for (PHIEUTRASACH pt : dspt)
            if (String.valueOf(pt.getTinhtrangsach().trim()).indexOf(TinhTrangSach) >= 0)
                kq.add(pt);
        return kq;
    }

    public ArrayList<PHIEUTRASACH> timTheoThanhTien(String Thanhtien) {
        ArrayList<PHIEUTRASACH> kq = new ArrayList<PHIEUTRASACH>();
        for (PHIEUTRASACH pt : dspt)
            if (String.valueOf(pt.getThanhtien()).equals(Thanhtien))
                kq.add(pt);
        return kq;
    }
}

package QLTV.BUS;

import java.util.ArrayList;

import javax.swing.JOptionPane;

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
            if (String.valueOf(pt.getThanhtien()).indexOf(Thanhtien) >= 0)
                kq.add(pt);
        return kq;
    }

    public int them(PHIEUTRASACH phieutrasach) throws Exception {
        if (KTMa(phieutrasach.getMaPT()) == 0) {
            JOptionPane.showMessageDialog(null, "Mã phiếu mượn vừa nhập bị trùng. Mời nhập lại!", "Lỗi",
                    JOptionPane.ERROR_MESSAGE);
            return -1;
        } else {
            // Truy cập vào database
            int kt = 0;
            QLTRADAO data = new QLTRADAO();
            kt = data.them(phieutrasach);
            if(kt == 0){
                dspt.add(phieutrasach);
            }
            return kt;
        }
    }

    public int sua(PHIEUTRASACH phieutramoi, PHIEUTRASACH phieutracu, int i) throws Exception {
        // Truy cập vào database
        int kt = 0;
        QLTRADAO data = new QLTRADAO();
        kt=data.sua(phieutramoi, phieutracu);
        if(kt == 0){
            dspt.set(i, phieutramoi);
        }
        return kt;
    }
    public int KTMa(String MaPtMoi) {
        for (PHIEUTRASACH phieutrasach : dspt)
            if (phieutrasach.getMaPT().trim().equals(MaPtMoi)) {
                return 0;
            }
        return 1;
    }
}

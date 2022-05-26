package QLTV.BUS;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import javax.swing.JOptionPane;

import QLTV.DAO.QLTRADAO;
import QLTV.DTO.PHIEUTRASACH;

public class QLTRABUS {
    public static ArrayList<PHIEUTRASACH> dspt;
    public static Set<PHIEUTRASACH> htXoa = new HashSet<PHIEUTRASACH>();

    public QLTRABUS() {

    }

    public void docDSPT() throws Exception {
        QLTRADAO data = new QLTRADAO();
        if (dspt == null)
            dspt = new ArrayList<PHIEUTRASACH>();
        dspt = data.docDSPT();
    }

    public ArrayList<PHIEUTRASACH> LocPT(int BDTra, int KTTra){
        ArrayList<PHIEUTRASACH> kq = new ArrayList<PHIEUTRASACH>();
        for(PHIEUTRASACH pt : dspt) {
             if(Integer.parseInt(pt.getNgaytra().replaceAll("-", "")) >= BDTra
             && Integer.parseInt(pt.getNgaytra().replaceAll("-", "")) <= KTTra)
                kq.add(pt);
        }
        return kq;
     }

    public PHIEUTRASACH timTheoMaPT(String MaPT) {
        for (PHIEUTRASACH pt : dspt)
            if (pt.getMaPT().toLowerCase().trim().equals(MaPT))
                return pt;
        return null;
    }

    public ArrayList<PHIEUTRASACH> timTheoMaPM(String MaPM) {
        ArrayList<PHIEUTRASACH> kq = new ArrayList<PHIEUTRASACH>();
        for (PHIEUTRASACH pt : dspt)
            if (pt.getMaPM().toLowerCase().trim().equals(MaPM))
                kq.add(pt);
        return kq;
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
            if (String.valueOf(pt.getTinhtrangsach().toLowerCase().replaceAll("\\s+", " ").trim()).indexOf(TinhTrangSach) >= 0)
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

    public int xoa(String MaSV, int i) throws Exception {
        int kt = -1;
        QLTRADAO data = new QLTRADAO();
        kt = data.xoa(MaSV);
        if(kt == 0)
            dspt.remove(i);
        return kt;
    }

    public int KTMa(String MaPtMoi) {
        for (PHIEUTRASACH phieutrasach : dspt)
            if (phieutrasach.getMaPT().replaceAll("\\s+", "").toLowerCase().equals(MaPtMoi)) {
                return 0;
            }
        return 1;
    }
}

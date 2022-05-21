package QLTV.BUS;

import java.util.ArrayList;
import javax.swing.JOptionPane;

import QLTV.DAO.QLNHANVIENDAO;
import QLTV.DTO.NHANVIEN;

public class QLNHANVIENBUS {
    public static ArrayList<NHANVIEN> dsnhanvien;
    public static ArrayList<NHANVIEN> htXoa = new ArrayList<NHANVIEN>();
    public static ArrayList<NHANVIEN> htSua = new ArrayList<NHANVIEN>();

    public QLNHANVIENBUS() {

    }

    public void docDSNHANVIEN() throws Exception {
        QLNHANVIENDAO data = new QLNHANVIENDAO();
        if (dsnhanvien == null)
            dsnhanvien = new ArrayList<NHANVIEN>();
        dsnhanvien = data.docDSSV();
    }

    public int them(NHANVIEN nhanvien) throws Exception {
        if (KTMa(nhanvien.getMaNV()) == 0) {
            JOptionPane.showMessageDialog(null, "Mã nhân viên vừa nhập bị trùng. Mời nhập lại!", "Lỗi",
                    JOptionPane.ERROR_MESSAGE);
            return -1;
        } else {
            // Truy cập vào database
            QLNHANVIENDAO data = new QLNHANVIENDAO();
            data.them(nhanvien);
            dsnhanvien.add(nhanvien);
            return 1;
        }
    }

    public int sua(NHANVIEN nhanvienmoi, NHANVIEN nhanviencu, int i) throws Exception {
        // Truy cập vào database
        int kt = -1;
        QLNHANVIENDAO data = new QLNHANVIENDAO();
        data.sua(nhanvienmoi, nhanviencu);
        if (kt == 0) {
            dsnhanvien.set(i, nhanvienmoi);
        }
        return kt;
    }

    public void xoa(String MaNV, int i) throws Exception {
        // int kt = -1;
        QLNHANVIENDAO data = new QLNHANVIENDAO();
        data.xoa(MaNV);
        dsnhanvien.remove(i);
    }

    public int hoantacXoa(NHANVIEN nhanvien) throws Exception {
        if (KTMa(nhanvien.getMaNV().trim()) == 0) {
            JOptionPane.showMessageDialog(null, "Mã sinh viên vừa nhập bị trùng. Mời nhập lại!", "Lỗi",
                    JOptionPane.ERROR_MESSAGE);
            return -1;
        } else {
            // Truy cập vào database
            QLNHANVIENDAO data = new QLNHANVIENDAO();
            data.hoantacXoa(nhanvien);
            dsnhanvien.add(nhanvien);
            return 1;
        }
    }

    // public ArrayList<NHANVIEN> loc(int NamBD, int NamKT) {
    // ArrayList<NHANVIEN> kq = new ArrayList<NHANVIEN>();
    // for (NHANVIEN sach : dsnhanvien) {
    // if (Integer.parseInt(sach.getNamXB().trim()) >= NamBD
    // && Integer.parseInt(sach.getNamXB().trim()) <= NamKT) {
    // kq.add(sach);
    // }
    // }

    // return kq;
    // }

    public int KTMa(String MaSachMoi) {
        for (NHANVIEN sach : dsnhanvien)
            if (sach.getMaNV().trim().equals(MaSachMoi)) {
                return 0;
            }
        return 1;
    }

    // public int KTSL(int SLtongmoi, int SLmoi) {
    // if (SLtongmoi < SLmoi) {
    // return 0;
    // }
    // return 1;
    // }

    public NHANVIEN timTheoMa(String Masach) {
        for (NHANVIEN sach : dsnhanvien)
            if (sach.getMaNV().trim().equals(Masach))
                return sach;
        return null;
    }

    public ArrayList<NHANVIEN> timTheoTen(String Tensach) {
        ArrayList<NHANVIEN> kq = new ArrayList<NHANVIEN>();
        for (NHANVIEN sach : dsnhanvien)
            if (sach.getTenNV().indexOf(Tensach) >= 0)
                kq.add(sach);
        return kq;
    }

    public void docDSSACH() {
    }

    // public ArrayList<NHANVIEN> timTheoChucvu(int Chucvu) {
    // ArrayList<NHANVIEN> kq = new ArrayList<NHANVIEN>();
    // for (NHANVIEN nhanvien : dsnhanvien)
    // if (nhanvien.getChucvu().indexOf(Chucvu) >= 0)
    // kq.add(nhanvien);
    // return kq;
    // }

}

package QLTV.BUS;

import java.util.ArrayList;

import javax.swing.JOptionPane;

import QLTV.DAO.QLNCCDAO;
import QLTV.DTO.NHACUNGCAP;

public class QLNCCBUS {
    public static ArrayList<NHACUNGCAP> dsncc;
    public static ArrayList<NHACUNGCAP> htXoa = new ArrayList<NHACUNGCAP>();
    public static ArrayList<NHACUNGCAP> htSua = new ArrayList<NHACUNGCAP>();

    public QLNCCBUS() {

    }

    public void docDSNCC() throws Exception {
        QLNCCDAO data = new QLNCCDAO();
        if (dsncc == null)
            dsncc = new ArrayList<NHACUNGCAP>();
        dsncc = data.docDSSV();
    }

    public int them(NHACUNGCAP ncc) throws Exception {
        if (KTMa(ncc.getMaNCC().trim()) == 0) {
            JOptionPane.showMessageDialog(null, "Mã Nhà Xuất Bản vừa nhập bị trùng. Mời nhập lại!", "Lỗi",
                    JOptionPane.ERROR_MESSAGE);
            return -1;
        } else {
            // Truy cập vào database
            QLNCCDAO data = new QLNCCDAO();
            data.them(ncc);
            dsncc.add(ncc);
            return 1;
        }
    }

    public int sua(NHACUNGCAP nccmoi, NHACUNGCAP ncccu, int i) throws Exception {
        // Truy cập vào database
        int kt = -1;
        QLNCCDAO data = new QLNCCDAO();
        kt = data.sua(nccmoi, ncccu);
        if (kt == 0) {
            dsncc.set(i, nccmoi);
        }
        return kt;
    }

    public int xoa(String MaSV, int i) throws Exception {
        int kt = -1;
        QLNCCDAO data = new QLNCCDAO();
        kt = data.xoa(MaSV);
        if (kt == 0) {
            dsncc.remove(i);
        }
        return kt;
    }

    public int hoantacXoa(NHACUNGCAP ncc) throws Exception {
        if (KTMa(ncc.getMaNCC()) == 0) {
            JOptionPane.showMessageDialog(null, "Mã Nhà Cung Cấp vừa nhập bị trùng. Mời nhập lại!", "Lỗi",
                    JOptionPane.ERROR_MESSAGE);
            return -1;
        } else {
            // Truy cập vào database
            int kt = -1;
            QLNCCDAO data = new QLNCCDAO();
            data.hoantacXoa(ncc);
            if (kt == 0) {
                dsncc.add(ncc);
            }
            return 1;
        }
    }

    public int KTMa(String MaNXBMoi) {
        for (NHACUNGCAP ncc : dsncc)
            if (ncc.getMaNCC().trim().equals(MaNXBMoi)) {
                return 0;
            }
        return 1;
    }

    public NHACUNGCAP timTheoMa(String MaNCC) {
        for (NHACUNGCAP ncc : dsncc)
            if (ncc.getMaNCC().trim().equals(MaNCC))
                return ncc;
        return null;
    }

    public ArrayList<NHACUNGCAP> timTheoTen(String TenNCC) {
        ArrayList<NHACUNGCAP> kq = new ArrayList<NHACUNGCAP>();
        for (NHACUNGCAP ncc : dsncc)
            if (ncc.getTenNCC().indexOf(TenNCC) >= 0)
                kq.add(ncc);
        return kq;
    }

}

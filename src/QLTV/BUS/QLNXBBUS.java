package QLTV.BUS;

import java.util.ArrayList;

import javax.swing.JOptionPane;

import QLTV.DAO.QLNXBDAO;
import QLTV.DTO.NXB;

public class QLNXBBUS {
    public static ArrayList<NXB> dsnxb;
    public static ArrayList<NXB> htXoa = new ArrayList<NXB>();
    public static ArrayList<NXB> htSua = new ArrayList<NXB>();

    public QLNXBBUS() {

    }

    public void docdsnxb() throws Exception {
        QLNXBDAO data = new QLNXBDAO();
        if (dsnxb == null)
            dsnxb = new ArrayList<NXB>();
        dsnxb = data.docDSSV();
    }

    public int them(NXB nxb) throws Exception {
        if (KTMa(nxb.getMaNXB().trim()) == 0) {
            JOptionPane.showMessageDialog(null, "Mã Nhà Xuất Bản vừa nhập bị trùng. Mời nhập lại!", "Lỗi",
                    JOptionPane.ERROR_MESSAGE);
            return -1;
        }else {
            // Truy cập vào database
            QLNXBDAO data = new QLNXBDAO();
            data.them(nxb);
            dsnxb.add(nxb);
            return 1;
        }
    }

    public int sua(NXB nxbmoi, NXB nxbcu, int i) throws Exception {
        // Truy cập vào database
        int kt=0;
        QLNXBDAO data = new QLNXBDAO();
        kt=data.sua(nxbmoi, nxbcu);
        dsnxb.set(i, nxbmoi);
        return kt;
    }

    public int xoa(String MaSV, int i) throws Exception {
        int kt=0;
        QLNXBDAO data = new QLNXBDAO();
        kt=data.xoa(MaSV);
        dsnxb.remove(i);
        return kt;
    }

    public int hoantacXoa(NXB nxb) throws Exception {
        if (KTMa(nxb.getMaNXB()) == 0) {
            JOptionPane.showMessageDialog(null, "Mã Nhà Xuất Bản vừa nhập bị trùng. Mời nhập lại!", "Lỗi",
                    JOptionPane.ERROR_MESSAGE);
            return -1;
        } else {
            // Truy cập vào database
            QLNXBDAO data = new QLNXBDAO();
            data.hoantacXoa(nxb);
            dsnxb.add(nxb);
            return 1;
        }
    }

    public int KTMa(String MaNXBMoi) {
        for (NXB nxb : dsnxb)
            if (nxb.getMaNXB().trim().equals(MaNXBMoi)) {
                return 0;
            }
        return 1;
    }

    public NXB timTheoMa(String MaNXB) {
        for (NXB sach : dsnxb)
            if (sach.getMaNXB().equals(MaNXB))
                return sach;
        return null;
    }

    public ArrayList<NXB> timTheoTen(String TenNXB) {
        ArrayList<NXB> kq = new ArrayList<NXB>();
        for (NXB sach : dsnxb)
            if (sach.getTenNXB().indexOf(TenNXB) >= 0)
                kq.add(sach);
        return kq;
    }
}

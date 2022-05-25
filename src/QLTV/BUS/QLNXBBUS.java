package QLTV.BUS;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import javax.swing.JOptionPane;

import QLTV.DAO.QLNXBDAO;
import QLTV.DTO.NXB;

public class QLNXBBUS {
    public static ArrayList<NXB> dsnxb;
    public static Set<NXB> htXoa = new HashSet<NXB>();
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
        if (KTMa(nxb.getMaNXB().replaceAll("\\s+", "").toLowerCase().trim()) == 0) {
            JOptionPane.showMessageDialog(null, "Mã Nhà Xuất Bản vừa nhập bị trùng. Mời nhập lại!", "Lỗi",
                    JOptionPane.ERROR_MESSAGE);
            return -1;
        } else if (KTTenNXB(nxb.getTenNXB().replaceAll("\\s+", "").toLowerCase().trim()) == 0) {
            JOptionPane.showMessageDialog(null, "Tên Nhà Xuất Bản vừa nhập bị trùng. Mời nhập lại!", "Lỗi",
                    JOptionPane.ERROR_MESSAGE);
            return -1;
        }
        else {
            // Truy cập vào database
            int kt = - 1;
            QLNXBDAO data = new QLNXBDAO();
            kt = data.them(nxb);
            if(kt == 0)
                dsnxb.add(nxb);
            return kt;
        }
    }

    public int sua(NXB nxbmoi, NXB nxbcu, int i) throws Exception {
        // Truy cập vào database
        if(KTMa(nxbmoi.getMaNXB().replaceAll("\\s+", "").toLowerCase()) == 0){
            JOptionPane.showMessageDialog(null, "Mã Nhà Xuất Bản vừa nhập bị trùng. Mời nhập lại!", "Lỗi",
            JOptionPane.ERROR_MESSAGE);
        return -1;        
        } else if (KTTenNXB(nxbmoi.getTenNXB().replaceAll("\\s+", "").toLowerCase().trim()) == 0) {
            JOptionPane.showMessageDialog(null, "Tên Nhà Xuất Bản vừa nhập bị trùng. Mời nhập lại!", "Lỗi",
                    JOptionPane.ERROR_MESSAGE);
            return -1;
        } else {
            int kt=-1;
            QLNXBDAO data = new QLNXBDAO();
            kt=data.sua(nxbmoi, nxbcu);
            if(kt == 0)
                dsnxb.set(i, nxbmoi);
            return kt;
        }
    }

    public int xoa(String MaSV, int i) throws Exception {
        int kt=-1;
        QLNXBDAO data = new QLNXBDAO();
        kt=data.xoa(MaSV);
        if(kt == 0)
            dsnxb.remove(i);
        return kt;
    }

    public int hoantacXoa(NXB nxb) throws Exception {
        if (KTMa(nxb.getMaNXB().trim()) == 0) {
            JOptionPane.showMessageDialog(null, "Mã Nhà Xuất Bản vừa nhập bị trùng. Mời nhập lại!", "Lỗi",
                    JOptionPane.ERROR_MESSAGE);
            return -1;
        } else {
            // Truy cập vào database
            int kt = -1;
            QLNXBDAO data = new QLNXBDAO();
            kt = data.hoantacXoa(nxb);
            if(kt == 0)
                dsnxb.add(nxb);
            return kt;
        }
    }

    public int KTMa(String MaNXBMoi) {
        for (NXB nxb : dsnxb)
            if (nxb.getMaNXB().replaceAll("\\s+", "").toLowerCase().trim().equals(MaNXBMoi)) {
                return 0;
            }
        return 1;
    }

    public int KTTenNXB(String TenNXBMoi) {
        for (NXB nxb : dsnxb)
            if (nxb.getTenNXB().replaceAll("\\s+", "").toLowerCase().trim().equals(TenNXBMoi)) {
                return 0;
            }
        return 1;
    }

    public NXB timTheoMa(String MaNXB) {
        for (NXB sach : dsnxb)
            if (sach.getMaNXB().replaceAll("\\s+", "").toLowerCase().equals(MaNXB))
                return sach;
        return null;
    }

    public ArrayList<NXB> timTheoTen(String TenNXB) {
        ArrayList<NXB> kq = new ArrayList<NXB>();
        for (NXB sach : dsnxb)
            if (sach.getTenNXB().replaceAll("\\s+", "").toLowerCase().indexOf(TenNXB) >= 0)
                kq.add(sach);
        return kq;
    }
}

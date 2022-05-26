package QLTV.BUS;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import javax.swing.JOptionPane;

import QLTV.DAO.QLSACHDAO;
import QLTV.DTO.SACH;

public class QLSACHBUS {
    public static ArrayList<SACH> dssach;
    public static Set<SACH> htXoa = new HashSet<SACH>();
    public static ArrayList<SACH> htSua = new ArrayList<SACH>();

    public QLSACHBUS() {

    }

    public void docDSSACH() throws Exception {
        QLSACHDAO data = new QLSACHDAO();
        if (dssach == null)
            dssach = new ArrayList<SACH>();
        dssach = data.docDSSV();
    }

    public int them(SACH sach) throws Exception {
        if (KTMa(sach.getMasach()) == 0) {
            JOptionPane.showMessageDialog(null, "Mã sách vừa nhập bị trùng. Mời nhập lại!", "Lỗi",
                    JOptionPane.ERROR_MESSAGE);
            return -1;
        } else if (KTSL(sach.getSLtong(), sach.getSL()) == 0) {
            JOptionPane.showMessageDialog(null, "Số lượng tổng phải lớn hơn số lượng", "Lỗi",
                    JOptionPane.ERROR_MESSAGE);
            return -1;
        } else {
            // Truy cập vào database
            int kt = 0;
            QLSACHDAO data = new QLSACHDAO();
            kt = data.them(sach);
            if(kt == 0)
                dssach.add(sach);
            return kt;
        }
    }

    public int themDataExcel(SACH sach) throws Exception {
        if (KTMa(sach.getMasach().trim()) == 0) {
            JOptionPane.showMessageDialog(null, "Mã sinh viên vừa nhập bị trùng. Mời nhập lại!", "Lỗi",
                    JOptionPane.ERROR_MESSAGE);
            return -1;
        } else if (KTSL(sach.getSLtong(), sach.getSL()) == 0) {
            JOptionPane.showMessageDialog(null, "Số lương tổng phải lớn hơn số lượng", "Lỗi",
                    JOptionPane.ERROR_MESSAGE);
            return -1;
        } else {
            int kt = 0;
            // Truy cập vào database
            QLSACHDAO data = new QLSACHDAO();
            kt=data.themDataExcel(sach);
            dssach.add(sach);
            return kt;
        }
    }

    public int sua(SACH sachmoi, SACH sachcu, int i) throws Exception {
        // Truy cập vào database
        if (KTSL(sachmoi.getSLtong(), sachcu.getSL()) == 0) {
            JOptionPane.showMessageDialog(null, "Số lương tổng phải lớn hơn số lượng", "Lỗi",
                    JOptionPane.ERROR_MESSAGE);
            return -1;
        } else {
            int kt = 0;
            QLSACHDAO data = new QLSACHDAO();
            kt = data.sua(sachmoi, sachcu);
            if(kt == 0){
                dssach.set(i, sachmoi);
            }
            return kt;
        }
    }

    public int xoa(String MaSV, int i) throws Exception {
        int kt = -1;
        QLSACHDAO data = new QLSACHDAO();
        kt = data.xoa(MaSV);
        if(kt == 0)
            dssach.remove(i);
        return kt;
    }

    public int hoantacXoa(SACH sach) throws Exception {
        if (KTMa(sach.getMasach().trim()) == 0) {
            JOptionPane.showMessageDialog(null, "Mã sinh viên vừa nhập bị trùng. Mời nhập lại!", "Lỗi",
                    JOptionPane.ERROR_MESSAGE);
            return -1;
        } else if (KTSL(sach.getSLtong(), sach.getSL()) == 0) {
            JOptionPane.showMessageDialog(null, "Số lương tổng phải lớn hơn số lượng", "Lỗi",
                    JOptionPane.ERROR_MESSAGE);
            return -1;
        } else {
            // Truy cập vào database
            int kt = -1;
            QLSACHDAO data = new QLSACHDAO();
            kt = data.hoantacXoa(sach);
            if(kt == 0)
                dssach.add(sach);
            return kt;
        }
    }

    public ArrayList<SACH> loc(int NamBD, int NamKT) {
        ArrayList<SACH> kq = new ArrayList<SACH>();
        for (SACH sach : dssach) {
            if (Integer.parseInt(sach.getNamXB().trim()) >= NamBD
                    && Integer.parseInt(sach.getNamXB().trim()) <= NamKT) {
                kq.add(sach);
            }
        }

        return kq;
    }

    public int KTMa(String MaSachMoi) {
        for (SACH sach : dssach)
            if (sach.getMasach().trim().equals(MaSachMoi)) {
                return 0;
            }
        return 1;
    }

    public int KTSL(int SLtongmoi, int SLmoi) {
        if (SLtongmoi < SLmoi) {
            return 0;
        }
        return 1;
    }

    public SACH timTheoMa(String Masach) {
        for (SACH sach : dssach)
            if (sach.getMasach().toLowerCase().trim().equals(Masach))
                return sach;
        return null;
    }

    public ArrayList<SACH> timTheoTen(String Tensach) {
        ArrayList<SACH> kq = new ArrayList<SACH>();
        for (SACH sach : dssach)
            if (sach.getTensach().replaceAll("\\s+", "").toLowerCase().trim().indexOf(Tensach) >= 0)
                kq.add(sach);
        return kq;
    }

    public ArrayList<SACH> timTheoMaNXB(String MaNXB) {
        ArrayList<SACH> kq = new ArrayList<SACH>();
        for (SACH sach : dssach)
            if (sach.getMaNXB().replaceAll("\\s+", "").toLowerCase().trim().indexOf(MaNXB) >= 0)
                kq.add(sach);
        return kq;
    }

    public ArrayList<SACH> timTheoMaTG(String MaTG) {
        ArrayList<SACH> kq = new ArrayList<SACH>();
        for (SACH sach : dssach)
            if (sach.getMaTG().replaceAll("\\s+", "").toLowerCase().trim().indexOf(MaTG) >= 0)
                kq.add(sach);
        return kq;
    }

    public ArrayList<SACH> timTheoNamXB(String NamXB) {
        ArrayList<SACH> kq = new ArrayList<SACH>();
        for (SACH sach : dssach)
            if (sach.getNamXB().replaceAll("\\s+", "").trim().indexOf(NamXB) >= 0)
                kq.add(sach);
        return kq;
    }

    public ArrayList<SACH> timTheoSLtong(String SLtong) {
        ArrayList<SACH> kq = new ArrayList<SACH>();
        for (SACH sach : dssach)
            if (String.valueOf(sach.getSLtong()).indexOf(SLtong) >= 0)
                kq.add(sach);
        return kq;
    }

    public ArrayList<SACH> timTheoSL(String SL) {
        ArrayList<SACH> kq = new ArrayList<SACH>();
        for (SACH sach : dssach)
            if (String.valueOf(sach.getSL()).indexOf(SL) >= 0)
                kq.add(sach);
        return kq;
    }

    public ArrayList<SACH> timTheoDonGia(String Dongia) {
        ArrayList<SACH> kq = new ArrayList<SACH>();
        for (SACH sach : dssach)
            if (String.valueOf(sach.getDongia()).indexOf(Dongia) >= 0)
                kq.add(sach);
        return kq;
    }

    public ArrayList<SACH> timNamXBHoacSL(String NamXB, String SL) {
        ArrayList<SACH> kq = new ArrayList<SACH>();
        for (SACH sach : dssach)
            if (sach.getNamXB().trim().indexOf(NamXB) >= 0 || String.valueOf(sach.getSL()).indexOf(SL) >= 0)
                kq.add(sach);
        return kq;
    }

    public ArrayList<SACH> timNamXBVaSL(String NamXB, String SL) {
        ArrayList<SACH> kq = new ArrayList<SACH>();
        for (SACH sach : dssach)
            if (sach.getNamXB().trim().indexOf(NamXB) >= 0 && String.valueOf(sach.getSL()).indexOf(SL) >= 0)
                kq.add(sach);
        return kq;
    }

    public int ThongKeMaSach() {
        int count = dssach.size();
        return count;
    }

    public int ThongKeSLBD() {
        int sum = 0;
        for (SACH sach : dssach) {
            sum = sum + sach.getSLtong();
        }
        return sum;
    }

    public int ThongKeSLHT() {
        int sum = 0;
        for (SACH sach : dssach) {
            sum = sum + sach.getSL();
        }
        return sum;
    }
}

package QLTV.BUS;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import javax.swing.JOptionPane;

import QLTV.DAO.QLTACGIADAO;
import QLTV.DTO.TACGIA;

public class QLTACGIABUS {
    public static ArrayList<TACGIA> dstacgia;
    public static Set<TACGIA> htXoa = new HashSet<TACGIA>();
    public static ArrayList<TACGIA> htSua = new ArrayList<TACGIA>();

    public QLTACGIABUS() {

    }

    public void docdstacgia() throws Exception {
        QLTACGIADAO data = new QLTACGIADAO();
        if (dstacgia == null)
            dstacgia = new ArrayList<TACGIA>();
        dstacgia = data.docDSSV();
    }

    public int them(TACGIA tacgia) throws Exception {
        if (KTMa(tacgia.getMaTacGia()) == 0) {
            JOptionPane.showMessageDialog(null, "Mã Tác Giả vừa nhập bị trùng. Mời nhập lại!", "Lỗi",
                    JOptionPane.ERROR_MESSAGE);
            return -1;
        } else {
            // Truy cập vào database
            int kt = -1;
            QLTACGIADAO data = new QLTACGIADAO();
            kt = data.them(tacgia);
            if(kt == 0)
                dstacgia.add(tacgia);
            return kt;
        }
    }

    public int sua(TACGIA tacgiamoi, TACGIA tacgiacu, int i) throws Exception {
        // Truy cập vào database
        if (KTTen(tacgiamoi.getTenTacGia().replaceAll("\\s+", "").toLowerCase()) == 0) {
            JOptionPane.showMessageDialog(null, "Tên Tác Giả vừa nhập bị trùng. Mời nhập lại!", "Lỗi",
                    JOptionPane.ERROR_MESSAGE);
            return -1;
        } else {
            int kt = -1;
            QLTACGIADAO data = new QLTACGIADAO();
            kt = data.sua(tacgiamoi, tacgiacu);
            if (kt == 0) {
                dstacgia.set(i, tacgiamoi);
            }
            return kt;
        }
    }

    public int xoa(String MaSV, int i) throws Exception {
        int kt = -1;
        QLTACGIADAO data = new QLTACGIADAO();
        kt = data.xoa(MaSV);
        if(kt == 0)
            dstacgia.remove(i);
        return kt;
    }

    public int hoantacXoa(TACGIA tacgia) throws Exception {
        if (KTMa(tacgia.getMaTacGia()) == 0) {
            JOptionPane.showMessageDialog(null, "Mã tác giả vừa nhập bị trùng. Mời nhập lại!", "Lỗi",
                    JOptionPane.ERROR_MESSAGE);
            return -1;
        } else {
            // Truy cập vào database
            int kt = -1;
            QLTACGIADAO data = new QLTACGIADAO();
            kt = data.hoantacXoa(tacgia);
            if(kt == 0)
                dstacgia.add(tacgia);
            return kt;
        }
    }

    public int KTMa(String MaTacGiaMoi) {
        for (TACGIA tacgia : dstacgia)
            if (tacgia.getMaTacGia().replaceAll("\\s+", "").toLowerCase().equals(MaTacGiaMoi)) {
                return 0;
            }
        return 1;
    }

    public int KTTen(String MaTacGiaMoi) {
        for (TACGIA tacgia : dstacgia)
            if (tacgia.getTenTacGia().replaceAll("\\s+", "").toLowerCase().equals(MaTacGiaMoi)) {
                return 0;
            }
        return 1;
    }

    public TACGIA timTheoMa(String Matacgia) {
        for (TACGIA sach : dstacgia)
            if (sach.getMaTacGia().replaceAll("\\s+", "").toLowerCase().equals(Matacgia))
                return sach;
        return null;
    }

    public ArrayList<TACGIA> timTheoTen(String Tentacgia) {
        ArrayList<TACGIA> kq = new ArrayList<TACGIA>();
        for (TACGIA sach : dstacgia)
            if (sach.getTenTacGia().replaceAll("\\s+", "").toLowerCase().indexOf(Tentacgia) >= 0)
                kq.add(sach);
        return kq;
    }

    public int ThongKeMaTacGia() {
        int count = dstacgia.size();
        return count;
    }

}

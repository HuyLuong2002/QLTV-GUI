package QLTV.BUS;

import java.util.ArrayList;

import javax.swing.JOptionPane;

import QLTV.DAO.QLTACGIADAO;
import QLTV.DTO.TACGIA;

public class QLTACGIABUS {
    public static ArrayList<TACGIA> dstacgia;
    public static ArrayList<TACGIA> htXoa = new ArrayList<TACGIA>();
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
            QLTACGIADAO data = new QLTACGIADAO();
            data.them(tacgia);
            dstacgia.add(tacgia);
            return 1;
        }
    }

    public int sua(TACGIA tacgiamoi, TACGIA tacgiacu, int i) throws Exception {
        // Truy cập vào database
        int kt = 0;
        QLTACGIADAO data = new QLTACGIADAO();
        kt = data.sua(tacgiamoi, tacgiacu);
        if (kt == 0) {
            dstacgia.set(i, tacgiamoi);
        }
        return kt;
    }

    public void xoa(String MaSV, int i) throws Exception {
        QLTACGIADAO data = new QLTACGIADAO();
        data.xoa(MaSV);
        dstacgia.remove(i);
    }

    public int hoantacXoa(TACGIA tacgia) throws Exception {
        if (KTMa(tacgia.getMaTacGia()) == 0) {
            JOptionPane.showMessageDialog(null, "Mã tác giả vừa nhập bị trùng. Mời nhập lại!", "Lỗi",
                    JOptionPane.ERROR_MESSAGE);
            return -1;
        } else {
            // Truy cập vào database
            QLTACGIADAO data = new QLTACGIADAO();
            data.hoantacXoa(tacgia);
            dstacgia.add(tacgia);
            return 1;
        }
    }

    public int KTMa(String MaTacGiaMoi) {
        for (TACGIA tacgia : dstacgia)
            if (tacgia.getMaTacGia().equals(MaTacGiaMoi)) {
                return 0;
            }
        return 1;
    }

    public TACGIA timTheoMa(String Matacgia) {
        for (TACGIA sach : dstacgia)
            if (sach.getMaTacGia().trim().equals(Matacgia))
                return sach;
        return null;
    }

    public ArrayList<TACGIA> timTheoTen(String Tentacgia) {
        ArrayList<TACGIA> kq = new ArrayList<TACGIA>();
        for (TACGIA sach : dstacgia)
            if (sach.getTenTacGia().trim().indexOf(Tentacgia) >= 0)
                kq.add(sach);
        return kq;
    }

    public int ThongKeMaTacGia() {
        int count = dstacgia.size();
        return count;
    }

}

package QLTV.BUS;

import java.util.ArrayList;

import javax.swing.JOptionPane;

import QLTV.DAO.QLTHELOAIDAO;
import QLTV.DTO.THELOAI;

public class QLTHELOAIBUS {
    public static ArrayList<THELOAI> dstheloai;
    public static ArrayList<THELOAI> htXoa = new ArrayList<THELOAI>();
    public static ArrayList<THELOAI> htSua = new ArrayList<THELOAI>();

    public QLTHELOAIBUS() {

    }

    public void docdsnxb() throws Exception {
        QLTHELOAIDAO data = new QLTHELOAIDAO();
        if (dstheloai == null)
            dstheloai = new ArrayList<THELOAI>();
        dstheloai = data.docDSSV();
    }

    public int them(THELOAI theloai) throws Exception {
        if (KTMa(theloai.getMaTL().trim()) == 0) {
            JOptionPane.showMessageDialog(null, "Mã thể loại vừa nhập bị trùng. Mời nhập lại!", "Lỗi",
                    JOptionPane.ERROR_MESSAGE);
            return -1;
        }else {
            // Truy cập vào database
            QLTHELOAIDAO data = new QLTHELOAIDAO();
            data.them(theloai);
            dstheloai.add(theloai);
            return 1;
        }
    }

    public int sua(THELOAI theloaimoi, THELOAI theloaicu, int i) throws Exception {
        // Truy cập vào database
        int kt=0;
        QLTHELOAIDAO data = new QLTHELOAIDAO();
        kt=data.sua(theloaimoi, theloaicu);
        dstheloai.set(i, theloaimoi);
        return kt;
    }

    public int xoa(String MaTL, int i) throws Exception {
        int kt=0;
        QLTHELOAIDAO data = new QLTHELOAIDAO();
        kt=data.xoa(MaTL);
        dstheloai.remove(i);
        return kt;
    }

    public int hoantacXoa(THELOAI theloai) throws Exception {
        if (KTMa(theloai.getMaTL()) == 0) {
            JOptionPane.showMessageDialog(null, "Mã Nhà Xuất Bản vừa nhập bị trùng. Mời nhập lại!", "Lỗi",
                    JOptionPane.ERROR_MESSAGE);
            return -1;
        } else {
            // Truy cập vào database
            QLTHELOAIDAO data = new QLTHELOAIDAO();
            data.hoantacXoa(theloai);
            dstheloai.add(theloai);
            return 1;
        }
    }

    public int KTMa(String MaNXBMoi) {
        for (THELOAI theloai : dstheloai)
            if (theloai.getMaTL().trim().equals(MaNXBMoi)) {
                return 0;
            }
        return 1;
    }

    public THELOAI timTheoMa(String MaNXB) {
        for (THELOAI theloai : dstheloai)
            if (theloai.getMaTL().equals(MaNXB))
                return theloai;
        return null;
    }

    public ArrayList<THELOAI> timTheoTen(String TenTL) {
        ArrayList<THELOAI> kq = new ArrayList<THELOAI>();
        for (THELOAI theloai : dstheloai)
            if (theloai.getTenTL().indexOf(TenTL) >= 0)
                kq.add(theloai);
        return kq;
    }
}

package QLTV.BUS;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import javax.swing.JOptionPane;

import QLTV.DAO.QLTHELOAIDAO;
import QLTV.DTO.THELOAI;

public class QLTHELOAIBUS {
    public static ArrayList<THELOAI> dstheloai;
    public static Set<THELOAI> htXoa = new HashSet<THELOAI>();
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
        if (KTMa(theloai.getMaTL().replaceAll("\\s+","").toLowerCase().trim()) == 0) {
            JOptionPane.showMessageDialog(null, "Mã thể loại vừa nhập bị trùng. Mời nhập lại!", "Lỗi",
                    JOptionPane.ERROR_MESSAGE);
            return -1;
        }
        if (KTTENTL(theloai.getTenTL().replaceAll("\\s+","").toLowerCase().trim()) == 0) {
            JOptionPane.showMessageDialog(null, "Tên thể loại vừa nhập bị trùng. Mời nhập lại!", "Lỗi",
                    JOptionPane.ERROR_MESSAGE);
            return -1;
        } else {
            // Truy cập vào database
            int kt = - 1;
            QLTHELOAIDAO data = new QLTHELOAIDAO();
            kt = data.them(theloai);
            if(kt == 0)
                dstheloai.add(theloai);
            return kt;
        }
    }

    public int sua(THELOAI theloaimoi, THELOAI theloaicu, int i) throws Exception {
        // Truy cập vào database
        if (KTTENTL(theloaimoi.getTenTL().replaceAll("\\s+","").toLowerCase().trim()) == 0) {
            JOptionPane.showMessageDialog(null, "Tên thể loại vừa nhập bị trùng. Mời nhập lại!", "Lỗi",
                    JOptionPane.ERROR_MESSAGE);
            return -1;
        } else {
            int kt = -1;
            QLTHELOAIDAO data = new QLTHELOAIDAO();
            kt = data.sua(theloaimoi, theloaicu);
            if (kt == 0) {
                dstheloai.set(i, theloaimoi);
            }
            return kt;
        }
    }

    public int xoa(String MaTL, int i) throws Exception {
        int kt = -1;
        QLTHELOAIDAO data = new QLTHELOAIDAO();
        kt = data.xoa(MaTL);
        if(kt == 0)
            dstheloai.remove(i);
        return kt;
    }

    public int hoantacXoa(THELOAI theloai) throws Exception {
        if (KTMa(theloai.getMaTL().trim()) == 0) {
            JOptionPane.showMessageDialog(null, "Mã Nhà Xuất Bản vừa nhập bị trùng. Mời nhập lại!", "Lỗi",
                    JOptionPane.ERROR_MESSAGE);
            return -1;
        } else {
            // Truy cập vào database
            int kt = -1;
            QLTHELOAIDAO data = new QLTHELOAIDAO();
            kt = data.hoantacXoa(theloai);
            if (kt == 0)
                dstheloai.add(theloai);
            return kt;
        }
    }

    public int KTMa(String MaNXBMoi) {
        for (THELOAI theloai : dstheloai)
            if (theloai.getMaTL().trim().equals(MaNXBMoi)) {
                return 0;
            }
        return 1;
    }

    public int KTTENTL(String TENTLMoi) {
        for (THELOAI theloai : dstheloai)
            if (theloai.getTenTL().replaceAll("\\s+", "").toLowerCase().trim().equals(TENTLMoi)) {
                return 0;
            }
        return 1;
    }

    public THELOAI timTheoMa(String MaNXB) {
        for (THELOAI theloai : dstheloai)
            if (theloai.getMaTL().toLowerCase().trim().equals(MaNXB))
                return theloai;
        return null;
    }

    public ArrayList<THELOAI> timTheoTen(String TenTL) {
        ArrayList<THELOAI> kq = new ArrayList<THELOAI>();
        for (THELOAI theloai : dstheloai)
            if (theloai.getTenTL().replaceAll("\\s+", "").toLowerCase().trim().indexOf(TenTL) >= 0)
                kq.add(theloai);
        return kq;
    }
}

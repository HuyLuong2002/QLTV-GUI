package QLTV.BUS;

import java.util.ArrayList;

import javax.swing.JOptionPane;

import QLTV.DAO.QLNVDAO;
import QLTV.DTO.DOCGIA;

public class QLNVBUS {
    public static ArrayList<DOCGIA> dsdg;
    public static ArrayList<DOCGIA> htXoa = new ArrayList<DOCGIA>();

    public QLNVBUS() {

    }

    public void docDS() throws Exception {
        QLNVDAO data = new QLNVDAO();
        if (dsdg == null)
            dsdg = new ArrayList<DOCGIA>();
        dsdg = data.docDS();       
    }

    public int them(DOCGIA docgia) throws Exception {
        int kt = 0;
        if (KTMa(docgia.getMaDG()) == 0) {
            JOptionPane.showMessageDialog(null, "Mã độc giả vừa nhập bị trùng. Mời nhập lại!", "Lỗi",
                    JOptionPane.ERROR_MESSAGE);
            return -1;
        }else {
            // Truy cập vào database
            QLNVDAO data = new QLNVDAO();
            kt=data.themDG(docgia);
            if(kt==0){
                dsdg.add(docgia);
            }
        }
        return kt;
    }

    public int sua(DOCGIA docgiamoi, DOCGIA docgiacu,int i) throws Exception {
        // Truy cập vào database
        int kt = 0;
        QLNVDAO data = new QLNVDAO();
        kt = data.suaDG(docgiamoi,docgiacu,i);
        if(kt == 0){
            dsdg.set(i,docgiamoi);
        }
        return kt;
    }

    public int xoa(String MaDG, int i) throws Exception {
        int kt = 0;
        QLNVDAO data = new QLNVDAO();
        kt = data.xoaDG(MaDG);
        if(kt == 0){
            dsdg.remove(i);
        }
        return kt;
    }

    public int hoantacXoa(DOCGIA docgia) throws Exception {
        int kt = 0;
        if (KTMa(docgia.getMaDG().trim()) == 0) {
            JOptionPane.showMessageDialog(null, "Mã độc giả vừa nhập bị trùng. Mời nhập lại!", "Lỗi",
                    JOptionPane.ERROR_MESSAGE);
            return -1;
        }else {
            // Truy cập vào database
            QLNVDAO data = new QLNVDAO();
            kt=data.hoantacXoa(docgia);
            if (kt == 0){
                dsdg.add(docgia);
            }
            return kt;
        }
    }

    public int KTMa(String MaDGMoi) {
        for (DOCGIA docgia : dsdg)
            if (docgia.getMaDG().trim().equals(MaDGMoi)) {
                return 0;
            }
        return 1;
    }

    public DOCGIA timTheoMa(String MaDG) {
        for (DOCGIA docgia : dsdg)
            if (docgia.getMaDG().trim().equals(MaDG))
                return docgia;
        return null;
    }

    public ArrayList<DOCGIA> timTheoTen(String TenDG) {
        ArrayList<DOCGIA> kq = new ArrayList<DOCGIA>();
        for (DOCGIA docgia : dsdg)
            if (docgia.getTenDG().trim().indexOf(TenDG) >= 0)
                kq.add(docgia);
        return kq;
    }

    public ArrayList<DOCGIA> timTheoDiaChi(String Diachi) {
        ArrayList<DOCGIA> kq = new ArrayList<DOCGIA>();
        for (DOCGIA docgia : dsdg)
            if (docgia.getDiachi().trim().indexOf(Diachi) >= 0)
                kq.add(docgia);
        return kq;
    }

    public ArrayList<DOCGIA> timTheoEmail(String Email) {
        ArrayList<DOCGIA> kq = new ArrayList<DOCGIA>();
        for (DOCGIA docgia : dsdg)
            if (docgia.getMail().trim().indexOf(Email) >= 0)
                kq.add(docgia);
        return kq;
    }

    public ArrayList<DOCGIA> timTheoTinhTrangSach(String TinhTrangThue) {
        ArrayList<DOCGIA> kq = new ArrayList<DOCGIA>();
        for (DOCGIA docgia : dsdg)
            if (docgia.getTinhtrangthue().trim().indexOf(TinhTrangThue) >= 0)
                kq.add(docgia);
        return kq;
    }
}

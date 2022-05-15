package QLTV.BUS;

import java.util.ArrayList;

import javax.swing.JOptionPane;

import QLTV.DAO.QLHDTPDAO;
import QLTV.DTO.HDTIENPHAT;

public class QLHDTPBUS {
    public static ArrayList<HDTIENPHAT> dshdtp;
    public static ArrayList<HDTIENPHAT> htSua = new ArrayList<HDTIENPHAT>();

    public QLHDTPBUS() {

    }

    public void docDS() throws Exception {
        QLHDTPDAO data = new QLHDTPDAO();
        if (dshdtp == null)
            dshdtp = new ArrayList<HDTIENPHAT>();
        dshdtp = data.docDS();
    }

    public HDTIENPHAT timTheoMaHD(String MaHD) {
        for (HDTIENPHAT hd : dshdtp)
            if (hd.getMaHD().trim().equals(MaHD))
                return hd;
        return null;
    }

    public ArrayList<HDTIENPHAT> timTheoMaDG(String MaDG) {
        ArrayList<HDTIENPHAT> kq = new ArrayList<HDTIENPHAT>();
        for (HDTIENPHAT hd : dshdtp)
            if (String.valueOf(hd.getMaDG().trim()).indexOf(MaDG) >= 0)
                kq.add(hd);
        return kq;
    }

    public ArrayList<HDTIENPHAT> timTheoSLtong(String SLtong) {
        ArrayList<HDTIENPHAT> kq = new ArrayList<HDTIENPHAT>();
        for (HDTIENPHAT hd : dshdtp)
            if (String.valueOf(hd.getSL()).indexOf(SLtong) >= 0)
                kq.add(hd);
        return kq;
    }

    public ArrayList<HDTIENPHAT> timTheoTienPhat(String TienPhat) {
        ArrayList<HDTIENPHAT> kq = new ArrayList<HDTIENPHAT>();
        for (HDTIENPHAT hd : dshdtp)
            if (String.valueOf(hd.getTienphat()).indexOf(TienPhat) >= 0)
                kq.add(hd);
        return kq;
    }

    public int them(HDTIENPHAT hdtienphat) throws Exception {
        if (KTMa(hdtienphat.getMaHD()) == 0) {
            JOptionPane.showMessageDialog(null, "Mã phiếu mượn vừa nhập bị trùng. Mời nhập lại!", "Lỗi",
                    JOptionPane.ERROR_MESSAGE);
            return -1;
        } else {
            // Truy cập vào database
            int kt = 0;
            QLHDTPDAO data = new QLHDTPDAO();
            kt = data.them(hdtienphat);
            if(kt == 0){
                dshdtp.add(hdtienphat);
            }
            return kt;
        }
    }

    public int sua(HDTIENPHAT hoadonmoi, HDTIENPHAT hoadoncu, int i) throws Exception {
        // Truy cập vào database
        int kt = 0;
        QLHDTPDAO data = new QLHDTPDAO();
        kt = data.sua(hoadonmoi, hoadoncu);
        if(kt == 0){
            dshdtp.set(i, hoadonmoi);
        }
        return kt;
    }

    public int KTMa(String MaHDMoi) {
        for (HDTIENPHAT hdtienphat : dshdtp)
            if (hdtienphat.getMaHD().trim().equals(MaHDMoi)) {
                return 0;
            }
        return 1;
    }
}

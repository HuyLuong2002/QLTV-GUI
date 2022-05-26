package QLTV.BUS;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import javax.swing.JOptionPane;

import QLTV.DAO.QLHDTPDAO;
import QLTV.DTO.CHITIETHDTIENPHAT;
import QLTV.DTO.HDTIENPHAT;

public class QLHDTPBUS {
    public static ArrayList<HDTIENPHAT> dshdtp;
    public static Set<HDTIENPHAT> htXoa = new HashSet<HDTIENPHAT>();

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
            if (hd.getMaHD().toLowerCase().trim().equals(MaHD))
                return hd;
        return null;
    }

    public ArrayList<HDTIENPHAT> timTheoMaDG(String MaDG) {
        ArrayList<HDTIENPHAT> kq = new ArrayList<HDTIENPHAT>();
        for (HDTIENPHAT hd : dshdtp)
            if (String.valueOf(hd.getMaDG().toLowerCase().trim()).indexOf(MaDG) >= 0)
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
            JOptionPane.showMessageDialog(null, "Mã hóa đơn vừa nhập bị trùng. Mời nhập lại!", "Lỗi",
                    JOptionPane.ERROR_MESSAGE);
            return -1;
        } else {
            // Truy cập vào database
            int kt = 0;
            QLHDTPDAO data = new QLHDTPDAO();
            kt = data.them(hdtienphat);
            if (kt == 0) {
                dshdtp.add(hdtienphat);
            }
            return kt;
        }
    }

    public int sua(HDTIENPHAT hoadonmoi, HDTIENPHAT hoadoncu, int i) throws Exception {
        if (checkSLHD(hoadonmoi) == -1) {
            JOptionPane.showMessageDialog(null, "Số lượng tổng vượt quá số lượng hóa đơn. Mời nhập lại!", "Lỗi",
                    JOptionPane.ERROR_MESSAGE);
            return -1;
        }
        // Truy cập vào database
        int kt = 0;
        QLHDTPDAO data = new QLHDTPDAO();
        kt = data.sua(hoadonmoi, hoadoncu);
        if (kt == 0) {
            dshdtp.set(i, hoadonmoi);
        }
        return kt;
    }

    public int xoa(String MaSV, int i) throws Exception {
        int kt = -1;
        QLHDTPDAO data = new QLHDTPDAO();
        kt = data.xoa(MaSV);
        if(kt == 0)
            dshdtp.remove(i);
        return kt;
    }

    public int KTMa(String MaHDMoi) {
        for (HDTIENPHAT hdtienphat : dshdtp)
            if (hdtienphat.getMaHD().trim().equals(MaHDMoi)) {
                return 0;
            }
        return 1;
    }

    public int checkSLHD(HDTIENPHAT hdtpNew) {
        int sumSLCTHD = 0;

        for (HDTIENPHAT hdtp : QLHDTPBUS.dshdtp) {
            for (CHITIETHDTIENPHAT cthdtp : QLCTHDTPBUS.dscthdtp) {
                if (hdtp.getMaHD().trim().equals(hdtpNew.getMaHD().trim())
                        && cthdtp.getMaHD().trim().equals(hdtpNew.getMaHD().trim())) {
                    sumSLCTHD = sumSLCTHD + cthdtp.getSL();
                }
            }
        }

        if (sumSLCTHD > hdtpNew.getSL())
            return -1;

        return 0;
    }
}

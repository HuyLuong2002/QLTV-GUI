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

    public int them(HDTIENPHAT hdtienphat) throws Exception {
        if (KTMa(hdtienphat.getMaHD()) == 0) {
            JOptionPane.showMessageDialog(null, "Mã phiếu mượn vừa nhập bị trùng. Mời nhập lại!", "Lỗi",
                    JOptionPane.ERROR_MESSAGE);
            return -1;
        } else {
            // Truy cập vào database
            QLHDTPDAO data = new QLHDTPDAO();
            data.them(hdtienphat);
            dshdtp.add(hdtienphat);
            return 1;
        }
    }

    public int sua(HDTIENPHAT hoadonmoi, HDTIENPHAT hoadoncu, int i) throws Exception {
        // Truy cập vào database
        int kt = 0;
        QLHDTPDAO data = new QLHDTPDAO();
        kt = data.sua(hoadonmoi, hoadoncu);
        dshdtp.set(i, hoadonmoi);
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

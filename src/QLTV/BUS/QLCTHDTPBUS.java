package QLTV.BUS;

import java.util.ArrayList;

import javax.swing.JOptionPane;

import QLTV.DAO.QLCTHDTPDAO;
import QLTV.DTO.CHITIETHDTIENPHAT;
import QLTV.DTO.HDTIENPHAT;

public class QLCTHDTPBUS {
    public static ArrayList<CHITIETHDTIENPHAT> dscthdtp;

    public QLCTHDTPBUS() {

    }

    public void docDS() throws Exception {
        QLCTHDTPDAO data = new QLCTHDTPDAO();
        if (dscthdtp == null)
            dscthdtp = new ArrayList<CHITIETHDTIENPHAT>();
        dscthdtp = data.docDS();
    }

    public int them(CHITIETHDTIENPHAT chitiethdtienphat) throws Exception {
        if (checkSLCTHD(chitiethdtienphat) == -1) {
            JOptionPane.showMessageDialog(null,
                    "Số lượng chi tiết hóa đơn vượt quá số lượng tổng của hóa đơn. Mời nhập lại!", "Lỗi",
                    JOptionPane.ERROR_MESSAGE);
            return -1;
        } else {
            int kt = 0;
            QLCTHDTPDAO data = new QLCTHDTPDAO();
            kt = data.them(chitiethdtienphat);
            if (kt == 0)
                dscthdtp.add(chitiethdtienphat);
            return kt;
        }

    }

    public int sua(CHITIETHDTIENPHAT chitiethdtienphatMoi, String MaHDCu, String MaSachCu, int i) throws Exception {
        if (checkSLCTHD(chitiethdtienphatMoi) == -1) {
            JOptionPane.showMessageDialog(null,
                    "Số lượng chi tiết hóa đơn vượt quá số lượng tổng của hóa đơn. Mời nhập lại!", "Lỗi",
                    JOptionPane.ERROR_MESSAGE);
            return -1;
        } else {
            // Truy cập vào database
            int kt = 0;
            QLCTHDTPDAO data = new QLCTHDTPDAO();
            kt = data.sua(chitiethdtienphatMoi, MaHDCu, MaSachCu);
            if (kt == 0)
                dscthdtp.set(i, chitiethdtienphatMoi);
            return kt;
        }
    }

    public int checkSLCTHD(CHITIETHDTIENPHAT cthdtpNew) {
        // Đối tượng chi tiết hd tiền phạt mới mà người dùng muốn thêm
        int sumSLCTHD = 0;
        int maxSLtongHD = 0;
        for (HDTIENPHAT hd : QLHDTPBUS.dshdtp) {
            for (CHITIETHDTIENPHAT cthdtp : QLCTHDTPBUS.dscthdtp) {
                if (hd.getMaHD().trim().equals(cthdtpNew.getMaHD().trim())
                        && cthdtp.getMaHD().trim().equals(cthdtpNew.getMaHD().trim())) {
                    sumSLCTHD = sumSLCTHD + cthdtp.getSL();
                    maxSLtongHD = hd.getSL();
                }
                else if(hd.getMaHD().trim().equals(cthdtpNew.getMaHD().trim())) {
                    if(maxSLtongHD < hd.getSL()){
                        maxSLtongHD = hd.getSL();
                    }
                }
            }
        }
        sumSLCTHD = sumSLCTHD + cthdtpNew.getSL();
        if (sumSLCTHD != 0 && maxSLtongHD == 0)
            return 0;
        else if (sumSLCTHD > maxSLtongHD)
            return -1;
        return 0;
    }
}

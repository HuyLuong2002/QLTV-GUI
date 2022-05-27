package QLTV.BUS;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import javax.swing.JOptionPane;

import QLTV.DAO.QLCTMUONDAO;
import QLTV.DTO.CHITIETPHIEUMUON;
import QLTV.DTO.PHIEUMUON;

public class QLCTMUONBUS {
    public static ArrayList<CHITIETPHIEUMUON> dsctpm;
    public static Set<CHITIETPHIEUMUON> htXoa = new HashSet<CHITIETPHIEUMUON>();

    public QLCTMUONBUS() {

    }

    public void docDSCTPM() throws Exception {
        QLCTMUONDAO data = new QLCTMUONDAO();
        if (dsctpm == null)
            dsctpm = new ArrayList<CHITIETPHIEUMUON>();
        dsctpm = data.docDSCTPM();
    }

    public int them(CHITIETPHIEUMUON ctphieumuon) throws Exception {
        if (checkSLCTPM(ctphieumuon) == -1) {
            JOptionPane.showMessageDialog(null, "Số lượng mượn vượt quá số lượng tổng mượn. Mời nhập lại!", "Lỗi",
                    JOptionPane.ERROR_MESSAGE);
            return -1;
        } else if (checkSLCTPM(ctphieumuon) == -2) {
            JOptionPane.showMessageDialog(null, "Số lượng phải lớn hơn 0", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return -1;
        } else {
            int kt = 0;
            // Truy cập vào database
            QLCTMUONDAO data = new QLCTMUONDAO();
            kt = data.them(ctphieumuon);
            if (kt == 0) {
                dsctpm.add(ctphieumuon);
            }
            return kt;
        }
    }

    public int sua(CHITIETPHIEUMUON ctphieumuonmoi, String MaPMCTPMCu, String MasachCTPMCu, int i) throws Exception {
        if (checkSLCTPM(ctphieumuonmoi) == -1) {
            JOptionPane.showMessageDialog(null, "Số lượng mượn vượt quá số lượng tổng mượn. Mời nhập lại!", "Lỗi",
                    JOptionPane.ERROR_MESSAGE);
            return -1;
        } else if (checkSLCTPM(ctphieumuonmoi) == -2) {
            return -1;
        } else {
            // Truy cập vào database
            int kt = -1;
            QLCTMUONDAO data = new QLCTMUONDAO();
            kt = data.sua(ctphieumuonmoi, MaPMCTPMCu, MasachCTPMCu);
            if (kt == 0) {
                dsctpm.set(i, ctphieumuonmoi);
            }
            return kt;
        }
    }

    public int xoa(String MaPM, String MaSach) throws Exception {
        int kt = -1;
        int i = 0;
        QLCTMUONDAO data = new QLCTMUONDAO();
        kt = data.xoa(MaPM, MaSach);
        if (kt == 0) {
            for (CHITIETPHIEUMUON ctpm : QLCTMUONBUS.dsctpm) {
                if (ctpm.getMaPM().trim().equals(MaPM) && ctpm.getMasach().trim().equals(MaSach)) {
                    dsctpm.remove(i);
                    break;
                }
                i++;
            }
        }
        return kt;
    }

    public int checkSLCTPM(CHITIETPHIEUMUON ctpmNew) {
        int sumSLCTPM = 0;
        int maxSLtongPM = 0;
        if (ctpmNew.getSL() == 0) {
            return -2;
        }
        for (PHIEUMUON pm : QLMUONBUS.dspm) {
            for (CHITIETPHIEUMUON ctpm : QLCTMUONBUS.dsctpm) {
                if (pm.getMaPM().trim().equals(ctpmNew.getMaPM().trim())
                        && ctpm.getMaPM().trim().equals(ctpmNew.getMaPM().trim())) {
                    sumSLCTPM = sumSLCTPM + ctpm.getSL();
                    maxSLtongPM = pm.getSLtong();
                }
            }
            if (pm.getMaPM().trim().equals(ctpmNew.getMaPM().trim())) {
                if (maxSLtongPM < pm.getSLtong())
                    maxSLtongPM = pm.getSLtong();
            }
        }

        sumSLCTPM = sumSLCTPM + ctpmNew.getSL();
        if (sumSLCTPM > maxSLtongPM)
            return -1;

        return 0;
    }
}

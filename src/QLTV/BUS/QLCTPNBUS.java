package QLTV.BUS;

import java.util.ArrayList;

import javax.swing.JOptionPane;

import QLTV.DAO.QLCTPNDAO;
import QLTV.DTO.CHITIETPHIEUNHAP;
import QLTV.DTO.PHIEUNHAP;

public class QLCTPNBUS {
    public static ArrayList<CHITIETPHIEUNHAP> dsctpn;
    public static ArrayList<CHITIETPHIEUNHAP> htXoa = new ArrayList<CHITIETPHIEUNHAP>();

    public QLCTPNBUS() {
    }

    public void docDSCTPN() throws Exception {
        QLCTPNDAO data = new QLCTPNDAO();
        if (dsctpn == null)
            dsctpn = new ArrayList<CHITIETPHIEUNHAP>();
        dsctpn = data.docDSPN();
    }

    public int them(CHITIETPHIEUNHAP ctpn) throws Exception {
        if (checkSLCTPN(ctpn) == -1) {
            JOptionPane.showMessageDialog(null, "Số lượng nhập vượt quá số lượng tổng nhập. Mời nhập lại!", "Lỗi",
                    JOptionPane.ERROR_MESSAGE);
            return -1;
        } else {
            int kt = 0;
            // Truy cập vào database
            QLCTPNDAO data = new QLCTPNDAO();
            kt = data.them(ctpn);
            if (kt == 0) {
                dsctpn.add(ctpn);
            }
            return kt;
        }
    }

    public int sua(CHITIETPHIEUNHAP phieunhapMoi, String MaCTPNCu, String MaSachCTPNCu, int i) throws Exception {
        if (checkSLCTPN(phieunhapMoi) == -1) {
            JOptionPane.showMessageDialog(null, "Số lượng nhập vượt quá số lượng tổng nhập. Mời nhập lại!", "Lỗi",
                    JOptionPane.ERROR_MESSAGE);
            return -1;
        } else {
            // Truy cập vào database
            int kt = -1;
            QLCTPNDAO data = new QLCTPNDAO();
            kt = data.sua(phieunhapMoi, MaCTPNCu, MaSachCTPNCu);
            if (kt == 0) {
                dsctpn.set(i, phieunhapMoi);
            }
            return kt;
        }
    }

    public int checkSLCTPN(CHITIETPHIEUNHAP ctpnNew) {
        int sumSLCTPN = 0;
        int maxSLtongPN = 0;

        for (PHIEUNHAP pn : QLPNBUS.dspn) {
            for (CHITIETPHIEUNHAP ctpn : QLCTPNBUS.dsctpn) {
                if (pn.getMaPN().trim().equals(ctpnNew.getMaPN().trim())
                        && ctpn.getMaPN().trim().equals(ctpnNew.getMaPN().trim())) {
                    sumSLCTPN = sumSLCTPN + ctpn.getSL();
                    maxSLtongPN = pn.getSLTong();
                }
                else if(pn.getMaPN().trim().equals(ctpnNew.getMaPN().trim())){
                    if(maxSLtongPN < pn.getSLTong()){
                        maxSLtongPN = pn.getSLTong();
                    }
                }
            }
        }
        sumSLCTPN = sumSLCTPN + ctpnNew.getSL();
        if(sumSLCTPN != 0 && maxSLtongPN == 0) return 0;
        else if(sumSLCTPN > maxSLtongPN)
                return -1;

        return 0;
    }
}

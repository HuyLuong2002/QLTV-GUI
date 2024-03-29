package QLTV.BUS;

import java.util.ArrayList;

import javax.swing.JOptionPane;

import QLTV.DAO.QLPNDAO;
import QLTV.DTO.CHITIETPHIEUNHAP;
import QLTV.DTO.PHIEUNHAP;

public class QLPNBUS {
    public static ArrayList<PHIEUNHAP> dspn;

    public QLPNBUS() {

    }

    public void docDS() throws Exception {
        QLPNDAO data = new QLPNDAO();
        if (dspn == null)
            dspn = new ArrayList<PHIEUNHAP>();
        dspn = data.docDS();
    }

    public ArrayList<PHIEUNHAP> LocPM(int BDNhap, int KTNhap) {
        ArrayList<PHIEUNHAP> kq = new ArrayList<PHIEUNHAP>();
        for (PHIEUNHAP pn : dspn) {
            if (Integer.parseInt(pn.getNgaynhap().replaceAll("-", "")) >= BDNhap
                    && Integer.parseInt(pn.getNgaynhap().replaceAll("-", "")) <= KTNhap)
                kq.add(pn);
        }
        return kq;
    }

    public PHIEUNHAP timTheoMa(String MaPN) {
        for (PHIEUNHAP pn : dspn)
            if (pn.getMaPN().toLowerCase().toLowerCase().replaceAll("\\s+", "").equals(MaPN))
                return pn;
        return null;
    }

    public ArrayList<PHIEUNHAP> timTheoSLtong(String SLtong) {
        ArrayList<PHIEUNHAP> kq = new ArrayList<PHIEUNHAP>();
        for (PHIEUNHAP pn : dspn)
            if (String.valueOf(pn.getSLTong()).trim().indexOf(SLtong) >= 0)
                kq.add(pn);
        return kq;
    }

    public ArrayList<PHIEUNHAP> timTheoDonGia(String DonGia) {
        ArrayList<PHIEUNHAP> kq = new ArrayList<PHIEUNHAP>();
        for (PHIEUNHAP pn : dspn)
            if (String.valueOf(pn.getDongia()).trim().indexOf(DonGia) >= 0)
                kq.add(pn);
        return kq;
    }

    public ArrayList<PHIEUNHAP> timTheoMaNV(String MaNV) {
        ArrayList<PHIEUNHAP> kq = new ArrayList<PHIEUNHAP>();
        for (PHIEUNHAP pn : dspn)
            if (String.valueOf(pn.getMaNV().toLowerCase().replaceAll("\\s+", "").trim()).indexOf(MaNV) >= 0)
                kq.add(pn);
        return kq;
    }

    public ArrayList<PHIEUNHAP> timTheoMaNCC(String MaNCC) {
        ArrayList<PHIEUNHAP> kq = new ArrayList<PHIEUNHAP>();
        for (PHIEUNHAP pn : dspn)
            if (String.valueOf(pn.getMaNCC().toLowerCase().replaceAll("\\s+", "").trim()).indexOf(MaNCC) >= 0)
                kq.add(pn);
        return kq;
    }

    public int them(PHIEUNHAP phieunhap) throws Exception {
        if (KTMa(phieunhap.getMaPN()) == 0) {
            JOptionPane.showMessageDialog(null, "Mã phiếu mượn vừa nhập bị trùng. Mời nhập lại!", "Lỗi",
                    JOptionPane.ERROR_MESSAGE);
            return -1;
        } else {
            // Truy cập vào database
            int kt = 0;
            QLPNDAO data = new QLPNDAO();
            kt = data.them(phieunhap);
            if (kt == 0) {
                dspn.add(phieunhap);
            }
            return kt;
        }
    }

    public int sua(PHIEUNHAP phieunhapmoi, PHIEUNHAP phieunhapcu, int i) throws Exception {
        if (checkSLPN(phieunhapmoi) == -1) {
            JOptionPane.showMessageDialog(null, "Số lượng tổng nhập vượt quá số lượng nhập. Mời nhập lại!", "Lỗi",
                    JOptionPane.ERROR_MESSAGE);
            return -1;
        }
        // Truy cập vào database
        int kt = -1;
        QLPNDAO data = new QLPNDAO();
        kt = data.sua(phieunhapmoi, phieunhapcu);
        if (kt == 0) {
            dspn.set(i, phieunhapmoi);
        }
        return kt;
    }

    public int KTMa(String MaPNMoi) {
        for (PHIEUNHAP phieunhap : dspn)
            if (phieunhap.getMaPN().replaceAll("\\s+", "").trim().equals(MaPNMoi)) {
                return 0;
            }
        return 1;
    }

    public int checkSLPN(PHIEUNHAP pnNew) {
        int sumSLCTPN = 0;

        for (PHIEUNHAP pn : QLPNBUS.dspn) {
            for (CHITIETPHIEUNHAP ctpn : QLCTPNBUS.dsctpn) {
                if (pn.getMaPN().trim().equals(pnNew.getMaPN().trim())
                        && ctpn.getMaPN().trim().equals(pnNew.getMaPN().trim())) {
                    sumSLCTPN = sumSLCTPN + ctpn.getSL();
                }
            }
        }
        if (sumSLCTPN > pnNew.getSLTong())
            return -1;

        return 0;
    }
}

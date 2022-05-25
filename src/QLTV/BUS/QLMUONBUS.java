package QLTV.BUS;

import java.util.ArrayList;
import javax.swing.JOptionPane;
import QLTV.DAO.QLMUONDAO;
import QLTV.DTO.CHITIETPHIEUMUON;
import QLTV.DTO.PHIEUMUON;
import QLTV.DTO.PHIEUTRASACH;
import QLTV.GUI.QLMTGUI;

public class QLMUONBUS {
    public static ArrayList<PHIEUMUON> dspm;
    public static ArrayList<PHIEUMUON> htXoa = new ArrayList<PHIEUMUON>();

    public QLMUONBUS() {

    }

    public void docDS() throws Exception {
        QLMUONDAO data = new QLMUONDAO();
        if (dspm == null)
            dspm = new ArrayList<PHIEUMUON>();
        dspm = data.docDS();
    }

    public ArrayList<PHIEUMUON> LocPM(int BDMuon, int KTMuon) {
        ArrayList<PHIEUMUON> kq = new ArrayList<PHIEUMUON>();
        for (PHIEUMUON pm : dspm) {
            if (Integer.parseInt(pm.getNgaymuon().replaceAll("-", "")) >= BDMuon
                    && Integer.parseInt(pm.getNgaymuon().replaceAll("-", "")) <= KTMuon)
                kq.add(pm);
        }
        return kq;
    }

    public PHIEUMUON timTheoMa(String MaPM) {
        for (PHIEUMUON pm : dspm)
            if (pm.getMaPM().toLowerCase().trim().equals(MaPM))
                return pm;
        return null;
    }

    public ArrayList<PHIEUMUON> timTheoSLtong(String SLtong) {
        ArrayList<PHIEUMUON> kq = new ArrayList<PHIEUMUON>();
        for (PHIEUMUON pm : dspm)
            if (String.valueOf(pm.getSLtong()).indexOf(SLtong) >= 0)
                kq.add(pm);
        return kq;
    }

    public ArrayList<PHIEUMUON> timTheoTinhTrangMuon(String TinhTrangMuon) {
        ArrayList<PHIEUMUON> kq = new ArrayList<PHIEUMUON>();
        for (PHIEUMUON pm : dspm)
            if (String.valueOf(pm.getTinhTrangMuon().toLowerCase().replaceAll("\\s+", "").trim())
                    .indexOf(TinhTrangMuon) >= 0)
                kq.add(pm);
        return kq;
    }

    public ArrayList<PHIEUMUON> timTheoMaDG(String MaDG) {
        ArrayList<PHIEUMUON> kq = new ArrayList<PHIEUMUON>();
        for (PHIEUMUON pm : dspm)
            if (String.valueOf(pm.getMaDG().toLowerCase().trim()).indexOf(MaDG) >= 0)
                kq.add(pm);
        return kq;
    }

    public int them(PHIEUMUON phieumuon) throws Exception {
        if (KTMa(phieumuon.getMaPM()) == 0) {
            JOptionPane.showMessageDialog(null, "Mã phiếu mượn vừa nhập bị trùng. Mời nhập lại!", "Lỗi",
                    JOptionPane.ERROR_MESSAGE);
            return -1;
        } else {
            // Truy cập vào database
            int kt = 0;
            QLMUONDAO data = new QLMUONDAO();
            kt = data.them(phieumuon);
            if (kt == 0) {
                dspm.add(phieumuon);
            }
            return kt;
        }
    }

    public int sua(PHIEUMUON phieumuonmoi, PHIEUMUON phieumuoncu, int i) throws Exception {
        if (checkSLPM(phieumuonmoi) == -1) {
            JOptionPane.showMessageDialog(null, "Số lượng tổng vượt quá số lượng mượn. Mời nhập lại!", "Lỗi",
                    JOptionPane.ERROR_MESSAGE);
            return -1;
        }
        // Truy cập vào database
        int kt = -1;
        QLMUONDAO data = new QLMUONDAO();
        kt = data.sua(phieumuonmoi, phieumuoncu);
        if (kt == 0) {
            dspm.set(i, phieumuonmoi);
        }
        return kt;
    }

    public int KTMa(String MaPmMoi) {
        for (PHIEUMUON phieumuon : dspm)
            if (phieumuon.getMaPM().trim().equals(MaPmMoi)) {
                return 0;
            }
        return 1;
    }

    public int checkSLPM(PHIEUMUON pmNew) {
        int sumSLCTPM = 0;

        for (PHIEUMUON pm : QLMUONBUS.dspm) {
            for (CHITIETPHIEUMUON ctpm : QLCTMUONBUS.dsctpm) {
                if (pm.getMaPM().trim().equals(pmNew.getMaPM().trim())
                        && ctpm.getMaPM().trim().equals(pmNew.getMaPM().trim())) {
                    sumSLCTPM = sumSLCTPM + ctpm.getSL();
                }
            }
        }

        if (sumSLCTPM > pmNew.getSLtong())
            return -1;

        return 0;
    }

    public int TinhTienThue(int vtPM) throws Exception {
        int ThanhTien = 0;
        QLMUONDAO data = new QLMUONDAO();
        String NgayMuon = dspm.get(vtPM).getNgaymuon();
        int songaymuon = data.TinhTienThue(NgayMuon, QLMTGUI.NgayTra);
        if (songaymuon > 0 && songaymuon <= 15) {
            ThanhTien = PHIEUTRASACH.Tienthue * songaymuon;
        } else if (songaymuon > 15 && songaymuon <= 30 || songaymuon > 15 && songaymuon <= 31) {
            ThanhTien = (PHIEUTRASACH.Tienthue + 2000) * songaymuon;
        } else {
            ThanhTien = 0;
        }
        return ThanhTien;
    }
}

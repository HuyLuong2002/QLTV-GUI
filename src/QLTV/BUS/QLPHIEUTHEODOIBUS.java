package QLTV.BUS;

import java.util.ArrayList;

import javax.swing.JOptionPane;

import QLTV.DAO.QLPHIEUTHEODOIDAO;
import QLTV.DTO.PHIEUTHEODOIMT;

public class QLPHIEUTHEODOIBUS {
    public static ArrayList<PHIEUTHEODOIMT> dsptd;
    public static ArrayList<PHIEUTHEODOIMT> htXoa = new ArrayList<PHIEUTHEODOIMT>();

    public QLPHIEUTHEODOIBUS() {

    }

    public void docDS() throws Exception {
        QLPHIEUTHEODOIDAO data = new QLPHIEUTHEODOIDAO();
        if (dsptd == null)
            dsptd = new ArrayList<PHIEUTHEODOIMT>();
        dsptd = data.docDS();
    }

    public int them(PHIEUTHEODOIMT ptd) throws Exception {
        int kt = 0;
        if (KTMa(ptd.getMaDG()) == 0) {
            JOptionPane.showMessageDialog(null, "Mã độc giả vừa nhập bị trùng. Mời nhập lại!", "Lỗi",
                    JOptionPane.ERROR_MESSAGE);
            return -1;
        } else {
            // Truy cập vào database
            QLPHIEUTHEODOIDAO data = new QLPHIEUTHEODOIDAO();
            kt = data.them(ptd);
            if (kt == 0) {
                dsptd.add(ptd);
            }
        }
        return kt;
    }

    public int sua(PHIEUTHEODOIMT ptdmoi, PHIEUTHEODOIMT ptdcu, int i) throws Exception {
        // Truy cập vào database
        int kt = 0;
        QLPHIEUTHEODOIDAO data = new QLPHIEUTHEODOIDAO();
        kt = data.sua(ptdmoi, ptdcu, i);
        if (kt == 0) {
            dsptd.set(i, ptdmoi);
        }
        return kt;
    }

    public int xoa(String MaDG, int i) throws Exception {
        int kt = 0;
        QLPHIEUTHEODOIDAO data = new QLPHIEUTHEODOIDAO();
        kt = data.xoa(MaDG);
        if (kt == 0) {
            dsptd.remove(i);
        }
        return kt;
    }

    public int hoantacXoa(PHIEUTHEODOIMT ptd) throws Exception {
        int kt = 0;
        if (KTMa(ptd.getMaDG().trim()) == 0) {
            JOptionPane.showMessageDialog(null, "Mã độc giả vừa nhập bị trùng. Mời nhập lại!", "Lỗi",
                    JOptionPane.ERROR_MESSAGE);
            return -1;
        }else {
            // Truy cập vào database
            QLPHIEUTHEODOIDAO data = new QLPHIEUTHEODOIDAO();
            kt=data.hoantacXoa(ptd);
            if (kt == 0){
                dsptd.add(ptd);
            }
            return kt;
        }
    }

    public int KTMa(String MaDGMoi) {
        for (PHIEUTHEODOIMT ptd : dsptd)
            if (ptd.getMaDG().trim().equals(MaDGMoi)) {
                return 0;
            }
        return 1;
    }

    public PHIEUTHEODOIMT timTheoMa(String MaDG) {
        for (PHIEUTHEODOIMT ptd : dsptd) {
            if (ptd.getMaDG().trim().equals(MaDG))
                return ptd;
        }
        return null;
    }

    public ArrayList<PHIEUTHEODOIMT> timTheoTongMuon(String TongMuon) {
        ArrayList<PHIEUTHEODOIMT> kq = new ArrayList<PHIEUTHEODOIMT>();
        for (PHIEUTHEODOIMT ptd : dsptd)
            if (String.valueOf(ptd.getTongmuon()).indexOf(TongMuon) >= 0)
                kq.add(ptd);
        return kq;
    }

    public ArrayList<PHIEUTHEODOIMT> timTheoTienCoc(String TienCoc) {
        ArrayList<PHIEUTHEODOIMT> kq = new ArrayList<PHIEUTHEODOIMT>();
        for (PHIEUTHEODOIMT ptd : dsptd)
            if (String.valueOf(ptd.getTiencoc()).indexOf(TienCoc) >= 0)
                kq.add(ptd);
        return kq;
    }


}

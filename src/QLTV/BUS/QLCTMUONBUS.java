package QLTV.BUS;

import java.util.ArrayList;
import javax.swing.JOptionPane;
import QLTV.DAO.QLCTMUONDAO;
import QLTV.DTO.CHITIETPHIEUMUON;

public class QLCTMUONBUS {
    public static ArrayList<CHITIETPHIEUMUON> dsctpm;
    public static ArrayList<CHITIETPHIEUMUON> htXoa = new ArrayList<CHITIETPHIEUMUON>();
    public static ArrayList<CHITIETPHIEUMUON> htSua = new ArrayList<CHITIETPHIEUMUON>();
    public QLCTMUONBUS() {

    }

    public void docDSCTPM() throws Exception {
        QLCTMUONDAO data = new QLCTMUONDAO();
        if (dsctpm == null)
            dsctpm = new ArrayList<CHITIETPHIEUMUON>();
        dsctpm = data.docDSCTPM();
    }

    public int them(CHITIETPHIEUMUON ctphieumuon) throws Exception {
        // if (KTMa(ctphieumuon.getMaPM()) == 0) {
        //     JOptionPane.showMessageDialog(null, "Mã phiếu mượn vừa nhập bị trùng. Mời nhập lại!", "Lỗi",
        //             JOptionPane.ERROR_MESSAGE);
        //     return -1;
        // } else {
        //     // Truy cập vào database
        //     QLCTMUONDAO data = new QLCTMUONDAO();
        //     data.them(ctphieumuon);
        //     dsctpm.add(ctphieumuon);
        //     return 1;
        // }
        // Truy cập vào database
        QLCTMUONDAO data = new QLCTMUONDAO();
        data.them(ctphieumuon);
        dsctpm.add(ctphieumuon);
        return 1;
    }

    public void sua(CHITIETPHIEUMUON phieumuonmoi, CHITIETPHIEUMUON phieumuoncu, int i) throws Exception {
        // Truy cập vào database
        QLCTMUONDAO data = new QLCTMUONDAO();
        data.sua(phieumuonmoi, phieumuoncu);
        dsctpm.set(i, phieumuonmoi);
    }
    public int KTMa(String MaCTPmMoi) {
        for (CHITIETPHIEUMUON phieumuon : dsctpm)
            if (phieumuon.getMaPM().trim().equals(MaCTPmMoi)) {
                return 0;
            }
        return 1;
    }
}

package QLTV.BUS;

import java.util.ArrayList;

import javax.swing.JOptionPane;

import QLTV.DAO.QLCTMUONDAO;
import QLTV.DTO.CHITIETPHIEUMUON;

public class QLCTMUONBUS {
    public static ArrayList<CHITIETPHIEUMUON> dsctpm;
    public static ArrayList<CHITIETPHIEUMUON> htXoa = new ArrayList<CHITIETPHIEUMUON>();
    public QLCTMUONBUS() {

    }

    public void docDSCTPM() throws Exception {
        QLCTMUONDAO data = new QLCTMUONDAO();
        if (dsctpm == null)
            dsctpm = new ArrayList<CHITIETPHIEUMUON>();
        dsctpm = data.docDSCTPM();
    }

    public int them(CHITIETPHIEUMUON ctphieumuon) throws Exception {
        int kt = 0;
        // Truy cập vào database
        QLCTMUONDAO data = new QLCTMUONDAO();
        kt=data.them(ctphieumuon);
        if(kt==0){
            dsctpm.add(ctphieumuon);
        }
        return kt;
    }

    public int sua(CHITIETPHIEUMUON phieumuonmoi, String MaPMCTPMCu, String MasachCTPMCu, int i) throws Exception {
        // Truy cập vào database
        int kt = -1;
        QLCTMUONDAO data = new QLCTMUONDAO();
        kt = data.sua(phieumuonmoi, MaPMCTPMCu, MasachCTPMCu);
        if(kt == 0){
            dsctpm.set(i, phieumuonmoi);
        }
        return kt;
    }
}

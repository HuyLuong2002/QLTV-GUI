package QLTV.BUS;

import java.util.ArrayList;

import QLTV.DAO.QLCTHDTPDAO;
import QLTV.DTO.CHITIETHDTIENPHAT;

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
        int kt = 0;
        QLCTHDTPDAO data = new QLCTHDTPDAO();
        kt = data.them(chitiethdtienphat);
        if(kt == 0)
            dscthdtp.add(chitiethdtienphat);
        return kt;
    }

    public int sua(CHITIETHDTIENPHAT chitiethdtienphatMoi, String MaHDCu, String MaSachCu, int i) throws Exception {
        // Truy cập vào database
        int kt = 0;
        QLCTHDTPDAO data = new QLCTHDTPDAO();
        kt = data.sua(chitiethdtienphatMoi, MaHDCu, MaSachCu);
        if(kt == 0)
            dscthdtp.set(i, chitiethdtienphatMoi);
        return kt;
    }
}

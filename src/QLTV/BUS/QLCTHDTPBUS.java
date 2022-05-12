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
        QLCTHDTPDAO data = new QLCTHDTPDAO();
        data.them(chitiethdtienphat);
        dscthdtp.add(chitiethdtienphat);
        return 1;
    }

    public void sua(CHITIETHDTIENPHAT chitiethdtienphatMoi, CHITIETHDTIENPHAT chitiethdtienphatCu, int i) throws Exception {
        // Truy cập vào database
        QLCTHDTPDAO data = new QLCTHDTPDAO();
        data.sua(chitiethdtienphatMoi, chitiethdtienphatCu);
        dscthdtp.set(i, chitiethdtienphatMoi);
    }
}

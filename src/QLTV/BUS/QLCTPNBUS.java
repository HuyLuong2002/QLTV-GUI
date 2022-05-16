package QLTV.BUS;

import java.util.ArrayList;

import QLTV.DAO.QLCTPNDAO;
import QLTV.DTO.CHITIETPHIEUNHAP;

public class QLCTPNBUS {
    public static ArrayList<CHITIETPHIEUNHAP> dsctpn;
    public static ArrayList<CHITIETPHIEUNHAP> htXoa = new ArrayList<CHITIETPHIEUNHAP>();
    public QLCTPNBUS(){}

    public void docDSCTPN() throws Exception{
        QLCTPNDAO data = new QLCTPNDAO();
        if(dsctpn == null)
            dsctpn = new ArrayList<CHITIETPHIEUNHAP>();
        dsctpn = data.docDSPN();
    }

    public int them(CHITIETPHIEUNHAP ctpn) throws Exception {
        int kt = 0;
        // Truy cập vào database
        QLCTPNDAO data = new QLCTPNDAO();
        kt=data.them(ctpn);
        if(kt==0){
            dsctpn.add(ctpn);
        }
        return kt;
    }

    public int sua(CHITIETPHIEUNHAP phieunhapMoi, String MaCTPNCu, String MaSachCTPNCu, int i) throws Exception {
        // Truy cập vào database
        int kt = -1;
        QLCTPNDAO data = new QLCTPNDAO();
        kt = data.sua(phieunhapMoi, MaCTPNCu, MaSachCTPNCu);
        if(kt == 0){
            dsctpn.set(i, phieunhapMoi);
        }
        return kt;
    }
}

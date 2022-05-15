package QLTV.BUS;

import java.util.ArrayList;

import QLTV.DAO.ThongKeDAO;
import QLTV.DTO.CHITIETPHIEUMUON;
import QLTV.DTO.PHIEUMUON;

public class ThongKeBUS {
    public ArrayList<CHITIETPHIEUMUON> getTop5LuotMuon() throws Exception {
        ThongKeDAO data = new ThongKeDAO();
        ArrayList<CHITIETPHIEUMUON> dskq = new ArrayList<CHITIETPHIEUMUON>();
        dskq = data.getTop5LuotMuon();
        return dskq;
    }

    public ArrayList<PHIEUMUON> getPMTheoQuy1(String year) {
        ArrayList<PHIEUMUON> kq = new ArrayList<PHIEUMUON>();
        try {
            ThongKeDAO data = new ThongKeDAO();
            kq = data.getPMTheoQuy1(year);
        } catch (Exception e) {
            System.out.println(e);
        }
        return kq;
    }

    public ArrayList<PHIEUMUON> getPMTheoQuy2(String year) {
        ArrayList<PHIEUMUON> kq = new ArrayList<PHIEUMUON>();
        try {
            ThongKeDAO data = new ThongKeDAO();
            kq = data.getPMTheoQuy2(year);
        } catch (Exception e) {
            System.out.println(e);
        }
        return kq;
    }

    public ArrayList<PHIEUMUON> getPMTheoQuy3(String year) {
        ArrayList<PHIEUMUON> kq = new ArrayList<PHIEUMUON>();
        try {
            ThongKeDAO data = new ThongKeDAO();
            kq = data.getPMTheoQuy3(year);
        } catch (Exception e) {
            System.out.println(e);
        }
        return kq;
    }

    public ArrayList<PHIEUMUON> getPMTheoQuy4(String year) {
        ArrayList<PHIEUMUON> kq = new ArrayList<PHIEUMUON>();
        try {
            ThongKeDAO data = new ThongKeDAO();
            kq = data.getPMTheoQuy4(year);
        } catch (Exception e) {
            System.out.println(e);
        }
        return kq;
    }

    public double getDoanhThuThang(int thang, int nam) throws Exception {
        ThongKeDAO data = new ThongKeDAO();
        return data.getDoanhThuThang(thang, nam);
    }

}

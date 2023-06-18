package QLTV.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import MyCustom.MSSQLConnect;
import QLTV.DTO.CHITIETPHIEUMUON;
import QLTV.DTO.PHIEUMUON;

public class ThongKeDAO {
    Connection conn = null;
    Statement st = null;
    ResultSet rs = null;

    public ThongKeDAO() throws Exception {
        MSSQLConnect connect = new MSSQLConnect();
        conn = connect.getConnection();
    }
    
    public ArrayList<CHITIETPHIEUMUON> getTop5LuotMuon(){
        ArrayList<CHITIETPHIEUMUON> dskq = new ArrayList<CHITIETPHIEUMUON>();
        try {
            // String qry = "SELECT TOP" + "(5)" + " MASACH," +  "SUM" + "(SL)" +
            // "FROM CHITIETPHIEUMUON " + 
            // "GROUP BY MASACH " +
            // "ORDER BY SUM" + "(SL)" + "DESC";
            String qry = "SELECT" + " MASACH," +  "SUM" + "(SL)" +
            "FROM CHITIETPHIEUMUON " + 
            "GROUP BY MASACH " +
            "ORDER BY SUM" + "(SL)" + "DESC LIMIT 5";

            st=conn.createStatement();
            rs=st.executeQuery(qry);
            while(rs.next()){
                CHITIETPHIEUMUON ctpm = new CHITIETPHIEUMUON();
                ctpm.setMasach(rs.getString(1));
                ctpm.setSL(Integer.parseInt(rs.getString(2)));
                dskq.add(ctpm);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return dskq;
    }
 
    public ArrayList<PHIEUMUON> getPMTheoQuy1(String year) {
        ArrayList<PHIEUMUON> kq = new ArrayList<PHIEUMUON>();
        try {

            String qry = "select * from PHIEUMUON WHERE NGAYMUON >= '" + year + "/01/01'" +
                    "AND NGAYMUON <= '" + year + "/03/31'";
            st = conn.createStatement();
            rs = st.executeQuery(qry);
            while (rs.next()) {
                PHIEUMUON pm = new PHIEUMUON();
                pm.setMaPM(rs.getString(1));
                pm.setNgaymuon(rs.getString(2));
                pm.setSLtong(Integer.parseInt(rs.getString(3)));
                pm.setNgaytra(rs.getString(4));
                pm.setTinhTrangMuon(rs.getString(5));
                pm.setMaDG(rs.getString(6));
                kq.add(pm);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return kq;
    }

    public ArrayList<PHIEUMUON> getPMTheoQuy2(String year) {
        ArrayList<PHIEUMUON> kq = new ArrayList<PHIEUMUON>();
        try {

            String qry = "select * from PHIEUMUON WHERE NGAYMUON >= '" + year + "/04/01'" +
                    "AND NGAYMUON <= '" + year + "/06/30'";
            st = conn.createStatement();
            rs = st.executeQuery(qry);
            while (rs.next()) {
                PHIEUMUON pm = new PHIEUMUON();
                pm.setMaPM(rs.getString(1));
                pm.setNgaymuon(rs.getString(2));
                pm.setSLtong(Integer.parseInt(rs.getString(3)));
                pm.setNgaytra(rs.getString(4));
                pm.setTinhTrangMuon(rs.getString(5));
                pm.setMaDG(rs.getString(6));
                kq.add(pm);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return kq;
    }

    public ArrayList<PHIEUMUON> getPMTheoQuy3(String year) {
        ArrayList<PHIEUMUON> kq = new ArrayList<PHIEUMUON>();
        try {

            String qry = "select * from PHIEUMUON WHERE NGAYMUON >= '" + year + "/07/01'" +
                    "AND NGAYMUON <= '" + year + "/09/30'";
            st = conn.createStatement();
            rs = st.executeQuery(qry);
            while (rs.next()) {
                PHIEUMUON pm = new PHIEUMUON();
                pm.setMaPM(rs.getString(1));
                pm.setNgaymuon(rs.getString(2));
                pm.setSLtong(Integer.parseInt(rs.getString(3)));
                pm.setNgaytra(rs.getString(4));
                pm.setTinhTrangMuon(rs.getString(5));
                pm.setMaDG(rs.getString(6));
                kq.add(pm);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return kq;
    }

    public ArrayList<PHIEUMUON> getPMTheoQuy4(String year) {
        ArrayList<PHIEUMUON> kq = new ArrayList<PHIEUMUON>();
        try {

            String qry = "select * from PHIEUMUON WHERE NGAYMUON >= '" + year + "/10/01'" +
                    "AND NGAYMUON <= '" + year + "/12/31'";
            st = conn.createStatement();
            rs = st.executeQuery(qry);
            while (rs.next()) {
                PHIEUMUON pm = new PHIEUMUON();
                pm.setMaPM(rs.getString(1));
                pm.setNgaymuon(rs.getString(2));
                pm.setSLtong(Integer.parseInt(rs.getString(3)));
                pm.setNgaytra(rs.getString(4));
                pm.setTinhTrangMuon(rs.getString(5));
                pm.setMaDG(rs.getString(6));
                kq.add(pm);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return kq;
    }

    public double getDoanhThuThang(int thang, int nam) {
        try {
            String sql = "SELECT SUM(THANHTIEN) FROM PHIEUTRASACH WHERE MONTH(NGAYTRA)=? AND YEAR(NGAYTRA)=?";
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setString(1, String.valueOf(thang));
            pre.setString(2, String.valueOf(nam));
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                return Double.parseDouble(rs.getInt(1) + "");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return nam;
    }
    
}

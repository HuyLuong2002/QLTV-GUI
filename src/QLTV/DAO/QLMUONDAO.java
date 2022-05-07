package QLTV.DAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import MyCustom.MSSQLConnect;
import QLTV.DTO.PHIEUMUON;

public class QLMUONDAO {
    Connection conn = null;
    Statement st = null;
    ResultSet rs = null;

    public QLMUONDAO() throws Exception {
        MSSQLConnect connect = new MSSQLConnect();
        conn = connect.getConnection();
    }

    public ArrayList<PHIEUMUON> docDSPM() {
        ArrayList<PHIEUMUON> dsmuon = new ArrayList<PHIEUMUON>();
        try {
            String qry = "select * from PHIEUMUON";
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
                dsmuon.add(pm);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Đọc dữ liệu thất bại", "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
        return dsmuon;
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
}

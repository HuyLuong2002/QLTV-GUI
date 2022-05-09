package QLTV.DAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;
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

    public ArrayList<PHIEUMUON> docDS() {
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
    public void them(PHIEUMUON phieumuon) {
        try {
            String qry = "INSERT INTO PHIEUMUON VALUES (?,?,?,?,?,?)";
            PreparedStatement ps = conn.prepareStatement(qry);
            ps.setString(1, phieumuon.getMaPM());
            ps.setString(2, phieumuon.getNgaymuon());
            ps.setString(3, String.valueOf(phieumuon.getSLtong()));
            ps.setString(4, phieumuon.getNgaytra());
            ps.setString(5, phieumuon.getTinhTrangMuon());
            ps.setString(6, phieumuon.getMaDG());

            int n = ps.executeUpdate();
            if (n != 0) {
                JOptionPane.showMessageDialog(null, "Thêm dữ liệu thành công", "Thông báo",
                        JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (SQLException e) {
            System.out.println(e);
            JOptionPane.showMessageDialog(null, "Thêm dữ liệu thất bại", "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void sua(PHIEUMUON phieumuonMoi, PHIEUMUON phieumuonCu){
        try {
            String qry = "UPDATE PHIEUMUON SET MAPM= ?, NGAYMUON= ?, SLTONG= ?, NGAYTRA= ?, MADG= ?" + "WHERE MAPM= ?";
            PreparedStatement ps = conn.prepareStatement(qry);
            ps.setString(1, phieumuonMoi.getMaPM());
            ps.setString(2, phieumuonMoi.getNgaymuon());
            ps.setString(3, String.valueOf(phieumuonMoi.getSLtong()));
            ps.setString(4, phieumuonMoi.getNgaytra());
            ps.setString(5, phieumuonMoi.getMaDG());
            ps.setString(6, phieumuonCu.getMaPM());

            int n = ps.executeUpdate();
            if (n != 0) {
                JOptionPane.showMessageDialog(null, "Thêm dữ liệu thành công", "Thông báo",
                        JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (Exception e) {
            System.out.println(e);
            JOptionPane.showMessageDialog(null, "Thêm dữ liệu thất bại", "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void xoa(String MaPM) {
        try {
            String qry = "delete from SACH where MASACH='" + MaPM + "'";
            st = conn.createStatement();
            st.executeUpdate(qry);
            if (st != null) {
                JOptionPane.showMessageDialog(null, "Xóa dữ liệu thành công", "Thông báo",
                        JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Xóa dữ liệu thất bại", "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }
}

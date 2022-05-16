package QLTV.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import MyCustom.MSSQLConnect;
import QLTV.DTO.DOCGIA;

public class QLNVDAO {
    Connection conn = null;
    Statement st = null;
    ResultSet rs = null;

    public QLNVDAO() throws Exception {
        MSSQLConnect connect = new MSSQLConnect();
        conn = connect.getConnection();
    }

    public ArrayList<DOCGIA> docDS() {
        ArrayList<DOCGIA> dsdg = new ArrayList<DOCGIA>();
        try {
            String qry = "select * from DOCGIA";
            st = conn.createStatement();
            rs = st.executeQuery(qry);
            while (rs.next()) {
                DOCGIA dg = new DOCGIA();
                dg.setMaDG(rs.getString(1));
                dg.setTenDG(rs.getString(2));
                dg.setDiachi(rs.getString(3));
                dg.setMail(rs.getString(4));
                dg.setTinhtrangthue(rs.getString(5));
                dsdg.add(dg);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Đọc dữ liệu thất bại", "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
        return dsdg;
    }

    public void themDG(DOCGIA docgia) {
        try {
            String qry = "INSERT INTO DOCGIA VALUES (?,?,?,?,?,?,?,?)";
            PreparedStatement ps = conn.prepareStatement(qry);
            ps.setString(1, docgia.getMaDG());
            ps.setString(2, docgia.getTenDG());
            ps.setString(3, docgia.getDiachi());
            ps.setString(4, docgia.getMail());
            ps.setString(5, docgia.getTinhtrangthue());

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

    public void hoantacXoa(DOCGIA docgia) {
        try {
            String qry = "insert into DOCGIA values  (" + "'" + docgia.getMaDG() + "'"
                    + "," + "N'" + docgia.getTenDG() + "'" + "," + "'" + "N'" + docgia.getDiachi() + "'" + ","
                    + "'" + docgia.getMail() + "'" + "," + "'" + "N'" + docgia.getTinhtrangthue() + "'" + ")";
            st = conn.createStatement();
            st.executeUpdate(qry);
        } catch (SQLException e) {
            System.out.println(e);
            JOptionPane.showMessageDialog(null, "Thêm dữ liệu thất bại", "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void suaDG(DOCGIA docgiamoi, DOCGIA docgiacu,int i) {
        try {
            String qry = "update DOCGIA set " + "MADG=" + "'" + docgiamoi.getMaDG() + "'" +
                    ",TENDG=" + "N'" + docgiamoi.getTenDG() + "'" + ",DIACHI=" + "'" + "N'" + docgiamoi.getDiachi() + "'" +
                    ",MAIL=" + "'" + docgiamoi.getMail() + "'" + ",TINHTRANGTHUE=" + "'" + "N'" + docgiamoi.getTinhtrangthue() + 
                    " " + "where MADG='" + docgiacu.getMaDG()
                    + "'";
            st = conn.createStatement();
            st.executeUpdate(qry);
            if (st != null) {
                JOptionPane.showMessageDialog(null, "Sửa dữ liệu thành công", "Thông báo",
                        JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Sửa dữ liệu thất bại", "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void xoaDG(String MaDG) {
        try {
            String qry = "delete from DOCGIA where MADG='" + MaDG + "'";
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

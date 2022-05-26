package QLTV.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import MyCustom.MSSQLConnect;
import QLTV.DTO.THELOAI;

public class QLTHELOAIDAO {
    Connection conn = null;
    Statement st = null;
    ResultSet rs = null;

    public QLTHELOAIDAO() throws Exception {
        MSSQLConnect connect = new MSSQLConnect();
        conn = connect.getConnection();
    }

    public ArrayList<THELOAI> docDSSV() {
        ArrayList<THELOAI> dsnxb = new ArrayList<THELOAI>();
        try {
            String qry = "select * from THELOAI";
            st = conn.createStatement();
            rs = st.executeQuery(qry);
            while (rs.next()) {
                THELOAI theloai = new THELOAI();
                theloai.setMaTL(rs.getString(1));
                theloai.setTenTL(rs.getString(2));
                theloai.setSLTL(Integer.valueOf(rs.getString(3)));
                dsnxb.add(theloai);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Đọc dữ liệu thất bại", "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
        return dsnxb;
    }

    public int them(THELOAI theloai) {
        try {
            String qry = "INSERT INTO THELOAI VALUES (?,?,?)";
            PreparedStatement ps = conn.prepareStatement(qry);
            ps.setString(1, theloai.getMaTL());
            ps.setString(2, theloai.getTenTL());
            ps.setString(3, String.valueOf(theloai.getSLTL()));
            int n = ps.executeUpdate();
            if (n != 0) {
                JOptionPane.showMessageDialog(null, "Thêm dữ liệu thành công", "Thông báo",
                        JOptionPane.INFORMATION_MESSAGE);
            }
            return 0;
        } catch (SQLException e) {
            System.out.println(e);
            JOptionPane.showMessageDialog(null, "Thêm dữ liệu thất bại", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return -1;
        }
    }

    public int hoantacXoa(THELOAI theloai) {
        try {
            String qry = "INSERT INTO THELOAI VALUES (?,?,?)";
            PreparedStatement ps = conn.prepareStatement(qry);
            ps.setString(1, theloai.getMaTL());
            ps.setString(2, theloai.getTenTL());
            ps.setString(3, String.valueOf(theloai.getSLTL()));
            return 0;
        } catch (SQLException e) {
            return -1;
        }
    }

    public int sua(THELOAI theloaimoi,THELOAI theloaicu) {
        try {
            String qry = "update THELOAI set " + "MATL=" + "'" + theloaimoi.getMaTL() + "'" +
                    ",TENTL=" + "N'" + theloaimoi.getTenTL() + "'" + " ,SLTL=" + "'" + theloaimoi.getSLTL() + "'" + " " + "where MATL='" + theloaicu.getMaTL().trim()
                    + "'";
            st = conn.createStatement();
            st.executeUpdate(qry);
            if (st != null) {
                JOptionPane.showMessageDialog(null, "Sửa dữ liệu thành công", "Thông báo",
                        JOptionPane.INFORMATION_MESSAGE);
            }
            return 0;
        } catch (SQLException e) {
            System.out.println(e);
            JOptionPane.showMessageDialog(null, "Sửa dữ liệu thất bại", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return -1;
        }
    }

    public int xoa(String MaTL) {
        try {
            String qry = "delete from theloai where MATL='" + MaTL + "'";
            st = conn.createStatement();
            st.executeUpdate(qry);
            if (st != null) {
                JOptionPane.showMessageDialog(null, "Xóa dữ liệu thành công", "Thông báo",
                        JOptionPane.INFORMATION_MESSAGE);
            }
            return 0;
        } catch (SQLException e) {
            System.out.println(e);
            JOptionPane.showMessageDialog(null, "Xóa dữ liệu thất bại", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return -1;
        }
    }

}

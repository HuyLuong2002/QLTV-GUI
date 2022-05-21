package QLTV.DAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import MyCustom.MSSQLConnect;
import QLTV.DTO.TACGIA;

public class QLTACGIADAO {
    Connection conn = null;
    Statement st = null;
    ResultSet rs = null;

    public QLTACGIADAO() throws Exception {
        MSSQLConnect connect = new MSSQLConnect();
        conn = connect.getConnection();
    }

    public ArrayList<TACGIA> docDSSV() {
        ArrayList<TACGIA> dstacgia = new ArrayList<TACGIA>();
        try {
            String qry = "select * from TACGIA";
            st = conn.createStatement();
            rs = st.executeQuery(qry);
            while (rs.next()) {
                TACGIA tacgia = new TACGIA();
                tacgia.setMaTacGia(rs.getString(1));
                tacgia.setTenTacGia(rs.getString(2));
                dstacgia.add(tacgia);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Đọc dữ liệu thất bại", "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
        return dstacgia;
    }

    public void them(TACGIA tacgia) {
        try {
            String qry = "insert into TACGIA values  (" + "'" + tacgia.getMaTacGia() + "'"
                    + "," + "N'" + tacgia.getTenTacGia() + "'" + ")";
            st = conn.createStatement();
            st.executeUpdate(qry);
            if (st != null) {
                JOptionPane.showMessageDialog(null, "Thêm dữ liệu thành công", "Thông báo",
                        JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (SQLException e) {
            System.out.println(e);
            JOptionPane.showMessageDialog(null, "Thêm dữ liệu thất bại", "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void hoantacXoa(TACGIA tacgia) {
        try {
            String qry = "insert into TACGIA values  (" + "'" + tacgia.getMaTacGia() + "'"
                    + "," + "N'" + tacgia.getTenTacGia() + "'" + ")";
            st = conn.createStatement();
            st.executeUpdate(qry);
        } catch (SQLException e) {
            System.out.println(e);
            JOptionPane.showMessageDialog(null, "Thêm dữ liệu thất bại", "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }

    public int sua(TACGIA tacgiamoi, TACGIA tacgiacu) {
        try {
            String qry = "update TACGIA set " + "MATG=" + "'" + tacgiamoi.getMaTacGia() + "'" +
                    ",TENTG=" + "N'" + tacgiamoi.getTenTacGia() + "'" + " " + "where MATG='" + tacgiacu.getMaTacGia()
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

    public void xoa(String MaTacGia) {
        try {
            String qry = "delete from TACGIA where MATG='" + MaTacGia + "'";
            st = conn.createStatement();
            st.executeUpdate(qry);
            if (st != null) {
                JOptionPane.showMessageDialog(null, "Xóa dữ liệu thành công", "Thông báo",
                        JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (SQLException e) {
            System.out.println(e);
            JOptionPane.showMessageDialog(null, "Xóa dữ liệu thất bại", "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }

}

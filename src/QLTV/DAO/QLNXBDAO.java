package QLTV.DAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import MyCustom.MSSQLConnect;
import QLTV.DTO.NXB;

public class QLNXBDAO {
    Connection conn = null;
    Statement st = null;
    ResultSet rs = null;

    public QLNXBDAO() throws Exception {
        MSSQLConnect connect = new MSSQLConnect();
        conn = connect.getConnection();
    }

    public ArrayList<NXB> docDSSV() {
        ArrayList<NXB> dsnxb = new ArrayList<NXB>();
        try {
            String qry = "select * from NXB";
            st = conn.createStatement();
            rs = st.executeQuery(qry);
            while (rs.next()) {
                NXB nxb = new NXB();
                nxb.setMaNXB(rs.getString(1));
                nxb.setTenNXB(rs.getString(2));
                dsnxb.add(nxb);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Đọc dữ liệu thất bại", "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
        return dsnxb;
    }

    public void them(NXB nxb) {
        try {
            String qry = "insert into NXB values  (" + "'" + nxb.getMaNXB().trim() + "'"
                    + "," + "N'" + nxb.getTenNXB() + "'" + ")";
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

    public void hoantacXoa(NXB nxb) {
        try {
            String qry = "insert into NXB values  (" + "'" + nxb.getMaNXB() + "'"
                    + "," + "N'" + nxb.getTenNXB() + "'" + ")";
            st = conn.createStatement();
            st.executeUpdate(qry);
        } catch (SQLException e) {
            System.out.println(e);
            JOptionPane.showMessageDialog(null, "Thêm dữ liệu thất bại", "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }

    public int sua(NXB nxbmoi, NXB nxbcu) {
        try {
            String qry = "update NXB set " + "MANXB=" + "'" + nxbmoi.getMaNXB() + "'" +
                    ",TENNXB=" + "N'" + nxbmoi.getTenNXB() + "'" + " " + "where MANXB='" + nxbcu.getMaNXB()
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

    public int xoa(String MaNXB) {
        try {
            String qry = "delete from NXB where MANXB='" + MaNXB + "'";
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

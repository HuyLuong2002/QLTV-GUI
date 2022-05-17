package QLTV.DAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import MyCustom.MSSQLConnect;
import QLTV.DTO.NHACUNGCAP;

public class QLNCCDAO {
    Connection conn = null;
    Statement st = null;
    ResultSet rs = null;

    public QLNCCDAO() throws Exception {
        MSSQLConnect connect = new MSSQLConnect();
        conn = connect.getConnection();
    }

    public ArrayList<NHACUNGCAP> docDSSV() {
        ArrayList<NHACUNGCAP> dsncc = new ArrayList<NHACUNGCAP>();
        try {
            String qry = "select * from NHACUNGCAP";
            st = conn.createStatement();
            rs = st.executeQuery(qry);
            while (rs.next()) {
                NHACUNGCAP ncc = new NHACUNGCAP();
                ncc.setMaNCC(rs.getString(1));
                ncc.setTenNCC(rs.getString(2));
                dsncc.add(ncc);
            }
        } catch (SQLException e) {

            JOptionPane.showMessageDialog(null, "Đọc dữ liệu thất bại", "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
        return dsncc;
    }

    public void them(NHACUNGCAP ncc) {
        try {
            String qry = "insert into NHACUNGCAP values  (" + "'" + ncc.getMaNCC().trim() + "'"
                    + "," + "N'" + ncc.getTenNCC() + "'" + ")";
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

    public void hoantacXoa(NHACUNGCAP ncc) {
        try {
            String qry = "insert into NHACUNGCAP values  (" + "'" + ncc.getMaNCC() + "'"
                    + "," + "N'" + ncc.getTenNCC() + "'" + ")";
            st = conn.createStatement();
            st.executeUpdate(qry);
        } catch (SQLException e) {
            System.out.println(e);
            JOptionPane.showMessageDialog(null, "Hoàn tác dữ liệu thất bại", "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }

    public int sua(NHACUNGCAP nccmoi, NHACUNGCAP ncccu) {
        try {
            String qry = "update NHACUNGCAP set " + "MANCC=" + "'" + nccmoi.getMaNCC() + "'" +
                    ",TENNCC=" + "N'" + ncccu.getTenNCC() + "'" + " " + "where MANCC='" + ncccu.getMaNCC()
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

    public int xoa(String MaNCC) {
        try {
            String qry = "delete from NHACUNGCAP where MANCC='" + MaNCC + "'";
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

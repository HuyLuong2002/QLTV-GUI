package QLTV.DAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import MyCustom.MSSQLConnect;
import QLTV.DTO.SACH;

public class QLSACHDAO {
    Connection conn = null;
    Statement st = null;
    ResultSet rs = null;

    public QLSACHDAO() throws Exception {
        MSSQLConnect connect = new MSSQLConnect();
        conn = connect.getConnection();
    }

    public ArrayList<SACH> docDSSV() {
        ArrayList<SACH> dssach = new ArrayList<SACH>();
        try {
            String qry = "select * from SACH";
            st = conn.createStatement();
            rs = st.executeQuery(qry);
            while (rs.next()) {
                SACH sach = new SACH();
                sach.setMasach(rs.getString(1));
                sach.setTensach(rs.getString(2));
                sach.setMaNXB(rs.getString(3));
                sach.setMaTG(rs.getString(4));
                sach.setNamXB(rs.getString(5));
                sach.setSLtong(Integer.valueOf(rs.getString(6)));
                sach.setSL(Integer.valueOf(rs.getString(7)));
                sach.setDongia(Integer.valueOf(rs.getString(8)));
                dssach.add(sach);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Đọc dữ liệu thất bại", "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
        return dssach;
    }

    public void them(SACH sach) {
        try {
            String qry = "insert into SACH values  (" + "'" + sach.getMasach() + "'"
                    + "," + "N'" + sach.getTensach() + "'" + "," + "'" + sach.getMaNXB() + "'" + ","
                    + "'" + sach.getMaTG() + "'" + "," + "'" + sach.getNamXB() + "'" + ","
                    + "N'" + String.valueOf(sach.getSLtong()) + "'" + "," + "'"
                    + String.valueOf(sach.getSL()) + "'" + "," + "'" + String.valueOf(sach.getDongia()) + "'"
                    + ")";
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

    public void hoantacXoa(SACH sach) {
        try {
            String qry = "insert into SACH values  (" + "'" + sach.getMasach() + "'"
                    + "," + "N'" + sach.getTensach() + "'" + "," + "'" + sach.getMaNXB() + "'" + ","
                    + "'" + sach.getMaTG() + "'" + "," + "'" + sach.getNamXB() + "'" + ","
                    + "N'" + String.valueOf(sach.getSLtong()) + "'" + "," + "'"
                    + String.valueOf(sach.getSL()) + "'" + "," + "'" + String.valueOf(sach.getDongia()) + "'"
                    + ")";
            st = conn.createStatement();
            st.executeUpdate(qry);
        } catch (SQLException e) {
            System.out.println(e);
            JOptionPane.showMessageDialog(null, "Thêm dữ liệu thất bại", "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void sua(SACH sachmoi, SACH sachcu) {
        try {
            String qry = "update SACH set " + "MASACH=" + "'" + sachmoi.getMasach() + "'" +
                    ",TENSACH=" + "N'" + sachmoi.getTensach() + "'" + ",MANXB=" + "'" + sachmoi.getMaNXB() + "'" +
                    ",MATG=" + "'" + sachmoi.getMaTG() + "'" + ",NAMXB=" + "'" + sachmoi.getNamXB() + "'" +
                    ",SLTONG=" + "'" + sachmoi.getSLtong() + "'" + ",SL=" + "'" + sachmoi.getSL() + "'" +
                    ",DONGIA=" + "'" + sachmoi.getDongia() + "'" + " " + "where MASACH='" + sachcu.getMasach()
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

    public void xoa(String Masach) {
        try {
            String qry = "delete from SACH where MASACH='" + Masach + "'";
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

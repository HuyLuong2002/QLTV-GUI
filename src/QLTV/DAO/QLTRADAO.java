package QLTV.DAO;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import MyCustom.MSSQLConnect;
import QLTV.DTO.PHIEUTRASACH;

public class QLTRADAO {
    Connection conn = null;
    Statement st = null;
    ResultSet rs = null;

    public QLTRADAO() throws Exception {
        MSSQLConnect connect = new MSSQLConnect();
        conn = connect.getConnection();
    }

    public ArrayList<PHIEUTRASACH> docDSPT() {
        ArrayList<PHIEUTRASACH> dstra = new ArrayList<PHIEUTRASACH>();
        try {
            String qry = "select * from PHIEUTRASACH";
            st = conn.createStatement();
            rs = st.executeQuery(qry);
            while (rs.next()) {
                PHIEUTRASACH pt = new PHIEUTRASACH();
                pt.setMaPT(rs.getString(1));
                pt.setNgaytra(rs.getString(2));
                pt.setTinhtrangsach(rs.getString(3));
                pt.setTienthue(Integer.parseInt(rs.getString(4)));
                pt.setThanhtien(Integer.parseInt(rs.getString(5)));
                pt.setMaPM(rs.getString(6));
                dstra.add(pt);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Đọc dữ liệu thất bại", "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
        return dstra;
    }

    public int them(PHIEUTRASACH phieutrasach) {
        try {
            String qry = "INSERT INTO PHIEUTRASACH VALUES (?,?,?,?,?,?)";
            PreparedStatement ps = conn.prepareStatement(qry);
            ps.setString(1, phieutrasach.getMaPT());
            ps.setString(2, phieutrasach.getNgaytra());
            ps.setString(3, phieutrasach.getTinhtrangsach());
            ps.setString(4, String.valueOf(phieutrasach.getTienthue()));
            ps.setString(5, String.valueOf(phieutrasach.getThanhtien()));
            ps.setString(6, phieutrasach.getMaPM());

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

    public int sua(PHIEUTRASACH phieutraMoi, PHIEUTRASACH phieutraCu){
        try {
            String qry = "UPDATE PHIEUTRASACH SET MAPT= ?, NGAYTRA= ?, TINHTRANGSACH= ?, TIENTHUE= ?, THANHTIEN= ?, MAPM= ?" + " WHERE MAPT= ?";
            PreparedStatement ps = conn.prepareStatement(qry);
            ps.setString(1, phieutraMoi.getMaPT());
            ps.setString(2, phieutraMoi.getNgaytra());
            ps.setString(3, phieutraMoi.getTinhtrangsach());
            ps.setString(4, String.valueOf(phieutraMoi.getTienthue()));
            ps.setString(5, String.valueOf(phieutraMoi.getThanhtien()));
            ps.setString(6, phieutraMoi.getMaPM());
            ps.setString(7, phieutraCu.getMaPT());

            int n = ps.executeUpdate();
            if (n != 0) {
                JOptionPane.showMessageDialog(null, "Sửa dữ liệu thành công", "Thông báo",
                        JOptionPane.INFORMATION_MESSAGE);
            }
            return 0;
        } catch (Exception e) {
            System.out.println(e);
            JOptionPane.showMessageDialog(null, "Sửa dữ liệu thất bại", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return -1;
        }
    }

    public int xoa(String Masach) {
        try {
            String qry = "delete from PHIEUTRASACH where MAPT='" + Masach + "'";
            st = conn.createStatement();
            st.executeUpdate(qry);
            if (st != null) {
                JOptionPane.showMessageDialog(null, "Xóa dữ liệu thành công", "Thông báo",
                        JOptionPane.INFORMATION_MESSAGE);
            }
            return 0;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Xóa dữ liệu thất bại", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return -1;
        }
    }
}

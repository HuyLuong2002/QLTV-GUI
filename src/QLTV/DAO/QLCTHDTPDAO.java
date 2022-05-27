package QLTV.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import MyCustom.MSSQLConnect;
import QLTV.DTO.CHITIETHDTIENPHAT;

public class QLCTHDTPDAO {
    Connection conn = null;
    Statement st = null;
    ResultSet rs = null;

    public QLCTHDTPDAO() throws Exception {
        MSSQLConnect connect = new MSSQLConnect();
        conn = connect.getConnection();
    }

    public ArrayList<CHITIETHDTIENPHAT> docDS() {
        ArrayList<CHITIETHDTIENPHAT> dscthdtp = new ArrayList<CHITIETHDTIENPHAT>();
        try {
            String qry = "select * from CHITIETHDTIENPHAT";
            st = conn.createStatement();
            rs = st.executeQuery(qry);
            while (rs.next()) {
                CHITIETHDTIENPHAT cthdtp = new CHITIETHDTIENPHAT();
                cthdtp.setMaHD(rs.getString(1).trim());
                cthdtp.setMasach(rs.getString(2).trim());
                cthdtp.setSL(Integer.parseInt(rs.getString(3)));
                cthdtp.setDongia(Integer.parseInt(rs.getString(4)));
                dscthdtp.add(cthdtp);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Đọc dữ liệu thất bại", "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
        return dscthdtp;
    }

    public int them(CHITIETHDTIENPHAT chitiethdtienphat) {
        try {
            String qry = "INSERT INTO CHITIETHDTIENPHAT VALUES (?,?,?,?)";
            PreparedStatement ps = conn.prepareStatement(qry);
            ps.setString(1, chitiethdtienphat.getMaHD());
            ps.setString(2, chitiethdtienphat.getMasach());
            ps.setString(3, String.valueOf(chitiethdtienphat.getSL()));
            ps.setString(4, String.valueOf(chitiethdtienphat.getDongia()));

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

    public int sua(CHITIETHDTIENPHAT chitiethdtienphatMoi, String MaHDCu, String MaSachCu){
        try {
            String qry = "UPDATE CHITIETHDTIENPHAT SET MAHD= ?, MASACH= ?, SL= ?, DONGIA= ?" + " WHERE MAHD=? AND MASACH= ?";
            PreparedStatement ps = conn.prepareStatement(qry);
            ps.setString(1, chitiethdtienphatMoi.getMaHD());
            ps.setString(2, chitiethdtienphatMoi.getMasach());
            ps.setString(3, String.valueOf(chitiethdtienphatMoi.getSL()));
            ps.setString(4, String.valueOf(chitiethdtienphatMoi.getDongia()));
            ps.setString(5, MaHDCu);
            ps.setString(6, MaSachCu);

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
    public int xoa(String MaHD, String Masach) {
        try {
            String qry = "delete from CHITIETHDTIENPHAT where MAHD=? AND MASACH=?";
            PreparedStatement ps = conn.prepareStatement(qry);
            ps.setString(1, MaHD);
            ps.setString(1, Masach);

            int n = ps.executeUpdate();

            if (n != 0) {
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

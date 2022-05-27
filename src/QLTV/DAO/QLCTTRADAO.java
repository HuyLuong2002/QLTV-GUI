package QLTV.DAO;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import MyCustom.MSSQLConnect;
import QLTV.DTO.CHITIETPHIEUTRA;

public class QLCTTRADAO {
    Connection conn = null;
    Statement st = null;
    ResultSet rs = null;

    public QLCTTRADAO() throws Exception {
        MSSQLConnect connect = new MSSQLConnect();
        conn = connect.getConnection();
    }

    public ArrayList<CHITIETPHIEUTRA> docDSCTPT() {
        ArrayList<CHITIETPHIEUTRA> dscttra = new ArrayList<CHITIETPHIEUTRA>();
        try {
            String qry = "select * from CHITIETPHIEUTRA";
            st = conn.createStatement();
            rs = st.executeQuery(qry);
            while (rs.next()) {
                CHITIETPHIEUTRA ctpt = new CHITIETPHIEUTRA();
                ctpt.setMaPT(rs.getString(1));
                ctpt.setMasach(rs.getString(2));
                ctpt.setSL(Integer.parseInt(rs.getString(3)));
                dscttra.add(ctpt);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Đọc dữ liệu thất bại", "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
        return dscttra;
    }

    public int them(CHITIETPHIEUTRA ctphieutra) {
        try {
            String qry = "INSERT INTO CHITIETPHIEUTRA VALUES (?,?,?)";
            PreparedStatement ps = conn.prepareStatement(qry);
            ps.setString(1, ctphieutra.getMaPT());
            ps.setString(2, ctphieutra.getMasach());
            ps.setString(3, String.valueOf(ctphieutra.getSL()));

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

    public int sua(CHITIETPHIEUTRA ctphieutraMoi, String MaPTCTPTCu, String MaSachCTPTCu){
        try {
            String qry = "UPDATE CHITIETPHIEUTRA SET MAPT= ?, MASACH= ?, SL= ?" + " WHERE MAPT= ? AND MASACH= ?";
            PreparedStatement ps = conn.prepareStatement(qry);
            ps.setString(1, ctphieutraMoi.getMaPT());
            ps.setString(2, ctphieutraMoi.getMasach());
            ps.setString(3, String.valueOf(ctphieutraMoi.getSL()));
            ps.setString(4, MaPTCTPTCu);
            ps.setString(5, MaSachCTPTCu);

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
    public int xoa(String MaPT, String Masach) {
        try {
            String qry = "delete from CHITIETPHIEUTRA where MAPT=? AND MASACH=?";
            PreparedStatement ps = conn.prepareStatement(qry);
            ps.setString(1, MaPT);
            ps.setString(2, Masach);
            int n = ps.executeUpdate();

            if (n != 0) {
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

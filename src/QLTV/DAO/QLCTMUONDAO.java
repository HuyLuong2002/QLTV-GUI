package QLTV.DAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.sql.PreparedStatement;
import javax.swing.JOptionPane;

import MyCustom.MSSQLConnect;
import QLTV.DTO.CHITIETPHIEUMUON;

public class QLCTMUONDAO {
    Connection conn = null;
    Statement st = null;
    ResultSet rs = null;

    public QLCTMUONDAO() throws Exception {
        MSSQLConnect connect = new MSSQLConnect();
        conn = connect.getConnection();
    }

    public ArrayList<CHITIETPHIEUMUON> docDSCTPM() {
        ArrayList<CHITIETPHIEUMUON> dsctmuon = new ArrayList<CHITIETPHIEUMUON>();
        try {
            String qry = "select * from CHITIETPHIEUMUON";
            st = conn.createStatement();
            rs = st.executeQuery(qry);
            while (rs.next()) {
                CHITIETPHIEUMUON ctpm = new CHITIETPHIEUMUON();
                ctpm.setMaPM(rs.getString(1));
                ctpm.setMasach(rs.getString(2));
                ctpm.setSL(Integer.parseInt(rs.getString(3)));
                dsctmuon.add(ctpm);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Đọc dữ liệu thất bại", "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
        return dsctmuon;
    }

    public void them(CHITIETPHIEUMUON ctphieumuon) {
        try {
            String qry = "INSERT INTO CHITIETPHIEUMUON VALUES (?,?,?)";
            PreparedStatement ps = conn.prepareStatement(qry);
            ps.setString(1, ctphieumuon.getMaPM());
            ps.setString(2, ctphieumuon.getMasach());
            ps.setString(3, String.valueOf(ctphieumuon.getSL()));

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

    public void sua(CHITIETPHIEUMUON ctphieumuonMoi, CHITIETPHIEUMUON ctphieumuonCu){
        try {
            String qry = "UPDATE CHITIETPHIEUMUON SET MAPM= ?, MASACH= ?, SL= ?" + " WHERE MAPM= ?";
            PreparedStatement ps = conn.prepareStatement(qry);
            ps.setString(1, ctphieumuonMoi.getMaPM());
            ps.setString(2, ctphieumuonMoi.getMasach());
            ps.setString(3, String.valueOf(ctphieumuonMoi.getSL()));
            ps.setString(4, ctphieumuonCu.getMaPM());

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
}

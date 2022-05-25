package QLTV.DAO;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import MyCustom.MSSQLConnect;
import QLTV.DTO.CHITIETPHIEUNHAP;

public class QLCTPNDAO {
    Connection conn = null;
    Statement st = null;
    ResultSet rs = null;

    public QLCTPNDAO() throws Exception {
        MSSQLConnect connect = new MSSQLConnect();
        conn = connect.getConnection();
    }

    public ArrayList<CHITIETPHIEUNHAP> docDSPN() {
        ArrayList<CHITIETPHIEUNHAP> dspCTPhieunhaps = new ArrayList<CHITIETPHIEUNHAP>();
        try {
            String qry = "select * from CHITIETPHIEUNHAP";
            st = conn.createStatement();
            rs = st.executeQuery(qry);
            while (rs.next()) {
                CHITIETPHIEUNHAP ctpn = new CHITIETPHIEUNHAP();
                ctpn.setMaPN(rs.getString(1));
                ctpn.setMasach(rs.getString(2));
                ctpn.setSL(Integer.parseInt(rs.getString(3)));
                dspCTPhieunhaps.add(ctpn);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Đọc dữ liệu thất bại", "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
        return dspCTPhieunhaps;
    }

    public int them(CHITIETPHIEUNHAP phieunhap) {
        try {
            String qry = "INSERT INTO CHITIETPHIEUNHAP VALUES (?,?,?)";
            PreparedStatement ps = conn.prepareStatement(qry);
            ps.setString(1, phieunhap.getMaPN());
            ps.setString(2, phieunhap.getMasach());
            ps.setString(3, String.valueOf(phieunhap.getSL()));

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

    public int sua(CHITIETPHIEUNHAP ctpnMoi, String MaCTPNCu, String MaCTPNSachCu){
        try {
            String qry = "UPDATE CHITIETPHIEUNHAP SET MAPN= ?, MASACH= ?, SL= ?" + " WHERE MAPN= ? AND MASACH= ?";
            PreparedStatement ps = conn.prepareStatement(qry);
            ps.setString(1, ctpnMoi.getMaPN());
            ps.setString(2, ctpnMoi.getMasach());
            ps.setString(3, String.valueOf(ctpnMoi.getSL()));
            ps.setString(4, MaCTPNCu);
            ps.setString(5, MaCTPNSachCu);

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
}

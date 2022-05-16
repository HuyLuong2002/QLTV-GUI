package QLTV.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import MyCustom.MSSQLConnect;
import QLTV.DTO.PHIEUNHAP;

public class QLPNDAO {
    Connection conn = null;
    Statement st = null;
    ResultSet rs = null;

    public QLPNDAO() throws Exception {
        MSSQLConnect connect = new MSSQLConnect();
        conn = connect.getConnection();
    }

    public ArrayList<PHIEUNHAP> docDS() {
        ArrayList<PHIEUNHAP> dspn = new ArrayList<PHIEUNHAP>();
        try {
            String qry = "select * from PHIEUNHAP";
            st = conn.createStatement();
            rs = st.executeQuery(qry);
            while (rs.next()) {
                PHIEUNHAP pn = new PHIEUNHAP();
                pn.setMaPN(rs.getString(1));
                pn.setNgaynhap(rs.getString(2));
                pn.setSLTong(Integer.parseInt(rs.getString(3)));
                pn.setDongia(Integer.parseInt(rs.getString(4)));
                pn.setMaNV(rs.getString(5));
                pn.setMaNCC(rs.getString(6));
                dspn.add(pn);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Đọc dữ liệu thất bại", "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
        return dspn;
    }

    public int them(PHIEUNHAP phieunhap) {
        try {
            String qry = "INSERT INTO PHIEUNHAP VALUES (?,?,?,?,?,?)";
            PreparedStatement ps = conn.prepareStatement(qry);
            ps.setString(1, phieunhap.getMaPN());
            ps.setString(2, phieunhap.getNgaynhap());
            ps.setString(3, String.valueOf(phieunhap.getSLTong()));
            ps.setString(4, String.valueOf(phieunhap.getDongia()));
            ps.setString(5, phieunhap.getMaNV());
            ps.setString(6, phieunhap.getMaNCC());

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

    public int sua(PHIEUNHAP phieunhapMoi, PHIEUNHAP phieunhapCu){
        try {
            String qry = "UPDATE PHIEUNHAP SET MAPN= ?, NGAYNHAP= ?, SLTONG= ?, DONGIA= ?, MANV= ?, MANCC= ?" + " WHERE MAPN= ?";
            PreparedStatement ps = conn.prepareStatement(qry);
            ps.setString(1, phieunhapMoi.getMaPN());
            ps.setString(2, phieunhapMoi.getNgaynhap());
            ps.setString(3, String.valueOf(phieunhapMoi.getSLTong()));
            ps.setString(4, String.valueOf(phieunhapMoi.getDongia()));
            ps.setString(5, phieunhapMoi.getMaNV());
            ps.setString(6, phieunhapMoi.getMaNCC());
            ps.setString(7, phieunhapCu.getMaPN());

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

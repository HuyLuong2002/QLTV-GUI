package QLTV.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import MyCustom.MSSQLConnect;
import QLTV.DTO.HDTIENPHAT;

public class QLHDTPDAO {
    Connection conn = null;
    Statement st = null;
    ResultSet rs = null;

    public QLHDTPDAO() throws Exception {
        MSSQLConnect connect = new MSSQLConnect();
        conn = connect.getConnection();
    }

    public ArrayList<HDTIENPHAT> docDS() {
        ArrayList<HDTIENPHAT> dshdtp = new ArrayList<HDTIENPHAT>();
        try {
            String qry = "select * from HDTIENPHAT";
            st = conn.createStatement();
            rs = st.executeQuery(qry);
            while (rs.next()) {
                HDTIENPHAT tp = new HDTIENPHAT();
                tp.setMaHD(rs.getString(1).trim());
                tp.setMaDG(rs.getString(2).trim());
                tp.setSL(Integer.parseInt(rs.getString(3)));
                tp.setTienphat(Integer.parseInt(rs.getString(4)));
                dshdtp.add(tp);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Đọc dữ liệu thất bại", "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
        return dshdtp;
    }

    public void them(HDTIENPHAT hdtienphat) {
        try {
            String qry = "INSERT INTO HDTIENPHAT VALUES (?,?,?,?)";
            PreparedStatement ps = conn.prepareStatement(qry);
            ps.setString(1, hdtienphat.getMaHD());
            ps.setString(2, hdtienphat.getMaDG());
            ps.setString(3, String.valueOf(hdtienphat.getSL()));
            ps.setString(4, String.valueOf(hdtienphat.getTienphat()));
    
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

    public int sua(HDTIENPHAT HDTPMoi, HDTIENPHAT HDTPCu){
        try {
            String qry = "UPDATE HDTIENPHAT SET MAHD= ?, MADG= ?, SLtong= ?, TIENPHAT= ?" + " WHERE MAHD= ?";
            PreparedStatement ps = conn.prepareStatement(qry);
            ps.setString(1, HDTPMoi.getMaHD());
            ps.setString(2, HDTPMoi.getMaDG());
            ps.setString(3, String.valueOf(HDTPMoi.getSL()));
            ps.setString(4, String.valueOf(HDTPMoi.getTienphat()));
            ps.setString(5, HDTPCu.getMaHD());

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

package QLTV.DAO;

import java.sql.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import MyCustom.MSSQLConnect;
import QLTV.DTO.ACCOUNT;

public class ACCOUNTDAO {
    Connection conn = null;
    Statement st = null;
    ResultSet rs = null;

    public ACCOUNTDAO() throws Exception {
        MSSQLConnect connect = new MSSQLConnect();
        conn = connect.getConnection();
    }

    public ArrayList<ACCOUNT> docAdminAcc() {
        ArrayList<ACCOUNT> accounts = new ArrayList<ACCOUNT>();
        try {
            String qry = "select * from ACCOUNT where PHANQUYEN = '1'";
            st = conn.createStatement();
            rs = st.executeQuery(qry);
            while(rs.next()){
                ACCOUNT acc = new ACCOUNT();
                acc.setID(rs.getString(1));
                acc.setUsername(rs.getString(2));
                acc.setPassword(rs.getString(3));
                acc.setHoLot(rs.getString(4));
                acc.setTen(rs.getString(5));
                acc.setNgaySinh(rs.getString(6));
                acc.setGioiTinh(rs.getString(7));
                acc.setSDT(rs.getString(8));
                accounts.add(acc);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Đọc dữ liệu thất bại", "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
        return accounts;
    }

    public void them(ACCOUNT account){
        try {
            String qry = "INSERT INTO ACCOUNT VALUES (?,?,?,?,?,?,?,?)";
            PreparedStatement ps = conn.prepareStatement(qry);
            ps.setString(1, account.getID());
            ps.setString(2, account.getUsername());
            ps.setString(3, account.getPassword());
            ps.setString(4, account.getHoLot());
            ps.setString(5, account.getTen());
            ps.setString(6, account.getNgaySinh());
            ps.setString(7, account.getGioiTinh());
            ps.setString(8, account.getSDT());

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
    public void hoantacXoa(ACCOUNT account){
        try {
            String qry = "INSERT INTO ACCOUNT VALUES (?,?,?,?,?,?,?,?)";
            PreparedStatement ps = conn.prepareStatement(qry);
            ps.setString(1, account.getID());
            ps.setString(2, account.getUsername());
            ps.setString(3, account.getPassword());
            ps.setString(4, account.getHoLot());
            ps.setString(5, account.getTen());
            ps.setString(6, account.getNgaySinh());
            ps.setString(7, account.getGioiTinh());
            ps.setString(8, account.getSDT());

            int n = ps.executeUpdate();
            if(n != 0){
                JOptionPane.showMessageDialog(null, "Hoàn tác thành công", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (Exception e) {
            System.out.println(e);
            JOptionPane.showMessageDialog(null, "Thêm dữ liệu thất bại", "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }
    public void sua(ACCOUNT newAcc, ACCOUNT oldAcc){
        try {
            String qry = "update ACCOUNT set " + "ID=" + "'" + newAcc.getID() + "'" +
            ",USERNAME=" + "N'" + newAcc.getUsername() + "'" + ",PASS=" + "'" + newAcc.getPassword() + "'" +
            ",HOLOT=" + "'" + newAcc.getHoLot() + "'" + ",TEN=" + "'" + newAcc.getTen() + "'" +
            ",NGAYSINH=" + "'" + newAcc.getNgaySinh() + "'" + ",GIOITINH=" + "'" + newAcc.getGioiTinh() + "'" +
            ",SDT=" + "'" + newAcc.getSDT() + "'" + " " + "where ID='" + oldAcc.getID()
            + "'";
            st = conn.createStatement();
            st.executeUpdate(qry);
            if (st != null) {
                JOptionPane.showMessageDialog(null, "Sửa dữ liệu thành công", "Thông báo",
                    JOptionPane.INFORMATION_MESSAGE);
            }        
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Sửa dữ liệu thất bại", "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }
    public void xoa(String ID){
        try {
            String qry = "DELETE FROM ACCOUNT WHERE ID='" + ID + "'"; 
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
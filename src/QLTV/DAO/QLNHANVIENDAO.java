package QLTV.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import MyCustom.MSSQLConnect;
import QLTV.DTO.NHANVIEN;

public class QLNHANVIENDAO {
    Connection conn = null;
    Statement st = null;
    ResultSet rs = null;

    public QLNHANVIENDAO() throws Exception {
        MSSQLConnect connect = new MSSQLConnect();
        conn = connect.getConnection();
    }

    public ArrayList<NHANVIEN> docDSSV() {
        ArrayList<NHANVIEN> dsnhanvien = new ArrayList<NHANVIEN>();
        try {
            String qry = "select * from NHANVIEN";
            st = conn.createStatement();
            rs = st.executeQuery(qry);
            while (rs.next()) {
                NHANVIEN nhanvien = new NHANVIEN();
                nhanvien.setMaNV(rs.getString(1));
                nhanvien.setTenNV(rs.getString(2));
                nhanvien.setChucvu(String.valueOf(rs.getString(3)));
                nhanvien.setLuongCB(Integer.valueOf(rs.getString(4)));
                nhanvien.setPhucap(Integer.valueOf(rs.getString(5)));
                nhanvien.setHesoluong(Double.valueOf(rs.getString(6)));
                nhanvien.setSDT(Integer.valueOf(rs.getString(7).trim()));
                nhanvien.setMail(rs.getString(8));
                dsnhanvien.add(nhanvien);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Đọc dữ liệu thất bại", "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
        return dsnhanvien;
    }

    public void them(NHANVIEN nhanvien) {
        try {
            String qry = "INSERT INTO NHANVIEN VALUES (?,?,?,?,?,?,?,?)";
            PreparedStatement ps = conn.prepareStatement(qry);
            ps.setString(1, nhanvien.getMaNV());
            ps.setString(2, nhanvien.getTenNV());
            ps.setString(3, String.valueOf(nhanvien.getChucvu()));
            ps.setString(4, String.valueOf(nhanvien.getLuongCB()));
            ps.setString(5, String.valueOf(nhanvien.getPhucap()));
            ps.setString(6, String.valueOf(nhanvien.getHesoluong()));
            ps.setString(7, String.valueOf(nhanvien.getSDT()));
            ps.setString(8, nhanvien.getMail());

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

    public void hoantacXoa(NHANVIEN nhanvien) {
        try {
            String qry = "INSERT INTO NHANVIEN VALUES (?,?,?,?,?,?,?,?)";
            PreparedStatement ps = conn.prepareStatement(qry);
            ps.setString(1, nhanvien.getMaNV());
            ps.setString(2, nhanvien.getTenNV());
            ps.setString(3, String.valueOf(nhanvien.getChucvu()));
            ps.setString(4, String.valueOf(nhanvien.getLuongCB()));
            ps.setString(5, String.valueOf(nhanvien.getPhucap()));
            ps.setString(6, String.valueOf(nhanvien.getHesoluong()));
            ps.setString(7, String.valueOf(nhanvien.getSDT()));
            ps.setString(8, nhanvien.getMail());

            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
            JOptionPane.showMessageDialog(null, "Hoàn tác dữ liệu thất bại", "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }

    public int sua(NHANVIEN nhanvienmoi, NHANVIEN nhanviencu) {
        try {
            String qry = "UPDATE NHANVIEN SET MANV=?, TENNV=?, CHUCVU=?, LUONGCB=?, PHUCAP=?, HESOLUONG=?, SDT=?, EMAIL=? WHERE MANV=? ";
            PreparedStatement ps = conn.prepareStatement(qry);
            ps.setString(1, nhanvienmoi.getMaNV());
            ps.setString(2, nhanvienmoi.getTenNV());
            ps.setString(3, String.valueOf(nhanvienmoi.getChucvu()));
            ps.setString(4, String.valueOf(nhanvienmoi.getLuongCB()));
            ps.setString(5, String.valueOf(nhanvienmoi.getPhucap()));
            ps.setString(6, String.valueOf(nhanvienmoi.getHesoluong()));
            ps.setString(7, String.valueOf(nhanvienmoi.getSDT()));
            ps.setString(8, nhanvienmoi.getMail());
            ps.setString(9, nhanviencu.getMaNV().trim());

            int n = ps.executeUpdate();
            if (n != 0) {
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

    public void xoa(String Masach) {
        try {
            String qry = "DELETE FROM NHANVIEN WHERE MANV=? ";
            PreparedStatement ps = conn.prepareStatement(qry);
            ps.setString(1, Masach);
            int n = ps.executeUpdate();
            if (n != 0) {
                JOptionPane.showMessageDialog(null, "Xóa dữ liệu thành công", "Thông báo",
                        JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (SQLException e) {
            System.out.println(e);
            JOptionPane.showMessageDialog(null, "Xóa dữ liệu thất bại", "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }

}

package QLTV.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import MyCustom.MSSQLConnect;
import QLTV.DTO.PHIEUTHEODOIMT;

public class QLPHIEUTHEODOIDAO {
    Connection conn = null;
    Statement st = null;
    ResultSet rs = null;

    public QLPHIEUTHEODOIDAO() throws Exception {
        MSSQLConnect connect = new MSSQLConnect();
        conn = connect.getConnection();
    }

    public ArrayList<PHIEUTHEODOIMT> docDS() {
        ArrayList<PHIEUTHEODOIMT> dsptd = new ArrayList<PHIEUTHEODOIMT>();
        try {
            String qry = "select * from PHIEUTHEODOIMT";
            st = conn.createStatement();
            rs = st.executeQuery(qry);
            while (rs.next()) {
                PHIEUTHEODOIMT ptd = new PHIEUTHEODOIMT();
                ptd.setMaDG(rs.getString(1));
                ptd.setTongmuon(Integer.parseInt(rs.getString(2)));
                ptd.setTiencoc(Integer.parseInt(rs.getString(3)));
                dsptd.add(ptd);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Đọc dữ liệu thất bại", "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
        return dsptd;
    }

    public int them(PHIEUTHEODOIMT ptd) {
        try {
            String qry = "INSERT INTO PHIEUTHEODOIMT VALUES (?,?,?)";
            PreparedStatement ps = conn.prepareStatement(qry);
            ps.setString(1, ptd.getMaDG());
            ps.setString(2, String.valueOf(ptd.getTongmuon()));
            ps.setString(3, String.valueOf(ptd.getTiencoc()));

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

    public int sua(PHIEUTHEODOIMT ptdmoi, PHIEUTHEODOIMT ptdcu, int i) {
        try {
            String qry = "update PHIEUTHEODOIMT set " + "MADG=" + "'" + ptdmoi.getMaDG().trim() + "'" +
                    ",TONGMUON=" + ptdmoi.getTongmuon() + ",TIENCOC=" + ptdmoi.getTiencoc() + " where MADG='" + ptdcu.getMaDG().trim() + "'";
            st = conn.createStatement();
            st.executeUpdate(qry);
            if (st != null) {
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

    public int xoa(String MaDG) {
        try {
            String qry = "delete from PHIEUTHEODOIMT where MADG='" + MaDG + "'";
            st = conn.createStatement();
            st.executeUpdate(qry);
            if (st != null) {
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

    public int hoantacXoa(PHIEUTHEODOIMT ptd) {
        try {
            String qry = "INSERT INTO PHIEUTHEODOIMT VALUES (?,?,?)";
            PreparedStatement ps = conn.prepareStatement(qry);
            ps.setString(1, ptd.getMaDG());
            ps.setString(2, String.valueOf(ptd.getTongmuon()));
            ps.setString(3, String.valueOf(ptd.getTiencoc()));


            ps.executeUpdate();

            return 0;
        } catch (SQLException e) {
            System.out.println(e);
            return -1;
        }
    }

}

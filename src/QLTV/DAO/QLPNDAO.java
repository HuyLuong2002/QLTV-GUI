package QLTV.DAO;

import java.sql.Connection;
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
}

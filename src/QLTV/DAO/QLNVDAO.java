package QLTV.DAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import MyCustom.MSSQLConnect;
import QLTV.DTO.DOCGIA;

public class QLNVDAO {
    Connection conn = null;
    Statement st = null;
    ResultSet rs = null;

    public QLNVDAO() throws Exception {
        MSSQLConnect connect = new MSSQLConnect();
        conn = connect.getConnection();
    }

    public ArrayList<DOCGIA> docDS() {
        ArrayList<DOCGIA> dsdg = new ArrayList<DOCGIA>();
        try {
            String qry = "select * from DOCGIA";
            st = conn.createStatement();
            rs = st.executeQuery(qry);
            while (rs.next()) {
                DOCGIA dg = new DOCGIA();
                dg.setMaDG(rs.getString(1));
                dg.setTenDG(rs.getString(2));
                dg.setDiachi(rs.getString(3));
                dg.setMail(rs.getString(4));
                dg.setTinhtrangthue(rs.getString(5));
                dsdg.add(dg);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Đọc dữ liệu thất bại", "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
        return dsdg;
    }

}

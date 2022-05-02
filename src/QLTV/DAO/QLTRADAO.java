package QLTV.DAO;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import MyCustom.MSSQLConnect;
import QLTV.DTO.PHIEUTRASACH;

public class QLTRADAO {
    Connection conn = null;
    Statement st = null;
    ResultSet rs = null;

    public QLTRADAO() throws Exception {
        MSSQLConnect connect = new MSSQLConnect();
        conn = connect.getConnection();
    }

    public ArrayList<PHIEUTRASACH> docDSPT() {
        ArrayList<PHIEUTRASACH> dstra = new ArrayList<PHIEUTRASACH>();
        try {
            String qry = "select * from PHIEUTRASACH";
            st = conn.createStatement();
            rs = st.executeQuery(qry);
            while (rs.next()) {
                PHIEUTRASACH pt = new PHIEUTRASACH();
                pt.setMaPT(rs.getString(1));
                pt.setNgaytra(rs.getString(2));
                pt.setTinhtrangsach(rs.getString(3));
                pt.setTienthue(Integer.parseInt(rs.getString(4)));
                pt.setThanhtien(Integer.parseInt(rs.getString(5)));
                pt.setMaPM(rs.getString(6));
                dstra.add(pt);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Đọc dữ liệu thất bại", "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
        return dstra;
    }
}

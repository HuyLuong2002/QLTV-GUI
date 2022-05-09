package QLTV.DAO;

import java.sql.Connection;
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
                tp.setMasach(rs.getString(2).trim());
                tp.setSL(Integer.parseInt(rs.getString(3)));
                tp.setTienphat(Integer.parseInt(rs.getString(4)));
                dshdtp.add(tp);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Đọc dữ liệu thất bại", "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
        return dshdtp;
    }
}

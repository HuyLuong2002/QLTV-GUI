package QLTV.DAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import MyCustom.MSSQLConnect;
import QLTV.DTO.CHITIETHDTIENPHAT;

public class QLCTHDTPDAO {
    Connection conn = null;
    Statement st = null;
    ResultSet rs = null;

    public QLCTHDTPDAO() throws Exception {
        MSSQLConnect connect = new MSSQLConnect();
        conn = connect.getConnection();
    }

    public ArrayList<CHITIETHDTIENPHAT> docDS() {
        ArrayList<CHITIETHDTIENPHAT> dscthdtp = new ArrayList<CHITIETHDTIENPHAT>();
        try {
            String qry = "select * from CHITIETHDTIENPHAT";
            st = conn.createStatement();
            rs = st.executeQuery(qry);
            while (rs.next()) {
                CHITIETHDTIENPHAT cthdtp = new CHITIETHDTIENPHAT();
                cthdtp.setMaHD(rs.getString(1).trim());
                cthdtp.setMasach(rs.getString(2).trim());
                cthdtp.setSL(Integer.parseInt(rs.getString(3)));
                cthdtp.setDongia(Integer.parseInt(rs.getString(4)));
                dscthdtp.add(cthdtp);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Đọc dữ liệu thất bại", "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
        return dscthdtp;
    }
}

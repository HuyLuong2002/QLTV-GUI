package QLTV.DAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import MyCustom.MSSQLConnect;
import QLTV.DTO.CHITIETPHIEUMUON;

public class QLCTMUONDAO {
    Connection conn = null;
    Statement st = null;
    ResultSet rs = null;

    public QLCTMUONDAO() throws Exception {
        MSSQLConnect connect = new MSSQLConnect();
        conn = connect.getConnection();
    }

    public ArrayList<CHITIETPHIEUMUON> docDSCTPM() {
        ArrayList<CHITIETPHIEUMUON> dsctmuon = new ArrayList<CHITIETPHIEUMUON>();
        try {
            String qry = "select * from CHITIETPHIEUMUON";
            st = conn.createStatement();
            rs = st.executeQuery(qry);
            while (rs.next()) {
                CHITIETPHIEUMUON ctpm = new CHITIETPHIEUMUON();
                ctpm.setMaPM(rs.getString(1));
                ctpm.setMasach(rs.getString(2));
                ctpm.setSL(Integer.parseInt(rs.getString(3)));
                dsctmuon.add(ctpm);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Đọc dữ liệu thất bại", "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
        return dsctmuon;
    }
}

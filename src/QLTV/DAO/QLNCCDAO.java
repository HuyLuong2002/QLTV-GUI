/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package QLTV.DAO;

import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import MyCustom.MSSQLConnect;
import QLTV.DTO.NHACUNGCAP;

/**
 *
 * @author MSI
 */
public class QLNCCDAO {
    Connection conn = null;
    Statement st = null;
    ResultSet rs = null;

    public QLNCCDAO() throws Exception {
        MSSQLConnect connect = new MSSQLConnect();
        conn = connect.getConnection();
    }

    public boolean them(NHACUNGCAP nxb) throws Exception {
        String sql = "insert into NHACUNGCAP(MANCC,TENNCC) values(?,?)";
        try (
                PreparedStatement ps = conn.prepareStatement(sql);) {
            ps.setString(1, nxb.getId());
            ps.setString(2, nxb.getName());
            return ps.executeUpdate() > 0;
        }
    }

    public ArrayList<NHACUNGCAP> getlist() throws Exception {
        ArrayList<NHACUNGCAP> list = new ArrayList<>();
        String sql = "SELECT * FROM NHACUNGCAP";
        try (
                PreparedStatement ps = conn.prepareStatement(sql);) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                NHACUNGCAP s = new NHACUNGCAP();
                s.setId(rs.getString("MANCC"));
                s.setName(rs.getString("TENNCC"));
                list.add(s);
            }

        }
        return list;
    }

    public NHACUNGCAP tim(String id) throws Exception {

        String sql = "select * from NHACUNGCAP where MANCC=?";
        try (
                PreparedStatement ps = conn.prepareStatement(sql);

        ) {
            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                NHACUNGCAP nxb = new NHACUNGCAP();
                nxb.setId(rs.getString("MANCC"));
                nxb.setName(rs.getString("TENNCC"));
                return nxb;
            }
        }
        return null;

    }

    public boolean sua(NHACUNGCAP nxb) throws Exception {
        String sql = "update NHACUNGCAP set TENNCC= ?" + "  where MANCC= ?";
        try (
                PreparedStatement ps = conn.prepareStatement(sql);) {
            ps.setString(1, nxb.getName());
            ps.setString(2, nxb.getId());
            return ps.executeUpdate() > 0;
        }

    }

    public boolean xoa(String id) throws Exception {
        String sql = "delete from NHACUNGCAP where MANCC= ?";
        try (
                PreparedStatement ps = conn.prepareStatement(sql);) {
            ps.setString(1, id);
            return ps.executeUpdate() > 0;
        }

    }
}

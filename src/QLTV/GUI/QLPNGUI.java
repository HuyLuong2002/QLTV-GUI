package QLTV.GUI;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import MyCustom.MyTable;
import QLTV.BUS.QLPNBUS;
import QLTV.DTO.PHIEUNHAP;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Vector;

public class QLPNGUI implements MouseListener {
    JPanel pnPhieuNhap, pnNhapPhieu, pnPN, pnCTPN;
    Color ColorPurple;
    TitledBorder titlePN;

    JTable tblQLPN,tblQLCTPN;
    DefaultTableModel modelPN, modelCTPN;

    public QLPNGUI() {

    }

    public JPanel setPNGUI() {
        ColorPurple = new Color(255, 20, 147);
        if (pnPhieuNhap == null) {
            MyTable myTable = new MyTable();
            pnPhieuNhap = new JPanel();
            pnPhieuNhap.setBounds(240, 0, 1145, 800);
            pnPhieuNhap.setLayout(null);

            // panel tab nhập phiếu
            pnNhapPhieu = new JPanel();
            pnNhapPhieu.setBounds(5, 5, 1138, 350);
            pnNhapPhieu.setLayout(new GridLayout(1, 2, 5, 0));

            pnPN = new JPanel();
            pnPN.setLayout(new GridLayout(1, 1));

            pnCTPN = new JPanel();
            pnCTPN.setLayout(new GridLayout(1, 1));


            pnPhieuNhap.add(pnNhapPhieu);

            pnNhapPhieu.add(pnPN);
            pnNhapPhieu.add(pnCTPN);

            setTitlePN();
            setTablePN();
            setTableCTPN();
            getDBPhieuNhap();
            myTable.setValueCellCenter(modelPN,tblQLPN);
        }
        return pnPhieuNhap;
    }

    public void setTitlePN() {
        // set Border
        Border empty;
        empty = BorderFactory.createEmptyBorder();

        titlePN = BorderFactory.createTitledBorder(empty, "THÔNG TIN PHIẾU NHẬP");
        titlePN.setTitleFont(new Font("Arial", Font.BOLD, 28));
        titlePN.setTitleJustification(TitledBorder.CENTER);
        pnPN.setBorder(titlePN);

        titlePN = BorderFactory.createTitledBorder(empty, "CHI TIẾT PHIẾU NHẬP");
        titlePN.setTitleFont(new Font("Arial", Font.BOLD, 28));
        titlePN.setTitleJustification(TitledBorder.CENTER);
        pnCTPN.setBorder(titlePN);
    }

    public void setTablePN() {
        // ----set up table----
        tblQLPN = new JTable();
        JScrollPane pane = new JScrollPane(tblQLPN);
        pane.setAutoscrolls(true);
        tblQLPN.setRowHeight(20);
        tblQLPN.setFont(new Font(null, 0, 13));
        tblQLPN.setBackground(Color.LIGHT_GRAY);
        tblQLPN.addMouseListener(this);
        tblQLPN.setDefaultEditor(Object.class, null);
        tblQLPN.setSelectionBackground(Color.GREEN);
        pnPN.add(pane);
    }

    public void setTableCTPN() {
        // ----set up table----
        tblQLCTPN = new JTable();
        JScrollPane pane = new JScrollPane(tblQLCTPN);
        pane.setAutoscrolls(true);
        tblQLCTPN.setRowHeight(20);
        tblQLCTPN.setFont(new Font(null, 0, 13));
        tblQLCTPN.setBackground(Color.LIGHT_GRAY);
        tblQLCTPN.addMouseListener(this);
        tblQLCTPN.setDefaultEditor(Object.class, null);
        tblQLCTPN.setSelectionBackground(Color.GREEN);
        pnCTPN.add(pane);
    }

    public void getDBPhieuNhap() {
        try {
            QLPNBUS qlbus = new QLPNBUS();
            if (QLPNBUS.dspn == null)
                try {
                    qlbus.docDS();
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            // set up header(column)
            Vector<String> header = new Vector<String>();
            header.add("Mã phiếu nhập");
            header.add("Ngày nhập");
            header.add("SL tổng");
            header.add("Đơn giá");
            header.add("Mã nhân viên");
            header.add("Mã nhà cung cấp");
            modelPN = new DefaultTableModel(header, 0);
            for (PHIEUNHAP pn : QLPNBUS.dspn) {
                ShowOnTablePN(pn);
            }
            tblQLPN.setModel(modelPN);
        } catch (Exception e1) {
            System.out.println(e1);
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        
    }

    @Override
    public void mousePressed(MouseEvent e) {
        
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        
    }

    @Override
    public void mouseExited(MouseEvent e) {
        
    }
    public void ShowOnTablePN(PHIEUNHAP pn) {
        Vector<String> row = new Vector<String>();
        row.add(pn.getMaPN().trim());
        row.add(pn.getNgaynhap().trim());
        row.add(String.valueOf(pn.getSLTong()));
        row.add(String.format("%,d",pn.getDongia()));
        row.add(pn.getMaNV().trim());
        row.add(pn.getMaNCC().trim());
        modelPN.addRow(row);

    }
}

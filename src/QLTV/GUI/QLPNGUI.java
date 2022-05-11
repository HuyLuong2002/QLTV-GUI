package QLTV.GUI;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

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

}

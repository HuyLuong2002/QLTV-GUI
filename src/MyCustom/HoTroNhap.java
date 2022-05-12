package MyCustom;

import java.util.ArrayList;
import java.util.Vector;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.*;
import java.awt.Color;
import javax.swing.table.DefaultTableModel;
import QLTV.BUS.QLMUONBUS;
import QLTV.BUS.QLNVBUS;
import QLTV.DTO.DOCGIA;
import QLTV.DTO.PHIEUMUON;

public class HoTroNhap extends JFrame implements MouseListener, ActionListener {
    JLabel lbHoTro, lbTuKhoaTK;
    JTextField txKhoaTK;
    JPanel pnTable;
    JButton btLuaChon, btTimKiem;
    JTable table;
    DefaultTableModel model;
    Vector<String> header;
    JScrollPane pane;

    public HoTroNhap() {
        MyTable myTable = new MyTable();
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setSize(700, 600);
        this.setTitle("Lựa chọn thông tin");
        this.setLayout(null);
        this.setLocationRelativeTo(null);

        pnTable = new JPanel();
        pnTable.setLayout(new GridLayout(1, 1));
        pnTable.setBounds(40, 100, 600, 300);

        setTitle();
        setTable();
        addTTPMOnTable();
        myTable.setValueCellCenter(model, table);

        this.add(pnTable);

        this.setVisible(true);
    }

    public void setTitle() {
        lbHoTro = new JLabel("LỰA CHỌN PHIẾU MƯỢN CẦN IN");
        lbHoTro.setFont(new Font("Arial", Font.BOLD, 20));
        lbHoTro.setBounds(180, 0, 320, 50);

        btLuaChon = new JButton("Chọn");
        btLuaChon.setFont(new Font("Arial", Font.BOLD, 15));
        btLuaChon.setBounds(560, 420, 80, 30);
        btLuaChon.setBackground(Color.cyan);
        btLuaChon.setBorder(new RoundedBorder(10));
        btLuaChon.addActionListener(this);

        lbTuKhoaTK = new JLabel("Nhập từ khóa tìm kiếm:");
        lbTuKhoaTK.setFont(new Font("Arial", Font.BOLD, 20));
        lbTuKhoaTK.setBounds(40, 20, 250, 100);

        txKhoaTK = new JTextField();
        txKhoaTK.setFont(new Font("Arial", Font.PLAIN, 15));
        txKhoaTK.setBounds(270, 55, 150, 30);

        btTimKiem = new JButton("Tìm kiếm");
        btTimKiem.setFont(new Font("Arial", Font.BOLD, 15));
        btTimKiem.setBounds(430, 55, 100, 30);
        btTimKiem.setBackground(Color.cyan);
        btTimKiem.setBorder(new RoundedBorder(10));
        btTimKiem.addActionListener(this);

        this.add(lbHoTro);
        this.add(btLuaChon);
        this.add(lbTuKhoaTK);
        this.add(txKhoaTK);
        this.add(btTimKiem);
    }

    public void setTable() {
        table = new JTable();
        pane = new JScrollPane(table);
        pane.setAutoscrolls(true);
        table.setRowHeight(30);
        table.setFont(new Font(null, 0, 13));
        table.setBackground(Color.LIGHT_GRAY);
        table.addMouseListener(this);
        table.setDefaultEditor(Object.class, null);
        table.setSelectionBackground(Color.GREEN);

        pnTable.add(pane);
    }

    public void addTTPMOnTable() {
        Vector<String> header = new Vector<String>();
        header.add("Mã phiếu mượn");
        header.add("Ngày mượn");
        header.add("SL tổng");
        header.add("Ngày trả");
        header.add("Tình trạng mượn");
        header.add("Mã độc giả");
        model = new DefaultTableModel(header, 0);
        QLMUONBUS qlmuonbus = new QLMUONBUS();
        if (QLMUONBUS.dspm == null) {
            try {
                qlmuonbus.docDS();
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
        for (PHIEUMUON pm : QLMUONBUS.dspm) {
            ShowOnTablePM(pm);
        }
        table.setModel(model);
    }

    public void ShowOnTablePM(PHIEUMUON pm) {
        Vector<String> row = new Vector<String>();
        row.add(pm.getMaPM().trim());
        row.add(pm.getNgaymuon().trim());
        row.add(String.valueOf(pm.getSLtong()));
        row.add(pm.getNgaytra().trim());
        row.add(pm.getTinhTrangMuon().trim());
        row.add(pm.getMaDG().trim());
        model.addRow(row);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btTimKiem) {
            String tukhoa = txKhoaTK.getText();
            if (tukhoa.equals("") == true) {
                JOptionPane.showMessageDialog(null, "Mời nhập từ khóa cần tìm", "Lỗi", JOptionPane.ERROR_MESSAGE);
            } else {
                model.setRowCount(0);
                ArrayList<PHIEUMUON> kq = new ArrayList<PHIEUMUON>();
                for (PHIEUMUON pm : QLMUONBUS.dspm) {
                    if (tukhoa.indexOf(pm.getMaPM()) >= 0 || tukhoa.indexOf(pm.getNgaymuon()) >= 0
                            || tukhoa.indexOf(pm.getNgaytra()) >= 0 || tukhoa.indexOf(pm.getSLtong()) >= 0
                            || tukhoa.indexOf(pm.getTinhTrangMuon()) >= 0 || tukhoa.indexOf(pm.getMaDG()) >= 0) {
                        kq.add(pm);
                    }
                }
                if (kq.size() == 0) {
                    JOptionPane.showMessageDialog(null, "Không tìm thấy dữ liệu", "Lỗi", JOptionPane.ERROR_MESSAGE);
                } else {
                    for (PHIEUMUON pm : kq) {
                        ShowOnTablePM(pm);
                    }
                    table.setModel(model);
                }
            }
        }
        if (e.getSource() == btLuaChon) {
            int i = table.getSelectedRow();
            if (i >= 0) {
                BangTTPM.txMaPM.setText(String.valueOf(model.getValueAt(i, 0)));
                String MaDG = String.valueOf(model.getValueAt(i,5)); 
                String TenDG = null;
                //Tìm kiếm tên độc giả qua mã độc giả
                QLNVBUS qlnvbus = new QLNVBUS();
                if(QLNVBUS.dsdg==null){
                    try {
                        qlnvbus.docDS();
                    } catch (Exception e1) {
                        e1.printStackTrace();
                    }
                }
                for (DOCGIA dg : QLNVBUS.dsdg){
                    if(dg.getMaDG().trim().equals(MaDG)){
                        TenDG = dg.getTenDG();
                    }
                }
                BangTTPM.txTenDG.setText(MaDG+"-"+TenDG);
                String tmp[] = String.valueOf(model.getValueAt(i,1)).split("-");
                int year = Integer.parseInt(tmp[0]);
                int month = Integer.parseInt(tmp[1]);
                int day = Integer.parseInt(tmp[2]);
                BangTTPM.datePickerNgayBDPM.getModel().setDate(year, month, day);
                String tmp1[] = String.valueOf(model.getValueAt(i,3)).split("-");
                year = Integer.parseInt(tmp1[0]);
                month = Integer.parseInt(tmp1[1]);
                day = Integer.parseInt(tmp1[2]);
                BangTTPM.datePickerNgayKTPM.getModel().setDate(year, month, day);
                BangTTPM.txSLtong.setText(String.valueOf(model.getValueAt(i,2)));
            }
            this.dispose();
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

}

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

import QLTV.BUS.QLHDTPBUS;
import QLTV.BUS.QLMUONBUS;
import QLTV.BUS.QLNVBUS;
import QLTV.BUS.QLSACHBUS;
import QLTV.BUS.QLTRABUS;
import QLTV.DTO.DOCGIA;
import QLTV.DTO.HDTIENPHAT;
import QLTV.DTO.PHIEUMUON;
import QLTV.DTO.PHIEUTRASACH;
import QLTV.DTO.SACH;
import QLTV.GUI.QLMTGUI;

public class HoTroNhap extends JFrame implements MouseListener, ActionListener {
    JLabel lbHoTro, lbTuKhoaTK;
    JTextField txKhoaTK;
    JPanel pnTable;
    JButton btLuaChon, btTimKiemPM, btTimKiemPT, btTimKiemHD, btTimKiemDG, btLuaChonCTPT,
            btLuaChonCTHDTP, btLuaChonSachPT, btLuaChonSachHD;
    JButton btLuaChonInPM, btTimKiemInPM, btLuaChonCTPM, btTimKiemSach, btLuaChonSach, btLuachonPM_Tra, btLuaChonMaDG, btLuaChonInHD, btTimKiemInHD;
    JTable table;
    DefaultTableModel model;
    Vector<String> header;
    JScrollPane pane;

    public HoTroNhap() {

    }

    public void setHoTroNhapInPM() {
        MyTable myTable = new MyTable();
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setSize(700, 600);
        this.setTitle("Lựa chọn thông tin");
        this.setLayout(null);
        this.setLocationRelativeTo(null);

        pnTable = new JPanel();
        pnTable.setLayout(new GridLayout(1, 1));
        pnTable.setBounds(40, 100, 600, 300);

        setTitleInPM();
        setTable();
        addTTPMOnTable();
        myTable.setValueCellCenter(model, table);

        this.add(pnTable);
        this.setVisible(true);
    }

    public void setHoTroNhapCTPM() {
        MyTable myTable = new MyTable();
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setSize(700, 600);
        this.setTitle("Lựa chọn thông tin");
        this.setLayout(null);
        this.setLocationRelativeTo(null);

        pnTable = new JPanel();
        pnTable.setLayout(new GridLayout(1, 1));
        pnTable.setBounds(40, 100, 600, 300);

        setTitleCTPM();
        setTable();
        addTTPMOnTable();
        myTable.setValueCellCenter(model, table);

        this.add(pnTable);
        this.setVisible(true);
    }

    public void setHoTroNhapPM_TRA() {
        MyTable myTable = new MyTable();
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setSize(700, 600);
        this.setTitle("Lựa chọn thông tin");
        this.setLayout(null);
        this.setLocationRelativeTo(null);

        pnTable = new JPanel();
        pnTable.setLayout(new GridLayout(1, 1));
        pnTable.setBounds(40, 100, 600, 300);

        setTitlePM_TRA();
        setTable();
        addTTPMOnTable();
        myTable.setValueCellCenter(model, table);

        this.add(pnTable);
        this.setVisible(true);
    }

    public void setHoTroNhapCTPT() {
        MyTable myTable = new MyTable();
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setSize(700, 600);
        this.setTitle("Lựa chọn thông tin");
        this.setLayout(null);
        this.setLocationRelativeTo(null);

        pnTable = new JPanel();
        pnTable.setLayout(new GridLayout(1, 1));
        pnTable.setBounds(40, 100, 600, 300);

        setTitleCTPT();
        setTable();
        addTTPTOnTable();
        myTable.setValueCellCenter(model, table);

        this.add(pnTable);
        this.setVisible(true);
    }

    public void setHoTroNhapInHD() {
        MyTable myTable = new MyTable();
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setSize(700, 600);
        this.setTitle("Lựa chọn thông tin");
        this.setLayout(null);
        this.setLocationRelativeTo(null);

        pnTable = new JPanel();
        pnTable.setLayout(new GridLayout(1, 1));
        pnTable.setBounds(40, 100, 600, 300);

        setTitleInHD();
        setTable();
        addTTHDOnTable();
        myTable.setValueCellCenter(model, table);

        this.add(pnTable);
        this.setVisible(true);
    }

    public void setHoTroNhapCTHDTP() {
        MyTable myTable = new MyTable();
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setSize(700, 600);
        this.setTitle("Lựa chọn thông tin");
        this.setLayout(null);
        this.setLocationRelativeTo(null);

        pnTable = new JPanel();
        pnTable.setLayout(new GridLayout(1, 1));
        pnTable.setBounds(40, 100, 600, 300);

        setTitleCTHDTP();
        setTable();
        addTTHDOnTable();
        myTable.setValueCellCenter(model, table);

        this.add(pnTable);
        this.setVisible(true);
    }

    public void setHoTroNhapMasach() {
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setSize(700, 600);
        this.setTitle("Lựa chọn thông tin");
        this.setLayout(null);
        this.setLocationRelativeTo(null);

        pnTable = new JPanel();
        pnTable.setLayout(new GridLayout(1, 1));
        pnTable.setBounds(40, 100, 600, 300);

        setTitleSach();
        setTable();
        addTTSachOnTable();

        this.add(pnTable);
        this.setVisible(true);
    }

    public void setHoTroNhapMaDG() {
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setSize(700, 600);
        this.setTitle("Lựa chọn thông tin");
        this.setLayout(null);
        this.setLocationRelativeTo(null);

        pnTable = new JPanel();
        pnTable.setLayout(new GridLayout(1, 1));
        pnTable.setBounds(40, 100, 600, 300);

        setTitleMaDG();
        setTable();
        addTTDGOnTable();

        this.add(pnTable);
        this.setVisible(true);
    }

    public void setTitleInPM() {
        lbHoTro = new JLabel("LỰA CHỌN PHIẾU MƯỢN CẦN IN");
        lbHoTro.setFont(new Font("Arial", Font.BOLD, 20));
        lbHoTro.setBounds(180, 0, 320, 50);

        btLuaChonInPM = new JButton("Chọn");
        btLuaChonInPM.setFont(new Font("Arial", Font.BOLD, 15));
        btLuaChonInPM.setBounds(560, 420, 80, 30);
        btLuaChonInPM.setBackground(Color.cyan);
        btLuaChonInPM.setBorder(new RoundedBorder(10));
        btLuaChonInPM.addActionListener(this);

        lbTuKhoaTK = new JLabel("Nhập từ khóa tìm kiếm:");
        lbTuKhoaTK.setFont(new Font("Arial", Font.BOLD, 20));
        lbTuKhoaTK.setBounds(40, 20, 250, 100);

        txKhoaTK = new JTextField();
        txKhoaTK.setFont(new Font("Arial", Font.PLAIN, 15));
        txKhoaTK.setBounds(270, 55, 150, 30);

        btTimKiemInPM = new JButton("Tìm kiếm");
        btTimKiemInPM.setFont(new Font("Arial", Font.BOLD, 15));
        btTimKiemInPM.setBounds(430, 55, 100, 30);
        btTimKiemInPM.setBackground(Color.cyan);
        btTimKiemInPM.setBorder(new RoundedBorder(10));
        btTimKiemInPM.addActionListener(this);

        this.add(lbHoTro);
        this.add(btLuaChonInPM);
        this.add(lbTuKhoaTK);
        this.add(txKhoaTK);
        this.add(btTimKiemInPM);
    }

    public void setTitleInHD() {
        lbHoTro = new JLabel("LỰA CHỌN IN HÓA ĐƠN TIỀN PHẠT");
        lbHoTro.setFont(new Font("Arial", Font.BOLD, 20));
        lbHoTro.setBounds(180, 0, 350, 50);

        btLuaChonInHD = new JButton("Chọn");
        btLuaChonInHD.setFont(new Font("Arial", Font.BOLD, 15));
        btLuaChonInHD.setBounds(560, 420, 80, 30);
        btLuaChonInHD.setBackground(Color.cyan);
        btLuaChonInHD.setBorder(new RoundedBorder(10));
        btLuaChonInHD.addActionListener(this);

        lbTuKhoaTK = new JLabel("Nhập từ khóa tìm kiếm:");
        lbTuKhoaTK.setFont(new Font("Arial", Font.BOLD, 20));
        lbTuKhoaTK.setBounds(40, 20, 250, 100);

        txKhoaTK = new JTextField();
        txKhoaTK.setFont(new Font("Arial", Font.PLAIN, 15));
        txKhoaTK.setBounds(270, 55, 150, 30);

        btTimKiemInHD = new JButton("Tìm kiếm");
        btTimKiemInHD.setFont(new Font("Arial", Font.BOLD, 15));
        btTimKiemInHD.setBounds(430, 55, 100, 30);
        btTimKiemInHD.setBackground(Color.cyan);
        btTimKiemInHD.setBorder(new RoundedBorder(10));
        btTimKiemInHD.addActionListener(this);

        this.add(lbHoTro);
        this.add(btLuaChonInHD);
        this.add(lbTuKhoaTK);
        this.add(txKhoaTK);
        this.add(btTimKiemInHD);
    }

    public void setTitleCTPM() {
        lbHoTro = new JLabel("LỰA CHỌN PHIẾU MƯỢN");
        lbHoTro.setFont(new Font("Arial", Font.BOLD, 20));
        lbHoTro.setBounds(210, 0, 320, 50);

        btLuaChonCTPM = new JButton("Chọn");
        btLuaChonCTPM.setFont(new Font("Arial", Font.BOLD, 15));
        btLuaChonCTPM.setBounds(560, 420, 80, 30);
        btLuaChonCTPM.setBackground(Color.cyan);
        btLuaChonCTPM.setBorder(new RoundedBorder(10));
        btLuaChonCTPM.addActionListener(this);

        lbTuKhoaTK = new JLabel("Nhập từ khóa tìm kiếm:");
        lbTuKhoaTK.setFont(new Font("Arial", Font.BOLD, 20));
        lbTuKhoaTK.setBounds(40, 20, 250, 100);

        txKhoaTK = new JTextField();
        txKhoaTK.setFont(new Font("Arial", Font.PLAIN, 15));
        txKhoaTK.setBounds(270, 55, 150, 30);

        btTimKiemPM = new JButton("Tìm kiếm");
        btTimKiemPM.setFont(new Font("Arial", Font.BOLD, 15));
        btTimKiemPM.setBounds(430, 55, 100, 30);
        btTimKiemPM.setBackground(Color.cyan);
        btTimKiemPM.setBorder(new RoundedBorder(10));
        btTimKiemPM.addActionListener(this);

        this.add(lbHoTro);
        this.add(btLuaChonCTPM);
        this.add(lbTuKhoaTK);
        this.add(txKhoaTK);
        this.add(btTimKiemPM);
    }

    public void setTitlePM_TRA() {
        lbHoTro = new JLabel("LỰA CHỌN PHIẾU MƯỢN");
        lbHoTro.setFont(new Font("Arial", Font.BOLD, 20));
        lbHoTro.setBounds(210, 0, 320, 50);

        btLuachonPM_Tra = new JButton("Chọn");
        btLuachonPM_Tra.setFont(new Font("Arial", Font.BOLD, 15));
        btLuachonPM_Tra.setBounds(560, 420, 80, 30);
        btLuachonPM_Tra.setBackground(Color.cyan);
        btLuachonPM_Tra.setBorder(new RoundedBorder(10));
        btLuachonPM_Tra.addActionListener(this);

        lbTuKhoaTK = new JLabel("Nhập từ khóa tìm kiếm:");
        lbTuKhoaTK.setFont(new Font("Arial", Font.BOLD, 20));
        lbTuKhoaTK.setBounds(40, 20, 250, 100);

        txKhoaTK = new JTextField();
        txKhoaTK.setFont(new Font("Arial", Font.PLAIN, 15));
        txKhoaTK.setBounds(270, 55, 150, 30);

        btTimKiemPM = new JButton("Tìm kiếm");
        btTimKiemPM.setFont(new Font("Arial", Font.BOLD, 15));
        btTimKiemPM.setBounds(430, 55, 100, 30);
        btTimKiemPM.setBackground(Color.cyan);
        btTimKiemPM.setBorder(new RoundedBorder(10));
        btTimKiemPM.addActionListener(this);

        this.add(lbHoTro);
        this.add(btLuachonPM_Tra);
        this.add(lbTuKhoaTK);
        this.add(txKhoaTK);
        this.add(btTimKiemPM);
    }

    public void setTitleCTPT() {
        lbHoTro = new JLabel("LỰA CHỌN PHIẾU TRẢ");
        lbHoTro.setFont(new Font("Arial", Font.BOLD, 20));
        lbHoTro.setBounds(210, 0, 320, 50);

        btLuaChonCTPT = new JButton("Chọn");
        btLuaChonCTPT.setFont(new Font("Arial", Font.BOLD, 15));
        btLuaChonCTPT.setBounds(560, 420, 80, 30);
        btLuaChonCTPT.setBackground(Color.cyan);
        btLuaChonCTPT.setBorder(new RoundedBorder(10));
        btLuaChonCTPT.addActionListener(this);

        lbTuKhoaTK = new JLabel("Nhập từ khóa tìm kiếm:");
        lbTuKhoaTK.setFont(new Font("Arial", Font.BOLD, 20));
        lbTuKhoaTK.setBounds(40, 20, 250, 100);

        txKhoaTK = new JTextField();
        txKhoaTK.setFont(new Font("Arial", Font.PLAIN, 15));
        txKhoaTK.setBounds(270, 55, 150, 30);

        btTimKiemPT = new JButton("Tìm kiếm");
        btTimKiemPT.setFont(new Font("Arial", Font.BOLD, 15));
        btTimKiemPT.setBounds(430, 55, 100, 30);
        btTimKiemPT.setBackground(Color.cyan);
        btTimKiemPT.setBorder(new RoundedBorder(10));
        btTimKiemPT.addActionListener(this);

        this.add(lbHoTro);
        this.add(btLuaChonCTPT);
        this.add(lbTuKhoaTK);
        this.add(txKhoaTK);
        this.add(btTimKiemPT);
    }

    public void setTitleCTHDTP() {
        lbHoTro = new JLabel("LỰA CHỌN HÓA ĐƠN TIỀN PHẠT");
        lbHoTro.setFont(new Font("Arial", Font.BOLD, 20));
        lbHoTro.setBounds(210, 0, 320, 50);

        btLuaChonCTHDTP = new JButton("Chọn");
        btLuaChonCTHDTP.setFont(new Font("Arial", Font.BOLD, 15));
        btLuaChonCTHDTP.setBounds(560, 420, 80, 30);
        btLuaChonCTHDTP.setBackground(Color.cyan);
        btLuaChonCTHDTP.setBorder(new RoundedBorder(10));
        btLuaChonCTHDTP.addActionListener(this);

        lbTuKhoaTK = new JLabel("Nhập từ khóa tìm kiếm:");
        lbTuKhoaTK.setFont(new Font("Arial", Font.BOLD, 20));
        lbTuKhoaTK.setBounds(40, 20, 250, 100);

        txKhoaTK = new JTextField();
        txKhoaTK.setFont(new Font("Arial", Font.PLAIN, 15));
        txKhoaTK.setBounds(270, 55, 150, 30);

        btTimKiemHD = new JButton("Tìm kiếm");
        btTimKiemHD.setFont(new Font("Arial", Font.BOLD, 15));
        btTimKiemHD.setBounds(430, 55, 100, 30);
        btTimKiemHD.setBackground(Color.cyan);
        btTimKiemHD.setBorder(new RoundedBorder(10));
        btTimKiemHD.addActionListener(this);

        this.add(lbHoTro);
        this.add(btLuaChonCTHDTP);
        this.add(lbTuKhoaTK);
        this.add(txKhoaTK);
        this.add(btTimKiemHD);
    }

    public void setTitleSach() {
        lbHoTro = new JLabel("LỰA CHỌN MÃ SÁCH");
        lbHoTro.setFont(new Font("Arial", Font.BOLD, 20));
        lbHoTro.setBounds(210, 0, 320, 50);

        btLuaChonSach = new JButton("Chọn");
        btLuaChonSach.setFont(new Font("Arial", Font.BOLD, 15));
        btLuaChonSach.setBounds(560, 420, 80, 30);
        btLuaChonSach.setBackground(Color.cyan);
        btLuaChonSach.setBorder(new RoundedBorder(10));
        btLuaChonSach.addActionListener(this);

        lbTuKhoaTK = new JLabel("Nhập từ khóa tìm kiếm:");
        lbTuKhoaTK.setFont(new Font("Arial", Font.BOLD, 20));
        lbTuKhoaTK.setBounds(40, 20, 250, 100);

        txKhoaTK = new JTextField();
        txKhoaTK.setFont(new Font("Arial", Font.PLAIN, 15));
        txKhoaTK.setBounds(270, 55, 150, 30);

        btTimKiemSach = new JButton("Tìm kiếm");
        btTimKiemSach.setFont(new Font("Arial", Font.BOLD, 15));
        btTimKiemSach.setBounds(430, 55, 100, 30);
        btTimKiemSach.setBackground(Color.cyan);
        btTimKiemSach.setBorder(new RoundedBorder(10));
        btTimKiemSach.addActionListener(this);

        this.add(lbHoTro);
        this.add(btLuaChonSach);
        this.add(lbTuKhoaTK);
        this.add(txKhoaTK);
        this.add(btTimKiemSach);
    }

    public void setTitleMaDG() {
        lbHoTro = new JLabel("LỰA CHỌN MÃ ĐỘC GIẢ");
        lbHoTro.setFont(new Font("Arial", Font.BOLD, 20));
        lbHoTro.setBounds(210, 0, 320, 50);

        btLuaChonMaDG = new JButton("Chọn");
        btLuaChonMaDG.setFont(new Font("Arial", Font.BOLD, 15));
        btLuaChonMaDG.setBounds(560, 420, 80, 30);
        btLuaChonMaDG.setBackground(Color.cyan);
        btLuaChonMaDG.setBorder(new RoundedBorder(10));
        btLuaChonMaDG.addActionListener(this);

        lbTuKhoaTK = new JLabel("Nhập từ khóa tìm kiếm:");
        lbTuKhoaTK.setFont(new Font("Arial", Font.BOLD, 20));
        lbTuKhoaTK.setBounds(40, 20, 250, 100);

        txKhoaTK = new JTextField();
        txKhoaTK.setFont(new Font("Arial", Font.PLAIN, 15));
        txKhoaTK.setBounds(270, 55, 150, 30);

        btTimKiemDG = new JButton("Tìm kiếm");
        btTimKiemDG.setFont(new Font("Arial", Font.BOLD, 15));
        btTimKiemDG.setBounds(430, 55, 100, 30);
        btTimKiemDG.setBackground(Color.cyan);
        btTimKiemDG.setBorder(new RoundedBorder(10));
        btTimKiemDG.addActionListener(this);

        this.add(lbHoTro);
        this.add(btLuaChonMaDG);
        this.add(lbTuKhoaTK);
        this.add(txKhoaTK);
        this.add(btTimKiemDG);
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

    public void addTTPTOnTable() {
        Vector<String> header = new Vector<String>();
        header.add("Mã phiếu trả");
        header.add("Ngày trả");
        header.add("Tình trạng sách");
        header.add("Tiền thuê");
        header.add("Thành tiền");
        header.add("Mã phiếu mượn");
        model = new DefaultTableModel(header, 0);
        QLTRABUS qltrabus = new QLTRABUS();
        if (QLTRABUS.dspt == null) {
            try {
                qltrabus.docDSPT();
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
        for (PHIEUTRASACH pt : QLTRABUS.dspt) {
            ShowOnTablePT(pt);
        }
        table.setModel(model);
    }

    public void addTTHDOnTable() { // Thống tin hóa đơn
        Vector<String> header = new Vector<String>();
        header.add("Mã hóa đơn");
        header.add("Mã độc giả");
        header.add("SL tổng");
        header.add("Tiền phạt");
        model = new DefaultTableModel(header, 0);
        QLHDTPBUS qlbus = new QLHDTPBUS();
        if (QLHDTPBUS.dshdtp == null) {
            try {
                qlbus.docDS();
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
        for (HDTIENPHAT hd : QLHDTPBUS.dshdtp) {
            ShowOnTableHD(hd);
        }
        table.setModel(model);
    }

    public void addTTSachOnTable() {
        Vector<String> header = new Vector<String>();
        header.add("Mã sách");
        header.add("Tên sách");
        header.add("Mã NXB");
        header.add("Mã tác giả");
        header.add("Năm xuất bản");
        header.add("SL tổng");
        header.add("SL");
        header.add("Đơn giá");
        model = new DefaultTableModel(header, 0);
        QLSACHBUS qlbus = new QLSACHBUS();
        if (QLSACHBUS.dssach == null) {
            try {
                qlbus.docDSSACH();
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
        for (SACH sach : QLSACHBUS.dssach) {
            ShowOnTableSach(sach);
        }
        table.setModel(model);
    }
    public void addTTDGOnTable() {
        Vector<String> header = new Vector<String>();
        header.add("Mã độc giả");
        header.add("Tên độc giả");
        header.add("Địa chỉ");
        header.add("Email");
        header.add("Tình trạng thuê");
        model = new DefaultTableModel(header, 0);
        QLNVBUS qldocgiabus = new QLNVBUS();
        if (QLNVBUS.dsdg == null) {
            try {
                qldocgiabus.docDS();
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
        for (DOCGIA docgia : QLNVBUS.dsdg) {
            ShowOnTableDG(docgia);
        }
        table.setModel(model);
    }

    public void ShowOnTableSach(SACH sach) {
        Vector<String> row = new Vector<String>();
        row.add(sach.getMasach().trim());
        row.add(sach.getTensach().trim());
        row.add(sach.getMaNXB().trim());
        row.add(sach.getMaTG().trim());
        row.add(sach.getNamXB().trim());
        row.add(String.valueOf(sach.getSLtong()));
        row.add(String.valueOf(sach.getSL()));
        row.add(String.format("%,d", sach.getDongia()));
        model.addRow(row);
    }

    public void ShowOnTableDG(DOCGIA docgia) {
        Vector<String> row = new Vector<String>();
        row.add(docgia.getMaDG().trim());
        row.add(docgia.getTenDG().trim());
        row.add(docgia.getDiachi().trim());
        row.add(docgia.getMail().trim());
        row.add(docgia.getTinhtrangthue().trim());
        model.addRow(row);
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

    public void ShowOnTablePT(PHIEUTRASACH pt) {
        Vector<String> row = new Vector<String>();
        row.add(pt.getMaPT().trim());
        row.add(pt.getNgaytra().trim());
        row.add(pt.getTinhtrangsach().trim());
        row.add(String.format("%,d", pt.getTienthue()));
        row.add(String.format("%,d", pt.getThanhtien()));
        row.add(pt.getMaPM().trim());
        model.addRow(row);
    }

    public void ShowOnTableHD(HDTIENPHAT hdtienphat) {
        Vector<String> row = new Vector<String>();
        row.add(hdtienphat.getMaHD().trim());
        row.add(hdtienphat.getMaDG().trim());
        row.add(String.valueOf(hdtienphat.getSL()));
        row.add(String.format("%,d", hdtienphat.getTienphat()));
        model.addRow(row);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btTimKiemInPM) {
            String tukhoa = txKhoaTK.getText();
            if (tukhoa.equals("") == true) {
                model.setRowCount(0);
                for (PHIEUMUON pm : QLMUONBUS.dspm) {
                    ShowOnTablePM(pm);
                }
            } else {
                model.setRowCount(0);
                ArrayList<PHIEUMUON> kq = new ArrayList<PHIEUMUON>();
                for (PHIEUMUON pm : QLMUONBUS.dspm) {
                    if (tukhoa.indexOf(pm.getMaPM().trim()) >= 0 || tukhoa.indexOf(pm.getNgaymuon()) >= 0
                            || tukhoa.indexOf(pm.getNgaytra()) >= 0
                            || tukhoa.indexOf(String.valueOf(pm.getSLtong())) >= 0
                            || tukhoa.indexOf(pm.getTinhTrangMuon().trim()) >= 0
                            || tukhoa.indexOf(pm.getMaDG().trim()) >= 0) {
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
        if(e.getSource()==btTimKiemPM){
            String tukhoa = txKhoaTK.getText();
            if (tukhoa.equals("") == true) {
                model.setRowCount(0);
                for (PHIEUMUON pm : QLMUONBUS.dspm) {
                    ShowOnTablePM(pm);
                }
            } else {
                model.setRowCount(0);
                ArrayList<PHIEUMUON> kq = new ArrayList<PHIEUMUON>();
                for (PHIEUMUON pm : QLMUONBUS.dspm) {
                    if (tukhoa.indexOf(pm.getMaPM().trim()) >= 0 || tukhoa.indexOf(pm.getNgaymuon()) >= 0
                            || tukhoa.indexOf(pm.getNgaytra()) >= 0
                            || tukhoa.indexOf(String.valueOf(pm.getSLtong())) >= 0
                            || tukhoa.indexOf(pm.getTinhTrangMuon().trim()) >= 0
                            || tukhoa.indexOf(pm.getMaDG().trim()) >= 0) {
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
        if (e.getSource() == btTimKiemPT) {
            String tukhoa = txKhoaTK.getText();
            if (tukhoa.equals("") == true) {
                model.setRowCount(0);
                for (PHIEUTRASACH pt : QLTRABUS.dspt) {
                    ShowOnTablePT(pt);
                }
            } else {
                model.setRowCount(0);
                ArrayList<PHIEUTRASACH> kq = new ArrayList<PHIEUTRASACH>();
                for (PHIEUTRASACH pt : QLTRABUS.dspt) {
                    if (tukhoa.indexOf(pt.getMaPT().trim()) >= 0 || tukhoa.indexOf(pt.getNgaytra()) >= 0
                            || tukhoa.indexOf(String.valueOf(pt.getTienthue())) >= 0
                            || tukhoa.indexOf(String.valueOf(pt.getTienthue())) >= 0
                            || tukhoa.indexOf(pt.getTinhtrangsach().trim()) >= 0
                            || tukhoa.indexOf(pt.getMaPM().trim()) >= 0) {
                        kq.add(pt);
                    }
                }
                if (kq.size() == 0) {
                    JOptionPane.showMessageDialog(null, "Không tìm thấy dữ liệu", "Lỗi", JOptionPane.ERROR_MESSAGE);
                } else {
                    for (PHIEUTRASACH pt : kq) {
                        ShowOnTablePT(pt);
                    }
                    table.setModel(model);
                }
            }
        }
        if (e.getSource() == btTimKiemHD) {
            String tukhoa = txKhoaTK.getText();
            if (tukhoa.equals("") == true) {
                model.setRowCount(0);
                for (HDTIENPHAT hd : QLHDTPBUS.dshdtp) {
                    ShowOnTableHD(hd);
                }
            } else {
                model.setRowCount(0);
                ArrayList<HDTIENPHAT> kq = new ArrayList<HDTIENPHAT>();
                for (HDTIENPHAT hd : QLHDTPBUS.dshdtp) {
                    if (tukhoa.indexOf(hd.getMaHD().trim()) >= 0 || tukhoa.indexOf(hd.getMaDG()) >= 0
                            || tukhoa.indexOf(String.valueOf(hd.getTienphat())) >= 0
                            || tukhoa.indexOf(String.valueOf(hd.getSL())) >= 0) {
                        kq.add(hd);
                    }
                }
                if (kq.size() == 0) {
                    JOptionPane.showMessageDialog(null, "Không tìm thấy dữ liệu", "Lỗi", JOptionPane.ERROR_MESSAGE);
                } else {
                    for (HDTIENPHAT hd : kq) {
                        ShowOnTableHD(hd);
                    }
                    table.setModel(model);
                }
            }
        }
        if (e.getSource() == btTimKiemDG) {
            String tukhoa = txKhoaTK.getText();
            if (tukhoa.equals("") == true) {
                model.setRowCount(0);
                for (DOCGIA docgia : QLNVBUS.dsdg) {
                    ShowOnTableDG(docgia);
                }
            } else {
                model.setRowCount(0);
                ArrayList<DOCGIA> kq = new ArrayList<DOCGIA>();
                for (DOCGIA docgia : QLNVBUS.dsdg) {
                    if (tukhoa.indexOf(docgia.getMaDG().trim()) >= 0 || tukhoa.indexOf(docgia.getTenDG()) >= 0
                            || tukhoa.indexOf(docgia.getMail()) >= 0 || tukhoa.indexOf(docgia.getDiachi()) >= 0
                            || tukhoa.indexOf(docgia.getTinhtrangthue()) >= 0) {
                        kq.add(docgia);
                    }
                }
                if (kq.size() == 0) {
                    JOptionPane.showMessageDialog(null, "Không tìm thấy dữ liệu", "Lỗi", JOptionPane.ERROR_MESSAGE);
                } else {
                    for (DOCGIA docgia : kq) {
                        ShowOnTableDG(docgia);
                    }
                    table.setModel(model);
                }
            }
        }
        if (e.getSource() == btTimKiemSach) {
            String tukhoa = txKhoaTK.getText();
            if (tukhoa.equals("") == true) {
                model.setRowCount(0);
                for (SACH sach : QLSACHBUS.dssach) {
                    ShowOnTableSach(sach);
                }
            } else {
                model.setRowCount(0);
                ArrayList<SACH> kq = new ArrayList<SACH>();
                for (SACH sach : QLSACHBUS.dssach) {
                    if (tukhoa.indexOf(sach.getMasach().trim()) >= 0 || tukhoa.indexOf(sach.getTensach().trim()) >= 0 ||
                            tukhoa.indexOf(sach.getMaNXB().trim()) >= 0 || tukhoa.indexOf(sach.getMaTG().trim()) >= 0 ||
                            tukhoa.indexOf(sach.getNamXB().trim()) >= 0
                            || tukhoa.indexOf(String.valueOf(sach.getSLtong())) >= 0 ||
                            tukhoa.indexOf(String.valueOf(sach.getSL())) >= 0
                            || tukhoa.indexOf(String.valueOf(sach.getDongia())) >= 0) {
                        kq.add(sach);
                    }
                }
                if (kq.size() == 0) {
                    JOptionPane.showMessageDialog(null, "Không tìm thấy dữ liệu", "Lỗi", JOptionPane.ERROR_MESSAGE);
                } else {
                    for (SACH sach1 : kq) {
                        ShowOnTableSach(sach1);
                    }
                    table.setModel(model);
                }
            }
        }
        if (e.getSource() == btTimKiemInHD) {
            String tukhoa = txKhoaTK.getText();
            if (tukhoa.equals("") == true) {
                model.setRowCount(0);
                for (HDTIENPHAT hd : QLHDTPBUS.dshdtp) {
                    ShowOnTableHD(hd);
                }
            } else {
                model.setRowCount(0);
                ArrayList<HDTIENPHAT> kq = new ArrayList<HDTIENPHAT>();
                for (HDTIENPHAT hd : QLHDTPBUS.dshdtp) {
                    if (tukhoa.indexOf(hd.getMaHD().trim()) >= 0 || tukhoa.indexOf(hd.getMaDG()) >= 0
                            || tukhoa.indexOf(String.valueOf(hd.getTienphat())) >= 0
                            || tukhoa.indexOf(String.valueOf(hd.getSL())) >= 0) {
                        kq.add(hd);
                    }
                }
                if (kq.size() == 0) {
                    JOptionPane.showMessageDialog(null, "Không tìm thấy dữ liệu", "Lỗi", JOptionPane.ERROR_MESSAGE);
                } else {
                    for (HDTIENPHAT hd : kq) {
                        ShowOnTableHD(hd);
                    }
                    table.setModel(model);
                }
            }
        }
        if (e.getSource() == btLuaChonInPM) {
            int i = table.getSelectedRow();
            if (i >= 0) {
                BangTTIn.txMaPM.setText(String.valueOf(model.getValueAt(i, 0)));
                String MaDG = String.valueOf(model.getValueAt(i, 5));
                String TenDG = null;
                // Tìm kiếm tên độc giả qua mã độc giả
                QLNVBUS qlnvbus = new QLNVBUS();
                if (QLNVBUS.dsdg == null) {
                    try {
                        qlnvbus.docDS();
                    } catch (Exception e1) {
                        e1.printStackTrace();
                    }
                }
                for (DOCGIA dg : QLNVBUS.dsdg) {
                    if (dg.getMaDG().trim().equals(MaDG)) {
                        TenDG = dg.getTenDG();
                    }
                }
                BangTTIn.txTenDGPM.setText(MaDG + "-" + TenDG);
                String tmp[] = String.valueOf(model.getValueAt(i, 1)).split("-");
                int year = Integer.parseInt(tmp[0]);
                int month = Integer.parseInt(tmp[1]);
                int day = Integer.parseInt(tmp[2]);
                BangTTIn.datePickerNgayBDPM.getModel().setDate(year, month, day);
                String tmp1[] = String.valueOf(model.getValueAt(i, 3)).split("-");
                year = Integer.parseInt(tmp1[0]);
                month = Integer.parseInt(tmp1[1]);
                day = Integer.parseInt(tmp1[2]);
                BangTTIn.datePickerNgayKTPM.getModel().setDate(year, month, day);
                BangTTIn.txSLtong.setText(String.valueOf(model.getValueAt(i, 2)));
            }
            this.dispose();
        }
        if (e.getSource() == btLuaChonCTPM) {
            int i = table.getSelectedRow();
            if (i >= 0) {
                QLMTGUI.txCTPMMaPM.setText(String.valueOf(model.getValueAt(i, 0)));
            }
            this.dispose();
        }
        if (e.getSource() == btLuachonPM_Tra) {
            int i = table.getSelectedRow();
            if (i >= 0) {
                QLMTGUI.txMaPMTra.setText(String.valueOf(model.getValueAt(i, 0)));
            }
            this.dispose();
        }
        if (e.getSource() == btLuaChonCTPT) {
            int i = table.getSelectedRow();
            if (i >= 0) {
                QLMTGUI.txCTPTMaPT.setText(String.valueOf(model.getValueAt(i, 0)));
            }
            this.dispose();
        }
        if (e.getSource() == btLuaChonCTHDTP) {
            int i = table.getSelectedRow();
            if (i >= 0) {
                QLMTGUI.txCTHDMaHD.setText(String.valueOf(model.getValueAt(i, 0)));
            }
            this.dispose();
        }
        if (e.getSource() == btLuaChonSach) {
            int i = table.getSelectedRow();
            if (i >= 0) {
                QLMTGUI.txCTPMMaSach.setText(String.valueOf(model.getValueAt(i, 0)));
                QLMTGUI.txCTPTMaSach.setText(String.valueOf(model.getValueAt(i, 0)));
                QLMTGUI.txCTHDMaSach.setText(String.valueOf(model.getValueAt(i, 0)));
            }
            this.dispose();
        }
        if (e.getSource() == btLuaChonMaDG) {
            int i = table.getSelectedRow();
            if (i >= 0) {
                QLMTGUI.txMaDG.setText(String.valueOf(model.getValueAt(i, 0)));
            }
            this.dispose();
        }
        if (e.getSource() == btLuaChonInHD) {
            int i = table.getSelectedRow();
            if (i >= 0) {
                String MaDG = String.valueOf(model.getValueAt(i, 1));
                String TenDG = null;
                if (QLNVBUS.dsdg == null) {
                    QLNVBUS qlbus = new QLNVBUS();
                    try {
                        qlbus.docDS();
                    } catch (Exception e1) {
                        e1.printStackTrace();
                    }
                }
                for (DOCGIA dg : QLNVBUS.dsdg) {
                    if (dg.getMaDG().trim().equals(MaDG)) {
                        TenDG = dg.getTenDG();
                    }
                }
                BangTTIn.txMaHD.setText(String.valueOf(model.getValueAt(i, 0)));
                BangTTIn.txTenDGHD.setText(MaDG + "-" + TenDG);
                BangTTIn.txSLtongHD.setText(String.valueOf(model.getValueAt(i, 2)));
                BangTTIn.txTienPhat.setText(String.valueOf(model.getValueAt(i, 3)));
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

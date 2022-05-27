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
import QLTV.BUS.QLNCCBUS;
import QLTV.BUS.QLNHANVIENBUS;
import QLTV.BUS.QLNVBUS;
import QLTV.BUS.QLPNBUS;
import QLTV.BUS.QLSACHBUS;
import QLTV.BUS.QLTRABUS;
import QLTV.DTO.DOCGIA;
import QLTV.DTO.HDTIENPHAT;
import QLTV.DTO.NHACUNGCAP;
import QLTV.DTO.NHANVIEN;
import QLTV.DTO.PHIEUMUON;
import QLTV.DTO.PHIEUNHAP;
import QLTV.DTO.PHIEUTRASACH;
import QLTV.DTO.SACH;
import QLTV.GUI.QLMTGUI;
import QLTV.GUI.QLNVGUI;
import QLTV.GUI.QLPNGUI;

public class HoTroNhap extends JFrame implements MouseListener, ActionListener {
    public static int ThanhTien = 0;
    MyTable myTable = new MyTable();
    JLabel lbHoTro, lbTuKhoaTK;
    JTextField txKhoaTK;
    JPanel pnTable;
    JButton btLuaChon, btTimKiemPT, btTimKiemHD, btTimKiemDG, btTimKiemPN, btTimKiemNV, btTimKiemNCC,
            btLuaChonCTPT, btLuaChonCTHDTP, btLuaChonSachPT, btLuaChonSachHD, btLuaChonMaPN, btLuaChonMaNV,
            btLuaChonMaNCC;
    JButton btLuaChonInPM, btTimKiemInPM, btLuaChonCTPM, btTimKiemSach, btLuaChonSach, btLuachonPM_Tra, btLuaChonMaDG,
            btLuaChonInHD, btLuachonMaDG_NV, btLuachonMaDG_HD;
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

    public void setHoTroNhapMaDG_NV() {
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setSize(700, 600);
        this.setTitle("Lựa chọn thông tin");
        this.setLayout(null);
        this.setLocationRelativeTo(null);

        pnTable = new JPanel();
        pnTable.setLayout(new GridLayout(1, 1));
        pnTable.setBounds(40, 100, 600, 300);

        setTitleMaDG_NV();
        setTable();
        addTTDGOnTable();

        this.add(pnTable);
        this.setVisible(true);
    }

    public void setHoTroNhapMaDG_HD() {
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setSize(700, 600);
        this.setTitle("Lựa chọn thông tin");
        this.setLayout(null);
        this.setLocationRelativeTo(null);

        pnTable = new JPanel();
        pnTable.setLayout(new GridLayout(1, 1));
        pnTable.setBounds(40, 100, 600, 300);

        setTitleMaDG_HD();
        setTable();
        addTTDGOnTable();

        this.add(pnTable);
        this.setVisible(true);
    }

    public void setHoTroNhapMaPN() {
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setSize(700, 600);
        this.setTitle("Lựa chọn thông tin");
        this.setLayout(null);
        this.setLocationRelativeTo(null);

        pnTable = new JPanel();
        pnTable.setLayout(new GridLayout(1, 1));
        pnTable.setBounds(40, 100, 600, 300);

        setTitleMaPN();
        setTable();
        addTTPNOnTable();

        this.add(pnTable);
        this.setVisible(true);
    }

    public void setHoTroNhapMaNV() {
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setSize(700, 600);
        this.setTitle("Lựa chọn thông tin");
        this.setLayout(null);
        this.setLocationRelativeTo(null);

        pnTable = new JPanel();
        pnTable.setLayout(new GridLayout(1, 1));
        pnTable.setBounds(40, 100, 600, 300);

        setTitleMaNV();
        setTable();
        addTTNVOnTable();

        this.add(pnTable);
        this.setVisible(true);
    }

    public void setHoTroNhapMaNCC() {
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setSize(700, 600);
        this.setTitle("Lựa chọn thông tin");
        this.setLayout(null);
        this.setLocationRelativeTo(null);

        pnTable = new JPanel();
        pnTable.setLayout(new GridLayout(1, 1));
        pnTable.setBounds(40, 100, 600, 300);

        setTitleMaNCC();
        setTable();
        addTTNCCOnTable();

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

        btTimKiemHD = new JButton("Tìm kiếm");
        btTimKiemHD.setFont(new Font("Arial", Font.BOLD, 15));
        btTimKiemHD.setBounds(430, 55, 100, 30);
        btTimKiemHD.setBackground(Color.cyan);
        btTimKiemHD.setBorder(new RoundedBorder(10));
        btTimKiemHD.addActionListener(this);

        this.add(lbHoTro);
        this.add(btLuaChonInHD);
        this.add(lbTuKhoaTK);
        this.add(txKhoaTK);
        this.add(btTimKiemHD);
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

        btTimKiemInPM = new JButton("Tìm kiếm");
        btTimKiemInPM.setFont(new Font("Arial", Font.BOLD, 15));
        btTimKiemInPM.setBounds(430, 55, 100, 30);
        btTimKiemInPM.setBackground(Color.cyan);
        btTimKiemInPM.setBorder(new RoundedBorder(10));
        btTimKiemInPM.addActionListener(this);

        this.add(lbHoTro);
        this.add(btLuaChonCTPM);
        this.add(lbTuKhoaTK);
        this.add(txKhoaTK);
        this.add(btTimKiemInPM);
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

        btTimKiemInPM = new JButton("Tìm kiếm");
        btTimKiemInPM.setFont(new Font("Arial", Font.BOLD, 15));
        btTimKiemInPM.setBounds(430, 55, 100, 30);
        btTimKiemInPM.setBackground(Color.cyan);
        btTimKiemInPM.setBorder(new RoundedBorder(10));
        btTimKiemInPM.addActionListener(this);

        this.add(lbHoTro);
        this.add(btLuachonPM_Tra);
        this.add(lbTuKhoaTK);
        this.add(txKhoaTK);
        this.add(btTimKiemInPM);
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

    public void setTitleMaDG_NV() {
        lbHoTro = new JLabel("LỰA CHỌN MÃ ĐỘC GIẢ");
        lbHoTro.setFont(new Font("Arial", Font.BOLD, 20));
        lbHoTro.setBounds(210, 0, 320, 50);

        btLuachonMaDG_NV = new JButton("Chọn");
        btLuachonMaDG_NV.setFont(new Font("Arial", Font.BOLD, 15));
        btLuachonMaDG_NV.setBounds(560, 420, 80, 30);
        btLuachonMaDG_NV.setBackground(Color.cyan);
        btLuachonMaDG_NV.setBorder(new RoundedBorder(10));
        btLuachonMaDG_NV.addActionListener(this);

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
        this.add(btLuachonMaDG_NV);
        this.add(lbTuKhoaTK);
        this.add(txKhoaTK);
        this.add(btTimKiemDG);
    }

    public void setTitleMaDG_HD() {
        lbHoTro = new JLabel("LỰA CHỌN MÃ ĐỘC GIẢ");
        lbHoTro.setFont(new Font("Arial", Font.BOLD, 20));
        lbHoTro.setBounds(210, 0, 320, 50);

        btLuachonMaDG_HD = new JButton("Chọn");
        btLuachonMaDG_HD.setFont(new Font("Arial", Font.BOLD, 15));
        btLuachonMaDG_HD.setBounds(560, 420, 80, 30);
        btLuachonMaDG_HD.setBackground(Color.cyan);
        btLuachonMaDG_HD.setBorder(new RoundedBorder(10));
        btLuachonMaDG_HD.addActionListener(this);

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
        this.add(btLuachonMaDG_HD);
        this.add(lbTuKhoaTK);
        this.add(txKhoaTK);
        this.add(btTimKiemDG);
    }

    public void setTitleMaPN() {
        lbHoTro = new JLabel("LỰA CHỌN MÃ PHIẾU NHẬP");
        lbHoTro.setFont(new Font("Arial", Font.BOLD, 20));
        lbHoTro.setBounds(210, 0, 320, 50);

        btLuaChonMaPN = new JButton("Chọn");
        btLuaChonMaPN.setFont(new Font("Arial", Font.BOLD, 15));
        btLuaChonMaPN.setBounds(560, 420, 80, 30);
        btLuaChonMaPN.setBackground(Color.cyan);
        btLuaChonMaPN.setBorder(new RoundedBorder(10));
        btLuaChonMaPN.addActionListener(this);

        lbTuKhoaTK = new JLabel("Nhập từ khóa tìm kiếm:");
        lbTuKhoaTK.setFont(new Font("Arial", Font.BOLD, 20));
        lbTuKhoaTK.setBounds(40, 20, 250, 100);

        txKhoaTK = new JTextField();
        txKhoaTK.setFont(new Font("Arial", Font.PLAIN, 15));
        txKhoaTK.setBounds(270, 55, 150, 30);

        btTimKiemPN = new JButton("Tìm kiếm");
        btTimKiemPN.setFont(new Font("Arial", Font.BOLD, 15));
        btTimKiemPN.setBounds(430, 55, 100, 30);
        btTimKiemPN.setBackground(Color.cyan);
        btTimKiemPN.setBorder(new RoundedBorder(10));
        btTimKiemPN.addActionListener(this);

        this.add(lbHoTro);
        this.add(btLuaChonMaPN);
        this.add(lbTuKhoaTK);
        this.add(txKhoaTK);
        this.add(btTimKiemPN);
    }

    public void setTitleMaNV() {
        lbHoTro = new JLabel("LỰA CHỌN MÃ NHÂN VIÊN");
        lbHoTro.setFont(new Font("Arial", Font.BOLD, 20));
        lbHoTro.setBounds(210, 0, 320, 50);

        btLuaChonMaNV = new JButton("Chọn");
        btLuaChonMaNV.setFont(new Font("Arial", Font.BOLD, 15));
        btLuaChonMaNV.setBounds(560, 420, 80, 30);
        btLuaChonMaNV.setBackground(Color.cyan);
        btLuaChonMaNV.setBorder(new RoundedBorder(10));
        btLuaChonMaNV.addActionListener(this);

        lbTuKhoaTK = new JLabel("Nhập từ khóa tìm kiếm:");
        lbTuKhoaTK.setFont(new Font("Arial", Font.BOLD, 20));
        lbTuKhoaTK.setBounds(40, 20, 250, 100);

        txKhoaTK = new JTextField();
        txKhoaTK.setFont(new Font("Arial", Font.PLAIN, 15));
        txKhoaTK.setBounds(270, 55, 150, 30);

        btTimKiemNV = new JButton("Tìm kiếm");
        btTimKiemNV.setFont(new Font("Arial", Font.BOLD, 15));
        btTimKiemNV.setBounds(430, 55, 100, 30);
        btTimKiemNV.setBackground(Color.cyan);
        btTimKiemNV.setBorder(new RoundedBorder(10));
        btTimKiemNV.addActionListener(this);

        this.add(lbHoTro);
        this.add(btLuaChonMaNV);
        this.add(lbTuKhoaTK);
        this.add(txKhoaTK);
        this.add(btTimKiemNV);
    }

    public void setTitleMaNCC() {
        lbHoTro = new JLabel("LỰA CHỌN MÃ NCC");
        lbHoTro.setFont(new Font("Arial", Font.BOLD, 20));
        lbHoTro.setBounds(210, 0, 320, 50);

        btLuaChonMaNCC = new JButton("Chọn");
        btLuaChonMaNCC.setFont(new Font("Arial", Font.BOLD, 15));
        btLuaChonMaNCC.setBounds(560, 420, 80, 30);
        btLuaChonMaNCC.setBackground(Color.cyan);
        btLuaChonMaNCC.setBorder(new RoundedBorder(10));
        btLuaChonMaNCC.addActionListener(this);

        lbTuKhoaTK = new JLabel("Nhập từ khóa tìm kiếm:");
        lbTuKhoaTK.setFont(new Font("Arial", Font.BOLD, 20));
        lbTuKhoaTK.setBounds(40, 20, 250, 100);

        txKhoaTK = new JTextField();
        txKhoaTK.setFont(new Font("Arial", Font.PLAIN, 15));
        txKhoaTK.setBounds(270, 55, 150, 30);

        btTimKiemNCC = new JButton("Tìm kiếm");
        btTimKiemNCC.setFont(new Font("Arial", Font.BOLD, 15));
        btTimKiemNCC.setBounds(430, 55, 100, 30);
        btTimKiemNCC.setBackground(Color.cyan);
        btTimKiemNCC.setBorder(new RoundedBorder(10));
        btTimKiemNCC.addActionListener(this);

        this.add(lbHoTro);
        this.add(btLuaChonMaNCC);
        this.add(lbTuKhoaTK);
        this.add(txKhoaTK);
        this.add(btTimKiemNCC);
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

    public void addTTPNOnTable() {
        Vector<String> header = new Vector<String>();
        header.add("Mã phiếu nhập");
        header.add("Ngày nhập");
        header.add("Số lượng tổng");
        header.add("Đơn giá");
        header.add("Mã nhân viên");
        header.add("Mã NCC");
        model = new DefaultTableModel(header, 0);
        QLPNBUS qlPNbus = new QLPNBUS();
        if (QLTRABUS.dspt == null) {
            try {
                qlPNbus.docDS();
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
        for (PHIEUNHAP pn : QLPNBUS.dspn) {
            ShowOnTablePN(pn);
        }
        table.setModel(model);
    }

    public void addTTNVOnTable() {
        Vector<String> header = new Vector<String>();
        header.add("Mã nhân viên");
        header.add("Tên nhân viên");
        header.add("Chức vụ");
        header.add("Lương cơ bản");
        header.add("Phụ cấp");
        header.add("Hệ số lượng");
        header.add("SĐT");
        header.add("Mail");

        model = new DefaultTableModel(header, 0);
        QLNHANVIENBUS qlnvbus = new QLNHANVIENBUS();
        if (QLNHANVIENBUS.dsnhanvien == null) {
            try {
                qlnvbus.docDSNHANVIEN();
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
        for (NHANVIEN nv : QLNHANVIENBUS.dsnhanvien) {
            ShowOnTableNV(nv);
        }
        table.setModel(model);
    }

    public void addTTNCCOnTable() {
        Vector<String> header = new Vector<String>();
        header.add("Mã NCC");
        header.add("Tên NCC");
        model = new DefaultTableModel(header, 0);
        QLNCCBUS qlNCCbus = new QLNCCBUS();
        if (QLNCCBUS.dsncc == null) {
            try {
                qlNCCbus.docDSNCC();
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
        for (NHACUNGCAP ncc : QLNCCBUS.dsncc) {
            ShowOnTableNCC(ncc);
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

    public void ShowOnTablePN(PHIEUNHAP phieunhap) {
        Vector<String> row = new Vector<String>();
        row.add(phieunhap.getMaPN().trim());
        row.add(phieunhap.getNgaynhap().trim());
        row.add(String.valueOf(phieunhap.getSLTong()).trim());
        row.add(String.format("%,d", phieunhap.getDongia()));
        row.add(phieunhap.getMaNV().trim());
        row.add(phieunhap.getMaNCC().trim());
        model.addRow(row);
    }

    public void ShowOnTableNCC(NHACUNGCAP NCC) {
        Vector<String> row = new Vector<String>();
        row.add(NCC.getMaNCC().trim());
        row.add(NCC.getTenNCC().trim());
        model.addRow(row);
    }

    public void ShowOnTableNV(NHANVIEN nhanvien) {
        Vector<String> row = new Vector<String>();
        row.add(nhanvien.getMaNV().trim());
        row.add(nhanvien.getTenNV().trim());
        row.add(nhanvien.getChucvu().trim());
        row.add(String.format("%,d", nhanvien.getLuongCB()).trim());
        row.add(String.format("%,d", nhanvien.getPhucap()).trim());
        row.add(String.format("%,f", nhanvien.getHesoluong()).trim());
        row.add(String.valueOf(nhanvien.getSDT()));
        row.add(nhanvien.getMail().trim());
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
            String tukhoa = txKhoaTK.getText().replaceAll("\\s+", "").toLowerCase().trim();
            if (tukhoa.equals("") == true) {
                model.setRowCount(0);
                for (PHIEUMUON pm : QLMUONBUS.dspm) {
                    ShowOnTablePM(pm);
                }
            } else {
                model.setRowCount(0);
                ArrayList<PHIEUMUON> kq = new ArrayList<PHIEUMUON>();
                for (PHIEUMUON pm : QLMUONBUS.dspm) {
                    if (String.valueOf(pm.getMaPM().toLowerCase()).indexOf(tukhoa) >= 0
                            || String.valueOf(pm.getNgaymuon().replaceAll("\\s+", "")).indexOf(tukhoa) >= 0
                            || String.valueOf(pm.getNgaytra().replaceAll("\\s+", "")).indexOf(tukhoa) >= 0
                            || String.valueOf(pm.getSLtong()).indexOf(tukhoa) >= 0
                            || String.valueOf(pm.getTinhTrangMuon().replaceAll("\\s+", "").toLowerCase()).indexOf(tukhoa) >= 0
                            || String.valueOf(pm.getMaDG().toLowerCase()).indexOf(tukhoa) >= 0) {
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
            String tukhoa = txKhoaTK.getText().replaceAll("\\s+", "").toLowerCase().trim();
            if (tukhoa.equals("") == true) {
                model.setRowCount(0);
                for (PHIEUTRASACH pt : QLTRABUS.dspt) {
                    ShowOnTablePT(pt);
                }
            } else {
                model.setRowCount(0);
                ArrayList<PHIEUTRASACH> kq = new ArrayList<PHIEUTRASACH>();
                for (PHIEUTRASACH pt : QLTRABUS.dspt) {
                    if (String.valueOf(pt.getMaPT().toLowerCase()).indexOf(tukhoa) >= 0
                            || String.valueOf(pt.getNgaytra().replaceAll("\\s+", "")).indexOf(tukhoa) >= 0
                            || String.valueOf(pt.getTienthue()).indexOf(tukhoa) >= 0
                            || String.valueOf(pt.getThanhtien()).indexOf(tukhoa) >= 0
                            || String.valueOf(pt.getTinhtrangsach().replaceAll("\\s+", "").toLowerCase()).indexOf(tukhoa) >= 0
                            || String.valueOf(pt.getMaPM().toLowerCase()).indexOf(tukhoa) >= 0) {
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
            String tukhoa = txKhoaTK.getText().replaceAll("\\s+", "").toLowerCase().trim();
            if (tukhoa.equals("") == true) {
                model.setRowCount(0);
                for (HDTIENPHAT hd : QLHDTPBUS.dshdtp) {
                    ShowOnTableHD(hd);
                }
            } else {
                model.setRowCount(0);
                ArrayList<HDTIENPHAT> kq = new ArrayList<HDTIENPHAT>();
                for (HDTIENPHAT hd : QLHDTPBUS.dshdtp) {
                    if (String.valueOf(hd.getMaHD().toLowerCase()).indexOf(tukhoa) >= 0
                            || String.valueOf(hd.getMaDG().toLowerCase()).indexOf(tukhoa) >= 0
                            || String.valueOf(hd.getTienphat()).indexOf(tukhoa) >= 0
                            || String.valueOf(hd.getSL()).indexOf(tukhoa) >= 0) {
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
            String tukhoa = txKhoaTK.getText().replaceAll("\\s+", "").toLowerCase().trim();
            if (tukhoa.equals("") == true) {
                model.setRowCount(0);
                for (DOCGIA docgia : QLNVBUS.dsdg) {
                    ShowOnTableDG(docgia);
                }
            } else {
                model.setRowCount(0);
                ArrayList<DOCGIA> kq = new ArrayList<DOCGIA>();
                for (DOCGIA docgia : QLNVBUS.dsdg) {
                    if (String.valueOf(docgia.getMaDG().toLowerCase()).indexOf(tukhoa) >= 0
                            || String.valueOf(docgia.getTenDG().replaceAll("\\s+", "").toLowerCase()).indexOf(tukhoa) >= 0
                            || String.valueOf(docgia.getMail().toLowerCase()).indexOf(tukhoa) >= 0
                            || String.valueOf(docgia.getDiachi().replaceAll("\\s+", "").toLowerCase()).indexOf(tukhoa) >= 0
                            || String.valueOf(docgia.getTinhtrangthue().replaceAll("\\s+", "").toLowerCase()).indexOf(tukhoa) >= 0) {
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
        if (e.getSource() == btTimKiemPN) {
            String tukhoa = txKhoaTK.getText().replaceAll("\\s+", "").toLowerCase().trim();
            if (tukhoa.equals("") == true) {
                model.setRowCount(0);
                for (PHIEUNHAP pn : QLPNBUS.dspn) {
                    ShowOnTablePN(pn);
                }
            } else {
                model.setRowCount(0);
                ArrayList<PHIEUNHAP> kq = new ArrayList<PHIEUNHAP>();
                for (PHIEUNHAP pn : QLPNBUS.dspn) {
                    if (String.valueOf(pn.getMaPN().toLowerCase()).indexOf(tukhoa) >= 0
                            || String.valueOf(pn.getNgaynhap().replaceAll("\\s+", "")).indexOf(tukhoa) >= 0
                            || String.valueOf(pn.getSLTong()).indexOf(tukhoa) >= 0
                            || String.valueOf(pn.getDongia()).indexOf(tukhoa) >= 0
                            || String.valueOf(pn.getMaNV().toLowerCase()).indexOf(tukhoa) >= 0
                            || String.valueOf(pn.getMaNCC().toLowerCase()).indexOf(tukhoa) >= 0) {
                        kq.add(pn);
                    }
                }
                if (kq.size() == 0) {
                    JOptionPane.showMessageDialog(null, "Không tìm thấy dữ liệu", "Lỗi", JOptionPane.ERROR_MESSAGE);
                } else {
                    for (PHIEUNHAP pn : kq) {
                        ShowOnTablePN(pn);
                    }
                    table.setModel(model);
                }
            }
        }
        if (e.getSource() == btTimKiemNV) {
            String tukhoa = txKhoaTK.getText().replaceAll("\\s+", "").toLowerCase().trim();
            if (tukhoa.equals("") == true) {
                model.setRowCount(0);
                for (NHANVIEN nv : QLNHANVIENBUS.dsnhanvien) {
                    ShowOnTableNV(nv);
                }
            } else {
                model.setRowCount(0);
                ArrayList<NHANVIEN> kq = new ArrayList<NHANVIEN>();
                for (NHANVIEN nv : QLNHANVIENBUS.dsnhanvien) {
                    if (String.valueOf(nv.getMaNV().toLowerCase()).indexOf(tukhoa) >= 0
                            || String.valueOf(nv.getTenNV().replaceAll("\\s+", "").toLowerCase()).indexOf(tukhoa) >= 0
                            || String.valueOf(nv.getLuongCB()).indexOf(tukhoa) >= 0
                            || String.valueOf(nv.getHesoluong()).indexOf(tukhoa) >= 0
                            || String.valueOf(nv.getPhucap()).indexOf(tukhoa) >= 0
                            || String.valueOf(nv.getChucvu().replaceAll("\\s+", "").toLowerCase()).indexOf(tukhoa) >= 0
                            || String.valueOf(nv.getSDT()).indexOf(tukhoa) >= 0
                            || String.valueOf(nv.getMail().toLowerCase()).indexOf(tukhoa) >= 0) {
                        kq.add(nv);
                    }
                }
                if (kq.size() == 0) {
                    JOptionPane.showMessageDialog(null, "Không tìm thấy dữ liệu", "Lỗi", JOptionPane.ERROR_MESSAGE);
                } else {
                    for (NHANVIEN nv : kq) {
                        ShowOnTableNV(nv);
                    }
                    table.setModel(model);
                }
            }
        }
        if (e.getSource() == btTimKiemNCC) {
            String tukhoa = txKhoaTK.getText().replaceAll("\\s+", "").toLowerCase().trim();
            if (tukhoa.equals("") == true) {
                model.setRowCount(0);
                for (NHACUNGCAP ncc : QLNCCBUS.dsncc) {
                    ShowOnTableNCC(ncc);
                }
            } else {
                model.setRowCount(0);
                ArrayList<NHACUNGCAP> kq = new ArrayList<NHACUNGCAP>();
                for (NHACUNGCAP ncc : QLNCCBUS.dsncc) {
                    if (String.valueOf(ncc.getMaNCC().toLowerCase()).indexOf(tukhoa) >= 0
                            || String.valueOf(ncc.getTenNCC().replaceAll("\\s+", "").toLowerCase()).indexOf(tukhoa) >= 0) {
                        kq.add(ncc);
                    }
                }
                if (kq.size() == 0) {
                    JOptionPane.showMessageDialog(null, "Không tìm thấy dữ liệu", "Lỗi", JOptionPane.ERROR_MESSAGE);
                } else {
                    for (NHACUNGCAP ncc : kq) {
                        ShowOnTableNCC(ncc);
                    }
                    table.setModel(model);
                }
            }
        }
        if (e.getSource() == btTimKiemSach) {
            String tukhoa = txKhoaTK.getText().replaceAll("\\s+", "").toLowerCase().trim();
            if (tukhoa.equals("") == true) {
                model.setRowCount(0);
                for (SACH sach : QLSACHBUS.dssach) {
                    ShowOnTableSach(sach);
                }
            } else {
                model.setRowCount(0);
                ArrayList<SACH> kq = new ArrayList<SACH>();
                for (SACH sach : QLSACHBUS.dssach) {
                    if (String.valueOf(sach.getMasach().toLowerCase()).indexOf(tukhoa) >= 0 
                            || String.valueOf(sach.getTensach().replaceAll("\\s+", "").toLowerCase()).indexOf(tukhoa) >= 0 
                            || String.valueOf(sach.getMaNXB().toLowerCase()).indexOf(tukhoa) >= 0 
                            || String.valueOf(sach.getMaTG().toLowerCase()).indexOf(tukhoa) >= 0 
                            || String.valueOf(sach.getNamXB()).indexOf(tukhoa) >= 0
                            || String.valueOf(sach.getSLtong()).indexOf(tukhoa) >= 0 
                            || String.valueOf(sach.getSL()).indexOf(tukhoa) >= 0
                            || String.valueOf(sach.getDongia()).indexOf(tukhoa) >= 0) {
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
                QLMUONBUS qlbus = new QLMUONBUS();
                try {
                    ThanhTien = qlbus.TinhTienThue(i);
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            }
            QLMTGUI.txThanhTien.setText(String.format("%,d", ThanhTien));
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
                if (QLMTGUI.txCTPMMaSach != null || QLMTGUI.txCTHDMaSach != null || QLMTGUI.txCTPTMaSach != null) {
                    QLMTGUI.txCTPMMaSach.setText(String.valueOf(model.getValueAt(i, 0)));
                    QLMTGUI.txCTPTMaSach.setText(String.valueOf(model.getValueAt(i, 0)));
                    QLMTGUI.txCTHDMaSach.setText(String.valueOf(model.getValueAt(i, 0)));
                }
                if(QLPNGUI.txCTPNMaSach != null){
                    QLPNGUI.txCTPNMaSach.setText(String.valueOf(model.getValueAt(i, 0)));
                }
                if(QLMTGUI.txCTHDDonGia != null){
                    QLMTGUI.txCTHDDonGia.setText(String.valueOf(model.getValueAt(i, 7)));
                }
                QLPNGUI.DonGiaCTPN = Integer.parseInt(myTable.RemoveCommaInString(String.valueOf(model.getValueAt(i, 7))));
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
        if (e.getSource() == btLuachonMaDG_NV) {
            int i = table.getSelectedRow();
            if (i >= 0) {
                QLNVGUI.txMaDGTDMT.setText(String.valueOf(model.getValueAt(i, 0)));
            }
            this.dispose();
        }
        if (e.getSource() == btLuachonMaDG_HD) {
            int i = table.getSelectedRow();
            if (i >= 0) {
                QLMTGUI.txMaHD_DG.setText(String.valueOf(model.getValueAt(i, 0)));
            }
            this.dispose();
        }
        if (e.getSource() == btLuaChonMaPN) {
            int i = table.getSelectedRow();
            if (i >= 0) {
                QLPNGUI.txCTPNMaPN.setText(String.valueOf(model.getValueAt(i, 0)));
            }
            this.dispose();
        }
        if (e.getSource() == btLuaChonMaNV) {
            int i = table.getSelectedRow();
            if (i >= 0) {
                QLPNGUI.txMaNV.setText(String.valueOf(model.getValueAt(i, 0)));
            }
            this.dispose();
        }
        if (e.getSource() == btLuaChonMaNCC) {
            int i = table.getSelectedRow();
            if (i >= 0) {
                QLPNGUI.txMaNCC.setText(String.valueOf(model.getValueAt(i, 0)));
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

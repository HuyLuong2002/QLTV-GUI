package QLTV.GUI;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import MyCustom.LoginPage;
import MyCustom.MyColor;
import MyCustom.MyTable;
import MyCustom.RoundedBorder;
import QLTV.BUS.QLNVBUS;
import QLTV.BUS.QLPHIEUTHEODOIBUS;
import QLTV.DTO.DOCGIA;
import QLTV.DTO.PHIEUTHEODOIMT;

public class QLNVGUI extends JFrame implements MouseListener, ActionListener {
    JPanel pnQLNV, pnTabDG, pnTabTheoDoiMT, pnDG, pnTDMT, pnShowAll, pnNhapDG, pnNhapTDMT, pnTimKiemDG, pnTimKiemTDMT;
    JButton btMenu, btSach, btMT, btQLNV, btDangXuat, btNhapSach, btMenuTimKiem, btThongKe;
    JButton btThoat, btSuaDG, btThemDG, btXoaDG, btHoanTac, btSapXep, btTimKiemDG;
    JLabel lbMaDG, lbTenDG, lbDiaChi, lbMail, lbTinhTrangThue, lbLCTKDG, lbTuKhoaTKDG;
    JLabel lbMaDGTDMT, lbTongMuon, lbTienCoc, lbLCTKTDMT, lbTuKhoaTKTDMT;
    JTextField txMaDG, txTenDG, txDiaChi, txMail, txKhoaTKDG, txKhoaTKTDMT;
    JTextField txMaDGTDMT, txTongMuon, txTiencoc;
    JButton btThemTDMT, btSuaTDMT, btXoaTDMT, btHoanTacTDMT, btTimKiemTDMT;
    JComboBox<String> cbTinhTrangThue, cbDSKhoaTKDG, cbDSKhoaTKTDMT;
    JTabbedPane tabbedPane;
    public JTable tblQLDG, tblQLTDMT;
    public DefaultTableModel modelDG, modelTDMT;
    TitledBorder titleDG, titleTDMT;
    Vector<String> header;
    JButton btShowAll;

    public QLNVGUI() {

    }

    public JPanel setQLNVGUI() {
        if (pnQLNV == null) {
            MyTable myTable = new MyTable();
            pnQLNV = new JPanel();
            pnQLNV.setBounds(240, 0, 1145, 800);
            pnQLNV.setLayout(null);

            // panel tab độc giả
            pnTabDG = new JPanel();
            pnTabDG.setLayout(new GridLayout(1, 1, 0, 0));

            // panel tab theo dõi mượn trả
            pnTabTheoDoiMT = new JPanel();
            pnTabTheoDoiMT.setLayout(new GridLayout(1, 1, 0, 0));

            pnDG = new JPanel();
            pnDG.setLayout(new GridLayout(1, 1));

            pnTDMT = new JPanel();
            pnTDMT.setLayout(new GridLayout(1, 1));
            pnTDMT.setVisible(false);

            pnShowAll = new JPanel();
            pnShowAll.setBounds(5, 358, 1135, 50);
            pnShowAll.setLayout(null);

            pnNhapDG = new JPanel();
            pnNhapDG.setLayout(null);
            pnNhapDG.setBounds(5, 410, 1137, 370);

            pnNhapTDMT = new JPanel();
            pnNhapTDMT.setLayout(null);
            pnNhapTDMT.setBounds(5, 410, 1137, 370);
            pnNhapTDMT.setVisible(false);

            pnTimKiemDG = new JPanel();
            pnTimKiemDG.setLayout(null);
            pnTimKiemDG.setBounds(570, 0, 567, 200);

            pnTimKiemTDMT = new JPanel();
            pnTimKiemTDMT.setLayout(null);
            pnTimKiemTDMT.setBounds(570, 0, 567, 200);

            tabbedPane = new JTabbedPane();
            tabbedPane.addTab("Độc giả", pnTabDG);
            tabbedPane.addTab("Phiếu theo dõi mượn trả", pnTabTheoDoiMT);
            tabbedPane.addMouseListener(this);
            tabbedPane.setBounds(5, 5, 1138, 350);

            pnQLNV.add(tabbedPane);
            pnQLNV.add(pnShowAll);
            pnQLNV.add(pnNhapDG);
            pnQLNV.add(pnNhapTDMT);

            pnNhapDG.add(pnTimKiemDG);
            pnNhapTDMT.add(pnTimKiemTDMT);

            pnTabDG.add(pnDG);
            pnTabTheoDoiMT.add(pnTDMT);

            // Độc giả
            setTitleDG();
            setTableDG();
            setShowAll();
            setInputDG();
            getDBDG();
            setTimKiemDG();

            // Theo dõi mượn trả
            setTitleTDMT();
            setTableTDMT();
            setInputTDMT();
            getDBTDMT();
            setTimKiemTDMT();
            myTable.setValueCellCenter(modelTDMT, tblQLTDMT);

        }

        return pnQLNV;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource() == tblQLDG) {
            int i = tblQLDG.getSelectedRow();
            if (i >= 0) {
                DOCGIA docgia = QLNVBUS.dsdg.get(i);
                txMaDG.setText(docgia.getMaDG().replaceAll("\\s+", "").trim());
                txTenDG.setText(docgia.getTenDG().replaceAll("\\s+", " ").trim());
                txDiaChi.setText(docgia.getDiachi().replaceAll("\\s+", " ").trim());
                txMail.setText(docgia.getMail().replaceAll("\\s+", " ").trim());
                cbTinhTrangThue.setSelectedItem(docgia.getTinhtrangthue());
            }
        }
        if (e.getSource() == tabbedPane) {
            int index = tabbedPane.getSelectedIndex();
            if (index == 0) {
                if (pnNhapDG.isVisible() == false) {
                    pnNhapDG.setVisible(true);
                }
                if (pnNhapTDMT.isVisible() == true) {
                    pnNhapTDMT.setVisible(false);
                }
                if (pnTabTheoDoiMT.isVisible() == true) {
                    pnTabTheoDoiMT.setVisible(false);
                }
                if (btSapXep.isVisible() == false) {
                    btSapXep.setVisible(true);
                }
            }
            if (index == 1) {
                if (pnNhapDG.isVisible() == true) {
                    pnNhapDG.setVisible(false);
                }
                if (pnTabDG.isVisible() == true) {
                    pnTabDG.setVisible(false);
                }
                if (pnNhapTDMT.isVisible() == false) {
                    pnNhapTDMT.setVisible(true);
                }
                if (pnTabTheoDoiMT.isVisible() == false) {
                    pnTabTheoDoiMT.setVisible(true);
                }
                if (pnTDMT.isVisible() == false) {
                    pnTDMT.setVisible(true);
                }
                if (btSapXep.isVisible() == true) {
                    btSapXep.setVisible(false);
                }
            }
        }
        if (e.getSource() == tblQLTDMT) {
            int i = tblQLTDMT.getSelectedRow();
            if (i >= 0) {
                PHIEUTHEODOIMT ptd = QLPHIEUTHEODOIBUS.dsptd.get(i);
                txMaDGTDMT.setText(ptd.getMaDG().replaceAll("\\s+", " ").trim());
                txTongMuon.setText(String.valueOf(ptd.getTongmuon()));
                txTiencoc.setText(String.format("%,d", ptd.getTiencoc()));
            }
        }
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

    public void setTableDG() {
        // ----set up table----
        tblQLDG = new JTable();
        JScrollPane pane = new JScrollPane(tblQLDG);
        pane.setAutoscrolls(true);
        tblQLDG.setRowHeight(20);
        tblQLDG.setFont(new Font(null, 0, 13));
        tblQLDG.setBackground(Color.LIGHT_GRAY);
        tblQLDG.addMouseListener(this);
        tblQLDG.setDefaultEditor(Object.class, null);
        tblQLDG.setSelectionBackground(Color.GREEN);
        pnDG.add(pane);
    }

    public void setTableTDMT() {
        // ----set up table----
        tblQLTDMT = new JTable();
        JScrollPane pane = new JScrollPane(tblQLTDMT);
        pane.setAutoscrolls(true);
        tblQLTDMT.setRowHeight(20);
        tblQLTDMT.setFont(new Font(null, 0, 13));
        tblQLTDMT.setBackground(Color.LIGHT_GRAY);
        tblQLTDMT.addMouseListener(this);
        tblQLTDMT.setDefaultEditor(Object.class, null);
        tblQLTDMT.setSelectionBackground(Color.GREEN);
        pnTDMT.add(pane);
    }

    public void getDBDG() {
        try {
            QLNVBUS qlnvbus = new QLNVBUS();
            if (QLNVBUS.dsdg == null)
                try {
                    qlnvbus.docDS();
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            // set up header(column)
            Vector<String> header = new Vector<String>();
            header.add("Mã độc giả");
            header.add("Tên độc giả");
            header.add("Địa chỉ");
            header.add("Email");
            header.add("Tình trạng thuê");
            modelDG = new DefaultTableModel(header, 0);
            for (DOCGIA dg : QLNVBUS.dsdg) {
                ShowOnTableDGDG(dg);
            }
            tblQLDG.setModel(modelDG);
        } catch (Exception e1) {
            System.out.println(e1);
        }
    }

    public void getDBTDMT() {
        try {
            QLPHIEUTHEODOIBUS qlbus = new QLPHIEUTHEODOIBUS();
            if (QLPHIEUTHEODOIBUS.dsptd == null)
                try {
                    qlbus.docDS();
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            // set up header(column)
            Vector<String> header = new Vector<String>();
            header.add("Mã độc giả");
            header.add("Tổng mượn");
            header.add("Tiền cọc");
            modelTDMT = new DefaultTableModel(header, 0);
            for (PHIEUTHEODOIMT ptd : QLPHIEUTHEODOIBUS.dsptd) {
                ShowOnTableTDMT(ptd);
            }
            tblQLTDMT.setModel(modelTDMT);
        } catch (Exception e1) {
            System.out.println(e1);
        }
    }

    public void ShowOnTableDGDG(DOCGIA dg) {
        Vector<String> row = new Vector<String>();
        row.add(dg.getMaDG().replaceAll("\\s+", " ").trim());
        row.add(dg.getTenDG().replaceAll("\\s+", " ").trim());
        row.add(dg.getDiachi().replaceAll("\\s+", " ").trim());
        row.add(dg.getMail().replaceAll("\\s+", " ").trim());
        row.add(dg.getTinhtrangthue().replaceAll("\\s+", " ").trim());
        modelDG.addRow(row);
    }

    public void setTitleDG() {
        // set Border
        Border empty;
        empty = BorderFactory.createEmptyBorder();

        titleDG = BorderFactory.createTitledBorder(empty, "THÔNG TIN ĐỘC GIẢ");
        titleDG.setTitleFont(new Font("Arial", Font.BOLD, 28));
        titleDG.setTitleJustification(TitledBorder.CENTER);
        pnDG.setBorder(titleDG);
    }

    public void setTitleTDMT() {
        // set Border
        Border empty;
        empty = BorderFactory.createEmptyBorder();

        titleTDMT = BorderFactory.createTitledBorder(empty, "THÔNG TIN THEO DÕI MƯỢN TRẢ");
        titleTDMT.setTitleFont(new Font("Arial", Font.BOLD, 28));
        titleTDMT.setTitleJustification(TitledBorder.CENTER);
        pnTDMT.setBorder(titleTDMT);
    }

    public void setInputDG() {
        lbMaDG = new JLabel("Mã độc giả");
        lbMaDG.setFont(new Font("Arial", Font.BOLD, 20));
        lbMaDG.setBounds(20, 0, 130, 80);

        lbTenDG = new JLabel("Tên độc giả:");
        lbTenDG.setFont(new Font("Arial", Font.BOLD, 18));
        lbTenDG.setBounds(20, 40, 130, 80);

        lbDiaChi = new JLabel("Địa chỉ:");
        lbDiaChi.setFont(new Font("Arial", Font.BOLD, 18));
        lbDiaChi.setBounds(20, 80, 130, 80);

        lbMail = new JLabel("Email:");
        lbMail.setFont(new Font("Arial", Font.BOLD, 18));
        lbMail.setBounds(20, 120, 130, 80);

        lbTinhTrangThue = new JLabel("Tình trạng thuê:");
        lbTinhTrangThue.setFont(new Font("Arial", Font.BOLD, 18));
        lbTinhTrangThue.setBounds(20, 160, 150, 80);

        txMaDG = new JTextField();
        txMaDG.setBounds(180, 25, 180, 30);
        txMaDG.setFont(new Font("Arial", Font.PLAIN, 15));
        txMaDG.addMouseListener(this);

        txTenDG = new JTextField();
        txTenDG.setBounds(180, 65, 180, 30);
        txTenDG.setFont(new Font("Arial", Font.PLAIN, 15));
        txTenDG.addMouseListener(this);

        txDiaChi = new JTextField();
        txDiaChi.setBounds(180, 105, 180, 30);
        txDiaChi.setFont(new Font("Arial", Font.PLAIN, 15));
        txDiaChi.addMouseListener(this);

        txMail = new JTextField();
        txMail.setBounds(180, 145, 180, 30);
        txMail.setFont(new Font("Arial", Font.PLAIN, 15));
        txMail.addMouseListener(this);

        String[] dsTinhTrangThue = { "", "Đang thuê", "Hết thuê" };
        cbTinhTrangThue = new JComboBox<>(dsTinhTrangThue);
        cbTinhTrangThue.setFont(new Font("Arial", Font.BOLD, 13));
        cbTinhTrangThue.setBounds(180, 185, 180, 30);
        cbTinhTrangThue.addActionListener(this);

        // JbuttonThem
        btThemDG = new JButton("Thêm");
        btThemDG.setFont(new Font("Arial", Font.BOLD, 15));
        btThemDG.setBounds(20, 230, 80, 30);
        btThemDG.setBackground(Color.cyan);
        btThemDG.setBorder(new RoundedBorder(10));
        btThemDG.addActionListener(this);
        // JbuttonSua
        btSuaDG = new JButton("Sửa");
        btSuaDG.setFont(new Font("Arial", Font.BOLD, 15));
        btSuaDG.setBounds(120, 230, 80, 30);
        btSuaDG.setBackground(Color.cyan);
        btSuaDG.setBorder(new RoundedBorder(10));
        btSuaDG.addActionListener(this);
        // JbuttonXoa
        btXoaDG = new JButton("Xóa");
        btXoaDG.setFont(new Font("Arial", Font.BOLD, 15));
        btXoaDG.setBounds(220, 230, 80, 30);
        btXoaDG.setBackground(Color.cyan);
        btXoaDG.setBorder(new RoundedBorder(10));
        btXoaDG.addActionListener(this);
        // JbuttonHoanTac
        btHoanTac = new JButton("Hoàn tác");
        btHoanTac.setFont(new Font("Arial", Font.BOLD, 15));
        btHoanTac.setBounds(320, 230, 100, 30);
        btHoanTac.setBackground(Color.cyan);
        btHoanTac.setBorder(new RoundedBorder(10));
        btHoanTac.addActionListener(this);

        pnNhapDG.add(lbMaDG);
        pnNhapDG.add(lbTenDG);
        pnNhapDG.add(lbDiaChi);
        pnNhapDG.add(lbMail);
        pnNhapDG.add(lbTinhTrangThue);
        pnNhapDG.add(txMaDG);
        pnNhapDG.add(txTenDG);
        pnNhapDG.add(txDiaChi);
        pnNhapDG.add(txMail);
        pnNhapDG.add(cbTinhTrangThue);

        pnNhapDG.add(btThemDG);
        pnNhapDG.add(btSuaDG);
        pnNhapDG.add(btXoaDG);
        pnNhapDG.add(btHoanTac);
    }

    public void setInputTDMT() {
        lbMaDGTDMT = new JLabel("Mã độc giả");
        lbMaDGTDMT.setFont(new Font("Arial", Font.BOLD, 20));
        lbMaDGTDMT.setBounds(20, 0, 130, 80);

        lbTongMuon = new JLabel("Tổng mượn:");
        lbTongMuon.setFont(new Font("Arial", Font.BOLD, 18));
        lbTongMuon.setBounds(20, 40, 130, 80);

        lbTienCoc = new JLabel("Tiền cọc:");
        lbTienCoc.setFont(new Font("Arial", Font.BOLD, 18));
        lbTienCoc.setBounds(20, 80, 130, 80);

        txMaDGTDMT = new JTextField();
        txMaDGTDMT.setBounds(180, 25, 180, 30);
        txMaDGTDMT.setFont(new Font("Arial", Font.PLAIN, 15));
        txMaDGTDMT.addMouseListener(this);

        txTongMuon = new JTextField();
        txTongMuon.setBounds(180, 65, 180, 30);
        txTongMuon.setFont(new Font("Arial", Font.PLAIN, 15));
        txTongMuon.addMouseListener(this);

        txTiencoc = new JTextField();
        txTiencoc.setBounds(180, 105, 180, 30);
        txTiencoc.setFont(new Font("Arial", Font.PLAIN, 15));
        txTiencoc.addMouseListener(this);

        // JbuttonThem
        btThemTDMT = new JButton("Thêm");
        btThemTDMT.setFont(new Font("Arial", Font.BOLD, 15));
        btThemTDMT.setBounds(20, 160, 80, 30);
        btThemTDMT.setBackground(Color.cyan);
        btThemTDMT.setBorder(new RoundedBorder(10));
        btThemTDMT.addActionListener(this);
        // JbuttonSua
        btSuaTDMT = new JButton("Sửa");
        btSuaTDMT.setFont(new Font("Arial", Font.BOLD, 15));
        btSuaTDMT.setBounds(120, 160, 80, 30);
        btSuaTDMT.setBackground(Color.cyan);
        btSuaTDMT.setBorder(new RoundedBorder(10));
        btSuaTDMT.addActionListener(this);
        // JbuttonXoa
        btXoaTDMT = new JButton("Xóa");
        btXoaTDMT.setFont(new Font("Arial", Font.BOLD, 15));
        btXoaTDMT.setBounds(220, 160, 80, 30);
        btXoaTDMT.setBackground(Color.cyan);
        btXoaTDMT.setBorder(new RoundedBorder(10));
        btXoaTDMT.addActionListener(this);
        // JbuttonHoanTac
        btHoanTacTDMT = new JButton("Hoàn tác");
        btHoanTacTDMT.setFont(new Font("Arial", Font.BOLD, 15));
        btHoanTacTDMT.setBounds(320, 160, 100, 30);
        btHoanTacTDMT.setBackground(Color.cyan);
        btHoanTacTDMT.setBorder(new RoundedBorder(10));
        btHoanTacTDMT.addActionListener(this);

        pnNhapTDMT.add(lbMaDGTDMT);
        pnNhapTDMT.add(lbTongMuon);
        pnNhapTDMT.add(lbTienCoc);

        pnNhapTDMT.add(txMaDGTDMT);
        pnNhapTDMT.add(txTongMuon);
        pnNhapTDMT.add(txTiencoc);

        pnNhapTDMT.add(btThemTDMT);
        pnNhapTDMT.add(btSuaTDMT);
        pnNhapTDMT.add(btXoaTDMT);
        pnNhapTDMT.add(btHoanTacTDMT);
    }

    public void setShowAll() {
        btShowAll = new JButton("Hiển thị tất cả");
        btShowAll.setFont(new Font("Arial", Font.BOLD, 15));
        btShowAll.setBounds(1000, 5, 130, 30);
        btShowAll.setBackground(Color.cyan);
        btShowAll.setBorder(new RoundedBorder(10));
        btShowAll.addActionListener(this);

        btSapXep = new JButton("Sắp xếp theo tên");
        btSapXep.setFont(new Font("Arial", Font.BOLD, 15));
        btSapXep.setBounds(820, 10, 150, 30);
        btSapXep.setBackground(Color.cyan);
        btSapXep.setBorder(new RoundedBorder(10));
        btSapXep.addActionListener(this);

        pnShowAll.add(btShowAll);
        pnShowAll.add(btSapXep);
    }

    public void setTimKiemDG() {
        if (btTimKiemDG == null) { // Là button của phần tìm kiếm cơ bản
            if (lbLCTKDG != null || lbTuKhoaTKDG != null || cbDSKhoaTKDG != null || txKhoaTKDG != null) {
                lbLCTKDG.setVisible(true);
                lbTuKhoaTKDG.setVisible(true);
                cbDSKhoaTKDG.setVisible(true);
                txKhoaTKDG.setVisible(true);
            }

            // labelLCTK
            lbLCTKDG = new JLabel("Lựa chọn khóa tìm kiếm:");
            lbLCTKDG.setFont(new Font("Arial", Font.BOLD, 18));
            lbLCTKDG.setBounds(5, 25, 230, 100);

            // labelTuKhoaTK
            lbTuKhoaTKDG = new JLabel("Nhập từ khóa tìm kiếm:");
            lbTuKhoaTKDG.setFont(new Font("Arial", Font.BOLD, 18));
            lbTuKhoaTKDG.setBounds(5, 75, 230, 100);

            // JTextField Khóa tìm kiếm
            txKhoaTKDG = new JTextField();
            txKhoaTKDG.setFont(new Font("Arial", Font.PLAIN, 15));
            txKhoaTKDG.setBounds(250, 110, 150, 30);

            String[] dsKhoaTK = { "", "Mã độc giả", "Tên độc giả", "Địa chỉ", "Email", "Tình trạng thuê" };
            cbDSKhoaTKDG = new JComboBox<>(dsKhoaTK);
            cbDSKhoaTKDG.setFont(new Font("Arial", Font.BOLD, 13));
            cbDSKhoaTKDG.setBounds(250, 60, 120, 30);
            cbDSKhoaTKDG.addActionListener(this);

            TitledBorder titleTK;
            Border blackline;
            blackline = BorderFactory.createLineBorder(Color.black);
            titleTK = BorderFactory.createTitledBorder(blackline, "Tìm kiếm");
            titleTK.setTitleFont(new Font("Arial", Font.BOLD, 25));
            titleTK.setTitleJustification(TitledBorder.CENTER);
            pnTimKiemDG.setBorder(titleTK);

            btTimKiemDG = new JButton("Tìm kiếm");
            btTimKiemDG.setFont(new Font("Arial", Font.BOLD, 15));
            btTimKiemDG.setBounds(300, 155, 100, 30);
            btTimKiemDG.setBackground(MyColor.ColorButton);
            btTimKiemDG.setBorder(new RoundedBorder(10));
            btTimKiemDG.addActionListener(this);

        }
        pnTimKiemDG.add(lbLCTKDG);
        pnTimKiemDG.add(lbTuKhoaTKDG);
        pnTimKiemDG.add(txKhoaTKDG);
        pnTimKiemDG.add(cbDSKhoaTKDG);
        pnTimKiemDG.add(btTimKiemDG);
    }

    public void setTimKiemTDMT() {
        if (btTimKiemTDMT == null) { // Là button của phần tìm kiếm cơ bản
            if (lbLCTKTDMT != null || lbTuKhoaTKTDMT != null || cbDSKhoaTKTDMT != null || txKhoaTKTDMT != null) {
                lbLCTKTDMT.setVisible(true);
                lbTuKhoaTKTDMT.setVisible(true);
                cbDSKhoaTKTDMT.setVisible(true);
                txKhoaTKTDMT.setVisible(true);
            }

            // labelLCTK
            lbLCTKTDMT = new JLabel("Lựa chọn khóa tìm kiếm:");
            lbLCTKTDMT.setFont(new Font("Arial", Font.BOLD, 18));
            lbLCTKTDMT.setBounds(5, 25, 230, 100);

            // labelTuKhoaTK
            lbTuKhoaTKTDMT = new JLabel("Nhập từ khóa tìm kiếm:");
            lbTuKhoaTKTDMT.setFont(new Font("Arial", Font.BOLD, 18));
            lbTuKhoaTKTDMT.setBounds(5, 75, 230, 100);

            // JTextField Khóa tìm kiếm
            txKhoaTKTDMT = new JTextField();
            txKhoaTKTDMT.setFont(new Font("Arial", Font.PLAIN, 15));
            txKhoaTKTDMT.setBounds(250, 110, 150, 30);

            String[] dsKhoaTK = { "", "Mã độc giả", "Tổng mượn", "Tiền cọc" };
            cbDSKhoaTKTDMT = new JComboBox<>(dsKhoaTK);
            cbDSKhoaTKTDMT.setFont(new Font("Arial", Font.BOLD, 13));
            cbDSKhoaTKTDMT.setBounds(250, 60, 120, 30);
            cbDSKhoaTKTDMT.addActionListener(this);

            TitledBorder titleTK;
            Border blackline;
            blackline = BorderFactory.createLineBorder(Color.black);
            titleTK = BorderFactory.createTitledBorder(blackline, "Tìm kiếm");
            titleTK.setTitleFont(new Font("Arial", Font.BOLD, 25));
            titleTK.setTitleJustification(TitledBorder.CENTER);
            pnTimKiemTDMT.setBorder(titleTK);

            btTimKiemTDMT = new JButton("Tìm kiếm");
            btTimKiemTDMT.setFont(new Font("Arial", Font.BOLD, 15));
            btTimKiemTDMT.setBounds(300, 155, 100, 30);
            btTimKiemTDMT.setBackground(MyColor.ColorButton);
            btTimKiemTDMT.setBorder(new RoundedBorder(10));
            btTimKiemTDMT.addActionListener(this);

        }
        pnTimKiemTDMT.add(lbLCTKTDMT);
        pnTimKiemTDMT.add(lbTuKhoaTKTDMT);
        pnTimKiemTDMT.add(txKhoaTKTDMT);
        pnTimKiemTDMT.add(cbDSKhoaTKTDMT);
        pnTimKiemTDMT.add(btTimKiemTDMT);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btThemDG) {
            DOCGIA docgia = new DOCGIA();
            getInfoTextFieldDG(docgia);
            // Truy cập vào bus
            QLNVBUS qlnvbus = new QLNVBUS();
            int kiemtra = 0;
            try {
                kiemtra = qlnvbus.them(docgia);
            } catch (Exception e1) {
                e1.printStackTrace();
            }
            if (kiemtra == 0) {
                // Đưa dữ liệu lên table
                header = new Vector<String>();
                header.add("Mã độc giả");
                header.add("Tên độc giả");
                header.add("Địa chỉ");
                header.add("Email");
                header.add("Tình trạng thuê");
                if (modelDG.getRowCount() == 0) {
                    modelDG = new DefaultTableModel(header, 0);
                }
                ShowOnTableDG(docgia);
                tblQLDG.setModel(modelDG);
            }
        } else if (e.getSource() == btSuaDG) {
            int i = tblQLDG.getSelectedRow();
            int kt = -1;
            if (i >= 0) {
                DOCGIA docgia = new DOCGIA();
                DOCGIA madocgiacu = QLNVBUS.dsdg.set(i, docgia);
                getInfoTextFieldDG(docgia);
                try {
                    QLNVBUS qlnvbus = new QLNVBUS();
                    kt = qlnvbus.sua(docgia, madocgiacu, i);
                } catch (Exception e1) {
                    System.out.println(e1);
                }
                if (kt == 0) {
                    modelDG.setValueAt(docgia.getMaDG(), i, 0);
                    modelDG.setValueAt(docgia.getTenDG(), i, 1);
                    modelDG.setValueAt(docgia.getDiachi(), i, 2);
                    modelDG.setValueAt(docgia.getMail(), i, 3);
                    modelDG.setValueAt(docgia.getTinhtrangthue(), i, 4);
                    tblQLDG.setModel(modelDG);
                }
            }
        } else if (e.getSource() == btXoaDG) {
            int XacNhanXoa = JOptionPane.showConfirmDialog(null, "Bạn có chắc chắn muốn xóa không ?", "Thông báo",
                    JOptionPane.YES_NO_OPTION);
            if (XacNhanXoa == 0) {
                int kt = -1;
                String madocgia = txMaDG.getText().trim();
                int i = tblQLDG.getSelectedRow();
                if (i >= 0) {
                    try {
                        // Truy cập xuống BUS
                        DOCGIA docgiaold = QLNVBUS.dsdg.get(i);
                        QLNVBUS.htXoa.add(docgiaold);
                        QLNVBUS qlnvbus = new QLNVBUS();
                        kt = qlnvbus.xoa(madocgia, i);
                    } catch (Exception e1) {
                        System.out.println(e1);
                    }
                    if (kt == 0) {
                        modelDG.removeRow(i);
                        tblQLDG.setModel(modelDG);
                    }
                }
            }
        } else if (e.getSource() == btHoanTac) {
            int ktHT = 0;
            if (QLNVBUS.htXoa.size() == 0) {
                JOptionPane.showMessageDialog(null, "Dữ liệu hoàn tác rỗng", "Lỗi",
                        JOptionPane.ERROR_MESSAGE);
            } else {
                for (DOCGIA docgia : QLNVBUS.htXoa) {
                    QLNVBUS qlnvbus = new QLNVBUS();
                    int kiemtra = 0;
                    try {
                        kiemtra = qlnvbus.hoantacXoa(docgia);
                    } catch (Exception e1) {
                        e1.printStackTrace();
                    }
                    if (kiemtra == 0) {
                        // Đưa dữ liệu lên table
                        header = new Vector<String>();
                        header.add("Mã độc giả");
                        header.add("Tên độc giả");
                        header.add("Địa chỉ");
                        header.add("Email");
                        header.add("Tình trạng thuê");
                        if (modelDG.getRowCount() == 0) {
                            modelDG = new DefaultTableModel(header, 0);
                        }
                        ShowOnTableDG(docgia);
                        ktHT = 1;
                    } else if (kiemtra == -1) {
                        ktHT = 0;
                    }
                }
                if (ktHT == 1) {
                    JOptionPane.showMessageDialog(null, "Hoàn tác dữ liệu thành công", "Thông báo",
                            JOptionPane.INFORMATION_MESSAGE);
                    tblQLDG.setModel(modelDG);
                } else {
                    JOptionPane.showMessageDialog(null, "Hoàn tác dữ liệu thất bại", "Lỗi",
                            JOptionPane.ERROR_MESSAGE);
                }
            }

        } else if (e.getSource() == btShowAll) {
            int i = tabbedPane.getSelectedIndex();
            if (i == 0) {
                modelDG.setRowCount(0);
                for (DOCGIA docgia : QLNVBUS.dsdg) {
                    ShowOnTableDG(docgia);
                }
                tblQLDG.setModel(modelDG);
            }
            if (i == 1) {
                modelTDMT.setRowCount(0);
                for (PHIEUTHEODOIMT ptd : QLPHIEUTHEODOIBUS.dsptd) {
                    ShowOnTableTDMT(ptd);
                }
                tblQLTDMT.setModel(modelTDMT);
            }
        }
        if (e.getSource() == btThemTDMT) {
            PHIEUTHEODOIMT ptd = new PHIEUTHEODOIMT();
            getInfoTextFieldTDMT(ptd);

            QLPHIEUTHEODOIBUS qlbus = new QLPHIEUTHEODOIBUS();
            int kiemtra = 0;
            try {
                kiemtra = qlbus.them(ptd);
            } catch (Exception e1) {
                System.out.println(e);
            }
            if (kiemtra == 0) {
                // Đưa dữ liệu lên table
                header = new Vector<String>();
                header.add("Mã độc giả");
                header.add("Tổng mượn");
                header.add("Tiền cọc");
                if (modelTDMT.getRowCount() == 0) {
                    modelTDMT = new DefaultTableModel(header, 0);
                }
                ShowOnTableTDMT(ptd);
                tblQLTDMT.setModel(modelTDMT);
            }
        }
        if(e.getSource()==btSuaTDMT){
            int i = tblQLDG.getSelectedRow();
            int kt = -1;
            if (i >= 0) {
                PHIEUTHEODOIMT ptd = new PHIEUTHEODOIMT();
                PHIEUTHEODOIMT madocgiacuPTD = QLPHIEUTHEODOIBUS.dsptd.set(i, ptd);
                getInfoTextFieldTDMT(ptd);
                try {
                    QLPHIEUTHEODOIBUS qlbus = new QLPHIEUTHEODOIBUS();
                    kt = qlbus.sua(ptd, madocgiacuPTD, i);
                } catch (Exception e1) {
                    System.out.println(e1);
                }
                if (kt == 0) {
                    modelTDMT.setValueAt(ptd.getMaDG(), i, 0);
                    modelTDMT.setValueAt(ptd.getTongmuon(), i, 1);
                    modelTDMT.setValueAt(ptd.getTiencoc(), i, 2);
                    tblQLTDMT.setModel(modelTDMT);
                }
            }
        }
        if(e.getSource()==btXoaTDMT){
            int XacNhanXoa = JOptionPane.showConfirmDialog(null, "Bạn có chắc chắn muốn xóa không ?", "Thông báo",
                    JOptionPane.YES_NO_OPTION);
            if (XacNhanXoa == 0) {
                int kt = -1;
                String madocgia = txMaDGTDMT.getText().trim();
                int i = tblQLTDMT.getSelectedRow();
                if (i >= 0) {
                    try {
                        // Truy cập xuống BUS
                        PHIEUTHEODOIMT docgiaold = QLPHIEUTHEODOIBUS.dsptd.get(i);
                        QLPHIEUTHEODOIBUS.htXoa.add(docgiaold);
                        QLPHIEUTHEODOIBUS qlbus = new QLPHIEUTHEODOIBUS();
                        kt = qlbus.xoa(madocgia, i);
                    } catch (Exception e1) {
                        System.out.println(e1);
                    }
                    if (kt == 0) {
                        modelTDMT.removeRow(i);
                        tblQLTDMT.setModel(modelTDMT);
                    }
                }
            }
        }
        
        if (e.getSource() == btHoanTacTDMT){
            int ktHT = 0;
            if (QLPHIEUTHEODOIBUS.htXoa.size() == 0) {
                JOptionPane.showMessageDialog(null, "Dữ liệu hoàn tác rỗng", "Lỗi",
                        JOptionPane.ERROR_MESSAGE);
            } else {
                for (PHIEUTHEODOIMT ptd : QLPHIEUTHEODOIBUS.htXoa) {
                    QLPHIEUTHEODOIBUS qlbus = new QLPHIEUTHEODOIBUS();
                    int kiemtra = 0;
                    try {
                        kiemtra = qlbus.hoantacXoa(ptd);
                    } catch (Exception e1) {
                        e1.printStackTrace();
                    }
                    if (kiemtra == 0) {
                        // Đưa dữ liệu lên table
                        header = new Vector<String>();
                        header.add("Mã độc giả");
                        header.add("Tổng mượn");
                        header.add("Tiền cọc");
                        if (modelTDMT.getRowCount() == 0) {
                            modelTDMT = new DefaultTableModel(header, 0);
                        }
                        ShowOnTableTDMT(ptd);
                        ktHT = 1;
                    } else if (kiemtra == -1) {
                        ktHT = 0;
                    }
                }
                if (ktHT == 1) {
                    JOptionPane.showMessageDialog(null, "Hoàn tác dữ liệu thành công", "Thông báo",
                            JOptionPane.INFORMATION_MESSAGE);
                    tblQLTDMT.setModel(modelTDMT);
                } else {
                    JOptionPane.showMessageDialog(null, "Hoàn tác dữ liệu thất bại", "Lỗi",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        }

        if (e.getSource() == btTimKiemDG) {
            int vtkey = Integer.parseInt(String.valueOf(cbDSKhoaTKDG.getSelectedIndex()));
            String tukhoa = txKhoaTKDG.getText().replaceAll("\\s+", " ").trim();
            if (tukhoa.equals("") == true) {
                JOptionPane.showMessageDialog(null, "Xin mời nhập từ khóa", "Lỗi", JOptionPane.ERROR_MESSAGE);
            } else if (vtkey == 0) {
                JOptionPane.showMessageDialog(null, "Xin mời lựa chọn khóa tìm kiếm", "Lỗi", JOptionPane.ERROR_MESSAGE);
            } else {
                QLNVBUS qlnvbus = new QLNVBUS();
                if (vtkey == 1) {
                    DOCGIA docgia = qlnvbus.timTheoMa(tukhoa);
                    modelDG.setRowCount(0);
                    if (docgia != null) {
                        ShowOnTableDG(docgia);
                        tblQLDG.setModel(modelDG);

                    } else {
                        JOptionPane.showMessageDialog(null, "Không tìm thấy dữ liệu phù hợp", "Lỗi",
                                JOptionPane.ERROR_MESSAGE);
                    }
                }
                if (vtkey == 2) {
                    ArrayList<DOCGIA> kq = qlnvbus.timTheoTen(tukhoa);
                    modelDG.setRowCount(0);
                    if (kq.size() == 0) {
                        JOptionPane.showMessageDialog(null, "Không tìm thấy dữ liệu phù hợp", "Lỗi",
                                JOptionPane.ERROR_MESSAGE);
                    } else {
                        for (DOCGIA dg : kq) {
                            ShowOnTableDG(dg);
                        }
                        tblQLDG.setModel(modelDG);
                    }
                }
                if (vtkey == 3) {
                    ArrayList<DOCGIA> kq = qlnvbus.timTheoDiaChi(tukhoa);
                    modelDG.setRowCount(0);
                    if (kq.size() == 0) {
                        JOptionPane.showMessageDialog(null, "Không tìm thấy dữ liệu phù hợp", "Lỗi",
                                JOptionPane.ERROR_MESSAGE);
                    } else {
                        for (DOCGIA dg : kq) {
                            ShowOnTableDG(dg);
                        }
                        tblQLDG.setModel(modelDG);
                    }
                }
                if (vtkey == 4) {
                    ArrayList<DOCGIA> kq = qlnvbus.timTheoEmail(tukhoa);
                    modelDG.setRowCount(0);
                    if (kq.size() == 0) {
                        JOptionPane.showMessageDialog(null, "Không tìm thấy dữ liệu phù hợp", "Lỗi",
                                JOptionPane.ERROR_MESSAGE);
                    } else {
                        for (DOCGIA dg : kq) {
                            ShowOnTableDG(dg);
                        }
                        tblQLDG.setModel(modelDG);
                    }
                }
                if (vtkey == 5) {
                    ArrayList<DOCGIA> kq = qlnvbus.timTheoTinhTrangSach(tukhoa);
                    modelDG.setRowCount(0);
                    if (kq.size() == 0) {
                        JOptionPane.showMessageDialog(null, "Không tìm thấy dữ liệu phù hợp", "Lỗi",
                                JOptionPane.ERROR_MESSAGE);
                    } else {
                        for (DOCGIA dg : kq) {
                            ShowOnTableDG(dg);
                        }
                        tblQLDG.setModel(modelDG);
                    }
                }
            }
        }
        if (e.getSource()==btTimKiemTDMT){
            int vtkey = Integer.parseInt(String.valueOf(cbDSKhoaTKTDMT.getSelectedIndex()));
            String tukhoa = txKhoaTKTDMT.getText().replaceAll("\\s+", " ").trim();
            if (tukhoa.equals("") == true) {
                JOptionPane.showMessageDialog(null, "Xin mời nhập từ khóa", "Lỗi", JOptionPane.ERROR_MESSAGE);
            } else if (vtkey == 0) {
                JOptionPane.showMessageDialog(null, "Xin mời lựa chọn khóa tìm kiếm", "Lỗi", JOptionPane.ERROR_MESSAGE);
            } else {
                QLPHIEUTHEODOIBUS qlbus = new QLPHIEUTHEODOIBUS();
                if (vtkey == 1) {
                    PHIEUTHEODOIMT ptd = qlbus.timTheoMa(tukhoa);
                    modelTDMT.setRowCount(0);
                    if (ptd != null) {
                        ShowOnTableTDMT(ptd);
                        tblQLTDMT.setModel(modelTDMT);
                    } else {
                        JOptionPane.showMessageDialog(null, "Không tìm thấy dữ liệu phù hợp", "Lỗi",
                                JOptionPane.ERROR_MESSAGE);
                    }
                }
                if (vtkey == 2){
                    ArrayList <PHIEUTHEODOIMT> kq = qlbus.timTheoTongMuon(tukhoa);
                    modelTDMT.setRowCount(0);
                    if (kq.size() == 0) {
                        JOptionPane.showMessageDialog(null, "Không tìm thấy dữ liệu phù hợp", "Lỗi",
                        JOptionPane.ERROR_MESSAGE);
                    } else {
                        for (PHIEUTHEODOIMT ptd : kq) {
                            ShowOnTableTDMT(ptd);
                        }
                        tblQLTDMT.setModel(modelTDMT);
                    }
                }
                if (vtkey == 3){
                    ArrayList <PHIEUTHEODOIMT> kq = qlbus.timTheoTienCoc(tukhoa);
                    modelTDMT.setRowCount(0);
                    if (kq.size() == 0) {
                        JOptionPane.showMessageDialog(null, "Không tìm thấy dữ liệu phù hợp", "Lỗi",
                        JOptionPane.ERROR_MESSAGE);
                    } else {
                        for (PHIEUTHEODOIMT ptd : kq) {
                            ShowOnTableTDMT(ptd);
                        }
                        tblQLTDMT.setModel(modelTDMT);
                    }
                }
            }
        }

        if (e.getSource() == btMenu) {
            this.dispose();
            new Menu();
        }
        if (e.getSource() == btDangXuat) {
            int ktra = JOptionPane.showConfirmDialog(null, "Bạn có chắc chắn muốn đăng xuất", "Xác nhận",
                    JOptionPane.YES_NO_OPTION);
            if (ktra == 0) {
                this.dispose();
                try {
                    new LoginPage();
                } catch (InterruptedException e1) {
                    System.out.println(e1);
                }
            }
        }
        if (e.getSource() == btThoat) {
            int ktra = JOptionPane.showConfirmDialog(null, "Bạn có chắc chắn muốn đăng xuất", "Xác nhận",
                    JOptionPane.YES_NO_OPTION);
            if (ktra == 0) {
                System.exit(0);
            }
        }
        if (e.getSource() == btSapXep) {
            SapXep();
        }

    }

    public void SapXep() {
        QLNVBUS.dsdg.sort((o1, o2) -> o1.getMaDG().compareTo(o2.getTenDG()));
        modelDG.setRowCount(0);
        for (DOCGIA theloai : QLNVBUS.dsdg) {
            ShowOnTableDG(theloai);
        }
        tblQLDG.setModel(modelDG);
    }

    public void ShowOnTableDG(DOCGIA docgia) {
        Vector<String> row = new Vector<String>();
        row.add(docgia.getMaDG().replaceAll("\\s+", " ").trim());
        row.add(docgia.getTenDG().replaceAll("\\s+", " ").trim());
        row.add(docgia.getDiachi().replaceAll("\\s+", " ").trim());
        row.add(docgia.getMail().replaceAll("\\s+", " ").trim());
        row.add(docgia.getTinhtrangthue().replaceAll("\\s+", " ").trim());
        modelDG.addRow(row);
    }

    public void ShowOnTableTDMT(PHIEUTHEODOIMT ptd) {
        Vector<String> row = new Vector<String>();
        row.add(ptd.getMaDG().replaceAll("\\s+", " ").trim());
        row.add(String.valueOf(ptd.getTongmuon()));
        row.add(String.format("%,d", ptd.getTiencoc()));
        modelTDMT.addRow(row);
    }

    public void getInfoTextFieldDG(DOCGIA docgia) {
        docgia.setMaDG(txMaDG.getText().replaceAll("\\s+", " ").trim());
        docgia.setTenDG(txTenDG.getText().replaceAll("\\s+", " ").trim());
        docgia.setDiachi(txDiaChi.getText().replaceAll("\\s+", " ").trim());
        docgia.setMail(txMail.getText().replaceAll("\\s+", " ").trim());
        docgia.setTinhtrangthue(String.valueOf(cbTinhTrangThue.getSelectedItem()));
    }

    public void getInfoTextFieldTDMT(PHIEUTHEODOIMT ptd) {
        ptd.setMaDG(txMaDGTDMT.getText().replaceAll("\\s+", " ").trim());
        ptd.setTongmuon(Integer.parseInt(txTongMuon.getText().trim()));
        String Tiencoc = RemoveCommaInString(txTiencoc);
        ptd.setTiencoc(Integer.parseInt(Tiencoc));

    }

    public String RemoveCommaInString(JTextField Tien) {
        String tmp[] = Tien.getText().split(",");
        String Dongia = "";
        for (int i = 0; i < tmp.length; i++) {
            Dongia = Dongia + tmp[i];
        }
        return Dongia;
    }
}

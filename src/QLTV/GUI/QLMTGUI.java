package QLTV.GUI;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Properties;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import MyCustom.DateLabelFormatter;
import MyCustom.RoundedBorder;
import QLTV.BUS.QLCTMUONBUS;
import QLTV.BUS.QLMUONBUS;
import QLTV.DTO.CHITIETPHIEUMUON;
import QLTV.DTO.PHIEUMUON;

public class QLMTGUI extends JFrame implements ActionListener, MouseListener {
    JPanel pnMuonTra, pnTabMuon, pnShowAll, pnMuon, pnCTMuon, pnNhapPM, pnTimKiem,pnLoc;
    JLabel lbHome, lbMaPM, lbNgayMuon, lbSLtong, lbNgayTra, lbTinhTrangMuon, 
    lbMaDG, lbLCTK, lbTuKhoaTK,lbNgayBD,lbNgayKT;
    JButton btMenu, btSach, btMT, btQLNV, btDangXuat, btNhapSach, btThoat, btMenuTimKiem, btThongKe;
    JButton btShowAll, btTimKiem,btLoc;
    JTextField txMaPM, txSLtong, txMaDG, txKhoaTK;
    JComboBox<String> cbTinhTrangMuon, cbDSKhoaTK;

    TitledBorder titleMuon;
    JTabbedPane tabbedPane;

    JTable tblQLMuon, tblQLCTMuon;
    DefaultTableModel modelMuon, modelCTMuon;

    UtilDateModel modelNgayBD, modelNgayKT;
    Properties pNgayBD, pNgayKT;
    JDatePanelImpl datePanelNgayBD, datePanelNgayKT;
    JDatePickerImpl datePickerNgayBD, datePickerNgayKT;
    Color ColorPurple;

    public QLMTGUI() {
    }

    public JPanel setMTGUI() {
        ColorPurple = new Color(255,20,147);

        if (pnMuonTra == null) {
            pnMuonTra = new JPanel();
            pnMuonTra.setBounds(240, 0, 1145, 800);
            pnMuonTra.setLayout(null);
            pnMuonTra.setBackground(ColorPurple);

            pnTabMuon = new JPanel();
            pnTabMuon.setLayout(new GridLayout(1, 2, 5, 0));

            pnMuon = new JPanel();
            pnMuon.setLayout(new GridLayout(1, 1));

            pnCTMuon = new JPanel();
            pnCTMuon.setLayout(new GridLayout(1, 1));

            pnShowAll = new JPanel();
            pnShowAll.setBounds(5, 358, 1135, 50);
            pnShowAll.setLayout(null);

            pnNhapPM = new JPanel();
            pnNhapPM.setLayout(null);
            pnNhapPM.setBounds(5, 410, 1137, 370);

            pnTimKiem = new JPanel();
            pnTimKiem.setLayout(null);
            pnTimKiem.setBounds(570, 0, 567, 200);

            pnLoc = new JPanel();
            pnLoc.setLayout(null);
            pnLoc.setBounds(570, 195, 300, 160);

            tabbedPane = new JTabbedPane();
            tabbedPane.addTab("Mượn sách", pnTabMuon);
            tabbedPane.addTab("Trả sách", null);
            tabbedPane.addTab("HĐ Tiền phạt", null);
            tabbedPane.addMouseListener(this);
            tabbedPane.setBounds(5, 5, 1138, 350);

            pnMuonTra.add(tabbedPane);
            pnMuonTra.add(pnShowAll);
            pnMuonTra.add(pnNhapPM);
            // pnMuonTra.add(pnTimKiem);
            // pnMuonTra.add(pnLoc);
            pnNhapPM.add(pnTimKiem);
            pnNhapPM.add(pnLoc);
            pnTabMuon.add(pnMuon);
            pnTabMuon.add(pnCTMuon);

            setTitle();
            setTableMuon();
            setTableCTMuon();
            setShowAll();
            setInputMuon();
            setTimKiem();
            setLoc();
            getDBMuon();
            getDBCTPM();
            setValueCellCenter(modelMuon, tblQLMuon);
            setValueCellCenter(modelCTMuon, tblQLCTMuon);
        }
        return pnMuonTra;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource() == tblQLMuon) {
            int i = tblQLMuon.getSelectedRow();
            if (i >= 0) {
                modelCTMuon.setRowCount(0);
                ArrayList<CHITIETPHIEUMUON> kq = new ArrayList<CHITIETPHIEUMUON>();
                PHIEUMUON pm = QLMUONBUS.dspm.get(i);
                for (CHITIETPHIEUMUON ctpm : QLCTMUONBUS.dsctpm) {
                    if (ctpm.getMaPM().indexOf(pm.getMaPM()) >= 0) {
                        kq.add(ctpm); // Chứa phần tử của ctpm thỏa mã pm
                    }
                }
                for (CHITIETPHIEUMUON ctpm : kq) {
                    ShowOnTableCTPM(ctpm);
                }
                tblQLCTMuon.setModel(modelCTMuon);
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
        if (e.getSource() == txMaPM) {
            txMaPM.setToolTipText("Gợi ý: PM001");
        } else if (e.getSource() == txSLtong) {
            txSLtong.setToolTipText("Gợi ý: Tổng sách mượn");
        } else if (e.getSource() == txMaDG) {
            txMaDG.setToolTipText("Gợi ý: DG001");
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    public void getDBMuon() {
        try {
            QLMUONBUS qlmuonbus = new QLMUONBUS();
            if (QLMUONBUS.dspm == null)
                try {
                    qlmuonbus.docDSPM();
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            // set up header(column)
            Vector<String> header = new Vector<String>();
            header.add("Mã phiếu mượn");
            header.add("Ngày mượn");
            header.add("SL tổng");
            header.add("Ngày trả");
            header.add("Mã độc giả");
            modelMuon = new DefaultTableModel(header, 0);
            for (PHIEUMUON pm : QLMUONBUS.dspm) {
                ShowOnTablePM(pm);
            }
            tblQLMuon.setModel(modelMuon);
        } catch (Exception e1) {
            System.out.println(e1);
        }
    }

    public void getDBCTPM() {
        try {
            QLCTMUONBUS qlCTPMbus = new QLCTMUONBUS();
            if (QLCTMUONBUS.dsctpm == null)
                try {
                    qlCTPMbus.docDSCTPM();
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            // set up header(column)
            Vector<String> header = new Vector<String>();
            header.add("Mã phiếu mượn");
            header.add("Mã sách");
            header.add("SL");
            modelCTMuon = new DefaultTableModel(header, 0);
            tblQLCTMuon.setModel(modelCTMuon);
        } catch (Exception e1) {
            System.out.println(e1);
        }
    }

    public void ShowOnTablePM(PHIEUMUON pm) {
        Vector<String> row = new Vector<String>();
        row.add(pm.getMaPM());
        row.add(pm.getNgaymuon());
        row.add(String.valueOf(pm.getSLtong()));
        row.add(pm.getNgaytra());
        row.add(pm.getTinhTrangMuon());
        row.add(pm.getMaDG());
        modelMuon.addRow(row);

    }

    public void ShowOnTableCTPM(CHITIETPHIEUMUON ctpm) {
        Vector<String> row = new Vector<String>();
        row.add(ctpm.getMaPM());
        row.add(ctpm.getMasach());
        row.add(String.valueOf(ctpm.getSL()));
        modelCTMuon.addRow(row);
    }

    public void setTitle() {
        // set Border
        Border empty;
        empty = BorderFactory.createEmptyBorder();

        titleMuon = BorderFactory.createTitledBorder(empty, "THÔNG TIN MƯỢN");
        titleMuon.setTitleFont(new Font("Arial", Font.BOLD, 28));
        titleMuon.setTitleJustification(TitledBorder.CENTER);
        pnMuon.setBorder(titleMuon);

        titleMuon = BorderFactory.createTitledBorder(empty, "CHI TIẾT MƯỢN");
        titleMuon.setTitleFont(new Font("Arial", Font.BOLD, 28));
        titleMuon.setTitleJustification(TitledBorder.CENTER);
        pnCTMuon.setBorder(titleMuon);

    }

    public void setShowAll() {
        btShowAll = new JButton("Hiển thị tất cả");
        btShowAll.setFont(new Font("Arial", Font.BOLD, 15));
        btShowAll.setBounds(1000, 10, 130, 30);
        btShowAll.setBackground(Color.cyan);
        btShowAll.setBorder(new RoundedBorder(10));
        btShowAll.addActionListener(this);
        pnShowAll.add(btShowAll);
    }

    public void setTableMuon() {
        // ----set up table----
        tblQLMuon = new JTable();
        JScrollPane pane = new JScrollPane(tblQLMuon);
        pane.setAutoscrolls(true);
        tblQLMuon.setRowHeight(20);
        tblQLMuon.setFont(new Font(null, 0, 13));
        tblQLMuon.setBackground(Color.LIGHT_GRAY);
        tblQLMuon.addMouseListener(this);
        tblQLMuon.setDefaultEditor(Object.class, null);
        pnMuon.add(pane);
    }

    public void setTableCTMuon() {
        // ----set up table----
        tblQLCTMuon = new JTable();
        JScrollPane pane = new JScrollPane(tblQLCTMuon);
        pane.setAutoscrolls(true);
        tblQLCTMuon.setRowHeight(20);
        tblQLCTMuon.setFont(new Font(null, 0, 13));
        tblQLCTMuon.setBackground(Color.LIGHT_GRAY);
        tblQLCTMuon.addMouseListener(this);
        tblQLCTMuon.setDefaultEditor(Object.class, null);
        pnCTMuon.add(pane);
    }

    public void setInputMuon() {
        lbMaPM = new JLabel("Mã phiếu mượn:");
        lbMaPM.setFont(new Font("Arial", Font.BOLD, 20));
        lbMaPM.setBounds(0, 0, 200, 100);

        lbNgayMuon = new JLabel("Ngày mượn:");
        lbNgayMuon.setFont(new Font("Arial", Font.BOLD, 20));
        lbNgayMuon.setBounds(0, 50, 200, 100);

        lbSLtong = new JLabel("SL tổng:");
        lbSLtong.setFont(new Font("Arial", Font.BOLD, 20));
        lbSLtong.setBounds(0, 100, 200, 100);

        lbNgayTra = new JLabel("Ngày trả:");
        lbNgayTra.setFont(new Font("Arial", Font.BOLD, 20));
        lbNgayTra.setBounds(0, 150, 200, 100);

        lbTinhTrangMuon = new JLabel("Tình trạng mượn:");
        lbTinhTrangMuon.setFont(new Font("Arial", Font.BOLD, 20));
        lbTinhTrangMuon.setBounds(0, 200, 200, 100);

        lbMaDG = new JLabel("Mã độc giả:");
        lbMaDG.setFont(new Font("Arial", Font.BOLD, 20));
        lbMaDG.setBounds(0, 250, 200, 100);

        txMaPM = new JTextField();
        txMaPM.setBounds(200, 35, 150, 30);
        txMaPM.setFont(new Font("Arial", Font.PLAIN, 15));
        txMaPM.addMouseListener(this);

        txSLtong = new JTextField();
        txSLtong.setBounds(200, 135, 150, 30);
        txSLtong.setFont(new Font("Arial", Font.PLAIN, 15));
        txSLtong.addMouseListener(this);

        txMaDG = new JTextField();
        txMaDG.setBounds(200, 285, 150, 30);
        txMaDG.setFont(new Font("Arial", Font.PLAIN, 15));
        txMaDG.addMouseListener(this);

        // set up ComboBox
        String[] dsTinhTrangMuon = { "", "Đang thuê", "Hết thuê" };
        cbTinhTrangMuon = new JComboBox<>(dsTinhTrangMuon);
        cbTinhTrangMuon.setFont(new Font("Arial", Font.BOLD, 13));
        cbTinhTrangMuon.setBounds(200, 235, 120, 30);
        cbTinhTrangMuon.addActionListener(this);

        // Set date picker1
        modelNgayBD = new UtilDateModel();
        modelNgayBD.setSelected(true);
        pNgayBD = new Properties();
        pNgayBD.put("text.today", "Today");
        pNgayBD.put("text.month", "Month");
        pNgayBD.put("text.year", "Year");
        datePanelNgayBD = new JDatePanelImpl(modelNgayBD, pNgayBD);
        datePickerNgayBD = new JDatePickerImpl(datePanelNgayBD, new DateLabelFormatter());
        datePickerNgayBD.setBounds(200, 85, 150, 30);

        // Set date picker1
        modelNgayKT = new UtilDateModel();
        modelNgayKT.setSelected(true);
        pNgayKT = new Properties();
        pNgayKT.put("text.today", "Today");
        pNgayKT.put("text.month", "Month");
        pNgayKT.put("text.year", "Year");
        datePanelNgayKT = new JDatePanelImpl(modelNgayKT, pNgayKT);
        datePickerNgayKT = new JDatePickerImpl(datePanelNgayKT, new DateLabelFormatter());
        datePickerNgayKT.setBounds(200, 185, 150, 30);

        pnNhapPM.add(lbMaPM);
        pnNhapPM.add(lbNgayMuon);
        pnNhapPM.add(lbSLtong);
        pnNhapPM.add(lbNgayTra);
        pnNhapPM.add(lbTinhTrangMuon);
        pnNhapPM.add(lbMaDG);

        pnNhapPM.add(txMaPM);
        pnNhapPM.add(txSLtong);
        pnNhapPM.add(txMaDG);
        pnNhapPM.add(cbTinhTrangMuon);

        pnNhapPM.add(datePickerNgayBD);
        pnNhapPM.add(datePickerNgayKT);
    }

    public void setTimKiem() {
        if (btTimKiem == null) { // Là button của phần tìm kiếm cơ bản
            if (lbLCTK != null || lbTuKhoaTK != null || cbDSKhoaTK != null || txKhoaTK != null) {
                lbLCTK.setVisible(true);
                lbTuKhoaTK.setVisible(true);
                cbDSKhoaTK.setVisible(true);
                txKhoaTK.setVisible(true);
            }

            // labelLCTK
            lbLCTK = new JLabel("Lựa chọn khóa tìm kiếm:");
            lbLCTK.setFont(new Font("Arial", Font.BOLD, 20));
            lbLCTK.setBounds(10, 30, 250, 100);

            // labelTuKhoaTK
            lbTuKhoaTK = new JLabel("Nhập từ khóa tìm kiếm:");
            lbTuKhoaTK.setFont(new Font("Arial", Font.BOLD, 20));
            lbTuKhoaTK.setBounds(10, 80, 250, 100);

            // JTextField Khóa tìm kiếm
            txKhoaTK = new JTextField();
            txKhoaTK.setFont(new Font("Arial", Font.PLAIN, 15));
            txKhoaTK.setBounds(260, 115, 200, 35);
            txKhoaTK.addMouseListener(this);

            String[] dsKhoaTK = { "", "Mã sách", "Tên sách", "Mã NXB", "Mã TG", "Năm XB", "SL tổng", "SL", "Đơn giá",
                    "Năm hoặc SL", "Năm và SL" };
            cbDSKhoaTK = new JComboBox<>(dsKhoaTK);
            cbDSKhoaTK.setFont(new Font("Arial", Font.BOLD, 13));
            cbDSKhoaTK.setBounds(260, 65, 120, 35);
            cbDSKhoaTK.addActionListener(this);

            TitledBorder titleTK;
            Border blackline;
            blackline = BorderFactory.createLineBorder(Color.black);
            titleTK = BorderFactory.createTitledBorder(blackline,"Tìm kiếm");
            titleTK.setTitleFont(new Font("Arial", Font.BOLD, 28));
            titleTK.setTitleJustification(TitledBorder.CENTER);
            pnTimKiem.setBorder(titleTK);

            btTimKiem = new JButton("Tìm kiếm");
            btTimKiem.setFont(new Font("Arial", Font.BOLD, 15));
            btTimKiem.setBounds(360, 155, 100, 35);
            btTimKiem.setBackground(Color.cyan);
            btTimKiem.setBorder(new RoundedBorder(10));
            btTimKiem.addActionListener(this);

        }

        pnTimKiem.add(lbLCTK);
        pnTimKiem.add(lbTuKhoaTK);
        pnTimKiem.add(txKhoaTK);
        pnTimKiem.add(cbDSKhoaTK);
        pnTimKiem.add(btTimKiem);
    }

    public void setLoc() {
        // set Border
        TitledBorder titleLoc;
        Border blackline;
        blackline = BorderFactory.createLineBorder(Color.black);
        titleLoc = BorderFactory.createTitledBorder(blackline, "Lọc dữ liệu");
        titleLoc.setTitleFont(new Font("Arial", Font.BOLD, 25));
        titleLoc.setTitleJustification(TitledBorder.CENTER);

        lbNgayBD = new JLabel("Ngày bắt đầu: ");
        lbNgayBD.setFont(new Font("Arial", Font.BOLD, 18));
        lbNgayBD.setBounds(5, 15, 150, 80);

        lbNgayKT = new JLabel("Ngày kết thúc: ");
        lbNgayKT.setFont(new Font("Arial", Font.BOLD, 18));
        lbNgayKT.setBounds(5, 55, 150, 80);

        btLoc = new JButton("Lọc");
        btLoc.setFont(new Font("Arial", Font.BOLD, 15));
        btLoc.setBounds(210, 120, 80, 30);
        btLoc.setBackground(Color.cyan);
        btLoc.setBorder(new RoundedBorder(10));
        btLoc.addActionListener(this);

        // Set date picker1
        modelNgayBD = new UtilDateModel();
        modelNgayBD.setSelected(true);
        pNgayBD = new Properties();
        pNgayBD.put("text.today", "Today");
        pNgayBD.put("text.month", "Month");
        pNgayBD.put("text.year", "Year");
        datePanelNgayBD = new JDatePanelImpl(modelNgayBD, pNgayBD);
        datePickerNgayBD = new JDatePickerImpl(datePanelNgayBD, new DateLabelFormatter());
        datePickerNgayBD.setBounds(140, 40, 150, 30);

        // Set date picker1
        modelNgayKT = new UtilDateModel();
        modelNgayKT.setSelected(true);
        pNgayKT = new Properties();
        pNgayKT.put("text.today", "Today");
        pNgayKT.put("text.month", "Month");
        pNgayKT.put("text.year", "Year");
        datePanelNgayKT = new JDatePanelImpl(modelNgayKT, pNgayKT);
        datePickerNgayKT = new JDatePickerImpl(datePanelNgayKT, new DateLabelFormatter());
        datePickerNgayKT.setBounds(140, 80, 150, 30);

        pnLoc.setBorder(titleLoc);
        pnLoc.add(lbNgayBD);
        pnLoc.add(lbNgayKT);
        pnLoc.add(btLoc);
        pnLoc.add(datePickerNgayBD);
        pnLoc.add(datePickerNgayKT);
    }

    public void setValueCellCenter(DefaultTableModel model, JTable table) {
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        for (int i = 0; i < model.getColumnCount(); i++) {
            table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }
    }
}

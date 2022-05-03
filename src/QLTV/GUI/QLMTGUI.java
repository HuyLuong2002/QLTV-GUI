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
import javax.swing.JOptionPane;
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
import QLTV.BUS.QLSACHBUS;
import QLTV.BUS.QLTRABUS;
import QLTV.DTO.CHITIETPHIEUMUON;
import QLTV.DTO.CHITIETPHIEUTRA;
import QLTV.DTO.PHIEUMUON;
import QLTV.DTO.PHIEUTRASACH;
import QLTV.DTO.SACH;

public class QLMTGUI extends JFrame implements ActionListener, MouseListener {
    JPanel pnMuonTra, pnTabMuon, pnTabTra, pnTabTienPhat, pnShowAll, pnMuon, pnCTMuon, pnNhapPM,
            pnTimKiemPM, pnLocPM, pnNhapPT, pnTimKiemPT, pnLocPT, pnTra, pnCTTra;
    JLabel lbHome, lbMaPM, lbNgayMuon, lbSLtong, lbNgayTra, lbTinhTrangMuon,
            lbMaDG, lbLCTK, lbTuKhoaTK, lbNgayBD, lbNgayKT;
    JButton btMenu, btSach, btMT, btQLNV, btDangXuat, btNhapSach, btThoat, btMenuTimKiem, btThongKe;
    JButton btShowAll, btTimKiem, btLoc;
    JTextField txMaPM, txSLtong, txMaDG, txKhoaTK;
    JComboBox<String> cbTinhTrangMuon, cbDSKhoaTK;

    TitledBorder titleMuon, titleTra;
    JTabbedPane tabbedPane;

    JTable tblQLMuon, tblQLCTMuon, tblQLTra, tblQLCTTra;
    DefaultTableModel modelMuon, modelCTMuon, modelTra, modelCTTra;

    UtilDateModel modelNgayBD, modelNgayKT;
    Properties pNgayBD, pNgayKT;
    JDatePanelImpl datePanelNgayBD, datePanelNgayKT;
    JDatePickerImpl datePickerNgayBD, datePickerNgayKT;
    Color ColorPurple;

    UtilDateModel modelNgayBDPM, modelNgayKTPM;
    Properties pNgayBDPM, pNgayKTPM;
    JDatePanelImpl datePanelNgayBDPM, datePanelNgayKTPM;
    JDatePickerImpl datePickerNgayBDPM, datePickerNgayKTPM;

    public QLMTGUI() {
    }

    public JPanel setMTGUI() {
        ColorPurple = new Color(255, 20, 147);
        if (pnMuonTra == null) {
            pnMuonTra = new JPanel();
            pnMuonTra.setBounds(240, 0, 1145, 800);
            pnMuonTra.setLayout(null);

            // panel tab mượn
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

            pnTimKiemPM = new JPanel();
            pnTimKiemPM.setLayout(null);
            pnTimKiemPM.setBounds(570, 0, 567, 200);

            pnLocPM = new JPanel();
            pnLocPM.setLayout(null);
            pnLocPM.setBounds(570, 195, 300, 155);

            // panel tab trả
            pnTabTra = new JPanel();
            pnTabTra.setLayout(new GridLayout(1, 2, 5, 0));

            pnTra = new JPanel();
            pnTra.setLayout(new GridLayout(1, 1));

            pnCTTra = new JPanel();
            pnCTTra.setLayout(new GridLayout(1, 1));

            pnNhapPT = new JPanel();
            pnNhapPT.setLayout(null);
            pnNhapPT.setBounds(5, 410, 1137, 370);

            pnTimKiemPT = new JPanel();
            pnTimKiemPT.setLayout(null);
            pnTimKiemPT.setBounds(570, 0, 567, 200);

            pnLocPT = new JPanel();
            pnLocPT.setLayout(null);
            pnLocPT.setBounds(570, 195, 300, 155);

            // panel tab tiền phạt
            pnTabTienPhat = new JPanel();
            pnTabTienPhat.setLayout(new GridLayout(1, 2, 5, 0));

            tabbedPane = new JTabbedPane();
            tabbedPane.addTab("Mượn sách", pnTabMuon);
            tabbedPane.addTab("Trả sách", pnTabTra);
            tabbedPane.addTab("HĐ Tiền phạt", pnTabTienPhat);
            tabbedPane.addMouseListener(this);
            tabbedPane.setBounds(5, 5, 1138, 350);

            pnMuonTra.add(tabbedPane);
            pnMuonTra.add(pnShowAll);
            pnMuonTra.add(pnNhapPM);
            pnMuonTra.add(pnNhapPT);

            pnNhapPM.add(pnTimKiemPM);
            pnNhapPM.add(pnLocPM);

            pnNhapPT.add(pnTimKiemPT);
            pnNhapPT.add(pnLocPT);

            pnTabMuon.add(pnMuon);
            pnTabMuon.add(pnCTMuon);

            pnTabTra.add(pnTra);
            pnTabTra.add(pnCTTra);

            // Phiếu mượn, chi tiết mượn
            setTitlePM();
            setTableMuon();
            setTableCTMuon();
            setShowAll();
            setInputMuon();
            setTimKiemPM();
            setLocPM();
            getDBMuon();
            getDBCTPM();
            setValueCellCenter(modelMuon, tblQLMuon);
            setValueCellCenter(modelCTMuon, tblQLCTMuon);

            // Phiếu trả, chi tiết phiếu trả
            setTitlePT();
            setTableTra();
            setTableCTTra();
            getDBTra();
            setValueCellCenter(modelTra, tblQLTra);
        }
        return pnMuonTra;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btTimKiem) {
            int vtkey = Integer.parseInt(String.valueOf(cbDSKhoaTK.getSelectedIndex()));
            String tukhoa = txKhoaTK.getText();
            if (tukhoa.equals("") == true) {
                JOptionPane.showMessageDialog(null, "Xin mời nhập từ khóa", "Lỗi", JOptionPane.ERROR_MESSAGE);
            } else if (vtkey == 0) {
                JOptionPane.showMessageDialog(null, "Xin mời lựa chọn khóa tìm kiếm", "Lỗi", JOptionPane.ERROR_MESSAGE);
            } else {
                QLMUONBUS qlsachbus = new QLMUONBUS();
                if (vtkey == 1) {
                    PHIEUMUON kq = qlsachbus.timTheoMa(tukhoa);
                    modelMuon.setRowCount(0);
                    if (kq == null) {
                        ShowOnTablePM(kq);
                        tblQLMuon.setModel(modelMuon);
                    }

                }
                if (vtkey == 2) {
                    ArrayList<PHIEUMUON> kq = qlsachbus.timTheoSLtong(tukhoa);
                    modelMuon.setRowCount(0);
                    if (kq.size() == 0) {
                        JOptionPane.showMessageDialog(null, "Không tìm thấy dữ liệu phù hợp", "Lỗi",
                                JOptionPane.ERROR_MESSAGE);
                    } else {
                        for (PHIEUMUON pm : kq) {
                            ShowOnTablePM(pm);
                        }
                        tblQLMuon.setModel(modelMuon);
                    }
                }
                if (vtkey == 3) {
                    ArrayList<PHIEUMUON> kq = qlsachbus.timTheoTinhTrangMuon(tukhoa.trim());
                    modelMuon.setRowCount(0);
                    if (kq.size() == 0) {
                        JOptionPane.showMessageDialog(null, "Không tìm thấy dữ liệu phù hợp", "Lỗi",
                                JOptionPane.ERROR_MESSAGE);
                    } else {
                        for (PHIEUMUON pm : kq) {
                            ShowOnTablePM(pm);
                        }
                        tblQLMuon.setModel(modelMuon);
                    }
                }
                if (vtkey == 4) {
                    ArrayList<PHIEUMUON> kq = qlsachbus.timTheoMaDG(tukhoa.trim());
                    modelMuon.setRowCount(0);
                    if (kq.size() == 0) {
                        JOptionPane.showMessageDialog(null, "Không tìm thấy dữ liệu phù hợp", "Lỗi",
                                JOptionPane.ERROR_MESSAGE);
                    } else {
                        for (PHIEUMUON pm : kq) {
                            ShowOnTablePM(pm);
                        }
                        tblQLMuon.setModel(modelMuon);
                    }
                }
            }
        }
        if (e.getSource() == btShowAll) {
            if (pnTabMuon.isVisible()) {
                modelMuon.setRowCount(0);
                for (PHIEUMUON pm : QLMUONBUS.dspm) {
                    ShowOnTablePM(pm);
                }
                tblQLMuon.setModel(modelMuon);
            }
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource() == tblQLMuon) {
            int i = tblQLMuon.getSelectedRow();
            if (i >= 0) {
                // Hiển thị bên CTPM
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
                // Hiển thị trên textfield
                PHIEUMUON pmTextField = new PHIEUMUON();
                pmTextField = QLMUONBUS.dspm.get(i);
                txMaPM.setText(pmTextField.getMaPM().trim());
                String tmp[] = pmTextField.getNgaymuon().split("-");
                datePanelNgayBDPM.getModel().setDate(Integer.parseInt(tmp[0]), Integer.parseInt(tmp[1]),
                        Integer.parseInt(tmp[2]));
                txSLtong.setText(String.valueOf(pmTextField.getSLtong()));
                String tmp1[] = pmTextField.getNgaytra().split("-");
                datePanelNgayKTPM.getModel().setDate(Integer.parseInt(tmp1[0]), Integer.parseInt(tmp1[1]),
                        Integer.parseInt(tmp1[2]));
                if (pmTextField.getTinhTrangMuon().equals("Đang mượn")) {
                    cbTinhTrangMuon.setSelectedIndex(1);
                }
                if (pmTextField.getTinhTrangMuon().equals("Hết mượn")) {
                    cbTinhTrangMuon.setSelectedIndex(2);
                }
                txMaDG.setText(pmTextField.getMaDG());

            }
        }
        if (e.getSource() == tabbedPane) {
            int index = tabbedPane.getSelectedIndex();
            if (index == 0) {
                if (pnTabMuon.isVisible() == false) {
                    pnTabMuon.setVisible(true);
                }
                if (pnNhapPM.isVisible() == false) {
                    pnNhapPM.setVisible(true);
                }
                pnTabTra.setVisible(false);
                pnTabTienPhat.setVisible(false);
            }
            if (index == 1) {
                if (pnTabTra.isVisible() == false) {
                    pnTabTra.setVisible(true);
                }
                if (pnNhapPM.isVisible() == true) {
                    pnNhapPM.setVisible(false);
                }
                pnTabMuon.setVisible(false);
                pnTabTienPhat.setVisible(false);
            }
            if (index == 2) {
                if (pnTabTienPhat.isVisible() == false) {
                    pnTabTienPhat.setVisible(true);
                }
                if (pnNhapPM.isVisible() == true) {
                    pnNhapPM.setVisible(false);
                }
                pnTabMuon.setVisible(false);
                pnTabTra.setVisible(false);
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
            header.add("Tình trạng mượn");
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

    public void getDBTra() {
        try {
            QLTRABUS qltrabus = new QLTRABUS();
            if (QLTRABUS.dspt == null)
                try {
                    qltrabus.docDSPT();
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            // set up header(column)
            Vector<String> header = new Vector<String>();
            header.add("Mã phiếu trả");
            header.add("Ngày trả");
            header.add("Tình trạng sách");
            header.add("Tiền thuê");
            header.add("Thành tiền");
            header.add("Mã phiếu mượn");
            modelTra = new DefaultTableModel(header, 0);
            for (PHIEUTRASACH pt : QLTRABUS.dspt) {
                ShowOnTablePT(pt);
            }
            tblQLTra.setModel(modelTra);
        } catch (Exception e1) {
            System.out.println(e1);
        }
    }

    public void ShowOnTablePM(PHIEUMUON pm) {
        Vector<String> row = new Vector<String>();
        row.add(pm.getMaPM().trim());
        row.add(pm.getNgaymuon().trim());
        row.add(String.valueOf(pm.getSLtong()));
        row.add(pm.getNgaytra().trim());
        row.add(pm.getTinhTrangMuon().trim());
        row.add(pm.getMaDG().trim());
        modelMuon.addRow(row);
    }

    public void ShowOnTableCTPM(CHITIETPHIEUMUON ctpm) {
        Vector<String> row = new Vector<String>();
        row.add(ctpm.getMaPM().trim());
        row.add(ctpm.getMasach().trim());
        row.add(String.valueOf(ctpm.getSL()));
        modelCTMuon.addRow(row);
    }

    public void ShowOnTablePT(PHIEUTRASACH pt) {
        Vector<String> row = new Vector<String>();
        row.add(pt.getMaPT().trim());
        row.add(pt.getNgaytra().trim());
        row.add(pt.getTinhtrangsach().trim());
        row.add(String.format("%,d", pt.getTienthue()));
        row.add(String.format("%,d", pt.getThanhtien()));
        row.add(pt.getMaPM().trim());
        modelTra.addRow(row);
    }

    public void ShowOnTableCTPT(CHITIETPHIEUTRA ctpt) {
        Vector<String> row = new Vector<String>();
        row.add(ctpt.getMaPT().trim());
        row.add(ctpt.getMasach().trim());
        row.add(String.valueOf(ctpt.getSL()));
        modelCTTra.addRow(row);
    }

    public void setTitlePM() {
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

    public void setTitlePT() {
        // set Border
        Border empty;
        empty = BorderFactory.createEmptyBorder();

        titleTra = BorderFactory.createTitledBorder(empty, "THÔNG TIN PHIẾU TRẢ");
        titleTra.setTitleFont(new Font("Arial", Font.BOLD, 28));
        titleTra.setTitleJustification(TitledBorder.CENTER);
        pnTra.setBorder(titleTra);

        titleTra = BorderFactory.createTitledBorder(empty, "CHI TIẾT PHIẾU TRẢ");
        titleTra.setTitleFont(new Font("Arial", Font.BOLD, 28));
        titleTra.setTitleJustification(TitledBorder.CENTER);
        pnCTTra.setBorder(titleTra);

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

    public void setTableTra() {
        // ----set up table----
        tblQLTra = new JTable();
        JScrollPane pane = new JScrollPane(tblQLTra);
        pane.setAutoscrolls(true);
        tblQLTra.setRowHeight(20);
        tblQLTra.setFont(new Font(null, 0, 13));
        tblQLTra.setBackground(Color.LIGHT_GRAY);
        tblQLTra.addMouseListener(this);
        tblQLTra.setDefaultEditor(Object.class, null);
        pnTra.add(pane);
    }

    public void setTableCTTra() {
        // ----set up table----
        tblQLCTTra = new JTable();
        JScrollPane pane = new JScrollPane(tblQLCTTra);
        pane.setAutoscrolls(true);
        tblQLCTTra.setRowHeight(20);
        tblQLCTTra.setFont(new Font(null, 0, 13));
        tblQLCTTra.setBackground(Color.LIGHT_GRAY);
        tblQLCTTra.addMouseListener(this);
        tblQLCTTra.setDefaultEditor(Object.class, null);
        pnCTTra.add(pane);
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
        String[] dsTinhTrangMuon = { "", "Đang mượn", "Hết mượn" };
        cbTinhTrangMuon = new JComboBox<>(dsTinhTrangMuon);
        cbTinhTrangMuon.setFont(new Font("Arial", Font.BOLD, 13));
        cbTinhTrangMuon.setBounds(200, 235, 120, 30);
        cbTinhTrangMuon.addActionListener(this);

        // Set date picker1
        modelNgayBDPM = new UtilDateModel();
        modelNgayBDPM.setSelected(true);
        pNgayBDPM = new Properties();
        pNgayBDPM.put("text.today", "Today");
        pNgayBDPM.put("text.month", "Month");
        pNgayBDPM.put("text.year", "Year");
        datePanelNgayBDPM = new JDatePanelImpl(modelNgayBDPM, pNgayBDPM);
        datePickerNgayBDPM = new JDatePickerImpl(datePanelNgayBDPM, new DateLabelFormatter());
        datePickerNgayBDPM.setBounds(200, 85, 150, 30);

        // Set date picker1
        modelNgayKTPM = new UtilDateModel();
        modelNgayKTPM.setSelected(true);
        pNgayKTPM = new Properties();
        pNgayKTPM.put("text.today", "Today");
        pNgayKTPM.put("text.month", "Month");
        pNgayKTPM.put("text.year", "Year");
        datePanelNgayKTPM = new JDatePanelImpl(modelNgayKTPM, pNgayKTPM);
        datePickerNgayKTPM = new JDatePickerImpl(datePanelNgayKTPM, new DateLabelFormatter());
        datePickerNgayKTPM.setBounds(200, 185, 150, 30);

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

        pnNhapPM.add(datePickerNgayBDPM);
        pnNhapPM.add(datePickerNgayKTPM);
    }

    public void setTimKiemPM() {
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

            String[] dsKhoaTK = { "", "Mã phiếu mượn", "SL tổng", "Tình trạng mượn", "Mã độc giả" };
            cbDSKhoaTK = new JComboBox<>(dsKhoaTK);
            cbDSKhoaTK.setFont(new Font("Arial", Font.BOLD, 13));
            cbDSKhoaTK.setBounds(260, 65, 120, 35);
            cbDSKhoaTK.addActionListener(this);

            TitledBorder titleTK;
            Border blackline;
            blackline = BorderFactory.createLineBorder(Color.black);
            titleTK = BorderFactory.createTitledBorder(blackline, "Tìm kiếm");
            titleTK.setTitleFont(new Font("Arial", Font.BOLD, 28));
            titleTK.setTitleJustification(TitledBorder.CENTER);
            pnTimKiemPM.setBorder(titleTK);

            btTimKiem = new JButton("Tìm kiếm");
            btTimKiem.setFont(new Font("Arial", Font.BOLD, 15));
            btTimKiem.setBounds(360, 155, 100, 35);
            btTimKiem.setBackground(Color.cyan);
            btTimKiem.setBorder(new RoundedBorder(10));
            btTimKiem.addActionListener(this);

        }

        pnTimKiemPM.add(lbLCTK);
        pnTimKiemPM.add(lbTuKhoaTK);
        pnTimKiemPM.add(txKhoaTK);
        pnTimKiemPM.add(cbDSKhoaTK);
        pnTimKiemPM.add(btTimKiem);
    }

    public void setLocPM() {
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
        btLoc.setBounds(210, 115, 80, 30);
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

        pnLocPM.setBorder(titleLoc);
        pnLocPM.add(lbNgayBD);
        pnLocPM.add(lbNgayKT);
        pnLocPM.add(btLoc);
        pnLocPM.add(datePickerNgayBD);
        pnLocPM.add(datePickerNgayKT);
    }

    public void setValueCellCenter(DefaultTableModel model, JTable table) {
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        for (int i = 0; i < model.getColumnCount(); i++) {
            table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }
    }
}

package QLTV.GUI;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Properties;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
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

import QLTV.BUS.QLCTMUONBUS;
import QLTV.BUS.QLMUONBUS;
import QLTV.DTO.CHITIETPHIEUMUON;
import QLTV.DTO.PHIEUMUON;

public class QLMTGUI extends JFrame implements ActionListener, MouseListener {
    JPanel pnMuonTra, pnTabMuon, pnMuon, pnCTMuon, pnNhapPM, pnNhapCTPM;

    JLabel lbHome;
    JButton btMenu, btSach, btMT, btQLNV, btDangXuat, btNhapSach, btThoat, btMenuTimKiem, btThongKe;

    TitledBorder titleMuon;
    JTabbedPane tabbedPane;

    JTable tblQLMuon, tblQLCTMuon;
    DefaultTableModel modelMuon, modelCTMuon;

    UtilDateModel modelNgayBD, modelNgayKT;
    Properties pNgayBD, pNgayKT;
    JDatePanelImpl datePanelNgayBD, datePanelNgayKT;
    JDatePickerImpl datePickerNgayBD, datePickerNgayKT;

    public QLMTGUI() {
    }

    public JPanel setMTGUI() {
        if (pnMuonTra == null) {
            pnMuonTra = new JPanel();
            pnMuonTra.setBounds(320, 0, 1200, 1000);
            pnMuonTra.setLayout(null);

            pnTabMuon = new JPanel();
            pnTabMuon.setLayout(new GridLayout(1, 2, 5, 0));

            pnMuon = new JPanel();
            pnMuon.setPreferredSize(new Dimension(300, 300));
            pnMuon.setLayout(new GridLayout(1, 1));

            pnCTMuon = new JPanel();
            pnCTMuon.setLayout(new GridLayout(1, 1));

            pnNhapPM = new JPanel();
            pnNhapPM.setLayout(null);
            pnNhapPM.setBackground(Color.BLACK);
            pnNhapPM.setBounds(5, 415, 585, 370);

            pnNhapCTPM = new JPanel();
            pnNhapCTPM.setLayout(null);
            pnNhapCTPM.setBackground(Color.RED);
            pnNhapCTPM.setBounds(605, 415, 590, 370);

            tabbedPane = new JTabbedPane();
            tabbedPane.addTab("Mượn sách", pnTabMuon);
            tabbedPane.addTab("Trả sách", null);
            tabbedPane.addTab("HĐ Tiền phạt", null);
            tabbedPane.addMouseListener(this);
            tabbedPane.setBounds(5, 5, 1190, 400);

            pnMuonTra.add(tabbedPane);
            pnMuonTra.add(pnNhapPM);
            pnMuonTra.add(pnNhapCTPM);
            pnTabMuon.add(pnMuon);
            pnTabMuon.add(pnCTMuon);

            setTitle();
            setTableMuon();
            setTableCTMuon();
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
            for (CHITIETPHIEUMUON ctpm : QLCTMUONBUS.dsctpm) {
                ShowOnTableCTPM(ctpm);
            }
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

    public void setValueCellCenter(DefaultTableModel model, JTable table) {
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        for (int i = 0; i < model.getColumnCount(); i++) {
            table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }
    }
}

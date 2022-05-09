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

import MyCustom.BangTTPM;
import MyCustom.DateLabelFormatter;
import MyCustom.RoundedBorder;
import QLTV.BUS.QLCTHDTPBUS;
import QLTV.BUS.QLCTMUONBUS;
import QLTV.BUS.QLMUONBUS;
import QLTV.BUS.QLTRABUS;
import QLTV.DTO.CHITIETHDTIENPHAT;
import QLTV.DTO.CHITIETPHIEUMUON;
import QLTV.DTO.CHITIETPHIEUTRA;
import QLTV.DTO.PHIEUMUON;
import QLTV.DTO.PHIEUTRASACH;

public class QLMTGUI extends JFrame implements ActionListener, MouseListener {
    JPanel pnMuonTra, pnTabMuon, pnTabTra, pnTabTienPhat, pnShowAll, pnMuon, pnCTMuon, pnNhapPM,
<<<<<<< HEAD
            pnTimKiemPM, pnLocPM, pnNhapPT, pnTimKiemPT, pnLocPT, pnTra, pnCTTra;
=======
            pnTimKiemPM, pnLocPM, pnNhapPT, pnTimKiemPT, pnLocPT, pnTra, pnCTTra, pnHDTP, pnCTHDTP;
>>>>>>> 80da50e157630b12aaa64f50b09ff5b1146bdb93
    JLabel lbHome, lbMaPM, lbNgayMuon, lbSLtong, lbNgayTra, lbTinhTrangMuon,
<<<<<<< HEAD
            lbMaDG, lbLCTK, lbTuKhoaTK, lbNgayBD, lbNgayKT;
    JButton btThoat, btSua, btThem, btXoa, btHoanTac;
    JButton btShowAll, btTimKiem, btLoc, btInPM;
    JTextField txMaPM, txSLtong, txMaDG, txKhoaTK;
    JComboBox<String> cbTinhTrangMuon, cbDSKhoaTK;
=======
            lbMaDG, lbLCTKPM, lbTuKhoaTKPM, lbLCTKPT, lbTuKhoaTKPT, lbNgayBD, lbNgayKT, lbMaPT, lbNgayTraPT,
            lbTinhTrangSach, lbTienThue, lbThanhTien, lbMaPMTra;
    JButton btMenu, btSach, btMT, btQLNV, btDangXuat, btNhapSach, btThoat, btMenuTimKiem, btThongKe;
    JButton btShowAll, btTimKiemPM, btLocPM, btInPM, btTimKiemPT, btLocPT;
    JTextField txMaPM, txSLtong, txMaDG, txKhoaTKPM, txKhoaTKPT, txMaPT, txTienThue, txThanhTien, txMaPMTra;
    JComboBox<String> cbTinhTrangMuon, cbTinhTrangTra, cbDSKhoaTKPM, cbDSKhoaTKPT;
>>>>>>> 2e9da2c8711846a07f27a86e09e310dd42ff5c5b

<<<<<<< HEAD
    TitledBorder titleMuon, titleTra;
    JTabbedPane tabbedPane;

    public JTable tblQLMuon, tblQLCTMuon, tblQLTra, tblQLCTTra;
    public DefaultTableModel modelMuon, modelCTMuon, modelTra, modelCTTra;
=======
    TitledBorder titleMuon, titleTra, titleHDTP, titleCTHDTP;
    JTabbedPane tabbedPane;

    public JTable tblQLMuon, tblQLCTMuon, tblQLTra, tblQLCTTra, tblQLHDTP, tblQLCTHDTP;
    public DefaultTableModel modelMuon, modelCTMuon, modelTra, modelCTTra, modelHDTP, modelCTHDTP;
>>>>>>> 80da50e157630b12aaa64f50b09ff5b1146bdb93

    UtilDateModel modelNgayBDMuon, modelNgayKTMuon;
    Properties pNgayBDMuon, pNgayKTMuon;
    JDatePanelImpl datePanelNgayBDMuon, datePanelNgayKTMuon;
    JDatePickerImpl datePickerNgayBDMuon, datePickerNgayKTMuon;
    Color ColorPurple;

    UtilDateModel modelNgayBDTra, modelNgayKTTra;
    Properties pNgayBDTra, pNgayKTTra;
    JDatePanelImpl datePanelNgayBDTra, datePanelNgayKTTra;
    JDatePickerImpl datePickerNgayBDTra, datePickerNgayKTTra;

    UtilDateModel modelNgayBDPM, modelNgayKTPM;
    Properties pNgayBDPM, pNgayKTPM;
    JDatePanelImpl datePanelNgayBDPM, datePanelNgayKTPM;
    JDatePickerImpl datePickerNgayBDPM, datePickerNgayKTPM;

<<<<<<< HEAD
    Vector<String> header;
=======
    UtilDateModel modelNgayBDPT;
    Properties pNgayBDPT;
    JDatePanelImpl datePanelNgayBDPT;
    JDatePickerImpl datePickerNgayBDPT;
>>>>>>> 2e9da2c8711846a07f27a86e09e310dd42ff5c5b

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
<<<<<<< HEAD
=======

            // panel tab tiền phạt
            pnHDTP = new JPanel();
            pnHDTP.setLayout(new GridLayout(1, 1));

            pnCTHDTP = new JPanel();
            pnCTHDTP.setLayout(new GridLayout(1, 1));
>>>>>>> 80da50e157630b12aaa64f50b09ff5b1146bdb93

            pnNhapPT = new JPanel();
            pnNhapPT.setLayout(null);
            pnNhapPT.setVisible(false);
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

<<<<<<< HEAD
=======
            pnTabTienPhat.add(pnHDTP);
            pnTabTienPhat.add(pnCTHDTP);

>>>>>>> 80da50e157630b12aaa64f50b09ff5b1146bdb93
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
            setInputTra();
            setTimKiemPT();
            setLocPT();
            getDBTra();
            setValueCellCenter(modelTra, tblQLTra);
<<<<<<< HEAD
=======

            // Hóa đơn tiền phạt, chi tiết hóa đơn
            setTitleHDTP();
            setTableHDTP();
            setTableCTHDTP();
            getDBHDTP();
            getDBCTHDTP();
            setValueCellCenter(modelHDTP, tblQLHDTP);
            setValueCellCenter(modelCTHDTP, tblQLCTHDTP);

>>>>>>> 80da50e157630b12aaa64f50b09ff5b1146bdb93
        }
        return pnMuonTra;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btTimKiemPM) {
            int vtkey = Integer.parseInt(String.valueOf(cbDSKhoaTKPM.getSelectedIndex()));
            String tukhoa = txKhoaTKPM.getText();
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
            int index = tabbedPane.getSelectedIndex();
            if (index == 0) {
                modelMuon.setRowCount(0);
                for (PHIEUMUON pm : QLMUONBUS.dspm) {
                    ShowOnTablePM(pm);
                }
                tblQLMuon.setModel(modelMuon);
            }
            if (index == 1) {
                modelTra.setRowCount(0);
                for (PHIEUTRASACH pt : QLTRABUS.dspt) {
                    ShowOnTablePT(pt);
                }
                tblQLTra.setModel(modelTra);
            }
            if (index == 2) {
                modelHDTP.setRowCount(0);
                for (HDTIENPHAT hd : QLHDTPBUS.dshdtp) {
                    ShowOnTableHD(hd);
                }
                tblQLHDTP.setModel(modelHDTP);
            }
        }
        if (e.getSource() == btInPM) {
            new BangTTPM();
        }
        if (e.getSource() == btThem){
            try {
                PHIEUMUON phieumuon = new PHIEUMUON();
                getInfoTextField(phieumuon);
                // Truy cập vào bus
                QLMUONBUS qlphieumuonbus = new QLMUONBUS();
                int kiemtra = 0;
                try {
                    kiemtra = qlphieumuonbus.them(phieumuon);
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
                if (kiemtra == 1) {
                    // Đưa dữ liệu lên table
                    header = new Vector<String>();
                    header.add("Mã sách");
                    header.add("Tên sách");
                    header.add("Mã NXB");
                    header.add("Mã tác giả");
                    header.add("Năm xuất bản");
                    header.add("SL tổng");
                    header.add("SL");
                    header.add("Đơn giá");
                    if (modelMuon.getRowCount() == 0) {
                        modelMuon = new DefaultTableModel(header, 0);
                    }
                    ShowOnTable(phieumuon);
                    tblQLMuon.setModel(modelMuon);
                }
            } catch (Exception e1) {
                System.out.println(e1);
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
        if (e.getSource() == tblQLHDTP) {
            int i = tblQLHDTP.getSelectedRow();
            if (i >= 0) {
                // Hiển thị bên CTHDTP
                modelCTHDTP.setRowCount(0);
                ArrayList<CHITIETHDTIENPHAT> kq = new ArrayList<CHITIETHDTIENPHAT>();
                HDTIENPHAT pm = QLHDTPBUS.dshdtp.get(i);
                for (CHITIETHDTIENPHAT cthd : QLCTHDTPBUS.dscthdtp) {
                    if (cthd.getMaHD().indexOf(pm.getMaHD()) >= 0) {
                        kq.add(cthd); // Chứa phần tử của ctpm thỏa mã pm
                    }
                }
                for (CHITIETHDTIENPHAT cthd : kq) {
                    ShowOnTableCTHD(cthd);
                }
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
<<<<<<< HEAD
=======
                if (btInPM.isVisible() == false) {
                    btInPM.setVisible(true);
                }
<<<<<<< HEAD
>>>>>>> 80da50e157630b12aaa64f50b09ff5b1146bdb93
=======
                if (pnNhapPT.isVisible() == true) {
                    pnNhapPT.setVisible(false);
                }
>>>>>>> 2e9da2c8711846a07f27a86e09e310dd42ff5c5b
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
<<<<<<< HEAD
=======
                if (btInPM.isVisible() == true) {
                    btInPM.setVisible(false);
                }
<<<<<<< HEAD
>>>>>>> 80da50e157630b12aaa64f50b09ff5b1146bdb93
=======
                if (pnNhapPT.isVisible() == false) {
                    pnNhapPT.setVisible(true);
                }
>>>>>>> 2e9da2c8711846a07f27a86e09e310dd42ff5c5b
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
<<<<<<< HEAD
=======
                if (btInPM.isVisible() == true) {
                    btInPM.setVisible(false);
                }
<<<<<<< HEAD
>>>>>>> 80da50e157630b12aaa64f50b09ff5b1146bdb93
=======
                if (pnNhapPT.isVisible() == true) {
                    pnNhapPT.setVisible(false);
                }
>>>>>>> 2e9da2c8711846a07f27a86e09e310dd42ff5c5b
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
                    qlmuonbus.docDS();
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

<<<<<<< HEAD
=======
    public void getDBHDTP() {
        try {
            QLHDTPBUS qlhdtpbus = new QLHDTPBUS();
            if (QLHDTPBUS.dshdtp == null)
                try {
                    qlhdtpbus.docDS();
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            // set up header(column)
            Vector<String> header = new Vector<String>();
            header.add("Mã hóa đơn");
            header.add("Mã độc giả");
            header.add("Số lượng");
            header.add("Tiền phạt");
            modelHDTP = new DefaultTableModel(header, 0);
            for (HDTIENPHAT hd : QLHDTPBUS.dshdtp) {
                ShowOnTableHD(hd);
            }
            tblQLHDTP.setModel(modelHDTP);
        } catch (Exception e1) {
            System.out.println(e1);
        }
    }

    public void getDBCTHDTP() {
        try {
            QLCTHDTPBUS qlcthdtpbus = new QLCTHDTPBUS();
            if (QLCTHDTPBUS.dscthdtp == null)
                try {
                    qlcthdtpbus.docDS();
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            // set up header(column)
            Vector<String> header = new Vector<String>();
            header.add("Mã hóa đơn");
            header.add("Mã sách");
            header.add("Số lượng");
            header.add("Đơn giá");
            modelCTHDTP = new DefaultTableModel(header, 0);
            tblQLCTHDTP.setModel(modelCTHDTP);
        } catch (Exception e1) {
            System.out.println(e1);
        }
    }

>>>>>>> 80da50e157630b12aaa64f50b09ff5b1146bdb93
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

<<<<<<< HEAD
=======
    public void ShowOnTableHD(HDTIENPHAT hd) {
        Vector<String> row = new Vector<String>();
        row.add(hd.getMaHD().trim());
        row.add(hd.getMaDG().trim());
        row.add(String.valueOf(hd.getSL()));
        row.add(String.format("%,d", hd.getTienphat()));
        modelHDTP.addRow(row);
    }

    public void ShowOnTableCTHD(CHITIETHDTIENPHAT cthd) {
        Vector<String> row = new Vector<String>();
        row.add(cthd.getMaHD().trim());
        row.add(cthd.getMasach().trim());
        row.add(String.valueOf(cthd.getSL()));
        row.add(String.format("%,d", cthd.getDongia()));
        modelCTHDTP.addRow(row);
    }

>>>>>>> 80da50e157630b12aaa64f50b09ff5b1146bdb93
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

        titleMuon = BorderFactory.createTitledBorder(empty, "THÔNG TIN PHIẾU MƯỢN");
        titleMuon.setTitleFont(new Font("Arial", Font.BOLD, 28));
        titleMuon.setTitleJustification(TitledBorder.CENTER);
        pnMuon.setBorder(titleMuon);

        titleMuon = BorderFactory.createTitledBorder(empty, "CHI TIẾT PHIẾU MƯỢN");
        titleMuon.setTitleFont(new Font("Arial", Font.BOLD, 28));
        titleMuon.setTitleJustification(TitledBorder.CENTER);
        pnCTMuon.setBorder(titleMuon);
    }

<<<<<<< HEAD
=======
    public void setTitleHDTP() {
        // set Border
        Border empty;
        empty = BorderFactory.createEmptyBorder();

        titleHDTP = BorderFactory.createTitledBorder(empty, "HÓA ĐƠN TIỀN PHẠT");
        titleHDTP.setTitleFont(new Font("Arial", Font.BOLD, 28));
        titleHDTP.setTitleJustification(TitledBorder.CENTER);
        pnHDTP.setBorder(titleHDTP);

        titleHDTP = BorderFactory.createTitledBorder(empty, "CHI TIẾT HÓA ĐƠN TIỀN PHẠT");
        titleHDTP.setTitleFont(new Font("Arial", Font.BOLD, 28));
        titleHDTP.setTitleJustification(TitledBorder.CENTER);
        pnCTHDTP.setBorder(titleHDTP);
    }

>>>>>>> 80da50e157630b12aaa64f50b09ff5b1146bdb93
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

        btInPM = new JButton("In phiếu mượn");
        btInPM.setFont(new Font("Arial", Font.BOLD, 15));
        btInPM.setBounds(0, 10, 130, 30);
        btInPM.setBackground(Color.cyan);
        btInPM.setBorder(new RoundedBorder(10));
        btInPM.addActionListener(this);

        pnShowAll.add(btShowAll);
        pnShowAll.add(btInPM);
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
        tblQLMuon.setSelectionBackground(Color.GREEN);
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
        tblQLCTMuon.setSelectionBackground(Color.ORANGE);
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
        tblQLTra.setSelectionBackground(Color.GREEN);
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
        tblQLCTTra.setSelectionBackground(Color.ORANGE);
        pnCTTra.add(pane);
    }

<<<<<<< HEAD
=======
    public void setTableHDTP() {
        // ----set up table----
        tblQLHDTP = new JTable();
        JScrollPane pane = new JScrollPane(tblQLHDTP);
        pane.setAutoscrolls(true);
        tblQLHDTP.setRowHeight(20);
        tblQLHDTP.setFont(new Font(null, 0, 13));
        tblQLHDTP.setBackground(Color.LIGHT_GRAY);
        tblQLHDTP.addMouseListener(this);
        tblQLHDTP.setDefaultEditor(Object.class, null);
        tblQLHDTP.setSelectionBackground(Color.GREEN);
        pnHDTP.add(pane);
    }

    public void setTableCTHDTP() {
        // ----set up table----
        tblQLCTHDTP = new JTable();
        JScrollPane pane = new JScrollPane(tblQLCTHDTP);
        pane.setAutoscrolls(true);
        tblQLCTHDTP.setRowHeight(20);
        tblQLCTHDTP.setFont(new Font(null, 0, 13));
        tblQLCTHDTP.setBackground(Color.LIGHT_GRAY);
        tblQLCTHDTP.addMouseListener(this);
        tblQLCTHDTP.setDefaultEditor(Object.class, null);
        tblQLCTHDTP.setSelectionBackground(Color.ORANGE);
        pnCTHDTP.add(pane);
    }

>>>>>>> 80da50e157630b12aaa64f50b09ff5b1146bdb93
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

        // Set date picker2
        modelNgayKTPM = new UtilDateModel();
        modelNgayKTPM.setSelected(true);
        pNgayKTPM = new Properties();
        pNgayKTPM.put("text.today", "Today");
        pNgayKTPM.put("text.month", "Month");
        pNgayKTPM.put("text.year", "Year");
        datePanelNgayKTPM = new JDatePanelImpl(modelNgayKTPM, pNgayKTPM);
        datePickerNgayKTPM = new JDatePickerImpl(datePanelNgayKTPM, new DateLabelFormatter());
        datePickerNgayKTPM.setBounds(200, 185, 150, 30);

        btThem = new JButton("Thêm");
        btThem.setFont(new Font("Arial", Font.BOLD, 15));
        btThem.setBounds(300, 150, 80, 30);
        btThem.setBackground(Color.cyan);
        btThem.setBorder(new RoundedBorder(10));
        btThem.addActionListener(this);

        btSua = new JButton("Sửa");
        btSua.setFont(new Font("Arial", Font.BOLD, 15));
        btSua.setBounds(110, 305, 80, 30);
        btSua.setBackground(Color.cyan);
        btSua.setBorder(new RoundedBorder(10));
        btSua.addActionListener(this);

        btXoa = new JButton("Xóa");
        btXoa.setFont(new Font("Arial", Font.BOLD, 15));
        btXoa.setBounds(210, 305, 80, 30);
        btXoa.setBackground(Color.cyan);
        btXoa.setBorder(new RoundedBorder(10));
        btXoa.addActionListener(this);

        btHoanTac = new JButton("Hoàn tác");
        btHoanTac.setFont(new Font("Arial", Font.BOLD, 15));
        btHoanTac.setBounds(310, 305, 90, 30);
        btHoanTac.setBackground(Color.cyan);
        btHoanTac.setBorder(new RoundedBorder(10));
        btHoanTac.addActionListener(this);

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

        pnNhapPM.add(btThem);
    }

<<<<<<< HEAD
    public void setTimKiemPM() {
        if (btTimKiem == null) { // Là button của phần tìm kiếm cơ bản
            if (lbLCTK != null || lbTuKhoaTK != null || cbDSKhoaTK != null || txKhoaTK != null) {
                lbLCTK.setVisible(true);
                lbTuKhoaTK.setVisible(true);
                cbDSKhoaTK.setVisible(true);
                txKhoaTK.setVisible(true);
=======
    public void setInputTra() {
        lbMaPT = new JLabel("Mã phiếu trả:");
        lbMaPT.setFont(new Font("Arial", Font.BOLD, 20));
        lbMaPT.setBounds(0, 0, 200, 100);

        lbNgayTraPT = new JLabel("Ngày trả:");
        lbNgayTraPT.setFont(new Font("Arial", Font.BOLD, 20));
        lbNgayTraPT.setBounds(0, 50, 200, 100);

        lbTinhTrangSach = new JLabel("Tình trạng sách:");
        lbTinhTrangSach.setFont(new Font("Arial", Font.BOLD, 20));
        lbTinhTrangSach.setBounds(0, 100, 200, 100);

        lbTienThue = new JLabel("Tiền thuê:");
        lbTienThue.setFont(new Font("Arial", Font.BOLD, 20));
        lbTienThue.setBounds(0, 150, 200, 100);

        lbThanhTien = new JLabel("Thành tiền:");
        lbThanhTien.setFont(new Font("Arial", Font.BOLD, 20));
        lbThanhTien.setBounds(0, 200, 200, 100);

        lbMaPMTra = new JLabel("Mã phiếu mượn:");
        lbMaPMTra.setFont(new Font("Arial", Font.BOLD, 20));
        lbMaPMTra.setBounds(0, 250, 200, 100);

        String[] dsTinhTrangTra = { "", "Bình thường", "Hư tổn" };
        cbTinhTrangTra = new JComboBox<>(dsTinhTrangTra);
        cbTinhTrangTra.setFont(new Font("Arial", Font.BOLD, 13));
        cbTinhTrangTra.setBounds(200, 135, 120, 30);
        cbTinhTrangTra.addActionListener(this);

        txMaPT = new JTextField();
        txMaPT.setBounds(200, 35, 150, 30);
        txMaPT.setFont(new Font("Arial", Font.PLAIN, 15));
        txMaPT.addMouseListener(this);

        txTienThue = new JTextField();
        txTienThue.setBounds(200, 188, 150, 30);
        txTienThue.setFont(new Font("Arial", Font.PLAIN, 15));
        txTienThue.addMouseListener(this);
        txTienThue.setEditable(false);
        txTienThue.setText(String.format("%,d",PHIEUTRASACH.Tienthue));

        txThanhTien = new JTextField();
        txThanhTien.setBounds(200, 235, 150, 30);
        txThanhTien.setFont(new Font("Arial", Font.PLAIN, 15));
        txThanhTien.addMouseListener(this);
        txThanhTien.setEditable(false);

        txMaPMTra = new JTextField();
        txMaPMTra.setBounds(200, 285, 150, 30);
        txMaPMTra.setFont(new Font("Arial", Font.PLAIN, 15));
        txMaPMTra.addMouseListener(this);

        // Set date picker1
        modelNgayBDPT = new UtilDateModel();
        modelNgayBDPT.setSelected(true);
        pNgayBDPT = new Properties();
        pNgayBDPT.put("text.today", "Today");
        pNgayBDPT.put("text.month", "Month");
        pNgayBDPT.put("text.year", "Year");
        datePanelNgayBDPT = new JDatePanelImpl(modelNgayBDPT, pNgayBDPT);
        datePickerNgayBDPT = new JDatePickerImpl(datePanelNgayBDPT, new DateLabelFormatter());
        datePickerNgayBDPT.setBounds(200, 85, 150, 30);

        pnNhapPT.add(lbMaPT);
        pnNhapPT.add(lbNgayTraPT);
        pnNhapPT.add(cbTinhTrangTra);
        pnNhapPT.add(lbTinhTrangSach);
        pnNhapPT.add(lbTienThue);
        pnNhapPT.add(lbThanhTien);
        pnNhapPT.add(lbMaPMTra);
        pnNhapPT.add(txMaPT);
        pnNhapPT.add(txTienThue);
        pnNhapPT.add(txThanhTien);
        pnNhapPT.add(txMaPMTra);

        pnNhapPT.add(datePickerNgayBDPT);
    }

    public void setTimKiemPM() {
        if (btTimKiemPM == null) { // Là button của phần tìm kiếm cơ bản
            if (lbLCTKPM != null || lbTuKhoaTKPM != null || cbDSKhoaTKPM != null || txKhoaTKPM != null) {
                lbLCTKPM.setVisible(true);
                lbTuKhoaTKPM.setVisible(true);
                cbDSKhoaTKPM.setVisible(true);
                txKhoaTKPM.setVisible(true);
>>>>>>> 2e9da2c8711846a07f27a86e09e310dd42ff5c5b
            }

            // labelLCTK
            lbLCTKPM = new JLabel("Lựa chọn khóa tìm kiếm:");
            lbLCTKPM.setFont(new Font("Arial", Font.BOLD, 20));
            lbLCTKPM.setBounds(10, 30, 250, 100);

            // labelTuKhoaTK
            lbTuKhoaTKPM = new JLabel("Nhập từ khóa tìm kiếm:");
            lbTuKhoaTKPM.setFont(new Font("Arial", Font.BOLD, 20));
            lbTuKhoaTKPM.setBounds(10, 80, 250, 100);

            // JTextField Khóa tìm kiếm
            txKhoaTKPM = new JTextField();
            txKhoaTKPM.setFont(new Font("Arial", Font.PLAIN, 15));
            txKhoaTKPM.setBounds(260, 115, 200, 35);

            String[] dsKhoaTK = { "", "Mã phiếu mượn", "SL tổng", "Tình trạng mượn", "Mã độc giả" };
            cbDSKhoaTKPM = new JComboBox<>(dsKhoaTK);
            cbDSKhoaTKPM.setFont(new Font("Arial", Font.BOLD, 13));
            cbDSKhoaTKPM.setBounds(260, 65, 120, 35);
            cbDSKhoaTKPM.addActionListener(this);

            TitledBorder titleTK;
            Border blackline;
            blackline = BorderFactory.createLineBorder(Color.black);
            titleTK = BorderFactory.createTitledBorder(blackline, "Tìm kiếm");
            titleTK.setTitleFont(new Font("Arial", Font.BOLD, 28));
            titleTK.setTitleJustification(TitledBorder.CENTER);
            pnTimKiemPM.setBorder(titleTK);

            btTimKiemPM = new JButton("Tìm kiếm");
            btTimKiemPM.setFont(new Font("Arial", Font.BOLD, 15));
            btTimKiemPM.setBounds(360, 155, 100, 35);
            btTimKiemPM.setBackground(Color.cyan);
            btTimKiemPM.setBorder(new RoundedBorder(10));
            btTimKiemPM.addActionListener(this);

        }
        pnTimKiemPM.add(lbLCTKPM);
        pnTimKiemPM.add(lbTuKhoaTKPM);
        pnTimKiemPM.add(txKhoaTKPM);
        pnTimKiemPM.add(cbDSKhoaTKPM);
        pnTimKiemPM.add(btTimKiemPM);
    }

<<<<<<< HEAD
=======
    public void setTimKiemPT() {
        if (btTimKiemPT == null) { // Là button của phần tìm kiếm cơ bản
            if (lbLCTKPT != null || lbTuKhoaTKPT != null || cbDSKhoaTKPT != null || txKhoaTKPT != null) {
                lbLCTKPT.setVisible(true);
                lbTuKhoaTKPT.setVisible(true);
                cbDSKhoaTKPT.setVisible(true);
                txKhoaTKPT.setVisible(true);
            }

            // labelLCTK
            lbLCTKPT = new JLabel("Lựa chọn khóa tìm kiếm:");
            lbLCTKPT.setFont(new Font("Arial", Font.BOLD, 20));
            lbLCTKPT.setBounds(10, 30, 250, 100);

            // labelTuKhoaTK
            lbTuKhoaTKPT = new JLabel("Nhập từ khóa tìm kiếm:");
            lbTuKhoaTKPT.setFont(new Font("Arial", Font.BOLD, 20));
            lbTuKhoaTKPT.setBounds(10, 80, 250, 100);

            // JTextField Khóa tìm kiếm
            txKhoaTKPT = new JTextField();
            txKhoaTKPT.setFont(new Font("Arial", Font.PLAIN, 15));
            txKhoaTKPT.setBounds(260, 115, 200, 35);

            String[] dsKhoaTK = { "", "Mã phiếu trả", "Ngày trả", "Tình trạng sách", "Tiền thuê", "Thành tiền", "Mã phiếu mượn"};
            cbDSKhoaTKPT = new JComboBox<>(dsKhoaTK);
            cbDSKhoaTKPT.setFont(new Font("Arial", Font.BOLD, 13));
            cbDSKhoaTKPT.setBounds(260, 65, 120, 35);
            cbDSKhoaTKPT.addActionListener(this);

            TitledBorder titleTK;
            Border blackline;
            blackline = BorderFactory.createLineBorder(Color.black);
            titleTK = BorderFactory.createTitledBorder(blackline, "Tìm kiếm");
            titleTK.setTitleFont(new Font("Arial", Font.BOLD, 28));
            titleTK.setTitleJustification(TitledBorder.CENTER);
            pnTimKiemPT.setBorder(titleTK);

            btTimKiemPT = new JButton("Tìm kiếm");
            btTimKiemPT.setFont(new Font("Arial", Font.BOLD, 15));
            btTimKiemPT.setBounds(360, 155, 100, 35);
            btTimKiemPT.setBackground(Color.cyan);
            btTimKiemPT.setBorder(new RoundedBorder(10));
            btTimKiemPT.addActionListener(this);

        }
        pnTimKiemPT.add(lbLCTKPT);
        pnTimKiemPT.add(lbTuKhoaTKPT);
        pnTimKiemPT.add(txKhoaTKPT);
        pnTimKiemPT.add(cbDSKhoaTKPT);
        pnTimKiemPT.add(btTimKiemPT);
    }

>>>>>>> 2e9da2c8711846a07f27a86e09e310dd42ff5c5b
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

        btLocPM = new JButton("Lọc");
        btLocPM.setFont(new Font("Arial", Font.BOLD, 15));
        btLocPM.setBounds(210, 115, 80, 30);
        btLocPM.setBackground(Color.cyan);
        btLocPM.setBorder(new RoundedBorder(10));
        btLocPM.addActionListener(this);

        // Set date picker1
        modelNgayBDMuon = new UtilDateModel();
        modelNgayBDMuon.setSelected(true);
        pNgayBDMuon = new Properties();
        pNgayBDMuon.put("text.today", "Today");
        pNgayBDMuon.put("text.month", "Month");
        pNgayBDMuon.put("text.year", "Year");
        datePanelNgayBDMuon = new JDatePanelImpl(modelNgayBDMuon, pNgayBDMuon);
        datePickerNgayBDMuon = new JDatePickerImpl(datePanelNgayBDMuon, new DateLabelFormatter());
        datePickerNgayBDMuon.setBounds(140, 40, 150, 30);

        // Set date picker1
        modelNgayKTMuon = new UtilDateModel();
        modelNgayKTMuon.setSelected(true);
        pNgayKTMuon = new Properties();
        pNgayKTMuon.put("text.today", "Today");
        pNgayKTMuon.put("text.month", "Month");
        pNgayKTMuon.put("text.year", "Year");
        datePanelNgayKTMuon = new JDatePanelImpl(modelNgayKTMuon, pNgayKTMuon);
        datePickerNgayKTMuon = new JDatePickerImpl(datePanelNgayKTMuon, new DateLabelFormatter());
        datePickerNgayKTMuon.setBounds(140, 80, 150, 30);

        pnLocPM.setBorder(titleLoc);
        pnLocPM.add(lbNgayBD);
        pnLocPM.add(lbNgayKT);
        pnLocPM.add(btLocPM);
        pnLocPM.add(datePickerNgayBDMuon);
        pnLocPM.add(datePickerNgayKTMuon);
    }

    public void setLocPT(){
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

        btLocPM = new JButton("Lọc");
        btLocPM.setFont(new Font("Arial", Font.BOLD, 15));
        btLocPM.setBounds(210, 115, 80, 30);
        btLocPM.setBackground(Color.cyan);
        btLocPM.setBorder(new RoundedBorder(10));
        btLocPM.addActionListener(this);

        // Set date picker1
        modelNgayBDTra = new UtilDateModel();
        modelNgayBDTra.setSelected(true);
        pNgayBDTra = new Properties();
        pNgayBDTra.put("text.today", "Today");
        pNgayBDTra.put("text.month", "Month");
        pNgayBDTra.put("text.year", "Year");
        datePanelNgayBDTra = new JDatePanelImpl(modelNgayBDTra, pNgayBDTra);
        datePickerNgayBDTra = new JDatePickerImpl(datePanelNgayBDTra, new DateLabelFormatter());
        datePickerNgayBDTra.setBounds(140, 40, 150, 30);

        // Set date picker1
        modelNgayKTTra = new UtilDateModel();
        modelNgayKTTra.setSelected(true);
        pNgayKTTra = new Properties();
        pNgayKTTra.put("text.today", "Today");
        pNgayKTTra.put("text.month", "Month");
        pNgayKTTra.put("text.year", "Year");
        datePanelNgayKTTra = new JDatePanelImpl(modelNgayKTTra, pNgayKTTra);
        datePickerNgayKTTra = new JDatePickerImpl(datePanelNgayKTTra, new DateLabelFormatter());
        datePickerNgayKTTra.setBounds(140, 80, 150, 30);

        pnLocPT.setBorder(titleLoc);
        pnLocPT.add(lbNgayBD);
        pnLocPT.add(lbNgayKT);
        pnLocPT.add(btLocPM);
        pnLocPT.add(datePickerNgayBDTra);
        pnLocPT.add(datePickerNgayKTTra);
    }

    public void setValueCellCenter(DefaultTableModel model, JTable table) {
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        for (int i = 0; i < model.getColumnCount(); i++) {
            table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }
    }

    public void ShowOnTable(PHIEUMUON phieumuon) {
        Vector<String> row = new Vector<String>();
        row.add(phieumuon.getMaPM().trim());
        row.add(phieumuon.getNgaymuon().trim());
        row.add(String.valueOf(phieumuon.getSLtong()));
        row.add(phieumuon.getNgaytra().trim());
        row.add(phieumuon.getMaDG().trim());
        modelMuon.addRow(row);
    }

    public void getInfoTextField(PHIEUMUON phieumuon) {
        phieumuon.setMaPM(txMaPM.getText());
        phieumuon.setNgaymuon(datePickerNgayBDPM.getJFormattedTextField().getText());
        phieumuon.setSLtong(Integer.parseInt(txSLtong.getText()));
        phieumuon.setNgaytra(datePickerNgayKTPM.getJFormattedTextField().getText());
        String TinhTrangMuon = (String) cbTinhTrangMuon.getSelectedItem(); 
        phieumuon.setTinhTrangMuon(TinhTrangMuon);
        phieumuon.setMaDG(txMaDG.getText());
        // String tmpDonGia = RemoveCommaInString(txDongia);
        // phieumuon.setDongia(Integer.parseInt(tmpDonGia));
    }

}

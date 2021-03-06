package QLTV.GUI;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import MyCustom.DateLabelFormatter;
import MyCustom.HoTroNhap;
import MyCustom.MyTable;
import MyCustom.RoundedBorder;
import MyCustom.MyColor;
import QLTV.BUS.QLCTPNBUS;
import QLTV.BUS.QLPNBUS;
import QLTV.BUS.QLSACHBUS;
import QLTV.DTO.CHITIETPHIEUNHAP;
import QLTV.DTO.PHIEUNHAP;
import QLTV.DTO.SACH;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Properties;
import java.util.Vector;

public class QLPNGUI implements ActionListener, MouseListener {
    public static int DonGiaCTPN = 0;
    JPanel pnPhieuNhap, pnNhapPhieu, pnNhapPN, pnPN, pnCTPN, pnTimKiemPN, pnLocPN, pnShowAll;

    JLabel lbNhapPN, lbNhapCTPN, lbMaPN, lbNgayNhap, lbSLTong, lbDonGia, lbMaNV, lbMaNCC, lbCTPNMaPN, lbCTPNMaSach,
            lbCTPNSL,
            lbLCTKPN, lbTuKhoaTKPN, lbNgayBDLocPN, lbNgayKTLocPN;

    JTextField txMaPN, txSLTong, txDonGia, txCTPNSL, txKhoaTKPN;
    public static JTextField txCTPNMaSach;

    public static JTextField txCTPNMaPN, txMaNCC, txMaNV;
    JButton btLocPN, btTimKiemPN, btThemPN, btSuaPN, btThemCTPN, btSuaCTPN, btShowAll,
            btHoTroNhapMaNV, btHoTroNhapMaNCC, btHoTroNhapMasachPN, btHoTroNhapMaPN;
    TitledBorder titlePN;

    JComboBox<String> cbDSKhoaTKPN;

    JTable tblQLPN, tblQLCTPN;
    DefaultTableModel modelPN, modelCTPN;

    UtilDateModel modelNgayBDPN;
    Properties pNgayBDPN;
    JDatePanelImpl datePanelNgayBDPN;
    JDatePickerImpl datePickerNgayBDPN;

    UtilDateModel modelNgayBDNhap, modelNgayKTNhap;
    Properties pNgayBDNhap, pNgayKTNhap;
    JDatePanelImpl datePanelNgayBDNhap, datePanelNgayKTNhap;
    JDatePickerImpl datePickerNgayBDNhap, datePickerNgayKTNhap;

    Vector<String> header;

    public QLPNGUI() {

    }

    MyTable myTable = new MyTable();

    public JPanel setPNGUI() {
        if (pnPhieuNhap == null) {
            pnPhieuNhap = new JPanel();
            pnPhieuNhap.setBounds(240, 0, 1145, 800);
            pnPhieuNhap.setLayout(null);
            pnPhieuNhap.setBackground(MyColor.ColorBlue);

            // panel tab nh???p phi???u
            pnNhapPhieu = new JPanel();
            pnNhapPhieu.setBounds(5, 5, 1138, 350);
            pnNhapPhieu.setLayout(new GridLayout(1, 2, 5, 0));
            pnNhapPhieu.setBackground(MyColor.ColorBlue);

            pnPN = new JPanel();
            pnPN.setLayout(new GridLayout(1, 1));
            pnPN.setBackground(MyColor.ColorBlue);

            pnCTPN = new JPanel();
            pnCTPN.setLayout(new GridLayout(1, 1));
            pnCTPN.setBackground(MyColor.ColorBlue);

            pnShowAll = new JPanel();
            pnShowAll.setBounds(5, 353, 1135, 40);
            pnShowAll.setLayout(null);
            pnShowAll.setBackground(MyColor.ColorBlue);

            pnNhapPN = new JPanel();
            pnNhapPN.setLayout(null);
            pnNhapPN.setBounds(5, 395, 1135, 380);
            pnNhapPN.setBackground(MyColor.ColorBlue);

            pnTimKiemPN = new JPanel();
            pnTimKiemPN.setLayout(null);
            pnTimKiemPN.setBounds(720, 155, 413, 200);
            pnTimKiemPN.setBackground(MyColor.ColorBlue);

            pnLocPN = new JPanel();
            pnLocPN.setLayout(null);
            pnLocPN.setBounds(720, 0, 300, 155);
            pnLocPN.setBackground(MyColor.ColorBlue);

            pnPhieuNhap.add(pnNhapPhieu);
            pnPhieuNhap.add(pnNhapPN);
            pnPhieuNhap.add(pnShowAll);

            pnNhapPhieu.add(pnPN);
            pnNhapPhieu.add(pnCTPN);

            pnNhapPN.add(pnTimKiemPN);
            pnNhapPN.add(pnLocPN);

            setTitlePN();
            setTablePN();
            setTableCTPN();
            setShowAll();
            setInputNhap();
            setInputCTPN();
            setTimKiemPN();
            setLocPN();
            getDBPhieuNhap();
            getDBCTPN();
            myTable.setValueCellCenter(modelPN, tblQLPN);
            myTable.setValueCellCenter(modelCTPN, tblQLCTPN);
        }
        return pnPhieuNhap;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btTimKiemPN) {
            int vtkey = Integer.parseInt(String.valueOf(cbDSKhoaTKPN.getSelectedIndex()));
            String tukhoa = txKhoaTKPN.getText().replaceAll("\\s+", "").toLowerCase().trim();
            if (tukhoa.equals("") == true) {
                JOptionPane.showMessageDialog(null, "Xin m???i nh???p t??? kh??a", "L???i", JOptionPane.ERROR_MESSAGE);
            } else if (vtkey == 0) {
                JOptionPane.showMessageDialog(null, "Xin m???i l???a ch???n kh??a t??m ki???m", "L???i", JOptionPane.ERROR_MESSAGE);
            } else {
                QLPNBUS qlnhapbus = new QLPNBUS();
                if (vtkey == 1) {
                    PHIEUNHAP kq = qlnhapbus.timTheoMa(tukhoa);
                    modelPN.setRowCount(0);
                    if (kq != null) {
                        ShowOnTablePN(kq);
                        tblQLPN.setModel(modelPN);
                    } else {
                        JOptionPane.showMessageDialog(null, "Kh??ng t??m th???y d??? li???u ph?? h???p", "L???i",
                                JOptionPane.ERROR_MESSAGE);
                    }
                }
                if (vtkey == 2) {
                    ArrayList<PHIEUNHAP> kq = qlnhapbus.timTheoSLtong(tukhoa);
                    modelPN.setRowCount(0);
                    if (kq.size() == 0) {
                        JOptionPane.showMessageDialog(null, "Kh??ng t??m th???y d??? li???u ph?? h???p", "L???i",
                                JOptionPane.ERROR_MESSAGE);
                    } else {
                        for (PHIEUNHAP pn : kq) {
                            ShowOnTablePN(pn);
                        }
                        tblQLPN.setModel(modelPN);
                    }
                }
                if (vtkey == 3) {
                    ArrayList<PHIEUNHAP> kq = qlnhapbus.timTheoDonGia(tukhoa);
                    modelPN.setRowCount(0);
                    if (kq.size() == 0) {
                        JOptionPane.showMessageDialog(null, "Kh??ng t??m th???y d??? li???u ph?? h???p", "L???i",
                                JOptionPane.ERROR_MESSAGE);
                    } else {
                        for (PHIEUNHAP pn : kq) {
                            ShowOnTablePN(pn);
                        }
                        tblQLPN.setModel(modelPN);
                    }
                }
                if (vtkey == 4) {
                    ArrayList<PHIEUNHAP> kq = qlnhapbus.timTheoMaNV(tukhoa);
                    modelPN.setRowCount(0);
                    if (kq.size() == 0) {
                        JOptionPane.showMessageDialog(null, "Kh??ng t??m th???y d??? li???u ph?? h???p", "L???i",
                                JOptionPane.ERROR_MESSAGE);
                    } else {
                        for (PHIEUNHAP pn : kq) {
                            ShowOnTablePN(pn);
                        }
                        tblQLPN.setModel(modelPN);
                    }
                }
                if (vtkey == 5) {
                    ArrayList<PHIEUNHAP> kq = qlnhapbus.timTheoMaNCC(tukhoa);
                    modelPN.setRowCount(0);
                    if (kq.size() == 0) {
                        JOptionPane.showMessageDialog(null, "Kh??ng t??m th???y d??? li???u ph?? h???p", "L???i",
                                JOptionPane.ERROR_MESSAGE);
                    } else {
                        for (PHIEUNHAP pn : kq) {
                            ShowOnTablePN(pn);
                        }
                        tblQLPN.setModel(modelPN);
                    }
                }
            }
        }
        if (e.getSource() == btLocPN) {
            String tmp = datePickerNgayBDNhap.getJFormattedTextField().getText().replaceAll("-", "");
            String tmp1 = datePickerNgayKTNhap.getJFormattedTextField().getText().replaceAll("-", "");
            int BDNhap = Integer.parseInt(tmp);
            int KTNhap = Integer.parseInt(tmp1);
            QLPNBUS qlnhapbus = new QLPNBUS();
            ArrayList<PHIEUNHAP> kq = qlnhapbus.LocPM(BDNhap, KTNhap);
            modelPN.setRowCount(0);
            if (kq.size() == 0) {
                JOptionPane.showMessageDialog(null, "K???t qu??? l???c kh??ng th???a ??i???u ki???n", "L???i",
                        JOptionPane.ERROR_MESSAGE);
            } else {
                for (PHIEUNHAP pn : kq) {
                    ShowOnTablePN(pn);
                }
                tblQLPN.setModel(modelPN);
                JOptionPane.showMessageDialog(null,
                        "K???t qu??? l???c: " + modelPN.getRowCount() + " Phi???u m?????n c?? ng??y m?????n th???a!");
            }
        }
        if (e.getSource() == btShowAll) {
            modelPN.setRowCount(0);
            for (PHIEUNHAP pn : QLPNBUS.dspn) {
                ShowOnTablePN(pn);
            }
            tblQLPN.setModel(modelPN);
        }
        if (e.getSource() == btThemPN) {
            try {
                PHIEUNHAP phieunhap = new PHIEUNHAP();
                getInfoTextFieldPN(phieunhap);
                // Truy c???p v??o bus
                QLPNBUS qlphieunhapbus = new QLPNBUS();
                int kiemtra = -1;
                try {
                    kiemtra = qlphieunhapbus.them(phieunhap);
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
                if (kiemtra == 0) {
                    // ????a d??? li???u l??n table
                    header = new Vector<String>();
                    header.add("M?? phi???u nh???p");
                    header.add("Ng??y nh???p");
                    header.add("SL t???ng");
                    header.add("????n gi??");
                    header.add("M?? nh??n vi??n");
                    header.add("M?? nh?? cung c???p");
                    if (modelPN.getRowCount() == 0) {
                        modelPN = new DefaultTableModel(header, 0);
                    }
                    ShowOnTablePN(phieunhap);
                    tblQLPN.setModel(modelPN);
                }
            } catch (Exception e1) {
                System.out.println(e1);
            }
        }
        if (e.getSource() == btSuaPN) {
            int i = tblQLPN.getSelectedRow();
            int kt = -1;
            if (i >= 0) {
                PHIEUNHAP phieunhap = new PHIEUNHAP();
                PHIEUNHAP MaPNCu = QLPNBUS.dspn.get(i);
                getInfoTextFieldPN(phieunhap);

                QLPNBUS qlphieunhapbus = new QLPNBUS();
                try {
                    kt = qlphieunhapbus.sua(phieunhap, MaPNCu, i);
                } catch (Exception e1) {
                    System.out.println(e1);
                }
                if (kt == 0) {
                    modelPN.setValueAt(phieunhap.getMaPN(), i, 0);
                    modelPN.setValueAt(phieunhap.getNgaynhap(), i, 1);
                    modelPN.setValueAt(phieunhap.getSLTong(), i, 2);
                    modelPN.setValueAt(phieunhap.getDongia(), i, 3);
                    modelPN.setValueAt(phieunhap.getMaNV(), i, 4);
                    modelPN.setValueAt(phieunhap.getMaNCC(), i, 5);
                    tblQLPN.setModel(modelPN);
                }
            }
        }
        if (e.getSource() == btThemCTPN) {
            int i = tblQLPN.getSelectedRow();
            try {
                CHITIETPHIEUNHAP ctphieunhap = new CHITIETPHIEUNHAP();
                getInfoTextFieldCTPN(ctphieunhap);
                if (!ctphieunhap.getMaPN().trim().equals(String.valueOf(modelPN.getValueAt(i, 0)))) {
                    JOptionPane.showMessageDialog(null, "M?? phi???u nh???p t???i b???ng chi ti???t kh??c v???i m?? phi???u nh???p", "L???i",
                            JOptionPane.ERROR_MESSAGE);
                } else {
                    // Truy c???p v??o bus
                    QLCTPNBUS qlctphieunhapbus = new QLCTPNBUS();
                    int kiemtra = 0;
                    try {
                        kiemtra = qlctphieunhapbus.them(ctphieunhap);
                    } catch (Exception e1) {
                        e1.printStackTrace();
                    }
                    if (kiemtra == 0) {
                        updateSLSachNhap(txCTPNMaSach.getText().trim());
                        updateThanhTien(ctphieunhap.getMaPN().trim(), ctphieunhap.getSL() * DonGiaCTPN);
                        // ????a d??? li???u l??n table
                        header = new Vector<String>();
                        header.add("M?? phi???u nh???p");
                        header.add("M?? s??ch");
                        header.add("SL");
                        if (modelCTPN.getRowCount() == 0) {
                            modelCTPN = new DefaultTableModel(header, 0);
                        }
                        ShowOnTableCTPN(ctphieunhap);
                        tblQLCTPN.setModel(modelCTPN);
                    }
                }
            } catch (Exception e1) {
                System.out.println(e1);
            }
        }
        if (e.getSource() == btSuaCTPN) {
            int i = tblQLCTPN.getSelectedRow();
            int kt = -1;
            if (i >= 0) {
                CHITIETPHIEUNHAP ctphieunhap = new CHITIETPHIEUNHAP();
                String MaCTPNCu = String.valueOf(modelCTPN.getValueAt(i, 0));
                String MaSachCTPNCu = String.valueOf(modelCTPN.getValueAt(i, 1));
                getInfoTextFieldCTPN(ctphieunhap);
                try {
                    QLCTPNBUS qlctphieutrabus = new QLCTPNBUS();
                    kt = qlctphieutrabus.sua(ctphieunhap, MaCTPNCu, MaSachCTPNCu, i);
                } catch (Exception e1) {
                    System.out.println(e1);
                }
                if (kt == 0) {
                    modelCTPN.setValueAt(ctphieunhap.getMaPN(), i, 0);
                    modelCTPN.setValueAt(ctphieunhap.getMasach(), i, 1);
                    modelCTPN.setValueAt(ctphieunhap.getSL(), i, 2);
                    tblQLCTPN.setModel(modelCTPN);
                }
            }
        }
        if (e.getSource() == btHoTroNhapMaPN) {
            HoTroNhap hoTroNhapMaPN = new HoTroNhap();
            hoTroNhapMaPN.setHoTroNhapMaPN();
        }
        if (e.getSource() == btHoTroNhapMaNV) {
            HoTroNhap hoTroNhapMaNV = new HoTroNhap();
            hoTroNhapMaNV.setHoTroNhapMaNV();
        }
        if (e.getSource() == btHoTroNhapMaNCC) {
            HoTroNhap hoTroNhapMaNCC = new HoTroNhap();
            hoTroNhapMaNCC.setHoTroNhapMaNCC();
        }
        if (e.getSource() == btHoTroNhapMasachPN) {
            HoTroNhap hoTroNhapMaSach = new HoTroNhap();
            hoTroNhapMaSach.setHoTroNhapMasach();
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource() == tblQLPN) {
            int i = tblQLPN.getSelectedRow();
            if (i >= 0) {
                // Hi???n th??? b??n CTPM
                modelCTPN.setRowCount(0);
                ArrayList<CHITIETPHIEUNHAP> kq = new ArrayList<CHITIETPHIEUNHAP>();
                PHIEUNHAP pm = QLPNBUS.dspn.get(i);
                for (CHITIETPHIEUNHAP ctpn : QLCTPNBUS.dsctpn) {
                    if (ctpn.getMaPN().indexOf(pm.getMaPN()) >= 0) {
                        kq.add(ctpn); // Ch???a ph???n t??? c???a ctpm th???a m?? pm
                    }
                }
                for (CHITIETPHIEUNHAP ctpn : kq) {
                    ShowOnTableCTPN(ctpn);
                }
                tblQLCTPN.setModel(modelCTPN);
                // Hi???n th??? tr??n textfield
                PHIEUNHAP pnTextField = new PHIEUNHAP();
                pnTextField = QLPNBUS.dspn.get(i);
                txMaPN.setText(pnTextField.getMaPN().trim());

                String tmp[] = pnTextField.getNgaynhap().split("-");
                datePanelNgayBDPN.getModel().setDate(Integer.parseInt(tmp[0]), Integer.parseInt(tmp[1]),
                        Integer.parseInt(tmp[2]));

                txSLTong.setText(String.valueOf(pnTextField.getSLTong()));
                txDonGia.setText(String.format("%,d", pnTextField.getDongia()));
                txMaNV.setText(pnTextField.getMaNV().trim());
                txMaNCC.setText(pnTextField.getMaNCC().trim());
            }
        }
        if (e.getSource() == tblQLCTPN) {
            int i = tblQLCTPN.getSelectedRow();
            if (i >= 0) {
                String MaPM = String.valueOf(modelCTPN.getValueAt(i, 0));
                String Masach = String.valueOf(modelCTPN.getValueAt(i, 1));
                String SL = String.valueOf(modelCTPN.getValueAt(i, 2));
                txCTPNMaPN.setText(MaPM);
                txCTPNMaSach.setText(Masach);
                txCTPNSL.setText(SL);
            }
        }

        if (e.getSource() == txDonGia) {
            if (DonGiaCTPN == 0) {
                JOptionPane.showMessageDialog(null, "Thi???u ????n gi??. M???i nh???p m?? s??ch b??n chi ti???t ????? t??nh th??nh ti???n!",
                        "L???i",
                        JOptionPane.ERROR_MESSAGE);
            } else if (txCTPNSL.getText().equals("")) {
                JOptionPane.showMessageDialog(null,
                        "Thi???u s??? l?????ng. M???i nh???p s??? l?????ng b??n chi ti???t ????? t??nh th??nh ti???n!", "L???i",
                        JOptionPane.ERROR_MESSAGE);
            } else {
                int thanhtien = Integer.parseInt(txCTPNSL.getText()) * DonGiaCTPN;
                txDonGia.setText(String.format("%,d", thanhtien));
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

    public void setTitlePN() {
        // set Border
        Border empty;
        empty = BorderFactory.createEmptyBorder();

        titlePN = BorderFactory.createTitledBorder(empty, "TH??NG TIN PHI???U NH???P");
        titlePN.setTitleFont(new Font("Arial", Font.BOLD, 28));
        titlePN.setTitleJustification(TitledBorder.CENTER);
        pnPN.setBorder(titlePN);

        titlePN = BorderFactory.createTitledBorder(empty, "CHI TI???T PHI???U NH???P");
        titlePN.setTitleFont(new Font("Arial", Font.BOLD, 28));
        titlePN.setTitleJustification(TitledBorder.CENTER);
        pnCTPN.setBorder(titlePN);
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
            header.add("M?? phi???u nh???p");
            header.add("Ng??y nh???p");
            header.add("SL t???ng");
            header.add("Th??nh ti???n");
            header.add("M?? nh??n vi??n");
            header.add("M?? nh?? cung c???p");
            modelPN = new DefaultTableModel(header, 0);
            for (PHIEUNHAP pn : QLPNBUS.dspn) {
                ShowOnTablePN(pn);
            }
            tblQLPN.setModel(modelPN);
        } catch (Exception e1) {
            System.out.println(e1);
        }
    }

    public void getDBCTPN() {
        try {
            QLCTPNBUS qlCTPNbus = new QLCTPNBUS();
            if (QLCTPNBUS.dsctpn == null)
                try {
                    qlCTPNbus.docDSCTPN();
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            // set up header(column)
            Vector<String> header = new Vector<String>();
            header.add("M?? phi???u nh???p");
            header.add("M?? s??ch");
            header.add("SL");
            modelCTPN = new DefaultTableModel(header, 0);
            tblQLCTPN.setModel(modelCTPN);
        } catch (Exception e1) {
            System.out.println(e1);
        }
    }

    public void ShowOnTablePN(PHIEUNHAP pn) {
        Vector<String> row = new Vector<String>();
        row.add(pn.getMaPN().replaceAll("\\s+", "").trim());
        row.add(pn.getNgaynhap().replaceAll("\\s+", "").trim());
        row.add(String.valueOf(pn.getSLTong()).replaceAll("\\s+", "").trim());
        row.add(String.format("%,d", pn.getDongia()).replaceAll("\\s+", "").trim());
        row.add(pn.getMaNV().replaceAll("\\s+", "").trim());
        row.add(pn.getMaNCC().replaceAll("\\s+", "").trim());
        modelPN.addRow(row);
    }

    public void ShowOnTableCTPN(CHITIETPHIEUNHAP ctpn) {
        Vector<String> row = new Vector<String>();
        row.add(ctpn.getMaPN().replaceAll("\\s+", "").trim());
        row.add(ctpn.getMasach().replaceAll("\\s+", "").trim());
        row.add(String.valueOf(ctpn.getSL()).replaceAll("\\s+", "").trim());
        modelCTPN.addRow(row);
    }

    public void setShowAll() {
        btShowAll = new JButton("Hi???n th??? t???t c???");
        btShowAll.setFont(new Font("Arial", Font.BOLD, 15));
        btShowAll.setBounds(1000, 5, 130, 30);
        btShowAll.setBackground(MyColor.ColorButton);
        btShowAll.setBorder(new RoundedBorder(10));
        btShowAll.addActionListener(this);

        pnShowAll.add(btShowAll);
    }

    public void setTablePN() {
        // ----set up table----
        tblQLPN = new JTable();
        JScrollPane pane = new JScrollPane(tblQLPN);
        pane.setAutoscrolls(true);
        tblQLPN.setRowHeight(20);
        tblQLPN.setFont(new Font(null, 0, 13));
        tblQLPN.setBackground(MyColor.ColorLightGray);
        tblQLPN.addMouseListener(this);
        tblQLPN.setDefaultEditor(Object.class, null);
        tblQLPN.setSelectionBackground(MyColor.Color);
        tblQLPN.getTableHeader().setBackground(MyColor.ColorSilver);
        pnPN.add(pane);
    }

    public void setTableCTPN() {
        // ----set up table----
        tblQLCTPN = new JTable();
        JScrollPane pane = new JScrollPane(tblQLCTPN);
        pane.setAutoscrolls(true);
        tblQLCTPN.setRowHeight(20);
        tblQLCTPN.setFont(new Font(null, 0, 13));
        tblQLCTPN.setBackground(MyColor.ColorLightGray);
        tblQLCTPN.addMouseListener(this);
        tblQLCTPN.setDefaultEditor(Object.class, null);
        tblQLCTPN.setSelectionBackground(MyColor.Color2);
        tblQLCTPN.getTableHeader().setBackground(MyColor.ColorSilver);
        pnCTPN.add(pane);
    }

    public void setInputNhap() {
        lbNhapPN = new JLabel("Phi???u Nh???p");
        lbNhapPN.setFont(new Font("Arial", Font.BOLD, 20));
        lbNhapPN.setBounds(100, 0, 180, 80);

        lbMaPN = new JLabel("M?? phi???u nh???p:");
        lbMaPN.setFont(new Font("Arial", Font.BOLD, 18));
        lbMaPN.setBounds(0, 40, 150, 80);

        lbNgayNhap = new JLabel("Ng??y nh???p:");
        lbNgayNhap.setFont(new Font("Arial", Font.BOLD, 18));
        lbNgayNhap.setBounds(0, 85, 150, 80);

        lbSLTong = new JLabel("S??? l?????ng t???ng:");
        lbSLTong.setFont(new Font("Arial", Font.BOLD, 18));
        lbSLTong.setBounds(0, 130, 170, 80);

        lbDonGia = new JLabel("Th??nh ti???n:");
        lbDonGia.setFont(new Font("Arial", Font.BOLD, 18));
        lbDonGia.setBounds(0, 175, 150, 80);

        lbMaNV = new JLabel("M?? nh??n vi??n:");
        lbMaNV.setFont(new Font("Arial", Font.BOLD, 18));
        lbMaNV.setBounds(0, 220, 150, 80);

        lbMaNCC = new JLabel("M?? NCC:");
        lbMaNCC.setFont(new Font("Arial", Font.BOLD, 18));
        lbMaNCC.setBounds(0, 265, 150, 80);

        txMaPN = new JTextField();
        txMaPN.setBounds(175, 65, 150, 30);
        txMaPN.setFont(new Font("Arial", Font.PLAIN, 15));
        txMaPN.addMouseListener(this);

        txSLTong = new JTextField();
        txSLTong.setBounds(175, 155, 150, 30);
        txSLTong.setFont(new Font("Arial", Font.PLAIN, 15));
        txSLTong.addMouseListener(this);

        txDonGia = new JTextField("0");
        txDonGia.setBounds(175, 200, 150, 30);
        txDonGia.setFont(new Font("Arial", Font.PLAIN, 15));
        txDonGia.addMouseListener(this);
        txDonGia.setEditable(false);

        txMaNV = new JTextField();
        txMaNV.setBounds(175, 245, 108, 30);
        txMaNV.setFont(new Font("Arial", Font.PLAIN, 15));
        txMaNV.addMouseListener(this);
        txMaNV.setEditable(false);

        txMaNCC = new JTextField();
        txMaNCC.setBounds(175, 290, 108, 30);
        txMaNCC.setFont(new Font("Arial", Font.PLAIN, 15));
        txMaNCC.addMouseListener(this);
        txMaNCC.setEditable(false);

        // Set date picker1
        modelNgayBDPN = new UtilDateModel();
        modelNgayBDPN.setSelected(true);
        pNgayBDPN = new Properties();
        pNgayBDPN.put("text.today", "Today");
        pNgayBDPN.put("text.month", "Month");
        pNgayBDPN.put("text.year", "Year");
        datePanelNgayBDPN = new JDatePanelImpl(modelNgayBDPN, pNgayBDPN);
        datePickerNgayBDPN = new JDatePickerImpl(datePanelNgayBDPN, new DateLabelFormatter());
        datePickerNgayBDPN.setBounds(175, 110, 150, 30);
        datePickerNgayBDPN.addMouseListener(this);

        btThemPN = new JButton("Th??m");
        btThemPN.setFont(new Font("Arial", Font.BOLD, 15));
        btThemPN.setBounds(95, 330, 80, 30);
        btThemPN.setBackground(MyColor.ColorButton);
        btThemPN.setBorder(new RoundedBorder(10));
        btThemPN.addActionListener(this);

        btSuaPN = new JButton("S???a");
        btSuaPN.setFont(new Font("Arial", Font.BOLD, 15));
        btSuaPN.setBounds(245, 330, 80, 30);
        btSuaPN.setBackground(MyColor.ColorButton);
        btSuaPN.setBorder(new RoundedBorder(10));
        btSuaPN.addActionListener(this);

        btHoTroNhapMaNV = new JButton("...");
        btHoTroNhapMaNV.setFont(new Font("Arial", Font.BOLD, 15));
        btHoTroNhapMaNV.setBounds(285, 245, 40, 30);
        btHoTroNhapMaNV.setBackground(MyColor.ColorButton);
        btHoTroNhapMaNV.setBorder(new RoundedBorder(10));
        btHoTroNhapMaNV.addActionListener(this);

        btHoTroNhapMaNCC = new JButton("...");
        btHoTroNhapMaNCC.setFont(new Font("Arial", Font.BOLD, 15));
        btHoTroNhapMaNCC.setBounds(285, 290, 40, 30);
        btHoTroNhapMaNCC.setBackground(MyColor.ColorButton);
        btHoTroNhapMaNCC.setBorder(new RoundedBorder(10));
        btHoTroNhapMaNCC.addActionListener(this);

        pnNhapPN.add(lbNhapPN);
        pnNhapPN.add(lbMaPN);
        pnNhapPN.add(lbNgayNhap);
        pnNhapPN.add(lbSLTong);
        pnNhapPN.add(lbDonGia);
        pnNhapPN.add(lbMaNV);
        pnNhapPN.add(lbMaNCC);

        pnNhapPN.add(txMaPN);
        pnNhapPN.add(txSLTong);
        pnNhapPN.add(txDonGia);
        pnNhapPN.add(txMaNV);
        pnNhapPN.add(txMaNCC);

        pnNhapPN.add(datePickerNgayBDPN);
        pnNhapPN.add(btThemPN);
        pnNhapPN.add(btSuaPN);
        pnNhapPN.add(btHoTroNhapMaNV);
        pnNhapPN.add(btHoTroNhapMaNCC);
    }

    public void setInputCTPN() {
        lbNhapCTPN = new JLabel("CT Phi???u Nh???p");
        lbNhapCTPN.setFont(new Font("Arial", Font.BOLD, 20));
        lbNhapCTPN.setBounds(460, 0, 200, 80);

        lbCTPNMaPN = new JLabel("M?? Phi???u Nh???p:");
        lbCTPNMaPN.setFont(new Font("Arial", Font.BOLD, 18));
        lbCTPNMaPN.setBounds(355, 40, 150, 80);

        lbCTPNMaSach = new JLabel("M?? S??ch:");
        lbCTPNMaSach.setFont(new Font("Arial", Font.BOLD, 18));
        lbCTPNMaSach.setBounds(355, 85, 150, 80);

        lbCTPNSL = new JLabel("S??? l?????ng:");
        lbCTPNSL.setFont(new Font("Arial", Font.BOLD, 18));
        lbCTPNSL.setBounds(355, 130, 150, 80);

        txCTPNMaPN = new JTextField();
        txCTPNMaPN.setBounds(515, 65, 130, 30);
        txCTPNMaPN.setFont(new Font("Arial", Font.PLAIN, 15));
        txCTPNMaPN.setEditable(false);

        txCTPNMaSach = new JTextField();
        txCTPNMaSach.setBounds(515, 110, 130, 30);
        txCTPNMaSach.setFont(new Font("Arial", Font.PLAIN, 15));
        txCTPNMaSach.setEditable(false);

        txCTPNSL = new JTextField();
        txCTPNSL.setBounds(515, 155, 130, 30);
        txCTPNSL.setFont(new Font("Arial", Font.PLAIN, 15));

        btThemCTPN = new JButton("Th??m");
        btThemCTPN.setFont(new Font("Arial", Font.BOLD, 15));
        btThemCTPN.setBounds(390, 200, 80, 30);
        btThemCTPN.setBackground(MyColor.ColorButton);
        btThemCTPN.setBorder(new RoundedBorder(10));
        btThemCTPN.addActionListener(this);

        btSuaCTPN = new JButton("S???a");
        btSuaCTPN.setFont(new Font("Arial", Font.BOLD, 15));
        btSuaCTPN.setBounds(550, 200, 80, 30);
        btSuaCTPN.setBackground(MyColor.ColorButton);
        btSuaCTPN.setBorder(new RoundedBorder(10));
        btSuaCTPN.addActionListener(this);

        btHoTroNhapMaPN = new JButton("...");
        btHoTroNhapMaPN.setFont(new Font("Arial", Font.BOLD, 15));
        btHoTroNhapMaPN.setBounds(660, 65, 40, 30);
        btHoTroNhapMaPN.setBackground(MyColor.ColorButton);
        btHoTroNhapMaPN.setBorder(new RoundedBorder(10));
        btHoTroNhapMaPN.addActionListener(this);

        btHoTroNhapMasachPN = new JButton("...");
        btHoTroNhapMasachPN.setFont(new Font("Arial", Font.BOLD, 15));
        btHoTroNhapMasachPN.setBounds(660, 110, 40, 30);
        btHoTroNhapMasachPN.setBackground(MyColor.ColorButton);
        btHoTroNhapMasachPN.setBorder(new RoundedBorder(10));
        btHoTroNhapMasachPN.addActionListener(this);

        pnNhapPN.add(lbNhapCTPN);
        pnNhapPN.add(lbCTPNMaPN);
        pnNhapPN.add(lbCTPNMaSach);
        pnNhapPN.add(lbCTPNSL);

        pnNhapPN.add(txCTPNMaPN);
        pnNhapPN.add(txCTPNMaSach);
        pnNhapPN.add(txCTPNSL);

        pnNhapPN.add(btThemCTPN);
        pnNhapPN.add(btSuaCTPN);
        pnNhapPN.add(btHoTroNhapMaPN);
        pnNhapPN.add(btHoTroNhapMasachPN);
    }

    public void setTimKiemPN() {
        if (btTimKiemPN == null) { // L?? button c???a ph???n t??m ki???m c?? b???n
            if (lbLCTKPN != null || lbTuKhoaTKPN != null || cbDSKhoaTKPN != null || txKhoaTKPN != null) {
                lbLCTKPN.setVisible(true);
                lbTuKhoaTKPN.setVisible(true);
                cbDSKhoaTKPN.setVisible(true);
                txKhoaTKPN.setVisible(true);
            }

            // labelLCTK
            lbLCTKPN = new JLabel("L???a ch???n kh??a t??m ki???m:");
            lbLCTKPN.setFont(new Font("Arial", Font.BOLD, 18));
            lbLCTKPN.setBounds(5, 25, 230, 100);

            // labelTuKhoaTK
            lbTuKhoaTKPN = new JLabel("Nh???p t??? kh??a t??m ki???m:");
            lbTuKhoaTKPN.setFont(new Font("Arial", Font.BOLD, 18));
            lbTuKhoaTKPN.setBounds(5, 75, 230, 100);

            // JTextField Kh??a t??m ki???m
            txKhoaTKPN = new JTextField();
            txKhoaTKPN.setFont(new Font("Arial", Font.PLAIN, 15));
            txKhoaTKPN.setBounds(250, 110, 150, 30);

            String[] dsKhoaTK = { "", "M?? phi???u nh???p", "SL t???ng", "????n gi??", "M?? nh??n vi??n", "M?? NCC" };
            cbDSKhoaTKPN = new JComboBox<>(dsKhoaTK);
            cbDSKhoaTKPN.setFont(new Font("Arial", Font.BOLD, 13));
            cbDSKhoaTKPN.setBounds(250, 60, 120, 30);
            cbDSKhoaTKPN.addActionListener(this);

            TitledBorder titleTK;
            Border blackline;
            blackline = BorderFactory.createLineBorder(Color.black);
            titleTK = BorderFactory.createTitledBorder(blackline, "T??m ki???m");
            titleTK.setTitleFont(new Font("Arial", Font.BOLD, 25));
            titleTK.setTitleJustification(TitledBorder.CENTER);
            pnTimKiemPN.setBorder(titleTK);

            btTimKiemPN = new JButton("T??m ki???m");
            btTimKiemPN.setFont(new Font("Arial", Font.BOLD, 15));
            btTimKiemPN.setBounds(300, 155, 100, 30);
            btTimKiemPN.setBackground(MyColor.ColorButton);
            btTimKiemPN.setBorder(new RoundedBorder(10));
            btTimKiemPN.addActionListener(this);

        }
        pnTimKiemPN.add(lbLCTKPN);
        pnTimKiemPN.add(lbTuKhoaTKPN);
        pnTimKiemPN.add(txKhoaTKPN);
        pnTimKiemPN.add(cbDSKhoaTKPN);
        pnTimKiemPN.add(btTimKiemPN);
    }

    public void setLocPN() {
        if (btLocPN == null) {
            if (lbNgayBDLocPN != null || lbNgayKTLocPN != null || datePickerNgayBDNhap != null
                    || datePickerNgayKTNhap != null) {
                lbNgayBDLocPN.setVisible(true);
                lbNgayKTLocPN.setVisible(true);
                datePickerNgayBDNhap.setVisible(true);
                datePickerNgayKTNhap.setVisible(true);
            }

            // set Border
            TitledBorder titleLoc;
            Border blackline;
            blackline = BorderFactory.createLineBorder(Color.black);
            titleLoc = BorderFactory.createTitledBorder(blackline, "L???c d??? li???u");
            titleLoc.setTitleFont(new Font("Arial", Font.BOLD, 25));
            titleLoc.setTitleJustification(TitledBorder.CENTER);
            pnLocPN.setBorder(titleLoc);

            lbNgayBDLocPN = new JLabel("Ng??y b???t ?????u: ");
            lbNgayBDLocPN.setFont(new Font("Arial", Font.BOLD, 18));
            lbNgayBDLocPN.setBounds(5, 15, 150, 80);

            lbNgayKTLocPN = new JLabel("Ng??y k???t th??c: ");
            lbNgayKTLocPN.setFont(new Font("Arial", Font.BOLD, 18));
            lbNgayKTLocPN.setBounds(5, 55, 150, 80);

            // Set date picker1
            modelNgayBDNhap = new UtilDateModel();
            modelNgayBDNhap.setSelected(true);
            pNgayBDNhap = new Properties();
            pNgayBDNhap.put("text.today", "Today");
            pNgayBDNhap.put("text.month", "Month");
            pNgayBDNhap.put("text.year", "Year");
            datePanelNgayBDNhap = new JDatePanelImpl(modelNgayBDNhap, pNgayBDNhap);
            datePickerNgayBDNhap = new JDatePickerImpl(datePanelNgayBDNhap, new DateLabelFormatter());
            datePickerNgayBDNhap.setBounds(140, 40, 150, 30);

            // Set date picker1
            modelNgayKTNhap = new UtilDateModel();
            modelNgayKTNhap.setSelected(true);
            pNgayKTNhap = new Properties();
            pNgayKTNhap.put("text.today", "Today");
            pNgayKTNhap.put("text.month", "Month");
            pNgayKTNhap.put("text.year", "Year");
            datePanelNgayKTNhap = new JDatePanelImpl(modelNgayKTNhap, pNgayKTNhap);
            datePickerNgayKTNhap = new JDatePickerImpl(datePanelNgayKTNhap, new DateLabelFormatter());
            datePickerNgayKTNhap.setBounds(140, 80, 150, 30);

            btLocPN = new JButton("L???c");
            btLocPN.setFont(new Font("Arial", Font.BOLD, 15));
            btLocPN.setBounds(210, 115, 80, 30);
            btLocPN.setBackground(MyColor.ColorButton);
            btLocPN.setBorder(new RoundedBorder(10));
            btLocPN.addActionListener(this);
        }
        pnLocPN.add(lbNgayBDLocPN);
        pnLocPN.add(lbNgayKTLocPN);
        pnLocPN.add(datePickerNgayBDNhap);
        pnLocPN.add(datePickerNgayKTNhap);
        pnLocPN.add(btLocPN);
    }

    public void getInfoTextFieldPN(PHIEUNHAP phieunhap) {
        phieunhap.setMaPN(txMaPN.getText().replaceAll("\\s+", "").trim());
        phieunhap.setNgaynhap(datePickerNgayBDPN.getJFormattedTextField().getText());
        phieunhap.setSLTong(Integer.parseInt(txSLTong.getText().replaceAll("\\s+", "").trim()));
        String DonGia = myTable.RemoveCommaInString(txDonGia).trim();
        phieunhap.setDongia(Integer.parseInt(DonGia));
        phieunhap.setMaNV(txMaNV.getText().replaceAll("\\s+", "").trim());
        phieunhap.setMaNCC(txMaNCC.getText().replaceAll("\\s+", "").trim());
    }

    public void getInfoTextFieldCTPN(CHITIETPHIEUNHAP ctphieunhap) {
        ctphieunhap.setMaPN(txCTPNMaPN.getText().replaceAll("\\s+", "").trim());
        ctphieunhap.setMasach(txCTPNMaSach.getText().replaceAll("\\s+", "").trim());
        ctphieunhap.setSL(Integer.parseInt(txCTPNSL.getText().replaceAll("\\s+", "").trim()));
    }

    public void updateSLSachNhap(String Masach) {
        QLSACHBUS qlbus = new QLSACHBUS();
        if (QLSACHBUS.dssach == null) {
            try {
                qlbus.docDSSACH();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        int indexSua = 0;
        // T??m m?? s??ch ????? c???p nh???t s??? l?????ng t???ng
        for (SACH sach : QLSACHBUS.dssach) {
            if (sach.getMasach().trim().equals(Masach)) {
                int SLtong = sach.getSLtong() + Integer.parseInt(txCTPNSL.getText());
                sach.setSLtong(SLtong);
                try {
                    qlbus.sua(sach, sach, indexSua);
                } catch (Exception e) {
                    System.out.println(e);
                }
            }
            indexSua++;
        }
        // Show l??n table
        QLSACHGUI.model.setRowCount(0);
        for (SACH sach : QLSACHBUS.dssach) {
            Vector<String> row = new Vector<String>();
            row.add(sach.getMasach().trim());
            row.add(sach.getTensach().trim());
            row.add(sach.getMaNXB().trim());
            row.add(sach.getMaTG().trim());
            row.add(sach.getNamXB().trim());
            row.add(String.valueOf(sach.getSLtong()));
            row.add(String.valueOf(sach.getSL()));
            row.add(String.format("%,d", sach.getDongia()));
            QLSACHGUI.model.addRow(row);
        }
    }

    public void updateThanhTien(String MaPN, int ThanhTien) {
        int i = 0;
        for (PHIEUNHAP pn : QLPNBUS.dspn) {
            if (pn.getMaPN().trim().equals(MaPN.trim())) {
                int thanhtienBD = pn.getDongia();
                pn.setDongia(thanhtienBD + ThanhTien);
                QLPNBUS qlbus = new QLPNBUS();
                try {
                    int kt = qlbus.sua(pn, pn, i);
                    if (kt == 0) {
                        modelPN.setValueAt(String.format("%,d", pn.getDongia()), i, 3);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            i++;
        }
    }
}

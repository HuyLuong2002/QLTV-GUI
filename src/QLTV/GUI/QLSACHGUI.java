package QLTV.GUI;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.util.ArrayList;
import java.util.Properties;
import java.util.Vector;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import MyCustom.BaoCaoThongKe;
import MyCustom.DateLabelFormatter;
import MyCustom.DocGhiFileExcel;
import MyCustom.LoginPage;
import MyCustom.Menu;
import MyCustom.MyColor;
import MyCustom.MyTable;
import MyCustom.RoundedBorder;
import QLTV.BUS.QLSACHBUS;
import QLTV.DTO.SACH;

public class QLSACHGUI extends JFrame implements ActionListener, MouseListener {
    MyTable myTable = new MyTable();
    JPanel pnTTSach, pnNhapTTSach, pnShowAll, pnMenu, pnTimKiem, pnLoc;
    JPanel pnMT, pnPN, pnThongKe, pnQLNV, pnLibrary;
    JLabel lbHome, lbTTSach, lbMasach, lbTensach, lbMaNXB, lbMaTG, lbNamXB, lbSLtong, lbSL, lbDongia, lbLCTK,
            lbTuKhoaTK;
    JLabel lbTKNam, lbTKSL, lbNgayBD, lbNgayKT, lbLibrary;
    JTextField txMasach, txTensach, txMaNXB, txMaTG, txNamXB, txSLtong, txSL, txDongia, txKhoaTK;
    JTextField txTKNam, txTKSL;
    JButton btThem, btSua, btXoa, btHoanTac, btMenuTimKiem, btShowAll, btThongKe;
    JButton btMenu, btSach, btMT, btQLNV, btDangXuat, btNhapSach, btThoat;
    JButton btTK, btSearch, btLoc, btSapXep, btNhapExcel, btXuatExcel;

    JScrollPane pane;
    JComboBox<String> comboBoxDSKhoaTK;

    JTable tblQLSACH;
    public static DefaultTableModel model;
    Vector<String> header;

    UtilDateModel modelNgayBD, modelNgayKT;
    Properties pNgayBD, pNgayKT;
    JDatePanelImpl datePanelNgayBD, datePanelNgayKT;
    JDatePickerImpl datePickerNgayBD, datePickerNgayKT;
    private ImageIcon imgIconHP;

    public QLSACHGUI() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(1400, 800);
        this.setTitle("Qu???n l?? th??ng tin s??ch");
        this.setLayout(null);
        this.setLocationRelativeTo(null);
        imgIconHP = new ImageIcon("images\\app_logo.png");
        this.setIconImage(imgIconHP.getImage());

        pnTTSach = new JPanel();
        pnNhapTTSach = new JPanel();
        pnShowAll = new JPanel();
        pnMenu = new JPanel();
        pnTimKiem = new JPanel();
        pnLoc = new JPanel();
        pnLibrary = new JPanel();

        pnLibrary.setLayout(null);
        pnLibrary.setBounds(0, 0, 240, 178);
        pnLibrary.setBackground(MyColor.ColorOcean);

        lbLibrary = new JLabel();
        lbLibrary.setIcon(new ImageIcon("images\\user_login.png"));
        lbLibrary.setBounds(55, 25, 125, 125);

        pnTTSach.setLayout(new GridLayout(2, 1, 0, -300));
        pnTTSach.setBackground(MyColor.ColorBlue);
        pnTTSach.setBounds(242, 0, 1142, 400);

        pnShowAll.setLayout(null);
        pnShowAll.setBackground(MyColor.ColorBlue);
        pnShowAll.setBounds(242, 402, 1142, 30);

        pnNhapTTSach.setLayout(null);
        pnNhapTTSach.setBackground(MyColor.ColorBlue);
        pnNhapTTSach.setBounds(242, 415, 720, 550);

        pnMenu.setLayout(new GridLayout(9, 1));
        pnMenu.setBounds(0, 178, 240, 590);

        pnTimKiem.setLayout(null);
        pnTimKiem.setBackground(MyColor.ColorBlue);
        pnTimKiem.setBounds(962, 430, 423, 332);

        pnLoc.setLayout(null);
        pnLoc.setBackground(MyColor.ColorBlue);
        pnLoc.setBounds(380, 105, 300, 180);
        // add components
        this.add(pnMenu);
        this.add(pnShowAll);
        this.add(pnNhapTTSach);
        this.add(pnTimKiem);
        this.add(pnLibrary);
        pnNhapTTSach.add(pnLoc);
        pnLibrary.add(lbLibrary);

        setTableSach();
        setInput();
        setLoc();
        setMenu();
        setShowAll();
        getDatabase();
        setValueCellCenter();

        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btThem) {
            try {
                SACH sach = new SACH();
                getInfoTextField(sach);
                // Truy c???p v??o bus
                QLSACHBUS qlsachbus = new QLSACHBUS();
                int kiemtra = -1;
                try {
                    kiemtra = qlsachbus.them(sach);
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
                if (kiemtra == 0) {
                    // ????a d??? li???u l??n table
                    header = new Vector<String>();
                    header.add("M?? s??ch");
                    header.add("T??n s??ch");
                    header.add("M?? NXB");
                    header.add("M?? t??c gi???");
                    header.add("N??m xu???t b???n");
                    header.add("SL t???ng");
                    header.add("SL");
                    header.add("????n gi??");
                    if (model.getRowCount() == 0) {
                        model = new DefaultTableModel(header, 0);
                    }
                    ShowOnTable(sach);
                    tblQLSACH.setModel(model);
                }
            } catch (Exception e1) {
                System.out.println(e1);
            }
        } else if (e.getSource() == btSua) {
            int i = tblQLSACH.getSelectedRow();
            int kt = -1;
            if (i >= 0) {
                SACH sach = new SACH();
                SACH masachCu = QLSACHBUS.dssach.get(i);
                getInfoTextField(sach);
                try {
                    QLSACHBUS qlsachbus = new QLSACHBUS();
                    kt = qlsachbus.sua(sach, masachCu, i);
                } catch (Exception e1) {
                    System.out.println(e1);
                }
                if (kt == 0) {
                    model.setValueAt(sach.getMasach(), i, 0);
                    model.setValueAt(sach.getTensach(), i, 1);
                    model.setValueAt(sach.getMaNXB(), i, 2);
                    model.setValueAt(sach.getMaTG(), i, 3);
                    model.setValueAt(sach.getNamXB(), i, 4);
                    model.setValueAt(sach.getSLtong(), i, 5);
                    model.setValueAt(sach.getSL(), i, 6);
                    model.setValueAt(String.format("%,d", sach.getDongia()), i, 7);
                    tblQLSACH.setModel(model);
                }
            }
        } else if (e.getSource() == btXoa) {
            int XacNhanXoa = JOptionPane.showConfirmDialog(null, "B???n c?? ch???c ch???n mu???n x??a kh??ng ?", "Th??ng b??o",
                    JOptionPane.YES_NO_OPTION);
            if (XacNhanXoa == 0) {
                String masach = txMasach.getText();
                int i = tblQLSACH.getSelectedRow();
                int kt = -1;
                if (i >= 0) {
                    try {
                        // Truy c???p xu???ng BUS
                        SACH SachOld = QLSACHBUS.dssach.get(i);
                        QLSACHBUS.htXoa.add(SachOld);
                        QLSACHBUS qlsachbus = new QLSACHBUS();
                        kt = qlsachbus.xoa(masach, i);
                        // Quay d??? GUI
                    } catch (Exception e1) {
                        System.out.println(e1);
                    }
                    if(kt == 0){
                        model.removeRow(i);
                        tblQLSACH.setModel(model);
                    }
                }
            }
        } else if (e.getSource() == btHoanTac) {
            int ktHT = 0;
            if (QLSACHBUS.htXoa.size() == 0) {
                JOptionPane.showMessageDialog(null, "D??? li???u ho??n t??c r???ng", "L???i",
                        JOptionPane.ERROR_MESSAGE);
            } else {
                for (SACH sach : QLSACHBUS.htXoa) {
                    QLSACHBUS qlsachbus = new QLSACHBUS();
                    int kiemtra = -1;
                    try {
                        kiemtra = qlsachbus.hoantacXoa(sach);
                    } catch (Exception e1) {
                        e1.printStackTrace();
                    }
                    if (kiemtra == 0) {
                        // ????a d??? li???u l??n table
                        header = new Vector<String>();
                        header.add("M?? s??ch");
                        header.add("T??n s??ch");
                        header.add("M?? NXB");
                        header.add("M?? t??c gi???");
                        header.add("N??m xu???t b???n");
                        header.add("SL t???ng");
                        header.add("SL");
                        header.add("????n gi??");
                        if (model.getRowCount() == 0) {
                            model = new DefaultTableModel(header, 0);
                        }
                        ShowOnTable(sach);
                        ktHT = 1;
                    } else if (kiemtra == -1) {
                        JOptionPane.showMessageDialog(null, "Ho??n t??c d??? li???u th???t b???i", "L???i",
                                JOptionPane.ERROR_MESSAGE);
                        ktHT = 0;
                    }
                }
            }
            if (ktHT == 1) {
                JOptionPane.showMessageDialog(null, "Ho??n t??c d??? li???u th??nh c??ng", "Th??ng b??o",
                        JOptionPane.INFORMATION_MESSAGE);
                tblQLSACH.setModel(model);
            }
        } else if (e.getSource() == comboBoxDSKhoaTK) {
            int vtkey = Integer.parseInt(String.valueOf(comboBoxDSKhoaTK.getSelectedIndex()));
            if (vtkey == 9 || vtkey == 10) {
                setTimKiemNC();
            } else {
                // ???n l???i c??c component c???a combobox key 9 10
                if (lbTKNam != null && txTKNam != null && lbTKSL != null && txTKSL != null && btTK != null) {
                    lbTKNam.setVisible(false);
                    txTKNam.setVisible(false);
                    lbTKSL.setVisible(false);
                    txTKSL.setVisible(false);
                    btTK.setVisible(false);
                }
                // Hi???n th??? c??c component c???a c??c key kh??c
                if (btSearch.isVisible() || txKhoaTK.isVisible() || btThongKe.isVisible()) {
                    btSearch.setVisible(true);
                    txKhoaTK.setVisible(true);
                    btThongKe.setVisible(true);
                }
            }
        } else if (e.getSource() == btTK) { // T??m Ki???m n??ng cao
            // btTK l?? c???a t??m ki???m n??ng cao
            String NamXB = txTKNam.getText();
            String SL = txTKSL.getText();
            if (NamXB.equals("") == true || SL.equals("") == true) {
                JOptionPane.showMessageDialog(null, "Xin m???i nh???p t??? kh??a", "L???i", JOptionPane.ERROR_MESSAGE);
            }
            QLSACHBUS qlsachbus = new QLSACHBUS();
            int vtkey = Integer.parseInt(String.valueOf(comboBoxDSKhoaTK.getSelectedIndex()));
            if (vtkey == 9) {
                ArrayList<SACH> kq = qlsachbus.timNamXBHoacSL(NamXB, SL);
                model.setRowCount(0);
                if (kq.size() == 0) {
                    JOptionPane.showMessageDialog(null, "Kh??ng t??m th???y d??? li???u", "L???i", JOptionPane.ERROR_MESSAGE);
                } else {
                    for (SACH sach : kq) {
                        ShowOnTable(sach);
                    }
                }
                tblQLSACH.setModel(model);
            } else if (vtkey == 10) {
                ArrayList<SACH> kq = qlsachbus.timNamXBVaSL(NamXB, SL);
                model.setRowCount(0);
                if (kq.size() == 0) {
                    JOptionPane.showMessageDialog(null, "Kh??ng t??m th???y d??? li???u", "L???i", JOptionPane.ERROR_MESSAGE);
                    JOptionPane.showMessageDialog(null, "Kh??ng t??m th???y d??? li???u", "L???i", JOptionPane.ERROR_MESSAGE);
                } else {
                    for (SACH sach : kq) {
                        ShowOnTable(sach);
                    }
                }
                tblQLSACH.setModel(model);
            }
        } else if (e.getSource() == btMenuTimKiem) { // C???a button T??m ki???m s??ch, ????? hi???n th???
            // khung t??m ki???m
            OffBTBgSelected();
            btMenuTimKiem.setBackground(MyColor.ColorLightBlue);
            if (pnMT != null) {
                pnMT.setVisible(false);
            }
            OffPageQLSACH(true);
            setTimKiem();
        } else if (e.getSource() == btSearch) { // T??m ki???m c?? b???n
            int vtkey = Integer.parseInt(String.valueOf(comboBoxDSKhoaTK.getSelectedIndex()));
            String tukhoa = txKhoaTK.getText().replaceAll("\\s+", "").toLowerCase().trim();
            if (vtkey == 8) {
                int dongia = Integer.parseInt(tukhoa);
                txKhoaTK.setText(String.format("%,d", dongia));
            }
            if (tukhoa.equals("") == true) {
                JOptionPane.showMessageDialog(null, "Xin m???i nh???p t??? kh??a", "L???i", JOptionPane.ERROR_MESSAGE);
            } else if (vtkey == 0) {
                JOptionPane.showMessageDialog(null, "Xin m???i l???a ch???n kh??a t??m ki???m", "L???i", JOptionPane.ERROR_MESSAGE);
            } else {
                QLSACHBUS qlsachbus = new QLSACHBUS();
                if (vtkey == 1) {
                    SACH kq = qlsachbus.timTheoMa(tukhoa);
                    model.setRowCount(0);
                    if (kq == null) {
                        JOptionPane.showMessageDialog(null, "Kh??ng t??m th???y d??? li???u", "L???i", JOptionPane.ERROR_MESSAGE);
                    } else {
                        ShowOnTable(kq);
                        tblQLSACH.setModel(model);
                    }
                } else if (vtkey == 2) {
                    ArrayList<SACH> kq = qlsachbus.timTheoTen(tukhoa);
                    model.setRowCount(0);
                    if (kq.size() == 0) {
                        JOptionPane.showMessageDialog(null, "Kh??ng t??m th???y d??? li???u", "L???i", JOptionPane.ERROR_MESSAGE);
                    } else {
                        for (SACH sach : kq) {
                            ShowOnTable(sach);
                        }
                        tblQLSACH.setModel(model);
                    }
                } else if (vtkey == 3) {
                    ArrayList<SACH> kq = qlsachbus.timTheoMaNXB(tukhoa);
                    model.setRowCount(0);
                    if (kq.size() == 0) {
                        JOptionPane.showMessageDialog(null, "Kh??ng t??m th???y d??? li???u", "L???i", JOptionPane.ERROR_MESSAGE);
                    } else {
                        for (SACH sach : kq) {
                            ShowOnTable(sach);
                        }
                        tblQLSACH.setModel(model);
                    }
                } else if (vtkey == 4) {
                    ArrayList<SACH> kq = qlsachbus.timTheoMaTG(tukhoa);
                    model.setRowCount(0);
                    if (kq.size() == 0) {
                        JOptionPane.showMessageDialog(null, "Kh??ng t??m th???y d??? li???u", "L???i", JOptionPane.ERROR_MESSAGE);
                    } else {
                        for (SACH sach : kq) {
                            ShowOnTable(sach);
                        }
                        tblQLSACH.setModel(model);
                    }
                } else if (vtkey == 5) {
                    ArrayList<SACH> kq = qlsachbus.timTheoNamXB(tukhoa);
                    model.setRowCount(0);
                    if (kq.size() == 0) {
                        JOptionPane.showMessageDialog(null, "Kh??ng t??m th???y d??? li???u", "L???i", JOptionPane.ERROR_MESSAGE);
                    } else {
                        for (SACH sach : kq) {
                            ShowOnTable(sach);
                        }
                        tblQLSACH.setModel(model);
                    }
                } else if (vtkey == 6) {
                    ArrayList<SACH> kq = qlsachbus.timTheoSLtong(tukhoa);
                    model.setRowCount(0);
                    if (kq.size() == 0) {
                        JOptionPane.showMessageDialog(null, "Kh??ng t??m th???y d??? li???u", "L???i", JOptionPane.ERROR_MESSAGE);
                    } else {
                        for (SACH sach : kq) {
                            ShowOnTable(sach);
                        }
                        tblQLSACH.setModel(model);
                    }
                } else if (vtkey == 7) {
                    ArrayList<SACH> kq = qlsachbus.timTheoSL(tukhoa);
                    model.setRowCount(0);
                    if (kq.size() == 0) {
                        JOptionPane.showMessageDialog(null, "Kh??ng t??m th???y d??? li???u", "L???i", JOptionPane.ERROR_MESSAGE);
                    } else {
                        for (SACH sach : kq) {
                            ShowOnTable(sach);
                        }
                        tblQLSACH.setModel(model);
                    }
                } else if (vtkey == 8) {
                    ArrayList<SACH> kq = qlsachbus.timTheoDonGia(tukhoa);
                    model.setRowCount(0);
                    if (kq.size() == 0) {
                        JOptionPane.showMessageDialog(null, "Kh??ng t??m th???y d??? li???u", "L???i", JOptionPane.ERROR_MESSAGE);
                    } else {
                        for (SACH sach : kq) {
                            ShowOnTable(sach);
                        }
                        tblQLSACH.setModel(model);
                    }
                }
            }
        } else if (e.getSource() == btShowAll) {
            model.setRowCount(0);
            for (SACH sach : QLSACHBUS.dssach) {
                ShowOnTable(sach);
            }
            tblQLSACH.setModel(model);
        }
        if (e.getSource() == btThongKe) {
            BaoCaoThongKe bctk = new BaoCaoThongKe();
            OffBTBgSelected();
            OffPageQLSACH(false);
            btThongKe.setBackground(MyColor.ColorLightBlue);
            if (pnTimKiem != null) {
                pnTimKiem.setVisible(false);
            }
            if (pnMT != null) {
                pnMT.setVisible(false);
            }
            if (pnQLNV != null) {
                pnQLNV.setVisible(false);
            }
            if (pnPN != null) {
                pnPN.setVisible(false);
            }
            try {
                if (pnThongKe == null) {
                    pnThongKe = bctk.BangBaoCaoThongKe();
                    this.add(pnThongKe);
                }
                else{
                    pnThongKe.setVisible(true);
                }
            } catch (Exception e1) {
                e1.printStackTrace();
            }

        }
        if (e.getSource() == btMenu) {
            OffBTBgSelected();
            OffPageQLSACH(false);
            btMenu.setBackground(MyColor.ColorLightBlue);
            if (pnTimKiem != null) {
                pnTimKiem.setVisible(false);
            }
            if (pnMT != null) {
                pnMT.setVisible(false);
            }
            if (pnThongKe != null) {
                pnThongKe.setVisible(false);
            }
            if (pnPN != null) {
                pnPN.setVisible(false);
            }
        }
        if (e.getSource() == btMT) {
            QLMTGUI qlmt = new QLMTGUI();
            OffBTBgSelected();
            OffPageQLSACH(false);
            btMT.setBackground(MyColor.ColorLightBlue);
            if (pnTimKiem != null) {
                pnTimKiem.setVisible(false);
            }
            if (pnThongKe != null) {
                pnThongKe.setVisible(false);
            }
            if (pnQLNV != null) {
                pnQLNV.setVisible(false);
            }
            if (pnPN != null) {
                pnPN.setVisible(false);
            }
            if (pnMT == null) {
                pnMT = qlmt.setMTGUI();
                this.add(pnMT);
            } else {
                pnMT.setVisible(true);
            }
        }
        if (e.getSource() == btDangXuat) {
            int ktra = JOptionPane.showConfirmDialog(null, "B???n c?? ch???c ch???n mu???n ????ng xu???t", "X??c nh???n",
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
        if (e.getSource() == btLoc) {
            String tmp[] = datePickerNgayBD.getJFormattedTextField().getText().split("-");
            String tmp1[] = datePickerNgayKT.getJFormattedTextField().getText().split("-");
            int NamBD = Integer.parseInt(tmp[0]);
            int NamKT = Integer.parseInt(tmp1[0]);
            QLSACHBUS qlsachbus = new QLSACHBUS();
            ArrayList<SACH> kq = qlsachbus.loc(NamBD, NamKT);
            model.setRowCount(0);
            if (kq.size() == 0) {
                JOptionPane.showMessageDialog(null, "L???c d??? li???u th???t b???i", "L???i", JOptionPane.ERROR_MESSAGE);
            } else {
                for (SACH sach : kq) {
                    ShowOnTable(sach);
                }
                tblQLSACH.setModel(model);
            }
        }
        if (e.getSource() == btSach) {
            if (pnMT != null) {
                pnMT.setVisible(false);
            }
            if (pnThongKe != null) {
                pnThongKe.setVisible(false);
            }
            if (pnQLNV != null) {
                pnQLNV.setVisible(false);
            }
            if (pnPN != null) {
                pnPN.setVisible(false);
            }
            OffPageQLSACH(true);
            OffBTBgSelected();
            btSach.setBackground(MyColor.ColorLightBlue);
        }
        if (e.getSource() == btNhapSach) {
            QLPNGUI qlpn = new QLPNGUI();
            OffPageQLSACH(false);
            OffBTBgSelected();
            btNhapSach.setBackground(MyColor.ColorLightBlue);
            if (pnMT != null) {
                pnMT.setVisible(false);
            }
            if (pnTimKiem != null) {
                pnTimKiem.setVisible(false);
            }
            if (pnThongKe != null) {
                pnThongKe.setVisible(false);
            }
            if (pnQLNV != null) {
                pnQLNV.setVisible(false);
            }
            if (pnPN == null) {
                pnPN = qlpn.setPNGUI();
                this.add(pnPN);
            }
            else{
                pnPN.setVisible(true);
            }
        }
        if (e.getSource() == btQLNV) {
            QLNVGUI qlnv = new QLNVGUI();
            OffPageQLSACH(false);
            OffBTBgSelected();
            btQLNV.setBackground(MyColor.ColorLightBlue);
            if (pnMT != null) {
                pnMT.setVisible(false);
            }
            if (pnTimKiem != null) {
                pnTimKiem.setVisible(false);
            }
            if (pnThongKe != null) {
                pnThongKe.setVisible(false);
            }
            if (pnPN != null) {
                pnPN.setVisible(false);
            }

            if (pnQLNV == null) {
                pnQLNV = qlnv.setQLNVGUI();
                this.add(pnQLNV);
            }
            else {
                pnQLNV.setVisible(true);
            }

        }
        if (e.getSource() == btThoat) {
            int ktra = JOptionPane.showConfirmDialog(null, "B???n c?? ch???c ch???n mu???n ????ng xu???t", "X??c nh???n",
                    JOptionPane.YES_NO_OPTION);
            if (ktra == 0) {
                System.exit(0);
            }
        }
        if (e.getSource() == btSapXep) {
            SapXep();
        }
        if (e.getSource() == btMenu) {
            this.dispose();
            new Menu();
        }
        if (e.getSource() == btNhapExcel) {
            JFileChooser fileChooser = new JFileChooser();
            String namepath = "";
            fileChooser.setCurrentDirectory(new File("C:\\"));
            // int response = fileChooser.showOpenDialog(null); //select file to open, tr???
            // v???
            // 0 n???u m??? file, c??n canel th?? tr??? v??? 1
            FileNameExtensionFilter fnef = new FileNameExtensionFilter("EXCEL FILES", "xls", "xlsx", "xlsm");
            fileChooser.setFileFilter(fnef);
            fileChooser.setDialogTitle("L???a ch???n file ????? nh???p");
            int response = fileChooser.showOpenDialog(null); // select file to save
            if (response == JFileChooser.APPROVE_OPTION) {
                File file = new File(fileChooser.getSelectedFile().getAbsolutePath());
                namepath = file.getAbsolutePath();
            }
            new DocGhiFileExcel(namepath, tblQLSACH, model, null, 1);
            JOptionPane.showMessageDialog(null, "Th??m d??? li???u th??nh c??ng", "Th??ng b??o",
                    JOptionPane.INFORMATION_MESSAGE);
        }
        if (e.getSource() == btXuatExcel) {
            JFileChooser fileChooser = new JFileChooser();
            String namepath = "";
            fileChooser.setCurrentDirectory(new File("C:\\"));
            FileNameExtensionFilter fnef = new FileNameExtensionFilter("EXCEL FILES", "xls", "xlsx", "xlsm");
            fileChooser.setFileFilter(fnef);
            fileChooser.setDialogTitle("L???a ch???n file ????? l??u");
            int response = fileChooser.showSaveDialog(null);
            if (response == JFileChooser.APPROVE_OPTION) {
                File file = new File(fileChooser.getSelectedFile().getAbsolutePath());
                namepath = file.getAbsolutePath();
            }
            DocGhiFileExcel file = new DocGhiFileExcel(namepath, tblQLSACH, model, fileChooser, 0);
            file.setTitleInExcel(model);
            JOptionPane.showMessageDialog(null, "Xu???t d??? li???u th??nh c??ng", "Th??ng b??o",
                    JOptionPane.INFORMATION_MESSAGE);
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource() == tblQLSACH) {
            int i = tblQLSACH.getSelectedRow();
            if (i >= 0) {
                SACH sach = new SACH();
                sach = QLSACHBUS.dssach.get(i);
                txMasach.setText(sach.getMasach().trim());
                txTensach.setText(sach.getTensach().trim());
                txMaNXB.setText(sach.getMaNXB().trim());
                txMaTG.setText(sach.getMaTG().trim());
                txNamXB.setText(sach.getNamXB().trim());
                txSLtong.setText(String.valueOf(sach.getSLtong()));
                txSL.setText(String.valueOf(sach.getSL()));
                txDongia.setText(String.format("%,d", sach.getDongia()));
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
        if (e.getSource() == txMasach) {
            txMasach.setToolTipText("G???i ??: MS001");
        } else if (e.getSource() == txMaNXB) {
            txMaNXB.setToolTipText("G???i ??: NXB001");
        } else if (e.getSource() == txMaTG) {
            txMaTG.setToolTipText("G???i ??: TG001");
        } else if (e.getSource() == txNamXB) {
            txNamXB.setToolTipText("G???i ??: 2002");
        } else if (e.getSource() == txSLtong) {
            txSLtong.setToolTipText("G???i ??: SLtong > SL");
        } else if (e.getSource() == txSL) {
            txSL.setToolTipText("G???i ??: SLtong > SL");
        } else if (e.getSource() == txDongia) {
            txDongia.setToolTipText("G???i ??: 3000");
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    public void setInput() {
        // label Masach
        lbMasach = new JLabel("M?? s??ch:");
        lbMasach.setFont(new Font("Arial", Font.BOLD, 18));
        lbMasach.setBounds(20, 0, 130, 80);
        // label Tensach
        lbTensach = new JLabel("T??n s??ch:");
        lbTensach.setFont(new Font("Arial", Font.BOLD, 18));
        lbTensach.setBounds(20, 40, 130, 80);
        // label MaNXB
        lbMaNXB = new JLabel("M?? NXB:");
        lbMaNXB.setFont(new Font("Arial", Font.BOLD, 18));
        lbMaNXB.setBounds(20, 80, 130, 80);
        // label MaTG
        lbMaTG = new JLabel("M?? t??c gi???: ");
        lbMaTG.setFont(new Font("Arial", Font.BOLD, 18));
        lbMaTG.setBounds(20, 120, 130, 80);
        // label NamXB
        lbNamXB = new JLabel("N??m xu???t b???n:");
        lbNamXB.setFont(new Font("Arial", Font.BOLD, 18));
        lbNamXB.setBounds(20, 160, 130, 80);
        // label SLtong
        lbSLtong = new JLabel("SL t???ng:");
        lbSLtong.setFont(new Font("Arial", Font.BOLD, 18));
        lbSLtong.setBounds(20, 200, 130, 80);
        // label SL
        lbSL = new JLabel("SL:");
        lbSL.setFont(new Font("Arial", Font.BOLD, 18));
        lbSL.setBounds(380, 0, 130, 80);
        // label ????n gi??
        lbDongia = new JLabel("????n gi??:");
        lbDongia.setFont(new Font("Arial", Font.BOLD, 18));
        lbDongia.setBounds(380, 40, 130, 80);
        // labelLCTK
        lbLCTK = new JLabel("L???a ch???n kh??a t??m ki???m:");
        lbLCTK.setFont(new Font("Arial", Font.BOLD, 18));
        lbLCTK.setBounds(10, 50, 230, 80);

        // labelTuKhoaTK
        lbTuKhoaTK = new JLabel("Nh???p t??? kh??a t??m ki???m:");
        lbTuKhoaTK.setFont(new Font("Arial", Font.BOLD, 18));
        lbTuKhoaTK.setBounds(10, 100, 230, 80);

        // JTextField M?? s??ch
        txMasach = new JTextField();
        txMasach.setBounds(160, 25, 180, 30);
        txMasach.setFont(new Font("Arial", Font.PLAIN, 15));
        txMasach.addMouseListener(this);
        // JTextField T??n s??ch
        txTensach = new JTextField();
        txTensach.setBounds(160, 65, 180, 30);
        txTensach.setFont(new Font("Arial", Font.PLAIN, 15));
        txTensach.addMouseListener(this);
        // JTextField M?? nh?? xu???t b???n
        txMaNXB = new JTextField();
        txMaNXB.setBounds(160, 105, 180, 30);
        txMaNXB.setFont(new Font("Arial", Font.PLAIN, 15));
        txMaNXB.addMouseListener(this);
        // JTextField M?? t??c gi???
        txMaTG = new JTextField();
        txMaTG.setBounds(160, 145, 180, 30);
        txMaTG.setFont(new Font("Arial", Font.PLAIN, 15));
        txMaTG.addMouseListener(this);
        // JTextField N??m xu???t b???n
        txNamXB = new JTextField();
        txNamXB.setBounds(160, 185, 180, 30);
        txNamXB.setFont(new Font("Arial", Font.PLAIN, 15));
        txNamXB.addMouseListener(this);
        // JTextField SL t???ng
        txSLtong = new JTextField();
        txSLtong.setBounds(160, 225, 180, 30);
        txSLtong.setFont(new Font("Arial", Font.PLAIN, 15));
        txSLtong.addMouseListener(this);
        // JTextField SL
        txSL = new JTextField();
        txSL.setBounds(500, 25, 180, 30);
        txSL.setFont(new Font("Arial", Font.PLAIN, 15));
        txSL.addMouseListener(this);
        // JTextField ????n gi??
        txDongia = new JTextField();
        txDongia.setBounds(500, 65, 180, 30);
        txDongia.setFont(new Font("Arial", Font.PLAIN, 15));
        txDongia.addMouseListener(this);
        // JTextField Kh??a t??m ki???m
        txKhoaTK = new JTextField();
        txKhoaTK.setFont(new Font("Arial", Font.PLAIN, 15));
        txKhoaTK.setBounds(245, 125, 160, 30);
        txKhoaTK.addMouseListener(this);

        // JbuttonThem
        btThem = new JButton("Th??m");
        btThem.setFont(new Font("Arial", Font.BOLD, 15));
        btThem.setBounds(10, 305, 80, 30);
        btThem.setBackground(MyColor.ColorButton);
        btThem.setBorder(new RoundedBorder(10));
        btThem.addActionListener(this);
        // JbuttonSua
        btSua = new JButton("S???a");
        btSua.setFont(new Font("Arial", Font.BOLD, 15));
        btSua.setBounds(110, 305, 80, 30);
        btSua.setBackground(MyColor.ColorButton);
        btSua.setBorder(new RoundedBorder(10));
        btSua.addActionListener(this);
        // JbuttonXoa
        btXoa = new JButton("X??a");
        btXoa.setFont(new Font("Arial", Font.BOLD, 15));
        btXoa.setBounds(210, 305, 80, 30);
        btXoa.setBackground(MyColor.ColorButton);
        btXoa.setBorder(new RoundedBorder(10));
        btXoa.addActionListener(this);
        // JbuttonHoanTac
        btHoanTac = new JButton("Ho??n t??c");
        btHoanTac.setFont(new Font("Arial", Font.BOLD, 15));
        btHoanTac.setBounds(310, 305, 90, 30);
        btHoanTac.setBackground(MyColor.ColorButton);
        btHoanTac.setBorder(new RoundedBorder(10));
        btHoanTac.addActionListener(this);

        // Jbutton nh???p excel
        btNhapExcel = new JButton("Nh???p Excel");
        btNhapExcel.setFont(new Font("Arial", Font.BOLD, 15));
        btNhapExcel.setBounds(440, 305, 120, 30);
        btNhapExcel.setBackground(MyColor.ColorButton);
        btNhapExcel.setBorder(new RoundedBorder(10));
        btNhapExcel.addActionListener(this);

        // Jbutton xu???t excel
        btXuatExcel = new JButton("Xu???t Excel");
        btXuatExcel.setFont(new Font("Arial", Font.BOLD, 15));
        btXuatExcel.setBounds(580, 305, 100, 30);
        btXuatExcel.setBackground(MyColor.ColorButton);
        btXuatExcel.setBorder(new RoundedBorder(10));
        btXuatExcel.addActionListener(this);

        // set up ComboBox
        String[] dsKhoaTK = { "", "M?? s??ch", "T??n s??ch", "M?? NXB", "M?? TG", "N??m XB", "SL t???ng", "SL", "????n gi??",
                "N??m ho???c SL", "N??m v?? SL" };
        comboBoxDSKhoaTK = new JComboBox<>(dsKhoaTK);
        comboBoxDSKhoaTK.setFont(new Font("Arial", Font.BOLD, 13));
        comboBoxDSKhoaTK.setBounds(245, 75, 100, 30);
        comboBoxDSKhoaTK.addActionListener(this);

        lbLCTK.setVisible(false);
        lbTuKhoaTK.setVisible(false);
        comboBoxDSKhoaTK.setVisible(false);
        txKhoaTK.setVisible(false);

        pnNhapTTSach.add(lbMasach);
        pnNhapTTSach.add(lbTensach);
        pnNhapTTSach.add(lbMaNXB);
        pnNhapTTSach.add(lbMaTG);
        pnNhapTTSach.add(lbNamXB);
        pnNhapTTSach.add(lbSLtong);
        pnNhapTTSach.add(lbSL);
        pnNhapTTSach.add(lbDongia);

        pnNhapTTSach.add(txMasach);
        pnNhapTTSach.add(txTensach);
        pnNhapTTSach.add(txMaNXB);
        pnNhapTTSach.add(txMaTG);
        pnNhapTTSach.add(txNamXB);
        pnNhapTTSach.add(txSLtong);
        pnNhapTTSach.add(txSL);
        pnNhapTTSach.add(txDongia);

        pnNhapTTSach.add(btThem);
        pnNhapTTSach.add(btSua);
        pnNhapTTSach.add(btXoa);
        pnNhapTTSach.add(btHoanTac);
        pnNhapTTSach.add(btNhapExcel);
        pnNhapTTSach.add(btXuatExcel);
    }

    public void setMenu() {
        // Set menu side left
        ImageIcon iconMenu = new ImageIcon("images\\menu.png");
        ImageIcon iconBook = new ImageIcon("images\\book.png");
        ImageIcon iconSearch = new ImageIcon("images\\search.png");
        ImageIcon iconRent = new ImageIcon("images\\payment.png");
        ImageIcon iconTK = new ImageIcon("images\\trend.png");
        ImageIcon iconNS = new ImageIcon("images\\cart.png");
        ImageIcon iconLogout = new ImageIcon("images\\logout.png");
        ImageIcon iconExited = new ImageIcon("images\\exit.png");

        btMenu = new JButton("Menu");
        btMenu.setFont(new Font("Times new Roman", Font.BOLD, 20));
        btMenu.setBackground(MyColor.ColorOcean);
        btMenu.setIcon(iconMenu);
        btMenu.setHorizontalAlignment(SwingConstants.LEFT);
        btMenu.setBorder(BorderFactory.createEmptyBorder());
        btMenu.addActionListener(this);

        btMenuTimKiem = new JButton("T??m ki???m s??ch");
        btMenuTimKiem.setFont(new Font("Times new Roman", Font.BOLD, 20));
        btMenuTimKiem.setBackground(MyColor.ColorOcean);
        btMenuTimKiem.setIcon(iconSearch);
        btMenuTimKiem.setHorizontalAlignment(SwingConstants.LEFT);
        btMenuTimKiem.setBorder(BorderFactory.createEmptyBorder());
        btMenuTimKiem.addActionListener(this);

        btSach = new JButton("Th??ng tin s??ch");
        btSach.setFont(new Font("Times new Roman", Font.BOLD, 20));
        btSach.setBackground(MyColor.ColorLightBlue);
        btSach.setIcon(iconBook);
        btSach.setHorizontalAlignment(SwingConstants.LEFT);
        btSach.setBorder(BorderFactory.createEmptyBorder());
        btSach.addActionListener(this);

        btMT = new JButton("M?????n tr??? s??ch");
        btMT.setFont(new Font("Times new Roman", Font.BOLD, 20));
        btMT.setBackground(MyColor.ColorOcean);
        btMT.setIcon(iconRent);
        btMT.setHorizontalAlignment(SwingConstants.LEFT);
        btMT.setBorder(BorderFactory.createEmptyBorder());
        btMT.addActionListener(this);

        btNhapSach = new JButton("Nh???p s??ch");
        btNhapSach.setFont(new Font("Times new Roman", Font.BOLD, 20));
        btNhapSach.setBackground(MyColor.ColorOcean);
        btNhapSach.setIcon(iconNS);
        btNhapSach.setHorizontalAlignment(SwingConstants.LEFT);
        btNhapSach.setBorder(BorderFactory.createEmptyBorder());
        btNhapSach.addActionListener(this);

        btQLNV = new JButton("Qu???n l?? nghi???p v???");
        btQLNV.setFont(new Font("Times new Roman", Font.BOLD, 20));
        btQLNV.setBackground(MyColor.ColorOcean);
        btQLNV.setIcon(iconRent);
        btQLNV.setHorizontalAlignment(SwingConstants.LEFT);
        btQLNV.setBorder(BorderFactory.createEmptyBorder());
        btQLNV.addActionListener(this);

        btThongKe = new JButton("Th???ng k??");
        btThongKe.setFont(new Font("Times new Roman", Font.BOLD, 20));
        btThongKe.setBackground(MyColor.ColorOcean);
        btThongKe.setIcon(iconTK);
        btThongKe.setHorizontalAlignment(SwingConstants.LEFT);
        btThongKe.setBorder(BorderFactory.createEmptyBorder());
        btThongKe.addActionListener(this);

        // JButton ????ng xu???t
        btDangXuat = new JButton("????ng xu???t");
        btDangXuat.setFont(new Font("Times new Roman", Font.BOLD, 20));
        btDangXuat.setBackground(MyColor.ColorOcean);
        btDangXuat.setIcon(iconLogout);
        btDangXuat.setHorizontalAlignment(SwingConstants.LEFT);
        btDangXuat.setBorder(BorderFactory.createEmptyBorder());
        btDangXuat.addActionListener(this);
        // JButton tho??t
        btThoat = new JButton("Tho??t");
        btThoat.setFont(new Font("Times new Roman", Font.BOLD, 20));
        btThoat.setBackground(MyColor.ColorOcean);
        btThoat.setIcon(iconExited);
        btThoat.setHorizontalAlignment(SwingConstants.LEFT);
        btThoat.setBorder(BorderFactory.createEmptyBorder());
        btThoat.addActionListener(this);

        // add Menu button
        pnMenu.add(btMenu);
        pnMenu.add(btSach);
        pnMenu.add(btMenuTimKiem);
        pnMenu.add(btMT);
        pnMenu.add(btNhapSach);
        pnMenu.add(btQLNV);
        pnMenu.add(btThongKe);
        pnMenu.add(btDangXuat);
        pnMenu.add(btThoat);
    }

    public void setTableSach() {
        // label TTSV
        lbTTSach = new JLabel("TH??NG TIN S??CH");
        lbTTSach.setFont(new Font("Arial", Font.BOLD, 35));
        lbTTSach.setHorizontalAlignment(SwingConstants.CENTER);
        lbTTSach.setVerticalAlignment(SwingConstants.TOP);
        // ----set up table----
        tblQLSACH = new JTable();
        pane = new JScrollPane(tblQLSACH);
        pane.setAutoscrolls(true);
        tblQLSACH.setRowHeight(30);
        tblQLSACH.setFont(new Font(null, 0, 13));
        tblQLSACH.setBackground(MyColor.ColorLightGray);
        tblQLSACH.getTableHeader().setBackground((MyColor.ColorSilver));
        tblQLSACH.addMouseListener(this);
        tblQLSACH.setDefaultEditor(Object.class, null);
        tblQLSACH.setSelectionBackground(MyColor.Color);

        this.add(pnTTSach);
        pnTTSach.add(lbTTSach);
        pnTTSach.add(pane);
    }

    public void setShowAll() {
        // JButtonShowAll
        btShowAll = new JButton("Hi???n th??? t???t c???");
        btShowAll.setFont(new Font("Arial", Font.BOLD, 15));
        btShowAll.setBounds(1010, 0, 130, 30);
        btShowAll.setBackground(MyColor.ColorButton);
        btShowAll.setBorder(new RoundedBorder(10));
        btShowAll.addActionListener(this);

        btSapXep = new JButton("S???p x???p theo t??n");
        btSapXep.setFont(new Font("Arial", Font.BOLD, 15));
        btSapXep.setBounds(830, 0, 150, 30);
        btSapXep.setBackground(MyColor.ColorButton);
        btSapXep.setBorder(new RoundedBorder(10));
        btSapXep.addActionListener(this);

        pnShowAll.add(btShowAll);
        pnShowAll.add(btSapXep);
    }

    public void ShowOnTable(SACH sach) {
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

    public void getInfoTextField(SACH sach) {
        sach.setMasach(txMasach.getText());
        sach.setTensach(txTensach.getText());
        sach.setMaNXB(txMaNXB.getText());
        sach.setMaTG(txMaTG.getText());
        sach.setNamXB(txNamXB.getText());
        sach.setSLtong(Integer.parseInt(txSLtong.getText()));
        sach.setSL(Integer.parseInt(txSL.getText()));
        String tmpDonGia = myTable.RemoveCommaInString(txDongia);
        sach.setDongia(Integer.parseInt(tmpDonGia));
    }

    public void getDatabase() {
        try {
            QLSACHBUS qlsachbus = new QLSACHBUS();
            if (QLSACHBUS.dssach == null)
                try {
                    qlsachbus.docDSSACH();
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            // set up header(column)
            header = new Vector<String>();
            header.add("M?? s??ch");
            header.add("T??n s??ch");
            header.add("M?? NXB");
            header.add("M?? t??c gi???");
            header.add("N??m xu???t b???n");
            header.add("SL t???ng");
            header.add("SL");
            header.add("????n gi??");
            model = new DefaultTableModel(header, 0);
            for (SACH sach : QLSACHBUS.dssach) {
                ShowOnTable(sach);
            }
            tblQLSACH.setModel(model);
        } catch (Exception e1) {
            System.out.println(e1);
        }
    }

    public void OffBTBgSelected() {
        btSach.setBackground(MyColor.ColorOcean);
        btThongKe.setBackground(MyColor.ColorOcean);
        btNhapSach.setBackground(MyColor.ColorOcean);
        btMenu.setBackground(MyColor.ColorOcean);
        btMenuTimKiem.setBackground(MyColor.ColorOcean);
        btMT.setBackground(MyColor.ColorOcean);
        btQLNV.setBackground(MyColor.ColorOcean);
        btDangXuat.setBackground(MyColor.ColorOcean);
        btThoat.setBackground(MyColor.ColorOcean);
    }

    public void setTimKiem() {
        if (btSearch == null) { // L?? button c???a ph???n t??m ki???m c?? b???n
            if (pnMT != null) {
                pnMT.setVisible(false);
            }
            lbLCTK.setVisible(true);
            lbTuKhoaTK.setVisible(true);
            comboBoxDSKhoaTK.setVisible(true);
            txKhoaTK.setVisible(true);

            TitledBorder titleTK;
            Border blackline;
            blackline = BorderFactory.createLineBorder(Color.black);
            titleTK = BorderFactory.createTitledBorder(blackline, "T??m ki???m");
            titleTK.setTitleFont(new Font("Arial", Font.BOLD, 28));
            titleTK.setTitleJustification(TitledBorder.CENTER);
            pnTimKiem.setBorder(titleTK);

            btSearch = new JButton("T??m ki???m");
            btSearch.setFont(new Font("Arial", Font.BOLD, 15));
            btSearch.setBounds(315, 165, 90, 30);
            btSearch.setBackground(MyColor.ColorButton);
            btSearch.setBorder(new RoundedBorder(10));
            btSearch.addActionListener(this);

        }
        if (pnTimKiem.isVisible() == true) {
            pnTimKiem.setVisible(false);
        } else {
            pnTimKiem.setVisible(true);
        }
        pnTimKiem.add(lbLCTK);
        pnTimKiem.add(lbTuKhoaTK);
        pnTimKiem.add(txKhoaTK);
        pnTimKiem.add(comboBoxDSKhoaTK);
        pnTimKiem.add(btSearch);
    }

    public void setTimKiemNC() {
        // ???n c??c component kh??ng d??ng t???i
        btSearch.setVisible(false);
        txKhoaTK.setVisible(false);
        // set up component m???i
        // N??m xb
        if (lbTKNam == null) {
            lbTKNam = new JLabel("N??m XB:");
            lbTKNam.setFont(new Font("Arial", Font.BOLD, 20));
            lbTKNam.setBounds(10, 170, 100, 50);
        }
        if (txTKNam == null) {
            txTKNam = new JTextField();
            txTKNam.setFont(new Font("Arial", Font.PLAIN, 15));
            txTKNam.setBounds(100, 182, 100, 30);
        }
        // SL
        if (lbTKSL == null) {
            lbTKSL = new JLabel("SL:");
            lbTKSL.setFont(new Font("Arial", Font.BOLD, 20));
            lbTKSL.setBounds(230, 172, 100, 50);
        }
        if (txTKSL == null) {
            txTKSL = new JTextField();
            txTKSL.setFont(new Font("Arial", Font.PLAIN, 15));
            txTKSL.setBounds(270, 182, 100, 30);
        }
        // button btTimKiem
        if (btTK == null) {
            btTK = new JButton("T??m ki???m");
            btTK.setFont(new Font("Arial", Font.BOLD, 15));
            btTK.setBounds(270, 220, 100, 34);
            btTK.setBackground(Color.cyan);
            btTK.setBorder(new RoundedBorder(10));
            btTK.addActionListener(this);
        }

        if (lbTKNam != null && lbTKSL != null && btTK != null && txTKNam != null && txTKSL != null) {
            lbTKNam.setVisible(true);
            txTKNam.setVisible(true);
            lbTKSL.setVisible(true);
            txTKSL.setVisible(true);
            btTK.setVisible(true);
        }
        pnTimKiem.add(lbTKNam);
        pnTimKiem.add(txTKNam);
        pnTimKiem.add(lbTKSL);
        pnTimKiem.add(txTKSL);
        pnTimKiem.add(btTK);
    }

    public void setLoc() {
        // set Border
        TitledBorder titleLoc;
        Border blackline;
        blackline = BorderFactory.createLineBorder(Color.black);
        titleLoc = BorderFactory.createTitledBorder(blackline, "L???c d??? li???u");
        titleLoc.setTitleFont(new Font("Arial", Font.BOLD, 25));
        titleLoc.setTitleJustification(TitledBorder.CENTER);

        lbNgayBD = new JLabel("Ng??y b???t ?????u: ");
        lbNgayBD.setFont(new Font("Arial", Font.BOLD, 18));
        lbNgayBD.setBounds(5, 15, 150, 80);

        lbNgayKT = new JLabel("Ng??y k???t th??c: ");
        lbNgayKT.setFont(new Font("Arial", Font.BOLD, 18));
        lbNgayKT.setBounds(5, 55, 150, 80);

        btLoc = new JButton("L???c");
        btLoc.setFont(new Font("Arial", Font.BOLD, 15));
        btLoc.setBounds(210, 120, 80, 30);
        btLoc.setBackground(MyColor.ColorButton);
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

    public void setValueCellCenter() {
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        for (int i = 0; i < model.getColumnCount(); i++) {
            if (i == 1)
                continue;
            else
                tblQLSACH.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }
    }

    public void OffPageQLSACH(Boolean x) {
        pnNhapTTSach.setVisible(x);
        pnShowAll.setVisible(x);
        pnTTSach.setVisible(x);
    }

    public void SapXep() {
        QLSACHBUS.dssach.sort(((o1, o2) -> o1.getTensach().compareTo(o2.getTensach())));
        model.setRowCount(0);
        for (SACH sach : QLSACHBUS.dssach) {
            ShowOnTable(sach);
        }
        tblQLSACH.setModel(model);
    }

}

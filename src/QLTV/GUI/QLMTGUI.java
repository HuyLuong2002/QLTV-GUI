package QLTV.GUI;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
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
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import MyCustom.BangTTIn;
import MyCustom.DateLabelFormatter;
import MyCustom.HoTroNhap;
import MyCustom.MyTable;
import MyCustom.RoundedBorder;
import MyCustom.MyColor;
import QLTV.BUS.QLCTHDTPBUS;
import QLTV.BUS.QLCTMUONBUS;
import QLTV.BUS.QLCTTRABUS;
import QLTV.BUS.QLHDTPBUS;
import QLTV.BUS.QLMUONBUS;
import QLTV.BUS.QLSACHBUS;
import QLTV.BUS.QLTRABUS;
import QLTV.DTO.CHITIETHDTIENPHAT;
import QLTV.DTO.CHITIETPHIEUMUON;
import QLTV.DTO.CHITIETPHIEUTRA;
import QLTV.DTO.HDTIENPHAT;
import QLTV.DTO.PHIEUMUON;
import QLTV.DTO.PHIEUTRASACH;
import QLTV.DTO.SACH;

public class QLMTGUI extends JFrame implements ActionListener, MouseListener, KeyListener{
    MyTable myTable = new MyTable();
    public static String NgayTra;
    JPanel pnMuonTra, pnTabMuon, pnTabTra, pnTabTienPhat, pnShowAll, pnMuon, pnCTMuon, pnNhapPM,
            pnTimKiemPM, pnLocPM, pnNhapPT, pnTimKiemPT, pnLocPT, pnTra, pnCTTra, pnHDTP, pnCTHDTP, pnNhapHDTP,
            pnTimKiemHDTP;

    JLabel lbHome, lbMaPM, lbNgayMuon, lbSLtong, lbNgayTra, lbTinhTrangMuon,
            lbMaDG, lbLCTK, lbTuKhoaTK, lbNhapPM, lbNhapCTPM, lbCTPMMaPM, lbCTPMMaSach, lbCTPMSL, lbKQTK;

    JLabel lbNhapTra, lbNhapCTPT, lbCTPTMaPT, lbCTPTMaSach, lbMaPT, lbNgayTraPT,
            lbTinhTrangSach, lbTienThue, lbThanhTien, lbMaPMTra;

    JLabel lbNhapHDTP, lbNhapCTHDTP, lbCTPTSL, lbMaHD, lbMaHD_DG, lbSLTongHD, lbTienPhat,
            lbCTHDMaHD, lbCTHDMaSach, lbCTHDSL, lbCTHDDonGia, lbTienTra, lbTienThua, lbTraTienPhat;

    JLabel lbNgayBDLocPM, lbNgayKTLocPM, lbNgayBDLocPT, lbNgayKTLocPT;
    JButton btThoat, btSuaPM, btThemPM, btXoa, btHoanTac,
            btSuaCTPM, btThemCTPM, btThemPT, btSuaPT, btThemCTPT, btSuaCTPT, btThemHDTP, btSuaHDTP, btThemCTHD,
            btSuaCTHD;
    JTextField txMaPM, txSLtong, txKhoaTK, txTienTra_M, txTienTra_HD, txTienThua, txTraTienPhat;
    public static JTextField txCTPMMaPM, txCTPMMaSach, txCTPMSL, txCTPTMaPT, txCTPTMaSach, txCTHDMaHD, txCTHDMaSach,
            txCTHDSL, txCTHDDonGia, txCTPTSL, txMaDG, txMaPMTra;
    JComboBox<String> cbTinhTrangMuon, cbDSKhoaTK;
    JLabel lbLCTKPM, lbTuKhoaTKPM, lbLCTKPT, lbTuKhoaTKPT,
            lbLCTKHD, lbTuKhoaTKHD;
    JButton btMenu, btSach, btMT, btQLNV, btDangXuat, btNhapSach, btMenuTimKiem, btThongKe;
    JButton btHoTroNhapMaPM, btHoTroNhapMaDG, btHoTroNhapPM_Tra, btHoTroNhapMasachPM, btHoTroNhapMasachPT,
            btHoTroNhapMasachHD,
            btHoTroNhapMaPT, btHoTroNhapMaHD, btShowAll,
            btTimKiemPM, btLocPM, btInPM, btTimKiemPT, btLocPT, btInPT,
            btTimKiemHDTP, btInHDTP;
    JTextField txKhoaTKPM, txKhoaTKPT, txKhoaTKHDTP, txMaPT, txTienThue, txMaHD, txMaHD_DG,
            txSLTongHD, txTienPhat;
    public static JTextField txThanhTien;
    JComboBox<String> cbTinhTrangTra, cbDSKhoaTKPM, cbDSKhoaTKPT, cbDSKhoaTKHDTP;

    TitledBorder titleMuon, titleTra, titleHDTP, titleCTHDTP;
    JTabbedPane tabbedPane;

    public JTable tblQLMuon, tblQLCTMuon, tblQLTra, tblQLCTTra, tblQLHDTP, tblQLCTHDTP;
    public DefaultTableModel modelMuon, modelCTMuon, modelTra, modelCTTra, modelHDTP, modelCTHDTP;
    // Lọc dữ liệu
    UtilDateModel modelNgayBDMuon, modelNgayKTMuon;
    Properties pNgayBDMuon, pNgayKTMuon;
    JDatePanelImpl datePanelNgayBDMuon, datePanelNgayKTMuon;
    JDatePickerImpl datePickerNgayBDMuon, datePickerNgayKTMuon;

    UtilDateModel modelNgayBDTra, modelNgayKTTra;
    Properties pNgayBDTra, pNgayKTTra;
    JDatePanelImpl datePanelNgayBDTra, datePanelNgayKTTra;
    JDatePickerImpl datePickerNgayBDTra, datePickerNgayKTTra;

    UtilDateModel modelNgayBDPT;
    Properties pNgayBDPT;
    JDatePanelImpl datePanelNgayBDPT;
    JDatePickerImpl datePickerNgayBDPT;

    UtilDateModel modelNgayBDPM, modelNgayKTPM;
    Properties pNgayBDPM, pNgayKTPM;
    JDatePanelImpl datePanelNgayBDPM, datePanelNgayKTPM;
    JDatePickerImpl datePickerNgayBDPM, datePickerNgayKTPM;

    Vector<String> header;

    public QLMTGUI() {
    }

    public JPanel setMTGUI() {
        if (pnMuonTra == null) {
            pnMuonTra = new JPanel();
            pnMuonTra.setBounds(240, 0, 1145, 800);
            pnMuonTra.setLayout(null);
            pnMuonTra.setBackground(MyColor.ColorBlue);

            // panel tab mượn
            pnTabMuon = new JPanel();
            pnTabMuon.setLayout(new GridLayout(1, 2, 5, 0));
            pnTabMuon.setBackground(MyColor.ColorBlue);

            pnMuon = new JPanel();
            pnMuon.setLayout(new GridLayout(1, 1));
            pnMuon.setBackground(MyColor.ColorBlue);

            pnCTMuon = new JPanel();
            pnCTMuon.setLayout(new GridLayout(1, 1));
            pnCTMuon.setBackground(MyColor.ColorBlue);

            pnShowAll = new JPanel();
            pnShowAll.setBounds(5, 353, 1135, 40);
            pnShowAll.setLayout(null);
            pnShowAll.setBackground(MyColor.ColorBlue);

            pnNhapPM = new JPanel();
            pnNhapPM.setLayout(null);
            pnNhapPM.setBounds(5, 405, 1135, 370);
            pnNhapPM.setBackground(MyColor.ColorBlue);

            pnTimKiemPM = new JPanel();
            pnTimKiemPM.setLayout(null);
            pnTimKiemPM.setBounds(720, 155, 413, 200);
            pnTimKiemPM.setBackground(MyColor.ColorBlue);

            pnLocPM = new JPanel();
            pnLocPM.setLayout(null);
            pnLocPM.setBounds(720, 0, 300, 155);
            pnLocPM.setBackground(MyColor.ColorBlue);

            // panel tab trả
            pnTabTra = new JPanel();
            pnTabTra.setLayout(new GridLayout(1, 2, 5, 0));
            pnTabTra.setBackground(MyColor.ColorBlue);

            pnTra = new JPanel();
            pnTra.setLayout(new GridLayout(1, 1));
            pnTra.setBackground(MyColor.ColorBlue);

            pnCTTra = new JPanel();
            pnCTTra.setLayout(new GridLayout(1, 1));
            pnCTTra.setBackground(MyColor.ColorBlue);

            pnNhapPT = new JPanel();
            pnNhapPT.setLayout(null);
            pnNhapPT.setVisible(false);
            pnNhapPT.setBounds(5, 405, 1135, 370);
            pnNhapPT.setBackground(MyColor.ColorBlue);

            pnTimKiemPT = new JPanel();
            pnTimKiemPT.setLayout(null);
            pnTimKiemPT.setBounds(720, 155, 413, 200);
            pnTimKiemPT.setBackground(MyColor.ColorBlue);

            pnLocPT = new JPanel();
            pnLocPT.setLayout(null);
            pnLocPT.setBounds(720, 0, 300, 155);
            pnLocPT.setBackground(MyColor.ColorBlue);

            // panel tab tiền phạt
            pnTabTienPhat = new JPanel();
            pnTabTienPhat.setLayout(new GridLayout(1, 2, 5, 0));
            pnTabTienPhat.setBackground(MyColor.ColorBlue);

            pnHDTP = new JPanel();
            pnHDTP.setLayout(new GridLayout(1, 1));
            pnHDTP.setBackground(MyColor.ColorBlue);

            pnCTHDTP = new JPanel();
            pnCTHDTP.setLayout(new GridLayout(1, 1));
            pnCTHDTP.setBackground(MyColor.ColorBlue);

            pnNhapHDTP = new JPanel();
            pnNhapHDTP.setLayout(null);
            pnNhapHDTP.setVisible(false);
            pnNhapHDTP.setBounds(5, 405, 1135, 370);
            pnNhapHDTP.setBackground(MyColor.ColorBlue);

            pnTimKiemHDTP = new JPanel();
            pnTimKiemHDTP.setLayout(null);
            pnTimKiemHDTP.setBounds(720, 155, 413, 200);
            pnTimKiemHDTP.setBackground(MyColor.ColorBlue);


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
            pnMuonTra.add(pnNhapHDTP);

            pnTabMuon.add(pnMuon);
            pnTabMuon.add(pnCTMuon);

            pnTabTra.add(pnTra);
            pnTabTra.add(pnCTTra);

            pnTabTienPhat.add(pnHDTP);
            pnTabTienPhat.add(pnCTHDTP);

            pnNhapPM.add(pnTimKiemPM);
            pnNhapPM.add(pnLocPM);

            pnNhapPT.add(pnTimKiemPT);
            pnNhapPT.add(pnLocPT);

            pnNhapHDTP.add(pnTimKiemHDTP);

            // Phiếu mượn, chi tiết mượn
            setTitlePM();
            setTableMuon();
            setTableCTMuon();
            setShowAll();
            setInputMuon();
            setInputCTPM();
            setTimKiemPM();
            setLocPM();
            getDBMuon();
            getDBCTPM();
            myTable.setValueCellCenter(modelMuon, tblQLMuon);
            myTable.setValueCellCenter(modelCTMuon, tblQLCTMuon);

            // Phiếu trả, chi tiết phiếu trả
            setTitlePT();
            setTableTra();
            setTableCTTra();
            setInputTra();
            setInputCTPT();
            setTimKiemPT();
            setLocPT();
            getDBTra();
            getDBCTPT();
            myTable.setValueCellCenter(modelTra, tblQLTra);
            myTable.setValueCellCenter(modelCTTra, tblQLCTTra);

            // Hóa đơn tiền phạt, chi tiết hóa đơn
            setTitleHDTP();
            setTableHDTP();
            setTableCTHDTP();
            setInputHDTP();
            setInputCTHDTP();
            setTimKiemHDTP();
            getDBHDTP();
            getDBCTHDTP();
            myTable.setValueCellCenter(modelHDTP, tblQLHDTP);
            myTable.setValueCellCenter(modelCTHDTP, tblQLCTHDTP);

        }
        return pnMuonTra;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btTimKiemPM) {
            int vtkey = Integer.parseInt(String.valueOf(cbDSKhoaTKPM.getSelectedIndex()));
            String tukhoa = txKhoaTKPM.getText().toLowerCase().replaceAll("\\s+", "").trim();
            if (tukhoa.equals("") == true) {
                JOptionPane.showMessageDialog(null, "Xin mời nhập từ khóa", "Lỗi", JOptionPane.ERROR_MESSAGE);
            } else if (vtkey == 0) {
                JOptionPane.showMessageDialog(null, "Xin mời lựa chọn khóa tìm kiếm", "Lỗi", JOptionPane.ERROR_MESSAGE);
            } else {
                QLMUONBUS qlsachbus = new QLMUONBUS();
                if (vtkey == 1) {
                    PHIEUMUON kq = qlsachbus.timTheoMa(tukhoa);
                    modelMuon.setRowCount(0);
                    if (kq != null) {
                        ShowOnTablePM(kq);
                        tblQLMuon.setModel(modelMuon);
                    } else {
                        JOptionPane.showMessageDialog(null, "Không tìm thấy dữ liệu phù hợp", "Lỗi",
                                JOptionPane.ERROR_MESSAGE);
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
                    ArrayList<PHIEUMUON> kq = qlsachbus.timTheoTinhTrangMuon(tukhoa);
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
                    ArrayList<PHIEUMUON> kq = qlsachbus.timTheoMaDG(tukhoa);
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
        if (e.getSource() == btTimKiemPT) {
            int vtkey = Integer.parseInt(String.valueOf(cbDSKhoaTKPT.getSelectedIndex()));
            String tukhoa = txKhoaTKPT.getText().toLowerCase().replaceAll("\\s+", "").trim();
            if (tukhoa.equals("") == true) {
                JOptionPane.showMessageDialog(null, "Xin mời nhập từ khóa", "Lỗi", JOptionPane.ERROR_MESSAGE);
            } else if (vtkey == 0) {
                JOptionPane.showMessageDialog(null, "Xin mời lựa chọn khóa tìm kiếm", "Lỗi", JOptionPane.ERROR_MESSAGE);
            } else {
                QLTRABUS qltrabus = new QLTRABUS();
                if (vtkey == 1) {
                    PHIEUTRASACH kq = qltrabus.timTheoMaPT(tukhoa);
                    modelTra.setRowCount(0);
                    if (kq != null) {
                        ShowOnTablePT(kq);
                        tblQLTra.setModel(modelTra);
                    } else {
                        JOptionPane.showMessageDialog(null, "Không tìm thấy dữ liệu phù hợp", "Lỗi",
                                JOptionPane.ERROR_MESSAGE);
                    }
                }
                if (vtkey == 2) {
                    ArrayList<PHIEUTRASACH> kq = qltrabus.timTheoTinhTrangSach(tukhoa);
                    modelTra.setRowCount(0);
                    if (kq.size() == 0) {
                        JOptionPane.showMessageDialog(null, "Không tìm thấy dữ liệu phù hợp", "Lỗi",
                                JOptionPane.ERROR_MESSAGE);
                    } else {
                        for (PHIEUTRASACH pt : kq) {
                            ShowOnTablePT(pt);
                        }
                        tblQLTra.setModel(modelTra);
                    }
                }
                if (vtkey == 3) {
                    ArrayList<PHIEUTRASACH> kq = qltrabus.timTheoThanhTien(tukhoa);
                    modelTra.setRowCount(0);
                    if (kq.size() == 0) {
                        JOptionPane.showMessageDialog(null, "Không tìm thấy dữ liệu phù hợp", "Lỗi",
                                JOptionPane.ERROR_MESSAGE);
                    } else {
                        for (PHIEUTRASACH pt : kq) {
                            ShowOnTablePT(pt);
                        }
                        tblQLTra.setModel(modelTra);
                    }
                }
                if (vtkey == 4) {
                    ArrayList<PHIEUTRASACH> kq = qltrabus.timTheoMaPM(tukhoa);
                    modelTra.setRowCount(0);
                    if (kq.size() == 0) {
                        JOptionPane.showMessageDialog(null, "Không tìm thấy dữ liệu phù hợp", "Lỗi",
                                JOptionPane.ERROR_MESSAGE);
                    } else {
                        for (PHIEUTRASACH pt : kq)
                            ShowOnTablePT(pt);
                        tblQLTra.setModel(modelTra);
                    }
                }
            }
        }
        if (e.getSource() == btTimKiemHDTP) {
            int vtkey = Integer.parseInt(String.valueOf(cbDSKhoaTKHDTP.getSelectedIndex()));
            String tukhoa = txKhoaTKHDTP.getText().toLowerCase().replaceAll("\\s+", "").trim();
            if (tukhoa.equals("") == true) {
                JOptionPane.showMessageDialog(null, "Xin mời nhập từ khóa", "Lỗi", JOptionPane.ERROR_MESSAGE);
            } else if (vtkey == 0) {
                JOptionPane.showMessageDialog(null, "Xin mời lựa chọn khóa tìm kiếm", "Lỗi", JOptionPane.ERROR_MESSAGE);
            } else {
                QLHDTPBUS qlhoadonbus = new QLHDTPBUS();
                if (vtkey == 1) {
                    HDTIENPHAT kq = qlhoadonbus.timTheoMaHD(tukhoa);
                    modelHDTP.setRowCount(0);
                    if (kq != null) {
                        ShowOnTableHD(kq);
                        tblQLHDTP.setModel(modelHDTP);
                    } else {
                        JOptionPane.showMessageDialog(null, "Không tìm thấy dữ liệu phù hợp", "Lỗi",
                                JOptionPane.ERROR_MESSAGE);
                    }
                }
                if (vtkey == 2) {
                    ArrayList<HDTIENPHAT> kq = qlhoadonbus.timTheoMaDG(tukhoa);
                    modelHDTP.setRowCount(0);
                    if (kq.size() == 0) {
                        JOptionPane.showMessageDialog(null, "Không tìm thấy dữ liệu phù hợp", "Lỗi",
                                JOptionPane.ERROR_MESSAGE);
                    } else {
                        for (HDTIENPHAT hd : kq) {
                            ShowOnTableHD(hd);
                        }
                        tblQLHDTP.setModel(modelHDTP);
                    }
                }
                if (vtkey == 3) {
                    ArrayList<HDTIENPHAT> kq = qlhoadonbus.timTheoSLtong(tukhoa);
                    modelHDTP.setRowCount(0);
                    if (kq.size() == 0) {
                        JOptionPane.showMessageDialog(null, "Không tìm thấy dữ liệu phù hợp", "Lỗi",
                                JOptionPane.ERROR_MESSAGE);
                    } else {
                        for (HDTIENPHAT hd : kq) {
                            ShowOnTableHD(hd);
                        }
                        tblQLHDTP.setModel(modelHDTP);
                    }
                }
                if (vtkey == 4) {
                    ArrayList<HDTIENPHAT> kq = qlhoadonbus.timTheoTienPhat(tukhoa);
                    modelHDTP.setRowCount(0);
                    if (kq.size() == 0) {
                        JOptionPane.showMessageDialog(null, "Không tìm thấy dữ liệu phù hợp", "Lỗi",
                                JOptionPane.ERROR_MESSAGE);
                    } else {
                        for (HDTIENPHAT hd : kq) {
                            ShowOnTableHD(hd);
                        }
                        tblQLHDTP.setModel(modelHDTP);
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
            BangTTIn ttpm = new BangTTIn();
            ttpm.setTTPM();
        }
        if (e.getSource() == btInHDTP) {
            BangTTIn ttpm = new BangTTIn();
            ttpm.setTTHD();
        }
        if (e.getSource() == btThemPM) {
            PHIEUMUON phieumuon = new PHIEUMUON();
            getInfoTextFieldPM(phieumuon);
            // Truy cập vào bus
            QLMUONBUS qlphieumuonbus = new QLMUONBUS();
            int kiemtra = -1;
            try {
                kiemtra = qlphieumuonbus.them(phieumuon);
            } catch (Exception e1) {
                e1.printStackTrace();
            }
            if (kiemtra == 0) {
                // Đưa dữ liệu lên table
                header = new Vector<String>();
                header.add("Mã phiếu mượn");
                header.add("Ngày mượn");
                header.add("Số lượng tổng");
                header.add("Ngày trả");
                header.add("Tình trạng mượn");
                header.add("Mã độc giả");
                if (modelMuon.getRowCount() == 0) {
                    modelMuon = new DefaultTableModel(header, 0);
                }
                ShowOnTablePM(phieumuon);
                tblQLMuon.setModel(modelMuon);
            }

        }
        if (e.getSource() == btSuaPM) {
            int i = tblQLMuon.getSelectedRow();
            int kt = -1;
            if (i >= 0) {
                PHIEUMUON phieumuon = new PHIEUMUON();
                PHIEUMUON MaPMCu = QLMUONBUS.dspm.get(i);
                getInfoTextFieldPM(phieumuon);
                try {
                    QLMUONBUS qlphieumuonbus = new QLMUONBUS();
                    kt = qlphieumuonbus.sua(phieumuon, MaPMCu, i);
                } catch (Exception e1) {
                    System.out.println(e1);
                }
                if (kt == 0) {
                    modelMuon.setValueAt(phieumuon.getMaPM(), i, 0);
                    modelMuon.setValueAt(phieumuon.getNgaymuon(), i, 1);
                    modelMuon.setValueAt(phieumuon.getSLtong(), i, 2);
                    modelMuon.setValueAt(phieumuon.getNgaytra(), i, 3);
                    modelMuon.setValueAt(phieumuon.getTinhTrangMuon(), i, 4);
                    modelMuon.setValueAt(phieumuon.getMaDG(), i, 5);
                    tblQLMuon.setModel(modelMuon);
                }
            }
        }
        if (e.getSource() == btThemCTPM) {
            int i = tblQLMuon.getSelectedRow();
            if (i >= 0) {
                CHITIETPHIEUMUON ctphieumuon = new CHITIETPHIEUMUON();
                getInfoTextFieldCTPM(ctphieumuon);
                if (!ctphieumuon.getMaPM().trim().equals(String.valueOf(modelMuon.getValueAt(i, 0)))) {
                    JOptionPane.showMessageDialog(null,
                            "Mã phiếu mượn tại bảng chi tiết khác với mã phiếu mượn trên table", "Lỗi",
                            JOptionPane.ERROR_MESSAGE);
                } else {
                    // Truy cập vào bus
                    int ktSachConLai = checkSLSachConLai(txCTPMMaSach.getText().trim());
                    if (ktSachConLai == 0) {
                        QLCTMUONBUS qlctphieumuonbus = new QLCTMUONBUS();
                        int kiemtra = -1;
                        try {
                            kiemtra = qlctphieumuonbus.them(ctphieumuon);
                        } catch (Exception e1) {
                            e1.printStackTrace();
                        }
                        if (kiemtra == 0) {
                            // Cập nhật số lượng bên kho sách
                            updateSLSachMuon(txCTPMMaSach.getText().trim());
                            // Đưa dữ liệu lên table
                            header = new Vector<String>();
                            header.add("Mã phiếu mượn");
                            header.add("Mã sách");
                            header.add("SL");
                            if (modelCTMuon.getRowCount() == 0) {
                                modelCTMuon = new DefaultTableModel(header, 0);
                                myTable.setValueCellCenter(modelCTMuon, tblQLCTMuon);
                            }
                            ShowOnTableCTPM(ctphieumuon);
                            tblQLCTMuon.setModel(modelCTMuon);
                        }
                    }
                }
            } else {
                JOptionPane.showMessageDialog(null, "Mời nhấp vào mã phiếu mượn trên bảng phiếu mượn trước khi thêm",
                        "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        }
        if (e.getSource() == btSuaCTPM) {
            int i = tblQLCTMuon.getSelectedRow();
            int kt = -1;
            if (i >= 0) {
                CHITIETPHIEUMUON ctphieumuon = new CHITIETPHIEUMUON();
                String MaPMCTPMCu = String.valueOf(modelCTMuon.getValueAt(i, 0));
                String MasachCTPMCu = String.valueOf(modelCTMuon.getValueAt(i, 1));
                getInfoTextFieldCTPM(ctphieumuon);
                try {
                    QLCTMUONBUS qlctphieumuonbus = new QLCTMUONBUS();
                    kt = qlctphieumuonbus.sua(ctphieumuon, MaPMCTPMCu, MasachCTPMCu, i);
                } catch (Exception e1) {
                    System.out.println(e1);
                }
                if (kt == 0) {
                    modelCTMuon.setValueAt(ctphieumuon.getMaPM(), i, 0);
                    modelCTMuon.setValueAt(ctphieumuon.getMasach(), i, 1);
                    modelCTMuon.setValueAt(ctphieumuon.getSL(), i, 2);
                    tblQLCTMuon.setModel(modelCTMuon);
                }
            }
        }
        if (e.getSource() == btThemPT) {
            try {
                PHIEUTRASACH phieutra = new PHIEUTRASACH();
                getInfoTextFieldPT(phieutra);
                // Truy cập vào bus
                QLTRABUS qlphieutrabus = new QLTRABUS();
                int kiemtra = -1;
                try {
                    kiemtra = qlphieutrabus.them(phieutra);
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
                if (kiemtra == 0) {
                    // Đưa dữ liệu lên table
                    header = new Vector<String>();
                    header.add("Mã phiếu trả");
                    header.add("Ngày trả");
                    header.add("Tình trạng sách");
                    header.add("Tiền thuê");
                    header.add("Thành tiền");
                    header.add("Mã phiếu mượn");
                    if (modelTra.getRowCount() == 0) {
                        modelTra = new DefaultTableModel(header, 0);
                    }
                    ShowOnTablePT(phieutra);
                    tblQLTra.setModel(modelTra);
                }
            } catch (Exception e1) {
                System.out.println(e1);
            }
        }
        if (e.getSource() == btSuaPT) {
            int i = tblQLTra.getSelectedRow();
            int kt = -1;
            if (i >= 0) {
                PHIEUTRASACH phieutrasach = new PHIEUTRASACH();
                PHIEUTRASACH MaPTCu = QLTRABUS.dspt.get(i);   // Lấy thông tin cũ
                getInfoTextFieldPT(phieutrasach);             // Lấy thông tin mới
                try {
                    QLTRABUS qlphieutrabus = new QLTRABUS();
                    kt = qlphieutrabus.sua(phieutrasach, MaPTCu, i);
                    // Set trong câu truy vấn
                } catch (Exception e1) {
                    System.out.println(e1);
                }
                if (kt == 0) {
                    modelTra.setValueAt(phieutrasach.getMaPT(), i, 0);
                    modelTra.setValueAt(phieutrasach.getNgaytra(), i, 1);
                    modelTra.setValueAt(phieutrasach.getTinhtrangsach(), i, 2);
                    modelTra.setValueAt(String.format("%,d", phieutrasach.getTienthue()), i, 3);
                    modelTra.setValueAt(String.format("%,d", phieutrasach.getThanhtien()), i, 4);
                    modelTra.setValueAt(phieutrasach.getMaPM(), i, 5);
                    tblQLTra.setModel(modelTra);
                }
            }
        }
        if (e.getSource() == btThemCTPT) {
            int i = tblQLTra.getSelectedRow();
            try {
                int ktSachTraDu = checkSLSachTra(txCTPMMaSach.getText().trim());
                // Kiểm tra số lượng sách trả có bị dư không
                if (ktSachTraDu == 0) {
                    CHITIETPHIEUTRA ctphieutra = new CHITIETPHIEUTRA();
                    getInfoTextFieldCTPT(ctphieutra);
                    if (!ctphieutra.getMaPT().trim().equals(String.valueOf(modelTra.getValueAt(i, 0)))) {
                        JOptionPane.showMessageDialog(null, "Mã phiếu trả tại bảng chi tiết khác với mã phiếu trả",
                                "Lỗi",
                                JOptionPane.ERROR_MESSAGE);
                    } else {
                        // Truy cập vào bus
                        QLCTTRABUS qlctphieutrabus = new QLCTTRABUS();
                        int kiemtra = 0;
                        try {
                            kiemtra = qlctphieutrabus.them(ctphieutra);
                        } catch (Exception e1) {
                            e1.printStackTrace();
                        }
                        if (kiemtra == 0) {
                            updateSLSachTra(txCTPTMaSach.getText().trim());
                            // Đưa dữ liệu lên table
                            header = new Vector<String>();
                            header.add("Mã phiếu trả");
                            header.add("Mã sách");
                            header.add("SL");
                            if (modelCTTra.getRowCount() == 0) {
                                modelCTTra = new DefaultTableModel(header, 0);
                            }
                            ShowOnTableCTPT(ctphieutra);
                            tblQLCTTra.setModel(modelCTTra);
                        }
                    }
                }
            } catch (Exception e1) {
                System.out.println(e1);
            }
        }
        if (e.getSource() == btSuaCTPT) {
            int i = tblQLCTTra.getSelectedRow();
            int kt = -1;
            if (i >= 0) {
                CHITIETPHIEUTRA ctphieutra = new CHITIETPHIEUTRA();
                String MaCTPTCu = String.valueOf(modelCTTra.getValueAt(i, 0));
                String MaSachCTPTCu = String.valueOf(modelCTTra.getValueAt(i, 1));
                getInfoTextFieldCTPT(ctphieutra);
                try {
                    QLCTTRABUS qlctphieutrabus = new QLCTTRABUS();
                    kt = qlctphieutrabus.sua(ctphieutra, MaCTPTCu, MaSachCTPTCu, i);
                } catch (Exception e1) {
                    System.out.println(e1);
                }
                if (kt == 0) {
                    modelCTTra.setValueAt(ctphieutra.getMaPT(), i, 0);
                    modelCTTra.setValueAt(ctphieutra.getMasach(), i, 1);
                    modelCTTra.setValueAt(ctphieutra.getSL(), i, 2);
                    tblQLCTTra.setModel(modelCTTra);
                }
            }
        }
        if (e.getSource() == btThemHDTP) {
            try {
                HDTIENPHAT hdtienphat = new HDTIENPHAT();
                getInfoTextFieldHD(hdtienphat);
                // Truy cập vào bus
                QLHDTPBUS qlhoadonbus = new QLHDTPBUS();
                int kiemtra = -1;
                try {
                    kiemtra = qlhoadonbus.them(hdtienphat);
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
                if (kiemtra == 0) {
                    // Đưa dữ liệu lên table
                    header = new Vector<String>();
                    header.add("Mã hóa đơn");
                    header.add("Mã độc giả");
                    header.add("Số lượng tổng");
                    header.add("Tiền phạt");
                    if (modelHDTP.getRowCount() == 0) {
                        modelHDTP = new DefaultTableModel(header, 0);
                    }
                    ShowOnTableHD(hdtienphat);
                    tblQLHDTP.setModel(modelHDTP);
                }
            } catch (Exception e1) {
                System.out.println(e1);
            }
        }
        if (e.getSource() == btSuaHDTP) {
            int i = tblQLHDTP.getSelectedRow();
            int kt = -1;
            if (i >= 0) {
                HDTIENPHAT hdtienphat = new HDTIENPHAT();
                HDTIENPHAT MaHDCu = QLHDTPBUS.dshdtp.set(i, hdtienphat);
                getInfoTextFieldHD(hdtienphat);
                try {
                    QLHDTPBUS qlhoadonbus = new QLHDTPBUS();
                    kt = qlhoadonbus.sua(hdtienphat, MaHDCu, i);
                } catch (Exception e1) {
                    System.out.println(e1);
                }
                if (kt == 0) {
                    modelHDTP.setValueAt(hdtienphat.getMaHD(), i, 0);
                    modelHDTP.setValueAt(hdtienphat.getMaDG(), i, 1);
                    modelHDTP.setValueAt(hdtienphat.getSL(), i, 2);
                    modelHDTP.setValueAt(String.format("%,d", hdtienphat.getTienphat()), i, 3);
                    tblQLHDTP.setModel(modelHDTP);
                }
            }
        }
        if (e.getSource() == btThemCTHD) {
            int i = tblQLHDTP.getSelectedRow();
            try {
                CHITIETHDTIENPHAT chitiethdtienphat = new CHITIETHDTIENPHAT();
                getInfoTextFieldCTHD(chitiethdtienphat);
                if (!chitiethdtienphat.getMaHD().trim().equals(String.valueOf(modelHDTP.getValueAt(i, 0)))) {
                    JOptionPane.showMessageDialog(null, "Mã hóa đơn tại bảng chi tiết khác với mã hóa đơn", "Lỗi",
                            JOptionPane.ERROR_MESSAGE);
                } else {
                    // Truy cập vào bus
                    QLCTHDTPBUS qlcthoadonbus = new QLCTHDTPBUS();
                    int kiemtra = 0;
                    try {
                        kiemtra = qlcthoadonbus.them(chitiethdtienphat);
                    } catch (Exception e1) {
                        e1.printStackTrace();
                    }
                    if (kiemtra == 0) {
                        // Đưa dữ liệu lên table
                        header = new Vector<String>();
                        header.add("Mã phiếu trả");
                        header.add("Mã sách");
                        header.add("Số lượng");
                        header.add("Đơn giá");
                        if (modelCTHDTP.getRowCount() == 0) {
                            modelCTHDTP = new DefaultTableModel(header, 0);
                        }
                        ShowOnTableCTHD(chitiethdtienphat);
                        tblQLCTHDTP.setModel(modelCTHDTP);
                    }
                }
            } catch (Exception e1) {
                System.out.println(e1);
            }
        }
        if (e.getSource() == btSuaCTHD) {
            int i = tblQLCTHDTP.getSelectedRow();
            int kt = -1;
            if (i >= 0) {
                CHITIETHDTIENPHAT chitiethdtienphat = new CHITIETHDTIENPHAT();
                String MaCTHDCu = String.valueOf(modelCTHDTP.getValueAt(i, 0));
                String MaSachCTHDCu = String.valueOf(modelCTHDTP.getValueAt(i, 1));
                getInfoTextFieldCTHD(chitiethdtienphat);
                try {
                    QLCTHDTPBUS qlcthoadonbus = new QLCTHDTPBUS();
                    kt = qlcthoadonbus.sua(chitiethdtienphat, MaCTHDCu, MaSachCTHDCu, i);
                } catch (Exception e1) {
                    System.out.println(e1);
                }
                if (kt == 0) {
                    modelCTHDTP.setValueAt(chitiethdtienphat.getMaHD(), i, 0);
                    modelCTHDTP.setValueAt(chitiethdtienphat.getMasach(), i, 1);
                    modelCTHDTP.setValueAt(chitiethdtienphat.getSL(), i, 2);
                    modelCTHDTP.setValueAt(String.format("%,d", chitiethdtienphat.getDongia()), i, 3);
                    tblQLCTHDTP.setModel(modelCTHDTP);
                }
            }
        }
        if (e.getSource() == btLocPM) {
            String tmp = datePickerNgayBDMuon.getJFormattedTextField().getText().replaceAll("-", "");
            String tmp1 = datePickerNgayKTMuon.getJFormattedTextField().getText().replaceAll("-", "");
            int BDMuon = Integer.parseInt(tmp);
            int KTMuon = Integer.parseInt(tmp1);
            QLMUONBUS qlmuonbus = new QLMUONBUS();
            ArrayList<PHIEUMUON> kq = qlmuonbus.LocPM(BDMuon, KTMuon);
            modelMuon.setRowCount(0);
            if (kq.size() == 0) {
                JOptionPane.showMessageDialog(null, "Kết quả lọc không thỏa điều kiện", "Lỗi",
                        JOptionPane.ERROR_MESSAGE);
            } else {
                for (PHIEUMUON pm : kq) {
                    ShowOnTablePM(pm);
                }
                tblQLMuon.setModel(modelMuon);
                JOptionPane.showMessageDialog(null,
                        "Kết quả lọc: " + modelMuon.getRowCount() + " Phiếu mượn có ngày mượn thỏa!");
            }
        }
        if (e.getSource() == btLocPT) {
            String tmp = datePickerNgayBDTra.getJFormattedTextField().getText().replaceAll("-", "");
            String tmp1 = datePickerNgayKTTra.getJFormattedTextField().getText().replaceAll("-", "");
            int BDTra = Integer.parseInt(tmp);
            int KTTra = Integer.parseInt(tmp1);
            QLTRABUS qltrabus = new QLTRABUS();
            ArrayList<PHIEUTRASACH> kq = qltrabus.LocPT(BDTra, KTTra);
            modelTra.setRowCount(0);
            if (kq.size() == 0)
                JOptionPane.showMessageDialog(null, "Kết quả lọc không thỏa điều kiện", "Lỗi",
                        JOptionPane.ERROR_MESSAGE);
            else {
                for (PHIEUTRASACH pt : kq)
                    ShowOnTablePT(pt);
                tblQLTra.setModel(modelTra);
                JOptionPane.showMessageDialog(null,
                        "Kết quả lọc: " + modelTra.getRowCount() + " Phiếu trả có ngày trả thỏa!");
            }
        }
        if (e.getSource() == btHoTroNhapMaPM) {
            HoTroNhap hoTroNhapMaPM = new HoTroNhap();
            hoTroNhapMaPM.setHoTroNhapCTPM();
        }
        if (e.getSource() == btHoTroNhapMaPT) {
            HoTroNhap hoTroNhapMaPT = new HoTroNhap();
            hoTroNhapMaPT.setHoTroNhapCTPT();
        }
        if (e.getSource() == btHoTroNhapMaHD) {
            HoTroNhap hoTroNhapMaHD = new HoTroNhap();
            hoTroNhapMaHD.setHoTroNhapCTHDTP();
        }
        if (e.getSource() == btHoTroNhapMasachPM) {
            HoTroNhap hoTroNhapMaSachPM = new HoTroNhap();
            hoTroNhapMaSachPM.setHoTroNhapMasach();
        }
        if (e.getSource() == btHoTroNhapMasachPT) {
            HoTroNhap hoTroNhapMaSachPT = new HoTroNhap();
            hoTroNhapMaSachPT.setHoTroNhapMasach();
        }
        if (e.getSource() == btHoTroNhapMasachHD) {
            HoTroNhap hoTroNhapMaSachHD = new HoTroNhap();
            hoTroNhapMaSachHD.setHoTroNhapMasach();
        }
        if (e.getSource() == btHoTroNhapMaDG) {
            HoTroNhap hoTroNhapMaDG = new HoTroNhap();
            hoTroNhapMaDG.setHoTroNhapMaDG();
        }
        if (e.getSource() == btHoTroNhapPM_Tra) {
            HoTroNhap hoTroNhapMaPM_Tra = new HoTroNhap();
            NgayTra = datePickerNgayBDPT.getJFormattedTextField().getText();
            hoTroNhapMaPM_Tra.setHoTroNhapPM_TRA();
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
                    if (ctpm.getMaPM().trim().indexOf(pm.getMaPM().trim()) >= 0) {
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
                txMaPM.setText(pmTextField.getMaPM().replaceAll("\\s+", " ").trim());

                String tmp[] = pmTextField.getNgaymuon().split("-");
                datePanelNgayBDPM.getModel().setDate(Integer.parseInt(tmp[0]), Integer.parseInt(tmp[1]),
                        Integer.parseInt(tmp[2]));

                txSLtong.setText(String.valueOf(pmTextField.getSLtong()).trim());
                String tmp1[] = pmTextField.getNgaytra().split("-");

                datePanelNgayKTPM.getModel().setDate(Integer.parseInt(tmp1[0]), Integer.parseInt(tmp1[1]),
                        Integer.parseInt(tmp1[2]));
                if (pmTextField.getTinhTrangMuon().equals("Đang mượn")) {
                    cbTinhTrangMuon.setSelectedIndex(1);
                }
                if (pmTextField.getTinhTrangMuon().equals("Hết mượn")) {
                    cbTinhTrangMuon.setSelectedIndex(2);
                }
                txMaDG.setText(pmTextField.getMaDG().replaceAll("\\s+", " ").trim());
            }
        }
        if (e.getSource() == tblQLTra) {
            int i = tblQLTra.getSelectedRow();
            if (i >= 0) {
                // Hiển thị bên CTPT
                modelCTTra.setRowCount(0);
                ArrayList<CHITIETPHIEUTRA> kq = new ArrayList<CHITIETPHIEUTRA>();
                PHIEUTRASACH pt = QLTRABUS.dspt.get(i);
                for (CHITIETPHIEUTRA ctpt : QLCTTRABUS.dsctpt) {
                    if (ctpt.getMaPT().indexOf(pt.getMaPT()) >= 0) {
                        kq.add(ctpt); // Chứa phần tử của ctpm thỏa mã pm
                    }
                }
                for (CHITIETPHIEUTRA ctpt : kq) {
                    ShowOnTableCTPT(ctpt);
                }
                tblQLCTTra.setModel(modelCTTra);
                // Hiển thị trên textfield

                PHIEUTRASACH ptTextField = new PHIEUTRASACH();
                ptTextField = QLTRABUS.dspt.get(i);
                txMaPT.setText(ptTextField.getMaPT().replaceAll("\\s+", "").trim());

                String tmp[] = ptTextField.getNgaytra().split("-");
                datePanelNgayBDPT.getModel().setDate(Integer.parseInt(tmp[0]), Integer.parseInt(tmp[1]),
                        Integer.parseInt(tmp[2]));

                if (ptTextField.getTinhtrangsach().equals("Bình thường")) {
                    cbTinhTrangTra.setSelectedIndex(1);
                }
                if (ptTextField.getTinhtrangsach().equals("Hư tổn")) {
                    cbTinhTrangTra.setSelectedIndex(2);
                }
                txTienThue.setText(String.format("%,d", ptTextField.getTienthue()).replaceAll("\\s+", "").trim());
                txThanhTien.setText(String.format("%,d", ptTextField.getThanhtien()).replaceAll("\\s+", "").trim());
                txMaPMTra.setText(ptTextField.getMaPM().replaceAll("\\s+", "").trim());
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

                // Hiển thị trên textField

                HDTIENPHAT ptTextField = new HDTIENPHAT();
                ptTextField = QLHDTPBUS.dshdtp.get(i);
                txMaHD.setText(ptTextField.getMaHD().replaceAll("\\s+", "").trim());
                txMaHD_DG.setText(ptTextField.getMaDG().replaceAll("\\s+", "").trim());
                txSLTongHD.setText(String.format("%,d", ptTextField.getSL()).replaceAll("\\s+", "").trim());
                txTienPhat.setText(String.format("%,d", ptTextField.getTienphat()).replaceAll("\\s+", "").trim());
            }
        }
        if (e.getSource() == tblQLCTMuon) {
            int i = tblQLCTMuon.getSelectedRow();
            if (i >= 0) {
                String MaPM = String.valueOf(modelCTMuon.getValueAt(i, 0));
                String Masach = String.valueOf(modelCTMuon.getValueAt(i, 1));
                String SL = String.valueOf(modelCTMuon.getValueAt(i, 2));
                txCTPMMaPM.setText(MaPM.trim());
                txCTPMMaSach.setText(Masach.trim());
                txCTPMSL.setText(String.valueOf(SL.trim()));
            }
        }
        if (e.getSource() == tblQLCTTra) {
            int i = tblQLCTTra.getSelectedRow();
            if (i >= 0) {
                String MaPT = String.valueOf(modelCTTra.getValueAt(i, 0));
                String Masach = String.valueOf(modelCTTra.getValueAt(i, 1));
                String SL = String.valueOf(modelCTTra.getValueAt(i, 2));
                txCTPTMaPT.setText(MaPT.trim());
                txCTPTMaSach.setText(Masach.trim());
                txCTPTSL.setText(String.valueOf(SL.trim()));
            }
        }
        if (e.getSource() == tblQLCTHDTP) {
            int i = tblQLCTHDTP.getSelectedRow();
            if (i >= 0) {
                String MaHD = String.valueOf(modelCTHDTP.getValueAt(i, 0));
                String Masach = String.valueOf(modelCTHDTP.getValueAt(i, 1));
                String SL = String.valueOf(modelCTHDTP.getValueAt(i, 2));
                String DonGia = String.valueOf(modelCTHDTP.getValueAt(i, 3));
                txCTHDMaHD.setText(MaHD.trim());
                txCTHDMaSach.setText(Masach.trim());
                txCTHDSL.setText(String.valueOf(SL.trim()));
                txCTHDDonGia.setText(String.valueOf(DonGia.trim()));
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
                if (btInPM.isVisible() == false) {
                    btInPM.setVisible(true);
                }
                if (pnNhapPT.isVisible() == true) {
                    pnNhapPT.setVisible(false);
                }
                if (pnNhapHDTP.isVisible() == true) {
                    pnNhapHDTP.setVisible(false);
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
                if (pnNhapHDTP.isVisible() == true) {
                    pnNhapHDTP.setVisible(false);
                }
                if (btInPM.isVisible() == true) {
                    btInPM.setVisible(false);
                }
                if (pnNhapPT.isVisible() == false) {
                    pnNhapPT.setVisible(true);
                }
                if (btInHDTP.isVisible() == true) {
                    btInHDTP.setVisible(false);
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
                if (btInPM.isVisible() == true) {
                    btInPM.setVisible(false);
                }
                if (btInHDTP.isVisible() == false) {
                    btInHDTP.setVisible(true);
                }
                if (pnNhapPT.isVisible() == true) {
                    pnNhapPT.setVisible(false);
                }
                if (pnNhapHDTP.isVisible() == false) {
                    pnNhapHDTP.setVisible(true);
                }
                pnTabMuon.setVisible(false);
                pnTabTra.setVisible(false);
            }
        }
        if (e.getSource() == txTienPhat) {
            if(txCTHDSL.getText().equals("")){
                JOptionPane.showMessageDialog(null, "Mời nhập số lượng để tính thành tiền", "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
            else if(txCTHDDonGia.getText().equals("")){
                JOptionPane.showMessageDialog(null, "Mời nhập đơn giá để tính thành tiền", "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
            else{
                int Dongia = Integer.parseInt(myTable.RemoveCommaInString(txCTHDDonGia));
                int SL = Integer.parseInt(txCTHDSL.getText());
                int Tienphat = Dongia*SL;
                txTienPhat.setText(String.format("%,d",Tienphat));
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (e.getSource() == datePickerNgayBDPT) {
            System.out.println("1");
        }
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

    @Override
    public void keyPressed(KeyEvent e){
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            if (txTienTra_M.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "Mời nhập tiền trả để tính", "Lỗi", JOptionPane.ERROR_MESSAGE);
            } else if (txThanhTien.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "Mời nhập thành tiền để tính", "Lỗi", JOptionPane.ERROR_MESSAGE);
            } else {
                int i = tblQLTra.getSelectedRow();
                if (i >= 0) {
                    int TienTra = Integer.parseInt(myTable.RemoveCommaInString(txTienTra_M));
                    int TienThue = Integer.parseInt(myTable.RemoveCommaInString(txThanhTien));
                    txTienTra_M.setText(String.format("%,d", TienTra));
                    int TienThua = TienTra - TienThue;
                    txTienThua.setText(String.format("%,d", TienThua));
                }
            }
        }
        if (e.getKeyCode() == KeyEvent.VK_SHIFT) {
            if (txTienTra_HD.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "Mời nhập tiền trả để tính", "Lỗi", JOptionPane.ERROR_MESSAGE);
            } else if (txTienPhat.getText().equals(""))
                JOptionPane.showMessageDialog(null, "Mời nhập tiền phạt để tính", "Lỗi", JOptionPane.ERROR_MESSAGE);
            else {
                int i = tblQLHDTP.getSelectedRow();
                if (i >= 0) {
                    int TienTra = Integer.parseInt(myTable.RemoveCommaInString(txTienTra_HD));
                    int TienPhat = Integer.parseInt(myTable.RemoveCommaInString(txTienPhat));
                    int TienThua = TienTra - TienPhat;
                    txTienTra_HD.setText(String.format("%,d", TienTra));
                    txTraTienPhat.setText(String.format("%,d", TienThua));
                }
            }
        }
    }

    @Override 
    public void  keyReleased(KeyEvent e){

    }

    @Override
    public void keyTyped(KeyEvent e){

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

    public void getDBCTPT() {
        try {
            QLCTTRABUS qlCTPTbus = new QLCTTRABUS();
            if (QLCTTRABUS.dsctpt == null)
                try {
                    qlCTPTbus.docDSCTPT();
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            // set up header(column)
            Vector<String> header = new Vector<String>();
            header.add("Mã phiếu trả");
            header.add("Mã sách");
            header.add("SL");
            modelCTTra = new DefaultTableModel(header, 0);
            tblQLCTTra.setModel(modelCTTra);
        } catch (Exception e1) {
            System.out.println(e1);
        }
    }

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
            header.add("Số lượng tổng");
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

    public void ShowOnTablePM(PHIEUMUON pm) {
        Vector<String> row = new Vector<String>();
        row.add(pm.getMaPM().replaceAll("\\s+", "").trim());
        row.add(pm.getNgaymuon().replaceAll("\\s+", "").trim());
        row.add(String.format("%,d", pm.getSLtong()).replaceAll("\\s+", "").trim());
        row.add(pm.getNgaytra().replaceAll("\\s+", "").trim());
        row.add(pm.getTinhTrangMuon());
        row.add(pm.getMaDG().replaceAll("\\s+", "").trim());
        modelMuon.addRow(row);
    }

    public void ShowOnTableCTPM(CHITIETPHIEUMUON ctpm) {
        Vector<String> row = new Vector<String>();
        row.add(ctpm.getMaPM().replaceAll("\\s+", "").trim());
        row.add(ctpm.getMasach().replaceAll("\\s+", "").trim());
        row.add(String.format("%,d", ctpm.getSL()).replaceAll("\\s+", "").trim());
        modelCTMuon.addRow(row);
    }

    public void ShowOnTableHD(HDTIENPHAT hd) {
        Vector<String> row = new Vector<String>();
        row.add(hd.getMaHD().replaceAll("\\s+", "").trim());
        row.add(hd.getMaDG().replaceAll("\\s+", "").trim());
        row.add(String.format("%,d", hd.getSL()).replaceAll("\\s+", "").trim());
        row.add(String.format("%,d", hd.getTienphat()).replaceAll("\\s+", "").trim());
        modelHDTP.addRow(row);
    }

    public void ShowOnTableCTHD(CHITIETHDTIENPHAT cthd) {
        Vector<String> row = new Vector<String>();
        row.add(cthd.getMaHD().replaceAll("\\s+", "").trim());
        row.add(cthd.getMasach().replaceAll("\\s+", "").trim());
        row.add(String.format("%,d", cthd.getSL()).replaceAll("\\s+", "").trim());
        row.add(String.format("%,d", cthd.getDongia()).replaceAll("\\s+", "").trim());
        modelCTHDTP.addRow(row);
    }

    public void ShowOnTablePT(PHIEUTRASACH pt) {
        Vector<String> row = new Vector<String>();
        row.add(pt.getMaPT().replaceAll("\\s+", "").trim());
        row.add(pt.getNgaytra().replaceAll("\\s+", "").trim());
        row.add(pt.getTinhtrangsach());
        row.add(String.format("%,d", pt.getTienthue()).replaceAll("\\s+", "").trim());
        row.add(String.format("%,d", pt.getThanhtien()).replaceAll("\\s+", "").trim());
        row.add(pt.getMaPM().replaceAll("\\s+", "").trim());
        modelTra.addRow(row);
    }

    public void ShowOnTableCTPT(CHITIETPHIEUTRA ctpt) {
        Vector<String> row = new Vector<String>();
        row.add(ctpt.getMaPT().replaceAll("\\s+", "").trim());
        row.add(ctpt.getMasach().replaceAll("\\s+", "").trim());
        row.add(String.format("%,d", ctpt.getSL()).replaceAll("\\s+", "").trim());
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
        btShowAll.setBackground(MyColor.ColorButton);
        btShowAll.setBorder(new RoundedBorder(10));
        btShowAll.addActionListener(this);

        btInPM = new JButton("In phiếu mượn");
        btInPM.setFont(new Font("Arial", Font.BOLD, 15));
        btInPM.setBounds(0, 10, 130, 30);
        btInPM.setBackground(MyColor.ColorButton);
        btInPM.setBorder(new RoundedBorder(10));
        btInPM.addActionListener(this);

        btInHDTP = new JButton("In hóa đơn");
        btInHDTP.setFont(new Font("Arial", Font.BOLD, 15));
        btInHDTP.setBounds(0, 10, 130, 30);
        btInHDTP.setBackground(MyColor.ColorButton);
        btInHDTP.setBorder(new RoundedBorder(10));
        btInHDTP.addActionListener(this);

        pnShowAll.add(btShowAll);
        pnShowAll.add(btInPM);
        pnShowAll.add(btInHDTP);
    }

    public void setTableMuon() {
        // ----set up table----
        tblQLMuon = new JTable();
        JScrollPane pane = new JScrollPane(tblQLMuon);
        pane.setAutoscrolls(true);
        tblQLMuon.setRowHeight(20);
        tblQLMuon.setFont(new Font(null, 0, 13));
        tblQLMuon.setBackground(MyColor.ColorLightGray);
        tblQLMuon.getTableHeader().setBackground(MyColor.ColorSilver);
        tblQLMuon.addMouseListener(this);
        tblQLMuon.setDefaultEditor(Object.class, null);
        tblQLMuon.setSelectionBackground(MyColor.Color);
        pnMuon.add(pane);
    }

    public void setTableCTMuon() {
        // ----set up table----
        tblQLCTMuon = new JTable();
        JScrollPane pane = new JScrollPane(tblQLCTMuon);
        pane.setAutoscrolls(true);
        tblQLCTMuon.setRowHeight(20);
        tblQLCTMuon.setFont(new Font(null, 0, 13));
        tblQLCTMuon.setBackground(MyColor.ColorLightGray);
        tblQLCTMuon.getTableHeader().setBackground(MyColor.ColorSilver);
        tblQLCTMuon.addMouseListener(this);
        tblQLCTMuon.setDefaultEditor(Object.class, null);
        tblQLCTMuon.setSelectionBackground(MyColor.Color2);
        pnCTMuon.add(pane);
    }

    public void setTableTra() {
        // ----set up table----
        tblQLTra = new JTable();
        JScrollPane pane = new JScrollPane(tblQLTra);
        pane.setAutoscrolls(true);
        tblQLTra.setRowHeight(20);
        tblQLTra.setFont(new Font(null, 0, 13));
        tblQLTra.setBackground(MyColor.ColorLightGray);
        tblQLTra.getTableHeader().setBackground(MyColor.ColorSilver);
        tblQLTra.addMouseListener(this);
        tblQLTra.setDefaultEditor(Object.class, null);
        tblQLTra.setSelectionBackground(MyColor.Color);
        pnTra.add(pane);
    }

    public void setTableCTTra() {
        // ----set up table----
        tblQLCTTra = new JTable();
        JScrollPane pane = new JScrollPane(tblQLCTTra);
        pane.setAutoscrolls(true);
        tblQLCTTra.setRowHeight(20);
        tblQLCTTra.setFont(new Font(null, 0, 13));
        tblQLCTTra.setBackground(MyColor.ColorLightGray);
        tblQLCTTra.getTableHeader().setBackground(MyColor.ColorSilver);
        tblQLCTTra.addMouseListener(this);
        tblQLCTTra.setDefaultEditor(Object.class, null);
        tblQLCTTra.setSelectionBackground(MyColor.Color2);
        pnCTTra.add(pane);
    }

    public void setTableHDTP() {
        // ----set up table----
        tblQLHDTP = new JTable();
        JScrollPane pane = new JScrollPane(tblQLHDTP);
        pane.setAutoscrolls(true);
        tblQLHDTP.setRowHeight(20);
        tblQLHDTP.setFont(new Font(null, 0, 13));
        tblQLHDTP.setBackground(MyColor.ColorLightGray);
        tblQLHDTP.getTableHeader().setBackground(MyColor.ColorSilver);
        tblQLHDTP.addMouseListener(this);
        tblQLHDTP.setDefaultEditor(Object.class, null);
        tblQLHDTP.setSelectionBackground(MyColor.Color);
        pnHDTP.add(pane);
    }

    public void setTableCTHDTP() {
        // ----set up table----
        tblQLCTHDTP = new JTable();
        JScrollPane pane = new JScrollPane(tblQLCTHDTP);
        pane.setAutoscrolls(true);
        tblQLCTHDTP.setRowHeight(20);
        tblQLCTHDTP.setFont(new Font(null, 0, 13));
        tblQLCTHDTP.setBackground(MyColor.ColorLightGray);
        tblQLCTHDTP.getTableHeader().setBackground(MyColor.ColorSilver);
        tblQLCTHDTP.addMouseListener(this);
        tblQLCTHDTP.setDefaultEditor(Object.class, null);
        tblQLCTHDTP.setSelectionBackground(MyColor.Color2);
        pnCTHDTP.add(pane);
    }

    public void setInputMuon() {
        lbNhapPM = new JLabel("Phiếu Mượn");
        lbNhapPM.setFont(new Font("Arial", Font.BOLD, 20));
        lbNhapPM.setBounds(100, 0, 180, 80);

        lbMaPM = new JLabel("Mã phiếu mượn:");
        lbMaPM.setFont(new Font("Arial", Font.BOLD, 18));
        lbMaPM.setBounds(0, 40, 150, 80);

        lbNgayMuon = new JLabel("Ngày mượn:");
        lbNgayMuon.setFont(new Font("Arial", Font.BOLD, 18));
        lbNgayMuon.setBounds(0, 85, 150, 80);

        lbSLtong = new JLabel("SL tổng:");
        lbSLtong.setFont(new Font("Arial", Font.BOLD, 18));
        lbSLtong.setBounds(0, 130, 150, 80);

        lbNgayTra = new JLabel("Ngày trả:");
        lbNgayTra.setFont(new Font("Arial", Font.BOLD, 18));
        lbNgayTra.setBounds(0, 175, 150, 80);

        lbTinhTrangMuon = new JLabel("Tình trạng mượn:");
        lbTinhTrangMuon.setFont(new Font("Arial", Font.BOLD, 18));
        lbTinhTrangMuon.setBounds(0, 220, 170, 80);

        lbMaDG = new JLabel("Mã độc giả:");
        lbMaDG.setFont(new Font("Arial", Font.BOLD, 18));
        lbMaDG.setBounds(0, 265, 150, 80);

        txMaPM = new JTextField();
        txMaPM.setBounds(175, 65, 150, 30);
        txMaPM.setFont(new Font("Arial", Font.PLAIN, 15));
        txMaPM.addMouseListener(this);

        txSLtong = new JTextField();
        txSLtong.setBounds(175, 155, 150, 30);
        txSLtong.setFont(new Font("Arial", Font.PLAIN, 15));
        txSLtong.addMouseListener(this);

        txMaDG = new JTextField();
        txMaDG.setBounds(175, 290, 108, 30);
        txMaDG.setFont(new Font("Arial", Font.PLAIN, 15));
        txMaDG.addMouseListener(this);
        txMaDG.setEditable(false);

        // set up ComboBox
        String[] dsTinhTrangMuon = { "", "Đang mượn", "Hết mượn" };
        cbTinhTrangMuon = new JComboBox<>(dsTinhTrangMuon);
        cbTinhTrangMuon.setFont(new Font("Arial", Font.BOLD, 13));
        cbTinhTrangMuon.setBounds(175, 245, 150, 30);
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
        datePickerNgayBDPM.setBounds(175, 110, 150, 30);

        // Set date picker2
        modelNgayKTPM = new UtilDateModel();
        modelNgayKTPM.setSelected(true);
        pNgayKTPM = new Properties();
        pNgayKTPM.put("text.today", "Today");
        pNgayKTPM.put("text.month", "Month");
        pNgayKTPM.put("text.year", "Year");
        datePanelNgayKTPM = new JDatePanelImpl(modelNgayKTPM, pNgayKTPM);
        datePickerNgayKTPM = new JDatePickerImpl(datePanelNgayKTPM, new DateLabelFormatter());
        datePickerNgayKTPM.setBounds(175, 200, 150, 30);

        btThemPM = new JButton("Thêm");
        btThemPM.setFont(new Font("Arial", Font.BOLD, 15));
        btThemPM.setBounds(95, 325, 80, 30);
        btThemPM.setBackground(MyColor.ColorButton);
        btThemPM.setBorder(new RoundedBorder(10));
        btThemPM.addActionListener(this);

        btSuaPM = new JButton("Sửa");
        btSuaPM.setFont(new Font("Arial", Font.BOLD, 15));
        btSuaPM.setBounds(245, 325, 80, 30);
        btSuaPM.setBackground(MyColor.ColorButton);
        btSuaPM.setBorder(new RoundedBorder(10));
        btSuaPM.addActionListener(this);

        btXoa = new JButton("Xóa");
        btXoa.setFont(new Font("Arial", Font.BOLD, 15));
        btXoa.setBounds(210, 305, 80, 30);
        btXoa.setBackground(MyColor.ColorButton);
        btXoa.setBorder(new RoundedBorder(10));
        btXoa.addActionListener(this);

        btHoanTac = new JButton("Hoàn tác");
        btHoanTac.setFont(new Font("Arial", Font.BOLD, 15));
        btHoanTac.setBounds(310, 305, 90, 30);
        btHoanTac.setBackground(MyColor.ColorButton);
        btHoanTac.setBorder(new RoundedBorder(10));
        btHoanTac.addActionListener(this);

        btHoTroNhapMaDG = new JButton("...");
        btHoTroNhapMaDG.setFont(new Font("Arial", Font.BOLD, 15));
        btHoTroNhapMaDG.setBounds(285, 290, 40, 30);
        btHoTroNhapMaDG.setBackground(MyColor.ColorButton);
        btHoTroNhapMaDG.setBorder(new RoundedBorder(10));
        btHoTroNhapMaDG.addActionListener(this);

        pnNhapPM.add(lbNhapPM);
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

        pnNhapPM.add(btThemPM);
        pnNhapPM.add(btSuaPM);
        pnNhapPM.add(btHoTroNhapMaDG);
    }

    public void setInputCTPM() {
        lbNhapCTPM = new JLabel("CT Phiếu Mượn");
        lbNhapCTPM.setFont(new Font("Arial", Font.BOLD, 20));
        lbNhapCTPM.setBounds(460, 0, 200, 80);

        lbCTPMMaPM = new JLabel("Mã Phiếu Mượn:");
        lbCTPMMaPM.setFont(new Font("Arial", Font.BOLD, 18));
        lbCTPMMaPM.setBounds(355, 40, 150, 80);

        lbCTPMMaSach = new JLabel("Mã Sách:");
        lbCTPMMaSach.setFont(new Font("Arial", Font.BOLD, 18));
        lbCTPMMaSach.setBounds(355, 85, 150, 80);

        lbCTPMSL = new JLabel("Số lượng:");
        lbCTPMSL.setFont(new Font("Arial", Font.BOLD, 18));
        lbCTPMSL.setBounds(355, 130, 150, 80);

        txCTPMMaPM = new JTextField();
        txCTPMMaPM.setBounds(515, 65, 130, 30);
        txCTPMMaPM.setFont(new Font("Arial", Font.PLAIN, 15));
        txCTPMMaPM.setEditable(false);

        txCTPMMaSach = new JTextField();
        txCTPMMaSach.setBounds(515, 110, 130, 30);
        txCTPMMaSach.setFont(new Font("Arial", Font.PLAIN, 15));
        txCTPMMaSach.setEditable(false);

        txCTPMSL = new JTextField();
        txCTPMSL.setBounds(515, 155, 130, 30);
        txCTPMSL.setFont(new Font("Arial", Font.PLAIN, 15));

        btThemCTPM = new JButton("Thêm");
        btThemCTPM.setFont(new Font("Arial", Font.BOLD, 15));
        btThemCTPM.setBounds(390, 200, 80, 30);
        btThemCTPM.setBackground(MyColor.ColorButton);
        btThemCTPM.setBorder(new RoundedBorder(10));
        btThemCTPM.addActionListener(this);

        btSuaCTPM = new JButton("Sửa");
        btSuaCTPM.setFont(new Font("Arial", Font.BOLD, 15));
        btSuaCTPM.setBounds(550, 200, 80, 30);
        btSuaCTPM.setBackground(MyColor.ColorButton);
        btSuaCTPM.setBorder(new RoundedBorder(10));
        btSuaCTPM.addActionListener(this);

        btHoTroNhapMaPM = new JButton("...");
        btHoTroNhapMaPM.setFont(new Font("Arial", Font.BOLD, 15));
        btHoTroNhapMaPM.setBounds(660, 65, 40, 30);
        btHoTroNhapMaPM.setBackground(MyColor.ColorButton);
        btHoTroNhapMaPM.setBorder(new RoundedBorder(10));
        btHoTroNhapMaPM.addActionListener(this);

        btHoTroNhapMasachPM = new JButton("...");
        btHoTroNhapMasachPM.setFont(new Font("Arial", Font.BOLD, 15));
        btHoTroNhapMasachPM.setBounds(660, 110, 40, 30);
        btHoTroNhapMasachPM.setBackground(MyColor.ColorButton);
        btHoTroNhapMasachPM.setBorder(new RoundedBorder(10));
        btHoTroNhapMasachPM.addActionListener(this);

        pnNhapPM.add(lbNhapCTPM);
        pnNhapPM.add(lbCTPMMaPM);
        pnNhapPM.add(lbCTPMMaSach);
        pnNhapPM.add(lbCTPMSL);

        pnNhapPM.add(txCTPMMaPM);
        pnNhapPM.add(txCTPMMaSach);
        pnNhapPM.add(txCTPMSL);

        pnNhapPM.add(btThemCTPM);
        pnNhapPM.add(btSuaCTPM);
        pnNhapPM.add(btHoTroNhapMaPM);
        pnNhapPM.add(btHoTroNhapMasachPM);
    }

    public void setInputTra() {
        lbNhapTra = new JLabel("Phiếu Trả");
        lbNhapTra.setFont(new Font("Arial", Font.BOLD, 20));
        lbNhapTra.setBounds(100, 0, 180, 80);

        lbMaPT = new JLabel("Mã phiếu trả:");
        lbMaPT.setFont(new Font("Arial", Font.BOLD, 18));
        lbMaPT.setBounds(0, 40, 150, 80);

        lbNgayTraPT = new JLabel("Ngày trả:");
        lbNgayTraPT.setFont(new Font("Arial", Font.BOLD, 18));
        lbNgayTraPT.setBounds(0, 85, 150, 80);

        lbTinhTrangSach = new JLabel("Tình trạng sách:");
        lbTinhTrangSach.setFont(new Font("Arial", Font.BOLD, 18));
        lbTinhTrangSach.setBounds(0, 130, 170, 80);

        lbTienThue = new JLabel("Tiền thuê:");
        lbTienThue.setFont(new Font("Arial", Font.BOLD, 18));
        lbTienThue.setBounds(0, 175, 150, 80);

        lbThanhTien = new JLabel("Thành tiền:");
        lbThanhTien.setFont(new Font("Arial", Font.BOLD, 18));
        lbThanhTien.setBounds(0, 220, 150, 80);

        lbMaPMTra = new JLabel("Mã phiếu mượn:");
        lbMaPMTra.setFont(new Font("Arial", Font.BOLD, 18));
        lbMaPMTra.setBounds(0, 265, 150, 80);

        String[] dsTinhTrangTra = { "", "Bình thường", "Hư tổn" };
        cbTinhTrangTra = new JComboBox<>(dsTinhTrangTra);
        cbTinhTrangTra.setFont(new Font("Arial", Font.BOLD, 13));
        cbTinhTrangTra.setBounds(175, 155, 150, 30);
        cbTinhTrangTra.addActionListener(this);

        txMaPT = new JTextField();
        txMaPT.setBounds(175, 65, 150, 30);
        txMaPT.setFont(new Font("Arial", Font.PLAIN, 15));
        txMaPT.addMouseListener(this);

        txTienThue = new JTextField();
        txTienThue.setBounds(175, 200, 150, 30);
        txTienThue.setFont(new Font("Arial", Font.PLAIN, 15));
        txTienThue.addMouseListener(this);
        txTienThue.setEditable(false);
        txTienThue.setText(String.format("%,d", PHIEUTRASACH.Tienthue));

        txThanhTien = new JTextField();
        txThanhTien.setBounds(175, 245, 150, 30);
        txThanhTien.setFont(new Font("Arial", Font.PLAIN, 15));
        txThanhTien.addMouseListener(this);
        txThanhTien.setEditable(false);

        txMaPMTra = new JTextField();
        txMaPMTra.setBounds(175, 290, 108, 30);
        txMaPMTra.setFont(new Font("Arial", Font.PLAIN, 15));
        txMaPMTra.addMouseListener(this);
        txMaPMTra.setEditable(false);

        // Set date picker1
        modelNgayBDPT = new UtilDateModel();
        modelNgayBDPT.setSelected(true);
        pNgayBDPT = new Properties();
        pNgayBDPT.put("text.today", "Today");
        pNgayBDPT.put("text.month", "Month");
        pNgayBDPT.put("text.year", "Year");
        datePanelNgayBDPT = new JDatePanelImpl(modelNgayBDPT, pNgayBDPT);
        datePickerNgayBDPT = new JDatePickerImpl(datePanelNgayBDPT, new DateLabelFormatter());
        datePickerNgayBDPT.setBounds(175, 110, 150, 30);
        datePickerNgayBDPT.addMouseListener(this);

        btThemPT = new JButton("Thêm");
        btThemPT.setFont(new Font("Arial", Font.BOLD, 15));
        btThemPT.setBounds(95, 325, 80, 30);
        btThemPT.setBackground(MyColor.ColorButton);
        btThemPT.setBorder(new RoundedBorder(10));
        btThemPT.addActionListener(this);

        btSuaPT = new JButton("Sửa");
        btSuaPT.setFont(new Font("Arial", Font.BOLD, 15));
        btSuaPT.setBounds(245, 325, 80, 30);
        btSuaPT.setBackground(MyColor.ColorButton);
        btSuaPT.setBorder(new RoundedBorder(10));
        btSuaPT.addActionListener(this);

        btHoTroNhapPM_Tra = new JButton("...");
        btHoTroNhapPM_Tra.setFont(new Font("Arial", Font.BOLD, 15));
        btHoTroNhapPM_Tra.setBounds(285, 290, 40, 30);
        btHoTroNhapPM_Tra.setBackground(MyColor.ColorButton);
        btHoTroNhapPM_Tra.setBorder(new RoundedBorder(10));
        btHoTroNhapPM_Tra.addActionListener(this);

        pnNhapPT.add(lbNhapTra);
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
        pnNhapPT.add(btThemPT);
        pnNhapPT.add(btSuaPT);
        pnNhapPT.add(btHoTroNhapPM_Tra);
    }

    public void setInputCTPT() {
        lbNhapCTPT = new JLabel("CT Phiếu Trả");
        lbNhapCTPT.setFont(new Font("Arial", Font.BOLD, 20));
        lbNhapCTPT.setBounds(460, 0, 200, 80);

        lbCTPTMaPT = new JLabel("Mã Phiếu Trả:");
        lbCTPTMaPT.setFont(new Font("Arial", Font.BOLD, 18));
        lbCTPTMaPT.setBounds(355, 40, 150, 80);

        lbCTPTMaSach = new JLabel("Mã Sách:");
        lbCTPTMaSach.setFont(new Font("Arial", Font.BOLD, 18));
        lbCTPTMaSach.setBounds(355, 85, 150, 80);

        lbCTPTSL = new JLabel("Số lượng:");
        lbCTPTSL.setFont(new Font("Arial", Font.BOLD, 18));
        lbCTPTSL.setBounds(355, 130, 150, 80);

        lbTienTra = new JLabel("Tiền trả:");
        lbTienTra.setFont(new Font("Arial", Font.BOLD, 18));
        lbTienTra.setBounds(355, 230, 130, 80);

        lbTienThua = new JLabel("Tiền thừa:");
        lbTienThua.setFont(new Font("Arial", Font.BOLD, 18));
        lbTienThua.setBounds(355, 275, 130, 80);

        txCTPTMaPT = new JTextField();
        txCTPTMaPT.setBounds(515, 65, 130, 30);
        txCTPTMaPT.setFont(new Font("Arial", Font.PLAIN, 15));
        txCTPTMaPT.setEditable(false);

        txCTPTMaSach = new JTextField();
        txCTPTMaSach.setBounds(515, 110, 130, 30);
        txCTPTMaSach.setFont(new Font("Arial", Font.PLAIN, 15));
        txCTPTMaSach.setEditable(false);

        txCTPTSL = new JTextField();
        txCTPTSL.setBounds(515, 155, 130, 30);
        txCTPTSL.setFont(new Font("Arial", Font.PLAIN, 15));

        txTienTra_M = new JTextField();
        txTienTra_M.setBounds(515, 255, 130, 30);
        txTienTra_M.setFont(new Font("Arial", Font.PLAIN, 15));
        txTienTra_M.addKeyListener(this);

        txTienThua = new JTextField();
        txTienThua.setBounds(515, 300, 130, 30);
        txTienThua.setFont(new Font("Arial", Font.PLAIN, 15));
        txTienThua.setEditable(false);

        btThemCTPT = new JButton("Thêm");
        btThemCTPT.setFont(new Font("Arial", Font.BOLD, 15));
        btThemCTPT.setBounds(390, 195, 80, 30);
        btThemCTPT.setBackground(MyColor.ColorButton);
        btThemCTPT.setBorder(new RoundedBorder(10));
        btThemCTPT.addActionListener(this);

        btSuaCTPT = new JButton("Sửa");
        btSuaCTPT.setFont(new Font("Arial", Font.BOLD, 15));
        btSuaCTPT.setBounds(550, 195, 80, 30);
        btSuaCTPT.setBackground(MyColor.ColorButton);
        btSuaCTPT.setBorder(new RoundedBorder(10));
        btSuaCTPT.addActionListener(this);

        btHoTroNhapMaPT = new JButton("...");
        btHoTroNhapMaPT.setFont(new Font("Arial", Font.BOLD, 15));
        btHoTroNhapMaPT.setBounds(660, 65, 40, 30);
        btHoTroNhapMaPT.setBackground(MyColor.ColorButton);
        btHoTroNhapMaPT.setBorder(new RoundedBorder(10));
        btHoTroNhapMaPT.addActionListener(this);

        btHoTroNhapMasachPT = new JButton("...");
        btHoTroNhapMasachPT.setFont(new Font("Arial", Font.BOLD, 15));
        btHoTroNhapMasachPT.setBounds(660, 110, 40, 30);
        btHoTroNhapMasachPT.setBackground(MyColor.ColorButton);
        btHoTroNhapMasachPT.setBorder(new RoundedBorder(10));
        btHoTroNhapMasachPT.addActionListener(this);

        pnNhapPT.add(lbNhapCTPT);
        pnNhapPT.add(lbCTPTMaPT);
        pnNhapPT.add(lbCTPTMaSach);
        pnNhapPT.add(lbCTPTSL);
        pnNhapPT.add(lbTienTra);
        pnNhapPT.add(lbTienThua);

        pnNhapPT.add(txCTPTMaPT);
        pnNhapPT.add(txCTPTMaSach);
        pnNhapPT.add(txCTPTSL);
        pnNhapPT.add(txTienTra_M);
        pnNhapPT.add(txTienThua);

        pnNhapPT.add(btThemCTPT);
        pnNhapPT.add(btSuaCTPT);
        pnNhapPT.add(btHoTroNhapMaPT);
        pnNhapPT.add(btHoTroNhapMasachPT);
    }

    public void setInputHDTP() {
        lbNhapHDTP = new JLabel("HD Tiền Phạt");
        lbNhapHDTP.setFont(new Font("Arial", Font.BOLD, 20));
        lbNhapHDTP.setBounds(100, 0, 180, 80);

        lbMaHD = new JLabel("Mã hóa đơn:");
        lbMaHD.setFont(new Font("Arial", Font.BOLD, 18));
        lbMaHD.setBounds(0, 40, 150, 80);

        lbMaHD_DG = new JLabel("Mã độc giả:");
        lbMaHD_DG.setFont(new Font("Arial", Font.BOLD, 18));
        lbMaHD_DG.setBounds(0, 85, 150, 80);

        lbSLTongHD = new JLabel("Số lượng tổng:");
        lbSLTongHD.setFont(new Font("Arial", Font.BOLD, 18));
        lbSLTongHD.setBounds(0, 130, 170, 80);

        lbTienPhat = new JLabel("Tiền phạt:");
        lbTienPhat.setFont(new Font("Arial", Font.BOLD, 18));
        lbTienPhat.setBounds(0, 175, 150, 80);

        txMaHD = new JTextField();
        txMaHD.setBounds(175, 65, 150, 30);
        txMaHD.setFont(new Font("Arial", Font.PLAIN, 15));
        txMaHD.addMouseListener(this);

        txMaHD_DG = new JTextField();
        txMaHD_DG.setBounds(175, 110, 150, 30);
        txMaHD_DG.setFont(new Font("Arial", Font.PLAIN, 15));
        txMaHD_DG.addMouseListener(this);

        txSLTongHD = new JTextField();
        txSLTongHD.setBounds(175, 155, 150, 30);
        txSLTongHD.setFont(new Font("Arial", Font.PLAIN, 15));
        txSLTongHD.addMouseListener(this);

        txTienPhat = new JTextField();
        txTienPhat.setBounds(175, 200, 150, 30);
        txTienPhat.setFont(new Font("Arial", Font.PLAIN, 15));
        txTienPhat.addMouseListener(this);
        txTienPhat.setEditable(false);

        btThemHDTP = new JButton("Thêm");
        btThemHDTP.setFont(new Font("Arial", Font.BOLD, 15));
        btThemHDTP.setBounds(95, 250, 80, 30);
        btThemHDTP.setBackground(MyColor.ColorButton);
        btThemHDTP.setBorder(new RoundedBorder(10));
        btThemHDTP.addActionListener(this);

        btSuaHDTP = new JButton("Sửa");
        btSuaHDTP.setFont(new Font("Arial", Font.BOLD, 15));
        btSuaHDTP.setBounds(245, 250, 80, 30);
        btSuaHDTP.setBackground(MyColor.ColorButton);
        btSuaHDTP.setBorder(new RoundedBorder(10));
        btSuaHDTP.addActionListener(this);

        pnNhapHDTP.add(lbNhapHDTP);
        pnNhapHDTP.add(lbMaHD);
        pnNhapHDTP.add(lbMaHD_DG);
        pnNhapHDTP.add(lbSLTongHD);
        pnNhapHDTP.add(lbTienPhat);

        pnNhapHDTP.add(txMaHD);
        pnNhapHDTP.add(txMaHD_DG);
        pnNhapHDTP.add(txSLTongHD);
        pnNhapHDTP.add(txTienPhat);

        pnNhapHDTP.add(btThemHDTP);
        pnNhapHDTP.add(btSuaHDTP);
    }

    public void setInputCTHDTP() {
        lbNhapCTHDTP = new JLabel("CTHD Tiền Phạt");
        lbNhapCTHDTP.setFont(new Font("Arial", Font.BOLD, 20));
        lbNhapCTHDTP.setBounds(460, 0, 200, 80);

        lbCTHDMaHD = new JLabel("Mã Hóa đơn:");
        lbCTHDMaHD.setFont(new Font("Arial", Font.BOLD, 18));
        lbCTHDMaHD.setBounds(355, 40, 150, 80);

        lbCTHDMaSach = new JLabel("Mã Sách:");
        lbCTHDMaSach.setFont(new Font("Arial", Font.BOLD, 18));
        lbCTHDMaSach.setBounds(355, 85, 150, 80);

        lbCTHDSL = new JLabel("Số lượng:");
        lbCTHDSL.setFont(new Font("Arial", Font.BOLD, 18));
        lbCTHDSL.setBounds(355, 130, 150, 80);

        lbCTHDDonGia = new JLabel("Đơn giá:");
        lbCTHDDonGia.setFont(new Font("Arial", Font.BOLD, 18));
        lbCTHDDonGia.setBounds(355, 175, 150, 80);

        lbTienTra = new JLabel("Tiền trả:");
        lbTienTra.setFont(new Font("Arial", Font.BOLD, 18));
        lbTienTra.setBounds(750, 40, 130, 80);

        lbTraTienPhat = new JLabel("Tiền thừa:");
        lbTraTienPhat.setFont(new Font("Arial", Font.BOLD, 18));
        lbTraTienPhat.setBounds(750, 85, 130, 80);

        txCTHDMaHD = new JTextField();
        txCTHDMaHD.setBounds(515, 65, 130, 30);
        txCTHDMaHD.setFont(new Font("Arial", Font.PLAIN, 15));
        txCTHDMaHD.setEditable(false);

        txCTHDMaSach = new JTextField();
        txCTHDMaSach.setBounds(515, 110, 130, 30);
        txCTHDMaSach.setFont(new Font("Arial", Font.PLAIN, 15));
        txCTHDMaSach.setEditable(false);

        txCTHDSL = new JTextField();
        txCTHDSL.setBounds(515, 155, 130, 30);
        txCTHDSL.setFont(new Font("Arial", Font.PLAIN, 15));

        txCTHDDonGia = new JTextField();
        txCTHDDonGia.setBounds(515, 200, 130, 30);
        txCTHDDonGia.setFont(new Font("Arial", Font.PLAIN, 15));
        txCTHDDonGia.setEditable(false);

        txTienTra_HD = new JTextField();
        txTienTra_HD.setBounds(870, 65, 130, 30);
        txTienTra_HD.setFont(new Font("Arial", Font.PLAIN, 15));
        txTienTra_HD.addKeyListener(this);

        txTraTienPhat = new JTextField();
        txTraTienPhat.setBounds(870, 110, 130, 30);
        txTraTienPhat.setFont(new Font("Arial", Font.PLAIN, 15));
        txTraTienPhat.setEditable(false);

        btThemCTHD = new JButton("Thêm");
        btThemCTHD.setFont(new Font("Arial", Font.BOLD, 15));
        btThemCTHD.setBounds(390, 250, 80, 30);
        btThemCTHD.setBackground(MyColor.ColorButton);
        btThemCTHD.setBorder(new RoundedBorder(10));
        btThemCTHD.addActionListener(this);

        btSuaCTHD = new JButton("Sửa");
        btSuaCTHD.setFont(new Font("Arial", Font.BOLD, 15));
        btSuaCTHD.setBounds(550, 250, 80, 30);
        btSuaCTHD.setBackground(MyColor.ColorButton);
        btSuaCTHD.setBorder(new RoundedBorder(10));
        btSuaCTHD.addActionListener(this);

        btHoTroNhapMaHD = new JButton("...");
        btHoTroNhapMaHD.setFont(new Font("Arial", Font.BOLD, 15));
        btHoTroNhapMaHD.setBounds(660, 65, 40, 30);
        btHoTroNhapMaHD.setBackground(MyColor.ColorButton);
        btHoTroNhapMaHD.setBorder(new RoundedBorder(10));
        btHoTroNhapMaHD.addActionListener(this);

        btHoTroNhapMasachHD = new JButton("...");
        btHoTroNhapMasachHD.setFont(new Font("Arial", Font.BOLD, 15));
        btHoTroNhapMasachHD.setBounds(660, 110, 40, 30);
        btHoTroNhapMasachHD.setBackground(MyColor.ColorButton);
        btHoTroNhapMasachHD.setBorder(new RoundedBorder(10));
        btHoTroNhapMasachHD.addActionListener(this);

        pnNhapHDTP.add(lbNhapCTHDTP);
        pnNhapHDTP.add(lbCTHDMaHD);
        pnNhapHDTP.add(lbCTHDMaSach);
        pnNhapHDTP.add(lbCTHDSL);
        pnNhapHDTP.add(lbCTHDDonGia);
        pnNhapHDTP.add(lbTienTra);
        pnNhapHDTP.add(lbTraTienPhat);

        pnNhapHDTP.add(txCTHDMaHD);
        pnNhapHDTP.add(txCTHDMaSach);
        pnNhapHDTP.add(txCTHDSL);
        pnNhapHDTP.add(txCTHDDonGia);
        pnNhapHDTP.add(txTienTra_HD);
        pnNhapHDTP.add(txTraTienPhat);

        pnNhapHDTP.add(btThemCTHD);
        pnNhapHDTP.add(btSuaCTHD);
        pnNhapHDTP.add(btHoTroNhapMaHD);
        pnNhapHDTP.add(btHoTroNhapMasachHD);
    }

    public void setTimKiemPM() {
        if (btTimKiemPM == null) { // Là button của phần tìm kiếm cơ bản
            if (lbLCTKPM != null || lbTuKhoaTKPM != null || cbDSKhoaTKPM != null || txKhoaTKPM != null) {
                lbLCTKPM.setVisible(true);
                lbTuKhoaTKPM.setVisible(true);
                cbDSKhoaTKPM.setVisible(true);
                txKhoaTKPM.setVisible(true);
            }

            // labelLCTK
            lbLCTKPM = new JLabel("Lựa chọn khóa tìm kiếm:");
            lbLCTKPM.setFont(new Font("Arial", Font.BOLD, 18));
            lbLCTKPM.setBounds(5, 25, 230, 100);

            // labelTuKhoaTK
            lbTuKhoaTKPM = new JLabel("Nhập từ khóa tìm kiếm:");
            lbTuKhoaTKPM.setFont(new Font("Arial", Font.BOLD, 18));
            lbTuKhoaTKPM.setBounds(5, 75, 230, 100);

            // JTextField Khóa tìm kiếm
            txKhoaTKPM = new JTextField();
            txKhoaTKPM.setFont(new Font("Arial", Font.PLAIN, 15));
            txKhoaTKPM.setBounds(250, 110, 150, 30);

            String[] dsKhoaTK = { "", "Mã phiếu mượn", "SL tổng", "Tình trạng mượn", "Mã độc giả" };
            cbDSKhoaTKPM = new JComboBox<>(dsKhoaTK);
            cbDSKhoaTKPM.setFont(new Font("Arial", Font.BOLD, 13));
            cbDSKhoaTKPM.setBounds(250, 60, 120, 30);
            cbDSKhoaTKPM.addActionListener(this);

            TitledBorder titleTK;
            Border blackline;
            blackline = BorderFactory.createLineBorder(Color.black);
            titleTK = BorderFactory.createTitledBorder(blackline, "Tìm kiếm");
            titleTK.setTitleFont(new Font("Arial", Font.BOLD, 25));
            titleTK.setTitleJustification(TitledBorder.CENTER);
            pnTimKiemPM.setBorder(titleTK);

            btTimKiemPM = new JButton("Tìm kiếm");
            btTimKiemPM.setFont(new Font("Arial", Font.BOLD, 15));
            btTimKiemPM.setBounds(300, 155, 100, 30);
            btTimKiemPM.setBackground(MyColor.ColorButton);
            btTimKiemPM.setBorder(new RoundedBorder(10));
            btTimKiemPM.addActionListener(this);

        }
        pnTimKiemPM.add(lbLCTKPM);
        pnTimKiemPM.add(lbTuKhoaTKPM);
        pnTimKiemPM.add(txKhoaTKPM);
        pnTimKiemPM.add(cbDSKhoaTKPM);
        pnTimKiemPM.add(btTimKiemPM);
    }

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
            lbLCTKPT.setFont(new Font("Arial", Font.BOLD, 18));
            lbLCTKPT.setBounds(5, 25, 230, 100);

            // labelTuKhoaTK
            lbTuKhoaTKPT = new JLabel("Nhập từ khóa tìm kiếm:");
            lbTuKhoaTKPT.setFont(new Font("Arial", Font.BOLD, 18));
            lbTuKhoaTKPT.setBounds(5, 75, 230, 100);

            // JTextField Khóa tìm kiếm
            txKhoaTKPT = new JTextField();
            txKhoaTKPT.setFont(new Font("Arial", Font.PLAIN, 15));
            txKhoaTKPT.setBounds(250, 110, 150, 30);

            String[] dsKhoaTK = { "", "Mã phiếu trả", "Tình trạng sách", "Thành tiền", "Mã phiếu mượn" };
            cbDSKhoaTKPT = new JComboBox<>(dsKhoaTK);
            cbDSKhoaTKPT.setFont(new Font("Arial", Font.BOLD, 13));
            cbDSKhoaTKPT.setBounds(250, 60, 120, 30);
            cbDSKhoaTKPT.addActionListener(this);

            TitledBorder titleTK;
            Border blackline;
            blackline = BorderFactory.createLineBorder(Color.black);
            titleTK = BorderFactory.createTitledBorder(blackline, "Tìm kiếm");
            titleTK.setTitleFont(new Font("Arial", Font.BOLD, 25));
            titleTK.setTitleJustification(TitledBorder.CENTER);
            pnTimKiemPT.setBorder(titleTK);

            btTimKiemPT = new JButton("Tìm kiếm");
            btTimKiemPT.setFont(new Font("Arial", Font.BOLD, 15));
            btTimKiemPT.setBounds(300, 155, 100, 30);
            btTimKiemPT.setBackground(MyColor.ColorButton);
            btTimKiemPT.setBorder(new RoundedBorder(10));
            btTimKiemPT.addActionListener(this);

        }
        pnTimKiemPT.add(lbLCTKPT);
        pnTimKiemPT.add(lbTuKhoaTKPT);
        pnTimKiemPT.add(txKhoaTKPT);
        pnTimKiemPT.add(cbDSKhoaTKPT);
        pnTimKiemPT.add(btTimKiemPT);
    }

    public void setTimKiemHDTP() {
        if (btTimKiemHDTP == null) { // Là button của phần tìm kiếm cơ bản
            if (lbLCTKHD != null || lbTuKhoaTKHD != null || cbDSKhoaTKHDTP != null || txKhoaTKHDTP != null) {
                lbLCTKHD.setVisible(true);
                lbTuKhoaTKHD.setVisible(true);
                cbDSKhoaTKHDTP.setVisible(true);
                txKhoaTKHDTP.setVisible(true);
            }

            // labelLCTK
            lbLCTKHD = new JLabel("Lựa chọn khóa tìm kiếm:");
            lbLCTKHD.setFont(new Font("Arial", Font.BOLD, 18));
            lbLCTKHD.setBounds(5, 25, 230, 100);

            // labelTuKhoaTK
            lbTuKhoaTKHD = new JLabel("Nhập từ khóa tìm kiếm:");
            lbTuKhoaTKHD.setFont(new Font("Arial", Font.BOLD, 18));
            lbTuKhoaTKHD.setBounds(5, 75, 230, 100);

            // JTextField Khóa tìm kiếm
            txKhoaTKHDTP = new JTextField();
            txKhoaTKHDTP.setFont(new Font("Arial", Font.PLAIN, 15));
            txKhoaTKHDTP.setBounds(250, 110, 150, 30);

            String[] dsKhoaTK = { "", "Mã hóa đơn", "Mã độc giả", "SL Tổng", "Tiền phạt" };
            cbDSKhoaTKHDTP = new JComboBox<>(dsKhoaTK);
            cbDSKhoaTKHDTP.setFont(new Font("Arial", Font.BOLD, 13));
            cbDSKhoaTKHDTP.setBounds(250, 60, 120, 30);
            cbDSKhoaTKHDTP.addActionListener(this);

            TitledBorder titleTK;
            Border blackline;
            blackline = BorderFactory.createLineBorder(Color.black);
            titleTK = BorderFactory.createTitledBorder(blackline, "Tìm kiếm");
            titleTK.setTitleFont(new Font("Arial", Font.BOLD, 25));
            titleTK.setTitleJustification(TitledBorder.CENTER);
            pnTimKiemHDTP.setBorder(titleTK);

            btTimKiemHDTP = new JButton("Tìm kiếm");
            btTimKiemHDTP.setFont(new Font("Arial", Font.BOLD, 15));
            btTimKiemHDTP.setBounds(300, 155, 100, 30);
            btTimKiemHDTP.setBackground(MyColor.ColorButton);
            btTimKiemHDTP.setBorder(new RoundedBorder(10));
            btTimKiemHDTP.addActionListener(this);

        }
        pnTimKiemHDTP.add(lbLCTKHD);
        pnTimKiemHDTP.add(lbTuKhoaTKHD);
        pnTimKiemHDTP.add(txKhoaTKHDTP);
        pnTimKiemHDTP.add(cbDSKhoaTKHDTP);
        pnTimKiemHDTP.add(btTimKiemHDTP);
    }

    public void setLocPM() {
        if (btLocPM == null) {
            if (lbNgayBDLocPM != null || lbNgayKTLocPM != null || datePickerNgayBDMuon != null
                    || datePickerNgayKTMuon != null) {
                lbNgayBDLocPM.setVisible(true);
                lbNgayKTLocPM.setVisible(true);
                datePickerNgayBDMuon.setVisible(true);
                datePickerNgayKTMuon.setVisible(true);
            }

            // set Border
            TitledBorder titleLoc;
            Border blackline;
            blackline = BorderFactory.createLineBorder(Color.black);
            titleLoc = BorderFactory.createTitledBorder(blackline, "Lọc dữ liệu");
            titleLoc.setTitleFont(new Font("Arial", Font.BOLD, 25));
            titleLoc.setTitleJustification(TitledBorder.CENTER);
            pnLocPM.setBorder(titleLoc);

            lbNgayBDLocPM = new JLabel("Ngày bắt đầu: ");
            lbNgayBDLocPM.setFont(new Font("Arial", Font.BOLD, 18));
            lbNgayBDLocPM.setBounds(5, 15, 150, 80);

            lbNgayKTLocPM = new JLabel("Ngày kết thúc: ");
            lbNgayKTLocPM.setFont(new Font("Arial", Font.BOLD, 18));
            lbNgayKTLocPM.setBounds(5, 55, 150, 80);

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

            btLocPM = new JButton("Lọc");
            btLocPM.setFont(new Font("Arial", Font.BOLD, 15));
            btLocPM.setBounds(210, 115, 80, 30);
            btLocPM.setBackground(MyColor.ColorButton);
            btLocPM.setBorder(new RoundedBorder(10));
            btLocPM.addActionListener(this);
        }
        pnLocPM.add(lbNgayBDLocPM);
        pnLocPM.add(lbNgayKTLocPM);
        pnLocPM.add(datePickerNgayBDMuon);
        pnLocPM.add(datePickerNgayKTMuon);
        pnLocPM.add(btLocPM);
    }

    public void setLocPT() {
        if (btLocPT == null) {
            if (lbNgayBDLocPT != null || lbNgayKTLocPT != null || datePickerNgayBDTra != null
                    || datePickerNgayKTTra != null) {
                lbNgayBDLocPT.setVisible(true);
                lbNgayKTLocPT.setVisible(true);
                datePickerNgayBDTra.setVisible(true);
                datePickerNgayKTTra.setVisible(true);
            }
            // set Border
            TitledBorder titleLoc;
            Border blackline;
            blackline = BorderFactory.createLineBorder(Color.black);
            titleLoc = BorderFactory.createTitledBorder(blackline, "Lọc dữ liệu");
            titleLoc.setTitleFont(new Font("Arial", Font.BOLD, 25));
            titleLoc.setTitleJustification(TitledBorder.CENTER);
            lbNgayBDLocPT = new JLabel("Ngày bắt đầu: ");
            lbNgayBDLocPT.setFont(new Font("Arial", Font.BOLD, 18));
            lbNgayBDLocPT.setBounds(5, 15, 150, 80);

            lbNgayKTLocPT = new JLabel("Ngày kết thúc: ");
            lbNgayKTLocPT.setFont(new Font("Arial", Font.BOLD, 18));
            lbNgayKTLocPT.setBounds(5, 55, 150, 80);

            btLocPT = new JButton("Lọc");
            btLocPT.setFont(new Font("Arial", Font.BOLD, 15));
            btLocPT.setBounds(210, 115, 80, 30);
            btLocPT.setBackground(MyColor.ColorButton);
            btLocPT.setBorder(new RoundedBorder(10));
            btLocPT.addActionListener(this);

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
            pnLocPT.add(lbNgayBDLocPT);
            pnLocPT.add(lbNgayKTLocPT);
            pnLocPT.add(btLocPT);
            pnLocPT.add(datePickerNgayBDTra);
            pnLocPT.add(datePickerNgayKTTra);
        }
    }

    public void getInfoTextFieldPM(PHIEUMUON phieumuon) {
        phieumuon.setMaPM(txMaPM.getText().replaceAll("\\s+", "").trim());
        phieumuon.setNgaymuon(datePickerNgayBDPM.getJFormattedTextField().getText());
        phieumuon.setSLtong(Integer.parseInt(txSLtong.getText().replaceAll("\\s+", "").trim()));
        phieumuon.setNgaytra(datePickerNgayKTPM.getJFormattedTextField().getText());
        String TinhTrangMuon = (String) cbTinhTrangMuon.getSelectedItem();
        phieumuon.setTinhTrangMuon(TinhTrangMuon);
        phieumuon.setMaDG(txMaDG.getText().replaceAll("\\s+", "").trim());
    }

    public void getInfoTextFieldCTPM(CHITIETPHIEUMUON ctphieumuon) {
        ctphieumuon.setMaPM(txCTPMMaPM.getText().replaceAll("\\s+", "").trim());
        ctphieumuon.setMasach(txCTPMMaSach.getText().replaceAll("\\s+", "").trim());
        ctphieumuon.setSL(Integer.parseInt(txCTPMSL.getText().replaceAll("\\s+", "").trim()));
    }

    public void getInfoTextFieldPT(PHIEUTRASACH phieutrasach) {
        phieutrasach.setMaPT(txMaPT.getText().replaceAll("\\s+", "").trim());
        phieutrasach.setNgaytra(datePickerNgayBDPT.getJFormattedTextField().getText());
        String TinhTrangSach = (String) cbTinhTrangTra.getSelectedItem();
        phieutrasach.setTinhtrangsach(TinhTrangSach);
        String Tienthue = myTable.RemoveCommaInString(txTienThue);
        phieutrasach.setTienthue(Integer.parseInt(Tienthue));

        String ThanhTien = myTable.RemoveCommaInString(txThanhTien);
        phieutrasach.setThanhtien(Integer.parseInt(ThanhTien.trim()));

        phieutrasach.setMaPM(txMaPMTra.getText().replaceAll("\\s+", "").trim());
    }

    public void getInfoTextFieldCTPT(CHITIETPHIEUTRA ctphieutra) {
        ctphieutra.setMaPT(txCTPTMaPT.getText().replaceAll("\\s+", "").trim());
        ctphieutra.setMasach(txCTPTMaSach.getText().replaceAll("\\s+", "").trim());
        ctphieutra.setSL(Integer.parseInt(txCTPTSL.getText().trim()));
    }

    public void getInfoTextFieldHD(HDTIENPHAT hdtienphat) {
        hdtienphat.setMaHD(txMaHD.getText().replaceAll("\\s+", "").trim());
        hdtienphat.setMaDG(txMaHD_DG.getText().replaceAll("\\s+", "").trim());
        hdtienphat.setSL(Integer.parseInt(txSLTongHD.getText().trim()));
        String TienPhat = myTable.RemoveCommaInString(txTienPhat);
        hdtienphat.setTienphat(Integer.parseInt(TienPhat.trim()));
    }

    public void getInfoTextFieldCTHD(CHITIETHDTIENPHAT chitiethdtienphat) {
        chitiethdtienphat.setMaHD(txCTHDMaHD.getText().replaceAll("\\s+", "").trim());
        chitiethdtienphat.setMasach(txCTHDMaSach.getText().replaceAll("\\s+", "").trim());
        chitiethdtienphat.setSL(Integer.parseInt(txCTHDSL.getText().trim()));
        String DonGia = myTable.RemoveCommaInString(txCTHDDonGia);
        chitiethdtienphat.setDongia(Integer.parseInt(DonGia.trim()));
    }

    public void updateSLSachMuon(String Masach) { // Mã sácg cần cập nhật
        QLSACHBUS qlbus = new QLSACHBUS();
        if (QLSACHBUS.dssach == null) {
            try {
                qlbus.docDSSACH();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        int indexSua = 0;
        // Tìm mã sách để cập nhật số lượng
        for (SACH sach : QLSACHBUS.dssach) {
            if (sach.getMasach().trim().equals(Masach)) {
                int SL = sach.getSL() - Integer.parseInt(txCTPMSL.getText());
                sach.setSL(SL);
                try {
                    qlbus.sua(sach, sach, indexSua);
                } catch (Exception e) {
                    System.out.println(e);
                }
            }
            indexSua++;
        }
        // Show lên table
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

    public void updateSLSachTra(String Masach) {
        QLSACHBUS qlbus = new QLSACHBUS();
        if (QLSACHBUS.dssach == null) {
            try {
                qlbus.docDSSACH();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        int indexSua = 0;
        // Tìm mã sách để cập nhật số lượng
        for (SACH sach : QLSACHBUS.dssach) {
            if (sach.getMasach().trim().equals(Masach)) {
                int SL = sach.getSL() + Integer.parseInt(txCTPTSL.getText());
                sach.setSL(SL);
                try {
                    qlbus.sua(sach, sach, indexSua);
                } catch (Exception e) {
                    System.out.println(e);
                }
            }
            indexSua++;
        }
        // Show lên table
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

    public int checkSLSachConLai(String Masach) {
        if (QLSACHBUS.dssach == null) {
            QLSACHBUS qlbus = new QLSACHBUS();
            try {
                qlbus.docDSSACH();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        for (SACH sach : QLSACHBUS.dssach) {
            if (sach.getMasach().trim().equals(Masach) && sach.getSL() < Integer.parseInt(txCTPMSL.getText())) {
                JOptionPane.showMessageDialog(null, "Số lượng cần mượn lớn hơn số lượng sách trong kho", "Lỗi",
                        JOptionPane.ERROR_MESSAGE);
                return -1;
            }
            if (sach.getMasach().trim().equals(Masach) && sach.getSL() <= 0) {
                JOptionPane.showMessageDialog(null, "Sách trong kho đã hết", "Lỗi",
                        JOptionPane.ERROR_MESSAGE);
                return -1;
            }
        }
        return 0;
    }

    public int checkSLSachTra(String Masach) {
        if (QLSACHBUS.dssach == null) {
            QLSACHBUS qlbus = new QLSACHBUS();
            try {
                qlbus.docDSSACH();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        for (SACH sach : QLSACHBUS.dssach) {
            int SLtra = sach.getSL() + Integer.parseInt(txCTPTSL.getText());
            if (sach.getMasach().trim().equals(Masach) && sach.getSLtong() < SLtra) {
                JOptionPane.showMessageDialog(null, "Số lượng cần trả lớn hơn số lượng sách trong kho", "Lỗi",
                        JOptionPane.ERROR_MESSAGE);
                return -1;
            }
        }
        return 0;
    }
}
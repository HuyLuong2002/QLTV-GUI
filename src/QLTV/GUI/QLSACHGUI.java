package QLTV.GUI;

import java.awt.Color;
import java.awt.Dimension;
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
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import MyCustom.DateLabelFormatter;
import MyCustom.LoginPage;
import MyCustom.RoundedBorder;
import QLTV.BUS.QLSACHBUS;
import QLTV.DTO.SACH;

public class QLSACHGUI extends JFrame implements ActionListener, MouseListener {
    JPanel pnTTSach, pnNhapTTSach, pnShowAll, pnMenu, pnTimKiem, pnLoc;
    JLabel lbHome, lbTTSach, lbMasach, lbTensach, lbMaNXB, lbMaTG, lbNamXB, lbSLtong, lbSL, lbDongia, lbLCTK,
            lbTuKhoaTK, lbKQTK;
    JLabel lbTKNam, lbTKSL, lbNgayBD, lbNgayKT;
    JTextField txMasach, txTensach, txMaNXB, txMaTG, txNamXB, txSLtong, txSL, txDongia, txKhoaTK;
    JTextField txTKNam, txTKSL;
    JButton btDoc, btThem, btSua, btXoa, btHoanTac, btMenuTimKiem, btShowAll, btThongKe;
    JButton btMenu, btMT, btQLNV, btDangXuat, btNhapSach, btThoat;
    JButton btTK, btSearch, btLoc;

    JRadioButton rbNam, rbNu, rbKhac;
    ButtonGroup buttonGroupGT;
    JComboBox<String> comboBoxDSKhoaTK;

    JTable tblQLSACH;
    DefaultTableModel model;
    Vector<String> header;

    UtilDateModel modelNgayBD, modelNgayKT;
    Properties pNgayBD, pNgayKT;
    JDatePanelImpl datePanelNgayBD, datePanelNgayKT;
    JDatePickerImpl datePickerNgayBD, datePickerNgayKT;

    public QLSACHGUI() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        this.setTitle("Quản lý thông tin sách");
        this.setLayout(null);

        pnTTSach = new JPanel();
        pnNhapTTSach = new JPanel();
        pnShowAll = new JPanel();
        pnMenu = new JPanel();
        pnTimKiem = new JPanel();
        pnLoc = new JPanel();
        pnTTSach.setLayout(new GridLayout(3, 1, 0, -300));
        pnTTSach.setBounds(320, 0, 1200, 400);
        pnShowAll.setLayout(null);
        pnShowAll.setBounds(300, 400, 1537, 40);
        pnNhapTTSach.setLayout(null);
        pnNhapTTSach.setBounds(200, 415, 820, 600);
        pnMenu.setBackground(Color.LIGHT_GRAY);
        pnMenu.setLayout(new GridLayout(15, 1));
        pnMenu.setSize(new Dimension(300, 1200));
        pnTimKiem.setLayout(null);
        pnTimKiem.setBounds(1030, 445, 490, 350);
        pnLoc.setLayout(null);
        pnLoc.setBounds(520, 130, 300, 180);

        // add components
        this.add(pnMenu);
        this.add(pnShowAll);
        this.add(pnNhapTTSach);
        this.add(pnTimKiem);
        pnNhapTTSach.add(pnLoc);

        setTableSach();
        setInput();
        setLoc();
        setMenu();
        setShowAll();
        getDatabase();

        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btDoc) {
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
                header.add("Mã sách");
                header.add("Tên sách");
                header.add("Mã NXB");
                header.add("Mã tác giả");
                header.add("Năm xuất bản");
                header.add("SL tổng");
                header.add("SL");
                header.add("Đơn giá");
                model = new DefaultTableModel(header, 0);
                for (SACH sach : QLSACHBUS.dssach) {
                    ShowOnTable(sach);
                    tblQLSACH.setModel(model);
                }
            } catch (Exception e1) {
                System.out.println(e1);
            }
        } else if (e.getSource() == btThem) {
            try {
                SACH sach = new SACH();
                getInfoTextField(sach);
                // Truy cập vào bus
                QLSACHBUS qlsachbus = new QLSACHBUS();
                int kiemtra = 0;
                try {
                    kiemtra = qlsachbus.them(sach);
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

            if (i >= 0) {
                SACH sach = new SACH();
                SACH masachCu = QLSACHBUS.dssach.set(i, sach);
                getInfoTextField(sach);
                try {
                    QLSACHBUS qlsachbus = new QLSACHBUS();
                    qlsachbus.sua(sach, masachCu, i);
                    model.setValueAt(sach.getMasach(), i, 0);
                    model.setValueAt(sach.getTensach(), i, 1);
                    model.setValueAt(sach.getMaNXB(), i, 2);
                    model.setValueAt(sach.getMaTG(), i, 3);
                    model.setValueAt(sach.getNamXB(), i, 4);
                    model.setValueAt(sach.getSLtong(), i, 5);
                    model.setValueAt(sach.getSL(), i, 6);
                    model.setValueAt(sach.getDongia(), i, 7);
                    tblQLSACH.setModel(model);
                } catch (Exception e1) {
                    System.out.println(e1);
                }
            }
        } else if (e.getSource() == btXoa) {
            int XacNhanXoa = JOptionPane.showConfirmDialog(null, "Bạn có chắc chắn muốn xóa không ?", "Thông báo",
                    JOptionPane.YES_NO_OPTION);
            if (XacNhanXoa == 0) {
                String masach = txMasach.getText();
                int i = tblQLSACH.getSelectedRow();
                if (i >= 0) {
                    try {
                        // Truy cập xuống BUS
                        SACH SachOld = QLSACHBUS.dssach.get(i);
                        QLSACHBUS.htXoa.add(SachOld);
                        QLSACHBUS qlsachbus = new QLSACHBUS();
                        qlsachbus.xoa(masach, i);
                        // Quay dề GUI
                        model.removeRow(i);
                        tblQLSACH.setModel(model);
                    } catch (Exception e1) {
                        System.out.println(e1);
                    }
                }
            }
        } else if (e.getSource() == btHoanTac) {
            int ktHT = 0;
            if (QLSACHBUS.htXoa.size() == 0) {
                JOptionPane.showMessageDialog(null, "Dữ liệu hoàn tác rỗng", "Lỗi",
                        JOptionPane.ERROR_MESSAGE);
            } else {
                for (SACH sach : QLSACHBUS.htXoa) {
                    QLSACHBUS qlsachbus = new QLSACHBUS();
                    int kiemtra = 0;
                    try {
                        kiemtra = qlsachbus.hoantacXoa(sach);
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
                        if (model.getRowCount() == 0) {
                            model = new DefaultTableModel(header, 0);
                        }
                        ShowOnTable(sach);
                        ktHT=1;
                    } else if (kiemtra == 0) {
                        JOptionPane.showMessageDialog(null, "Hoàn tác dữ liệu thất bại", "Lỗi",
                                JOptionPane.ERROR_MESSAGE);
                        ktHT=0;
                    }
                }
            }
            if(ktHT==1){
                JOptionPane.showMessageDialog(null, "Hoàn tác dữ liệu thành công", "Thông báo",
                JOptionPane.INFORMATION_MESSAGE);
                tblQLSACH.setModel(model);
            }
        } else if (e.getSource() == comboBoxDSKhoaTK) {
            int vtkey = Integer.parseInt(String.valueOf(comboBoxDSKhoaTK.getSelectedIndex()));
            if (vtkey == 9 || vtkey == 10) {
                setTimKiemNC();
            } else {
                // Ẩn lại các component của combobox key 9 10
                if (lbTKNam != null && txTKNam != null && lbTKSL != null && txTKSL != null && btTK != null) {
                    lbTKNam.setVisible(false);
                    txTKNam.setVisible(false);
                    lbTKSL.setVisible(false);
                    txTKSL.setVisible(false);
                    btTK.setVisible(false);
                }
                // Hiển thị các component của các key khác
                if (btSearch.isVisible() || txKhoaTK.isVisible() || btThongKe.isVisible()) {
                    btSearch.setVisible(true);
                    txKhoaTK.setVisible(true);
                    btThongKe.setVisible(true);
                }
            }
        } else if (e.getSource() == btTK) {
            // btTK là của tìm kiếm nâng cao
            lbKQTK.setFont(new Font("Arial", Font.BOLD, 20));
            lbKQTK.setForeground(Color.red);
            String NamXB = txTKNam.getText();
            String SL = txTKSL.getText();
            if (NamXB.equals("") == true || SL.equals("") == true) {
                JOptionPane.showMessageDialog(null, "Xin mời nhập từ khóa", "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
            QLSACHBUS qlsachbus = new QLSACHBUS();
            int vtkey = Integer.parseInt(String.valueOf(comboBoxDSKhoaTK.getSelectedIndex()));
            if (vtkey == 9) {
                ArrayList<SACH> kq = qlsachbus.timNamXBHoacSL(NamXB, SL);
                model.setRowCount(0);
                if (kq.size() == 0) {
                    lbKQTK.setText("Kết quả tìm kiếm: Không tìm thấy");
                } else {
                    for (SACH sach : kq) {
                        ShowOnTable(sach);
                    }
                }
                lbKQTK.setText("Kết quả tìm kiếm: Tìm thấy " + model.getRowCount() + " SV");
                tblQLSACH.setModel(model);
            } else if (vtkey == 10) {
                ArrayList<SACH> kq = qlsachbus.timNamXBVaSL(NamXB, SL);
                model.setRowCount(0);
                if (kq.size() == 0) {
                    lbKQTK.setText("Kết quả tìm kiếm: Không tìm thấy");
                } else {
                    for (SACH sach : kq) {
                        ShowOnTable(sach);
                    }
                }
                lbKQTK.setText("Kết quả tìm kiếm: Tìm thấy " + model.getRowCount() + " SV");
                tblQLSACH.setModel(model);
            }
        } else if (e.getSource() == btMenuTimKiem) { // Của button Tìm kiếm sách, để hiện thị
            // khung tìm kiếm
            OffBgSelected();
            btMenuTimKiem.setBackground(Color.green);
            setTimKiem();
        } else if (e.getSource() == btSearch) {
            int vtkey = Integer.parseInt(String.valueOf(comboBoxDSKhoaTK.getSelectedIndex()));
            String tukhoa = txKhoaTK.getText();
            lbKQTK.setFont(new Font("Arial", Font.BOLD, 20));
            lbKQTK.setForeground(Color.red);
            if (tukhoa.equals("") == true) {
                JOptionPane.showMessageDialog(null, "Xin mời nhập từ khóa", "Lỗi", JOptionPane.ERROR_MESSAGE);
            } else if (vtkey == 0) {
                JOptionPane.showMessageDialog(null, "Xin mời lựa chọn khóa tìm kiếm", "Lỗi", JOptionPane.ERROR_MESSAGE);
            } else {
                QLSACHBUS qlsachbus = new QLSACHBUS();
                if (vtkey == 1) {
                    SACH kq = qlsachbus.timTheoMa(tukhoa);
                    model.setRowCount(0);
                    if (kq == null) {
                        lbKQTK.setText("Kết quả tìm kiếm: Không tìm thấy");
                    } else {
                        ShowOnTable(kq);
                        lbKQTK.setText("Kết quả tìm kiếm: Tìm thấy " + model.getRowCount() + " SV");
                        tblQLSACH.setModel(model);
                    }
                } else if (vtkey == 2) {
                    ArrayList<SACH> kq = qlsachbus.timTheoTen(tukhoa);
                    model.setRowCount(0);
                    if (kq.size() == 0) {
                        lbKQTK.setText("Kết quả tìm kiếm: Không tìm thấy");
                    } else {
                        for (SACH sach : kq) {
                            ShowOnTable(sach);
                        }
                        lbKQTK.setText("Kết quả tìm kiếm: Tìm thấy " + model.getRowCount() + " SV");
                        tblQLSACH.setModel(model);
                    }
                } else if (vtkey == 3) {
                    ArrayList<SACH> kq = qlsachbus.timTheoMaNXB(tukhoa);
                    model.setRowCount(0);
                    if (kq.size() == 0) {
                        lbKQTK.setText("Kết quả tìm kiếm: Không tìm thấy");
                    } else {
                        for (SACH sach : kq) {
                            ShowOnTable(sach);
                        }
                        lbKQTK.setText("Kết quả tìm kiếm: Tìm thấy " + model.getRowCount() + " SV");
                        tblQLSACH.setModel(model);
                    }
                } else if (vtkey == 4) {
                    ArrayList<SACH> kq = qlsachbus.timTheoMaTG(tukhoa);
                    model.setRowCount(0);
                    if (kq.size() == 0) {
                        lbKQTK.setText("Kết quả tìm kiếm: Không tìm thấy");
                    } else {
                        for (SACH sach : kq) {
                            ShowOnTable(sach);
                        }
                        lbKQTK.setText("Kết quả tìm kiếm: Tìm thấy " + model.getRowCount() + " SV");
                        tblQLSACH.setModel(model);
                    }
                } else if (vtkey == 5) {
                    ArrayList<SACH> kq = qlsachbus.timTheoNamXB(tukhoa);
                    model.setRowCount(0);
                    if (kq.size() == 0) {
                        lbKQTK.setText("Kết quả tìm kiếm: Không tìm thấy");
                    } else {
                        for (SACH sach : kq) {
                            ShowOnTable(sach);
                        }
                        lbKQTK.setText("Kết quả tìm kiếm: Tìm thấy " + model.getRowCount() + " SV");
                        tblQLSACH.setModel(model);
                    }
                } else if (vtkey == 6) {
                    ArrayList<SACH> kq = qlsachbus.timTheoSLtong(tukhoa);
                    model.setRowCount(0);
                    if (kq.size() == 0) {
                        lbKQTK.setText("Kết quả tìm kiếm: Không tìm thấy");
                    } else {
                        for (SACH sach : kq) {
                            ShowOnTable(sach);
                        }
                        lbKQTK.setText("Kết quả tìm kiếm: Tìm thấy " + model.getRowCount() + " SV");
                        tblQLSACH.setModel(model);
                    }
                } else if (vtkey == 7) {
                    ArrayList<SACH> kq = qlsachbus.timTheoSL(tukhoa);
                    model.setRowCount(0);
                    if (kq.size() == 0) {
                        lbKQTK.setText("Kết quả tìm kiếm: Không tìm thấy");
                    } else {
                        for (SACH sach : kq) {
                            ShowOnTable(sach);
                        }
                        lbKQTK.setText("Kết quả tìm kiếm: Tìm thấy " + model.getRowCount() + " SV");
                        tblQLSACH.setModel(model);
                    }
                } else if (vtkey == 8) {
                    ArrayList<SACH> kq = qlsachbus.timTheoDonGia(tukhoa);
                    model.setRowCount(0);
                    if (kq.size() == 0) {
                        lbKQTK.setText("Kết quả tìm kiếm: Không tìm thấy");
                    } else {
                        for (SACH sach : kq) {
                            ShowOnTable(sach);
                        }
                        lbKQTK.setText("Kết quả tìm kiếm: Tìm thấy " + model.getRowCount() + " SV");
                        tblQLSACH.setModel(model);
                    }
                }
            }
        } else if (e.getSource() == btShowAll) {
            lbKQTK.setText("");
            model.setRowCount(0);
            for (SACH sach : QLSACHBUS.dssach) {
                ShowOnTable(sach);
            }
            tblQLSACH.setModel(model);
        }
        if (e.getSource() == btThongKe) {
            String luachon = "";
            int count, sum;
            do {
                String[] options = { "", "Mã sách", "SL sách ban đầu", "SL hiện tại" };
                luachon = (String) JOptionPane.showInputDialog(null, "Mời lựa chọn khóa thống kê:", "Thống kê",
                        JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);
                QLSACHBUS qlsachbus = new QLSACHBUS();
                switch (luachon) {
                    case "Mã sách":
                        count = qlsachbus.ThongKeMaSach();
                        JOptionPane.showMessageDialog(null, "Đếm số lượng sách: " + String.valueOf(count),
                                "Kết quả", JOptionPane.PLAIN_MESSAGE);
                        break;
                    case "SL sách ban đầu":
                        sum = qlsachbus.ThongKeSLBD();
                        JOptionPane.showMessageDialog(null, "Số lượng sách ban đầu: " + String.valueOf(sum),
                                "Kết quả", JOptionPane.PLAIN_MESSAGE);
                        break;
                    case "SL hiện tại":
                        sum = qlsachbus.ThongKeSLHT();
                        JOptionPane.showMessageDialog(null, "Số lượng sách hiện tại: " + String.valueOf(sum),
                                "Kết quả", JOptionPane.PLAIN_MESSAGE);
                        break;
                }
            } while (luachon != null);
        }
        if (e.getSource() == btMenu) {

        }
        if (e.getSource() == btDangXuat) {
            this.dispose();
            try {
                new LoginPage();
            } catch (InterruptedException e1) {
                System.out.println(e1);
            }
        }
        if (e.getSource() == btLoc) {
            lbKQTK.setFont(new Font("Arial", Font.BOLD, 20));
            lbKQTK.setForeground(Color.red);
            String tmp[] = datePickerNgayBD.getJFormattedTextField().getText().split("-");
            String tmp1[] = datePickerNgayKT.getJFormattedTextField().getText().split("-");
            int NamBD = Integer.parseInt(tmp[0]);
            int NamKT = Integer.parseInt(tmp1[0]);
            QLSACHBUS qlsachbus = new QLSACHBUS();
            ArrayList<SACH> kq = qlsachbus.loc(NamBD, NamKT);
            model.setRowCount(0);
            if (kq.size() == 0) {
                lbKQTK.setText("Kết quả lọc: Không thỏa điều kiện");
            } else {
                for (SACH sach : kq) {
                    ShowOnTable(sach);
                }
                lbKQTK.setText("Kết quả lọc: " + model.getRowCount() + " SV");
                tblQLSACH.setModel(model);
            }
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource() == tblQLSACH) {
            int i = tblQLSACH.getSelectedRow();
            if (i >= 0) {
                SACH sach = new SACH();
                sach = QLSACHBUS.dssach.get(i);
                txMasach.setText(sach.getMasach());
                txTensach.setText(sach.getTensach());
                txMaNXB.setText(sach.getMaNXB());
                txMaTG.setText(sach.getMaTG());
                txNamXB.setText(sach.getNamXB());
                txSLtong.setText(String.valueOf(sach.getSLtong()));
                txSL.setText(String.valueOf(sach.getSL()));
                txDongia.setText(String.valueOf(sach.getDongia()));
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
            txMasach.setToolTipText("Gợi ý: MS001");
        } else if (e.getSource() == txMaNXB) {
            txMaNXB.setToolTipText("Gợi ý: NXB001");
        } else if (e.getSource() == txMaTG) {
            txMaTG.setToolTipText("Gợi ý: TG001");
        } else if (e.getSource() == txNamXB) {
            txNamXB.setToolTipText("Gợi ý: 2002");
        } else if (e.getSource() == txSLtong) {
            txSLtong.setToolTipText("Gợi ý: SLtong > SL");
        } else if (e.getSource() == txSL) {
            txSL.setToolTipText("Gợi ý: SLtong > SL");
        } else if (e.getSource() == txDongia) {
            txDongia.setToolTipText("Gợi ý: 3000");
        }

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    public void setInput() {
        // label Masach
        lbMasach = new JLabel("Mã sách:");
        lbMasach.setFont(new Font("Arial", Font.BOLD, 20));
        lbMasach.setBounds(120, 0, 150, 100);
        // label Tensach
        lbTensach = new JLabel("Tên sách:");
        lbTensach.setFont(new Font("Arial", Font.BOLD, 20));
        lbTensach.setBounds(120, 50, 150, 100);
        // label MaNXB
        lbMaNXB = new JLabel("Mã NXB:");
        lbMaNXB.setFont(new Font("Arial", Font.BOLD, 20));
        lbMaNXB.setBounds(120, 100, 150, 100);
        // label MaTG
        lbMaTG = new JLabel("Mã tác giả: ");
        lbMaTG.setFont(new Font("Arial", Font.BOLD, 20));
        lbMaTG.setBounds(120, 150, 150, 100);
        // label NamXB
        lbNamXB = new JLabel("Năm xuất bản:");
        lbNamXB.setFont(new Font("Arial", Font.BOLD, 20));
        lbNamXB.setBounds(120, 200, 150, 100);
        // label SLtong
        lbSLtong = new JLabel("SL tổng:");
        lbSLtong.setFont(new Font("Arial", Font.BOLD, 20));
        lbSLtong.setBounds(120, 250, 150, 100);
        // label SL
        lbSL = new JLabel("SL:");
        lbSL.setFont(new Font("Arial", Font.BOLD, 20));
        lbSL.setBounds(520, 0, 150, 100);
        // label Đơn giá
        lbDongia = new JLabel("Đơn giá:");
        lbDongia.setFont(new Font("Arial", Font.BOLD, 20));
        lbDongia.setBounds(520, 50, 150, 100);
        // labelLCTK
        lbLCTK = new JLabel("Lựa chọn khóa tìm kiếm:");
        lbLCTK.setFont(new Font("Arial", Font.BOLD, 20));
        lbLCTK.setBounds(10, 50, 250, 100);

        // labelTuKhoaTK
        lbTuKhoaTK = new JLabel("Nhập từ khóa tìm kiếm:");
        lbTuKhoaTK.setFont(new Font("Arial", Font.BOLD, 20));
        lbTuKhoaTK.setBounds(10, 100, 250, 100);

        // JTextField Mã sách
        txMasach = new JTextField();
        txMasach.setBounds(270, 30, 200, 35);
        txMasach.setFont(new Font("Arial", Font.PLAIN, 15));
        txMasach.addMouseListener(this);
        // JTextField Tên sách
        txTensach = new JTextField();
        txTensach.setBounds(270, 80, 200, 35);
        txTensach.setFont(new Font("Arial", Font.PLAIN, 15));
        txTensach.setEditable(false);
        txTensach.addMouseListener(this);
        // JTextField Mã nhà xuất bản
        txMaNXB = new JTextField();
        txMaNXB.setBounds(270, 130, 200, 35);
        txMaNXB.setFont(new Font("Arial", Font.PLAIN, 15));
        txMaNXB.addMouseListener(this);
        // JTextField Mã tác giả
        txMaTG = new JTextField();
        txMaTG.setBounds(270, 180, 200, 35);
        txMaTG.setFont(new Font("Arial", Font.PLAIN, 15));
        txMaTG.addMouseListener(this);
        // JTextField Năm xuất bản
        txNamXB = new JTextField();
        txNamXB.setBounds(270, 230, 200, 35);
        txNamXB.setFont(new Font("Arial", Font.PLAIN, 15));
        txNamXB.addMouseListener(this);
        // JTextField SL tổng
        txSLtong = new JTextField();
        txSLtong.setBounds(270, 280, 200, 35);
        txSLtong.setFont(new Font("Arial", Font.PLAIN, 15));
        txSLtong.addMouseListener(this);
        // JTextField SL
        txSL = new JTextField();
        txSL.setBounds(620, 30, 200, 35);
        txSL.setFont(new Font("Arial", Font.PLAIN, 15));
        txSL.addMouseListener(this);
        // JTextField Đơn giá
        txDongia = new JTextField();
        txDongia.setBounds(620, 80, 200, 35);
        txDongia.setFont(new Font("Arial", Font.PLAIN, 15));
        txDongia.addMouseListener(this);
        // JTextField Khóa tìm kiếm
        txKhoaTK = new JTextField();
        txKhoaTK.setFont(new Font("Arial", Font.PLAIN, 15));
        txKhoaTK.setBounds(260, 135, 200, 35);
        txKhoaTK.addMouseListener(this);

        // JButtonDoc
        btDoc = new JButton("Đọc DS");
        btDoc.setFont(new Font("Arial", Font.BOLD, 15));
        btDoc.setBounds(120, 330, 100, 40);
        btDoc.setBackground(Color.cyan);
        btDoc.setBorder(new RoundedBorder(10));
        btDoc.addActionListener(this);
        // JbuttonThem
        btThem = new JButton("Thêm");
        btThem.setFont(new Font("Arial", Font.BOLD, 15));
        btThem.setBounds(120, 330, 100, 40);
        btThem.setBackground(Color.cyan);
        btThem.setBorder(new RoundedBorder(10));
        btThem.addActionListener(this);
        // JbuttonSua
        btSua = new JButton("Sửa");
        btSua.setFont(new Font("Arial", Font.BOLD, 15));
        btSua.setBounds(240, 330, 100, 40);
        btSua.setBackground(Color.cyan);
        btSua.setBorder(new RoundedBorder(10));
        btSua.addActionListener(this);
        // JbuttonXoa
        btXoa = new JButton("Xóa");
        btXoa.setFont(new Font("Arial", Font.BOLD, 15));
        btXoa.setBounds(360, 330, 100, 40);
        btXoa.setBackground(Color.cyan);
        btXoa.setBorder(new RoundedBorder(10));
        btXoa.addActionListener(this);
        // JbuttonHoanTac
        btHoanTac = new JButton("Hoàn tác");
        btHoanTac.setFont(new Font("Arial", Font.BOLD, 15));
        btHoanTac.setBounds(480, 330, 100, 40);
        btHoanTac.setBackground(Color.cyan);
        btHoanTac.setBorder(new RoundedBorder(10));
        btHoanTac.addActionListener(this);

        // set up ComboBox
        String[] dsKhoaTK = { "", "Mã sách", "Tên sách", "Mã NXB", "Mã TG", "Năm XB", "SL tổng", "SL", "Đơn giá",
                "Năm hoặc SL", "Năm và SL" };
        comboBoxDSKhoaTK = new JComboBox<>(dsKhoaTK);
        comboBoxDSKhoaTK.setFont(new Font("Arial", Font.BOLD, 13));
        comboBoxDSKhoaTK.setBounds(260, 85, 120, 35);
        comboBoxDSKhoaTK.addActionListener(this);

        btDoc.setVisible(false);
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

        pnNhapTTSach.add(btDoc);
        pnNhapTTSach.add(btThem);
        pnNhapTTSach.add(btSua);
        pnNhapTTSach.add(btXoa);
        pnNhapTTSach.add(btHoanTac);

    }

    public void setMenu() {
        // Set menu side left
        ImageIcon iconHome = new ImageIcon("images\\home.png");
        ImageIcon iconMenu = new ImageIcon("images\\menu.png");
        ImageIcon iconSearch = new ImageIcon("images\\search.png");
        ImageIcon iconRent = new ImageIcon("images\\payment.png");
        ImageIcon iconTK = new ImageIcon("images\\trend.png");
        ImageIcon iconNS = new ImageIcon("images\\cart.png");
        ImageIcon iconLogout = new ImageIcon("images\\logout.png");
        ImageIcon iconExited = new ImageIcon("images\\exit.png");

        lbHome = new JLabel();
        lbHome.setHorizontalAlignment(SwingConstants.CENTER);
        lbHome.setIcon(iconHome);

        btMenu = new JButton("Menu");
        btMenu.setFont(new Font("Arial", Font.BOLD, 20));
        btMenu.setBackground(Color.LIGHT_GRAY);
        btMenu.setIcon(iconMenu);
        btMenu.setHorizontalAlignment(SwingConstants.LEFT);
        btMenu.setBorder(BorderFactory.createEmptyBorder());
        btMenu.addActionListener(this);

        btMenuTimKiem = new JButton("Tìm kiếm sách");
        btMenuTimKiem.setFont(new Font("Arial", Font.BOLD, 20));
        btMenuTimKiem.setBackground(Color.LIGHT_GRAY);
        btMenuTimKiem.setIcon(iconSearch);
        btMenuTimKiem.setHorizontalAlignment(SwingConstants.LEFT);
        btMenuTimKiem.setBorder(BorderFactory.createEmptyBorder());
        btMenuTimKiem.addActionListener(this);

        btMT = new JButton("Mượn trả sách");
        btMT.setFont(new Font("Arial", Font.BOLD, 20));
        btMT.setBackground(Color.LIGHT_GRAY);
        btMT.setIcon(iconRent);
        btMT.setHorizontalAlignment(SwingConstants.LEFT);
        btMT.setBorder(BorderFactory.createEmptyBorder());
        btMT.addActionListener(this);

        btNhapSach = new JButton("Nhập sách");
        btNhapSach.setFont(new Font("Arial", Font.BOLD, 20));
        btNhapSach.setBackground(Color.LIGHT_GRAY);
        btNhapSach.setIcon(iconNS);
        btNhapSach.setHorizontalAlignment(SwingConstants.LEFT);
        btNhapSach.setBorder(BorderFactory.createEmptyBorder());
        btNhapSach.addActionListener(this);

        btQLNV = new JButton("Quản lý nghiệp vụ");
        btQLNV.setFont(new Font("Arial", Font.BOLD, 20));
        btQLNV.setBackground(Color.LIGHT_GRAY);
        btQLNV.setIcon(iconRent);
        btQLNV.setHorizontalAlignment(SwingConstants.LEFT);
        btQLNV.setBorder(BorderFactory.createEmptyBorder());
        btQLNV.addActionListener(this);

        btThongKe = new JButton("Thống kê");
        btThongKe.setFont(new Font("Arial", Font.BOLD, 20));
        btThongKe.setBackground(Color.LIGHT_GRAY);
        btThongKe.setIcon(iconTK);
        btThongKe.setHorizontalAlignment(SwingConstants.LEFT);
        btThongKe.setBorder(BorderFactory.createEmptyBorder());
        btThongKe.addActionListener(this);

        // JButton Đăng xuất
        btDangXuat = new JButton("Đăng xuất");
        btDangXuat.setFont(new Font("Arial", Font.BOLD, 20));
        btDangXuat.setBackground(Color.LIGHT_GRAY);
        btDangXuat.setIcon(iconLogout);
        btDangXuat.setHorizontalAlignment(SwingConstants.LEFT);
        btDangXuat.setBorder(BorderFactory.createEmptyBorder());
        btDangXuat.addActionListener(this);
        // JButton thoát
        btThoat = new JButton("Thoát");
        btThoat.setFont(new Font("Arial", Font.BOLD, 20));
        btThoat.setBackground(Color.LIGHT_GRAY);
        btThoat.setIcon(iconExited);
        btThoat.setHorizontalAlignment(SwingConstants.LEFT);
        btThoat.setBorder(BorderFactory.createEmptyBorder());
        btThoat.addActionListener(this);

        // add Menu button
        pnMenu.add(lbHome);
        pnMenu.add(btMenu);
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
        lbTTSach = new JLabel("THÔNG TIN SÁCH");
        lbTTSach.setFont(new Font("Arial", Font.BOLD, 30));
        lbTTSach.setHorizontalAlignment(SwingConstants.CENTER);
        lbTTSach.setVerticalAlignment(SwingConstants.TOP);
        // labelKQTK
        lbKQTK = new JLabel();
        lbKQTK.setHorizontalAlignment(SwingConstants.CENTER);
        lbKQTK.setVerticalAlignment(SwingConstants.TOP);
        // ----set up table----
        tblQLSACH = new JTable();
        JScrollPane pane = new JScrollPane(tblQLSACH);
        pane.setAutoscrolls(true);
        tblQLSACH.setRowHeight(20);
        tblQLSACH.setFont(new Font(null, 0, 13));
        tblQLSACH.setBackground(Color.LIGHT_GRAY);
        tblQLSACH.addMouseListener(this);
        tblQLSACH.setDefaultEditor(Object.class, null);

        this.add(pnTTSach);
        pnTTSach.add(lbTTSach);
        pnTTSach.add(lbKQTK);
        pnTTSach.add(pane);
    }

    public void setShowAll() {
        // JButtonShowAll
        btShowAll = new JButton("Hiển thị tất cả");
        btShowAll.setFont(new Font("Arial", Font.BOLD, 15));
        btShowAll.setBounds(1070, 10, 150, 30);
        btShowAll.setBackground(Color.cyan);
        btShowAll.setBorder(new RoundedBorder(10));
        btShowAll.addActionListener(this);
        pnShowAll.add(btShowAll);
    }

    public void ShowOnTable(SACH sach) {
        Vector<String> row = new Vector<String>();
        row.add(sach.getMasach());
        row.add(sach.getTensach());
        row.add(sach.getMaNXB());
        row.add(sach.getMaTG());
        row.add(sach.getNamXB());
        row.add(String.valueOf(sach.getSLtong()));
        row.add(String.valueOf(sach.getSL()));
        row.add(String.valueOf(sach.getDongia()));
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
        sach.setDongia(Integer.parseInt(txDongia.getText()));
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
            header.add("Mã sách");
            header.add("Tên sách");
            header.add("Mã NXB");
            header.add("Mã tác giả");
            header.add("Năm xuất bản");
            header.add("SL tổng");
            header.add("SL");
            header.add("Đơn giá");
            model = new DefaultTableModel(header, 0);
            for (SACH sach : QLSACHBUS.dssach) {
                ShowOnTable(sach);
                tblQLSACH.setModel(model);
            }
        } catch (Exception e1) {
            System.out.println(e1);
        }
    }

    public void OffBgSelected() {
        btMenu.setBackground(Color.lightGray);
        btMenuTimKiem.setBackground(Color.lightGray);
        btMT.setBackground(Color.lightGray);
        btQLNV.setBackground(Color.lightGray);

    }

    public void setTimKiem() {
        if (btSearch == null) { // Là button của phần tìm kiếm cơ bản
            lbLCTK.setVisible(true);
            lbTuKhoaTK.setVisible(true);
            comboBoxDSKhoaTK.setVisible(true);
            txKhoaTK.setVisible(true);

            TitledBorder titleTK;
            titleTK = BorderFactory.createTitledBorder("Tìm kiếm");
            titleTK.setTitleFont(new Font("Arial", Font.BOLD, 28));
            titleTK.setTitleJustification(TitledBorder.CENTER);
            pnTimKiem.setBorder(titleTK);

            btSearch = new JButton("Tìm kiếm");
            btSearch.setFont(new Font("Arial", Font.BOLD, 15));
            btSearch.setBounds(360, 175, 100, 35);
            btSearch.setBackground(Color.cyan);
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
        // Ẩn các component không dùng tới
        btSearch.setVisible(false);
        txKhoaTK.setVisible(false);
        // set up component mới
        // Năm xb
        if (lbTKNam == null) {
            lbTKNam = new JLabel("Năm XB:");
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
            btTK = new JButton("Tìm kiếm");
            btTK.setFont(new Font("Arial", Font.BOLD, 15));
            btTK.setBounds(380, 180, 100, 34);
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
}

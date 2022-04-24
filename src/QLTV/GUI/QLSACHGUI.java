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
import java.util.Vector;
import javax.swing.ButtonGroup;
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
import javax.swing.table.DefaultTableModel;

import MyCustom.RoundedBorder;
import QLTV.BUS.QLSACHBUS;
import QLTV.DTO.SACH;

public class QLSACHGUI extends JFrame implements ActionListener, MouseListener {
    JPanel pnTTSach, pnNhapTTSach, pnShowAll, pnMenu;
    JLabel lbMenu, lbTTSach, lbMasach, lbTensach, lbMaNXB, lbMaTG, lbNamXB, lbSLtong, lbSL, lbDongia, lbTimKiem, lbLCTK,
            lbTuKhoaTK, lbKQTK;
    JLabel lbTKNam, lbTKSL;
    JTextField txMasach, txTensach, txMaNXB, txMaTG, txNamXB, txSLtong, txSL, txDongia, txKhoaTK;
    JTextField txTKNam, txTKSL;
    JButton btDoc, btThem, btSua, btXoa, btTimKiem, btShowAll, btThongKe;
    JButton btQLSACH, btQLNXB, btQLTHELOAI, btQLTACGIA, btQLNHACC,
            btQLDOCGIA, btHDTIENPHAT, btQLNHANVIEN, btPHIEUTDMT, btPM, btPN, btPT,
            btCTPT, btCTPM, btCTPN, btDangXuat, btThoat;
    JButton btTK;

    JRadioButton rbNam, rbNu, rbKhac;
    ButtonGroup buttonGroupGT;
    JComboBox<String> comboBoxDSKhoaTK;

    JTable tblQLSACH;
    DefaultTableModel model;
    Vector<String> header;

    public QLSACHGUI() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        this.setTitle("Quản lý thông tin sách");
        this.setLayout(null);

        pnTTSach = new JPanel();
        pnNhapTTSach = new JPanel();
        pnShowAll = new JPanel();
        pnMenu = new JPanel();
        pnTTSach.setLayout(new GridLayout(3, 1, 0, -300));
        pnTTSach.setBounds(320, 0, 1200, 400);
        pnShowAll.setLayout(null);
        pnShowAll.setBounds(300, 400, 1537, 40);
        pnNhapTTSach.setLayout(null);
        pnNhapTTSach.setBounds(200, 415, 1537, 600);
        pnMenu.setBackground(Color.LIGHT_GRAY);
        pnMenu.setLayout(new GridLayout(20, 1));
        pnMenu.setPreferredSize(new Dimension(50, 1200));

        JScrollPane pane = new JScrollPane(pnMenu);
        pane.setSize(310, 800);
        // add components
        this.add(pane);
        this.add(pnShowAll);
        this.add(pnNhapTTSach);

        setTableSach();
        setInput();
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
                    header.add("Mã sinh viên");
                    header.add("Họ");
                    header.add("Tên");
                    header.add("Giới tính");
                    header.add("Năm sinh");
                    header.add("Khoa");
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
                    try {
                        qlsachbus.sua(sach, masachCu, i);
                    } catch (Exception e1) {
                        e1.printStackTrace();
                    }
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
            String masach = txMasach.getText();
            int i = tblQLSACH.getSelectedRow();
            if (i >= 0) {
                try {
                    // Truy cập xuống BUS
                    QLSACHBUS qlsachbus = new QLSACHBUS();
                    qlsachbus.xoa(masach, i);
                    // Quay dề GUI
                    model.removeRow(i);
                    tblQLSACH.setModel(model);
                } catch (Exception e1) {
                    System.out.println(e1);
                }
            }
        } else if (e.getSource() == comboBoxDSKhoaTK) {
            int vtkey = Integer.parseInt(String.valueOf(comboBoxDSKhoaTK.getSelectedIndex()));
            if (vtkey == 9) {
                // Ẩn các component không dùng tới
                btTimKiem.setVisible(false);
                txKhoaTK.setVisible(false);
                btThongKe.setVisible(false);
                // set up component mới
                // Năm xb
                lbTKNam = new JLabel("Năm XB:");
                lbTKNam.setFont(new Font("Arial", Font.BOLD, 20));
                lbTKNam.setBounds(670, 300, 100, 50);
                txTKNam = new JTextField();
                txTKNam.setFont(new Font("Arial", Font.PLAIN, 15));
                txTKNam.setBounds(760, 310, 100, 30);
                // SL
                lbTKSL = new JLabel("SL:");
                lbTKSL.setFont(new Font("Arial", Font.BOLD, 20));
                lbTKSL.setBounds(880, 300, 100, 50);
                txTKSL = new JTextField();
                txTKSL.setFont(new Font("Arial", Font.PLAIN, 15));
                txTKSL.setBounds(920, 310, 100, 30);
                // button btTimKiem
                btTK = new JButton("Tìm kiếm");
                btTK.setFont(new Font("Arial", Font.BOLD, 15));
                btTK.setBounds(1040, 308, 100, 34);
                btTK.setBackground(Color.cyan);
                btTK.setBorder(new RoundedBorder(10));
                btTK.addActionListener(this);
                pnNhapTTSach.add(lbTKNam);
                pnNhapTTSach.add(txTKNam);
                pnNhapTTSach.add(lbTKSL);
                pnNhapTTSach.add(txTKSL);
                pnNhapTTSach.add(btTK);
            } else {
                // Ẩn lại các component của combobox key 9 10
                lbTKNam.setVisible(false);
                txTKNam.setVisible(false);
                lbTKSL.setVisible(false);
                txTKSL.setVisible(false);
                btTK.setVisible(false);
                // Hiển thị các component của các key khác
                btTimKiem.setVisible(true);
                txKhoaTK.setVisible(true);
                btThongKe.setVisible(true);
            }
        } else if (e.getSource() == btTK) {
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

        } else if (e.getSource() == btTimKiem) {
            int vtkey = Integer.parseInt(String.valueOf(comboBoxDSKhoaTK.getSelectedIndex()));
            String tukhoa = txKhoaTK.getText();
            lbKQTK.setFont(new Font("Arial", Font.BOLD, 20));
            lbKQTK.setForeground(Color.red);
            if (tukhoa.equals("") == true) {
                JOptionPane.showMessageDialog(null, "Xin mời nhập từ khóa", "Lỗi", JOptionPane.ERROR_MESSAGE);
            } else if (vtkey == 0) {
                JOptionPane.showMessageDialog(null, "Xin mời lựa chọn khóa tìm kiếm", "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
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
        } else if (e.getSource() == txTensach) {
            txTensach.setToolTipText("Gợi ý: Cha giàu, cha nghèo");
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
        // labelTimKiem
        lbTimKiem = new JLabel("TÌM KIẾM");
        lbTimKiem.setFont(new Font("Arial", Font.BOLD, 25));
        lbTimKiem.setBounds(870, 130, 200, 100);
        // labelLCTK
        lbLCTK = new JLabel("Lựa chọn khóa tìm kiếm:");
        lbLCTK.setFont(new Font("Arial", Font.BOLD, 20));
        lbLCTK.setBounds(670, 180, 250, 100);
        // labelTuKhoaTK
        lbTuKhoaTK = new JLabel("Nhập từ khóa tìm kiếm:");
        lbTuKhoaTK.setFont(new Font("Arial", Font.BOLD, 20));
        lbTuKhoaTK.setBounds(670, 230, 250, 100);

        // JTextField Mã sách
        txMasach = new JTextField();
        txMasach.setBounds(270, 30, 200, 35);
        txMasach.setFont(new Font("Arial", Font.PLAIN, 15));
        txMasach.addMouseListener(this);
        // JTextField Tên sách
        txTensach = new JTextField();
        txTensach.setBounds(270, 80, 200, 35);
        txTensach.setFont(new Font("Arial", Font.PLAIN, 15));
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
        txKhoaTK.setBounds(920, 262, 200, 35);
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
        // JbuttonTimKiem
        btTimKiem = new JButton("Tìm kiếm");
        btTimKiem.setFont(new Font("Arial", Font.BOLD, 15));
        btTimKiem.setBounds(1130, 260, 110, 34);
        btTimKiem.setBackground(Color.cyan);
        btTimKiem.setBorder(new RoundedBorder(10));
        btTimKiem.addActionListener(this);

        // set up ComboBox
        String[] dsKhoaTK = { "", "Mã sách", "Tên sách", "Mã NXB", "Mã TG", "Năm XB", "SL tổng", "SL", "Đơn giá",
                "Năm hoặc SL", "Năm và SL" };
        comboBoxDSKhoaTK = new JComboBox<>(dsKhoaTK);
        comboBoxDSKhoaTK.setFont(new Font("Arial", Font.BOLD, 13));
        comboBoxDSKhoaTK.setBounds(920, 210, 120, 35);
        comboBoxDSKhoaTK.addActionListener(this);

        // JButtonThongKe
        btThongKe = new JButton("THỐNG KÊ");
        btThongKe.setFont(new Font("Arial", Font.BOLD, 20));
        btThongKe.setBounds(670, 320, 150, 55);
        btThongKe.setBackground(Color.cyan);
        btThongKe.setBorder(new RoundedBorder(10));
        btThongKe.addActionListener(this);

        btDoc.setVisible(false);

        pnNhapTTSach.add(lbMasach);
        pnNhapTTSach.add(lbTensach);
        pnNhapTTSach.add(lbMaNXB);
        pnNhapTTSach.add(lbMaTG);
        pnNhapTTSach.add(lbNamXB);
        pnNhapTTSach.add(lbSLtong);
        pnNhapTTSach.add(lbSL);
        pnNhapTTSach.add(lbDongia);
        pnNhapTTSach.add(lbTimKiem);
        pnNhapTTSach.add(lbLCTK);
        pnNhapTTSach.add(lbTuKhoaTK);

        pnNhapTTSach.add(txMasach);
        pnNhapTTSach.add(txTensach);
        pnNhapTTSach.add(txMaNXB);
        pnNhapTTSach.add(txMaTG);
        pnNhapTTSach.add(txNamXB);
        pnNhapTTSach.add(txSLtong);
        pnNhapTTSach.add(txSL);
        pnNhapTTSach.add(txDongia);
        pnNhapTTSach.add(txKhoaTK);

        pnNhapTTSach.add(comboBoxDSKhoaTK);

        pnNhapTTSach.add(btDoc);
        pnNhapTTSach.add(btThem);
        pnNhapTTSach.add(btSua);
        pnNhapTTSach.add(btXoa);
        pnNhapTTSach.add(btTimKiem);
        pnNhapTTSach.add(btThongKe);

    }

    public void setMenu() {
        // label Menu
        lbMenu = new JLabel("MENU CHỨC NĂNG");
        lbMenu.setForeground(Color.red);
        lbMenu.setFont(new Font("Arial", Font.BOLD, 25));
        lbMenu.setPreferredSize(new Dimension(100, 100));
        lbMenu.setHorizontalAlignment(SwingConstants.CENTER);
        lbMenu.setVerticalAlignment(SwingConstants.TOP);

        // JButton quản lý sách
        btQLSACH = new JButton("QUẢN LÝ SÁCH");
        btQLSACH.setFont(new Font("Arial", Font.BOLD, 15));
        btQLSACH.setBackground(Color.green);
        // btQLSACH.setBorder(BorderFactory.createEmptyBorder());
        btQLSACH.addActionListener(this);

        // JButton quản lý nxb
        btQLNXB = new JButton("QUẢN LÝ NXB");
        btQLNXB.setFont(new Font("Arial", Font.BOLD, 15));
        btQLNXB.setBackground(Color.LIGHT_GRAY);
        btQLNXB.addActionListener(this);

        // JButton quản lý thể loại
        btQLTHELOAI = new JButton("QUẢN LÝ THỂ LOẠI");
        btQLTHELOAI.setFont(new Font("Arial", Font.BOLD, 15));
        btQLTHELOAI.setBackground(Color.LIGHT_GRAY);
        btQLTHELOAI.addActionListener(this);

        // JButton quản lý độc giả
        btQLDOCGIA = new JButton("QUẢN LÝ ĐỘC GIẢ");
        btQLDOCGIA.setFont(new Font("Arial", Font.BOLD, 15));
        btQLDOCGIA.setBackground(Color.LIGHT_GRAY);
        btQLDOCGIA.addActionListener(this);

        // JButton quản lý phiếu theo dõi mượn trả
        btPHIEUTDMT = new JButton("QUẢN LÝ PHIẾU THEO DÕI MT");
        btPHIEUTDMT.setFont(new Font("Arial", Font.BOLD, 15));
        btPHIEUTDMT.setBackground(Color.LIGHT_GRAY);
        btPHIEUTDMT.addActionListener(this);

        // JButton quản lý nhà cung cấp
        btQLNHACC = new JButton("QUẢN LÝ NHÀ CC");
        btQLNHACC.setFont(new Font("Arial", Font.BOLD, 15));
        btQLNHACC.setBackground(Color.LIGHT_GRAY);
        btQLNHACC.addActionListener(this);

        // JButton quản lý nhân viên
        btQLNHANVIEN = new JButton("QUẢN LÝ NHÂN VIÊN");
        btQLNHANVIEN.setFont(new Font("Arial", Font.BOLD, 15));
        btQLNHANVIEN.setBackground(Color.LIGHT_GRAY);
        btQLNHANVIEN.addActionListener(this);

        // JButton quản lý tác giả
        btQLTACGIA = new JButton("QUẢN LÝ TÁC GIẢ");
        btQLTACGIA.setFont(new Font("Arial", Font.BOLD, 15));
        btQLTACGIA.setBackground(Color.LIGHT_GRAY);
        btQLTACGIA.addActionListener(this);

        // JButton quản lý phiếu mượn
        btPM = new JButton("QUẢN LÝ PHIẾU MƯỢN");
        btPM.setFont(new Font("Arial", Font.BOLD, 15));
        btPM.setBackground(Color.LIGHT_GRAY);
        btPM.addActionListener(this);

        // JButton quản lý phiếu nhập
        btPN = new JButton("QUẢN LÝ PHIẾU NHẬP");
        btPN.setFont(new Font("Arial", Font.BOLD, 15));
        btPN.setBackground(Color.LIGHT_GRAY);
        btPN.addActionListener(this);

        // JButton quản lý phiếu trả
        btPT = new JButton("QUẢN LÝ PHIẾU TRẢ");
        btPT.setFont(new Font("Arial", Font.BOLD, 15));
        btPT.setBackground(Color.LIGHT_GRAY);
        btPT.addActionListener(this);

        // JButton quản lý chi tiết phiếu mươn
        btCTPM = new JButton("QUẢN LÝ CT PHIẾU MƯỢN");
        btCTPM.setFont(new Font("Arial", Font.BOLD, 15));
        btCTPM.setBackground(Color.LIGHT_GRAY);
        btCTPM.addActionListener(this);

        // JButton quản lý chi tiết phiếu nhập
        btCTPN = new JButton("QUẢN LÝ CT PHIẾU NHẬP");
        btCTPN.setFont(new Font("Arial", Font.BOLD, 15));
        btCTPN.setBackground(Color.LIGHT_GRAY);
        btCTPN.addActionListener(this);

        // JButton quản lý phiếu trả
        btCTPT = new JButton("QUẢN LÝ CT PHIẾU TRẢ");
        btCTPT.setFont(new Font("Arial", Font.BOLD, 15));
        btCTPT.setBackground(Color.LIGHT_GRAY);
        btCTPT.addActionListener(this);

        // JButton quản lý hóa đơn tiền phạt
        btHDTIENPHAT = new JButton("QUẢN LÝ HĐ TIỀN PHẠT");
        btHDTIENPHAT.setFont(new Font("Arial", Font.BOLD, 15));
        btHDTIENPHAT.setBackground(Color.LIGHT_GRAY);
        btHDTIENPHAT.addActionListener(this);

        // JButton Đăng xuất
        btDangXuat = new JButton("ĐĂNG XUẤT");
        btDangXuat.setFont(new Font("Arial", Font.BOLD, 15));
        btDangXuat.setBackground(Color.LIGHT_GRAY);
        btDangXuat.addActionListener(this);
        // JButton thoát
        btThoat = new JButton("THOÁT");
        btThoat.setFont(new Font("Arial", Font.BOLD, 15));
        btThoat.setBackground(Color.LIGHT_GRAY);
        btThoat.addActionListener(this);

        // add Menu button
        pnMenu.add(lbMenu);
        pnMenu.add(btQLSACH);
        pnMenu.add(btQLNXB);
        pnMenu.add(btQLTHELOAI);
        pnMenu.add(btQLDOCGIA);
        pnMenu.add(btPHIEUTDMT);
        pnMenu.add(btQLNHACC);
        pnMenu.add(btQLNHANVIEN);
        pnMenu.add(btQLTACGIA);
        pnMenu.add(btPM);
        pnMenu.add(btPN);
        pnMenu.add(btPT);
        pnMenu.add(btCTPM);
        pnMenu.add(btPN);
        pnMenu.add(btCTPT);
        pnMenu.add(btHDTIENPHAT);
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
}

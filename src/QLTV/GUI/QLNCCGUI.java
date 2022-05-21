package QLTV.GUI;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Vector;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
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
import javax.swing.table.DefaultTableModel;

import MyCustom.LoginPage;
import MyCustom.Menu;
import MyCustom.RoundedBorder;
import MyCustom.MyColor;
import QLTV.BUS.QLNCCBUS;
import QLTV.DTO.NHACUNGCAP;

public class QLNCCGUI extends JFrame implements ActionListener, MouseListener {
    JPanel pnTTNCC, pnNhapTTNCC, pnShowAll, pnMenu, pnTimKiem;
    JLabel lbHome, lbTTNCC, lbMaNCC, lbTenNCC, lbLCTK, lbTuKhoaTK;
    JLabel lbTKMaNCC;
    JTextField txMaNCC, txTenNCC, txKhoaTK;
    JButton btThem, btnxb, btSua, btXoa, btHoanTac, btMenuTimKiem, btShowAll, btThongKe;
    JButton btMenu, btSapXep, btDangXuat, btThoat;
    JButton btSearch;

    JComboBox<String> comboBoxDSKhoaTK;
    JScrollPane pane;

    JTable tblQLNCC;
    DefaultTableModel model;
    Vector<String> header;

    public QLNCCGUI() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(1400, 800);
        this.setTitle("Quản lý thông tin nhà xuất bản");
        this.setLayout(null);
        this.setLocationRelativeTo(null);

        pnTTNCC = new JPanel();
        pnNhapTTNCC = new JPanel();
        pnShowAll = new JPanel();
        pnMenu = new JPanel();
        pnTimKiem = new JPanel();
        pnTTNCC.setLayout(new GridLayout(2, 1, 0, -300));
        pnTTNCC.setBounds(242, 0, 1142, 400);
        pnTTNCC.setBackground(MyColor.ColorBlue);

        pnShowAll.setLayout(null);
        pnShowAll.setBounds(242, 402, 1142, 30);
        pnShowAll.setBackground(MyColor.ColorBlue);

        pnNhapTTNCC.setLayout(null);
        pnNhapTTNCC.setBounds(242, 415, 720, 550);
        pnNhapTTNCC.setBackground(MyColor.ColorBlue);

        pnMenu.setLayout(new GridLayout(9, 1));
        pnMenu.setBounds(0, 178, 240, 590);
        pnMenu.setBackground(MyColor.ColorOcean);

        pnTimKiem.setLayout(null);
        pnTimKiem.setBounds(962, 430, 423, 332);
        pnTimKiem.setBackground(MyColor.ColorBlue);

        // add components
        this.add(pnMenu);
        this.add(pnShowAll);
        this.add(pnNhapTTNCC);
        this.add(pnTimKiem);

        setTableNXB();
        setInput();
        setMenu();
        setShowAll();
        getDatabase();

        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btThem) {
            try {
                NHACUNGCAP ncc = new NHACUNGCAP();
                getInfoTextField(ncc);
                // Truy cập vào bus
                QLNCCBUS qlnccbus = new QLNCCBUS();
                int kiemtra = 0;
                try {
                    kiemtra = qlnccbus.them(ncc);
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
                if (kiemtra == 1) {
                    // Đưa dữ liệu lên table
                    header = new Vector<String>();
                    header.add("Mã Nhà Cung Cấp");
                    header.add("Tên Nhà Cung Cấp");
                    if (model.getRowCount() == 0) {
                        model = new DefaultTableModel(header, 0);
                    }
                    ShowOnTable(ncc);
                    tblQLNCC.setModel(model);
                }
            } catch (Exception e1) {
                System.out.println(e1);
            }
        } else if (e.getSource() == btSua) {
            int i = tblQLNCC.getSelectedRow();
            int kt = -1;
            if (i >= 0) {
                NHACUNGCAP ncc = new NHACUNGCAP();
                NHACUNGCAP mancccu = QLNCCBUS.dsncc.get(i);
                getInfoTextField(ncc);
                try {
                    QLNCCBUS qlnccbus = new QLNCCBUS();
                    kt = qlnccbus.sua(ncc, mancccu, i);

                } catch (Exception e1) {
                    System.out.println(e1);
                }
                if (kt == 0) {
                    model.setValueAt(ncc.getMaNCC(), i, 0);
                    model.setValueAt(ncc.getTenNCC(), i, 1);
                    tblQLNCC.setModel(model);
                }
            }
        } else if (e.getSource() == btXoa) {
            int XacNhanXoa = JOptionPane.showConfirmDialog(null, "Bạn có chắc chắn muốn xóa không ?", "Thông báo",
                    JOptionPane.YES_NO_OPTION);
            if (XacNhanXoa == 0) {
                int kt = -1;
                String manxb = txMaNCC.getText();
                int i = tblQLNCC.getSelectedRow();
                if (i >= 0) {
                    try {
                        // Truy cập xuống BUS
                        NHACUNGCAP SachOld = QLNCCBUS.dsncc.get(i);
                        QLNCCBUS.htXoa.add(SachOld);
                        QLNCCBUS qlnccbus = new QLNCCBUS();
                        kt = qlnccbus.xoa(manxb, i);
                        // Quay dề GUI

                    } catch (Exception e1) {
                        System.out.println(e1);
                    }
                    if (kt == 0) {
                        model.removeRow(i);
                        tblQLNCC.setModel(model);
                    }
                }
            }
        } else if (e.getSource() == btHoanTac) {
            int ktHT = 0;
            if (QLNCCBUS.htXoa.size() == 0) {
                JOptionPane.showMessageDialog(null, "Dữ liệu hoàn tác rỗng", "Lỗi",
                        JOptionPane.ERROR_MESSAGE);
            } else {
                for (NHACUNGCAP nxb : QLNCCBUS.htXoa) {
                    QLNCCBUS qlnccbus = new QLNCCBUS();
                    int kiemtra = 0;
                    try {
                        kiemtra = qlnccbus.hoantacXoa(nxb);
                    } catch (Exception e1) {
                        e1.printStackTrace();
                    }
                    if (kiemtra == 1) {
                        // Đưa dữ liệu lên table
                        header = new Vector<String>();
                        header.add("Mã Nhà Cung Cấp");
                        header.add("Tên Nhà Cung Cấp");
                        if (model.getRowCount() == 0) {
                            model = new DefaultTableModel(header, 0);
                        }
                        ShowOnTable(nxb);
                        ktHT = 1;
                    } else if (kiemtra == 0) {
                        JOptionPane.showMessageDialog(null, "Hoàn tác dữ liệu thất bại", "Lỗi",
                                JOptionPane.ERROR_MESSAGE);
                        ktHT = 0;
                    }
                }
            }
            if (ktHT == 1) {
                JOptionPane.showMessageDialog(null, "Hoàn tác dữ liệu thành công", "Thông báo",
                        JOptionPane.INFORMATION_MESSAGE);
                tblQLNCC.setModel(model);
            }
        } else if (e.getSource() == btMenuTimKiem) { // Của button Tìm kiếm nhà xuất bản, để hiện thị
            // khung tìm kiếm
            OffBTBgSelected();
            btMenuTimKiem.setBackground(MyColor.ColorLightBlue);
            setTimKiem();
        } else if (e.getSource() == btSearch) {
            int vtkey = Integer.parseInt(String.valueOf(comboBoxDSKhoaTK.getSelectedIndex()));
            String tukhoa = txKhoaTK.getText();
            if (tukhoa.equals("") == true) {
                JOptionPane.showMessageDialog(null, "Xin mời nhập từ khóa", "Lỗi", JOptionPane.ERROR_MESSAGE);
            } else if (vtkey == 0) {
                JOptionPane.showMessageDialog(null, "Xin mời lựa chọn khóa tìm kiếm", "Lỗi", JOptionPane.ERROR_MESSAGE);
            } else {
                QLNCCBUS qlnccbus = new QLNCCBUS();
                if (vtkey == 1) {
                    NHACUNGCAP kq = qlnccbus.timTheoMa(tukhoa);
                    model.setRowCount(0);
                    if (kq == null) {
                        JOptionPane.showMessageDialog(null, "Không tìm thấy dữ liệu", "Lỗi", JOptionPane.ERROR_MESSAGE);
                    } else {
                        ShowOnTable(kq);
                        tblQLNCC.setModel(model);
                    }
                } else if (vtkey == 2) {
                    ArrayList<NHACUNGCAP> kq = qlnccbus.timTheoTen(tukhoa);
                    model.setRowCount(0);
                    if (kq.size() == 0) {
                        JOptionPane.showMessageDialog(null, "Không tìm thấy dữ liệu", "Lỗi", JOptionPane.ERROR_MESSAGE);
                    } else {
                        for (NHACUNGCAP ncc : kq) {
                            ShowOnTable(ncc);
                        }
                        tblQLNCC.setModel(model);
                    }
                }
            }
        } else if (e.getSource() == btShowAll) {
            model.setRowCount(0);
            for (NHACUNGCAP ncc : QLNCCBUS.dsncc) {
                ShowOnTable(ncc);
            }
            tblQLNCC.setModel(model);
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
        if (e.getSource() == btnxb) {
            OffPageQLSACH(true);
            OffBTBgSelected();
            btnxb.setBackground(MyColor.ColorLightBlue);
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
        QLNCCBUS.dsncc.sort(((o1, o2) -> o1.getTenNCC().compareTo(o2.getTenNCC())));
        model.setRowCount(0);
        for (NHACUNGCAP ncc : QLNCCBUS.dsncc) {
            ShowOnTable(ncc);
        }
        tblQLNCC.setModel(model);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource() == tblQLNCC) {
            int i = tblQLNCC.getSelectedRow();
            if (i >= 0) {
                NHACUNGCAP ncc = new NHACUNGCAP();
                ncc = QLNCCBUS.dsncc.get(i);
                txMaNCC.setText(ncc.getMaNCC().trim());
                txTenNCC.setText(ncc.getTenNCC().replaceAll("\\s+", " ").trim());
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
        if (e.getSource() == txMaNCC) {
            txMaNCC.setToolTipText("Gợi ý: NXB001");
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    public void setInput() {
        // label Matacgia
        lbMaNCC = new JLabel("Mã NCC:");
        lbMaNCC.setFont(new Font("Arial", Font.BOLD, 20));
        lbMaNCC.setBounds(20, 0, 130, 80);
        // label Tentacgia
        lbTenNCC = new JLabel("Tên NCC:");
        lbTenNCC.setFont(new Font("Arial", Font.BOLD, 20));
        lbTenNCC.setBounds(20, 40, 130, 80);

        // labelLCTK
        lbLCTK = new JLabel("Lựa chọn khóa tìm kiếm:");
        lbLCTK.setFont(new Font("Arial", Font.BOLD, 20));
        lbLCTK.setBounds(10, 50, 260, 80);

        // labelTuKhoaTK
        lbTuKhoaTK = new JLabel("Nhập từ khóa tìm kiếm:");
        lbTuKhoaTK.setFont(new Font("Arial", Font.BOLD, 20));
        lbTuKhoaTK.setBounds(10, 100, 260, 80);

        // JTextField Mã Nhà Xuất Bản
        txMaNCC = new JTextField();
        txMaNCC.setBounds(160, 25, 180, 30);
        txMaNCC.setFont(new Font("Arial", Font.PLAIN, 15));
        txMaNCC.addMouseListener(this);
        // JTextField Tên Nhà Xuất Bản
        txTenNCC = new JTextField();
        txTenNCC.setBounds(160, 65, 180, 30);
        txTenNCC.setFont(new Font("Arial", Font.PLAIN, 15));
        txTenNCC.addMouseListener(this);
        // JTextField Khóa tìm kiếm
        txKhoaTK = new JTextField();
        txKhoaTK.setFont(new Font("Arial", Font.PLAIN, 15));
        txKhoaTK.setBounds(250, 125, 160, 30);
        txKhoaTK.addMouseListener(this);

        // JbuttonThem
        btThem = new JButton("Thêm");
        btThem.setFont(new Font("Arial", Font.BOLD, 15));
        btThem.setBounds(20, 120, 80, 30);
        btThem.setBackground(MyColor.ColorButton);
        btThem.setBorder(new RoundedBorder(10));
        btThem.addActionListener(this);
        // JbuttonSua
        btSua = new JButton("Sửa");
        btSua.setFont(new Font("Arial", Font.BOLD, 15));
        btSua.setBounds(120, 120, 80, 30);
        btSua.setBackground(MyColor.ColorButton);
        btSua.setBorder(new RoundedBorder(10));
        btSua.addActionListener(this);
        // JbuttonXoa
        btXoa = new JButton("Xóa");
        btXoa.setFont(new Font("Arial", Font.BOLD, 15));
        btXoa.setBounds(220, 120, 80, 30);
        btXoa.setBackground(MyColor.ColorButton);
        btXoa.setBorder(new RoundedBorder(10));
        btXoa.addActionListener(this);
        // JbuttonHoanTac
        btHoanTac = new JButton("Hoàn tác");
        btHoanTac.setFont(new Font("Arial", Font.BOLD, 15));
        btHoanTac.setBounds(320, 120, 90, 30);
        btHoanTac.setBackground(MyColor.ColorButton);
        btHoanTac.setBorder(new RoundedBorder(10));
        btHoanTac.addActionListener(this);

        // set up ComboBox
        String[] dsKhoaTK = { "", "Mã NCC", "Tên NCC" };
        comboBoxDSKhoaTK = new JComboBox<>(dsKhoaTK);
        comboBoxDSKhoaTK.setFont(new Font("Arial", Font.BOLD, 13));
        comboBoxDSKhoaTK.setBounds(250, 75, 100, 30);
        comboBoxDSKhoaTK.addActionListener(this);

        lbLCTK.setVisible(false);
        lbTuKhoaTK.setVisible(false);
        comboBoxDSKhoaTK.setVisible(false);
        txKhoaTK.setVisible(false);

        pnNhapTTNCC.add(lbMaNCC);
        pnNhapTTNCC.add(lbTenNCC);

        pnNhapTTNCC.add(txMaNCC);
        pnNhapTTNCC.add(txTenNCC);

        pnNhapTTNCC.add(btThem);
        pnNhapTTNCC.add(btSua);
        pnNhapTTNCC.add(btXoa);
        pnNhapTTNCC.add(btHoanTac);

    }

    public void setMenu() {
        // Set menu side left
        ImageIcon iconMenu = new ImageIcon("images\\menu.png");
        ImageIcon iconPubCompany = new ImageIcon("images\\company-building-icon.png");
        ImageIcon iconSearch = new ImageIcon("images\\search.png");
        ImageIcon iconLogout = new ImageIcon("images\\logout.png");
        ImageIcon iconExited = new ImageIcon("images\\exit.png");

        btMenu = new JButton("Menu");
        btMenu.setFont(new Font("Arial", Font.BOLD, 20));
        btMenu.setBackground(MyColor.ColorOcean);
        btMenu.setIcon(iconMenu);
        btMenu.setHorizontalAlignment(SwingConstants.LEFT);
        btMenu.setBorder(BorderFactory.createEmptyBorder());
        btMenu.addActionListener(this);

        btnxb = new JButton("Thông tin NCC");
        btnxb.setFont(new Font("Arial", Font.BOLD, 20));
        btnxb.setBackground(MyColor.ColorLightBlue);
        btnxb.setIcon(iconPubCompany);
        btnxb.setHorizontalAlignment(SwingConstants.LEFT);
        btnxb.setBorder(BorderFactory.createEmptyBorder());
        btnxb.addActionListener(this);

        btMenuTimKiem = new JButton("Tìm kiếm NCC");
        btMenuTimKiem.setFont(new Font("Arial", Font.BOLD, 20));
        btMenuTimKiem.setBackground(MyColor.ColorOcean);
        btMenuTimKiem.setIcon(iconSearch);
        btMenuTimKiem.setHorizontalAlignment(SwingConstants.LEFT);
        btMenuTimKiem.setBorder(BorderFactory.createEmptyBorder());
        btMenuTimKiem.addActionListener(this);

        // JButton Đăng xuất
        btDangXuat = new JButton("Đăng xuất");
        btDangXuat.setFont(new Font("Arial", Font.BOLD, 20));
        btDangXuat.setBackground(MyColor.ColorOcean);
        btDangXuat.setIcon(iconLogout);
        btDangXuat.setHorizontalAlignment(SwingConstants.LEFT);
        btDangXuat.setBorder(BorderFactory.createEmptyBorder());
        btDangXuat.addActionListener(this);
        // JButton thoát
        btThoat = new JButton("Thoát");
        btThoat.setFont(new Font("Arial", Font.BOLD, 20));
        btThoat.setBackground(MyColor.ColorOcean);
        btThoat.setIcon(iconExited);
        btThoat.setHorizontalAlignment(SwingConstants.LEFT);
        btThoat.setBorder(BorderFactory.createEmptyBorder());
        btThoat.addActionListener(this);

        // add Menu button
        pnMenu.add(btMenu);
        pnMenu.add(btnxb);
        pnMenu.add(btMenuTimKiem);
        pnMenu.add(btDangXuat);
        pnMenu.add(btThoat);
    }

    public void setTableNXB() {
        // label TTNXB
        lbTTNCC = new JLabel("THÔNG TIN NHÀ CUNG CẤP");
        lbTTNCC.setFont(new Font("Arial", Font.BOLD, 30));
        lbTTNCC.setHorizontalAlignment(SwingConstants.CENTER);
        lbTTNCC.setVerticalAlignment(SwingConstants.TOP);
        // ----set up table----
        tblQLNCC = new JTable();
        pane = new JScrollPane(tblQLNCC);
        pane.setAutoscrolls(true);
        tblQLNCC.setRowHeight(30);
        tblQLNCC.setFont(new Font(null, 0, 13));
        tblQLNCC.setBackground(MyColor.ColorLightGray);
        tblQLNCC.addMouseListener(this);
        tblQLNCC.setSelectionBackground(MyColor.Color);
        tblQLNCC.getTableHeader().setBackground(MyColor.ColorSilver);

        this.add(pnTTNCC);
        pnTTNCC.add(lbTTNCC);
        pnTTNCC.add(pane);
    }

    public void setShowAll() {
        // JButtonShowAll
        btShowAll = new JButton("Hiển thị tất cả");
        btShowAll.setFont(new Font("Arial", Font.BOLD, 15));
        btShowAll.setBounds(1010, 0, 130, 30);
        btShowAll.setBackground(MyColor.ColorButton);
        btShowAll.setBorder(new RoundedBorder(10));
        btShowAll.addActionListener(this);

        btSapXep = new JButton("Sắp xếp theo tên");
        btSapXep.setFont(new Font("Arial", Font.BOLD, 15));
        btSapXep.setBounds(830, 0, 150, 30);
        btSapXep.setBackground(MyColor.ColorButton);
        btSapXep.setBorder(new RoundedBorder(10));
        btSapXep.addActionListener(this);

        pnShowAll.add(btShowAll);
        pnShowAll.add(btSapXep);
    }

    public void ShowOnTable(NHACUNGCAP ncc) {
        Vector<String> row = new Vector<String>();
        row.add(ncc.getMaNCC().trim());
        row.add(ncc.getTenNCC().replaceAll("\\s+", " ").trim());
        model.addRow(row);
    }

    public void getInfoTextField(NHACUNGCAP ncc) {
        ncc.setMaNCC(txMaNCC.getText().trim());
        ncc.setTenNCC(txTenNCC.getText().replaceAll("\\s+", " ").trim());
    }

    public void getDatabase() {
        try {
            QLNCCBUS qlnccbus = new QLNCCBUS();
            if (QLNCCBUS.dsncc == null)
                try {
                    qlnccbus.docDSNCC();
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            // set up header(column)
            header = new Vector<String>();
            header.add("Mã Nhà Cung Cấp");
            header.add("Tên Nhà Cung Cấp");
            model = new DefaultTableModel(header, 0);
            for (NHACUNGCAP ncc : QLNCCBUS.dsncc) {
                ShowOnTable(ncc);
            }
            tblQLNCC.setModel(model);
        } catch (Exception e1) {
            System.out.println(e1);
        }
    }

    public void OffBTBgSelected() {
        btnxb.setBackground(MyColor.ColorOcean);
        btMenu.setBackground(MyColor.ColorOcean);
        btMenuTimKiem.setBackground(MyColor.ColorOcean);
        btDangXuat.setBackground(MyColor.ColorOcean);
        btThoat.setBackground(MyColor.ColorOcean);
    }

    public void setTimKiem() {
        if (btSearch == null) { // Là button của phần tìm kiếm cơ bản
            lbLCTK.setVisible(true);
            lbTuKhoaTK.setVisible(true);
            comboBoxDSKhoaTK.setVisible(true);
            txKhoaTK.setVisible(true);

            TitledBorder titleTK;
            Border blackline;
            blackline = BorderFactory.createLineBorder(Color.black);
            titleTK = BorderFactory.createTitledBorder(blackline,"Tìm kiếm");
            titleTK.setTitleFont(new Font("Arial", Font.BOLD, 28));
            titleTK.setTitleJustification(TitledBorder.CENTER);
            pnTimKiem.setBorder(titleTK);

            btSearch = new JButton("Tìm kiếm");
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

    public void OffPageQLSACH(Boolean x) {
        pnNhapTTNCC.setVisible(x);
        pnShowAll.setVisible(x);
        pnTimKiem.setVisible(x);
        pnTTNCC.setVisible(x);
    }
}

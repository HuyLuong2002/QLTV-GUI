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
import QLTV.BUS.QLNXBBUS;
import QLTV.DTO.NXB;

public class QLNXBGUI extends JFrame implements ActionListener, MouseListener {
    JPanel pnTTNXB, pnNhapTTNXB, pnShowAll, pnMenu, pnTimKiem, pnLibrary;
    JLabel lbHome, lbTTNXB, lbMaNXB, lbTenNXB, lbLCTK, lbTuKhoaTK, lbLibrary;
    JLabel lbTKMaNXB;
    JTextField txMaNXB, txTenNXB, txKhoaTK;
    JButton btThem, btnxb, btSua, btXoa, btHoanTac, btMenuTimKiem, btShowAll, btThongKe;
    JButton btMenu, btSapXep, btDangXuat, btThoat;
    JButton btSearch;

    JComboBox<String> comboBoxDSKhoaTK;
    JScrollPane pane;

    JTable tblQLNXB;
    DefaultTableModel model;
    Vector<String> header;
    private ImageIcon imgIconHP;

    public QLNXBGUI() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(1400, 800);
        this.setTitle("Quản lý thông tin nhà xuất bản");
        this.setLayout(null);
        this.setLocationRelativeTo(null);
        imgIconHP = new ImageIcon("images\\app_logo.png");
        this.setIconImage(imgIconHP.getImage());

        pnTTNXB = new JPanel();
        pnNhapTTNXB = new JPanel();
        pnShowAll = new JPanel();
        pnMenu = new JPanel();
        pnTimKiem = new JPanel();
        pnLibrary = new JPanel();

        pnTTNXB.setLayout(new GridLayout(2, 1, 0, -300));
        pnTTNXB.setBounds(242, 0, 1142, 400);
        pnTTNXB.setBackground(MyColor.ColorBlue);

        pnLibrary.setLayout(null);
        pnLibrary.setBounds(0, 0, 240, 178);
        pnLibrary.setBackground(MyColor.ColorOcean);

        lbLibrary = new JLabel();
        lbLibrary.setIcon(new ImageIcon("images\\user_login.png"));
        lbLibrary.setBounds(55, 25, 125, 125);

        pnLibrary.add(lbLibrary);

        pnShowAll.setLayout(null);
        pnShowAll.setBounds(242, 402, 1142, 30);
        pnShowAll.setBackground(MyColor.ColorBlue);

        pnNhapTTNXB.setLayout(null);
        pnNhapTTNXB.setBounds(242, 415, 720, 550);
        pnNhapTTNXB.setBackground(MyColor.ColorBlue);

        pnMenu.setLayout(new GridLayout(9, 1));
        pnMenu.setBounds(0, 180, 240, 590);
        pnMenu.setBackground(MyColor.ColorOcean);

        pnTimKiem.setLayout(null);
        pnTimKiem.setBounds(962, 430, 423, 332);
        pnTimKiem.setBackground(MyColor.ColorBlue);

        // add components
        this.add(pnMenu);
        this.add(pnShowAll);
        this.add(pnNhapTTNXB);
        this.add(pnTimKiem);
        this.add(pnLibrary);

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
                NXB nxb = new NXB();
                getInfoTextField(nxb);
                // Truy cập vào bus
                QLNXBBUS qlnxbbus = new QLNXBBUS();
                int kiemtra = -1;
                try {
                    kiemtra = qlnxbbus.them(nxb);
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
                if (kiemtra == 0) {
                    // Đưa dữ liệu lên table
                    header = new Vector<String>();
                    header.add("Mã Nhà Xuất Bản");
                    header.add("Tên Nhà Xuất Bản");
                    if (model.getRowCount() == 0) {
                        model = new DefaultTableModel(header, 0);
                    }
                    ShowOnTable(nxb);
                    tblQLNXB.setModel(model);
                }
            } catch (Exception e1) {
                System.out.println(e1);
            }
        } else if (e.getSource() == btSua) {
            int i = tblQLNXB.getSelectedRow();
            int kt = -1;
            if (i >= 0) {
                NXB nxb = new NXB();
                NXB manxbcu = QLNXBBUS.dsnxb.get(i);
                getInfoTextField(nxb);
                try {
                    QLNXBBUS qlnxbbus = new QLNXBBUS();
                    kt = qlnxbbus.sua(nxb, manxbcu, i);
                } catch (Exception e1) {
                    System.out.println(e1);
                }
                if (kt == 0) {
                    model.setValueAt(nxb.getMaNXB(), i, 0);
                    model.setValueAt(nxb.getTenNXB(), i, 1);
                    tblQLNXB.setModel(model);
                }
            }
        } else if (e.getSource() == btXoa) {
            int XacNhanXoa = JOptionPane.showConfirmDialog(null, "Bạn có chắc chắn muốn xóa không ?", "Thông báo",
                    JOptionPane.YES_NO_OPTION);
            if (XacNhanXoa == 0) {
                int kt = -1;
                String manxb = txMaNXB.getText();
                int i = tblQLNXB.getSelectedRow();
                if (i >= 0) {
                    try {
                        // Truy cập xuống BUS
                        NXB SachOld = QLNXBBUS.dsnxb.get(i);
                        QLNXBBUS.htXoa.add(SachOld);
                        QLNXBBUS qlnxbbus = new QLNXBBUS();
                        kt = qlnxbbus.xoa(manxb, i);
                        // Quay dề GUI
                    } catch (Exception e1) {
                        System.out.println(e1);
                    }
                    if (kt == 0) {
                        model.removeRow(i);
                        tblQLNXB.setModel(model);
                    }
                }
            }
        } else if (e.getSource() == btHoanTac) {
            int ktHT = 0;
            if (QLNXBBUS.htXoa.size() == 0) {
                JOptionPane.showMessageDialog(null, "Dữ liệu hoàn tác rỗng", "Lỗi",
                        JOptionPane.ERROR_MESSAGE);
            } else {
                for (NXB nxb : QLNXBBUS.htXoa) {
                    QLNXBBUS qlnxbbus = new QLNXBBUS();
                    int kiemtra = -1;
                    try {
                        kiemtra = qlnxbbus.hoantacXoa(nxb);
                    } catch (Exception e1) {
                        e1.printStackTrace();
                    }
                    if (kiemtra == 0) {
                        // Đưa dữ liệu lên table
                        header = new Vector<String>();
                        header.add("Mã Nhà Xuất Bản");
                        header.add("Tên Nhà Xuất Bản");
                        if (model.getRowCount() == 0) {
                            model = new DefaultTableModel(header, 0);
                        }
                        ShowOnTable(nxb);
                        ktHT = 1;
                    } else if (kiemtra == -1) {
                        JOptionPane.showMessageDialog(null, "Hoàn tác dữ liệu thất bại", "Lỗi",
                                JOptionPane.ERROR_MESSAGE);
                        ktHT = 0;
                    }
                }
            }
            if (ktHT == 1) {
                JOptionPane.showMessageDialog(null, "Hoàn tác dữ liệu thành công", "Thông báo",
                        JOptionPane.INFORMATION_MESSAGE);
                tblQLNXB.setModel(model);
            }
        } else if (e.getSource() == btMenuTimKiem) { // Của button Tìm kiếm nhà xuất bản, để hiện thị
            // khung tìm kiếm
            OffBTBgSelected();
            btMenuTimKiem.setBackground(MyColor.ColorLightBlue);
            setTimKiem();
        } else if (e.getSource() == btSearch) {
            int vtkey = Integer.parseInt(String.valueOf(comboBoxDSKhoaTK.getSelectedIndex()));
            String tukhoa = txKhoaTK.getText().replaceAll("\\s+", "").toLowerCase();
            if (tukhoa.equals("") == true) {
                JOptionPane.showMessageDialog(null, "Xin mời nhập từ khóa", "Lỗi", JOptionPane.ERROR_MESSAGE);
            } else if (vtkey == 0) {
                JOptionPane.showMessageDialog(null, "Xin mời lựa chọn khóa tìm kiếm", "Lỗi", JOptionPane.ERROR_MESSAGE);
            } else {
                QLNXBBUS qlnxbbus = new QLNXBBUS();
                if (vtkey == 1) {
                    NXB kq = qlnxbbus.timTheoMa(tukhoa);
                    model.setRowCount(0);
                    if (kq == null) {
                        JOptionPane.showMessageDialog(null, "Không tìm thấy dữ liệu", "Lỗi", JOptionPane.ERROR_MESSAGE);
                    } else {
                        ShowOnTable(kq);
                        tblQLNXB.setModel(model);
                    }
                } else if (vtkey == 2) {
                    ArrayList<NXB> kq = qlnxbbus.timTheoTen(tukhoa);
                    model.setRowCount(0);
                    if (kq.size() == 0) {
                        JOptionPane.showMessageDialog(null, "Không tìm thấy dữ liệu", "Lỗi", JOptionPane.ERROR_MESSAGE);
                    } else {
                        for (NXB nxb : kq) {
                            ShowOnTable(nxb);
                        }
                        tblQLNXB.setModel(model);
                    }
                }
            }
        } else if (e.getSource() == btShowAll) {
            model.setRowCount(0);
            for (NXB nxb : QLNXBBUS.dsnxb) {
                ShowOnTable(nxb);
            }
            tblQLNXB.setModel(model);
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
        QLNXBBUS.dsnxb.sort(((o1, o2) -> o1.getTenNXB().compareTo(o2.getTenNXB())));
        model.setRowCount(0);
        for (NXB nxb : QLNXBBUS.dsnxb) {
            ShowOnTable(nxb);
        }
        tblQLNXB.setModel(model);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource() == tblQLNXB) {
            int i = tblQLNXB.getSelectedRow();
            if (i >= 0) {
                NXB nxb = new NXB();
                nxb = QLNXBBUS.dsnxb.get(i);
                txMaNXB.setText(nxb.getMaNXB().replaceAll("\\s+", " ").trim());
                txTenNXB.setText(nxb.getTenNXB().replaceAll("\\s+", " ").trim());
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
        if (e.getSource() == txMaNXB) {
            txMaNXB.setToolTipText("Gợi ý: NXB001");
        }
        if(e.getSource() == txTenNXB)
            txTenNXB.setToolTipText("Gợi ý: Nhà xuất bản trẻ");
    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    public void setInput() {
        // label Matacgia
        lbMaNXB = new JLabel("Mã NXB:");
        lbMaNXB.setFont(new Font("Arial", Font.BOLD, 20));
        lbMaNXB.setBounds(20, 0, 130, 80);
        // label Tentacgia
        lbTenNXB = new JLabel("Tên NXB:");
        lbTenNXB.setFont(new Font("Arial", Font.BOLD, 20));
        lbTenNXB.setBounds(20, 40, 130, 80);

        // labelLCTK
        lbLCTK = new JLabel("Lựa chọn khóa tìm kiếm:");
        lbLCTK.setFont(new Font("Arial", Font.BOLD, 20));
        lbLCTK.setBounds(10, 50, 260, 80);

        // labelTuKhoaTK
        lbTuKhoaTK = new JLabel("Nhập từ khóa tìm kiếm:");
        lbTuKhoaTK.setFont(new Font("Arial", Font.BOLD, 20));
        lbTuKhoaTK.setBounds(10, 100, 260, 80);

        // JTextField Mã Nhà Xuất Bản
        txMaNXB = new JTextField();
        txMaNXB.setBounds(160, 25, 180, 30);
        txMaNXB.setFont(new Font("Arial", Font.PLAIN, 15));
        txMaNXB.addMouseListener(this);
        // JTextField Tên Nhà Xuất Bản
        txTenNXB = new JTextField();
        txTenNXB.setBounds(160, 65, 180, 30);
        txTenNXB.setFont(new Font("Arial", Font.PLAIN, 15));
        txTenNXB.addMouseListener(this);
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
        String[] dsKhoaTK = { "", "Mã NXB", "Tên NXB" };
        comboBoxDSKhoaTK = new JComboBox<>(dsKhoaTK);
        comboBoxDSKhoaTK.setFont(new Font("Arial", Font.BOLD, 13));
        comboBoxDSKhoaTK.setBounds(250, 75, 100, 30);
        comboBoxDSKhoaTK.addActionListener(this);

        lbLCTK.setVisible(false);
        lbTuKhoaTK.setVisible(false);
        comboBoxDSKhoaTK.setVisible(false);
        txKhoaTK.setVisible(false);

        pnNhapTTNXB.add(lbMaNXB);
        pnNhapTTNXB.add(lbTenNXB);

        pnNhapTTNXB.add(txMaNXB);
        pnNhapTTNXB.add(txTenNXB);

        pnNhapTTNXB.add(btThem);
        pnNhapTTNXB.add(btSua);
        pnNhapTTNXB.add(btXoa);
        pnNhapTTNXB.add(btHoanTac);

    }

    public void setMenu() {
        // Set menu side left
        ImageIcon iconMenu = new ImageIcon("images\\menu.png");
        ImageIcon iconPubCompany = new ImageIcon("images\\company-building-icon.png");
        ImageIcon iconSearch = new ImageIcon("images\\search.png");
        ImageIcon iconLogout = new ImageIcon("images\\logout.png");
        ImageIcon iconExited = new ImageIcon("images\\exit.png");

        btMenu = new JButton("Menu");
        btMenu.setFont(new Font("Times new Roman", Font.BOLD, 20));
        btMenu.setBackground(MyColor.ColorOcean);
        btMenu.setIcon(iconMenu);
        btMenu.setHorizontalAlignment(SwingConstants.LEFT);
        btMenu.setBorder(BorderFactory.createEmptyBorder());
        btMenu.addActionListener(this);

        btnxb = new JButton("Thông tin NXB");
        btnxb.setFont(new Font("Times new Roman", Font.BOLD, 20));
        btnxb.setBackground(MyColor.ColorLightBlue);
        btnxb.setIcon(iconPubCompany);
        btnxb.setHorizontalAlignment(SwingConstants.LEFT);
        btnxb.setBorder(BorderFactory.createEmptyBorder());
        btnxb.addActionListener(this);

        btMenuTimKiem = new JButton("Tìm kiếm NXB");
        btMenuTimKiem.setFont(new Font("Times new Roman", Font.BOLD, 20));
        btMenuTimKiem.setBackground(MyColor.ColorOcean);
        btMenuTimKiem.setIcon(iconSearch);
        btMenuTimKiem.setHorizontalAlignment(SwingConstants.LEFT);
        btMenuTimKiem.setBorder(BorderFactory.createEmptyBorder());
        btMenuTimKiem.addActionListener(this);

        // JButton Đăng xuất
        btDangXuat = new JButton("Đăng xuất");
        btDangXuat.setFont(new Font("Times new Roman", Font.BOLD, 20));
        btDangXuat.setBackground(MyColor.ColorOcean);
        btDangXuat.setIcon(iconLogout);
        btDangXuat.setHorizontalAlignment(SwingConstants.LEFT);
        btDangXuat.setBorder(BorderFactory.createEmptyBorder());
        btDangXuat.addActionListener(this);
        // JButton thoát
        btThoat = new JButton("Thoát");
        btThoat.setFont(new Font("Times new Roman", Font.BOLD, 20));
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
        lbTTNXB = new JLabel("THÔNG TIN NXB");
        lbTTNXB.setFont(new Font("Arial", Font.BOLD, 30));
        lbTTNXB.setHorizontalAlignment(SwingConstants.CENTER);
        lbTTNXB.setVerticalAlignment(SwingConstants.TOP);
        // ----set up table----
        tblQLNXB = new JTable();
        pane = new JScrollPane(tblQLNXB);
        pane.setAutoscrolls(true);
        tblQLNXB.setRowHeight(30);
        tblQLNXB.setFont(new Font(null, 0, 13));
        tblQLNXB.setBackground(MyColor.ColorLightGray);
        tblQLNXB.addMouseListener(this);
        tblQLNXB.setDefaultEditor(Object.class, null);
        tblQLNXB.setSelectionBackground(MyColor.Color);
        tblQLNXB.getTableHeader().setBackground(MyColor.ColorSilver);

        this.add(pnTTNXB);
        pnTTNXB.add(lbTTNXB);
        pnTTNXB.add(pane);
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

    public void ShowOnTable(NXB nxb) {
        Vector<String> row = new Vector<String>();
        row.add(nxb.getMaNXB().replaceAll("\\s+", "").trim());
        row.add(nxb.getTenNXB().replaceAll("\\s+", " ").trim());
        model.addRow(row);
    }

    public void getInfoTextField(NXB nxb) {
        nxb.setMaNXB(txMaNXB.getText().replaceAll("\\s+", "").trim());
        nxb.setTenNXB(txTenNXB.getText().replaceAll("\\s+", " ").trim());
    }

    public void getDatabase() {
        try {
            QLNXBBUS qlnxbbus = new QLNXBBUS();
            if (QLNXBBUS.dsnxb == null)
                try {
                    qlnxbbus.docdsnxb();
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            // set up header(column)
            header = new Vector<String>();
            header.add("Mã Nhà Xuất Bản");
            header.add("Tên Nhà Xuất Bản");
            model = new DefaultTableModel(header, 0);
            for (NXB nxb : QLNXBBUS.dsnxb) {
                ShowOnTable(nxb);
            }
            tblQLNXB.setModel(model);
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

    public void setTimKiemNC() {
        // Ẩn các component không dùng tới
        btSearch.setVisible(false);
        txKhoaTK.setVisible(false);
        // set up component mới
        // Năm xb
        if (lbTKMaNXB == null) {
            lbTKMaNXB = new JLabel("Mã Nhà Xuất Bản:");
            lbTKMaNXB.setFont(new Font("Arial", Font.BOLD, 20));
            lbTKMaNXB.setBounds(10, 170, 100, 50);
        }
        if (lbTKMaNXB != null) {
            lbTKMaNXB.setVisible(true);
        }
        pnTimKiem.add(lbTKMaNXB);
    }

    public void OffPageQLSACH(Boolean x) {
        pnNhapTTNXB.setVisible(x);
        pnShowAll.setVisible(x);
        pnTimKiem.setVisible(x);
        pnTTNXB.setVisible(x);
    }
}

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
import MyCustom.RoundedBorder;
import QLTV.BUS.QLNXBBUS;
import QLTV.DTO.NXB;

public class QLNXBGUI extends JFrame implements ActionListener, MouseListener {
    JPanel pnTTNXB, pnNhapTTNXB, pnShowAll, pnMenu, pnTimKiem;
    JLabel lbHome, lbTTNXB, lbMaNXB, lbTenNXB, lbLCTK, lbTuKhoaTK, lbKQTK;
    JLabel lbTKMaNXB;
    JTextField txMaNXB, txTenNXB, txKhoaTK;
    JButton btDoc, btThem, btnxb, btSua, btXoa, btHoanTac, btMenuTimKiem, btShowAll, btThongKe;
    JButton btMenu, btDangXuat, btThoat;
    JButton btSearch;

    JComboBox<String> comboBoxDSKhoaTK;

    JTable tblQLNXB;
    DefaultTableModel model;
    Vector<String> header;

    public QLNXBGUI() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        this.setTitle("Quản lý thông tin nhà xuất bản");
        this.setLayout(null);

        pnTTNXB = new JPanel();
        pnNhapTTNXB = new JPanel();
        pnShowAll = new JPanel();
        pnMenu = new JPanel();
        pnTimKiem = new JPanel();
        pnTTNXB.setLayout(new GridLayout(3, 1, 0, -300));
        pnTTNXB.setBounds(320, 0, 1200, 400);
        pnShowAll.setLayout(null);
        pnShowAll.setBounds(300, 400, 1537, 40);
        pnNhapTTNXB.setLayout(null);
        pnNhapTTNXB.setBounds(200, 415, 820, 600);
        pnMenu.setBackground(Color.LIGHT_GRAY);
        pnMenu.setLayout(new GridLayout(15, 1));
        pnMenu.setSize(new Dimension(300, 1200));
        pnTimKiem.setLayout(null);
        pnTimKiem.setBounds(1030, 445, 490, 350);

        // add components
        this.add(pnMenu);
        this.add(pnShowAll);
        this.add(pnNhapTTNXB);
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
        if (e.getSource() == btDoc) {
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
                header.add("Mã nhà xuất bản");
                header.add("Tên nhà xuất bản");
                model = new DefaultTableModel(header, 0);
                for (NXB nxb : QLNXBBUS.dsnxb) {
                    ShowOnTable(nxb);
                    tblQLNXB.setModel(model);
                }
            } catch (Exception e1) {
                System.out.println(e1);
            }
        } else if (e.getSource() == btThem) {
            try {
                NXB nxb = new NXB();
                getInfoTextField(nxb);
                // Truy cập vào bus
                QLNXBBUS qlnxbbus = new QLNXBBUS();
                int kiemtra = 0;
                try {
                    kiemtra = qlnxbbus.them(nxb);
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
                if (kiemtra == 1) {
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

            if (i >= 0) {
                NXB nxb = new NXB();
                NXB manxbcu = QLNXBBUS.dsnxb.set(i, nxb);
                getInfoTextField(nxb);
                try {
                    QLNXBBUS qlnxbbus = new QLNXBBUS();
                    qlnxbbus.sua(nxb, manxbcu, i);
                    model.setValueAt(nxb.getMaNXB(), i, 0);
                    model.setValueAt(nxb.getTenNXB(), i, 1);
                    tblQLNXB.setModel(model);
                } catch (Exception e1) {
                    System.out.println(e1);
                }
            }
        } else if (e.getSource() == btXoa) {
            int XacNhanXoa = JOptionPane.showConfirmDialog(null, "Bạn có chắc chắn muốn xóa không ?", "Thông báo",
                    JOptionPane.YES_NO_OPTION);
            if (XacNhanXoa == 0) {
                String manxb = txMaNXB.getText();
                int i = tblQLNXB.getSelectedRow();
                if (i >= 0) {
                    try {
                        // Truy cập xuống BUS
                        NXB SachOld = QLNXBBUS.dsnxb.get(i);
                        QLNXBBUS.htXoa.add(SachOld);
                        QLNXBBUS qlnxbbus = new QLNXBBUS();
                        qlnxbbus.xoa(manxb, i);
                        // Quay dề GUI
                        model.removeRow(i);
                        tblQLNXB.setModel(model);
                    } catch (Exception e1) {
                        System.out.println(e1);
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
                    int kiemtra = 0;
                    try {
                        kiemtra = qlnxbbus.hoantacXoa(nxb);
                    } catch (Exception e1) {
                        e1.printStackTrace();
                    }
                    if (kiemtra == 1) {
                        // Đưa dữ liệu lên table
                        header = new Vector<String>();
                        header.add("Mã Nhà Xuất Bản");
                        header.add("Tên Nhà Xuất Bản");
                        if (model.getRowCount() == 0) {
                            model = new DefaultTableModel(header, 0);
                        }
                        ShowOnTable(nxb);
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
                tblQLNXB.setModel(model);
            }
        }else if (e.getSource() == btMenuTimKiem) { // Của button Tìm kiếm nhà xuất bản, để hiện thị
            // khung tìm kiếm
            OffBTBgSelected();
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
                QLNXBBUS qlnxbbus = new QLNXBBUS();
                if (vtkey == 1) {
                    NXB kq = qlnxbbus.timTheoMa(tukhoa);
                    model.setRowCount(0);
                    if (kq == null) {
                        lbKQTK.setText("Kết quả tìm kiếm: Không tìm thấy");
                    } else {
                        ShowOnTable(kq);
                        lbKQTK.setText("Kết quả tìm kiếm: Tìm thấy " + model.getRowCount() + " NXB");
                        tblQLNXB.setModel(model);
                    }
                } else if (vtkey == 2) {
                    ArrayList<NXB> kq = qlnxbbus.timTheoTen(tukhoa);
                    model.setRowCount(0);
                    if (kq.size() == 0) {
                        lbKQTK.setText("Kết quả tìm kiếm: Không tìm thấy");
                    } else {
                        for (NXB nxb : kq) {
                            ShowOnTable(nxb);
                        }
                        lbKQTK.setText("Kết quả tìm kiếm: Tìm thấy " + model.getRowCount() + " NXB");
                        tblQLNXB.setModel(model);
                    }
                }
            }
        } else if (e.getSource() == btShowAll) {
            lbKQTK.setText("");
            model.setRowCount(0);
            for (NXB nxb : QLNXBBUS.dsnxb) {
                ShowOnTable(nxb);
            }
            tblQLNXB.setModel(model);
        }
        if (e.getSource() == btThongKe) {
            String luachon = "";
            int count;
            do {
                String[] options = { "", "Mã Nhà Xuất Bản", "SL Nhà Xuất Bản ban đầu", "SL hiện tại" };
                luachon = (String) JOptionPane.showInputDialog(null, "Mời lựa chọn khóa thống kê:", "Thống kê",
                        JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);
                QLNXBBUS qlnxbbus = new QLNXBBUS();
                switch (luachon) {
                    case "Mã Nhà Xuất Bản":
                        count = qlnxbbus.ThongKeMaNXB();
                        JOptionPane.showMessageDialog(null, "Đếm số lượng Nhà Xuất Bản: " + String.valueOf(count),
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
        if (e.getSource() == btnxb) {
            OffPageQLSACH(true);
            OffBTBgSelected();
            btnxb.setBackground(Color.green);
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource() == tblQLNXB) {
            int i = tblQLNXB.getSelectedRow();
            if (i >= 0) {
                NXB nxb = new NXB();
                nxb = QLNXBBUS.dsnxb.get(i);
                txMaNXB.setText(nxb.getMaNXB());
                txTenNXB.setText(nxb.getTenNXB());
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
    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    public void setInput() {
        // label Matacgia
        lbMaNXB = new JLabel("Mã NXB:");
        lbMaNXB.setFont(new Font("Arial", Font.BOLD, 20));
        lbMaNXB.setBounds(120, 0, 150, 100);
        // label Tentacgia
        lbTenNXB = new JLabel("Tên NXB:");
        lbTenNXB.setFont(new Font("Arial", Font.BOLD, 20));
        lbTenNXB.setBounds(120, 50, 150, 100);

        // labelLCTK
        lbLCTK = new JLabel("Lựa chọn khóa tìm kiếm:");
        lbLCTK.setFont(new Font("Arial", Font.BOLD, 20));
        lbLCTK.setBounds(10, 50, 250, 100);

        // labelTuKhoaTK
        lbTuKhoaTK = new JLabel("Nhập từ khóa tìm kiếm:");
        lbTuKhoaTK.setFont(new Font("Arial", Font.BOLD, 20));
        lbTuKhoaTK.setBounds(10, 100, 250, 100);

        // JTextField Mã Nhà Xuất Bản
        txMaNXB = new JTextField();
        txMaNXB.setBounds(270, 30, 200, 35);
        txMaNXB.setFont(new Font("Arial", Font.PLAIN, 15));
        txMaNXB.addMouseListener(this);
        // JTextField Tên Nhà Xuất Bản
        txTenNXB = new JTextField();
        txTenNXB.setBounds(270, 80, 200, 35);
        txTenNXB.setFont(new Font("Arial", Font.PLAIN, 15));
        txTenNXB.addMouseListener(this);
        // JTextField Khóa tìm kiếm
        txKhoaTK = new JTextField();
        txKhoaTK.setFont(new Font("Arial", Font.PLAIN, 15));
        txKhoaTK.setBounds(260, 135, 200, 35);
        txKhoaTK.addMouseListener(this);

        // JButtonDoc
        btDoc = new JButton("Đọc DS");
        btDoc.setFont(new Font("Arial", Font.BOLD, 15));
        btDoc.setBounds(120, 130, 100, 40);
        btDoc.setBackground(Color.cyan);
        btDoc.setBorder(new RoundedBorder(10));
        btDoc.addActionListener(this);
        // JbuttonThem
        btThem = new JButton("Thêm");
        btThem.setFont(new Font("Arial", Font.BOLD, 15));
        btThem.setBounds(120, 130, 100, 40);
        btThem.setBackground(Color.cyan);
        btThem.setBorder(new RoundedBorder(10));
        btThem.addActionListener(this);
        // JbuttonSua
        btSua = new JButton("Sửa");
        btSua.setFont(new Font("Arial", Font.BOLD, 15));
        btSua.setBounds(240, 130, 100, 40);
        btSua.setBackground(Color.cyan);
        btSua.setBorder(new RoundedBorder(10));
        btSua.addActionListener(this);
        // JbuttonXoa
        btXoa = new JButton("Xóa");
        btXoa.setFont(new Font("Arial", Font.BOLD, 15));
        btXoa.setBounds(360, 130, 100, 40);
        btXoa.setBackground(Color.cyan);
        btXoa.setBorder(new RoundedBorder(10));
        btXoa.addActionListener(this);
        // JbuttonHoanTac
        btHoanTac = new JButton("Hoàn tác");
        btHoanTac.setFont(new Font("Arial", Font.BOLD, 15));
        btHoanTac.setBounds(480, 130, 100, 40);
        btHoanTac.setBackground(Color.cyan);
        btHoanTac.setBorder(new RoundedBorder(10));
        btHoanTac.addActionListener(this);

        // set up ComboBox
        String[] dsKhoaTK = { "", "Mã Nhà Xuất Bản", "Tên Nhà Xuất Bản"};
        comboBoxDSKhoaTK = new JComboBox<>(dsKhoaTK);
        comboBoxDSKhoaTK.setFont(new Font("Arial", Font.BOLD, 13));
        comboBoxDSKhoaTK.setBounds(260, 85, 120, 35);
        comboBoxDSKhoaTK.addActionListener(this);

        btDoc.setVisible(false);
        lbLCTK.setVisible(false);
        lbTuKhoaTK.setVisible(false);
        comboBoxDSKhoaTK.setVisible(false);
        txKhoaTK.setVisible(false);

        pnNhapTTNXB.add(lbMaNXB);
        pnNhapTTNXB.add(lbTenNXB);

        pnNhapTTNXB.add(txMaNXB);
        pnNhapTTNXB.add(txTenNXB);

        pnNhapTTNXB.add(btDoc);
        pnNhapTTNXB.add(btThem);
        pnNhapTTNXB.add(btSua);
        pnNhapTTNXB.add(btXoa);
        pnNhapTTNXB.add(btHoanTac);

    }

    public void setMenu() {
        // Set menu side left
        ImageIcon iconHome = new ImageIcon("images\\home.png");
        ImageIcon iconMenu = new ImageIcon("images\\menu.png");
        ImageIcon iconPubCompany = new ImageIcon("images\\company-building-icon.png");
        ImageIcon iconSearch = new ImageIcon("images\\search.png");
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

        btnxb = new JButton("Thông tin NXB");
        btnxb.setFont(new Font("Arial", Font.BOLD, 20));
        btnxb.setBackground(Color.green);
        btnxb.setIcon(iconPubCompany);
        btnxb.setHorizontalAlignment(SwingConstants.LEFT);
        btnxb.setBorder(BorderFactory.createEmptyBorder());
        btnxb.addActionListener(this);

        btMenuTimKiem = new JButton("Tìm kiếm NXB");
        btMenuTimKiem.setFont(new Font("Arial", Font.BOLD, 20));
        btMenuTimKiem.setBackground(Color.LIGHT_GRAY);
        btMenuTimKiem.setIcon(iconSearch);
        btMenuTimKiem.setHorizontalAlignment(SwingConstants.LEFT);
        btMenuTimKiem.setBorder(BorderFactory.createEmptyBorder());
        btMenuTimKiem.addActionListener(this);

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
        // labelKQTK
        lbKQTK = new JLabel();
        lbKQTK.setHorizontalAlignment(SwingConstants.CENTER);
        lbKQTK.setVerticalAlignment(SwingConstants.TOP);
        // ----set up table----
        tblQLNXB = new JTable();
        JScrollPane pane = new JScrollPane(tblQLNXB);
        pane.setAutoscrolls(true);
        tblQLNXB.setRowHeight(20);
        tblQLNXB.setFont(new Font(null, 0, 13));
        tblQLNXB.setBackground(Color.LIGHT_GRAY);
        tblQLNXB.addMouseListener(this);
        tblQLNXB.setDefaultEditor(Object.class, null);

        this.add(pnTTNXB);
        pnTTNXB.add(lbTTNXB);
        pnTTNXB.add(lbKQTK);
        pnTTNXB.add(pane);
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

    public void ShowOnTable(NXB nxb) {
        Vector<String> row = new Vector<String>();
        row.add(nxb.getMaNXB());
        row.add(nxb.getTenNXB());
        model.addRow(row);
    }

    public void getInfoTextField(NXB nxb) {
        nxb.setMaNXB(txMaNXB.getText());
        nxb.setTenNXB(txTenNXB.getText());
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
                tblQLNXB.setModel(model);
            }
        } catch (Exception e1) {
            System.out.println(e1);
        }
    }

    public void OffBTBgSelected() {
        btnxb.setBackground(Color.lightGray);
        btMenu.setBackground(Color.lightGray);
        btMenuTimKiem.setBackground(Color.lightGray);
        btDangXuat.setBackground(Color.lightGray);
        btThoat.setBackground(Color.lightGray);
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

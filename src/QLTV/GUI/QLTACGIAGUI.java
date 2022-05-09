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
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import MyCustom.LoginPage;
import MyCustom.Menu;
import MyCustom.RoundedBorder;
import QLTV.BUS.QLTACGIABUS;
import QLTV.DTO.TACGIA;

public class QLTACGIAGUI extends JFrame implements ActionListener, MouseListener {
    JPanel pnTTTacgia, pnNhapTTTacgia, pnShowAll, pnMenu, pnTimKiem;
    JLabel lbHome, lbTTTacgia, lbMatacgia, lbTentacgia, lbLCTK, lbTuKhoaTK, lbKQTK;
    JLabel lbTKMatacgia;
    JTextField txMatacgia, txTentacgia, txKhoaTK;
    JButton btDoc, btThem, btSua, btXoa, btHoanTac, btMenuTimKiem, btShowAll, btThongKe;
    JButton btMenu, bttacgia, btDangXuat, btThoat;
    JButton btSearch;

    JComboBox<String> comboBoxDSKhoaTK;

    JTable tblQLTACGIA;
    DefaultTableModel model;
    Vector<String> header;

    public QLTACGIAGUI() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        this.setTitle("Quản lý thông tin tác giả");
        this.setLayout(null);

        pnTTTacgia = new JPanel();
        pnNhapTTTacgia = new JPanel();
        pnShowAll = new JPanel();
        pnMenu = new JPanel();
        pnTimKiem = new JPanel();
        pnTTTacgia.setLayout(new GridLayout(3, 1, 0, -300));
        pnTTTacgia.setBounds(320, 0, 1200, 400);
        pnShowAll.setLayout(null);
        pnShowAll.setBounds(300, 400, 1537, 40);
        pnNhapTTTacgia.setLayout(null);
        pnNhapTTTacgia.setBounds(200, 415, 820, 600);
        pnMenu.setBackground(Color.LIGHT_GRAY);
        pnMenu.setLayout(new GridLayout(15, 1));
        pnMenu.setSize(new Dimension(300, 1200));
        pnTimKiem.setLayout(null);
        pnTimKiem.setBounds(1030, 445, 490, 350);

        // add components
        this.add(pnMenu);
        this.add(pnShowAll);
        this.add(pnNhapTTTacgia);
        this.add(pnTimKiem);

        setTableTacGia();
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
                QLTACGIABUS qltacgiabus = new QLTACGIABUS();
                if (QLTACGIABUS.dstacgia == null)
                    try {
                        qltacgiabus.docdstacgia();
                    } catch (Exception e1) {
                        e1.printStackTrace();
                    }
                // set up header(column)
                header = new Vector<String>();
                header.add("Mã tác giả");
                header.add("Tên tác giả");
                model = new DefaultTableModel(header, 0);
                for (TACGIA tacgia : QLTACGIABUS.dstacgia) {
                    ShowOnTable(tacgia);
                    tblQLTACGIA.setModel(model);
                }
            } catch (Exception e1) {
                System.out.println(e1);
            }
        } else if (e.getSource() == btThem) {
            try {
                TACGIA tacgia = new TACGIA();
                getInfoTextField(tacgia);
                // Truy cập vào bus
                QLTACGIABUS QLTACGIABUS = new QLTACGIABUS();
                int kiemtra = 0;
                try {
                    kiemtra = QLTACGIABUS.them(tacgia);
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
                if (kiemtra == 1) {
                    // Đưa dữ liệu lên table
                    header = new Vector<String>();
                    header.add("Mã Tác Giả");
                    header.add("Tên Tác Giả");
                    if (model.getRowCount() == 0) {
                        model = new DefaultTableModel(header, 0);
                    }
                    ShowOnTable(tacgia);
                    tblQLTACGIA.setModel(model);
                }
            } catch (Exception e1) {
                System.out.println(e1);
            }
        } else if (e.getSource() == btSua) {
            int i = tblQLTACGIA.getSelectedRow();

            if (i >= 0) {
                TACGIA tacgia = new TACGIA();
                TACGIA matacgiacu = QLTACGIABUS.dstacgia.set(i, tacgia);
                getInfoTextField(tacgia);
                try {
                    QLTACGIABUS QLTACGIABUS = new QLTACGIABUS();
                    QLTACGIABUS.sua(tacgia, matacgiacu, i);
                    model.setValueAt(tacgia.getMaTacGia(), i, 0);
                    model.setValueAt(tacgia.getTenTacGia(), i, 1);
                    tblQLTACGIA.setModel(model);
                } catch (Exception e1) {
                    System.out.println(e1);
                }
            }
        } else if (e.getSource() == btXoa) {
            int XacNhanXoa = JOptionPane.showConfirmDialog(null, "Bạn có chắc chắn muốn xóa không ?", "Thông báo",
                    JOptionPane.YES_NO_OPTION);
            if (XacNhanXoa == 0) {
                String matacgia = txMatacgia.getText();
                int i = tblQLTACGIA.getSelectedRow();
                if (i >= 0) {
                    try {
                        // Truy cập xuống BUS
                        TACGIA tacgiaOld = QLTACGIABUS.dstacgia.get(i);
                        QLTACGIABUS.htXoa.add(tacgiaOld);
                        QLTACGIABUS QLTACGIABUS = new QLTACGIABUS();
                        QLTACGIABUS.xoa(matacgia, i);
                        // Quay dề GUI
                        model.removeRow(i);
                        tblQLTACGIA.setModel(model);
                    } catch (Exception e1) {
                        System.out.println(e1);
                    }
                }
            }
        } else if (e.getSource() == btHoanTac) {
            int ktHT = 0;
            if (QLTACGIABUS.htXoa.size() == 0) {
                JOptionPane.showMessageDialog(null, "Dữ liệu hoàn tác rỗng", "Lỗi",
                        JOptionPane.ERROR_MESSAGE);
            } else {
                for (TACGIA tacgia : QLTACGIABUS.htXoa) {
                    QLTACGIABUS QLTACGIABUS = new QLTACGIABUS();
                    int kiemtra = 0;
                    try {
                        kiemtra = QLTACGIABUS.hoantacXoa(tacgia);
                    } catch (Exception e1) {
                        e1.printStackTrace();
                    }
                    if (kiemtra == 1) {
                        // Đưa dữ liệu lên table
                        header = new Vector<String>();
                        header.add("Mã tác giả");
                        header.add("Tên tác giả");
                        if (model.getRowCount() == 0) {
                            model = new DefaultTableModel(header, 0);
                        }
                        ShowOnTable(tacgia);
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
                tblQLTACGIA.setModel(model);
            }
        }else if (e.getSource() == btMenuTimKiem) { // Của button Tìm kiếm tác giả, để hiện thị
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
                QLTACGIABUS QLTACGIABUS = new QLTACGIABUS();
                if (vtkey == 1) {
                    TACGIA kq = QLTACGIABUS.timTheoMa(tukhoa);
                    model.setRowCount(0);
                    if (kq == null) {
                        lbKQTK.setText("Kết quả tìm kiếm: Không tìm thấy");
                    } else {
                        ShowOnTable(kq);
                        lbKQTK.setText("Kết quả tìm kiếm: Tìm thấy " + model.getRowCount() + " TACGIA");
                        tblQLTACGIA.setModel(model);
                    }
                } else if (vtkey == 2) {
                    ArrayList<TACGIA> kq = QLTACGIABUS.timTheoTen(tukhoa);
                    model.setRowCount(0);
                    if (kq.size() == 0) {
                        lbKQTK.setText("Kết quả tìm kiếm: Không tìm thấy");
                    } else {
                        for (TACGIA tacgia : kq) {
                            ShowOnTable(tacgia);
                        }
                        lbKQTK.setText("Kết quả tìm kiếm: Tìm thấy " + model.getRowCount() + " TACGIA");
                        tblQLTACGIA.setModel(model);
                    }
                }
            }
        } else if (e.getSource() == btShowAll) {
            lbKQTK.setText("");
            model.setRowCount(0);
            for (TACGIA tacgia : QLTACGIABUS.dstacgia) {
                ShowOnTable(tacgia);
            }
            tblQLTACGIA.setModel(model);
        }
        if (e.getSource() == btThongKe) {
            String luachon = "";
            int count;
            do {
                String[] options = { "", "Mã tác giả", "SL tác giả ban đầu", "SL hiện tại" };
                luachon = (String) JOptionPane.showInputDialog(null, "Mời lựa chọn khóa thống kê:", "Thống kê",
                        JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);
                QLTACGIABUS QLTACGIABUS = new QLTACGIABUS();
                switch (luachon) {
                    case "Mã tác giả":
                        count = QLTACGIABUS.ThongKeMaTacGia();
                        JOptionPane.showMessageDialog(null, "Đếm số lượng tác giả: " + String.valueOf(count),
                                "Kết quả", JOptionPane.PLAIN_MESSAGE);
                        break;
                }
            } while (luachon != null);
        }
        if (e.getSource() == btMenu) {
            this.dispose();
            new Menu();
        }
        if (e.getSource() == btDangXuat) {
            this.dispose();
            try {
                new LoginPage();
            } catch (InterruptedException e1) {
                System.out.println(e1);
            }
        }
        if (e.getSource() == bttacgia) {
            OffPageQLSACH(true);
            OffBTBgSelected();
            bttacgia.setBackground(Color.green);
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource() == tblQLTACGIA) {
            int i = tblQLTACGIA.getSelectedRow();
            if (i >= 0) {
                TACGIA tacgia = new TACGIA();
                tacgia = QLTACGIABUS.dstacgia.get(i);
                txMatacgia.setText(tacgia.getMaTacGia());
                txTentacgia.setText(tacgia.getTenTacGia());
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
        if (e.getSource() == txMatacgia) {
            txMatacgia.setToolTipText("Gợi ý: TG001");
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    public void setInput() {
        // label Matacgia
        lbMatacgia = new JLabel("Mã tác giả:");
        lbMatacgia.setFont(new Font("Arial", Font.BOLD, 20));
        lbMatacgia.setBounds(120, 0, 150, 100);
        // label Tentacgia
        lbTentacgia = new JLabel("Tên tác giả:");
        lbTentacgia.setFont(new Font("Arial", Font.BOLD, 20));
        lbTentacgia.setBounds(120, 50, 150, 100);

        // labelLCTK
        lbLCTK = new JLabel("Lựa chọn khóa tìm kiếm:");
        lbLCTK.setFont(new Font("Arial", Font.BOLD, 20));
        lbLCTK.setBounds(10, 50, 250, 100);

        // labelTuKhoaTK
        lbTuKhoaTK = new JLabel("Nhập từ khóa tìm kiếm:");
        lbTuKhoaTK.setFont(new Font("Arial", Font.BOLD, 20));
        lbTuKhoaTK.setBounds(10, 100, 250, 100);

        // JTextField Mã tác giả
        txMatacgia = new JTextField();
        txMatacgia.setBounds(270, 30, 200, 35);
        txMatacgia.setFont(new Font("Arial", Font.PLAIN, 15));
        txMatacgia.addMouseListener(this);
        // JTextField Tên tác giả
        txTentacgia = new JTextField();
        txTentacgia.setBounds(270, 80, 200, 35);
        txTentacgia.setFont(new Font("Arial", Font.PLAIN, 15));

        txTentacgia.addMouseListener(this);
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
        String[] dsKhoaTK = { "", "Mã tác giả", "Tên tác giả"};
        comboBoxDSKhoaTK = new JComboBox<>(dsKhoaTK);
        comboBoxDSKhoaTK.setFont(new Font("Arial", Font.BOLD, 13));
        comboBoxDSKhoaTK.setBounds(260, 85, 120, 35);
        comboBoxDSKhoaTK.addActionListener(this);

        btDoc.setVisible(false);
        lbLCTK.setVisible(false);
        lbTuKhoaTK.setVisible(false);
        comboBoxDSKhoaTK.setVisible(false);
        txKhoaTK.setVisible(false);

        pnNhapTTTacgia.add(lbMatacgia);
        pnNhapTTTacgia.add(lbTentacgia);

        pnNhapTTTacgia.add(txMatacgia);
        pnNhapTTTacgia.add(txTentacgia);

        pnNhapTTTacgia.add(btDoc);
        pnNhapTTTacgia.add(btThem);
        pnNhapTTTacgia.add(btSua);
        pnNhapTTTacgia.add(btXoa);
        pnNhapTTTacgia.add(btHoanTac);

    }

    public void setMenu() {
        // Set menu side left
        ImageIcon iconHome = new ImageIcon("images\\home.png");
        ImageIcon iconMenu = new ImageIcon("images\\menu.png");
        ImageIcon iconauthor = new ImageIcon("images\\user-icon.png");
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

        bttacgia = new JButton("Thông tin tác giả");
        bttacgia.setFont(new Font("Arial", Font.BOLD, 20));
        bttacgia.setBackground(Color.green);
        bttacgia.setIcon(iconauthor);
        bttacgia.setHorizontalAlignment(SwingConstants.LEFT);
        bttacgia.setBorder(BorderFactory.createEmptyBorder());
        bttacgia.addActionListener(this);

        btMenuTimKiem = new JButton("Tìm kiếm tác giả");
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
        pnMenu.add(bttacgia);
        pnMenu.add(btMenuTimKiem);
        pnMenu.add(btDangXuat);
        pnMenu.add(btThoat);
    }

    public void setTableTacGia() {
        // label TTTACGIA
        lbTTTacgia = new JLabel("THÔNG TIN TÁC GIẢ");
        lbTTTacgia.setFont(new Font("Arial", Font.BOLD, 30));
        lbTTTacgia.setHorizontalAlignment(SwingConstants.CENTER);
        lbTTTacgia.setVerticalAlignment(SwingConstants.TOP);
        // labelKQTK
        lbKQTK = new JLabel();
        lbKQTK.setHorizontalAlignment(SwingConstants.CENTER);
        lbKQTK.setVerticalAlignment(SwingConstants.TOP);
        // ----set up table----
        tblQLTACGIA = new JTable();
        JScrollPane pane = new JScrollPane(tblQLTACGIA);
        pane.setAutoscrolls(true);
        tblQLTACGIA.setRowHeight(20);
        tblQLTACGIA.setFont(new Font(null, 0, 13));
        tblQLTACGIA.setBackground(Color.LIGHT_GRAY);
        tblQLTACGIA.addMouseListener(this);
        tblQLTACGIA.setDefaultEditor(Object.class, null);

        this.add(pnTTTacgia);
        pnTTTacgia.add(lbTTTacgia);
        pnTTTacgia.add(lbKQTK);
        pnTTTacgia.add(pane);
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

    public void ShowOnTable(TACGIA tacgia) {
        Vector<String> row = new Vector<String>();
        row.add(tacgia.getMaTacGia());
        row.add(tacgia.getTenTacGia());
        model.addRow(row);
    }

    public void getInfoTextField(TACGIA tacgia) {
        tacgia.setMaTacGia(txMatacgia.getText());
        tacgia.setTenTacGia(txTentacgia.getText());
    }

    public void getDatabase() {
        try {
            QLTACGIABUS qltacgiabus = new QLTACGIABUS();
            if (QLTACGIABUS.dstacgia == null)
                try {
                    qltacgiabus.docdstacgia();
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            // set up header(column)
            header = new Vector<String>();
            header.add("Mã tác giả");
            header.add("Tên tác giả");
            model = new DefaultTableModel(header, 0);
            for (TACGIA tacgia : QLTACGIABUS.dstacgia) {
                ShowOnTable(tacgia);
                tblQLTACGIA.setModel(model);
            }
        } catch (Exception e1) {
            System.out.println(e1);
        }
    }

    public void OffBTBgSelected() {
        bttacgia.setBackground(Color.lightGray);
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
        if (lbTKMatacgia == null) {
            lbTKMatacgia = new JLabel("Mã tác giả:");
            lbTKMatacgia.setFont(new Font("Arial", Font.BOLD, 20));
            lbTKMatacgia.setBounds(10, 170, 100, 50);
        }
        if (lbTKMatacgia != null) {
            lbTKMatacgia.setVisible(true);
        }
        pnTimKiem.add(lbTKMatacgia);
    }

    public void OffPageQLSACH(Boolean x) {
        pnNhapTTTacgia.setVisible(x);
        pnShowAll.setVisible(x);
        pnTimKiem.setVisible(x);
        pnTTTacgia.setVisible(x);
    }
}

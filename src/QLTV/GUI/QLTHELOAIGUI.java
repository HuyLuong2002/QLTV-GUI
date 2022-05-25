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
import QLTV.BUS.QLTHELOAIBUS;
import QLTV.DTO.THELOAI;

public class QLTHELOAIGUI extends JFrame implements ActionListener, MouseListener {
    JPanel pnTTTheLoai, pnNhapTTTL, pnShowAll, pnMenu, pnTimKiem;
    JLabel lbHome, lbTTTL, lbMaTL, lbTenTL, lbSLTL, lbLCTK, lbTuKhoaTK;
    JLabel lbTKMaTL;
    JTextField txMaTL, txTenTL, txSLTL, txKhoaTK;
    JButton btThem, bttheloai, btSua, btXoa, btHoanTac, btMenuTimKiem, btShowAll;
    JButton btMenu, btSapXep, btDangXuat, btThoat;
    JButton btSearch;

    JComboBox<String> comboBoxDSKhoaTK;
    JScrollPane pane;

    JTable tblQLTHELOAI;
    DefaultTableModel model;
    Vector<String> header;

    public QLTHELOAIGUI() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(1400, 800);
        this.setTitle("Quản lý thông tin thể loại");
        this.setLayout(null);
        this.setLocationRelativeTo(null);

        pnTTTheLoai = new JPanel();
        pnNhapTTTL = new JPanel();
        pnShowAll = new JPanel();
        pnMenu = new JPanel();
        pnTimKiem = new JPanel();
        pnTTTheLoai.setLayout(new GridLayout(2, 1, 0, -300));
        pnTTTheLoai.setBounds(242, 0, 1142, 400);
        pnTTTheLoai.setBackground(MyColor.ColorBlue);

        pnShowAll.setLayout(null);
        pnShowAll.setBounds(242, 402, 1142, 30);
        pnShowAll.setBackground(MyColor.ColorBlue);

        pnNhapTTTL.setLayout(null);
        pnNhapTTTL.setBounds(242, 415, 720, 550);
        pnNhapTTTL.setBackground(MyColor.ColorBlue);

        pnMenu.setLayout(new GridLayout(9, 1));
        pnMenu.setBounds(0, 178, 240, 590);
        pnMenu.setBackground(MyColor.ColorOcean);

        pnTimKiem.setLayout(null);
        pnTimKiem.setBounds(962, 430, 423, 332);
        pnTimKiem.setBackground(MyColor.ColorBlue);

        // add components
        this.add(pnMenu);
        this.add(pnShowAll);
        this.add(pnNhapTTTL);
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
                THELOAI theloai = new THELOAI();
                getInfoTextField(theloai);
                // Truy cập vào bus
                QLTHELOAIBUS qltheloaibus = new QLTHELOAIBUS();
                int kiemtra = -1;
                try {
                    kiemtra = qltheloaibus.them(theloai);
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
                if (kiemtra == 0) {
                    // Đưa dữ liệu lên table
                    header = new Vector<String>();
                    header.add("Mã THể Loại");
                    header.add("Tên Thể Loại");
                    header.add("Số Lượng Thể Loại");
                    if (model.getRowCount() == 0) {
                        model = new DefaultTableModel(header, 0);
                    }
                    ShowOnTable(theloai);
                    tblQLTHELOAI.setModel(model);
                }
            } catch (Exception e1) {
                System.out.println(e1);
            }
        } else if (e.getSource() == btSua) {
            int i = tblQLTHELOAI.getSelectedRow();
            int kt = -1;
            if (i >= 0) {
                THELOAI theloai = new THELOAI();
                THELOAI matheloaicu = QLTHELOAIBUS.dstheloai.get(i);
                getInfoTextField(theloai);
                try {
                    QLTHELOAIBUS qltheloaibus = new QLTHELOAIBUS();
                    kt = qltheloaibus.sua(theloai, matheloaicu, i);
                } catch (Exception e1) {
                    System.out.println(e1);
                }
                if (kt == 0) {
                    model.setValueAt(theloai.getMaTL(), i, 0);
                    model.setValueAt(theloai.getTenTL(), i, 1);
                    model.setValueAt(theloai.getSLTL(), i, 2);
                    tblQLTHELOAI.setModel(model);
                }
            }
        } else if (e.getSource() == btXoa) {
            int XacNhanXoa = JOptionPane.showConfirmDialog(null, "Bạn có chắc chắn muốn xóa không ?", "Thông báo",
                    JOptionPane.YES_NO_OPTION);
            if (XacNhanXoa == 0) {
                String matheloai = txMaTL.getText();
                int i = tblQLTHELOAI.getSelectedRow();
                int kt = -1;
                if (i >= 0) {
                    try {
                        // Truy cập xuống BUS
                        THELOAI theloaiold = QLTHELOAIBUS.dstheloai.get(i);
                        QLTHELOAIBUS.htXoa.add(theloaiold);
                        QLTHELOAIBUS qltheloaibus = new QLTHELOAIBUS();
                        kt = qltheloaibus.xoa(matheloai, i);
                        // Quay dề GUI
                    } catch (Exception e1) {
                        System.out.println(e1);
                    }
                    if(kt == 0){
                        model.removeRow(i);
                        tblQLTHELOAI.setModel(model);
                    }
                }
            }
        } else if (e.getSource() == btHoanTac) {
            int ktHT = 0;
            if (QLTHELOAIBUS.htXoa.size() == 0) {
                JOptionPane.showMessageDialog(null, "Dữ liệu hoàn tác rỗng", "Lỗi",
                        JOptionPane.ERROR_MESSAGE);
            } else {
                for (THELOAI theloai : QLTHELOAIBUS.htXoa) {
                    QLTHELOAIBUS qltheloaibus = new QLTHELOAIBUS();
                    int kiemtra = -1;
                    try {
                        kiemtra = qltheloaibus.hoantacXoa(theloai);
                    } catch (Exception e1) {
                        e1.printStackTrace();
                    }
                    if (kiemtra == 0) {
                        // Đưa dữ liệu lên table
                        header = new Vector<String>();
                        header.add("Mã Thể Loại");
                        header.add("Tên Thể Loại");
                        header.add("Số Lượng Thể Loại");
                        if (model.getRowCount() == 0) {
                            model = new DefaultTableModel(header, 0);
                        }
                        ShowOnTable(theloai);
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
                tblQLTHELOAI.setModel(model);
            }
        } else if (e.getSource() == btMenuTimKiem) { // Của button Tìm kiếm thể loại, để hiện thị
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
                QLTHELOAIBUS qltheloaibus = new QLTHELOAIBUS();
                if (vtkey == 1) {
                    THELOAI kq = qltheloaibus.timTheoMa(tukhoa);
                    model.setRowCount(0);
                    if (kq == null) {
                        JOptionPane.showMessageDialog(null, "Không tìm thấy dữ liệu", "Lỗi", JOptionPane.ERROR_MESSAGE);
                    } else {
                        ShowOnTable(kq);
                        tblQLTHELOAI.setModel(model);
                    }
                } else if (vtkey == 2) {
                    ArrayList<THELOAI> kq = qltheloaibus.timTheoTen(tukhoa);
                    model.setRowCount(0);
                    if (kq.size() == 0) {
                        JOptionPane.showMessageDialog(null, "Không tìm thấy dữ liệu", "Lỗi", JOptionPane.ERROR_MESSAGE);
                    } else {
                        for (THELOAI theloai : kq) {
                            ShowOnTable(theloai);
                        }
                        tblQLTHELOAI.setModel(model);
                    }
                }
            }
        } else if (e.getSource() == btShowAll) {
            model.setRowCount(0);
            for (THELOAI theloai : QLTHELOAIBUS.dstheloai) {
                ShowOnTable(theloai);
            }
            tblQLTHELOAI.setModel(model);
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
        if (e.getSource() == bttheloai) {
            OffPageQLSACH(true);
            OffBTBgSelected();
            bttheloai.setBackground(MyColor.ColorLightBlue);
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
        QLTHELOAIBUS.dstheloai.sort((o1, o2) -> o1.getMaTL().compareTo(o2.getTenTL()));
        model.setRowCount(0);
        for (THELOAI theloai : QLTHELOAIBUS.dstheloai) {
            ShowOnTable(theloai);
        }
        tblQLTHELOAI.setModel(model);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource() == tblQLTHELOAI) {
            int i = tblQLTHELOAI.getSelectedRow();
            if (i >= 0) {
                THELOAI theloai = new THELOAI();
                theloai = QLTHELOAIBUS.dstheloai.get(i);
                txMaTL.setText(theloai.getMaTL().replaceAll("\\s+", " ").trim());
                txTenTL.setText(theloai.getTenTL().replaceAll("\\s+", " ").trim());
                txSLTL.setText(String.valueOf(theloai.getSLTL()));
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
        if (e.getSource() == txMaTL) {
            txMaTL.setToolTipText("Gợi ý: TL001");
        }
        else if (e.getSource() == txTenTL) {
            txTenTL.setToolTipText("Gợi ý: Kinh tế");
        }
        else if (e.getSource() == txSLTL) {
            txSLTL.setToolTipText("Gợi ý: 5");
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    public void setInput() {
        // label Matheloai
        lbMaTL = new JLabel("Mã Thể Loại:");
        lbMaTL.setFont(new Font("Arial", Font.BOLD, 20));
        lbMaTL.setBounds(20, 0, 140, 80);
        // label Tentheloai
        lbTenTL = new JLabel("Tên Thể Loại:");
        lbTenTL.setFont(new Font("Arial", Font.BOLD, 20));
        lbTenTL.setBounds(20, 40, 140, 80);
        // label SLTL
        lbSLTL = new JLabel("SLTL:");
        lbSLTL.setFont(new Font("Arial", Font.BOLD, 20));
        lbSLTL.setBounds(20, 80, 140, 80);

        // labelLCTK
        lbLCTK = new JLabel("Lựa chọn khóa tìm kiếm:");
        lbLCTK.setFont(new Font("Arial", Font.BOLD, 20));
        lbLCTK.setBounds(10, 50, 260, 80);

        // labelTuKhoaTK
        lbTuKhoaTK = new JLabel("Nhập từ khóa tìm kiếm:");
        lbTuKhoaTK.setFont(new Font("Arial", Font.BOLD, 20));
        lbTuKhoaTK.setBounds(10, 100, 260, 80);

        // JTextField Mã Thể Loại
        txMaTL = new JTextField();
        txMaTL.setBounds(160, 25, 180, 30);
        txMaTL.setFont(new Font("Arial", Font.PLAIN, 15));
        txMaTL.addMouseListener(this);
        // JTextField Tên Thể Loại
        txTenTL = new JTextField();
        txTenTL.setBounds(160, 65, 180, 30);
        txTenTL.setFont(new Font("Arial", Font.PLAIN, 15));
        txTenTL.addMouseListener(this);
        // JTextField SLTL
        txSLTL = new JTextField();
        txSLTL.setBounds(160, 105, 180, 30);
        txSLTL.setFont(new Font("Arial", Font.PLAIN, 15));
        txSLTL.addMouseListener(this);
        // JTextField Khóa tìm kiếm
        txKhoaTK = new JTextField();
        txKhoaTK.setFont(new Font("Arial", Font.PLAIN, 15));
        txKhoaTK.setBounds(245, 125, 160, 30);
        txKhoaTK.addMouseListener(this);

        // JbuttonThem
        btThem = new JButton("Thêm");
        btThem.setFont(new Font("Arial", Font.BOLD, 15));
        btThem.setBounds(20, 160, 80, 30);
        btThem.setBackground(MyColor.ColorButton);
        btThem.setBorder(new RoundedBorder(10));
        btThem.addActionListener(this);
        // JbuttonSua
        btSua = new JButton("Sửa");
        btSua.setFont(new Font("Arial", Font.BOLD, 15));
        btSua.setBounds(120, 160, 80, 30);
        btSua.setBackground(MyColor.ColorButton);
        btSua.setBorder(new RoundedBorder(10));
        btSua.addActionListener(this);
        // JbuttonXoa
        btXoa = new JButton("Xóa");
        btXoa.setFont(new Font("Arial", Font.BOLD, 15));
        btXoa.setBounds(220, 160, 80, 30);
        btXoa.setBackground(MyColor.ColorButton);
        btXoa.setBorder(new RoundedBorder(10));
        btXoa.addActionListener(this);
        // JbuttonHoanTac
        btHoanTac = new JButton("Hoàn tác");
        btHoanTac.setFont(new Font("Arial", Font.BOLD, 15));
        btHoanTac.setBounds(320, 160, 90, 30);
        btHoanTac.setBackground(MyColor.ColorButton);
        btHoanTac.setBorder(new RoundedBorder(10));
        btHoanTac.addActionListener(this);

        // set up ComboBox
        String[] dsKhoaTK = { "", "Mã Thể Loại", "Tên Thể Loại" };
        comboBoxDSKhoaTK = new JComboBox<>(dsKhoaTK);
        comboBoxDSKhoaTK.setFont(new Font("Arial", Font.BOLD, 13));
        comboBoxDSKhoaTK.setBounds(245, 75, 100, 30);
        comboBoxDSKhoaTK.addActionListener(this);

        lbLCTK.setVisible(false);
        lbTuKhoaTK.setVisible(false);
        comboBoxDSKhoaTK.setVisible(false);
        txKhoaTK.setVisible(false);

        pnNhapTTTL.add(lbMaTL);
        pnNhapTTTL.add(lbTenTL);
        pnNhapTTTL.add(lbSLTL);

        pnNhapTTTL.add(txMaTL);
        pnNhapTTTL.add(txTenTL);
        pnNhapTTTL.add(txSLTL);

        pnNhapTTTL.add(btThem);
        pnNhapTTTL.add(btSua);
        pnNhapTTTL.add(btXoa);
        pnNhapTTTL.add(btHoanTac);

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
        btMenu.setBackground(MyColor.ColorOcean);
        btMenu.setIcon(iconMenu);
        btMenu.setHorizontalAlignment(SwingConstants.LEFT);
        btMenu.setBorder(BorderFactory.createEmptyBorder());
        btMenu.addActionListener(this);

        bttheloai = new JButton("Thông tin thể loại");
        bttheloai.setFont(new Font("Arial", Font.BOLD, 20));
        bttheloai.setBackground(MyColor.ColorLightBlue);
        bttheloai.setIcon(iconPubCompany);
        bttheloai.setHorizontalAlignment(SwingConstants.LEFT);
        bttheloai.setBorder(BorderFactory.createEmptyBorder());
        bttheloai.addActionListener(this);

        btMenuTimKiem = new JButton("Tìm kiếm thể loại");
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
        pnMenu.add(bttheloai);
        pnMenu.add(btMenuTimKiem);
        pnMenu.add(btDangXuat);
        pnMenu.add(btThoat);
    }

    public void setTableNXB() {
        // label TTNXB
        lbTTTL = new JLabel("THÔNG TIN THỂ LOẠI");
        lbTTTL.setFont(new Font("Arial", Font.BOLD, 30));
        lbTTTL.setHorizontalAlignment(SwingConstants.CENTER);
        lbTTTL.setVerticalAlignment(SwingConstants.TOP);
        // ----set up table----
        tblQLTHELOAI = new JTable();
        pane = new JScrollPane(tblQLTHELOAI);
        pane.setAutoscrolls(true);
        tblQLTHELOAI.setRowHeight(30);
        tblQLTHELOAI.setFont(new Font(null, 0, 13));
        tblQLTHELOAI.setBackground(MyColor.ColorLightGray);
        tblQLTHELOAI.getTableHeader().setBackground(MyColor.ColorSilver);
        tblQLTHELOAI.addMouseListener(this);
        tblQLTHELOAI.setDefaultEditor(Object.class, null);
        tblQLTHELOAI.setSelectionBackground(MyColor.Color);

        this.add(pnTTTheLoai);
        pnTTTheLoai.add(lbTTTL);
        pnTTTheLoai.add(pane);
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

    public void ShowOnTable(THELOAI theloai) {
        Vector<String> row = new Vector<String>();
        row.add(theloai.getMaTL().replaceAll("\\s+", "").trim());
        row.add(theloai.getTenTL().replaceAll("\\s+", " ").trim());
        row.add(String.valueOf(theloai.getSLTL()));
        model.addRow(row);
    }

    public void getInfoTextField(THELOAI theloai) {
        theloai.setMaTL(txMaTL.getText().replaceAll("\\s+", "").trim());
        theloai.setTenTL(txTenTL.getText().replaceAll("\\s+", " ").trim());
        theloai.setSLTL(Integer.valueOf(txSLTL.getText()));
    }

    public void getDatabase() {
        try {
            QLTHELOAIBUS qltheloaibus = new QLTHELOAIBUS();
            if (QLTHELOAIBUS.dstheloai == null)
                try {
                    qltheloaibus.docdsnxb();
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            // set up header(column)
            header = new Vector<String>();
            header.add("Mã Thể Loại");
            header.add("Tên Thể Loại");
            header.add("Số Lượng Thể Loại");
            model = new DefaultTableModel(header, 0);
            for (THELOAI theloai : QLTHELOAIBUS.dstheloai) {
                ShowOnTable(theloai);
            }
            tblQLTHELOAI.setModel(model);
        } catch (Exception e1) {
            System.out.println(e1);
        }
    }

    public void OffBTBgSelected() {
        bttheloai.setBackground(MyColor.ColorOcean);
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
        if (lbTKMaTL == null) {
            lbTKMaTL = new JLabel("Mã Thể Loại:");
            lbTKMaTL.setFont(new Font("Arial", Font.BOLD, 20));
            lbTKMaTL.setBounds(10, 170, 100, 50);
        }
        if (lbTKMaTL != null) {
            lbTKMaTL.setVisible(true);
        }
        pnTimKiem.add(lbTKMaTL);
    }

    public void OffPageQLSACH(Boolean x) {
        pnNhapTTTL.setVisible(x);
        pnShowAll.setVisible(x);
        pnTimKiem.setVisible(x);
        pnTTTheLoai.setVisible(x);
    }
}

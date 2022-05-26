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
import QLTV.BUS.QLTACGIABUS;
import QLTV.DTO.TACGIA;

public class QLTACGIAGUI extends JFrame implements ActionListener, MouseListener {
    JPanel pnTTTacgia, pnNhapTTTacgia, pnShowAll, pnMenu, pnTimKiem, pnLibrary;
    JLabel lbHome, lbTTTacgia, lbMatacgia, lbTentacgia, lbLCTK, lbTuKhoaTK, lbLibrary;
    JLabel lbTKMatacgia;
    JTextField txMatacgia, txTentacgia, txKhoaTK;
    JButton btDoc, btThem, btSua, btXoa, btHoanTac, btMenuTimKiem, btShowAll, btThongKe;
    JButton btMenu, btSapXep, bttacgia, btDangXuat, btThoat;
    JButton btSearch;

    JComboBox<String> comboBoxDSKhoaTK;
    JScrollPane pane;

    JTable tblQLTACGIA;
    DefaultTableModel model;
    Vector<String> header;
    private ImageIcon imgIconHP;

    public QLTACGIAGUI() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(1400, 800);
        this.setTitle("Quản lý thông tin tác giả");
        this.setLayout(null);
        this.setLocationRelativeTo(null);
        imgIconHP = new ImageIcon("images\\app_logo.png");
        this.setIconImage(imgIconHP.getImage());

        pnTTTacgia = new JPanel();
        pnNhapTTTacgia = new JPanel();
        pnShowAll = new JPanel();
        pnMenu = new JPanel();
        pnTimKiem = new JPanel();
        pnLibrary = new JPanel();

        pnTTTacgia.setLayout(new GridLayout(2, 1, 0, -300));
        pnTTTacgia.setBounds(242, 0, 1142, 400);
        pnTTTacgia.setBackground(MyColor.ColorBlue);

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

        pnNhapTTTacgia.setLayout(null);
        pnNhapTTTacgia.setBounds(242, 415, 720, 550);
        pnNhapTTTacgia.setBackground(MyColor.ColorBlue);

        pnMenu.setLayout(new GridLayout(9, 1));
        pnMenu.setBounds(0, 180, 240, 590);
        pnMenu.setBackground(MyColor.ColorOcean);

        pnTimKiem.setLayout(null);
        pnTimKiem.setBounds(962, 430, 423, 332);
        pnTimKiem.setBackground(MyColor.ColorBlue);

        // add components
        this.add(pnMenu);
        this.add(pnShowAll);
        this.add(pnNhapTTTacgia);
        this.add(pnTimKiem);
        this.add(pnLibrary);

        setTableTacGia();
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
                TACGIA tacgia = new TACGIA();
                getInfoTextField(tacgia);
                // Truy cập vào bus
                QLTACGIABUS QLTACGIABUS = new QLTACGIABUS();
                int kiemtra = -1;
                try {
                    kiemtra = QLTACGIABUS.them(tacgia);
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
                if (kiemtra == 0) {
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
            int kt = 0;
            if (i >= 0) {
                TACGIA tacgia = new TACGIA();
                TACGIA matacgiacu = QLTACGIABUS.dstacgia.get(i);
                getInfoTextField(tacgia);
                try {
                    QLTACGIABUS QLTACGIABUS = new QLTACGIABUS();
                    kt = QLTACGIABUS.sua(tacgia, matacgiacu, i);
                } catch (Exception e1) {
                    System.out.println(e1);
                }
                if (kt == 0) {
                    model.setValueAt(tacgia.getMaTacGia(), i, 0);
                    model.setValueAt(tacgia.getTenTacGia(), i, 1);
                    tblQLTACGIA.setModel(model);
                }
            }
        } else if (e.getSource() == btXoa) {
            int XacNhanXoa = JOptionPane.showConfirmDialog(null, "Bạn có chắc chắn muốn xóa không ?", "Thông báo",
                    JOptionPane.YES_NO_OPTION);
            if (XacNhanXoa == 0) {
                String matacgia = txMatacgia.getText();
                int i = tblQLTACGIA.getSelectedRow();
                int kt = -1;
                if (i >= 0) {
                    try {
                        // Truy cập xuống BUS
                        TACGIA tacgiaOld = QLTACGIABUS.dstacgia.get(i);
                        QLTACGIABUS.htXoa.add(tacgiaOld);
                        QLTACGIABUS QLTACGIABUS = new QLTACGIABUS();
                        kt = QLTACGIABUS.xoa(matacgia, i);
                        // Quay dề GUI
                    } catch (Exception e1) {
                        System.out.println(e1);
                    }
                    if(kt == 0){
                        model.removeRow(i);
                        tblQLTACGIA.setModel(model);
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
                    int kiemtra = -1;
                    try {
                        kiemtra = QLTACGIABUS.hoantacXoa(tacgia);
                    } catch (Exception e1) {
                        e1.printStackTrace();
                    }
                    if (kiemtra == 0) {
                        // Đưa dữ liệu lên table
                        header = new Vector<String>();
                        header.add("Mã tác giả");
                        header.add("Tên tác giả");
                        if (model.getRowCount() == 0) {
                            model = new DefaultTableModel(header, 0);
                        }
                        ShowOnTable(tacgia);
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
                tblQLTACGIA.setModel(model);
            }
        } else if (e.getSource() == btMenuTimKiem) { // Của button Tìm kiếm tác giả, để hiện thị
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
                QLTACGIABUS QLTACGIABUS = new QLTACGIABUS();
                if (vtkey == 1) {
                    TACGIA kq = QLTACGIABUS.timTheoMa(tukhoa);
                    model.setRowCount(0);
                    if (kq == null) {
                        JOptionPane.showMessageDialog(null, "Không tìm thấy dữ liệu", "Lỗi", JOptionPane.ERROR_MESSAGE);
                    } else {
                        ShowOnTable(kq);
                        tblQLTACGIA.setModel(model);
                    }
                } else if (vtkey == 2) {
                    ArrayList<TACGIA> kq = QLTACGIABUS.timTheoTen(tukhoa);
                    model.setRowCount(0);
                    if (kq.size() == 0) {
                        JOptionPane.showMessageDialog(null, "Không tìm thấy dữ liệu", "Lỗi", JOptionPane.ERROR_MESSAGE);
                    } else {
                        for (TACGIA tacgia : kq) {
                            ShowOnTable(tacgia);
                        }
                        tblQLTACGIA.setModel(model);
                    }
                }
            }
        } else if (e.getSource() == btShowAll) {
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
        if (e.getSource() == bttacgia) {
            OffPageQLSACH(true);
            OffBTBgSelected();
            bttacgia.setBackground(MyColor.ColorLightBlue);
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
        QLTACGIABUS.dstacgia.sort(((o1, o2) -> o1.getTenTacGia().compareTo(o2.getTenTacGia())));
        model.setRowCount(0);
        for (TACGIA tacgia : QLTACGIABUS.dstacgia) {
            ShowOnTable(tacgia);
        }
        tblQLTACGIA.setModel(model);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource() == tblQLTACGIA) {
            int i = tblQLTACGIA.getSelectedRow();
            if (i >= 0) {
                TACGIA tacgia = new TACGIA();
                tacgia = QLTACGIABUS.dstacgia.get(i);
                txMatacgia.setText(tacgia.getMaTacGia().replaceAll("\\s+", " ").trim());
                txTentacgia.setText(tacgia.getTenTacGia().replaceAll("\\s+", " ").trim());
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
        if(e.getSource() == txTentacgia)
            txTentacgia.setToolTipText("Gợi ý: Nguyễn Văn A");
    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    public void setInput() {
        // label Matacgia
        lbMatacgia = new JLabel("Mã tác giả:");
        lbMatacgia.setFont(new Font("Arial", Font.BOLD, 20));
        lbMatacgia.setBounds(20, 0, 130, 80);
        // label Tentacgia
        lbTentacgia = new JLabel("Tên tác giả:");
        lbTentacgia.setFont(new Font("Arial", Font.BOLD, 20));
        lbTentacgia.setBounds(20, 40, 130, 80);

        // labelLCTK
        lbLCTK = new JLabel("Lựa chọn khóa tìm kiếm:");
        lbLCTK.setFont(new Font("Arial", Font.BOLD, 20));
        lbLCTK.setBounds(10, 50, 250, 80);

        // labelTuKhoaTK
        lbTuKhoaTK = new JLabel("Nhập từ khóa tìm kiếm:");
        lbTuKhoaTK.setFont(new Font("Arial", Font.BOLD, 20));
        lbTuKhoaTK.setBounds(10, 100, 230, 80);

        // JTextField Mã tác giả
        txMatacgia = new JTextField();
        txMatacgia.setBounds(160, 25, 180, 30);
        txMatacgia.setFont(new Font("Arial", Font.PLAIN, 15));
        txMatacgia.addMouseListener(this);
        // JTextField Tên tác giả
        txTentacgia = new JTextField();
        txTentacgia.setBounds(160, 65, 180, 30);
        txTentacgia.setFont(new Font("Arial", Font.PLAIN, 15));

        txTentacgia.addMouseListener(this);
        // JTextField Khóa tìm kiếm
        txKhoaTK = new JTextField();
        txKhoaTK.setFont(new Font("Arial", Font.PLAIN, 15));
        txKhoaTK.setBounds(250, 125, 160, 30);
        txKhoaTK.addMouseListener(this);

        // JbuttonThem
        btThem = new JButton("Thêm");
        btThem.setFont(new Font("Arial", Font.BOLD, 15));
        btThem.setBounds(10, 105, 80, 30);
        btThem.setBackground(MyColor.ColorButton);
        btThem.setBorder(new RoundedBorder(10));
        btThem.addActionListener(this);
        // JbuttonSua
        btSua = new JButton("Sửa");
        btSua.setFont(new Font("Arial", Font.BOLD, 15));
        btSua.setBounds(110, 105, 80, 30);
        btSua.setBackground(MyColor.ColorButton);
        btSua.setBorder(new RoundedBorder(10));
        btSua.addActionListener(this);
        // JbuttonXoa
        btXoa = new JButton("Xóa");
        btXoa.setFont(new Font("Arial", Font.BOLD, 15));
        btXoa.setBounds(210, 105, 80, 30);
        btXoa.setBackground(MyColor.ColorButton);
        btXoa.setBorder(new RoundedBorder(10));
        btXoa.addActionListener(this);
        // JbuttonHoanTac
        btHoanTac = new JButton("Hoàn tác");
        btHoanTac.setFont(new Font("Arial", Font.BOLD, 15));
        btHoanTac.setBounds(310, 105, 90, 30);
        btHoanTac.setBackground(MyColor.ColorButton);
        btHoanTac.setBorder(new RoundedBorder(10));
        btHoanTac.addActionListener(this);

        // set up ComboBox
        String[] dsKhoaTK = { "", "Mã tác giả", "Tên tác giả" };
        comboBoxDSKhoaTK = new JComboBox<>(dsKhoaTK);
        comboBoxDSKhoaTK.setFont(new Font("Arial", Font.BOLD, 13));
        comboBoxDSKhoaTK.setBounds(250, 75, 100, 30);
        comboBoxDSKhoaTK.addActionListener(this);

        lbLCTK.setVisible(false);
        lbTuKhoaTK.setVisible(false);
        comboBoxDSKhoaTK.setVisible(false);
        txKhoaTK.setVisible(false);

        pnNhapTTTacgia.add(lbMatacgia);
        pnNhapTTTacgia.add(lbTentacgia);

        pnNhapTTTacgia.add(txMatacgia);
        pnNhapTTTacgia.add(txTentacgia);

        pnNhapTTTacgia.add(btThem);
        pnNhapTTTacgia.add(btSua);
        pnNhapTTTacgia.add(btXoa);
        pnNhapTTTacgia.add(btHoanTac);

    }

    public void setMenu() {
        // Set menu side left
        ImageIcon iconMenu = new ImageIcon("images\\menu.png");
        ImageIcon iconauthor = new ImageIcon("images\\user-icon.png");
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

        bttacgia = new JButton("Thông tin tác giả");
        bttacgia.setFont(new Font("Times new Roman", Font.BOLD, 20));
        bttacgia.setBackground(MyColor.ColorLightBlue);
        bttacgia.setIcon(iconauthor);
        bttacgia.setHorizontalAlignment(SwingConstants.LEFT);
        bttacgia.setBorder(BorderFactory.createEmptyBorder());
        bttacgia.addActionListener(this);

        btMenuTimKiem = new JButton("Tìm kiếm tác giả");
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
        // ----set up table----
        tblQLTACGIA = new JTable();
        pane = new JScrollPane(tblQLTACGIA);
        pane.setAutoscrolls(true);
        tblQLTACGIA.setRowHeight(30);
        tblQLTACGIA.setFont(new Font(null, 0, 13));
        tblQLTACGIA.setBackground(MyColor.ColorLightGray);
        tblQLTACGIA.addMouseListener(this);
        tblQLTACGIA.setDefaultEditor(Object.class, null);
        tblQLTACGIA.setSelectionBackground(MyColor.Color);
        tblQLTACGIA.getTableHeader().setBackground(MyColor.ColorSilver);

        this.add(pnTTTacgia);
        pnTTTacgia.add(lbTTTacgia);
        pnTTTacgia.add(pane);
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

    public void ShowOnTable(TACGIA tacgia) {
        Vector<String> row = new Vector<String>();
        row.add(tacgia.getMaTacGia().replaceAll("\\s+", " ").trim());
        row.add(tacgia.getTenTacGia().replaceAll("\\s+", " ").trim());
        model.addRow(row);
    }

    public void getInfoTextField(TACGIA tacgia) {
        tacgia.setMaTacGia(txMatacgia.getText().replaceAll("\\s+", " ").trim());
        tacgia.setTenTacGia(txTentacgia.getText().replaceAll("\\s+", " ").trim());
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
            }
            tblQLTACGIA.setModel(model);
        } catch (Exception e1) {
            System.out.println(e1);
        }
    }

    public void OffBTBgSelected() {
        bttacgia.setBackground(MyColor.ColorOcean);
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
            titleTK = BorderFactory.createTitledBorder(blackline, "Tìm kiếm");
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
        pnNhapTTTacgia.setVisible(x);
        pnShowAll.setVisible(x);
        pnTimKiem.setVisible(x);
        pnTTTacgia.setVisible(x);
    }
}

package QLTV.GUI;

import java.awt.Color;
// import java.awt.Dimension;
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
import MyCustom.RoundedBorder;
import QLTV.BUS.QLNHANVIENBUS;
import QLTV.DTO.NHANVIEN;

public class QLNHANVIENGUI extends JFrame implements ActionListener, MouseListener {
    JPanel pnTTnhanvien, pnNhapTTnhanvien, pnShowAll, pnMenu, pnTimKiem;
    JLabel lbHome, lbTTNhanVien, lbManhanvien, lbTennhanvien, lbChucvu, lbLuongCB, lbPhucap, lbHesoluong, lbSDT, lbEmail, lbLCTK, lbTuKhoaTK, lbKQTK;
    JLabel lbTKMaNV;
    JTextField txMaNV, txTenNV,  txChucvu, txLuongCB, txPhucap, txHesoluong, txSDT, txEmail,  txKhoaTK;
    JButton btThem, btNV, btSua, btXoa, btHoanTac, btSapXep, btMenuTimKiem, btShowAll, btThongKe;
    JButton btMenu, btNhanvien, btDangXuat, btThoat;
    JButton btSearch;

    JComboBox<String> comboBoxDSKhoaTK;

    JTable tblQLNHANVIEN;
    DefaultTableModel model;
    Vector<String> header;

    Color ColorOcean, ColorPurple;

    public QLNHANVIENGUI() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // this.setSize(1400,800);
        this.setTitle("Quản lý thông tin nhân viên");
        this.setLayout(null);
        this.setLocationRelativeTo(null);

        pnTTnhanvien = new JPanel();
        pnNhapTTnhanvien = new JPanel();
        pnShowAll = new JPanel();
        pnMenu = new JPanel();
        pnTimKiem = new JPanel();
        pnTTnhanvien.setLayout(new GridLayout(3, 1, 0, -300));
        pnTTnhanvien.setBounds(242, 0, 1142, 400);
        pnShowAll.setLayout(null);
        pnShowAll.setBounds(242, 402, 1142, 30);
        pnNhapTTnhanvien.setLayout(null);
        pnNhapTTnhanvien.setBounds(242, 415, 720, 550);
        pnMenu.setLayout(new GridLayout(9, 1));
        pnMenu.setBounds(0,178,240,590);
        pnTimKiem.setLayout(null);
        pnTimKiem.setBounds(970, 440, 410, 300);

        // add components
        this.add(pnMenu);
        this.add(pnShowAll);
        this.add(pnNhapTTnhanvien);
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
                NHANVIEN nhanvien = new NHANVIEN();
                getInfoTextField(nhanvien);
                // Truy cập vào bus
                QLNHANVIENBUS qlnhanvienbus = new QLNHANVIENBUS();
                int kiemtra = 0;
                try {
                    kiemtra = qlnhanvienbus.them(nhanvien);
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
                if (kiemtra == 1) {
                    // Đưa dữ liệu lên table
                    header = new Vector<String>();
                    header.add("Mã nhân viên");
                    header.add("Tên nhân viên");
                    header.add("Chức vụ");
                    header.add("Lương cơ bản");
                    header.add("Phụ cấp");
                    header.add("Hệ số lương");
                    header.add("SDT");
                    header.add("EMail");
                    if (model.getRowCount() == 0) {
                        model = new DefaultTableModel(header, 0);
                    }
                    ShowOnTable(nhanvien);
                    tblQLNHANVIEN.setModel(model);
                }
            } catch (Exception e1) {
                System.out.println(e1);
            }
        } else if (e.getSource() == btSua) {
            int i = tblQLNHANVIEN.getSelectedRow();

            if (i >= 0) {
                NHANVIEN nhanvien = new NHANVIEN();
                NHANVIEN manhanviencu = QLNHANVIENBUS.dsnhanvien.set(i, nhanvien);
                getInfoTextField(nhanvien);
                try {
                    QLNHANVIENBUS qlnhanvienbus = new QLNHANVIENBUS();
                    qlnhanvienbus.sua(nhanvien, manhanviencu, i);
                    model.setValueAt(nhanvien.getMaNV(), i, 0);
                    model.setValueAt(nhanvien.getTenNV(), i, 1);
                    model.setValueAt(nhanvien.getChucvu(), i, 2);
                    model.setValueAt(nhanvien.getLuongCB(), i, 3);
                    model.setValueAt(nhanvien.getPhucap(), i, 4);
                    model.setValueAt(nhanvien.getHesoluong(), i, 5);
                    model.setValueAt(nhanvien.getSDT(), i, 6);
                    model.setValueAt(nhanvien.getMail(), i, 7);
                    tblQLNHANVIEN.setModel(model);
                } catch (Exception e1) {
                    System.out.println(e1);
                }
            }
        } else if (e.getSource() == btXoa) {
            int XacNhanXoa = JOptionPane.showConfirmDialog(null, "Bạn có chắc chắn muốn xóa không ?", "Thông báo",
                    JOptionPane.YES_NO_OPTION);
            if (XacNhanXoa == 0) {
                String masach = txMaNV.getText();
                int i = tblQLNHANVIEN.getSelectedRow();
                if (i >= 0) {
                    try {
                        // Truy cập xuống BUS
                        NHANVIEN nhanviencu = QLNHANVIENBUS.dsnhanvien.get(i);
                        QLNHANVIENBUS.htXoa.add(nhanviencu);
                        QLNHANVIENBUS qlsachbus = new QLNHANVIENBUS();
                        qlsachbus.xoa(masach, i);
                        // Quay dề GUI
                        model.removeRow(i);
                        tblQLNHANVIEN.setModel(model);
                    } catch (Exception e1) {
                        System.out.println(e1);
                    }
                }
            }
        } else if (e.getSource() == btHoanTac) {
            int ktHT = 0;
            if (QLNHANVIENBUS.htXoa.size() == 0) {
                JOptionPane.showMessageDialog(null, "Dữ liệu hoàn tác rỗng", "Lỗi",
                        JOptionPane.ERROR_MESSAGE);
            } else {
                for (NHANVIEN nhanvien : QLNHANVIENBUS.htXoa) {
                    QLNHANVIENBUS qlnxbbus = new QLNHANVIENBUS();
                    int kiemtra = 0;
                    try {
                        kiemtra = qlnxbbus.hoantacXoa(nhanvien);
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
                        ShowOnTable(nhanvien);
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
                tblQLNHANVIEN.setModel(model);
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
                QLNHANVIENBUS qlnhanvienbus = new QLNHANVIENBUS();
                if (vtkey == 1) {
                    NHANVIEN kq = qlnhanvienbus.timTheoMa(tukhoa);
                    model.setRowCount(0);
                    if (kq == null) {
                        lbKQTK.setText("Kết quả tìm kiếm: Không tìm thấy");
                    } else {
                        ShowOnTable(kq);
                        lbKQTK.setText("Kết quả tìm kiếm: Tìm thấy " + model.getRowCount() + " NHÂN VIÊN");
                        tblQLNHANVIEN.setModel(model);
                    }
                } else if (vtkey == 2) {
                    ArrayList<NHANVIEN> kq = qlnhanvienbus.timTheoTen(tukhoa);
                    model.setRowCount(0);
                    if (kq.size() == 0) {
                        lbKQTK.setText("Kết quả tìm kiếm: Không tìm thấy");
                    } else {
                        for (NHANVIEN nhanvien : kq) {
                            ShowOnTable(nhanvien);
                        }
                        lbKQTK.setText("Kết quả tìm kiếm: Tìm thấy " + model.getRowCount() + " NHÂN VIÊN");
                        tblQLNHANVIEN.setModel(model);
                    }
                }
            }
        } else if (e.getSource() == btShowAll) {
            lbKQTK.setText("");
            model.setRowCount(0);
            for (NHANVIEN nhanvien : QLNHANVIENBUS.dsnhanvien) {
                ShowOnTable(nhanvien);
            }
            tblQLNHANVIEN.setModel(model);
        }
        if (e.getSource() == btThongKe) {
            String luachon = "";
            do {
                String[] options = { "", "Mã Nhà Xuất Bản", "SL Nhà Xuất Bản ban đầu", "SL hiện tại" };
                luachon = (String) JOptionPane.showInputDialog(null, "Mời lựa chọn khóa thống kê:", "Thống kê",
                        JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);
                // QLNHANVIENBUS qlnhanvienbus = new QLNHANVIENBUS();
                // switch (luachon) {
                //     case "Mã Nhà Xuất Bản":
                //         count = qlnhanvienbus.ThongKeMaNV();
                //         JOptionPane.showMessageDialog(null, "Đếm số lượng Nhà Xuất Bản: " + String.valueOf(count),
                //                 "Kết quả", JOptionPane.PLAIN_MESSAGE);
                //         break;
                // }
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
        if (e.getSource() == btNV) {
            OffPageQLSACH(true);
            OffBTBgSelected();
            btNV.setBackground(Color.green);
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource() == tblQLNHANVIEN) {
            int i = tblQLNHANVIEN.getSelectedRow();
            if (i >= 0) {
                NHANVIEN nhanvien = new NHANVIEN();
                nhanvien = QLNHANVIENBUS.dsnhanvien.get(i);
                txMaNV.setText(nhanvien.getMaNV().trim());
                txTenNV.setText(nhanvien.getTenNV().trim());
                txChucvu.setText(String.valueOf(nhanvien.getChucvu()));
                txLuongCB.setText(String.valueOf(nhanvien.getLuongCB()));
                txPhucap.setText(String.valueOf(nhanvien.getPhucap()));
                txHesoluong.setText(String.valueOf(nhanvien.getHesoluong()));
                txSDT.setText(String.valueOf(nhanvien.getSDT()));
                txEmail.setText(String.format(nhanvien.getMail()));
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
        if (e.getSource() == txMaNV) {
            txMaNV.setToolTipText("Gợi ý: NV001");
        } else if (e.getSource() == txChucvu) {
            txChucvu.setToolTipText("Gợi ý: Nguyễn Văn A");
        } else if (e.getSource() == txLuongCB) {
            txLuongCB.setToolTipText("Gợi ý: Giám đốc");
        } else if (e.getSource() == txPhucap) {
            txPhucap.setToolTipText("Gợi ý: 30000000");
        } else if (e.getSource() == txHesoluong) {
            txHesoluong.setToolTipText("Gợi ý: 5000000");
        } else if (e.getSource() == txSDT) {
            txSDT.setToolTipText("Gợi ý: 3000");
        } else if (e.getSource() == txEmail) {
            txEmail.setToolTipText("Gợi ý: 915656572 ");
        }else if (e.getSource() == txEmail) {
            txEmail.setToolTipText("Gợi ý: nguyenvana@gmail.com ");
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    public void setInput() {
        // label Matacgia
        lbManhanvien = new JLabel("Mã NV:");
        lbManhanvien.setFont(new Font("Arial", Font.BOLD, 18));
        lbManhanvien.setBounds(20, 0, 130, 80);
        // label Tensach
        lbTennhanvien = new JLabel("Tên NV:");
        lbTennhanvien.setFont(new Font("Arial", Font.BOLD, 18));
        lbTennhanvien.setBounds(20, 40, 130, 80);
        // label Chức vụ
        lbChucvu = new JLabel("Chức vụ:");
        lbChucvu.setFont(new Font("Arial", Font.BOLD, 18));
        lbChucvu.setBounds(20, 80, 130, 80);
        // label Lương cơ bản
        lbLuongCB = new JLabel("Lương CB: ");
        lbLuongCB.setFont(new Font("Arial", Font.BOLD, 18));
        lbLuongCB.setBounds(20, 120, 130, 80);
        // label Phụ cấp
        lbPhucap = new JLabel("Phụ cấp:");
        lbPhucap.setFont(new Font("Arial", Font.BOLD, 18));
        lbPhucap.setBounds(20, 160, 130, 80);
        // label Hệ số lương
        lbHesoluong = new JLabel("Hệ số lương:");
        lbHesoluong.setFont(new Font("Arial", Font.BOLD, 18));
        lbHesoluong.setBounds(20, 200, 130, 80);
        // label SDT
        lbSDT = new JLabel("SDT:");
        lbSDT.setFont(new Font("Arial", Font.BOLD, 18));
        lbSDT.setBounds(380, 0, 130, 80);
        // label Email
        lbEmail = new JLabel("Email:");
        lbEmail.setFont(new Font("Arial", Font.BOLD, 18));
        lbEmail.setBounds(380, 40, 130, 80);
        // labelLCTK
        lbLCTK = new JLabel("Lựa chọn khóa tìm kiếm:");
        lbLCTK.setFont(new Font("Arial", Font.BOLD, 18));
        lbLCTK.setBounds(10, 50, 230, 80);

        // labelTuKhoaTK
        lbTuKhoaTK = new JLabel("Nhập từ khóa tìm kiếm:");
        lbTuKhoaTK.setFont(new Font("Arial", Font.BOLD, 18));
        lbTuKhoaTK.setBounds(10, 100, 230, 80);

        // JTextField Mã nhân viên
        txMaNV = new JTextField();
        txMaNV.setBounds(160, 25, 180, 30);
        txMaNV.setFont(new Font("Arial", Font.PLAIN, 15));
        txMaNV.addMouseListener(this);
        // JTextField Tên nhân viên
        txTenNV = new JTextField();
        txTenNV.setBounds(160, 65, 180, 30);
        txTenNV.setFont(new Font("Arial", Font.PLAIN, 15));
        txTenNV.addMouseListener(this);
        // JTextField Chức vụ
        txChucvu = new JTextField();
        txChucvu.setBounds(160, 105, 180, 30);
        txChucvu.setFont(new Font("Arial", Font.PLAIN, 15));
        txChucvu.addMouseListener(this);
        // JTextField Lương cơ bản  
        txLuongCB = new JTextField();
        txLuongCB.setBounds(160, 145, 180, 30);
        txLuongCB.setFont(new Font("Arial", Font.PLAIN, 15));
        txLuongCB.addMouseListener(this);
        // JTextField Phụ cấp
        txPhucap = new JTextField();
        txPhucap.setBounds(160, 185, 180, 30);
        txPhucap.setFont(new Font("Arial", Font.PLAIN, 15));
        txPhucap.addMouseListener(this);
        // JTextField Hệ số lương
        txHesoluong = new JTextField();
        txHesoluong.setBounds(160, 225, 180, 30);
        txHesoluong.setFont(new Font("Arial", Font.PLAIN, 15));
        txHesoluong.addMouseListener(this);
        // JTextField SDT
        txSDT = new JTextField();
        txSDT.setBounds(500, 25, 180, 30);
        txSDT.setFont(new Font("Arial", Font.PLAIN, 15));
        txSDT.addMouseListener(this);
        // JTextField Email
        txEmail = new JTextField();
        txEmail.setBounds(500, 65, 180, 30);
        txEmail.setFont(new Font("Arial", Font.PLAIN, 15));
        txEmail.addMouseListener(this);
        // JTextField Khóa tìm kiếm
        txKhoaTK = new JTextField();
        txKhoaTK.setFont(new Font("Arial", Font.PLAIN, 15));
        txKhoaTK.setBounds(245, 125, 160, 30);
        txKhoaTK.addMouseListener(this);

        // JbuttonThem
        btThem = new JButton("Thêm");
        btThem.setFont(new Font("Arial", Font.BOLD, 15));
        btThem.setBounds(10, 305, 80, 30);
        btThem.setBackground(Color.cyan);
        btThem.setBorder(new RoundedBorder(10));
        btThem.addActionListener(this);
        // JbuttonSua
        btSua = new JButton("Sửa");
        btSua.setFont(new Font("Arial", Font.BOLD, 15));
        btSua.setBounds(110, 305, 80, 30);
        btSua.setBackground(Color.cyan);
        btSua.setBorder(new RoundedBorder(10));
        btSua.addActionListener(this);
        // JbuttonXoa
        btXoa = new JButton("Xóa");
        btXoa.setFont(new Font("Arial", Font.BOLD, 15));
        btXoa.setBounds(210, 305, 80, 30);
        btXoa.setBackground(Color.cyan);
        btXoa.setBorder(new RoundedBorder(10));
        btXoa.addActionListener(this);
        // JbuttonHoanTac
        btHoanTac = new JButton("Hoàn tác");
        btHoanTac.setFont(new Font("Arial", Font.BOLD, 15));
        btHoanTac.setBounds(310, 305, 90, 30);
        btHoanTac.setBackground(Color.cyan);
        btHoanTac.setBorder(new RoundedBorder(10));
        btHoanTac.addActionListener(this);

        // set up ComboBox
        String[] dsKhoaTK = { "", "Mã Nhà Xuất Bản", "Tên Nhà Xuất Bản"};
        comboBoxDSKhoaTK = new JComboBox<>(dsKhoaTK);
        comboBoxDSKhoaTK.setFont(new Font("Arial", Font.BOLD, 13));
        comboBoxDSKhoaTK.setBounds(260, 85, 120, 35);
        comboBoxDSKhoaTK.addActionListener(this);

        lbLCTK.setVisible(false);
        lbTuKhoaTK.setVisible(false);
        comboBoxDSKhoaTK.setVisible(false);
        txKhoaTK.setVisible(false);

        pnNhapTTnhanvien.add(lbManhanvien);
        pnNhapTTnhanvien.add(lbTennhanvien);
        pnNhapTTnhanvien.add(lbChucvu);
        pnNhapTTnhanvien.add(lbLuongCB);
        pnNhapTTnhanvien.add(lbPhucap);
        pnNhapTTnhanvien.add(lbHesoluong);
        pnNhapTTnhanvien.add(lbSDT);
        pnNhapTTnhanvien.add(lbEmail);

        pnNhapTTnhanvien.add(txMaNV);
        pnNhapTTnhanvien.add(txTenNV);
        pnNhapTTnhanvien.add(txChucvu);
        pnNhapTTnhanvien.add(txLuongCB);
        pnNhapTTnhanvien.add(txPhucap);
        pnNhapTTnhanvien.add(txHesoluong);
        pnNhapTTnhanvien.add(txSDT);
        pnNhapTTnhanvien.add(txEmail);

        pnNhapTTnhanvien.add(btThem);
        pnNhapTTnhanvien.add(btSua);
        pnNhapTTnhanvien.add(btXoa);
        pnNhapTTnhanvien.add(btHoanTac);
    }

    public void setMenu() {
        // Set menu side left
        ImageIcon iconHome = new ImageIcon("images\\home.png");
        ImageIcon iconMenu = new ImageIcon("images\\menu.png");
        ImageIcon iconBook = new ImageIcon("images\\book.png");
        ImageIcon iconSearch = new ImageIcon("images\\search.png");
        ImageIcon iconLogout = new ImageIcon("images\\logout.png");
        ImageIcon iconExited = new ImageIcon("images\\exit.png");

        lbHome = new JLabel();
        lbHome.setHorizontalAlignment(SwingConstants.CENTER);
        lbHome.setIcon(iconHome);

        btMenu = new JButton("Menu");
        btMenu.setFont(new Font("Arial", Font.BOLD, 20));
        btMenu.setBackground(ColorOcean);
        btMenu.setIcon(iconMenu);
        btMenu.setHorizontalAlignment(SwingConstants.LEFT);
        btMenu.setBorder(BorderFactory.createEmptyBorder());
        btMenu.addActionListener(this);

        btMenuTimKiem = new JButton("Tìm kiếm nhân viên");
        btMenuTimKiem.setFont(new Font("Arial", Font.BOLD, 20));
        btMenuTimKiem.setBackground(ColorOcean);
        btMenuTimKiem.setIcon(iconSearch);
        btMenuTimKiem.setHorizontalAlignment(SwingConstants.LEFT);
        btMenuTimKiem.setBorder(BorderFactory.createEmptyBorder());
        btMenuTimKiem.addActionListener(this);

        btNhanvien = new JButton("Thông tin nhân viên");
        btNhanvien.setFont(new Font("Arial", Font.BOLD, 20));
        btNhanvien.setBackground(ColorPurple);
        btNhanvien.setIcon(iconBook);
        btNhanvien.setHorizontalAlignment(SwingConstants.LEFT);
        btNhanvien.setBorder(BorderFactory.createEmptyBorder());
        btNhanvien.addActionListener(this);

        // JButton Đăng xuất
        btDangXuat = new JButton("Đăng xuất");
        btDangXuat.setFont(new Font("Arial", Font.BOLD, 20));
        btDangXuat.setBackground(ColorOcean);
        btDangXuat.setIcon(iconLogout);
        btDangXuat.setHorizontalAlignment(SwingConstants.LEFT);
        btDangXuat.setBorder(BorderFactory.createEmptyBorder());
        btDangXuat.addActionListener(this);
        // JButton thoát
        btThoat = new JButton("Thoát");
        btThoat.setFont(new Font("Arial", Font.BOLD, 20));
        btThoat.setBackground(ColorOcean);
        btThoat.setIcon(iconExited);
        btThoat.setHorizontalAlignment(SwingConstants.LEFT);
        btThoat.setBorder(BorderFactory.createEmptyBorder());
        btThoat.addActionListener(this);

        // add Menu button
        // pnMenu.add(lbHome);
        pnMenu.add(btMenu);
        pnMenu.add(btNhanvien);
        pnMenu.add(btMenuTimKiem);
        pnMenu.add(btDangXuat);
        pnMenu.add(btThoat);
    }

    public void setTableNXB() {
        // label TTNXB
        lbTTNhanVien = new JLabel("THÔNG TIN NXB");
        lbTTNhanVien.setFont(new Font("Arial", Font.BOLD, 30));
        lbTTNhanVien.setHorizontalAlignment(SwingConstants.CENTER);
        lbTTNhanVien.setVerticalAlignment(SwingConstants.TOP);
        // labelKQTK
        lbKQTK = new JLabel();
        lbKQTK.setHorizontalAlignment(SwingConstants.CENTER);
        lbKQTK.setVerticalAlignment(SwingConstants.TOP);
        // ----set up table----
        tblQLNHANVIEN = new JTable();
        JScrollPane pane = new JScrollPane(tblQLNHANVIEN);
        pane.setAutoscrolls(true);
        tblQLNHANVIEN.setRowHeight(20);
        tblQLNHANVIEN.setFont(new Font(null, 0, 13));
        tblQLNHANVIEN.setBackground(Color.LIGHT_GRAY);
        tblQLNHANVIEN.addMouseListener(this);
        tblQLNHANVIEN.setDefaultEditor(Object.class, null);

        this.add(pnTTnhanvien);
        pnTTnhanvien.add(lbTTNhanVien);
        pnTTnhanvien.add(lbKQTK);
        pnTTnhanvien.add(pane);
    }

    public void setShowAll() {
        // JButtonShowAll
        btShowAll = new JButton("Hiển thị tất cả");
        btShowAll.setFont(new Font("Arial", Font.BOLD, 15));
        btShowAll.setBounds(1010, 0, 130, 30);
        btShowAll.setBackground(Color.cyan);
        btShowAll.setBorder(new RoundedBorder(10));
        btShowAll.addActionListener(this);

        btSapXep = new JButton("Sắp xếp theo tên");
        btSapXep.setFont(new Font("Arial", Font.BOLD, 15));
        btSapXep.setBounds(830, 0, 150, 30);
        btSapXep.setBackground(Color.cyan);
        btSapXep.setBorder(new RoundedBorder(10));
        btSapXep.addActionListener(this);

        pnShowAll.add(btShowAll);
        pnShowAll.add(btSapXep);
    }


    public void ShowOnTable(NHANVIEN nhanvien) {
        Vector<String> row = new Vector<String>();
        row.add(nhanvien.getMaNV().trim());
        row.add(nhanvien.getTenNV().trim());
        row.add(String.valueOf(nhanvien.getChucvu()));
        row.add(String.valueOf(nhanvien.getLuongCB()));
        row.add(String.valueOf(nhanvien.getPhucap()));
        row.add(String.valueOf(nhanvien.getHesoluong()));
        row.add(String.valueOf(nhanvien.getSDT()));
        row.add( nhanvien.getMail().trim());
        model.addRow(row);
    }

    public void getInfoTextField(NHANVIEN nhanvien) {
        nhanvien.setMaNV(txMaNV.getText());
        nhanvien.setTenNV(txTenNV.getText());
        nhanvien.setChucvu(Integer.parseInt(txChucvu.getText()));
        nhanvien.setLuongCB(Integer.parseInt(txLuongCB.getText()));
        nhanvien.setPhucap(Integer.parseInt(txPhucap.getText()));
        nhanvien.setHesoluong(Integer.parseInt(txHesoluong.getText()));
        nhanvien.setSDT(Integer.parseInt(txSDT.getText()));
        nhanvien.setMail(txEmail.getText());
    }

    public void getDatabase() {
        try {
            QLNHANVIENBUS qlsachbus = new QLNHANVIENBUS();
            if (QLNHANVIENBUS.dsnhanvien == null)
                try {
                    qlsachbus.docDSSACH();
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            // set up header(column)
            header = new Vector<String>();
            header.add("Mã nhân viên");
            header.add("Tên nhân viên");
            header.add("Chức vụ");
            header.add("Lương cơ bản");
            header.add("Phụ cấp");
            header.add("Hệ số lương");
            header.add("SDT");
            header.add("Email");
            model = new DefaultTableModel(header, 0);
            for (NHANVIEN nhanvien : QLNHANVIENBUS.dsnhanvien) {
                ShowOnTable(nhanvien);
            }
            tblQLNHANVIEN.setModel(model);
        } catch (Exception e1) {
            System.out.println(e1);
        }
    }

    public void OffBTBgSelected() {
        btNhanvien.setBackground(ColorOcean);
        btMenu.setBackground(ColorOcean);
        btMenuTimKiem.setBackground(ColorOcean);
        btDangXuat.setBackground(ColorOcean);
        btThoat.setBackground(ColorOcean);
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
        if (lbTKMaNV == null) {
            lbTKMaNV = new JLabel("Mã Nhân Viên:");
            lbTKMaNV.setFont(new Font("Arial", Font.BOLD, 20));
            lbTKMaNV.setBounds(10, 170, 100, 50);
        }
        if (lbTKMaNV != null) {
            lbTKMaNV.setVisible(true);
        }
        pnTimKiem.add(lbTKMaNV);
    }

    public void OffPageQLSACH(Boolean x) {
        pnNhapTTnhanvien.setVisible(x);
        pnShowAll.setVisible(x);
        pnTimKiem.setVisible(x);
        pnTTnhanvien.setVisible(x);
    }
}
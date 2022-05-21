package QLTV.GUI;

import java.awt.Font;
import java.awt.Color;
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
import MyCustom.MyTable;
import QLTV.BUS.QLNHANVIENBUS;
import QLTV.DTO.NHANVIEN;

public class QLNHANVIENGUI extends JFrame implements ActionListener, MouseListener {
    MyTable myTable = new MyTable();
    JPanel pnTTnhanvien, pnNhapTTnhanvien, pnShowAll, pnMenu, pnTimKiem;
    JLabel lbHome, lbTTNhanVien, lbManhanvien, lbTennhanvien, lbChucvu, lbLuongCB, lbPhucap, lbHesoluong, lbSDT,
            lbEmail, lbLCTK, lbTuKhoaTK;
    JLabel lbTKMaNV;
    JTextField txMaNV, txTenNV, txChucvu, txLuongCB, txPhucap, txHesoluong, txSDT, txEmail, txKhoaTK;
    JButton btThem, btSua, btXoa, btHoanTac, btSapXep, btMenuTimKiem, btShowAll, btThongKe;
    JButton btMenu, btNhanvien, btDangXuat, btThoat;
    JButton btSearch;

    JComboBox<String> comboBoxDSKhoaTK;

    JTable tblQLNHANVIEN;
    DefaultTableModel model;
    Vector<String> header;

    public QLNHANVIENGUI() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(1400, 800);
        this.setTitle("Quản lý thông tin nhân viên");
        this.setLayout(null);
        this.setLocationRelativeTo(null);

        pnTTnhanvien = new JPanel();
        pnNhapTTnhanvien = new JPanel();
        pnShowAll = new JPanel();
        pnMenu = new JPanel();
        pnTimKiem = new JPanel();
        pnTTnhanvien.setLayout(new GridLayout(2, 1, 0, -300));
        pnTTnhanvien.setBounds(242, 0, 1142, 400);
        pnTTnhanvien.setBackground(MyColor.ColorBlue);

        pnShowAll.setLayout(null);
        pnShowAll.setBounds(242, 402, 1142, 30);
        pnShowAll.setBackground(MyColor.ColorBlue);

        pnNhapTTnhanvien.setLayout(null);
        pnNhapTTnhanvien.setBounds(242, 415, 720, 550);
        pnNhapTTnhanvien.setBackground(MyColor.ColorBlue);

        pnMenu.setLayout(new GridLayout(9, 1));
        pnMenu.setBounds(0, 178, 240, 590);
        pnMenu.setBackground(MyColor.ColorOcean);

        pnTimKiem.setLayout(null);
        pnTimKiem.setBounds(962, 430, 423, 332);
        pnTimKiem.setBackground(MyColor.ColorBlue);

        // add components
        this.add(pnMenu);
        this.add(pnShowAll);
        this.add(pnNhapTTnhanvien);
        this.add(pnTimKiem);

        setTableNV();
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
                int kt = -1;
                NHANVIEN nhanvien = new NHANVIEN();
                NHANVIEN manhanviencu = QLNHANVIENBUS.dsnhanvien.get(i);
                getInfoTextField(nhanvien);
                try {
                    QLNHANVIENBUS qlnhanvienbus = new QLNHANVIENBUS();
                    qlnhanvienbus.sua(nhanvien, manhanviencu, i);
                } catch (Exception e1) {
                    System.out.println(e1);
                }
                if (kt == 0) {
                    model.setValueAt(nhanvien.getMaNV(), i, 0);
                    model.setValueAt(nhanvien.getTenNV(), i, 1);
                    model.setValueAt(nhanvien.getChucvu(), i, 2);
                    model.setValueAt(nhanvien.getLuongCB(), i, 3);
                    model.setValueAt(nhanvien.getPhucap(), i, 4);
                    model.setValueAt(nhanvien.getHesoluong(), i, 5);
                    model.setValueAt(nhanvien.getSDT(), i, 6);
                    model.setValueAt(nhanvien.getMail(), i, 7);
                    tblQLNHANVIEN.setModel(model);
                }
            }
        } else if (e.getSource() == btXoa) {
            int XacNhanXoa = JOptionPane.showConfirmDialog(null, "Bạn có chắc chắn muốn xóa không ?", "Thông báo",
                    JOptionPane.YES_NO_OPTION);
            if (XacNhanXoa == 0) {
                String MaNV = txMaNV.getText();
                int i = tblQLNHANVIEN.getSelectedRow();
                if (i >= 0) {
                    try {
                        // Truy cập xuống BUS
                        NHANVIEN nhanviencu = QLNHANVIENBUS.dsnhanvien.get(i);
                        QLNHANVIENBUS.htXoa.add(nhanviencu);
                        QLNHANVIENBUS qlsachbus = new QLNHANVIENBUS();
                        qlsachbus.xoa(MaNV, i);
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
                    QLNHANVIENBUS qlnhanvienbus = new QLNHANVIENBUS();
                    int kiemtra = 0;
                    try {
                        kiemtra = qlnhanvienbus.hoantacXoa(nhanvien);
                    } catch (Exception e1) {
                        e1.printStackTrace();
                    }
                    if (kiemtra == 1) {
                        // Đưa dữ liệu lên table
                        header = new Vector<String>();
                        header.add("Mã Nhân Viên");
                        header.add("Tên Nhân Viên");
                        header.add("Chức Vụ");
                        header.add("Lương Cơ Bản");
                        header.add("Phụ Cấp");
                        header.add("Hệ Số Lương");
                        header.add("Số Điện Thoại");
                        header.add("Email");
                        if (model.getRowCount() == 0) {
                            model = new DefaultTableModel(header, 0);
                        }
                        ShowOnTable(nhanvien);
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
                tblQLNHANVIEN.setModel(model);
            }
        } else if (e.getSource() == btMenuTimKiem) { // Của button Tìm kiếm nhân viên, để hiện thị
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
                QLNHANVIENBUS qlnhanvienbus = new QLNHANVIENBUS();
                if (vtkey == 1) {
                    NHANVIEN kq = qlnhanvienbus.timTheoMa(tukhoa);
                    model.setRowCount(0);
                    if (kq == null) {
                        JOptionPane.showMessageDialog(null, "Không tìm thấy dữ liệu", "Lỗi", JOptionPane.ERROR_MESSAGE);
                    } else {
                        ShowOnTable(kq);
                        tblQLNHANVIEN.setModel(model);
                    }
                } else if (vtkey == 2) {
                    ArrayList<NHANVIEN> kq = qlnhanvienbus.timTheoTen(tukhoa);
                    model.setRowCount(0);
                    if (kq.size() == 0) {
                        JOptionPane.showMessageDialog(null, "Không tìm thấy dữ liệu", "Lỗi", JOptionPane.ERROR_MESSAGE);
                    } else {
                        for (NHANVIEN nhanvien : kq) {
                            ShowOnTable(nhanvien);
                        }
                        tblQLNHANVIEN.setModel(model);
                    }
                }
            }
        } else if (e.getSource() == btShowAll) {
            model.setRowCount(0);
            for (NHANVIEN nhanvien : QLNHANVIENBUS.dsnhanvien) {
                ShowOnTable(nhanvien);
            }
            tblQLNHANVIEN.setModel(model);
        }
        if (e.getSource() == btMenu) {
            OffBTBgSelected();
            OffPageQLSACH(false);
            btMenu.setBackground(MyColor.ColorOcean);
        }
        if (e.getSource() == btMenu) {
            this.dispose();
            new Menu();
        }
        if (e.getSource() == btThoat) {
            int ktra = JOptionPane.showConfirmDialog(null, "Bạn có chắc chắn muốn đăng xuất", "Xác nhận",
                    JOptionPane.YES_NO_OPTION);
            if (ktra == 0) {
                System.exit(0);
            }
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
        if (e.getSource() == btNhanvien) {
            OffPageQLSACH(true);
            OffBTBgSelected();
            btNhanvien.setBackground(MyColor.ColorLightBlue);
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource() == tblQLNHANVIEN) {
            int i = tblQLNHANVIEN.getSelectedRow();
            if (i >= 0) {
                NHANVIEN nhanvien = new NHANVIEN();
                nhanvien = QLNHANVIENBUS.dsnhanvien.get(i);
                txMaNV.setText(nhanvien.getMaNV().replaceAll("\\s+", " ").trim());
                txTenNV.setText(nhanvien.getTenNV().replaceAll("\\s+", " ").trim());
                txChucvu.setText(String.valueOf(nhanvien.getChucvu()));
                txLuongCB.setText(String.format("%,d", nhanvien.getLuongCB()));
                txPhucap.setText(String.format("%,d", nhanvien.getPhucap()));
                txHesoluong.setText(String.valueOf(nhanvien.getHesoluong()));
                txSDT.setText(String.valueOf(nhanvien.getSDT()));
                txEmail.setText(String.valueOf(nhanvien.getMail()));
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
        } else if (e.getSource() == txEmail) {
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
        btThem.setBounds(20, 270, 80, 30);
        btThem.setBackground(MyColor.ColorButton);
        btThem.setBorder(new RoundedBorder(10));
        btThem.addActionListener(this);
        // JbuttonSua
        btSua = new JButton("Sửa");
        btSua.setFont(new Font("Arial", Font.BOLD, 15));
        btSua.setBounds(120, 270, 80, 30);
        btSua.setBackground(MyColor.ColorButton);
        btSua.setBorder(new RoundedBorder(10));
        btSua.addActionListener(this);
        // JbuttonXoa
        btXoa = new JButton("Xóa");
        btXoa.setFont(new Font("Arial", Font.BOLD, 15));
        btXoa.setBounds(220, 270, 80, 30);
        btXoa.setBackground(MyColor.ColorButton);
        btXoa.setBorder(new RoundedBorder(10));
        btXoa.addActionListener(this);
        // JbuttonHoanTac
        btHoanTac = new JButton("Hoàn tác");
        btHoanTac.setFont(new Font("Arial", Font.BOLD, 15));
        btHoanTac.setBounds(320, 270, 90, 30);
        btHoanTac.setBackground(MyColor.ColorButton);
        btHoanTac.setBorder(new RoundedBorder(10));
        btHoanTac.addActionListener(this);

        // set up ComboBox
        String[] dsKhoaTK = { "", "Mã Nhân Viên", "Tên Nhân Viên" };
        comboBoxDSKhoaTK = new JComboBox<>(dsKhoaTK);
        comboBoxDSKhoaTK.setFont(new Font("Arial", Font.BOLD, 13));
        comboBoxDSKhoaTK.setBounds(245, 74, 120, 35);
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
        ImageIcon iconMenu = new ImageIcon("images\\menu.png");
        ImageIcon iconBook = new ImageIcon("images\\book.png");
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

        btMenuTimKiem = new JButton("Tìm kiếm nhân viên");
        btMenuTimKiem.setFont(new Font("Arial", Font.BOLD, 20));
        btMenuTimKiem.setBackground(MyColor.ColorOcean);
        btMenuTimKiem.setIcon(iconSearch);
        btMenuTimKiem.setHorizontalAlignment(SwingConstants.LEFT);
        btMenuTimKiem.setBorder(BorderFactory.createEmptyBorder());
        btMenuTimKiem.addActionListener(this);

        btNhanvien = new JButton("Thông tin nhân viên");
        btNhanvien.setFont(new Font("Arial", Font.BOLD, 20));
        btNhanvien.setBackground(MyColor.ColorLightBlue);
        btNhanvien.setIcon(iconBook);
        btNhanvien.setHorizontalAlignment(SwingConstants.LEFT);
        btNhanvien.setBorder(BorderFactory.createEmptyBorder());
        btNhanvien.addActionListener(this);

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
        pnMenu.add(btNhanvien);
        pnMenu.add(btMenuTimKiem);
        pnMenu.add(btDangXuat);
        pnMenu.add(btThoat);
    }

    public void setTableNV() {
        // label TTNV
        lbTTNhanVien = new JLabel("THÔNG TIN NHÂN VIÊN");
        lbTTNhanVien.setFont(new Font("Arial", Font.BOLD, 30));
        lbTTNhanVien.setHorizontalAlignment(SwingConstants.CENTER);
        lbTTNhanVien.setVerticalAlignment(SwingConstants.TOP);
        // ----set up table----
        tblQLNHANVIEN = new JTable();
        JScrollPane pane = new JScrollPane(tblQLNHANVIEN);
        pane.setAutoscrolls(true);
        tblQLNHANVIEN.setRowHeight(30);
        tblQLNHANVIEN.setFont(new Font(null, 0, 13));
        tblQLNHANVIEN.setBackground(MyColor.ColorLightGray);
        tblQLNHANVIEN.getTableHeader().setBackground(MyColor.ColorSilver);
        tblQLNHANVIEN.setSelectionBackground(MyColor.Color);
        tblQLNHANVIEN.addMouseListener(this);
        tblQLNHANVIEN.setDefaultEditor(Object.class, null);

        this.add(pnTTnhanvien);
        pnTTnhanvien.add(lbTTNhanVien);
        pnTTnhanvien.add(pane);
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

    public void ShowOnTable(NHANVIEN nhanvien) {
        Vector<String> row = new Vector<String>();
        row.add(nhanvien.getMaNV().replaceAll("\\s+", " ").trim());
        row.add(nhanvien.getTenNV().replaceAll("\\s+", " ").trim());
        row.add(String.valueOf(nhanvien.getChucvu()));
        row.add(String.format("%,d", nhanvien.getLuongCB()));
        row.add(String.format("%,d", nhanvien.getPhucap()));
        row.add(String.valueOf(nhanvien.getHesoluong()));
        row.add(String.valueOf(nhanvien.getSDT()));
        row.add(nhanvien.getMail().replaceAll("\\s+", " ").trim());
        model.addRow(row);
    }

    public void getInfoTextField(NHANVIEN nhanvien) {
        String luongCB = myTable.RemoveCommaInString(txLuongCB);
        String phucap = myTable.RemoveCommaInString(txPhucap);
        nhanvien.setMaNV(txMaNV.getText().replaceAll("\\s+", " ").trim());
        nhanvien.setTenNV(txTenNV.getText().replaceAll("\\s+", " ").trim());
        nhanvien.setChucvu(String.valueOf(txChucvu.getText().replaceAll("\\s+", " ").trim()));
        nhanvien.setLuongCB(Integer.parseInt(luongCB));
        nhanvien.setPhucap(Integer.parseInt(phucap));
        nhanvien.setHesoluong(Double.parseDouble(txHesoluong.getText().replaceAll("\\s+", " ").trim()));
        nhanvien.setSDT(Integer.parseInt(txSDT.getText().replaceAll("\\s+", " ").trim()));
        nhanvien.setMail(txEmail.getText().replaceAll("\\s+", " ").trim());
    }

    public void getDatabase() {
        try {
            QLNHANVIENBUS qlsachbus = new QLNHANVIENBUS();
            if (QLNHANVIENBUS.dsnhanvien == null)
                try {
                    qlsachbus.docDSNHANVIEN();
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
        btNhanvien.setBackground(MyColor.ColorOcean);
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
        pnNhapTTnhanvien.setVisible(x);
        pnShowAll.setVisible(x);
        pnTimKiem.setVisible(x);
        pnTTnhanvien.setVisible(x);
    }
}

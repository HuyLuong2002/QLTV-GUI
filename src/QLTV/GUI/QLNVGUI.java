package QLTV.GUI;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Vector;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import MyCustom.LoginPage;
import MyCustom.MyTable;
import MyCustom.RoundedBorder;
import QLTV.BUS.QLNVBUS;
import QLTV.DTO.DOCGIA;

public class QLNVGUI implements MouseListener, ActionListener {
    JPanel pnQLNV, pnTabDG, pnTabTheoDoiMT, pnDG, pnShowAll, pnNhapDG, pnTimKiemPM, pnLocPM;
    JButton btMenu, btSach, btMT, btQLNV, btDangXuat, btNhapSach, btMenuTimKiem, btThongKe;
    JButton btThoat, btSuaDG, btThemDG, btXoa, btHoanTac, btSapXep;
    JLabel lbMaDG, lbTenDG, lbDiaChi, lbMail, lbTInhTrangThue, lbKQTK;
    JTextField txMaDG, txTenDG, txDiaChi, txMail, txTinhTrangThue;
    JComboBox<String> cbTinhTrangThue;
    JTabbedPane tabbedPane;
    public JTable tblQLDG, tblQLPTD;
    public DefaultTableModel modelDG, modelPTD;
    TitledBorder titleDG;
    Vector<String> header;
    JButton btShowAll;
public QLNVGUI() {

    }

    public JPanel setQLNVGUI() {
        if (pnQLNV == null) {
            pnQLNV = new JPanel();
            pnQLNV.setBounds(240, 0, 1145, 800);
            pnQLNV.setLayout(null);

            // panel tab mượn
            pnTabDG = new JPanel();
            pnTabDG.setLayout(new GridLayout(1, 1, 0, 0));

            pnDG = new JPanel();
            pnDG.setLayout(new GridLayout(1, 1));

            pnShowAll = new JPanel();
            pnShowAll.setBounds(5, 358, 1135, 50);
            pnShowAll.setLayout(null);

            pnNhapDG = new JPanel();
            pnNhapDG.setLayout(null);
            pnNhapDG.setBounds(5, 410, 1137, 370);

            pnTimKiemPM = new JPanel();
            pnTimKiemPM.setLayout(null);
            pnTimKiemPM.setBounds(570, 0, 567, 200);

            pnLocPM = new JPanel();
            pnLocPM.setLayout(null);
            pnLocPM.setBounds(570, 195, 300, 155);

            tabbedPane = new JTabbedPane();
            tabbedPane.addTab("Độc giả", pnTabDG);
            tabbedPane.addTab("Phiếu theo dõi mượn trả", pnTabTheoDoiMT);
            tabbedPane.addMouseListener(this);
            tabbedPane.setBounds(5, 5, 1138, 350);

            pnQLNV.add(tabbedPane);
            pnQLNV.add(pnShowAll);
            pnQLNV.add(pnNhapDG);

            pnNhapDG.add(pnTimKiemPM);
            pnNhapDG.add(pnLocPM);

            pnTabDG.add(pnDG);

            // Phiếu mượn, chi tiết mượn
            setTitleDG();
            setTableDG();
            setShowAll();
            setInputDG();
            getDBDG();
            //setInputMuon();
            //setTimKiemPM();
            //setLocPM();
        }

        return pnQLNV;
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
    
    public void setTableDG() {
        // ----set up table----
        tblQLDG = new JTable();
        JScrollPane pane = new JScrollPane(tblQLDG);
        pane.setAutoscrolls(true);
        tblQLDG.setRowHeight(20);
        tblQLDG.setFont(new Font(null, 0, 13));
        tblQLDG.setBackground(Color.LIGHT_GRAY);
        tblQLDG.addMouseListener(this);
        tblQLDG.setDefaultEditor(Object.class, null);
        tblQLDG.setSelectionBackground(Color.GREEN);
        pnDG.add(pane);
    }

    public void getDBDG() {
        try {
            QLNVBUS qlnvbus = new QLNVBUS();
            if (QLNVBUS.dsdg == null)
                try {
                    qlnvbus.docDS();
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            // set up header(column)
            Vector<String> header = new Vector<String>();
            header.add("Mã độc giả");
            header.add("Tên độc giả");
            header.add("Địa chỉ");
            header.add("Email");
            header.add("Tình trạng thuê");
            modelDG = new DefaultTableModel(header, 0);
            for (DOCGIA dg : QLNVBUS.dsdg) {
                ShowOnTableDGDG(dg);
            }
            tblQLDG.setModel(modelDG);
        } catch (Exception e1) {
            System.out.println(e1);
        }
    }

    public void ShowOnTableDGDG(DOCGIA dg) {
        Vector<String> row = new Vector<String>();
        row.add(dg.getMaDG().replaceAll("\\s+", " ").trim());
        row.add(dg.getTenDG().replaceAll("\\s+", " ").trim());
        row.add(dg.getDiachi().replaceAll("\\s+", " ").trim());
        row.add(dg.getMail().replaceAll("\\s+", " ").trim());
        row.add(dg.getTinhtrangthue().replaceAll("\\s+", " ").trim());
        modelDG.addRow(row);
    }

    public void setTitleDG() {
        // set Border
        Border empty;
        empty = BorderFactory.createEmptyBorder();

        titleDG = BorderFactory.createTitledBorder(empty, "THÔNG TIN ĐỘC GIẢ");
        titleDG.setTitleFont(new Font("Arial", Font.BOLD, 28));
        titleDG.setTitleJustification(TitledBorder.CENTER);
        pnDG.setBorder(titleDG);
    }

    public void setInputDG(){
        lbMaDG = new JLabel("Mã độc giả");
        lbMaDG.setFont(new Font("Arial", Font.BOLD, 20));
        lbMaDG.setBounds(20, 0, 130, 80);

        lbTenDG = new JLabel("Tên Độc Giả:");
        lbTenDG.setFont(new Font("Arial", Font.BOLD, 18));
        lbTenDG.setBounds(20, 40, 130, 80);

        lbDiaChi = new JLabel("Địa chỉ:");
        lbDiaChi.setFont(new Font("Arial", Font.BOLD, 18));
        lbDiaChi.setBounds(20, 80, 130, 80);

        lbMail = new JLabel("Email:");
        lbMail.setFont(new Font("Arial", Font.BOLD, 18));
        lbMail.setBounds(20, 120, 130, 80);

        lbTInhTrangThue = new JLabel("Tình trạng thuê:");
        lbTInhTrangThue.setFont(new Font("Arial", Font.BOLD, 18));
        lbTInhTrangThue.setBounds(20, 160, 130, 80);

        txMaDG = new JTextField();
        txMaDG.setBounds(160, 25, 180, 30);
        txMaDG.setFont(new Font("Arial", Font.PLAIN, 15));
        txMaDG.addMouseListener(this);

        txTenDG = new JTextField();
        txTenDG.setBounds(160, 65, 180, 30);
        txTenDG.setFont(new Font("Arial", Font.PLAIN, 15));
        txTenDG.addMouseListener(this);

        txDiaChi = new JTextField();
        txDiaChi.setBounds(160, 105, 180, 30);
        txDiaChi.setFont(new Font("Arial", Font.PLAIN, 15));
        txDiaChi.addMouseListener(this);

        txMail = new JTextField();
        txMail.setBounds(160, 145, 180, 30);
        txMail.setFont(new Font("Arial", Font.PLAIN, 15));
        txMail.addMouseListener(this);
        
        String[] dsTinhTrangThue = { "", "Đang thuê", "Hết thuê" };
        cbTinhTrangThue = new JComboBox<>(dsTinhTrangThue);
        cbTinhTrangThue.setFont(new Font("Arial", Font.BOLD, 13));
        cbTinhTrangThue.setBounds(160, 185, 180, 30);
        cbTinhTrangThue.addActionListener(this);

        // JbuttonThem
        btThemDG = new JButton("Thêm");
        btThemDG.setFont(new Font("Arial", Font.BOLD, 15));
        btThemDG.setBounds(20, 220, 80, 30);
        btThemDG.setBackground(Color.cyan);
        btThemDG.setBorder(new RoundedBorder(10));
        btThemDG.addActionListener(this);
        // JbuttonSua
        btSuaDG = new JButton("Sửa");
        btSuaDG.setFont(new Font("Arial", Font.BOLD, 15));
        btSuaDG.setBounds(120, 220, 80, 30);
        btSuaDG.setBackground(Color.cyan);
        btSuaDG.setBorder(new RoundedBorder(10));
        btSuaDG.addActionListener(this);
        // JbuttonXoa
        btXoa = new JButton("Xóa");
        btXoa.setFont(new Font("Arial", Font.BOLD, 15));
        btXoa.setBounds(220, 220, 80, 30);
        btXoa.setBackground(Color.cyan);
        btXoa.setBorder(new RoundedBorder(10));
        btXoa.addActionListener(this);
        // JbuttonHoanTac
        btHoanTac = new JButton("Hoàn tác");
        btHoanTac.setFont(new Font("Arial", Font.BOLD, 15));
        btHoanTac.setBounds(320, 220, 80, 30);
        btHoanTac.setBackground(Color.cyan);
        btHoanTac.setBorder(new RoundedBorder(10));
        btHoanTac.addActionListener(this);

        pnNhapDG.add(lbMaDG);
        pnNhapDG.add(lbTenDG);
        pnNhapDG.add(lbDiaChi);
        pnNhapDG.add(lbMail);
        pnNhapDG.add(lbTInhTrangThue);
        pnNhapDG.add(txMaDG);
        pnNhapDG.add(txTenDG);
        pnNhapDG.add(txDiaChi);
        pnNhapDG.add(txMail);
        pnNhapDG.add(cbTinhTrangThue);

        pnNhapDG.add(btThemDG);
        pnNhapDG.add(btSuaDG);
        pnNhapDG.add(btXoa);
        pnNhapDG.add(btHoanTac);
    }

    public void setShowAll() {
        btShowAll = new JButton("Hiển thị tất cả");
        btShowAll.setFont(new Font("Arial", Font.BOLD, 15));
        btShowAll.setBounds(1000, 10, 130, 30);
        btShowAll.setBackground(Color.cyan);
        btShowAll.setBorder(new RoundedBorder(10));
        btShowAll.addActionListener(this);

        btSapXep = new JButton("Sắp xếp theo tên");
        btSapXep.setFont(new Font("Arial", Font.BOLD, 15));
        btSapXep.setBounds(820, 10, 150, 30);
        btSapXep.setBackground(Color.cyan);
        btSapXep.setBorder(new RoundedBorder(10));
        btSapXep.addActionListener(this);

        pnShowAll.add(btShowAll);
        pnShowAll.add(btSapXep);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btThemDG) {
            try {
                DOCGIA docgia = new DOCGIA();
                getInfoTextFieldDG(docgia);
                // Truy cập vào bus
               QLNVBUS qlnvbus = new QLNVBUS();
                int kiemtra = 0;
                try {
                    kiemtra = qlnvbus.them(docgia);
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
                if (kiemtra == 1) {
                    // Đưa dữ liệu lên table
                    header = new Vector<String>();
                    header.add("Mã Độc Giả");
                    header.add("Tên Độc Giả");
                    header.add("Địa Chỉ");
                    header.add("Email");
                    header.add("Tình Trạng Thuê");
                    if (modelDG.getRowCount() == 0) {
                        modelDG = new DefaultTableModel(header, 0);
                    }
                    ShowOnTableDG(docgia);
                    tblQLDG.setModel(modelDG);
                }
            } catch (Exception e1) {
                System.out.println(e1);
            }
        }
        //  else if (e.getSource() == btSuaDG) {
        //     int i = tblQLDG.getSelectedRow();
        //     int kt = -1;
        //     if (i >= 0) {
        //         DOCGIA docgia = new DOCGIA();
        //         DOCGIA madocgiacu = QLNVBUS.dsdg.set(i, docgia);
        //         getInfoTextFieldDG(docgia);
        //         try {
        //             QLNVBUS qlnvbus = new QLNVBUS();
        //             kt = qlnvbus.sua(docgia, madocgiacu, i);
        //         } catch (Exception e1) {
        //             System.out.println(e1);
        //         }
        //         if (kt == 0) {
        //             modelDG.setValueAt(docgia.getMaDG(), i, 0);
        //             modelDG.setValueAt(docgia.getTenDG(), i, 1);
        //             modelDG.setValueAt(docgia.getDiachi(), i, 2);
        //             modelDG.setValueAt(docgia.getMail(), i, 3);
        //             modelDG.setValueAt(docgia.getTinhtrangthue(), i, 4);
        //             tblQLDG.setModel(modelDG);
        //         }
        //     }
        // } else if (e.getSource() == btXoa) {
        //     int XacNhanXoa = JOptionPane.showConfirmDialog(null, "Bạn có chắc chắn muốn xóa không ?", "Thông báo",
        //             JOptionPane.YES_NO_OPTION);
        //     if (XacNhanXoa == 0) {
        //         int kt = -1;
        //         String madocgia = txMaDG.getText();
        //         int i = tblQLDG.getSelectedRow();
        //         if (i >= 0) {
        //             try {
        //                 // Truy cập xuống BUS
        //                 DOCGIA docgiaold = QLNVBUS.dsdg.get(i);
        //                 QLNVBUS.htXoa.add(docgiaold);
        //                 QLNVBUS qlnvbus = new QLNVBUS();
        //                 kt = qlnvbus.xoa(madocgia, i);
        //                 // Quay dề GUI

        //             } catch (Exception e1) {
        //                 System.out.println(e1);
        //             }
        //             if (kt == 0) {
        //                 modelDG.removeRow(i);
        //                 tblQLDG.setModel(modelDG);
        //             }
        //         }
        //     }
        // } 
        else if (e.getSource() == btHoanTac) {
            int ktHT = 0;
            if (QLNVBUS.htXoa.size() == 0) {
                JOptionPane.showMessageDialog(null, "Dữ liệu hoàn tác rỗng", "Lỗi",
                        JOptionPane.ERROR_MESSAGE);
            } else {
                for (DOCGIA docgia : QLNVBUS.htXoa) {
                    QLNVBUS qlnvbus = new QLNVBUS();
                    int kiemtra = 0;
                    try {
                        kiemtra = qlnvbus.hoantacXoa(docgia);
                    } catch (Exception e1) {
                        e1.printStackTrace();
                    }
                    if (kiemtra == 1) {
                        // Đưa dữ liệu lên table
                        header = new Vector<String>();
                        header.add("Mã Độc Giả");
                        header.add("Tên Độc Giả");
                        header.add("Địa Chỉ");
                        header.add("EMail");
                        header.add("Tình Trạng Thuê");
                        if (modelDG.getRowCount() == 0) {
                            modelDG = new DefaultTableModel(header, 0);
                        }
                        ShowOnTableDG(docgia);
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
                tblQLDG.setModel(modelDG);
            }
        // } else if (e.getSource() == btMenuTimKiem) { // Của button Tìm kiếm thể loại, để hiện thị
        //     // khung tìm kiếm
        //     OffBTBgSelected();
        //     btMenuTimKiem.setBackground(ColorPurple);
        //     setTimKiem();
        // } else if (e.getSource() == btSearch) {
            // int vtkey = Integer.parseInt(String.valueOf(comboBoxDSKhoaTK.getSelectedIndex()));
            // String tukhoa = txKhoaTK.getText();
            // lbKQTK.setFont(new Font("Arial", Font.BOLD, 20));
            // lbKQTK.setForeground(Color.red);
            // if (tukhoa.equals("") == true) {
            //     JOptionPane.showMessageDialog(null, "Xin mời nhập từ khóa", "Lỗi", JOptionPane.ERROR_MESSAGE);
            // } else if (vtkey == 0) {
            //     JOptionPane.showMessageDialog(null, "Xin mời lựa chọn khóa tìm kiếm", "Lỗi", JOptionPane.ERROR_MESSAGE);
            // } else {
            //     QLTHELOAIBUS qltheloaibus = new QLTHELOAIBUS();
            //     if (vtkey == 1) {
            //         THELOAI kq = qltheloaibus.timTheoMa(tukhoa);
            //         model.setRowCount(0);
            //         if (kq == null) {
            //             lbKQTK.setText("Kết quả tìm kiếm: Không tìm thấy");
            //         } else {
            //             ShowOnTableDG(kq);
            //             lbKQTK.setText("Kết quả tìm kiếm: Tìm thấy " + model.getRowCount() + " THELOAI");
            //             tblQLTHELOAI.setModel(model);
            //         }
            //     } else if (vtkey == 2) {
            //         ArrayList<THELOAI> kq = qltheloaibus.timTheoTen(tukhoa);
            //         model.setRowCount(0);
            //         if (kq.size() == 0) {
            //             lbKQTK.setText("Kết quả tìm kiếm: Không tìm thấy");
            //         } else {
            //             for (THELOAI theloai : kq) {
            //                 ShowOnTableDG(theloai);
            //             }
            //             lbKQTK.setText("Kết quả tìm kiếm: Tìm thấy " + model.getRowCount() + " THELOAI");
            //             tblQLTHELOAI.setModel(model);
            //         }
            //     }
            // }
        // } 
        else if (e.getSource() == btShowAll) {
            lbKQTK.setText("");
            modelDG.setRowCount(0);
            for (DOCGIA docgia : QLNVBUS.dsdg) {
                ShowOnTableDG(docgia);
            }
            tblQLDG.setModel(modelDG);
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
    }

    private void dispose() {
    }

    public void SapXep() {
        QLNVBUS.dsdg.sort((o1, o2) -> o1.getMaDG().compareTo(o2.getTenDG()));
        modelDG.setRowCount(0);
        for (DOCGIA theloai : QLNVBUS.dsdg) {
            ShowOnTableDG(theloai);
        }
        tblQLDG.setModel(modelDG);
    }

    public void ShowOnTableDG(DOCGIA docgia) {
        Vector<String> row = new Vector<String>();
        row.add(docgia.getMaDG().replaceAll("\\s", "").trim());
        row.add(docgia.getTenDG().replaceAll("\\s", "").trim());
        row.add(docgia.getDiachi().replaceAll("\\s", "").trim());
        row.add(docgia.getMail().replaceAll("\\s", "").trim());
        row.add(docgia.getTinhtrangthue().replaceAll("\\s", "").trim());
        modelDG.addRow(row);
    }

    public void getInfoTextFieldDG(DOCGIA docgia) {
        docgia.setMaDG(txMaDG.getText().replaceAll("\\s", "").trim());
        docgia.setTenDG(txTenDG.getText().replaceAll("\\s", "").trim());
        docgia.setDiachi(txDiaChi.getText().replaceAll("\\s", "").trim());
        docgia.setMail(txMail.getText().replaceAll("\\s", "").trim());
        docgia.setTinhtrangthue(txTinhTrangThue.getText().replaceAll("\\s", "").trim());
    }
}  

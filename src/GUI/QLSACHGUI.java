package GUI;
import java.awt.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Vector;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import DTO.SACH;
import lib.RoundedBorder;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class QLSACHGUI extends JFrame implements ActionListener, MouseListener {
    JPanel pnTTSach, pnNhapTTSach, pnShowAll, pnMenu;
    JLabel lbMenu, lbTTSach, lbMasach, lbTensach, lbMaNXB, lbMaTG, lbNamXB, lbSLtong, lbSL, lbDongia, lbTimKiem, lbLCTK,
            lbTuKhoaTK, lbKQTK;
    JTextField txMasach, txTensach, txMaNXB, txMaTG, txNamXB, txSLtong, txSL, txDongia, txKhoaTK;
    JButton btDoc, btThem, btSua, btXoa, btTimKiem, btShowAll, btThongKe;
    JButton btQLSACH, btQLNXB, btQLTHELOAI, btDangXuat, btThoat;
    JRadioButton rbNam, rbNu, rbKhac;
    ButtonGroup buttonGroupGT;
    JComboBox<String> comboBoxDSKhoaTK;

    public static ArrayList<SACH> dssach = new ArrayList<SACH>();
    JTable tblQLSACH;
    DefaultTableModel model;
    Vector<String> header, row, row1, row2;
    Connection conn = null;
    Statement st = null;
    ResultSet rs = null;

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
        pnMenu.setBounds(0, 0, 300, 1537);

        // add components
        this.add(pnMenu);
        this.add(pnShowAll);
        this.add(pnNhapTTSach);

        setTableSach();
        setInput();
        setMenu();
        setShowAll();

        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btDoc) {
            try {
                dssach.clear();
                String user = "sa";
                String password = "sa";
                String url = "jdbc:sqlserver://DESKTOP-5IRG803\\SQLEXPRESS:1433;databaseName=QLTV;trustServerCertificate=true;integratedSecurity=true;";
                Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                conn = DriverManager.getConnection(url, user, password);
                String qry = "select * from SACH";
                st = conn.createStatement();
                rs = st.executeQuery(qry);
                while (rs.next()) {
                    SACH sach = new SACH();
                    sach.setMasach(rs.getString(1));
                    sach.setTensach(rs.getString(2));
                    sach.setMaNXB(rs.getString(3));
                    sach.setMaTG(rs.getString(4));
                    sach.setNamXB(rs.getString(5));
                    sach.setSLtong(Integer.valueOf(rs.getString(6)));
                    sach.setSL(Integer.valueOf(rs.getString(7)));
                    sach.setDongia(Integer.valueOf(rs.getString(8)));
                    dssach.add(sach);
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
                for (SACH sach : dssach) {
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
                    tblQLSACH.setModel(model);
                }
            } catch (Exception e1) {
                System.out.println(e1);
            }
        } else if (e.getSource() == btThem) {
            String ktra = txMasach.getText();
            if (KTMa(ktra) == 0) {
                JOptionPane.showMessageDialog(null, "Mã sách vừa nhập bị trùng", "Lỗi", JOptionPane.ERROR_MESSAGE);
            } else {
                try {
                    SACH sach = new SACH();
                    sach.setMasach(txMasach.getText());
                    sach.setTensach(txTensach.getText());
                    sach.setMaNXB(txMaNXB.getText());
                    sach.setMaTG(txMaTG.getText());
                    sach.setNamXB(String.valueOf(txNamXB.getText()));
                    sach.setSLtong(Integer.parseInt(txSLtong.getText()));
                    sach.setSL(Integer.parseInt(txSL.getText()));
                    sach.setDongia(Integer.parseInt(txDongia.getText()));
                    dssach.add(sach);

                    String user = "sa";
                    String password = "sa";
                    String url = "jdbc:sqlserver://DESKTOP-5IRG803\\SQLEXPRESS:1433;databaseName=QLTV;trustServerCertificate=true;integratedSecurity=true;";
                    Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                    conn = DriverManager.getConnection(url, user, password);
                    String qry = "insert into SACH values  (" + "'" + sach.getMasach() + "'"
                            + "," + "N'" + sach.getTensach() + "'" + "," + "'" + sach.getMaNXB() + "'" + ","
                            + "'" + sach.getMaTG() + "'" + "," + "'" + sach.getNamXB() + "'" + ","
                            + "N'" + String.valueOf(sach.getSLtong()) + "'" + "," + "'"
                            + String.valueOf(sach.getSL()) + "'" + "," + "'" + String.valueOf(sach.getDongia()) + "'"
                            + ")";
                    st = conn.createStatement();
                    st.executeUpdate(qry);

                    if (model.getRowCount() == 0) {
                        model = new DefaultTableModel(header, 0);
                    }
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
                    tblQLSACH.setModel(model);
                } catch (Exception e1) {
                    System.out.println(e1);
                }
            }
        } else if (e.getSource() == btSua) {
            int i = tblQLSACH.getSelectedRow();
            if (i >= 0) {
                SACH sach = new SACH();
                SACH masachCu = dssach.set(i, sach);
                sach.setMasach(txMasach.getText());
                sach.setTensach(txTensach.getText());
                sach.setMaNXB(txMaNXB.getText());
                sach.setMaTG(txMaTG.getText());
                sach.setNamXB(txNamXB.getText());
                sach.setSLtong(Integer.parseInt(txSLtong.getText()));
                sach.setSL(Integer.parseInt(txSL.getText()));
                sach.setDongia(Integer.parseInt(txDongia.getText()));
                try {
                    String user = "sa";
                    String password = "sa";
                    String url = "jdbc:sqlserver://DESKTOP-5IRG803\\SQLEXPRESS:1433;databaseName=QLTV;trustServerCertificate=true;integratedSecurity=true;";
                    Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                    conn = DriverManager.getConnection(url, user, password);
                    String qry = "update SACH set " + "MASACH=" + "'" + sach.getMasach() + "'" +
                            ",TENSACH=" + "N'" + sach.getTensach() + "'" + ",MANXB=" + "'" + sach.getMaNXB() + "'" +
                            ",MATG=" + "'" + sach.getMaTG() + "'" + ",NAMXB=" + "'" + sach.getNamXB() + "'" +
                            ",SLTONG=" + "'" + sach.getSLtong() + "'" + ",SL=" + "'" + sach.getSL() + "'" +
                            ",DONGIA=" + "'" + sach.getDongia() + "'" + " " + "where MASACH='" + masachCu.getMasach()
                            + "'";
                    st = conn.createStatement();
                    st.executeUpdate(qry);

                    SACH old = dssach.set(i, masachCu);
                    dssach.set(i, sach);
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
                    String user = "sa";
                    String password = "sa";
                    String url = "jdbc:sqlserver://DESKTOP-5IRG803\\SQLEXPRESS:1433;databaseName=QLTV;trustServerCertificate=true;integratedSecurity=true;";
                    Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                    conn = DriverManager.getConnection(url, user, password);
                    String qry = "delete from SACH where MASACH='" + masach + "'";
                    st = conn.createStatement();
                    st.executeUpdate(qry);
                    dssach.remove(i);
                    model.removeRow(i);
                    tblQLSACH.setModel(model);
                } catch (Exception e1) {
                    System.out.println(e1);
                }
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
            if (vtkey == 1) {
                SACH kq = timTheoMa(tukhoa);
                model.setRowCount(0);
                if (kq == null) {
                    lbKQTK.setText("Kết quả tìm kiếm: Không tìm thấy");
                } else {
                    Vector<String> row = new Vector<String>();
                    row.add(kq.getMasach());
                    row.add(kq.getTensach());
                    row.add(kq.getMaNXB());
                    row.add(kq.getMaTG());
                    row.add(kq.getNamXB());
                    row.add(String.valueOf(kq.getSLtong()));
                    row.add(String.valueOf(kq.getSL()));
                    row.add(String.valueOf(kq.getDongia()));
                    model.addRow(row);
                    lbKQTK.setText("Kết quả tìm kiếm: Tìm thấy " + model.getRowCount() + " SV");
                    tblQLSACH.setModel(model);
                }
            } else if (vtkey == 2) {
                ArrayList<SACH> kq = timTheoTen(tukhoa);
                model.setRowCount(0);
                if (kq.size() == 0) {
                    lbKQTK.setText("Kết quả tìm kiếm: Không tìm thấy");
                } else {
                    for (SACH sach : kq) {
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
                    lbKQTK.setText("Kết quả tìm kiếm: Tìm thấy " + model.getRowCount() + " SV");
                    tblQLSACH.setModel(model);
                }
            } else if (vtkey == 3) {
                ArrayList<SACH> kq = timTheoMaNXB(tukhoa);
                model.setRowCount(0);
                if (kq.size() == 0) {
                    lbKQTK.setText("Kết quả tìm kiếm: Không tìm thấy");
                } else {
                    for (SACH sach : kq) {
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
                    lbKQTK.setText("Kết quả tìm kiếm: Tìm thấy " + model.getRowCount() + " SV");
                    tblQLSACH.setModel(model);
                }
            } else if (vtkey == 4) {
                ArrayList<SACH> kq = timTheoMaTG(tukhoa);
                model.setRowCount(0);
                if (kq.size() == 0) {
                    lbKQTK.setText("Kết quả tìm kiếm: Không tìm thấy");
                } else {
                    for (SACH sach : kq) {
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
                    lbKQTK.setText("Kết quả tìm kiếm: Tìm thấy " + model.getRowCount() + " SV");
                    tblQLSACH.setModel(model);
                }
            } else if (vtkey == 5) {
                ArrayList<SACH> kq = timTheoNamXB(tukhoa);
                model.setRowCount(0);
                if (kq.size() == 0) {
                    lbKQTK.setText("Kết quả tìm kiếm: Không tìm thấy");
                } else {
                    for (SACH sach : kq) {
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
                    lbKQTK.setText("Kết quả tìm kiếm: Tìm thấy " + model.getRowCount() + " SV");
                    tblQLSACH.setModel(model);
                }
            } else if (vtkey == 6) {
                ArrayList<SACH> kq = timTheoSLtong(tukhoa);
                model.setRowCount(0);
                if (kq.size() == 0) {
                    lbKQTK.setText("Kết quả tìm kiếm: Không tìm thấy");
                } else {
                    for (SACH sach : kq) {
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
                    lbKQTK.setText("Kết quả tìm kiếm: Tìm thấy " + model.getRowCount() + " SV");
                    tblQLSACH.setModel(model);
                }
            } else if (vtkey == 7) {
                ArrayList<SACH> kq = timTheoSL(tukhoa);
                model.setRowCount(0);
                if (kq.size() == 0) {
                    lbKQTK.setText("Kết quả tìm kiếm: Không tìm thấy");
                } else {
                    for (SACH sach : kq) {
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
                    lbKQTK.setText("Kết quả tìm kiếm: Tìm thấy " + model.getRowCount() + " SV");
                    tblQLSACH.setModel(model);
                }
            } else if (vtkey == 8) {
                ArrayList<SACH> kq = timTheoDonGia(tukhoa);
                model.setRowCount(0);
                if (kq.size() == 0) {
                    lbKQTK.setText("Kết quả tìm kiếm: Không tìm thấy");
                } else {
                    for (SACH sach : kq) {
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
                    lbKQTK.setText("Kết quả tìm kiếm: Tìm thấy " + model.getRowCount() + " SV");
                    tblQLSACH.setModel(model);
                }
            }
        } else if (e.getSource() == btShowAll) {
            lbKQTK.setText("");
            model.setRowCount(0);
            for (SACH sach : dssach) {
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
            tblQLSACH.setModel(model);
        }
        if (e.getSource() == btThongKe) {
            // String luachon = "";
            // int count;
            // do{
            // String[] options = {"", "Tuổi", "Phái", "Năm sinh", "Ngày sinh","Năm sinh và
            // Phái"};
            // luachon = (String) JOptionPane.showInputDialog(null,"Mời lựa chọn khóa thống
            // kê:", "Thống kê", JOptionPane.INFORMATION_MESSAGE,null,options,options[0]);
            // switch(luachon){
            // case "Tuổi":
            // String tuoi = "";
            // while(tuoi!=null){
            // tuoi = (String) JOptionPane.showInputDialog(null,"Nhập tuổi:","Thống
            // kê",JOptionPane.INFORMATION_MESSAGE);
            // Calendar calendar = Calendar.getInstance();
            // int thisYear = calendar.get(Calendar.YEAR); // 2022
            // int namsinh = thisYear - Integer.parseInt(tuoi);
            // count = 0;
            // for(SACH sach: dssach){
            // String tmp[] = sach.getNamsinh().split("/");
            // if(tmp[2].equals(String.valueOf(namsinh))){
            // count++;
            // }
            // }
            // if(tuoi!=null)
            // JOptionPane.showMessageDialog(null,"Đếm số sv theo tuổi: " +
            // String.valueOf(count),"Kết quả",JOptionPane.PLAIN_MESSAGE);
            // }
            // break;
            // case "Phái":
            // String phai = "";
            // while(phai!=null){
            // phai = (String) JOptionPane.showInputDialog(null,"Nhập phái:","Thống
            // kê",JOptionPane.INFORMATION_MESSAGE);
            // count = 0;
            // for(SinhVien sv: dssach){
            // if(sv.getGioitinh().equalsIgnoreCase(phai)){
            // count++;
            // }
            // }
            // if(phai!=null)
            // JOptionPane.showMessageDialog(null,"Đếm số sv theo phái: " +
            // String.valueOf(count),"Kết quả",JOptionPane.PLAIN_MESSAGE);
            // }
            // break;
            // case "Năm sinh":
            // String namsinh = "";
            // while(namsinh!=null){
            // namsinh = (String) JOptionPane.showInputDialog(null,"Nhập năm sinh:","Thống
            // kê",JOptionPane.INFORMATION_MESSAGE);
            // count = 0;
            // for(SinhVien sv: dssach){
            // String tmp[] = sv.getNamsinh().split("/");
            // if(tmp[2].equals(namsinh)){
            // count++;
            // }
            // }
            // if(namsinh!=null)
            // JOptionPane.showMessageDialog(null,"Đếm số sv theo năm sinh: " +
            // String.valueOf(count),"Kết quả",JOptionPane.PLAIN_MESSAGE);
            // }
            // break;
            // case "Ngày sinh":
            // count = 0;
            // String ngayA = (String) JOptionPane.showInputDialog(null,"Nhập ngày
            // BĐ:","Thống kê",JOptionPane.INFORMATION_MESSAGE);
            // String thangA = (String) JOptionPane.showInputDialog(null,"Nhập tháng
            // BĐ:","Thống kê",JOptionPane.INFORMATION_MESSAGE);
            // String namA = (String) JOptionPane.showInputDialog(null,"Nhập năm BĐ:","Thống
            // kê",JOptionPane.INFORMATION_MESSAGE);
            // String ngayB = (String) JOptionPane.showInputDialog(null,"Nhập ngày
            // KT:","Thống kê",JOptionPane.INFORMATION_MESSAGE);
            // String thangB = (String) JOptionPane.showInputDialog(null,"Nhập tháng
            // KT:","Thống kê",JOptionPane.INFORMATION_MESSAGE);
            // String namB = (String) JOptionPane.showInputDialog(null,"Nhập năm KT:","Thống
            // kê",JOptionPane.INFORMATION_MESSAGE);
            // for(SinhVien sv: dssach){
            // String tmp = sv.getNamsinh();
            // String[] tmp1;
            // tmp1 = tmp.split("/");
            // String ngaySV = tmp1[0];
            // String thangSV = tmp1[1];
            // String namSV = tmp1[2];
            // if(Integer.parseInt(ngaySV) >= Integer.parseInt(ngayA) &&
            // Integer.parseInt(ngaySV) <= Integer.parseInt(ngayB) &&
            // Integer.parseInt(thangSV) >= Integer.parseInt(thangA) &&
            // Integer.parseInt(thangSV) <= Integer.parseInt(thangB) &&
            // Integer.parseInt(namSV) >= Integer.parseInt(namA) &&
            // Integer.parseInt(namSV) <= Integer.parseInt(namB)){
            // count++;
            // }
            // }
            // JOptionPane.showMessageDialog(null,"Đếm số sv theo ngày A, ngày B: " +
            // String.valueOf(count),"Kết quả",JOptionPane.PLAIN_MESSAGE);
            // break;
            // case "Năm sinh và Phái":
            // try {
            // this.dispose();
            // new BaoCaoThongKe();
            // this.wait();
            // } catch (InterruptedException e2) {
            // System.out.println(e2);
            // }
            // break;
            // }
            // }while(luachon!=null);
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource() == tblQLSACH) {
            int i = tblQLSACH.getSelectedRow();
            if (i >= 0) {
                SACH sach = new SACH();
                sach = dssach.get(i);
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

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    public int KTMa(String MaSachMoi) {
        for (SACH sach : dssach)
            if (sach.getMasach().equals(MaSachMoi)) {
                return 0;
            }
        return 1;
    }

    public SACH timTheoMa(String Masach) {
        for (SACH sach : dssach)
            if (sach.getMasach().equals(Masach))
                return sach;
        return null;
    }

    public ArrayList<SACH> timTheoTen(String Tensach) {
        ArrayList<SACH> kq = new ArrayList<SACH>();
        for (SACH sach : dssach)
            if (sach.getTensach().indexOf(Tensach) >= 0)
                kq.add(sach);
        return kq;
    }

    public ArrayList<SACH> timTheoMaNXB(String MaNXB) {
        ArrayList<SACH> kq = new ArrayList<SACH>();
        for (SACH sach : dssach)
            if (sach.getMaNXB().indexOf(MaNXB) >= 0)
                kq.add(sach);
        return kq;
    }

    public ArrayList<SACH> timTheoMaTG(String MaTG) {
        ArrayList<SACH> kq = new ArrayList<SACH>();
        for (SACH sach : dssach)
            if (sach.getMaTG().indexOf(MaTG) >= 0)
                kq.add(sach);
        return kq;
    }

    public ArrayList<SACH> timTheoNamXB(String NamXB) {
        ArrayList<SACH> kq = new ArrayList<SACH>();
        for (SACH sach : dssach)
            if (sach.getNamXB().indexOf(NamXB) >= 0)
                kq.add(sach);
        return kq;
    }

    public ArrayList<SACH> timTheoSLtong(String SLtong) {
        ArrayList<SACH> kq = new ArrayList<SACH>();
        for (SACH sach : dssach)
            if (String.valueOf(sach.getSLtong()).indexOf(SLtong) >= 0)
                kq.add(sach);
        return kq;
    }

    public ArrayList<SACH> timTheoSL(String SL) {
        ArrayList<SACH> kq = new ArrayList<SACH>();
        for (SACH sach : dssach)
            if (String.valueOf(sach.getSL()).indexOf(SL) >= 0)
                kq.add(sach);
        return kq;
    }

    public ArrayList<SACH> timTheoDonGia(String Dongia) {
        ArrayList<SACH> kq = new ArrayList<SACH>();
        for (SACH sach : dssach)
            if (String.valueOf(sach.getDongia()).indexOf(Dongia) >= 0)
                kq.add(sach);
        return kq;
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
        lbTimKiem.setBounds(870, 80, 200, 100);
        // labelLCTK
        lbLCTK = new JLabel("Lựa chọn khóa tìm kiếm:");
        lbLCTK.setFont(new Font("Arial", Font.BOLD, 20));
        lbLCTK.setBounds(670, 130, 250, 100);
        // labelTuKhoaTK
        lbTuKhoaTK = new JLabel("Nhập từ khóa tìm kiếm:");
        lbTuKhoaTK.setFont(new Font("Arial", Font.BOLD, 20));
        lbTuKhoaTK.setBounds(670, 180, 250, 100);

        // JTextField Mã sách
        txMasach = new JTextField();
        txMasach.setBounds(270, 30, 200, 35);
        txMasach.setFont(new Font("Arial", Font.PLAIN, 15));
        // JTextField Tên sách
        txTensach = new JTextField();
        txTensach.setBounds(270, 80, 200, 35);
        txTensach.setFont(new Font("Arial", Font.PLAIN, 15));
        // JTextField Mã nhà xuất bản
        txMaNXB = new JTextField();
        txMaNXB.setBounds(270, 130, 200, 35);
        txMaNXB.setFont(new Font("Arial", Font.PLAIN, 15));
        // JTextField Mã tác giả
        txMaTG = new JTextField();
        txMaTG.setBounds(270, 180, 200, 35);
        txMaTG.setFont(new Font("Arial", Font.PLAIN, 15));
        // JTextField Năm xuất bản
        txNamXB = new JTextField();
        txNamXB.setBounds(270, 230, 200, 35);
        txNamXB.setFont(new Font("Arial", Font.PLAIN, 15));
        // JTextField SL tổng
        txSLtong = new JTextField();
        txSLtong.setBounds(270, 280, 200, 35);
        txSLtong.setFont(new Font("Arial", Font.PLAIN, 15));
        // JTextField SL
        txSL = new JTextField();
        txSL.setBounds(620, 30, 200, 35);
        txSL.setFont(new Font("Arial", Font.PLAIN, 15));
        // JTextField Đơn giá
        txDongia = new JTextField();
        txDongia.setBounds(620, 80, 200, 35);
        txDongia.setFont(new Font("Arial", Font.PLAIN, 15));
        // JTextField Khóa tìm kiếm
        txKhoaTK = new JTextField();
        txKhoaTK.setBounds(920, 210, 200, 35);

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
        btThem.setBounds(230, 330, 100, 40);
        btThem.setBackground(Color.cyan);
        btThem.setBorder(new RoundedBorder(10));
        btThem.addActionListener(this);
        // JbuttonSua
        btSua = new JButton("Sửa");
        btSua.setFont(new Font("Arial", Font.BOLD, 15));
        btSua.setBounds(340, 330, 100, 40);
        btSua.setBackground(Color.cyan);
        btSua.setBorder(new RoundedBorder(10));
        btSua.addActionListener(this);
        // JbuttonXoa
        btXoa = new JButton("Xóa");
        btXoa.setFont(new Font("Arial", Font.BOLD, 15));
        btXoa.setBounds(450, 330, 100, 40);
        btXoa.setBackground(Color.cyan);
        btXoa.setBorder(new RoundedBorder(10));
        btXoa.addActionListener(this);
        // JbuttonTimKiem
        btTimKiem = new JButton("Tìm kiếm");
        btTimKiem.setFont(new Font("Arial", Font.BOLD, 15));
        btTimKiem.setBounds(1130, 210, 110, 34);
        btTimKiem.setBackground(Color.cyan);
        btTimKiem.setBorder(new RoundedBorder(10));
        btTimKiem.addActionListener(this);

        // set up ComboBox
        String[] dsKhoaTK = { "", "Mã sách", "Tên sách", "Mã NXB", "Mã TG", "Năm XB", "SL tổng", "SL", "Đơn giá" };
        comboBoxDSKhoaTK = new JComboBox<>(dsKhoaTK);
        comboBoxDSKhoaTK.setFont(new Font("Arial", Font.BOLD, 13));
        comboBoxDSKhoaTK.setBounds(920, 160, 120, 35);

        // JButtonThongKe
        btThongKe = new JButton("THỐNG KÊ");
        btThongKe.setFont(new Font("Arial", Font.BOLD, 20));
        btThongKe.setBounds(670, 265, 150, 55);
        btThongKe.setBackground(Color.cyan);
        btThongKe.setBorder(new RoundedBorder(10));
        btThongKe.addActionListener(this);

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

        pnMenu.add(lbMenu);
        pnMenu.add(btQLSACH);
        pnMenu.add(btQLNXB);
        pnMenu.add(btQLTHELOAI);
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
}

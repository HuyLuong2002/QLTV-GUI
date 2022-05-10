package MyCustom;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Vector;
import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import QLTV.BUS.QLMUONBUS;
import QLTV.BUS.QLNHANVIENBUS;
import QLTV.BUS.QLNVBUS;
import QLTV.BUS.QLSACHBUS;
import QLTV.BUS.QLTRABUS;
import QLTV.DTO.DOCGIA;
import QLTV.DTO.NHANVIEN;
import QLTV.DTO.PHIEUMUON;
import QLTV.DTO.PHIEUTRASACH;
import QLTV.DTO.SACH;
import QLTV.GUI.QLMTGUI;

public class BaoCaoThongKe implements ActionListener {
    JPanel pnThongKe, pnSach, pnKhachHang, pnNhanVien, pnTongDoanhThu, pnBaoCaoDoanhThu;
    JLabel lbThongKe, lbKQSach, lbKQKH, lbKQNV, lbKQDT;
    JComboBox<String> cbKhoaThongKe;
    JButton btXemThem;

    JTable tblDoanhThu;
    DefaultTableModel model;
    Vector<String> header;

    public BaoCaoThongKe() {

    }

    public JPanel BangBaoCaoThongKe() {
        if (pnThongKe == null) {
            LineBorder line = new LineBorder(Color.black, 3, true);

            pnThongKe = new JPanel();
            pnThongKe.setBounds(240, 0, 1145, 800);
            pnThongKe.setLayout(null);

            pnSach = new JPanel();
            pnSach.setBounds(200, 80, 300, 180);

            pnSach.setBorder(line);
            pnSach.setLayout(null);

            pnKhachHang = new JPanel();
            pnKhachHang.setBounds(200, 310, 300, 180);
            pnKhachHang.setBorder(line);
            pnKhachHang.setLayout(null);

            pnNhanVien = new JPanel();
            pnNhanVien.setBounds(600, 80, 300, 180);
            pnNhanVien.setBorder(line);
            pnNhanVien.setLayout(null);

            pnTongDoanhThu = new JPanel();
            pnTongDoanhThu.setBounds(600, 310, 300, 180);
            pnTongDoanhThu.setBorder(line);
            pnTongDoanhThu.setLayout(null);

            pnBaoCaoDoanhThu = new JPanel();
            pnBaoCaoDoanhThu.setBounds(200, 550, 700, 100);
            pnBaoCaoDoanhThu.setBorder(line);
            pnBaoCaoDoanhThu.setLayout(new GridLayout(1, 1));

            lbThongKe = new JLabel("THỐNG KÊ TỔNG QUÁT");
            lbThongKe.setFont(new Font("Arial", Font.BOLD, 30));
            lbThongKe.setBounds(400, 0, 380, 80);

            String[] dsNam = { "", "2021", "2022", "2023" };
            cbKhoaThongKe = new JComboBox<>(dsNam);
            cbKhoaThongKe.setFont(new Font("Arial", Font.BOLD, 13));
            cbKhoaThongKe.setBounds(500, 500, 100, 30);
            cbKhoaThongKe.addActionListener(this);

            ImageIcon iconXemTheme = new ImageIcon("images\\right-arrow.png");
            btXemThem = new JButton();
            btXemThem.setIcon(iconXemTheme);
            btXemThem.setFont(new Font("Arial", Font.BOLD, 15));
            btXemThem.setBounds(1000, 700, 80, 40);
            btXemThem.setBackground(Color.cyan);
            btXemThem.setBorder(new RoundedBorder(10));
            btXemThem.addActionListener(this);

            pnThongKe.add(pnSach);
            pnThongKe.add(pnKhachHang);
            pnThongKe.add(pnNhanVien);
            pnThongKe.add(pnTongDoanhThu);
            pnThongKe.add(pnBaoCaoDoanhThu);
            pnThongKe.add(lbThongKe);
            pnThongKe.add(cbKhoaThongKe);
            pnThongKe.add(btXemThem);

            setTKSach();
            setTKDT();
            setTKNV();
            setTKDG();
            setTableThongKe();
        }
        return pnThongKe;
    }

    public void setTKSach() {
        int sumSach = 0;
        for (SACH sach : QLSACHBUS.dssach) {
            sumSach = sumSach + sach.getSL();
        }
        if (sumSach != 0) {
            JLabel lbKQ = new JLabel();
            lbKQSach = new JLabel("Sách trong kho hiện tại");
            lbKQSach.setFont(new Font("Arial", Font.BOLD, 20));
            lbKQSach.setBounds(8, 80, 250, 80);
            lbKQ.setText(String.valueOf(sumSach));
            lbKQ.setFont(new Font("Arial", Font.BOLD, 35));
            lbKQ.setBounds(50, 30, 250, 80);
            pnSach.add(lbKQSach);
            pnSach.add(lbKQ);
        }
    }

    public void setTKDG(){
        int countDG = 0;
        QLNVBUS qlnvbus = new QLNVBUS();
        if (QLNVBUS.dsdg == null)
            try {
                qlnvbus.docDS();
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        for (DOCGIA dg : QLNVBUS.dsdg) {
            if(dg.getMaDG().trim().isEmpty()==false) {
                countDG++;
            }
        }
        if (countDG != 0) {
            JLabel lbKQ = new JLabel();
            lbKQKH = new JLabel("Số lượng độc giả");
            lbKQKH.setFont(new Font("Arial", Font.BOLD, 20));
            lbKQKH.setBounds(8, 80, 250, 80);
            lbKQ.setText(String.valueOf(countDG));
            lbKQ.setFont(new Font("Arial", Font.BOLD, 35));
            lbKQ.setBounds(50, 30, 250, 80);
            pnKhachHang.add(lbKQKH);
            pnKhachHang.add(lbKQ);
        }
    }

    public void setTKNV(){
        int countNV = 0;
        QLNHANVIENBUS qlnhanvienbus = new QLNHANVIENBUS();
        if (QLNHANVIENBUS.dsnhanvien == null)
        try {
            qlnhanvienbus.docDSNHANVIEN();
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        for (NHANVIEN nv : QLNHANVIENBUS.dsnhanvien) {
            if(nv.getMaNV().trim().isEmpty()==false) {
                countNV++;
            }
        }
        if (countNV != 0) {
            JLabel lbKQ = new JLabel();
            lbKQNV = new JLabel("Số lượng nhân viên");
            lbKQNV.setFont(new Font("Arial", Font.BOLD, 20));
            lbKQNV.setBounds(8, 80, 250, 80);
            lbKQ.setText(String.valueOf(countNV));
            lbKQ.setFont(new Font("Arial", Font.BOLD, 35));
            lbKQ.setBounds(50, 30, 250, 80);
            pnNhanVien.add(lbKQNV);
            pnNhanVien.add(lbKQ);
        }
    }

    public void setTKDT() {
        int TongDoanhThu = 0;
        // Tính tổng doanh thu
        if (QLTRABUS.dspt == null) {
            QLTRABUS qltrabus = new QLTRABUS();
            try {
                qltrabus.docDSPT();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        for (PHIEUTRASACH pt : QLTRABUS.dspt) {
            TongDoanhThu += pt.getThanhtien();
        }
        if (TongDoanhThu != 0) {
            JLabel lbKQ = new JLabel();
            lbKQDT = new JLabel("Tổng doanh thu");
            lbKQDT.setFont(new Font("Arial", Font.BOLD, 20));
            lbKQDT.setBounds(25, 80, 250, 80);
            lbKQ.setText(String.format("%,d", TongDoanhThu));
            lbKQ.setFont(new Font("Arial", Font.BOLD, 35));
            lbKQ.setBounds(40, 30, 250, 80);
            pnTongDoanhThu.add(lbKQDT);
            pnTongDoanhThu.add(lbKQ);
        }
    }

    public void setTableThongKe() {
        tblDoanhThu = new JTable();
        tblDoanhThu.getTableHeader().setBackground(new Color(32, 136, 203));
        tblDoanhThu.getTableHeader().setForeground(new Color(255,255,255));
        tblDoanhThu.getTableHeader().setFont(new Font("Arial", Font.BOLD, 18));
        tblDoanhThu.setRowHeight(30);
        tblDoanhThu.setFont(new Font("Arial", Font.BOLD, 15));
        tblDoanhThu.setDefaultEditor(Object.class, null);
        model = new DefaultTableModel(new Object[][] {
                { "Doanh thu", null, null, null, null },
                { "Tổng cộng", null, null, null, null }
        },
                new String[] {
                        "Quý", "Quý 1", "Quý 2", "Quý 3", "Quý 4", "Tổng cộng"
                });
        tblDoanhThu.setSelectionBackground(Color.GREEN);
        tblDoanhThu.setModel(model);
        QLMTGUI qlmtgui = new QLMTGUI();
        qlmtgui.setValueCellCenter(model, tblDoanhThu);
        pnBaoCaoDoanhThu.add(new JScrollPane(tblDoanhThu));

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == cbKhoaThongKe) {
            // Tính doanh thu mỗi quý
            int DoanhThuQuy1 = 0, DoanhThuQuy2 = 0, DoanhThuQuy3 = 0, DoanhThuQuy4 = 0, TongDoanhThu = 0;
            int vtkey = cbKhoaThongKe.getSelectedIndex();
            String NamThongKe = cbKhoaThongKe.getItemAt(vtkey);
            QLMUONBUS qlmuonbus = new QLMUONBUS();
            QLMTGUI qlmtgui = new QLMTGUI();
            ArrayList<PHIEUMUON> kq = new ArrayList<PHIEUMUON>();
            // Quý 1
            kq = qlmuonbus.getPMTheoQuy1(NamThongKe);
            for (PHIEUMUON pm : kq) {
                String MaPM = pm.getMaPM();
                for (PHIEUTRASACH pt : QLTRABUS.dspt) {
                    if (pt.getMaPM().equals(MaPM)) {
                        String tmp[] = pt.getNgaytra().split("-");
                        String tmp1[] = pm.getNgaymuon().split("-");
                        int songay = Integer.parseInt(tmp[2]) - Integer.parseInt(tmp1[2]);
                        if (songay <= 15) {
                            DoanhThuQuy1 = DoanhThuQuy1 + pt.getTienthue() * songay;
                        } else {
                            DoanhThuQuy1 = DoanhThuQuy1 + pt.getTienthue() * songay
                                    + (pt.getTienthue() + 2000) * songay;
                        }
                    }
                }
            }
            // Quý 2
            kq = qlmuonbus.getPMTheoQuy2(NamThongKe);
            for (PHIEUMUON pm : kq) {
                String MaPM = pm.getMaPM();
                for (PHIEUTRASACH pt : QLTRABUS.dspt) {
                    if (pt.getMaPM().equals(MaPM)) {
                        String tmp[] = pt.getNgaytra().split("-");
                        String tmp1[] = pm.getNgaymuon().split("-");
                        int songay = Integer.parseInt(tmp[2]) - Integer.parseInt(tmp1[2]);
                        if (songay <= 15) {
                            DoanhThuQuy2 = DoanhThuQuy2 + pt.getTienthue() * songay;
                        } else {
                            DoanhThuQuy2 = DoanhThuQuy2 + pt.getTienthue() * songay
                                    + (pt.getTienthue() + 2000) * songay;
                        }
                    }
                }
            }
            // Quý 3
            kq = qlmuonbus.getPMTheoQuy3(NamThongKe);
            for (PHIEUMUON pm : kq) {
                String MaPM = pm.getMaPM();
                for (PHIEUTRASACH pt : QLTRABUS.dspt) {
                    if (pt.getMaPM().equals(MaPM)) {
                        String tmp[] = pt.getNgaytra().split("-");
                        String tmp1[] = pm.getNgaymuon().split("-");
                        int songay = Integer.parseInt(tmp[2]) - Integer.parseInt(tmp1[2]);
                        if (songay <= 15) {
                            DoanhThuQuy3 = DoanhThuQuy3 + pt.getTienthue() * songay;
                        } else {
                            DoanhThuQuy3 = DoanhThuQuy3 + pt.getTienthue() * songay
                                    + (pt.getTienthue() + 2000) * songay;
                        }
                    }
                }
            }
            // Quý 4
            kq = qlmuonbus.getPMTheoQuy4(NamThongKe);
            for (PHIEUMUON pm : kq) {
                String MaPM = pm.getMaPM();
                for (PHIEUTRASACH pt : QLTRABUS.dspt) {
                    if (pt.getMaPM().equals(MaPM)) {
                        String tmp[] = pt.getNgaytra().split("-");
                        String tmp1[] = pm.getNgaymuon().split("-");
                        int songay = Integer.parseInt(tmp[2]) - Integer.parseInt(tmp1[2]);
                        if (songay <= 15) {
                            DoanhThuQuy4 = DoanhThuQuy4 + pt.getTienthue() * songay;
                        } else {
                            DoanhThuQuy4 = DoanhThuQuy4 + pt.getTienthue() * songay
                                    + (pt.getTienthue() + 2000) * songay;
                        }
                    }
                }
            }

            // Tính tổng doanh thu
            TongDoanhThu = DoanhThuQuy1 + DoanhThuQuy2 + DoanhThuQuy3 + DoanhThuQuy4;

            model.setValueAt(String.format("%,d", DoanhThuQuy1), 0, 1);
            model.setValueAt(String.format("%,d", DoanhThuQuy2), 0, 2);
            model.setValueAt(String.format("%,d", DoanhThuQuy3), 0, 3);
            model.setValueAt(String.format("%,d", DoanhThuQuy4), 0, 4);
            
            model.setValueAt(String.format("%,d", TongDoanhThu), 0, 5);
            model.setValueAt(String.format("%,d", TongDoanhThu), 1, 5);

            qlmtgui.setValueCellCenter(model, tblDoanhThu);
        }

    }

}

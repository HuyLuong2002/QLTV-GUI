package MyCustom;

import java.awt.Color;
import java.awt.Font;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Vector;
import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import QLTV.BUS.QLNHANVIENBUS;
import QLTV.BUS.QLNVBUS;
import QLTV.BUS.QLSACHBUS;
import QLTV.BUS.QLTRABUS;
import QLTV.BUS.ThongKeBUS;
import QLTV.DTO.CHITIETPHIEUMUON;
import QLTV.DTO.DOCGIA;
import QLTV.DTO.NHANVIEN;
import QLTV.DTO.PHIEUMUON;
import QLTV.DTO.PHIEUTRASACH;
import QLTV.DTO.SACH;
// import MyCustom.MyColor;

public class BaoCaoThongKe implements ActionListener {
    ThongKeBUS thongkebus = new ThongKeBUS();
    JPanel pnThongKe, pnSach, pnKhachHang, pnNhanVien, pnTongDoanhThu, pnBaoCaoDoanhThu;
    JLabel lbThongKe, lbKQSach, lbKQKH, lbKQNV, lbKQDT;
    JComboBox<String> cbKhoaThongKe;
    JButton btXemThem, btQuayLai;

    JPanel pnThongKeBieuDo, pnBieuDo;
    JLabel lbImageBangTK,lbTop1,lbTop2,lbTop3,lbTop4,lbTop5,lbSLTop1,lbSLTop2,lbSLTop3,lbSLTop4,lbSLTop5;
    ImagePanel pnBangThongKe;

    JTable tblDoanhThu;
    DefaultTableModel model;
    Vector<String> header;

    ArrayList<CHITIETPHIEUMUON> dskq = new ArrayList<CHITIETPHIEUMUON>();

    private ChartPanel chartPanel;

    public BaoCaoThongKe() {

    }

    public JPanel BangBaoCaoThongKe() throws Exception {
        if (pnThongKeBieuDo != null) {
            pnThongKeBieuDo.setVisible(false);
        }
        if (pnThongKe == null) {
            LineBorder line = new LineBorder(Color.black, 3, true);

            pnThongKe = new JPanel();
            pnThongKe.setBounds(240, 0, 1145, 800);
            pnThongKe.setLayout(null);

            pnSach = new JPanel();
            pnSach.setBounds(200, 80, 369, 201);
            pnSach.setBorder(line);
            pnSach.setLayout(null);

            pnKhachHang = new JPanel();
            pnKhachHang.setBounds(200, 310, 369, 201);
            pnKhachHang.setBorder(line);
            pnKhachHang.setLayout(null);

            pnNhanVien = new JPanel();
            pnNhanVien.setBounds(600, 80, 369, 201);
            pnNhanVien.setBorder(line);
            pnNhanVien.setLayout(null);

            pnTongDoanhThu = new JPanel();
            pnTongDoanhThu.setBounds(600, 310, 369, 201);
            pnTongDoanhThu.setBorder(line);
            pnTongDoanhThu.setLayout(null);

            pnBaoCaoDoanhThu = new JPanel();
            pnBaoCaoDoanhThu.setBounds(200, 560, 775, 100);
            pnBaoCaoDoanhThu.setBorder(line);
            pnBaoCaoDoanhThu.setLayout(new GridLayout(1, 1));
            pnBaoCaoDoanhThu.setBackground(MyColor.ColorBlue);

            lbThongKe = new JLabel("THỐNG KÊ TỔNG QUÁT");
            lbThongKe.setFont(new Font("Arial", Font.BOLD, 30));
            lbThongKe.setBounds(400, 0, 380, 80);

            String[] dsNam = { "", "2021", "2022", "2023" };
            cbKhoaThongKe = new JComboBox<>(dsNam);
            cbKhoaThongKe.setFont(new Font("Arial", Font.BOLD, 13));
            cbKhoaThongKe.setBounds(535, 520, 100, 30);
            cbKhoaThongKe.addActionListener(this);

            ImageIcon iconXemThem = new ImageIcon("images\\right-arrow.png");
            btXemThem = new JButton();
            btXemThem.setIcon(iconXemThem);
            btXemThem.setFont(new Font("Arial", Font.BOLD, 15));
            btXemThem.setBounds(1000, 700, 80, 40);
            btXemThem.setBackground(MyColor.ColorButton);
            btXemThem.setBorder(new RoundedBorder(10));
            btXemThem.addActionListener(this);

            pnThongKeBieuDo = new JPanel();
            pnThongKeBieuDo.setBounds(0, 0, 1145, 800);
            pnThongKeBieuDo.setLayout(null);
            pnThongKeBieuDo.setBackground(Color.white);
            pnThongKeBieuDo.setVisible(false);

            pnBieuDo = new JPanel();
            pnBieuDo.setBounds(100, 350, 1000, 350);
            pnBieuDo.setLayout(null);

            pnBangThongKe = new ImagePanel("images\\bangct.png");
            pnBangThongKe.setBounds(260, -5, 650, 340);

            ImageIcon iconQuayLai = new ImageIcon("images\\left-arrow.png");
            btQuayLai = new JButton();
            btQuayLai.setIcon(iconQuayLai);
            btQuayLai.setFont(new Font("Arial", Font.BOLD, 15));
            btQuayLai.setBounds(20, 700, 80, 40);
            btQuayLai.setBackground(MyColor.ColorButton);
            btQuayLai.setBorder(new RoundedBorder(10));
            btQuayLai.addActionListener(this);

            lbTop1 = new JLabel("1");
            lbTop1.setFont(new Font("Arial", Font.BOLD, 20));
            lbTop1.setBounds(128, 89, 300, 80);
    
            lbTop2 = new JLabel("2");
            lbTop2.setFont(new Font("Arial", Font.BOLD, 20));
            lbTop2.setBounds(128, 133, 300, 80);
    
            lbTop3 = new JLabel("3");
            lbTop3.setFont(new Font("Arial", Font.BOLD, 20));
            lbTop3.setBounds(128, 173, 300, 80);
    
            lbTop4 = new JLabel("4");
            lbTop4.setFont(new Font("Arial", Font.BOLD, 20));
            lbTop4.setBounds(128, 218, 300, 80);
    
            lbTop5 = new JLabel("5");
            lbTop5.setFont(new Font("Arial", Font.BOLD, 20));
            lbTop5.setBounds(128, 261, 300, 80);


            lbSLTop1 = new JLabel("1");
            lbSLTop1.setFont(new Font("Arial", Font.BOLD, 20));
            lbSLTop1.setBounds(595, 89, 100, 80);
    
            lbSLTop2 = new JLabel("2");
            lbSLTop2.setFont(new Font("Arial", Font.BOLD, 20));
            lbSLTop2.setBounds(595, 133, 100, 80);
    
            lbSLTop3 = new JLabel("3");
            lbSLTop3.setFont(new Font("Arial", Font.BOLD, 20));
            lbSLTop3.setBounds(595, 173, 100, 80);
    
            lbSLTop4 = new JLabel("4");
            lbSLTop4.setFont(new Font("Arial", Font.BOLD, 20));
            lbSLTop4.setBounds(595, 218, 100, 80);
    
            lbSLTop5 = new JLabel("5");
            lbSLTop5.setFont(new Font("Arial", Font.BOLD, 20));
            lbSLTop5.setBounds(595, 261, 100, 80);
    
            pnBangThongKe.add(lbTop1);
            pnBangThongKe.add(lbTop2);
            pnBangThongKe.add(lbTop3);
            pnBangThongKe.add(lbTop4);
            pnBangThongKe.add(lbTop5);

            pnBangThongKe.add(lbSLTop1);
            pnBangThongKe.add(lbSLTop2);
            pnBangThongKe.add(lbSLTop3);
            pnBangThongKe.add(lbSLTop4);
            pnBangThongKe.add(lbSLTop5);

            pnThongKeBieuDo.add(pnBieuDo);
            pnThongKeBieuDo.add(pnBangThongKe);
            pnThongKeBieuDo.add(btQuayLai);

            pnThongKe.add(pnSach);
            pnThongKe.add(pnKhachHang);
            pnThongKe.add(pnNhanVien);
            pnThongKe.add(pnTongDoanhThu);
            pnThongKe.add(pnBaoCaoDoanhThu);
            pnThongKe.add(lbThongKe);
            pnThongKe.add(cbKhoaThongKe);
            pnThongKe.add(btXemThem);
            pnThongKe.add(pnThongKeBieuDo);

            setTKSach();
            setTKDT();
            setTKNV();
            setTKDG();
            setTableThongKe();
            setValueTableTK();
            setChart();
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

    public void setTKDG() {
        int countDG = 0;
        QLNVBUS qlnvbus = new QLNVBUS();
        if (QLNVBUS.dsdg == null)
            try {
                qlnvbus.docDS();
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        for (DOCGIA dg : QLNVBUS.dsdg) {
            if (dg.getMaDG().trim().isEmpty() == false) {
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

    public void setTKNV() {
        int countNV = 0;
        QLNHANVIENBUS qlnhanvienbus = new QLNHANVIENBUS();
        if (QLNHANVIENBUS.dsnhanvien == null)
            try {
                qlnhanvienbus.docDSNHANVIEN();
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        for (NHANVIEN nv : QLNHANVIENBUS.dsnhanvien) {
            if (nv.getMaNV().trim().isEmpty() == false) {
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
        tblDoanhThu.getTableHeader().setForeground(new Color(255, 255, 255));
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
        tblDoanhThu.setSelectionBackground(MyColor.Color);
        tblDoanhThu.setModel(model);
        MyTable myTable = new MyTable();
        myTable.setValueCellCenter(model, tblDoanhThu);
        pnBaoCaoDoanhThu.add(new JScrollPane(tblDoanhThu));

    }

    public void setValueTableTK(){
        String Tensach[] = new String[5];
        String SL[] = new String[5];
        try {
            dskq = thongkebus.getTop5LuotMuon();
        } catch (Exception e) {
            System.out.println(e);
        }
        if(dskq.size() == 0){
            JOptionPane.showMessageDialog(null, "Không tìm thấy dữ liệu!","Lỗi", JOptionPane.ERROR_MESSAGE);
        }
        else {
            int i=0;
            for (CHITIETPHIEUMUON ctpm : dskq){
                for(SACH sach : QLSACHBUS.dssach){
                    if(ctpm.getMasach().trim().equals(sach.getMasach().trim())){
                        String tmp = sach.getTensach().trim();
                        String tmp1 = String.valueOf(ctpm.getSL());
                        Tensach[i] = tmp;
                        SL[i] = tmp1;
                    }
                }
                i++;
            }
            lbTop1.setText(Tensach[0]);
            lbTop2.setText(Tensach[1]);
            lbTop3.setText(Tensach[2]);
            lbTop4.setText(Tensach[3]);
            lbTop5.setText(Tensach[4]);

            lbSLTop1.setText(SL[0]);
            lbSLTop2.setText(SL[1]);
            lbSLTop3.setText(SL[2]);
            lbSLTop4.setText(SL[3]);
            lbSLTop5.setText(SL[4]);
        }
    }

    public void setChart() throws Exception {
        chartPanel = new ChartPanel(createChart());
        chartPanel.setBounds(0, 0, 1000, 350);

        pnBieuDo.add(chartPanel);
    }

    private JFreeChart createChart() throws Exception {
        JFreeChart barChart = ChartFactory.createBarChart(
                "Doanh thu năm " + Calendar.getInstance().get(Calendar.YEAR),
                "Tháng", "Doanh thu",
                createDataset(), PlotOrientation.VERTICAL, false, false, false);
        return barChart;
    }

    private CategoryDataset createDataset() throws Exception {
        
        final DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        for (int i = 1; i <= 12; i++) {
            double value = thongkebus.getDoanhThuThang(i, Calendar.getInstance().get(Calendar.YEAR));
            dataset.addValue(value, "Doanh thu", i + "");
        }
        return dataset;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == cbKhoaThongKe) {
            // Tính doanh thu mỗi quý
            int DoanhThuQuy1 = 0, DoanhThuQuy2 = 0, DoanhThuQuy3 = 0, DoanhThuQuy4 = 0, TongDoanhThu = 0;
            int vtkey = cbKhoaThongKe.getSelectedIndex();
            String NamThongKe = cbKhoaThongKe.getItemAt(vtkey);
            MyTable myTable = new MyTable();
            ArrayList<PHIEUMUON> kq = new ArrayList<PHIEUMUON>();
            // Quý 1
            kq = thongkebus.getPMTheoQuy1(NamThongKe);
            for (PHIEUMUON pm : kq) {
                String MaPM = pm.getMaPM();
                for (PHIEUTRASACH pt : QLTRABUS.dspt) {
                    if (pt.getMaPM().equals(MaPM)) {
                        String tmp[] = pt.getNgaytra().split("-");
                        String tmp1[] = pm.getNgaymuon().split("-");
                        int songay = Integer.parseInt(tmp[2]) - Integer.parseInt(tmp1[2]);
                        if (songay <= 15) {
                            DoanhThuQuy1 = DoanhThuQuy1 + pt.getTienthue() * songay;
                        } else if(songay >= 15 && songay <= 30 || songay >= 15 && songay <= 31) {
                            DoanhThuQuy1 = DoanhThuQuy1 + pt.getTienthue() * songay
                                    + (pt.getTienthue() + 2000) * songay;
                        }
                    }
                }
            }
            // Quý 2
            kq = thongkebus.getPMTheoQuy2(NamThongKe);
            for (PHIEUMUON pm : kq) {
                String MaPM = pm.getMaPM();
                for (PHIEUTRASACH pt : QLTRABUS.dspt) {
                    if (pt.getMaPM().equals(MaPM)) {
                        String tmp[] = pt.getNgaytra().split("-");
                        String tmp1[] = pm.getNgaymuon().split("-");
                        int songay = Integer.parseInt(tmp[2]) - Integer.parseInt(tmp1[2]);
                        if (songay <= 15) {
                            DoanhThuQuy2 = DoanhThuQuy2 + pt.getTienthue() * songay;
                        } else if(songay >= 15 && songay <= 30 || songay >= 15 && songay <= 31){
                            DoanhThuQuy2 = DoanhThuQuy2 + pt.getTienthue() * songay
                                    + (pt.getTienthue() + 2000) * songay;
                        }
                    }
                }
            }
            // Quý 3
            kq = thongkebus.getPMTheoQuy3(NamThongKe);
            for (PHIEUMUON pm : kq) {
                String MaPM = pm.getMaPM();
                for (PHIEUTRASACH pt : QLTRABUS.dspt) {
                    if (pt.getMaPM().equals(MaPM)) {
                        String tmp[] = pt.getNgaytra().split("-");
                        String tmp1[] = pm.getNgaymuon().split("-");
                        int songay = Integer.parseInt(tmp[2]) - Integer.parseInt(tmp1[2]);
                        if (songay <= 15) {
                            DoanhThuQuy3 = DoanhThuQuy3 + pt.getTienthue() * songay;
                        } else if(songay >= 15 && songay <= 30 || songay >= 15 && songay <= 31){
                            DoanhThuQuy3 = DoanhThuQuy3 + pt.getTienthue() * songay
                                    + (pt.getTienthue() + 2000) * songay;
                        }
                    }
                }
            }
            // Quý 4
            kq = thongkebus.getPMTheoQuy4(NamThongKe);
            for (PHIEUMUON pm : kq) {
                String MaPM = pm.getMaPM();
                for (PHIEUTRASACH pt : QLTRABUS.dspt) {
                    if (pt.getMaPM().equals(MaPM)) {
                        String tmp[] = pt.getNgaytra().split("-");
                        String tmp1[] = pm.getNgaymuon().split("-");
                        int songay = Integer.parseInt(tmp[2]) - Integer.parseInt(tmp1[2]);
                        if (songay <= 15) {
                            DoanhThuQuy4 = DoanhThuQuy4 + pt.getTienthue() * songay;
                        } else if(songay >= 15 && songay <= 30 || songay >= 15 && songay <= 31){
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

            myTable.setValueCellCenter(model, tblDoanhThu);
        }
        if (e.getSource() == btXemThem) {
            if (pnSach.isVisible() == true) {
                pnSach.setVisible(false);
            }
            if (pnKhachHang.isVisible() == true) {
                pnKhachHang.setVisible(false);
            }
            if (pnNhanVien.isVisible() == true) {
                pnNhanVien.setVisible(false);
            }
            if (pnTongDoanhThu.isVisible() == true) {
                pnTongDoanhThu.setVisible(false);
            }
            if (pnBaoCaoDoanhThu.isVisible() == true) {
                pnBaoCaoDoanhThu.setVisible(false);
            }
            if (cbKhoaThongKe.isVisible() == true) {
                cbKhoaThongKe.setVisible(false);
            }
            if (btXemThem.isVisible() == true) {
                btXemThem.setVisible(false);
            }
            if (lbThongKe.isVisible() == true) {
                lbThongKe.setVisible(false);
            }
            pnThongKeBieuDo.setVisible(true);

        }
        if (e.getSource() == btQuayLai) {
            if (pnThongKeBieuDo.isVisible() == true) {
                pnThongKeBieuDo.setVisible(false);
            }
            if (btQuayLai.isVisible() == false) {
                btQuayLai.setVisible(true);
            }
            if (btXemThem.isVisible() == false) {
                btXemThem.setVisible(true);
            }
            if (lbThongKe.isVisible() == false) {
                lbThongKe.setVisible(true);
            }
            if (pnSach.isVisible() == false) {
                pnSach.setVisible(true);
            }
            if (pnKhachHang.isVisible() == false) {
                pnKhachHang.setVisible(true);
            }
            if (pnNhanVien.isVisible() == false) {
                pnNhanVien.setVisible(true);
            }
            if (pnTongDoanhThu.isVisible() == false) {
                pnTongDoanhThu.setVisible(true);
            }
            if (pnBaoCaoDoanhThu.isVisible() == false) {
                pnBaoCaoDoanhThu.setVisible(true);
            }
            if (cbKhoaThongKe.isVisible() == false) {
                cbKhoaThongKe.setVisible(true);
            }
        }
    }



}

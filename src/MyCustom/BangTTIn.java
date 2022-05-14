package MyCustom;

import javax.swing.*;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import QLTV.BUS.QLCTHDTPBUS;
import QLTV.BUS.QLCTMUONBUS;
import QLTV.BUS.QLSACHBUS;
import QLTV.DTO.CHITIETHDTIENPHAT;
import QLTV.DTO.CHITIETPHIEUMUON;
import QLTV.DTO.SACH;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Properties;

public class BangTTIn extends JFrame implements ActionListener {
    JLabel lbInPM, lbMaPM, lbTenDG, lbNgayMuon, lbNgayTra, lbSLtong, lbDS, lbInHD, lbMaHD, lbMaDG, lbTienPhat,
            lbSLtongHD;
    static JTextField txMaHD, txTenDGHD, txMaPM, txTenDGPM, txSLtong, txSLtongHD, txTienPhat;
    JButton btHoTroNhap, btHoTroNhapHD, btIn, btInHDTP;

    UtilDateModel modelNgayBDPM, modelNgayKTPM;
    Properties pNgayBDPM, pNgayKTPM;
    JDatePanelImpl datePanelNgayBDPM, datePanelNgayKTPM;
    static JDatePickerImpl datePickerNgayBDPM, datePickerNgayKTPM;
    static ArrayList<SACH> dsSachMuon, dsSachHDTP;

    public BangTTIn() {
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setSize(700, 500);
        this.setTitle("Bảng thông tin phiếu mượn");
        this.setLayout(null);
        this.setLocationRelativeTo(null);

        this.setVisible(true);
    }

    public void setTTPM() {
        lbInPM = new JLabel("PHIẾU MƯỢN SÁCH");
        lbInPM.setFont(new Font("Arial", Font.BOLD, 25));
        lbInPM.setBounds(230, 0, 250, 80);

        lbMaPM = new JLabel("Mã phiếu mượn:");
        lbMaPM.setFont(new Font("Arial", Font.BOLD, 18));
        lbMaPM.setBounds(90, 50, 150, 80);

        lbTenDG = new JLabel("Tên độc giả:");
        lbTenDG.setFont(new Font("Arial", Font.BOLD, 18));
        lbTenDG.setBounds(90, 100, 150, 80);

        lbNgayMuon = new JLabel("Ngày mượn:");
        lbNgayMuon.setFont(new Font("Arial", Font.BOLD, 18));
        lbNgayMuon.setBounds(90, 150, 150, 80);

        lbNgayTra = new JLabel("Ngày trả:");
        lbNgayTra.setFont(new Font("Arial", Font.BOLD, 18));
        lbNgayTra.setBounds(90, 200, 150, 80);

        lbSLtong = new JLabel("Số lượng mượn:");
        lbSLtong.setFont(new Font("Arial", Font.BOLD, 18));
        lbSLtong.setBounds(90, 250, 150, 80);

        txMaPM = new JTextField();
        txMaPM.setBounds(250, 75, 150, 30);
        txMaPM.setFont(new Font("Arial", Font.PLAIN, 15));

        txTenDGPM = new JTextField();
        txTenDGPM.setBounds(250, 125, 150, 30);
        txTenDGPM.setFont(new Font("Arial", Font.PLAIN, 15));
        txTenDGPM.setEditable(false);

        txSLtong = new JTextField();
        txSLtong.setBounds(250, 275, 150, 30);
        txSLtong.setFont(new Font("Arial", Font.PLAIN, 15));
        txSLtong.setEditable(false);

        btHoTroNhap = new JButton("...");
        btHoTroNhap.setFont(new Font("Arial", Font.BOLD, 15));
        btHoTroNhap.setBounds(405, 75, 30, 30);
        btHoTroNhap.setBackground(Color.cyan);
        btHoTroNhap.setBorder(new RoundedBorder(10));
        btHoTroNhap.addActionListener(this);

        btIn = new JButton("In phiếu");
        btIn.setFont(new Font("Arial", Font.BOLD, 15));
        btIn.setBounds(300, 320, 100, 30);
        btIn.setBackground(Color.cyan);
        btIn.setBorder(new RoundedBorder(10));
        btIn.addActionListener(this);

        // Set date picker1
        modelNgayBDPM = new UtilDateModel();
        modelNgayBDPM.setSelected(true);
        pNgayBDPM = new Properties();
        pNgayBDPM.put("text.today", "Today");
        pNgayBDPM.put("text.month", "Month");
        pNgayBDPM.put("text.year", "Year");
        datePanelNgayBDPM = new JDatePanelImpl(modelNgayBDPM, pNgayBDPM);
        datePickerNgayBDPM = new JDatePickerImpl(datePanelNgayBDPM, new DateLabelFormatter());
        datePickerNgayBDPM.setBounds(250, 175, 150, 30);

        // Set date picker1
        modelNgayKTPM = new UtilDateModel();
        modelNgayKTPM.setSelected(true);
        pNgayKTPM = new Properties();
        pNgayKTPM.put("text.today", "Today");
        pNgayKTPM.put("text.month", "Month");
        pNgayKTPM.put("text.year", "Year");
        datePanelNgayKTPM = new JDatePanelImpl(modelNgayKTPM, pNgayKTPM);
        datePickerNgayKTPM = new JDatePickerImpl(datePanelNgayKTPM, new DateLabelFormatter());
        datePickerNgayKTPM.setBounds(250, 225, 150, 30);

        this.add(lbInPM);
        this.add(lbMaPM);
        this.add(lbTenDG);
        this.add(lbNgayMuon);
        this.add(lbNgayTra);
        this.add(lbSLtong);
        this.add(txMaPM);
        this.add(txTenDGPM);
        this.add(txSLtong);
        this.add(btHoTroNhap);
        this.add(btIn);
        this.add(datePickerNgayBDPM);
        this.add(datePickerNgayKTPM);
    }

    public void setTTHD() {
        lbInHD = new JLabel("HÓA ĐƠN TIỀN PHẠT");
        lbInHD.setFont(new Font("Arial", Font.BOLD, 25));
        lbInHD.setBounds(230, 0, 260, 80);

        lbMaHD = new JLabel("Mã hóa đơn:");
        lbMaHD.setFont(new Font("Arial", Font.BOLD, 18));
        lbMaHD.setBounds(90, 50, 150, 80);

        lbMaDG = new JLabel("Mã độc giả:");
        lbMaDG.setFont(new Font("Arial", Font.BOLD, 18));
        lbMaDG.setBounds(90, 100, 150, 80);

        lbSLtongHD = new JLabel("SL tổng:");
        lbSLtongHD.setFont(new Font("Arial", Font.BOLD, 18));
        lbSLtongHD.setBounds(90, 150, 150, 80);

        lbTienPhat = new JLabel("Tiền phạt:");
        lbTienPhat.setFont(new Font("Arial", Font.BOLD, 18));
        lbTienPhat.setBounds(90, 200, 150, 80);

        txMaHD = new JTextField();
        txMaHD.setBounds(250, 75, 150, 30);
        txMaHD.setFont(new Font("Arial", Font.PLAIN, 15));

        txTenDGHD = new JTextField();
        txTenDGHD.setBounds(250, 125, 150, 30);
        txTenDGHD.setFont(new Font("Arial", Font.PLAIN, 15));
        txTenDGHD.setEditable(false);

        txSLtongHD = new JTextField();
        txSLtongHD.setBounds(250, 175, 150, 30);
        txSLtongHD.setFont(new Font("Arial", Font.PLAIN, 15));
        txSLtongHD.setEditable(false);

        txTienPhat = new JTextField();
        txTienPhat.setBounds(250, 225, 150, 30);
        txTienPhat.setFont(new Font("Arial", Font.PLAIN, 15));
        txTienPhat.setEditable(false);

        btHoTroNhapHD = new JButton("...");
        btHoTroNhapHD.setFont(new Font("Arial", Font.BOLD, 15));
        btHoTroNhapHD.setBounds(405, 75, 30, 30);
        btHoTroNhapHD.setBackground(Color.cyan);
        btHoTroNhapHD.setBorder(new RoundedBorder(10));
        btHoTroNhapHD.addActionListener(this);

        btInHDTP = new JButton("In hóa đơn");
        btInHDTP.setFont(new Font("Arial", Font.BOLD, 15));
        btInHDTP.setBounds(270, 280, 120, 30);
        btInHDTP.setBackground(Color.cyan);
        btInHDTP.setBorder(new RoundedBorder(10));
        btInHDTP.addActionListener(this);

        this.add(lbInHD);
        this.add(lbMaHD);
        this.add(lbMaDG);
        this.add(lbSLtongHD);
        this.add(lbTienPhat);
        this.add(txMaHD);
        this.add(txTenDGHD);
        this.add(txSLtongHD);
        this.add(txTienPhat);
        this.add(btHoTroNhapHD);
        this.add(btInHDTP);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btHoTroNhap) {
            HoTroNhap ht = new HoTroNhap();
            ht.setHoTroNhapInPM();
        }
        if (e.getSource() == btHoTroNhapHD) {
            HoTroNhap ht = new HoTroNhap();
            ht.setHoTroNhapInHD();
        }
        if (e.getSource() == btIn) {
            if (txMaPM.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "Mời nhập thông tin cần in", "Lỗi", JOptionPane.ERROR_MESSAGE);
            } else {
                String MaPM = txMaPM.getText();
                String TenDG = txTenDGPM.getText();
                int ngaymuon = datePickerNgayBDPM.getModel().getDay();
                int thangmuon = datePickerNgayBDPM.getModel().getMonth();
                int nammuon = datePickerNgayBDPM.getModel().getYear();
                String NgayBD = String.valueOf(nammuon) + "-" + String.valueOf(thangmuon) + "-"
                        + String.valueOf(ngaymuon);
                int ngaytra = datePickerNgayKTPM.getModel().getDay();
                int thangtra = datePickerNgayKTPM.getModel().getMonth();
                int namtra = datePickerNgayKTPM.getModel().getYear();
                String NgayKT = String.valueOf(namtra) + "-" + String.valueOf(thangtra) + "-" + String.valueOf(ngaytra);
                String SLtong = txSLtong.getText();
                if (dsSachMuon == null) {
                    dsSachMuon = new ArrayList<>();
                }
                for (CHITIETPHIEUMUON ctpm : QLCTMUONBUS.dsctpm) {
                    if (ctpm.getMaPM().trim().equals(MaPM)) {
                        String Masach = ctpm.getMasach().trim();
                        String SLmuon = String.valueOf(ctpm.getSL()).trim();
                        for (SACH sach : QLSACHBUS.dssach) {
                            SACH sachTMP = new SACH();
                            if (sach.getMasach().trim().equals(Masach)) {
                                String Tensach = sach.getTensach();
                                sachTMP.setMasach(Masach);
                                sachTMP.setTensach(Tensach);
                                sachTMP.setSL(Integer.parseInt(SLmuon));
                                dsSachMuon.add(sachTMP);
                            }
                        }
                    }
                }
                ExportPDF export = new ExportPDF();
                export.setExportPDFPM(MaPM, TenDG, NgayBD, NgayKT, SLtong, dsSachMuon);
                dsSachMuon.clear();
            }
        }
        if (e.getSource() == btInHDTP) {
            if (txMaHD.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "Mời nhập thông tin cần in", "Lỗi", JOptionPane.ERROR_MESSAGE);
            } else {
                String MaHD = txMaHD.getText();
                String TenDG = txTenDGHD.getText();
                String SLtong = txSLtongHD.getText();
                String Tienphat = txTienPhat.getText();
                if (dsSachHDTP == null) {
                    dsSachHDTP = new ArrayList<>();
                }
                for (CHITIETHDTIENPHAT cthdtp : QLCTHDTPBUS.dscthdtp) {
                    if (cthdtp.getMaHD().trim().equals(MaHD)) {
                        String Masach = cthdtp.getMasach().trim();
                        String SL = String.valueOf(cthdtp.getSL());
                        for (SACH sach : QLSACHBUS.dssach) {
                            if (sach.getMasach().trim().equals(Masach)) {
                                SACH sachTMP = new SACH();
                                sachTMP.setMasach(Masach);
                                sachTMP.setTensach(sach.getTensach());
                                sachTMP.setSL(Integer.valueOf(SL));
                                sachTMP.setDongia(sach.getDongia());
                                dsSachHDTP.add(sachTMP);
                            }
                        }
                    }
                }
                ExportPDF export = new ExportPDF();
                export.setExportPDFHDTP(MaHD, TenDG, SLtong, Tienphat, dsSachHDTP);
                dsSachHDTP.clear();
            }
        }
    }
}

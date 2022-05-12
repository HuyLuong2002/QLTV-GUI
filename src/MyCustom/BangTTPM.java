package MyCustom;

import javax.swing.*;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import QLTV.BUS.QLCTMUONBUS;
import QLTV.BUS.QLSACHBUS;
import QLTV.DTO.CHITIETPHIEUMUON;
import QLTV.DTO.SACH;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Properties;

public class BangTTPM extends JFrame implements ActionListener {
    JLabel lbInPM, lbMaPM, lbTenDG, lbNgayMuon, lbNgayTra, lbSLtong, lbDS;
    static JTextField txMaPM, txTenDG, txSLtong;
    JButton btHoTroNhap, btIn;

    UtilDateModel modelNgayBDPM, modelNgayKTPM;
    Properties pNgayBDPM, pNgayKTPM;
    JDatePanelImpl datePanelNgayBDPM, datePanelNgayKTPM;
    static JDatePickerImpl datePickerNgayBDPM, datePickerNgayKTPM;
    static ArrayList<SACH> dsSachMuon;

    public BangTTPM() {
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setSize(700, 500);
        this.setTitle("Bảng thông tin phiếu mượn");
        this.setLayout(null);
        this.setLocationRelativeTo(null);

        setTTPM();

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

        txTenDG = new JTextField();
        txTenDG.setBounds(250, 125, 150, 30);
        txTenDG.setFont(new Font("Arial", Font.PLAIN, 15));
        txTenDG.setEditable(false);

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
        this.add(txTenDG);
        this.add(txSLtong);
        this.add(btHoTroNhap);
        this.add(btIn);
        this.add(datePickerNgayBDPM);
        this.add(datePickerNgayKTPM);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btHoTroNhap) {
            HoTroNhap ht = new HoTroNhap();
            ht.setHoTroNhapInPM();
        }
        if (e.getSource() == btIn) {
            if (txMaPM.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "Mời nhập thông tin cần in", "Lỗi", JOptionPane.ERROR_MESSAGE);
            } else {
                String MaPM = txMaPM.getText();
                String TenDG = txTenDG.getText();
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

    }

}

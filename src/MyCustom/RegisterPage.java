package MyCustom;
import javax.swing.*;
import javax.swing.border.Border;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import QLTV.BUS.ACCOUNTBUS;
import QLTV.DTO.ACCOUNT;

import java.awt.*;
import java.awt.event.*;
import java.util.Properties;

public class RegisterPage extends JFrame implements ActionListener {
    JPanel panelHomePage;
    JLabel labelHomePage,labelUsername,labelPassword;
    JLabel labelLibrary,labelNgaySinh,labelTen, labelHoLot, labelGioiTinh, labelSDT, labelID, labelConfirmPass;
    ImageIcon ImageBackground;
    JTextField txusername, textNgaySinh, textTen, textHoLot, textSDT, textID;
    JPasswordField txpassword, txConfirmPass;
    Border borderForgotPasswd;
    JButton buttonDangNhap,buttonDangKy,buttonForgotPasswd;
    Color ColorWhite;
    JComboBox<String> comboBoxGioiTinh;

    UtilDateModel modelNgaySinh;
    Properties pNgaySinh;
    JDatePanelImpl datePanelNgaySinh;
    JDatePickerImpl datePickerNgaySinh;

    public RegisterPage() throws InterruptedException{
        this.setTitle("Quản lý thư viện");
        this.setSize(500, 810);
        // this.setExtendedState(JFrame.MAXIMIZED_BOTH); 
        this.setLayout(null);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // thay đổi logo thư viện
        ImageIcon img = new ImageIcon("images\\app_logo.png");
        ColorWhite = new Color(240,255,255);
        this.setIconImage(img.getImage());
        setHomePage();
        getDB();
        this.setVisible(true);

    }
    //
    public void setHomePage(){
        //set up panel
        panelHomePage = new JPanel();
        panelHomePage.setLayout(null);
        panelHomePage.setBounds(0, 0, 500, 810);
        panelHomePage.setBackground(ColorWhite);
        //set up label
        labelHomePage = new JLabel("ĐĂNG KÝ");
        labelUsername = new JLabel("Tài khoản");
        labelPassword = new JLabel("Mật khẩu ");
        labelConfirmPass = new JLabel("Xác nhận mật khẩu");
        labelHoLot = new JLabel("Họ và tên lót");
        labelGioiTinh = new JLabel("Giới tính");
        labelID = new JLabel("ID");
        labelNgaySinh = new JLabel("Ngày Sinh");
        labelSDT = new JLabel("Số điện thoại");
        labelTen = new JLabel("Tên");

        // Label Library
        labelLibrary = new JLabel();
        labelLibrary.setIcon(new ImageIcon("images\\library.png"));
        labelLibrary.setBounds(10, 0, 64, 64);

        //Label Dang nhap
        labelHomePage.setFont(new Font("Arial", Font.BOLD,28));
        labelHomePage.setForeground(Color.CYAN);
        labelHomePage.setBounds(180, 0, 230, 150);

        labelID.setFont(new Font("Arial", Font.PLAIN, 17));
        labelID.setForeground(Color.black);
        labelID.setBounds(50,100,120,80);
        //Label Username
        labelUsername.setFont(new Font("Arial", Font.PLAIN, 17));
        labelUsername.setForeground(Color.black);
        labelUsername.setBounds(50,160,120,80);

        //Label Password
        labelPassword.setFont(new Font("Arial", Font.PLAIN, 17));
        labelPassword.setForeground(Color.black);
        labelPassword.setBounds(50,220,130,80);

        labelConfirmPass.setFont(new Font("Arial", Font.PLAIN, 17));
        labelConfirmPass.setForeground(Color.black);
        labelConfirmPass.setBounds(50,280,150,80);

        labelHoLot.setFont(new Font("Arial", Font.PLAIN, 17));
        labelHoLot.setForeground(Color.BLACK);
        labelHoLot.setBounds(50, 340, 150, 80);

        labelTen.setForeground(Color.BLACK);
        labelTen.setBounds(50, 400,150,80);
        labelTen.setFont(new Font("Arial", Font.PLAIN, 17));

        labelNgaySinh.setFont(new Font("Arial", Font.PLAIN, 17));
        labelNgaySinh.setForeground(Color.black);
        labelNgaySinh.setBounds(50,460,120,80);

        labelGioiTinh.setFont(new Font("Arial", Font.PLAIN, 17));
        labelGioiTinh.setForeground(Color.black);
        labelGioiTinh.setBounds(50,520,120,80);

        labelSDT.setFont(new Font("Arial", Font.PLAIN, 17));
        labelSDT.setForeground(Color.black);
        labelSDT.setBounds(50,580,120,80);

        textID = new JTextField();
        txusername = new JTextField();
        txpassword = new JPasswordField();
        txConfirmPass = new JPasswordField();
        textHoLot = new JTextField();
        textTen = new JTextField();
        textNgaySinh = new JTextField();
        textSDT = new JTextField();

        textID.setFont(new Font("Arial",Font.PLAIN,18));
        textID.setBounds(250,125,180,30);
        txusername.setFont(new Font("Arial",Font.PLAIN,18));
        txusername.setBounds(250,185,180,30);
        txpassword.setFont(new Font("Arial",Font.PLAIN,18));
        txpassword.setBounds(250,245,180,30);
        txConfirmPass.setFont(new Font("Arial", Font.PLAIN,18));
        txConfirmPass.setBounds(250, 305, 180, 30);
        textHoLot.setFont(new Font("Arial",Font.PLAIN,18));
        textHoLot.setBounds(250,365,180,30);
        textTen.setFont(new Font("Arial",Font.PLAIN,18));
        textTen.setBounds(250,425,180,30);
        textSDT.setFont(new Font("Arial",Font.PLAIN,18));
        textSDT.setBounds(250,605,180,30);

        String [] dsGioiTinh = {"", "Nam", "Nữ", "Khác"};
        comboBoxGioiTinh = new JComboBox<>(dsGioiTinh);
        comboBoxGioiTinh.setFont(new Font("Arial", Font.BOLD, 13));
        comboBoxGioiTinh.setBounds(250, 545, 180, 30);
        comboBoxGioiTinh.addActionListener(this);

        modelNgaySinh = new UtilDateModel();
        modelNgaySinh.setSelected(true);
        pNgaySinh = new Properties();
        pNgaySinh.put("text.today", "Today");
        pNgaySinh.put("text.month", "Month");
        pNgaySinh.put("text.year", "Year");
        datePanelNgaySinh = new JDatePanelImpl(modelNgaySinh, pNgaySinh);
        datePickerNgaySinh = new JDatePickerImpl(datePanelNgaySinh, new DateLabelFormatter());
        datePickerNgaySinh.setBounds(250, 485, 180, 30);
       
        //Button Dang nhap, Dang ky, ForgotPasswd
        buttonDangKy = new JButton("Đăng ký");
        buttonDangNhap = new JButton("Đăng nhập");
        buttonForgotPasswd = new JButton("Forgot Password");
        //set bound button
        buttonDangKy.setBounds(50,680,150,35);
        buttonDangNhap.setBounds(280,680, 150 ,35);
        buttonForgotPasswd.setBounds(325, 520, 100,35);
        //set font
        buttonDangKy.setFont(new Font("Arial",Font.BOLD,16));
        buttonDangKy.setIcon(new ImageIcon("images\\add-user.png"));
        buttonDangNhap.setFont(new Font("Arial",Font.BOLD,16));
        buttonDangNhap.setIcon(new ImageIcon("images\\logout.png"));
        buttonForgotPasswd.setFont(new Font("Arial", Font.PLAIN,11));
        //set background
        buttonDangKy.setBackground(Color.CYAN);
        buttonDangNhap.setBackground(Color.CYAN);
        buttonForgotPasswd.setBackground(Color.WHITE);
        // Tạo border 
        Border borderForgotPasswd = BorderFactory.createEmptyBorder();
        buttonForgotPasswd.setBorder(borderForgotPasswd);
        // Thêm sự kiện
        buttonDangNhap.addActionListener(this);
        buttonDangKy.addActionListener(this);
        // comboBoxPhanQuyen.addActionListener(this);

        // String [] dsPhanQuyen = {"","admin","Nhân viên"};
        // comboBoxPhanQuyen = new JComboBox<>(dsPhanQuyen);
        // comboBoxPhanQuyen.setFont(new Font("Arial", Font.PLAIN, 13));
        // comboBoxPhanQuyen.setBounds(200, 305, 100, 30);
        // comboBoxPhanQuyen.setBackground(Color.WHITE);

        this.add(panelHomePage);

        panelHomePage.add(labelLibrary);
        panelHomePage.add(labelHomePage);

        panelHomePage.add(labelID);
        panelHomePage.add(labelUsername);
        panelHomePage.add(labelPassword);
        panelHomePage.add(labelConfirmPass);
        panelHomePage.add(labelHoLot);
        panelHomePage.add(labelTen);
        panelHomePage.add(labelGioiTinh);
        panelHomePage.add(labelNgaySinh);
        panelHomePage.add(labelSDT);

        panelHomePage.add(textID);
        panelHomePage.add(txusername);
        panelHomePage.add(txpassword);
        panelHomePage.add(txConfirmPass);
        panelHomePage.add(textHoLot);
        panelHomePage.add(textTen);
        panelHomePage.add(datePickerNgaySinh);
        panelHomePage.add(textSDT);
        panelHomePage.add(comboBoxGioiTinh);

        // panelHomePage.add(comboBoxPhanQuyen);

        panelHomePage.add(buttonDangKy);
        panelHomePage.add(buttonDangNhap);
        // panelHomePage.add(buttonForgotPasswd);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==buttonDangKy){
            try {
                ACCOUNT account = new ACCOUNT();
                account.setID(textID.getText().trim());
                account.setUsername(txusername.getText().trim());
                account.setPassword(String.valueOf(txpassword.getPassword()).trim());
                account.setHoLot(textHoLot.getText().trim());
                account.setTen(textTen.getText().trim());
                account.setNgaySinh(datePickerNgaySinh.getJFormattedTextField().getText());
                String GioiTinh = (String) comboBoxGioiTinh.getSelectedItem();
                account.setGioiTinh(GioiTinh);
                account.setSDT(textSDT.getText().trim());
                account.setPhanQuyen(0);

                String x = String.valueOf(txpassword.getPassword());
                String y = String.valueOf(txConfirmPass.getPassword());

                if (textID.getText().equals("") || txusername.getText().equals("") || textHoLot.getText().equals("")
                        || textSDT.getText().equals("") || textTen.getText().equals("") || x.equals("") || y.equals("")
                        || GioiTinh.equals("")) {
                    JOptionPane.showMessageDialog(null, "Thông tin đăng ký trống", "Thông báo",
                            JOptionPane.INFORMATION_MESSAGE);
                } else if (x.trim().equals(y.trim())){
                    ACCOUNTBUS data = new ACCOUNTBUS();
                    int kt = -1;
                    try {
                        // data.docDSACC();
                        kt = data.them(account);
                    } catch (Exception e1) {
                        e1.printStackTrace();
                    }
                    if (kt == 0) {
                        this.dispose();
                        new LoginPage();
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Mật khẩu nhập không trùng nhau! Cần nhập lại");
                }
            } catch (InterruptedException e1) {
                System.out.println(e1);
               
            }
        }
        if(e.getSource() == buttonDangNhap){
            try {
                this.dispose();
                new LoginPage();
            } catch (InterruptedException e1) {
                e1.printStackTrace();
            }
        }
    }

    public void getDB(){
        try {
            ACCOUNTBUS data = new ACCOUNTBUS();
            if (ACCOUNTBUS.dsacc == null)
                try {   
                    data.docDSACC();
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
        } catch (Exception e1) {
            System.out.println(e1);
        }
    }
}

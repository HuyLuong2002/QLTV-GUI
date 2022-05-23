package MyCustom;
import javax.swing.*;
import javax.swing.border.Border;

import QLTV.BUS.ACCOUNTBUS;

import java.awt.*;
import java.awt.event.*;

public class LoginPage extends JFrame implements ActionListener {
    JPanel panelHomePage;
    JLabel labelHomePage,labelUsername,labelPassword;
    JLabel labelLibrary,labelBackground,labelIconUser,labelIconPassword;
    ImageIcon ImageBackground;
    JTextField username;
    JPasswordField password;
    Border borderForgotPasswd;
    JButton buttonDangNhap,buttonDangKy,buttonForgotPasswd;
    ImageIcon imgIconHP;
    Color ColorPanel;

    public LoginPage() throws InterruptedException{
        this.setTitle("Quản lý thư viện");
        this.setSize(960, 594);
        // this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setLocationRelativeTo(null);
        this.setLayout(new GridLayout(1,1));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // thay đổi logo thư viện
        imgIconHP = new ImageIcon("images\\app_logo.png");
        ColorPanel = new Color(240,255,255);
        this.setIconImage(imgIconHP.getImage());
        setHomePage();
        this.setVisible(true);

    }
    //set up Home Page
    public void setHomePage(){
        //set up panel
        panelHomePage = new JPanel();
        panelHomePage.setLayout(null);
        panelHomePage.setBackground(ColorPanel);
        //set up label
        labelHomePage = new JLabel("USER LOGIN");
        labelUsername = new JLabel(" Tài khoản ");
        labelPassword = new JLabel(" Mật khẩu ");

        // Label Library
        labelLibrary = new JLabel();
        labelLibrary.setIcon(new ImageIcon("images\\library.png"));
        labelLibrary.setBounds(10, 0, 64, 64);

        // Label background
        ImageBackground = new ImageIcon("images\\backgroundsach.png");
        labelBackground = new JLabel(ImageBackground);
        labelBackground.setBounds(481, 0, 480, 594);

        // Label icon user
        labelIconUser = new JLabel();
        labelIconUser.setIcon(new ImageIcon("images\\user.png"));
        labelIconUser.setBounds(68, 225, 24, 24);

        // Label IconLock
        labelIconPassword = new JLabel();
        labelIconPassword.setIcon(new ImageIcon("images\\padlock.png"));
        labelIconPassword.setBounds(68, 300, 24, 24);

        //Label Dang nhap
        labelHomePage.setFont(new Font("Arial", Font.BOLD, 30));
        labelHomePage.setForeground(Color.CYAN);
        labelHomePage.setBounds(150, 0, 200, 180);

        //Label Username
        labelUsername.setFont(new Font("Arial", Font.PLAIN, 17));
        labelUsername.setForeground(Color.black);
        labelUsername.setBounds(210,130,150,150);


        //Label Password
        labelPassword.setFont(new Font("Arial", Font.PLAIN, 17));
        labelPassword.setForeground(Color.black);
        labelPassword.setBounds(205,210,150,150);

        username = new JTextField();
        password = new JPasswordField();
        username.setFont(new Font("Arial",Font.ITALIC,18));
        username.setBounds(98,220,320,40);
        password.setFont(new Font("Arial",Font.ITALIC,18));
        password.setBounds(98,295,320,40);

        // Label chèn
       
        //Button Dang nhap, Dang ky, ForgotPasswd
        buttonDangNhap = new JButton("Đăng nhập");
        buttonDangKy = new JButton("Đăng ký");
        buttonForgotPasswd = new JButton("Quên mật khẩu");
        //set bound button
        buttonDangNhap.setBounds(98,380,320,35);
        buttonDangNhap.setIcon(new ImageIcon("images\\logout.png"));
        buttonDangKy.setBounds(98,440, 320 ,35);
        buttonDangKy.setIcon(new ImageIcon("images\\add-user.png"));
        buttonForgotPasswd.setBounds(325, 500, 100,35);
        //set font
        buttonDangNhap.setFont(new Font("Arial",Font.BOLD,16));
        buttonDangKy.setFont(new Font("Arial",Font.BOLD,16));
        buttonForgotPasswd.setFont(new Font("Arial", Font.PLAIN,11));
        //set background
        buttonDangNhap.setBackground(Color.CYAN);
        buttonDangKy.setBackground(Color.CYAN);
        buttonForgotPasswd.setBackground(Color.WHITE);
        // Tạo border 
        Border borderForgotPasswd = BorderFactory.createEmptyBorder();
        buttonForgotPasswd.setBorder(borderForgotPasswd);
        // Thêm sự kiện
        buttonDangNhap.addActionListener(this);
        buttonDangKy.addActionListener(this);
        
        this.add(panelHomePage);

        panelHomePage.add(labelLibrary);
        panelHomePage.add(labelHomePage);


        panelHomePage.add(labelUsername);
        panelHomePage.add(username);
        panelHomePage.add(labelIconUser);

        panelHomePage.add(labelPassword);
        panelHomePage.add(password);
        panelHomePage.add(labelIconPassword);

        panelHomePage.add(buttonDangNhap);
        panelHomePage.add(buttonDangKy);
        panelHomePage.add(buttonForgotPasswd);
        // background phai de cuoi
        panelHomePage.add(labelBackground);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==buttonDangKy){
            try {
                this.dispose();
                new RegisterPage();
            } catch (InterruptedException e1) {
                e1.printStackTrace();
            }
        }
        if(e.getSource()==buttonDangNhap){
            String pass = String.valueOf(password.getPassword());
            String user = username.getText();
            try {
                int kt = -1;
                ACCOUNTBUS dangnhap = new ACCOUNTBUS();
                kt = dangnhap.login(user, pass);
                if(kt == 0){
                    this.dispose();
                    new Menu();
                }
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
    }

    
}

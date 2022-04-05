import javax.swing.*;
import javax.swing.border.Border;

import java.awt.*;

public class LoginPage extends JFrame {
    public LoginPage(){
        this.setTitle("Quản lý thư viện");
        this.setSize(960, 594);
        // this.setExtendedState(JFrame.MAXIMIZED_BOTH); 
        this.setLayout(new GridLayout(1,1));
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // thay đổi logo thư viện
        ImageIcon img = new ImageIcon("images\\app_logo.png");
        this.setIconImage(img.getImage());
        setHomePage();
        this.setVisible(true);

    }
    //
    public void setHomePage(){
        //set up panel
        JPanel panelHomePage1 = new JPanel();
        panelHomePage1.setLayout(null);
        panelHomePage1.setBackground(Color.WHITE);
        //set up label
        JLabel labelHomePage = new JLabel("USER LOGIN");
        JLabel labelUsername = new JLabel(" Account ");
        JLabel labelPassword = new JLabel(" Password ");

        // Label Library
        JLabel labelLibrary = new JLabel();
        labelLibrary.setIcon(new ImageIcon("images\\library.png"));
        labelLibrary.setBounds(10, 0, 64, 64);

        // Label BackGround
        ImageIcon ImageBackground = new ImageIcon("images\\backgroundsach.png");
        JLabel labelBackground = new JLabel(ImageBackground);
        labelBackground.setBounds(481, 0, 480, 594);


        // Label Iconuser
        JLabel labelIconUser = new JLabel();
        labelIconUser.setIcon(new ImageIcon("images\\user.png"));
        labelIconUser.setBounds(68, 225, 24, 24);

        // Label IconLock
        JLabel labelIconPassword = new JLabel();
        labelIconPassword.setIcon(new ImageIcon("images\\padlock.png"));
        labelIconPassword.setBounds(68, 300, 24, 24);

        //Label Dang nhap
        labelHomePage.setFont(new Font("Arial", Font.BOLD, 30));
        labelHomePage.setForeground(Color.CYAN);
        labelHomePage.setBounds(150, 0, 200, 180);

        //Label Username
        labelUsername.setFont(new Font("Arial", Font.ITALIC, 17));
        labelUsername.setForeground(Color.black);
        labelUsername.setBounds(210,130,150,150);


        //Label Password
        labelPassword.setFont(new Font("Arial", Font.ITALIC, 17));
        labelPassword.setForeground(Color.black);
        labelPassword.setBounds(205,210,150,150);

        JTextField username = new JTextField();
        JPasswordField password = new JPasswordField();
        username.setFont(new Font("Arial",Font.ITALIC,18));
        username.setBounds(98,220,320,40);
        password.setFont(new Font("Arial",Font.ITALIC,18));
        password.setBounds(98,295,320,40);

        // Tạo border 
        Border borderForgotPasswd = BorderFactory.createEmptyBorder();

        // Label chèn
       
        //Button Dang nhap, Dang ky, ForgotPasswd
        JButton buttonDangNhap = new JButton("Sign up");
        JButton buttonDangKy = new JButton("Sign in");
        JButton buttonForgotPasswd = new JButton("Forgot Password");
        buttonDangNhap.setBounds(98,380,320,35);
        buttonDangKy.setBounds(98,440, 320 ,35);
        buttonForgotPasswd.setBounds(325, 500, 100,35);
        buttonDangNhap.setFont(new Font("Arial",Font.BOLD,16));
        buttonDangKy.setFont(new Font("Arial",Font.BOLD,16));
        buttonForgotPasswd.setFont(new Font("Arial", Font.PLAIN,11));
        buttonDangNhap.setBackground(Color.CYAN);
        buttonDangKy.setBackground(Color.CYAN);
        buttonForgotPasswd.setBorder(borderForgotPasswd);
        buttonForgotPasswd.setBackground(Color.WHITE);
        
        this.add(panelHomePage1);

        panelHomePage1.add(labelLibrary);
        panelHomePage1.add(labelHomePage);


        panelHomePage1.add(labelUsername);
        panelHomePage1.add(username);
        panelHomePage1.add(labelIconUser);

        panelHomePage1.add(labelPassword);
        panelHomePage1.add(password);
        panelHomePage1.add(labelIconPassword);

        panelHomePage1.add(buttonDangNhap);
        panelHomePage1.add(buttonDangKy);
        panelHomePage1.add(buttonForgotPasswd);
        // background phai de cuoi
       
        panelHomePage1.add(labelBackground);
    }
}

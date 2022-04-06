import javax.swing.*;
import javax.swing.border.Border;
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
        panelHomePage = new JPanel();
        panelHomePage.setLayout(null);
        panelHomePage.setBackground(Color.WHITE);
        //set up label
        labelHomePage = new JLabel("USER LOGIN");
        labelUsername = new JLabel(" Account ");
        labelPassword = new JLabel(" Password ");

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
        labelUsername.setFont(new Font("Arial", Font.ITALIC, 17));
        labelUsername.setForeground(Color.black);
        labelUsername.setBounds(210,130,150,150);


        //Label Password
        labelPassword.setFont(new Font("Arial", Font.ITALIC, 17));
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
        buttonDangNhap = new JButton("Sign in");
        buttonDangKy = new JButton("Sign up");
        buttonForgotPasswd = new JButton("Forgot Password");
        //set bound button
        buttonDangNhap.setBounds(98,380,320,35);
        buttonDangKy.setBounds(98,440, 320 ,35);
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
            this.dispose();
            new RegisterPage();
        }
        
    }
}

package MyCustom;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.*;

public class RegisterPage extends JFrame implements ActionListener {
    JPanel panelHomePage;
    JLabel labelHomePage,labelUsername,labelPassword;
    JLabel labelLibrary,labelBackground,lablePhanQuyen, labelConfirmPass;
    ImageIcon ImageBackground;
    JTextField txusername;
    JPasswordField txpassword, txConfirmPass;
    Border borderForgotPasswd;
    JButton buttonDangNhap,buttonDangKy,buttonForgotPasswd;
    Color ColorWhite;
    JComboBox<String> comboBoxPhanQuyen;

    public RegisterPage() throws InterruptedException{
        this.setTitle("Quản lý thư viện");
        this.setSize(960, 594);
        // this.setExtendedState(JFrame.MAXIMIZED_BOTH); 
        this.setLayout(null);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // thay đổi logo thư viện
        ImageIcon img = new ImageIcon("images\\app_logo.png");
        ColorWhite = new Color(240,255,255);
        this.setIconImage(img.getImage());
        setHomePage();
        this.setVisible(true);

    }
    //
    public void setHomePage(){
        //set up panel
        panelHomePage = new JPanel();
        panelHomePage.setLayout(null);
        panelHomePage.setBounds(0, 0, 960, 594);
        panelHomePage.setBackground(ColorWhite);
        //set up label
        labelHomePage = new JLabel("USER REGISTER");
        labelUsername = new JLabel(" Account ");
        labelPassword = new JLabel(" Password ");
        labelConfirmPass = new JLabel("Confirm Password");

        // Label Library
        labelLibrary = new JLabel();
        labelLibrary.setIcon(new ImageIcon("images\\library.png"));
        labelLibrary.setBounds(10, 0, 64, 64);

        // Label background
        ImageBackground = new ImageIcon("images\\backgroundsach.png");
        labelBackground = new JLabel(ImageBackground);
        labelBackground.setBounds(481, 0, 480, 594);

        //Label Dang nhap
        labelHomePage.setFont(new Font("Arial", Font.BOLD,28));
        labelHomePage.setForeground(Color.CYAN);
        labelHomePage.setBounds(130, 0, 230, 150);

        //Label Username
        labelUsername.setFont(new Font("Arial", Font.PLAIN, 17));
        labelUsername.setForeground(Color.black);
        labelUsername.setBounds(50,100,120,80);


        //Label Password
        labelPassword.setFont(new Font("Arial", Font.PLAIN, 17));
        labelPassword.setForeground(Color.black);
        labelPassword.setBounds(50,160,130,80);

        labelConfirmPass.setFont(new Font("Arial", Font.PLAIN, 17));
        labelConfirmPass.setForeground(Color.BLACK);
        labelConfirmPass.setBounds(50, 220, 150, 80);

        lablePhanQuyen = new JLabel("Phân quyền");
        lablePhanQuyen.setForeground(Color.BLACK);
        lablePhanQuyen.setBounds(50, 280,150,80);
        lablePhanQuyen.setFont(new Font("Arial", Font.PLAIN, 17));

        txusername = new JTextField();
        txpassword = new JPasswordField();
        txConfirmPass = new JPasswordField();
        txusername.setFont(new Font("Arial",Font.ITALIC,18));
        txusername.setBounds(200,125,210,30);
        txpassword.setFont(new Font("Arial",Font.ITALIC,18));
        txpassword.setBounds(200,185,210,30);
        txConfirmPass.setFont(new Font("Arial", Font.ITALIC,18));
        txConfirmPass.setBounds(200, 245, 210, 30);

        // Label chèn
       
        //Button Dang nhap, Dang ky, ForgotPasswd
        buttonDangKy = new JButton("Sign up");
        buttonDangNhap = new JButton("Sign in");
        buttonForgotPasswd = new JButton("Forgot Password");
        //set bound button
        buttonDangKy.setBounds(90,400,320,35);
        buttonDangNhap.setBounds(90,460, 320 ,35);
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
        comboBoxPhanQuyen.addActionListener(this);

        String [] dsPhanQuyen = {"","admin","Nhân viên"};
        comboBoxPhanQuyen = new JComboBox<>(dsPhanQuyen);
        comboBoxPhanQuyen.setFont(new Font("Arial", Font.PLAIN, 13));
        comboBoxPhanQuyen.setBounds(200, 305, 100, 30);
        comboBoxPhanQuyen.setBackground(Color.WHITE);

        this.add(panelHomePage);

        panelHomePage.add(labelLibrary);
        panelHomePage.add(labelHomePage);

        panelHomePage.add(labelUsername);
        panelHomePage.add(txusername);
        panelHomePage.add(labelPassword);
        panelHomePage.add(txpassword);
        panelHomePage.add(labelConfirmPass);
        panelHomePage.add(txConfirmPass);
        panelHomePage.add(lablePhanQuyen);
        panelHomePage.add(comboBoxPhanQuyen);

        panelHomePage.add(buttonDangKy);
        panelHomePage.add(buttonDangNhap);
        panelHomePage.add(buttonForgotPasswd);
        // background phai de cuoi
        panelHomePage.add(labelBackground);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==buttonDangNhap){
            try {
                this.dispose();
                new LoginPage();
            } catch (InterruptedException e1) {
                e1.printStackTrace();
            }
        }
        
    }
}

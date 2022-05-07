package MyCustom;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.*;

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
    JComboBox<String> comboBoxPhanQuyen;
    JRadioButton rbNam, rbNu, rbKhac;
    ButtonGroup buttonGroupGioiTinh;

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
        labelHomePage = new JLabel("USER REGISTER");
        labelUsername = new JLabel("Account ");
        labelPassword = new JLabel("Password ");
        labelConfirmPass = new JLabel("Confirm Password");
        labelHoLot = new JLabel("Ho Lot");
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
        labelHomePage.setBounds(130, 0, 230, 150);

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

        textID.setFont(new Font("Arial",Font.ITALIC,18));
        textID.setBounds(220,125,210,30);
        txusername.setFont(new Font("Arial",Font.ITALIC,18));
        txusername.setBounds(220,185,210,30);
        txpassword.setFont(new Font("Arial",Font.ITALIC,18));
        txpassword.setBounds(220,245,210,30);
        txConfirmPass.setFont(new Font("Arial", Font.ITALIC,18));
        txConfirmPass.setBounds(220, 305, 210, 30);
        textHoLot.setFont(new Font("Arial",Font.ITALIC,18));
        textHoLot.setBounds(220,365,210,30);
        textTen.setFont(new Font("Arial",Font.ITALIC,18));
        textTen.setBounds(220,425,210,30);
        textNgaySinh.setFont(new Font("Arial",Font.ITALIC,18));
        textNgaySinh.setBounds(220,485,210,30);
        textSDT.setFont(new Font("Arial",Font.ITALIC,18));
        textSDT.setBounds(220,605,210,30);
       
        //Button Dang nhap, Dang ky, ForgotPasswd
        buttonDangKy = new JButton("Sign up");
        buttonDangNhap = new JButton("Sign in");
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
        panelHomePage.add(textNgaySinh);
        panelHomePage.add(textSDT);

        // panelHomePage.add(comboBoxPhanQuyen);

        panelHomePage.add(buttonDangKy);
        panelHomePage.add(buttonDangNhap);
        // panelHomePage.add(buttonForgotPasswd);
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

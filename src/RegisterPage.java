import javax.swing.*;
import java.awt.*;
public class RegisterPage extends JFrame {
    RegisterPage(){
        this.setTitle("Quản lý thư viện");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.pack();     
        this.setLayout(new GridLayout(1,1));
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        setHomePage();
    }
    public void setHomePage(){
        //set up panel
        JPanel panelHomePage1 = new JPanel();
        panelHomePage1.setLayout(null);
        panelHomePage1.setBackground(Color.WHITE);
        //set up label
        JLabel labelHomePage = new JLabel("Đăng ký");
        JLabel labelUsername = new JLabel(" Tài khoản: ");
        JLabel labelPassword = new JLabel(" Mật khẩu: ");
        JLabel labelPasswordAgain = new JLabel(" Xác nhận lại mật khẩu:");

        //Label Dang nhap
        labelHomePage.setFont(new Font("Arial", Font.BOLD, 40));
        labelHomePage.setBounds(675, 10, 300, 300);

        //Label Username
        labelUsername.setFont(new Font("Arial", Font.BOLD, 20));
        labelUsername.setBounds(540,150,200,200);

        //Label Password
        labelPassword.setFont(new Font("Arial", Font.BOLD, 20));
        labelPassword.setBounds(540,260,200,200);

        //Label PasswordAgain
        labelPasswordAgain.setFont(new Font("Arial", Font.BOLD, 20));
        labelPasswordAgain.setBounds(540,370,250,200);

        JTextField username = new HintTextField(" Enter your username ");
        JTextField password = new HintTextField(" Enter your password ");
        JTextField passwordAgain = new HintTextField(" Enter your password again ");
        username.setFont(new Font("Arial",Font.ITALIC,18));
        username.setBounds(547,280,450,50);
        password.setFont(new Font("Arial",Font.ITALIC,18));
        password.setBounds(547,390,450,50);
        passwordAgain.setFont(new Font("Arial",Font.ITALIC,18));
        passwordAgain.setBounds(547,500,450,50);

        //Button Dang nhap
        JButton buttonDangKy = new JButton("Đăng ký");
        buttonDangKy.setBounds(547,580,450,50);
        buttonDangKy.setFont(new Font("Arial",Font.BOLD,18));
        buttonDangKy.setBackground(Color.CYAN);
        
        this.add(panelHomePage1);
        panelHomePage1.add(labelHomePage);
        panelHomePage1.add(labelUsername);
        panelHomePage1.add(username);
        panelHomePage1.add(labelPassword);
        panelHomePage1.add(password);
        panelHomePage1.add(labelPasswordAgain);
        panelHomePage1.add(passwordAgain);
        panelHomePage1.add(buttonDangKy);
    }
    
}

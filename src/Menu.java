import java.awt.Color;
import java.awt.*;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Menu extends JFrame{
    JPanel panelMenu;
    JLabel labelQLTV, labelBackGroundMenu;
    JButton btSach, btNhanvien, btNXB, btTheLoai, btTacGia, btNhaCC;
    ImageIcon imagebackground;
    public Menu(){
        this.setTitle("Quản lý thư viện");
        this.setSize(600, 650);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLayout(new GridLayout(1,1));

        // imagebackground = new ImageIcon("images\\backgroundMenu.png");
        // this.setIconImage(imagebackground.getImage());
        setMenu();
        //this.pack();
        this.setVisible(true);
    }

    public void setMenu(){
        panelMenu = new JPanel();
        panelMenu.setLayout(null);
        panelMenu.setBackground(Color.WHITE);

        labelQLTV = new JLabel("QUẢN LÝ THƯ VIỆN");
        labelQLTV.setFont(new Font("Arial", Font.BOLD, 20));
        labelQLTV.setForeground(Color.WHITE);
        labelQLTV.setBounds(270, 0, 250, 100);
        
        labelBackGroundMenu = new JLabel();
        labelBackGroundMenu.setIcon(new ImageIcon("images\\backgroundMenu.png"));
        labelBackGroundMenu.setBounds(0,0, 600, 650);

        btSach = new JButton("Sách");
        btNXB = new JButton("Nhà Xuất Bản");
        btTheLoai = new JButton("Thể loại");
        btTacGia = new JButton("Tác giả");
        btNhanvien = new JButton("Nhân viên");
        btNhaCC = new JButton("Nhà cung cấp");

        // Color 
        Color colorBlue = new Color(30,144,255);
        Color colorOrange = new Color(255,215,0);
        Color colorRed_Light = new Color(255,99,71);
        Color colorMangeta = new Color(199,21,133);
        Color colorGreen = new Color(60,179,113);
        Color colorOrange_Dark = new Color(255,140,0);


        btNXB.setFont(new Font("Times new Roman", Font.BOLD, 18));
        btNXB.setForeground(Color.WHITE);
        btNXB.setBackground(colorRed_Light);
        btNXB.setBounds(360,120,150,60);

        btNhaCC.setFont(new Font("Times new Roman", Font.BOLD, 18));
        btNhaCC.setForeground(Color.WHITE);
        btNhaCC.setBackground(colorBlue);
        btNhaCC.setBounds(360, 200, 150, 60);

        btNhanvien.setFont(new Font("Times new Roman", Font.BOLD, 18));
        btNhanvien.setForeground(Color.WHITE);
        btNhanvien.setBackground(colorOrange);
        btNhanvien.setBounds(360, 280, 150, 60);

        btSach.setFont(new Font("Times new Roman", Font.BOLD, 18));
        btSach.setForeground(Color.WHITE);
        btSach.setBackground(colorMangeta);
        btSach.setBounds(360, 360, 150, 60);

        btTheLoai.setFont(new Font("Times new Roman", Font.BOLD, 18));
        btTheLoai.setBackground(colorOrange_Dark);
        btTheLoai.setForeground(Color.WHITE);
        btTheLoai.setBounds(360, 440, 150, 60);

        btTacGia.setForeground(Color.WHITE);
        btTacGia.setFont(new Font("Times new Roman", Font.BOLD, 18));
        btTacGia.setBackground(colorGreen);
        btTacGia.setBounds(360, 520, 150, 60);

        this.add(panelMenu);
        panelMenu.add(labelQLTV);

        panelMenu.add(btSach);
        panelMenu.add(btNhanvien);
        panelMenu.add(btTheLoai);
        panelMenu.add(btNXB);
        panelMenu.add(btNhaCC);
        panelMenu.add(btTacGia);
        panelMenu.add(labelBackGroundMenu);
    }
}

import java.awt.Color;
import java.awt.*;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Menu extends JFrame{
    JPanel panelMenu, panelBackGround, panelTitle, panelLibrary;
    JLabel labelQLTV, labelBackGroundMenu, labelLibrary;
    JButton btSach, btNhanvien, btNXB, btTheLoai, btTacGia, btNhaCC, btThoat, btDangXuat;
    ImageIcon imagebackground;
    Color ColorOCean, ColorDeepAqua, ColorRed;
    public Menu(){
        this.setTitle("Quản lý thư viện");
        this.setSize(959, 750);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLayout(null);

        setMenu();
        // this.pack();
        this.setVisible(true);
    }

    public void setMenu(){
        ColorOCean = new Color(0,139,139);
        ColorDeepAqua = new Color(64,224,220);
        ColorRed = new Color(250, 75,75);

        panelMenu = new JPanel();
        panelMenu.setLayout(new GridLayout(8,1));
        panelMenu.setBackground(Color.WHITE);
        panelMenu.setBounds(0,178, 240, 540);

        panelBackGround = new JPanel();
        panelBackGround.setLayout(null);
        panelBackGround.setBounds(243, 180, 701, 540);

        panelTitle = new JPanel();
        panelTitle.setLayout(null);
        panelTitle.setBounds(243, 0, 700, 178);
        panelTitle.setBackground(ColorDeepAqua);

        panelLibrary = new JPanel();
        panelLibrary.setLayout(null);
        panelLibrary.setBounds(0, 0, 240, 178);
        panelLibrary.setBackground(Color.LIGHT_GRAY);

        labelQLTV = new JLabel("QUẢN LÝ THƯ VIỆN");
        labelQLTV.setFont(new Font("Arial", Font.BOLD, 20));
        labelQLTV.setForeground(ColorRed);
        labelQLTV.setBounds(250, 40, 250, 100);
        
        labelBackGroundMenu = new JLabel();
        labelBackGroundMenu.setIcon(new ImageIcon("images\\backgroundMenu.png"));
        labelBackGroundMenu.setBounds(0,0, 700, 540);

        labelLibrary = new JLabel();
        labelLibrary.setIcon(new ImageIcon("images\\library.png"));
        labelLibrary.setBounds(0, 0, 64, 64);;

        btSach = new JButton("Sách");
        btNXB = new JButton("Nhà Xuất Bản");
        btTheLoai = new JButton("Thể loại");
        btTacGia = new JButton("Tác giả");
        btNhanvien = new JButton("Nhân viên");
        btNhaCC = new JButton("Nhà cung cấp");
        btDangXuat = new JButton("Đăng xuất");
        btThoat = new JButton("Thoát");

        btNXB.setFont(new Font("Times new Roman", Font.BOLD, 18));
        btNXB.setForeground(Color.WHITE);
        btNXB.setBackground(ColorOCean);

        btNhaCC.setFont(new Font("Times new Roman", Font.BOLD, 18));
        btNhaCC.setForeground(Color.WHITE);
        btNhaCC.setBackground(ColorOCean);

        btNhanvien.setFont(new Font("Times new Roman", Font.BOLD, 18));
        btNhanvien.setForeground(Color.WHITE);
        btNhanvien.setBackground(ColorOCean);
        btNhanvien.setIcon(new ImageIcon("images\\group.png"));

        btSach.setFont(new Font("Times new Roman", Font.BOLD, 18));
        btSach.setForeground(Color.WHITE);
        btSach.setBackground(ColorOCean);
        btSach.setIcon(new ImageIcon("images\\book.png"));

        btTheLoai.setFont(new Font("Times new Roman", Font.BOLD, 18));
        btTheLoai.setForeground(Color.WHITE);
        btTheLoai.setBackground(ColorOCean);

        btTacGia.setForeground(Color.WHITE);
        btTacGia.setFont(new Font("Times new Roman", Font.BOLD, 18));
        btTacGia.setBackground(ColorOCean);

        btDangXuat.setForeground(Color.WHITE);
        btDangXuat.setFont(new Font("Times new Roman", Font.BOLD, 18));
        btDangXuat.setBackground(ColorOCean);
        btDangXuat.setIcon(new ImageIcon("images\\logout.png"));

        btThoat.setForeground(Color.WHITE);
        btThoat.setFont(new Font("Times new Roman", Font.BOLD, 18));
        btThoat.setBackground(ColorOCean);
        btThoat.setIcon(new ImageIcon("images\\exit.png"));

        this.add(panelMenu);
        this.add(panelBackGround);
        this.add(panelTitle);
        this.add(panelLibrary);

        panelMenu.add(btSach);
        panelMenu.add(btNhanvien);
        panelMenu.add(btTheLoai);
        panelMenu.add(btNXB);
        panelMenu.add(btNhaCC);
        panelMenu.add(btTacGia);
        panelMenu.add(btDangXuat);
        panelMenu.add(btThoat);

        panelTitle.add(labelQLTV);
        panelBackGround.add(labelBackGroundMenu);
        panelLibrary.add(labelLibrary);
    }
}

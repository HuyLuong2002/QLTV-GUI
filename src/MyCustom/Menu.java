package MyCustom;
import java.awt.Color;
import java.awt.*;
import java.awt.event.*;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import QLTV.GUI.QLNCCGUI;
import QLTV.GUI.QLNXBGUI;
import QLTV.GUI.QLSACHGUI;
import QLTV.GUI.QLTACGIAGUI;

public class Menu extends JFrame implements ActionListener{
    JPanel panelMenu, panelBackGround, panelTitle, panelLibrary;
    JLabel labelQLTV, labelBackGroundMenu, labelLibrary;
    JButton btSach, btNhanvien, btNXB, btTheLoai, btTacGia, btNhaCC, btThoat, btDangXuat;
    ImageIcon imagebackground;
    Color ColorOCean, ColorDeepAqua, ColorRed, ColorBlue;

    public Menu(){
        this.setTitle("Quản lý thư viện");
        this.setSize(1214, 750);
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
        panelBackGround.setBounds(243, 91, 954, 629);

        panelTitle = new JPanel();
        panelTitle.setLayout(null);
        panelTitle.setBounds(243, 0, 954, 89);
        panelTitle.setBackground(ColorDeepAqua);

        panelLibrary = new JPanel();
        panelLibrary.setLayout(null);
        panelLibrary.setBounds(0, 0, 240, 178);
        panelLibrary.setBackground(ColorDeepAqua);

        labelQLTV = new JLabel("QUẢN LÝ THƯ VIỆN");
        labelQLTV.setFont(new Font("Arial", Font.BOLD, 35));
        labelQLTV.setForeground(ColorRed);
        labelQLTV.setBounds(320, 20, 400, 50);
        
        labelBackGroundMenu = new JLabel();
        labelBackGroundMenu.setIcon(new ImageIcon("images\\backgroundMenu.png"));
        labelBackGroundMenu.setBounds(0,0, 954, 629);

        labelLibrary = new JLabel();
        labelLibrary.setIcon(new ImageIcon("images\\user_login1.png"));
        labelLibrary.setBounds(55, 25, 125, 125);;

        btSach = new JButton("Quản lý Sách");
        btNXB = new JButton("Nhà Xuất Bản");
        btTheLoai = new JButton("Quản lý Thể loại");
        btTacGia = new JButton("Quản lý Tác giả");
        btNhanvien = new JButton("Quản lý Nhân viên");
        btNhaCC = new JButton("Nhà cung cấp");
        btDangXuat = new JButton("Đăng xuất");
        btThoat = new JButton("Thoát");

        btNXB.setFont(new Font("Times new Roman", Font.BOLD, 18));
        btNXB.setForeground(Color.WHITE);
        btNXB.setBackground(ColorOCean);
        btNXB.setIcon(new ImageIcon("images\\publisher.png"));
        btNXB.setHorizontalAlignment(SwingConstants.LEFT);
        btNXB.addActionListener(this);

        btNhaCC.setFont(new Font("Times new Roman", Font.BOLD, 18));
        btNhaCC.setForeground(Color.WHITE);
        btNhaCC.setBackground(ColorOCean);
        btNhaCC.setIcon(new ImageIcon("images\\courier.png"));
        btNhaCC.setHorizontalAlignment(SwingConstants.LEFT);
        btNhaCC.addActionListener(this);

        btNhanvien.setFont(new Font("Times new Roman", Font.BOLD, 18));
        btNhanvien.setForeground(Color.WHITE);
        btNhanvien.setBackground(ColorOCean);
        btNhanvien.setIcon(new ImageIcon("images\\group.png"));
        btNhanvien.setHorizontalAlignment(SwingConstants.LEFT);
        btNhanvien.addActionListener(this);

        btSach.setFont(new Font("Times new Roman", Font.BOLD, 18));
        btSach.setForeground(Color.WHITE);
        btSach.setBackground(ColorOCean);
        btSach.setIcon(new ImageIcon("images\\book.png"));
        btSach.setHorizontalAlignment(SwingConstants.LEFT);
        btSach.addActionListener(this);

        btTheLoai.setFont(new Font("Times new Roman", Font.BOLD, 18));
        btTheLoai.setForeground(Color.WHITE);
        btTheLoai.setBackground(ColorOCean);
        btTheLoai.setIcon(new ImageIcon("images\\list.png"));
        btTheLoai.setHorizontalAlignment(SwingConstants.LEFT);
        btTheLoai.addActionListener(this);

        btTacGia.setForeground(Color.WHITE);
        btTacGia.setFont(new Font("Times new Roman", Font.BOLD, 18));
        btTacGia.setBackground(ColorOCean);
        btTacGia.setIcon(new ImageIcon("images\\author.png"));
        btTacGia.setHorizontalAlignment(SwingConstants.LEFT);
        btTacGia.addActionListener(this);

        btDangXuat.setForeground(Color.WHITE);
        btDangXuat.setFont(new Font("Times new Roman", Font.BOLD, 18));
        btDangXuat.setBackground(ColorOCean);
        btDangXuat.setIcon(new ImageIcon("images\\logout.png"));
        btDangXuat.setHorizontalAlignment(SwingConstants.LEFT);
        btDangXuat.addActionListener(this);

        btThoat.setForeground(Color.WHITE);
        btThoat.setFont(new Font("Times new Roman", Font.BOLD, 18));
        btThoat.setBackground(ColorOCean);
        btThoat.setIcon(new ImageIcon("images\\exit.png"));
        btThoat.setHorizontalAlignment(SwingConstants.LEFT);
        btThoat.addActionListener(this);

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

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==btSach){
            this.dispose();
            new QLSACHGUI();
        }
        if(e.getSource()==btTacGia){
            this.dispose();
            new QLTACGIAGUI();
        }
        if(e.getSource()==btNXB){
            this.dispose();
            new QLNXBGUI();
        }
        if(e.getSource()==btNhaCC){
            try {
                this.dispose();
                new QLNCCGUI(null);
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
        
    }
}

package QLTV.GUI;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Properties;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

public class QLMuonGUI extends JFrame implements ActionListener, MouseListener {
    JPanel pnMenu, pnTabMuon, pnMuon, pnCTMuon;

    JLabel lbHome;
    JLabel lbTTMuon;

    JButton btMenu, btMT, btQLNV, btDangXuat, btNhapSach, btThoat,btMenuTimKiem,btThongKe;

    TitledBorder titleMuon;
    JTabbedPane tabbedPane;

    JTable tblQLMuon, tblQLCTMuon;
    DefaultTableModel modelMuon;

    UtilDateModel modelNgayBD, modelNgayKT;
    Properties pNgayBD, pNgayKT;
    JDatePanelImpl datePanelNgayBD, datePanelNgayKT;
    JDatePickerImpl datePickerNgayBD, datePickerNgayKT;

    public QLMuonGUI() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        this.setTitle("Quản lý mượn sách");
        this.setLayout(null);

        pnTabMuon = new JPanel();
        pnTabMuon.setLayout(new GridLayout(1, 2, 5, 0));

        pnMuon = new JPanel();
        pnMuon.setLayout(new GridLayout(1, 1));

        pnCTMuon = new JPanel();
        pnCTMuon.setLayout(new GridLayout(1, 1));

        tabbedPane = new JTabbedPane();
        tabbedPane.addTab("Mượn sách", pnTabMuon);
        tabbedPane.addTab("Trả sách", null);
        tabbedPane.addTab("HĐ Tiền phạt", null);
        tabbedPane.setBounds(320, 10, 1200, 400);

        pnMenu = new JPanel();
        pnMenu.setBackground(Color.LIGHT_GRAY);
        pnMenu.setLayout(new GridLayout(15, 1));
        pnMenu.setSize(new Dimension(300, 1200));

        this.add(tabbedPane);
        this.add(pnMenu);
        pnTabMuon.add(pnMuon);
        pnTabMuon.add(pnCTMuon);
        setMenu();
        setTitle();
        setTableMuon();
        setTableCTMuon();


        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    public void setTitle() {
        // set Border
        Border empty;
        empty = BorderFactory.createEmptyBorder();

        titleMuon = BorderFactory.createTitledBorder(empty,"THÔNG TIN MƯỢN");
        titleMuon.setTitleFont(new Font("Arial", Font.BOLD, 28));
        titleMuon.setTitleJustification(TitledBorder.CENTER);
        pnMuon.setBorder(titleMuon);

        titleMuon = BorderFactory.createTitledBorder(empty,"CHI TIẾT MƯỢN");
        titleMuon.setTitleFont(new Font("Arial", Font.BOLD, 28));
        titleMuon.setTitleJustification(TitledBorder.CENTER);
        pnCTMuon.setBorder(titleMuon);

    }

    public void setTableMuon() {
        // ----set up table----
        tblQLMuon = new JTable();
        JScrollPane pane = new JScrollPane(tblQLMuon);
        pane.setAutoscrolls(true);
        tblQLMuon.setRowHeight(20);
        tblQLMuon.setFont(new Font(null, 0, 13));
        tblQLMuon.setBackground(Color.LIGHT_GRAY);
        tblQLMuon.addMouseListener(this);
        tblQLMuon.setDefaultEditor(Object.class, null);
        pnMuon.add(pane);
    }

    public void setTableCTMuon() {
        // ----set up table----
        tblQLCTMuon = new JTable();
        JScrollPane pane = new JScrollPane(tblQLCTMuon);
        pane.setAutoscrolls(true);
        tblQLCTMuon.setRowHeight(20);
        tblQLCTMuon.setFont(new Font(null, 0, 13));
        tblQLCTMuon.setBackground(Color.LIGHT_GRAY);
        tblQLCTMuon.addMouseListener(this);
        tblQLCTMuon.setDefaultEditor(Object.class, null);
        pnCTMuon.add(pane);
    }

    public void setMenu() {
        // Set menu side left
        ImageIcon iconHome = new ImageIcon("images\\home.png");
        ImageIcon iconMenu = new ImageIcon("images\\menu.png");
        ImageIcon iconSearch = new ImageIcon("images\\search.png");
        ImageIcon iconRent = new ImageIcon("images\\payment.png");
        ImageIcon iconTK = new ImageIcon("images\\trend.png");
        ImageIcon iconNS = new ImageIcon("images\\cart.png");
        ImageIcon iconLogout = new ImageIcon("images\\logout.png");
        ImageIcon iconExited = new ImageIcon("images\\exit.png");

        lbHome = new JLabel();
        lbHome.setHorizontalAlignment(SwingConstants.CENTER);
        lbHome.setIcon(iconHome);

        btMenu = new JButton("Menu");
        btMenu.setFont(new Font("Arial", Font.BOLD, 20));
        btMenu.setBackground(Color.LIGHT_GRAY);
        btMenu.setIcon(iconMenu);
        btMenu.setHorizontalAlignment(SwingConstants.LEFT);
        btMenu.setBorder(BorderFactory.createEmptyBorder());
        btMenu.addActionListener(this);

        btMenuTimKiem = new JButton("Tìm kiếm sách");
        btMenuTimKiem.setFont(new Font("Arial", Font.BOLD, 20));
        btMenuTimKiem.setBackground(Color.LIGHT_GRAY);
        btMenuTimKiem.setIcon(iconSearch);
        btMenuTimKiem.setHorizontalAlignment(SwingConstants.LEFT);
        btMenuTimKiem.setBorder(BorderFactory.createEmptyBorder());
        btMenuTimKiem.addActionListener(this);

        btMT = new JButton("Mượn trả sách");
        btMT.setFont(new Font("Arial", Font.BOLD, 20));
        btMT.setBackground(Color.LIGHT_GRAY);
        btMT.setIcon(iconRent);
        btMT.setHorizontalAlignment(SwingConstants.LEFT);
        btMT.setBorder(BorderFactory.createEmptyBorder());
        btMT.addActionListener(this);

        btNhapSach = new JButton("Nhập sách");
        btNhapSach.setFont(new Font("Arial", Font.BOLD, 20));
        btNhapSach.setBackground(Color.LIGHT_GRAY);
        btNhapSach.setIcon(iconNS);
        btNhapSach.setHorizontalAlignment(SwingConstants.LEFT);
        btNhapSach.setBorder(BorderFactory.createEmptyBorder());
        btNhapSach.addActionListener(this);

        btQLNV = new JButton("Quản lý nghiệp vụ");
        btQLNV.setFont(new Font("Arial", Font.BOLD, 20));
        btQLNV.setBackground(Color.LIGHT_GRAY);
        btQLNV.setIcon(iconRent);
        btQLNV.setHorizontalAlignment(SwingConstants.LEFT);
        btQLNV.setBorder(BorderFactory.createEmptyBorder());
        btQLNV.addActionListener(this);

        btThongKe = new JButton("Thống kê");
        btThongKe.setFont(new Font("Arial", Font.BOLD, 20));
        btThongKe.setBackground(Color.LIGHT_GRAY);
        btThongKe.setIcon(iconTK);
        btThongKe.setHorizontalAlignment(SwingConstants.LEFT);
        btThongKe.setBorder(BorderFactory.createEmptyBorder());
        btThongKe.addActionListener(this);

        // JButton Đăng xuất
        btDangXuat = new JButton("Đăng xuất");
        btDangXuat.setFont(new Font("Arial", Font.BOLD, 20));
        btDangXuat.setBackground(Color.LIGHT_GRAY);
        btDangXuat.setIcon(iconLogout);
        btDangXuat.setHorizontalAlignment(SwingConstants.LEFT);
        btDangXuat.setBorder(BorderFactory.createEmptyBorder());
        btDangXuat.addActionListener(this);
        // JButton thoát
        btThoat = new JButton("Thoát");
        btThoat.setFont(new Font("Arial", Font.BOLD, 20));
        btThoat.setBackground(Color.LIGHT_GRAY);
        btThoat.setIcon(iconExited);
        btThoat.setHorizontalAlignment(SwingConstants.LEFT);
        btThoat.setBorder(BorderFactory.createEmptyBorder());
        btThoat.addActionListener(this);

        // add Menu button
        pnMenu.add(lbHome);
        pnMenu.add(btMenu);
        pnMenu.add(btMenuTimKiem);
        pnMenu.add(btMT);
        pnMenu.add(btNhapSach);
        pnMenu.add(btQLNV);
        pnMenu.add(btThongKe);
        pnMenu.add(btDangXuat);
        pnMenu.add(btThoat);
    }
}

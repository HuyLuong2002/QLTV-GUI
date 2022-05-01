package MyCustom;

import java.util.Vector;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class HoTroNhap extends JFrame {
    DefaultTableModel model;
    JLabel lbHoTro;
    JPanel pnTable;
    JButton btLuaChon;

    public HoTroNhap(JScrollPane pane, DefaultTableModel model, Vector<String> header) {
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setSize(700,600);
        this.setTitle("Lựa chọn thông tin");
        this.setLayout(null);

        pnTable = new JPanel();
        pnTable.setLayout(new GridLayout(1,1));
        pnTable.setBounds(40,65,600,300);

        lbHoTro = new JLabel("LỰA CHỌN SÁCH");
        lbHoTro.setFont(new Font("Arial", Font.BOLD, 20));
        lbHoTro.setBounds(250,0,200,50);

        
        
        this.add(pnTable);
        this.add(lbHoTro);
        pnTable.add(pane);
        
        this.setVisible(true);
    }
    
}

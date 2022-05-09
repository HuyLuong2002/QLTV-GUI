package QLTV.GUI;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Vector;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import MyCustom.RoundedBorder;
import QLTV.BUS.QLNVBUS;
import QLTV.DTO.DOCGIA;

public class QLNVGUI implements MouseListener, ActionListener {
    JPanel pnQLNV, pnTabDG, pnTabTheoDoiMT, pnDG, pnShowAll, pnNhapDG,
            pnTimKiemPM, pnLocPM;
    JTabbedPane tabbedPane;
    public JTable tblQLDG, tblQLPTD;
    public DefaultTableModel modelDG, modelPTD;
    TitledBorder titleDG;
    JButton btShowAll;
    public QLNVGUI() {

    }

    public JPanel setQLNVGUI() {
        if (pnQLNV == null) {
            pnQLNV = new JPanel();
            pnQLNV.setBounds(240, 0, 1145, 800);
            pnQLNV.setLayout(null);

            // panel tab mượn
            pnTabDG = new JPanel();
            pnTabDG.setLayout(new GridLayout(1, 1, 0, 0));

            pnDG = new JPanel();
            pnDG.setLayout(new GridLayout(1, 1));

            pnShowAll = new JPanel();
            pnShowAll.setBounds(5, 358, 1135, 50);
            pnShowAll.setLayout(null);

            pnNhapDG = new JPanel();
            pnNhapDG.setLayout(null);
            pnNhapDG.setBounds(5, 410, 1137, 370);

            pnTimKiemPM = new JPanel();
            pnTimKiemPM.setLayout(null);
            pnTimKiemPM.setBounds(570, 0, 567, 200);

            pnLocPM = new JPanel();
            pnLocPM.setLayout(null);
            pnLocPM.setBounds(570, 195, 300, 155);

            tabbedPane = new JTabbedPane();
            tabbedPane.addTab("Độc giả", pnTabDG);
            tabbedPane.addTab("Phiếu theo dõi mượn trả", pnTabTheoDoiMT);
            tabbedPane.addMouseListener(this);
            tabbedPane.setBounds(5, 5, 1138, 350);

            pnQLNV.add(tabbedPane);
            pnQLNV.add(pnShowAll);
            pnQLNV.add(pnNhapDG);

            pnNhapDG.add(pnTimKiemPM);
            pnNhapDG.add(pnLocPM);

            pnTabDG.add(pnDG);

            // Phiếu mượn, chi tiết mượn
            setTitleDG();
            setTableDG();
            setShowAll();
            //setInputMuon();
            //setTimKiemPM();
            //setLocPM();
            getDBDG();
        }

        return pnQLNV;
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
    public void setTableDG() {
        // ----set up table----
        tblQLDG = new JTable();
        JScrollPane pane = new JScrollPane(tblQLDG);
        pane.setAutoscrolls(true);
        tblQLDG.setRowHeight(20);
        tblQLDG.setFont(new Font(null, 0, 13));
        tblQLDG.setBackground(Color.LIGHT_GRAY);
        tblQLDG.addMouseListener(this);
        tblQLDG.setDefaultEditor(Object.class, null);
        tblQLDG.setSelectionBackground(Color.GREEN);
        pnDG.add(pane);
    }

    public void getDBDG() {
        try {
            QLNVBUS qlnvbus = new QLNVBUS();
            if (QLNVBUS.dsdg == null)
                try {
                    qlnvbus.docDS();
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            // set up header(column)
            Vector<String> header = new Vector<String>();
            header.add("Mã độc giả");
            header.add("Tên độc giả");
            header.add("Địa chỉ");
            header.add("Email");
            header.add("Tình trạng thuê");
            modelDG = new DefaultTableModel(header, 0);
            for (DOCGIA dg : QLNVBUS.dsdg) {
                ShowOnTableDG(dg);
            }
            tblQLDG.setModel(modelDG);
        } catch (Exception e1) {
            System.out.println(e1);
        }
    }

    public void ShowOnTableDG(DOCGIA dg) {
        Vector<String> row = new Vector<String>();
        row.add(dg.getMaDG().trim());
        row.add(dg.getTenDG().trim());
        row.add(dg.getDiachi().trim());
        row.add(dg.getMail().trim());
        row.add(dg.getTinhtrangthue().trim());
        modelDG.addRow(row);
    }

    public void setTitleDG() {
        // set Border
        Border empty;
        empty = BorderFactory.createEmptyBorder();

        titleDG = BorderFactory.createTitledBorder(empty, "THÔNG TIN ĐỘC GIẢ");
        titleDG.setTitleFont(new Font("Arial", Font.BOLD, 28));
        titleDG.setTitleJustification(TitledBorder.CENTER);
        pnDG.setBorder(titleDG);
    }

    public void setShowAll() {
        btShowAll = new JButton("Hiển thị tất cả");
        btShowAll.setFont(new Font("Arial", Font.BOLD, 15));
        btShowAll.setBounds(1000, 10, 130, 30);
        btShowAll.setBackground(Color.cyan);
        btShowAll.setBorder(new RoundedBorder(10));
        btShowAll.addActionListener(this);

        pnShowAll.add(btShowAll);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
    }
}

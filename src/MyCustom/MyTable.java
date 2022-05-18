package MyCustom;

import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public class MyTable {
    public MyTable() {

    }
    public void setValueCellCenter(DefaultTableModel model, JTable table) {
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        for (int i = 0; i < model.getColumnCount(); i++) {
            table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }
    }

    public String RemoveCommaInString(JTextField Tien) {
        String tmp[] = Tien.getText().split(",");
        String Dongia = "";
        for (int i = 0; i < tmp.length; i++) {
            Dongia = Dongia + tmp[i];
        }
        return Dongia;
    }
    
}

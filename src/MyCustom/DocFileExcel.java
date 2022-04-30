package MyCustom;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Vector;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Color;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import QLTV.BUS.QLSACHBUS;
import QLTV.DTO.SACH;

public class DocFileExcel {
    FileInputStream excelfis=null;
    BufferedInputStream excelbis=null;
    XSSFWorkbook excelJTableImport=null;
    public DocFileExcel() {}
    public DocFileExcel(String namepath, JTable excelTable, DefaultTableModel model) {
        try {
            File file = new File(namepath); // creating a new file instance
            excelfis = new FileInputStream(file); // obtaining bytes from the file
            excelbis = new BufferedInputStream(excelfis);
            // creating Workbook instance that refers to .xlsx file
            excelJTableImport = new XSSFWorkbook(excelbis);
            XSSFSheet excelSheet = excelJTableImport.getSheetAt(0); // creating a Sheet object to retrieve object
            //Looping through excel colums and rows
            for(int i=1; i <= excelSheet.getLastRowNum(); i++){
                XSSFRow excelRow = excelSheet.getRow(i);
                XSSFCell excelMasach = excelRow.getCell(0);
                XSSFCell excelTensach = excelRow.getCell(1);
                XSSFCell excelNXB = excelRow.getCell(2);
                XSSFCell excelTG = excelRow.getCell(3);
                XSSFCell excelNamXB = excelRow.getCell(4);
                XSSFCell excelSLtong = excelRow.getCell(5);
                XSSFCell excelSL= excelRow.getCell(6);
                XSSFCell excelDongia = excelRow.getCell(7);

                SACH sach = new SACH();
                sach.setMasach(excelMasach.getStringCellValue());
                sach.setTensach(excelTensach.getStringCellValue());
                sach.setMaNXB(excelNXB.getStringCellValue());
                sach.setMaTG(excelTG.getStringCellValue());
                sach.setNamXB(String.valueOf(excelNamXB.getNumericCellValue()).substring(0,4));
                sach.setSLtong((int)excelSLtong.getNumericCellValue());
                sach.setSL((int)(excelSL.getNumericCellValue()));
                sach.setDongia((int)(excelDongia.getNumericCellValue()));

                QLSACHBUS qlsachbus = new QLSACHBUS();
                int kiemtra = 0;
                try {
                    kiemtra = qlsachbus.themDataExcel(sach);
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
                if (kiemtra == 1) {
                    // Đưa dữ liệu lên table
                    Vector<String> header = new Vector<String>();
                    header.add("Mã sách");
                    header.add("Tên sách");
                    header.add("Mã NXB");
                    header.add("Mã tác giả");
                    header.add("Năm xuất bản");
                    header.add("SL tổng");
                    header.add("SL");
                    header.add("Đơn giá");
                    if (model.getRowCount() == 0) {
                        model = new DefaultTableModel(header, 0);
                    }
                    ShowOnTable(sach,model);
                    excelTable.setModel(model); 
                }


                // for(int j=0; j < excelRow.getLastCellNum(); j++){
                //     XSSFCell excelCell = excelRow.getCell(j);
                //     System.out.println(excelCell.getStringCellValue());
                // }
            }

        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
        catch (IOException e){
            System.out.println(e.getMessage());
        }
        JOptionPane.showMessageDialog(null, "Thêm dữ liệu thành công", "Thông báo",
        JOptionPane.INFORMATION_MESSAGE);
    }

    public void ShowOnTable(SACH sach, DefaultTableModel model) {
        Vector<String> row = new Vector<String>();
        row.add(sach.getMasach().trim());
        row.add(sach.getTensach().trim());
        row.add(sach.getMaNXB().trim());
        row.add(sach.getMaTG().trim());
        row.add(sach.getNamXB().trim());
        row.add(String.valueOf(sach.getSLtong()));
        row.add(String.valueOf(sach.getSL()));
        row.add(String.valueOf(sach.getDongia()));
        model.addRow(row);
    }
}
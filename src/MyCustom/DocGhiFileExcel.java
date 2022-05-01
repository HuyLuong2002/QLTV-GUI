package MyCustom;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import QLTV.BUS.QLSACHBUS;
import QLTV.DTO.SACH;

public class DocGhiFileExcel {
    FileInputStream excelfis = null;
    BufferedInputStream excelbis = null;
    FileOutputStream excelFOU = null;
    BufferedOutputStream excelBOU = null;
    XSSFWorkbook excelJTableImport = null;
    XSSFWorkbook excelJTableExport = null;
    XSSFSheet excelSheet= null;

    public DocGhiFileExcel() {
    }

    public DocGhiFileExcel(String namepath, JTable excelTable, DefaultTableModel model, JFileChooser filechooser,
            int flag) {
        try {
            if (flag == 1) {
                File file = new File(namepath); // creating a new file instance
                excelfis = new FileInputStream(file); // obtaining bytes from the file
                excelbis = new BufferedInputStream(excelfis); // creating a Sheet object to retrieve object
                // creating Workbook instance that refers to .xlsx file
                excelJTableImport = new XSSFWorkbook(excelbis);
                excelSheet = excelJTableImport.getSheetAt(0);
                // Looping through excel colums and rows
                for (int i = 1; i <= excelSheet.getLastRowNum(); i++) {
                    XSSFRow excelRow = excelSheet.getRow(i);
                    XSSFCell excelMasach = excelRow.getCell(0);
                    XSSFCell excelTensach = excelRow.getCell(1);
                    XSSFCell excelNXB = excelRow.getCell(2);
                    XSSFCell excelTG = excelRow.getCell(3);
                    XSSFCell excelNamXB = excelRow.getCell(4);
                    XSSFCell excelSLtong = excelRow.getCell(5);
                    XSSFCell excelSL = excelRow.getCell(6);
                    XSSFCell excelDongia = excelRow.getCell(7);

                    SACH sach = new SACH();
                    sach.setMasach(excelMasach.getStringCellValue());
                    sach.setTensach(excelTensach.getStringCellValue());
                    sach.setMaNXB(excelNXB.getStringCellValue());
                    sach.setMaTG(excelTG.getStringCellValue());
                    sach.setNamXB(String.valueOf(excelNamXB.getNumericCellValue()).substring(0, 4));
                    sach.setSLtong((int) excelSLtong.getNumericCellValue());
                    sach.setSL((int) (excelSL.getNumericCellValue()));
                    sach.setDongia((int) (excelDongia.getNumericCellValue()));

                    QLSACHBUS qlsachbus = new QLSACHBUS();
                    int kiemtra = 0;
                    try {
                        kiemtra = qlsachbus.themDataExcel(sach);
                    } catch (Exception e1) {
                        e1.printStackTrace();
                    }
                    if (kiemtra == 1) {
                        // Đưa dữ liệu lên table
                        Vector <String> header = new Vector<String>();
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
                        ShowOnTable(sach, model);
                        excelTable.setModel(model);
                    }
                }
                JOptionPane.showMessageDialog(null, "Thêm dữ liệu thành công", "Thông báo",
                        JOptionPane.INFORMATION_MESSAGE);
            }
            if (flag == 0) { //Export file excel
                excelJTableExport = new XSSFWorkbook();
                excelSheet = excelJTableExport.createSheet("KhoSachExcel");

                for (int i = 0; i < model.getRowCount(); i++) {
                    XSSFRow excelRow = excelSheet.createRow(i + 1);
                    for (int j = 0; j < model.getColumnCount(); j++) {
                        XSSFCell excelCell = excelRow.createCell(j);
                        excelCell.setCellValue(model.getValueAt(i, j).toString());
                    }
                }
                // Append xlsx file extension to selected files. To
                // create unique file names.
                excelFOU = new FileOutputStream(filechooser.getSelectedFile() + ".xlsx");
                excelBOU = new BufferedOutputStream(excelFOU);
                // excelJTableExport.write(excelBOU);
            }

        } catch (FileNotFoundException e) {
            Logger.getLogger(DocGhiFileExcel.class.getName()).log(Level.SEVERE, null, e);
        } catch (IOException e) {
            Logger.getLogger(DocGhiFileExcel.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            try {
                if (excelfis != null) {
                    excelfis.close();
                }
                if (excelbis != null) {
                    excelbis.close();
                }
                if (excelJTableImport != null) {
                    excelJTableImport.close();
                }
            } catch (IOException e) {
                Logger.getLogger(DocGhiFileExcel.class.getName()).log(Level.SEVERE, null, e);
            }
        }
    }

    public void setTitleInExcel(DefaultTableModel model) {
        XSSFRow excelRow = excelSheet.createRow(0);
        String title[]={"Mã sách","Tên sách","Mã NXB","Mã tác giả","Năm xuất bản","Số lượng tổng","Số lượng","Đơn giá"};
        for (int i = 0; i < model.getColumnCount(); i++) {
            XSSFCell excelCell = excelRow.createCell(i);
            excelCell.setCellValue(title[i]);
        }
        try {
            excelJTableExport.write(excelBOU);
            JOptionPane.showMessageDialog(null, "Xuất dữ liệu thành công", "Thông báo",
            JOptionPane.INFORMATION_MESSAGE);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        finally{
            try {
                if (excelBOU != null) {
                    excelBOU.close();
                }
                if (excelFOU != null) {
                    excelFOU.close();
                }
                if (excelJTableExport != null) {
                    excelJTableExport.close();
                }
            } catch (Exception e) {
                Logger.getLogger(DocGhiFileExcel.class.getName()).log(Level.SEVERE, null, e);
            }
        }

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
package MyCustom;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import QLTV.DTO.SACH;

public class ExportPDF {
    JFileChooser fileChooser;
    String namepath = "";
    public ExportPDF() {
        fileChooser = new JFileChooser();
        fileChooser.setCurrentDirectory(new File("C:\\"));
        FileNameExtensionFilter fnef = new FileNameExtensionFilter("PDF file", "pdf");
        fileChooser.setFileFilter(fnef);
        fileChooser.setDialogTitle("Lựa chọn file để lưu");
        int response = fileChooser.showSaveDialog(null);
        if (response == JFileChooser.APPROVE_OPTION) {
            File file = new File(fileChooser.getSelectedFile().getAbsolutePath());
            namepath = file.getAbsolutePath();
        }
    }

    public void setExportPDFPM(String MaPM, String TenDG, String NgayMuon, String NgayTra, String SLtong, ArrayList<SACH> kq) {
        // tạo một document
        Document document = new Document();
        BaseFont bf = null;
        try {
            // khởi tạo một PdfWriter truyền vào document và FileOutputStream

            PdfWriter.getInstance(document, new FileOutputStream(namepath + ".pdf"));
            // mở file để thực hiện viết
            document.open();
            // thêm nội dung sử dụng add function
            bf = BaseFont.createFont("font/ARIALUNI.TTF", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
            Font fontPDF = new Font(bf, 13);
            Font fontTitle = new Font(bf,18,Font.BOLD);
            Font fontHeader = new Font(bf,13,Font.BOLD);
            Paragraph paragraph = new Paragraph("PHIẾU MƯỢN SÁCH", fontTitle);
            paragraph.setAlignment(Element.ALIGN_CENTER);
            // Khai báo 2 paragraph
            Paragraph paragraph1 = new Paragraph("Mã phiếu mượn: " + MaPM + "\nTên độc giả: " + TenDG
                    + "\nNgày mượn: " + NgayMuon + "\nNgày trả: " + NgayTra + "\nSố lượng mượn: " + SLtong + "\n",
                    fontPDF);
            // Định dạng đoạn văn bản thứ nhất
            paragraph1.setAlignment(Element.ALIGN_LEFT);
            paragraph1.setSpacingAfter(16);
            // Đinh dạng đoạn văn bản thứ 2
            Paragraph paragraph2 = new Paragraph("Danh sách mượn:\n", fontHeader);
            paragraph2.setAlignment(Element.ALIGN_LEFT);
            PdfPTable pdfPTable1 = new PdfPTable(4);
            pdfPTable1.setSpacingBefore(16);
            // Create cells
            PdfPCell pdfPCell1 = new PdfPCell(new Paragraph("Mã sách",fontHeader));
            pdfPCell1.setBorderColor(BaseColor.WHITE);
            pdfPCell1.setHorizontalAlignment(Element.ALIGN_CENTER);
            PdfPCell pdfPCell2 = new PdfPCell(new Paragraph("Tên sách",fontHeader));
            pdfPCell2.setColspan(2);
            pdfPCell2.setBorderColor(BaseColor.WHITE);
            pdfPCell2.setHorizontalAlignment(Element.ALIGN_LEFT);
            PdfPCell pdfPCell3 = new PdfPCell(new Paragraph("Số lượng",fontHeader));
            pdfPCell3.setBorderColor(BaseColor.WHITE);
            pdfPCell3.setHorizontalAlignment(Element.ALIGN_CENTER);
            // Add cells to table
            pdfPTable1.addCell(pdfPCell1);
            pdfPTable1.addCell(pdfPCell2);
            pdfPTable1.addCell(pdfPCell3);

            // Thêm 2 đoạn văn bản vào document
            document.add(paragraph);
            document.add(paragraph1);
            document.add(paragraph2);
            document.add(pdfPTable1);

            // Thêm danh sách mượn sách
            for (int i = 0; i < kq.size(); i++) {
                PdfPTable pdfPTable2 = new PdfPTable(4);
                SACH sach = kq.get(i);

                Paragraph paragraph3 = new Paragraph(sach.getMasach());
                Paragraph paragraph4 = new Paragraph(sach.getTensach(),fontPDF);
                Paragraph paragraph5 = new Paragraph(String.valueOf(sach.getSL()));
                // Paragraph paragraph6 = new Paragraph("");

                PdfPCell PdfPCell1 = new PdfPCell(paragraph3);
                PdfPCell1.setBorderColor(BaseColor.WHITE);
                PdfPCell1.setHorizontalAlignment(Element.ALIGN_CENTER);
                PdfPCell PdfPCell2 = new PdfPCell(paragraph4);
                PdfPCell2.setColspan(2);
                PdfPCell2.setBorderColor(BaseColor.WHITE);
                PdfPCell2.setHorizontalAlignment(Element.ALIGN_LEFT);
                PdfPCell PdfPCell3 = new PdfPCell(paragraph5);
                PdfPCell3.setBorderColor(BaseColor.WHITE);
                PdfPCell3.setHorizontalAlignment(Element.ALIGN_CENTER);

                
                pdfPTable2.addCell(PdfPCell1);
                pdfPTable2.addCell(PdfPCell2);
                pdfPTable2.addCell(PdfPCell3);

                document.add(pdfPTable2);
            }

        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // đóng file
            if (document != null) {
                document.close();
            }
            JOptionPane.showMessageDialog(null, "In thành công", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    public void setExportPDFHDTP(String MaHD, String TenDG, String SLtong, String Tienphat, ArrayList<SACH> kq) {
        // tạo một document
        Document document = new Document();
        BaseFont bf = null;
        try {
            // khởi tạo một PdfWriter truyền vào document và FileOutputStream

            PdfWriter.getInstance(document, new FileOutputStream(namepath + ".pdf"));
            // mở file để thực hiện viết
            document.open();
            // thêm nội dung sử dụng add function
            bf = BaseFont.createFont("font/ARIALUNI.TTF", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
            Font fontPDF = new Font(bf, 14);
            Font fontTitle = new Font(bf,20,Font.BOLD);
            Font fontHeader = new Font(bf,14,Font.BOLD);
            Paragraph paragraph = new Paragraph("HÓA ĐƠN TIỀN PHẠT", fontTitle);
            paragraph.setAlignment(Element.ALIGN_CENTER);
            // Khai báo 2 paragraph
            Paragraph paragraph1 = new Paragraph("Mã hóa đơn: " + MaHD + "\nTên độc giả: " + TenDG
                    + "\nSố lượng tổng: " + SLtong + "\nTiền phạt: " + Tienphat + "\n",
                    fontPDF);
            // Định dạng đoạn văn bản thứ nhất
            paragraph1.setAlignment(Element.ALIGN_LEFT);
            paragraph1.setSpacingAfter(16);
            // Đinh dạng đoạn văn bản thứ 2
            Paragraph paragraph2 = new Paragraph("Danh sách sách:\n",fontHeader);
            paragraph2.setAlignment(Element.ALIGN_LEFT);
            PdfPTable pdfPTable1 = new PdfPTable(5);
            pdfPTable1.setSpacingBefore(16);
            // Create cells
            PdfPCell pdfPCell1 = new PdfPCell(new Paragraph("Mã sách",fontHeader));
            pdfPCell1.setHorizontalAlignment(Element.ALIGN_CENTER);
            pdfPCell1.setBorderColor(BaseColor.WHITE);
            PdfPCell pdfPCell2 = new PdfPCell(new Paragraph("Tên sách",fontHeader));
            pdfPCell2.setColspan(2);
            pdfPCell2.setBorderColor(BaseColor.WHITE);
            pdfPCell2.setHorizontalAlignment(Element.ALIGN_LEFT);
            PdfPCell pdfPCell3 = new PdfPCell(new Paragraph("Số lượng",fontHeader));
            pdfPCell3.setBorderColor(BaseColor.WHITE);
            pdfPCell3.setHorizontalAlignment(Element.ALIGN_CENTER);
            PdfPCell pdfPCell4 = new PdfPCell(new Paragraph("Đơn giá",fontHeader));
            pdfPCell4.setBorderColor(BaseColor.WHITE);
            pdfPCell4.setHorizontalAlignment(Element.ALIGN_CENTER);
            // Add cells to table
            pdfPTable1.addCell(pdfPCell1);
            pdfPTable1.addCell(pdfPCell2);
            pdfPTable1.addCell(pdfPCell3);
            pdfPTable1.addCell(pdfPCell4);

            // Thêm 2 đoạn văn bản vào document
            document.add(paragraph);
            document.add(paragraph1);
            document.add(paragraph2);
            document.add(pdfPTable1);

            // Thêm danh sách mượn sách
            for (int i = 0; i < kq.size(); i++) {
                PdfPTable pdfPTable2 = new PdfPTable(5);
                SACH sach = kq.get(i);

                Paragraph paragraph3 = new Paragraph(sach.getMasach());
                Paragraph paragraph4 = new Paragraph(sach.getTensach(),fontPDF);
                Paragraph paragraph5 = new Paragraph(String.valueOf(sach.getSL()));
                Paragraph paragraph6 = new Paragraph(String.format("%,d",sach.getDongia()));

                PdfPCell PdfPCell1 = new PdfPCell(paragraph3);
                PdfPCell1.setBorderColor(BaseColor.WHITE);
                PdfPCell1.setHorizontalAlignment(Element.ALIGN_CENTER);
                PdfPCell PdfPCell2 = new PdfPCell(paragraph4);
                PdfPCell2.setColspan(2);
                PdfPCell2.setBorderColor(BaseColor.WHITE);
                PdfPCell2.setHorizontalAlignment(Element.ALIGN_LEFT);
                PdfPCell PdfPCell3 = new PdfPCell(paragraph5);
                PdfPCell3.setBorderColor(BaseColor.WHITE);
                PdfPCell3.setHorizontalAlignment(Element.ALIGN_CENTER);
                PdfPCell PdfPCell4 = new PdfPCell(paragraph6);
                PdfPCell4.setBorderColor(BaseColor.WHITE);
                PdfPCell4.setHorizontalAlignment(Element.ALIGN_CENTER);
                
                pdfPTable2.addCell(PdfPCell1);
                pdfPTable2.addCell(PdfPCell2);
                pdfPTable2.addCell(PdfPCell3);
                pdfPTable2.addCell(PdfPCell4);

                document.add(pdfPTable2);
            }

        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // đóng file
            if (document != null) {
                document.close();
            }
            JOptionPane.showMessageDialog(null, "In thành công", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
        }
    }
}

package MyCustom;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import QLTV.DTO.SACH;

public class ExportPDF {
    public ExportPDF() {

    }

    public void setExportPDFPM(String MaPM, String TenDG, String NgayMuon, String NgayTra, String SLtong, ArrayList<SACH> kq) {
        // tạo một document
        Document document = new Document();
        BaseFont bf = null;
        try {
            // khởi tạo một PdfWriter truyền vào document và FileOutputStream
            PdfWriter.getInstance(document, new FileOutputStream("D:\\HelloWorld.pdf"));
            // mở file để thực hiện viết
            document.open();
            // thêm nội dung sử dụng add function
            bf = BaseFont.createFont("font/ARIALUNI.TTF", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
            Font fontPDF = new Font(bf, 14);
            Paragraph paragraph = new Paragraph("PHIẾU MƯỢN SÁCH", new Font(bf, 20));
            paragraph.setAlignment(Element.ALIGN_CENTER);
            // Khai báo 2 paragraph
            Paragraph paragraph1 = new Paragraph("Mã phiếu mượn: " + MaPM + "\nTên độc giả: " + TenDG
                    + "\nNgày mượn: " + NgayMuon + "\nNgày trả: " + NgayTra + "\nSố lượng mượn: " + SLtong + "\n",
                    fontPDF);
            // Định dạng đoạn văn bản thứ nhất
            paragraph1.setAlignment(Element.ALIGN_LEFT);
            paragraph1.setSpacingAfter(16);
            // Đinh dạng đoạn văn bản thứ 2
            Paragraph paragraph2 = new Paragraph("Danh sách mượn\n", fontPDF);
            paragraph2.setSpacingBefore(16);
            paragraph2.setAlignment(Element.ALIGN_LEFT);
            PdfPTable pdfPTable1 = new PdfPTable(3);
            pdfPTable1.setSpacingBefore(16);
            // Create cells
            PdfPCell pdfPCell1 = new PdfPCell(new Paragraph("Mã sách",fontPDF));
            PdfPCell pdfPCell2 = new PdfPCell(new Paragraph("Tên sách",fontPDF));
            PdfPCell pdfPCell3 = new PdfPCell(new Paragraph("Số lượng",fontPDF));
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
                PdfPTable pdfPTable2 = new PdfPTable(3);
                SACH sach = kq.get(i);

                Paragraph paragraph3 = new Paragraph(sach.getMasach());
                Paragraph paragraph4 = new Paragraph(sach.getTensach(),fontPDF);
                Paragraph paragraph5 = new Paragraph(String.valueOf(sach.getSL()));

                PdfPCell PdfPCell1 = new PdfPCell(paragraph3);
                PdfPCell PdfPCell2 = new PdfPCell(paragraph4);
                PdfPCell PdfPCell3 = new PdfPCell(paragraph5);
                
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
}

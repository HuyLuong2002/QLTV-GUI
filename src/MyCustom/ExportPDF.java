package MyCustom;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;

import javax.swing.JTable;

public class ExportPDF {
    public ExportPDF() {

    }

    public void setExportPDF(String MaPM, String TenDG, String NgayMuon, String NgayTra, String SLtong) {
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
            Font fontPDF = new Font(bf,16);
            Paragraph paragraph = new Paragraph("Phiếu mượn sách",new Font(bf,20));
            paragraph.setAlignment(Element.ALIGN_CENTER);
            // Khai báo 2 paragraph
            Paragraph paragraph1 = new Paragraph("Mã phiếu mượn:\nTên độc giả:\nNgày mượn:\nNgày trả:\nSố lượng mượn:\n",fontPDF);
            // Định dạng đoạn văn bản thứ nhất
            paragraph1.setAlignment(Element.ALIGN_LEFT);
            paragraph1.setSpacingAfter(15);
            // Đinh dạng đoạn văn bản thứ 2
            // paragraph2.setSpacingBefore(15);
            // paragraph2.setAlignment(Element.ALIGN_LEFT);
            // Thêm 2 đoạn văn bản vào document
            document.add(paragraph);
            document.add(paragraph1);


            // đóng file
            document.close();

        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

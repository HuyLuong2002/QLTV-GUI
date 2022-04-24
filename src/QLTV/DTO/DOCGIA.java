package QLTV.DTO;

public class DOCGIA {
    private String MaDG;
    private String Tinhtrangthue;
    private String TenDG;
    private String Diachi;
    private String Mail;
    public DOCGIA(){}
    public DOCGIA(String MaDG,String Tinhtrangthue,String TenDG,String Diachi,String Mail){
        this.MaDG = MaDG;
        this.Tinhtrangthue = Tinhtrangthue;
        this.TenDG = TenDG;
        this.Diachi = Diachi;
        this.Mail = Mail;
    }
    public void setMaDG(String MaDG){
        this.MaDG = MaDG;
    }
    public void setTinhtrangthue(String Tinhtrangthue){
        this.Tinhtrangthue = Tinhtrangthue;
    }
    public void setTenDG(String TenDG){
        this.TenDG = TenDG;
    }
    public void setDiachi(String Diachi){
        this.Diachi = Diachi;
    }
    public void setMail(String Mail){
        this.Mail = Mail;
    }
    public String getMaDG(){
        return MaDG;
    }
    public String getTinhtrangthue(){
        return Tinhtrangthue;
    }
    public String getTenDG(){
        return TenDG;
    }
    public String getDiachi(){
        return Diachi;
    }
    public String getMail(){
        return Mail;
    }
}
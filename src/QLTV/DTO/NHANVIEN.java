package QLTV.DTO;

public class NHANVIEN {
    private String MaNV;
    private String TenNV;
    private int Chucvu;
    private int LuongCB;
    private int Phucap;
    private double Hesoluong;
    private int SDT;
    private String Mail;
    public NHANVIEN(){}
    public NHANVIEN(String MaNV,String TenNV,int Chucvu,int LuongCB,int Phucap,double Hesoluong,int SDT,String Mail){
        this.MaNV = MaNV;
        this.TenNV = TenNV;
        this.Chucvu = Chucvu;
        this.LuongCB = LuongCB;
        this.Phucap = Phucap;
        this.Hesoluong = Hesoluong;
        this.SDT = SDT;
        this.Mail = Mail;
    }
    public void setMaNV(String MaNV){
        this.MaNV = MaNV;
    }
    public void setTenNV(String TenNV){
        this.TenNV = TenNV;
    }
    public void setChucvu(int Chucvu){
        this.Chucvu = Chucvu;
    }
    public void setLuongCB(int LuongCB){
        this.LuongCB = LuongCB;
    }
    public void setPhucap(int Phucap){
        this.Phucap = Phucap;
    }
    public void setHesoluong(double Hesoluong){
        this.Hesoluong = Hesoluong;
    }
    public void setSDT(int SDT){
        this.SDT = SDT;
    }
    public void setMail(String Mail){
        this.Mail = Mail;
    }
    public String getMaNV(){
        return MaNV;
    }
    public String getTenNV(){
        return TenNV;
    }
    public int getChucvu(){
        return Chucvu;
    }
    public int getLuongCB(){
        return LuongCB;
    }
    public int getPhucap(){
        return Phucap;
    }
    public double getHesoluong(){
        return Hesoluong;
    }
    public int getSDT(){
        return SDT;
    }
    public String getMail(){
        return Mail;
    }
}

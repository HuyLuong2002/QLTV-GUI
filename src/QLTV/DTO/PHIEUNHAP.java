package QLTV.DTO;

public class PHIEUNHAP {
    private String MaPN;
    private String Ngaynhap;
    private int SLTong;
    private int Dongia;
    private String MaNV;
    private String TenNV;
    private String MaNCC;
    public PHIEUNHAP(){}
    public PHIEUNHAP(String MaPN,String Ngaynhap,int SLTong,int Dongia,String MaNV,String TenNV,String MaNCC){
        this.MaPN = MaPN;
        this.Ngaynhap = Ngaynhap;
        this.SLTong = SLTong;
        this.Dongia = Dongia;
        this.MaNV = MaNV;
        this.TenNV = TenNV;
        this.MaNCC = MaNCC;
    }
    public void setMaPN(String MaPN){
        this.MaPN = MaPN;
    }
    public void setNgaynhap(String Ngaynhap){
        this.Ngaynhap = Ngaynhap;
    }
    public void setSLTong(int SLTong){
        this.SLTong = SLTong;
    }
    public void setDongia(int Dongia){
        this.Dongia = Dongia;
    }
    public void setMaNV(String MaNV){
        this.MaNV = MaNV;
    }
    public void setTenNV(String TenNV){
        this.TenNV = TenNV;
    }
    public void setMaNCC(String MaNCC){
        this.MaNCC = MaNCC;
    }
    public String getMaPN(){
        return MaPN;
    }
    public String getNgaynhap(){
        return Ngaynhap;
    }
    public int getSLTong(){
        return SLTong;
    }
    public int getDongia(){
        return Dongia;
    }
    public String getMaNV(){
        return MaNV;
    }
    public String getTenNV(){
        return TenNV;
    }
    public String getMaNCC(){
        return MaNCC;
    }
}

package QLTV.DTO;

public class TACGIA {
    private String MaTacGia;
    private String TenTacGia;
    public TACGIA(){};
    public TACGIA(String MaTacGia ,String TenTacGia){
        this.MaTacGia = MaTacGia;
        this.TenTacGia = TenTacGia;
    }
    public void setMaTacGia(String MaTacGia){
        this.MaTacGia = MaTacGia;
    }
    public void setTenTacGia(String TenTacGia){
        this.TenTacGia = TenTacGia;
    }
    public String getMaTacGia(){
        return MaTacGia;
    }
    public String getTenTacGia(){
        return TenTacGia;
    }
}

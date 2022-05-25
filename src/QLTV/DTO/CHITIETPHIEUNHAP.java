package QLTV.DTO;

public class CHITIETPHIEUNHAP {
    private String MaPN;
    private String Masach;
    private int SL;
    public CHITIETPHIEUNHAP(){}
    public CHITIETPHIEUNHAP(String MaPN,String Masach,int SL){
        this.MaPN = MaPN;
        this.Masach = Masach;
        this.SL = SL;
    }
    public void setMaPN(String MaPN){
        this.MaPN = MaPN;
    }
    public void setMasach(String Masach){
        this.Masach = Masach;
    }
    public void setSL(int SL){
        this.SL = SL;
    }
    public String getMaPN(){
        return MaPN;
    }
    public String getMasach(){
        return Masach;
    }
    public int getSL(){
        return SL;
    }
}

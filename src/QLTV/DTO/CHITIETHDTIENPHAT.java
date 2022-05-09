package QLTV.DTO;

public class CHITIETHDTIENPHAT {
    private String MaHD;
    private String Masach;
    private int Dongia;
    private int SL;
    public CHITIETHDTIENPHAT(){}
    public CHITIETHDTIENPHAT(String MaHD,String Masach,int Dongia,int SL){
        this.MaHD = MaHD;
        this.Masach = Masach;
        this.Dongia = Dongia;
        this.SL = SL;
    }
    public void setMaHD(String MaHD){
        this.MaHD = MaHD;
    }
    public void setMasach(String Masach){
        this.Masach = Masach;
    }
    public void setDongia(int Dongia){
        this.Dongia = Dongia;
    }
    public void setSL(int SL){
        this.SL = SL;
    }
    public String getMaHD(){
        return MaHD;
    }
    public String getMasach(){
        return Masach;
    }
    public int getDongia(){
        return Dongia;
    }
    public int getSL(){
        return SL;
    }
}
